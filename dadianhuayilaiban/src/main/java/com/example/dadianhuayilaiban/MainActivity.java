package com.example.dadianhuayilaiban;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import pub.devrel.easypermissions.EasyPermissions;

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
                if (EasyPermissions.hasPermissions(MainActivity.this,Manifest.permission.CALL_PHONE)){
                    call();
                }else {
                    EasyPermissions.requestPermissions(MainActivity.this,"确定",1,Manifest.permission.CALL_PHONE);
                }

            }
        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1){
        if (EasyPermissions.hasPermissions(MainActivity.this,Manifest.permission.CALL_PHONE)){
            call();
        }else {
            Toast.makeText(this, "没有给予权限", Toast.LENGTH_SHORT).show();
        }

    }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void call(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:19894641882"));
        startActivity(intent);
    }
}
