package com.hzbhd.util;

import android.os.Handler;
import android.os.HandlerThread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HandlerThreadPoolExecutor.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0017\u001a\u00020\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\b\u0010\u0019\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001b\u0010\r\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\f\u001a\u0004\b\u000e\u0010\nR\u001b\u0010\u0010\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\f\u001a\u0004\b\u0011\u0010\nR\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "", "()V", "task1Running", "", "task2Running", "task3Running", "threadHandler1", "Landroid/os/Handler;", "getThreadHandler1", "()Landroid/os/Handler;", "threadHandler1$delegate", "Lkotlin/Lazy;", "threadHandler2", "getThreadHandler2", "threadHandler2$delegate", "threadHandler3", "getThreadHandler3", "threadHandler3$delegate", "workQueue", "Ljava/util/concurrent/BlockingQueue;", "Lkotlin/Function0;", "", "execute", "action", "runHandler", "thread_util_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class HandlerThreadPoolExecutor {
    private boolean task1Running;
    private boolean task2Running;
    private boolean task3Running;
    private final BlockingQueue<Function0<Unit>> workQueue = new LinkedBlockingQueue(100);

    /* renamed from: threadHandler1$delegate, reason: from kotlin metadata */
    private final Lazy threadHandler1 = LazyKt.lazy(new Function0<Handler>() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$threadHandler1$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            HandlerThread handlerThread = new HandlerThread("back.1");
            handlerThread.setPriority(8);
            handlerThread.start();
            return new Handler(handlerThread.getLooper());
        }
    });

    /* renamed from: threadHandler2$delegate, reason: from kotlin metadata */
    private final Lazy threadHandler2 = LazyKt.lazy(new Function0<Handler>() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$threadHandler2$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            HandlerThread handlerThread = new HandlerThread("back.2");
            handlerThread.setPriority(8);
            handlerThread.start();
            return new Handler(handlerThread.getLooper());
        }
    });

    /* renamed from: threadHandler3$delegate, reason: from kotlin metadata */
    private final Lazy threadHandler3 = LazyKt.lazy(new Function0<Handler>() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$threadHandler3$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Handler invoke() {
            HandlerThread handlerThread = new HandlerThread("back.3");
            handlerThread.setPriority(8);
            handlerThread.start();
            return new Handler(handlerThread.getLooper());
        }
    });

    private final Handler getThreadHandler1() {
        return (Handler) this.threadHandler1.getValue();
    }

    private final Handler getThreadHandler2() {
        return (Handler) this.threadHandler2.getValue();
    }

    private final Handler getThreadHandler3() {
        return (Handler) this.threadHandler3.getValue();
    }

    public final void execute(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        this.workQueue.put(action);
        runHandler();
    }

    private final void runHandler() {
        if (!this.task1Running) {
            this.task1Running = true;
            getThreadHandler1().post(new Runnable() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    HandlerThreadPoolExecutor.m1216runHandler$lambda0(this.f$0);
                }
            });
        }
        if (!this.task2Running && this.workQueue.size() > 1) {
            this.task2Running = true;
            getThreadHandler2().post(new Runnable() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException {
                    HandlerThreadPoolExecutor.m1217runHandler$lambda1(this.f$0);
                }
            });
        }
        if (this.task3Running || this.workQueue.size() <= 2) {
            return;
        }
        this.task3Running = true;
        getThreadHandler3().post(new Runnable() { // from class: com.hzbhd.util.HandlerThreadPoolExecutor$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                HandlerThreadPoolExecutor.m1218runHandler$lambda2(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runHandler$lambda-0, reason: not valid java name */
    public static final void m1216runHandler$lambda0(HandlerThreadPoolExecutor this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        while (function0Poll != null) {
            function0Poll.invoke();
            function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        }
        this$0.task1Running = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runHandler$lambda-1, reason: not valid java name */
    public static final void m1217runHandler$lambda1(HandlerThreadPoolExecutor this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        while (function0Poll != null) {
            function0Poll.invoke();
            function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        }
        this$0.task2Running = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: runHandler$lambda-2, reason: not valid java name */
    public static final void m1218runHandler$lambda2(HandlerThreadPoolExecutor this$0) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function0<Unit> function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        while (function0Poll != null) {
            function0Poll.invoke();
            function0Poll = this$0.workQueue.poll(1L, TimeUnit.SECONDS);
        }
        this$0.task3Running = false;
    }
}
