package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.cart.AddToCartRequest;
import com.kimhong.project_final.data.model.cart.AddToCartResponse;
import com.kimhong.project_final.data.model.cart.GetCartResponse;
import com.kimhong.project_final.data.model.user.updateUser.updateUserRequest;
import com.kimhong.project_final.data.model.user.updateUser.updateUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CartService {
    @POST("cartdetails/addToCart")
    Call<AddToCartResponse> addToCart(@Body AddToCartRequest addToCartRequest, @Header("Authorization") String authHeader);

    @GET("cartdetails/myCart")
    Call<GetCartResponse> getMyCart(@Header("Authorization") String authHeader);
}
