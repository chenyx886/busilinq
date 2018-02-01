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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            ViewHolder vHolder = ((ViewHolder) holder);
            UserShopAddrEntity entity = dataList.get(position);
            vHolder.consignee_tv.setText(entity.getName());
            vHolder.address_tell_tv.setText(entity.getCell());
            vHolder.address_unit_tv.setText(entity.getCountry());
            vHolder.detail_address_tv.setText(entity.getSpecificAddr());
            if (entity.getIsDefault().equals("1")) { // 默认地址
                vHolder.selected_iv.setChecked(true);
            } else {
                vHolder.selected_iv.setChecked(false);
            }
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

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




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
