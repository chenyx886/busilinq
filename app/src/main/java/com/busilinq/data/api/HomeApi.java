package com.busilinq.data.api;


import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.BannerEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.InfoNoticeEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Company：华科建邺
 * Class Describe：首页模块接口
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface HomeApi {

    /**
     * 获取轮播广告
     *
     * @param type 类型
     * @return
     */
    @GET("/api/adv/banner")
    Observable<BaseData<List<BannerEntity>>> banner(@Query("type") String type);

    /**
     * 获取推荐商品列表
     *
     * @param page  当前页
     * @param limit 每页条数
     * @return
     */
    @GET("/api/goods/recommend")
    Observable<BaseData<PageEntity<HomeGoodsEntity>>> recommend(@Query("page") int page, @Query("limit") int limit);

    /**
     * 信息公告列表
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GET("api/common/affache")
    Observable<BaseData<PageEntity<InfoNoticeEntity>>> getInfoNoticeList(@Query("userId") int userId, @Query("page") int page, @Query("limit") int limit);
}
