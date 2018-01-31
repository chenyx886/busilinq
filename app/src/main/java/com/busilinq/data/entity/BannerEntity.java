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
    private int advId;
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
    private String advType;
    /**
     * HOME 首页
     */
    private int showType;

    public int getAdvId() {
        return advId;
    }

    public void setAdvId(int advId) {
        this.advId = advId;
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

    public String getAdvType() {
        return advType;
    }

    public void setAdvType(String advType) {
        this.advType = advType;
    }

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }
}
