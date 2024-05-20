package com.kimhong.project_final.data.model.auth;

public class LogoutRequest {
    public String getToken() {
        return token;
    }

    private String token;
    public LogoutRequest(String token){
        this.token = token;
    }
}
