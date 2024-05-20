package com.kimhong.project_final.layout.manager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.adapter.ProductAdapter;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.model.product.ProductResponse.Product;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductManagerActivity extends AppCompatActivity {
    private ListView listView;
    private ProductAdapter productAdapter;
    private ProductService productService;
    private List<ProductResponse.Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_manager);

        listView = findViewById(R.id.listview);
        productService = APIUtils.getProductService();
        getProductList();
    }

    private void getProductList() {
        productService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    if (productResponse.getCode() == 200) {
                        products = productResponse.getResult();
                        setupProductAdapter(products);
                    } else {
                        Toast.makeText(ProductManagerActivity.this, "Error: " + productResponse.getCode(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ProductManagerActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(ProductManagerActivity.this, "Connection error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupProductAdapter(List<ProductResponse.Product> products) {
        productAdapter = new ProductAdapter(
                ProductManagerActivity.this,
                products,
                productId -> deleteProduct(productId),
                productId -> editProduct(productId)
        );
        listView.setAdapter(productAdapter);
    }

    private void deleteProduct(String productId) {
        // Implement the logic to delete the product
    }

    private void editProduct(String productId) {
        // Implement the logic to edit the product
    }
}