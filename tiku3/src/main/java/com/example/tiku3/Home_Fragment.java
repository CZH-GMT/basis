package com.example.tiku3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragment extends Fragment {

    private RecyclerView recyclerview;
    private FragmentActivity activity;
    private MyAdapter myAdapter;

    public Home_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_, container, false);
        activity = getActivity();
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        recyclerview=inflate.findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext());
        recyclerview.setAdapter(myAdapter);
        myAdapter.setOnitemclicklistener(new MyAdapter.onitemclicklistener() {
            @Override
            public void onitemclick() {
                startActivity(new Intent(activity,WebView_Activity.class));
            }
        });
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                BannerBean bannerBean = new Gson().fromJson(string, BannerBean.class);
                final List<BannerBean.DataBean> data = bannerBean.getData();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.addbanneritem(data);
                    }
                });
            }
        });
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request build1 = new Request.Builder()
                .url("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/4")
                .build();
        okHttpClient1.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                final List<Bean.ResultsBean> results = bean.getResults();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(results);
                    }
                });
            }
        });


    }
}
