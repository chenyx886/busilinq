package com.busilinq.contract.home;


import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.InfoNoticeEntity;

/**
 * Company：华科建邺
 * Class Describe：消息公告
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public interface IInfoNoticeView extends IBaseMvpView {


    /**
     * 商品搜索
     *
     * @param list
     */
    void showNoticeList(PageEntity<InfoNoticeEntity> list);
}
