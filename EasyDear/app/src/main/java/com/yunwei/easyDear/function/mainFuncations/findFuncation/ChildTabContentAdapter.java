package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleItemEntity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Describe:tab child adapter
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 15:02
 * Version:1.0
 */

public class ChildTabContentAdapter extends BaseRecyclerViewAdapter<ArticleItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public ChildTabContentAdapter(Context context) {
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;

        ArticleItemEntity entity = mLists.get(position);
        viewHolder.contentText.setText(entity.getContent());
        viewHolder.businessName.setText(entity.getBusinessName());
        viewHolder.pubTime.setText(entity.getPubTime());
        Glide.with(mContent).load(BuildConfig.DOMAI + entity.getLogo()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(viewHolder.headView));
        Glide.with(mContent).load(BuildConfig.DOMAI + entity.getArticleImage()).into(viewHolder.articleImageView);

        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("id",mLists.get(position).getArticleId());
                bundle.putString("businessNo",mLists.get(position).getBusinessNO());
                ISkipActivityUtil.startIntent(mContent, ArticleActivity.class,bundle);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder = new ItemViewHolder(inflater.inflate(R.layout.item_tab_child_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_tab_child_head_imageView)
        ImageView headView;
        @BindView(R.id.item_tab_child_content_textView)
        TextView contentText;
        @BindView(R.id.item_tab_child_business_textView)
        TextView businessName;
        @BindView(R.id.item_tab_child_date_textView)
        TextView pubTime;
        @BindView(R.id.item_tab_child_business_imageView)
        ImageView articleImageView;
        @BindView(R.id.item_tab_child_layout)
        LinearLayout layout;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }
    }

}
