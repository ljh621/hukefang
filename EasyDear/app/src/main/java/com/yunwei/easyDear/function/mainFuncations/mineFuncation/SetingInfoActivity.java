package com.yunwei.easyDear.function.mainFuncations.mineFuncation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.ModiflyNickFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.ModiflyPasswordFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.TrackSetingFragment;
import com.yunwei.easyDear.function.pictureFuncation.SelectPictureActivity;
import com.yunwei.easyDear.function.pictureFuncation.data.PictureEntity;
import com.yunwei.easyDear.utils.IActivityManage;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.IUtil;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.mineFuncation
 * @Description:设置界面
 * @date 2016/11/28 10:18
 */

public class SetingInfoActivity extends BaseActivity {

    @BindView(R.id.SetingInfoHeadView_iamgeView)
    ImageView headImageView;
    @BindView(R.id.setting_info_number)
    TextView umberText;
    @BindView(R.id.setting_info_nickname)
    TextView nickName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_seting_info);
        setToolbarTitle("设置");
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        UserInfoEntity entity = DataApplication.getInstance().getUserInfoEntity();
        if (entity == null) {
            return;
        }
        nickName.setText(entity.getNickName());
        nickName.setText(entity.getUserNo());
        Glide.with(this).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headImageView));
    }

    @OnClick({R.id.SetingInfoHeadView_layout, R.id.SetingInfo_exit_btn, R.id.setting_info_about_us_container, R.id.setting_info_change_password_container, R.id.setting_info_nickname_container, R.id.setting_info_contact_us_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SetingInfoHeadView_layout:
                SelectPictureActivity.startIntent(this,1);
                break;
            case R.id.SetingInfo_exit_btn:
                IActivityManage.getInstance().exit();
                LoginRegistActivity.startIntent(this, LoginRegistPagerViewPagerAdapter.VALUE_LOGIN);
                break;
            case R.id.setting_info_about_us_container:/*关于我们*/
                break;
            case R.id.setting_info_change_password_container:/*修改密码*/
                UpdateActivity.startIntent(this, ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG,"密码修改",901);
                break;
            case R.id.setting_info_nickname_container:/*修改Nick*/
                UpdateActivity.startIntent(this, ModiflyNickFragment.MODIFLY_NICK_FLAG,"昵称修改",900);
                break;
            case R.id.setting_info_contact_us_container:/*联系我们*/
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==SelectPictureActivity.SELECT_PICTURE_RESULT_CODE){
            List<PictureEntity> list=(List<PictureEntity>) data.getSerializableExtra("result");
            if (list!=null&&list.size()>0){
                Glide.with(this).load(IUtil.fitterUrl(list.get(0).getUrl())).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(headImageView));
            }
        }
    }
}