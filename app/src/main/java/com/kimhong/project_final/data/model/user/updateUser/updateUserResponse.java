package com.kimhong.project_final.data.model.user.updateUser;

public class updateUserResponse {
    private int code;
    private updateUserResult result;

    public updateUserResponse() {
    }

    public updateUserResponse(int code, updateUserResult result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public updateUserResult getResult() {
        return result;
    }

    public void setResult(updateUserResult result) {
        this.result = result;
    }
}
