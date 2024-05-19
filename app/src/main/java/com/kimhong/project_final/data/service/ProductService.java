package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.product.ProductResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<ProductResponse> getProducts();
}