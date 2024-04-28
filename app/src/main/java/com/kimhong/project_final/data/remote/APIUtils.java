package com.kimhong.project_final.data.remote;

public class APIUtils {
    public static final String BASE_URL = "http://172.22.96.1:8080/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}

