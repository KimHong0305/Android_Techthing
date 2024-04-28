package com.kimhong.project_final.data.model.signup;

public class SignUpRequest {
    private String username;
    private String password;
    private String fullname;
    private String mail;
    private String phone;

    public SignUpRequest(String username, String password, String fullname, String mail, String phone) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.mail = mail;
        this.phone = phone;
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
}