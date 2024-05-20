package com.kimhong.project_final.data.model.auth;

import com.google.gson.annotations.SerializedName;

public class LogoutResponse {
    @SerializedName("code")
    private int code;
    public int getCode() {
        return code;
    }
}
