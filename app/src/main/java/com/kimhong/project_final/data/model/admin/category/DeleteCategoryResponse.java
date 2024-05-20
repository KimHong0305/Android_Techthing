package com.kimhong.project_final.data.model.admin.category;

import com.google.gson.annotations.SerializedName;

public class DeleteCategoryResponse {
    @SerializedName("code")
    private int code;
    public int getCode() {
        return code;
    }
}
