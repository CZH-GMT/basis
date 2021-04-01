package com.example.okhttp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private SmartRefreshLayout sml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }
private int pager=3;
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request builder = new Request.Builder()
                .url("http://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/"+pager++)
                .build();
        okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                final List<Bean.ResultsDTO> results = bean.getResults();


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(results);
                        sml.finishLoadMore();
                    }
                });
            }
        });


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        sml = (SmartRefreshLayout) findViewById(R.id.sml);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        sml.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //上拉
                initData();
                sml.finishLoadMore(3000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //下拉
                sml.finishRefresh(3000);
            }
        });



    }


}
