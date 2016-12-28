package com.yunwei.easyDear.function.mainFuncations;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.common.dialog.DialogFactory;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.easyDear.function.mainFuncations.homeFuncation.HomeFragment;
import com.yunwei.easyDear.function.mainFuncations.mineFuncation.MineFragment;
import com.yunwei.easyDear.function.mainFuncations.findFuncation.FindFragment;
import com.yunwei.easyDear.function.mainFuncations.messageFuncation.MessageFragment;
import com.yunwei.easyDear.function.mainFuncations.traingCodeFuncation.TrackFragment;
import com.yunwei.easyDear.utils.IActivityManage;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.view.MainBottomNavigationBar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author hezhiWu
 * @version V1.0
 * @Package com.yunwei.frame.function.mainFuncations
 * @Description:主界面
 * @date 2016/11/22 15:40
 */

public class MainActivity extends BaseActivity implements MainBottomNavigationBar.BottomTabSelectedListener, MainContract.MainView {
    private final String TAG = getClass().getSimpleName();

    /*模块标记*/
    private final int TAB_HOME = 0;
    private final int TAB_FIND = 1;
    private final int TAB_TRAINGCODE = 2;
    private final int TAB_MESSAGE = 3;
    private final int TAB_MINE = 4;


    @BindView(R.id.main_bottom_navigationBar)
    MainBottomNavigationBar mainBottomNavigationBar;

    private MainPresenter mMainPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        setToolbarVisibility(View.GONE);
        setSwipeEnabled(false);
        ButterKnife.bind(this);

        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initBottomNavigationBar();
        initPresenter();
    }

    /**
     * 初始化BottomNavigationBar
     */
    private void initBottomNavigationBar() {
        mainBottomNavigationBar.initConfig(this, R.id.main_container_FrameLayout);
        mainBottomNavigationBar.addTabItem(R.mipmap.ic_home_white_24dp, R.string.main_home_tab).addTabItem(R.mipmap.main_tab_mission_n, R.string.main_find_tab).addTabItem(R.mipmap.ic_location_on_white_24dp, R.string.main_code_tab).addTabItem(R.mipmap.main_tab_record_n, R.string.main_message_tab).addTabItem(R.mipmap.main_tab_mine_n, R.string.main_mine_tab);
        mainBottomNavigationBar.addFragment(HomeFragment.newInstance()).addFragment(FindFragment.newInstance()).addFragment(TrackFragment.newInstance()).addFragment(MessageFragment.newInstance()).addFragment(MineFragment.newInstance());
        mainBottomNavigationBar.setDefaultFragment(0);
        mainBottomNavigationBar.setTabSelectedListener(this);
    }

    /**
     * 初始化Presenter
     */
    private void initPresenter() {
        mMainPresenter = new MainPresenter(MainRemoteRepo.newInstance(), this);
//        mMainPresenter.reqQiNiuToken();
        mMainPresenter.startLocation();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
          /*将这一行注释掉，阻止activity保存fragment的状态*/
//        super.onSaveInstanceState(outState);
    }

    @Override
    public void onTabSelected(int position) {
        switch (position) {
            case TAB_HOME:
                setToolbarCenterTitle(R.string.main_home_tab);
                break;
            case TAB_FIND:
                setToolbarCenterTitle(R.string.main_find_tab);
                break;
            case TAB_TRAINGCODE:
                setToolbarCenterTitle(R.string.main_code_tab);
                break;
            case TAB_MESSAGE:
                setToolbarCenterTitle(R.string.main_message_tab);
                break;
            case TAB_MINE:
                setToolbarCenterTitle(R.string.main_mine_tab);
                break;
        }
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        ILog.d(TAG,"city="+location.getCity());
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
        DialogFactory.showMsgDialog(this, getString(R.string.dialog_title_exit), getString(R.string.exit_msg) + getString(R.string.app_name) + "?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.gc();
                IActivityManage.getInstance().exit();
                System.exit(0);
            }
        });
    }
}
