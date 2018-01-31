package com.busilinq.ui.classify.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractBaseAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.GoodsCategoryEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：商品管理分类适配
 * Create Person：Chenyx
 * Create Time：2017/11/5 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CateLeftAdapter extends AbstractBaseAdapter<GoodsCategoryEntity> {

    public int selectPostion = 0;

    public CateLeftAdapter(Context context) {
        super(context);
    }

    public void setSections(int postion) {
        if (postion == selectPostion) {
            return;
        }
        selectPostion = postion;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_classify_left, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        GoodsCategoryEntity ge = items.get(position);
        vHolder.mTitle.setText(ge.getName());

        if (selectPostion == position) {
            vHolder.item_layout.setBackgroundResource(R.color.white);
            vHolder.mView.setVisibility(View.VISIBLE);
            vHolder.mTitle.setTextColor(mContext.getResources().getColor(R.color.color_e44010));
        } else {
            vHolder.item_layout.setBackgroundDrawable(null);
            vHolder.mView.setVisibility(View.INVISIBLE);
            vHolder.mTitle.setTextColor(mContext.getResources().getColor(R.color.cate_color));
        }

        return convertView;
    }


    class ViewHolder {
        /**
         * 标题
         */
        @BindView(R.id.tv_cate_name)
        TextView mTitle;
        /**
         * 价格
         */
        @BindView(R.id.goods_item_view)
        View mView;
        /**
         * 数量
         */
        @BindView(R.id.gruop_item_layout)
        LinearLayout item_layout;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}