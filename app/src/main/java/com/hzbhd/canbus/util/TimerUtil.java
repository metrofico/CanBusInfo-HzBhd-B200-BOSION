package com.hzbhd.canbus.util;

import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class TimerUtil {
    private Timer mTimer;
    private TimerTask mTimerTask;

    public void startTimer(TimerTask timerTask, long j, long j2) {
        Log.i("TimerUtil", "startTimer: " + this);
        if (timerTask == null) {
            return;
        }
        this.mTimerTask = timerTask;
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.schedule(this.mTimerTask, j, j2);
        }
    }

    public void startTimer(TimerTask timerTask, long j) {
        Log.i("TimerUtil", "startTimer: " + this);
        if (timerTask == null) {
            return;
        }
        this.mTimerTask = timerTask;
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.schedule(this.mTimerTask, j);
        }
    }

    public void stopTimer() {
        Log.i("TimerUtil", "stopTimer: " + this);
        TimerTask timerTask = this.mTimerTask;
        if (timerTask != null) {
            timerTask.cancel();
            this.mTimerTask = null;
        }
        Timer timer = this.mTimer;
        if (timer != null) {
            timer.cancel();
            this.mTimer = null;
        }
    }
}
