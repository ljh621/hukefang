package com.jingan.easydearbusiness.function.billFunction;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.jingan.easydearbusiness.base.BaseFragment;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.billFunction
 * @Description:
 * @date 2017/1/1 12:09
 */

public class BillFragmentViewPagerAdapter extends FragmentPagerAdapter {
    public final static int TAB_NUMBER_VALUE = 0;
    public final static int TAB_QR_VALUE = 1;

    public BillFragmentViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        BaseFragment fragment = null;
        switch (position) {
            case TAB_NUMBER_VALUE:
                fragment = NumberBillFragment.newInstance();
                break;
            case TAB_QR_VALUE:
                fragment = QrBillFragment.newInstance();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
