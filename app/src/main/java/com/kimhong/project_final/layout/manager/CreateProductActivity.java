package com.kimhong.project_final.layout.manager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.kimhong.project_final.R;
import com.kimhong.project_final.data.model.admin.product.CreateProductRequest;
import com.kimhong.project_final.data.model.admin.product.CreateProductResponse;
import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailRequest;
import com.kimhong.project_final.data.model.forgotPassword.VerifyEmailResponse;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.AdminService;
import com.kimhong.project_final.layout.LoginActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateProductActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_PICK_IMAGE = 123;
    private File selectedImageFile;
    private EditText nameEditText, priceEditText, descriptionEditText, categoryEditText;
    private ConstraintLayout saveButton;
    private ImageView btnUpload, btnBack;
    private AdminService adminService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);
        nameEditText = findViewById(R.id.Name);
        priceEditText = findViewById(R.id.Price);
        descriptionEditText = findViewById(R.id.Desc);
        categoryEditText = findViewById(R.id.catagory);
        saveButton = findViewById(R.id.btnCreate);


        adminService = APIUtils.getAdminService();
        btnUpload = findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProductActivity.this, ProductManagerActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProduct();
            }
        });
    }
    private void createProduct() {
        String name = nameEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String category = categoryEditText.getText().toString();
        CreateProductRequest request = new CreateProductRequest(name, price, description, category);
        createProductOnServer(request);
    }
    private void createProductOnServer(CreateProductRequest request) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE);
        String authToken = sharedPreferences.getString("token", "");

        // Nếu authToken hợp lệ, tiếp tục gọi API
        if (selectedImageFile != null) {
            RequestBody fileBody = RequestBody.create(MediaType.parse("image/*"), selectedImageFile);
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", selectedImageFile.getName(), fileBody);

            adminService.createProduct("Bearer " + authToken, request, filePart)
                    .enqueue(new Callback<CreateProductResponse>() {
                        @Override
                        public void onResponse(Call<CreateProductResponse> call, Response<CreateProductResponse> response) {
                            if (response.isSuccessful()) {
                                CreateProductResponse createProductResponse = response.body();
                                Toast.makeText(CreateProductActivity.this, "Tạo sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateProductActivity.this, ProductManagerActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreateProductActivity.this, "Tạo sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateProductResponse> call, Throwable t) {
                            Toast.makeText(CreateProductActivity.this, "Lỗi kết nối ", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Nếu chưa có file ảnh được chọn, gọi API với null cho filePart
            adminService.createProduct(authToken, request, null)
                    .enqueue(new Callback<CreateProductResponse>() {
                        @Override
                        public void onResponse(Call<CreateProductResponse> call, Response<CreateProductResponse> response) {
                            if (response.isSuccessful()) {
                                CreateProductResponse createProductResponse = response.body();
                                Toast.makeText(CreateProductActivity.this, "Tạo sản phẩm thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(CreateProductActivity.this, ProductManagerActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(CreateProductActivity.this, "Tạo sản phẩm không thành công", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<CreateProductResponse> call, Throwable t) {
                            Toast.makeText(CreateProductActivity.this, "Lỗi kết nối", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            handleSelectedImage(selectedImageUri);
        }
    }

    private void handleSelectedImage(Uri selectedImageUri) {
        // Lưu ảnh đã chọn vào selectedImageFile
        selectedImageFile = new File(getRealPathFromUri(selectedImageUri));
    }

    private String getRealPathFromUri(Uri uri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}