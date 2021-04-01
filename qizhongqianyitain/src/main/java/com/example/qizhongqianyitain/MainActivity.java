package com.example.qizhongqianyitain;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
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
        tv = (TextView) findViewById(R.id.tv);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(iv, View.ALPHA, 1, 0);
        objectAnimator.setDuration(1000).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (dao>0){
                    try {
                        Thread.sleep(500);
                        dao--;
                        mHandler.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();

    }
}
