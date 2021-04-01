package com.example.a6;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Home_Activity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        tl = (TabLayout) findViewById(R.id.tl);
        setTitle("");
        setSupportActionBar(toolbar);

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new One_Fragment());
        fragments.add(new Two_Fragment());
        fragments.add(new Three_Fragment());
        fragments.add(new Four_Fragment());


        vp.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
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


        tl.getTabAt(0).setText("A页面").setIcon(R.drawable.dheap);
        tl.getTabAt(1).setText("B页面").setIcon(R.drawable.dheap);
        tl.getTabAt(2).setText("C页面").setIcon(R.drawable.dheap);
        tl.getTabAt(3).setText("D页面").setIcon(R.drawable.dheap);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float v = nv.getWidth() * slideOffset;
                ll.setLeft((int) v);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });


    }
}
