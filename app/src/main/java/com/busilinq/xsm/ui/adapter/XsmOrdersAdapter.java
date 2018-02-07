package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.busilinq.R;
import com.busilinq.xsm.data.entity.CustOrder;
import com.busilinq.xsm.ulits.StringParse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by dingyi on 2016/11/18.
 */

public class XsmOrdersAdapter extends BaseAdapter {

    private Context mContext;
    private List<CustOrder> mOrders;

    public XsmOrdersAdapter(Context mContext, List<CustOrder> orders) {
        this.mContext = mContext;
        this.mOrders = orders;
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

    public void setOrders(List<CustOrder> orders){
        this.mOrders = orders;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.myxsm_orders_item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        updateOrder(holder,mOrders.get(i));
        return view;
    }

    private void updateOrder(ViewHolder holder, CustOrder order){
        holder.mOrderNumberTv.setText(order.getCoNum());

        String orderTime = mContext.getResources().getString(R.string.myxsm_orders_time)+ StringParse.getDate(mContext,order.getOrderDate(),".");
        holder.mOrderTimeTv.setText(orderTime);

        String qty = mContext.getResources().getString(R.string.myxsm_order_qyt)+order.getOrdQtySum()+mContext.getResources().getString(R.string.tiao);
        holder.mOrderQtyTv.setText(qty);

        //积分功能暂时没有
        holder.mOrderIntegralTv.setText("");

        String payState = mContext.getResources().getString(R.string.myxsm_orders_paystate) + StringParse.getOrderPayStatus(mContext,order.getPmtStatus());
        holder.mOrderPaystateTv.setText(payState);

        String amount = mContext.getResources().getString(R.string.myxsm_orders_amout)+ StringParse.formatMoney(order.getOrdAmtSum());
        holder.mOrderAmountTv.setText(amount);
    }

    class ViewHolder {
        @BindView(R.id.myxsm_orders_item_order_number_tv)
        TextView mOrderNumberTv;
        @BindView(R.id.myxsm_orders_item_order_time_tv)
        TextView mOrderTimeTv;
        @BindView(R.id.myxsm_orders_item_order_qty_tv)
        TextView mOrderQtyTv;
        @BindView(R.id.myxsm_orders_item_order_integral_tv)
        TextView mOrderIntegralTv;
        @BindView(R.id.myxsm_orders_item_order_paystate_tv)
        TextView mOrderPaystateTv;
        @BindView(R.id.myxsm_orders_item_order_amount_tv)
        TextView mOrderAmountTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
