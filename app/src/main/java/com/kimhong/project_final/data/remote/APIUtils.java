package com.kimhong.project_final.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.kimhong.project_final.data.service.ForgotPasswordService;
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

    public static UserService getUserService(Context context) {
        if (retrofit == null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request original = chain.request();

                            // Lấy token từ SharedPreferences
                            SharedPreferences sharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
                            String token = sharedPreferences.getString("token", "");

                            // Thêm token vào header
                            Request request = original.newBuilder()
                                    .header("Authorization", "Bearer " + token)
                                    .build();

                            return chain.proceed(request);
                        }
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(UserService.class);
    }
    public static ForgotPasswordService getForgotPasswordService() {
        return RetrofitClient.getClient(BASE_URL).create(ForgotPasswordService.class);
    }

    public static ProductService getProductService(){
        return RetrofitClient.getClient(BASE_URL).create(ProductService.class);
    }

}

