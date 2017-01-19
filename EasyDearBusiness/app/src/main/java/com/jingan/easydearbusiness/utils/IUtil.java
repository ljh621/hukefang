package com.jingan.easydearbusiness.utils;


import com.jingan.easydearbusiness.base.DataApplication;

import java.text.SimpleDateFormat;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.utils
 * @Description:工具类
 * @date 2016/11/30 11:03
 */

public class IUtil {

    /**
     * 获取字符串资源
     *
     * @param res
     * @return
     */
    public static String getStrToRes(int res) {
        return DataApplication.getInstance().getResources().getString(res);
    }
    /**
     * 时间戳格式化
     *
     * @param time
     * @param str
     * @return
     */
    public static String formatDate(long time, String str) {
        return new SimpleDateFormat(str).format(time);
    }

}
