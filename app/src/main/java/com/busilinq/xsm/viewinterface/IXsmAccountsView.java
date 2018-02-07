package com.busilinq.xsm.viewinterface;


import com.busilinq.xsm.ui.adapter.XsmAccountsAdapter;

/**
 * Created by yu on 2017/6/27.
 */

public interface IXsmAccountsView extends IBaseView{
    void updateListView(XsmAccountsAdapter adapter);
    void updateView(boolean isDatas);
}
