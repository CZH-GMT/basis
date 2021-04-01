package com.example.a6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

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
public class Three_Fragment extends Fragment {

    private ExpandableListView elv;
    private FragmentActivity avtivity;
    private ElvAdapter elvAdapter;

    public Three_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_three_, container, false);
        avtivity = getActivity();
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        elv = inflate.findViewById(R.id.elv);
        elvAdapter = new ElvAdapter(avtivity);
        elv.setAdapter(elvAdapter);

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/tree/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                ElvBean elvBean = new Gson().fromJson(string, ElvBean.class);
                final List<ElvBean.DataBean> data = elvBean.getData();

                avtivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        elvAdapter.addiiitem(data);
                    }
                });

            }
        });


    }
}
