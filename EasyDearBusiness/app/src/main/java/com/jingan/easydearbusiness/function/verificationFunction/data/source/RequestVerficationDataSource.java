package com.jingan.easydearbusiness.function.verificationFunction.data.source;

import com.jingan.easydearbusiness.base.BaseDataSourse;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationDetailEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction.data.source
 * @Description:
 * @date 2017/1/1 15:02
 */

public interface RequestVerficationDataSource extends BaseDataSourse {

    interface DownRefreshCallBack {
        void getDownRefreshSuccess(List<VerficationEntity> entities);

        void getDownRefreshFailure();

        String getBusinessNo();

        int pageSize();

        int pageCount();

        String getDate();
    }

    interface PullRefreshCallBack {
        void getPullRefreshSuccess(List<VerficationEntity> entities);

        void getPullRefreshFailure();

        String getBusinessNo();

        int pageSize();

        int pageCount();

        String getDate();
    }

    interface QueryVerficationDetailCallBack {
        void getDetailSuccess(List<VerficationDetailEntity> entities);

        void getDetailFailure(String error);

        String getBillNo();
    }

    void downRefresh(DownRefreshCallBack callBack);

    void pullRefresh(PullRefreshCallBack callBack);

    void queryVerficationDetail(QueryVerficationDetailCallBack callBack);
}
