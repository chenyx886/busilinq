package com.busilinq.xsm.viewinterface;


import com.busilinq.xsm.data.entity.Order;
import com.busilinq.xsm.ui.adapter.XsmOrderDetailAdapter;

/**
 * Created by dingyi on 2016/11/18.
 */

public interface IXsmOrdersView extends IBaseView {
    void updateCurrentOrder(Order order, int number, double total);
    void updateList(XsmOrderDetailAdapter adapter);
}
