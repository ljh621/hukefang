package com.yunwei.easyDear.function.mainFuncations.searchFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */

public interface SearchContact {

    interface SearchView {
        void onGetHotSearchSuccess(List<SearchHotEntity> list);

        void onGetHotSearchFailure(String msg);
    }

    interface Presenter {
        void requestHotSearch();
    }
}
