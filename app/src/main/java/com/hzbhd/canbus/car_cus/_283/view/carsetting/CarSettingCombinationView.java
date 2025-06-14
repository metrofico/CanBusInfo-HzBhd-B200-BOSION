package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingSelectView;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class CarSettingCombinationView extends RelativeLayout {
    private Context mContext;
    private View mView;
    private SettingSelectView ssv_average_consumption;
    private SettingSelectView ssv_average_speed;
    private SettingSelectView ssv_convenience_consumer;
    private SettingSelectView ssv_current_consumption;
    private SettingSelectView ssv_digital_speed_display;
    private SettingSelectView ssv_distance_travelled;
    private SettingSelectView ssv_eco;
    private SettingSelectView ssv_oil_temperature;
    private SettingSelectView ssv_speed_warning;
    private SettingSelectView ssv_travelling_time;
    private SettingView sv_reset_long_term;
    private SettingView sv_reset_since_start;

    public CarSettingCombinationView(Context context) {
        this(context, null);
    }

    public CarSettingCombinationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingCombinationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_combination, (ViewGroup) this, true);
        initView();
        initClick();
        new Handler().postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m1103x8b1207a2();
            }
        }, 1000L);
    }

    private void initView() {
        this.ssv_current_consumption = (SettingSelectView) this.mView.findViewById(R.id.ssv_current_consumption);
        this.ssv_average_consumption = (SettingSelectView) this.mView.findViewById(R.id.ssv_average_consumption);
        this.ssv_convenience_consumer = (SettingSelectView) this.mView.findViewById(R.id.ssv_convenience_consumer);
        this.ssv_eco = (SettingSelectView) this.mView.findViewById(R.id.ssv_eco);
        this.ssv_travelling_time = (SettingSelectView) this.mView.findViewById(R.id.ssv_travelling_time);
        this.ssv_distance_travelled = (SettingSelectView) this.mView.findViewById(R.id.ssv_distance_travelled);
        this.ssv_average_speed = (SettingSelectView) this.mView.findViewById(R.id.ssv_average_speed);
        this.ssv_digital_speed_display = (SettingSelectView) this.mView.findViewById(R.id.ssv_digital_speed_display);
        this.ssv_speed_warning = (SettingSelectView) this.mView.findViewById(R.id.ssv_speed_warning);
        this.ssv_oil_temperature = (SettingSelectView) this.mView.findViewById(R.id.ssv_oil_temperature);
        this.sv_reset_since_start = (SettingView) this.mView.findViewById(R.id.sv_reset_since_start);
        this.sv_reset_long_term = (SettingView) this.mView.findViewById(R.id.sv_reset_long_term);
    }

    private void initClick() {
        SettingSelectView.OnItemClickListener onItemClickListener = new SettingSelectView.OnItemClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView$$ExternalSyntheticLambda2
            @Override // com.hzbhd.canbus.car_cus._283.view.SettingSelectView.OnItemClickListener
            public final void onClick(View view, boolean z) {
                CarSettingCombinationView.lambda$initClick$1(view, z);
            }
        };
        this.ssv_current_consumption.setOnItemClickListener(onItemClickListener);
        this.ssv_average_consumption.setOnItemClickListener(onItemClickListener);
        this.ssv_convenience_consumer.setOnItemClickListener(onItemClickListener);
        this.ssv_eco.setOnItemClickListener(onItemClickListener);
        this.ssv_travelling_time.setOnItemClickListener(onItemClickListener);
        this.ssv_distance_travelled.setOnItemClickListener(onItemClickListener);
        this.ssv_average_speed.setOnItemClickListener(onItemClickListener);
        this.ssv_digital_speed_display.setOnItemClickListener(onItemClickListener);
        this.ssv_speed_warning.setOnItemClickListener(onItemClickListener);
        this.ssv_oil_temperature.setOnItemClickListener(onItemClickListener);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m1102x444db11d(view);
            }
        };
        this.sv_reset_since_start.setOnClickListener(onClickListener);
        this.sv_reset_long_term.setOnClickListener(onClickListener);
    }

    static /* synthetic */ void lambda$initClick$1(View view, boolean z) {
        byte b = 1;
        switch (view.getId()) {
            case R.id.ssv_average_consumption /* 2131363387 */:
                b = 2;
                break;
            case R.id.ssv_average_speed /* 2131363388 */:
                b = 7;
                break;
            case R.id.ssv_convenience_consumer /* 2131363391 */:
                b = 3;
                break;
            case R.id.ssv_digital_speed_display /* 2131363395 */:
                b = 8;
                break;
            case R.id.ssv_distance_travelled /* 2131363396 */:
                b = 6;
                break;
            case R.id.ssv_eco /* 2131363398 */:
                b = 4;
                break;
            case R.id.ssv_oil_temperature /* 2131363409 */:
                b = 10;
                break;
            case R.id.ssv_speed_warning /* 2131363416 */:
                b = 9;
                break;
            case R.id.ssv_travelling_time /* 2131363418 */:
                b = 5;
                break;
        }
        MessageSender.sendMsg((byte) 123, b, z);
    }

    /* renamed from: lambda$initClick$3$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingCombinationView, reason: not valid java name */
    /* synthetic */ void m1102x444db11d(View view) {
        int id = view.getId();
        int i = R.string._283_combination_1;
        final byte b = 11;
        switch (id) {
            case R.id.sv_reset_long_term /* 2131363456 */:
                b = NfDef.AVRCP_EVENT_ID_UIDS_CHANGED;
                i = R.string._283_combination_2;
                break;
        }
        Context context = this.mContext;
        DialogUtils.showTipsDialog(context, context.getString(i), new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingCombinationView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MessageSender.sendMsg(new byte[]{22, 123, b, 0});
            }
        });
    }

    /* renamed from: refreshUi, reason: merged with bridge method [inline-methods] */
    public void m1103x8b1207a2() {
        this.ssv_current_consumption.setSelect(GeneralDzData.combination_current_consumption);
        this.ssv_average_consumption.setSelect(GeneralDzData.combination_average_consumption);
        this.ssv_convenience_consumer.setSelect(GeneralDzData.combination_convenience_consumer);
        this.ssv_eco.setSelect(GeneralDzData.combination_eco);
        this.ssv_travelling_time.setSelect(GeneralDzData.combination_travelling_time);
        this.ssv_distance_travelled.setSelect(GeneralDzData.combination_distance_travelled);
        this.ssv_average_speed.setSelect(GeneralDzData.combination_average_speed);
        this.ssv_digital_speed_display.setSelect(GeneralDzData.combination_digital_speed_display);
        this.ssv_speed_warning.setSelect(GeneralDzData.combination_speed_warning);
        this.ssv_oil_temperature.setSelect(GeneralDzData.combination_oil_temperature);
    }
}
