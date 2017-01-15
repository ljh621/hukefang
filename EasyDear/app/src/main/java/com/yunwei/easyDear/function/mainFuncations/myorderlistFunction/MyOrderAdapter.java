package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.messageFuncation.MessageContentAdapter;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyOrderAdapter extends BaseRecyclerViewAdapter<ItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener{

    public MyOrderAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MyOrderAdapter.ItemViewHolder viewHolder = new MyOrderAdapter.ItemViewHolder(inflater.inflate(R.layout.item_myorder_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.myorder_business_head_imageView)
        ImageView headView;
        @BindView(R.id.myorder_card_type_textView)
        TextView cardTypeText;
        @BindView(R.id.myorder_card_validate_textView)
        TextView validateText;
        @BindView(R.id.myorder_card_price)
        TextView priceText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.myorder_apply_refund})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.myorder_apply_refund:
                    Log.d(TAG, "----------> onClick  Refund, position = " + position);
                    break;
            }
        }
    }
}
