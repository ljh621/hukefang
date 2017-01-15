package com.yunwei.easyDear.function.mainFuncations.myorderlistFunction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyOrderAdapter extends BaseRecyclerViewAdapter<OrderItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

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
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        if (position >= 2) {
            viewHolder.businessNameText.setText(mLists.get(position).getBusinessName());
            viewHolder.cardTypeText.setText(mLists.get(position).getCardType());
            viewHolder.validateText.setText(mLists.get(position).getValidateDate());
            viewHolder.priceText.setText(mLists.get(position).getCardPrice());
            viewHolder.amountText.setText(mLists.get(position).getCardAmount());
//            Glide.with(mContent).load(mLists.get(position).getHeadUrl()).into(viewHolder.headView);
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.myorder_business_head_imageView)
        ImageView headView;
        @BindView(R.id.myorder_business_name)
        TextView businessNameText;
        @BindView(R.id.myorder_card_type_textView)
        TextView cardTypeText;
        @BindView(R.id.myorder_card_validate_textView)
        TextView validateText;
        @BindView(R.id.myorder_card_price)
        TextView priceText;
        @BindView(R.id.myorder_card_amount)
        TextView amountText;
        @BindView(R.id.myorder_card_sum_price)
        TextView sumPriceText;

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
