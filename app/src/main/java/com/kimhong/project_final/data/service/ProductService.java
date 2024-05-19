package com.kimhong.project_final.data.service;



import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.model.productdetail.ProductDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductService {
    //all products
    @GET("products")
    Call<ProductResponse> getProducts();
    //findbyid
    @GET("/products/{id}")
    Call<ProductDetailResponse> productDetail (@Path("id") String id);
}
