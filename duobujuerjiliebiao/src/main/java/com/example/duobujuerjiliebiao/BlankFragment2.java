package com.example.duobujuerjiliebiao;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
public class BlankFragment2 extends Fragment {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank2, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        recyclerView=inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext());
        recyclerView.setAdapter(myAdapter);


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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.addbanneritem(data);
                    }
                });

            }
        });
        OkHttpClient okHttpClient1 = new OkHttpClient();
        Request build1= new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        okHttpClient.newCall(build1).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RcyBean RcyBean1 = new Gson().fromJson(string, RcyBean.class);
                final List<RcyBean.DataBean> data = RcyBean1.getData();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(data);
                    }
                });

            }
        });


    }
}
