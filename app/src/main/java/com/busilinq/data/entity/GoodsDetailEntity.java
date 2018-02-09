package com.busilinq.data.entity;


import java.io.Serializable;
import java.util.List;

/**
 * Company：华科建邺
 * Class Describe： 公共商品详细实体
 * Create Person：Chenyx
 * Create Time：2017/11/13 下午2:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodsDetailEntity extends BaseEntity implements Serializable {

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

    //商品轮播
    private List<GoodsImgEntity> banner;
    //商品图片详情
    private List<GoodsImgEntity> image;
    //商品基本参数
    private HomeGoodsEntity goods;

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

    public List<GoodsImgEntity> getBanner() {
        return banner;
    }

    public void setBanner(List<GoodsImgEntity> banner) {
        this.banner = banner;
    }

    public List<GoodsImgEntity> getImage() {
        return image;
    }

    public void setImage(List<GoodsImgEntity> image) {
        this.image = image;
    }

    public HomeGoodsEntity getGoods() {
        return goods;
    }

    public void setGoods(HomeGoodsEntity goods) {
        this.goods = goods;
    }
}
