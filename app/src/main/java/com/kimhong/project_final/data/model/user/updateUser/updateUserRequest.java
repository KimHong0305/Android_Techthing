package com.kimhong.project_final.data.model.user.updateUser;

public class updateUserRequest {
    private String fullname;
    private String mail;
    private String phone;

    public updateUserRequest(String fullname, String mail, String phone) {
        this.fullname = fullname;
        this.mail = mail;
        this.phone = phone;
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
