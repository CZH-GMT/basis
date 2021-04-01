package com.example.tongzhi;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv = (TextView) findViewById(R.id.tv);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager systemService = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                String id = "666";                           //通知管理           系统服务
                String name = "华哥";
                if (Build.VERSION.SDK_INT >= 26) {
                    NotificationChannel notificationChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT);
                    systemService.createNotificationChannel(notificationChannel);
                }

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent activity = PendingIntent.getActivity(MainActivity.this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);


                Notification build = new NotificationCompat.Builder(MainActivity.this, id)
                        .setLargeIcon(bitmap)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("陈智华天下第一棒！！！！！！！！！")
                        .setContentIntent(activity)
                        .setAutoCancel(true)
                        .build();
                systemService.notify(100, build);


            }
        });
    }
}
