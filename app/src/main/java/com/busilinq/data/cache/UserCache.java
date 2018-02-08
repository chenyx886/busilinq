package com.busilinq.data.cache;

import android.text.TextUtils;

import com.busilinq.data.entity.UserEntity;
import com.chenyx.libs.utils.SysConfig;

/**
 * Company：华科建邺
 * Class Describe： 用户实体缓存
 * Create Person：Chenyx
 * Create Time：2018/01/29 11:30
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
     * 用户名
     */
    public static final String NAME = "name";
    /**
     * 用户电话号码
     */
    public static final String CELL = "cell";
    /**
     * 性别:0男 1女
     */
    public static final String GENDER = "gender";
    /**
     * 用户真实姓名
     */
    public static final String REALNAME = "realName";
    /**
     * 用户头像
     */
    public static final String HEADIMGURL = "headimgurl";

    /**
     * 登录保存的session
     */
    public static final String SESSION = "session";


    /**
     * 保存登陆用户信息
     *
     * @param user
     */
    public static void put(UserEntity user) {

        PrefCache.putData(USERID, user.getUserId());
        PrefCache.putData(NAME, user.getName());
        PrefCache.putData(CELL, user.getCell());
        PrefCache.putData(GENDER, user.getGender());
        PrefCache.putData(REALNAME, user.getRealName());
        PrefCache.putData(HEADIMGURL, user.getHeadimgurl());
        PrefCache.putData(SESSION, user.getSession());

    }

    public static void clear() {
        PrefCache.putData(USERID, "");
        PrefCache.putData(NAME, "");
        PrefCache.putData(CELL, "");
        PrefCache.putData(GENDER, "");
        PrefCache.putData(REALNAME, "");
        PrefCache.putData(HEADIMGURL, "");
        PrefCache.putData(SESSION, "");
        user = null;
    }

    /**
     * 获取用户ID
     *
     * @return
     */
    public static int GetUserId() {
        if (UserCache.get() != null)
            return UserCache.get().getUserId();
        return 0;
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
                user.setUserId(SysConfig.nullToInt(id));
                user.setName((String) PrefCache.getData(NAME, ""));
                user.setCell((String) PrefCache.getData(CELL, ""));
                user.setGender((String) PrefCache.getData(GENDER, ""));
                user.setRealName((String) PrefCache.getData(REALNAME, ""));
                user.setHeadimgurl((String) PrefCache.getData(HEADIMGURL, ""));
                user.setSession((String) PrefCache.getData(SESSION, ""));
            }
        }
        return user;
    }

}
