package com.yunwei.easyDear.base;

import android.app.Application;

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
}
