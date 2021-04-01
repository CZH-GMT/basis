package com.example.tiku3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private RecyclerView recyclerView;
    private FragmentActivity activity;
    private RcyAdapter rcyAdapter;
    private Context Context;
    private android.content.Context context;
    private SmartRefreshLayout sm;
    private RcyBean.DataBean data;

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
        context = getContext();

        initView(inflate);
        initData();
        return inflate;
    }

    private int count = 1;

    private void initData() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/project/list/1/json?cid=" + mParam1)
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                RcyBean rcyBean = new Gson().fromJson(string, RcyBean.class);
                data = rcyBean.getData();
                final List<RcyBean.DataBean.DatasBean> datas = data.getDatas();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rcyAdapter.addRcyitem(datas);
                        sm.finishRefresh();
                        sm.finishLoadMore();
                    }
                });


            }
        });
        count++;


    }

    private void initView(View inflate) {
        sm = inflate.findViewById(R.id.sm);
        recyclerView = inflate.findViewById(R.id.recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Context);
        recyclerView.setLayoutManager(linearLayoutManager);
        rcyAdapter = new RcyAdapter(activity);
        recyclerView.setAdapter(rcyAdapter);
        registerForContextMenu(recyclerView);
        rcyAdapter.setOnItemclicklistener(new RcyAdapter.onItemclicklistener() {
            @Override
            public void onItemclicklistener(RcyBean.DataBean.DatasBean bean) {
                Intent intent = new Intent(activity, Main2Activity.class);
                String apkLink = bean.getLink();
                intent.putExtra("a",apkLink);
                startActivity(intent);
            }
        });

        sm.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if(data!=null) {
                    if (count <= data.getPageCount()) {
                        initData();

                    } else {
                        Toast.makeText(activity, "别划了大哥！！！！！！！！！！！！！！！！", Toast.LENGTH_SHORT).show();
                    }
                }
                sm.finishLoadMore();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                rcyAdapter.list.clear();
                count=1;
                initData();
                rcyAdapter.notifyDataSetChanged();
                sm.finishRefresh();

            }
        });

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"删除");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                if (flag) {
                    int position = rcyAdapter.getPosition();
                    rcyAdapter.list.remove(position);
                    rcyAdapter.notifyDataSetChanged();
                    break;
                }
        }
        return super.onContextItemSelected(item);
    }

    public boolean flag;


    @Override
    public void onResume() {
        flag=true;
        super.onResume();
    }

    @Override
    public void onPause() {
        flag=false;
        super.onPause();
    }
}

