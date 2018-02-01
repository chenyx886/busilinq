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
public interface ICodeView extends IBaseMvpView {
    /**
     * 操作成功
     */
    void Success(String code);
}
