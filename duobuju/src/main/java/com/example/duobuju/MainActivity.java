package com.example.duobuju;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class    MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private ArrayList<Integer> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(R.drawable.c1);
        list.add(R.drawable.c2);
        list.add(R.drawable.c3);
        list.add(R.drawable.c4);
        list.add(R.drawable.c5);
//        list.add(R.drawable.b6);
//        list.add(R.drawable.b7);
//        list.add(R.drawable.b8);
//        list.add(R.drawable.b9);
//        list.add(R.drawable.b1);
        myAdapter.additem(list);


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);


    }
}
