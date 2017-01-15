package com.yunwei.easyDear.function.mainFuncations.mycardlistFunction;

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
import com.yunwei.easyDear.function.mainFuncations.myorderlistFunction.OrderItemEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyCardAdapter extends BaseRecyclerViewAdapter<CardItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MyCardAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MyCardAdapter.ItemViewHolder viewHolder = new MyCardAdapter.ItemViewHolder(inflater.inflate(R.layout.item_mycard_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        if (position >= 2) {
            viewHolder.viceTitleText.setText(mLists.get(position).getViceTitle());
            viewHolder.cardNameText.setText(mLists.get(position).getCardName());
            viewHolder.validateText.setText(mLists.get(position).getValidate());
//            Glide.with(mContent).load(mLists.get(position).getHeadUrl()).into(viewHolder.logoView);
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mycard_business_logo)
        ImageView logoView;
        @BindView(R.id.mycard_vice_title)
        TextView viceTitleText;
        @BindView(R.id.mycard_name)
        TextView cardNameText;
        @BindView(R.id.mycard_validate)
        TextView validateText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.mycard_name})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.mycard_name:
                    Log.d(TAG, "----------> onClick  position = " + position);
                    break;
            }
        }
    }
}
