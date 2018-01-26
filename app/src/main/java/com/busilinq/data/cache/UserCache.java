package com.busilinq.data.cache;

import android.text.TextUtils;

import com.busilinq.data.entity.UserEntity;

/**
 * Company：华科建邺
 * Class Describe： 用户实体缓存
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserCache {


    /**
     * 缓存用户信息
     */
    private static UserEntity user;
    /**
     * 用户ID
     */
    public static final String USERID = "userId";
    /**
     * 昵称
     */
    public static final String NICK = "nick";
    /**
     * 用户名
     */
    public static final String NAME = "name";
    /**
     * 手机号
     */
    public static final String PHONENO = "phoneNo";
    /**
     * 头像
     */
    public static final String HEADERIMAGE = "headerImage";

    /**
     * 登录保存的Token
     */
    public static final String TOKEN = "token";

    /**
     * 保存登录密码
     */
    public static final String PASSWORD = "password";

    /**
     * 保存登陆用户信息
     *
     * @param user
     */
    public static void put(UserEntity user) {

        PrefCache.putData(USERID, user.getUserId());
        PrefCache.putData(PHONENO, user.getPhoneNo());
        PrefCache.putData(NAME, user.getName());
        PrefCache.putData(NICK, user.getNick());
        PrefCache.putData(HEADERIMAGE, user.getHeaderImage());
        PrefCache.putData(TOKEN, user.getToken());

    }

    public static void clear() {
        PrefCache.putData(USERID, "");
        PrefCache.putData(PHONENO, "");
        PrefCache.putData(HEADERIMAGE, "");
        PrefCache.putData(NAME, "");
        PrefCache.putData(NICK, "");
        PrefCache.putData(TOKEN, "");
        PrefCache.putData(PASSWORD, "");
        user = null;
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static UserEntity get() {

        String id = (String) PrefCache.getData(USERID, "");
        if (!TextUtils.isEmpty(id)) {
            if (user == null) {
                user = new UserEntity();
                user.setUserId(id);
                user.setPhoneNo((String) PrefCache.getData(PHONENO, ""));
                user.setNick((String) PrefCache.getData(NICK, ""));
                user.setName((String) PrefCache.getData(NAME, ""));
                user.setHeaderImage((String) PrefCache.getData(PHONENO, ""));
                user.setToken((String) PrefCache.getData(TOKEN, ""));

            }
        }
        return user;
    }

}
