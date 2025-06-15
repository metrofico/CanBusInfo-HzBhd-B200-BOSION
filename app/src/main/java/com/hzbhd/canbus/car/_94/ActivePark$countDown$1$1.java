package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class ActivePark$countDown$1$1 extends TimerTask {
    final /* synthetic */ TimerUtil $this_run;
    private int count = 10;
    private final String second;
    private final String text;
    final /* synthetic */ ActivePark this$0;

    ActivePark$countDown$1$1(Context context, int i, TimerUtil timerUtil, ActivePark activePark) {
        this.$this_run = timerUtil;
        this.this$0 = activePark;
        this.text = context.getString(i);
        this.second = CommUtil.getStrByResId(context, "_197_second");
    }

    public final String getText() {
        return this.text;
    }

    public final int getCount() {
        return this.count;
    }

    public final void setCount(int i) {
        this.count = i;
    }

    public final String getSecond() {
        return this.second;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public void run() {
        if (this.count >= 0) {
            final String str = this.text + '\n' + this.count + this.second;
            final ActivePark activePark = this.this$0;
            activePark.mHandler.post(new Runnable() { // from class: com.hzbhd.canbus.car._94.ActivePark$countDown$1$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ActivePark$countDown$1$1.m942run$lambda1$lambda0(activePark, str);
                }
            });
            this.count--;
            return;
        }
        this.$this_run.stopTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: run$lambda-1$lambda-0, reason: not valid java name */
    public static final void m942run$lambda1$lambda0(ActivePark this$0, String it) {


        this$0.setAlertMessage(it);
    }
}
