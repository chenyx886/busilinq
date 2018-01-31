package com.busilinq.data.entity;

import java.util.Date;

/**
 * Company：华科建邺
 * Class Describe：资源文件上传文件
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:31
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class FileEntity extends BaseEntity{
    //
    private int fileId;
    //删除标志 -1删除 1未删除
    //
    //
    //文件大小
    private int fileSize;
    //后缀名
    private String suffix;
    //上传时间
    private Date uploadTime;
    //存储类型
    private int fileType;
    //文件保存本地路径
    private String localUrl;
    //文件访问路径
    private String remoteUrl;

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }
}
