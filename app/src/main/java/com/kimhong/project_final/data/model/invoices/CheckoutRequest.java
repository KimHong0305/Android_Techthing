package com.kimhong.project_final.data.model.invoices;

import java.util.List;

public class CheckoutRequest {
    String shippingInfor;
    List<String> cartDetailIds;

    public CheckoutRequest(String shippingInfor, List<String> cartDetailIds) {
        this.shippingInfor = shippingInfor;
        this.cartDetailIds = cartDetailIds;
    }

    public String getShippingInfor() {
        return shippingInfor;
    }

    public void setShippingInfor(String shippingInfor) {
        this.shippingInfor = shippingInfor;
    }

    public List<String> getCartDetailIds() {
        return cartDetailIds;
    }

    public void setCartDetailIds(List<String> cartDetailIds) {
        this.cartDetailIds = cartDetailIds;
    }
}
