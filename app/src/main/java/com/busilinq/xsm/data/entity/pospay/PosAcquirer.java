package com.busilinq.xsm.data.entity.pospay;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/9/5.
 */

public class PosAcquirer implements Serializable {

    /**
     * merchantId : 898520158140765
     * merchantName : 毕节邮储
     * posId : 01183778
     * sn : P820119383
     */

    private String merchantId;
    private String merchantName;
    private String posId;
    private String sn;

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public String getPosId() {
        return posId;
    }

    public String getSn() {
        return sn;
    }
}
