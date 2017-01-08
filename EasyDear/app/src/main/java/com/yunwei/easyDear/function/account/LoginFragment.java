package com.yunwei.easyDear.function.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 09:12
 * Version:1.0
 */

public class LoginFragment extends BaseFragment {

    @BindView(R.id.loginFragment_account_editView)
    ResetEditView loginFragmentAccountEditView;
    @BindView(R.id.loginFragment_password_editView)
    ResetEditView loginFragmentPasswordEditView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgment_login_layout, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.loginFragment_forget_password_tv, R.id.loginFragment_login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFragment_forget_password_tv:
                ISkipActivityUtil.startIntent(getActivity(),RestPasswordActivity.class);
                break;
            case R.id.loginFragment_login_button:
                break;
        }
    }
}
