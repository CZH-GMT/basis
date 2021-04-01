package com.example.day8zhong;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
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

    private TabLayout tl;
    private ViewPager vp;
    private ArrayList<String> strings;
    private ArrayList<Fragment> list;

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
                Log.e("TAG", "onFailure: "+e.toString() );

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                List<Bean.DataBean> data = bean.getData();
                list = new ArrayList<>();
                strings = new ArrayList<>();
                for (int i = 0; i <data.size() ; i++) {
                    Bean.DataBean dataBean = data.get(i);
                   list.add(Home_Fragment.newInstance(dataBean.getId()+"",null));
                   strings.add(dataBean.getName());

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                            @NonNull
                            @Override
                            public Fragment getItem(int position) {
                                return list.get(position);
                            }

                            @Override
                            public int getCount() {
                                return list.size();
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
        tl = (TabLayout) findViewById(R.id.tl);
        vp = (ViewPager) findViewById(R.id.vp);




    }
}
