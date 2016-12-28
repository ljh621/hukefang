package com.yunwei.easyDear.function.account.data.soure;

import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.base.BaseDataSourse;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data.soure
 * @Description:
 * @date 2016/11/29 15:02
 */

public interface LoginDataSoure extends BaseDataSourse{

    interface LoginCallBack {

        void onLoginSuccess(UserInfoEntity entity);

        void onLoginFailure(String error);

    }

    void login(String account, String password, LoginCallBack callBack);
}
