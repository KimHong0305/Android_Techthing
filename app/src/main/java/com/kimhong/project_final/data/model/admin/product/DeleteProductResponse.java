package com.kimhong.project_final.data.model.admin.product;

import com.google.gson.annotations.SerializedName;

public class DeleteProductResponse {
    @SerializedName("code")
    private int code;
    public int getCode() {
        return code;
    }
}
