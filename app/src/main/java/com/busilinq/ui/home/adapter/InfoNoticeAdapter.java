package com.busilinq.ui.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.AbstractRecyclerViewAdapter;
import com.busilinq.R;
import com.busilinq.data.entity.HomeGoodsEntity;
import com.busilinq.data.entity.InfoNoticeEntity;
import com.chenyx.libs.glide.GlideShowImageUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Company：华科建邺
 * Class Describe：信息公告 适配器
 * Create Person：lwx
 * Create Time：2018/2/27
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class InfoNoticeAdapter extends AbstractRecyclerViewAdapter<InfoNoticeEntity> {

    public InfoNoticeAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_info_notice_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder != null) {
            ViewHolder vHolder = ((ViewHolder) holder);
            InfoNoticeEntity entity = getItem(position);
            vHolder.mTime.setText(entity.getTime());
            vHolder.mTitle.setText(entity.getTitle());
            vHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * 公告时间
         */
        @BindView(R.id.tv_time)
        TextView mTime;
        /**
         * 公告标题
         */
        @BindView(R.id.tv_title)
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
