package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.UserEntity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/2 下午4:19
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface UserInfoView extends IBaseMvpView {

    void getUserInfoSuccess(UserEntity entity);
}
