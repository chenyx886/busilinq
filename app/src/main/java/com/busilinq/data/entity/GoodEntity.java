package com.busilinq.data.entity;


import com.chenyx.libs.utils.AmountUtils;

/**
 * Company：华科建邺
 * Class Describe： 商品实体
 * Create Person：Chenyx
 * Create Time：2017/11/13 下午2:09
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class GoodEntity extends BaseEntity {

    private int vo_countGoodsPrice;
    private String vo_shoppingCartNum;
    /**
     * 零售价，单位是分
     */
    private String vo_retailPrice;
    /**
     * 商品的价格编号
     */
    private String vo_priceId;
    private String vo_unitName;
    /**
     * 商品id
     */
    private int goodsId;
    private int categoryId;
    private String goodsName;
    private String nickName;
    private String image;
    private int delState;
    private String simpleDescribe;
    private String detailDescribe;
    private int isMarketable;
    private int recommend;
    private String createTime;
    private String updateTime;
    private int adminId;

    /**
     * 商品选择 数量
     */
    private int num;
    /**
     * 商品分类
     */
    private CateEntity cate;

    public float getPrice() {
        try {
            return AmountUtils.changeF2Y(vo_retailPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVo_countGoodsPrice() {
        return vo_countGoodsPrice;
    }

    public void setVo_countGoodsPrice(int vo_countGoodsPrice) {
        this.vo_countGoodsPrice = vo_countGoodsPrice;
    }

    public String getVo_shoppingCartNum() {
        return vo_shoppingCartNum;
    }

    public void setVo_shoppingCartNum(String vo_shoppingCartNum) {
        this.vo_shoppingCartNum = vo_shoppingCartNum;
    }

    public String getVo_retailPrice() {
        return vo_retailPrice;
    }

    public void setVo_retailPrice(String vo_retailPrice) {
        this.vo_retailPrice = vo_retailPrice;
    }

    public String getVo_priceId() {
        return vo_priceId;
    }

    public void setVo_priceId(String vo_priceId) {
        this.vo_priceId = vo_priceId;
    }

    public String getVo_unitName() {
        return vo_unitName;
    }

    public void setVo_unitName(String vo_unitName) {
        this.vo_unitName = vo_unitName;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getDelState() {
        return delState;
    }

    public void setDelState(int delState) {
        this.delState = delState;
    }

    public String getSimpleDescribe() {
        return simpleDescribe;
    }

    public void setSimpleDescribe(String simpleDescribe) {
        this.simpleDescribe = simpleDescribe;
    }

    public String getDetailDescribe() {
        return detailDescribe;
    }

    public void setDetailDescribe(String detailDescribe) {
        this.detailDescribe = detailDescribe;
    }

    public int getIsMarketable() {
        return isMarketable;
    }

    public void setIsMarketable(int isMarketable) {
        this.isMarketable = isMarketable;
    }

    public int getRecommend() {
        return recommend;
    }

    public void setRecommend(int recommend) {
        this.recommend = recommend;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public CateEntity getCate() {
        return cate;
    }

    public void setCate(CateEntity cate) {
        this.cate = cate;
    }
}
