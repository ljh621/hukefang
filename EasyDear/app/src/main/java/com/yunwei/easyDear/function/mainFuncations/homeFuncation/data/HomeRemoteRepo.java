package com.yunwei.easyDear.function.mainFuncations.homeFuncation.data;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/4.
 */

public class HomeRemoteRepo implements HomeDataSource {
    private final String TAG = getClass().getSimpleName();

    private static HomeRemoteRepo instance;

    public static HomeRemoteRepo getInstance() {
        if (instance == null) {
            instance = new HomeRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestHomeTopScrollArticles(final HomeCallBack callBack) {
        Call<ResponseModel<ArrayList<ArticleItemEntity>>> call = RetrofitManager.getInstance().getService().reqHomeTopScrollArticleList("", "", "");
        call.enqueue(new Callback<ResponseModel<ArrayList<ArticleItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Response<ResponseModel<ArrayList<ArticleItemEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onGetTopScrollArticlesSuccess(response.body().getData());
                } else {
                    callBack.onGetTopScrollArticlesSuccess(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Throwable t) {
                callBack.onGetTopScrollArticlesSuccess("获取顶部轮播文章列表失败");
            }
        });
    }

}
