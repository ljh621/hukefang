package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.mycardlistFunction.CardItemEntity;
import com.yunwei.easyDear.function.mainFuncations.mycardlistFunction.MyCardAdapter;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyMemberActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.mymember_recyclerView)
    PullToRefreshRecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_mymember);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

        initRecyclerView();
    }

    @OnClick({R.id.mymember_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mymember_back:
                onBackPressed();
                break;
        }
    }

    private void initRecyclerView() {
        List<MemberItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            MemberItemEntity entity = new MemberItemEntity();
            if (i < 2) {
                list.add(entity);
            } else {
                entity.setBusinessName("人在茶在(印象城店)");
                entity.setLevel("V2");
                entity.setCardAmount("您有3张礼券");
                entity.setCredit("您距离会员升级还需680积分");
                list.add(entity);
            }
        }
        MyMemberAdapter adapter = new MyMemberAdapter(this);
        adapter.addItems(list);
        mRecyclerView.setRecyclerViewAdapter(adapter);
    }
}
