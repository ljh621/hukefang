package com.jingan.easydearbusiness.function.accountFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.common.handler.HandlerValue;
import com.jingan.easydearbusiness.utils.ISkipActivityUtil;

import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.accountFunction
 * @Description:启动页
 * @date 2017/1/1 10:55
 */

public class SplashActivity extends BaseActivity {

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
//        if (!TextUtils.isEmpty(ISpfUtil.getValue(Constant.ACCOUNT_KEY, "").toString()) && !TextUtils.isEmpty(ISpfUtil.getValue(Constant.PSSWORD_KEY, "").toString())) {
//            loginPresenter = new AccountPresenter(LoginRemoteRepo.newInstance(), this);
//            loginPresenter.login();
//        } else {
        mHandler.sendEmptyMessageDelayed(HandlerValue.START_PAGE_DELAYED, 1000 * 3);
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
