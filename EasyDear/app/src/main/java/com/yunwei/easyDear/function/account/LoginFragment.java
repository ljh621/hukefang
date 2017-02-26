package com.yunwei.easyDear.function.account;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.base.DataApplication;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.dialog.DialogFactory;
import com.yunwei.easyDear.common.dialog.ToastUtil;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.account.data.soure.LoginRemoteRepo;
import com.yunwei.easyDear.function.mainFuncations.MainActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.ISpfUtil;
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

public class LoginFragment extends BaseFragment implements AccountContract.LoginView {

    @BindView(R.id.loginFragment_account_editView)
    ResetEditView loginFragmentAccountEditView;
    @BindView(R.id.loginFragment_password_editView)
    ResetEditView loginFragmentPasswordEditView;

    private LoginPresenter loginPresenter;

    private Dialog loadDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(LoginRemoteRepo.newInstance(), this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgment_login_layout, null);
        ButterKnife.bind(this, rootView);
        initUI();
        return rootView;
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null) {
            return;
        }
        loginFragmentAccountEditView.setText(ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString());
        loginFragmentPasswordEditView.setText(ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString());
    }

    @OnClick({R.id.loginFragment_forget_password_tv, R.id.loginFragment_login_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginFragment_forget_password_tv:
                ISkipActivityUtil.startIntent(getActivity(), RestPasswordActivity.class);
                break;
            case R.id.loginFragment_login_button:
                if (TextUtils.isEmpty(loginFragmentAccountEditView.getText().toString().toString())) {
                    ToastUtil.showToast(getActivity(), "账号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(loginFragmentPasswordEditView.getText().toString().trim())) {
                    ToastUtil.showToast(getActivity(), "密码不能为空");
                    return;
                }
                loginPresenter.login();
                break;
        }
    }

    @Override
    public void showDialog() {
        loadDialog = DialogFactory.createLoadingDialog(getActivity(), "登录...");
    }

    @Override
    public void dismissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void loginSuccess(UserInfoEntity entity) {
        ISkipActivityUtil.startIntent(getActivity(), MainActivity.class);
    }

    @Override
    public void loginFailure(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public String getAccount() {
        return loginFragmentAccountEditView.getText().toString();
    }

    @Override
    public String getPassword() {
        return loginFragmentPasswordEditView.getText().toString();
    }
}
