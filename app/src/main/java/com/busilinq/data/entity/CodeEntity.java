package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：验证码
 * Create Person：Chenyx
 * Create Time：2018/2/1 上午11:14
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CodeEntity extends BaseEntity {
    //验证码
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
