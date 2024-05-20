package com.kimhong.project_final.data.model.cart;

public class CartItem {
    private String productId;

    private String productName;
    private String image;
    private int quantity;
    private double price;

    // Constructors, Getters and Setters


    public CartItem(String productId, String productName, String image, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.image = image;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
