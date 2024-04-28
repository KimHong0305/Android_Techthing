package com.kimhong.project_final.data.model.login;

public class LoginResponse {
    private int code;
    private String message;
    private LoginResult result;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LoginResult getResult() {
        return result;
    }
}