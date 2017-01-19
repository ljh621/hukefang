package com.jingan.easydearbusiness.function.verificationFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;
import com.jingan.easydearbusiness.base.BaseRecyclerViewAdapter;
import com.jingan.easydearbusiness.base.DataApplication;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.source.RequestVerficationRemoteRepo;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;
import com.jingan.easydearbusiness.utils.IUtil;
import com.jingan.easydearbusiness.utils.ToastUtil;
import com.jingan.easydearbusiness.vender.eventBus.EventConstant;
import com.jingan.easydearbusiness.vender.eventBus.NoticeEvent;
import com.jingan.easydearbusiness.view.PullToRefreshRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:账单
 * @date 2017/1/1 11:48
 */

public class VerficationFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener, VerficationContract.View, BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    private final int DEFULT_PAGE_COUNT = 20;

    @BindView(R.id.verficationFragment_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;
    @BindView(R.id.verficationFragment_empty_textView)
    TextView emptyTextView;

    private VerficationAdapter adapter;

    private VerficationPresenter presenter;
    /*页数默认从1开始*/
    private int pageSize = 1;
    /*日期*/
    private String data;

    private static VerficationFragment fragment;

    public static VerficationFragment newInstance() {
        if (fragment == null) {
            fragment = new VerficationFragment();
        }
        return fragment;
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 0x401:
                List<VerficationEntity> entities = (List<VerficationEntity>) msg.obj;
                if (adapter.getLoadState() == BaseRecyclerViewAdapter.LOADING_MORE) {
                    adapter.addItems(entities, adapter.getItemCount() - 1);
                } else {
                    adapter.clearList();
                    if (entities != null && entities.size() > 0) {
                        emptyTextView.setVisibility(View.GONE);
                        adapter.addItems(entities);
                    } else {
                        emptyTextView.setVisibility(View.VISIBLE);
                    }
                    mRecyclerView.closeDownRefresh();
                }
                mRecyclerView.setLoading(false);
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new VerficationAdapter(getContext());
        presenter = new VerficationPresenter(RequestVerficationRemoteRepo.newInstance(), this);
        data = IUtil.formatDate(System.currentTimeMillis(), "yyyy-MM");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.verfication_fragment, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.BOTH);
        mRecyclerView.startUpRefresh();
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void onDownRefresh() {
        presenter.downRefreshActivon();
    }

    @Override
    public void onPullRefresh() {
        presenter.pullRefreshAction();
    }

    @Override
    public void onItemClick(View view, Object data, int position) {
//        ToastUtil.showToast(getContext(), position + "");
        Bundle bundle = new Bundle();
        bundle.putString("billNo", adapter.getList().get(position).getBillNo());
        ISkipActivityUtil.startIntent(getActivity(), VerficationDetailedActivity.class, bundle);
    }

    @Override
    public void downRefreshFailure() {

    }

    @Override
    public void downRefreshSuccess(List<VerficationEntity> entities) {
        Message msg = new Message();
        msg.what = 0x401;
        msg.obj = entities;
        mHandler.sendMessage(msg);
    }

    @Override
    public void pullRefreshSuccess(List<VerficationEntity> entities) {
        Message msg = new Message();
        msg.what = 0x401;
        msg.obj = entities;
        mHandler.sendMessage(msg);
    }

    @Override
    public void pullRefreshFailure() {

    }

    @Override
    public String getBusinessNo() {
        return DataApplication.getInstance().getUserInfoEntity().getBusinessNO();
    }

    @Override
    public int getPageCount() {
        return DEFULT_PAGE_COUNT;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public String getDate() {
        return data;
    }

    @Override
    public void onMainThreadUserEvent(NoticeEvent event) {
        super.onMainThreadUserEvent(event);
        switch (event.getFlag()) {
            case EventConstant.NOTICE1:
                mRecyclerView.startUpRefresh();
                data = event.getObj().toString();
                presenter.downRefreshActivon();
                break;
        }
    }
}
