package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.MessageDetailEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/22.
 */

public interface MessageContact {
    interface MessageView {
        void setBusinessMessages(ArrayList<BusMessageItemEntity> data);
    }

    interface MessageDetailView {
        void onMsgStart();

        void onMsgEnd();

        void onMsgSuccess(List<MessageDetailEntity> list);

        void onMsgFailure(int code, String error);

        int getPageSize();

        int getPageCount();

        String getUserNo();

        String getBusinessNo();
    }

    interface MessagePresenter {
        void requestBusMessages(String useNo);

        void reqMsgDetail();
    }
}
