package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.entity.CodeEntity;
import com.busilinq.data.entity.UserEntity;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
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
    @POST("/api/user/login")
    Observable<BaseData<UserEntity>> login(@Body RequestBody body);


    /**
     * 获取验证码
     *
     * @param type
     * @param phone
     * @return
     */
    @GET("/api/user/getCode")
    Observable<BaseData<CodeEntity>> getCode(@Query("type") int type, @Query("phone") String phone);


    /**
     * 注册
     *
     * @param body
     * @return
     */
    @POST("/api/user/register")
    Observable<BaseData> register(@Body RequestBody body);

    /**
     * 验证验证码
     *
     * @param body
     * @return
     */
    @PUT("/api/user/verifyCode")
    Observable<BaseData> verifyCode(@Body RequestBody body);

    /**
     * 忘记密码
     *
     * @param body
     * @return
     */
    @PUT("/api/user/gorgetPassword")
    Observable<BaseData> gorgetPassword(@Body RequestBody body);

    /**
     * 获取用户信息
     *
     * @param body
     * @return
     */
    @GET("/api/user/getInfo")
    Observable<BaseData> getInfo(@Body RequestBody body);

    /**
     * 修改用户头像
     *
     * @param body
     * @return
     */
    @GET("/api/user/modifyHead")
    Observable<BaseData> modifyHead(@Body RequestBody body);

    /**
     * 修改密码
     *
     * @param body
     * @return
     */
    @PUT("/api/user/modifyPassword")
    Observable<BaseData> modifyPassword(@Body RequestBody body);

}
