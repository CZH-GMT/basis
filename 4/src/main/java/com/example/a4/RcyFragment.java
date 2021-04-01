package com.example.a4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RcyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RcyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerVew;
    private FragmentActivity activity;
    private MyAdapter myAdapter;
    private SmartRefreshLayout sm;
    private Beans.DataBean data;

    public RcyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RcyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RcyFragment newInstance(String param1, String param2) {
        RcyFragment fragment = new RcyFragment();
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
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_rcy, container, false);
        activity = getActivity();
        initView(inflate);
        initData();
        return inflate;
    }

    private int count = 1;

    private void initView(final View inflate) {
        recyclerVew = inflate.findViewById(R.id.recyclerVew);
        sm = inflate.findViewById(R.id.sm);
        recyclerVew.setLayoutManager(new LinearLayoutManager(activity));
        myAdapter = new MyAdapter(activity);
        recyclerVew.setAdapter(myAdapter);
        myAdapter.setOnitemclicklistener(new MyAdapter.onitemclicklistener() {
            @Override
            public void onitemclicklistener(Beans.DataBean.DatasBean bean) {
                Intent intent = new Intent(activity, WebViewActivity.class);
                String link = bean.getLink();
                intent.putExtra("a",link);
                startActivity(intent);

            }
        });

        sm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (count<=data.getPageCount()){
                    initData();
                }else {
                    Toast.makeText(activity, "别划了", Toast.LENGTH_SHORT).show();
                }
                sm.finishLoadMore(3000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                myAdapter.list.clear();
                count=1;
                initData();
                sm.finishRefresh(3000);

            }
        });
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/project/list/"+count+"/json?cid=" + mParam1)
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Beans beans = new Gson().fromJson(string, Beans.class);
                data = beans.getData();
                final List<Beans.DataBean.DatasBean> datas = data.getDatas();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(datas);
                        sm.finishRefresh();
                        sm.finishLoadMore();
                    }
                });

            }
        });

        count++;
    }
}
