package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：广告实体
 * Create Person：Chenyx
 * Create Time：2018/1/29 下午3:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class BannerEntity extends BaseEntity {


    /**
     * 广告id
     */
    private int id;
    /**
     * 图片地址
     */
    private String imageUrl;
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 外部连接
     */
    private String href;
    /**
     * 类型：0：纯图片展示 1:商品详情 2:外部连接
     */
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
