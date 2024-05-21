package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.login.LoginRequest;
import com.kimhong.project_final.data.model.login.LoginResponse;
import com.kimhong.project_final.data.model.signup.SignUpRequest;
import com.kimhong.project_final.data.model.signup.SignUpResponse;
import com.kimhong.project_final.data.model.user.updateUser.updateUserRequest;
import com.kimhong.project_final.data.model.user.UserResponse;
import com.kimhong.project_final.data.model.user.updateUser.updateUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
public interface UserService {
    //login
    @POST("auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    //signup
    @POST("user/add")
    Call<SignUpResponse> signUp(@Body SignUpRequest signUpRequest);

    @GET("user/bio")
    Call<UserResponse> userinfo(@Header("Authorization") String authHeader);

    @PUT ("user/update/bio")
    Call<updateUserResponse> updateinfo(@Body updateUserRequest updateUserRequest,@Header("Authorization") String authHeader);

}