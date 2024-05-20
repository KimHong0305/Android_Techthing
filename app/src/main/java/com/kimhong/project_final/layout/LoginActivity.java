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
import com.kimhong.project_final.data.model.auth.AuthRequest;
import com.kimhong.project_final.data.model.auth.AuthResponse;
import com.kimhong.project_final.data.model.login.LoginRequest;
import com.kimhong.project_final.data.model.login.LoginResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AuthService;
import com.kimhong.project_final.data.service.UserService;
import com.kimhong.project_final.layout.manager.MainAdminActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private ConstraintLayout btnLogin;
    private UserService userService;
    private AuthService authService;
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
        authService = APIUtils.getAuthService();

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
                        String token = getToken();
                        check_Role(token);

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
    private void check_Role(String token){
        AuthRequest authRequest = new AuthRequest(token);
        authService.check(authRequest).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                if (response.isSuccessful()) {
                    AuthResponse authResponse = response.body();
                    if (authResponse.getCode() == 200) {
                        AuthResponse.Result result = authResponse.getResult();
                        if (result.isValid()) {
                            String role = result.getRole();
                            switch (role) {
                                case "USER":
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                    break;
                                case "ADMIN":
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginActivity.this, MainAdminActivity.class));
                                    finish();
                                    break;
                                case "MANAGER":
                                    // Xử lý khi người dùng có role là MANAGER
                                    break;
                                default:
                                    // Xử lý khi người dùng có role không hợp lệ
                                    break;
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Chưa được cấp quyền", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Chưa được cấp quyền", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
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
        // Lưu thông tin người dùng từ LoginResponse
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginResponse.getResult().getToken());
        editor.putString("userId", loginResponse.getResult().getUserId());
        editor.apply();
    }
    // Lấy token từ SharedPreferences
    private String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        return sharedPreferences.getString("token", "");
    }
}