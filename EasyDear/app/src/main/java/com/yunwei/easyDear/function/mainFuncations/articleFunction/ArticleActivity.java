package com.yunwei.easyDear.function.mainFuncations.articleFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.businessFunction.CardItemEntity;
import com.yunwei.easyDear.function.mainFuncations.businessFunction.BusinessActivity;
import com.yunwei.easyDear.function.mainFuncations.cardDetailFunction.CardDetailActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.utils.IViewUtil;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

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
    /*文章Id*/
    private String articleId;
    private String businessNo;

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

    @BindView(R.id.article_discount_coupon_imageview)
    ImageView mCardImage;
    @BindView(R.id.article_card_name)
    TextView mCardName;
    @BindView(R.id.article_card_brief_intro)
    TextView mCardIntroduce;
    @BindView(R.id.article_card_price)
    TextView mCardPrice;
    @BindView(R.id.article_card_old_price)
    TextView mCardOldPrice;

    @BindView(R.id.article_listview)
    ListView mArticleListView;
    List<ArticleItemEntity> mArticleList;

    private ArticlePresenter mArticlePresenter;

    private ArticleItemEntity mArticleItemEntity;
    private CardItemEntity cardItemEntity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_article);
        articleId = getIntent().getStringExtra("id");
        businessNo = getIntent().getStringExtra("businessNo");
        setToolbarTitle("易兑正文");
        setToolbarRightImage(R.mipmap.icon_shap);
        ButterKnife.bind(this);
        initPresenter();
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mArticlePresenter = new ArticlePresenter(ArticleRemoteRepo.getInstance(), this);
        /*获取文章详情*/
        mArticlePresenter.requestArticleDetail(articleId);
        /*卡券信息*/
        mArticlePresenter.requestLatestCardInfo(businessNo);
        /*商家软文信息*/
        mArticlePresenter.requestBusinessArticles(businessNo);
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        shareAction();
    }

    /**
     * 设置文章详情信息
     */
    @Override
    public void setArticleDetail(ArticleItemEntity entity) {
        if (entity == null) {
            return;
        }
        mArticleItemEntity = entity;
        mArticleTitle.setText(entity.getTitle());
        mBusinessName.setText(entity.getBusinessName());
        mPubTime.setText(entity.getPubTime());
        mArticleContent.setText(entity.getContent());
        mArticleForward.setText(getString(R.string.article_detail_forward) + entity.getArticleForward());
        Glide.with(this).load(BuildConfig.DOMAI + entity.getLogo()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(mBusinessLogo));
        Glide.with(this).load(BuildConfig.DOMAI + entity.getArticleImage()).into(mArticleImage);
    }


    /**
     * 设置最新卡券信息
     */
    @Override
    public void setLatestCardInfo(ArrayList<CardItemEntity> cardItems) {
        if (cardItems == null || cardItems.size() == 0) {
            return;
        }
        cardItemEntity = cardItems.get(0);
        mCardName.setText(cardItemEntity.getCardName());
        mCardIntroduce.setText(cardItemEntity.getAssociateName());
        mCardPrice.setText("¥ " + cardItemEntity.getCardPrice());
        mCardOldPrice.setText(cardItemEntity.getCardOldPrice() == null ? "" : getString(R.string.article_old_price) + cardItemEntity.getCardOldPrice());
        Glide.with(this).load(BuildConfig.DOMAI + cardItemEntity.getLogo()).into(mCardImage);
    }

    /**
     * 设置商家软文信息
     *
     * @param businessArticleItems
     */
    @Override
    public void setBusinessArticles(ArrayList<ArticleItemEntity> businessArticleItems) {
        if (businessArticleItems == null && businessArticleItems.size() == 0) {
            return;
        }
        mArticleList = businessArticleItems;
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        adapter.setArticleItemList(businessArticleItems);
        mArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        IViewUtil.setListViewHeightBasedOnChildren(mArticleListView);

        mArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                ArticleItemEntity articleItem = mArticleList.get(i);
                bundle.putString("id", articleItem.getArticleId());
                bundle.putString("businessNo", articleItem.getBusinessNO());
                ISkipActivityUtil.startIntent(ArticleActivity.this, ArticleActivity.class, bundle);
                ArticleActivity.this.finish();
            }
        });
    }

    @OnClick({R.id.article_discount_purchase, R.id.article_more_info, R.id.article_to_discount_detail})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_to_discount_detail:/*券详情*/
            case R.id.article_discount_purchase:
                Bundle bundle = new Bundle();
                bundle.putString("cardNo", cardItemEntity.getCardNo());
                ISkipActivityUtil.startIntent(this, CardDetailActivity.class, bundle);
                break;
            case R.id.article_more_info:
                Bundle businessBundle = new Bundle();
                businessBundle.putString("businessNo", mArticleItemEntity.getBusinessNO());
                businessBundle.putString("businessName", mArticleItemEntity.getBusinessName());
                businessBundle.putString("businessLogo", mArticleItemEntity.getLogo());
                ISkipActivityUtil.startIntent(this, BusinessActivity.class, businessBundle);
                break;
        }
    }

    private void shareAction() {
        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("image/*");
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "http://society.qq.com/a/20161222/035882.htm#p=1");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, "分享到"));
    }

}
