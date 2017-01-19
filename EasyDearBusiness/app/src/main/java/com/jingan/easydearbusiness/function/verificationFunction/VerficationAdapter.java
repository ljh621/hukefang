package com.jingan.easydearbusiness.function.verificationFunction;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingan.easydearbusiness.BuildConfig;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseRecyclerViewAdapter;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationEntity;
import com.jingan.easydearbusiness.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:
 * @date 2017/1/1 14:52
 */

public class VerficationAdapter extends BaseRecyclerViewAdapter<VerficationEntity> {


    public VerficationAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.item_verfacition_layout, parent, false));
    }

    @Override
    public void onBindBaseViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        VerficationEntity entity = mLists.get(position);
        Glide.with(mContent).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(viewHolder.itemVerfacitionHeadImageView));
        viewHolder.itemVerfacitionTotalTextView.setText("+" + entity.getBuyAmount());
        viewHolder.itemVerfacitionDayTextView.setText(entity.getCreateTime().substring(entity.getCreateTime().lastIndexOf("-") + 1, entity.getCreateTime().lastIndexOf(" ")) + "日");
        viewHolder.itemVerfacitionTimeTextView.setText(entity.getCreateTime().substring(entity.getCreateTime().lastIndexOf(" "), entity.getCreateTime().length()));
        viewHolder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.onItemClick(view, mLists.get(position), position);
                }
            }
        });
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_verfacition_day_textView)
        TextView itemVerfacitionDayTextView;
        @BindView(R.id.item_verfacition_time_textView)
        TextView itemVerfacitionTimeTextView;
        @BindView(R.id.itemVerfacition_date_layout)
        LinearLayout itemVerfacitionDateLayout;
        @BindView(R.id.item_verfacition_head_imageView)
        ImageView itemVerfacitionHeadImageView;
        @BindView(R.id.item_verfacition_total_textView)
        TextView itemVerfacitionTotalTextView;
        @BindView(R.id.itemVerfacition_layout)
        RelativeLayout itemLayout;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
//            itemLayout = ButterKnife.findById(view, R.id.itemVerfacition_layout);
        }
    }
}
