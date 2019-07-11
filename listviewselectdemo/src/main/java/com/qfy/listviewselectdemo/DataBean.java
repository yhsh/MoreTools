package com.qfy.listviewselectdemo;


public class DataBean {

    public String id;

    public String title;

    public String desc;

    public boolean isCheck;  //该属性主要标志CheckBox是否选中

    public DataBean(String id, String title, String desc) {
        this.id = id;
        this.title = title;
        this.desc = desc;
    }


}

