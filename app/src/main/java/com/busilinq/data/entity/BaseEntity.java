package com.busilinq.data.entity;

import com.busilinq.data.BaseResp;

import java.io.Serializable;
import java.util.Date;

/**
 * Company：华科建邺
 * Class Describe：实体基类
 * Create Person：Chenyx
 * Create Time：2018/01/29 上午11:39
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class BaseEntity extends BaseResp implements Serializable {
    //删除标志 -1删除 1未删除
    private int isDel;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public int getIsDel() {
        return isDel;
    }

    public void setIsDel(int isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
