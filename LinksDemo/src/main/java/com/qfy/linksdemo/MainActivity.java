package com.qfy.linksdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends Activity {

    private CheckBox cbAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cbAgreement = findViewById(R.id.cb_agreement);
        setAgreement();
    }

    private void setAgreement() {
        SpannableString msp = new SpannableString("我阅读并同意《扬宏豕慧使用协议》和《腾讯开发平台的开户协议》");
        int length = msp.length();
        //设置字体前景色
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#555555")), 0, 6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#8B1C21")), 6, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#555555")), 16, 17, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new ForegroundColorSpan(Color.parseColor("#8B1C21")), 17, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（绝对值,单位：像素）
        msp.setSpan(new TextAppearanceSpan(this, 16), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new Clickable(agreement1), 6, 16, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new Clickable(agreement2), 17, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        cbAgreement.setText(msp);
        cbAgreement.setClickable(true);
        cbAgreement.setMovementMethod(LinkMovementMethod.getInstance());
        cbAgreement.setHighlightColor(Color.TRANSPARENT);
    }

    /**
     * 创建 clickable对象
     */
    class Clickable extends ClickableSpan implements View.OnClickListener {
        private final View.OnClickListener mListener;

        public Clickable(View.OnClickListener l) {
            mListener = l;
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(v);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setUnderlineText(false);
        }
    }

    /**
     * 协议1点击的监听
     */

    View.OnClickListener agreement1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startProtocol("扬宏豕慧使用协议", "http://tieba.baidu.com/tb/eula.html");
        }
    };
    View.OnClickListener agreement2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startProtocol("腾讯开发平台的开户协议", "http://wiki.open.qq.com/wiki/%E8%85%BE%E8%AE%AF%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0%E5%BC%80%E5%8F%91%E8%80%85%E5%8D%8F%E8%AE%AE");
        }
    };


    private void startProtocol(String title, String url) {
        Intent intent = new Intent(this, AgreementActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
