package com.hzbhd.canbus.car._94;

import android.content.Context;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.TimerUtil;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActivePark.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0019\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\n \n*\u0004\u0018\u00010\t0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0011"}, d2 = {"com/hzbhd/canbus/car/_94/ActivePark$countDown$1$1", "Ljava/util/TimerTask;", "count", "", "getCount", "()I", "setCount", "(I)V", "second", "", "kotlin.jvm.PlatformType", "getSecond", "()Ljava/lang/String;", "text", "getText", "run", "", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.setAlertMessage(it);
    }
}
