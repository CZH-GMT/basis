package com.example.bannerbuju;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclewView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private static final String TAG = "MainActivity";
    //首页文章列表访问路径
    public static final String articleStr = "http://www.wanandroid.com/article/list/0/json";
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
                Log.e("TAG", e.toString() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: "+string);
                BannerBean bannerBean = new Gson().fromJson(string, BannerBean.class);
                final List<BannerBean.DataBean> data = bannerBean.getData();
                runOnUiThread(new Runnable() {
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
                Log.e("TAG", e.toString() );
            }

            private static final String TAG = "MainActivity";
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: "+string);
                ListBean listBean = new Gson().fromJson(string, ListBean.class);
                final List<ListBean.DataBean.DatasBean> datas1 = listBean.getData().getDatas();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.addItem(datas1);
                    }
                });
            }
        });


    }

    private void initView() {
        recyclewView = (RecyclerView) findViewById(R.id.recyclewView);
        recyclewView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclewView.setAdapter(myAdapter);
    }
}
