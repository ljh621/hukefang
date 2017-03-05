package com.yunwei.easyDear.function.mainFuncations.locationFunction;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.yunwei.easyDear.R;
import com.yunwei.easyDear.base.BaseActivity;
import com.yunwei.easyDear.base.DataApplication;
import com.yunwei.easyDear.function.mainFuncations.MainContract;
import com.yunwei.easyDear.function.mainFuncations.MainPresenter;
import com.yunwei.easyDear.function.mainFuncations.data.soure.MainRemoteRepo;
import com.yunwei.easyDear.utils.ILog;
import com.yunwei.easyDear.view.MeasuredGridView;
import com.yunwei.easyDear.view.MeasuredListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by LJH on 2017/3/4.
 */
public class LocationActivity extends BaseActivity implements LocationContact.LocationView, MainContract.MainView, AdapterView.OnItemClickListener {

    final String TAG = getClass().getSimpleName();

    @BindView(R.id.location_current_city)
    TextView mCurCityTextView;
    @BindView(R.id.location_district_gridView)
    MeasuredGridView mCurCityDistrictGridView;

    @BindView(R.id.location_locate_city_text)
    TextView mLocateCityText;
    @BindView(R.id.location_locate_city)
    TextView mLocateCityTextView;

    @BindView(R.id.location_hot_city_text)
    TextView mHotCityText;
    @BindView(R.id.location_hot_city_gridView)
    MeasuredGridView mHotCityGridView;

    @BindView(R.id.location_all_city_listView)
    MeasuredListView mGroupedAllCityListView;

    private LocationPresenter mLocationPresenter;
    private AMapLocation mCurrentLocation;
    private AMapLocation mLocatedLocation;
    private LocationAdapter mCurCityDistrictAdapter;
    private LocationAdapter mHotCityAdapter;
    private ArrayList<LocationEntity> mCurCityDistrictList;
    private ArrayList<LocationEntity> mHotCityList;
    private ArrayList<LocationEntity> mAllCityList;
    private ArrayList<LocationEntity> mGroupedAllCityList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        setToolbarTitle("选择城市");
        ButterKnife.bind(this);
        mCurrentLocation = DataApplication.getInstance().getLocation();
        initPresenter();
        initCurCityDistrictGridView();
        initLocatedCity();
        initHotCityGridView();
        initAllCityListView();
        requestAllCity();
    }

    private void initPresenter() {
        mLocationPresenter = new LocationPresenter(LocationRemoteRepo.getInstance(), this);
    }

    private void initCurCityDistrictGridView() {
        mCurCityTextView.setText("当前: " + mCurrentLocation.getCity());
        mCurCityDistrictAdapter = new LocationAdapter(LocationActivity.this);
        mCurCityDistrictGridView.setAdapter(mCurCityDistrictAdapter);
        mCurCityDistrictGridView.setOnItemClickListener(this);
    }

    private void initLocatedCity() {
        new MainPresenter(MainRemoteRepo.newInstance(), this).startLocation();
    }

    private void initHotCityGridView() {
        mHotCityList = new ArrayList<LocationEntity>();
        mHotCityAdapter = new LocationAdapter(LocationActivity.this);
        mHotCityGridView.setAdapter(mHotCityAdapter);
        mHotCityGridView.setOnItemClickListener(this);
    }

    private void initAllCityListView() {
        mGroupedAllCityListView.setOnItemClickListener(this);
    }

    private void requestAllCity() {
        mLocationPresenter.requestAllCity();
    }

    @OnClick({R.id.location_switch_district, R.id.location_locate_city})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.location_switch_district:
                requestCurrentCityDistricts();
                break;
            case R.id.location_locate_city:
                if (mLocatedLocation != null) {
                    DataApplication.getInstance().setLocation(mLocatedLocation);
                    ILog.v(TAG, "切换到" + mLocatedLocation.getCity());
                }
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public String getCityCode() {
        String adCode = mCurrentLocation.getAdCode();
        int len = adCode.length();
        Log.v(TAG, "getCityCode adCode = " + adCode);
        if (len >= 4) {
            return adCode.substring(0, 4);
        }
        return adCode;
    }

    private void requestCurrentCityDistricts() {
        mLocationPresenter.requestCurrentCityDistrict();
    }

    @Override
    public void onGetDistrictSuccess(List<LocationEntity> entities) {
        if (entities == null) {
            return;
        }
        mCurCityDistrictList = (ArrayList<LocationEntity>) entities;
        mCurCityDistrictGridView.setVisibility(View.VISIBLE);
        mCurCityDistrictAdapter.setLocationList(mCurCityDistrictList);
        mCurCityDistrictAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDistrictFailure(String msg) {

    }

    @Override
    public void onGetAllCitySuccess(List<LocationEntity> entities) {
        if (entities == null) {
            return;
        }
        mAllCityList = (ArrayList<LocationEntity>) entities;
        setHotCity();
        setAllCityList();
    }

    @Override
    public void onGetAllCityFailure(String msg) {

    }

    @Override
    public void locationSuccess(AMapLocation location) {
        mLocatedLocation = location;
        if (mLocatedLocation != null) {
            mLocateCityText.setVisibility(View.VISIBLE);
            mLocateCityTextView.setVisibility(View.VISIBLE);
            mLocateCityTextView.setText("切换到: " + mLocatedLocation.getCity());
        }
    }

    private void setHotCity() {
        if (mHotCityList == null) {
            mHotCityList = new ArrayList<LocationEntity>();
        } else {
            mHotCityList.clear();
        }
        for (LocationEntity entity : mAllCityList) {
            if ("是".equals(entity.getIshot())) {
                mHotCityList.add(entity);
            }
        }
        if (mHotCityList.size() > 0) {
            mHotCityText.setVisibility(View.VISIBLE);
            mHotCityAdapter.setLocationList(mHotCityList);
            mHotCityAdapter.notifyDataSetChanged();
        }
    }

    private void setAllCityList() {
        if (mGroupedAllCityList == null) {
            mGroupedAllCityList = new ArrayList<LocationEntity>();
        } else {
            mGroupedAllCityList.clear();
        }

        /**
         *  比对 AllCityList 中每个 Entity 的 Ping, 根据 Ping 的值来分组
         *  每一个 Ping 对应的 List 暂时放到 singleGroupList 中
         *  最后再加入到 mGroupedAllCityList
         * */
        ArrayList<LocationEntity> singleGroupList = new ArrayList<LocationEntity>();
        for (int i = 'A'; i <= 'Z'; i++) {
            char c = (char) i;
            String ping = String.valueOf(c);

            singleGroupList.clear();
            for (LocationEntity entity : mAllCityList) {
                if (ping.equals(entity.getPing())) {
                    singleGroupList.add(entity);
                }
            }

            if (singleGroupList.size() > 0) {
                LocationEntity nameEntity = new LocationEntity();
                nameEntity.setName(ping);
                nameEntity.setCode("");
                nameEntity.setPing("");
                nameEntity.setIshot("");
                mGroupedAllCityList.add(nameEntity);
                mGroupedAllCityList.addAll(singleGroupList);
            }
        }

        int size = mGroupedAllCityList.size();
        String[] cityArr = new String[size];
        for (int i = 0; i < size; i++) {
            cityArr[i] = mGroupedAllCityList.get(i).getName();
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(LocationActivity.this, R.layout.item_search_history_layout, cityArr);
        mGroupedAllCityListView.setAdapter(arrayAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.v(TAG, "----------> OnItemClick i = " + i);

        LocationEntity entity = new LocationEntity();
        switch (adapterView.getId()) {
            case R.id.location_district_gridView:
                entity = mCurCityDistrictList.get(i);
                break;
            case R.id.location_hot_city_gridView:
                entity = mHotCityList.get(i);
                break;
            case R.id.location_all_city_listView:
                entity = mGroupedAllCityList.get(i);
                break;
            default:
                break;
        }
        // TODO Click Item 后的处理
        Log.v(TAG, "----------> entity getName = " + entity.getName());
        Log.v(TAG, "----------> entity getCode = " + entity.getCode());
        Log.v(TAG, "----------> entity getPing = " + entity.getPing());
        Log.v(TAG, "----------> entity getIshot = " + entity.getIshot());
    }
}
