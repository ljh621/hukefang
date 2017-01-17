package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

import android.widget.LinearLayout;

import com.yunwei.easyDear.function.mainFuncations.homeFuncation.data.HomeDataSource;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.data.HomeRemoteRepo;

/**
 * Created by LJH on 2017/1/4.
 */

public class HomePresenter implements HomeContract.Presenter, HomeDataSource.RequestHome {

    private HomeRemoteRepo mRemoteRepo;
    private HomeContract.HomeView mHomeView;

    public HomePresenter(HomeRemoteRepo homeRemoteRepo, HomeContract.HomeView homeView) {
        mRemoteRepo = homeRemoteRepo;
        mHomeView = homeView;
    }

    @Override
    public void requestScrollImageUrls() {
        mRemoteRepo.requestScrollImageUrls(this);
    }

    @Override
    public void requestHomeArticleList() {
        mRemoteRepo.requestHomeArticleList(this);
    }

    @Override
    public void getScrollImageUrlSuccess(String urls) {
        mHomeView.initImageUrl(urls);
    }

    @Override
    public void getHomeArticleListSuccess() {

    }

    public void addLayoutIntoScroll(LinearLayout layout) {
        mRemoteRepo.addLayoutIntoScroll(layout);
    }
}
