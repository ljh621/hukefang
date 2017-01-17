package com.yunwei.easyDear.function.mainFuncations.messagedetailFunction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.OrderItemEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MessageDetailAdapter extends BaseRecyclerViewAdapter<MessageDetailItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MessageDetailAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MessageDetailAdapter.ItemViewHolder viewHolder = new MessageDetailAdapter.ItemViewHolder(inflater.inflate(R.layout.item_message_detail_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        if (position >= 2) {
            viewHolder.messageContentText.setText(mLists.get(position).getMessageContent());
            viewHolder.messageTimeText.setText(mLists.get(position).getMessageTime());
            viewHolder.messageConsumeInfoText.setText(mLists.get(position).getMessageConsumeInfo());
//            Glide.with(mContent).load(mLists.get(position).getHeadUrl()).into(viewHolder.headView);
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_detail_business_head)
        ImageView headView;
        @BindView(R.id.message_detail_content)
        TextView messageContentText;
        @BindView(R.id.message_detail_time)
        TextView messageTimeText;
        @BindView(R.id.message_detail_consume_info)
        TextView messageConsumeInfoText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

    }

}
