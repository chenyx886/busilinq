package com.busilinq.xsm.data.usercenter;



import com.busilinq.xsm.ulits.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 新商盟 用户实体表
 */
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 6189224288823022630L;
    private String area;
    private String address;
    private String headImgUrl;
    private String accessTime;
    private String userName;
    private String remark;
    private String email;
    private String cell;
    private String sysAct;
    private String gender;
    private String realName;
    private String memberId;
    private String password;
    private String fixedQR;
    private List<ServiceEntity> serviceList;
    private List<RuleEntity> ruleList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public String getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(String accessTime) {
        this.accessTime = accessTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFixedQR() {
        return fixedQR;
    }

    public void setFixedQR(String fixedQR) {
        this.fixedQR = fixedQR;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getSysAct() {
        return sysAct;
    }

    public void setSysAct(String sysAct) {
        this.sysAct = sysAct;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ServiceEntity> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceEntity> serviceList) {
        this.serviceList = serviceList;
    }

    public List<RuleEntity> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<RuleEntity> ruleList) {
        this.ruleList = ruleList;
    }

    /**
     * 根据Code指定值 获取URL
     *
     * @param code
     * @return
     */
    public String getService(String code) {
        String serviceUrl = null;
        ServiceEntity service = null;
        if (null == serviceList || 0 == serviceList.size())
            return serviceUrl;
        for (ServiceEntity entity : serviceList) {
            if (entity.getServiceId().equals(code)) {
                service = entity;
                break;
            }
        }

        if (null != service) {
            serviceUrl = service.getService();
            if (!StringUtils.isUrl(serviceUrl))
                serviceUrl = null;
        }
        return serviceUrl;
    }

    /**
     * 根据Code指定值 获取Permission 0:显示 1:不显示
     *
     * @param code
     * @return
     */
    public int getServicePermission(String code) {
        int servicePermission = 1;
        ServiceEntity service = null;
        if (null == serviceList || 0 == serviceList.size())
            return servicePermission;
        for (ServiceEntity entity : serviceList) {
            if (entity.getServiceId().equals(code)) {
                service = entity;
                break;
            }
        }
        if (null != service) {
            servicePermission = service.getPermission();
        }
        return servicePermission;
    }
}
