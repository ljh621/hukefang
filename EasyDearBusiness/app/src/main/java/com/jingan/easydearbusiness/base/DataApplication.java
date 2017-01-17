package com.jingan.easydearbusiness.base;

import android.app.Application;

import com.google.gson.Gson;
import com.jingan.easydearbusiness.common.Constant;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;
import com.jingan.easydearbusiness.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:Application类
 * @date 2016/11/22 14:59
 */

public class DataApplication extends Application {

    private static DataApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    public static DataApplication getInstance() {
        return instance;
    }

    public UserInfoEntity getUserInfoEntity() {
        UserInfoEntity entity = new Gson().fromJson(ISpfUtil.getValue(Constant.USERINFO_KEY, "").toString(), UserInfoEntity.class);
        return entity;
    }

}
