package com.yunwei.easyDear.function.mainFuncations.mineFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.function.account.LoginRegistActivity;
import com.yunwei.easyDear.function.account.LoginRegistPagerViewPagerAdapter;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.adapter.BusinessAdapter;
import com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.MyOrderActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:我的主界面
 * @date 2016/11/22 18:12
 */

public class MineFragment extends BaseFragment {

    private static MineFragment fragment;
    @BindView(R.id.mineFragment_login_rigest_layout)
    LinearLayout mineFragmentLoginRigestLayout;
    @BindView(R.id.mineFragment_user_nick_tv)
    TextView mineFragmentUserNickTv;
    @BindView(R.id.mineFragment_user_headview_iv)
    ImageView mineFragmentUserHeadviewIv;
    @BindView(R.id.mineFragment_consumer_coupon_iv)
    ImageView mineFragmentConsumerCouponIv;
    @BindView(R.id.mineFragment_consumer_coupon_text)
    TextView mineFragmentConsumerCouponText;
    @BindView(R.id.mineFragment_consumer_coupon_container)
    RelativeLayout mineFragmentConsumerCouponContainer;
    @BindView(R.id.mineFragment_equity_merchant_iv)
    ImageView mineFragmentEquityMerchantIv;
    @BindView(R.id.mineFragment_equity_merchant_text)
    TextView mineFragmentEquityMerchantText;
    @BindView(R.id.mineFragment_equity_merchant_container)
    RelativeLayout mineFragmentEquityMerchantContainer;
    @BindView(R.id.mineFragment_userInfo_layout)
    RelativeLayout mineFragmentUserInfoLayout;
    @BindView(R.id.mineFragment_business_gridView)
    GridView mineFragmentBusinessGridView;
    @BindView(R.id.mineFragment_tontact_layout)
    RelativeLayout mineFragmentTontactLayout;

    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_mine, null);
        ButterKnife.bind(this, rootView);
        initGridView();
        return rootView;
    }

    /**
     * 初始化会员默认列表
     */
    private void initGridView(){
        mineFragmentBusinessGridView.setAdapter(new BusinessAdapter(getActivity()));
    }

    @OnClick({R.id.mineFragment_login_btn, R.id.mineFragment_regist_btn, R.id.mineFragment_setting_tv, R.id.mineFragment_all_order_layout, R.id.mineFragment_all_business_layout, R.id.mineFragment_tontact_layout, R.id.mineFragment_into_business_layout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mineFragment_login_btn:
                LoginRegistActivity.startIntent(getActivity(), LoginRegistPagerViewPagerAdapter.VALUE_LOGIN);
                break;
            case R.id.mineFragment_regist_btn:
                LoginRegistActivity.startIntent(getActivity(), LoginRegistPagerViewPagerAdapter.VALUE_REGIST);
                break;
            case R.id.mineFragment_setting_tv:
                break;
            case R.id.mineFragment_all_order_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.mineFragment_all_business_layout:
                break;
            case R.id.mineFragment_tontact_layout:
                break;
            case R.id.mineFragment_into_business_layout:
                break;
        }
    }
}
