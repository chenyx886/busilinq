package com.busilinq.ui.cart.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.cache.AssembleProduct;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.ui.classify.GoodsDetailActivity;
import com.chenyx.libs.glide.GlideShowImageUtils;
import com.chenyx.libs.utils.JumpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Company：华科建邺
 * Class Describe：购物车适配器
 * Create Person：wenxin.li
 * Create Time：2018/1/31 13:35
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class CartAdapter extends AbstractRecyclerViewAdapter<MainCartEntity> {

    public interface MyInterface{
        void getCarInfo();
    }
    private MyInterface myInterface;
    public CartAdapter(Context context,MyInterface myInterface) {
        super(context);
        this.myInterface = myInterface;
        AssembleProduct.getInstance().clear();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //数据列表
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_list, parent, false);
        return new CartAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);

        final CartAdapter.ViewHolder vHolder = (CartAdapter.ViewHolder) holder;
        if (getItem(position) != null) {
            final MainCartEntity item =   getItem(position);
            vHolder.mTitle.setText(item.getGoods().getGoods().getName());//商品名称
            vHolder.mNum.setText(item.getCart().getNumber()+"");//数量
            vHolder.mPrice.setText("¥"+item.getGoods().getGoods().getPrice()+"/"+item.getGoods().getGoods().getUnit());//价格：¥58.5/盒
            GlideShowImageUtils.displayNetImage(mContext, item.getGoods().getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);

            if(item.getCart().getIsChecked() == 0){
                vHolder.mIsCheck.setChecked(false);
            }else {
                vHolder.mIsCheck.setChecked(true);
            }
//            /vHolder.mIsCheck.setChecked(false);

//            if (AssembleProduct.getInstance().getGoods() != null && AssembleProduct.getInstance().getGoods().size() > 0) {
//                for (MainCartEntity gc : AssembleProduct.getInstance().getGoods()) {
//                    if (gc.getCart().getCartId() == item.getCart().getCartId()) {
//                        vHolder.mIsCheck.setChecked(true);
//                    }
//                }
//            }


            /**
             * 点击跳转到详情
             */
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("goodsId", item.getGoods().getGoods().getGoodsId());
                    JumpUtil.overlay(mContext, GoodsDetailActivity.class, bundle);
                }
            });
            /**
             * 点击加号
             */
            vHolder.mAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(view,position);
                    if(vHolder.mIsCheck.isChecked()){
                        item.getCart().setIsChecked(1);
                    }else{
                        item.getCart().setIsChecked(0);
                    }
                }
            });
            /**
             * 点击减号
             */
            vHolder.mReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemViewClickListener.onViewClick(view,position);
                    if(vHolder.mIsCheck.isChecked()){
                        item.getCart().setIsChecked(1);
                    }else{
                        item.getCart().setIsChecked(0);
                    }
                }
            });
            /**
             * 选中与取消事件
             * getCartInfo实现回调，提供数据给fragment
             */
            vHolder.mIsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b){
                        item.getCart().setIsChecked(1);
                        //AssembleProduct.getInstance().increase(item.getCart());//加+
                        AssembleProduct.getInstance().addSingleProduct(item.getCart());
                    }else {
                        item.getCart().setIsChecked(0);
                        AssembleProduct.getInstance().removeSingleProduct(item.getCart());//减
                    }
                    if(myInterface != null){
                        myInterface.getCarInfo();
                    }
                }
            });
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * 是否选中
         */
        @BindView(R.id.check_select)
        CheckBox mIsCheck;
        /**
         * 图片
         */
        @BindView(R.id.img_item_pic)
        ImageView mItemPic;
        /**
         * 标题
         */
        @BindView(R.id.tv_title)
        TextView mTitle;
        /**
         * 价格：格式为：¥59.9/盒
         */
        @BindView(R.id.tv_price)
        TextView mPrice;
        /**
         * 增加
         */
        @BindView(R.id.img_add)
        ImageView mAdd;
        /**
         * 减少
         */
        @BindView(R.id.img_reduce)
        ImageView mReduce;
        /**
         * 数量
         */
        @BindView(R.id.tv_num)
        TextView mNum;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
