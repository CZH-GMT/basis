package com.example.myapplication2;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private TabLayout tl;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        tl = (TabLayout) findViewById(R.id.tl);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        setTitle("");
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        final ArrayList<Fragment> fragments = new ArrayList<>();
       fragments.add(new Home_Fragment());
       fragments.add(new Home2_Fragment());
       vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
           @NonNull
           @Override
           public Fragment getItem(int position) {
               return fragments.get(position);
           }

           @Override
           public int getCount() {
               return fragments.size();
           }


       });
        tl.setupWithViewPager(vp);
//       tl.addTab(tl.newTab().setIcon(R.drawable.sheap).setText("首页"));
//////       tl.addTab(tl.newTab().setIcon(R.drawable.sheap).setText("2"));
        tl.getTabAt(0).setText("shouye").setIcon(R.drawable.sheap);
        tl.getTabAt(1).setText("shouye").setIcon(R.drawable.sheap);

    }
}
