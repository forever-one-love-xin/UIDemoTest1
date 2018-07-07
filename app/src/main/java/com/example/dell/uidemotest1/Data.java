package com.example.dell.uidemotest1;

import android.widget.ImageView;
import android.widget.TextView;
/*
* 为第一个页面的数据写一个数据类
* */
public class Data {
    public String write_tit;
    public String write_content;
    public int write_img;
    public String user_img;
    public String user_name;
    public String send_time;

    public Data(String write_tit, String write_content, int write_img, String user_img, String user_name, String send_time) {
        this.write_tit = write_tit;
        this.write_content = write_content;
        this.write_img = write_img;
        this.user_img = user_img;
        this.user_name = user_name;
        this.send_time = send_time;
    }
}
