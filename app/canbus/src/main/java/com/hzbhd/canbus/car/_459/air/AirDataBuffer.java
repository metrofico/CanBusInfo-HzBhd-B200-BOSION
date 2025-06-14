package com.hzbhd.canbus.car._459.air;

import android.os.CountDownTimer;
import android.util.Log;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.proxy.share.ShareDataManager;

/* loaded from: classes2.dex */
public class AirDataBuffer {
    private boolean ac;
    private boolean acMax;
    private boolean auto;
    private boolean bufferTag;
    private boolean cycle;
    private boolean frontDefog;
    private boolean ptc;
    private double temp;
    private int tempOut;
    private CountDownTimer timer;
    private int windLevel;
    private String windMode;

    private static class Holder {
        private static final AirDataBuffer INSTANCE = new AirDataBuffer();

        private Holder() {
        }
    }

    private AirDataBuffer() {
        this.bufferTag = false;
        this.windLevel = 0;
        this.windMode = "FACE";
        this.temp = 18.0d;
        this.cycle = true;
        this.acMax = false;
        this.ptc = false;
        this.ac = false;
        this.frontDefog = false;
        this.auto = false;
        this.tempOut = 30;
    }

    public static AirDataBuffer getInstance() {
        return Holder.INSTANCE;
    }

    public void resetTimer() {
        this.bufferTag = true;
        CountDownTimer countDownTimer = this.timer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
            this.timer = null;
        }
        CountDownTimer countDownTimer2 = new CountDownTimer(3000L, 1000L) { // from class: com.hzbhd.canbus.car._459.air.AirDataBuffer.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                Log.d("AIR_TIMER", "空调数据缓冲倒计时：" + j);
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                Log.d("AIR_TIMER", "空调数据缓冲倒计结束");
                AirDataBuffer.this.bufferTag = false;
                AirDataBuffer airDataBuffer = AirDataBuffer.this;
                airDataBuffer.notifyOtherModule("WIND_LEVEL", Integer.valueOf(airDataBuffer.windLevel));
                AirDataBuffer airDataBuffer2 = AirDataBuffer.this;
                airDataBuffer2.notifyOtherModule("WIND_MODE", airDataBuffer2.windMode);
                AirDataBuffer airDataBuffer3 = AirDataBuffer.this;
                airDataBuffer3.notifyOtherModule("TEMP", Double.valueOf(airDataBuffer3.temp));
                AirDataBuffer airDataBuffer4 = AirDataBuffer.this;
                airDataBuffer4.notifyOtherModule("CYCLE", Boolean.valueOf(airDataBuffer4.cycle));
                AirDataBuffer airDataBuffer5 = AirDataBuffer.this;
                airDataBuffer5.notifyOtherModule("AC_MAX", Boolean.valueOf(airDataBuffer5.acMax));
                AirDataBuffer airDataBuffer6 = AirDataBuffer.this;
                airDataBuffer6.notifyOtherModule("PTC", Boolean.valueOf(airDataBuffer6.ptc));
                AirDataBuffer airDataBuffer7 = AirDataBuffer.this;
                airDataBuffer7.notifyOtherModule("AC", Boolean.valueOf(airDataBuffer7.ac));
                AirDataBuffer airDataBuffer8 = AirDataBuffer.this;
                airDataBuffer8.notifyOtherModule("FRONT_DEFOG", Boolean.valueOf(airDataBuffer8.frontDefog));
                AirDataBuffer airDataBuffer9 = AirDataBuffer.this;
                airDataBuffer9.notifyOtherModule("AUTO", Boolean.valueOf(airDataBuffer9.auto));
                AirDataBuffer airDataBuffer10 = AirDataBuffer.this;
                airDataBuffer10.notifyOtherModule("TEMP_OUT", Integer.valueOf(airDataBuffer10.tempOut));
            }
        };
        this.timer = countDownTimer2;
        countDownTimer2.start();
    }

    public void setData(int i, String str, double d, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, int i2) {
        if (!this.bufferTag) {
            if (this.windLevel != i && i <= 9 && i >= 0) {
                notifyOtherModule("WIND_LEVEL", Integer.valueOf(i));
            }
            if (!this.windMode.equals(str)) {
                notifyOtherModule("WIND_MODE", str);
            }
            if (this.temp != d) {
                notifyOtherModule("TEMP", Double.valueOf(d));
            }
            if (this.cycle != z) {
                notifyOtherModule("CYCLE", Boolean.valueOf(z));
            }
            if (this.acMax != z2) {
                notifyOtherModule("AC_MAX", Boolean.valueOf(z2));
            }
            if (this.ptc != z3) {
                notifyOtherModule("PTC", Boolean.valueOf(z3));
            }
            if (this.ac != z4) {
                notifyOtherModule("AC", Boolean.valueOf(z4));
            }
            if (this.frontDefog != z5) {
                notifyOtherModule("FRONT_DEFOG", Boolean.valueOf(z5));
            }
            if (this.auto != z6) {
                notifyOtherModule("AUTO", Boolean.valueOf(z6));
            }
            if (this.tempOut != i2) {
                notifyOtherModule("TEMP_OUT", Integer.valueOf(i2));
            }
        }
        if (this.windLevel != i && i <= 9 && i >= 0) {
            this.windLevel = i;
        }
        this.windMode = str;
        this.temp = d;
        this.cycle = z;
        this.acMax = z2;
        this.ptc = z3;
        this.ac = z4;
        this.frontDefog = z5;
        this.auto = z6;
        this.tempOut = i2;
    }

    public void notifyOtherModule(String str, Object obj) {
        ShareDataManager.getInstance().reportString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, "{\"TAG\":\"" + str + "\",\"VALUE\":\"" + obj + "\"}");
    }
}
