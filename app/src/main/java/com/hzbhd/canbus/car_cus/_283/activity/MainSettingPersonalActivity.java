package com.hzbhd.canbus.car_cus._283.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.widget.NestedScrollView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.bean.SettingDialogBean;
import com.hzbhd.canbus.car_cus._283.view.MyRelativeView;
import com.hzbhd.canbus.car_cus._283.view.SettingDialogView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingDoorView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingLightView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingPAView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingParkingView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRearView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRevertView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTimeView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingTyreView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUnitView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingUpKeepView;
import com.hzbhd.canbus.car_cus._283.view.carsetting.HybridView;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MainSettingPersonalActivity extends AbstractBaseActivity implements View.OnClickListener {
    public static final String CARSETTING_BUNDLE_WHAT = "_283_CarSetting_what";
    public static final int CARSETTING_CONBINATION = 2;
    public static final int CARSETTING_DOOR = 3;
    public static final int CARSETTING_HYBRID = 13;
    public static final int CARSETTING_LIGHT = 4;
    public static final int CARSETTING_NOW = 1;
    public static final int CARSETTING_PA = 6;
    public static final int CARSETTING_PARKING = 5;
    public static final int CARSETTING_PERSONAL = 16;
    public static final int CARSETTING_REAR = 7;
    public static final int CARSETTING_REVERT = 8;
    public static final int CARSETTING_TIME = 9;
    public static final int CARSETTING_TYRE = 10;
    public static final int CARSETTING_UNIT = 11;
    public static final int CARSETTING_UPKEEP = 12;
    private ImageView iv_back;
    private CarSettingCombinationView mCarSettingCombinationView;
    private CarSettingDoorView mCarSettingDoorView;
    private CarSettingLightView mCarSettingLightView;
    private CarSettingPAView mCarSettingPAView;
    private CarSettingParkingView mCarSettingParkingView;
    private CarSettingRearView mCarSettingRearView;
    private CarSettingRevertView mCarSettingRevertView;
    private CarSettingTimeView mCarSettingTimeView;
    private CarSettingTyreView mCarSettingTyreView;
    private CarSettingUnitView mCarSettingUnitView;
    private CarSettingUpKeepView mCarSettingUpKeepView;
    private HybridView mHybridView;
    private int mWhat;
    private NestedScrollView mainSettingScrollView;
    private SettingDialogView sdv_esc;
    private SettingView sv_carLight;
    private SettingView sv_combination;
    private SettingView sv_door;
    private SettingView sv_hybrid;
    private SettingView sv_pa;
    private SettingView sv_parking;
    private SettingView sv_rear;
    private SettingView sv_revert;
    private SettingView sv_time;
    private SettingView sv_tyre;
    private SettingView sv_unit;
    private SettingView sv_upkeep;
    private TextView tv_title;
    private List<SettingDialogBean> lists = new ArrayList();
    private boolean isSecondSetting = false;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_fragment_main_personal_setting);
        initView();
        getWindow().setFlags(1024, 1024);
    }

    private void initView() {
        this.mainSettingScrollView = (NestedScrollView) findViewById(R.id.mainSettingScrollView);
        this.iv_back = (ImageView) findViewById(R.id.iv_back);
        this.sdv_esc = (SettingDialogView) findViewById(R.id.sdv_esc);
        this.sv_carLight = (SettingView) findViewById(R.id.sv_carLight);
        this.sv_tyre = (SettingView) findViewById(R.id.sv_tyre);
        this.sv_pa = (SettingView) findViewById(R.id.sv_pa);
        this.sv_parking = (SettingView) findViewById(R.id.sv_parking);
        this.sv_rear = (SettingView) findViewById(R.id.sv_rear);
        this.sv_door = (SettingView) findViewById(R.id.sv_door);
        this.sv_combination = (SettingView) findViewById(R.id.sv_combination);
        this.sv_time = (SettingView) findViewById(R.id.sv_time);
        this.sv_unit = (SettingView) findViewById(R.id.sv_unit);
        this.sv_upkeep = (SettingView) findViewById(R.id.sv_upkeep);
        this.sv_revert = (SettingView) findViewById(R.id.sv_revert);
        this.sv_hybrid = (SettingView) findViewById(R.id.sv_hybrid);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.mCarSettingCombinationView = (CarSettingCombinationView) findViewById(R.id.carSettingCombinationView);
        this.mCarSettingDoorView = (CarSettingDoorView) findViewById(R.id.carSettingDoorView);
        this.mCarSettingLightView = (CarSettingLightView) findViewById(R.id.carSettingLightView);
        this.mCarSettingParkingView = (CarSettingParkingView) findViewById(R.id.carSettingParkingView);
        this.mCarSettingPAView = (CarSettingPAView) findViewById(R.id.carSettingPAView);
        this.mCarSettingRearView = (CarSettingRearView) findViewById(R.id.carSettingRearView);
        this.mCarSettingRevertView = (CarSettingRevertView) findViewById(R.id.carSettingRevertView);
        this.mCarSettingTimeView = (CarSettingTimeView) findViewById(R.id.carSettingTimeView);
        this.mCarSettingTyreView = (CarSettingTyreView) findViewById(R.id.carSettingTyreView);
        this.mCarSettingUnitView = (CarSettingUnitView) findViewById(R.id.carSettingUnitView);
        this.mCarSettingUpKeepView = (CarSettingUpKeepView) findViewById(R.id.carSettingUpKeepView);
        this.mHybridView = (HybridView) findViewById(R.id.hybridView);
        if (SelectCanTypeUtil.getCurrentCanDiffId() == 1) {
            this.sv_hybrid.setVisibility(0);
        } else {
            this.sv_hybrid.setVisibility(8);
        }
        this.iv_back.setOnClickListener(this);
        this.sv_carLight.setOnClickListener(this);
        this.sv_revert.setOnClickListener(this);
        this.sv_tyre.setOnClickListener(this);
        this.sv_pa.setOnClickListener(this);
        this.sv_parking.setOnClickListener(this);
        this.sv_rear.setOnClickListener(this);
        this.sv_door.setOnClickListener(this);
        this.sv_combination.setOnClickListener(this);
        this.sv_time.setOnClickListener(this);
        this.sv_unit.setOnClickListener(this);
        this.sv_upkeep.setOnClickListener(this);
        this.sv_hybrid.setOnClickListener(this);
        this.lists.add(new SettingDialogBean(getString(R.string._283_esc_asr)));
        this.lists.add(new SettingDialogBean(getString(R.string._283_esc_cativate)));
        this.lists.add(new SettingDialogBean(getString(R.string._283_esc_sport)));
        this.lists.get(0).setSelect(true);
        this.sdv_esc.setList(this.lists);
        this.sdv_esc.setOnItemClickListener(new SettingDialogView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.activity.MainSettingPersonalActivity$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingDialogView.OnItemClickListener
            public final void onClick(View view, int i) {
                MessageSender.sendMsg(new byte[]{22, -118, 3, (byte) i});
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back /* 2131362534 */:
                if (this.isSecondSetting) {
                    allViewGone();
                    break;
                } else {
                    onBackPressed();
                    break;
                }
            case R.id.sv_carLight /* 2131363444 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_3);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingLightView.setVisibility(0);
                break;
            case R.id.sv_combination /* 2131363447 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_8);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingCombinationView.setVisibility(0);
                break;
            case R.id.sv_door /* 2131363449 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_7);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingDoorView.setVisibility(0);
                break;
            case R.id.sv_hybrid /* 2131363452 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_hybrid);
                this.mainSettingScrollView.setVisibility(8);
                this.mHybridView.setVisibility(0);
                break;
            case R.id.sv_pa /* 2131363453 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_4);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingPAView.setVisibility(0);
                break;
            case R.id.sv_parking /* 2131363454 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_5);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingParkingView.setVisibility(0);
                break;
            case R.id.sv_rear /* 2131363455 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_6);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingRearView.setVisibility(0);
                break;
            case R.id.sv_revert /* 2131363458 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_12);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingRevertView.setVisibility(0);
                break;
            case R.id.sv_time /* 2131363468 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_9);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingTimeView.setVisibility(0);
                break;
            case R.id.sv_tyre /* 2131363470 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_2);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingTyreView.setVisibility(0);
                break;
            case R.id.sv_unit /* 2131363471 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_10);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingUnitView.setVisibility(0);
                break;
            case R.id.sv_upkeep /* 2131363472 */:
                this.isSecondSetting = true;
                this.tv_title.setText(R.string._283_setting_title_11);
                this.mainSettingScrollView.setVisibility(8);
                this.mCarSettingUpKeepView.setVisibility(0);
                break;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        if (bundle == null) {
        }
        this.mWhat = bundle.getInt("_283_CarSetting_what");
        Log.d("mww", "refreshUi---------- " + this.mWhat);
        int i = this.mWhat;
        if (i == 16) {
            ((MyRelativeView) findViewById(R.id.settings)).refreshUi();
            return;
        }
        switch (i) {
            case 1:
                this.sdv_esc.setSelect(GeneralDzData.esc);
                break;
            case 2:
                this.mCarSettingCombinationView.m1103x8b1207a2();
                break;
            case 3:
                this.mCarSettingDoorView.m1104x9fcd31a5();
                break;
            case 4:
                this.mCarSettingLightView.m1107xd61148e9();
                break;
            case 5:
                this.mCarSettingParkingView.m1112xc1aadb0b();
                break;
            case 6:
                this.mCarSettingPAView.m1109xbc8e36c8();
                break;
            case 7:
                this.mCarSettingRearView.m1113x253b7c9b();
                break;
            case 8:
                this.mCarSettingRevertView.refreshUi();
                break;
            case 9:
                this.mCarSettingTimeView.m1116xdc59b944();
                break;
            case 10:
                this.mCarSettingTyreView.m1118x3345a86f();
                break;
            case 11:
                this.mCarSettingUnitView.m1119x46ba0c7b();
                break;
            case 12:
                this.mCarSettingUpKeepView.m1120x7d055937();
                break;
            case 13:
                this.mHybridView.m1124x1900f6f7();
                break;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 4 && this.isSecondSetting) {
            allViewGone();
            return false;
        }
        return super.onKeyUp(i, keyEvent);
    }

    private void allViewGone() {
        this.isSecondSetting = false;
        this.mCarSettingCombinationView.setVisibility(8);
        this.mCarSettingDoorView.setVisibility(8);
        this.mCarSettingLightView.setVisibility(8);
        this.mCarSettingParkingView.setVisibility(8);
        this.mCarSettingPAView.setVisibility(8);
        this.mCarSettingRearView.setVisibility(8);
        this.mCarSettingRevertView.setVisibility(8);
        this.mCarSettingTimeView.setVisibility(8);
        this.mCarSettingTyreView.setVisibility(8);
        this.mCarSettingUnitView.setVisibility(8);
        this.mCarSettingUpKeepView.setVisibility(8);
        this.mHybridView.setVisibility(8);
        this.tv_title.setText(R.string._283_car_setting);
        this.mainSettingScrollView.setVisibility(0);
    }
}
