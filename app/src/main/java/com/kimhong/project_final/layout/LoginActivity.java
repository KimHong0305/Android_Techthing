package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.login.LoginRequest;
import com.kimhong.project_final.data.model.login.LoginResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private ConstraintLayout btnLogin;
    private UserService userService;
    private ImageView seePassword;
    private TextView forgotPassword;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo các view
        etUsername = findViewById(R.id.UserName);
        etPassword = findViewById(R.id.Password);
        btnLogin = findViewById(R.id.btnLogin);
        seePassword = findViewById(R.id.seePassword);
        forgotPassword = findViewById(R.id.forgot);

        // chuyen qua trang quen mat khau
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity1.class);
                startActivity(intent);
            }
        });

        TextView btnRegister = findViewById(R.id.btnRegister);
        seePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                updatePasswordVisibility();
            }
        });
        // chuyển qua đăng kí
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Lấy instance của UserService
        userService = APIUtils.getUserService();

        // Thiết lập listener cho nút đăng nhập
        btnLogin.setOnClickListener(v -> {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();
            login(username, password);
        });
    }

    private void login(String username, String password) {
        // Tạo đối tượng LoginRequest
        LoginRequest loginRequest = new LoginRequest(username, password);

        // Gọi API login
        userService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getCode() == 200) {
                        // Lưu thông tin người dùng
                        saveUserData(loginResponse);

                        // Chuyển đến màn hình chính
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        // Hiển thị thông báo lỗi
                        Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý lỗi khác
                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                // Xử lý lỗi kết nối
                Toast.makeText(LoginActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // ham hien thi mat khau
    private void updatePasswordVisibility() {
        if (isPasswordVisible) {
            etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            seePassword.setImageResource(R.drawable.visible);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            seePassword.setImageResource(R.drawable.invisible);
        }
        etPassword.setSelection(etPassword.getText().length()); // Để con trỏ chuột ở cuối
    }

    private void saveUserData(LoginResponse loginResponse) {
        // Lưu thông tin người dùng từ LoginResponse, ví dụ:
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginResponse.getResult().getToken());
        editor.putString("userId", loginResponse.getResult().getUserId());
        editor.apply();
    }
}