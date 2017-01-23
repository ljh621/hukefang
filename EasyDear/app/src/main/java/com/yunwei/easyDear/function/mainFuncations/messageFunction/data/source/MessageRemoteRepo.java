package com.yunwei.easyDear.function.mainFuncations.messageFunction.data.source;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LJH on 2017/1/22.
 */

public class MessageRemoteRepo implements MessageDataSource {

    private final String TAG = getClass().getSimpleName();

    private static MessageRemoteRepo instance;

    private Call<ResponseModel<List<MessageDetailEntity>>> msgDetailCall;

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

    @Override
    public void reqMsgDetail(final MsgDetailCallBack callBack) {
        msgDetailCall = RetrofitManager.getInstance().getService().reqMessageDetail(callBack.getUserNo(), callBack.getBusinessNo(), callBack.getPageSize(), callBack.getPageCount());
        msgDetailCall.enqueue(new Callback<ResponseModel<List<MessageDetailEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<MessageDetailEntity>>> call, Response<ResponseModel<List<MessageDetailEntity>>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.getMsgSuccess(response.body().getData());
                } else {
                    callBack.getMsgFailure(response.body().getCode(), response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<MessageDetailEntity>>> call, Throwable t) {
                callBack.getMsgFailure(201, "数据加载失败");
            }
        });
    }
}
