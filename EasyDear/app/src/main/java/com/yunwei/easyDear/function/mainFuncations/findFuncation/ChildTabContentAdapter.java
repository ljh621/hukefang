package com.yunwei.easyDear.function.mainFuncations.findFuncation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.findFuncation.data.ItemEntity;
import com.yunwei.easyDear.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Describe:tab child adapter
 * Author: hezhiWu
 * Date: 2016-12-25
 * Time: 15:02
 * Version:1.0
 */

public class ChildTabContentAdapter extends BaseRecyclerViewAdapter<ItemEntity> {

    public ChildTabContentAdapter(Context context){
        super(context);
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder=(ItemViewHolder)holder;
        viewHolder.contentText.setText(mLists.get(position).getContent());
        Glide.with(mContent).load(mLists.get(position).getHeadUrl()).asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(viewHolder.headView));
        Glide.with(mContent).load(mLists.get(position).getContentUrl()).into(viewHolder.contentImagerView);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        ItemViewHolder viewHolder=new ItemViewHolder(inflater.inflate(R.layout.item_tab_child_layout,parent,false));
        return viewHolder;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_tab_child_head_imageView)
        ImageView headView;
        @BindView(R.id.item_tab_child_content_textView)
        TextView contentText;
        @BindView(R.id.item_tab_child_business_imageView)
      ImageView contentImagerView;

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(ItemViewHolder.this,view);
        }
    }
}
