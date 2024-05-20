package com.kimhong.project_final.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.kimhong.project_final.data.service.CartService;
import com.kimhong.project_final.data.service.ForgotPasswordService;
import com.kimhong.project_final.data.service.InvoiceService;
import com.kimhong.project_final.data.service.ProductService;
import com.kimhong.project_final.data.service.UserService;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIUtils {
    public static final String BASE_URL = "http://192.168.239.1:8080/";

    private static Retrofit retrofit = null;

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
    public static ForgotPasswordService getForgotPasswordService() {
        return RetrofitClient.getClient(BASE_URL).create(ForgotPasswordService.class);
    }

    public static ProductService getProductService(){
        return RetrofitClient.getClient(BASE_URL).create(ProductService.class);
    }
    public static CartService getCartService() {

        return RetrofitClient.getClient(BASE_URL).create(CartService.class);
    }
    public static InvoiceService getInvoiceService() {

        return RetrofitClient.getClient(BASE_URL).create(InvoiceService.class);
    }


}

