package com.jingan.easydearbusiness.function.billFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.billFunction
 * @Description:
 * @date 2017/1/1 11:48
 */

public class BillFragment extends BaseFragment implements ViewPager.OnPageChangeListener {

    @BindView(R.id.bill_Fragment_ViewPager)
    ViewPager mViewPager;
    @BindView(R.id.BillFragment_Number_tab_TextView)
    TextView mNumberTabTextView;
    @BindView(R.id.BillFragment_qr_tab_TextView)
    TextView mQrTabTextView;

    private static BillFragment fragment;


    public static BillFragment newInstance() {
        if (fragment == null) {
            fragment = new BillFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.bill_fragment_number, null);
        ButterKnife.bind(this, rootView);
        initViewPager();
        return rootView;
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        BillFragmentViewPagerAdapter adapter = new BillFragmentViewPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(this);
        switchTab(BillFragmentViewPagerAdapter.TAB_NUMBER_VALUE);
    }

    @OnClick({R.id.BillFragment_Number_tab_TextView, R.id.BillFragment_qr_tab_TextView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BillFragment_Number_tab_TextView:
                switchTab(BillFragmentViewPagerAdapter.TAB_NUMBER_VALUE);
                break;
            case R.id.BillFragment_qr_tab_TextView:
                switchTab(BillFragmentViewPagerAdapter.TAB_QR_VALUE);
                break;
        }
    }

    private void switchTab(int tabPosition) {
        switch (tabPosition) {
            case BillFragmentViewPagerAdapter.TAB_NUMBER_VALUE:
                mNumberTabTextView.setTextColor(getResources().getColor(R.color.white));
                mQrTabTextView.setTextColor(getResources().getColor(R.color.gray));

                mNumberTabTextView.setBackgroundResource(R.color.red);
                mQrTabTextView.setBackgroundResource(R.color.white);

//                mNumberTabTextView.setTextSize(R.dimen.bill_tab_selecte_textSize);
//                mQrTabTextView.setTextSize(R.dimen.bill_tab_default_textSize);
                break;
            case BillFragmentViewPagerAdapter.TAB_QR_VALUE:

                mNumberTabTextView.setTextColor(getResources().getColor(R.color.gray));
                mQrTabTextView.setTextColor(getResources().getColor(R.color.white));

                mNumberTabTextView.setBackgroundResource(R.color.white);
                mQrTabTextView.setBackgroundResource(R.color.red);

//                mNumberTabTextView.setTextSize(R.dimen.bill_tab_default_textSize);
//                mQrTabTextView.setTextSize(R.dimen.bill_tab_selecte_textSize);
                break;
        }
        mViewPager.setCurrentItem(tabPosition);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switchTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
