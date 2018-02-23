package com.busilinq.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.busilinq.ulits.BugGoutAgent;
import com.busilinq.xsm.ulits.Logger;
import com.testin.agent.Bugout;
import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;

/**
 * Company：华科建邺
 * Class Describe： RxFragment 基类
 * Create Person：Chenyx
 * Create Time：2016/10/13 11:30
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public abstract class BaseFragment extends RxFragment implements IBackHandled {

    protected String TAG;

    protected Subscription mSubscription;

    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BugGoutAgent.init(getActivity(), false);
        initTAG(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        initUI();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //依赖注入绑定
        Logger.e("onViewCreated---bind");
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        //注：回调 1
        Bugout.onResume(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        //注：回调 2
        Bugout.onPause(getActivity());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("onDestroyView---unbind");
        unbinder.unbind();
    }

    /**
     * 日志TAG初始化
     *
     * @param fragment
     */
    protected void initTAG(Fragment fragment) {
        TAG = fragment.getClass().getSimpleName();
    }
    /**
     * 界面初始化
     */
    protected abstract void initUI();


    @Override
    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }
}
