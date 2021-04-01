package com.example.a2;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Collection_Activity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        AnimationDrawable drawable = (AnimationDrawable) iv.getDrawable();
        drawable.start();
    }
}
