package com.hzbhd.canbus.car._206;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.adapter.HybirdValueLvAdapter;
import com.hzbhd.canbus.interfaces.OnHybirdPageStatusListener;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_set.HybirdPageUiSet;
import com.hzbhd.canbus.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MqbHybirdActivity extends AbstractBaseActivity {
    private ImageView mBattery;
    private int[] mBatteryLevelImage = {R.drawable.vw_golf7_hybrid_battery_0, R.drawable.vw_golf7_hybrid_battery_1, R.drawable.vw_golf7_hybrid_battery_2, R.drawable.vw_golf7_hybrid_battery_3, R.drawable.vw_golf7_hybrid_battery_4, R.drawable.vw_golf7_hybrid_battery_5, R.drawable.vw_golf7_hybrid_battery_6, R.drawable.vw_golf7_hybrid_battery_7, R.drawable.vw_golf7_hybrid_battery_8, R.drawable.vw_golf7_hybrid_battery_9, R.drawable.vw_golf7_hybrid_battery_10};
    private TextView mBatteryValue;
    private ImageView mEnergyDirection;
    private HybirdValueLvAdapter mHybirdValueLvAdapter;
    private List<String> mList;
    private ImageView mMotor;
    private OnHybirdPageStatusListener mOnHybirdPageStatusListener;
    private RecyclerView mRecyclerView;
    private ImageView mWheelTrackLeft;
    private ImageView mWheelTrackRight;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._206_hybrid_activity);
        findViews();
        initViews();
    }

    private void findViews() {
        this.mMotor = (ImageView) findViewById(R.id.iv_motor);
        this.mBattery = (ImageView) findViewById(R.id.iv_battery);
        this.mEnergyDirection = (ImageView) findViewById(R.id.iv_flow);
        this.mWheelTrackLeft = (ImageView) findViewById(R.id.iv_track_left);
        this.mWheelTrackRight = (ImageView) findViewById(R.id.iv_track_right);
        this.mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        this.mBatteryValue = (TextView) findViewById(R.id.tv_battery_value);
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
        if (getResources().getConfiguration().orientation == 2) {
            linearLayoutManager.setOrientation(0);
        }
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.setAdapter(this.mHybirdValueLvAdapter);
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        try {
            refreshUi(null);
        } catch (Exception e) {
            LogUtil.showLog("VehicleMonitorActivity onResume:" + e.toString());
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (isShouldRefreshUi()) {
            if (GeneralHybirdData.valueList != null) {
                this.mList.clear();
                this.mList.addAll(GeneralHybirdData.valueList);
                this.mHybirdValueLvAdapter.notifyDataSetChanged();
            }
            this.mBatteryValue.setVisibility(0);
            this.mBatteryValue.setText(GeneralHybirdData.powerBatteryValue + "%");
            if (GeneralHybirdData.powerBatteryLevel == 0) {
                this.mBattery.setBackgroundResource(R.drawable.vw_golf7_hybrid_battery_0);
            } else {
                this.mBattery.setBackgroundResource(this.mBatteryLevelImage[GeneralHybirdData.powerBatteryLevel]);
            }
            this.mEnergyDirection.setVisibility(0);
            this.mWheelTrackLeft.setVisibility(0);
            this.mWheelTrackRight.setVisibility(0);
            this.mMotor.setVisibility(0);
            this.mBattery.setVisibility(0);
            if (!GeneralHybirdData.isShowMotor) {
                this.mMotor.setVisibility(8);
            }
            if (!GeneralHybirdData.isShowBattery) {
                this.mBattery.setVisibility(8);
            }
            if (GeneralHybirdData.energyDirection == 0) {
                this.mEnergyDirection.setVisibility(8);
            } else if (GeneralHybirdData.energyDirection == 1) {
                this.mEnergyDirection.setImageResource(R.drawable.vw_golf7_hybrid_flow_motor_to_battery);
            } else {
                this.mEnergyDirection.setImageResource(R.drawable.vw_golf7_hybrid_flow_battery_to_motor);
            }
            int i = GeneralHybirdData.wheelTrack;
            if (i == 0) {
                this.mWheelTrackLeft.setVisibility(8);
                this.mWheelTrackRight.setVisibility(8);
                return;
            }
            if (i == 1) {
                this.mWheelTrackLeft.setImageResource(R.drawable.vw_golf7_hybrid_drive_engine_left);
                this.mWheelTrackRight.setImageResource(R.drawable.vw_golf7_hybrid_drive_engine_right);
                return;
            }
            if (i == 2) {
                this.mWheelTrackLeft.setImageResource(R.drawable.vw_golf7_hybrid_drive_energy_recovery_left);
                this.mWheelTrackRight.setImageResource(R.drawable.vw_golf7_hybrid_drive_energy_recovery_right);
            } else if (i == 3) {
                this.mWheelTrackLeft.setImageResource(R.drawable.vw_golf7_hybrid_drive_motor_left);
                this.mWheelTrackRight.setImageResource(R.drawable.vw_golf7_hybrid_drive_motor_right);
            } else {
                if (i != 4) {
                    return;
                }
                this.mWheelTrackLeft.setImageResource(R.drawable.vw_golf7_hybrid_drive_hy_left);
                this.mWheelTrackRight.setImageResource(R.drawable.vw_golf7_hybrid_drive_hy_right);
            }
        }
    }
}
