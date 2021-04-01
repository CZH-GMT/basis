package com.example.day8zhong;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
 * Use the {@link Home_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentActivity activity;
    private Context context;
    private RecyclerView recyclerView;
    private View inflate;
    private MyAdapter myAdapter;

    public Home_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_Fragment newInstance(String param1, String param2) {
        Home_Fragment fragment = new Home_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_home_, container, false);
        activity = getActivity();
        context = getContext();
        initView();
        initData();


        // Inflate the layout for this fragment


        return inflate;
    }

    private void initView() {
       recyclerView=inflate.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        myAdapter = new MyAdapter(context);
        recyclerView.setAdapter(myAdapter);
    }

    private static final String TAG = "Home_Fragment";
    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/project/list/1/json?cid="+mParam1)
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {



            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RcyBean bean = new Gson().fromJson(string, RcyBean.class);
                final List<RcyBean.DataBean.DatasBean> datas = bean.getData().getDatas();
                Log.d(TAG, "onResponse: "+datas.size());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.addItem(datas);
                    }
                });

            }
        });

    }
}
