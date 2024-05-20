package com.kimhong.project_final.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.user.UserResponse;

import java.util.List;

public class UserAdapter extends ArrayAdapter<UserResponse.User> {
    private Context context;
    private List<UserResponse.User> users;

    public UserAdapter(@NonNull Context context, List<UserResponse.User> users) {
        super(context, R.layout.view_user_manager, users);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_user_manager, parent, false);

        UserResponse.User user = users.get(position);

        TextView txtName = view.findViewById(R.id.txtName);
        TextView txtPhone = view.findViewById(R.id.txtPhone);
        TextView txtEmail = view.findViewById(R.id.txtEmail);
        ImageView deleteButton = view.findViewById(R.id.imageView25);

        txtName.setText(user.getFullname());
        txtPhone.setText(user.getPhone());
        txtEmail.setText(user.getMail());

        // Xử lý sự kiện xóa user
        deleteButton.setOnClickListener(v -> {
            // Xóa user từ danh sách và cập nhật adapter
            users.remove(position);
            notifyDataSetChanged();
        });

        return view;
    }
}