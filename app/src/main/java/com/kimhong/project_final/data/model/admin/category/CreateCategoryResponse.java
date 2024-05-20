package com.kimhong.project_final.data.model.admin.category;

public class CreateCategoryResponse {
    private int code;
    private CategoryData result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CategoryData getResult() {
        return result;
    }

    public void setResult(CategoryData result) {
        this.result = result;
    }

    public static class CategoryData {
        private String id;
        private String name;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}