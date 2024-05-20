package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.user.updateUser.updateUserResponse;
import com.kimhong.project_final.data.model.user.updateUser.updateUserRequest;
import com.kimhong.project_final.data.model.user.UserResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserInfoActivity extends AppCompatActivity {

    private TextView fullname, username, email, phone;
    private UserService userService;
    private Button btnsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        fullname = findViewById(R.id.inputfullname);
        username = findViewById(R.id.inputusername);
        email = findViewById(R.id.inputmail);
        phone = findViewById(R.id.inputphone);
        btnsave = findViewById(R.id.btnsave);
        // fill data
        callApi();
        //save changes
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ud_fullname = fullname.getText().toString();
                String ud_mail = email.getText().toString();
                String ud_phone = phone.getText().toString();

                // Log the values
                Log.d("UserInfoActivity", "Fullname: " + ud_fullname);
                Log.d("UserInfoActivity", "Email: " + ud_mail);
                Log.d("UserInfoActivity", "Phone: " + ud_phone);

                callApiUpdateBio(ud_fullname, ud_mail, ud_phone);
            }
        });
    }
    private void callApiUpdateBio(String fullname, String mail, String phone) {
        userService = APIUtils.getUserService();
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "

        updateUserRequest request = new updateUserRequest(fullname, mail, phone);
        userService = APIUtils.getUserService();
        userService.updateinfo(request,authHeader).enqueue(new Callback<updateUserResponse>() {
            @Override
            public void onResponse(Call<updateUserResponse> call, Response<updateUserResponse> response) {
                if(response.isSuccessful() && response.body().getCode() == 200) {
                    Toast.makeText(UserInfoActivity.this, "Cập nhật thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("TAG", "Response Body: " + response.code());
                    Toast.makeText(UserInfoActivity.this, "Cập nhật KHÔNG thành công!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<updateUserResponse> call, Throwable t) {
                Toast.makeText(UserInfoActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // fill data api
    private void callApi() {
        userService = APIUtils.getUserService();
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "
        userService.userinfo(authHeader).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                Toast.makeText(UserInfoActivity.this, "Da vao Userinfo", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful() && response.body() != null) {
                    UserResponse userResponse = response.body();
                    fullname.setText(userResponse.getResult().getFullname());
                    username.setText(userResponse.getResult().getUsername());
                    phone.setText(userResponse.getResult().getPhone());
                    email.setText(userResponse.getResult().getMail());
                } else {
                    Toast.makeText(UserInfoActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(UserInfoActivity.this, "API call failed: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Loi api","API call failed" + t.getMessage());
            }
        });
    }
}
