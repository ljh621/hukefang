package com.jingan.easydearbusiness.function.verificationFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;
import com.jingan.easydearbusiness.base.BaseRecyclerViewAdapter;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.source.RequestVerficationRemoteRepo;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;
import com.jingan.easydearbusiness.utils.ToastUtil;
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


    @BindView(R.id.verficationFragment_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private VerficationAdapter adapter;

    private VerficationPresenter presenter;

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
                    adapter.addItems(entities);
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.verfication_fragment, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
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
        ISkipActivityUtil.startIntent(getActivity(),VerficationDetailedActivity.class);
    }

    @Override
    public void downRefreshFailure() {

    }

    @Override
    public void downRefreshSuccess(List<VerficationEntity> entities) {
        Message msg = new Message();
        msg.what = 0x401;
        msg.obj = entities;
        mHandler.sendMessageDelayed(msg, 5000);
    }

    @Override
    public void pullRefreshSuccess(List<VerficationEntity> entities) {
        Message msg = new Message();
        msg.what = 0x401;
        msg.obj = entities;
        mHandler.sendMessageDelayed(msg, 3000);
    }

    @Override
    public void pullRefreshFailure() {

    }
}
