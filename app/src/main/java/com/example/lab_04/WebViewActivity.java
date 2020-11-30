package com.example.lab_04;

import android.content.Intent;
import android.net.Uri;
import android.net.UrlQuerySanitizer;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import java.net.MalformedURLException;
import java.net.URL;

public class WebViewActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setAllowContentAccess(true);
        //得到鼠标点击后的intent
        Intent intent = getIntent();
        Uri uri = intent.getData();//获取data属性
        String urlString = null;
        try {
            urlString = new URL(uri.getScheme(),uri.getHost(),uri.getPath()).toString();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webView.loadUrl(urlString);//加载
        //手机默认浏览器打开，为了通过webView打开网页，设置
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("http:") || url.startsWith("http:")) {
                    view.loadUrl(url);
                    return false;
                }
                view.loadUrl(url);
                return true;
            }


        });

    }
}
