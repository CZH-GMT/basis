package com.example.a5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
public class OneFragment extends Fragment {

    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        activity = getActivity();
        initView(inflate);
        initData();

        return inflate;
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://gitee.com/ccyy2019/server/raw/master/workbook_test1")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                final Bean bean = new Gson().fromJson(string, Bean.class);
                final Bean.DataBean data = bean.getData();
                final List<Bean.DataBean.CatalogBean> catalog = data.getCatalog();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        myAdapter.additem(catalog);
                    }
                });


            }
        });

    }

    private void initView(View inflate) {
        recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myAdapter = new MyAdapter(activity);
        recyclerView.setAdapter(myAdapter);


    }
}
