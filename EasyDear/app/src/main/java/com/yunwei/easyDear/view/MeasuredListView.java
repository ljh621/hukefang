package com.yunwei.easyDear.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
import android.widget.ListView;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */

public class MeasuredListView extends ListView {

    public MeasuredListView(Context context) {
        super(context);
    }

    public MeasuredListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
