package com.qfy.a2018108;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class WebViewActivity extends Activity {

    private WebView wv;
    private ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        int position = getIntent().getIntExtra("position", 0);
        wv = findViewById(R.id.wv);
        pb = findViewById(R.id.pb);
        initData(position);
        wv.loadUrl("https://www.baidu.com");
    }

    private void initData(int position) {
        if (position == 0) {
            Toast.makeText(this, "打开了网页", Toast.LENGTH_SHORT).show();
            Uri uri = Uri.parse("https://www.baidu.com");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (intent.resolveActivity(getPackageManager()) != null) {
                // 网址正确 跳转成功
                startActivity(intent);
            }
        } else if (position == 1) {
            //方法一
            wv.setWebViewClient(new WebViewClient());
            //方法二
//            wv.setWebViewClient(new WebViewClient() {
//                @Override
//                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                    wv.loadUrl("https://www.baidu.com");
//                    return true;
//                }
//            });
        } else if (position == 2) {
            wv.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onReceivedIcon(WebView view, Bitmap icon) {
                    super.onReceivedIcon(view, icon);
//                    setIcon(icon);
                }

                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    pb.setProgress(newProgress);
                    if (newProgress == 100) {
                        pb.setVisibility(View.GONE);
                    }
                    super.onProgressChanged(view, newProgress);
                }

                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    setTitle(title);
                }
            });
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    super.onPageFinished(view, url);
                    setTitle(String.valueOf(view.getTitle()));
                }

                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    pb.setVisibility(View.VISIBLE);
                    super.onPageStarted(view, url, favicon);
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
