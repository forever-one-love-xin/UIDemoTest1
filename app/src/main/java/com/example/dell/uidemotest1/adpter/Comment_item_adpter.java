package com.example.dell.uidemotest1.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.uidemotest1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 评论页面的adpter
 * */
public class Comment_item_adpter extends BaseAdapter {
    private Context context = null;
    private List<Map<String, Object>> datas = new ArrayList<> ();

    public Comment_item_adpter(Context context, List<Map<String, Object>> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size ();
    }

    @Override
    public Object getItem(int position) {
        return datas.get (position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder ();
            convertView = LayoutInflater.from (context).inflate (R.layout.comment_items, null);
            viewHolder.comment_img = convertView.findViewById (R.id.comment_img);
            viewHolder.comment_name = convertView.findViewById (R.id.comment_name);
            viewHolder.comment_content = convertView.findViewById (R.id.comment_content);
            viewHolder.comment_time = convertView.findViewById (R.id.comment_time);
            viewHolder.comment_stair = convertView.findViewById (R.id.comment_stair);
            viewHolder.comment_stair_content = convertView.findViewById (R.id.comment_stair_content);
            viewHolder.comment_second_name1 = convertView.findViewById (R.id.comment_second_name1);
            viewHolder.comment_second_content1 = convertView.findViewById (R.id.comment_second_content1);
            viewHolder.comment_second_name2 = convertView.findViewById (R.id.comment_second_name2);
            viewHolder.comment_second_content2 = convertView.findViewById (R.id.comment_second_content2);
            viewHolder.comment_second_name3 = convertView.findViewById (R.id.comment_second_name3);
            viewHolder.comment_second_content3 = convertView.findViewById (R.id.comment_second_content3);
            convertView.setTag (viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag ();
        }
        Map<String, Object> map = datas.get (position);
        viewHolder.comment_img.setImageResource ((Integer) map.get ("img"));
        viewHolder.comment_name.setText ((String) map.get ("name"));
        viewHolder.comment_content.setText ((String) map.get ("content"));
        viewHolder.comment_time.setText ((String) map.get ("time"));
        viewHolder.comment_stair.setText ((String) map.get ("stair"));
        viewHolder.comment_stair_content.setText ((String) map.get ("stair_content"));
        viewHolder.comment_second_name1.setText (((String[]) map.get ("second_names"))[0]);
        viewHolder.comment_second_content1.setText (((String[]) map.get ("second_contents"))[0]);
        viewHolder.comment_second_name2.setText (((String[]) map.get ("second_names"))[1]);
        viewHolder.comment_second_content2.setText (((String[]) map.get ("second_contents"))[0]);
        viewHolder.comment_second_name3.setText (((String[]) map.get ("second_names"))[2]);
        viewHolder.comment_second_content3.setText (((String[]) map.get ("second_contents"))[0]);

        return convertView;
    }

    public final class ViewHolder {
        public TextView comment_name, comment_content, comment_time, comment_stair, comment_stair_content;
        public TextView comment_second_name1, comment_second_name2, comment_second_name3;
        public TextView comment_second_content1, comment_second_content2, comment_second_content3;
        public ImageView comment_img;
    }
}
