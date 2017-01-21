package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction;

import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.source.BusinessDataSource;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.source.BusinessRemoteRepo;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction
 * @Description:
 * @date 2017/1/21 14:53
 */

public class BusinessPresenter implements BusinessContract.Present,BusinessDataSource.BusinessCallBack{

    private BusinessRemoteRepo remoteRepo;
    private BusinessContract.BusinessView businessView;

    public BusinessPresenter(BusinessContract.BusinessView businessView){
        this.remoteRepo=BusinessRemoteRepo.newInstance();
        this.businessView=businessView;
    }
    @Override
    public void reqBusinessListAction() {
        businessView.onBusinessStart();
        remoteRepo.reqBusinessList(this);
    }

    @Override
    public void getBusinessSuccess(List<BusinessEntity> list) {
        businessView.onBusinessSuccess(list);
        businessView.onBusinessEnd();
    }

    @Override
    public void getBusinessFailure(String error) {
        businessView.onBusinessFaliure(error);
        businessView.onBusinessEnd();
    }

    @Override
    public String getUserNo() {
        return businessView.getUserNo();
    }

    @Override
    public int getPageSize() {
        return businessView.getPageSize();
    }

    @Override
    public int getPageCount() {
        return businessView.getPageCount();
    }
}
