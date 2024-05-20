package com.kimhong.project_final.layout.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.user.UserResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AdminService;
import com.kimhong.project_final.adapter.UserAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserManagerActivity extends AppCompatActivity {
    private ListView listView;
    private UserAdapter userAdapter;
    private AdminService adminService;
    private String authToken; // Biến lưu trữ token

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manager);

        listView = findViewById(R.id.listview);
        adminService = APIUtils.getAdminService();
        authToken = getAuthToken(); // Lấy token từ đâu đó
        getUserList();
    }

    private void getUserList() {
        adminService.getAllUsers("Bearer " + authToken).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse.getCode() == 200) {
                        List<UserResponse.User> users = userResponse.getResult();
                        userAdapter = new UserAdapter(UserManagerActivity.this, users);
                        listView.setAdapter(userAdapter);
                    } else {
                        Toast.makeText(UserManagerActivity.this, "Error: " + userResponse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserManagerActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(UserManagerActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getAuthToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
}