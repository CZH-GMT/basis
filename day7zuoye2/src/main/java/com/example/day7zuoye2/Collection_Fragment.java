package com.example.day7zuoye2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
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
public class Collection_Fragment extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;

    public Collection_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_collection_, container, false);
        initView(inflate);
        initData();
        return inflate;


    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("http://www.wanandroid.com/project/tree/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
           }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RcyBean bean = new Gson().fromJson(string, RcyBean.class);
                final List<RcyBean.DataBean> data = bean.getData();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.additem(data);
                        adapter.notifyDataSetChanged();

                    }
                });
            }
        });

    }

    private void initView(View inflate) {
        recyclerView=inflate.findViewById(R.id.recyclerView);
        adapter = new Adapter(getContext());
        recyclerView.setAdapter(adapter);



    }
}
