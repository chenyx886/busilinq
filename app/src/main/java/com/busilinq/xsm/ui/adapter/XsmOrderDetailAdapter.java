package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.ulits.Logger;
import com.busilinq.xsm.ulits.StringParse;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yu on 2017/6/16.
 */

public class XsmOrderDetailAdapter extends XsmBaseAdapter {

    private Context mContext;
    private List<OrderDetail> mDetails;

    public XsmOrderDetailAdapter(Context mContext, List<OrderDetail> mDetails) {
        super();
        this.mContext = mContext;
        this.mDetails = mDetails;
    }

    public void setDetails(List<OrderDetail> details) {
        this.mDetails = details;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null == mDetails)
            return 0;
        return mDetails.size();
    }

    public int getNumber() {
        int number = 0;
        for (OrderDetail detail : mDetails)
            number += Integer.valueOf(detail.getOrdQty());
        return number;
    }

    public double getTotal() {
        double total = 0;
        for (OrderDetail detail : mDetails) {
            int number = Integer.valueOf(detail.getOrdQty());
            double price = Double.valueOf(detail.getPrice());
            total += number * price;
        }
        return total;
    }

    @Override
    public Object getItem(int i) {
        return mDetails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myxsm_order_detail_item, null);
            holder = new ViewHolder(view);
            AutoUtils.autoSize(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        updateViews(holder, mDetails.get(i));
        return view;
    }

    private void updateViews(ViewHolder holder, OrderDetail detail) {
        Logger.e(detail.toString());
        holder.mNameTv.setText(detail.getCgtName());
        double price = Double.valueOf(detail.getPrice());
        holder.mPriceTv.setText("单价：" + price + mContext.getResources().getString(R.string.tiao));
        holder.mQtyTv.setText("数量：" + detail.getOrdQty());
        double total = price * Integer.valueOf(detail.getOrdQty());
        holder.mTotalTv.setText(mContext.getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(total));
        showImageView(holder.mItemIgv, detail.getCgtCode());
    }

    class ViewHolder {
        @BindView(R.id.ordering_cgtinfo_special_pic_igv)
        ImageView mReduceIgv;
        @BindView(R.id.myxsm_order_detail_item_igv)
        ImageView mItemIgv;
        @BindView(R.id.myxsm_order_detail_item_name_tv)
        TextView mNameTv;
        @BindView(R.id.myxsm_order_detail_item_price_tv)
        TextView mPriceTv;
        @BindView(R.id.myxsm_order_detail_item_number_tv)//数量
                TextView mQtyTv;
        @BindView(R.id.myxsm_order_detail_item_total_tv)//总价
                TextView mTotalTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
