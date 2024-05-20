package com.kimhong.project_final.data.model.cart;

public class AddToCartResponse {
    private int code;
    private CartDetailResponse result;

    public AddToCartResponse(int code, CartDetailResponse result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public CartDetailResponse getResult() {
        return result;
    }

    public void setResult(CartDetailResponse result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "AddToCartResponse{" +
                "code=" + code +
                ", result=" + result +
                '}';
    }

    public static class CartDetailResponse {
        private String cart_id;
        private String product_id;
        private int quantity;

        public CartDetailResponse(String cart_id, String product_id, int quantity) {
            this.cart_id = cart_id;
            this.product_id = product_id;
            this.quantity = quantity;
        }

        public String getCart_id() {
            return cart_id;
        }

        public void setCart_id(String cart_id) {
            this.cart_id = cart_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "CartDetailResponse{" +
                    "cart_id='" + cart_id + '\'' +
                    ", product_id='" + product_id + '\'' +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
