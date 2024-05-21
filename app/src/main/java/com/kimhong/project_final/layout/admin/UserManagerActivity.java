package com.kimhong.project_final.layout.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.user.DeleteResponse;
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
    private ImageView btnHome;
    private String authToken; // Biến lưu trữ token
    private List<UserResponse.User> users;

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
        userAdapter = new UserAdapter(UserManagerActivity.this, users, userId -> deleteUser(userId));
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserManagerActivity.this, MainAdminActivity.class);
                startActivity(intent);
            }
        });
        getUserList();
    }
    private void deleteUser(String userId) {
        adminService.deleteUser("Bearer " + authToken, userId).enqueue(new Callback<DeleteResponse>() {
            @Override
            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                if (response.isSuccessful()) {
                    DeleteResponse logoutResponse = response.body();
                    if (logoutResponse.getCode() == 200) {
                        Toast.makeText(UserManagerActivity.this, "Xóa user thành công", Toast.LENGTH_SHORT).show();
                        getUserList(); // Refresh the user list
                    }
                } else {
                    Toast.makeText(UserManagerActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteResponse> call, Throwable t) {
                Toast.makeText(UserManagerActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getUserList() {
        adminService.getAllUsers("Bearer " + authToken).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse.getCode() == 200) {
                        List<UserResponse.User> users = userResponse.getResult();
                        userAdapter = new UserAdapter(UserManagerActivity.this, users, userId -> deleteUser(userId));
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