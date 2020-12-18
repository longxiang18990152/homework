package com.example.sqlshop;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sqlshop.adapter.PrefUtils;
import com.example.sqlshop.db.UserDBAdapter;
import com.example.sqlshop.utils.PermissionsUtils;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText account, pwd;
    private UserDBAdapter userDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account = findViewById(R.id.account);
        pwd = findViewById(R.id.pwd);
        requestPermission();
        userDBAdapter = new UserDBAdapter(this);
    }

    private void requestPermission() {
        String[] permiss = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionsUtils.getInstance().chekPermissions(this, permiss, new PermissionsUtils.IPermissionsResult() {
            @Override
            public void passPermissons() {

            }

            @Override
            public void forbitPermissons() {
            }
        });
    }

    public void login(View view) {
        String us = account.getText().toString();
        String pd = pwd.getText().toString();
        if (!TextUtils.isEmpty(us) && !TextUtils.isEmpty(pd))
            if (userDBAdapter.login(us, pd)) {
                PrefUtils.setString(this, "userId", us);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
    }

    public void regist(View view) {
        startActivity(new Intent(LoginActivity.this, RegistActivity.class));
    }
}
