package com.qfy.postrequestdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//import部分

public class MainActivity extends Activity {


    private Button m_butPost;
    private TextView m_lblPostResult;
    private Button go_to_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_butPost = (Button) findViewById(R.id.butPost);
        go_to_pager = (Button) findViewById(R.id.go_to_pager);
        m_lblPostResult = (TextView) findViewById(R.id.lblPostResult);
        m_butPost.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                butPost_OnClick(v);
            }
        });
        go_to_pager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
    }


    //提交测试
    private void butPost_OnClick(View v) {
        //请求参数键-值对
        String strRecSmsMsg = "收短信测试";
        //提交
        RecSmsToPost(strRecSmsMsg);
        openToast("提交测试完成");
    }

    //收到短信 后 提交
    private void RecSmsToPost(String strRecSmsMsg) {
        final String strNowDateTime = getNowDateTime("yyyy-MM-dd|HH:mm:ss");//当前时间
        //参数
        final Map<String, String> params = new HashMap<String, String>();
        params.put("RECSMSMSG", strRecSmsMsg);
        //params.put("name", "李四");

        //服务器请求路径
        new Thread() {
            @Override
            public void run() {
                String strUrlPath = "http://192.168.1.9:80/JJKSms/RecSms.php" + "?DateTime=" + strNowDateTime;
                final String strResult = HttpUtils.submitPostData(strUrlPath, params, "utf-8");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        m_lblPostResult.setText(strResult);
                    }
                });
            }
        }.start();

        //openToast("提交完成");
    }

    //获取当前时间
    private String getNowDateTime(String strFormat) {
        if ("".equals(strFormat)) {
            strFormat = "yyyy-MM-dd HH:mm:ss";
        }
        Date now = new Date();
        SimpleDateFormat df = new SimpleDateFormat(strFormat, Locale.CHINA);//设置日期格式
        return df.format(now); // new Date()为获取当前系统时间
    }

    //弹出消息
    private void openToast(String strMsg) {
        Toast.makeText(this, strMsg, Toast.LENGTH_LONG).show();
    }
}
