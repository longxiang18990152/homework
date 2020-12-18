package com.example.sqlshop;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sqlshop.adapter.BaseRecyclerAdapter;
import com.example.sqlshop.adapter.OnItemClickListener;
import com.example.sqlshop.db.Product;
import com.example.sqlshop.db.ProductDBAdapter;
import com.example.sqlshop.utils.GlideImageLoader;
import com.example.sqlshop.utils.StringUrlUtil;
import com.youth.banner.Banner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductFragment extends Fragment {

    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private BaseRecyclerAdapter adapter;
    private ProductDBAdapter productDBAdapter;
    private List<Product> productList = new ArrayList<>();
    private Button btn_search, add_btn;
    private EditText searchView;
    private Banner banner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        btn_search = view.findViewById(R.id.btn_search);
        searchView = view.findViewById(R.id.searchView);
        banner = view.findViewById(R.id.banner);
        add_btn = view.findViewById(R.id.add_btn);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddProductActivity.class));
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (Product product : productList) {
                    if (searchView.getText().toString().equals(product.getName())) {
                        startActivity(new Intent(getActivity(), ProductActivity.class)
                                .putExtra("product", product));
                    }
                }

            }
        });
        initData();
        initBanner();
        initView();
        return view;
    }

    private void initView() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里获取数据的逻辑
                productList.clear();
                productList = productDBAdapter.findAll();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull int position) {
                final Product product = productList.get(position);

                startActivity(new Intent(getContext(), ProductActivity.class).putExtra("product", product));
            }
        });
    }

    private void initBanner() {
        List<Integer> imgList = new ArrayList<>();
        imgList.add(R.mipmap.a);
        imgList.add(R.mipmap.b);
        imgList.add(R.mipmap.c);
        banner.setImages(imgList).setImageLoader(new GlideImageLoader()).start();
    }

    private void initData() {
        productDBAdapter = new ProductDBAdapter(getContext());
        productList = productDBAdapter.findAll();
        adapter = new BaseRecyclerAdapter() {
            @Override
            protected void onBindView(@NonNull BaseViewHolder holder, @NonNull int position) {
                final Product product = productList.get(position);
                TextView name = holder.getView(R.id.product_name);
                ImageView bmp = holder.getView(R.id.product_bmp);
                TextView price = holder.getView(R.id.product_price);
                name.setText(product.getName());
                price.setText("￥" + product.getPrice() + "元");
                bmp.setImageURI(StringUrlUtil.getUri(getContext(), product.bmppath));
            }

            @Override
            protected int getLayoutResId(int position) {
                return R.layout.product_item;
            }

            @Override
            public int getItemCount() {
                return productList.size();
            }
        };
    }
}