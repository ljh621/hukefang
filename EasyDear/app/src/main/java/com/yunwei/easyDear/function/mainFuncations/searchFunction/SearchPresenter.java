package com.yunwei.easyDear.function.mainFuncations.searchFunction;


import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */
public class SearchPresenter implements SearchContact.Presenter, SearchDataSource.SearchCallBack {

    private SearchContact.SearchView mSearchView;
    private SearchRemoteRepo mRemoteRepo;

    public SearchPresenter(SearchRemoteRepo remoteRepo, SearchContact.SearchView searchView) {
        mRemoteRepo = remoteRepo;
        mSearchView = searchView;
    }

    @Override
    public void requestHotSearch() {
        mRemoteRepo.reqHotSearch(this);
    }

    @Override
    public void getHotSearchSuccess(List<SearchHotEntity> list) {
        mSearchView.onGetHotSearchSuccess(list);
    }

    @Override
    public void getHotSearchFailure(String msg) {
        mSearchView.onGetHotSearchFailure(msg);
    }
}
