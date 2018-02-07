package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.Cigarette;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dingyi on 2016/11/22.
 * update by yu on 2017年6月8日
 */

public class XsmSearchAdapter extends XsmBaseAdapter {

    private Context mContext;
    private List<Cigarette> mCgtInfos;

    public XsmSearchAdapter(Context context, List<Cigarette> infos) {
        this.mContext = context;
        this.mCgtInfos = infos;
    }

    public void setCgtInfos(List<Cigarette> infos) {
        this.mCgtInfos = infos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null == mCgtInfos)
            return 0;
        return mCgtInfos.size();
    }

    @Override
    public Object getItem(int i) {
        return mCgtInfos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myxsm_ordering_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
            AutoUtils.autoSize(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        updateViews(holder, mCgtInfos.get(i));
        return view;
    }

    private void updateViews(ViewHolder holder, Cigarette info) {

        holder.mNameTv.setText(info.getCgtName());
    }

    class ViewHolder {
        @BindView(R.id.myxsm_seach_item_name_tv)
        TextView mNameTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
