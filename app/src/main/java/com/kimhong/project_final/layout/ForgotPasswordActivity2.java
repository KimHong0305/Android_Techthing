package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.forgotPassword.VerifyOTPRequest;
import com.kimhong.project_final.data.model.forgotPassword.VerifyOTPResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ForgotPasswordService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity2 extends AppCompatActivity {
    private ConstraintLayout btnVerify;
    private ImageView btnBack;
    private TextView txtEmail;
    private ForgotPasswordService forgotPasswordService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_forgot_password);

        btnBack = findViewById(R.id.comeBack);
        btnVerify = findViewById(R.id.btnVerify);

        EditText[] otpEditTexts = {
                findViewById(R.id.otp_1),
                findViewById(R.id.otp_2),
                findViewById(R.id.otp_3),
                findViewById(R.id.otp_4),
                findViewById(R.id.otp_5),
                findViewById(R.id.otp_6)
        };
        String mail = getIntent().getStringExtra("email");
        txtEmail = findViewById(R.id.txtMail);
        txtEmail.setText(mail);
        // Quay lại trang trước đó
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity2.this, ForgotPasswordActivity1.class);
                startActivity(intent);
            }
        });

        forgotPasswordService = APIUtils.getForgotPasswordService();
        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int otp = getOTPFromEditTexts(otpEditTexts);
                if (otp != 0) {
                    // Tạo VerifyOTPRequest object
                    VerifyOTPRequest verifyOtpRequest = new VerifyOTPRequest(mail, otp);

                    // Gọi API để xác minh OTP
                    forgotPasswordService.verifyOtp(verifyOtpRequest).enqueue(new Callback<VerifyOTPResponse>() {
                        @Override
                        public void onResponse(Call<VerifyOTPResponse> call, Response<VerifyOTPResponse> response) {
                            if (response.isSuccessful()) {
                                VerifyOTPResponse verifyOTPResponse = response.body();
                                if (verifyOTPResponse.getCode() == 200) {
                                    // OTP hợp lệ, chuyển sang màn hình tiếp theo
                                    Toast.makeText(ForgotPasswordActivity2.this, "OTP hợp lệ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ForgotPasswordActivity2.this, ForgotPasswordActivity3.class);
                                    intent.putExtra("email", mail);
                                    intent.putExtra("otp", otp);
                                    startActivity(intent);
                                }
                            } else {
                                // Xử lý lỗi từ API
                                Toast.makeText(ForgotPasswordActivity2.this, "Lỗi xác minh OTP", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VerifyOTPResponse> call, Throwable t) {
                            // Xử lý lỗi kết nối
                            Toast.makeText(ForgotPasswordActivity2.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    // Hiển thị thông báo lỗi nếu người dùng chưa nhập đủ 6 chữ số
                    Toast.makeText(ForgotPasswordActivity2.this, "Vui lòng nhập đủ 6 chữ số OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Hàm lấy số từ các EditText
    public int getOTPFromEditTexts(EditText[] otpEditTexts) {
        StringBuilder otpBuilder = new StringBuilder();
        for (EditText editText : otpEditTexts) {
            String digit = editText.getText().toString().trim();
            if (digit.isEmpty()) {
                // Nếu có bất kỳ EditText nào chưa được nhập, trả về 0
                return 0;
            }
            otpBuilder.append(digit);
        }

        // Chuyển chuỗi thành số
        return Integer.parseInt(otpBuilder.toString());
    }
}