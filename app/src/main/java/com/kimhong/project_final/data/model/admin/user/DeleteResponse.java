package com.kimhong.project_final.data.model.admin.user;

import com.google.gson.annotations.SerializedName;

public class DeleteResponse {
    @SerializedName("code")
    private int code;
    public int getCode() {
        return code;
    }
}
