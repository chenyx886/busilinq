package com.busilinq.ui.cart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.MainCartEntity;
import com.busilinq.ui.MainActivity;
import com.chenyx.libs.utils.JumpUtil;
import com.chenyx.libs.utils.ToastUtils;

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
    private DataUpdateListener mDataUpdateListener;
    private int checkNumber = 0;

    public int getCheckNumber() {
        return checkNumber;
    }

    public void delectItem(int position) {
        if (items.get(position).getCart().getIsChecked() == 1)
            checkNumber--;
        items.remove(position);
        this.notifyDataSetChanged();
    }

    public void setAllCheck(boolean isCheck) {
        if (isCheck) {
            if (checkNumber == 0)
                setCheck(1);
            else {
                if (checkNumber == getItems().size())
                    setCheck(0);
                else
                    setCheck(1);
            }
        } else {
            setCheck(0);
        }
    }

    private void setCheck(int check) {
        for (MainCartEntity entity : items) {
            entity.getCart().setIsChecked(check);
        }
        this.notifyDataSetChanged();
    }

    public void setEntityData(int position, MainCartEntity entity) {
        items.get(position).setCart(entity.getCart());
        this.notifyDataSetChanged();
    }

    public CartAdapter(Context context, DataUpdateListener mDataUpdateListener) {
        super(context);
        this.mDataUpdateListener = mDataUpdateListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_cart_list, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof ViewHolder) {
            final ViewHolder vHolder = (ViewHolder) holder;
            if (getItem(position) != null) {
                final MainCartEntity item = getItem(position);
                vHolder.mTitle.setText(item.getGoods().getGoods().getName());//商品名称
                vHolder.mNum.setText(item.getCart().getNumber() + "");//数量
                vHolder.mPrice.setText("¥" + item.getGoods().getPrice().getSalesPrice() + "/" + item.getGoods().getGoods().getUnit());//价格：¥58.5/盒
                showImageView(item.getGoods().getGoods().getImage(), vHolder.mItemPic, R.mipmap.default_error);

                vHolder.mItemPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v, position);
                    }
                });
                vHolder.mAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int number = item.getCart().getNumber() + 1;
                        mDataUpdateListener.update(position, number);
                    }
                });
                vHolder.mReduce.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (item.getCart().getNumber() == 1) {
                            ToastUtils.showShort("数量为最小值，可长按删除本条记录");
                            return;
                        }
                        int number = item.getCart().getNumber() - 1;
                        mDataUpdateListener.update(position, number);
                    }
                });
                vHolder.mIsCheck.setChecked(item.getCart().getIsChecked() == 0 ? false : true);
                vHolder.mIsCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            item.getCart().setIsChecked(1);
                            checkNumber++;
                        } else {
                            item.getCart().setIsChecked(0);
                            checkNumber--;
                        }
                        mDataUpdateListener.updateCheck();
                    }
                });
            }
        } else {
            final EmptyViewHolder vHolder = (EmptyViewHolder) holder;
            vHolder.img.setBackgroundResource(R.mipmap.empty_cart);
            vHolder.msgTv.setText(mContext.getResources().getText(R.string.cart_empty));
            vHolder.mPurchase.setVisibility(View.VISIBLE);
            vHolder.mPurchase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    JumpUtil.overlay(mContext, MainActivity.class);
                }
            });
        }
    }

    public interface DataUpdateListener {
        void update(int position, int number);

        void updateCheck();
    }

    /**
     * 无数据时 显示
     */
    class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_empty)
        ImageView img;
        @BindView(R.id.empty_msg_tv)
        TextView msgTv;
        @BindView(R.id.btn_purchase)
        Button mPurchase;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
