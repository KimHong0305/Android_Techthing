package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.cart.GetCartResponse;
import com.kimhong.project_final.data.model.invoices.CheckoutRequest;
import com.kimhong.project_final.data.model.invoices.CheckoutResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.CartService;
import com.kimhong.project_final.data.service.InvoiceService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShippingInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping_info);
        TextView shippinginfo = findViewById(R.id.et_shippinginfo);

        CartService cartService = APIUtils.getCartService();
        InvoiceService invoiceService = APIUtils.getInvoiceService();

        Button btn = findViewById(R.id.btn_checkout);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay cartdetail id
                SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                String token = sharedPreferences.getString("token", "");
                String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "



                List<String> cartDetailIDs = new ArrayList<>();

                String address = shippinginfo.getText().toString();
                Log.d("TAG", "Address: " + address);
                //--lay cartDetailid-----
                cartService.getMyCart(authHeader).enqueue(new Callback<GetCartResponse>() {
                    @Override
                    public void onResponse(Call<GetCartResponse> call, Response<GetCartResponse> response) {
                        if(response.isSuccessful()){
                            Log.d("TAG", "Dang lay Cart DetailiD ");
                            Toast.makeText(ShippingInfo.this, "Dang lay Cart DetailiD", Toast.LENGTH_SHORT).show();
                            List<GetCartResponse.ItemCartResponse> itemCartResponses = response.body().getResult();
                            for (GetCartResponse.ItemCartResponse item : itemCartResponses) {
                                cartDetailIDs.add(item.getCartDetail_id());
                            }
                            Log.d("TAG", "Cart DetailiD " + cartDetailIDs);

                            //---checkout-----
                            SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
                            String token = sharedPreferences.getString("token", "");
                            String authHeader = "Bearer " + token; // Thêm tiền tố "Bearer "
                            CheckoutRequest request = new CheckoutRequest(address, cartDetailIDs);

                            invoiceService.createInvoice(request,authHeader).enqueue(new Callback<CheckoutResponse>() {
                                @Override
                                public void onResponse(Call<CheckoutResponse> call, Response<CheckoutResponse> response) {
                                    if(response.isSuccessful()){
                                        Toast.makeText(ShippingInfo.this, "Da dat hang thanh cong", Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        Log.d("TAG", "Response Body: " + response.code());
                                        Toast.makeText(ShippingInfo.this, "Da dat hang KHONG thanh cong", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<CheckoutResponse> call, Throwable t) {
                                    Toast.makeText(ShippingInfo.this, "Fail to call API INVOICE", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else {
                            Toast.makeText(ShippingInfo.this, "FAIl", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetCartResponse> call, Throwable t) {
                        Toast.makeText(ShippingInfo.this, "Fail to call API CART", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });




    }
}