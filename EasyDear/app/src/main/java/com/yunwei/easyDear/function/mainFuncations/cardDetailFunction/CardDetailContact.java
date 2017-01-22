package com.yunwei.easyDear.function.mainFuncations.cardDetailFunction;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.CardItemEntity;

/**
 * Created by LJH on 2017/1/21.
 */

public interface CardDetailContact {

    interface CardDetailView {
        void setCardDetailInfo(CardItemEntity data);
    }

    interface Presenter {
        void requestCardDetail(String cardNo);
    }
}
