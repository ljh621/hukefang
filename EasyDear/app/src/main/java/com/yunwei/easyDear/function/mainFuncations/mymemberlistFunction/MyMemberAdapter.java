package com.yunwei.easyDear.function.mainFuncations.mymemberlistFunction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseRecyclerViewAdapter;
import com.yunwei.easyDear.function.mainFuncations.mycardlistFunction.CardItemEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class MyMemberAdapter extends BaseRecyclerViewAdapter<MemberItemEntity> implements BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener {

    public MyMemberAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        MyMemberAdapter.ItemViewHolder viewHolder = new MyMemberAdapter.ItemViewHolder(inflater.inflate(R.layout.item_mymember_layout, parent, false));
        setOnItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindBaseViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        if (position >= 2) {
            viewHolder.businessNameText.setText(mLists.get(position).getBusinessName());
            viewHolder.levelText.setText(mLists.get(position).getLevel());
            viewHolder.cardAmountText.setText(mLists.get(position).getCardAmount());
            viewHolder.creditText.setText(mLists.get(position).getCredit());
//            Glide.with(mContent).load(mLists.get(position).getHeadUrl()).into(viewHolder.logoView);
        }
    }

    @Override
    public void onItemClick(View view, Object data, int position) {

    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mymember_business_logo)
        ImageView logoView;
        @BindView(R.id.mymember_business_name)
        TextView businessNameText;
        @BindView(R.id.mymember_level)
        TextView levelText;
        @BindView(R.id.mymember_card_amount)
        TextView cardAmountText;
        @BindView(R.id.mymember_credit)
        TextView creditText;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(ItemViewHolder.this, view);
        }

        @OnClick({R.id.mymember_business_name})
        public void onClick(View view) {
            int position = getAdapterPosition();
            switch (view.getId()) {
                case R.id.mymember_business_name:
                    Log.d(TAG, "----------> onClick  position = " + position);
                    break;
            }
        }
    }
}
