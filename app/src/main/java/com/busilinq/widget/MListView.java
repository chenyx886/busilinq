package com.busilinq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Company：华科建邺
 * Class Describe：MListView的onMeasure()方法，计算MListView的高度
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MListView extends ListView {

    public MListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MListView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}