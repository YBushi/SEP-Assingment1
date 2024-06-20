package com.github.scribejava.core.oauth;

import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.scribejava.core.builder.api.DefaultApi10a;
import com.github.scribejava.core.builder.api.OAuth1SignatureType;
import com.github.scribejava.core.extractors.HeaderExtractor;
import com.github.scribejava.core.model.OAuthConstants;
import com.github.scribejava.core.model.OAuthRequest;

// Test Created by Luis
public class OAuth10aServiceTest {
    
    private DefaultApi10a mockApi;
    private HeaderExtractor mockHeaderExtractor;
    private OAuthRequest mockRequest;
    private OAuth10aService oAuth10aService;

    @Before
    public void setup() {
        mockApi = mock(DefaultApi10a.class);
        mockHeaderExtractor = mock(HeaderExtractor.class);
        mockRequest = mock(OAuthRequest.class);
        
        when(mockApi.getHeaderExtractor()).thenReturn(mockHeaderExtractor);
        
        oAuth10aService = new OAuth10aService(mockApi, "apiKey", "apiSecret", "callback", "scope",
                null, "userAgent", null, null);
    }

    @Test
    public void testAppendSignatureHeader() {
        // Branch #1: HEADER 
        when(mockApi.getSignatureType()).thenReturn(OAuth1SignatureType.HEADER);
        String oauthHeader = "value";
        when(mockHeaderExtractor.extract(mockRequest)).thenReturn(oauthHeader);
    
        oAuth10aService.appendSignature(mockRequest);
    
        verify(mockRequest).addHeader(OAuthConstants.HEADER, oauthHeader); // Expected result: true
    }
    
    
    @Test
    public void testAppendSignatureQueryString() {
        // Branch #2: QUERY_STRING 
        when(mockApi.getSignatureType()).thenReturn(OAuth1SignatureType.QUERY_STRING);
        Map<String, String> oauthParameters = new HashMap<>();
        oauthParameters.put("param1", "value1");
        oauthParameters.put("param2", "value2");
        when(mockRequest.getOauthParameters()).thenReturn(oauthParameters);
    
        oAuth10aService.appendSignature(mockRequest);
     
        verify(mockRequest).addQuerystringParameter("param1", "value1");  // Expected results for both: true
        verify(mockRequest).addQuerystringParameter("param2", "value2");  
    }
    
    @Test(expected = IllegalStateException.class)
    public void testAppendSignatureUnknownType() {
        // Branch #3: UNKNOWN 
        when(mockApi.getSignatureType()).thenReturn(OAuth1SignatureType.UNKNOWN);
    
        oAuth10aService.appendSignature(mockRequest);    
        // Expected result: true (Exception thrown)
    }
    
}
