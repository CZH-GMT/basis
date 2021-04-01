package com.example.tiku3;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class WebView_Activity extends AppCompatActivity {

    private WebView wb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_);
        initView();
    }

    private void initView() {
        wb = (WebView) findViewById(R.id.wb);
        wb.loadUrl("https://www.baidu.com/?tn=40020637_7_oem_dg");
        wb.setWebViewClient(new WebViewClient());
    }
}
