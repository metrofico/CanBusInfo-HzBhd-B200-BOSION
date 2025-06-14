package com.hzbhd.canbus.car_cus._283.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingPersonalActivity;
import com.hzbhd.canbus.util.CommUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes2.dex */
public class DrivingPatternView extends RelativeLayout {
    private boolean isClick;
    private Context mContext;
    private ImageView mPersonSetting;
    private View mView;
    private RadioButton radioButtonComfort;
    private RadioButton radioButtonCrossCountry;
    private RadioButton radioButtonCustom;
    private RadioButton radioButtonEconomics;
    private RadioButton radioButtonSnowfield;
    private RadioButton radioButtonSport;
    private RadioButton radioButtonStandard;
    private List<RadioButton> radioButtons;
    private RadioGroup radioGroup;
    private ExecutorService threadExecutor;

    public DrivingPatternView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.radioButtons = new ArrayList();
        this.threadExecutor = Executors.newSingleThreadExecutor();
        this.isClick = true;
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_driving_pattern, this);
        initView();
        initData();
    }

    private void initView() {
        this.radioGroup = (RadioGroup) this.mView.findViewById(R.id.radioGroup);
        this.radioButtonComfort = (RadioButton) this.mView.findViewById(R.id.radioButton_comfort);
        this.radioButtonStandard = (RadioButton) this.mView.findViewById(R.id.radioButton_standard);
        this.radioButtonSport = (RadioButton) this.mView.findViewById(R.id.radioButton_sport);
        this.radioButtonEconomics = (RadioButton) this.mView.findViewById(R.id.radioButton_economics);
        this.radioButtonCustom = (RadioButton) this.mView.findViewById(R.id.radioButton_custom);
        this.radioButtonCrossCountry = (RadioButton) this.mView.findViewById(R.id.radioButton_cross_country);
        this.radioButtonSnowfield = (RadioButton) this.mView.findViewById(R.id.radioButton_snowfield);
        ImageView imageView = (ImageView) this.mView.findViewById(R.id.person_setting);
        this.mPersonSetting = imageView;
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.DrivingPatternView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1094x61053478(view);
            }
        });
        this.threadExecutor.execute(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.DrivingPatternView.1
            @Override // java.lang.Runnable
            public void run() {
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonComfort);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonStandard);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonSport);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonEconomics);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonCustom);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonCrossCountry);
                DrivingPatternView.this.radioButtons.add(DrivingPatternView.this.radioButtonSnowfield);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-hzbhd-canbus-car_cus-_283-view-DrivingPatternView, reason: not valid java name */
    /* synthetic */ void m1094x61053478(View view) {
        this.mContext.startActivity(new Intent(this.mContext, (Class<?>) MainSettingPersonalActivity.class));
    }

    private void initData() {
        this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.hzbhd.canbus.car_cus._283.view.DrivingPatternView.2
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                byte b;
                switch (i) {
                    case R.id.radioButton_comfort /* 2131363029 */:
                    case R.id.radioButton_original /* 2131363033 */:
                    default:
                        b = 1;
                        break;
                    case R.id.radioButton_cross_country /* 2131363030 */:
                        b = 8;
                        break;
                    case R.id.radioButton_custom /* 2131363031 */:
                        b = 5;
                        break;
                    case R.id.radioButton_economics /* 2131363032 */:
                        b = 4;
                        break;
                    case R.id.radioButton_snowfield /* 2131363034 */:
                        b = 6;
                        break;
                    case R.id.radioButton_sport /* 2131363035 */:
                        b = 3;
                        break;
                    case R.id.radioButton_standard /* 2131363036 */:
                        b = 2;
                        break;
                }
                if (DrivingPatternView.this.isClick) {
                    byte[] bArr = {22, -117, b, 0, 0};
                    CanbusMsgSender.sendMsg(bArr);
                    CommUtil.printHexString("scyscyscy:Mode DCC 单击 发 ", bArr);
                }
            }
        });
    }

    public void refreshUi() {
        this.isClick = false;
        this.radioButtonComfort.setVisibility(GeneralDzData.drivingMode_comfort ? 0 : 8);
        this.radioButtonSport.setVisibility(GeneralDzData.drivingMode_sport ? 0 : 8);
        this.radioButtonEconomics.setVisibility(GeneralDzData.drivingMode_eco ? 0 : 8);
        this.radioButtonCustom.setVisibility(GeneralDzData.drivingMode_indivdual ? 0 : 8);
        this.radioButtonSnowfield.setVisibility(GeneralDzData.drivingMode_snowfield ? 0 : 8);
        this.radioButtonCrossCountry.setVisibility(GeneralDzData.drivingMode_cross_country ? 0 : 8);
        int i = GeneralDzData.drivingMode;
        switch (i) {
            case 1:
                this.radioGroup.check(R.id.radioButton_cross_country);
                scaleRadioButton(this.radioButtonCrossCountry);
                break;
            case 2:
                this.radioGroup.check(R.id.radioButton_snowfield);
                scaleRadioButton(this.radioButtonSnowfield);
                break;
            case 3:
                this.radioGroup.check(R.id.radioButton_custom);
                scaleRadioButton(this.radioButtonCustom);
                break;
            case 4:
                this.radioGroup.check(R.id.radioButton_economics);
                scaleRadioButton(this.radioButtonEconomics);
                break;
            case 5:
                this.radioGroup.check(R.id.radioButton_sport);
                scaleRadioButton(this.radioButtonSport);
                break;
            case 6:
                this.radioGroup.check(R.id.radioButton_standard);
                scaleRadioButton(this.radioButtonStandard);
                break;
            case 7:
                this.radioGroup.check(R.id.radioButton_comfort);
                scaleRadioButton(this.radioButtonComfort);
                break;
        }
        this.isClick = true;
        if (i == 3) {
            this.mPersonSetting.setVisibility(0);
        } else {
            this.mPersonSetting.setVisibility(8);
        }
    }

    private void scaleRadioButton(RadioButton radioButton) {
        for (int i = 0; i < this.radioButtons.size(); i++) {
            if (radioButton == this.radioButtons.get(i)) {
                radioButton.setScaleX(1.1f);
                radioButton.setScaleY(1.1f);
            } else {
                this.radioButtons.get(i).setScaleX(1.0f);
                this.radioButtons.get(i).setScaleY(1.0f);
            }
        }
    }
}
