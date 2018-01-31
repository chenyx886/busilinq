package com.busilinq.data;

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
public class PageEntity<T> extends BaseResp {

    /**
     * 当前页
     */
    public int page;
    /**
     * 每页条数
     */
    public int limit;
    /**
     * 数据列表
     */
    public List<T> list = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
