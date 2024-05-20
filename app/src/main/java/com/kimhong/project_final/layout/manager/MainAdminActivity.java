package com.kimhong.project_final.layout.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.auth.AuthRequest;
import com.kimhong.project_final.data.model.auth.AuthResponse;
import com.kimhong.project_final.data.model.auth.LogoutRequest;
import com.kimhong.project_final.data.model.auth.LogoutResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AuthService;
import com.kimhong.project_final.layout.ForgotPasswordActivity1;
import com.kimhong.project_final.layout.ForgotPasswordActivity2;
import com.kimhong.project_final.layout.LoginActivity;
import com.kimhong.project_final.layout.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdminActivity extends AppCompatActivity {
    private ConstraintLayout btnUser, btnProduct;
    private ImageView btnLogout;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btnUser = findViewById(R.id.btnUser);
        btnLogout = findViewById(R.id.btnLogout);
        btnProduct = findViewById(R.id.btnProduct);

        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String token =  sharedPreferences.getString("token", "");
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, UserManagerActivity.class);
                startActivity(intent);
            }
        });

        btnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, ProductManagerActivity.class);
                startActivity(intent);
            }
        });
        authService = APIUtils.getAuthService();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogoutRequest logoutRequest = new LogoutRequest(token);
                authService.logout(logoutRequest).enqueue(new Callback<LogoutResponse>() {
                    @Override
                    public void onResponse(Call<LogoutResponse> call, Response<LogoutResponse> response) {
                        if (response.isSuccessful()) {
                            LogoutResponse logoutResponse = response.body();
                            if (logoutResponse.getCode() == 200) {
                                Toast.makeText(MainAdminActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainAdminActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LogoutResponse> call, Throwable t) {
                        Toast.makeText(MainAdminActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}