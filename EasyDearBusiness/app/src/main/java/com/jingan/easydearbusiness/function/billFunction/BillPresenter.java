package com.jingan.easydearbusiness.function.billFunction;

import com.jingan.easydearbusiness.base.DataApplication;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.function.billFunction.data.source.BillDataSource;
import com.jingan.easydearbusiness.function.billFunction.data.source.BillRemoteRemo;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-16
 * Time: 23:54
 * Version:1.0
 */

public class BillPresenter implements BillDataSource.BillCallBack ,BillConstacts{

    private BillRemoteRemo remoteRemo;
    private BillConstacts.View view;

    public BillPresenter(BillConstacts.View view){
        remoteRemo=BillRemoteRemo.newInstance();
        this.view=view;
    }
    @Override
    public void billAction() {
        view.onBillStart();
        remoteRemo.reqBill(this);
    }

    @Override
    public void getBillSuccess(ResponseModel<String> result) {
        view.onBillSuccess(result);
        view.onBillEnd();
    }

    @Override
    public void getBillFailure(String error) {
        view.onBillFailure(error);
        view.onBillEnd();
    }


    @Override
    public String getCode() {
        return view.getCode();
    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getBusinessNO();
    }
}
