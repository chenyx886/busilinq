package com.busilinq.xsm.widget;


import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;
import com.busilinq.xsm.viewinterface.IBaseView;

/**
 * Created by dingyi on 2016/11/18.
 */

public interface IXsmOrderDetailView extends IBaseView {
    void updateOrderInfo(Order order);
    void updateListView(XsmOrderDetailAdapter adapter);
    void updateView(Order order, boolean isCurrentOrder);
}
