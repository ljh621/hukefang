package com.yunwei.easyDear.function.mainFuncations.searchFunction;


import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/3/4.
 */
public class SearchRemoteRepo implements SearchDataSource {

    private static SearchRemoteRepo instance;

    public static SearchRemoteRepo getInstance() {
        if (instance == null) {
            instance = new SearchRemoteRepo();
        }
        return instance;
    }

    @Override
    public void reqHotSearch(final SearchCallBack callBack) {
        Call<ResponseModel<List<SearchHotEntity>>> call = RetrofitManager.getInstance().getService().reqHotSearch();
        call.enqueue(new Callback<ResponseModel<List<SearchHotEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<SearchHotEntity>>> call, Response<ResponseModel<List<SearchHotEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getHotSearchSuccess(response.body().getData());
                } else {
                    callBack.getHotSearchFailure(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<SearchHotEntity>>> call, Throwable t) {
                callBack.getHotSearchFailure("获取热门搜索失败");
            }
        });

    }
}
