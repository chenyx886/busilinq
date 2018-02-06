package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.CartEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.data.entity.UserShopAddrEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Company：华科建邺
 * Class Describe：购物车模块接口
 * Create Person：Chenyx
 * Create Time：2018/1/26 上午11:38
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface CartApi {

    /**
     * @param type 类型
     * @return
     */
    @GET("/api/adv/banner")
    Observable<BaseData<List<BannerEntity>>> banner(@Query("type") String type);

    /**
     * 获取购物车列表
     *
     * @param page  当前页
     * @param limit 每页条数
     * @return
     */
    @GET("/api/user/cart")
    Observable<BaseData<PageEntity<MainCartEntity>>> cart(@Query("page") int page, @Query("limit") int limit, @Query("userId") String userId);

    /**
     * 确认订单时获取默认收货地址
     *
     * @param userId
     * @return
     */
    @GET("/api/user/address/default")
    Observable<BaseData<UserShopAddrEntity>> getDefaultAddress(@Query("userId") String userId);

    /**编辑购物车，包括增加减少修改，共用一接口
     * @param body
     * @return
     */
    @PUT("/api/user/cart")
    Observable<BaseData<CartEntity>> UpdateCart(@Body RequestBody body);
    /**
     * 删除购物车列表项
     * @param userId
     * @return
     */
    @DELETE("/api/user/cart")
    Observable<BaseData> deleteCart(@Query("userId") String userId, @Query("cartId") Integer cartId);

}
