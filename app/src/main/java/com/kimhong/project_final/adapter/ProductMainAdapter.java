package com.kimhong.project_final.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.product.OnProductClickListener;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.bumptech.glide.Glide;
import com.kimhong.project_final.layout.DetailProductActivity;

import java.util.List;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ProductViewHolder> {

    private List<ProductResponse.Product> productList;
    public OnProductClickListener onProductClickListener;

    public interface OnProductClickListener {
        void onProductClick(ProductResponse.Product product);
    }

    public void setOnProductClickListener(OnProductClickListener onProductClickListener) {
        this.onProductClickListener = onProductClickListener;
    }

    public ProductMainAdapter(List<ProductResponse.Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductResponse.Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText("$" + String.valueOf((int) product.getPrice()));

        String imageUrl = product.getImage();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onProductClickListener != null) {
                    onProductClickListener.onProductClick(product);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if(productList != null){
            return productList.size();
        }
        return 0;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        private  TextView nameTextView;
        private  TextView priceTextView;
        private  ImageView imageView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.NameProduct);
            priceTextView = itemView.findViewById(R.id.Price);
            imageView = itemView.findViewById(R.id.image_product);
        }
    }
}
