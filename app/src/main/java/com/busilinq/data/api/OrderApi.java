package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.HomeOrderEntity;
import com.busilinq.data.entity.OrderEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/1/26 上午11:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface OrderApi {
    @GET("/api/order/list")
    Observable<BaseData<PageEntity<HomeOrderEntity>>> getOrders(@Query("userId") int userId, @Query("page") int page, @Query("limit") int limit);

    @GET("/api/order/detail")
    Observable<BaseData<HomeOrderEntity>> getOrdersDetial(@Query("userId") int userId, @Query("orderNum") String orderNum);

    /**
     * 提交订单
     *
     * @param body
     * @return
     */
    @POST("/api/order/create")
    Observable<BaseData<OrderEntity>> submitOrder(@Body RequestBody body);

    /**
     * 删除订单
     *
     * @param userId
     * @return
     */
    @DELETE("/api/order/delete")
    Observable<BaseData> deleteOrder(@Query("userId") int userId, @Query("orderId") int orderId, @Query("orderNum") String orderNum);

}
