package com.hzbhd.canbus.car._3;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;

/* loaded from: classes2.dex */
public class CycleRequest {
    private static CycleRequest myCountDownTimer;
    public final int ACTION_TAG = 255;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._3.CycleRequest.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 255 && CycleRequest.this.runningState) {
                CycleRequest.this.thisActionDo.toDo(null);
            }
        }
    };
    private boolean runningState = false;
    private ActionCallback thisActionDo;

    private CycleRequest() {
    }

    public static CycleRequest getInstance() {
        if (myCountDownTimer == null) {
            myCountDownTimer = new CycleRequest();
        }
        return myCountDownTimer;
    }

    public void start(int i, ActionCallback actionCallback) {
        if (this.runningState) {
            return;
        }
        this.runningState = true;
        this.thisActionDo = actionCallback;
        reset(i);
    }

    public void reset(int i) {
        this.mHandler.removeMessages(255);
        this.mHandler.obtainMessage().what = 255;
        this.mHandler.sendEmptyMessageDelayed(255, i);
    }

    public void stop() {
        if (this.runningState) {
            this.runningState = false;
        }
    }
}
