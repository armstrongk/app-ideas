package com.example.armstrong.mysqlapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class HomeWebPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_web_page);
        WebView webb =(WebView)findViewById(R.id.webView1);
        WebSettings webSettings = webb.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webb.loadUrl("http://www.cit.mak.ac.ug");
        webb.setWebViewClient(new WebViewClient());

    }
}
