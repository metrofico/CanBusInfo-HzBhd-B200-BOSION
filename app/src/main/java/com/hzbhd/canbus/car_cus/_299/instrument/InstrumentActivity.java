package com.hzbhd.canbus.car_cus._299.instrument;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import java.util.List;

/* loaded from: classes2.dex */
public class InstrumentActivity extends AbstractBaseActivity {
    private ImageView iv_bottom_left_open;
    private ImageView iv_bottom_open;
    private ImageView iv_bottom_right_open;
    private ImageView iv_hand;
    private TextView iv_rotate_speed;
    private ImageView iv_safety;
    private ImageView iv_top_left_open;
    private ImageView iv_top_open;
    private ImageView iv_top_right_open;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() { // from class: com.hzbhd.canbus.car_cus._299.instrument.InstrumentActivity$$ExternalSyntheticLambda0
        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f$0.m1129x9062c354(compoundButton, z);
        }
    };
    private ImageView pointerSpeed;
    private ImageView pointerSpeedRotate;
    private RadioGroup radioGroup;
    private TextView tv_hand;
    private TextView tv_out_temp;
    private TextView tv_safety;
    private TextView tv_speed;

    /* renamed from: lambda$new$0$com-hzbhd-canbus-car_cus-_299-instrument-InstrumentActivity, reason: not valid java name */
    /* synthetic */ void m1129x9062c354(CompoundButton compoundButton, boolean z) {
        Resources resources;
        int i;
        if (z) {
            resources = getResources();
            i = R.dimen._299_instrument_text_select;
        } else {
            resources = getResources();
            i = R.dimen._299_instrument_text_normal;
        }
        compoundButton.setTextSize(resources.getDimension(i));
    }

    @Override // com.hzbhd.canbus.activity.AbstractBaseActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout._299_activity_instrument);
        getWindow().setFlags(1024, 1024);
        initView();
    }

    private void initView() {
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.pointerSpeed = (ImageView) findViewById(R.id.iv_speed_pointer);
        this.pointerSpeedRotate = (ImageView) findViewById(R.id.iv_rotate_speed_pointer);
        this.iv_bottom_open = (ImageView) findViewById(R.id.iv_bottom_open);
        this.iv_top_open = (ImageView) findViewById(R.id.iv_top_open);
        this.iv_top_left_open = (ImageView) findViewById(R.id.iv_top_left_open);
        this.iv_bottom_left_open = (ImageView) findViewById(R.id.iv_bottom_left_open);
        this.iv_top_right_open = (ImageView) findViewById(R.id.iv_top_right_open);
        this.iv_bottom_right_open = (ImageView) findViewById(R.id.iv_bottom_right_open);
        this.iv_safety = (ImageView) findViewById(R.id.iv_safety);
        this.iv_hand = (ImageView) findViewById(R.id.iv_hand);
        this.tv_speed = (TextView) findViewById(R.id.tv_speed);
        this.iv_rotate_speed = (TextView) findViewById(R.id.iv_rotate_speed);
        this.tv_out_temp = (TextView) findViewById(R.id.tv_out_temp);
        this.tv_safety = (TextView) findViewById(R.id.tv_safety);
        this.tv_hand = (TextView) findViewById(R.id.tv_hand);
        ((RadioButton) findViewById(R.id.radioButton1)).setOnCheckedChangeListener(this.onCheckedChangeListener);
        ((RadioButton) findViewById(R.id.radioButton2)).setOnCheckedChangeListener(this.onCheckedChangeListener);
        ((RadioButton) findViewById(R.id.radioButton3)).setOnCheckedChangeListener(this.onCheckedChangeListener);
        ((RadioButton) findViewById(R.id.radioButton4)).setOnCheckedChangeListener(this.onCheckedChangeListener);
        ((RadioButton) findViewById(R.id.radioButton5)).setOnCheckedChangeListener(this.onCheckedChangeListener);
        this.radioGroup.setEnabled(false);
    }

    @Override // com.hzbhd.canbus.interfaces.MgrRefreshUiInterface
    public void refreshUi(Bundle bundle) {
        this.iv_bottom_open.setVisibility(GeneralDoorData.isBackOpen ? 0 : 8);
        this.iv_top_open.setVisibility(GeneralDoorData.isFrontOpen ? 0 : 8);
        this.iv_top_left_open.setVisibility(GeneralDoorData.isLeftFrontDoorOpen ? 0 : 8);
        this.iv_bottom_left_open.setVisibility(GeneralDoorData.isLeftRearDoorOpen ? 0 : 8);
        this.iv_top_right_open.setVisibility(GeneralDoorData.isRightFrontDoorOpen ? 0 : 8);
        this.iv_bottom_right_open.setVisibility(GeneralDoorData.isRightRearDoorOpen ? 0 : 8);
        this.iv_hand.setImageResource(GeneralDoorData.isHandBrakeUp ? R.drawable._c6_seven_color_brake_blue : R.drawable._299_seven_color_brake_red);
        this.iv_safety.setImageResource(GeneralDoorData.isSeatBeltTie ? R.drawable._c6_seatbelt_blue : R.drawable._299_seatbelt_red);
        this.tv_hand.setText(getString(GeneralDoorData.isHandBrakeUp ? R.string._299_instrument_hand : R.string._299_instrument_hand_2));
        this.tv_safety.setText(getString(GeneralDoorData.isSeatBeltTie ? R.string._299_instrument_safety : R.string._299_instrument_safety_2));
        List<DriverUpdateEntity> list = GeneralDriveData.dataList;
        this.tv_speed.setText(list.get(0).getValue());
        this.iv_rotate_speed.setText(list.get(1).getValue());
        this.tv_out_temp.setText(list.get(2).getValue());
        try {
            int iIntValue = Integer.valueOf(list.get(6).getValue()).intValue();
            if (iIntValue == 0) {
                this.radioGroup.clearCheck();
            } else if (iIntValue == 1) {
                this.radioGroup.check(R.id.radioButton1);
            } else if (iIntValue == 2) {
                this.radioGroup.check(R.id.radioButton2);
            } else if (iIntValue == 3) {
                this.radioGroup.check(R.id.radioButton3);
            } else if (iIntValue == 4) {
                this.radioGroup.check(R.id.radioButton4);
            } else if (iIntValue == 5) {
                this.radioGroup.check(R.id.radioButton5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int iIntValue2 = Integer.valueOf(list.get(0).getValue()).intValue();
            if (iIntValue2 >= 0 && iIntValue2 <= 330) {
                this.pointerSpeed.setRotation((int) (iIntValue2 * 0.8030303f));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            int iIntValue3 = Integer.valueOf(list.get(1).getValue()).intValue();
            if (iIntValue3 < 0 || iIntValue3 > 8000) {
                return;
            }
            this.pointerSpeedRotate.setRotation(-((int) (iIntValue3 * 0.030375f)));
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
