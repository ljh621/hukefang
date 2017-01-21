package com.yunwei.easyDear.function.account;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.common.dialog.DialogFactory;
import com.yunwei.easyDear.common.dialog.ToastUtil;
import com.yunwei.easyDear.function.account.data.soure.LoginRemoteRepo;
import com.yunwei.easyDear.function.mainFuncations.MainActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.IUtil;
import com.yunwei.easyDear.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-01-08
 * Time: 09:13
 * Version:1.0
 */

public class RegistFragment extends BaseFragment implements AccountContract.RegistView, AccountContract.validateView {

    @BindView(R.id.registFragment_account_editView)
    ResetEditView registFragmentAccountEditView;
    @BindView(R.id.registFragment_password_editView)
    ResetEditView registFragmentPasswordEditView;
    @BindView(R.id.registFragment_validateCode_editText)
    EditText validateCodeEditView;

    private Dialog loadDialog;

    private String validateCode;

    private LoginPresenter loginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frgment_regist_layout, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.registFragment_login_button, R.id.registFragment_send_vaildate_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registFragment_login_button:
                if (TextUtils.isEmpty(registFragmentAccountEditView.getText().toString())) {
                    ToastUtil.showToast(getActivity(), "手机号不能为空");
                    return;
                }
                if (TextUtils.isEmpty(registFragmentPasswordEditView.getText().toString())) {
                    ToastUtil.showToast(getActivity(), "密码不能为空");
                    return;
                }
                if (TextUtils.isEmpty(validateCodeEditView.getText().toString())) {
                    ToastUtil.showToast(getActivity(), "验证码不能为空");
                    return;
                }
                if (!validateCode.equals(validateCodeEditView.getText().toString())) {
                    ToastUtil.showToast(getActivity(), "验证码不正确");
                    return;
                }
                loginPresenter = new LoginPresenter(LoginRemoteRepo.newInstance(), this);
                loginPresenter.regist();
                break;
            case R.id.registFragment_send_vaildate_code:
                if (TextUtils.isEmpty(registFragmentAccountEditView.getText().toString())) {
                    ToastUtil.showToast(getActivity(), "手机号不能为空");
                    return;
                }
                loginPresenter = new LoginPresenter(this);
                loginPresenter.sendValidateCode();
                break;
        }
    }

    @Override
    public void showDialog() {
        loadDialog = DialogFactory.createLoadingDialog(getActivity(), "注册...");
    }

    @Override
    public void dismissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void registSuccess() {
        ToastUtil.showToast(getActivity(), "注册成功");
        ISkipActivityUtil.startIntent(getActivity(), MainActivity.class);
    }

    @Override
    public void registFailure(String error) {
        ToastUtil.showToast(getActivity(), error);
    }

    @Override
    public String getMobile() {
        return registFragmentAccountEditView.getText().toString();
    }

    @Override
    public String getPassword() {
        return registFragmentPasswordEditView.getText().toString();
    }

    @Override
    public String getMobileKey() {
        return IUtil.getIMEI(getActivity());
    }

    @Override
    public void onStartSendValidateCode() {
        loadDialog=DialogFactory.createLoadingDialog(getActivity(),"正在发送...");
    }

    @Override
    public void onEndSendValidateCode() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void getValidateCodeSuccess(String code) {
        validateCode = code;
        ToastUtil.showToast(getActivity(), "验证码已发送");
    }

    @Override
    public void getValidateCodeFailure(String error) {
        ToastUtil.showToast(getActivity(), error);
    }
}
