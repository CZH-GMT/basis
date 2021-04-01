package com.example.p5;

import android.os.Bundle;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
        ArrayList<UserBean> list = new ArrayList<>();
        ArrayList<UserBean.PersonBean> personBeans = new ArrayList<>();
        personBeans.add(new UserBean.PersonBean(R.drawable.a1,"张三"));
        personBeans.add(new UserBean.PersonBean(R.drawable.a4,"李四"));
     // personBeans.add(new UserBean.PersonBean(R.drawable.a3,"王五"));
//        personBeans.add(new UserBean.PersonBean(R.drawable.a5,"赵六"));
        list.add(new UserBean("好友",personBeans));

        ArrayList<UserBean.PersonBean> personBeans1 = new ArrayList<>();
        personBeans1.add(new UserBean.PersonBean(R.drawable.a5,"赵六"));
        list.add(new UserBean("黑名单",personBeans1));

        ArrayList<UserBean.PersonBean> personBeans2 = new ArrayList<>();
        personBeans2.add(new UserBean.PersonBean(R.drawable.a1,"张三"));
        personBeans2.add(new UserBean.PersonBean(R.drawable.a4,"李四"));
        list.add(new UserBean("亲人",personBeans2));

      elvAdapter.addItems(list);
    }

    private void initView() {
        elv = (ExpandableListView) findViewById(R.id.elv);
        elvAdapter = new ElvAdapter(this);
        elv.setAdapter(elvAdapter);
    }
}
