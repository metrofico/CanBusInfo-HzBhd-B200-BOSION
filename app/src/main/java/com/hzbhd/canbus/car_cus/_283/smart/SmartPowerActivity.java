package com.hzbhd.canbus.car_cus._283.smart;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.util.DataHandleUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class SmartPowerActivity extends AbstractBaseActivity {
    private RelativeLayout bottomRelative;
    private TextView mTitle;
    private TextView mVersion;
    private RadioButton radioButtonComfort;
    private RadioButton radioButtonEconomics;
    private RadioButton radioButtonOriginal;
    private RadioButton radioButtonSport;
    private RadioButton radioButtonTrack;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private List<RadioButton> radioButtons = new ArrayList();
    private ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
    private boolean isClick = true;

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._283_activity_smart_power);
        initView();
        initData();
        if (GeneralDzSmartData.show) {
            refreshUi(null);
        }
    }

    private void initView() {
        this.seekBar = (SeekBar) findViewById(R.id.seekBar);
        this.bottomRelative = (RelativeLayout) findViewById(R.id.bottomRelative);
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.radioButtonComfort = (RadioButton) findViewById(R.id.radioButton_comfort);
        this.radioButtonOriginal = (RadioButton) findViewById(R.id.radioButton_original);
        this.radioButtonSport = (RadioButton) findViewById(R.id.radioButton_sport);
        this.radioButtonEconomics = (RadioButton) findViewById(R.id.radioButton_economics);
        this.radioButtonTrack = (RadioButton) findViewById(R.id.radioButton_track);
        this.mTitle = (TextView) findViewById(R.id.tv_title);
        this.mVersion = (TextView) findViewById(R.id.tv_version);
        setEnabled(false);
        this.radioButtons.add(this.radioButtonEconomics);
        this.radioButtons.add(this.radioButtonOriginal);
        this.radioButtons.add(this.radioButtonComfort);
        this.radioButtons.add(this.radioButtonSport);
        this.radioButtons.add(this.radioButtonTrack);
    }

    private void initData() {
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity$$ExternalSyntheticLambda0
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public final void onCheckedChanged(RadioGroup radioGroup, int i) {
                this.f$0.m1093x826b1148(radioGroup, i);
            }
        });
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (z) {
                    SmartPowerActivity.this.sendModeValue(i);
                }
            }
        });
    }

    /* renamed from: lambda$initData$0$com-hzbhd-canbus-car_cus-_283-smart-SmartPowerActivity, reason: not valid java name */
    /* synthetic */ void m1093x826b1148(RadioGroup radioGroup, int i) {
        int i2 = 0;
        switch (i) {
            case R.id.radioButton_comfort /* 2131363029 */:
                i2 = 2;
                break;
            case R.id.radioButton_original /* 2131363033 */:
                i2 = 1;
                break;
            case R.id.radioButton_sport /* 2131363035 */:
                i2 = 3;
                break;
            case R.id.radioButton_track /* 2131363037 */:
                i2 = 4;
                break;
        }
        if (this.isClick) {
            MessageSender.sendDzMsg(i2, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        setEnabled(true);
        this.isClick = false;
        scaleRadioButton(this.radioButtons.get(GeneralDzSmartData.mode));
        if (GeneralDzSmartData.mode_int >= 0) {
            this.seekBar.setProgress(GeneralDzSmartData.mode_int);
        }
        this.mVersion.setText(getVersionText(GeneralDzSmartData.version));
        if (GeneralDzSmartData.mode == 1) {
            this.bottomRelative.setVisibility(8);
        } else {
            this.bottomRelative.setVisibility(0);
        }
        this.isClick = true;
    }

    private void setEnabled(boolean z) {
        this.radioButtonEconomics.setEnabled(z);
        this.radioButtonOriginal.setEnabled(z);
        this.radioButtonComfort.setEnabled(z);
        this.radioButtonSport.setEnabled(z);
        this.radioButtonTrack.setEnabled(z);
        this.seekBar.setEnabled(z);
    }

    private String getVersionText(int i) {
        if (i <= 0) {
            return "";
        }
        if (i < 10) {
            return "v 0." + i;
        }
        return "v " + (i / 10) + "." + (i % 10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendModeValue(int i) {
        int i2 = GeneralDzSmartData.mode;
        boolean z = (i2 == 3 || i2 == 4) ? false : true;
        int i3 = (i2 == 3 || i2 == 0) ? 4 : 0;
        if (z) {
            MessageSender.sendDzMsg(i2, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data3, i + 1, i3, 4), GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        } else {
            MessageSender.sendDzMsg(i2, GeneralDzSmartData.data3, DataHandleUtils.setIntFromByteWithBit(GeneralDzSmartData.data4, i + 1, i3, 4), GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        }
    }

    private void scaleRadioButton(RadioButton radioButton) {
        for (int i = 0; i < this.radioButtons.size(); i++) {
            if (radioButton == this.radioButtons.get(i)) {
                radioButton.setScaleX(1.195f);
                radioButton.setScaleY(1.195f);
                radioButton.setChecked(true);
            } else {
                this.radioButtons.get(i).setScaleX(1.0f);
                this.radioButtons.get(i).setScaleY(1.0f);
            }
        }
    }
}
