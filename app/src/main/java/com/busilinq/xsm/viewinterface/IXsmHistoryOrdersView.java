package com.busilinq.xsm.viewinterface;


import com.busilinq.xsm.ui.adapter.XsmHistoryOrderAdapter;

/**
 * Created by yu on 2017/6/16.
 */

public interface IXsmHistoryOrdersView extends IBaseView {
    void updateList(XsmHistoryOrderAdapter adapter);
}
