package com.yunwei.easyDear.function.mainFuncations.searchFunction;

import java.util.List;

/**
 * Created by LJH on 2017/3/4.
 */
public interface SearchDataSource {

    void reqHotSearch(SearchCallBack callBack);

    interface SearchCallBack {
        void getHotSearchSuccess(List<SearchHotEntity> data);

        void getHotSearchFailure(String message);
    }
}
