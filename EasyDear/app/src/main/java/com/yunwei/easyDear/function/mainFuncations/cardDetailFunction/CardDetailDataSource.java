package com.yunwei.easyDear.function.mainFuncations.CardDetailFunction;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.CardItemEntity;

/**
 * Created by LJH on 2017/1/21.
 */

public interface CardDetailDataSource {

    void requestCardDetail(String cardNo, CardDetailCallBack callBack);

    interface CardDetailCallBack {

        void onReqCardDetailSuccess(CardItemEntity data);

        void onReqCardDetailFailure(String message);
    }
}
