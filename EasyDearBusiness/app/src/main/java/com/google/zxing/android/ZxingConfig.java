package com.google.zxing.android;

/**
 * @Author slientwhale
 * @Version V1.0
 * @Package com.google.zxing
 * @Moudle 模块
 * @Description  个人创建的设置管理类，可以通过集成 重写实现个人的配置，未遵循符合 国际ISO 条形码的规范设计
 * @Date 2016/12/15.
 */

import com.google.zxing.common.StringUtils;

/**
 * 主要是替代原来的 PreferencesActivity 中的常量
 */

public class ZxingConfig {

    public boolean key_decode_1d_product = true;//允许识别 条形码--商品码
    public boolean key_decode_1d_indusirial = true;//允许识别 条形码--工业码
    final public boolean key_decode_rqcode = true;//允许识别 二维码QRCode ，本配置不可以修改

    public boolean key_beep_voice = true;//是否声音提示

    public String key_decode_character= StringUtils.UTF8; //解码格式的



}
