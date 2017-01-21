package com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data.source;

import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data.BillEntity;
import com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data.BusinessEntity;
import com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data.CardEntity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.membershipFuncation.data.source
 * @Description:
 * @date 2017/1/21 15:40
 */

public class BillRemoteRepo implements BillDataSource {

    private Call<ResponseModel<CardEntity>> cardCall;
    private Call<ResponseModel<BusinessEntity>> businessCall;
    private Call<ResponseModel<BillEntity>> billCall;

    private static BillRemoteRepo remoteRepo;

    public static BillRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new BillRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void reqCardSize(final CardCallBack cardCallBack) {
        cardCall = RetrofitManager.getInstance().getService().reqCardCount(cardCallBack.getUserNo());
        cardCall.enqueue(new Callback<ResponseModel<CardEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<CardEntity>> call, Response<ResponseModel<CardEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    cardCallBack.onCardSuccess(response.body().getData().getCardSize());
                } else {
                    cardCallBack.onCardFalire(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<CardEntity>> call, Throwable t) {
                cardCallBack.onCardFalire("获取失败");
            }
        });
    }

    @Override
    public void reqBunisess(final BusinessCallBack callBack) {
        businessCall = RetrofitManager.getInstance().getService().reqBusinessCount(callBack.getUserNo());
        businessCall.enqueue(new Callback<ResponseModel<BusinessEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<BusinessEntity>> call, Response<ResponseModel<BusinessEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onBusinessSuccess(response.body().getData().getBusinessSize());
                } else {
                    callBack.onBusinessFalire(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<BusinessEntity>> call, Throwable t) {
                callBack.onBusinessFalire("获取失败");
            }
        });
    }

    @Override
    public void reqBill(final BillssCallBack callBack) {
        billCall = RetrofitManager.getInstance().getService().reqBillCount(callBack.getUserNo());
        billCall.enqueue(new Callback<ResponseModel<BillEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<BillEntity>> call, Response<ResponseModel<BillEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onBillSuccess(response.body().getData().getBillSize());
                } else {
                    callBack.onBillFalire(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<BillEntity>> call, Throwable t) {
                callBack.onBillFalire("获取失败");
            }
        });
    }
}
