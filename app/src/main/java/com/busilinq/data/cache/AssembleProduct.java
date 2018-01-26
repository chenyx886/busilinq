package com.busilinq.data.cache;

import com.busilinq.data.entity.GoodEntity;

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

    List<GoodEntity> goods = new ArrayList<>();

    private static AssembleProduct sSingleton = null;

    public static synchronized AssembleProduct getInstance() {
        if (sSingleton == null) {
            sSingleton = new AssembleProduct();
        }
        return sSingleton;
    }

    /**
     * 添加单个商品
     *
     * @param item
     */
    public void addSingleProduct(GoodEntity item) {
        if (item == null) {
            return;
        }

        goods.add(item);
    }

    /**
     * 删除单个商品
     *
     * @param item
     */
    public void removeSingleProduct(GoodEntity item) {
        if (item == null) {
            return;
        }

        for (GoodEntity good : goods) {
            if (good == item) {
                goods.remove(item);
            }
        }
    }

    /**
     * 商品加1
     *
     * @param item
     */
    public void increase(GoodEntity item) {
        if (item == null) {
            return;
        }
        for (GoodEntity good : goods) {
            if (good.getGoodsId() == item.getGoodsId()) {
                // 商品数量 +1
                int num = good.getNum() + 1;
                good.setNum(num);
                return;
            }
        }
        item.setNum(1);
        goods.add(item);
    }

    /**
     * 商品减1
     *
     * @param item
     */
    public void decrease(GoodEntity item) {
        if (item == null) {
            return;
        }

        // 解决 异常 ：java.util.ConcurrentModificationException
        for (Iterator<GoodEntity> it = goods.iterator(); it.hasNext(); ) {
            GoodEntity ge = it.next();
            if (ge.getGoodsId() == item.getGoodsId()) {
                if (ge.getNum() == 1) {
                    // 如果 数量为1 则移除商品
                    // goods.remove(item);
                    it.remove();
                } else {
                    // 商品数量 -1
                    int num = ge.getNum() - 1;
                    ge.setNum(num);
                }
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
        for (GoodEntity ge : goods) {
            tmp += ge.getNum();
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
        for (GoodEntity ge : goods) {
            tmp += (ge.getPrice() * ge.getNum());
        }

        float scale = tmp;
        DecimalFormat fnum = new DecimalFormat("##0.00");
        float price = Float.parseFloat(fnum.format(scale));

        return price;
    }

    /**
     * 返回选好对应分类 商品个数
     *
     * @return
     */
    public int getCateCount(int cateid) {
        int tmp = 0;
        for (Iterator<GoodEntity> it = goods.iterator(); it.hasNext(); ) {
            GoodEntity ge = it.next();
            if (ge.getCate().getId() == cateid) {
                tmp += ge.getNum();
            }
        }
        return tmp;
    }

    /**
     * 商品id获取商品数量
     *
     * @return
     */
    public int getGoodsCount(int goodsid) {
        int tmp = 0;
        for (Iterator<GoodEntity> it = goods.iterator(); it.hasNext(); ) {
            GoodEntity ge = it.next();
            if (ge.getGoodsId() == goodsid) {
                tmp += ge.getNum();
                break;
            }
        }
        return tmp;
    }

    public List<GoodEntity> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodEntity> goods) {
        this.goods = goods;
    }
}
