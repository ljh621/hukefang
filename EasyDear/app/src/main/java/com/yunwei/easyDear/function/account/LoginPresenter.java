package com.yunwei.easyDear.function.account;

import com.google.gson.Gson;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.account.data.soure.LoginDataSoure;
import com.yunwei.easyDear.function.account.data.soure.LoginRemoteRepo;
import com.yunwei.easyDear.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account
 * @Description:登录Presenter
 * @date 2016/11/29 15:21
 */

public class LoginPresenter implements LoginDataSoure.LoginCallBack, AccountContract.Presenter, LoginDataSoure.RigestCallBack, LoginDataSoure.ValidateCallBack {

    private AccountContract.LoginView loginView;
    private AccountContract.RegistView registView;
    private AccountContract.validateView validateView;
    private LoginDataSoure remoteRepo;

    public LoginPresenter(LoginRemoteRepo remoteRepo, AccountContract.LoginView loginView) {
        this.loginView = loginView;
        this.remoteRepo = remoteRepo;
    }

    public LoginPresenter(LoginRemoteRepo remoteRepo, AccountContract.RegistView registView) {
        this.remoteRepo = remoteRepo;
        this.registView = registView;
    }

    public LoginPresenter(AccountContract.validateView validateView) {
        this.remoteRepo = LoginRemoteRepo.newInstance();
        this.validateView = validateView;
    }

    @Override
    public void login() {
        loginView.showDialog();
        remoteRepo.login(loginView.getAccount(), loginView.getPassword(), this);
    }

    @Override
    public void regist() {
        registView.showDialog();
        remoteRepo.rigest(this);
    }

    @Override
    public void sendValidateCode() {
        validateView.onStartSendValidateCode();
        remoteRepo.sendValidateCode(this);
    }

    @Override
    public void onLoginSuccess(UserInfoEntity entity) {
        if (entity != null) {
            /*数据本地化*/
            ISpfUtil.setValue(Constant.ACCOUNT_KEY, loginView.getAccount());
            ISpfUtil.setValue(Constant.PSSWORD_KEY, loginView.getPassword());
            ISpfUtil.setValue(Constant.USERINFO_KEY, new Gson().toJson(entity));
        }
        loginView.loginSuccess(entity);
        loginView.dismissDialog();
    }

    @Override
    public void onLoginFailure(String error) {
        loginView.loginFailure(error);
        loginView.dismissDialog();
    }

    /**
     * 取消请求
     */
    public void cancelRequest() {
        remoteRepo.cancelRequest();
    }

    @Override
    public void onRigestSuccess(UserInfoEntity entity) {
        if (entity != null) {
            /*数据本地化*/
            ISpfUtil.setValue(Constant.ACCOUNT_KEY, registView.getMobile());
            ISpfUtil.setValue(Constant.PSSWORD_KEY, registView.getPassword());
            ISpfUtil.setValue(Constant.USERINFO_KEY, new Gson().toJson(entity));
        }
        registView.registSuccess();
        registView.dismissDialog();
    }

    @Override
    public void onRigestFailure(String error) {
        registView.registFailure(error);
        registView.dismissDialog();
    }

    @Override
    public String getMobile() {
        return registView.getMobile();
    }

    @Override
    public String getPassword() {
        return registView.getPassword();
    }

    @Override
    public String getMobileKey() {
        return registView.getMobileKey();
    }

    @Override
    public void onValidateSuccess(String code) {
        validateView.getValidateCodeSuccess(code);
        validateView.onEndSendValidateCode();
    }

    @Override
    public void onValidateFailure(String error) {
        validateView.getValidateCodeFailure(error);
        validateView.onEndSendValidateCode();
    }

    @Override
    public String getSendMobile() {
        return validateView.getMobile();
    }
}
