package com.jingan.easydearbusiness.function;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.jingan.easydearbusiness.R;
import com.jingan.easydearbusiness.base.BaseActivity;
import com.jingan.easydearbusiness.common.dialog.DialogFactory;
import com.jingan.easydearbusiness.function.billFunction.BillFragment;
import com.jingan.easydearbusiness.function.verificationFunction.VerficationFragment;
import com.jingan.easydearbusiness.utils.IActivityManage;
import com.jingan.easydearbusiness.utils.TimeSelector;
import com.jingan.easydearbusiness.view.MainBottomNavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.jingan.easydearbusiness.function.mainFunction
 * @Description:主界面
 * @date 2017/1/1 10:31
 */

public class MainActivity extends BaseActivity implements MainBottomNavigationBar.BottomTabSelectedListener {

    private final int TAB_BILL_VALUE = 0;
    private final int TAB_VERFICATION_VALUE = 1;

    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setSwipeEnabled(false);
        setToolbarCenterTitle("宁波宝恒宝马(诚信路店)");
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        initBottomNavigationBar();
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        mainBottomNavigationBar.addTabItem(R.mipmap.ic_home_white_24dp, R.string.main_tab_bill).addTabItem(R.mipmap.ic_home_white_24dp, R.string.main_tab_order);
        mainBottomNavigationBar.addFragment(BillFragment.newInstance()).addFragment(VerficationFragment.newInstance());
        mainBottomNavigationBar.setDefaultFragment(TAB_BILL_VALUE);
        mainBottomNavigationBar.setTabSelectedListener(this);
    }

    @Override
    public void onClickToolbarRightLayout() {
        super.onClickToolbarRightLayout();
        TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
//                Toast.makeText(getApplicationContext(), time, Toast.LENGTH_LONG).show();
                setToolbarCenterTitle(time.substring(time.indexOf("-") + 1, time.length())+"月账单");
            }
        }, "2000-11-22 17:34", "2020-12-1 15:20");
        timeSelector.show();
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_BILL_VALUE:
                setToolbarCenterTitle("宁波宝恒宝马(诚信路店)");
                setToolbarRightText("");
                break;
            case TAB_VERFICATION_VALUE:
                setToolbarCenterTitle("本月账单");
                setToolbarRightText("筛选");
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
          /*将这一行注释掉，阻止activity保存fragment的状态*/
//        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            showExitDialog();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 退出Dialog
     */
    private void showExitDialog() {
        DialogFactory.showMsgDialog(this, "退出提示", getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                IActivityManage.getInstance().exit();
                System.exit(0);
            }
        });
    }
}
