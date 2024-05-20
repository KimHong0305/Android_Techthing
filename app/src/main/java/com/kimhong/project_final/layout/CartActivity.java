package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.adapter.CartItemAdapter;
import com.kimhong.project_final.data.model.cart.CartItem;
import com.kimhong.project_final.data.model.cart.GetCartResponse;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.CartService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    private ListView listView;
    private CartItemAdapter adapter;
    private List<CartItem> cartItems = new ArrayList<>();

    private CartService cartService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        //setup list view
        listView = findViewById(R.id.shopping_list_view);
        adapter = new CartItemAdapter(this, cartItems);
        listView.setAdapter(adapter);
        Button btn_checkout = findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, ShippingInfo.class);
                startActivity(intent);
            }
        });
        //-------- call api --------
        callapicart();


    }
    public void callapicart(){
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "
        cartService = APIUtils.getCartService();
        cartService.getMyCart(authHeader).enqueue(new Callback<GetCartResponse>() {
            @Override
            public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(CartActivity.this, "Thanh cong", Toast.LENGTH_SHORT).show();

                    List<CartItem> newCartItems = convertToCartItems(response.body().getResult());
                    GetCartResponse getCartResponse = response.body();
                    updateCartItemUI(newCartItems);
                } else {
                    Toast.makeText(CartActivity.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetCartResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Call API fail", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateCartItemUI(List<CartItem> newCartItems) {
        cartItems.clear();
        cartItems.addAll(newCartItems);
        adapter.notifyDataSetChanged();
    }
    private List<CartItem> convertToCartItems(List<GetCartResponse.ItemCartResponse> itemCartResponses) {
        List<CartItem> cartItems = new ArrayList<>();
        for (GetCartResponse.ItemCartResponse response : itemCartResponses) {
            CartItem item = new CartItem(response.getProduct_id(),response.getProduct_name(),response.getImage(), (int) response.getQuantity(), response.getSub_total());
            cartItems.add(item);
        }
        return cartItems;
    }
}