package com.kimhong.project_final.data.model.cart;

public class AddToCartRequest {
    private String product_id;
    private int quantity;

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

    public AddToCartRequest(String product_id, int quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AddToCartRequest{" +
                "product_id='" + product_id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
