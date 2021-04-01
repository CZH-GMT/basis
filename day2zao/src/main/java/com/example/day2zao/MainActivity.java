package com.example.day2zao;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView elv;
    private ElvAdapter elvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        ArrayList<UserBean> strings = new ArrayList<>();

        ArrayList<UserBean.personBean> personBeans = new ArrayList<>();
        personBeans.add(new UserBean.personBean(R.drawable.a1, "猫"));
        personBeans.add(new UserBean.personBean(R.drawable.a1, "狗"));
        strings.add(new UserBean(R.drawable.a4, "动物", personBeans));


        ArrayList<UserBean.personBean> personBeans1 = new ArrayList<>();
        personBeans1.add(new UserBean.personBean(R.drawable.a1, "植物"));
        personBeans1.add(new UserBean.personBean(R.drawable.a1, "狗"));
        strings.add(new UserBean(R.drawable.a4, "植物", personBeans1));

        ArrayList<UserBean.personBean> personBeans2 = new ArrayList<>();
        personBeans2.add(new UserBean.personBean(R.drawable.a1, "红色"));
        personBeans2.add(new UserBean.personBean(R.drawable.a1, "紫色"));
        strings.add(new UserBean(R.drawable.a4, "颜色", personBeans2));
        elvAdapter.getitem(strings);
    }

    private void initView() {
        elv = (ExpandableListView) findViewById(R.id.elv);
        elvAdapter = new ElvAdapter(this);
        elv.setAdapter(elvAdapter);

        elv.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                elvAdapter.deleter(groupPosition,childPosition);
                return false;
            }
        });

    }
}
