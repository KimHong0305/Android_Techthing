package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.adapter.ProductAdapter;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ProductService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//import com.kimhong.project_final.adapter.ProductAdapter.OnProductClickListener;

public class MainActivity extends AppCompatActivity implements ProductAdapter.OnProductClickListener  {
    private ProductService productService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //--------------------get all products--------------------
        productService = APIUtils.getProductService();
        productService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    updateProductsUI(productResponse.getResult());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
            }
        });
        //--------------------use userinfo--------------------
        ImageView btn_userinfo = findViewById(R.id.btn_userinfo);
        btn_userinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserInfoActivity.class);
                startActivity(intent);
            }
        });
    }
    private void updateProductsUI(List<ProductResponse.Product> products) {
        ProductAdapter adapter = new ProductAdapter(products);
        adapter.setOnProductClickListener(this);
        RecyclerView recyclerView = findViewById(R.id.rcv_products);
        // Thiết lập LayoutManager
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onProductClick(ProductResponse.Product product) {
        // Xử lý khi một sản phẩm được nhấn
        Intent intent = new Intent(this, DetailProductActivity.class);
        intent.putExtra("id", product.getId());
        intent.putExtra("image", product.getImage());
        intent.putExtra("desc", product.getDescription());
        intent.putExtra("price", product.getPrice());
        intent.putExtra("name", product.getName());
        startActivity(intent);
    }
}