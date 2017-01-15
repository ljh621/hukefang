package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseFragment;
import com.yunwei.easyDear.common.Constant;
import com.yunwei.easyDear.common.eventbus.EventConstant;
import com.yunwei.easyDear.common.eventbus.NoticeEvent;
import com.yunwei.easyDear.function.mainFuncations.findFuncation.FindViewPagerAdater;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.data.HomeRemoteRepo;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.utils.ISpfUtil;
import com.yunwei.easyDear.view.PullToRefreshRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnPageChange;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations.homeFuncation
 * @Description:首页
 * @date 2016/11/22 18:12
 */

public class HomeFragment extends BaseFragment implements HomeContract.HomeView {
    private final String TAG = getClass().getSimpleName();

    private static HomeFragment fragment;

    @BindView(R.id.main_home_location_textView)
    TextView mLocationTextView;
    @BindView(R.id.home_scroll_vp)
    ViewPager mScrollViewPager;
    @BindView(R.id.home_scroll_dot_layout)
    LinearLayout mDotLayout;
    @BindView(R.id.home_scroll_container)
    LinearLayout mScrollLayout;
    @BindView(R.id.find_tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.find_viewPager)
    ViewPager mViewPager;
//    @BindView(R.id.tab_child_recyclerView)
//    PullToRefreshRecyclerView mRecyclerView;

    private HomePresenter mHomePresenter;

    private static final int HOME_SCROLL_IMAGE = 1001;
    private String[] tabNames;
    private List<ImageView> dots = new ArrayList<ImageView>();

    public static HomeFragment newInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        tabNames=getResources().getStringArray(R.array.tab_tiltle);
        initPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.main_fragment_home, null);
        ButterKnife.bind(this,rootView);

        setLocationCity();
        initTabLayout();
        addScrollLayout();
        requestScrollImageUrls();

        //TODO To be deleted!
        String[] urls = new String[4];
        initScrollImages(urls);
        setScrollViewListener();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
    * 初始化Presenter
     */
    private void initPresenter() {
        mHomePresenter = new HomePresenter(HomeRemoteRepo.getInstance(), this);
    }

    /**
     * EventBus 更新 Location City
     */
    @Subscribe
    public void onEventMainThread(NoticeEvent event) {
        if (event.getFlag() == EventConstant.NOTICE11) {
            setLocationCity();
        }
    }

    /**
     * 设置所在城市
     */
    private void setLocationCity() {
        String city = (String)ISpfUtil.getValue(Constant.AMAP_LOCATION_CITY, "");
        mLocationTextView.setText(city);
        ILog.v(TAG, "setLocationCity: " + city);
    }

    /**
     * 初始化TabLayout
     */
    private void initTabLayout() {
        mViewPager.setAdapter(new FindViewPagerAdater(getChildFragmentManager(), tabNames));
        mViewPager.setOffscreenPageLimit(tabNames.length);
        mViewPager.setPageMargin(10);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initTabView(mTabLayout);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ((TextView)tab.getCustomView()).setTextColor(getResources().getColor(R.color.colorAccent));
                ((TextView)tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ((TextView)tab.getCustomView()).setTextColor(getResources().getColor(R.color.gray));
                ((TextView)tab.getCustomView()).setTextSize(TypedValue.COMPLEX_UNIT_SP,15);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * 初始化TabView
     * @param tabLayout
     */
    private void initTabView(TabLayout tabLayout){
        for (int i=0;i<tabNames.length;i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            View view = LayoutInflater.from(getContext()).inflate(R.layout.title_tab_layout, null);
            TextView textView=(TextView)view.findViewById(R.id.title_tab_textView);
            textView.setText(tabNames[i]);
            /*设置默认选择*/
            if (i==0){
                textView.setTextColor(getResources().getColor(R.color.colorAccent));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,17);
            }
            tab.setCustomView(textView);
        }
    }

    private void addScrollLayout() {
//        mHomePresenter.addLayoutIntoScroll(mScrollLayout);
//        mRecyclerView.startUpRefresh();
    }

    /**
     * 请求轮播图url
    */
    private void requestScrollImageUrls() {
        mHomePresenter.requestScrollImageUrls();
    }

    /**
     * 初始化ScrollImageUrl
     */
    @Override
    public void initImageUrl(String urls) {
        //TODO 待处理url
        String urlArr[] = new String[4];
//        initScrollImages(urlArr);
//        setScrollViewListener();
    }

    /**
    * 初始化ScrollImage
     * @param urls
     */
    private void initScrollImages(String[] urls) {
        if (urls == null) {
            return;
        }
        List<String> urlList = new ArrayList<String>();
        int len = urls.length;
        for (int i = 0; i < len; i++) {
            urlList.add(urls[i]);
        }
        ScrollPagerAdapter adapter = new ScrollPagerAdapter(getContext(), urlList);
        mScrollViewPager.setAdapter(adapter);
        for (int i = 0; i < 4; i++) {
            ImageView img = new ImageView(getContext());
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
        mHandler.sendEmptyMessageDelayed(HOME_SCROLL_IMAGE, Constant.FIVE_SECONDES);
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

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        ILog.v(TAG, "dispatchMessage msg.what = " + msg.what);
        switch (msg.what) {
            case HOME_SCROLL_IMAGE:
                mHandler.removeMessages(HOME_SCROLL_IMAGE);
                int pos = mScrollViewPager.getCurrentItem();
//                ILog.v(TAG, "dispatchMessage pos1 = " + pos);
                mScrollViewPager.setCurrentItem(++pos);
                ILog.d(TAG, "dispatchMessage pos2 = " + mScrollViewPager.getCurrentItem());
                mHandler.sendEmptyMessageDelayed(HOME_SCROLL_IMAGE, Constant.FIVE_SECONDES);
                break;
            default:
                break;
        }
    }

}
