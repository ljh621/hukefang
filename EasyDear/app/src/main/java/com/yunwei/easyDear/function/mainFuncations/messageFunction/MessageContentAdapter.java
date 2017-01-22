package com.yunwei.easyDear.function.mainFuncations.messageFunction;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.BuildConfig;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.messageFunction.data.BusMessageItemEntity;
import com.yunwei.easyDear.function.mainFuncations.messagedetailFunction.MessageDetailActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MessageContentAdapter extends BaseRecyclerViewAdapter<BusMessageItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

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
        viewHolder.timeText.setText(mLists.get(position).getCreateTime());
        viewHolder.contentText.setText(mLists.get(position).getContent());
        viewHolder.titleTextView.setText(mLists.get(position).getBusinessName());
        Glide.with(mContent).load(BuildConfig.DOMAI + mLists.get(position).getLogo()).into(viewHolder.headView);
        viewHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ISkipActivityUtil.startIntent(mContent, MessageDetailActivity.class);
            }
        });
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
        @BindView(R.id.item_message_layout)
        RelativeLayout layout;
        @BindView(R.id.item_message_title_textView)
        TextView titleTextView;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }
    }
}
