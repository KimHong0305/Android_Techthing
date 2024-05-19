package com.kimhong.project_final.data.model.forgotPassword;

public class VerifyOTPResponse {
    private int code;
    private String message;

    public String getResult() {
        return result;
    }

    private String result;
    private boolean success;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}