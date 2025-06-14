package com.hzbhd.canbus.car._141;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.hzbhd.canbus.interfaces.ActionDo;

/* loaded from: classes.dex */
public class RedoUtil {
    private ActionDo thisActionDo;
    private final int ACTION_TAG = 255;
    private boolean redoTag = false;
    private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._141.RedoUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 255 && RedoUtil.this.redoTag) {
                RedoUtil.this.thisActionDo.todo(null);
            }
        }
    };

    public RedoUtil(ActionDo actionDo) {
        this.thisActionDo = actionDo;
    }

    public void startTimer(long j) {
        this.redoTag = true;
        this.mHandler.obtainMessage().what = 255;
        this.mHandler.sendEmptyMessageDelayed(255, j);
    }

    public void stopTimer() {
        this.mHandler.removeMessages(255);
        this.redoTag = false;
    }
}
