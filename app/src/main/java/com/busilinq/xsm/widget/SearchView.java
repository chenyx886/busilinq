package com.busilinq.xsm.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.busilinq.R;
import com.zhy.autolayout.AutoLinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Company：华科建邺
 * Class Describe：
 * Create Person：Chenyx
 * Create Time：2017/11/15 下午6:05
 * Update Person：
 * Update Time：
 * Update Remark：
 */
public class SearchView extends AutoLinearLayout implements View.OnClickListener {


    @BindView(R.id.searchview_input_edt)
    EditText mInputEdt;
    @BindView(R.id.searchview_btn)
    TextView mBtn;
    @BindView(R.id.searchview_lv)
    ListView mLv;


    private Context mContext;
    private SearchViewListener mListener;
    private boolean isBtnOnclick = false;

    /**
     * 提示adapter （推荐adapter）
     */
    private ArrayAdapter<String> mHistoryAdapter;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.searchview_layout, this);
        ButterKnife.bind(view, this);
        mInputEdt.addTextChangedListener(new EditChangedListener());
        mLv.setOnItemClickListener(mOnItemClickListener);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSearchViewListener(SearchViewListener listener) {
        this.mListener = listener;
    }

    @OnClick({R.id.searchview_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchview_btn:
//                notifyStartSearching(mInputEdt.getText().toString());
                close();
                break;

        }
    }

    private void close() {
        isBtnOnclick = true;
        mInputEdt.setText("");
        if (mListener != null) {
            mListener.onClose();
        }
        isBtnOnclick = false;
    }

    public void setDataListViewVisible() {
        mLv.setVisibility(VISIBLE);
    }

    /**
     * 通知监听者 进行搜索操作
     *
     * @param text
     */
    private void notifyStartSearching(String text) {
        if (mListener != null) {
            mListener.onResult(text);
        }
        //隐藏软键盘
        mLv.setVisibility(GONE);
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 设置热搜版提示 adapter
     */
    public void setHistoryAdapter(ArrayAdapter<String> adapter) {
        this.mHistoryAdapter = adapter;
        if (mLv.getAdapter() == null) {
            mLv.setAdapter(mHistoryAdapter);
        }
    }

    private class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (isBtnOnclick)
                return;
            if (!"".equals(charSequence.toString())) {
                mLv.setVisibility(GONE);
                //更新autoComplete数据
                if (mListener != null) {
                    mListener.onSearch(charSequence + "");
                }
            } else {
                if (mHistoryAdapter != null) {
                    mLv.setAdapter(mHistoryAdapter);
                }
                if (null != mListener)
                    mListener.onClear();
                mLv.setVisibility(VISIBLE);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            if (i < mHistoryAdapter.getCount() - 1) {
                String key = mHistoryAdapter.getItem(i).toString();
                notifyStartSearching(key);
            }
        }
    };

    /**
     * search view回调方法
     */
    public interface SearchViewListener {

        /**
         * 清除搜索内容
         */
        void onClear();

        /**
         * 更新自动补全内容
         *
         * @param text
         */
        void onSearch(String text);

        /**
         * 搜索结果
         *
         * @param text
         */
        void onResult(String text);

        /**
         * 关闭界面
         */
        void onClose();
    }

}

