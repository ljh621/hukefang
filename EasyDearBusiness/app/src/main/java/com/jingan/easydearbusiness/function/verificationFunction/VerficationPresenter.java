package com.jingan.easydearbusiness.function.verificationFunction;

import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationDetailEntity;
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

public class VerficationPresenter implements RequestVerficationDataSource.DownRefreshCallBack, RequestVerficationDataSource.PullRefreshCallBack, VerficationContract.Presenter, RequestVerficationDataSource.QueryVerficationDetailCallBack {

    private RequestVerficationRemoteRepo remoteRepo;
    private VerficationContract.View mView;

    private VerficationContract.VerficationDetailView verficationDetailView;

    public VerficationPresenter(RequestVerficationRemoteRepo remoteRepo, VerficationContract.View view) {
        this.remoteRepo = remoteRepo;
        this.mView = view;
    }

    public VerficationPresenter(VerficationContract.VerficationDetailView verficationDetailView) {
        this.remoteRepo = RequestVerficationRemoteRepo.newInstance();
        this.verficationDetailView = verficationDetailView;
    }

    @Override
    public void queryFacationDetail() {
        verficationDetailView.showDialog();
        remoteRepo.queryVerficationDetail(this);
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

    @Override
    public String getBusinessNo() {
        return mView.getBusinessNo();
    }

    @Override
    public int pageSize() {
        return mView.getPageSize();
    }

    @Override
    public int pageCount() {
        return mView.getPageCount();
    }

    @Override
    public String getDate() {
        return mView.getDate();
    }

    @Override
    public void getDetailSuccess(List<VerficationDetailEntity> entities) {
        verficationDetailView.queryVerficationDetailSuccess(entities);
        verficationDetailView.dimissDialog();
    }

    @Override
    public void getDetailFailure(String error) {
        verficationDetailView.queryVerficationDetailFailure(error);
        verficationDetailView.dimissDialog();
    }

    @Override
    public String getBillNo() {
        return verficationDetailView.getBillNo();
    }
}
