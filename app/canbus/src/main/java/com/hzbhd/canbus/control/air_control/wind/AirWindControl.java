package com.hzbhd.canbus.control.air_control.wind;

import com.hzbhd.canbus.control.air_control.AbstractAirControl;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public abstract class AirWindControl extends AbstractAirControl {
    private static final long WORK_DELAY = 0;
    private static final int WORK_PERIOD = 200;

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public abstract boolean isComplete();

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public abstract void step();

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void most() {
        AirControlHelper.INSTANCE.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.control.air_control.wind.AirWindControl.1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (AirWindControl.this.isComplete()) {
                    AirWindControl.this.reset();
                } else {
                    AirWindControl.this.step();
                }
            }
        }, 0L, 200);
    }

    @Override // com.hzbhd.canbus.control.air_control.AbstractAirControl, com.hzbhd.canbus.interfaces.AirControlInterface
    public void reset() {
        AirControlHelper.INSTANCE.stopTimer();
    }
}
