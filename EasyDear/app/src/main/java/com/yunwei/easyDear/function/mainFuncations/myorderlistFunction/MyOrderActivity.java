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
        MyOrderAdapter adapter = new MyOrderAdapter(this);
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
