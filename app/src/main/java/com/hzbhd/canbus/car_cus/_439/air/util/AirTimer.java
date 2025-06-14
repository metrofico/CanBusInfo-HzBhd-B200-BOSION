package com.hzbhd.canbus.car_cus._439.air.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._439.air.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class AirTimer {
    private static AirTimer myCountDownTimer;
    public final int ACTION_TAG = 255;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._439.air.util.AirTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 255 && AirTimer.this.thisActionDo != null) {
                AirTimer.this.thisActionDo.toDo(null);
            }
        }
    };
    private ActionCallback thisActionDo;

    private AirTimer() {
    }

    public static AirTimer getInstance() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new AirTimer();
        }
        return myCountDownTimer;
    }

    public void start(int i, ActionCallback actionCallback) {
        this.thisActionDo = actionCallback;
        this.mHandler.removeMessages(255);
        this.mHandler.obtainMessage().what = 255;
        this.mHandler.sendEmptyMessageDelayed(255, i);
    }
}
