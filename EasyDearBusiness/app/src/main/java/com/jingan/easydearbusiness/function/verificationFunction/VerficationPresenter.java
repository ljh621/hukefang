package com.jingan.easydearbusiness.function.verificationFunction;

import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.source.RequestVerficationDataSource;
import com.jingan.easydearbusiness.function.verificationFunction.data.source.RequestVerficationRemoteRepo;

import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:
 * @date 2017/1/1 15:07
 */

public class VerficationPresenter implements RequestVerficationDataSource.DownRefreshCallBack, RequestVerficationDataSource.PullRefreshCallBack, VerficationContract.Presenter {

    private RequestVerficationRemoteRepo remoteRepo;
    private VerficationContract.View mView;

    public VerficationPresenter(RequestVerficationRemoteRepo remoteRepo, VerficationContract.View view) {
        this.remoteRepo = remoteRepo;
        this.mView = view;
    }

    @Override
    public void downRefreshActivon() {
        remoteRepo.downRefresh(this);
    }

    @Override
    public void pullRefreshAction() {
        remoteRepo.pullRefresh(this);
    }

    @Override
    public void getDownRefreshSuccess(List<VerficationEntity> entities) {
        mView.downRefreshSuccess(entities);
    }

    @Override
    public void getDownRefreshFailure() {
        mView.downRefreshFailure();
    }

    @Override
    public void getPullRefreshSuccess(List<VerficationEntity> entities) {
        mView.pullRefreshSuccess(entities);
    }

    @Override
    public void getPullRefreshFailure() {
        mView.pullRefreshFailure();
    }
}
