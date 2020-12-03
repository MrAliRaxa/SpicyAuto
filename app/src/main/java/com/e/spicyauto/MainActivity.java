package com.e.spicyauto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.e.spicyauto.Constants.WebUrls;
import com.e.spicyauto.databinding.ActivityMainBinding;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mDataBinding;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        mDataBinding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);
        AlertDialog loading = new SpotsDialog.Builder().setContext(MainActivity.this).build();

        mDataBinding.webView.loadUrl(WebUrls.BASE_URL);
        mDataBinding.webView.getSettings().setJavaScriptEnabled(true);
        mDataBinding.webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loading.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.dismiss();
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                loading.dismiss();
            }


            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

        });

    }

    @Override
    public void onBackPressed() {
    if(mDataBinding.webView.canGoBack()){
        mDataBinding.webView.goBack();
    }else{
        super.onBackPressed();
    }

    }
}

