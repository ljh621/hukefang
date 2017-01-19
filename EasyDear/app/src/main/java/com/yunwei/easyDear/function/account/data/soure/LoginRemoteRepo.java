package com.yunwei.easyDear.function.account.data.soure;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.retrofit.RetrofitManager;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.base.DataApplication;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.utils.INetWorkUtil;
import com.yunwei.easyDear.utils.IUtil;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.account.data.soure
 * @Description:登录业务请求
 * @date 2016/11/29 15:19
 */

public class LoginRemoteRepo implements LoginDataSoure {
    private final String TAG = getClass().getSimpleName();

    private static LoginRemoteRepo remoteRepo;
    private Call<ResponseModel<UserInfoEntity>> call;

    public static LoginRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new LoginRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void login(String account, String password, final LoginCallBack callBack) {
        if (!INetWorkUtil.isNetworkAvailable(DataApplication.getInstance())) {
            callBack.onLoginFailure(IUtil.getStrToRes(R.string.invalid_network));
            return;
        }
        call = RetrofitManager.getInstance().getService().loginRepo(account, password);
        call.enqueue(new Callback<ResponseModel<UserInfoEntity>>() {
            @Override
            public void onResponse(Call<ResponseModel<UserInfoEntity>> call, Response<ResponseModel<UserInfoEntity>> response) {
                if (response.isSuccessful() && response.body().getCode() == Constant.HTTP_SUCESS_CODE) {
                    callBack.onLoginSuccess(response.body().getData());
                } else {
                    callBack.onLoginFailure(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<UserInfoEntity>> call, Throwable t) {
                callBack.onLoginFailure(IUtil.getStrToRes(R.string.login_failure));
            }
        });
    }

    /**
     * 取消请求
     */
    @Override
    public void cancelRequest() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
            ILog.d(TAG, "isCanceled==" + call.isCanceled());
        }
    }
}
