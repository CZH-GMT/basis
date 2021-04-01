package com.example.day6zao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FrameLayout fl;
    private FrameLayout f2;
    private Button btn1;
    private Button btn2;
    private FragmentManager supportFragmentManager;
    private com.example.day6zao.fragment fragment;
    private Fragment2 fragment2;
    int image=R.drawable.a1;

    public int getImage() {
        return image;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        fl = (FrameLayout) findViewById(R.id.fl);


        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        supportFragmentManager = getSupportFragmentManager();
        fragment = new fragment();
        fragment2 = new Fragment2();

        Bundle bundle = new Bundle();
        bundle.putInt("image",R.drawable.a1);
        fragment2.setArguments(bundle);

        supportFragmentManager.beginTransaction()
                .add(R.id.fl, fragment2)
                .add(R.id.fl,fragment)
                .hide(fragment2)
                .setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                .setMaxLifecycle(fragment2, Lifecycle.State.STARTED)
                .commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                supportFragmentManager.beginTransaction()
                        .show(fragment)
                        .hide(fragment2)
                        .setMaxLifecycle(fragment, Lifecycle.State.RESUMED)
                        .setMaxLifecycle(fragment2, Lifecycle.State.STARTED)
                        .commit();
                break;
            case R.id.btn2:
                supportFragmentManager.beginTransaction()
                        .show(fragment2)
                        .hide(fragment)
                        .setMaxLifecycle(fragment, Lifecycle.State.STARTED)
                        .setMaxLifecycle(fragment2, Lifecycle.State.RESUMED)
                        .commit();
                break;
        }
    }
}
