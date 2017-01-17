package com.yunwei.easyDear.function.mainFuncations.homeFuncation.data;

import android.widget.LinearLayout;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.HomePresenter;

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
    public void requestScrollImageUrls(final RequestHome callBack) {
        Call<ResponseModel<String>> call = RetrofitManager.getInstance().getService().reqScrollImageUrls();
        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                if (response.code() == Constant.HTTP_SUCESS_CODE) {
                    //TODO 待处理call.toString, 获取url
                    String url = "解析call.toString()";
                    callBack.getScrollImageUrlSuccess(url);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {

            }
        });
    }

    public void requestHomeArticleList(final RequestHome callBack) {
        Call<ResponseModel<String>> call = RetrofitManager.getInstance().getService().requestHomeArticleList();
    }

    public void addLayoutIntoScroll(LinearLayout layout) {
    }
}
