package com.yunwei.easyDear.function.mainFuncations.cardDetailFunction;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.CardItemEntity;

/**
 * Created by LJH on 2017/1/21.
 */

public class CardDetailPresenter implements CardDetailContact.Presenter, CardDetailDataSource.CardDetailCallBack {

    private CardDetailContact.CardDetailView mCardDetailView;
    private CardDetailRemoteRepo mRemoteRepo;

    public CardDetailPresenter(CardDetailRemoteRepo remoteRepo, CardDetailContact.CardDetailView cardDetailView) {
        mRemoteRepo = remoteRepo;
        mCardDetailView = cardDetailView;
    }

    @Override
    public void requestCardDetail(String cardNo) {
        mRemoteRepo.requestCardDetail(cardNo, this);
    }

    @Override
    public void onReqCardDetailSuccess(CardItemEntity entity) {
        mCardDetailView.setCardDetailInfo(entity);
    }

    @Override
    public void onReqCardDetailFailure(String message) {

    }
}
