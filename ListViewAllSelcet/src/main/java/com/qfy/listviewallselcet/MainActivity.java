package com.qfy.listviewallselcet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ListView listView;
    private Button all_sel;
    private Button all_unsel;
    private ListitemAdapter adapter;
    private List<DataHolder> datalist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.list);
        all_sel = (Button) this.findViewById(R.id.all_sel);
        all_unsel = (Button) this.findViewById(R.id.all_unsel);
        all_sel.setOnClickListener(this);
        all_unsel.setOnClickListener(this);

        //初始化数据
        datalist = new ArrayList<DataHolder>();
        for (int i = 0; i < 10; i++) {
            datalist.add(new DataHolder("今日头条:" + i, "内容:" + i, false));
        }

        //创建数据适配器并且绑定数据
        adapter = new ListitemAdapter(MainActivity.this, datalist);
        listView.setAdapter(adapter);

        //设置列表点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //记录选中item
                boolean checked = datalist.get(position).checked;
                if (!checked) {
                    datalist.get(position).checked = true;
                } else {
                    datalist.get(position).checked = false;
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //设置全部选中
            case R.id.all_sel:
                for (int i = 0; i < datalist.size(); i++) {
                    datalist.get(i).checked = true;
                }
                adapter.notifyDataSetChanged();
                break;
            //取消全部选中
            case R.id.all_unsel:
                for (int i = 0; i < datalist.size(); i++) {
                    datalist.get(i).checked = false;
                }
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
