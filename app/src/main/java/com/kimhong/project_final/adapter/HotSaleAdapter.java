package com.kimhong.project_final.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.product.ProductResponse;

import java.util.List;

public class HotSaleAdapter extends RecyclerView.Adapter<HotSaleAdapter.ViewHolder> {
    private List<ProductResponse.Product> productList;

    public HotSaleAdapter(List<ProductResponse.Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_product_main, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductResponse.Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("$" + String.valueOf((int) product.getPrice()));

        String imageUrl = product.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameTextView;
        private final TextView priceTextView;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.NameProduct);
            priceTextView = itemView.findViewById(R.id.Price);
            imageView = itemView.findViewById(R.id.image_product);
        }
    }
}