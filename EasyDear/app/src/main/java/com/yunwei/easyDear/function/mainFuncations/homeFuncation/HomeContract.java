package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/2.
 */

public interface HomeContract {

    interface HomeView {
        void setTopScrollArticles(ArrayList<ArticleItemEntity> articleItems);
    }

    interface Presenter {
        void requestTopScrollArticles();
    }
}
