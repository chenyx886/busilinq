package com.busilinq.xsm.data.usercenter;

import java.io.Serializable;

/**
 * Created by dingyi on 2016/12/4.
 */

public class EquipmentEntity implements Serializable {

    private static final long serialVersionUID = 266919117255969848L;
    private String equipOS;
    private String equipNo;
    private String cell;
    private String jpushCode;
    private String memberId;

    public String getEquipOS() {
        return equipOS;
    }

    public void setEquipOS(String equipOS) {
        this.equipOS = equipOS;
    }

    public String getEquipNo() {
        return equipNo;
    }

    public void setEquipNo(String equipNo) {
        this.equipNo = equipNo;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getJpushCode() {
        return jpushCode;
    }

    public void setJpushCode(String jpushCode) {
        this.jpushCode = jpushCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "EquipmentEntity{" +
                "equipOS='" + equipOS + '\'' +
                ", equipNo='" + equipNo + '\'' +
                ", cell='" + cell + '\'' +
                ", jpushCode='" + jpushCode + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
