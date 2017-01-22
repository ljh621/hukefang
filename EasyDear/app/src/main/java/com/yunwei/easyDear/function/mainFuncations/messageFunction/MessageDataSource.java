package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/22.
 */
public interface MessageDataSource {
    
    void requestBusMessages(String useNo, BusMsgCallBack callBack);

    interface BusMsgCallBack {
        void onReqBusMessagesSuccess(ArrayList<BusMessageItemEntity> data);

        void onReqBusMessagesFailure(String message);
    }
}
