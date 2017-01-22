package com.yunwei.easyDear.base;

import android.app.Application;

import com.amap.api.location.AMapLocation;
import com.google.gson.Gson;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.utils.ISpfUtil;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.base
 * @Description:Application类
 * @date 2016/11/22 14:59
 */

public class DataApplication extends Application {

    private static DataApplication instance;

    private AMapLocation location;

    private UserInfoEntity userInfoEntity;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static DataApplication getInstance() {
        return instance;
    }

    public AMapLocation getLocation() {
        return location;
    }

    public void setLocation(AMapLocation location) {
        this.location = location;
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public UserInfoEntity getUserInfoEntity() {
        if (userInfoEntity == null) {
            userInfoEntity = new Gson().fromJson(ISpfUtil.getValue(Constant.USERINFO_KEY, "").toString(), UserInfoEntity.class);
        }
        return userInfoEntity;
    }
}
