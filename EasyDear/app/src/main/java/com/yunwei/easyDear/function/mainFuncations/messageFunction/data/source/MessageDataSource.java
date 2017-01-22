package com.yunwei.easyDear.function.mainFuncations.messageFunction.data.source;

import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/22.
 */
public interface MessageDataSource {

    void requestBusMessages(String useNo, BusMsgCallBack callBack);

    interface BusMsgCallBack {
        void onReqBusMessagesSuccess(ArrayList<BusMessageItemEntity> data);

        void onReqBusMessagesFailure(String message);
    }

    interface MsgDetailCallBack {

        void getMsgSuccess(List<MessageDetailEntity> list);

        void getMsgFailure(String error);

        String getPageSize();

        String getPageCount();

        String getUserNo();
    }

    void reqMsgDetail(MsgDetailCallBack callBack);
}
