package com.busilinq.data.api;

import com.busilinq.data.BaseData;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.CodeEntity;
import com.busilinq.data.entity.MyCollectionEntity;
import com.busilinq.data.entity.TServiceAccountEntity;
import com.busilinq.data.entity.TUpgradeEntity;
import com.busilinq.data.entity.UploadEntity;
import com.busilinq.data.entity.UserEntity;
import com.busilinq.data.entity.UserFavoriteEntity;
import com.busilinq.data.entity.UserShopAddrEntity;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    Observable<BaseData<CodeEntity>> getCode(@Query("type") int type,
                                             @Query("phone") String phone);


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
    @PUT("/api/user/forgetPassword")
    Observable<BaseData> forgetPassword(@Body RequestBody body);

    /**
     * 获取用户信息
     *
     * @return
     */
    @GET("/api/user/getInfo")
    Observable<BaseData<UserEntity>> getInfo(@Query("userId") int userId,
                                             @Query("name") String name);

    /**
     * 修改用户头像
     *
     * @param body
     * @return
     */
    @PUT("/api/user/modifyHead")
    Observable<BaseData> modifyHead(@Body RequestBody body);


    /**
     * 上传文件
     *
     * @param userId
     * @param multiparts
     * @return
     */
    @Multipart
    @POST("/api/file/upload")
    Observable<BaseData<List<UploadEntity>>> upload(@Query("userId") int userId,
                                                    @Part List<MultipartBody.Part> multiparts);

    /**
     * 修改密码
     *
     * @param body
     * @return
     */
    @PUT("/api/user/modifyPassword")
    Observable<BaseData> modifyPassword(@Body RequestBody body);

    /**
     * 获取收货地址列表
     *
     * @param userId
     * @return
     */
    @GET("/api/user/address")
    Observable<BaseData<List<UserShopAddrEntity>>> getAddressList(@Query("userId") int userId);

    /**
     * 查询当前版本
     *
     * @param body
     * @return
     */
    @POST("/api/software/upgrade")
    Observable<BaseData<TUpgradeEntity>> upgrade(@Body RequestBody body);

    /**
     * 设置默认收货地址
     *
     * @param userId
     * @return
     */
    @PUT("/api/user/address/default")
    Observable<BaseData<List<UserShopAddrEntity>>> setDefaultAddress(@Query("userId") int userId,
                                                                     @Query("addrId") Integer addrId);


    /**
     * 添加收货地址
     *
     * @param body
     * @return
     */
    @POST("/api/user/address")
    Observable<BaseData> addAddress(@Body RequestBody body);

    /**
     * 设置默认收货地址
     *
     * @param userId
     * @return
     */
    @DELETE("/api/user/address")
    Observable<BaseData> deleteAddress(@Query("userId") int userId,
                                       @Query("addrId") Integer addrId);

    /**
     * 添加收货地址
     *
     * @param body
     * @return
     */
    @PUT("/api/user/address")
    Observable<BaseData> editAddress(@Body RequestBody body);

    /**
     * 修改用户资料
     *
     * @param body
     * @return
     */
    @PUT("/api/user/modifyDetail")
    Observable<BaseData> modifyUserInfo(@Body RequestBody body);

    /**
     * 我的收藏
     *
     * @param userId
     * @param page
     * @param limit
     * @return
     */
    @GET("/api/user/favorite")
    Observable<BaseData<PageEntity<MyCollectionEntity>>> getMyCollectionList(@Query("userId") int userId,
                                                                             @Query("page") int page,
                                                                             @Query("limit") int limit);

    /**
     * 添加收藏
     *
     * @return
     */
    @POST("/api/user/favorite")
    Observable<BaseData<UserFavoriteEntity>> addFavorite(@Body RequestBody body);

    /**
     * 判断是否收藏
     *
     * @param userId
     * @param goodsId
     * @return
     */
    @GET("/api/user/favorite/verify")
    Observable<BaseData<UserFavoriteEntity>> FavoriteVerify(@Query("userId") int userId,
                                                            @Query("goodsId") int goodsId);

    /**
     * 删除收藏列表
     *
     * @param favoriteId
     * @return
     */
    @DELETE("/api/user/favorite")
    Observable<BaseData> deleteMyCollection(@Query("userId") int userId,
                                            @Query("favoriteId") String favoriteId,
                                            @Query("goodsId") String goodsId);

    /**
     * 获取xsm服务地址列表
     *
     * @return
     */
    @GET("/api/common/service")
    Observable<BaseData<List<TServiceAccountEntity>>> getService();

    /**
     * 提交意见反馈
     *
     * @return
     */
    @POST("/api/user/opinion")
    Observable<BaseData> submitFeedback(@Body RequestBody body);

}
