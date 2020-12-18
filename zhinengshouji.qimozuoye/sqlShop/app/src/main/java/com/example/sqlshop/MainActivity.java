package com.example.sqlshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigation;
    private Fragment[] fragmentsAdmin;
    private ProductFragment productFragment;
    private ShopFragment shopFragment;
    private int lastFragments = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productFragment = new ProductFragment();
        shopFragment = new ShopFragment();
        fragmentsAdmin = new Fragment[]{productFragment, shopFragment};
        lastFragments = 0;
        bottomNavigation = findViewById(R.id.bottomNavigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.mframeLayout, fragmentsAdmin[0]).show(fragmentsAdmin[0]).commit();
        bottomNavigation.setOnNavigationItemSelectedListener(changeFragment);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_product:
                    if (lastFragments != 0) {
                        switchFragment(lastFragments, 0);
                        lastFragments = 0;
                    }
                    return true;
                case R.id.menu_shopcar:
                    if (lastFragments != 1) {
                        switchFragment(lastFragments, 1);
                        lastFragments = 1;
                    }

                    return true;
            }

            return false;
        }
    };

    private void switchFragment(int lastFragments, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragmentsAdmin[lastFragments]);
        if (!fragmentsAdmin[index].isAdded()) {
            transaction.add(R.id.mframeLayout, fragmentsAdmin[index]);
        }
        transaction.show(fragmentsAdmin[index]).commitAllowingStateLoss();
    }
}