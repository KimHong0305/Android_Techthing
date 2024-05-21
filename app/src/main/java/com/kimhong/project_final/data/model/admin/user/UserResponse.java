package com.kimhong.project_final.data.model.admin.user;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("result")
    private List<User> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<User> getResult() {
        return result;
    }

    public void setResult(List<User> result) {
        this.result = result;
    }

    public static class User {
        @SerializedName("id")
        private String id;

        @SerializedName("username")
        private String username;

        @SerializedName("fullname")
        private String fullname;

        @SerializedName("mail")
        private String mail;

        @SerializedName("phone")
        private String phone;

        @SerializedName("address")
        private String address;

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

        public String getMail() {
            return mail;
        }

        public void setMail(String mail) {
            this.mail = mail;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}