package com.example.duquxinxi;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Uri parse;
    private ContentResolver contentResolver;

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
                if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.READ_SMS)) {
                    contentResolver = getContentResolver();
                    parse = Uri.parse("content://sms");
                    Cursor query = contentResolver.query(parse, null, null, null, null);
                    ArrayList<String> strings = new ArrayList<>();

                    while (query.moveToNext()) {
                        String body = query.getString(query.getColumnIndex("body"));
                        strings.add(body + "\n");
                    }
                    tv.setText(strings.toString());

                }else {
                    EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.READ_SMS);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
            if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.READ_SMS)) {
                contentResolver = getContentResolver();
                parse = Uri.parse("content://sms");
                Cursor query = contentResolver.query(parse, null, null, null, null);
                ArrayList<String> strings = new ArrayList<>();

                while (query.moveToNext()) {
                    String body = query.getString(query.getColumnIndex("body"));
                    strings.add(body + "\n");
                }
                tv.setText(strings.toString());

            }else {
                EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.READ_SMS);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
