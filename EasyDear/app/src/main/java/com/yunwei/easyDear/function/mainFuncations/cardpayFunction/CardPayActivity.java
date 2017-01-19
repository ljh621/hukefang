package com.yunwei.easyDear.function.mainFuncations.cardpayFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.businessFunction.BusinessActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/17.
 */

public class CardPayActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_card_pay);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);

//        initRecyclerView();
    }


    @OnClick({R.id.card_pay_back, R.id.card_pay_backto_business})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_pay_back:
                onBackPressed();
                break;
            case R.id.card_pay_backto_business:
                ISkipActivityUtil.startIntent(this, BusinessActivity.class);
                break;
        }
    }
}
