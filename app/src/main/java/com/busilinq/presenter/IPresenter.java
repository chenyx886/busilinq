package com.busilinq.presenter;

/**
 * Company：华科建邺
 * Class Describe： IPresenter 基类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public interface IPresenter<V> {


    void attachView(V view);

    void detachView();
}

