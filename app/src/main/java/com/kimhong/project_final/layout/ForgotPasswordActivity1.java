package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailRequest;
import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailResponse;
import com.kimhong.project_final.data.service.ForgotPasswordService;
import com.kimhong.project_final.data.remote.APIUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity1 extends AppCompatActivity {
    private EditText Email;
    private ImageView btnBack;
    private ConstraintLayout btnSend;
    private ForgotPasswordService forgotPasswordService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_forgot_password);
        Email = findViewById(R.id.txtemail);
        btnBack = findViewById(R.id.comeBack);
        btnSend = findViewById(R.id.btnSend);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity1.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        // Tạo instance của ForgotPasswordService
        forgotPasswordService = APIUtils.getForgotPasswordService();

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString().trim();
                // Kiểm tra email có hợp lệ không
                if (isValidEmail(email)) {
                    // Gửi yêu cầu xác minh email
                    VerifyEmailRequest verifyEmailRequest = new VerifyEmailRequest(email);
                    forgotPasswordService.verifyEmail(verifyEmailRequest).enqueue(new Callback<VerifyEmailResponse>() {
                        @Override
                        public void onResponse(Call<VerifyEmailResponse> call, Response<VerifyEmailResponse> response) {
                            if (response.isSuccessful()) {
                                VerifyEmailResponse verifyEmailResponse = response.body();
                                if (verifyEmailResponse != null) {
                                    if (verifyEmailResponse.getCode() == 200) {
                                        // Chuyển sang ForgotPasswordActivity2
                                        Toast.makeText(ForgotPasswordActivity1.this, "Đã gửi mã OTP đến email của bạn", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(ForgotPasswordActivity1.this, ForgotPasswordActivity2.class);
                                        startActivity(intent);
                                    } else {
                                        // Xử lý lỗi từ API
                                        String errorMessage = verifyEmailResponse.getMessage();
                                        if (verifyEmailResponse.getResult() != null) {
                                            errorMessage += ": " + verifyEmailResponse.getResult().toString();
                                        }
                                        Toast.makeText(ForgotPasswordActivity1.this, errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    // Xử lý lỗi từ API
                                    Toast.makeText(ForgotPasswordActivity1.this, "Đã xảy ra lỗi, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Xử lý lỗi từ API
                                Toast.makeText(ForgotPasswordActivity1.this, "Đã xảy ra lỗi, vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyEmailResponse> call, Throwable t) {
                            Toast.makeText(ForgotPasswordActivity1.this, "Đã gửi mã OTP đến email của bạn", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordActivity1.this, ForgotPasswordActivity2.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    // Email không hợp lệ
                    Toast.makeText(ForgotPasswordActivity1.this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}