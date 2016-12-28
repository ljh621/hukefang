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

public class LoginPresenter implements LoginDataSoure.LoginCallBack, AccountContract.Presenter {

    private AccountContract.LoginView loginView;
    private LoginDataSoure remoteRepo;

    public LoginPresenter(LoginRemoteRepo remoteRepo, AccountContract.LoginView loginView) {
        this.loginView = loginView;
        this.remoteRepo = remoteRepo;
    }

    @Override
    public void login() {
        loginView.showDialog();
        remoteRepo.login(loginView.getAccount(), loginView.getPassword(), this);
    }

    @Override
    public void onLoginSuccess(UserInfoEntity entity) {
        if (entity != null) {
            /*数据本地化*/
            ISpfUtil.setValue(Constant.ACCESS_TOKEN_KEY, entity.getAccess_token());
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
}
