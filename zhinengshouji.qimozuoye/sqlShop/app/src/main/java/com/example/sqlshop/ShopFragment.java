package com.example.sqlshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlshop.adapter.BaseRecyclerAdapter;
import com.example.sqlshop.adapter.OnItemClickListener;
import com.example.sqlshop.db.Product;
import com.example.sqlshop.db.ProductDBAdapter;
import com.example.sqlshop.db.ShopCar;
import com.example.sqlshop.db.ShopDBAdapter;
import com.example.sqlshop.utils.GlideImageLoader;
import com.example.sqlshop.utils.StringUrlUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public class ShopFragment extends Fragment {

    public ShopFragment() {
        // Required empty public constructor
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private BaseRecyclerAdapter adapter;
    private ShopDBAdapter shopDBAdapter;
    private List<ShopCar> shopCarList = new ArrayList<>();
    private TextView total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        total = view.findViewById(R.id.total);
        recyclerView = view.findViewById(R.id.recyclerView);
        initData();
        initView();
        return view;
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                shopCarList = shopDBAdapter.findAll();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    private double totalSize = 0.00;

    private void initData() {
        shopDBAdapter = new ShopDBAdapter(getContext());
        shopCarList = shopDBAdapter.findAll();
        adapter = new BaseRecyclerAdapter() {
            @Override
            protected void onBindView(@NonNull BaseViewHolder holder, @NonNull int position) {
                final ShopCar product = shopCarList.get(position);
                TextView name = holder.getView(R.id.product_name);
                ImageView bmp = holder.getView(R.id.product_bmp);
                TextView price = holder.getView(R.id.product_price);
                TextView num = holder.getView(R.id.product_num);
                name.setText(product.getName());
                bmp.setImageURI(StringUrlUtil.getUri(getContext(), product.bmppath));
                num.setText("数量" + product.getNum() + "件");
                price.setText("￥" + product.getPrice() + "元");
                totalSize += Double.parseDouble(product.getPrice());
                total.setText("总价￥" + totalSize + "元");

            }

            @Override
            protected int getLayoutResId(int position) {
                return R.layout.shopcar_item;
            }

            @Override
            public int getItemCount() {
                return shopCarList.size();
            }
        };
    }
}