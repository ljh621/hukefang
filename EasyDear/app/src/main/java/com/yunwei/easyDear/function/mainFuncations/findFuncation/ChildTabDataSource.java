package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import com.yunwei.easyDear.base.BaseDataSourse;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ChildTabDataSource extends BaseDataSourse {
    
    void requestRecyclerArticles(String type, ChildTabCallBack callBack);

    interface ChildTabCallBack {
        
        void onGetArticleListSuccess(ArrayList<ArticleItemEntity> articleItems);

        void onGetArticleListFailure(String message);
    }
}
