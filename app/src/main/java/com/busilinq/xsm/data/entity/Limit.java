package com.busilinq.xsm.data.entity;

import java.io.Serializable;

/**
 * Created by dingyi on 2017/2/17.
 */

public class Limit implements Serializable {

    private static final long serialVersionUID = 7578434480283798324L;
    /**
     * coQtyLmt : 240
     * monQtyOrd : 10
     * monQtyLmt : 800
     */

    private String coQtyLmt;
    private String monQtyOrd;
    private String monQtyLmt;

    public void setCoQtyLmt(String coQtyLmt) {
        this.coQtyLmt = coQtyLmt;
    }

    public void setMonQtyOrd(String monQtyOrd) {
        this.monQtyOrd = monQtyOrd;
    }

    public void setMonQtyLmt(String monQtyLmt) {
        this.monQtyLmt = monQtyLmt;
    }

    public String getCoQtyLmt() {
        return coQtyLmt;
    }

    public String getMonQtyOrd() {
        return monQtyOrd;
    }

    public String getMonQtyLmt() {
        return monQtyLmt;
    }
}
