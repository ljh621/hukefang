package com.google.zxing;

/**
 * @Author slientwhale
 * @Version zxing V1.0
 * @Package com.google.zxing
 * @Moudle 说明模块
 * @Description  本项目 主要整合了zxing 在github上的 core、android、android-core 三个项目
 * @Date 整理日期 2016/12/7.
 * @Scource  https://github.com/zxing/zxing
 *           依赖的jar地址：http://repo1.maven.org/maven2/com/google/zxing/core/3.3.0/
 */

public final class ReadMe {
    /**
     * 本项目中的源码 基于 Zxing 3.3.0 版本，对原有 识别支持格式进行精简
     *
     * @原有支持识别格式
     * zxing Supported Formats 支持格式：
     * 1D product	| 1D industrial	    | 2D
     * UPC-A	    | Code 39	        | QR Code
     * UPC-E	    | Code 93	        | Data Matrix
     * EAN-8	    | Code 128	        | Aztec (beta)
     * EAN-13	    | Codabar	        | PDF 417 (beta)
     *              | ITF               | MaxiCode
     *              | RSS-14
     *              | RSS-Expanded
     *
     * @现有支持识别格式
     * zxing Supported Formats 支持格式：
     * 1D product	| 1D industrial	    | 2D
     * UPC-A	    | Code 39	        | QR Code
     * UPC-E	    | Code 93
     * EAN-8	    | Code 128
     * EAN-13	    | Codabar
     *              | RSS-14
     *              | RSS-Expanded
     *
     * @识别效果
     *  ~ 如果包含的内容比较多，定位符相对较小，内容识别速度会大大减慢
     *  ~
     *
     * 在本项目中 oned 包下面包含的是 1D product,1D industrial的识别源码
     * 其余的   aztec、datamatrix、pdf417、qrcode 包都是2D二维码的处理
     *
     *  符合 JSR-234规范(自动对焦)
     *
     * @识别说明
     * (1)但是如果是彩色的二维码，识别效率会变得很低,最好使用黑白的颜色识别效率会很高
     * 如果内容的数字长度过长，那么生成的二维码 定位点会越小，如果内容长度越多，生成的二维码越密集因而识别效率也会下降很多
     * (2)本项目还进行了各种内容的处理包括：
     *      WPA（网络数据）、URL（链接）、TEXT（文本）、
     * (3)向二维码中添加logo 不影响识别之后内容的完整性
     *
     * @本项目操作
         *  @Delete 表示可以删除的模块，用于精简项目
         *
     */

    /***
     * @相关资料
     *
     * @条码格式用途
     * 目前，国际广泛使用的条码种类有EAN、UPC码（商品条码，用于在世界范围内唯一标识一种商品。
     * 我们在超市中最常见的就是这种条码）、Code39码（可表示数字和字母，在管理领域应用最广）、
     * ITF25码（在物流管理中应用较多）、Codebar码（多用于医疗、图书领域）、Code93码、Code128码等。
     *
     * 相关文章
     * 将zxing项目改为配置的module  http://blog.csdn.net/zhangxing52077/article/details/52854330
     *
     * QRCode 二维码的生成细节和原理   http://coolshell.cn/articles/10590.html
     *
     * 有关各种二维码格式 在线生成     http://barcode.cnaidc.com/html/BCGcodabar.php
     *                              http://barcode.tec-it.com/zh  （了解各种编码生成的图像格式是什么样子的）
     *
     * Zxing 深度研究  http://iluhcm.com/2016/01/08/scan-qr-code-and-recognize-it-from-picture-fastly-using-zxing/?utm_source=tuicool&utm_medium=referral
     * Zxing 改造 识别卡片数量   http://www.cnblogs.com/dreamfactory/p/3516131.html
     */

    /**
     * @学习
     *  本项目可以学习的知识点
     *   · Camera相关
     *   · Preference 使用 PreferenceFragment 和 PreferenceScreen 操作Preference
     *   · 线程的调度
     *
     *
     */
}
