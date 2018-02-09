package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.HomeOrderEntity;

import retrofit2.http.GET;
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

}
