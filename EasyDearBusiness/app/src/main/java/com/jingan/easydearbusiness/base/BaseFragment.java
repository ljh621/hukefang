package com.jingan.easydearbusiness.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.jingan.easydearbusiness.common.handler.BaseHandler;
import com.jingan.easydearbusiness.vender.eventBus.NoticeEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:基类Fragment
 * @date 2016/11/22 14:57
 */

public class BaseFragment extends Fragment {

    /*
    * 消息处理Handler
    */
    protected BaseHandler mHandler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        EventBus.getDefault().register(this);
    }

    /**
     * 初始化Handler
     */
    private void initHandler() {
        mHandler = new BaseHandler(getActivity()) {
            @Override
            public void handleMessage(Message msg) {
                BaseFragment.this.dispatchMessage(msg);
            }
        };
    }

    /**
     * Handler事件分发处理
     *
     * @param msg
     */
    protected void dispatchMessage(Message msg) {
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBackGroundUserEvent(NoticeEvent event) {
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThreadUserEvent(NoticeEvent event){

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
