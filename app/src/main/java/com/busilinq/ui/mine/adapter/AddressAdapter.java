package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.UserShopAddrEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：wangshimei
 * Create Time：18/2/1 上午9:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class AddressAdapter extends AbstractRecyclerViewAdapter<UserShopAddrEntity> {
    private List<UserShopAddrEntity> dataList;

    public AddressAdapter(Context context) {
        super(context);
    }

    /**
     * 添加数据
     *
     * @param dataList
     */
    public void setData(List<UserShopAddrEntity> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataList != null && dataList.size() > 0 ? dataList.size() : 0;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_address_layout, parent, false);
        return new ViewHolder(view, mOnclickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            ViewHolder vHolder = ((ViewHolder) holder);
            UserShopAddrEntity entity = dataList.get(position);
            vHolder.consignee_tv.setText(entity.getName());
            vHolder.address_tell_tv.setText(entity.getCell());
            vHolder.address_unit_tv.setText(entity.getCompany());
            vHolder.detail_address_tv.setText(entity.getSpecificAddr());
            if (entity.getIsDefault() != null) {
                if (entity.getIsDefault().equals("1")) { // 默认地址
                    vHolder.selected_iv.setChecked(true);
                } else {
                    vHolder.selected_iv.setChecked(false);
                }
            } else {
                vHolder.selected_iv.setChecked(false);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * 收货人
         */
        @BindView(R.id.consignee_tv)
        TextView consignee_tv;

        /**
         * 电话
         */
        @BindView(R.id.address_tell_tv)
        TextView address_tell_tv;

        /**
         * 收货单位
         */
        @BindView(R.id.address_unit_tv)
        TextView address_unit_tv;

        /**
         * 收货地址
         */
        @BindView(R.id.detail_address_tv)
        TextView detail_address_tv;

        /**
         * 设置默认地址按钮
         */
        @BindView(R.id.selected_iv)
        CheckBox selected_iv;

        /**
         * 删除地址
         */
        @BindView(R.id.delete_tv)
        TextView delete_tv;

        /**
         * 编辑地址
         */
        @BindView(R.id.edit_tv)
        TextView edit_tv;

        ButtonOnClickListener onClickListener;


        public ViewHolder(View itemView, ButtonOnClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            onClickListener = listener;
            selected_iv.setOnClickListener(this);
            delete_tv.setOnClickListener(this);
            edit_tv.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int key = view.getId();
            switch (key) {
                case R.id.selected_iv:
                    if (onClickListener != null) {
                        onClickListener.selectedClick(view, getPosition());
                    }
                    break;
                case R.id.delete_tv:
                    if (onClickListener != null) {
                        onClickListener.deletedClick(view, getPosition());
                    }
                    break;
                case R.id.edit_tv:
                    if (onClickListener != null) {
                        onClickListener.editClick(view, getPosition());
                    }
                    break;
            }
        }
    }

    // 点击事件
    public interface ButtonOnClickListener {
        /**
         * 设置默认地址
         *
         * @param view
         * @param pos
         */
        void selectedClick(View view, int pos);

        /**
         * 删除地址
         *
         * @param view
         * @param pos
         */
        void deletedClick(View view, int pos);

        /**
         * 编辑地址
         *
         * @param view
         * @param pos
         */
        void editClick(View view, int pos);
    }

    private ButtonOnClickListener mOnclickListener;

    public void setOnclickListener(ButtonOnClickListener listener) {
        this.mOnclickListener = listener;
    }
}
