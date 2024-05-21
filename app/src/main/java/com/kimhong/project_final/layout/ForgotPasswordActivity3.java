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
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.forgotPassword.ChangePasswordRequest;
import com.kimhong.project_final.data.model.forgotPassword.ChangePasswordResponse;
import com.kimhong.project_final.data.model.forgotPassword.PasswordResult;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ForgotPasswordService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity3 extends AppCompatActivity {
    private ImageView btnBack, seePassword, seeConfirmPassword;
    private ConstraintLayout btnSave;
    private EditText password, password_confirm;
    private boolean isNewPasswordVisible = false;
    private ForgotPasswordService forgotPasswordService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_forgot_password);

        password = findViewById(R.id.Password);
        password_confirm = findViewById(R.id.ConfirmPassword);
        seePassword = findViewById(R.id.seePassword);
        seeConfirmPassword = findViewById(R.id.seeConfirmPassword);
        btnBack = findViewById(R.id.comeBack);
        btnSave = findViewById(R.id.btnSave);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity3.this, ForgotPasswordActivity1.class);
                startActivity(intent);
            }
        });
        String mail = getIntent().getStringExtra("email");
        int otp = getIntent().getIntExtra("otp",0);

        seePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNewPasswordVisible = !isNewPasswordVisible;
                updatePasswordVisibility(password, seePassword);
            }
        });
        isNewPasswordVisible = false;
        seeConfirmPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNewPasswordVisible = !isNewPasswordVisible;
                updatePasswordVisibility(password_confirm, seeConfirmPassword);
            }
        });

        forgotPasswordService = APIUtils.getForgotPasswordService();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = password.getText().toString();
                String newPasswordConfirm = password_confirm.getText().toString();
                if (newPassword.equals(newPasswordConfirm)) {
                    ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(otp, mail, newPassword, newPasswordConfirm);
                    forgotPasswordService.changePassword(changePasswordRequest).enqueue(new Callback<ChangePasswordResponse>() {
                        @Override
                        public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                ChangePasswordResponse changePasswordResponse = response.body();
                                if (changePasswordResponse.getCode() == 200) {
                                    PasswordResult result = changePasswordResponse.getResult();
                                    // Đổi mật khẩu thành công, hiển thị thông báo và chuyển đến màn hình đăng nhập
                                    Toast.makeText(ForgotPasswordActivity3.this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPasswordActivity3.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    // Xử lý trường hợp lỗi
                                    Toast.makeText(ForgotPasswordActivity3.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Xử lý trường hợp lỗi
                                Toast.makeText(ForgotPasswordActivity3.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {
                            // Xử lý khi gặp lỗi kết nối hoặc lỗi không xác định
                            Toast.makeText(ForgotPasswordActivity3.this, "Đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Mật khẩu xác nhận không khớp, hiển thị thông báo lỗi
                    Toast.makeText(ForgotPasswordActivity3.this, "Mật khẩu xác nhận không khớp", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updatePasswordVisibility(EditText pass, ImageView see) {
        if (isNewPasswordVisible) {
            pass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            see.setImageResource(R.drawable.visible);
        } else {
            pass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            see.setImageResource(R.drawable.invisible);
        }
        pass.setSelection(pass.getText().length()); // Để con trỏ chuột ở cuối
    }
}