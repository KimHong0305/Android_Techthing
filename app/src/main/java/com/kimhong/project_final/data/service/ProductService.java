package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.model.product.SearchResponse;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {
    @GET("products")
    Call<ProductResponse> getProducts();

    @GET("products/search?keyword={key}")
    Call<SearchResponse> search();
}