package com.github.scribejava.apis.polar;

import com.github.scribejava.core.model.OAuth2AccessToken;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Map;

public class PolarOAuth2AccessToken extends OAuth2AccessToken {

    private static final long serialVersionUID = 1L;
    private final String userId;

    // Coverage Luis
    private static final ConcurrentHashMap<String, AtomicBoolean> branchCoverage = new ConcurrentHashMap<>();

    static {
        branchCoverage.put("branch_1", new AtomicBoolean(false)); // this == obj
        branchCoverage.put("branch_2", new AtomicBoolean(false)); // obj == null
        branchCoverage.put("branch_3", new AtomicBoolean(false)); // getClass() != obj.getClass()
        branchCoverage.put("branch_4", new AtomicBoolean(false)); // !super.equals(obj)
        branchCoverage.put("branch_5", new AtomicBoolean(false)); // Objects.equals(userId, ((PolarOAuth2AccessToken) obj).getUserId())

        // Print info
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (System.out) {
                    System.out.println("PolarOAuth2AccessToken.java completed method coverage:");
                    for (Map.Entry<String, AtomicBoolean> entry : branchCoverage.entrySet()) {
                        System.out.println(entry.getKey() + ": " + (entry.getValue().get() ? "Taken" : "Not taken"));
                    }
                }
            }
        }));
    }
    // =================================== Coverage =====================================================

    public PolarOAuth2AccessToken(String accessToken, String tokenType, Integer expiresIn, String refreshToken,
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
            branchCoverage.get("branch_1").set(true);
            return true;
        }
        // ID: branch_2
        if (obj == null) {
            branchCoverage.get("branch_2").set(true);
            return false;
        }
        // ID: branch_3
        if (getClass() != obj.getClass()) {
            branchCoverage.get("branch_3").set(true);
            return false;
        }
        // ID: branch_4
        if (!super.equals(obj)) {
            branchCoverage.get("branch_4").set(true);
            return false;
        }
        // ID: branch_5
        boolean userIdEquals = Objects.equals(userId, ((PolarOAuth2AccessToken) obj).getUserId());
        branchCoverage.get("branch_5").set(true);
        return userIdEquals;
    }
}
