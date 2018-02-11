package com.busilinq.presenter.classify;

import com.busilinq.contract.classify.IGoodsDetailView;
import com.busilinq.data.JsonRequestBody;
import com.busilinq.data.SubscriberCallBack;
import com.busilinq.data.api.RetrofitApiFactory;
import com.busilinq.data.cache.UserCache;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.GoodsDetailEntity;
import com.busilinq.data.entity.UserFavoriteEntity;
import com.busilinq.presenter.BasePresenter;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * Company：华科建邺
 * Class Describe：商品详情 界面业务操作类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView> {

    public GoodsDetailPresenter(IGoodsDetailView MvpView) {
        super(MvpView);
    }

    /**
     * 获取商品详情
     *
     * @param userId
     */
    public void getGoodsDetail(int userId, int goodsId) {
        addSubscription(RetrofitApiFactory.getClassifyApi().getGoodsDetails(userId, goodsId), new SubscriberCallBack<GoodsDetailEntity>() {
            @Override
            protected void onSuccess(GoodsDetailEntity data) {
                MvpView.GoodsDetail(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });

    }

    /**
     * @param goodsId 商品id
     * @param number  修改数量
     * @param price   价格
     */
    public void AddCart(int goodsId, int number, double price) {

        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("goodsId", goodsId);
        param.put("number", number);
        param.put("price", price);
        RequestBody body = JsonRequestBody.createJsonBody(param);

        MvpView.showProgress("加入中...");
        addSubscription(RetrofitApiFactory.getCartApi().AddCart(body), new SubscriberCallBack<CartEntity>() {
            @Override
            protected void onSuccess(CartEntity cartEntity) {
                MvpView.Success(cartEntity);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    public void addFavorite(int goodsId) {
        Map<String, Object> param = new HashMap<>();
        param.put("userId", UserCache.GetUserId());
        param.put("goodsId", goodsId);
        RequestBody body = JsonRequestBody.createJsonBody(param);
        addSubscription(RetrofitApiFactory.getMineApi().addFavorite(body), new SubscriberCallBack<UserFavoriteEntity>() {
            @Override
            protected void onSuccess(UserFavoriteEntity data) {
                MvpView.ShowFavorite(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 取消收藏
     *
     * @param goodsId
     */
    public void cancelFavorite(String goodsId) {

        addSubscription(RetrofitApiFactory.getMineApi().deleteMyCollection(UserCache.GetUserId(), "", goodsId), new SubscriberCallBack<UserFavoriteEntity>() {
            @Override
            protected void onSuccess(UserFavoriteEntity data) {
                MvpView.ShowFavorite(data);
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }

    /**
     * 判断是否收藏
     *
     * @param goodsId
     */
    public void FavoriteVerify(int goodsId) {

        addSubscription(RetrofitApiFactory.getMineApi().FavoriteVerify(UserCache.GetUserId(), goodsId), new SubscriberCallBack<UserFavoriteEntity>() {
            @Override
            protected void onSuccess(UserFavoriteEntity data) {
                if (data != null) {
                    MvpView.ShowFavorite(data);
                }
            }

            @Override
            public void onCompleted() {
                MvpView.hideProgress();
            }
        });
    }
}
