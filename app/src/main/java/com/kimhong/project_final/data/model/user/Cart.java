package com.kimhong.project_final.data.model.user;

import java.util.List;

public class Cart {
    private String id;
    private List<CartDetail> cartDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartDetail> getCartDetails() {
        return cartDetails;
    }

    public void setCartDetails(List<CartDetail> cartDetails) {
        this.cartDetails = cartDetails;
    }
}
