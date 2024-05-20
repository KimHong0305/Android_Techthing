package com.kimhong.project_final.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.cart.CartItem;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.kimhong.project_final.data.model.productdetail.ProductDetailResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ProductService;
import com.kimhong.project_final.layout.CartActivity;
import com.kimhong.project_final.layout.DetailProductActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartItemAdapter extends ArrayAdapter<CartItem> {
    private Context context;
    private List<CartItem> cartItems;


    public CartItemAdapter(Context context, List<CartItem> cartItems) {
        super(context, 0, cartItems);
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_list_item, parent, false);
        }

        CartItem cartItem = cartItems.get(position);

        TextView productName = convertView.findViewById(R.id.product_name);
        TextView productQuantity = convertView.findViewById(R.id.product_quantity);
        TextView productPrice = convertView.findViewById(R.id.product_price);
        ImageView ivimage = convertView.findViewById(R.id.image_product);

        String imageUrl = cartItem.getImage();
        Log.d("CartItemAdapter", "Image URL: " + imageUrl);
        productName.setText(cartItem.getProductName());
        productQuantity.setText("Quantity: " + cartItem.getQuantity());
        productPrice.setText("Price: $ " + cartItem.getPrice());



        return convertView;
    }
}

