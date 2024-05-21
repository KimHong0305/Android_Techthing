package com.kimhong.project_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.category.CategoryResponse;

import java.util.List;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private List<CategoryResponse.Category> categories;
    private OnCategoryActionListener listener;

    public CategoryAdapter(Context context, List<CategoryResponse.Category> categories, OnCategoryActionListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.view_category_manager, parent, false);
        }

        CategoryResponse.Category category = categories.get(position);

        TextView txtName = convertView.findViewById(R.id.txtName);
        ImageView btnDelete = convertView.findViewById(R.id.btnDelete);
        ImageView btnEdit = convertView.findViewById(R.id.btnEdit);

        txtName.setText(category.getName());

        btnDelete.setOnClickListener(v -> listener.onDeleteCategory(category));
        btnEdit.setOnClickListener(v -> listener.onEditCategory(category));

        return convertView;
    }

    public interface OnCategoryActionListener {
        void onDeleteCategory(CategoryResponse.Category category);
        void onEditCategory(CategoryResponse.Category category);
    }
}