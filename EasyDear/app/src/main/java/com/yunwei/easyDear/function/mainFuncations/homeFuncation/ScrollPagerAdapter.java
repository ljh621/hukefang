package com.yunwei.easyDear.function.mainFuncations.homeFuncation;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/3.
 */

public class ScrollPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private List<String> mUrlList;
    private List<ImageView> mImageViewList;

    private int mImagePosition;

    public ScrollPagerAdapter(Context context, List<String> urlList) {
        mContext = context;
        mUrlList = urlList;
        mImageViewList = new ArrayList<ImageView>();
        initImageViewList();

//        ImageView iv1 = new ImageView(mContext);
//        iv1.setBackgroundResource(R.mipmap.scene_1);
//        mImageViewList.add(iv1);
//        ImageView iv2 = new ImageView(mContext);
//        iv2.setBackgroundResource(R.mipmap.scene_2);
//        mImageViewList.add(iv2);
//        ImageView iv3 = new ImageView(mContext);
//        iv3.setBackgroundResource(R.mipmap.scene_3);
//        mImageViewList.add(iv3);
//        ImageView iv4 = new ImageView(mContext);
//        iv4.setBackgroundResource(R.mipmap.scene_4);
//        mImageViewList.add(iv4);
    }

    private void initImageViewList() {
        if (mUrlList == null) {
            return;
        }
        for (String url : mUrlList) {
            url = "http://pic38.nipic.com/20140228/3822951_135521683000_2.jpg";
            ImageView iv = new ImageView(mContext);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide.with(mContext).load(url).into(iv);
            mImageViewList.add(iv);
        }
    }

    @Override
    public int getCount() {
        return 10000;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mImageViewList.size();
        if (position < 0) {
            position += mImageViewList.size();
        }
        ILog.d(TAG, "instantiateItem position = " + position);
        mImagePosition = position;
        ImageView iv = mImageViewList.get(position);
        ViewParent vp = iv.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(iv);
        }
        iv.setOnClickListener(this);
        container.addView(iv);
        return iv;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public void onClick(View view) {
        ILog.d(TAG, "--------> ViewPager Position = " + mImagePosition);
        ISkipActivityUtil.startIntent(mContext, ArticleActivity.class);
    }
}
