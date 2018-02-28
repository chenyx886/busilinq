package com.busilinq.data.entity;


/**
 * Company：华科建邺
 * Class Describe： 信息公告
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class InfoNoticeEntity extends BaseEntity {
    private int afficheId;
    private String time;
    private String title;
    private String url;

    public int getAfficheId() {
        return afficheId;
    }

    public void setAfficheId(int afficheId) {
        this.afficheId = afficheId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
