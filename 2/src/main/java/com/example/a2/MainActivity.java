package com.example.a2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.ChipGroup;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayout ll;
    private NavigationView nv;
    private DrawerLayout dl;
    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request build = new Request.Builder()
                .url("https://www.wanandroid.com/banner/json")
                .build();
        okHttpClient.newCall(build).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Bean bean = new Gson().fromJson(string, Bean.class);
                final List<Bean.DataBean> data = bean.getData();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myAdapter.additem(data);
                    }
                });

            }
        });


    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        ll = (LinearLayout) findViewById(R.id.ll);
        nv = (NavigationView) findViewById(R.id.nv);
        dl = (DrawerLayout) findViewById(R.id.dl);
        setTitle("标题");

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, dl, toolbar, 0, 0);
        dl.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);


        dl.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                float v = nv.getWidth() * slideOffset;
                ll.setLeft((int)v);
            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void OnItemClickListener(View v, int position) {
                View inflate = LayoutInflater.from(MainActivity.this).inflate(R.layout.window, null);
                PopupWindow popupWindow = new PopupWindow(inflate, MATCH_PARENT, ChipGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow.showAtLocation(recyclerView, Gravity.CENTER,0,0);


                Button btn = inflate.findViewById(R.id.btn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this, "hh", Toast.LENGTH_SHORT).show();
//                        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                        String id="111";
//                        String name="小天使";
//                        if (Build.VERSION.SDK_INT>=26){
//                            NotificationChannel notificationChannel = new NotificationChannel(id,name,NotificationManager.IMPORTANCE_DEFAULT);
//                            systemService.createNotificationChannel(notificationChannel);
//                        }
//                        Intent intent = new Intent(MainActivity.this,Collection_Activity.class);
//                        PendingIntent activity = PendingIntent.getActivity(MainActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//                        Notification build = new NotificationCompat.Builder(MainActivity.this, id)
//                                .setSmallIcon(R.drawable.ic_launcher_background)
//                                .setContentTitle("添加成功")
//                                .setContentText("添加成功，点击进入收藏页面。")
//                                .setContentIntent(activity)
//                                .build();
//                        systemService.notify(100,build);
                        Toast.makeText(MainActivity.this, "hhhhhhhhhhhh", Toast.LENGTH_SHORT).show();
                        NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        String id="hh";
                        String name="华哥";
                        if (Build.VERSION.SDK_INT>=26) {
                            NotificationChannel notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
                            systemService.createNotificationChannel(notificationChannel);
                        }
                            Intent intent = new Intent(MainActivity.this,Collection_Activity.class);
                            PendingIntent activity = PendingIntent.getActivity(MainActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                            Notification build = new NotificationCompat.Builder(MainActivity.this, id)
                                    .setSmallIcon(R.drawable.a5)
                                    .setContentTitle("华哥最帅")
                                    .setContentText("已收藏，点击进入收藏页面！")
                                    .setContentIntent(activity)
                                    .build();
                            systemService.notify(100,build);


                    }
                });
            }
        });



    }
}
