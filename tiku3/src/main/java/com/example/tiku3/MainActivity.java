package com.example.tiku3;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
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

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;

    private LinearLayout ll;
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


        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                toolbar.setTitle(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Home_Fragment());
        fragments.add(new Two_Fragment());
        fragments.add(new Three_Fragment());
        fragments.add(new Four_Fragment());
        fragments.add(new Five_Fragment());


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
        tl.getTabAt(0).setText("首页").setIcon(R.drawable.sheap);
        tl.getTabAt(1).setText("知识体系").setIcon(R.drawable.sheap);
        tl.getTabAt(2).setText("公众号").setIcon(R.drawable.sheap);
        tl.getTabAt(3).setText("导航").setIcon(R.drawable.sheap);
        tl.getTabAt(4).setText("我的").setIcon(R.drawable.sheap);


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.CALL_PHONE)) {
                            call();
                        } else {
                        EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.CALL_PHONE);
                        }


                        break;
                }
                return false;
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       if (requestCode==1){
           if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.CALL_PHONE)) {
               call();
           } else {
               EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.CALL_PHONE);
           }
       }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        startActivity(intent);
    }

}
