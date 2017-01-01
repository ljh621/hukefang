package com.jingan.easydearbusiness.function.verificationFunction;

import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:
 * @date 2017/1/1 14:57
 */

public interface VerficationContract {

    interface View {
        void downRefreshSuccess(List<VerficationEntity> entities);

        void downRefreshFailure();

        void pullRefreshSuccess(List<VerficationEntity> entities);

        void pullRefreshFailure();
    }

    interface Presenter {
        void downRefreshActivon();

        void pullRefreshAction();
    }
}
