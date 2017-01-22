package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2016-12-24
 * Time: 12:15
 * Version:1.0
 */

public class FindViewPagerAdater extends FragmentPagerAdapter {

    private final int TAB_SELECTED=0;
    private final int TAB_DELICACY=1;
    private final int TAB_ENTERTAINMENT=2;
    private final int TAB_FASHION=3;
    private final int TAB_CAR=4;
    private final int TAB_WEDDING=5;

    private String[] tabNames;
    public FindViewPagerAdater(FragmentManager fm){
        super(fm);
    }

    public FindViewPagerAdater(FragmentManager fm,String[] tabNames){
        super(fm);
        this.tabNames=tabNames;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        Bundle bundle=new Bundle();
        bundle.putString("type",tabNames[position]);
        switch (position){
            case TAB_SELECTED:/*精选*/
                fragment=new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
            case TAB_DELICACY:/*美食*/
                fragment=new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
            case TAB_ENTERTAINMENT:/*娱乐*/
                fragment=new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
            case TAB_FASHION:/*时尚*/
                fragment=new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
            case TAB_CAR:/*爱车*/
                fragment =new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
            case TAB_WEDDING:/*婚礼*/
                fragment=new ChildTabContentFragment();
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }
}
