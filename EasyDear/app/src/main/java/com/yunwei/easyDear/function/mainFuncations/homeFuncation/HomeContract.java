package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

/**
 * Created by LJH on 2017/1/2.
 */

public interface HomeContract {

    interface HomeView {
        void initImageUrl(String urls);
    }

    interface Presenter {
        void requestScrollImageUrls();
    }
}
