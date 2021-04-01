package com.example.servies;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private MyService getservice;
    private Button btn3;
    private Button btn4;
    private Button btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        bindService(intent, my, Service.BIND_AUTO_CREATE);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = (Button) findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                getservice.start();

//                String string = getservice.getString();
//                btn1.setText(string);
                break;
            case R.id.btn2:
                getservice.stop();
//                getservice.setString("随便");
//                break;
            case R.id.btn3:
                getservice.pause();
                break;
            case R.id.btn4:
                getservice.up();
                break;
            case R.id.btn5:
                getservice.next();
                break;
        }
    }

    //自定义类,并实现ServiceConnection接口

    public class my implements ServiceConnection {
        //当Activity与Service绑定后执行一次.可以在这个方法当中,调用IBinder中的方法获取Service对象
        // IBinder service 中必须提供一个方法,用来返回Service对象
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.Mybinder mybinder = (MyService.Mybinder) service;
            getservice = mybinder.getservice();

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    my my = new my();
}
