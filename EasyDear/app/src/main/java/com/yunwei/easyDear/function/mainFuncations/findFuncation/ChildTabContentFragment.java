package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.findFuncation.data.ItemEntity;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:选项卡内容模块
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 12:0
 * Version:1.0
 */

public class ChildTabContentFragment extends BaseFragment implements PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    @BindView(R.id.tab_child_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private ChildTabContentAdapter adapter;

    private String name;

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 0x401:
                List<ItemEntity> entities = (List<ItemEntity>) msg.obj;
                if (adapter.getLoadState() == BaseRecyclerViewAdapter.LOADING_MORE) {
                    adapter.addItems(entities, adapter.getItemCount() - 1);
                    adapter.setLoadState(BaseRecyclerViewAdapter.LOADING_MORE);
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
        name = getArguments().getString("name");
        adapter = new ChildTabContentAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_child_layout, null);
        ButterKnife.bind(this, rootView);
        initRecylerView();
        return rootView;
    }

    /**
     * 初始化RecylerView
     */
    private void initRecylerView() {
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.BOTH);
        mRecyclerView.startUpRefresh();
    }


    @Override
    public void onDownRefresh() {
        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setContent(name + (i + 1));
            entity.setHeadUrl("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entity.setContentUrl("http://www.shifuw.com/upload/150203100412_163.jpg");
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 5000);
    }

    @Override
    public void onPullRefresh() {
        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ItemEntity entity = new ItemEntity();
            entity.setContent(name + (i + 1));
            entity.setHeadUrl("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entity.setContentUrl("http://www.shifuw.com/upload/150203100412_163.jpg");
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 5000);
    }
}
