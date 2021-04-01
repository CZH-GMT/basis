package com.example.day4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShouYeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Fragment> list=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.shouye, null);
        initData();
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        recyclerView=inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext());
        recyclerView.setAdapter(myAdapter);

    }
    //首页文章列表访问路径
    public static final String articleStr = "https://gitee.com/ccyy2019/server/raw/master/workbook_test1";
    //首页录播图访问路径
    public static final String bannerStr = "https://www.wanandroid.com/banner/json";

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url(bannerStr)
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                bannerBean bannerBean = new Gson().fromJson(string, bannerBean.class);
                final List<com.example.day4.bannerBean.DataBean> data = bannerBean.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.addbanneritem(data);
                    }
                });

            }
        });
        Request build1 = new Request.Builder()
                .url(articleStr)
                .build();
        okHttpClient.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ListBean listBean = new Gson().fromJson(string, ListBean.class);
                final ListBean.DataBean data = listBean.getData();
                final List<ListBean.DataBean.CatalogBean> catalog = data.getCatalog();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(catalog);
                    }
                });
            }
        });

    }
}
