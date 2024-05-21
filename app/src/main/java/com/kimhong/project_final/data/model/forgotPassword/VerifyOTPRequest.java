package com.kimhong.project_final.data.model.forgotPassword;

public class VerifyOTPRequest {
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    private String mail;

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
    }

    private int otp;
    public VerifyOTPRequest(String mail, int otp){
        this.mail = mail;
        this.otp = otp;
    }
}
