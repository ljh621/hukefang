package com.jingan.easydearbusiness.function.accountFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.common.dialog.DialogFactory;
import com.jingan.easydearbusiness.function.MainActivity;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;
import com.jingan.easydearbusiness.function.accountFunction.data.source.LoginRemoteRepo;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;
import com.jingan.easydearbusiness.utils.ISpfUtil;
import com.jingan.easydearbusiness.widget.ResetEditView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.accountFunction
 * @Description:登录界面
 * @date 2017/1/1 10:54
 */

public class LoginActivity extends BaseActivity implements AccountContract.LoginView {

    @BindView(R.id.loginActivity_account_editView)
    ResetEditView loginActivityAccountEditView;
    @BindView(R.id.loginActivity_password_editView)
    ResetEditView loginActivityPasswordEditView;
    private AccountPresenter mLoginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_login);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();
        mLoginPresenter = new AccountPresenter(LoginRemoteRepo.newInstance(), this);
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
