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
    private OnDeleteClickListener onDeleteClickListener;

    public UserAdapter(@NonNull Context context, List<UserResponse.User> users, OnDeleteClickListener onDeleteClickListener) {
        super(context, R.layout.view_user_manager, users);
        this.context = context;
        this.users = users;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.view_user_manager, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        UserResponse.User user = users.get(position);
        holder.bind(user, onDeleteClickListener);

        return convertView;
    }

    public static class ViewHolder {
        private TextView txtName, txtPhone, txtEmail;
        private ImageView deleteButton;

        public ViewHolder(View view) {
            txtName = view.findViewById(R.id.txtName);
            txtPhone = view.findViewById(R.id.txtPhone);
            txtEmail = view.findViewById(R.id.txtEmail);
            deleteButton = view.findViewById(R.id.imageView25);
        }

        public void bind(UserResponse.User user, OnDeleteClickListener onDeleteClickListener) {
            txtName.setText(user.getFullname());
            txtPhone.setText(user.getPhone());
            txtEmail.setText(user.getMail());
            deleteButton.setOnClickListener(v -> onDeleteClickListener.onDeleteClick(user.getId()));
        }
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(String userId);
    }
}