package com.example.day6wan;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class BlankFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Bean.DataBean> list=new ArrayList<>();
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1){
            myAdapter.notifyDataSetChanged();
            }
        }
    };
    private MyAdapter myAdapter;
    private Sqlite sqlite;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        sqlite = new Sqlite(getContext());
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        recyclerView=inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myAdapter = new MyAdapter(getContext(),list);
        recyclerView.setAdapter(myAdapter);
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onLongClickListener(View v, int position) {
                Bean.DataBean dataBean = list.get(position);

                SQLiteDatabase writableDatabase = sqlite.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put("image",dataBean.getImagePath());
                values.put("title",dataBean.getTitle());
                values.put("desc1",dataBean.getDesc());
                writableDatabase.insert("user",null,values);
                writableDatabase.close();
                sqlite.close();

            }
        });

    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                final List<Bean.DataBean> data = bean.getData();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.addAll(data);
                        mHandler.sendEmptyMessage(1);
                    }
                });

            }
        });

    }
}
