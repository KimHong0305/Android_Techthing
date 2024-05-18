package com.kimhong.project_final.data.model.user;

import java.util.List;

public class UserResult {
    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mail;
    private String phone;
    private String address;
    private List<Roles> roles;
    private List<Invoices> invoices;
    private Cart cart;
    //forgot password null
    private int forgotPassword;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public List<Invoices> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoices> invoices) {
        this.invoices = invoices;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getForgotPassword() {
        return forgotPassword;
    }

    public void setForgotPassword(int forgotPassword) {
        this.forgotPassword = forgotPassword;
    }
}
