package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction;

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

public class MyCardActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.mycard_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mycard);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    @OnClick({R.id.mycard_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mycard_back:
                onBackPressed();
                break;
        }
    }

    private void initRecyclerView() {
        List<CardItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CardItemEntity entity = new CardItemEntity();
            if (i < 2) {
                list.add(entity);
            } else {
                entity.setViceTitle("中杯饮品");
                entity.setCardName("星巴克咖啡");
                entity.setValidate("2017-3-12");
                list.add(entity);
            }
        }
        MyCardAdapter adapter = new MyCardAdapter(this);
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
