package com.busilinq.xsm.presenter;

import android.widget.ArrayAdapter;


import com.busilinq.xsm.data.entity.Cigarette;
import com.busilinq.xsm.ui.adapter.XsmSearchAdapter;
import com.busilinq.xsm.ulits.ACache;
import com.busilinq.xsm.viewinterface.IXsmSearchView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by dingyi on 2016/11/22.
 */

public class XsmSearchPresenter extends XsmBasePresenter<IXsmSearchView> {

    /**
     * 保存及显示历史查询记录数量
     */
    private final int mHistorySize = 20;
    /**
     * 历史搜索
     */
    private ArrayAdapter<String> mHistoryAdapter;

    /**
     * 当前输入搜索
     */
    private ArrayAdapter<String> mSearchAdapter;

    /**
     * 搜索结果列表adapter
     */
    private XsmSearchAdapter mResultAdapter;

    /**
     * 搜索结果
     */
    private List<Cigarette> mResults = new ArrayList<>();

    /**
     * 搜索列表
     */
    private HashMap<String, Cigarette> mCgtInfos;

    /**
     * 当前搜索数据
     */
    private List<String> mSearches = new ArrayList<>();

    /**
     * 历史搜索数据
     */
    private List<String> mHistorys;

    private ACache mACache;

    private boolean isShwoHistory=true;//判断当前是否显示的是历史搜索
    @Override
    public void attachView(IXsmSearchView view) {
        super.attachView(view);
        this.mACache = ACache.get(mContext);
        this.mCgtInfos = mXsmDbApi.getCigarettes();
    }

    @Override
    public void start() {
        updateHistoryList();
    }

    private void updateHistoryList() {
        mHistorys = getHistorySearch();
        mHistorys.add(mContext.getResources().getString(R.string.myxsm_seach_history));
        mHistoryAdapter = new ArrayAdapter<String>(mContext, R.layout.myxsm_searchview_item, mHistorys);
        mBaseView.updateHistoryList(mHistoryAdapter);
        isShwoHistory=true;
    }

    public void updateSearchList(String keys) {
        mSearches = getSearches(keys);
        isShwoHistory=false;
        if (null == mSearchAdapter) {
            mSearchAdapter = new ArrayAdapter<String>(mContext, R.layout.myxsm_searchview_item, mSearches);
            mBaseView.updateSearchList(mSearchAdapter);
        } else {
            mSearchAdapter.notifyDataSetChanged();
        }
    }

    public void updateResultList(String keys) {
        mResults = getResults(keys);
        if (null == mResultAdapter) {
            mResultAdapter = new XsmSearchAdapter(mContext, mResults);
            mBaseView.updateResultList(mResultAdapter);
        } else {
            mResultAdapter.setCgtInfos(mResults);
        }
    }
    public boolean getIsShowHistory()
    {
        return isShwoHistory;
    }
    public void clearmResults() {
        mResults.clear();
    }

    public Cigarette getResultItem(int i) {
        return mResults.get(i);
    }

    /**
     * 获取历史数据
     *
     * @return 历史数据
     */
    private List<String> getHistorySearch() {
        List<String> search = new ArrayList<>();
        String str;
        str = mACache.getAsString(XsmSearchPresenter.class.getSimpleName());
        if (null != str && !str.equals("")) {
            String[] arr = str.split(",");
            search.addAll(Arrays.asList(arr));
            Collections.reverse(search);
        }
        return search;
    }

    /**
     * 保存搜索数据
     *
     * @param key
     */
    private void saveSearch(String key) {
        List<String> list = getHistorySearch();

        if (list.size() == mHistorySize) {
            list.remove(list.size() - 1);
        }

        if (!list.contains(key))
            list.add(key);
        else {
            return;
        }
        Collections.reverse(list);

        String str = list.toString().replace("[", "").replace("]", "").replace(" ", "");
        mACache.put(XsmSearchPresenter.class.getSimpleName(), str);
    }

    /**
     * 获取搜索结果
     */
    private List<Cigarette> getResults(String key) {
        mResults.clear();
        if (null != key && !key.equals("")) {
            Iterator<Map.Entry<String, Cigarette>> it = mCgtInfos.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Cigarette> entry1 = it.next();
                Cigarette cgtinfo = entry1.getValue();
                if (cgtinfo.getCgtName().contains(key.trim())) {
                    mResults.add(cgtinfo);
                }
            }
            saveSearch(key);
        }
        return mResults;
    }

    /**
     * 获取自动补全数据
     *
     * @param key
     * @return
     */
    private List<String> getSearches(String key) {
        mSearches.clear();
        if (null != key && !key.equals("")) {
            Iterator<Map.Entry<String, Cigarette>> it = mCgtInfos.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Cigarette> entry1 = it.next();
                Cigarette cgtinfo = entry1.getValue();
                if (cgtinfo.getCgtName().contains(key.trim())) {
                    mSearches.add(cgtinfo.getCgtName());
                }
            }
        }
        return mSearches;
    }

}
