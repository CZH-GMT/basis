package com.example.day7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tl = (TabLayout) findViewById(R.id.tl);
       tl.addTab(tl.newTab().setCustomView(getView("设置",R.drawable.item_selected)));
       tl.addTab(tl.newTab().setCustomView(getView("我的",R.drawable.item_selected)));
       tl.addTab(tl.newTab().setCustomView(getView("收藏",R.drawable.item_selected)));
       tl.addTab(tl.newTab().setCustomView(getView("个人中心",R.drawable.item_selected)));



    }

    private View getView (String text,int image) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        TextView tv = inflate.findViewById(R.id.tv);

//        Glide.with(this).load(image).into(iv);
        iv.setBackgroundResource(image);
        tv.setText(text);
        return inflate;
    }
}
