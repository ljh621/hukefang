package com.yunwei.easyDear.function.mainFuncations.mineFuncation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.base.DataApplication;
import com.yunwei.easyDear.function.account.LoginActivity;
import com.yunwei.easyDear.function.account.LoginRegistActivity;
import com.yunwei.easyDear.function.account.LoginRegistPagerViewPagerAdapter;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.AboutFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.MessageSetingFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.TrackSetingFragment;
import com.yunwei.easyDear.utils.IActivityManage;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation
 * @Description:
 * @date 2016/11/28 10:18
 */

public class SetingInfoActivity extends BaseActivity {

    @BindView(R.id.SetingInfoHeadView_iamgeView)
    ImageView headImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_seting_info);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null) {
            return;
        }
        Glide.with(this).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headImageView));
    }

    @OnClick({R.id.SetingInfoHeadView_layout, R.id.SetingInfo_exit_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetingInfoHeadView_layout:

                break;
            case R.id.SetingInfo_exit_btn:
                IActivityManage.getInstance().exit();
                LoginRegistActivity.startIntent(this, LoginRegistPagerViewPagerAdapter.VALUE_LOGIN);
                break;
        }
    }
}