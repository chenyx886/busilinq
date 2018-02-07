package com.busilinq.xsm.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.busilinq.R;
import com.busilinq.xsm.data.entity.XsmAccount;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yu on 2017/6/27.
 */

public class XsmAccountsAdapter extends BaseAdapter {

    private Context mContext;
    private List<XsmAccount> mAccounts;

    public XsmAccountsAdapter(Context context, List<XsmAccount> accounts) {
        this.mContext = context;
        this.mAccounts = accounts;
    }

    public void setAccounts(List<XsmAccount> accounts) {
        this.mAccounts = accounts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (null == mAccounts)
            return 0;
        return mAccounts.size();
    }

    @Override
    public Object getItem(int i) {
        return mAccounts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xsm_accounts_item, null);
            holder = new ViewHolder(view);
            AutoUtils.autoSize(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        updateView(holder, mAccounts.get(i));
        return view;
    }

    private void updateView(ViewHolder holder, XsmAccount account) {
        holder.mNameTv.setText(account.getName());
        holder.mAccountTv.setText(account.getAccount());
    }


    class ViewHolder {
        @BindView(R.id.xsm_accouts_item_igv)
        ImageView mIgv;
        @BindView(R.id.xsm_accouts_item_name_tv)
        TextView mNameTv;
        @BindView(R.id.xsm_accouts_item_accout_tv)
        TextView mAccountTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
