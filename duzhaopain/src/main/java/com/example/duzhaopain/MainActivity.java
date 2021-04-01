package com.example.duzhaopain;

import android.Manifest;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {


        if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ContentResolver contentResolver = getContentResolver();
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor query = contentResolver.query(uri, null, null, null, null);
            ArrayList<String> strings = new ArrayList<>();

            while (query.moveToNext()) {
                String string = query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATA));
                strings.add(string);

            }
            myAdapter.additem(strings);

        }else {
            EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(this);
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
            if (EasyPermissions.hasPermissions(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ContentResolver contentResolver = getContentResolver();
                Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Cursor query = contentResolver.query(uri, null, null, null, null);
                ArrayList<String> strings = new ArrayList<>();

                while (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex(MediaStore.Audio.Media.DATA));
                    strings.add(string);

                }
                myAdapter.additem(strings);

            }else {
                EasyPermissions.requestPermissions(MainActivity.this,"",1,Manifest.permission.READ_EXTERNAL_STORAGE);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
