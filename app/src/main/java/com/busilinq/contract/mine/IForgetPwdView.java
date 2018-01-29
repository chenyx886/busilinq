package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;

/**
 * Company：华科建邺
 * Class Describe：获取验证码 Presention 和 View的 关联
 * Create Person：Chenyx
 * Create Time：2017/11/12 下午11:26
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface IForgetPwdView extends IBaseMvpView {
    /**
     * 修改成功
     *
     * @param type 1:发送验证码 2:修改密码
     * @param msg  提示消息
     */
    void Success(int type, String msg);
}
