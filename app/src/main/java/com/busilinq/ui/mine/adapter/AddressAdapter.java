package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.UserShopAddrEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：收货地址  适配器
 * Create Person：wangshimei
 * Create Time：18/2/1 上午9:27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class AddressAdapter extends AbstractRecyclerViewAdapter<UserShopAddrEntity> {

    public AddressAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == EMPTY_VIEW) {
            View view = getInflater().inflate(R.layout.layout_empty_view, parent, false);
            return new EmptyViewHolder(view);
        }
        View view = getInflater().inflate(R.layout.item_address_layout, parent, false);
        return new ViewHolder(view, mOnclickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = ((ViewHolder) holder);
            UserShopAddrEntity entity = items.get(position);
            vHolder.consignee_tv.setText(entity.getName());
            vHolder.address_tell_tv.setText(entity.getCell());
            vHolder.address_unit_tv.setText(entity.getCompany());
            vHolder.detail_address_tv.setText(entity.getProvince() + entity.getCity() + entity.getDistrict() + entity.getSpecificAddr());
            vHolder.selected_iv.setChecked(false);

            if (entity.getIsDefault().equals("1")) { // 默认地址
                vHolder.selected_iv.setChecked(true);
            } else {
                vHolder.selected_iv.setChecked(false);
            }
        }
    }

    /**
     * 无数据时 显示
     */
    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_empty)
        public ImageView img;
        @BindView(R.id.empty_msg_tv)
        public TextView msgTv;

        public EmptyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /**
         * 收货人
         */
        @BindView(R.id.consignee_tv)
        TextView consignee_tv;
        /**
         * 默认
         */
        @BindView(R.id.selected_tv)
        TextView selected_tv;

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
        /**
         * 总布局
         */
        @BindView(R.id.item_line)
        LinearLayout item_line;

        ButtonOnClickListener onClickListener;

        public ViewHolder(View itemView, ButtonOnClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            onClickListener = listener;
            selected_iv.setOnClickListener(this);
            selected_tv.setOnClickListener(this);
            delete_tv.setOnClickListener(this);
            edit_tv.setOnClickListener(this);
            item_line.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int key = view.getId();
            switch (key) {
                case R.id.selected_iv:
                    if (onClickListener != null) {
                        onClickListener.selectedClick(view, getPosition() - 1);
                    }
                    break;
                case R.id.selected_tv:
                    if (onClickListener != null) {
                        onClickListener.selectedClick(view, getPosition() - 1);
                    }
                    break;
                case R.id.delete_tv:
                    if (onClickListener != null) {
                        onClickListener.deletedClick(view, getPosition() - 1);
                    }
                    break;
                case R.id.edit_tv:
                    if (onClickListener != null) {
                        onClickListener.editClick(view, getPosition() - 1);
                    }
                    break;
                case R.id.item_line:
                    if (onClickListener != null) {
                        onClickListener.itermClick(view, getPosition() - 1);
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

        /**
         * 选择默认地址
         *
         * @param view
         * @param pos
         */
        void itermClick(View view, int pos);
    }

    private ButtonOnClickListener mOnclickListener;

    public void setOnclickListener(ButtonOnClickListener listener) {
        this.mOnclickListener = listener;
    }
}
