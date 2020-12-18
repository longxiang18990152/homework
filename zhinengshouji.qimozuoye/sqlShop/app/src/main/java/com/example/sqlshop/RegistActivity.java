package com.example.sqlshop;

import android.app.VoiceInteractor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.example.sqlshop.adapter.PrefUtils;
import com.example.sqlshop.db.UserDBAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class RegistActivity extends AppCompatActivity {
    private EditText account, pwd;
    private UserDBAdapter userDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        userDBAdapter = new UserDBAdapter(this);
        account = findViewById(R.id.account);//获取控件
        pwd = findViewById(R.id.pwd);

    }

    public void regist(View view) {
        String us = account.getText().toString();
        String pd = pwd.getText().toString();//获取内容
        if (!TextUtils.isEmpty(us) && !TextUtils.isEmpty(pd)) {
            if (!userDBAdapter.CheckIsDataAlreadyInDBorNot(us)) {
                userDBAdapter.add(us, pd);
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                finish();

            } else {
                Toast.makeText(this, "该用户名存在", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "请输入正确用户名密码", Toast.LENGTH_SHORT).show();
        }
    }
}
