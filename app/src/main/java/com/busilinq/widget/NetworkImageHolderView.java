package com.busilinq.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.busilinq.R;
import com.busilinq.data.entity.GoodsImgEntity;
import com.chenyx.libs.glide.GlideShowImageUtils;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/2/27 下午3:15
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class NetworkImageHolderView implements Holder<GoodsImgEntity> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, GoodsImgEntity data) {
        imageView.setImageResource(R.mipmap.default_error);
        GlideShowImageUtils.displayNetImage(context, data.getImage(), imageView, R.mipmap.default_error);
    }
}

