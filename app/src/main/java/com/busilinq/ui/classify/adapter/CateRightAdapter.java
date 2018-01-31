package com.busilinq.ui.classify.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.data.entity.GoodsCategoryEntity;
import com.busilinq.widget.SectionedBaseAdapter;

/**
 * Company：华科建邺
 * Class Describe：商品列表 适配器
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CateRightAdapter extends SectionedBaseAdapter {

    private Context mContext;

    private GoodsCategoryEntity mGoodsCate;


    public CateRightAdapter(Context context, GoodsCategoryEntity mGoodsCate) {
        this.mContext = context;
        this.mGoodsCate = mGoodsCate;
    }

    public void appendList(GoodsCategoryEntity mgoods) {
        if (mgoods == null) {
            return;
        }
        mGoodsCate = mgoods;
        notifyDataSetChanged();
    }


    @Override
    public Object getItem(int section, int position) {
        return mGoodsCate.getList().get(position);
    }

    @Override
    public long getItemId(int section, int position) {
        return 0;
    }

    @Override
    public int getPositionInSectionForPosition(int position) {
        return super.getPositionInSectionForPosition(position);
    }

    @Override
    public int getSectionCount() {
        if (mGoodsCate == null) {
            return 0;
        }
        return 1;
    }


    @Override
    public int getCountForSection(int section) {
        if (mGoodsCate.getList() == null || mGoodsCate.getList().size() == 0) {
            return 0;
        }
        return mGoodsCate.getList().size();
    }

    @Override
    public View getItemView(final int section, final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_classify_right, null);
            holder.mSonName = convertView.findViewById(R.id.tv_son_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final GoodsCategoryEntity categoryEntity = mGoodsCate.getList().get(position);
        holder.mSonName.setText(categoryEntity.getName());
        return convertView;

    }

    static class ViewHolder {
        TextView mSonName;
    }

    @Override
    public View getSectionHeaderView(int section, View convertView, ViewGroup parent) {
        final ViewHolderHeader holder;
        if (convertView == null) {
            holder = new ViewHolderHeader();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cate_list_header, null);
            holder.mTitle = convertView.findViewById(R.id.goods_list_header_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderHeader) convertView.getTag();
        }
        GoodsCategoryEntity GoodsCatesHeader = mGoodsCate;

        holder.mTitle.setText(GoodsCatesHeader.getName());
        return convertView;
    }

    static class ViewHolderHeader {
        TextView mTitle;
    }

}
