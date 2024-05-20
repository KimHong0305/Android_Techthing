package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.admin.user.DeleteResponse;
import com.kimhong.project_final.data.model.admin.user.UserResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AdminService {
    @GET("admin/getAllUser")
    Call<UserResponse> getAllUsers(@Header("Authorization") String authHeader);

    @DELETE("admin/user/delete/{userId}")
    Call<DeleteResponse> deleteUser(@Header("Authorization") String authHeader, @Path("userId") String userId);
}
