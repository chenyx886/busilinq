package com.busilinq.data.cache;


import com.busilinq.data.entity.MainCartEntity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Company：华科建邺
 * Class Describe：选择商品时 缓存
 * Create Person：Chenyx
 * Create Time：2017/11/12 下午11:26
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class AssembleProduct {

    List<MainCartEntity> goods = new ArrayList<>();

    private static AssembleProduct sSingleton = null;

    public static synchronized AssembleProduct getInstance() {
        if (sSingleton == null) {
            sSingleton = new AssembleProduct();
        }
        return sSingleton;
    }


    /**
     * 删除单个商品
     *
     * @param CartId
     */
    public void removeSingleProduct(int CartId) {
        if (CartId<=0) {
            return;
        }
        if(goods.size()>0) {
            for (MainCartEntity good : goods) {
                if (good.getCart().getCartId() == CartId) {

                    goods.remove(good);
                }
            }
        }
    }

    /**
     * 商品加减
     *
     * @param item
     */
    public void increase(MainCartEntity item) {
        if (item == null&&item.getCart().getCartId()<=0) {
            return;
        }
        for (MainCartEntity good : goods) {
            if (good.getCart().getCartId() == item.getCart().getCartId()) {
                good.getCart().setNumber(item.getCart().getNumber());
                return;
            }
        }
        item.getCart().setNumber(item.getCart().getNumber());
        goods.add(item);
    }

    /**
     * 商品减
     *
     * @param item
     */
    public void decrease(MainCartEntity item) {
        if (item == null&&item.getCart().getCartId()<=0) {
            return;
        }

        // 解决 异常 ：java.util.ConcurrentModificationException
        for (Iterator<MainCartEntity> it = goods.iterator(); it.hasNext(); ) {
            MainCartEntity ge = it.next();
            if (ge.getCart().getCartId() == item.getCart().getCartId()) {
                    ge.getCart().setNumber(item.getCart().getNumber());

            }
        }

    }

    public void clear() {
        goods.clear();
    }

    /**
     * 返回选好总条数
     *
     * @return
     */
    public Integer getListSize() {
        return goods.size();
    }

    /**
     * 返回选好数量
     *
     * @return
     */
    public Integer getSubNum() {
        int tmp = 0;
        for (MainCartEntity ge : goods) {
            tmp += ge.getCart().getNumber();
        }
        return tmp;
    }

    /**
     * 返回选好总价
     *
     * @return
     */
    public float getSubPrice() {
        float tmp = 0.0f;
        for (MainCartEntity ge : goods) {
            tmp += (ge.getGoods().getGoods().getPrice() * ge.getCart().getNumber());
        }

        float scale = tmp;
        DecimalFormat fnum = new DecimalFormat("##0.00");
        float price = Float.parseFloat(fnum.format(scale));

        return price;
    }




    public List<MainCartEntity> getGoods() {
        return goods;
    }

    public void setGoods(List<MainCartEntity> goods) {
        this.goods = goods;
    }
}
