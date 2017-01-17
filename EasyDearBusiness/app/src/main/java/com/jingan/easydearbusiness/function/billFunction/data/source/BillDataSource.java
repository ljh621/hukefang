package com.jingan.easydearbusiness.function.billFunction.data.source;

import com.jingan.easydearbusiness.base.BaseDataSourse;
import com.jingan.easydearbusiness.entity.ResponseModel;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-16
 * Time: 23:46
 * Version:1.0
 */

public interface BillDataSource extends BaseDataSourse{

    interface BillCallBack{
        void getBillSuccess(ResponseModel<String> result);
        void getBillFailure(String error);
        String getCode();
        String getUserNo();
    }

    void reqBill(BillCallBack callBack);
}
