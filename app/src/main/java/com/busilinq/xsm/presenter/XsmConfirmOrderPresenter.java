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
import com.busilinq.xsm.ui.XsmOrderingActivity;
import com.busilinq.xsm.ui.XsmOrdersActivity;
import com.busilinq.xsm.ui.adapter.XsmOrderingAdapter;
import com.busilinq.xsm.ui.adapter.XsmOrderingBaseAdapter;
import com.busilinq.xsm.ulits.Logger;
import com.busilinq.xsm.ulits.PreciseCompute;
import com.busilinq.xsm.ulits.XsmUtil;
import com.busilinq.xsm.viewinterface.IXsmConfirmOrderView;
import com.busilinq.xsm.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * 策略：1、获取客户限量；2、获取烟品信息；3、获取购物车；4、获取当前订单；5、获取烟品限量；6、更新列表
 * Created by yu on 2017/6/7.
 */

public class XsmConfirmOrderPresenter extends XsmBasePresenter<IXsmConfirmOrderView> implements Comparator<Cigarette> {
    private View mCurItemView;
    private Merchant mMerchant;
    private XsmOrderingAdapter mYuAdapter;
    private Limit mLimit;
    //购物车
    private HashMap<String, Cart> mCarts = new HashMap<>();
    //总烟品map
    private HashMap<String, Cigarette> mCgtMap = new HashMap<>();
    //所有烟品
    private List<Cigarette> mCigarettes = new ArrayList<>();
    //总价格
    private double mOrderAmt = 0;
    //总数量
    private int mOrderReqSum = 0;

    @Override
    public void attachView(IXsmConfirmOrderView view) {
        super.attachView(view);
        mMerchant = mXsmDbApi.getMerchant();
    }


    public void start() {
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


    public void addOrder() {
        final List<OrderDetail> details = createCommitData();
        if (null == details || 0 == details.size()) {
            return;
        }

        final Merchant merchant = mXsmDbApi.getMerchant();
        mBaseView.showProgressDialog("正在提交数据，请稍候");
        Subscription subscription = mXsmApi.current(mMerchant.getCustCode())
                .delay(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<HttpEntity<Order>, Observable<HttpEntity<Object>>>() {
                    @Override
                    public Observable<HttpEntity<Object>> call(HttpEntity<Order> httpEntity) {
                        if (httpEntity.isSuccess()) {
                            Order order = httpEntity.getData();
                            if (!XsmUtil.isAddStateOrder(order)) {
                                mBaseView.closeProgressDialog();
                                mBaseView.toast("订单已存在，且无法修改");
                                return null;
                            } else {
                                return mXsmApi.updateOrder(merchant.getCustCode(), merchant.getOrderDate(), order.getCoNum(), details);
                            }
                        } else {
                            return mXsmApi.addOrder(merchant.getCustCode(), merchant.getOrderDate(), details);
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<HttpEntity<Object>>() {
                    @Override
                    public void call(HttpEntity<Object> httpEntity) {
                        mBaseView.closeProgressDialog();
                        mBaseView.toast(httpEntity.getMsg());
                        if (httpEntity.isSuccess()) {
                            mXsmDbApi.clear(Cart.class.getSimpleName());
                            XsmOrderingActivity.getYuXsmOrderingActivity().finish();
                            mBaseView.skipActivity((Activity) mContext, XsmOrdersActivity.class);
                        }
                    }
                }, getThrowableAction("0013"));
        mSubscriptions.add(subscription);
    }

    //得到具体烟品限量
    private void getCgtLmt(View v, final String cgtCode) {
        final Cigarette cigarette = mCgtMap.get(cgtCode);
        String lmt = null;
        if (null != cigarette) {
            lmt = cigarette.getQtyLmt();
            mCgtMap.put(cgtCode, cigarette);
        }
        if (null != lmt) {
            mYuAdapter.setCgtLmt(v, cgtCode);
        }
        mCurItemView = v;
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


    //获取客户限量
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

    //获取购物车
    private void getCarts() {
        HashMap<String, Cart> carts = mXsmDbApi.getCarts();
        createCgtCarts(carts);
        getCgtLimits();
    }


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
                                    for (int i = 0; i < cigarettes.size(); i++) {
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
            mYuAdapter.setDataUpdateListener(mDataUpdateListener);
            mYuAdapter.setCgtCarts(mCarts);
            mYuAdapter.setMaxReqQty(Integer.parseInt(mLimit.getCoQtyLmt()));
            mYuAdapter.setOrderAmt(mOrderAmt);
            mYuAdapter.setOrderReqSum(mOrderReqSum);
//            mYuAdapter.setItemBackground(R.color.color_eaeaea);
            mBaseView.updateListView(mYuAdapter);
        } else {
//            mYuAdapter.setItemBackground(R.color.color_eaeaea);
            mYuAdapter.setOrderAmt(mOrderAmt);
            mYuAdapter.setOrderReqSum(mOrderReqSum);
            mYuAdapter.setCgtInfos(mCigarettes);
            mYuAdapter.notifyDataSetChanged();
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
     * 使用购物车订单
     */
    private void createCgtInfoList() {
        mCigarettes.clear();
        List<Cigarette> cigarettes = getCartsCgtInfo();
        mCigarettes.addAll(0, cigarettes);
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


            Cigarette cigarette = mCgtMap.get(cart.getCgtCode());
            if (cigarette == null)
                Logger.e(cart.getCgtCode());
            cigarettes.add(cigarette);
            int req = Integer.parseInt(cart.getReqQty());
            mOrderReqSum += req;
            double price = Double.parseDouble(cigarette.getPrice());
            mOrderAmt += PreciseCompute.mul(price, req);
        }
        Collections.sort(cigarettes, this);
        return cigarettes;
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

    /**
     * 创建需要提交订单的订单明细
     *
     * @return
     */
    private List<OrderDetail> createCommitData() {
        int iCoMultiSum = 0;
        HashMap<String, Cart> map = mYuAdapter.getCgtCarts();
        List<OrderDetail> details = new ArrayList<>();
        if (map == null || map.size() == 0) {
            mBaseView.toast("您没有选择任何烟品");
            return null;
        }
        Iterator<Map.Entry<String, Cart>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            String cgtCode = null;
            String cgtName = null;
            int ordqty = 0;
            int qtylmt = 0;
            double ordamt = 0.00;
            double price = 0.00;
            Map.Entry<String, Cart> entry = it.next();
            Cart cart = entry.getValue();
            cgtCode = cart.getCgtCode();
            cgtName = cart.getCgtName();
            Cigarette info = mCgtMap.get(cgtCode);
            OrderDetail detail = new OrderDetail();
            detail.setCgtCode(cgtCode);
            detail.setCgtName(cgtName);

            price = Double.parseDouble(info.getPrice());
            try {
                qtylmt = Integer.parseInt(info.getQtyLmt());
            } catch (Exception e) {
                qtylmt = 0;
            }
            ordqty = Integer.parseInt(cart.getOrdQty());
            if (qtylmt < ordqty)
                ordqty = qtylmt;

            if (ordqty == 0)
                continue;

            ordamt = PreciseCompute.mul(price, ordqty);

            if (info.getIsCoMulti().equals("1")) {
                iCoMultiSum += ordqty;
            }

            detail.setOrdAmt(String.format("%.2f", ordamt));
            detail.setOrdQty(cart.getOrdQty());
            detail.setReqQty(cart.getReqQty());
            detail.setPrice(info.getPrice());
            detail.setQtyLmt(info.getQtyLmt());
            detail.setRtlPrice(info.getRtlPrice());
            detail.setShortCode(info.getShortCode());
            detail.setUmSaleName(info.getUmSaleName());
            detail.setVfyQty(cart.getOrdQty());
            details.add(detail);
        }

        if ((iCoMultiSum % 5) != 0) {
            mBaseView.toast(mRes.getString(R.string.myxsm_ordering_failure_cgtsum_error) + "  " + iCoMultiSum);
            details = null;
        }
        return details;
    }

    private void updateViews() {
        mBaseView.updateAmount(Double.toString(mOrderAmt));
        mBaseView.updateCategory(getCgtCategory(mCarts));
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
        }

        @Override
        public void updateItem(Object... params) {
            getCgtLmt((View) params[0], (String) params[1]);
        }

        public void updateView() {

        }
    };

    public Merchant getMerchant() {
        return mXsmDbApi.getMerchant();
    }
}
