package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.UserEntity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2018/2/8 下午1:50
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface IMineView extends IBaseMvpView {
    /**
     * 显示
     */
    void showUserInfo(UserEntity user);
}
