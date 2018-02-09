package com.busilinq.contract.mine.order;

import com.busilinq.contract.IBaseMvpView;
import com.busilinq.data.entity.HomeOrderEntity;

/**
 * Created by yu on 2018/1/31.
 */
public interface IMyOrdersDetailView extends IBaseMvpView {
    void OrdersListDetail(HomeOrderEntity detail);

    /**
     * 删除成功
     */
    void success();
}
