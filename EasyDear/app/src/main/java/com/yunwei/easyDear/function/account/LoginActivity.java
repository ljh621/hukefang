package com.yunwei.easyDear.function.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.dialog.DialogFactory;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.account.data.soure.LoginRemoteRepo;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.MainActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.ISpfUtil;
import com.yunwei.easyDear.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:登录界面
 * @date 2016/11/25 14:02
 */

public class LoginActivity extends BaseActivity implements AccountContract.LoginView {

    @BindView(R.id.loginActivity_account_editView)
    ResetEditView loginActivityAccountEditView;
    @BindView(R.id.loginActivity_password_editView)
    ResetEditView loginActivityPasswordEditView;
    @BindView(R.id.loginActivity_login_button)
    Button loginActivityLoginButton;

    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();
        mLoginPresenter = new LoginPresenter(LoginRemoteRepo.newInstance(), this);
    }

    /**
     * 初始化界面
     */
    private void initUI() {
        String account = ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString();
        String pwd = ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString();
        loginActivityAccountEditView.setText(account);
        loginActivityPasswordEditView.setText(pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.cancelRequest();
    }

    @OnClick(R.id.loginActivity_login_button)
    public void onClick() {
        loginAction();
//        ISkipActivityUtil.startIntent(this,MainActivity.class);
    }

    private void loginAction() {
        if (TextUtils.isEmpty(loginActivityAccountEditView.getText())) {
            showToast(R.string.account_not_null);
            return;
        }
        if (TextUtils.isEmpty(loginActivityPasswordEditView.getText())) {
            showToast(R.string.pwd_not_null);
            return;
        }
        mLoginPresenter.login();
    }

    @Override
    public void showDialog() {
        loadDialog = DialogFactory.createLoadingDialog(this, R.string.login_dialog);
    }

    @Override
    public void dismissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void loginSuccess(UserInfoEntity entity) {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
        finish();
    }

    @Override
    public void loginFailure(String error) {
        showToast(error);
    }

    @Override
    public String getAccount() {
        return loginActivityAccountEditView.getText();
    }

    @Override
    public String getPassword() {
        return loginActivityPasswordEditView.getText();
    }
}
