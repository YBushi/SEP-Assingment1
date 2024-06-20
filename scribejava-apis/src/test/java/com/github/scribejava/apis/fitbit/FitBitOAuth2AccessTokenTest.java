package com.github.scribejava.apis.fitbit;

import org.junit.Test;
import static org.junit.Assert.*;

// Luis Added tests
public class FitBitOAuth2AccessTokenTest {

    @Test
    public void testSameObjectComparison() {
        FitBitOAuth2AccessToken token1 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");
        FitBitOAuth2AccessToken token4 = token1;

        // Same object comparison 
        assertTrue(token1.equals(token4)); // true, should set branch_1 to true
    }

    @Test
    public void testNullComparison() {
        FitBitOAuth2AccessToken token1 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");

        // Null comparison 
        assertFalse(token1.equals(null)); // false, should set branch_2 to true
    }

    @Test
    public void testDifferentClassComparison() {
        FitBitOAuth2AccessToken token1 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");

        // Different class comparison 
        assertFalse(token1.equals("some string")); // false, should set branch_3 to true
    }

    @Test
    public void testDifferentObjectComparison() {
        FitBitOAuth2AccessToken token1 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");
        FitBitOAuth2AccessToken token2 = new FitBitOAuth2AccessToken("token2", "openIdToken2", "rawResponse2");

        // Different object comparison
        assertFalse(token1.equals(token2)); // false, should set branch_4 to true
    }

    @Test
    public void testPrintUserIdComparison() {
        FitBitOAuth2AccessToken token1 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");
        FitBitOAuth2AccessToken token3 = new FitBitOAuth2AccessToken("token1", "openIdToken1", "rawResponse1");

        // Print userId comparison (branch_5)
        assertTrue(token1.equals(token3)); // true, should set branch_5 to true
    }
}