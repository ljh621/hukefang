package com.jingan.easydearbusiness.function.accountFunction.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.account
 * @Description:
 * @date 2017/1/1 10:12
 */

public class UserInfoEntity {

    private String UserNo;

    private String Mobile;

    private String NickName;

    private String Imagery;

    public void setUserNo(String UserNo){
        this.UserNo = UserNo;
    }
    public String getUserNo(){
        return this.UserNo;
    }
    public void setMobile(String Mobile){
        this.Mobile = Mobile;
    }
    public String getMobile(){
        return this.Mobile;
    }
    public void setNickName(String NickName){
        this.NickName = NickName;
    }
    public String getNickName(){
        return this.NickName;
    }
    public void setImagery(String Imagery){
        this.Imagery = Imagery;
    }
    public String getImagery(){
        return this.Imagery;
    }
}
