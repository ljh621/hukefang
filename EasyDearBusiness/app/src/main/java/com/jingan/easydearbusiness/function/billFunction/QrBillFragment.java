package com.jingan.easydearbusiness.function.billFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.billFunction
 * @Description:
 * @date 2017/1/1 12:13
 */

public class QrBillFragment extends BaseFragment {

    private static QrBillFragment fragment;

    public static QrBillFragment newInstance(){
        if (fragment==null){
            fragment=new QrBillFragment();
        }
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.qr_bill_fragment,null);
        return rootView;
    }
}
