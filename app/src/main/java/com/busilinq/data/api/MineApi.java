package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.entity.UserEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Company：华科建邺
 * Class Describe：我的模块接口
 * Create Person：Chenyx
 * Create Time：2018/1/26 上午11:34
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface MineApi {
    /**
     * 用户登录
     *
     * @param body
     * @return
     */
    @POST("shop/user/user/toLogin")
    Observable<BaseData<UserEntity>> toLogin(@Body RequestBody body);

    /**
     * 修改密码
     *
     * @param body
     * @return
     */
    @POST("shop/user/user/toLogin")
    Observable<BaseData<UserEntity>> updateUserPwd(@Body RequestBody body);

    /**
     * 获取验证码
     *
     * @param body
     * @return
     */
    @POST("shop/user/user/toLogin")
    Observable<BaseData> sendCode(@Body RequestBody body);

    /**
     * 个人信息
     *
     * @param body
     * @return
     */
    @POST("shop/user/user/toLogin")
    Observable<BaseData<UserEntity>> getUserById(@Body RequestBody body);
}
