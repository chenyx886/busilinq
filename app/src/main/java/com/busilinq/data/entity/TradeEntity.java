package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：行业公共信息
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:24
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class TradeEntity extends BaseEntity{

    //
    private int tradeId;
    //名称
    private String name;
    //类型
    private String type;
    //备注
    private String remark;

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
