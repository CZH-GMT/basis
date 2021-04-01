package com.example.day6wan;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {

    private Sqlite sqlite;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private List<Bean.DataBean> list=new ArrayList<>();
    private View inflate;

    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflate = inflater.inflate(R.layout.fragment_blank2, container, false);
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



    }

    private void initData() {
        list.clear();
        SQLiteDatabase writableDatabase = sqlite.getWritableDatabase();
        Cursor user = writableDatabase.query("user", null, null, null, null, null, null);
        while (user.moveToNext()){
            int id = user.getInt(user.getColumnIndex("id"));
            String image = user.getString(user.getColumnIndex("image"));
            String title = user.getString(user.getColumnIndex("title"));
            String desc1 = user.getString(user.getColumnIndex("desc1"));

            Bean.DataBean dataBean = new Bean.DataBean();
            dataBean.setImagePath(image);
            dataBean.setTitle(title);
            dataBean.setDesc(desc1);

            list.add(dataBean);
           myAdapter.notifyDataSetChanged();

        }

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(isVisibleToUser){
//
//            initData();
//
//            myAdapter.notifyDataSetChanged();
//        }
//    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
