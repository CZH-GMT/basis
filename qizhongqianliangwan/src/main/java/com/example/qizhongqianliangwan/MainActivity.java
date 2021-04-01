package com.example.qizhongqianliangwan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/project/tree/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                TabAdapter tabAdapter = new Gson().fromJson(string, TabAdapter.class);
                List<TabAdapter.DataBean> data = tabAdapter.getData();
                fragments = new ArrayList<>();
                strings = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    TabAdapter.DataBean dataBean = data.get(i);
                    fragments.add(Home_Fragment.newInstance(dataBean.getId() + "", null));
                    strings.add(dataBean.getName());


                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                            @NonNull
                            @Override
                            public Fragment getItem(int position) {
                                return fragments.get(position);
                            }

                            @Override
                            public int getCount() {
                                return fragments.size();
                            }

                            @Nullable
                            @Override
                            public CharSequence getPageTitle(int position) {
                                return strings.get(position);
                            }
                        });

                        tl.setupWithViewPager(vp);

                    }
                });

            }
        });


    }

    private void initView() {

        vp = (ViewPager) findViewById(R.id.vp);

        tl = (TabLayout) findViewById(R.id.tl);

    }
}
