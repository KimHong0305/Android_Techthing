package com.kimhong.project_final.layout.manager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.kimhong.project_final.R;
import com.kimhong.project_final.layout.ForgotPasswordActivity1;
import com.kimhong.project_final.layout.ForgotPasswordActivity2;

public class MainAdminActivity extends AppCompatActivity {
    private ConstraintLayout btnUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);

        btnUser = findViewById(R.id.btnUser);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainAdminActivity.this, UserManagerActivity.class);
                startActivity(intent);
            }
        });
    }
}