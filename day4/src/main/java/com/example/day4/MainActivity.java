package com.example.day4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    int index;
    private ArrayList<View> list;
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        list = new ArrayList<>();
        size = list.size();
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
                startActivity(new Intent(MainActivity.this, Home_Activity.class));

            }
        });

        VpAdapter vpAdapter = new VpAdapter();
        vpAdapter.list = list;
        vp.setAdapter(vpAdapter);
        vp.setSaveEnabled(false);
        vp.setOnTouchListener(new View.OnTouchListener() {
            private float yDown;
            private float xDown;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    xDown = event.getX();
                    yDown = event.getY();
                    Log.v("OnTouchListener", "Down");
                } else if (event.getAction() == MotionEvent.ACTION_UP) {// 松开处理
                    float xUp = event.getX();
                    //按下和松开绝对值差当大于20时滑动，否则不显示
                    if ((xUp - xDown) > 20) {
                        //添加要处理的内容
                    } else if (0 == (xDown - xUp)) {
                        int viewWidth = v.getWidth();
                        if (xDown < viewWidth / 2) {
                            //靠左点
                            int currentItem = vp.getCurrentItem();
                            if (currentItem >0 && currentItem < list.size()) {
                                currentItem--;
                            }
                            Toast.makeText(MainActivity.this, "左--" + currentItem, Toast.LENGTH_SHORT).show();
                            vp.setCurrentItem(currentItem);
                        } else {
                            //靠右点击
                            int currentItem = vp.getCurrentItem();
                            if (currentItem >-1 && currentItem < list.size()-1) {
                                currentItem++;
                            }
                            Toast.makeText(MainActivity.this, "右--" + currentItem, Toast.LENGTH_SHORT).show();
                            vp.setCurrentItem(currentItem);
                        }
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);

    }

    private int getPageIndex(ArrayList<String> list) {

        if (index < list.size()) {
            index++;
        }
        for (int i = 0; i < list.size(); i++) {
            index++;
        }
        return index;
    }

}
