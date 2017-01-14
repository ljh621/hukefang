package com.yunwei.easyDear.function.mainFuncations.articleFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
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
        initUI();
    }

    private void initUI() {
        setListView();
    }

    private void setListView() {
        ArticleListAdapter adapter = new ArticleListAdapter(this);
        List<ArticleItem> articleItemList = new ArrayList<ArticleItem>();

        ArticleItem item1 = new ArticleItem();
        item1.setType("[促销]");
        item1.setTitle("现车热卖");
        item1.setDate("10.14");
        articleItemList.add(item1);

        ArticleItem item2 = new ArticleItem();
        item2.setType("[促销]");
        item2.setTitle("购奔驰E级敞篷");
        item2.setDate("10.14");
        articleItemList.add(item2);

        ArticleItem item3 = new ArticleItem();
        item3.setType("[促销]");
        item3.setTitle("现车热卖");
        item3.setDate("10.16");
        articleItemList.add(item3);

        ArticleItem item4 = new ArticleItem();
        item4.setType("[促销]");
        item4.setTitle("现车热卖");
        item4.setDate("10.18");
        articleItemList.add(item4);

        adapter.setArticleItemList(articleItemList);
        mArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.article_back, R.id.article_more_info})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.article_back:
                onBackPressed();
                break;
            case R.id.article_more_info:
                onBackPressed();
                break;
        }
    }


}
