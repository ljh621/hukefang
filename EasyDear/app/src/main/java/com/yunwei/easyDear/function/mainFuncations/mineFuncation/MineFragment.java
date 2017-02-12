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

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.base.DataApplication;
import com.yunwei.easyDear.common.dialog.CommPopupWindow;
import com.yunwei.easyDear.function.account.LoginRegistActivity;
import com.yunwei.easyDear.function.account.LoginRegistPagerViewPagerAdapter;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.adapter.BusinessAdapter;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.BusinessContract;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.MemberBusinessPresenter;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.MyMemberActivity;
import com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction.data.BusinessEntity;
import com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.MyOrderActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import java.util.List;

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

public class MineFragment extends BaseFragment implements BusinessContract.BusinessView {

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

    private CommPopupWindow commPopupWindow;

    private MemberBusinessPresenter memberBusinessPresenter;

    private BusinessAdapter businessAdapter;

    public static MineFragment newInstance() {
        if (fragment == null) {
            fragment = new MineFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        memberBusinessPresenter = new MemberBusinessPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_mine, null);
        ButterKnife.bind(this, rootView);
        initGridView();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUI();
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null) {
            mineFragmentLoginRigestLayout.setVisibility(View.VISIBLE);
            mineFragmentUserInfoLayout.setVisibility(View.GONE);
            return;
        }
        mineFragmentUserInfoLayout.setVisibility(View.VISIBLE);
        mineFragmentLoginRigestLayout.setVisibility(View.GONE);
        mineFragmentUserNickTv.setText(entity.getNickName());
        Glide.with(getActivity()).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(mineFragmentUserHeadviewIv));
    }

    /**
     * 初始化会员默认列表
     */
    private void initGridView() {
        businessAdapter = new BusinessAdapter(getActivity());
        mineFragmentBusinessGridView.setAdapter(businessAdapter);
        memberBusinessPresenter.reqBusinessListAction();
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
                ISkipActivityUtil.startIntent(getActivity(), SetingInfoActivity.class);
                break;
            case R.id.mineFragment_all_order_layout:
                ISkipActivityUtil.startIntent(getActivity(), MyOrderActivity.class);
                break;
            case R.id.mineFragment_all_business_layout:/*会员商家*/
                ISkipActivityUtil.startIntent(getActivity(), MyMemberActivity.class);
                break;
            case R.id.mineFragment_tontact_layout:/*联系客户*/
                View tontactView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tontact_layout, null);
                tontactView.findViewById(R.id.dialog_tontact_calcel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commPopupWindow.dismiss();
                    }
                });
                commPopupWindow = new CommPopupWindow(tontactView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                commPopupWindow.showAtButton(getView());
                break;
            case R.id.mineFragment_into_business_layout:/*联系商家*/
                View businessView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_tontact_layout, null);
                businessView.findViewById(R.id.dialog_tontact_calcel_btn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        commPopupWindow.dismiss();
                    }
                });
                commPopupWindow = new CommPopupWindow(businessView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                commPopupWindow.showAtButton(getView());
                break;
        }
    }

    @Override
    public void onBusinessStart() {

    }

    @Override
    public void onBusinessEnd() {

    }

    @Override
    public void onBusinessSuccess(List<BusinessEntity> list) {
        businessAdapter.addData(list);
    }

    @Override
    public void onBusinessFaliure(String error) {

    }

    @Override
    public String getUserNo() {
        return DataApplication.getInstance().getUserInfoEntity().getUserNo();
    }

    @Override
    public int getPageSize() {
        return 1;
    }

    @Override
    public int getPageCount() {
        return 4;
    }
}
