package com.jingan.easydearbusiness.function.billFunction;

import com.jingan.easydearbusiness.entity.ResponseModel;

/**
 * Describe:
 * Author: hezhiWu
 * De: 2017-01-16
 * Time: 23:44
 * Version:1.0
 */

public interface BillConstacts {

    interface View {
        void onBillStart();
        void onBillSuccess(ResponseModel<String> result);
        void onBillFailure(String error);
        void onBillEnd();
        String getCode();
    }

    void billAction();
}
