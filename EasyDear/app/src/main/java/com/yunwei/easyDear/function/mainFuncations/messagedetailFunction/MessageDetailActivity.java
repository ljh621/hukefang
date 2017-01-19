package com.yunwei.easyDear.function.mainFuncations.messagedetailFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/16.
 */

public class MessageDetailActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.message_detail_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_message_detail);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        List<MessageDetailItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MessageDetailItemEntity entity = new MessageDetailItemEntity();
            if (i < 3) {
                list.add(entity);
            } else {
                entity.setMessageContent("感谢您成为中村屋会员");
                entity.setMessageTime("10:12");
                entity.setMessageConsumeInfo("有效期至2017-1-31");
                list.add(entity);
            }
        }
        MessageDetailAdapter adapter = new MessageDetailAdapter(this);
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }

    @OnClick({R.id.message_detail_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_detail_back:
                onBackPressed();
                break;
        }
    }

}
