package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
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
        final ArrayList<View> list = new ArrayList<>();
        View inflate1 = LayoutInflater.from(this).inflate(R.layout.item1, null);

        View inflate2 = LayoutInflater.from(this).inflate(R.layout.item2, null);
        View inflate3 = LayoutInflater.from(this).inflate(R.layout.item3, null);
        View inflate4 = LayoutInflater.from(this).inflate(R.layout.item4, null);
        list.add(inflate1);
        list.add(inflate2);
        list.add(inflate3);
        list.add(inflate4);
        View viewById1 = inflate4.findViewById(R.id.btn);



       viewById1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,Home_Activity.class));
           }
       });
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view==object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = list.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = list.get(position);
                container.removeView(view);
            }
        });
    }
}
