package com.busilinq.xsm.viewinterface;

import android.widget.ArrayAdapter;

import com.busilinq.xsm.ui.adapter.XsmSearchAdapter;


/**
 * Created by dingyi on 2016/11/22.
 */

public interface IXsmSearchView extends IBaseView{
    void updateHistoryList(ArrayAdapter<String> adapter);
    void updateSearchList(ArrayAdapter<String> adapter);
    void updateResultList(XsmSearchAdapter adapter);

}
