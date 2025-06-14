package com.hzbhd.canbus.car_cus._291.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.hzbhd.R;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;

/* loaded from: classes2.dex */
public class DataAlarmView extends RelativeLayout {
    private int[] isOpen;
    private View mView;
    private TextView tv_alarm_belt;
    private TextView tv_alarm_clean;
    private TextView tv_alarm_oil;
    private TextView tv_alarm_v;

    public DataAlarmView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.isOpen = new int[]{R.string._291_close, R.string._291_open};
        this.mView = LayoutInflater.from(context).inflate(R.layout._291_data_alarm_view, this);
        initView();
    }

    private void initView() {
        this.tv_alarm_belt = (TextView) this.mView.findViewById(R.id.tv_alarm_belt);
        this.tv_alarm_clean = (TextView) this.mView.findViewById(R.id.tv_alarm_clean);
        this.tv_alarm_oil = (TextView) this.mView.findViewById(R.id.tv_alarm_oil);
        this.tv_alarm_v = (TextView) this.mView.findViewById(R.id.tv_alarm_v);
    }

    public void refreshUi() {
        this.tv_alarm_oil.setText(getStr(GeneralDoorData.isFuelWarning));
        this.tv_alarm_v.setText(getStr(GeneralDoorData.isBatteryWarning));
        this.tv_alarm_belt.setText(getStr(GeneralDoorData.isSeatBeltTie));
        this.tv_alarm_clean.setText(getStr(GeneralDoorData.isWashingFluidWarning));
    }

    private int getStr(boolean z) {
        if (z) {
            return this.isOpen[1];
        }
        return this.isOpen[0];
    }
}
