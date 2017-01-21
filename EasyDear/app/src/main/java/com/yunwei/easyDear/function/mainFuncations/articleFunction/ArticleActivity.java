package com.yunwei.easyDear.function.mainFuncations.articleFunction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.businessFunction.BusinessActivity;
import com.yunwei.easyDear.function.mainFuncations.cardDetailFunction.CardDetailActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.IViewUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/12.
 */

public class ArticleActivity extends BaseActivity implements ArticleContact.ArticleView {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.article_listview)
    ListView mArticleListView;

    @BindView(R.id.article_title)
    TextView mArticleTitle;
    @BindView(R.id.article_content)
    TextView mArticleContent;
    @BindView(R.id.article_business_textView)
    TextView mBusinessName;
    @BindView(R.id.article_pubtime)
    TextView mPubTime;
    @BindView(R.id.article_business_logo)
    ImageView mBusinessLogo;
    @BindView(R.id.article_business_imageview)
    ImageView mArticleImage;
    @BindView(R.id.article_forward)
    TextView mArticleForward;

    @BindView(R.id.article_card_name)
    TextView mCardName;
    @BindView(R.id.article_card_brief_intro)
    TextView mCardIntroduce;
    @BindView(R.id.article_card_price)
    TextView mCardPrice;
    @BindView(R.id.article_card_old_price)
    TextView mCardOldPrice;

    private ArticlePresenter mArticlePresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_article);
//        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        setToolbarTitle("易兑正文");
        ButterKnife.bind(this);
        initPresenter();
        requestArticleDetail();
        requestLatestCardInfo();
        requestBusinessArticles();
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mArticlePresenter = new ArticlePresenter(ArticleRemoteRepo.getInstance(), this);
    }

    /**
     * 获取文章详情
     */
    private void requestArticleDetail() {
        String articleId = "11";
        mArticlePresenter.requestArticleDetail(articleId);
    }

    /**
     * 设置文章详情信息
     */
    @Override
    public void setArticleDetail(ArticleItemEntity entity) {

//        setArticleInfos(entity);

        // TODO 调试用，可删除 --- begin
        Message msg = new Message();
        entity.setArticleImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1485010526779&di=d8a491c69c8c8ac48af83ce99636521b&imgtype=0&src=http%3A%2F%2Fimg.tuku.cn%2Ffile_big%2F201502%2F0e93d8ab02314174a933b5f00438d357.jpg");
        msg.what = 0x100;
        msg.obj = entity;
        mHandler.sendMessageDelayed(msg, 4500);
        // TODO 调试用，可删除 --- end
    }

    /**
     * 获取最新卡券信息
     */
    private void requestLatestCardInfo() {
        String businessNo = "20170113204209845267";
        mArticlePresenter.requestLatestCardInfo(businessNo);
    }

    /**
     * 获取商家软文信息
     */
    private void requestBusinessArticles() {
        String businessNo = "20170113204209845267";
        mArticlePresenter.requestBusinessArticles(businessNo);
    }

    /**
     * 设置最新卡券信息
     */
    @Override
    public void setLatestCardInfo(ArrayList<CardItemEntity> cardItems) {
//        setCardInfo(cardItems);

        // TODO 调试用，可删除 --- begin
        Message msg = new Message();
        msg.what = 0x200;
        msg.obj = cardItems;
        mHandler.sendMessageDelayed(msg, 5000);
        // TODO 调试用，可删除 --- end
    }

    @Override
    public void setBusinessArticles(ArrayList<ArticleItemEntity> businessArticleItems) {
        if (businessArticleItems == null) {
            return;
        }
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        adapter.setArticleItemList(businessArticleItems);
        mArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        IViewUtil.setListViewHeightBasedOnChildren(mArticleListView);
    }

    private void setArticleInfo(ArticleItemEntity entity) {
        if (entity == null) {
            return;
        }
        mArticleTitle.setText(entity.getTitle());
        mBusinessName.setText(entity.getBusinessName());
        mPubTime.setText(entity.getPubTime());
        mArticleContent.setText(entity.getContent());
        mArticleForward.setText(getString(R.string.article_detail_forward) + entity.getArticleForward());
        Glide.with(getApplicationContext()).load(entity.getLogo()).into(mBusinessLogo);
        Glide.with(getApplicationContext()).load(entity.getArticleImage()).into(mArticleImage);
    }

    private void setCardInfo(ArrayList<CardItemEntity> cardItems) {
        if (cardItems == null) {
            return;
        }
        CardItemEntity entity = cardItems.get(0);
        mCardName.setText(entity.getCardName());
        mCardIntroduce.setText(entity.getAssociateName());
        mCardPrice.setText("¥ " + entity.getCardPrice());
        mCardOldPrice.setText(getString(R.string.article_old_price) + entity.getCardOldPrice());
    }

    @OnClick({R.id.article_back, R.id.article_send, R.id.article_to_discount_detail, R.id.article_discount_purchase, R.id.article_more_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_back:
                onBackPressed();
                break;
            case R.id.article_send:
                startToSend();
                break;
            case R.id.article_to_discount_detail:
                ISkipActivityUtil.startIntent(this, CardDetailActivity.class);
                break;
            case R.id.article_discount_purchase:
                ISkipActivityUtil.startIntent(this, CardDetailActivity.class);
                break;
            case R.id.article_more_info:
                ISkipActivityUtil.startIntent(this, BusinessActivity.class);
                break;
        }
    }

    private void startToSend() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "http://society.qq.com/a/20161222/035882.htm#p=1");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "类名"));
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            // TODO 调试用,可删除 --- begin
            case 0x100:
                setArticleInfo((ArticleItemEntity) msg.obj);
                break;
            case 0x200:
                setCardInfo((ArrayList<CardItemEntity>) msg.obj);
                break;
            // TODO 调试用，可删除 --- end
        }
    }
}
