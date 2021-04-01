package com.example.a4;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tl;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initData() {
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request build = new Request.Builder()
//                .url("https://www.wanandroid.com/project/tree/json")
//                .build();
//        okHttpClient.newCall(build).enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String string = response.body().string();
//                Bean bean = new Gson().fromJson(string, Bean.class);
//                final List<Bean.DataBean> data = bean.getData();
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        fragments = new ArrayList<>();
//                        strings = new ArrayList<>();
//                        for (int i = 0; i <data.size() ; i++) {
//                            Bean.DataBean dataBean = data.get(i);
//                            fragments.add(Home_Fragment.newInstance(dataBean.getId()+"",null));
//                            strings.add(dataBean.getName());
//                        }
//                        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//                            @NonNull
//                            @Override
//                            public Fragment getItem(int position) {
//                                return fragments.get(position);
//                            }
//
//                            @Override
//                            public int getCount() {
//                                return fragments.size();
//                            }
//                        });
//                        tl.setupWithViewPager(vp);
//
//                    }
//                });
//
//
//            }
//        });

    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        setTitle("首页");
        setSupportActionBar(toolbar);
//        View headerView = nv.getHeaderView(0);
        View headerView = nv.getHeaderView(0);

//        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
//        dl.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Home_Fragment());
        fragments.add(new GongZhongHaoFragment());
        fragments.add(new NeiRongFragment());
        fragments.add(new ProjectFragment());
        fragments.add(new MyFragment());

//        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
//            @NonNull
//            @Override
//            public Fragment getItem(int position) {
//                return fragments.get(position);
//            }
//
//            @Override
//            public int getCount() {
//                return fragments.size();
//            }
//
//        });
        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });

        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setIcon(R.drawable.sheap).setText("首页");
        tl.getTabAt(1).setIcon(R.drawable.sheap).setText("公众号");
        tl.getTabAt(2).setIcon(R.drawable.sheap).setText("内容");
        tl.getTabAt(3).setIcon(R.drawable.sheap).setText("项目");
        tl.getTabAt(4).setIcon(R.drawable.sheap).setText("我的");

        ImageView viewById = headerView.findViewById(R.id.iv);
        Glide.with(this).load(R.drawable.ic_launcher_background).apply(new RequestOptions().circleCrop()).into(viewById);
    }

}
