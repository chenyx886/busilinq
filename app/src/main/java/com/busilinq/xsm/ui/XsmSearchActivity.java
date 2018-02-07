package com.busilinq.xsm.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.busilinq.R;
import com.busilinq.xsm.base.XsmBaseActivity;
import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.presenter.XsmSearchPresenter;
import com.busilinq.xsm.ui.adapter.XsmSearchAdapter;
import com.busilinq.xsm.ulits.Logger;
import com.busilinq.xsm.viewinterface.IXsmSearchView;
import com.busilinq.xsm.widget.SearchView;

import butterknife.BindView;

/**
 * Created by dingyi on 2016/11/22.
 * update by yu on 2017年6月8日
 */

public class XsmSearchActivity extends XsmBaseActivity implements IXsmSearchView, SearchView.SearchViewListener {
    @BindView(R.id.myxsm_searchview)
    SearchView mSearchview;
    @BindView(R.id.myxsm_searchview_hist_search_lv)
    ListView mSearchLv;
    @BindView(R.id.myxsm_searchview_results_lv)
    ListView mResultsLv;

    private XsmSearchPresenter mPresenter;

    @Override
    public int initContentView() {
        return R.layout.myxsm_search_activity;
    }

    @Override
    public void initData() {
        mPresenter = new XsmSearchPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void initUi() {
        mSearchview.setSearchViewListener(this);
        mSearchLv.setOnItemClickListener(mSearchViewClick);
        mResultsLv.setOnItemClickListener(mResultViewClick);
        mPresenter.start();
    }

    @Override
    public void onSearch(String text) {
        Logger.e("onSearch " + text);
        mSearchLv.setVisibility(View.VISIBLE);
        mResultsLv.setVisibility(View.GONE);
        mPresenter.updateSearchList(text);
    }

    @Override
    public void onResult(String text) {
        Logger.e("onResult " + text);
        mSearchLv.setVisibility(View.GONE);
        mResultsLv.setVisibility(View.VISIBLE);
        mPresenter.updateResultList(text);
    }

    @Override
    public void onClose() {
        if (mSearchLv.getVisibility() == View.VISIBLE || mResultsLv.getVisibility() == View.VISIBLE) {
           if(mSearchLv.getVisibility() == View.VISIBLE&&mPresenter.getIsShowHistory()) {
               XsmSearchActivity.this.onBackPressed();
               return;
           }
            mSearchLv.setVisibility(View.GONE);
            mResultsLv.setVisibility(View.GONE);
            mPresenter.start();
        } else {
            XsmSearchActivity.this.onBackPressed();
        }
    }

    @Override
    public void onClear() {
        mSearchLv.setVisibility(View.GONE);
        mResultsLv.setVisibility(View.GONE);
    }

    @Override
    public void updateHistoryList(ArrayAdapter<String> adapter) {
        mSearchview.setHistoryAdapter(adapter);
        mSearchview.setDataListViewVisible();
    }

    @Override
    public void updateSearchList(ArrayAdapter<String> adapter) {
        mSearchLv.setAdapter(adapter);
    }

    @Override
    public void updateResultList(XsmSearchAdapter adapter) {
        mResultsLv.setAdapter(adapter);
    }


    private AdapterView.OnItemClickListener mSearchViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String key = mSearchLv.getAdapter().getItem(i).toString();
            onResult(key);
        }
    };

    private AdapterView.OnItemClickListener mResultViewClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent();
            intent.putExtra(Cigarette.class.getSimpleName(), mPresenter.getResultItem(i));
            setResult(RESULT_OK, intent);
            XsmSearchActivity.this.onBackPressed();
        }
    };
}
