package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction;

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
 * Created by LJH on 2017/1/15.
 */

public class MyOrderActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.myorder_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_myorder);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    @OnClick({R.id.myorder_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.myorder_back:
                onBackPressed();
                break;
        }
    }

    private void initRecyclerView() {
        List<OrderItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            OrderItemEntity entity = new OrderItemEntity();
            if (i < 2) {
                list.add(entity);
            } else {
                entity.setBusinessName("星巴克咖啡");
                entity.setCardType("净饮双计");
                entity.setValidateDate("有效期至2017-1-31");
                entity.setCardPrice("¥30");
                entity.setCardAmount("90");
                list.add(entity);
            }
        }
        MyOrderAdapter adapter = new MyOrderAdapter(this);
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
