package com.busilinq.xsm.presenter;

import android.app.Activity;
import android.view.View;


import com.busilinq.R;
import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.data.entity.Limit;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.data.entity.OrderDetail;
import com.busilinq.xsm.data.usercenter.HttpEntity;
import com.busilinq.xsm.ui.adapter.XsmOrderingAdapter;
import com.busilinq.xsm.ui.adapter.XsmOrderingBaseAdapter;
import com.busilinq.xsm.ulits.PreciseCompute;
import com.busilinq.xsm.viewinterface.IXsmOrderingView;
import com.busilinq.xsm.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 策略：1、获取客户限量；2、获取烟品信息；3、获取购物车；4、获取当前订单；5、获取烟品限量；6、更新列表
 * Created by yu on 2017/6/7.
 */

public class XsmOrderingPresenter extends XsmBasePresenter<IXsmOrderingView> implements Comparator<Cigarette> {

    private View mCurItemView;
    private Merchant mMerchant;
    private XsmOrderingAdapter mYuAdapter;
    private XsmOrderingAdapter mYuCartAdapter;
    private Limit mLimit;
    //购物车
    private HashMap<String, Cart> mCarts = new HashMap<>();
    //总烟品map
    private HashMap<String, Cigarette> mCgtMap = new HashMap<>();
    private HashMap<String, Cigarette> mFindList = new HashMap<>();
    //所有烟品
    private List<Cigarette> mCigarettes = new ArrayList<>();
    private List<Cigarette> mCartList = new ArrayList<>();//购物车烟品列表集合
    //当前订单
    private Order mCurrentOrder;
    private Cigarette mCurFindCgt;
    //总价格
    private double mOrderAmt = 0;
    //总数量
    private int mOrderReqSum = 0;

    @Override
    public void attachView(IXsmOrderingView view) {
        super.attachView(view);
        mMerchant = mXsmDbApi.getMerchant();
    }


    public void start() {
        mCurrentOrder = (Order) ((Activity) mContext).getIntent().getSerializableExtra(Order.class.getSimpleName());
        getLimit();
    }

    @Override
    public int compare(Cigarette lhs, Cigarette rhs) {
        int result = 0;
        double cmp = PreciseCompute.sub(Double.parseDouble(lhs.getPrice()), Double.parseDouble(rhs.getPrice()));
        if (cmp > 0) {
            result = 1;
        } else if (cmp < 0) {
            result = -1;
        }
        return result;
    }

    public void setFindedCgtInfo(Cigarette cigarette) {
        if (null != mFindList.get(cigarette.getCgtCode()))
            mFindList.remove(cigarette.getCgtCode());
        mCurFindCgt = cigarette;
        mFindList.put(cigarette.getCgtCode(), cigarette);
    }

    public void reset() {
        mOrderAmt = 0;
        mOrderReqSum = 0;
        mCarts.clear();
        mCurFindCgt = null;
        mFindList.clear();
        mCartList.clear();//清楚购物车烟品
        if (null != mCgtMap && 0 != mCgtMap.size()) {
            initListView();
        }
        mXsmDbApi.clear(Cart.class.getSimpleName());
    }

    public boolean isHaveCigarettes() {
        if (null != mCgtMap && mCgtMap.size() != 0) {

            return true;
        } else {
            mBaseView.toast("暂无烟品数据，请稍后在试");
            return false;
        }
    }

    //得到具体烟品限量
    private void getCgtLmt(View v, final String cgtCode, boolean isCartList) {
        final Cigarette cigarette = mCgtMap.get(cgtCode);
        String lmt = null;
        if (null != cigarette) {
            lmt = cigarette.getQtyLmt();
            mCgtMap.put(cgtCode, cigarette);
        }
        if (null != lmt) {
            if (!isCartList)
                mYuAdapter.setCgtLmt(v, cgtCode);
            else
                mYuCartAdapter.setCgtLmt(v, cgtCode);
            return;
        }
        mCurItemView = v;
        mBaseView.showProgressDialog("");
        Subscription subscription = mXsmApi.getCgtLmts(mMerchant.getCustCode(), mMerchant.getOrderDate(), cigarette.getCgtCode())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Cigarette>>>() {
                    @Override
                    public void call(HttpEntity<List<Cigarette>> httpEntity) {
                        mBaseView.closeProgressDialog();
                        if (httpEntity.isSuccess()) {
                            List<Cigarette> cigarettes = httpEntity.getData();
                            if (null != cigarettes && cigarettes.size() > 0) {
                                updateCgtLmt(cgtCode, cigarettes.get(0).getQtyLmt());
                            }
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction("04007"));
        mSubscriptions.add(subscription);
    }

    //获得所有烟品信息
    private void getCgts() {
        mCgtMap = mXsmDbApi.getCigarettes();
        if (null != mCgtMap && mCgtMap.size() != 0) {
            getCarts();
            return;
        }
        Subscription subscription = mXsmApi.getCigarettes(mMerchant.getCustCode())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Cigarette>>>() {
                    @Override
                    public void call(HttpEntity<List<Cigarette>> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            List<Cigarette> cigarettes = httpEntity.getData();
                            if (null != cigarettes && cigarettes.size() > 0) {
                                mCigarettes = cigarettes;
                                mCgtMap = getCgtMap(cigarettes);
                                mXsmDbApi.saveCigarettes(mCgtMap);
                                getCarts();
                            } else {
                                mBaseView.toast("暂无烟品数据");
                                mBaseView.showLoadingError(EmptyLayout.NODATA_ENABLE_CLICK);
                            }
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                        }
                    }
                }, getThrowableAction("04006"));
        mSubscriptions.add(subscription);
    }

    private void updateCurrentOrderList() {
        if (null == mCurrentOrder) {
            mCarts.clear();
            mOrderAmt = 0;
        } else {
            createCgtCarts(mCurrentOrder.getDetails());
        }
        updateViews();
    }

    //获得人员限量
    private void getLimit() {
        mBaseView.showLoading(View.VISIBLE);
        mLimit = mXsmDbApi.getLimit();
        if (null != mLimit) {
            getCgts();
            return;
        }
        Subscription subscription = mXsmApi.getLimit(mMerchant.getCustCode())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<Limit>>() {
                    @Override
                    public void call(HttpEntity<Limit> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            mLimit = httpEntity.getData();
                            mXsmDbApi.saveLimit(mLimit);
                            getCgts();
                        } else {
                            mBaseView.toast(httpEntity.getMsg());
                            mBaseView.showLoadingError(EmptyLayout.NODATA_ENABLE_CLICK);
                        }
                    }
                }, getThrowableAction("04005"));
        mSubscriptions.add(subscription);
    }

    private void getCarts() {
        HashMap<String, Cart> carts = mXsmDbApi.getCarts();
//        if (null == carts || 0 == carts.size()) {
//            getCurrentOrder();
//        } else {
        if (mCurrentOrder != null) {
            updateCurrentOrderList();
        } else {
            createCgtCarts(carts);
        }
        getCgtLimits();
//        }
    }

//    //获得当期订单
//    private void getCurrentOrder() {
//        Subscription subscription = mXsmApi.current(mMerchant.getCustCode())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<HttpEntity<Order>>() {
//                    @Override
//                    public void call(HttpEntity<Order> httpEntity) {
//                        if (httpEntity.isSuccess()) {
//                            mCurrentOrder = httpEntity.getData();
//                        } else {
//                            mCurrentOrder = null;
//                        }
//                        updateCurrentOrderList();
//                        getCgtLimits();
//                    }
//                }, getThrowableAction("009"));
//        mSubscriptions.add(subscription);
//    }

    private void getCgtLimits() {
        List<String> cgtCodes = getLimitCgtCodes();
        if (null == cgtCodes || cgtCodes.size() == 0) {
            createCgtInfoList();
            initListView();
            return;
        }

        String str = "";
        for (String cgtCode : cgtCodes) {
            str += cgtCode + ",";
        }
        str = str.substring(0, str.length() - 1);
        Subscription subscription = mXsmApi.getCgtLmts(mMerchant.getCustCode(), mMerchant.getOrderDate(), str)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<List<Cigarette>>>() {
                    @Override
                    public void call(HttpEntity<List<Cigarette>> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            List<Cigarette> cigarettes = httpEntity.getData();
                            if (null != cigarettes || cigarettes.size() > 0) {
                                for (Cigarette cgtLmt : cigarettes) {
                                    String cgtCode = cgtLmt.getCgtCode();
                                    String qtyLmt = cgtLmt.getQtyLmt();
                                    Cigarette cigarette = mCgtMap.get(cgtCode);
                                    cigarette.setQtyLmt(qtyLmt);
                                    mCgtMap.put(cgtCode, cigarette);
                                }
                                // 获取每个商品的cgtCode
                                HashMap<String, Cart> carts = mCarts;
                                if (null != carts) {
                                    for (int i = 0 ; i < cigarettes.size(); i++) {
                                        String code = cigarettes.get(i).getCgtCode();
                                        String limit = cigarettes.get(i).getQtyLmt();
                                        Iterator<Map.Entry<String, Cart>> it = carts.entrySet().iterator();
                                        while (it.hasNext()) {
                                            Map.Entry<String, Cart> entry = it.next();
                                            Cart cart = entry.getValue();
                                            String cgtCode = cart.getCgtCode();
                                            if (code.equals(cgtCode)) {
                                                String qty = cart.getOrdQty();
                                                if (Integer.valueOf(qty) > Integer.valueOf(limit)) {
                                                    cart.setOrdQty(limit);
                                                    cart.setReqQty(limit);
                                                    mCarts.put(cgtCode, cart);
                                                } else {
                                                    mCarts.put(cgtCode, cart);
                                                }
                                            }
                                        }
                                    }
                                }

                                mXsmDbApi.saveCarts(mCarts);
                            }
                        }
                        createCgtInfoList();
                        initListView();
                    }
                }, getThrowableAction("04007"));
        mSubscriptions.add(subscription);
    }

    private void initListView() {
        if (null == mYuAdapter) {
            mYuAdapter = new XsmOrderingAdapter(mContext, mCigarettes);
            mYuAdapter.setItemBackground(R.color.color_eaeaea);
            mYuAdapter.setDataUpdateListener(mDataUpdateListener);
            mYuAdapter.setCgtCarts(mCarts);
            mYuAdapter.setMaxReqQty(Integer.parseInt(mLimit.getCoQtyLmt()));
            mYuAdapter.setOrderAmt(mOrderAmt);
            mYuAdapter.setOrderReqSum(mOrderReqSum);
            mBaseView.updateListView(mYuAdapter);
        } else {
            mYuAdapter.setItemBackground(R.color.color_eaeaea);
            mYuAdapter.setOrderAmt(mOrderAmt);
            mYuAdapter.setOrderReqSum(mOrderReqSum);
            mYuAdapter.setCgtInfos(mCigarettes);
            mYuAdapter.notifyDataSetChanged();
        }
        addShopList();
        if (mYuCartAdapter == null) {
            mYuCartAdapter = new XsmOrderingAdapter(mContext, mCartList);
            mYuCartAdapter.setDataUpdateListener(mCartDataUpdateListener);
            mYuCartAdapter.setCgtCarts(mCarts);
            mYuCartAdapter.setMaxReqQty(Integer.parseInt(mLimit.getCoQtyLmt()));
            mYuCartAdapter.setOrderAmt(mOrderAmt);
            mYuCartAdapter.setOrderReqSum(mOrderReqSum);
            mYuCartAdapter.setItemBackground(R.color.color_f6f6f6);
            mBaseView.updateCartListView(mYuCartAdapter);
        } else {
            mYuCartAdapter.setItemBackground(R.color.color_f6f6f6);
            mYuCartAdapter.setOrderAmt(mOrderAmt);
            mYuCartAdapter.setOrderReqSum(mOrderReqSum);
            mYuCartAdapter.setCgtInfos(mCartList);
            mYuCartAdapter.notifyDataSetChanged();
        }
        updateViews();
    }

    /**
     * 获取异型烟数量
     *
     * @param map ：购物车；Adapter中的HashMap
     * @return:异型烟数量
     */
    private int getSpecialCgtQty(HashMap<String, Cart> map) {
        int qty = 0;
        if (null != map && map.size() != 0) {
            Iterator<Map.Entry<String, Cart>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                int ordqty = 0;
                Map.Entry<String, Cart> entry = it.next();
                Cart cart = entry.getValue();
                Cigarette cigarette = mCgtMap.get(cart.getCgtCode());
                ordqty = Integer.parseInt(cart.getOrdQty());
                if (!cigarette.getIsCoMulti().equals("1")) {
                    qty += ordqty;
                }
            }
        }
        return qty;
    }

    /**
     * 获取规格
     *
     * @param map ：购物车；Adapter中的HashMap
     * @return:
     */
    private int getCgtCategory(HashMap<String, Cart> map) {
        int category = 0;
        if (null != map && map.size() != 0) {
            Iterator<Map.Entry<String, Cart>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                int ordQty = 0;
                Map.Entry<String, Cart> entry = it.next();
                Cart cart = entry.getValue();
                ordQty = Integer.parseInt(cart.getOrdQty());
                if (ordQty > 0)
                    category += 1;
            }
        }
        return category;
    }

    /**
     * 从网络中获取历史订单，并使用历史订单明细产生购物车
     *
     * @param details
     */
    private void createCgtCarts(List<OrderDetail> details) {
        mOrderReqSum = 0;
        mOrderAmt = 0.00;
        mCarts.clear();
        for (OrderDetail detail : details) {
            Cart cart = new Cart();
            String cgtcode = detail.getCgtCode();
            String ordQty = detail.getOrdQty();
            //String strPrice = detail.getPrice();
            cart.setCgtCode(cgtcode);
            cart.setCgtName(detail.getCgtName());
            cart.setOrdQty(ordQty);
            cart.setReqQty(ordQty);
            int req = Integer.parseInt(ordQty);
            mOrderReqSum += req;
            double price = Double.parseDouble(detail.getPrice());
            mOrderAmt += PreciseCompute.mul(price, req);
            mCarts.put(cgtcode, cart);
        }
    }

    /**
     * 从数据库存储存储数据中获取购物车
     */
    private void createCgtCarts(HashMap<String, Cart> carts) {
        String cgtCode = null;
        mOrderReqSum = 0;
        mOrderAmt = 0.00;
        mCarts.clear();
        if (null != carts) {
            Iterator<Map.Entry<String, Cart>> it = carts.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Cart> entry = it.next();
                Cart cart = entry.getValue();
                cgtCode = cart.getCgtCode();
                mCarts.put(cgtCode, cart);
            }
        }

    }

    /**
     * 使用购物车产生排序订单
     */
    private void createCgtInfoList() {
        mCigarettes.clear();
        List<Cigarette> cigarettes = getCartsCgtInfo();
        Iterator<Map.Entry<String, Cigarette>> it = mCgtMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cigarette> entry1 = it.next();
            Cigarette cigarette = entry1.getValue();
            if (null != mCarts.get(cigarette.getCgtCode()))
                continue;
            if (null != mFindList.get(cigarette.getCgtCode()))
                continue;
            mCigarettes.add(cigarette);
        }
        Collections.sort(mCigarettes, this);
        mCigarettes.addAll(0, cigarettes);
        mCigarettes.addAll(0, getFindCgtInfos());
    }

    /**
     * 获取购物车中的CgtInfo List，排定到ListView前面
     */
    private List<Cigarette> getCartsCgtInfo() {
        mOrderReqSum = 0;
        mOrderAmt = 0.00;

        Iterator<Map.Entry<String, Cart>> it = mCarts.entrySet().iterator();
        List<Cigarette> cigarettes = new ArrayList<>();
        while (it.hasNext()) {
            Map.Entry<String, Cart> entry = it.next();
            Cart cart = entry.getValue();

            if (null != mFindList.get(cart.getCgtCode()))
                continue;

            Cigarette cigarette = mCgtMap.get(cart.getCgtCode());
            cigarettes.add(cigarette);
            int req = Integer.parseInt(cart.getReqQty());
            mOrderReqSum += req;
            double price = Double.parseDouble(cigarette.getPrice());
            mOrderAmt += PreciseCompute.mul(price, req);
        }
        Collections.sort(cigarettes, this);
        return cigarettes;
    }

    private List<Cigarette> getFindCgtInfos() {
        List<Cigarette> cigarettes = new ArrayList<>();
        Iterator<Map.Entry<String, Cigarette>> it = mFindList.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Cigarette> entry1 = it.next();
            Cigarette find = entry1.getValue();

            if (mCurFindCgt.getCgtCode().equals(find.getCgtCode()))
                continue;
            cigarettes.add(find);
        }
        if (0 != mFindList.size())
            cigarettes.add(0, mCurFindCgt);
        return cigarettes;
    }

    public int getCartsSize() {
        return mCarts.size();
    }

    public List<String> getLimitCgtCodes() {
        if (mCarts == null || mCarts.size() == 0)
            return null;

        List<String> limitCgtCodes = new ArrayList<>();
        Iterator<Map.Entry<String, Cart>> it = mCarts.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cart> entry = it.next();
            Cart cart = entry.getValue();
            Cigarette cigarette = mCgtMap.get(cart.getCgtCode());
            if (null == cigarette.getQtyLmt())
                limitCgtCodes.add(cigarette.getCgtCode());
        }
        return limitCgtCodes;
    }


    private void updateViews() {
        mBaseView.updateAmount(Double.toString(mOrderAmt));
        int special = getSpecialCgtQty(mCarts);
        int normal = mOrderReqSum - special;
        int remain = Integer.parseInt(mLimit.getCoQtyLmt()) - mOrderReqSum;
        mBaseView.updateRemain(remain);
        mBaseView.updateReqSum(special, normal);
        mBaseView.showLoading(View.GONE);
    }

    //修改变动数量
    private void updateCgtLmt(String cgtCode, String qtyLmt) {
        int position = (Integer) mCurItemView.getTag();
        Cigarette cigarette = mCigarettes.get(position);
        cigarette.setQtyLmt(qtyLmt);
        mCgtMap.put(cgtCode, cigarette);
        mCigarettes.remove(position);
        mCigarettes.add(position, cigarette);
        mYuAdapter.setCgtInfos(mCigarettes);
        mYuAdapter.setCgtLmt(mCurItemView, cgtCode);
        mYuAdapter.setDataUpdateListener(mDataUpdateListener);
        mYuAdapter.notifyDataSetChanged();
        mCurItemView = null;
        mXsmDbApi.saveCigarettes(mCgtMap);
    }

    /**
     * 添加到购物车
     */
    private void addShopList() {
        if (mCartList.size() > 0) {
            mCartList.clear();
        }
        for (Cigarette c : mCigarettes) {
            if (null != mYuAdapter.getCgtCarts().get(c.getCgtCode())) {
                mCartList.add(c);
            }
        }
    }

    private XsmOrderingBaseAdapter.DataUpdateListener mDataUpdateListener = new XsmOrderingBaseAdapter.DataUpdateListener() {

        @Override
        public void update(Object... params) {
            mOrderAmt = Double.valueOf((String) params[0]);
            mOrderReqSum = Integer.parseInt((String) params[1]);
            mBaseView.updateAmount((String) params[0]);
            mBaseView.updateRemain(Integer.parseInt((String) params[1]));
            HashMap<String, Cart> map = mYuAdapter.getCgtCarts();
            mXsmDbApi.saveCarts(map);
            int special = getSpecialCgtQty(map);
            int normal = Integer.parseInt((String) params[2]) - special;
            mBaseView.updateReqSum(special, normal);
            addShopList();
            mYuCartAdapter.setCgtInfos(mCartList);
            mYuCartAdapter.setOrderAmt(mOrderAmt);
            mYuCartAdapter.setOrderReqSum(mOrderReqSum);
            mYuCartAdapter.setCgtCarts(mYuAdapter.getCgtCarts());
            mYuCartAdapter.notifyDataSetChanged();
        }

        @Override
        public void updateItem(Object... params) {
            getCgtLmt((View) params[0], (String) params[1], false);
        }

        public void updateView() {

        }
    };
    private XsmOrderingBaseAdapter.DataUpdateListener mCartDataUpdateListener = new XsmOrderingBaseAdapter.DataUpdateListener() {

        @Override
        public void update(Object... params) {
            mOrderAmt = Double.valueOf((String) params[0]);
            mOrderReqSum = Integer.parseInt((String) params[1]);
            mBaseView.updateAmount((String) params[0]);
            mBaseView.updateRemain(Integer.parseInt((String) params[1]));
            HashMap<String, Cart> map = mYuCartAdapter.getCgtCarts();
            mXsmDbApi.saveCarts(map);
            int special = getSpecialCgtQty(map);
            int normal = Integer.parseInt((String) params[2]) - special;
            mBaseView.updateReqSum(special, normal);
            mYuAdapter.setCgtInfos(mCigarettes);
            mYuAdapter.setOrderAmt(mOrderAmt);
            mYuAdapter.setCgtCarts(mYuCartAdapter.getCgtCarts());
            mYuAdapter.notifyDataSetChanged();
        }

        @Override
        public void updateItem(Object... params) {
            getCgtLmt((View) params[0], (String) params[1], true);
        }

        public void updateView() {

        }
    };

    public Merchant getMerchant() {
        return mXsmDbApi.getMerchant();
    }
}
