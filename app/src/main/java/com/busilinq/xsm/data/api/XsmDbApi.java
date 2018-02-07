package com.busilinq.xsm.data.api;


import com.busilinq.xsm.data.entity.Cart;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.data.entity.Limit;
import com.busilinq.xsm.data.entity.Merchant;
import com.busilinq.xsm.data.entity.XsmAccount;
import com.busilinq.xsm.data.entity.XsmRandom;
import com.busilinq.xsm.ulits.ACache;

import java.util.HashMap;
import java.util.Random;

/**
 * 统一管理新商盟需要存储的数据
 * Created by dingyi on 2016/11/21.
 */

public class XsmDbApi {
    private ACache mACache;
    public XsmDbApi(ACache aCache) {
        this.mACache = aCache;
    }

    public void saveMerchant(Merchant merchant){
        mACache.put(Merchant.class.getSimpleName(), merchant);
    }

    public void saveXsmAccount(XsmAccount account){
        mACache.put(XsmAccount.class.getSimpleName(),account);
    }

    public void saveXsmAccounts(HashMap<String,XsmAccount> accounts){
        mACache.put(XsmAccount.class.getName(),accounts);
    }

    public void saveLimit(Limit limit){
        mACache.put(Limit.class.getSimpleName(),limit);
    }

    public void saveCigarettes(HashMap<String,Cigarette> cigarettes){
        mACache.put(Cigarette.class.getSimpleName(),cigarettes);
    }

    public void saveCarts(HashMap<String,Cart> carts){
        mACache.put(Cart.class.getSimpleName(),carts);
    }

    public Merchant getMerchant(){
        Merchant merchant = (Merchant)mACache.getAsObject(Merchant.class.getSimpleName());
        return merchant;
    }

    public XsmAccount getXsmAccount(){
        return (XsmAccount)mACache.getAsObject(XsmAccount.class.getSimpleName());
    }

    public HashMap<String,XsmAccount> getXsmAccounts(){
        return (HashMap<String,XsmAccount>)mACache.getAsObject(XsmAccount.class.getName());
    }

    public Limit getLimit(){
        return (Limit)mACache.getAsObject(Limit.class.getSimpleName());
    }

    public HashMap<String,Cigarette> getCigarettes(){
        return (HashMap<String,Cigarette>)mACache.getAsObject(Cigarette.class.getSimpleName());
    }

    public HashMap<String,Cart> getCarts(){
        return (HashMap<String,Cart>)mACache.getAsObject(Cart.class.getSimpleName());
    }

    public void clear(String key){
        mACache.remove(key);
    }

    public void clearOrdering(){
        mACache.remove(Limit.class.getSimpleName());
        mACache.remove(Cigarette.class.getSimpleName());
        mACache.remove(Cart.class.getSimpleName());
    }

    public void clearRemoteAll(){
        mACache.remove(Merchant.class.getSimpleName());
    }

    public void clearAll(){
        mACache.remove(Merchant.class.getSimpleName());
        mACache.remove(Limit.class.getSimpleName());
        mACache.remove(Cigarette.class.getSimpleName());
        mACache.remove(Cart.class.getSimpleName());
        mACache.remove(XsmAccount.class.getSimpleName());
        mACache.remove(XsmAccount.class.getName());
    }
    //二维码扫描登录时保存随机数
    public void saveRandom(XsmRandom random){
        mACache.put(Random.class.getSimpleName(),random);
    }
    public XsmRandom getRandom(){
        return (XsmRandom)mACache.getAsObject(XsmRandom.class.getSimpleName());
    }
}

