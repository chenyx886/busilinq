package com.busilinq.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Company：华科建邺
 * Class Describe：GridView的onMeasure()方法，计算GridView的高度
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MGridView extends GridView {

    private boolean isOnMeasure;

    public MGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MGridView(Context context) {
        super(context);
    }

    public MGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        isOnMeasure = false;
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        isOnMeasure = true;
        super.onLayout(changed, l, t, r, b);
    }

    public boolean isMeasured() {
        return isOnMeasure;
    }

}
