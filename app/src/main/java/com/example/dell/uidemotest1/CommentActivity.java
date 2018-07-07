package com.example.dell.uidemotest1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.uidemotest1.adpter.Comment_item_adpter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentActivity extends AppCompatActivity {
    private ListView listView;
    List<Map<String, Object>> datas;
    private ScrollView scrollView;
    private TextView back;
    private ImageButton edit, del;
    private int[] img;
    private String[] name;
    private String[] content;
    private String[] date;
    private String[] stair;
    private String[] stair_contents;
    private String[][] comment_name;
    private String[][] comment_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        supportRequestWindowFeature (Window.FEATURE_NO_TITLE);
        setContentView (R.layout.activity_comment);

        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow ().getDecorView ();
            decorView.setSystemUiVisibility (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            );
            getWindow ().setStatusBarColor (Color.TRANSPARENT);
        }
        //初始化数据
        initdata ();

        //显示scrollview的最顶端，因为嵌套了Listview
        scrollView = findViewById (R.id.comment_scroll);
        scrollView.smoothScrollTo (0, 0);

        //为顶部标题栏添加事件
        back = findViewById (R.id.back);
        back.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                finish ();
            }
        });
        edit = findViewById (R.id.edit);
        edit.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //1.创建builder对象
                AlertDialog.Builder b = new AlertDialog.Builder (CommentActivity.this);
                //2.设置属性
                b.setTitle ("提示");
                b.setMessage ("是否修改此文章");
                b.setNegativeButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (CommentActivity.this, "你点击了确定修改按钮", Toast.LENGTH_SHORT).show ();
                    }
                });
                b.setPositiveButton ("取消", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (CommentActivity.this, "你点击了取消修改按钮", Toast.LENGTH_SHORT).show ();
                    }
                });
                b.create ();//创建
                b.show ();//show
            }
        });
        del = findViewById (R.id.del);
        del.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                //1.创建builder对象
                AlertDialog.Builder b = new AlertDialog.Builder (CommentActivity.this);
                //2.设置属性
                b.setTitle ("提示");
                b.setMessage ("是否删除此文章");
                b.setNegativeButton ("确定", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (CommentActivity.this, "你删除了此文章", Toast.LENGTH_SHORT).show ();
                        finish ();
                    }
                });
                b.setPositiveButton ("取消", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText (CommentActivity.this, "你取消了删除此文章", Toast.LENGTH_SHORT).show ();
                    }
                });
                b.create ();//创建
                b.show ();//show
            }
        });

        //为lsitview添加adpter
        listView = findViewById (R.id.comment_item_view);
        Comment_item_adpter adpter = new Comment_item_adpter (getBaseContext (), datas);
        listView.setAdapter (adpter);

    }

    /*
     * 初始化数据
     * */
    public void initdata() {
        img = new int[]{
                R.drawable.comment_img2, R.drawable.comment_img1, R.drawable.comment_img2
        };
        name = new String[]{
                "华为", "腾讯", "阿里"
        };
        content = new String[]{
                "这个视频很有趣，我一下就明白了，这个视频很有趣",
                "我觉得这个视频还需要改进一下，就更好了",
                "我认为这个老师很好，这个视频很有趣，我一下就明白了"
        };
        date = new String[]{
                "今天 18：33", "今天 18:55", "今天 19：05"
        };
        stair = new String[]{
                "张老师的评语", "刘老师的评语", "黄老师的评语"
        };
        stair_contents = new String[]{
                "思维方式新颖，值得鼓励，继续加油，我很期待",
                "思维方式新颖，值得鼓励，继续加油，我很期待",
                "思维方式新颖，值得鼓励，继续加油，我很期待"
        };
        comment_name = new String[][]{
                {"张三", "刘佳权", "爱德华"},
                {"张三", "刘佳权", "爱德华"},
                {"张三", "刘佳权", "爱德华"}
        };
        comment_content = new String[][]{
                {"我也是，真的讲的很有趣", "我也是，真的讲的很有趣", "我也是，真的讲的很有趣"},
                {"我也是，真的讲的很有趣", "我也是，真的讲的很有趣", "我也是，真的讲的很有趣"},
                {"我也是，真的讲的很有趣", "我也是，真的讲的很有趣", "我也是，真的讲的很有趣"}
        };

        datas = new ArrayList<> ();
        for (int i = 0; i < 3; i++) {
            Map<String, Object> map = new HashMap<> ();
            map.put ("img", img[i]);
            map.put ("name", name[i]);
            map.put ("content", content[i]);
            map.put ("time", date[i]);
            map.put ("stair", stair[i]);
            map.put ("stair_content", stair_contents[i]);
            map.put ("second_names", comment_name[i]);
            map.put ("second_contents", comment_content[i]);
            datas.add (map);
        }
    }
}
