package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.admin.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface AdminService {
    @GET("admin/getAllUser")
    Call<UserResponse> getAllUsers(@Header("Authorization") String authHeader);
}
