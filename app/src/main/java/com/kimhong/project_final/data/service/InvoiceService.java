package com.kimhong.project_final.data.service;

import com.kimhong.project_final.data.model.invoices.CheckoutRequest;
import com.kimhong.project_final.data.model.invoices.CheckoutResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface InvoiceService {
    @POST("invoices/user")
    Call<CheckoutResponse> createInvoice(@Body CheckoutRequest checkoutRequest,@Header("Authorization") String authHeader);

}
