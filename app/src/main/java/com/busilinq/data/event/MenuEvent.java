package com.busilinq.data.event;


/**
 * Company：华科建邺
 * Class Describe：选择商品 + - 刷新主界面 Event
 * Create Person：Chenyx
 * Create Time：2017/11/13 上午10:07
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MenuEvent {
    /**
     * 菜单索引值
     */
    private int index;

    public MenuEvent() {
        super();
    }

    public MenuEvent(int index) {
        super();
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
