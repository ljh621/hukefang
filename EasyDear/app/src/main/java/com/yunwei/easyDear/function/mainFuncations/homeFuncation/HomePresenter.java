package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

import android.widget.LinearLayout;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.data.HomeDataSource;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.data.HomeRemoteRepo;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/4.
 */

public class HomePresenter implements HomeContract.Presenter, HomeDataSource.HomeCallBack {

    private HomeRemoteRepo mRemoteRepo;
    private HomeContract.HomeView mHomeView;

    public HomePresenter(HomeRemoteRepo homeRemoteRepo, HomeContract.HomeView homeView) {
        mRemoteRepo = homeRemoteRepo;
        mHomeView = homeView;
    }

    @Override
    public void requestTopScrollArticles() {
        mRemoteRepo.requestHomeTopScrollArticles(this);
    }

    @Override
    public void onGetTopScrollArticlesSuccess(ArrayList<ArticleItemEntity> articleItems) {
        mHomeView.setTopScrollArticles(articleItems);
    }

    @Override
    public void onGetTopScrollArticlesSuccess(String message) {

    }

    @Override
    public String getProvince() {
        return mHomeView.getProvince();
    }

    @Override
    public String getCity() {
        return mHomeView.getCity();
    }

    @Override
    public String getArea() {
        return mHomeView.getArea();
    }
}
