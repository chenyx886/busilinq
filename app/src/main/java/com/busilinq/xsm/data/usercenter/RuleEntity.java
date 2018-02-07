package com.busilinq.xsm.data.usercenter;

import java.io.Serializable;

/**
 *  text : 0
 * desc : 更新操作
 * ruleId : 5
 * key : app_updated
 *
 * Created by dingyi on 2016/11/30.
 */

public class RuleEntity implements Serializable {

    private static final long serialVersionUID = -9194152408355412064L;
    private String text;
    private String desc;
    private int ruleId;
    private String key;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
