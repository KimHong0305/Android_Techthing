package com.kimhong.project_final.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kimhong.project_final.R;
import com.kimhong.project_final.data.remote.APIUtils;
import com.kimhong.project_final.data.service.ProductService;

import org.w3c.dom.Text;

public class DetailProductActivity extends AppCompatActivity {

    TextView tvname, tvdesc, tvprice;
    ImageView ivimage;
    private ProductService productService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        tvname = findViewById(R.id.txt_producttitle);
        tvdesc = findViewById(R.id.txt_desc);
        tvprice = findViewById(R.id.txt_productprice);
        ivimage = findViewById(R.id.product_image);
        // Nhận dữ liệu từ Intent

        String imageUrl = getIntent().getStringExtra("image");
        String name = getIntent().getStringExtra("name");
        float price = getIntent().getFloatExtra("price", 0.0f);
        String desc = getIntent().getStringExtra("desc");

        // Hiển thị dữ liệu
        Glide.with(this).load(imageUrl).into(ivimage);
        tvname.setText(name);
        tvprice.setText(String.valueOf(price));
        tvdesc.setText(desc);

    }
    public void callApi(){
        productService = APIUtils.getProductService();
//        productService.productDetail();
    }
}