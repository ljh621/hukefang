package com.yunwei.easyDear.function.mainFuncations.qrcode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easyDear.base.BaseFragment;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 13:25
 * Version:1.0
 */

public class ScanQrFragment extends BaseFragment {

    private static ScanQrFragment fragment;
    public static ScanQrFragment newInstance(){
        if (fragment==null){
            fragment=new ScanQrFragment();
        }
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
