package com.example.sqlshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqlshop.db.ProductDBAdapter;
import com.example.sqlshop.utils.StringUrlUtil;

public class AddProductActivity extends AppCompatActivity {
    private EditText add_proContent, add_proPrice, add_proName;
    private ImageView add_proBmppath;
    private ProductDBAdapter productDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        add_proContent = findViewById(R.id.add_proContent);
        add_proPrice = findViewById(R.id.add_proPrice);
        add_proName = findViewById(R.id.add_proName);
        add_proBmppath = findViewById(R.id.add_proBmppath);
        productDBAdapter = new ProductDBAdapter(this);
        add_proBmppath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 1001);
            }
        });

    }

    public void addProduct(View view) {
        String name = add_proName.getText().toString();
        String price = add_proPrice.getText().toString();
        String content = add_proContent.getText().toString();
        String bmpPath = StringUrlUtil.getRealFilePath(this, uri);
        Log.e("testDemo", bmpPath);
        if (!TextUtils.isEmpty(name) & !TextUtils.isEmpty(bmpPath)) {
            try {
                if (!productDBAdapter.CheckIsDataAlreadyInDBorNot(name)) {
                    productDBAdapter.add(name, price, bmpPath, content);
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "商品已存在", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
            }

        }
    }

    private Uri uri;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                uri = data.getData();

                add_proBmppath.setImageURI(uri);
            }
        }
    }

}