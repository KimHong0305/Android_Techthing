package com.kimhong.project_final.layout.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.kimhong.project_final.adapter.CategoryAdapter;
import com.kimhong.project_final.data.model.admin.category.CategoryResponse;
import com.kimhong.project_final.data.model.admin.category.DeleteCategoryResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AdminService;
import com.kimhong.project_final.layout.manager.MainManagerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryManagerActivity extends AppCompatActivity {
    private AdminService adminService;
    private CategoryAdapter categoryAdapter;
    private ListView listView;
    private ImageView btnHome;
    private ConstraintLayout btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_manager);
        listView = findViewById(R.id.listview);
        btnHome = findViewById(R.id.btnHome);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoryManagerActivity.this, CreateCategoryActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String role =  sharedPreferences.getString("role", "");
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == "ADMIN") {
                    Intent intent = new Intent(CategoryManagerActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(CategoryManagerActivity.this, MainManagerActivity.class);
                    startActivity(intent);
                }
            }
        });

        adminService = APIUtils.getAdminService();
        getCategoryList();
    }

    private void getCategoryList() {
        adminService.getCategory().enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful()) {
                    CategoryResponse categoryResponse = response.body();
                    if (categoryResponse.getCode() == 200) {
                        List<CategoryResponse.Category> categories = categoryResponse.getResult();
                        categoryAdapter = new CategoryAdapter(CategoryManagerActivity.this, categories, new CategoryAdapter.OnCategoryActionListener() {
                            @Override
                            public void onDeleteCategory(CategoryResponse.Category category) {
                                deleteCategory(category.getId());
                            }

                            @Override
                            public void onEditCategory(CategoryResponse.Category category) {
                                // Xử lý sự kiện chỉnh sửa danh mục
                            }
                        });
                        listView.setAdapter(categoryAdapter);
                    } else {
                        Toast.makeText(CategoryManagerActivity.this, "Error: " + categoryResponse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(CategoryManagerActivity.this, "Loi ket noi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteCategory(String categoryId) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("token", "");

        adminService.deleteCategory("Bearer " + authToken, categoryId).enqueue(new Callback<DeleteCategoryResponse>() {
            @Override
            public void onResponse(Call<DeleteCategoryResponse> call, Response<DeleteCategoryResponse> response) {
                if (response.isSuccessful()) {
                    DeleteCategoryResponse deleteCategoryResponse = response.body();
                    if (deleteCategoryResponse.getCode() == 200) {
                        Toast.makeText(CategoryManagerActivity.this, "Xóa danh mục thành công", Toast.LENGTH_SHORT).show();
                        getCategoryList(); // Refresh the category list
                    } else {
                        Toast.makeText(CategoryManagerActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CategoryManagerActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteCategoryResponse> call, Throwable t) {
                Toast.makeText(CategoryManagerActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }
}