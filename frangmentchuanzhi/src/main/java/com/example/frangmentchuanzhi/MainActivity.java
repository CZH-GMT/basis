package com.example.frangmentchuanzhi;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private FrameLayout fl1;
    private FrameLayout fl2;
    private BlankFragment blankFragment;
    private BlankFragment2 blankFragment2;

    public BlankFragment getBlankFragment() {
        return blankFragment;
    }

    public BlankFragment2 getBlankFragment2() {
        return blankFragment2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        blankFragment = new BlankFragment();
        blankFragment2 = new BlankFragment2();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl1, blankFragment)
                .add(R.id.fl2, blankFragment2)
                .commit();


    }

    private void initView() {
        fl1 = (FrameLayout) findViewById(R.id.fl1);
        fl2 = (FrameLayout) findViewById(R.id.fl2);
    }
}
