package com.busilinq.xsm.presenter;


import com.busilinq.MApplication;
import com.busilinq.xsm.data.api.XsmApi;
import com.busilinq.xsm.data.api.XsmDbApi;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.viewinterface.IBaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dingyi on 2017/2/25.
 */

public abstract class XsmBasePresenter<T extends IBaseView> extends ABasePresenter<T> {

    protected XsmApi mXsmApi;
    protected XsmDbApi mXsmDbApi;

    @Override
    public void attachView(T view) {
        super.attachView(view);
        this.mXsmApi = XsmApi.getInstance(MApplication.context());
        this.mXsmDbApi = new XsmDbApi(ACache.get(MApplication.context()));
    }

    protected HashMap<String,Cigarette> getCgtMap (List<Cigarette> cigarettes){
        HashMap<String, Cigarette> map = new HashMap<>(cigarettes.size());
        for (Cigarette cigarette : cigarettes) {
            map.put(cigarette.getCgtCode(), cigarette);
        }
        return map;
    }

    protected List<Cigarette> getCgtList(HashMap<String,Cigarette> map) {

        List<Cigarette> cigarettes = new ArrayList<>();
        Iterator<Map.Entry<String, Cigarette>> it =  map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Cigarette> entry = it.next();
            Cigarette cigarette = entry.getValue();
            cigarettes.add(cigarette);
        }
        return cigarettes;
    }
}
