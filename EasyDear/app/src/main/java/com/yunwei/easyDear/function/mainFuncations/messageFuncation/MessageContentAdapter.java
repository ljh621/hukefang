package com.yunwei.easyDear.function.mainFuncations.messageFuncation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.ArticleActivity;
import com.yunwei.easyDear.function.mainFuncations.messageFuncation.data.ItemEntity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MessageContentAdapter extends BaseRecyclerViewAdapter<ItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MessageContentAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MessageContentAdapter.ItemViewHolder viewHolder = new MessageContentAdapter.ItemViewHolder(inflater.inflate(R.layout.item_message_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.timeText.setText(mLists.get(position).getTime());
        viewHolder.typeText.setText(mLists.get(position).getType());
        viewHolder.contentText.setText(mLists.get(position).getContent());
        Glide.with(mContent).load(mLists.get(position).getContentUrl()).into(viewHolder.headView);
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_message_head_imageView)
        ImageView headView;
        @BindView(R.id.item_message_time_textView)
        TextView timeText;
        @BindView(R.id.item_message_type_textView)
        TextView typeText;
        @BindView(R.id.item_message_content_textView)
        TextView contentText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.item_message_content_textView})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.item_tab_child_content_textView:
                    Log.d(TAG, "----------> onClick  Read All, position = " + position);
                    String msgUrl = mLists.get(position).getContentUrl();
                    Bundle bundle = new Bundle();
                    bundle.putString("msg_usl", msgUrl);
                    ISkipActivityUtil.startIntent(mContent, ArticleActivity.class);
                    break;
            }
        }
    }
}
