package com.kimhong.project_final.data.model.cart;

import java.util.List;

public class GetCartResponse {
    private int code;
    private List<ItemCartResponse> result;

    public GetCartResponse(int code, List<ItemCartResponse> result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ItemCartResponse> getResult() {
        return result;
    }

    public void setResult(List<ItemCartResponse> result) {
        this.result = result;
    }

    public static class ItemCartResponse{
        String cartDetail_id;
        String product_id;
        String product_name;
        String image;
        float price;
        int quantity;
        float sub_total;

        public ItemCartResponse(String cartDetail_id, String product_id, String product_name, String image, float price, int quantity, float sub_total) {
            this.cartDetail_id = cartDetail_id;
            this.product_id = product_id;
            this.product_name = product_name;
            this.image = image;
            this.price = price;
            this.quantity = quantity;
            this.sub_total = sub_total;
        }

        public String getCartDetail_id() {
            return cartDetail_id;
        }

        public void setCartDetail_id(String cartDetail_id) {
            this.cartDetail_id = cartDetail_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public float getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getSub_total() {
            return sub_total;
        }

        public void setSub_total(float sub_total) {
            this.sub_total = sub_total;
        }
    }
}
