package com.busilinq.data.event;


import com.busilinq.data.entity.GoodEntity;

/**
 * Company：华科建邺
 * Class Describe：选择商品 + - 刷新主界面 Event
 * Create Person：Chenyx
 * Create Time：2017/11/13 上午10:07
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SelGoodEvent {
    /**
     * 类型
     */
    private int type;
    private int[] start_location;
    private GoodEntity mGoodEntity;

    public SelGoodEvent() {
        super();
    }

    public SelGoodEvent(int type, int[] start_location,GoodEntity goodEntity) {
        super();
        this.type = type;
        this.mGoodEntity = goodEntity;
        this.start_location = start_location;
    }

    public int getType() {
        return type;
    }

    public int[] getStart_location() {
        return start_location;
    }

    public GoodEntity getGoodEntity() {
        return mGoodEntity;
    }
}
