package com.example.tiku3;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        Intent intent = getIntent();

        String a = intent.getStringExtra("a");
        wv.loadUrl(a);
        wv.setWebViewClient(new WebViewClient());

    }
}
