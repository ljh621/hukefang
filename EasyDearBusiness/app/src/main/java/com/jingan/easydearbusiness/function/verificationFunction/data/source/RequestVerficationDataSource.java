package com.jingan.easydearbusiness.function.verificationFunction.data.source;

import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction.data.source
 * @Description:
 * @date 2017/1/1 15:02
 */

public interface RequestVerficationDataSource {

    interface DownRefreshCallBack {
        void getDownRefreshSuccess(List<VerficationEntity> entities);

        void getDownRefreshFailure();
    }

    interface PullRefreshCallBack {
        void getPullRefreshSuccess(List<VerficationEntity> entities);

        void getPullRefreshFailure();
    }

    void downRefresh(DownRefreshCallBack callBack);

    void pullRefresh(PullRefreshCallBack callBack);
}
