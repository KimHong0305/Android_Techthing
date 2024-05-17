package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailRequest;
import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailResponse;
import com.kimhong.project_final.data.model.forgotPassword.VerifyOTPResponse;
import com.kimhong.project_final.data.model.forgotPassword.VerifyOtpRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ForgotPasswordService {
    @POST("forgotPassword/verifyEmail")
    Call<VerifyEmailResponse> verifyEmail(@Body VerifyEmailRequest verifyEmailRequest);

    @POST("forgotPassword/verifyOtp")
    Call<VerifyOTPResponse> verifyOtp(@Body VerifyOtpRequest verifyOtpRequest);

}