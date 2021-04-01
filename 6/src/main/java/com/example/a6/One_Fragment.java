package com.example.a6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class One_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private FragmentActivity activity;
    private MyAdapter myAdapter;

    public One_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one_, container, false);
        activity = getActivity();
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity, 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position==0){
                    return 3;
                }
               return 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);

        myAdapter = new MyAdapter(activity);
        recyclerView.setAdapter(myAdapter);

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://gank.io/api/data/%E7%A6%8F%E5%88%A9/20/16")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                final List<Bean.ResultsBean> results = bean.getResults();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(results);
                    }
                });

            }
        });



    }
}
