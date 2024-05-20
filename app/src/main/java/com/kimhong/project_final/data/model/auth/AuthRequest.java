package com.kimhong.project_final.data.model.auth;

public class AuthRequest {
    public String getToken() {
        return token;
    }

    private String token;
    public AuthRequest(String token){
        this.token = token;
    }
}
