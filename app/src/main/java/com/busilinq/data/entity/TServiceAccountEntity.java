package com.busilinq.data.entity;

/**
 * 其他服务地址表-临时
 *
 * @author denghshun.yin
 * @email 237837717@qq.com
 * @date 2018-02-07 11:21:34
 */
public class TServiceAccountEntity extends BaseEntity {

    private int accountId;
    //服务名称
    private String name;
    //服务地址
    private String url;
    //是否启用
    private int isEnable;
    //备注信息
    private String remark;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
