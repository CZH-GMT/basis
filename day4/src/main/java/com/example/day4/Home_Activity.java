package com.example.day4;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tab;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        fl = (FrameLayout) findViewById(R.id.fl);
        setTitle("ToolBar");
        nv.setItemIconTintList(null);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        tab.addTab(tab.newTab().setIcon(R.drawable.xuanze).setText("首页"));
        tab.addTab(tab.newTab().setIcon(R.drawable.xuanze).setText("我的"));
        tab.addTab(tab.newTab().setIcon(R.drawable.xuanze).setText("联系人"));
        tab.addTab(tab.newTab().setIcon(R.drawable.xuanze).setText("收藏"));
        final ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new ShouYeFragment());
        fragments.add(new ShoutwoFragment());
        fragments.add(new ShoutwoFragment());
        fragments.add(new ShoutwoFragment());




//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl,fragments.get(0))
//                .commit();
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fl,fragments.get(position))
                        .commit();
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
