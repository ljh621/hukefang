package com.yunwei.easyDear.function.mainFuncations.messageFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.function.mainFuncations.messageFuncation.data.ItemEntity;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:消息模块
 * @date 2016/11/22 18:12
 */

public class MessageFragment extends BaseFragment {

    @BindView(R.id.msg_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private static MessageFragment fragment;


    public static MessageFragment newInstance() {
        if (fragment == null) {
            fragment = new MessageFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_record, null);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        return rootView;
    }

    private void initRecyclerView() {
        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemEntity entity = new ItemEntity();
            if (i < 2) {
                entity.setTime("上午8:16");
                entity.setType("消费券信息");
                entity.setContent("您有5张一点点奶茶消费券入账，立即查看>>");
                entity.setContentUrl("http://cdn.duitang.com/uploads/people/201207/24/20120724112116_yksB4.jpeg");
                list.add(entity);
            } else {
                entity.setTime("下午4:30");
                entity.setType("中村屋-南部商业区店");
                entity.setContent("感谢您的评论，欢迎下次光临");
                entity.setContentUrl("http://img4.imgtn.bdimg.com/it/u=3081843971,3565251645&fm=23&gp=0.jpg");
                list.add(entity);
            }
        }
        MessageContentAdapter adapter = new MessageContentAdapter(getActivity());
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
