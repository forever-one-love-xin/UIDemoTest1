package com.example.dell.uidemotest1.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dell.uidemotest1.CommentActivity;
import com.example.dell.uidemotest1.Data;
import com.example.dell.uidemotest1.MainActivity;
import com.example.dell.uidemotest1.R;
import com.example.dell.uidemotest1.Utils.HttpUtil;
import com.example.dell.uidemotest1.adpter.WriteFragmentAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class Write_fragment extends Fragment {
    private List<Data> datas;
    private ListView listView;
    private static String url = "http://lion.snslearn.com//index.php?app=blog&mod=Ajax&act=getBlog&tag=3&token=OGQ5YWNhOTQyNWY3OWJiODVjNTQ4MjhjZTM5MTliM2Y";
    private static final String Tag = "UIDemoTest1";

    public Write_fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_write, container, false);
        listView = view.findViewById (R.id.write_item);
        initData (listView);

        return view;
    }

    private void initData(ListView listView) {
        datas = new ArrayList<Data> ();
        sendRequestWithOkHttp (url);



    }

    /*
     * 向服务器发生请求，响应的数据会回调到onResponse()方法中
     * */
    private void sendRequestWithOkHttp(final String url) {
        new Thread (new Runnable () {
            @Override
            public void run() {

                //在子线程中执行Http请求，并将最终的请求结果回调到okhttp3.Callback中
                HttpUtil.sendOkHttpRequest (url, new okhttp3.Callback () {

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //得到服务器返回的具体内容
                        String responseData = response.body ().string ();
                        //解析从服务器传过来的数据
                        datas = parseJsonWithJSONObject (responseData);
                        getActivity ().runOnUiThread (new Runnable () {
                            @Override
                            public void run() {
                                Log.i (Tag, String.valueOf (datas.size ()));
                                WriteFragmentAdapter adapter = new WriteFragmentAdapter (getContext (), datas);
                                listView.setAdapter (adapter);
                                listView.setOnItemClickListener (new AdapterView.OnItemClickListener () {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent (getActivity (),CommentActivity.class);
                                        startActivity (intent);
                                    }
                                });
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        //在这里进行异常情况处理
                        Log.i (Tag, "错误");
                    }
                });
            }
        }).start ();
    }

    private List<Data> parseJsonWithJSONObject(String JsonData) {
        List<Data> dataTest = new ArrayList<Data> ();
        if (!JsonData.isEmpty ()) {
            try {
                JSONObject jsonObject = new JSONObject (JsonData);

                JSONArray jsonArray = jsonObject.getJSONArray ("data");
                for (int i = 0; i < jsonArray.length (); i++) {
                    JSONObject dataObject = jsonArray.getJSONObject (i);
                    JSONObject user_info = dataObject.getJSONObject ("user_info");
                    //获取所需的数据
                    String write_tit = dataObject.getString ("title");
                    String write_content = dataObject.getString ("content");
                    //由于服务器中数据未空，所以设置默认的本地图片
                    int write_img = R.drawable.write_img;
                    String user_img = user_info.getString ("logo");
                    String user_name = user_info.getString ("name");
                    String send_time = dataObject.getString ("format_ctime");
                    Data data = new Data (write_tit, write_content, write_img, user_img, user_name, send_time);
                    dataTest.add (data);
                    Log.i (Tag,user_img);
                }
            } catch (JSONException e) {
                e.printStackTrace ();
            }
        }
        return dataTest;
    }



}
