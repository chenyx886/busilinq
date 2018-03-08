package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/26 下午1:28
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface FeedbackView extends IBaseMvpView {
    /**
     * 提交意见反馈成功
     *
     * @param msg
     */
    void submitSuccess(String msg);
}
