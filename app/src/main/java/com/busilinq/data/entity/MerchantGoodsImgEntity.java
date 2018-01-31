package com.busilinq.data.entity;

/**
 * Company：华科建邺
 * Class Describe：商户商品图片资源
 * Create Person：Chenyx
 * Create Time：2018/1/31 上午11:28
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class MerchantGoodsImgEntity extends BaseEntity {
    //
    private int imgId;
    //删除状态：1.启用 -1.未启用 （后台未启用页面不显示，前台显示已下架）
    //创建时间
    //更新时间
    //商品id
    private int goodsId;
    //图片路径
    private String image;
    //图片类型  商品详情  商品缩略图  展示位置
    private int type;

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
