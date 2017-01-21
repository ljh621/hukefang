package com.yunwei.easyDear.function.mainFuncations.articleFunction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.businessFunction.BusinessActivity;
import com.yunwei.easyDear.function.mainFuncations.cardDetailFunction.CardDetailActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/12.
 */

public class ArticleActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.article_listview)
    ListView mArticleListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_article);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initPresenter();
    }

    private void initPresenter() {
    }

    private void initUI() {
        setArticleListView();
    }

    private void setArticleListView() {
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        List<ArticleItemEntity> articleItemList = new ArrayList<ArticleItemEntity>();

        ArticleItemEntity item1 = new ArticleItemEntity();
        item1.setType("[促销]");
        item1.setTitle("现车热卖");
        item1.setPubTime("10.14");
        articleItemList.add(item1);

        ArticleItemEntity item2 = new ArticleItemEntity();
        item2.setType("[促销]");
        item2.setTitle("购奔驰E级敞篷");
        item2.setPubTime("10.14");
        articleItemList.add(item2);

        ArticleItemEntity item3 = new ArticleItemEntity();
        item3.setType("[促销]");
        item3.setTitle("现车热卖");
        item3.setPubTime("10.16");
        articleItemList.add(item3);

        ArticleItemEntity item4 = new ArticleItemEntity();
        item4.setType("[促销]");
        item4.setTitle("现车热卖");
        item4.setPubTime("10.18");
        articleItemList.add(item4);

        adapter.setArticleItemList(articleItemList);
        mArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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


}
