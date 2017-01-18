package com.jingan.easydearbusiness.function.accountFunction.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.account
 * @Description:
 * @date 2017/1/1 10:12
 */

public class UserInfoEntity {

    private String BusinessNO;

    private String Logo ;

    private String BusinessName;

    public String getBusinessNO() {
        return BusinessNO;
    }

    public void setBusinessNO(String businessNO) {
        BusinessNO = businessNO;
    }

    public String getLogo() {
        return Logo;
    }

    public void setLogo(String logo) {
        Logo = logo;
    }

    public String getBusinessName() {
        return BusinessName;
    }

    public void setBusinessName(String businessName) {
        BusinessName = businessName;
    }
}
