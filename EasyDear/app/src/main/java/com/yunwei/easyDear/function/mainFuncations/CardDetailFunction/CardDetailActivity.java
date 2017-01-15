package com.yunwei.easyDear.function.mainFuncations.cardDetailFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class CardDetailActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_card_detail);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
    }

    @OnClick({R.id.card_detail_back, R.id.card_purchase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_detail_back:
                onBackPressed();
                break;
            case R.id.card_purchase:
//                ISkipActivityUtil.startIntent(this, .class);
                break;
        }
    }
}
