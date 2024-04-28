package com.kimhong.project_final.data.model.signup;

public class SignUpResponse {
    private int code;
    private SignUpResult result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public SignUpResult getResult() {
        return result;
    }

    public void setResult(SignUpResult result) {
        this.result = result;
    }
}