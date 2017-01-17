package com.jingan.easydearbusiness.function.billFunction;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseFragment;
import com.jingan.easydearbusiness.common.dialog.DialogFactory;
import com.jingan.easydearbusiness.entity.ResponseModel;
import com.jingan.easydearbusiness.utils.TextUtil;
import com.jingan.easydearbusiness.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.billFunction
 * @Description:
 * @date 2017/1/1 12:13
 */

public class NumberBillFragment extends BaseFragment implements BillConstacts.View {

    @BindView(R.id.numberBillFragment_serial_number_textView)
    TextView mNumberTextView;

    private StringBuffer sb;

    private Dialog loadDialog;
    private BillPresenter billPresenter;

    private static NumberBillFragment fragment;

    public static NumberBillFragment newInstance() {
        if (fragment == null) {
            fragment = new NumberBillFragment();
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sb = new StringBuffer();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.number_bill_fragment, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick({R.id.number_delete, R.id.number_one, R.id.number_two, R.id.number_three, R.id.number_four, R.id.number_five, R.id.number_six, R.id.number_seven, R.id.number_eight, R.id.number_nine, R.id.number_zero, R.id.BillFragment_number_bill_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.number_one:
                setNumberText("1");
                break;
            case R.id.number_two:
                setNumberText("2");
                break;
            case R.id.number_three:
                setNumberText("3");
                break;
            case R.id.number_four:
                setNumberText("4");
                break;
            case R.id.number_five:
                setNumberText("5");
                break;
            case R.id.number_six:
                setNumberText("6");
                break;
            case R.id.number_seven:
                setNumberText("7");
                break;
            case R.id.number_eight:
                setNumberText("8");
                break;
            case R.id.number_nine:
                setNumberText("9");
                break;
            case R.id.number_zero:
                setNumberText("0");
                break;
            case R.id.number_delete:
                setNumberText("delete");
                break;
            case R.id.BillFragment_number_bill_button:
                if (TextUtil.isEmpty(mNumberTextView.getText().toString())) {
                    ToastUtil.showToast(getContext(), "序列号不能为空");
                    return;
                }
                billPresenter.billAction();
                break;
        }
    }

    private void setNumberText(String text) {
        if (TextUtils.isEmpty(text)) {
            return;
        }
        if ("delete".equals(text)) {
            if (!TextUtils.isEmpty(sb.toString())) {
                sb = sb.delete(sb.length() - 1, sb.length());
            }
        } else {
            sb.append(text);
        }
        mNumberTextView.setText(sb.toString());
    }

    @Override
    public void onBillStart() {
        loadDialog = DialogFactory.createLoadingDialog(getActivity(), "正在验证...");
    }

    @Override
    public void onBillSuccess(ResponseModel<String> result) {
        ToastUtil.showToast(getContext(), result.getMessage());
    }

    @Override
    public void onBillFailure(String error) {
        ToastUtil.showToast(getContext(), error);
    }

    @Override
    public void onBillEnd() {
        DialogFactory.dimissDialog(loadDialog);
    }

    @Override
    public String getCode() {
        return mNumberTextView.getText().toString();
    }
}
