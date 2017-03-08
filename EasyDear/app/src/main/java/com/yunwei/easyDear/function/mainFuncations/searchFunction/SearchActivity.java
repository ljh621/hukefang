package com.yunwei.easyDear.function.mainFuncations.searchFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.MainActivity;
import com.yunwei.easyDear.utils.ISpfUtil;
import com.yunwei.easyDear.view.MeasuredGridView;
import com.yunwei.easyDear.view.MeasuredListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/3/4.
 */
public class SearchActivity extends BaseActivity implements SearchContact.SearchView {

    final String TAG = getClass().getSimpleName();

    @BindView(R.id.search_editText)
    EditText mSearchEditText;
    @BindView(R.id.search_hot_gridView)
    MeasuredGridView mSearchHotGridView;
    @BindView(R.id.search_history_textView)
    TextView mSearchHistoryTextView;
    @BindView(R.id.search_history_listView)
    MeasuredListView mSearchHistoryListView;

    private SearchPresenter mSearchPresenter;
    private SearchHotAdapter mSearchHotAdapter;
    private String mSearchText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setToolbarTitle("搜索");
        setToolbarVisibility(View.GONE);
        ButterKnife.bind(this);
        initPresenter();
        initSearchHotGridView();
        initSearchHistoryListView();
    }

    private void initPresenter() {
        mSearchPresenter = new SearchPresenter(SearchRemoteRepo.getInstance(), this);
    }

    private void initSearchHotGridView() {
        mSearchHotAdapter = new SearchHotAdapter(this);
        mSearchHotGridView.setAdapter(mSearchHotAdapter);
        requestHotSearch();
    }

    private void initSearchHistoryListView() {
        String savedHistory = (String) ISpfUtil.getValue("search_history", "");
        Log.v(TAG, "Saved search history = " + savedHistory);
        if (!savedHistory.isEmpty()) {
            final String[] savedHisArr = savedHistory.split(";");
            int len = savedHisArr.length;
            if (len > 0) {
                mSearchHistoryTextView.setVisibility(View.VISIBLE);
                mSearchHistoryListView.setVisibility(View.VISIBLE);
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SearchActivity.this, R.layout.item_search_history_layout, savedHisArr);
                mSearchHistoryListView.setAdapter(arrayAdapter);
                mSearchHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        saveAndSearch(savedHisArr[i]);
                    }
                });
            }
        }
    }

    private void requestHotSearch() {
        mSearchPresenter.requestHotSearch();
    }

    @Override
    public void onGetHotSearchSuccess(final List<SearchHotEntity> entities) {
        if (entities == null) {
            return;
        }
        mSearchHotAdapter.setSearchHotList((ArrayList<SearchHotEntity>) entities);
        mSearchHotAdapter.notifyDataSetChanged();
        mSearchHotGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String newSearch = entities.get(position).getMsg();
                saveAndSearch(newSearch);
            }
        });
    }

    @Override
    public void onGetHotSearchFailure(String msg) {

    }

    private void saveAndSearch(String search) {
        saveSearchHistory(search);
        startToSearch(search);
    }

    private void saveSearchHistory(String searchText) {
        String savedHistory = (String) ISpfUtil.getValue("search_history", "");
        String newHistory = searchText + ";";
        if (!savedHistory.isEmpty()) {
            String[] savedHisArr = savedHistory.split(";");
            int len = savedHisArr.length;
            for (int i = 0; i < 4 && i < len; i++) {
                if (!savedHisArr[i].equals(searchText)) {
                    newHistory += savedHisArr[i] + ";";
                }
            }
        }
        ISpfUtil.setValue("search_history", newHistory);
        Log.v(TAG, "Save new history = " + newHistory);
    }

    private void startToSearch(String searchText) {
        Intent intent = new Intent();
        intent.putExtra("search_key", searchText);
        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick({R.id.search_cancel_textView, R.id.search_start_textView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_cancel_textView:
                finish();
                break;
            case R.id.search_start_textView:
                mSearchText = mSearchEditText.getText().toString();
                saveAndSearch(mSearchText);
                break;
            default:
                break;
        }
    }

}
