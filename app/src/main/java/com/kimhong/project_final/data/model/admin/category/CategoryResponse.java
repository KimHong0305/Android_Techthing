package com.kimhong.project_final.data.model.admin.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private List<Category> result;

    public int getCode() {
        return code;
    }

    public List<Category> getResult() {
        return result;
    }

    public static class Category {
        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("description")
        private String description;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}