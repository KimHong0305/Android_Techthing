package com.kimhong.project_final.data.remote;

import com.kimhong.project_final.data.service.AdminService;
import com.kimhong.project_final.data.service.AuthService;
import com.kimhong.project_final.data.service.CartService;
import com.kimhong.project_final.data.service.ForgotPasswordService;
import com.kimhong.project_final.data.service.InvoiceService;
import com.kimhong.project_final.data.service.ProductService;
import com.kimhong.project_final.data.service.UserService;

public class APIUtils {
    public static final String BASE_URL = "http://172.22.96.1:8080/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
    public static ForgotPasswordService getForgotPasswordService() {
        return RetrofitClient.getClient(BASE_URL).create(ForgotPasswordService.class);
    }
    public static ProductService getProductService() {
        return RetrofitClient.getClient(BASE_URL).create(ProductService.class);
    }
    public static AuthService getAuthService() {
        return RetrofitClient.getClient(BASE_URL).create(AuthService.class);
    }
    public static AdminService getAdminService() {
        return RetrofitClient.getClient(BASE_URL).create(AdminService.class);
    }
    public static CartService getCartService() {

        return RetrofitClient.getClient(BASE_URL).create(CartService.class);
    }
    public static InvoiceService getInvoiceService() {

        return RetrofitClient.getClient(BASE_URL).create(InvoiceService.class);
    }
}

