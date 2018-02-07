package com.busilinq.data.entity;

import com.busilinq.data.BaseResp;

/**
 * Company：华科建邺
 * Class Describe：实体基类
 * Create Person：Chenyx
 * Create Time：2018/01/29 上午11:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class BaseEntity extends BaseResp {
    //删除标志 -1删除 1未删除
    private String isDel;
    //创建时间
    private String createTime;
    //更新时间
    private String updateTime;

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
