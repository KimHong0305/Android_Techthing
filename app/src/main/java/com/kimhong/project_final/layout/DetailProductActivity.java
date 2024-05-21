package com.kimhong.project_final.layout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.cart.AddToCartRequest;
import com.kimhong.project_final.data.model.cart.AddToCartResponse;
import com.kimhong.project_final.data.model.user.updateUser.updateUserRequest;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.CartService;
import com.kimhong.project_final.data.service.ProductService;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProductActivity extends AppCompatActivity {

    TextView tvname, tvdesc, tvprice;
    ImageView ivimage;
    Button btn;
    private ProductService productService;
    private CartService cartService;
    private static final String TAG = "DetailProductActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        tvname = findViewById(R.id.txt_producttitle);
        tvdesc = findViewById(R.id.txt_desc);
        tvprice = findViewById(R.id.txt_productprice);
        ivimage = findViewById(R.id.product_image);
        btn = findViewById(R.id.add_to_cart_button);
        // Nhận dữ liệu từ Intent

        String imageUrl = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        float price = getIntent().getFloatExtra("price", 0.0f);
        String desc = getIntent().getStringExtra("desc");
        String sprice = String.valueOf(price);
        // Hiển thị dữ liệu
        Glide.with(this).load(imageUrl).into(ivimage);
        tvname.setText(name);
        tvprice.setText(sprice);
        tvdesc.setText(desc);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callApi_addtoCart();
            }
        });



    }
    //add to cart
    public void callApi_addtoCart() {
        String id = getIntent().getStringExtra("id");
        AddToCartRequest request = new AddToCartRequest(id, 1);
        cartService = APIUtils.getCartService();
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "

        Log.d(TAG, "Token: " + sharedPreferences.getString("token", ""));
        Log.d(TAG, "Request: " + request.toString());
        cartService.addToCart(request,authHeader).enqueue(new Callback<AddToCartResponse>() {
            @Override
            public void onResponse(Call<AddToCartResponse> call, Response<AddToCartResponse> response) {
                Log.d(TAG, "Response Body: " + response.code());
                if (response.isSuccessful()) {
                    Toast.makeText(DetailProductActivity.this, "Success Add To Cart", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DetailProductActivity.this, "Fail to add", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AddToCartResponse> call, Throwable t) {
                Toast.makeText(DetailProductActivity.this, "Fail to call API", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "API Call Failed: " + t.getMessage());
            }
        });
    }
}