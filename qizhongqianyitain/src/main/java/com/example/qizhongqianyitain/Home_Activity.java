package com.example.qizhongqianyitain;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    private RecyclerView recyclerView;
    private LinearLayout ll;
    private NavigationView nv;
    private int math;
    private DrawerLayout dl;
    private List<Bean.DataBean> list=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
                HeaderAdapter.notifyDataSetChanged();
            }
        }
    };
    private MyAdapter HeaderAdapter;
    private Bean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        initView();
        initData();
        initRcy();
        initHeader();

    }

    private void initData() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        okHttpClient.newCall(build)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String string = response.body().string();
                        Bean bean = new Gson().fromJson(string, Bean.class);
                        final List<Bean.DataBean> data = bean.getData();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                list.addAll(data);
                                mHandler.sendEmptyMessage(1);

                            }
                        });
                    }
                });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float v = nv.getWidth() * slideOffset;
                ll.setLeft((int)v);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });



    }

    private void initRcy() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter RcyAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(RcyAdapter);
        registerForContextMenu(recyclerView);
    }

    private void initHeader() {
        View headerView = nv.getHeaderView(0);
        final RecyclerView viewById = headerView.findViewById(R.id.recyclerView);

        viewById.setLayoutManager(new LinearLayoutManager(this));
        HeaderAdapter = new MyAdapter(this,list);
        viewById.setAdapter(HeaderAdapter);
        HeaderAdapter.setOnItemClikListener(new MyAdapter.OnItemClikListener() {
            @Override
            public void OnItemClik(View v, int position) {
                math=position;
                dataBean = list.get(position);

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"红色");
        menu.add(0,1,0,"蓝色");
        menu.add(0,2,0,"恢复");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                ll.setBackgroundColor(Color.RED);

            break;
            case 1:
                ll.setBackgroundColor(Color.BLUE);
                break;
            case 2:
                ll.setBackgroundColor(Color.WHITE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,0,0,"删除");
        menu.add(0,1,0,"修改");
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Bean.DataBean dataBean = list.get(myAdapter.position);
                list.remove(dataBean);
                break;
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onContextItemSelected(item);
    }

}
