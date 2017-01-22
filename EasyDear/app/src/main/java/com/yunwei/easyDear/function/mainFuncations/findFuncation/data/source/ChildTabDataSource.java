package com.yunwei.easyDear.function.mainFuncations.findFuncation.data.source;

import com.yunwei.easyDear.base.BaseDataSourse;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ChildTabDataSource extends BaseDataSourse {

    void requestRecyclerArticles(ChildTabCallBack callBack);

    interface ChildTabCallBack {

        void onGetArticleListSuccess(ArrayList<ArticleItemEntity> articleItems);

        void onGetArticleListFailure(int code, String message);

        int getPageSize();

        int getPageCount();

        String getKey();

        String getType();

        String getProvince();

        String getCity();

        String getArea();
    }
}
