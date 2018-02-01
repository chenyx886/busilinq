package com.busilinq.ui.mine.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.UserShopAddrEntity;

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

    protected AddressAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_layout, parent, false);
        return new ViewHolder(view);
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




        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
