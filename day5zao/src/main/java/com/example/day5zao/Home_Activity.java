package com.example.day5zao;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Home_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<ListBean.DataBean.DatasBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        initView();
        initData();
    }

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

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
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

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ListBean listBean = new Gson().fromJson(string, ListBean.class);
                final ListBean.DataBean data1 = listBean.getData();
                final List<ListBean.DataBean.DatasBean> datas = data1.getDatas();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(datas);
                    }
                });
            }
        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        setSupportActionBar(toolbar);
        setTitle("");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nv.setItemIconTintList(null);
        registerForContextMenu(recyclerView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "页面一");
        menu.add(0, 1, 0, "页面二");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case 0:
                startActivity(new Intent(this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, 0, 0, "删除");
        menu.add(0, 1, 0, "修改");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                int position = myAdapter.position;
                myAdapter.delete(position);
        }
        return super.onContextItemSelected(item);
    }
}
