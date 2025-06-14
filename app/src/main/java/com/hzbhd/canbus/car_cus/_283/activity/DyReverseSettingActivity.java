package com.hzbhd.canbus.car_cus._283.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.hzbhd.R;
import com.hzbhd.canbus.car_cus._283.HandlerUtils;
import com.hzbhd.canbus.car_cus._283.utils._283_SharedPreferencesUtils;
import com.hzbhd.canbus.car_cus._283.view.CarImageView;
import com.hzbhd.canbus.util.SharePreUtil;

/* loaded from: classes2.dex */
public class DyReverseSettingActivity extends AppCompatActivity implements View.OnClickListener {
    private int carTypeCount;
    private int[] carTypes = {R.string._283_car_type_0, R.string._283_car_type_1, R.string._283_car_type_2, R.string._283_car_type_3};
    private CheckBox dialog_radar_cb;
    private LinearLayout dialog_radar_lin;
    private CheckBox jzx_cb;
    private LinearLayout jzx_lay;
    private CheckBox ld_cb;
    private LinearLayout ld_lay;
    private ImageView leftChooseImage;
    private SharedPreferences.Editor mEditor;
    private ImageView rightChooseImage;
    private TextView textCarType;
    private CheckBox zjx_cb;
    private LinearLayout zjx_lay;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dy_reverse_setting);
        this.zjx_lay = (LinearLayout) findViewById(R.id.zjx_lay);
        this.jzx_lay = (LinearLayout) findViewById(R.id.jzx_lay);
        this.ld_lay = (LinearLayout) findViewById(R.id.ld_lay);
        this.dialog_radar_lin = (LinearLayout) findViewById(R.id.dialog_radar_lin);
        this.zjx_cb = (CheckBox) findViewById(R.id.zjx_cb);
        this.jzx_cb = (CheckBox) findViewById(R.id.jzx_cb);
        this.ld_cb = (CheckBox) findViewById(R.id.ld_cb);
        this.dialog_radar_cb = (CheckBox) findViewById(R.id.dialog_radar_cb);
        this.leftChooseImage = (ImageView) findViewById(R.id.leftChooseImage);
        this.rightChooseImage = (ImageView) findViewById(R.id.rightChooseImage);
        this.textCarType = (TextView) findViewById(R.id.textCarType);
        this.leftChooseImage.setOnClickListener(this);
        this.rightChooseImage.setOnClickListener(this);
        this.zjx_lay.setOnClickListener(this);
        this.zjx_cb.setOnClickListener(this);
        this.jzx_lay.setOnClickListener(this);
        this.jzx_cb.setOnClickListener(this);
        this.ld_lay.setOnClickListener(this);
        this.dialog_radar_lin.setOnClickListener(this);
        this.ld_cb.setOnClickListener(this);
        this.dialog_radar_cb.setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences(CarImageView.CARTYPE_Main, 0);
        this.mEditor = sharedPreferences.edit();
        int i = sharedPreferences.getInt(CarImageView.CARTYPE, 0);
        this.carTypeCount = i;
        this.textCarType.setText(this.carTypes[i]);
        initView();
    }

    private void initView() {
        setZJX(getZJX());
        setJZX(getJZX());
        setLD(getLD());
        setDialogRadarSwitch(getDialogRadar());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_radar_cb /* 2131362191 */:
            case R.id.dialog_radar_lin /* 2131362192 */:
                if (getDialogRadar()) {
                    setDialogRadarSwitch(false);
                    break;
                } else {
                    setDialogRadarSwitch(true);
                    break;
                }
            case R.id.jzx_cb /* 2131362682 */:
            case R.id.jzx_lay /* 2131362683 */:
                if (getJZX()) {
                    setJZX(false);
                    break;
                } else {
                    setJZX(true);
                    break;
                }
            case R.id.ld_cb /* 2131362716 */:
            case R.id.ld_lay /* 2131362717 */:
                if (getLD()) {
                    setLD(false);
                    break;
                } else {
                    setLD(true);
                    break;
                }
            case R.id.leftChooseImage /* 2131362719 */:
                int i = this.carTypeCount - 1;
                this.carTypeCount = i;
                if (i < 0) {
                    this.carTypeCount = this.carTypes.length - 1;
                }
                this.textCarType.setText(this.carTypes[this.carTypeCount]);
                HandlerUtils.getInstance().refreshUI();
                this.mEditor.putInt(CarImageView.CARTYPE, this.carTypeCount);
                this.mEditor.apply();
                break;
            case R.id.rightChooseImage /* 2131363147 */:
                int i2 = this.carTypeCount + 1;
                this.carTypeCount = i2;
                int[] iArr = this.carTypes;
                if (i2 >= iArr.length) {
                    this.carTypeCount = 0;
                }
                this.textCarType.setText(iArr[this.carTypeCount]);
                HandlerUtils.getInstance().refreshUI();
                this.mEditor.putInt(CarImageView.CARTYPE, this.carTypeCount);
                this.mEditor.apply();
                break;
            case R.id.zjx_cb /* 2131363809 */:
            case R.id.zjx_lay /* 2131363810 */:
                if (getZJX()) {
                    setZJX(false);
                    break;
                } else {
                    setZJX(true);
                    break;
                }
        }
    }

    private boolean getZJX() {
        return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_ZJX_SWITCH, true);
    }

    private boolean getJZX() {
        return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_JZX_SWITCH, true);
    }

    private boolean getLD() {
        return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_LD_SWITCH, true);
    }

    private boolean getDialogRadar() {
        return SharePreUtil.getBoolValue(this, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, false);
    }

    private void setZJX(boolean z) {
        if (z) {
            this.zjx_cb.setChecked(true);
        } else {
            this.zjx_cb.setChecked(false);
        }
        SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_ZJX_SWITCH, z);
    }

    private void setJZX(boolean z) {
        if (z) {
            this.jzx_cb.setChecked(true);
        } else {
            this.jzx_cb.setChecked(false);
        }
        SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_JZX_SWITCH, z);
    }

    private void setLD(boolean z) {
        if (z) {
            this.ld_cb.setChecked(true);
        } else {
            this.ld_cb.setChecked(false);
        }
        SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_LD_SWITCH, z);
    }

    private void setDialogRadarSwitch(boolean z) {
        if (z) {
            this.dialog_radar_cb.setChecked(true);
        } else {
            this.dialog_radar_cb.setChecked(false);
        }
        SharePreUtil.setBoolValue(this, _283_SharedPreferencesUtils.KEY_DIALOG_RADAR_SWITCH, z);
    }
}
