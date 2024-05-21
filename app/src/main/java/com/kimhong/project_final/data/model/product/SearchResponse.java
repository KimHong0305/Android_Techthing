package com.kimhong.project_final.data.model.product;

import java.util.List;

public class SearchResponse {
    private int code;
    private List<ProductResponse.Product> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ProductResponse.Product> getResult() {
        return result;
    }

    public void setResult(List<ProductResponse.Product> result) {
        this.result = result;
    }

    public static class Product {
        private String id;
        private String name;
        private double price;
        private String image;
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

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
