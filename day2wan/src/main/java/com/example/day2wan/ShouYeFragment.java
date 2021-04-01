package com.example.day2wan;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ShouYeFragment extends Fragment {
    private ExpandableListView elv;
    private ElvAdapter elvAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.shouye, null);
        initData();
        initView(inflate);
        return inflate;


    }

    private void initView(View inflate) {
        elv = inflate.findViewById(R.id.elv);
        elvAdapter = new ElvAdapter(getContext());
        elv.setAdapter(elvAdapter);

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request builder = new Request.Builder()
                .url("http://www.wanandroid.com/tree/json")
                .build();
        okHttpClient.newCall(builder).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            private static final String TAG = "ShouYeFragment";
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Log.d(TAG, "onResponse: "+string);
                UserBean userBean = new Gson().fromJson(string, UserBean.class);
                final List<UserBean.DataDTO> data = userBean.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        elvAdapter.getitem((ArrayList<UserBean.DataDTO>) data);
                    }
                });

            }
        });

    }
}
