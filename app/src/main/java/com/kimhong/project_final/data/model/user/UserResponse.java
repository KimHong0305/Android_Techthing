package com.kimhong.project_final.data.model.user;

public class UserResponse {

    private int code;
    private UserResult result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserResult getResult() {
        return result;
    }

    public void setResult(UserResult result) {
        this.result = result;
    }
}
