package com.jingan.easydearbusiness.function.verificationFunction.data.source;

import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction.data.source
 * @Description:
 * @date 2017/1/1 15:06
 */

public class RequestVerficationRemoteRepo implements RequestVerficationDataSource {

    private static RequestVerficationRemoteRepo remoteRepo;

    public static RequestVerficationRemoteRepo newInstance() {
        if (remoteRepo == null) {
            remoteRepo = new RequestVerficationRemoteRepo();
        }
        return remoteRepo;
    }

    @Override
    public void downRefresh(DownRefreshCallBack callBack) {
        List<VerficationEntity> entities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            VerficationEntity entity = new VerficationEntity();
            entity.setHeadUrl("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entities.add(entity);
        }
        callBack.getDownRefreshSuccess(entities);
    }

    @Override
    public void pullRefresh(PullRefreshCallBack callBack) {
        List<VerficationEntity> entities = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            VerficationEntity entity = new VerficationEntity();
            entity.setHeadUrl("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg");
            entities.add(entity);
        }
        callBack.getPullRefreshSuccess(entities);
    }
}
