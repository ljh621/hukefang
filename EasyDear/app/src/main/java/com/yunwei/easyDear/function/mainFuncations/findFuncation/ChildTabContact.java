package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;

import java.util.ArrayList;

/**
 * Created by LJH on 2017/1/21.
 */

public interface ChildTabContact {

    interface ChildPresenter {
        void requestRecyclerArticles(String type);
    }

    interface ChileTabView {

        void setArticleList(ArrayList<ArticleItemEntity> data);

    }
}
