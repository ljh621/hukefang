package com.yunwei.easyDear.function.mainFuncations.businessFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleListAdapter;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.ScrollPagerAdapter;
import com.yunwei.easyDear.function.mainFuncations.membercenterFunction.MemberCenterActivity;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

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
    @BindView(R.id.business_scroll_vp)
    ViewPager mScrollViewPager;
    @BindView(R.id.business_scroll_dot_layout)
    LinearLayout mDotLayout;

    private static final int BUSINESS_SCROLL_IMAGE = 1001;
    private List<ImageView> dots = new ArrayList<ImageView>();
    private ArrayList<ArticleItemEntity> mArticleItemList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_business);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initUI();

        //TODO To be deleted!
        String[] urls = new String[4];
        initScrollImages(urls);
        setScrollViewListener();
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
        mBusinessArticleListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        for (ArticleItemEntity entity : articleItemList) {
            mArticleItemList.add(entity);
        }
    }

    /**
     * 初始化ScrollImage
     * @param urls
     */
    private void initScrollImages(String[] urls) {

        ScrollPagerAdapter adapter = new ScrollPagerAdapter(this, mArticleItemList);
        mScrollViewPager.setAdapter(adapter);
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(this);
            if (i == 0) {
                img.setImageResource(R.drawable.dot_focus);
            } else {
                img.setImageResource(R.drawable.dot_normal);
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(5, 0, 5, 30);

            // 加载到布局容器
            mDotLayout.addView(img, params);
            dots.add(img);
        }
        mHandler.sendEmptyMessageDelayed(BUSINESS_SCROLL_IMAGE, Constant.FIVE_SECONDES);
    }

    /**
     * 设置轮播ViewPager监听
     */
    private void setScrollViewListener() {
        mScrollViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                ILog.v(TAG, "onPageScrolled position = " + position);
            }

            @Override
            public void onPageSelected(int position) {
                int size = dots.size();
                ILog.d(TAG, "onPageSelected position = " + position);
                for (int i = 0; i < size; i++) {
                    ImageView img = dots.get(i);
                    if (i == position % size) {
                        img.setImageResource(R.drawable.dot_focus);
                    } else {
                        img.setImageResource(R.drawable.dot_normal);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                ILog.v(TAG, "onPageScrollStateChanged state = " + state);
            }
        });
    }

    @OnClick({R.id.business_back, R.id.business_activity_purchase, R.id.business_become_member})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.business_back:
                onBackPressed();
                break;
            case R.id.business_activity_purchase:
                break;
            case R.id.business_become_member:
                ISkipActivityUtil.startIntent(this, MemberCenterActivity.class);
                break;
        }
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        ILog.v(TAG, "dispatchMessage msg.what = " + msg.what);
        switch (msg.what) {
            case BUSINESS_SCROLL_IMAGE:
                mHandler.removeMessages(BUSINESS_SCROLL_IMAGE);
                int pos = mScrollViewPager.getCurrentItem();
//                ILog.v(TAG, "dispatchMessage pos1 = " + pos);
                mScrollViewPager.setCurrentItem(++pos);
                ILog.d(TAG, "dispatchMessage pos2 = " + mScrollViewPager.getCurrentItem());
                mHandler.sendEmptyMessageDelayed(BUSINESS_SCROLL_IMAGE, Constant.FIVE_SECONDES);
                break;
            default:
                break;
        }
    }
}
