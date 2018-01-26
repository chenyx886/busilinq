package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe： 用户实体
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class UserEntity {
    /**
     *
     */
    private String memberId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String name;
    /**
     * 真实姓名
     */
    private String nick;
    /**
     * 性别
     */
    private String gender;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 出生年月
     */
    private String birthday;
    /**
     * 头像
     */
    private String headerImage;
    /**
     * token 值
     */
    private String token;


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeaderImage() {
        return headerImage;
    }

    public void setHeaderImage(String headerImage) {
        this.headerImage = headerImage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
