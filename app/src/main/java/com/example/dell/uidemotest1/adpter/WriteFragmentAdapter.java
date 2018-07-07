package com.example.dell.uidemotest1.adpter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.uidemotest1.Data;
import com.example.dell.uidemotest1.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.List;
/*
* 第一个页面的listview的adpter
* */
public class WriteFragmentAdapter extends BaseAdapter {
    private List<Data> datas = null;
    private Context context = null;
    //带有图片的item
    private final int IMG_STYLE = 0;
    //不带图片的item
    private final int OTHER_STYLE = 1;

    public WriteFragmentAdapter(Context context, List<Data> datas) {
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

    public int getItemViewType(int position) {
        // 按位置分配布局
        if (position % 2 == 0) {
            return OTHER_STYLE;
        } else {
            return IMG_STYLE;
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *布局类型数量
     * */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder_Img viewHolder_img = null;
        ViewHolder_Other viewHolder_other = null;
        int type = getItemViewType (position);

        if (convertView == null) {
            viewHolder_img = new ViewHolder_Img ();
            viewHolder_other = new ViewHolder_Other ();
            //确定布局
            switch (type) {
                case IMG_STYLE:
                    convertView = LayoutInflater.from (context).inflate (R.layout.write_fragment_item1, null);
                    viewHolder_img.write_tit = (TextView) convertView.findViewById (R.id.write_tit);
                    viewHolder_img.write_content = (TextView) convertView.findViewById (R.id.write_content);
                    viewHolder_img.write_img = (ImageView) convertView.findViewById (R.id.write_img);
                    viewHolder_img.user_img = (ImageView) convertView.findViewById (R.id.user_img);
                    viewHolder_img.user_name = (TextView) convertView.findViewById (R.id.user_name);
                    viewHolder_img.send_time = (TextView) convertView.findViewById (R.id.send_time);
                    convertView.setTag (viewHolder_img);
                    break;
                case OTHER_STYLE:
                    convertView = LayoutInflater.from (context).inflate (R.layout.write_fragment_item2, null);
                    viewHolder_other.write_tit = (TextView) convertView.findViewById (R.id.write_tit);
                    viewHolder_other.write_content = (TextView) convertView.findViewById (R.id.write_content);
                    viewHolder_other.user_img = (ImageView) convertView.findViewById (R.id.user_img);
                    viewHolder_other.user_name = (TextView) convertView.findViewById (R.id.user_name);
                    viewHolder_other.send_time = (TextView) convertView.findViewById (R.id.send_time);
                    convertView.setTag (viewHolder_other);
                    break;
                default:
                    break;
            }

        } else {
            switch (type) {
                case IMG_STYLE:
                    viewHolder_img = (ViewHolder_Img) convertView.getTag ();
                    break;
                case OTHER_STYLE:
                    viewHolder_other = (ViewHolder_Other) convertView.getTag ();
            }
        }

        switch (type) {
            case IMG_STYLE:
                viewHolder_img.write_tit.setText (datas.get (position).write_tit);
                viewHolder_img.write_content.setText (datas.get (position).write_content);
                viewHolder_img.write_img.setImageResource (datas.get (position).write_img);
                viewHolder_img.user_img.setImageResource (R.drawable.write_img);
                ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
                ImageLoader loader=ImageLoader.getInstance();
                loader.init(config);
                loader.displayImage(datas.get (position).user_img,viewHolder_img.user_img);
                viewHolder_img.user_name.setText (datas.get (position).user_name);
                viewHolder_img.send_time.setText (datas.get (position).send_time);
                break;
            case OTHER_STYLE:
                viewHolder_other.write_tit.setText (datas.get (position).write_tit);
                viewHolder_other.write_content.setText (datas.get (position).write_content);
                viewHolder_other.user_img.setImageResource (R.drawable.write_img);
                ImageLoaderConfiguration configs = new ImageLoaderConfiguration.Builder(context).build();
                ImageLoader loaders=ImageLoader.getInstance();
                loaders.init(configs);
                loaders.displayImage(datas.get (position).user_img,viewHolder_other.user_img);
                viewHolder_other.user_name.setText (datas.get (position).user_name);
                viewHolder_other.send_time.setText (datas.get (position).send_time);
                break;
            default:
                break;
        }

        return convertView;
    }

    public final class ViewHolder_Img {
        public TextView write_tit;
        public TextView write_content;
        public ImageView write_img;
        public ImageView user_img;
        public TextView user_name;
        public TextView send_time;
    }

    public final class ViewHolder_Other {
        public TextView write_tit;
        public TextView write_content;
        public ImageView user_img;
        public TextView user_name;
        public TextView send_time;
    }
}
