package com.example.day5zhongfragment;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl;
    private FragmentManager fragmentManager;
    private MyFragment myFragment;
    private FrameLayout f2;
    private MyFragment myFragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);
        f2 = (FrameLayout) findViewById(R.id.f2);
        myFragment = new MyFragment();
        myFragment1 = new MyFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fl, this.myFragment)
                .commit();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.f2, this.myFragment1)
                .commit();

    }
}
