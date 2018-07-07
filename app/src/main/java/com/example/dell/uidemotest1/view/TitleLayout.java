package com.example.dell.uidemotest1.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dell.uidemotest1.R;

public class TitleLayout extends RelativeLayout {
    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super (context, attrs);
        LayoutInflater.from (context).inflate (R.layout.title_layout, this);
    }
}
