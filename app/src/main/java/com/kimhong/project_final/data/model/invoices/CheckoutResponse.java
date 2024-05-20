package com.kimhong.project_final.data.model.invoices;

import java.sql.Timestamp;

public class CheckoutResponse {
    int code;
    InvoiceResponse result;

    public CheckoutResponse(int code, InvoiceResponse result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public InvoiceResponse getResult() {
        return result;
    }

    public void setResult(InvoiceResponse result) {
        this.result = result;
    }

    static class InvoiceResponse{
        String id;
        String shippingInfo;
        Timestamp timeOrder;
        String status;
        UserResponse user;

        public InvoiceResponse(String id, String shippingInfo, Timestamp timeOrder, String status, UserResponse user) {
            this.id = id;
            this.shippingInfo = shippingInfo;
            this.timeOrder = timeOrder;
            this.status = status;
            this.user = user;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShippingInfo() {
            return shippingInfo;
        }

        public void setShippingInfo(String shippingInfo) {
            this.shippingInfo = shippingInfo;
        }

        public Timestamp getTimeOrder() {
            return timeOrder;
        }

        public void setTimeOrder(Timestamp timeOrder) {
            this.timeOrder = timeOrder;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public UserResponse getUser() {
            return user;
        }

        public void setUser(UserResponse user) {
            this.user = user;
        }

        static class UserResponse {
            String id;
            String username;
            String fullname;

            public UserResponse(String id, String username, String fullname) {
                this.id = id;
                this.username = username;
                this.fullname = fullname;

            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getFullname() {
                return fullname;
            }

            public void setFullname(String fullname) {
                this.fullname = fullname;
            }

        }
    }
}
