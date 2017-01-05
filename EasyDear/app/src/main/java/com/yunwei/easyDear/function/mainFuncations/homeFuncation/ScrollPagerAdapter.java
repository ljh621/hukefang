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
import com.yunwei.easyDear.utils.ILog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LJH on 2017/1/3.
 */

public class ScrollPagerAdapter extends PagerAdapter {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private List<String> mUrlList;
    private List<ImageView> mImageViewList;

    public ScrollPagerAdapter(Context context, List<String> urlList) {
        mContext = context;
        mUrlList = urlList;
        mImageViewList = new ArrayList<ImageView>();
        initImageViewList();

        ImageView iv1 = new ImageView(mContext);
        iv1.setImageResource(R.mipmap.scene_1);
        mImageViewList.add(iv1);
        ImageView iv2 = new ImageView(mContext);
        iv2.setImageResource(R.mipmap.scene_2);
        mImageViewList.add(iv2);
        ImageView iv3 = new ImageView(mContext);
        iv3.setImageResource(R.mipmap.scene_3);
        mImageViewList.add(iv3);
        ImageView iv4 = new ImageView(mContext);
        iv4.setImageResource(R.mipmap.scene_4);
        mImageViewList.add(iv4);
    }

    private void initImageViewList() {
        if (mUrlList == null) {
            return;
        }
        for (String url : mUrlList) {
            ImageView iv = new ImageView(mContext);
            Glide.with(mContext).load(url).into(iv);
//            mImageViewList.add(iv);
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
        ImageView view = mImageViewList.get(position);
        ViewParent vp = view.getParent();
        if (vp != null) {
            ViewGroup parent = (ViewGroup) vp;
            parent.removeView(view);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }
}
