package com.github.scribejava.httpclient.apache;

import com.github.scribejava.core.httpclient.AbstractAsyncOnlyHttpClient;
import com.github.scribejava.core.httpclient.multipart.MultipartPayload;
import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

public class ApacheHttpClient extends AbstractAsyncOnlyHttpClient {

    private final CloseableHttpAsyncClient client;
    //data structure for info about the branches
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();
    static {
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_1", new AtomicBoolean(false)); // GET
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_2", new AtomicBoolean(false)); // PUT
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_3", new AtomicBoolean(false)); // DELETE
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_4", new AtomicBoolean(false)); // HEAD
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_5", new AtomicBoolean(false)); // POST
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_6", new AtomicBoolean(false)); // PATCH
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_7", new AtomicBoolean(false)); // TRACE
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_8", new AtomicBoolean(false)); // OPTIONS
        branchCoverage.put("ApacheHttpClient.getRequestBuilder.branch_9", new AtomicBoolean(false)); // DEFAULT
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (System.out) {
                    System.out.println("ApacheHttpClient.java getRequestBuilder method coverage:");
                    for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                        System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                    }
                }
            }
        }));
    }

    public ApacheHttpClient() {
        this(ApacheHttpClientConfig.defaultConfig());
    }

    public ApacheHttpClient(ApacheHttpClientConfig config) {
        this(config.getHttpAsyncClientBuilder());
    }

    public ApacheHttpClient(HttpAsyncClientBuilder builder) {
        this(builder.build());
    }

    public ApacheHttpClient(CloseableHttpAsyncClient client) {
        this.client = client;
        this.client.start();
    }

    @Override
    public void close() throws IOException {
        client.close();
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            byte[] bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new ByteArrayEntity(bodyContents);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            MultipartPayload bodyContents, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {

        throw new UnsupportedOperationException("ApacheHttpClient does not support MultipartPayload yet.");
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            String bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new StringEntity(bodyContents, StandardCharsets.UTF_8);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    @Override
    public <T> Future<T> executeAsync(String userAgent, Map<String, String> headers, Verb httpVerb, String completeUrl,
            File bodyContents, OAuthAsyncRequestCallback<T> callback, OAuthRequest.ResponseConverter<T> converter) {
        final HttpEntity entity = bodyContents == null ? null : new FileEntity(bodyContents);
        return doExecuteAsync(userAgent, headers, httpVerb, completeUrl, entity, callback, converter);
    }

    private <T> Future<T> doExecuteAsync(String userAgent, Map<String, String> headers, Verb httpVerb,
            String completeUrl, HttpEntity entity, OAuthAsyncRequestCallback<T> callback,
            OAuthRequest.ResponseConverter<T> converter) {
        final RequestBuilder builder = getRequestBuilder(httpVerb);
        builder.setUri(completeUrl);

        if (httpVerb.isPermitBody()) {
            if (!headers.containsKey(CONTENT_TYPE)) {
                builder.addHeader(CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            }
            builder.setEntity(entity);
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }

        if (userAgent != null) {
            builder.setHeader(OAuthConstants.USER_AGENT_HEADER_NAME, userAgent);
        }
        final OAuthAsyncCompletionHandler<T> handler = new OAuthAsyncCompletionHandler<>(callback, converter);
        final Future<HttpResponse> future = client.execute(builder.build(), handler);
        return new ApacheHttpFuture<>(future, handler);
    }

    //branch coverage: Tomas
    public static RequestBuilder getRequestBuilder(Verb httpVerb) {
        switch (httpVerb) {
            //ID: ApacheHttpClient.getRequestBuilder.branch_1
            case GET:
                branchCoverage.get("branch_1").set(true);
                return RequestBuilder.get();
            //ID: ApacheHttpClient.getRequestBuilder.branch_2
            case PUT:
                branchCoverage.get("branch_2").set(true);
                return RequestBuilder.put();
            //ID: ApacheHttpClient.getRequestBuilder.branch_3 
            case DELETE:
                branchCoverage.get("branch_3").set(true);
                return RequestBuilder.delete();
            //ID: ApacheHttpClient.getRequestBuilder.branch_4    
            case HEAD:
                branchCoverage.get("branch_4").set(true);
                return RequestBuilder.head();
            //ID: ApacheHttpClient.getRequestBuilder.branch_5    
            case POST:
                branchCoverage.get("branch_5").set(true);
                return RequestBuilder.post();
            //ID: ApacheHttpClient.getRequestBuilder.branch_6
            case PATCH:
                branchCoverage.get("branch_6").set(true);
                return RequestBuilder.patch();
            //ID: ApacheHttpClient.getRequestBuilder.branch_7
            case TRACE:
                branchCoverage.get("branch_7").set(true);
                return RequestBuilder.trace();
            //ID: ApacheHttpClient.getRequestBuilder.branch_8
            case OPTIONS:
                branchCoverage.get("branch_8").set(true);
                return RequestBuilder.options();
            //ID: ApacheHttpClient.getRequestBuilder.branch_9
            default:
                branchCoverage.get("branch_9").set(true);
                throw new IllegalArgumentException("message build error: unknown verb type");
        }
    }
}
