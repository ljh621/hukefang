package com.jingan.easydearbusiness.function.billFunction.data.source;

import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.vender.retrofit.RetrofitManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-16
 * Time: 23:48
 * Version:1.0
 */

public class BillRemoteRemo implements BillDataSource {

    private Call<ResponseModel<String>> call;

    private static BillRemoteRemo remoteRemo;

    public static BillRemoteRemo newInstance() {
        if (remoteRemo == null) {
            remoteRemo = new BillRemoteRemo();
        }
        return remoteRemo;
    }

    @Override
    public void reqBill(final BillCallBack callBack) {
        call = RetrofitManager.getInstance().getService().queryCode(callBack.getUserNo(),callBack.getCode());
        call.enqueue(new Callback<ResponseModel<String>>() {
            @Override
            public void onResponse(Call<ResponseModel<String>> call, Response<ResponseModel<String>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == Constant.HTTP_SUCESS_CODE){
                        callBack.getBillSuccess(response.body());
                    }else {
                        callBack.getBillFailure("查询失败");
                    }
                } else {
                    callBack.getBillFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<String>> call, Throwable t) {
                callBack.getBillFailure("查询失败");
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
    }
}
