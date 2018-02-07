package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.ulits.Logger;
import com.busilinq.xsm.ulits.StringParse;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yu on 2017/6/7.
 */

public class XsmOrderingAdapter extends XsmOrderingBaseAdapter {

    private int mLastClickPosition;

    public int getLastClickPosition() {
        return mLastClickPosition;
    }

    public void resetLastClickPosition() {
        mLastClickPosition = -1;
    }

    private int itemBackgroundColorId = -1;

    public XsmOrderingAdapter(Context context, List<Cigarette> infos) {
        super(context, infos);
    }

    @Override
    public int getCount() {
        if (null == mCgtInfos)
            return 0;
        return mCgtInfos.size();
    }

    public void setItemBackground(int colorId) {
        itemBackgroundColorId = colorId;
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
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myxsm_ordering_new_item, null);
            holder = new ViewHolder(view);
            holder.mReduceIgv.setOnClickListener(mOnClickListener);
            holder.mIncreaseIgv.setOnClickListener(mOnClickListener);
            holder.mQtyTv.setOnClickListener(mOnClickListener);
            AutoUtils.autoSize(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        updateViews(holder, mCgtInfos.get(i), i);
        return view;
    }

    private void updateViews(ViewHolder holder, Cigarette info, int i) {
        if (itemBackgroundColorId != -1) {
            holder.mBgLly.setBackgroundColor(mContext.getResources().getColor(itemBackgroundColorId));
        }
        holder.mNameTv.setText(info.getCgtName());
        String price = mContext.getResources().getString(R.string.rmb_symbol) + info.getPrice() + mContext.getResources().getString(R.string.tiao);
        holder.mPriceTv.setText(price);
        String limit = StringParse.getBrdType(mContext, info.getBrdType()) + "    ";
        limit += mContext.getResources().getString(R.string.myxsm_ordering_limit);
        String qtyLmt = info.getQtyLmt();
        if (null == qtyLmt || qtyLmt.equals("")) {
            limit += "--";
        } else {
            limit += qtyLmt;
        }
        holder.mLimitTv.setText(limit);
        holder.mReduceIgv.setEnabled(true);
        holder.mIncreaseIgv.setEnabled(true);

        String qty = "0";
        Cart cart = mCgtCarts.get(info.getCgtCode());

        if (null == cart) {
            holder.mReduceIgv.setVisibility(View.INVISIBLE);
            holder.mQtyTv.setVisibility(View.INVISIBLE);
        } else {
            qty = cart.getOrdQty();
            if (qty.equals("0")) {
                holder.mReduceIgv.setVisibility(View.INVISIBLE);
                holder.mQtyTv.setVisibility(View.INVISIBLE);
            } else {
                holder.mReduceIgv.setVisibility(View.VISIBLE);
                holder.mQtyTv.setVisibility(View.VISIBLE);
            }
        }
        String lmt = info.getQtyLmt();

        if (null != lmt && Integer.parseInt(qty) >= Integer.parseInt(lmt)) {
            holder.mIncreaseIgv.setEnabled(false);
        }
        if (info.getIsCoMulti().equals("1")) {
            holder.mSpecialIgv.setVisibility(View.GONE);
        } else {
            holder.mSpecialIgv.setVisibility(View.VISIBLE);
        }
        if (mOrderReqSum >= mMaxReqQty)
            holder.mIncreaseIgv.setEnabled(false);


        holder.mQtyTv.setText(qty);
        holder.mReduceIgv.setTag(i);
        holder.mIncreaseIgv.setTag(i);
        holder.mQtyTv.setTag(i);
        showImageView(holder.mItemIgv, info.getCgtCode());
    }

    public void setCgtLmt(View v, String cgtCode) {
        int index = -1;
        index = (Integer) v.getTag();
        mLastClickPosition = index;
        switch (v.getId()) {
            case R.id.myxsm_ordering_item_reduce_igv:
                decrease(index);
                break;
            case R.id.myxsm_ordering_item_increase_igv:
                increase(index);
                break;
            case R.id.myxsm_ordering_item_qty_tv:
                input(v, index);
                break;
            default:
                break;
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int index = -1;
            index = (Integer) v.getTag();
            Logger.e("-------------1" + index);
            String cgtCode = mCgtInfos.get(index).getCgtCode();
            Logger.e("-------------2" + cgtCode);
            String lmt = mCgtInfos.get(index).getQtyLmt();
            Logger.e("-------------3" + lmt);
            if (null != lmt) {
                setCgtLmt(v, cgtCode);
                if (null != mDataUpdateListener)
                    mDataUpdateListener.updateView();
                return;
            }
            if (null != mDataUpdateListener)
                mDataUpdateListener.updateItem((View) v, (String) cgtCode);
        }
    };

    class ViewHolder {
        @BindView(R.id.myxsm_ordering_item_igv)
        ImageView mItemIgv;
        @BindView(R.id.myxsm_ordering_item_name_tv)
        TextView mNameTv;
        @BindView(R.id.myxsm_ordering_item_limit_tv)
        TextView mLimitTv;
        @BindView(R.id.myxsm_ordering_item_price_tv)
        TextView mPriceTv;
        @BindView(R.id.myxsm_ordering_item_reduce_igv)
        ImageView mReduceIgv;
        @BindView(R.id.myxsm_ordering_item_qty_tv)
        TextView mQtyTv;
        @BindView(R.id.myxsm_ordering_item_increase_igv)
        ImageView mIncreaseIgv;
        @BindView(R.id.ordering_cgtinfo_special_pic_igv)
        ImageView mSpecialIgv;
        @BindView(R.id.myxsm_ordering_item_bg_lly)
        LinearLayout mBgLly;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
