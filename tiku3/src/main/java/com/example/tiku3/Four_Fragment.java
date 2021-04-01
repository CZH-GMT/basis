package com.example.tiku3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Four_Fragment extends Fragment {

    private FragmentActivity activity;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> strings;
    private TabLayout tl;
    private ViewPager vp;

    public Four_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_four_, container, false);
        activity = getActivity();
        initView(inflate);
        initData();

        return inflate;
    }

    private void initView(View inflate) {
        tl = inflate.findViewById(R.id.tll);
        vp = inflate.findViewById(R.id.vp);

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

            private static final String TAG = "Four_Fragment";
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Beans beans = new Gson().fromJson(string, Beans.class);
                List<Beans.DataBean> data = beans.getData();
                fragments = new ArrayList<>();
                strings = new ArrayList<>();
                for (int i = 0; i < data.size(); i++) {
                    Beans.DataBean dataBean = data.get(i);
                    fragments.add(RcyFragment.newInstance(dataBean.getId() + "", null));
                    strings.add(dataBean.getName());

                }
                Log.d(TAG, "onResponse: "+fragments.size());
                Log.d(TAG, "onResponse: "+strings.size());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        vp.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
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
}
