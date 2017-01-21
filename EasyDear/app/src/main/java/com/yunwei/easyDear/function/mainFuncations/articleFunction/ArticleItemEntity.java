package com.yunwei.easyDear.function.mainFuncations.articleFunction;

/**
 * Created by LJH on 2017/1/14.
 */

public class ArticleItemEntity {

    private String ArticleId;
    private String Title;
    private String Type;
    private String BusinessNO;
    private String Logo;
    private String BusinessName;
    private String ArticleImage;
    private String Content;
    private String PubTime;

    public ArticleItemEntity() {
    }

    public String getArticleId() {
        return ArticleId;
    }

    public void setArticleId(String articleId) {
        ArticleId = articleId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getType() {
        return Type;
    }
    public void setType(String type) {
        this.Type = type;
    }

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

    public String getArticleImage() {
        return ArticleImage;
    }

    public void setArticleImage(String articleImage) {
        ArticleImage = articleImage;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getPubTime() {
        return PubTime;
    }

    public void setPubTime(String pubTime) {
        PubTime = pubTime;
    }

}
