package com.busilinq.xsm.data.entity;


/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/13 上午10:07
 * Update Person：
 * Update Time：
 * Update Remark：
 */

public class AppUpdateInfo {


//    "code": "android",
//            "url": "http://busilinq.com:18081/hkbaccypospay/baccytest.apk",
//            "desc": "修正界面跳转Crash问题",
//            "version": "1",
//            "newVersion": "2"


    private String code;
    private String url;
    private String desc;
    private String version;
    private String newVersion;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    @Override
    public String toString() {
        return "AppUpdateInfo{" +
                "code='" + code + '\'' +
                ", url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                ", version='" + version + '\'' +
                ", newVersion='" + newVersion + '\'' +
                '}';
    }
}
