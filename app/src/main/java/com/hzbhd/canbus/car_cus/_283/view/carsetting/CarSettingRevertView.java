package com.hzbhd.canbus.car_cus._283.view.carsetting;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.hzbhd.R;
import com.hzbhd.canbus.car._283.MsgMgr;
import com.hzbhd.canbus.car_cus._283.DialogUtils;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.view.SettingView;
import com.hzbhd.canbus.util.DataHandleUtils;

/* loaded from: classes2.dex */
public class CarSettingRevertView extends RelativeLayout implements View.OnClickListener {
    private Context mContext;
    private View mView;
    private SettingView sv_revert_all;
    private SettingView sv_revert_assistance;
    private SettingView sv_revert_combination;
    private SettingView sv_revert_door;
    private SettingView sv_revert_light;
    private SettingView sv_revert_parking;
    private SettingView sv_revert_rear;
    private SettingView sv_revert_time;

    public void refreshUi() {
    }

    public CarSettingRevertView(Context context) {
        this(context, null);
    }

    public CarSettingRevertView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CarSettingRevertView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mView = LayoutInflater.from(context).inflate(R.layout._283_car_setting_revert, (ViewGroup) this, true);
        initView();
    }

    private void initView() {
        this.sv_revert_light = (SettingView) this.mView.findViewById(R.id.sv_revert_light);
        this.sv_revert_all = (SettingView) this.mView.findViewById(R.id.sv_revert_all);
        this.sv_revert_assistance = (SettingView) this.mView.findViewById(R.id.sv_revert_assistance);
        this.sv_revert_parking = (SettingView) this.mView.findViewById(R.id.sv_revert_parking);
        this.sv_revert_rear = (SettingView) this.mView.findViewById(R.id.sv_revert_rear);
        this.sv_revert_door = (SettingView) this.mView.findViewById(R.id.sv_revert_door);
        this.sv_revert_combination = (SettingView) this.mView.findViewById(R.id.sv_revert_combination);
        this.sv_revert_time = (SettingView) this.mView.findViewById(R.id.sv_revert_time);
        this.sv_revert_light.setOnClickListener(this);
        this.sv_revert_all.setOnClickListener(this);
        this.sv_revert_assistance.setOnClickListener(this);
        this.sv_revert_parking.setOnClickListener(this);
        this.sv_revert_rear.setOnClickListener(this);
        this.sv_revert_door.setOnClickListener(this);
        this.sv_revert_combination.setOnClickListener(this);
        this.sv_revert_time.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        final int i = 7;
        int i2 = R.string._283_revert_content_all;
        switch (id) {
            case R.id.sv_revert_assistance /* 2131363460 */:
                i2 = R.string._283_setting_title_4;
                i = 6;
                break;
            case R.id.sv_revert_combination /* 2131363461 */:
                i2 = R.string._283_setting_title_8;
                i = 1;
                break;
            case R.id.sv_revert_door /* 2131363462 */:
                i2 = R.string._283_setting_title_7;
                i = 2;
                break;
            case R.id.sv_revert_light /* 2131363463 */:
                i2 = R.string._283_setting_title_3;
                i = 4;
                break;
            case R.id.sv_revert_parking /* 2131363464 */:
                i2 = R.string._283_setting_title_5;
                i = 5;
                break;
            case R.id.sv_revert_rear /* 2131363465 */:
                i2 = R.string._283_setting_title_6;
                i = 3;
                break;
            case R.id.sv_revert_time /* 2131363466 */:
                i2 = R.string._283_setting_title_13;
                i = -1;
                break;
        }
        DialogUtils.showTipsDialog(this.mContext, getContent(i2), new View.OnClickListener() { // from class: com.hzbhd.canbus.car_cus._283.view.carsetting.CarSettingRevertView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.m1114x805dfe32(i, view2);
            }
        });
    }

    /* renamed from: lambda$onClick$0$com-hzbhd-canbus-car_cus-_283-view-carsetting-CarSettingRevertView, reason: not valid java name */
    /* synthetic */ void m1114x805dfe32(int i, View view) {
        if (i == -1) {
            setTime();
        } else {
            MessageSender.sendMsg(new byte[]{22, 26, (byte) DataHandleUtils.setIntByteWithBit(0, i, true)});
        }
    }

    private void setTime() {
        MsgMgr.CHANG_TIME = true;
    }

    private String getContent(int i) {
        return this.mContext.getString(R.string._283_revert_content_1) + this.mContext.getString(i) + this.mContext.getString(R.string._283_revert_content_2);
    }
}
