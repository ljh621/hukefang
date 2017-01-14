package com.jingan.easydearbusiness.function.billFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.zxing.android.CaptureActivity;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;

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
    @BindView(R.id.numberBillFragment_serial_number_textView)
    TextView mNumberTextView;

    private StringBuffer sb;

    private static BillFragment fragment;


    public static BillFragment newInstance() {
        if (fragment == null) {
            fragment = new BillFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sb=new StringBuffer();
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
//        BillFragmentViewPagerAdapter adapter = new BillFragmentViewPagerAdapter(getChildFragmentManager());
//        mViewPager.setAdapter(adapter);
//        mViewPager.addOnPageChangeListener(this);
//        switchTab(BillFragmentViewPagerAdapter.TAB_NUMBER_VALUE);
    }

    @OnClick({R.id.BillFragment_Number_tab_TextView, R.id.BillFragment_qr_tab_TextView, R.id.number_delete, R.id.number_one, R.id.number_two, R.id.number_three, R.id.number_four, R.id.number_five, R.id.number_six, R.id.number_seven, R.id.number_eight, R.id.number_nine, R.id.number_zero, R.id.number_bill_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.BillFragment_Number_tab_TextView:
                switchTab(BillFragmentViewPagerAdapter.TAB_NUMBER_VALUE);
                break;
            case R.id.BillFragment_qr_tab_TextView:
                switchTab(BillFragmentViewPagerAdapter.TAB_QR_VALUE);
                break;
            case R.id.number_one:
                setNumberText("1");
                break;
            case R.id.number_two:
                setNumberText("2");
                break;
            case R.id.number_three:
                setNumberText("3");
                break;
            case R.id.number_four:
                setNumberText("4");
                break;
            case R.id.number_five:
                setNumberText("5");
                break;
            case R.id.number_six:
                setNumberText("6");
                break;
            case R.id.number_seven:
                setNumberText("7");
                break;
            case R.id.number_eight:
                setNumberText("8");
                break;
            case R.id.number_nine:
                setNumberText("9");
                break;
            case R.id.number_zero:
                setNumberText("0");
                break;
            case R.id.number_delete:
                setNumberText("delete");
                break;
            case R.id.number_bill_button:
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

//                mNumberTabTextView.setTextColor(getResources().getColor(R.color.gray));
//                mQrTabTextView.setTextColor(getResources().getColor(R.color.white));
//
//                mNumberTabTextView.setBackgroundResource(R.color.white);
//                mQrTabTextView.setBackgroundResource(R.color.red);

                ISkipActivityUtil.startIntent(getActivity(), CaptureActivity.class);
//                mNumberTabTextView.setTextSize(R.dimen.bill_tab_default_textSize);
//                mQrTabTextView.setTextSize(R.dimen.bill_tab_selecte_textSize);
                break;

        }
        mViewPager.setCurrentItem(tabPosition);
    }

    private void setNumberText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if ("delete".equals(text)) {
            if (!TextUtils.isEmpty(sb.toString())) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
        } else {
            sb.append(text);
        }
        mNumberTextView.setText(sb.toString());
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
