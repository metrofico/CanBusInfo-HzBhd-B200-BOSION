package com.hzbhd.canbus.car._459.mp5_state;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;




final class TaskObserver$handler$2 extends Lambda implements Function0<Handler> {
    final /* synthetic */ TaskObserver this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    TaskObserver$handler$2(TaskObserver taskObserver) {
        super(0);
        this.this$0 = taskObserver;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    public final Handler invoke() {
        HandlerThread handlerThread = new HandlerThread("refreshMemoryInfo");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        final TaskObserver taskObserver = this.this$0;
        return new Handler(looper, new Handler.Callback() { // from class: com.hzbhd.canbus.car._459.mp5_state.TaskObserver$handler$2$$ExternalSyntheticLambda0
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return TaskObserver$handler$2.m880invoke$lambda0(taskObserver, message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: invoke$lambda-0, reason: not valid java name */
    public static final boolean m880invoke$lambda0(TaskObserver this$0, Message message) {

        if (message.what != this$0.getMSG_REFRESH_MEMEORY()) {
            return true;
        }
        this$0.refreshStorage();
        this$0.refreshMemory();
        this$0.refreshRunning();
        this$0.reSendMsg();
        return true;
    }
}
