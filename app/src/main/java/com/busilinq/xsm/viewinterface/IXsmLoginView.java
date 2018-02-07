package com.busilinq.xsm.viewinterface;

/**
 * Created by dingyi on 2017/2/28.
 */

public interface IXsmLoginView extends IBaseView {
    void updateProvinceView(String province);

    void updateCompView(String comp);

    void updateAccoutView(String name);

    void updateAccoutsIv(boolean isEnabled);

}
