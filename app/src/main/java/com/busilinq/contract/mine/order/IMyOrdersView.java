package com.busilinq.contract.mine.order;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.PageEntity;
import com.busilinq.data.entity.HomeOrderEntity;

/**
 * Created by yu on 2018/1/31.
 */
public interface IMyOrdersView extends IBaseMvpView {
    void OrdersList(PageEntity<HomeOrderEntity> list);
}
