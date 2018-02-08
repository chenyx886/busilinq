package com.busilinq.xsm.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.busilinq.R;



/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */

@SuppressLint("AppCompatCustomView")
public class DrawableTextView extends TextView {
    int drawableWidth, drawableHeight;

    public DrawableTextView(Context context) {
        super(context);

    }

    public DrawableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        /**
         * 取得自定义属性值
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView);
        drawableWidth = ta.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, -1);
        drawableHeight = ta.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, -1);

        /**
         * 取得TextView的Drawable(左上右下四个组成的数组值)
         */
        Drawable[] drawables = getCompoundDrawables();
        Drawable textDrawable = null;
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                textDrawable = drawable;
            }
        }
        /**
         * 设置宽高
         */
        if (textDrawable != null && drawableWidth != -1 && drawableHeight != -1) {
            textDrawable.setBounds(0, 0, drawableWidth, drawableHeight);
        }
        /**
         * 设置给TextView
         */
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        /**
         * 回收ta
         */
        ta.recycle();
    }

    public DrawableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 取得自定义属性值
         */
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DrawableTextView);
        drawableWidth = ta.getDimensionPixelSize(R.styleable.DrawableTextView_drawableWidth, -1);
        drawableHeight = ta.getDimensionPixelSize(R.styleable.DrawableTextView_drawableHeight, -1);
        /**
               * 取得TextView的Drawable(左上右下四个组成的数组值)
               */
        Drawable[] drawables = getCompoundDrawables();
        Drawable textDrawable = null;
        for (Drawable drawable : drawables) {
            if (drawable != null) {
                textDrawable = drawable;
            }
        }
        /**
         * 设置宽高
         */
        if (textDrawable != null && drawableWidth != -1 && drawableHeight != -1) {
            textDrawable.setBounds(0, 0, drawableWidth, drawableHeight);
        }
        /**
         * 设置给TextView
         */
        setCompoundDrawables(drawables[0], drawables[1], drawables[2], drawables[3]);
        /**
         * 回收ta
         */
        ta.recycle();
    }

}
