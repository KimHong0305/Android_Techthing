package com.kimhong.project_final.data.model.forgotPassword;

import com.kimhong.project_final.data.model.login.LoginResult;

public class VerifyEmailResponse {
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