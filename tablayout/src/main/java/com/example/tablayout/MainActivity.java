package com.example.tablayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

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

        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
        tl.addTab(tl.newTab().setText("首页").setIcon(R.drawable.shap));
    }
}
