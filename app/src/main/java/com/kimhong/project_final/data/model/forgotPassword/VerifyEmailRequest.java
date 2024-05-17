package com.kimhong.project_final.data.model.forgotPassword;

public class VerifyEmailRequest {
    private String mail;

    public VerifyEmailRequest(String email) {
        this.mail = email;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
