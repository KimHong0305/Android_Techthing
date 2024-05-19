package com.kimhong.project_final.data.model.forgotPassword;

public class ChangePasswordRequest {
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    private String mail;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    private String password_confirm;

    public ChangePasswordRequest(String mail, String password, String password_confirm){
        this.mail = mail;
        this.password = password;
        this.password_confirm = password_confirm;
    }
}
