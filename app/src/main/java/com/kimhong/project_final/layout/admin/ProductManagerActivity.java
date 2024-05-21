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
import com.kimhong.project_final.adapter.ProductAdapter;
import com.kimhong.project_final.data.model.admin.product.DeleteProductResponse;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ProductService;
import com.kimhong.project_final.data.service.AdminService;
import com.kimhong.project_final.layout.manager.MainManagerActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManagerActivity extends AppCompatActivity {
    private ListView listView;
    private ProductAdapter productAdapter;
    private ProductService productService;
    private AdminService adminService;
    private List<ProductResponse.Product> products;
    private ConstraintLayout btnCreate;
    private ImageView btnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);

        listView = findViewById(R.id.listview);
        btnCreate = findViewById(R.id.btnCreate);
        btnHome = findViewById(R.id.btnHome);

        productService = APIUtils.getProductService();
        adminService = APIUtils.getAdminService();
        getProductList();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagerActivity.this, CreateProductActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String role =  sharedPreferences.getString("role", "");
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (role == "ADMIN") {
                    Intent intent = new Intent(ProductManagerActivity.this, MainAdminActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(ProductManagerActivity.this, MainManagerActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void getProductList() {
        productService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();
                    if (productResponse.getCode() == 200) {
                        products = productResponse.getResult();
                        setupProductAdapter(products);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductManagerActivity.this, "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupProductAdapter(List<ProductResponse.Product> products) {
        if (products != null && !products.isEmpty()) {
            productAdapter = new ProductAdapter(
                    ProductManagerActivity.this,
                    products,
                    productId -> deleteProduct(productId),
                    productId -> editProduct(productId)
            );
            listView.setAdapter(productAdapter);
        } else {
            // Handle the case where the products list is empty
            Toast.makeText(ProductManagerActivity.this, "No products found.", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteProduct(String productId) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("token", "");
        adminService.deleteProduct("Bearer " + authToken, productId).enqueue(new Callback<DeleteProductResponse>() {
            @Override
            public void onResponse(Call<DeleteProductResponse> call, Response<DeleteProductResponse> response) {
                if (response.isSuccessful()) {
                    DeleteProductResponse deleteProductResponse = response.body();
                    if (deleteProductResponse.getCode() == 200) {
                        Toast.makeText(ProductManagerActivity.this, "Xóa sản phẩm thành công", Toast.LENGTH_SHORT).show();
                        getProductList(); // Refresh the user list
                    }
                } else {
                    Toast.makeText(ProductManagerActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteProductResponse> call, Throwable t) {
                Toast.makeText(ProductManagerActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editProduct(String productId) {
        // Implement the logic to edit the product
    }
}