package com.jingan.easydearbusiness.function.accountFunction.data.source;

import com.jingan.easydearbusiness.base.BaseDataSourse;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data.soure
 * @Description:
 * @date 2016/11/29 15:02
 */

public interface LoginDataSoure extends BaseDataSourse {

    interface LoginCallBack {

        void onLoginSuccess(UserInfoEntity entity);

        void onLoginFailure(String error);

    }

    void login(String account, String password, LoginCallBack callBack);
}
