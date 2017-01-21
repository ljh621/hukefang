package com.yunwei.easyDear.function.mainFuncations.cardDetailFunction;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.articleFunction.CardItemEntity;
import com.yunwei.easyDear.function.mainFuncations.cardpayFunction.CardPayActivity;
import com.yunwei.easyDear.utils.ISkipActivityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/1/15.
 */

public class CardDetailActivity extends BaseActivity implements CardDetailContact.CardDetailView {

    private final String TAG = this.getClass().getSimpleName();

    @BindView(R.id.card_detail_business_logo)
    ImageView mBusinessLogo;
    @BindView(R.id.card_detail_business_name)
    TextView mBusinessName;
    @BindView(R.id.card_detail_intro)
    TextView mAssociateName;
    @BindView(R.id.card_detail_price)
    TextView mCardPrice;
    @BindView(R.id.card_detail_validity_date)
    TextView mCardEndTime;
    @BindView(R.id.card_detail_number)
    TextView mCardNo;

    private CardDetailPresenter mCardDetailPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_card_detail);
        setToolbarVisibility(View.GONE);
//        setSwipeEnabled(false);
        ButterKnife.bind(this);
        initPresenter();
        requestCardDetailInfo();
    }

    private void initPresenter() {
        mCardDetailPresenter = new CardDetailPresenter(CardDetailRemoteRepo.getInstance(), this);
    }

    /**
     * 获取券详情
     */
    private void requestCardDetailInfo() {
        String cardNo = "20170113211446474936";
        mCardDetailPresenter.requestCardDetail(cardNo);
    }

    /**
     * 设置券详情信息
     */
    @Override
    public void setCardDetailInfo(CardItemEntity entity) {
        if (entity == null) {
            return;
        }

        Message msg = new Message();
        msg.what = 0x100;
        msg.obj = entity;
        mHandler.sendMessageDelayed(msg, 5000);
    }

    public void setCardDetailInfos(CardItemEntity entity) {
        if (entity == null) {
            return;
        }

        mBusinessName.setText(entity.getCardName());
        mAssociateName.setText(entity.getAssociateName());
        mCardPrice.setText("总价 " + entity.getCardPrice());
        mCardEndTime.setText("有效期至 " + entity.getCardEndTime());
        mCardNo.setText(entity.getCardNo());
        Glide.with(this).load(entity.getLogo()).into(mBusinessLogo);
    }

    @OnClick({R.id.card_detail_back, R.id.card_purchase})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_detail_back:
                onBackPressed();
                break;
            case R.id.card_purchase:
                ISkipActivityUtil.startIntent(this, CardPayActivity.class);
                break;
        }
    }

    @Override
    protected void dispatchMessage(Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case 0x100:
                setCardDetailInfos((CardItemEntity) msg.obj);
                break;
        }
    }
}
