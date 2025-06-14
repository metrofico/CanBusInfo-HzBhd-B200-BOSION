package com.hzbhd.canbus.car_cus._304.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._304.bean.MainPageEntity;
import com.hzbhd.canbus.car_cus._304.view.MainView;
import com.hzbhd.canbus.car_cus._304.view.VehicleAssistanceView;
import com.hzbhd.canbus.car_cus._304.view.VehicleDiagnosisView;
import com.hzbhd.canbus.car_cus._304.view.VehicleEnergyView;
import com.hzbhd.canbus.car_cus._304.view.VehicleInfoView;
import com.hzbhd.canbus.car_cus._304.view.VehiclePanoramicView;
import com.hzbhd.canbus.car_cus._304.view.VehicleSettingsView;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MainActivity extends AbstractBaseActivity {
    private final String TAG = "_304_MainActivity";
    private MainView mMainView;

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._304_activity_main);
        findViews();
        initViews();
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mMainView.reset();
    }

    private void findViews() {
        this.mMainView = (MainView) findViewById(R.id.main_view);
    }

    private void initViews() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        VehicleDiagnosisView vehicleDiagnosisView = new VehicleDiagnosisView(this);
        VehicleInfoView vehicleInfoView = new VehicleInfoView(this);
        VehicleEnergyView vehicleEnergyView = new VehicleEnergyView(this);
        VehicleAssistanceView vehicleAssistanceView = new VehicleAssistanceView(this);
        VehicleSettingsView vehicleSettingsView = new VehicleSettingsView(this, this);
        VehiclePanoramicView vehiclePanoramicView = new VehiclePanoramicView(this);
        vehicleDiagnosisView.setLayoutParams(layoutParams);
        vehicleInfoView.setLayoutParams(layoutParams);
        vehicleEnergyView.setLayoutParams(layoutParams);
        vehicleAssistanceView.setLayoutParams(layoutParams);
        vehicleSettingsView.setLayoutParams(layoutParams);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MainPageEntity(getString(R.string._304_main_title_diagnosis), vehicleDiagnosisView));
        arrayList.add(new MainPageEntity(getString(R.string._304_main_title_assistance), vehicleAssistanceView));
        arrayList.add(new MainPageEntity(getString(R.string._304_main_title_panoramic), vehiclePanoramicView));
        this.mMainView.initView(this, arrayList);
    }
}
