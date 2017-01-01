package com.jingan.easydearbusiness.vender.retrofit;

import com.jingan.easydearbusiness.BuildConfig;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.common.retrofit
 * @Description:请求API接口配制
 * @date 2016/11/29 15:50
 */

public interface APIService {
    /**
     * 登录
     *
     * @param entity
     * @return
     */
    @POST(BuildConfig.LOGIN_URL)
    Call<UserInfoEntity> loginRepo(@Body RequestBody entity);

}
