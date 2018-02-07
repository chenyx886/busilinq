package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.ulits.PreciseCompute;
import com.busilinq.xsm.widget.InputAlertDialog;
import com.busilinq.xsm.widget.interf.OnAlertDialogListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XsmOrderingBaseAdapter extends XsmBaseAdapter {

    private InputAlertDialog mDialog;

    protected Context mContext;
    protected List<Cigarette> mCgtInfos;
    protected HashMap<String, Cart> mCgtCarts;

    protected int mOrderReqSum;
    protected int mMaxReqQty;
    private double mOrderAmt;

    protected DataUpdateListener mDataUpdateListener;

    public XsmOrderingBaseAdapter(Context context, List<Cigarette> infos) {
        this.mContext = context;
        this.mCgtInfos = infos;
    }

    public void setDataUpdateListener(DataUpdateListener listener) {
        this.mDataUpdateListener = listener;
    }

    public void setOrderReqSum(int reqSum) {
        this.mOrderReqSum = reqSum;
    }

    public int getOrderReqSum() {
        return mOrderReqSum;
    }

    public void setOrderAmt(double amout) {
        this.mOrderAmt = amout;
    }

    public double getOrderAmt() {
        return this.mOrderAmt;
    }

    public void setMaxReqQty(int max) {
        this.mMaxReqQty = max;
    }

    public int getMaxReqQty() {
        return this.mMaxReqQty;
    }

    public void setCgtInfos(List<Cigarette> list) {
        this.mCgtInfos = list;
    }

    public List<Cigarette> getCgtInfos() {
        return mCgtInfos;
    }

    public void setCgtCarts(HashMap<String, Cart> carts) {
        if (null == carts)
            this.mCgtCarts = new HashMap<String, Cart>();
        else
            this.mCgtCarts = carts;
    }

    public HashMap<String, Cart> getCgtCarts() {
        return mCgtCarts;
    }

    protected void decrease(int index) {
        int iReqQty = 0;
        iReqQty = getReqQty(index);
        if (0 == iReqQty)
            iReqQty = 0;
        else
            iReqQty -= 1;
        mOrderAmt = PreciseCompute.sub(mOrderAmt, getAmount(index));
        cart(index, iReqQty);
        mOrderAmt = PreciseCompute.add(mOrderAmt, getAmount(index));
        updateViews();
    }

    protected void increase(int index) {
        int iReqQty = 0;
        iReqQty = getReqQty(index);
        iReqQty += 1;
        mOrderAmt = PreciseCompute.sub(mOrderAmt, getAmount(index));
        cart(index, iReqQty);
        mOrderAmt = PreciseCompute.add(mOrderAmt, getAmount(index));
        updateViews();
    }

    protected void input(View v, int index) {
        String s = ((TextView) v).getText().toString();
        if (null == mDialog) {
            mDialog = new InputAlertDialog(mContext);
            mDialog.setTitle("请输入需求量");
            mDialog.setDialogListener(mOnAlertDialogListener);
        }
        mDialog.setQty(s);
        mDialog.setIndex(index);
        mDialog.show();
    }

    protected double getAmount(int index) {
        int iReq = 0;
        double price = 0;
        double amt = 0;
        Cigarette info = mCgtInfos.get(index);
        Cart cart = mCgtCarts.get(info.getCgtCode());
        if (null == cart)
            return amt;
        price = Double.parseDouble(info.getPrice());
        iReq = Integer.parseInt(cart.getReqQty());
        amt = PreciseCompute.mul(price, iReq);
        return amt;
    }

    protected int getReqQty(int index) {
        int reqQty = 0;
        String cgtCode = null;
        Cart cart = null;
        cgtCode = mCgtInfos.get(index).getCgtCode();
        cart = mCgtCarts.get(cgtCode);
        if (null == cart) {
            reqQty = 0;
        } else {
            reqQty = Integer.parseInt(cart.getReqQty());
        }
        return reqQty;
    }

    @Override
    public int getCount() {
        if (null == mCgtInfos)
            return 0;
        return mCgtInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return mCgtInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    private int getRestSum() {
        int reqSum = 0;
        int restSum = 0;
        Iterator<Map.Entry<String, Cart>> it = mCgtCarts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cart> entry = it.next();
            Cart cart = entry.getValue();
            reqSum += Integer.parseInt(cart.getReqQty());
        }
        restSum = mMaxReqQty - reqSum;

        return restSum;
    }

    private void cart(int index, int reqQty) {
        String cgtCode = null;
        Cart cart = null;
        cgtCode = mCgtInfos.get(index).getCgtCode();
        cart = mCgtCarts.get(cgtCode);

        String lmt = mCgtInfos.get(index).getQtyLmt();
        if (null != lmt) {
            int iLmt = Integer.parseInt(lmt);
            if (reqQty > Integer.parseInt(lmt))
                reqQty = iLmt;
        }

        if (0 == reqQty) {
            mCgtCarts.remove(cgtCode);
        } else {
            if (null == cart) {
                cart = new Cart();
                cart.setCgtCode(cgtCode);
                cart.setCgtName(mCgtInfos.get(index).getCgtName());
            }
            mCgtCarts.remove(cgtCode);
            int iRestSum = getRestSum();
            if (iRestSum < reqQty)
                reqQty = iRestSum;
            cart.setReqQty(Integer.toString(reqQty));
            cart.setOrdQty(Integer.toString(reqQty));
            mCgtCarts.put(cgtCode, cart);
        }
        notifyDataSetChanged();
    }

    private void updateViews() {
        if (null == mDataUpdateListener)
            return;
        int restSum = getRestSum();
        int reqSum = mMaxReqQty - restSum;
        double amount = mOrderAmt;
        mOrderReqSum = reqSum;
        mDataUpdateListener.update(String.format("%.2f", amount), Integer.toString(restSum), Integer.toString(reqSum));
    }

    public interface DataUpdateListener {
        void updateItem(Object... params);

        void update(Object... params);

        void updateView();
    }

    private OnAlertDialogListener mOnAlertDialogListener = new OnAlertDialogListener() {
        @Override
        public void onSure(int index, String content) {
            super.onSure(index, content);
            mOrderAmt = PreciseCompute.sub(mOrderAmt, getAmount(index));
            cart(index, Integer.parseInt(content));
            mOrderAmt = PreciseCompute.add(mOrderAmt, getAmount(index));

            updateViews();
            mDialog.dismiss();
        }

        @Override
        public void onCancel() {
            super.onCancel();
            mDialog.dismiss();
        }
    };
}
