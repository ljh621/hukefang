package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import android.util.Log;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/22.
 */

public class MessageRemoteRepo implements MessageDataSource {

    private final String TAG = getClass().getSimpleName();

    private static MessageRemoteRepo instance;

    public static MessageRemoteRepo getInstance() {
        if (instance == null) {
            instance = new MessageRemoteRepo();
        }
        return instance;
    }

    @Override
    public void requestBusMessages(String useNo, final BusMsgCallBack callBack) {

        Call<ResponseModel<ArrayList<BusMessageItemEntity>>> call = RetrofitManager.getInstance().getService().requestBusMessages(useNo, 1, 5);
        call.enqueue(new Callback<ResponseModel<ArrayList<BusMessageItemEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArrayList<BusMessageItemEntity>>> call, Response<ResponseModel<ArrayList<BusMessageItemEntity>>> response) {

                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onReqBusMessagesSuccess(response.body().getData());
                } else {
                    callBack.onReqBusMessagesFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArrayList<BusMessageItemEntity>>> call, Throwable t) {

            }
        });

    }
}
