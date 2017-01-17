package com.jingan.easydearbusiness.vender.retrofit;

import com.jingan.easydearbusiness.BuildConfig;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.function.accountFunction.data.UserInfoEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationDetailEntity;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
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

    /**
     * 序列号验证
     *
     * @param businessNo
     * @param cardTransactionNo
     * @return
     */
    @GET(BuildConfig.QUERY_CODE)
    Call<ResponseModel<String>> queryCode(@Query("BusinessNo") String businessNo, @Query("cardTransactionNo") String cardTransactionNo);

    /**
     * 账单列表
     *
     * @param businessNo
     * @param pageSize
     * @param pageCount
     * @return
     */
    @GET(BuildConfig.QUERY_VERFICATION_LIST)
    Call<ResponseModel<List<VerficationEntity>>> queryVerficationList(@Query("businessNo") String businessNo, @Query("pageSize") int pageSize, @Query("pageCount") int pageCount, @Query("keyDate") String date);

    /**
     * 账单详情
     *
     * @param billNo
     * @return
     */
    @GET(BuildConfig.VERFICATION_DETAIL)
    Call<ResponseModel<List<VerficationDetailEntity>>> queryVerficationDetail(@Query("billNo") String billNo);
}
