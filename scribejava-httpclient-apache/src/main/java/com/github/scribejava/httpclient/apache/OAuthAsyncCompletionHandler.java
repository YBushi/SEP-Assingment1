package com.github.scribejava.httpclient.apache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.concurrent.FutureCallback;

import com.github.scribejava.core.model.OAuthAsyncRequestCallback;
import com.github.scribejava.core.model.OAuthRequest.ResponseConverter;
import com.github.scribejava.core.model.Response;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpEntity;

public class OAuthAsyncCompletionHandler<T> implements FutureCallback<HttpResponse> {

    private final OAuthAsyncRequestCallback<T> callback;
    private final ResponseConverter<T> converter;
    private final CountDownLatch latch;
    private T result;
    private Exception exception;
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();
    static {
        branchCoverage.put("branch_1", new AtomicBoolean(false)); // loop over headers
        branchCoverage.put("branch_2", new AtomicBoolean(false)); // httpEntity null check
        branchCoverage.put("branch_3", new AtomicBoolean(false)); // converter null check
        branchCoverage.put("branch_4", new AtomicBoolean(false)); // callback null check (onCompleted)
        branchCoverage.put("branch_5", new AtomicBoolean(false)); // IOException/RuntimeException catch block
        branchCoverage.put("branch_6", new AtomicBoolean(false)); // callback null check (onThrowable)
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (System.out) {
                    System.out.println("OAuthAsyncCompletionHandler.java completed method coverage:");
                    for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                        System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                    }
                }
            }
        }));
    }

    public OAuthAsyncCompletionHandler(OAuthAsyncRequestCallback<T> callback, ResponseConverter<T> converter) {
        this.callback = callback;
        this.converter = converter;
        this.latch = new CountDownLatch(1);
    }

    //branch coverage Tomas
    @Override
    public void completed(HttpResponse httpResponse) {
        try {
            final Map<String, String> headersMap = new HashMap<>();
            // ID: branch_1
            for (Header header : httpResponse.getAllHeaders()) {
                headersMap.put(header.getName(), header.getValue());
            }
    
            final StatusLine statusLine = httpResponse.getStatusLine();
            final HttpEntity httpEntity = httpResponse.getEntity();
    
            // ID: branch_2
            if (httpEntity == null) {
                branchCoverage.get("branch_2").set(true);
                result = null; // Ensure result is null if httpEntity is null
            } else {
                branchCoverage.get("branch_2").set(true);
                final InputStream contentStream = httpEntity.getContent();
                final Response response = new Response(
                    statusLine.getStatusCode(),
                    statusLine.getReasonPhrase(),
                    headersMap,
                    contentStream,
                    contentStream
                );
    
                @SuppressWarnings("unchecked")
                final T t = converter == null ? (T) response : converter.convert(response);
                // ID: branch_3
                if (converter == null) {
                    branchCoverage.get("branch_3").set(true);
                } else {
                    branchCoverage.get("branch_3").set(true);
                }
                result = t;
    
                // ID: branch_4
                if (callback != null) {
                    branchCoverage.get("branch_4").set(true);
                    callback.onCompleted(result);
                }
            }
        } catch (IOException | RuntimeException e) {
            branchCoverage.get("branch_5").set(true);
            exception = e;
            if (callback != null) {
                branchCoverage.get("branch_6").set(true);
                callback.onThrowable(e);
            }
        } finally {
            latch.countDown();
        }
    }

    @Override
    public void failed(Exception e) {
        exception = e;
        try {
            if (callback != null) {
                callback.onThrowable(e);
            }
        } finally {
            latch.countDown();
        }
    }

    @Override
    public void cancelled() {
        exception = new CancellationException();
        try {
            if (callback != null) {
                callback.onThrowable(exception);
            }
        } finally {
            latch.countDown();
        }
    }

    public T getResult() throws InterruptedException, ExecutionException {
        latch.await();
        if (exception != null) {
            throw new ExecutionException(exception);
        }
        return result;
    }

    public T getResult(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {

        if (!latch.await(timeout, unit)) {
            throw new TimeoutException();
        }
        if (exception != null) {
            throw new ExecutionException(exception);
        }
        return result;
    }
}
