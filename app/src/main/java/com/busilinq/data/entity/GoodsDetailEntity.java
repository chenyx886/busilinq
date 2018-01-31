package com.busilinq.data.entity;


/**
 * Company：华科建邺
 * Class Describe： 公共商品详细实体
 * Create Person：Chenyx
 * Create Time：2017/11/13 下午2:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsDetailEntity extends BaseEntity {
    //
    private int detailId;
    //
    private int goodsId;
    //
    private int warehouseId;
    //
    private String vender;
    //
    private String label;
    //
    private String keyword;
    //
    private String graphicDescription;

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getVender() {
        return vender;
    }

    public void setVender(String vender) {
        this.vender = vender;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getGraphicDescription() {
        return graphicDescription;
    }

    public void setGraphicDescription(String graphicDescription) {
        this.graphicDescription = graphicDescription;
    }
}
