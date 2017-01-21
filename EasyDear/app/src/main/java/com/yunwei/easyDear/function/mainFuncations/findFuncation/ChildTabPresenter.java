package com.yunwei.easyDear.function.mainFuncations.findFuncation;


import android.util.Log;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */
public class ChildTabPresenter implements ChildTabContact.ChildPresenter, ChildTabDataSource.ChildTabCallBack {

    private ChildTabContact.ChileTabView mChildTabView;
    private ChildTabDataSource mRemoteRepo;

    public ChildTabPresenter(ChildTabRemoteRepo childTabRemoteRepo, ChildTabContact.ChileTabView chileTabView) {
        mRemoteRepo = childTabRemoteRepo;
        mChildTabView = chileTabView;
    }

    @Override
    public void requestRecyclerArticles(String type) {
        mRemoteRepo.requestRecyclerArticles(type, this);
    }

    @Override
    public void onGetArticleListSuccess(ArrayList<ArticleItemEntity> data) {
        Log.d("ChildTabPresenter", "----------> ChildTab onGetArticleListSuccess data = " + (data == null));
        mChildTabView.setArticleList(data);
    }

    @Override
    public void onGetArticleListFailure(String message) {

    }
}
