package com.example.sqlshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sqlshop.db.Product;
import com.example.sqlshop.db.ProductDBAdapter;
import com.example.sqlshop.db.ShopDBAdapter;
import com.example.sqlshop.utils.StringUrlUtil;

public class ProductActivity extends AppCompatActivity {
    private Product product;
    private EditText add_proContent, add_proPrice, add_proName;
    private ImageView add_proBmppath;
    private ProductDBAdapter productDBAdapter;
    private ShopDBAdapter shopDBAdapter;
    private Button update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        productDBAdapter = new ProductDBAdapter(this);
        shopDBAdapter = new ShopDBAdapter(this);
        add_proContent = findViewById(R.id.add_proContent);
        add_proPrice = findViewById(R.id.add_proPrice);
        add_proName = findViewById(R.id.add_proName);
        add_proBmppath = findViewById(R.id.add_proBmppath);
        update_btn = findViewById(R.id.update_btn);
        product = (Product) getIntent().getSerializableExtra("product");
        initData();
        add_proBmppath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 1001);
            }
        });
    }

    private void initData() {
        add_proContent.setText(product.getContent());
        add_proPrice.setText("￥" + product.getPrice() + "元");
        add_proName.setText(product.getName());
        add_proBmppath.setImageURI(StringUrlUtil.getUri(this, product.getBmppath()));
    }

    public void updateProduct(View view) {

        try {
            if (update_btn.getText().toString().equals("更新商品")) {
                update_btn.setText("完成");
                add_proBmppath.setClickable(true);
                add_proName.setEnabled(true);
                add_proPrice.setEnabled(true);
                add_proContent.setEnabled(true);
                add_proPrice.setText(product.getPrice());

            } else {
                if (uri == null) {
                    uri = StringUrlUtil.getUri(this, product.getBmppath());
                }
                productDBAdapter.update(product.getName()
                        , add_proName.getText().toString()
                        , add_proPrice.getText().toString()
                        , StringUrlUtil.getRealFilePath(this, uri)
                        , add_proContent.getText().toString());
                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
                finish();
            }

        } catch (Exception e) {
            Toast.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteProduct(View view) {
        try {
            productDBAdapter.delete(add_proName.getText().toString());
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show();
        }
    }

    public void addShopCar(View view) {
        try {
            if (uri == null) {
                uri = StringUrlUtil.getUri(this, product.getBmppath());
            }
            shopDBAdapter.add(add_proName.getText().toString()
                    , add_proPrice.getText().toString().replace("￥", "").replace("元", "")
                    , "1"
                    , StringUrlUtil.getRealFilePath(this, uri));
            Toast.makeText(this, "加购成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "添加失败", Toast.LENGTH_SHORT).show();
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