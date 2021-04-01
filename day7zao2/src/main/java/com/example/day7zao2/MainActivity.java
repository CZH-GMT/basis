package com.example.day7zao2;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pg;
    private TextView tv;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
private int count;
    private void initView() {
        pg = (ProgressBar) findViewById(R.id.pg);
        tv = (TextView) findViewById(R.id.tv);
        fl = (FrameLayout) findViewById(R.id.fl);


        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        tv.setText(count+"");
                     if (count==100){
                         pg.setAlpha(0);
                            tv.setAlpha(0);
                     }
                    }
                });

            }
        },20,20);
    }
}
