package com.example.day4;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        ArrayList<View> list = new ArrayList<>();
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_one, null);
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.item_two, null);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.item_three, null);
        list.add(inflate);
        list.add(inflate1);
        list.add(inflate2);
        View viewById = inflate2.findViewById(R.id.btn);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Home_Activity.class));

            }
        });

        VpAdapter vpAdapter = new VpAdapter();
        vpAdapter.list = list;
        vp.setAdapter(vpAdapter);
    }
}
