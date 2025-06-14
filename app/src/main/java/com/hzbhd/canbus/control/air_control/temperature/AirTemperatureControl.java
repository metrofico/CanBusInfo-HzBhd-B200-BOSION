package com.hzbhd.canbus.control.air_control.temperature;

import android.text.TextUtils;
import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public abstract class AirTemperatureControl extends AbstractAirControl {
    private static final int FAULT_TOLERANT_COUNT = 5;
    private static final long WORK_DELAY = 0;
    private static final int WORK_PERIOD = 500;
    private int mCount;
    private String mTemperatureNow;

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public abstract void step();

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public boolean isComplete() {
        if (this.mCount >= 5) {
            return true;
        }
        if (TextUtils.equals(this.mTemperatureNow, GeneralAirData.front_left_temperature)) {
            this.mCount++;
        }
        this.mTemperatureNow = GeneralAirData.front_left_temperature;
        return false;
    }

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void most() {
        AirControlHelper.INSTANCE.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.control.air_control.temperature.AirTemperatureControl.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (AirTemperatureControl.this.isComplete()) {
                    AirTemperatureControl.this.reset();
                } else {
                    AirTemperatureControl.this.step();
                }
            }
        }, 0L, 500);
    }

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void reset() {
        AirControlHelper.INSTANCE.stopTimer();
        this.mCount = 0;
    }
}
