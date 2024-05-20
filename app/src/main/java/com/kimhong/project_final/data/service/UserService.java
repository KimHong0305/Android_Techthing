package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.login.LoginRequest;
import com.kimhong.project_final.data.model.login.LoginResponse;
import com.kimhong.project_final.data.model.signup.SignUpRequest;
import com.kimhong.project_final.data.model.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    //login
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    //signup
    @POST("user/add")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);


}