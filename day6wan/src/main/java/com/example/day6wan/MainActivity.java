package com.example.day6wan;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        tl = (TabLayout) findViewById(R.id.tl);


        tl.addTab(tl.newTab().setText("首页"));
        tl.addTab(tl.newTab().setText("收藏"));

        final BlankFragment blankFragment = new BlankFragment();
        final BlankFragment2 blankFragment2 = new BlankFragment2();



        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl,blankFragment)
                .add(R.id.fl,blankFragment2)
                .hide(blankFragment2)


                .commit();

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(blankFragment)
                                .hide(blankFragment2)
                                .setMaxLifecycle(blankFragment, Lifecycle.State.RESUMED)
                                .setMaxLifecycle(blankFragment2, Lifecycle.State.STARTED)
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(blankFragment2)
                                .hide(blankFragment)
                                .setMaxLifecycle(blankFragment, Lifecycle.State.STARTED)
                                .setMaxLifecycle(blankFragment2, Lifecycle.State.RESUMED)
                                .commit();
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
