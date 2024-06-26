package com.github.scribejava.httpclient.armeria;

import static java.util.Objects.requireNonNull;

import com.github.scribejava.core.httpclient.AbstractAsyncOnlyHttpClient;
import com.github.scribejava.core.httpclient.multipart.MultipartPayload;
import com.github.scribejava.core.httpclient.multipart.MultipartUtils;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.common.AggregatedHttpResponse;
import com.linecorp.armeria.common.HttpData;
import com.linecorp.armeria.common.HttpMethod;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpStatus;
import com.linecorp.armeria.common.MediaType;
import com.linecorp.armeria.common.RequestHeaders;
import com.linecorp.armeria.common.RequestHeadersBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

/**
 * An implementation of {@link AbstractAsyncOnlyHttpClient} based on
 * <a href="https://line.github.io/armeria/">Armeria HTTP client</a>.
 */
public class ArmeriaHttpClient extends AbstractAsyncOnlyHttpClient {

    /**
     * A builder of new instances of Armeria's {@link WebClient}
     */
    private final ArmeriaWebClientBuilder clientBuilder;
    /**
     * A list of cached Endpoints. It helps avoiding building a new Endpoint per
     * each request.
     */
    private final Map<String, WebClient> httpClients = new HashMap<>();
    /**
     * A read/write lock to access the list of cached Endpoints concurrently.
     */
    private final ReentrantReadWriteLock httpClientsLock = new ReentrantReadWriteLock();

    public ArmeriaHttpClient() {
        this(ArmeriaHttpClientConfig.defaultConfig());
    }

    public ArmeriaHttpClient(ArmeriaHttpClientConfig config) {
        clientBuilder = config.createClientBuilder();
    }

    /**
     * Cleans up the list of cached Endpoints.
     */
    @Override
    public void close() {
        final Lock writeLock = httpClientsLock.writeLock();
        writeLock.lock();
        try {
            httpClients.clear();
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            byte[] bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, new BytesBody(bodyContents), callback,
                converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            MultipartPayload bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, new MultipartBody(bodyContents), callback,
                converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            String bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, new StringBody(bodyContents), callback,
                converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            File bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, new FileBody(bodyContents), callback,
                converter);
    }

    private <T> CompletableFuture<T> doExecuteAsync(String userAgent, Map<String, String> headers, Verb httpVerb,
            String completeUrl, Supplier<HttpData> contentSupplier, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {
        // Get the URI and Path
        final URI uri = URI.create(completeUrl);
        final String path = getServicePath(uri);

        // Fetch/Create WebClient instance for a given Endpoint
        final WebClient client = getClient(uri);

        // Build HTTP request
        final RequestHeadersBuilder headersBuilder = RequestHeaders.of(getHttpMethod(httpVerb), path).toBuilder();

        headersBuilder.add(headers.entrySet());
        if (userAgent != null) {
            headersBuilder.add(OAuthConstants.USER_AGENT_HEADER_NAME, userAgent);
        }

        // Build the request body and execute HTTP request
        final HttpResponse response;
        if (httpVerb.isPermitBody()) { // POST, PUT, PATCH and DELETE methods
            final HttpData contents = contentSupplier.get();
            if (httpVerb.isRequiresBody() && contents == null) { // POST or PUT methods
                throw new IllegalArgumentException("Contents missing for request method " + httpVerb.name());
            }

            if (headersBuilder.contentType() == null) {
                headersBuilder.contentType(MediaType.FORM_DATA);
            }

            if (contents != null) {
                response = client.execute(headersBuilder.build(), contents);
            } else {
                response = client.execute(headersBuilder.build());
            }
        } else {
            response = client.execute(headersBuilder.build());
        }

        // Aggregate HTTP response (asynchronously) and return the result Future
        return response.aggregate()
                .thenApply(aggregatedResponse -> whenResponseComplete(callback, converter, aggregatedResponse))
                .exceptionally(throwable -> completeExceptionally(callback, throwable));
    }

    /**
     * Provides an instance of {@link WebClient} for a given endpoint {@link URI}
     * based on an endpoint as
     * {@code scheme://authority}.
     *
     * @param uri an endpoint {@link URI}
     * @return {@link WebClient} instance
     */
    private WebClient getClient(URI uri) {
        final String endpoint = getEndPoint(uri);

        WebClient client;
        final Lock readLock = httpClientsLock.readLock();
        readLock.lock();
        try {
            client = httpClients.get(endpoint);
        } finally {
            readLock.unlock();
        }

        if (client != null) {
            return client;
        }

        client = clientBuilder.newWebClient(
                requireNonNull(uri.getScheme(), "scheme"),
                requireNonNull(uri.getAuthority(), "authority"));

        final Lock writeLock = httpClientsLock.writeLock();
        writeLock.lock();
        try {
            if (!httpClients.containsKey(endpoint)) {
                httpClients.put(endpoint, client);
                return client;
            } else {
                return httpClients.get(endpoint);
            }
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * Extracts {@code scheme} and {@code authority} portion of the {@link URI}.
     *
     * Assuming the {@link URI} as the following:
     * {@code URI = scheme:[//authority]path[?query][#fragment]}
     */
    private static String getEndPoint(URI uri) {
        return requireNonNull(uri.getScheme(), "scheme") + "://" + requireNonNull(uri.getAuthority(), "authority");
    }

    private static boolean[] getServiceCaseCoverage = new boolean[4]; // Data structure

    /**
     * Extracts {@code path}, {@code query} and {@code fragment} portion of the
     * {@link URI}.
     *
     * Assuming the {@link URI} as the following:
     * {@code URI = scheme:[//authority]path[?query][#fragment]}
     */
    public static String getServicePath(URI uri) {
        final StringBuilder builder = new StringBuilder()
                .append(requireNonNull(uri.getPath(), "path"));
        final String query = uri.getQuery();
        if (query != null) {
            getServiceCaseCoverage[0] = true; // ID: ArmeriaHttpClient.getServicePath.branch_1
            builder.append('?').append(query);
        } else {
            // If query is null do nothing. This is purely for invisible eye coverage
            getServiceCaseCoverage[1] = true; // ID: ArmeriaHttpClient.getServicePath.branch_2
        }
        final String fragment = uri.getFragment();
        if (fragment != null) {
            getServiceCaseCoverage[2] = true; // ID: ArmeriaHttpClient.getServicePath.branch_2
            builder.append('#').append(fragment);
        } else {
            // If fragment is null do nothing. This is purely for invisible eye coverage
            getServiceCaseCoverage[3] = true; // ID: ArmeriaHttpClient.getServicePath.branch_3
        }
        return builder.toString();
    }

    private static void printCoverage() {
        System.out.println("GetHttpMethod Coverage report:");
        for (int i = 0; i < getHttpCaseCoverage.length; i++) {
            String coverageStatus = getHttpCaseCoverage[i] ? "Taken" : "Not taken";
            System.out.println("ArmeriaHttpClient.getHttpMethod.branch_" + (i + 1) + ": " + coverageStatus);
        }

        System.out.println("GetService Coverage report:");
        for (int i = 0; i < getServiceCaseCoverage.length; i++) {
            String coverageStatus = getServiceCaseCoverage[i] ? "Taken" : "Not taken";
            System.out.println("ArmeriaHttpClient.getServicePath.branch_" + (i + 1) + ": " + coverageStatus);
        }
    }

    static {
        // Ensure the coverage report is printed when the class is loaded
        Runtime.getRuntime().addShutdownHook(new Thread(ArmeriaHttpClient::printCoverage));
    }

    /**
     * Maps {@link Verb} to {@link HttpMethod}
     *
     * @param httpVerb a {@link Verb} to match with {@link HttpMethod}
     * @return {@link HttpMethod} corresponding to the parameter
     */

    private static boolean[] getHttpCaseCoverage = new boolean[9];

    public static HttpMethod getHttpMethod(Verb httpVerb) {
        switch (httpVerb) {
            case GET:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_1
                getHttpCaseCoverage[0] = true;
                return HttpMethod.GET;
            case POST:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_2
                getHttpCaseCoverage[1] = true;
                return HttpMethod.POST;
            case PUT:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_3
                getHttpCaseCoverage[2] = true;
                return HttpMethod.PUT;
            case DELETE:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_4
                getHttpCaseCoverage[3] = true;
                return HttpMethod.DELETE;
            case HEAD:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_5
                getHttpCaseCoverage[4] = true;
                return HttpMethod.HEAD;
            case OPTIONS:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_6
                getHttpCaseCoverage[5] = true;
                return HttpMethod.OPTIONS;
            case TRACE:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_7
                getHttpCaseCoverage[6] = true;
                return HttpMethod.TRACE;
            case PATCH:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_8
                getHttpCaseCoverage[7] = true;
                return HttpMethod.PATCH;
            default:
                // ID: ArmeriaHttpClient.getHttpMethod.branch_9
                getHttpCaseCoverage[8] = true;
                throw new IllegalArgumentException(
                        "message build error: unsupported HTTP method: " + httpVerb.name());
        }
    }

    // Response asynchronous handlers
    /**
     * Converts {@link AggregatedHttpResponse} to {@link Response}
     *
     * @param aggregatedResponse an instance of {@link AggregatedHttpResponse} to
     *                           convert to {@link Response}
     * @return a {@link Response} converted from {@link AggregatedHttpResponse}
     */
    private Response convertResponse(AggregatedHttpResponse aggregatedResponse) {
        final Map<String, String> headersMap = new HashMap<>();
        aggregatedResponse.headers().forEach((header, value) -> headersMap.put(header.toString(), value));

        final HttpStatus status = aggregatedResponse.status();
        final InputStream inputStream = aggregatedResponse.content().toInputStream();

        return new Response(status.code(), status.reasonPhrase(), headersMap, inputStream, inputStream);
    }

    /**
     * Converts {@link AggregatedHttpResponse} to {@link Response} upon its
     * aggregation completion and invokes
     * {@link OAuthAsyncRequestCallback} for it.
     *
     * @param callback           a {@link OAuthAsyncRequestCallback} callback to
     *                           invoke upon response completion
     * @param converter          an optional {@link OAuthRequest.ResponseConverter}
     *                           result converter for {@link Response}
     * @param aggregatedResponse a source {@link AggregatedHttpResponse} to handle
     * @param <T>                converter {@link OAuthRequest.ResponseConverter}
     *                           specific type or {@link Response}
     * @return either instance of {@link Response} or converted result based on
     *         {@link OAuthRequest.ResponseConverter}
     */
    private <T> T whenResponseComplete(OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter, AggregatedHttpResponse aggregatedResponse) {
        final Response response = convertResponse(aggregatedResponse);
        try {
            @SuppressWarnings("unchecked")
            final T t = converter == null ? (T) response : converter.convert(response);
            if (callback != null) {
                callback.onCompleted(t);
            }
            return t;
        } catch (IOException | RuntimeException e) {
            return completeExceptionally(callback, e);
        }
    }

    /**
     * Invokes {@link OAuthAsyncRequestCallback} upon {@link Throwable} error result
     *
     * @param callback  a {@link OAuthAsyncRequestCallback} callback to invoke upon
     *                  response completion
     * @param throwable a {@link Throwable} error result
     * @param <T>       converter {@link OAuthRequest.ResponseConverter} specific
     *                  type or {@link Response}
     * @return null
     */
    private <T> T completeExceptionally(OAuthAsyncRequestCallback<T> callback, Throwable throwable) {
        if (callback != null) {
            callback.onThrowable(throwable);
        }
        return null;
    }

    // Body type suppliers
    private static class BytesBody implements Supplier<HttpData> {

        private final byte[] bodyContents;

        BytesBody(byte[] bodyContents) {
            this.bodyContents = bodyContents;
        }

        @Override
        public HttpData get() {
            return (bodyContents != null) ? HttpData.wrap(bodyContents) : null;
        }
    }

    private static class StringBody implements Supplier<HttpData> {

        private final String bodyContents;

        StringBody(String bodyContents) {
            this.bodyContents = bodyContents;
        }

        @Override
        public HttpData get() {
            return (bodyContents != null) ? HttpData.ofUtf8(bodyContents) : null;
        }
    }

    private static class FileBody implements Supplier<HttpData> {

        private final File bodyContents;

        FileBody(File bodyContents) {
            this.bodyContents = bodyContents;
        }

        @Override
        public HttpData get() {
            try {
                return (bodyContents != null)
                        ? HttpData.wrap(Files.readAllBytes(bodyContents.toPath()))
                        : null;
            } catch (IOException ioE) {
                throw new RuntimeException(ioE);
            }
        }
    }

    private static class MultipartBody implements Supplier<HttpData> {

        private final MultipartPayload bodyContents;

        MultipartBody(MultipartPayload bodyContents) {
            this.bodyContents = bodyContents;
        }

        @Override
        public HttpData get() {
            try {
                return (bodyContents != null)
                        ? HttpData.wrap(MultipartUtils.getPayload(bodyContents).toByteArray())
                        : null;
            } catch (IOException ioE) {
                throw new RuntimeException(ioE);
            }
        }
    }
}