package com.busilinq.contract;


import com.busilinq.data.entity.UserEntity;

/**
 * Company：华科建邺
 * Class Describe：登录 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface ILoginView extends IBaseMvpView {
    /**
     * 登录成功
     */
    void Success(UserEntity data);
}
