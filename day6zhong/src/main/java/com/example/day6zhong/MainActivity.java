package com.example.day6zhong;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);
        fl = (FrameLayout) findViewById(R.id.fl);

        BlankFragment blankFragment = new BlankFragment().newInstance("华哥1", "华哥2");
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl,blankFragment)
                .commit();
    }
}
