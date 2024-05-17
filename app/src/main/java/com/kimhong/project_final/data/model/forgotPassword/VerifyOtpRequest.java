package com.kimhong.project_final.data.model.forgotPassword;

public class VerifyOtpRequest {
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    private String mail;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    private String otp;
    private VerifyOtpRequest(String mail, String otp){
        this.mail = mail;
        this.otp = otp;
    }
}
