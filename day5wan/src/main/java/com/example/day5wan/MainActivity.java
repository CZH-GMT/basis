package com.example.day5wan;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView hh;
    private TextView tv;
    private int dao=5;
    private Handler mHandler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
           String[] arr={"1","2","3","4","5"};
           tv.setText(arr[dao]);
           if (dao==0){
               startActivity(new Intent(MainActivity.this,Home_Activity.class));

           }
            return false;

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        hh = (TextView) findViewById(R.id.hh);
        tv = (TextView) findViewById(R.id.tv);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (dao>0){
                    try {
                        Thread.sleep(1000);
                        mHandler.sendEmptyMessage(0);
                        dao--;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }
}
