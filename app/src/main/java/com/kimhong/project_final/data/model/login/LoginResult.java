package com.kimhong.project_final.data.model.login;

public class LoginResult {
    private String token;
    private String userId;
    private boolean authenticated;

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }
}