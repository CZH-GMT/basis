package com.example.day7zao;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private TabLayout tl;
    private FirstFragment firstFragment;
    private CollectionFragment collectionFragment;

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
        firstFragment = new FirstFragment();
        collectionFragment = new CollectionFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl,firstFragment)
                .add(R.id.fl,collectionFragment)
                .hide(collectionFragment)
                .setMaxLifecycle(firstFragment, Lifecycle.State.RESUMED)
                .setMaxLifecycle(collectionFragment,Lifecycle.State.STARTED)
                .commit();

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .show(firstFragment)
                                .hide(collectionFragment)
                                .setMaxLifecycle(firstFragment, Lifecycle.State.RESUMED)
                                .setMaxLifecycle(collectionFragment,Lifecycle.State.STARTED)
                                .commit();
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .hide(firstFragment)
                                .show(collectionFragment)
                                .setMaxLifecycle(collectionFragment, Lifecycle.State.RESUMED)
                                .setMaxLifecycle(firstFragment,Lifecycle.State.STARTED)
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
