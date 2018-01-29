package com.busilinq.data;

import com.busilinq.data.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：分页实体
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class PageEntity<T> extends BaseEntity {

    /**
     * 总条
     */
    public int total;
    /**
     * 总页
     */
    public int pages;
    /**
     * 当前页
     */
    public int pageNo;
    /**
     * 页大小
     */
    public int pageSize;
    /**
     * 数据列表
     */
    public List<T> dataList = new ArrayList<>();

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
