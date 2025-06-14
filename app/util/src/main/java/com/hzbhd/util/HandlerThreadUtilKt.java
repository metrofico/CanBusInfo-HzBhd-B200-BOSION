package com.hzbhd.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

import com.hzbhd.util.HandlerThreadUtilKt$msgUIHandler$2;
import com.hzbhd.util.HandlerThreadUtilKt$threadHandler$2;
import com.hzbhd.util.HandlerThreadUtilKt$threadMsgHandler$2;
import com.hzbhd.util.HandlerThreadUtilKt$uiHandler$2;

import java.util.Locale;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.CharsKt;

/* compiled from: HandlerThreadUtil.kt */
@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\u001a\b\u0010\u001c\u001a\u00020\u0007H\u0002\u001a\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 \u001a\u000e\u0010!\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 \u001a\u0014\u0010\"\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010%\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010(\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a$\u0010)\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u0014\u0010*\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u0014\u0010+\u001a\u00020\u001e2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010,\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a\u001c\u0010-\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\u001a$\u0010.\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010&\u001a\u00020'2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0$\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001b\u0010\b\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\u0005\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\r\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0005\u001a\u0004\b\u000e\u0010\u000b\"\u000e\u0010\u0010\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001b\u0010\u0011\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0005\u001a\u0004\b\u0012\u0010\u000b\"\u001b\u0010\u0014\u001a\u00020\u00158FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0005\u001a\u0004\b\u0016\u0010\u0017\"\u001b\u0010\u0019\u001a\u00020\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0005\u001a\u0004\b\u001a\u0010\u000b¨\u0006/"}, d2 = {"backThread", "Landroid/os/HandlerThread;", "getBackThread", "()Landroid/os/HandlerThread;", "backThread$delegate", "Lkotlin/Lazy;", "handlerWhatIndex", "", "msgUIHandler", "Landroid/os/Handler;", "getMsgUIHandler", "()Landroid/os/Handler;", "msgUIHandler$delegate", "threadHandler", "getThreadHandler", "threadHandler$delegate", "threadIndex", "threadMsgHandler", "getThreadMsgHandler", "threadMsgHandler$delegate", "threadPoolExecutor", "Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "getThreadPoolExecutor", "()Lcom/hzbhd/util/HandlerThreadPoolExecutor;", "threadPoolExecutor$delegate", "uiHandler", "getUiHandler", "uiHandler$delegate", "getHandlerWhat", "removeBackMsg", "", "msgString", "", "removeUiMsg", "runBack", "action", "Lkotlin/Function0;", "runBackDelay", "time", "", "runBackMsg", "runBackMsgDelay", "runFast", "runUi", "runUiDelay", "runUiMsg", "runUiMsgDelay", "thread_util_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class HandlerThreadUtilKt {
    private static int handlerWhatIndex;
    private static int threadIndex;
    private static final Lazy uiHandler$delegate = LazyKt.lazy(new Function0<HandlerThreadUtilKt$uiHandler$2.AnonymousClass1>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$uiHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.hzbhd.util.HandlerThreadUtilKt$uiHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            return new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.util.HandlerThreadUtilKt$uiHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });
    private static final Lazy msgUIHandler$delegate = LazyKt.lazy(new Function0<HandlerThreadUtilKt$msgUIHandler$2.AnonymousClass1>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$msgUIHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.hzbhd.util.HandlerThreadUtilKt$msgUIHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            return new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.util.HandlerThreadUtilKt$msgUIHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });
    private static final Lazy backThread$delegate = LazyKt.lazy(new Function0<HandlerThread>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$backThread$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HandlerThread invoke() {
            HandlerThread handlerThread = new HandlerThread("back");
            handlerThread.setPriority(8);
            handlerThread.start();
            return handlerThread;
        }
    });
    private static final Lazy threadPoolExecutor$delegate = LazyKt.lazy(new Function0<HandlerThreadPoolExecutor>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$threadPoolExecutor$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final HandlerThreadPoolExecutor invoke() {
            return new HandlerThreadPoolExecutor();
        }
    });
    private static final Lazy threadHandler$delegate = LazyKt.lazy(new Function0<HandlerThreadUtilKt$threadHandler$2.AnonymousClass1>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$threadHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.hzbhd.util.HandlerThreadUtilKt$threadHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            return new Handler(HandlerThreadUtilKt.getBackThread().getLooper()) { // from class: com.hzbhd.util.HandlerThreadUtilKt$threadHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });
    private static final Lazy threadMsgHandler$delegate = LazyKt.lazy(new Function0<HandlerThreadUtilKt$threadMsgHandler$2.AnonymousClass1>() { // from class: com.hzbhd.util.HandlerThreadUtilKt$threadMsgHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.hzbhd.util.HandlerThreadUtilKt$threadMsgHandler$2$1] */
        @Override // kotlin.jvm.functions.Function0
        public final AnonymousClass1 invoke() {
            HandlerThread handlerThread = new HandlerThread("backMsg");
            handlerThread.start();
            return new Handler(handlerThread.getLooper()) { // from class: com.hzbhd.util.HandlerThreadUtilKt$threadMsgHandler$2.1
                @Override // android.os.Handler
                public void handleMessage(Message msg) {
                    Intrinsics.checkNotNullParameter(msg, "msg");
                    try {
                        Object obj = msg.obj;
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Function0<kotlin.Unit>");
                        ((Function0) TypeIntrinsics.beforeCheckcastToFunctionOfArity(obj, 0)).invoke();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    });

    private static final int getHandlerWhat() {
        int i = handlerWhatIndex + 1;
        handlerWhatIndex = i;
        return i;
    }

    private static final Handler getUiHandler() {
        return (Handler) uiHandler$delegate.getValue();
    }

    private static final Handler getMsgUIHandler() {
        return (Handler) msgUIHandler$delegate.getValue();
    }

    public static final HandlerThread getBackThread() {
        return (HandlerThread) backThread$delegate.getValue();
    }

    public static final HandlerThreadPoolExecutor getThreadPoolExecutor() {
        return (HandlerThreadPoolExecutor) threadPoolExecutor$delegate.getValue();
    }

    private static final Handler getThreadHandler() {
        return (Handler) threadHandler$delegate.getValue();
    }

    private static final Handler getThreadMsgHandler() {
        return (Handler) threadMsgHandler$delegate.getValue();
    }

    public static final void runUi(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getUiHandler().sendMessage(getUiHandler().obtainMessage(getHandlerWhat(), action));
    }

    public static final void runUiDelay(long j, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getUiHandler().sendMessageDelayed(getUiHandler().obtainMessage(getHandlerWhat(), action), j);
    }

    public static final void runBack(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getThreadHandler().sendMessage(getThreadHandler().obtainMessage(getHandlerWhat(), action));
    }

    public static final void runBackDelay(long j, Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getThreadHandler().sendMessageDelayed(getThreadHandler().obtainMessage(getHandlerWhat(), action), j);
    }

    public static final void runFast(Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        getThreadPoolExecutor().execute(action);
    }

    public static final void runUiMsg(String msgString, Function0<Unit> action) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        Intrinsics.checkNotNullParameter(action, "action");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
        getMsgUIHandler().sendMessage(getMsgUIHandler().obtainMessage(Integer.parseInt(msgString, CharsKt.checkRadix(36)), action));
    }

    public static final void runUiMsgDelay(String msgString, long j, Function0<Unit> action) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        Intrinsics.checkNotNullParameter(action, "action");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
        getMsgUIHandler().sendMessageDelayed(getMsgUIHandler().obtainMessage(Integer.parseInt(msgString, CharsKt.checkRadix(36)), action), j);
    }

    public static final void removeUiMsg(String msgString) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler msgUIHandler = getMsgUIHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        msgUIHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
    }

    public static final void runBackMsg(String msgString, Function0<Unit> action) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        Intrinsics.checkNotNullParameter(action, "action");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
        getThreadMsgHandler().sendMessage(getThreadMsgHandler().obtainMessage(Integer.parseInt(msgString, CharsKt.checkRadix(36)), action));
    }

    public static final void removeBackMsg(String msgString) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
    }

    public static final void runBackMsgDelay(String msgString, long j, Function0<Unit> action) throws Exception {
        Intrinsics.checkNotNullParameter(msgString, "msgString");
        Intrinsics.checkNotNullParameter(action, "action");
        if (msgString.length() > 5) {
            throw new Exception("msg length > 5 error");
        }
        Handler threadMsgHandler = getThreadMsgHandler();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
        String lowerCase = msgString.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        threadMsgHandler.removeMessages(Integer.parseInt(lowerCase, CharsKt.checkRadix(36)));
        getThreadMsgHandler().sendMessageDelayed(getThreadMsgHandler().obtainMessage(Integer.parseInt(msgString, CharsKt.checkRadix(36)), action), j);
    }
}
