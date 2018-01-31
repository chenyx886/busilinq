package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.entity.GoodsCategoryEntity;

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
    Observable<BaseData<List<GoodsCategoryEntity>>> getClassifyList(@Query("userId") String userId);
}
