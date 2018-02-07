package com.busilinq.xsm.viewinterface;


import com.busilinq.xsm.data.entity.pospay.PosPayOrder;

/**
 * Created by dingyi on 2017/9/5.
 */

public interface IUmsPosPayView extends IBaseView{
    void updatePayInfoViews(PosPayOrder order);
    void updateValidTimeView(String validTime);
//    void updateNotice(String notice);
}
