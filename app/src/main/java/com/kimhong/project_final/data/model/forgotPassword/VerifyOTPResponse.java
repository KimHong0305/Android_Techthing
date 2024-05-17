package com.kimhong.project_final.data.model.forgotPassword;

public class VerifyOTPResponse {
    private int code;
    private String result;
    private boolean success;

    public VerifyOTPResponse(int code, String result, boolean success) {
        this.code = code;
        this.result = result;
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
}