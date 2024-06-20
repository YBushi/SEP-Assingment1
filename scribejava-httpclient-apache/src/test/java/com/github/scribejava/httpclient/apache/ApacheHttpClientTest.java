package com.github.scribejava.httpclient.apache;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.concurrent.Future;

import org.apache.http.client.methods.RequestBuilder;
import org.junit.Test;

import com.github.scribejava.core.AbstractClientTest;
import com.github.scribejava.core.httpclient.HttpClient;
import com.github.scribejava.core.model.Verb;

public class ApacheHttpClientTest extends AbstractClientTest {

    @Override
    protected HttpClient createNewClient() {
        return new ApacheHttpClient();
    }

    // new tests
    @Test
    public void testGetRequestBuilderGet() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.GET);
        assertTrue(builder.getMethod().equalsIgnoreCase("GET"));
    }

    @Test
    public void testGetRequestBuilderPut() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.PUT);
        assertTrue(builder.getMethod().equalsIgnoreCase("PUT"));
    }

    @Test
    public void testGetRequestBuilderPost() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.POST);
        assertTrue(builder.getMethod().equalsIgnoreCase("POST"));
    }

    @Test
    public void testGetRequestBuilderDelete() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.DELETE);
        assertTrue(builder.getMethod().equalsIgnoreCase("DELETE"));
    }

    @Test
    public void testGetRequestBuilderHead() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.HEAD);
        assertTrue(builder.getMethod().equalsIgnoreCase("HEAD"));
    }

    @Test
    public void testGetRequestBuilderOptions() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.OPTIONS);
        assertTrue(builder.getMethod().equalsIgnoreCase("OPTIONS"));
    }

    @Test
    public void testGetRequestBuilderPatch() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.PATCH);
        assertTrue(builder.getMethod().equalsIgnoreCase("PATCH"));
    }

    @Test
    public void testGetRequestBuilderTrace() {
        RequestBuilder builder = ApacheHttpClient.getRequestBuilder(Verb.TRACE);
        assertTrue(builder.getMethod().equalsIgnoreCase("TRACE"));
    }
}
