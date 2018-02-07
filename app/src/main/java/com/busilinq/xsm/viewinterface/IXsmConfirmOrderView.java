package com.busilinq.xsm.viewinterface;


import com.busilinq.xsm.ui.adapter.XsmOrderingAdapter;

/**
 * Created by yu on 2017/6/14.
 */

public interface IXsmConfirmOrderView extends IBaseView {
    void updateRemain(int remain);

    void updateReqSum(int special, int normal);

    void updateAmount(String amount);

    void updateCategory(int category);

    void updateListView(XsmOrderingAdapter adapter);
}
