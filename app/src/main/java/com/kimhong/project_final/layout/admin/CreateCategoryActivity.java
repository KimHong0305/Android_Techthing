package com.kimhong.project_final.layout.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.category.CreateCategoryRequest;
import com.kimhong.project_final.data.model.admin.category.CreateCategoryResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AdminService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCategoryActivity extends AppCompatActivity {
    private EditText etName, etDescription;
    private ConstraintLayout btnCreate;
    private AdminService adminService;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_category);

        etName = findViewById(R.id.Name);
        etDescription = findViewById(R.id.Desc);
        btnCreate = findViewById(R.id.btnCreate);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCategoryActivity.this, CategoryManagerActivity.class);
                startActivity(intent);
            }
        });

        adminService = APIUtils.getAdminService();

        btnCreate.setOnClickListener(v -> createCategory());
    }

    private void createCategory() {
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setName(name);
        request.setDescription(description);

        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("token", "");

        adminService.createCategory("Bearer " + authToken, request).enqueue(new Callback<CreateCategoryResponse>() {
            @Override
            public void onResponse(Call<CreateCategoryResponse> call, Response<CreateCategoryResponse> response) {
                if (response.isSuccessful()) {
                    CreateCategoryResponse createCategoryResponse = response.body();
                    if (createCategoryResponse.getCode() == 200) {
                        CreateCategoryResponse.CategoryData categoryData = createCategoryResponse.getResult();
                        Toast.makeText(CreateCategoryActivity.this, "Thêm danh mục thành công ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreateCategoryActivity.this, CategoryManagerActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(CreateCategoryActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CreateCategoryResponse> call, Throwable t) {
                Toast.makeText(CreateCategoryActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}