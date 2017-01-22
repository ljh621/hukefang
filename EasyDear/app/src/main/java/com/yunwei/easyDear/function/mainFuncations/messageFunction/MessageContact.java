package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/22.
 */

public interface MessageContact {
    interface MessageView {
        void setBusinessMessages(ArrayList<BusMessageItemEntity> data);
    }

    interface MessagePresenter {
        void requestBusMessages(String useNo);
    }
}
