package com.yunwei.easyDear.function.mainFuncations.homeFuncation.data;

/**
 * Created by LJH on 2017/1/4.
 */

public interface HomeDataSource {

    interface RequestHome {
        void getScrollImageUrlSuccess(String urls);


        void getHomeArticleListSuccess();
    }

    void requestScrollImageUrls(RequestHome callBack);
}
