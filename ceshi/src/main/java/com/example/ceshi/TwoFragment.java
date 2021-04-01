package com.example.ceshi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

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
public class TwoFragment extends Fragment {

    private FragmentActivity activity;
    private ExpandableListView elv;
    private ElvAdapter elvAdapter;

    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_two, container, false);
        activity = getActivity();
        initView(inflate);
        initData();
        return inflate;
    }

    private void initView(View inflate) {
        elv=inflate.findViewById(R.id.elv);
        elvAdapter = new ElvAdapter(activity);
        elv.setAdapter(elvAdapter);
        elv.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                List<ElvBean.DataBean> list = elvAdapter.getList();


                Toast.makeText(activity, list.get(groupPosition).getName(), Toast.LENGTH_SHORT).show();

                return false;
            }
        });
        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                List<ElvBean.DataBean> list = elvAdapter.getList();
                Toast.makeText(activity, list.get(groupPosition).getChildren().get(childPosition).getName(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

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
                final ElvBean elvBean = new Gson().fromJson(string, ElvBean.class);
                final List<ElvBean.DataBean> data = elvBean.getData();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        elvAdapter.additem(data);
                    }
                });

            }
        });



    }

}
