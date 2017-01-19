package com.jingan.easydearbusiness.function.verificationFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingan.easydearbusiness.BuildConfig;
import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.common.dialog.DialogFactory;
import com.jingan.easydearbusiness.function.verificationFunction.data.VerficationDetailEntity;
import com.jingan.easydearbusiness.view.RoundedBitmapImageViewTarget;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.verificationFunction
 * @Description:
 * @date 2017/1/1 16:02
 */

public class VerficationDetailedActivity extends BaseActivity implements VerficationContract.VerficationDetailView {

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

    private VerficationPresenter verficationPresenter;

    private String billNo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_verfication_detailed);
        verficationPresenter = new VerficationPresenter(this);
        billNo = getIntent().getStringExtra("billNo");
        setToolbarTitle("账单详情");
        ButterKnife.bind(this);
        verficationPresenter.queryFacationDetail();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }

    private void initUI(VerficationDetailEntity entity) {
        if (entity == null) {
            return;
        }
        Glide.with(this).load(BuildConfig.DOMAI + entity.getImagery()).asBitmap().centerCrop().error(R.mipmap.homepage_headimg_defaut).into(new RoundedBitmapImageViewTarget(VerficationDetaildHeadImageView));
        VerficationDetaildRemarkTextView.setText(entity.getRemark());/*转账说明*/
        VerficationDetaildNameTextView.setText(entity.getNickName());/*Nick*/
        VerficationDetaildTotalTextView.setText("+" + entity.getBuyAmount());/*金额*/
        VerficationDetaildAccountTextView.setText(entity.getAccount());/*对方账号*/
        VerficationDetaildDateImageView.setText(entity.getCreateTime());/*创建时间*/
    }

    @Override
    public void showDialog() {
        loadDialog = DialogFactory.createLoadingDialog(this, "获取账单...");
    }

    @Override
    public void dimissDialog() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public void queryVerficationDetailSuccess(List<VerficationDetailEntity> entities) {
        initUI(entities.get(0));
    }

    @Override
    public void queryVerficationDetailFailure(String erro) {
        showToast(erro);
    }

    @Override
    public String getBillNo() {
        return billNo;
    }
}
