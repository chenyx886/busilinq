package com.busilinq.xsm.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.busilinq.MApplication;
import com.busilinq.R;
import com.chenyx.libs.glide.GlideShowImageUtils;

/**
 * Created by dingyi on 2016/11/19.
 */

public class XsmBaseAdapter extends BaseAdapter {

    private String mUrlBegin = "http://gz.xinshangmeng.com/xsm6/resource/ec/cgtpic/";
    private String mUrlEnd = "_middle_face.png";

    public XsmBaseAdapter() {
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    protected void showImageView(ImageView igv, String barcode) {
        String code = barcode;
        if (code.substring(0, 1).equals("F")) {
            code = code.substring(1);
        }
        String url = mUrlBegin + code + mUrlEnd;
        GlideShowImageUtils.displayNetImage(MApplication.getAppContext(), url, igv, R.mipmap.cgt_no_pic);

    }

}
