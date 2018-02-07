package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.ulits.StringParse;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yu on 2017/6/16.
 */

public class XsmHistoryOrderAdapter extends XsmBaseAdapter {

    private Context mContext;
    private List<Order> mOrders;

    public XsmHistoryOrderAdapter(Context mContext, List<Order> orders) {
        super();
        this.mContext = mContext;
        this.mOrders = orders;
    }

    public void setDetails(List<Order> details) {
        this.mOrders = details;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null == mOrders)
            return 0;
        return mOrders.size();
    }

    @Override
    public Object getItem(int i) {
        return mOrders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myxsm_history_order_item, null);
            holder = new ViewHolder(view);
            AutoUtils.autoSize(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        int date = -1;
        if (i - 1 > -1) {
            Order lastOrder = mOrders.get(i - 1);
            date = Integer.valueOf(lastOrder.getCrtDate().substring(0, 6));
        }
        updateViews(holder, mOrders.get(i), date);
        return view;
    }

    private void updateViews(ViewHolder holder, Order order, int date) {
        int mOrderData = Integer.valueOf(order.getCrtDate().substring(0, 6));
        if (date != -1 && mOrderData == date)
            holder.mDataTv.setVisibility(View.GONE);
        else
            holder.mDataTv.setVisibility(View.VISIBLE);
        holder.mCodeTv.setText(order.getCoNum());
        holder.mToTalNumberTv.setText(mContext.getResources().getString(R.string.gong) + order.getReqQtySum() + mContext.getResources().getString(R.string.tiao));
        if (order.getPmtStatus().equals("1"))
            holder.mTypeTv.setTextColor(mContext.getResources().getColor(R.color.color_009999));
        else
            holder.mTypeTv.setTextColor(mContext.getResources().getColor(R.color.color_f20c0c));
        holder.mTypeTv.setText(StringParse.getOrderPayStatus(mContext, order.getPmtStatus()));
        holder.mTotalTv.setText(mContext.getResources().getString(R.string.rmb_symbol) + StringParse.formatMoney(order.getOrdAmtSum()));
        holder.mDataTv.setText(order.getCrtDate().substring(0, 4) + "-" + order.getCrtDate().substring(4, 6));
        holder.mDayTv.setText(order.getCrtDate().substring(6, 8));
        holder.mTimeTv.setText(order.getCrtTime().substring(0, 6));

    }

    class ViewHolder {
        @BindView(R.id.xsm_history_orders_date_tv)
        TextView mDataTv;
        @BindView(R.id.xsm_history_orders_day_tv)
        TextView mDayTv;
        @BindView(R.id.xsm_history_orders_time_tv)
        TextView mTimeTv;
        @BindView(R.id.xsm_history_orders_custcode_tv)
        TextView mCodeTv;
        @BindView(R.id.xsm_history_orders_total_number_tv)
        TextView mToTalNumberTv;
        @BindView(R.id.xsm_history_orders_type_tv)
        TextView mTypeTv;
        @BindView(R.id.xsm_history_orders_total_tv)//总价
                TextView mTotalTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
