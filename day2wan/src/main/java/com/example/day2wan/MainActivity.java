package com.example.day2wan;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ViewPager viewPager;
    private NavigationView nv;
    private DrawerLayout dl;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        tl = (TabLayout) findViewById(R.id.tl);
        setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, R.string.app_name, R.string.app_name);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        tl.addTab(tl.newTab().setIcon(R.drawable.hh).setText("首页"));
        tl.addTab(tl.newTab().setIcon(R.drawable.hh).setText("收藏"));
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ShouYeFragment());
        fragments.add(new ShouCangFragment());
       // VpAdapter vpAdapter = new VpAdapter(fragments);


    }
}
