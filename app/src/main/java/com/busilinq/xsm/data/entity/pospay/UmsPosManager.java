package com.busilinq.xsm.data.entity.pospay;

/**
 * Created by dingyi on 2017/9/5.
 */

public class UmsPosManager {
    private int imageId;
    private String title;
    private String code;

    public UmsPosManager(int imageId, String title, String code) {
        this.imageId = imageId;
        this.title = title;
        this.code = code;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
