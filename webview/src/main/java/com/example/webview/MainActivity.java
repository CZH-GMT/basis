package com.example.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
        wv.loadUrl("https://www.baidu.com/?tn=88093251_33_hao_pg");
        wv.setWebViewClient(new WebViewClient());
    }
}
