package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.common.eventbus.EventConstant;
import com.yunwei.easyDear.common.eventbus.NoticeEvent;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easyDear.function.mainFuncations.findFuncation.data.ItemEntity;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

public class ChildTabContentFragment extends BaseFragment implements ChildTabContact.ChileTabView, PullToRefreshRecyclerView.PullToRefreshRecyclerViewListener {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tab_child_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private ChildTabContentAdapter adapter;
    private ChildTabPresenter mChildTabPresenter;

    private String name;
    private String[] mTabNames;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getArguments().getString("name");
        mTabNames = getResources().getStringArray(R.array.tab_tiltle);
        adapter = new ChildTabContentAdapter(getActivity());
        EventBus.getDefault().register(this);
        initPresenter();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_child_layout, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mChildTabPresenter = new ChildTabPresenter(ChildTabRemoteRepo.getInstance(), this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mRecyclerView.setPullToRefreshListener(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
        mRecyclerView.setMode(PullToRefreshRecyclerView.Mode.BOTH);
        mRecyclerView.startUpRefresh();
    }

    /**
     * EventBus 通知获取文章列表
     */
    @Subscribe
    public void onEventMainThread(NoticeEvent event) {
        if (event.getFlag() == EventConstant.NOTICE12) {
            int tabPosition = event.getNum();
            String tabName = mTabNames[tabPosition];
            Log.d(TAG, "ChildTab name = " + tabName);
            requestRecyclerArticles(tabName);
//            mHandler.sendEmptyMessageDelayed(0x101, 300);
        }
    }

    /**
     *  获取文章列表
     */
    public void requestRecyclerArticles(String type) {
        mChildTabPresenter.requestRecyclerArticles(type);
    }

    public void setArticleList(ArrayList<ArticleItemEntity> items) {
        if (items != null) {
            ILog.v(TAG, "ArticleList size:  " + items.size());

            Message message = new Message();
            message.what = 0x401;
            message.obj = items;
            mHandler.sendMessageDelayed(message, 6000);
        }
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 0x401:
                List<ArticleItemEntity> entities = (List<ArticleItemEntity>) msg.obj;
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
            case 0x101:
               ILog.d(TAG, "----------> dispatchMessage  msg.what = " + msg.what);
                if (mHandler.hasMessages(msg.what)) {
                    ILog.d(TAG, "----------> dispatchMessage hasMessages msg.what = " + msg.what);
                    mHandler.removeMessages(msg.what);
                }
                break;
        }
    }

    @Override
    public void onDownRefresh() {
        List<ArticleItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ArticleItemEntity entity = new ArticleItemEntity();
            entity.setContent(name + (i + 1));
            entity.setLogo("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entity.setArticleImage("http://pic38.nipic.com/20140228/3822951_135521683000_2.jpg");
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 4000);
    }

    @Override
    public void onPullRefresh() {
        List<ArticleItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArticleItemEntity entity = new ArticleItemEntity();
            entity.setContent(name + (i + 1));
            entity.setLogo("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entity.setArticleImage("http://pic38.nipic.com/20140228/3822951_135521683000_2.jpg");
            list.add(entity);
        }
        Message message = new Message();
        message.what = 0x401;
        message.obj = list;
        mHandler.sendMessageDelayed(message, 4000);
    }

}
