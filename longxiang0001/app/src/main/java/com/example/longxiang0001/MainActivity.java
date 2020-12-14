package com.example.longxiang0001;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.longxiang0001.util.DisplayUtil;
import com.example.longxiang0001.util.Utils;

public class MainActivity extends AppCompatActivity {

    private TextView text_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取id
        text_screen = findViewById(R.id.test_screen);


        int dip_10 = Utils.dip2px(this,10L);

        text_screen.setPadding(dip_10, dip_10, dip_10, dip_10);
        text_screen.setBackgroundColor(0xff00ffff);
        text_screen.setTextColor(0xff333333);
        showScreenInfo();
    }

    private  void  showScreenInfo(){
        int width = DisplayUtil.getScreenWidth( this);
        int height = DisplayUtil.getScreenWidth( this);
        int sensity = DisplayUtil.getScreenWidth( this);
        String info = String.format("当前屏幕的宽度是%dpx，高度是%dpx，像数密度是%f",width,height,sensity);
        text_screen.setText(info);
    }
}
