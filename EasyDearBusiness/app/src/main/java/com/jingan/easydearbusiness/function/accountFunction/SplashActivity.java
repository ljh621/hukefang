package com.jingan.easydearbusiness.function.accountFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.common.handler.HandlerValue;
import com.jingan.easydearbusiness.function.MainActivity;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;
import com.jingan.easydearbusiness.function.accountFunction.data.source.LoginRemoteRepo;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;
import com.jingan.easydearbusiness.utils.ISpfUtil;

import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.accountFunction
 * @Description:启动页
 * @date 2017/1/1 10:55
 */

public class SplashActivity extends BaseActivity implements AccountContract.LoginView{

    private AccountPresenter accountPresenter;
    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case HandlerValue.START_PAGE_DELAYED:
                ISkipActivityUtil.startIntent(this, LoginActivity.class);
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_splash);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);
//        Glide.with(this).load(R.mipmap.default_welcom).into(splashIv);
        /*重新登录获取新Token*/
        if (!TextUtils.isEmpty(ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString()) && !TextUtils.isEmpty(ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString())) {
            accountPresenter = new AccountPresenter(LoginRemoteRepo.newInstance(), this);
            accountPresenter.login();
        } else {
        mHandler.sendEmptyMessageDelayed(HandlerValue.START_PAGE_DELAYED, 1000 * 3);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void loginSuccess(UserInfoEntity entity) {
        ISkipActivityUtil.startIntent(this, MainActivity.class);
    }

    @Override
    public void loginFailure(String error) {
        ISkipActivityUtil.startIntent(this, LoginActivity.class);
    }

    @Override
    public String getAccount() {
        return ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString();
    }

    @Override
    public String getPassword() {
        return ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString();
    }
}
