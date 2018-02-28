package com.busilinq.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.busilinq.R;
import com.busilinq.data.entity.BannerEntity;
import com.chenyx.libs.picasso.PicassoLoader;

/**
 * Company：华科建邺
 * Class Describe：首页轮播图 加载
 * Create Person：Chenyx
 * Create Time：2018/2/28 下午12:53
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class NetworkImageHomeBanner implements Holder<BannerEntity> {

    private ImageView imageView;

    @Override
    public View createView(Context context) {
        //你可以通过layout文件来创建，也可以像我一样用代码创建，不一定是Image，任何控件都可以进行翻页
        imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerEntity data) {
        imageView.setImageResource(R.mipmap.default_error);
        PicassoLoader.displayImage(context, data.getImageUrl(), imageView, R.mipmap.default_error);

    }
}

