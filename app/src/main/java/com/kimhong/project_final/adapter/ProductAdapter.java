package com.kimhong.project_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.product.ProductResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<ProductResponse.Product> products;
    private OnDeleteClickListener onDeleteClickListener;
    private OnEditClickListener onEditClickListener;

    public ProductAdapter(Context context, List<ProductResponse.Product> products, OnDeleteClickListener onDeleteClickListener, OnEditClickListener onEditClickListener) {
        this.context = context;
        this.products = products;
        this.onDeleteClickListener = onDeleteClickListener;
        this.onEditClickListener = onEditClickListener;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.view_product_main, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ProductResponse.Product product = products.get(position);
        holder.bind(product, onDeleteClickListener, onEditClickListener);

        return convertView;
    }

    public static class ViewHolder {
        private TextView txtName, txtPrice;

        public ViewHolder(View view) {
            txtName = view.findViewById(R.id.txtName);
//            txtPrice = view.findViewById(R.id.txtPrice);
        }

        public void bind(ProductResponse.Product product, OnDeleteClickListener onDeleteClickListener, OnEditClickListener onEditClickListener) {
//
            txtName.setText(product.getName());
//            txtPrice.setText(String.format("%.2f", product.getPrice()));
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(String productId);
    }

    public interface OnEditClickListener {
        void onEditClick(String productId);
    }
}