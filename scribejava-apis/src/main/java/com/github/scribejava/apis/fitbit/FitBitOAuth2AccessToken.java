package com.github.scribejava.apis.fitbit;

import com.github.scribejava.core.model.OAuth2AccessToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

public class FitBitOAuth2AccessToken extends OAuth2AccessToken {

    private static final long serialVersionUID = -6374486860742407411L;
    private final String userId;

    // Coverage Luis
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();

    static {
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_1", new AtomicBoolean(false)); // this == obj
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_1_else", new AtomicBoolean(false)); // this != obj
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_2", new AtomicBoolean(false)); // obj == null
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_2_else", new AtomicBoolean(false)); // obj != null
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_3", new AtomicBoolean(false)); // getClass() != obj.getClass()
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_3_else", new AtomicBoolean(false)); // getClass() == obj.getClass()
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_4", new AtomicBoolean(false)); // !super.equals(obj)
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_4_else", new AtomicBoolean(false)); // super.equals(obj)
        branchCoverage.put("FitBitOAuth2AccessToken.equals.branch_5", new AtomicBoolean(false)); // Objects.equals(userId, ((FitBitOAuth2AccessToken) obj).getUserId())

        // Print info
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (System.out) {
                    System.out.println("FitBitOAuth2AccessToken.java completed method coverage:");
                    for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                        System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                    }
                }
            }
        }));
    }
    // ============================== Coverage ====================================

    public FitBitOAuth2AccessToken(String accessToken, String openIdToken, String rawResponse) {
        this(accessToken, null, null, null, null, openIdToken, rawResponse);
    }

    public FitBitOAuth2AccessToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken,
                                   String scope, String userId, String rawResponse) {
        super(accessToken, tokenType, expiresIn, refreshToken, scope, rawResponse);
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + Objects.hashCode(userId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        // ID: branch_1
        if (this == obj) {
            branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_1").set(true);
            return true;
        }
        branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_1_else").set(true);

        // ID: branch_2
        if (obj == null) {
            branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_2").set(true);
            return false;
        }
        // Invisible else
        branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_2_else").set(true);

        // ID: branch_3
        if (getClass() != obj.getClass()) {
            branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_3").set(true);
            return false;
        }
        // Invisible else
        branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_3_else").set(true);

        // ID: branch_4
        if (!super.equals(obj)) {
            branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_4").set(true);
            return false;
        }
        // Invisible else
        branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_4_else").set(true);

        // ID: branch_5
        branchCoverage.get("FitBitOAuth2AccessToken.equals.branch_5").set(true);
        return Objects.equals(userId, ((FitBitOAuth2AccessToken) obj).getUserId());
    }
}
