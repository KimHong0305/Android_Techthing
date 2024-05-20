package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.signup.SignUpRequest;
import com.kimhong.project_final.data.model.signup.SignUpResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.UserService;
import androidx.constraintlayout.widget.ConstraintLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private UserService userService;
    private EditText Username, Password, ConfirmPassword, Fullname, Email, Phone;
    private ConstraintLayout btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Khởi tạo các EditText
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        ConfirmPassword = findViewById(R.id.ConfirmPassword);
        Fullname = findViewById(R.id.txtfullname);
        Email = findViewById(R.id.txtemail);
        Phone = findViewById(R.id.txtphone);

        // Lấy instance của UserService
        userService = APIUtils.getUserService();

        // Thiết lập listener cho nút đăng ký
        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(v -> {
            String username = Username.getText().toString();
            String password = Password.getText().toString();
            String confirmPassword = ConfirmPassword.getText().toString();
            String fullname = Fullname.getText().toString();
            String mail = Email.getText().toString();
            String phone = Phone.getText().toString();
            signUp(username, password, confirmPassword, fullname, mail, phone);
        });
    }

    private void signUp(String username, String password, String confirmPassword, String fullname, String mail, String phone) {
        // Kiểm tra các trường bắt buộc
        if (TextUtils.isEmpty(fullname)) {
            Fullname.setError("Vui lòng nhập họ và tên");
            return;
        }
        if (TextUtils.isEmpty(mail)) {
            Email.setError("Vui lòng nhập email");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            Phone.setError("Vui lòng nhập số điện thoại");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            Username.setError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Password.setError("Vui lòng nhập mật khẩu");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            ConfirmPassword.setError("Vui lòng xác nhận mật khẩu");
            return;
        }
        if (!password.equals(confirmPassword)) {
            ConfirmPassword.setError("Mật khẩu xác nhận không khớp");
            return;
        }
        // Tạo đối tượng SignUpRequest
        SignUpRequest signUpRequest = new SignUpRequest(username, password, fullname, mail, phone);

        // Gọi API đăng ký
        userService.signUp(signUpRequest).enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccessful()) {
                    SignUpResponse signUpResponse = response.body();
                    if (signUpResponse.getCode() == 200) {
                        // Đăng ký thành công, lưu thông tin người dùng
                        String username = signUpResponse.getResult().getUsername();
                        String fullname = signUpResponse.getResult().getFullname();
                        String mail = signUpResponse.getResult().getMail();
                        String phone = signUpResponse.getResult().getPhone();
                        // Lưu thông tin người dùng vào SharedPreferences hoặc nơi khác
                        Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        // Đăng ký thất bại, hiển thị lỗi
                        Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Xử lý lỗi khác
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                // Xử lý lỗi kết nối
                Toast.makeText(RegisterActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}