package com.kimhong.project_final.data.model.admin.product;

public class CreateProductResponse {
    private int code;
    private CreateProductResult result;

    public int getCode() {
        return code;
    }

    public CreateProductResult getResult() {
        return result;
    }

    public static class CreateProductResult {
        private String id;
        private String name;
        private double price;
        private String image;
        private String description;
        private Category category;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public String getImage() {
            return image;
        }

        public String getDescription() {
            return description;
        }

        public Category getCategory() {
            return category;
        }
    }

    public static class Category {
        private String id;
        private String name;
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
