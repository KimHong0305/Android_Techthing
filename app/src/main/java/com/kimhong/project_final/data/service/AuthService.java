package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.auth.AuthRequest;
import com.kimhong.project_final.data.model.auth.AuthResponse;
import com.kimhong.project_final.data.model.signup.SignUpRequest;
import com.kimhong.project_final.data.model.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("auth/introspect")
    Call<AuthResponse> check(@Body AuthRequest authRequest);
}
