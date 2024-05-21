package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.admin.category.CategoryResponse;
import com.kimhong.project_final.data.model.admin.category.DeleteCategoryResponse;
import com.kimhong.project_final.data.model.admin.product.CreateProductRequest;
import com.kimhong.project_final.data.model.admin.product.CreateProductResponse;
import com.kimhong.project_final.data.model.admin.product.DeleteProductResponse;
import com.kimhong.project_final.data.model.admin.user.DeleteResponse;
import com.kimhong.project_final.data.model.admin.user.UserResponse;
import com.kimhong.project_final.data.model.admin.category.CreateCategoryRequest;
import com.kimhong.project_final.data.model.admin.category.CreateCategoryResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface AdminService {
    @GET("admin/getAllUser")
    Call<UserResponse> getAllUsers(@Header("Authorization") String authHeader);

    @DELETE("admin/user/delete/{userId}")
    Call<DeleteResponse> deleteUser(@Header("Authorization") String authHeader, @Path("userId") String userId);

    @DELETE("products/admin-manager/{productId}")
    Call<DeleteProductResponse> deleteProduct(@Header("Authorization") String authHeader, @Path("productId") String productId);

    @Multipart
    @POST("products/admin-manager")
    Call<CreateProductResponse> createProduct(
            @Header("Authorization") String authorization,
            @Part("createProductRequest") CreateProductRequest createProductRequest,
            @Part MultipartBody.Part file
    );

    @GET("categories")
    Call<CategoryResponse> getCategory();

    @DELETE("categories/admin-manager/{categoryId}")
    Call<DeleteCategoryResponse> deleteCategory(@Header("Authorization") String authHeader, @Path("categoryId") String categoryId);
    @POST("categories/admin-manager")
    Call<CreateCategoryResponse> createCategory(@Header("Authorization") String authHeader, @Body CreateCategoryRequest createCategoryRequest);
}
