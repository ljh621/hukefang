package com.yunwei.easyDear.function.mainFuncations.businessFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItem;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class BusinessActivity extends BaseActivity {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.business_article_listview)
    ListView mBusinessArticleListView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_business);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        setArticleListView();
    }

    private void setArticleListView() {
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
        mBusinessArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.business_back, R.id.business_activity_purchase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.business_back:
                onBackPressed();
                break;
            case R.id.business_activity_purchase:
                break;
        }
    }
}
