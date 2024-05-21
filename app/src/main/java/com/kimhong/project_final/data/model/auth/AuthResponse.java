package com.kimhong.project_final.data.model.auth;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private Result result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public static class Result {
        @SerializedName("valid")
        private boolean valid;

        @SerializedName("role")
        private String role;

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
    }
}