package com.jingan.easydearbusiness.vender.retrofit;

import com.jingan.easydearbusiness.BuildConfig;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
     * @return
     */
    @GET(BuildConfig.LOGIN_URL)
    Call<ResponseModel<List<UserInfoEntity>>> loginRepo(@Query("mobile") String mobil, @Query("password") String pwd);

    @GET(BuildConfig.QUERY_CODE)
    Call<ResponseModel<String>> queryCode(@Query("businessNo") String businessNo, @Query("cardTransactionNo") String cardTransactionNo);
}
