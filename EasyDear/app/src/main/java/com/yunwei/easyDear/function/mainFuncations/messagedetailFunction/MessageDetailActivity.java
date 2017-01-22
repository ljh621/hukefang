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

    private String businessName;
    private String businessNo;

    @BindView(R.id.message_detail_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    private MessageDetailAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_message_detail);
        businessName = getIntent().getStringExtra("businessName");
        businessNo = getIntent().getStringExtra("businessNo");
        setToolbarTitle("消息列表");
        ButterKnife.bind(this);

        initRecyclerView();
    }

    private void initRecyclerView() {
        adapter = new MessageDetailAdapter(this);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
