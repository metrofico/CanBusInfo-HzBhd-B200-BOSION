package com.hzbhd.canbus.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hzbhd.R;
import com.hzbhd.canbus.adapter.HybirdValueLvAdapter;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HybirdActivity extends AbstractBaseActivity {
    private ImageView mBatteryDriveMotor;
    private ImageView mBatteryLevel;
    private final int[] mBatteryLevelImage = {R.drawable.camry_bacttery_level_1, R.drawable.camry_bacttery_level_2, R.drawable.camry_bacttery_level_3, R.drawable.camry_bacttery_level_4, R.drawable.camry_bacttery_level_5, R.drawable.camry_bacttery_level_6, R.drawable.camry_bacttery_level_7, R.drawable.camry_bacttery_level_full};
    private ImageView mEngineDriveMotor;
    private ImageView mEngineDriveWheel;
    private HybirdValueLvAdapter mHybirdValueLvAdapter;
    private List<String> mList;
    private ImageView mMotorDriveBattery;
    private ImageView mMotorDriveWheel;
    private OnHybirdPageStatusListener mOnHybirdPageStatusListener;
    private RecyclerView mRecyclerView;
    private TextView mTitleTv;
    private ImageView mWheelDriveMotor;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_hybird);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mWheelDriveMotor = findViewById(R.id.camry_power_wheel_drive_motor);
        this.mBatteryDriveMotor = findViewById(R.id.camry_power_battery_drive_motor);
        this.mEngineDriveWheel = findViewById(R.id.camry_power_engine_drive_wheel);
        this.mEngineDriveMotor = findViewById(R.id.camry_power_engine_drive_motor);
        this.mMotorDriveWheel = findViewById(R.id.camry_power_motor_drive_wheel);
        this.mMotorDriveBattery = findViewById(R.id.camry_power_motor_drive_battery);
        this.mBatteryLevel = findViewById(R.id.camry_power_battery_level);
        this.mRecyclerView = findViewById(R.id.rv_list);
        this.mTitleTv = findViewById(R.id.tv_title);
    }

    private void initViews() {
        HybirdPageUiSet hybirdPageUiSet = getUiMgrInterface(this).getHybirdPageUiSet(this);
        if (hybirdPageUiSet != null) {
            OnHybirdPageStatusListener onHybirdPageStatusListener = hybirdPageUiSet.getOnHybirdPageStatusListener();
            this.mOnHybirdPageStatusListener = onHybirdPageStatusListener;
            if (onHybirdPageStatusListener != null) {
                onHybirdPageStatusListener.onStatusChange(1);
            }
        }
        this.mList = new ArrayList();
        this.mHybirdValueLvAdapter = new HybirdValueLvAdapter(this.mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mHybirdValueLvAdapter);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            if (!TextUtils.isEmpty(GeneralHybirdData.title)) {
                this.mTitleTv.setText(GeneralHybirdData.title);
            }
            if (GeneralHybirdData.valueList != null) {
                this.mList.clear();
                this.mList.addAll(GeneralHybirdData.valueList);
                this.mHybirdValueLvAdapter.notifyDataSetChanged();
            }
            if (GeneralHybirdData.powerBatteryLevel == 0) {
                this.mBatteryLevel.setBackground(null);
            } else {
                this.mBatteryLevel.setBackgroundResource(this.mBatteryLevelImage[GeneralHybirdData.powerBatteryLevel - 1]);
            }
            if (GeneralHybirdData.isWheelDriveMotor) {
                this.mWheelDriveMotor.setVisibility(View.VISIBLE);
            } else {
                this.mWheelDriveMotor.setVisibility(View.GONE);
            }
            if (GeneralHybirdData.isBatteryDriveMotor) {
                this.mBatteryDriveMotor.setVisibility(View.VISIBLE);
            } else {
                this.mBatteryDriveMotor.setVisibility(View.GONE);
            }
            if (GeneralHybirdData.isEngineDriveWheel) {
                this.mEngineDriveWheel.setVisibility(View.VISIBLE);
            } else {
                this.mEngineDriveWheel.setVisibility(View.GONE);
            }
            if (GeneralHybirdData.isEngineDriveMotor) {
                this.mEngineDriveMotor.setVisibility(View.VISIBLE);
            } else {
                this.mEngineDriveMotor.setVisibility(View.GONE);
            }
            if (GeneralHybirdData.isMotorDriveWheel) {
                this.mMotorDriveWheel.setVisibility(View.VISIBLE);
            } else {
                this.mMotorDriveWheel.setVisibility(View.GONE);
            }
            if (GeneralHybirdData.isMotorDriveBattery) {
                this.mMotorDriveBattery.setVisibility(View.VISIBLE);
            } else {
                this.mMotorDriveBattery.setVisibility(View.GONE);
            }
        }
    }
}
