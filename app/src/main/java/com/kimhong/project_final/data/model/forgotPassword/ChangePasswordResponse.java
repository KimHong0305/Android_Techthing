package com.kimhong.project_final.data.model.forgotPassword;

public class ChangePasswordResponse {
    private int code;
    private PasswordResult result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PasswordResult getResult() {
        return result;
    }

    public void setResult(PasswordResult result) {
        this.result = result;
    }
}
