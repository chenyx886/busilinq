package com.busilinq.contract.mine;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.MyCollectionEntity;

/**
 * Company：华科建邺
 * Class Describe：我的收藏
 * Create Person：wangshimei
 * Create Time：18/2/6 下午1:53
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface MyCollectionView extends IBaseMvpView {
    /**
     * 收藏列表
     *
     * @param list
     */
    void getMyCollectionList(PageEntity<MyCollectionEntity> list);

    /**
     * 删除收藏 成功
     */
    void deleteSuccess(int pos);
}
