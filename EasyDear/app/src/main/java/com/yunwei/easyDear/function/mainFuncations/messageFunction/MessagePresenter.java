package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/22.
 */

public class MessagePresenter implements MessageContact.MessagePresenter, MessageDataSource.BusMsgCallBack {

    private MessageContact.MessageView mMessageView;
    private MessageRemoteRepo mRemoteRepo;

    public MessagePresenter(MessageRemoteRepo remoteRepo, MessageFragment messageView) {
        mMessageView = messageView;
        mRemoteRepo = remoteRepo;
    }

    @Override
    public void requestBusMessages(String useNo) {
        mRemoteRepo.requestBusMessages(useNo, this);
    }

    @Override
    public void onReqBusMessagesSuccess(ArrayList<BusMessageItemEntity> data) {
        mMessageView.setBusinessMessages(data);
    }

    @Override
    public void onReqBusMessagesFailure(String message) {

    }
}
