package com.yunwei.easyDear.common.retrofit;

import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.entity.ResponseModel;
import com.yunwei.easyDear.function.account.data.UserInfoEntity;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
     * @param mobile
     * @param password
     * @return
     */
    @GET(BuildConfig.LOGIN_URL)
    Call<ResponseModel<UserInfoEntity>> loginRepo(@Query("mobile") String mobile, @Query("password") String password);

    /**
     * 请求七牛Token
     *
     * @return
     */
    @GET(BuildConfig.QINIU_TOKEN_URL)
    Call<ResponseModel<String>> reqQiniuToken();

    /*** 获取首页顶部轮播文章列表*/
    @GET(BuildConfig.HOME_TOP_SCROLL_ARTICLE_LIST)
    Call<ResponseModel<ArrayList<ArticleItemEntity>>> requestHomeTopScrollArticleList(@Query("province") String province, @Query("city") String city, @Query("area") String area);

    /** 获取首页文章列表*/
    @GET(BuildConfig.HOME_ARTICLE_LIST)
    Call<ResponseModel<ArrayList<ArticleItemEntity>>> requestHomeArticleList(@Query("pageSize") int pageSize, @Query("pageCount") int pageCount,
                                                                             @Query("key") String key, @Query("type") String type,
                                                                             @Query("province") String province, @Query("city") String city, @Query("area") String area);

    /** 文章详情*/
    @GET(BuildConfig.ARTICLE_DETAIL)
    Call<ResponseModel<ArticleItemEntity>> requestArticleDetail(@Query("articleId") String articleId);

}
