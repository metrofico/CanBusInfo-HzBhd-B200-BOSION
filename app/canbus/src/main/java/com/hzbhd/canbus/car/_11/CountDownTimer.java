package com.hzbhd.canbus.car._11;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes.dex */
public class CountDownTimer {
    private static CountDownTimer myCountDownTimer;
    public final int ACTION_TAG = 255;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._11.CountDownTimer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 255) {
                return;
            }
            CountDownTimer.this.thisActionDo.toDo(null);
        }
    };
    private ActionCallback thisActionDo;

    private CountDownTimer() {
    }

    public static CountDownTimer getInstance() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new CountDownTimer();
        }
        return myCountDownTimer;
    }

    public void reset(int i, ActionCallback actionCallback) {
        this.thisActionDo = actionCallback;
        this.mHandler.removeMessages(255);
        this.mHandler.obtainMessage().what = 255;
        this.mHandler.sendEmptyMessageDelayed(255, i);
    }
}
