package com.example.day1wan;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.util.LogTime;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ExpandableListView elv;
    private String arr="";
   // private ArrayList<UserBean.DataDTO> list=new ArrayList<>();
//    private Handler mHandler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what==1){
//                elvAdapter.notifyDataSetChanged();
//            }
//        }
//    };
    private ElvAdapter elvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("https://www.wanandroid.com/tree/json");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    if (urlConnection.getResponseCode()==200){
                        InputStream inputStream = urlConnection.getInputStream();
                        byte[] bytes = new byte[1024];
                        int len=0;
                        while ((len=inputStream.read(bytes))!=-1){
                            arr+=new String(bytes,0,len);
                        }
                        UserBean userBean = new Gson().fromJson(arr, UserBean.class);
                        List<UserBean.DataDTO> data = userBean.getData();

                       // list.addAll(data);
                        elvAdapter.getitem(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private static final String TAG = "MainActivity";
    private void initView() {
        elv = (ExpandableListView) findViewById(R.id.elv);

        elvAdapter = new ElvAdapter(this);
        elv.setAdapter(elvAdapter);


    }
}
