package com.example.a4;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        initView();
    }

    private void initView() {
        wb = (WebView) findViewById(R.id.wb);

        Intent intent = getIntent();
        String a = intent.getStringExtra("a");
        wb.loadUrl(a);
        wb.setWebViewClient(new WebViewClient());
    }
}
