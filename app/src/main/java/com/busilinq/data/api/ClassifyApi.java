package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.GoodsCategoryEntity;
import com.busilinq.data.entity.GoodsDetailEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.SpecialGoodsEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Company：华科建邺
 * Class Describe：分类模块接口
 * Create Person：Chenyx
 * Create Time：2018/1/26 上午11:36
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface ClassifyApi {

    /**
     * 获取商品分类列表
     *
     * @param userId 用户id
     * @return
     */
    @GET("/api/goods/classify")
    Observable<BaseData<List<GoodsCategoryEntity>>> getClassifyList(@Query("userId") int userId);

    /**
     * 获取商品列表
     *
     * @param userId     用户id
     * @param classifyId 分类ID
     * @param page       当前页
     * @param limit      每页条数
     * @return
     */
    @GET("/api/goods/list")
    Observable<BaseData<PageEntity<HomeGoodsEntity>>> getGoodsList(@Query("userId") int userId, @Query("classifyId") String classifyId, @Query("page") int page, @Query("limit") int limit);

    /**
     * 获取特价商品列表
     *
     * @param userId     用户id
     * @param classifyId 分类ID
     * @param page       当前页
     * @param limit      每页条数
     * @return
     */
    @GET("/api/goods/special")
    Observable<BaseData<PageEntity<SpecialGoodsEntity>>> getSpecialGoodsList(@Query("userId") int userId, @Query("classifyId") int classifyId, @Query("page") int page, @Query("limit") int limit);

    /**
     * 商品搜索列表
     *
     * @param userId     用户id
     * @param classifyId 分类ID
     * @param page       当前页
     * @param limit      每页条数
     * @return
     */
    @GET("/api/goods/search")
    Observable<BaseData<PageEntity<HomeGoodsEntity>>> getGoodsSearchList(@Query("userId") int userId, @Query("classifyId") String classifyId, @Query("page") int page, @Query("limit") int limit, @Query("name") String name);

    /**
     * 获取商品详情
     *
     * @param userId  用户id
     * @param goodsId 商品id
     * @return
     */
    @GET("/api/goods/details")
    Observable<BaseData<GoodsDetailEntity>> getGoodsDetails(@Query("userId") int userId, @Query("goodsId") int goodsId);
}
