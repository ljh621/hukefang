package com.yunwei.easyDear.function.mainFuncations.mineFuncation;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.ModiflyNickFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.fragment.ModiflyPasswordFragment;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

/**
 * Describe:
 * Author: hezhiWu
 * Date: 2017-02-18
 * Time: 11:40
 * Version:1.0
 */

public class UpdateActivity extends BaseActivity {
    public final static String FROM_FLAG = "from_flag";
    public final static String TITLE_FLAG = "title_flag";

    private String fromValue;

    public static void startIntent(Activity context, String fromFlag, String titleText, int requestCode) {
        Bundle bundle = new Bundle();
        bundle.putString(FROM_FLAG, fromFlag);
        bundle.putString(TITLE_FLAG, titleText);
        ISkipActivityUtil.startIntentForResult(context, UpdateActivity.class, bundle, requestCode);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_update_info);
        setToolbarTitle(getIntent().getStringExtra(TITLE_FLAG));
        fromValue = getIntent().getStringExtra(FROM_FLAG);
        initView();
    }

    private void initView() {
        if (TextUtils.isEmpty(fromValue)) {
            return;
        }
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (fromValue.equals(ModiflyNickFragment.MODIFLY_NICK_FLAG)) {
            setToolbarRightText("修改");
            transaction.replace(R.id.activity_update_container, ModiflyNickFragment.newInstance());
        } else if (fromValue.equals(ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG)) {
            setToolbarRightText("修改");
            transaction.replace(R.id.activity_update_container, ModiflyPasswordFragment.newInstance());
        }
        transaction.commit();
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        if (fromValue.equals(ModiflyNickFragment.MODIFLY_NICK_FLAG)) {//昵称修改返回
            String nick = ModiflyNickFragment.newInstance().getEditViewText();
            if (!TextUtils.isEmpty(nick)) {
                showToast(nick);
            }else {
                showToast("昵称不能为空");
            }
        } else if (fromValue.equals(ModiflyPasswordFragment.MODIFLY_PASSWORD_FLAG)) {//密码修改返回
            String newPwd = ModiflyPasswordFragment.newInstance().newPasswordText();
            if (!TextUtils.isEmpty(newPwd)) {
                showToast(newPwd);
            }
        }
    }
}
