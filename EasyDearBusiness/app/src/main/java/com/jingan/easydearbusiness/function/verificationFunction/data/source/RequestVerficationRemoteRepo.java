package com.jingan.easydearbusiness.function.verificationFunction.data.source;

import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationDetailEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;
import com.jingan.easydearbusiness.utils.ILog;
import com.jingan.easydearbusiness.vender.retrofit.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction.data.source
 * @Description:
 * @date 2017/1/1 15:06
 */

public class RequestVerficationRemoteRepo implements RequestVerficationDataSource {
    private final String TAG = getClass().getSimpleName();

    private Call<ResponseModel<List<VerficationEntity>>> call;
    private Call<ResponseModel<List<VerficationDetailEntity>>> verficationCall;

    private static RequestVerficationRemoteRepo remoteRepo;

    public static RequestVerficationRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new RequestVerficationRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void downRefresh(final DownRefreshCallBack callBack) {
        call = RetrofitManager.getInstance().getService().queryVerficationList(callBack.getBusinessNo(), callBack.pageSize(), callBack.pageCount(), callBack.getDate());
        call.enqueue(new Callback<ResponseModel<List<VerficationEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<VerficationEntity>>> call, Response<ResponseModel<List<VerficationEntity>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == Constant.HTTP_SUCESS_CODE){
                        callBack.getDownRefreshSuccess(response.body().getData());
                    }else {
                        callBack.getDownRefreshFailure();
                    }
                } else {
                    List<VerficationEntity> entities = new ArrayList<>();
                    callBack.getDownRefreshSuccess(entities);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<VerficationEntity>>> call, Throwable t) {
                callBack.getDownRefreshFailure();
            }
        });
    }

    @Override
    public void pullRefresh(final PullRefreshCallBack callBack) {
        call = RetrofitManager.getInstance().getService().queryVerficationList(callBack.getBusinessNo(), callBack.pageSize(), callBack.pageCount(), callBack.getDate());
        call.enqueue(new Callback<ResponseModel<List<VerficationEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<VerficationEntity>>> call, Response<ResponseModel<List<VerficationEntity>>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode() == Constant.HTTP_SUCESS_CODE){
                        callBack.getPullRefreshSuccess(response.body().getData());
                    }else {
                        callBack.getPullRefreshFailure();
                    }
                } else {
                    List<VerficationEntity> entities = new ArrayList<>();
                    callBack.getPullRefreshSuccess(entities);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<VerficationEntity>>> call, Throwable t) {
                callBack.getPullRefreshFailure();
            }
        });
    }

    @Override
    public void queryVerficationDetail(final QueryVerficationDetailCallBack callBack) {
        verficationCall = RetrofitManager.getInstance().getService().queryVerficationDetail(callBack.getBillNo());
        verficationCall.enqueue(new Callback<ResponseModel<List<VerficationDetailEntity>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<VerficationDetailEntity>>> call, Response<ResponseModel<List<VerficationDetailEntity>>> response) {
                if (response.isSuccessful()) {
                    if ( response.body().getCode() == Constant.HTTP_SUCESS_CODE){
                        callBack.getDetailSuccess(response.body().getData());
                    }else {
                        callBack.getDetailFailure(response.body().getMessage());
                    }
                } else {
                    callBack.getDetailFailure("获取账单详情失败");
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<VerficationDetailEntity>>> call, Throwable t) {
                callBack.getDetailFailure("获取账单详情失败");
            }
        });
    }

    @Override
    public void cancelRequest() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
        }
        if (verficationCall != null && !verficationCall.isCanceled()) {
            verficationCall.cancel();
        }
    }
}
