package com.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chenyx.libs.glide.GlideShowImageUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 类描述：   RecyclerView 适配器基类
 * 创建人：陈永祥
 * 创建时间：2016/10/10 11:03
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public abstract class AbstractRecyclerViewAdapter<T extends Object> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected static final int EMPTY_VIEW = 1;

    protected List<T> items = new ArrayList<>();

    protected Context mContext;

    protected OnItemClickListener onItemClickListener;

    protected OnItemLongClickListener onItemLongClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {

        this.onItemLongClickListener = onItemLongClickListener;
    }

    public interface OnItemClickListener {

        void onItemClick(View itemView, int position);

    }

    public interface OnItemLongClickListener {

        void onItemLongClick(View itemView, int position);

    }

    protected OnItemViewClickListener onItemViewClickListener;

    public void setOnItemViewClickListener(OnItemViewClickListener onItemViewClickListener) {

        this.onItemViewClickListener = onItemViewClickListener;
    }

    public interface OnItemViewClickListener {
        void onViewClick(View view, int position);
    }


    @Override
    public int getItemViewType(int position) {
        if (items.size() <= 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }


    /**
     * 绑定ItemView事件
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder.itemView != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemViewClickListener != null) {
                        onItemViewClickListener.onViewClick(holder.itemView, position);
                    }
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemLongClickListener != null) {
                        onItemLongClickListener.onItemLongClick(holder.itemView, position);
                    }
                    return true;
                }
            });
        }
    }

    protected AbstractRecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public LayoutInflater getInflater() {
        return LayoutInflater.from(mContext);
    }

    public T getItem(int position) {
        return items.get(position);
    }

    public void setData(List<T> items) {
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void insert(int position, T item) {
        items.add(position, item);
        notifyDataSetChanged();
    }

    public void insert(int position, List<T> items) {
        this.items.addAll(position, items);
        notifyDataSetChanged();
    }

    public void remove(int position) {

        T remove = items.remove(position);
        if (remove != null) {
            notifyDataSetChanged();
        }
    }

    public void remove(T item) {

        for (int i = 0; i < items.size(); i++) {
            T temp = items.get(i);
            if (temp.equals(item)) {
                remove(i);
                i--;
            }
        }
    }

    public void replace(int position, T item) {
        Collections.replaceAll(items, getItem(position), item);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size() > 0 ? items.size() : 1;
    }


    public List<T> getItems() {
        return items;
    }

    protected void showImageView(String imageUrl, ImageView mImageView, int defaultImg) {
        GlideShowImageUtils.displayNetImage(mContext, imageUrl, mImageView, defaultImg);
    }
    /**
     * 无数据时 显示
     */
    protected static class EmptyViewHolder extends RecyclerView.ViewHolder {

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}