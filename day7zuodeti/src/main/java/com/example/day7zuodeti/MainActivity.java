package com.example.day7zuodeti;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager vp;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private TabLayout tl;
    private ImageView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        vp = (ViewPager) findViewById(R.id.vp);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        tl = (TabLayout) findViewById(R.id.tl);
        setSupportActionBar(toolbar);
        setTitle("");
        View headerView = nv.getHeaderView(0);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        nv.setItemIconTintList(null);
        viewById = headerView.findViewById(R.id.iv);


        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 100);
            }
        });


//        final ArrayList<String> strings = new ArrayList<>();
//        strings.add("首页");
//        strings.add("收藏");
//        strings.add("我的");
//        strings.add("联系人");
        final ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new CollectionFragment());
        fragments.add(new BlankFragment());
        fragments.add(new BlankFragment2());
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

//            @Nullable
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return strings.get(position);
//            }
        });
        tl.setupWithViewPager(vp);

        tl.getTabAt(0).setCustomView(getview("首页", R.drawable.item_selected));
        tl.getTabAt(1).setCustomView(getview("首页", R.drawable.item_selected));
        tl.getTabAt(2).setCustomView(getview("首页", R.drawable.item_selected));
        tl.getTabAt(3).setCustomView(getview("首页", R.drawable.item_selected));


    }

    public View getview(String text, int image) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.tablayout, null);
        ImageView iv = inflate.findViewById(R.id.iv);
        TextView tv = inflate.findViewById(R.id.title);
        iv.setBackgroundResource(image);
        tv.setText(text);
        return inflate;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "搜索");
        menu.add(0, 0, 0, "分享");
        menu.getItem(0).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.getItem(1).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                View inflate = LayoutInflater.from(this).inflate(R.layout.popuwindow, null);
                final PopupWindow popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(dl, Gravity.BOTTOM, 0, 0);

                final Window window = getWindow();
                final WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha = 0.3f;
                window.setAttributes(attributes);


                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {

                        WindowManager.LayoutParams attributes = window.getAttributes();
                        attributes.alpha = 1f;
                        window.setAttributes(attributes);
                    }
                });

                inflate.findViewById(R.id.peng).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                inflate.findViewById(R.id.weibo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                break;
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri data1 = data.getData();
            viewById.setImageURI(data1);
        }
    }
}
