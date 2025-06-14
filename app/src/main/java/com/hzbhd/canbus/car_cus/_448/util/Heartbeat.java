package com.hzbhd.canbus.car_cus._448.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class Heartbeat {
    private static Heartbeat myCountDownTimer;
    public final int ACTION_TAG = 255;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car_cus._448.util.Heartbeat.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 255) {
                return;
            }
            Heartbeat.this.thisActionDo.toDo(null);
        }
    };
    private ActionCallback thisActionDo;

    private Heartbeat() {
    }

    public static Heartbeat getInstance() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new Heartbeat();
        }
        return myCountDownTimer;
    }

    public void start(int i, ActionCallback actionCallback) {
        this.thisActionDo = actionCallback;
        reset(i);
    }

    public void reset(int i) {
        this.mHandler.removeMessages(255);
        this.mHandler.obtainMessage().what = 255;
        this.mHandler.sendEmptyMessageDelayed(255, i);
    }
}
