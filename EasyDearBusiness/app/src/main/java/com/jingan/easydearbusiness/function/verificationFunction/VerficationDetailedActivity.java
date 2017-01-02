package com.jingan.easydearbusiness.function.verificationFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.view.RoundedBitmapImageViewTarget;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:
 * @date 2017/1/1 16:02
 */

public class VerficationDetailedActivity extends BaseActivity {

    @BindView(R.id.VerficationDetaild_head_ImageView)
    ImageView VerficationDetaildHeadImageView;
    @BindView(R.id.VerficationDetaild_Name_TextView)
    TextView VerficationDetaildNameTextView;
    @BindView(R.id.VerficationDetaild_total_textView)
    TextView VerficationDetaildTotalTextView;
    @BindView(R.id.VerficationDetaild_status_textView)
    TextView VerficationDetaildStatusTextView;
    @BindView(R.id.VerficationDetaild_remark_textView)
    TextView VerficationDetaildRemarkTextView;
    @BindView(R.id.VerficationDetaild_account_textView)
    TextView VerficationDetaildAccountTextView;
    @BindView(R.id.VerficationDetaild_date_ImageView)
    TextView VerficationDetaildDateImageView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_verfication_detailed);
        setToolbarCenterTitle("账单详情");
        ButterKnife.bind(this);
        Glide.with(this).load("http://v1.qzone.cc/avatar/201406/07/17/42/5392de935ac3d200.jpg%21200x200.jpg").asBitmap().centerCrop().into(new RoundedBitmapImageViewTarget(VerficationDetaildHeadImageView));
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}
