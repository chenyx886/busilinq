package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：yuzhenxing
 * Create Time：2018/2/1 15:31
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class TUpgradeEntity extends BaseEntity {
    /**
     * id
     */
    private int upgradeId;

    /**
     * 更新版本号
     */
    private String version;
    /**
     * 更新版本名称
     */
    private String versionName;
    /**
     * 更新目标 0用户端APP 1卖家端APP
     */
    private int target;
    /**
     * 下载地址
     */
    private String url;
    /**
     * 文件MD5
     */
    private String md5;
    /**
     * 更新类型 0立即更新 1闲时更新、2强制更新
     */
    private int updateType;
    /**
     * 备注
     */
    private String remark;


    /**
     * 设置：
     */
    public void setUpgradeId(int upgradeId) {
        this.upgradeId = upgradeId;
    }

    /**
     * 获取：
     */
    public int getUpgradeId() {
        return upgradeId;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * 设置：更新版本号
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * 获取：更新版本号
     */
    public String getVersion() {
        return version;
    }


    /**
     * 设置：更新目标 0用户端APP 1卖家端APP
     */
    public void setTarget(int target) {
        this.target = target;
    }

    /**
     * 获取：更新目标 0用户端APP 1卖家端APP
     */
    public int getTarget() {
        return target;
    }


    /**
     * 设置：下载地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：下载地址
     */
    public String getUrl() {
        return url;
    }


    /**
     * 设置：文件MD5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * 获取：文件MD5
     */
    public String getMd5() {
        return md5;
    }


    /**
     * 设置：更新类型 0立即更新 1闲时更新、2强制更新
     */
    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    /**
     * 获取：更新类型 0立即更新 1闲时更新、2强制更新
     */
    public int getUpdateType() {
        return updateType;
    }


    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }


}
