package com.qfy.a2018108;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

    private Intent intent;
    private int selectPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lv = findViewById(R.id.lv);
        intent = new Intent(MainActivity.this, WebViewActivity.class);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        selectPosition = position;
                        break;
                    case 1:
                        selectPosition = position;
                        break;
                    case 2:
                        selectPosition = position;
                        break;
                    case 3:
                        selectPosition = position;
                        break;
                    case 4:
                        selectPosition = position;
                        break;
                    case 5:
                        selectPosition = position;
                        break;
                }
                intent.putExtra("position", selectPosition);
                startActivity(intent);
            }
        });
    }
}
