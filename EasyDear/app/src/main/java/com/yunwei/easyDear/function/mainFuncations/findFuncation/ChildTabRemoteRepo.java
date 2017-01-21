package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/21.
 */
public class ChildTabRemoteRepo implements ChildTabDataSource {

    private final String TAG = getClass().getSimpleName();

    private static ChildTabRemoteRepo instance;

    public static ChildTabRemoteRepo getInstance() {
        if (instance == null) {
            instance = new ChildTabRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestRecyclerArticles(String type, final ChildTabCallBack callBack) {

        Call<ResponseModel<ArrayList<ArticleItemEntity>>> call = RetrofitManager.getInstance().getService().reqHomeArticleList(1, 5, "", "", "", "", "");
        call.enqueue(new Callback<ResponseModel<ArrayList<ArticleItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Response<ResponseModel<ArrayList<ArticleItemEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onGetArticleListSuccess(response.body().getData());
                } else {
                    callBack.onGetArticleListFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<ArticleItemEntity>>> call, Throwable t) {
                callBack.onGetArticleListFailure("获取文章列表失败");
            }
        });
    }

    @Override
    public void cancelRequest() {

    }

}
