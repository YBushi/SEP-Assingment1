package com.github.scribejava.apis.polar;

import com.github.scribejava.core.model.OAuth2AccessToken;
import org.junit.Test;
import static org.junit.Assert.*;

public class PolarOAuth2AccessTokenTest {

    @Test
    public void testEqualsSameObject() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");
        PolarOAuth2AccessToken token4 = token1;

        // Same object comparison (branch_1)
        assertTrue(token1.equals(token4));
    }

    @Test
    public void testEqualsNullObject() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");

        // Null comparison (branch_2)
        assertFalse(token1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");

        // Different class comparison (branch_3)
        assertFalse(token1.equals("some string"));
    }

    @Test
    public void testEqualsDifferentToken() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token1", "type1", 3600, "refresh1", "scope1", "user1", "raw1");
        PolarOAuth2AccessToken token2 = new PolarOAuth2AccessToken("token2", "type2", 3600, "refresh2", "scope2", "user2", "raw2");

        // Different object comparison (branch_4)
        assertFalse(token1.equals(token2));
    }

    @Test
    public void testEqualsSameToken() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");
        PolarOAuth2AccessToken token3 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");

        // Same values comparison (branch_5)
        assertTrue(token1.equals(token3));
    }

    @Test
    public void testEqualsDifferentUserId() {
        PolarOAuth2AccessToken token1 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user1", "raw");
        PolarOAuth2AccessToken token3 = new PolarOAuth2AccessToken("token", "type", 3600, "refresh", "scope", "user2", "raw");

        // Different userId comparison (branch_5)
        assertFalse(token1.equals(token3));
    }
}