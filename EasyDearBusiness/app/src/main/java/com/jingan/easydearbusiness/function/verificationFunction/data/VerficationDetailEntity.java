package com.jingan.easydearbusiness.function.verificationFunction.data;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction.data
 * @Description:
 * @date 2017/1/17 10:31
 */

public class VerficationDetailEntity {
    private String BillNo;

    private String UserNo;

    private String NickName;

    private String Imagery;

    private String Account;

    private String BuyAmount;

    private String Remark;

    private String CreateTime;

    public void setBillNo(String BillNo){
        this.BillNo = BillNo;
    }
    public String getBillNo(){
        return this.BillNo;
    }
    public void setUserNo(String UserNo){
        this.UserNo = UserNo;
    }
    public String getUserNo(){
        return this.UserNo;
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
    public void setAccount(String Account){
        this.Account = Account;
    }
    public String getAccount(){
        return this.Account;
    }
    public void setBuyAmount(String BuyAmount){
        this.BuyAmount = BuyAmount;
    }
    public String getBuyAmount(){
        return this.BuyAmount;
    }
    public void setRemark(String Remark){
        this.Remark = Remark;
    }
    public String getRemark(){
        return this.Remark;
    }
    public void setCreateTime(String CreateTime){
        this.CreateTime = CreateTime;
    }
    public String getCreateTime(){
        return this.CreateTime;
    }


}
