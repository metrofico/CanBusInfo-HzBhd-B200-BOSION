package com.hzbhd.canbus.vm.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import com.hzbhd.canbus.vm.Vm;
import com.hzbhd.canbus.vm.util.BhdWindowManager;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.log4j.helpers.DateLayout;

/* compiled from: BhdWindowManager.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0010\u0010\u0010\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\bJ\u0006\u0010\u0012\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/canbus/vm/util/BhdWindowManager;", "", "()V", "mIsAddReverseView", "", "mMainHandler", "Landroid/os/Handler;", "mReverseViewParams", "Landroid/view/WindowManager$LayoutParams;", "mWindowManager", "Landroid/view/WindowManager;", "addReverseView", "", "init", "context", "Landroid/content/Context;", "initReverseWindowParams", "params", "removeReverseView", "HANDLER_MAIN_MSG", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class BhdWindowManager {
    public static final BhdWindowManager INSTANCE = new BhdWindowManager();
    private static boolean mIsAddReverseView;
    private static final Handler mMainHandler;
    private static WindowManager.LayoutParams mReverseViewParams;
    private static WindowManager mWindowManager;

    /* compiled from: BhdWindowManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/hzbhd/canbus/vm/util/BhdWindowManager$HANDLER_MAIN_MSG;", "", "(Ljava/lang/String;I)V", DateLayout.NULL_DATE_FORMAT, "MSG_ADD_REVERSE_VIEW", "MSG_REMOVE_REVERSE_VIEW", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum HANDLER_MAIN_MSG {
        NULL,
        MSG_ADD_REVERSE_VIEW,
        MSG_REMOVE_REVERSE_VIEW
    }

    private BhdWindowManager() {
    }

    public final void init(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        mWindowManager = (WindowManager) context.getSystemService(WindowManager.class);
    }

    static {
        final Looper mainLooper = Looper.getMainLooper();
        mMainHandler = new Handler(mainLooper) { // from class: com.hzbhd.canbus.vm.util.BhdWindowManager$mMainHandler$1

            /* compiled from: BhdWindowManager.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[BhdWindowManager.HANDLER_MAIN_MSG.values().length];
                    iArr[BhdWindowManager.HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal()] = 1;
                    iArr[BhdWindowManager.HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                BhdWindowManager.HANDLER_MAIN_MSG handler_main_msg = BhdWindowManager.HANDLER_MAIN_MSG.values()[msg.what];
                if (LogUtil.log5()) {
                    LogUtil.d("bhd window manager :" + handler_main_msg);
                }
                int i = WhenMappings.$EnumSwitchMapping$0[handler_main_msg.ordinal()];
                if (i != 1) {
                    if (i == 2 && BhdWindowManager.mIsAddReverseView) {
                        WindowManager windowManager = BhdWindowManager.mWindowManager;
                        Intrinsics.checkNotNull(windowManager);
                        windowManager.removeView(Vm.Companion.getVm().getReverseMainView());
                        BhdWindowManager bhdWindowManager = BhdWindowManager.INSTANCE;
                        BhdWindowManager.mIsAddReverseView = false;
                        return;
                    }
                    return;
                }
                if (BhdWindowManager.mIsAddReverseView) {
                    return;
                }
                if (LogUtil.log5()) {
                    LogUtil.d("handleMessage: " + Vm.Companion.getVm().getReverseMainView() + "  ,  " + BhdWindowManager.mReverseViewParams);
                }
                WindowManager windowManager2 = BhdWindowManager.mWindowManager;
                Intrinsics.checkNotNull(windowManager2);
                windowManager2.addView(Vm.Companion.getVm().getReverseMainView(), BhdWindowManager.mReverseViewParams);
                BhdWindowManager bhdWindowManager2 = BhdWindowManager.INSTANCE;
                BhdWindowManager.mIsAddReverseView = true;
            }
        };
    }

    public final void initReverseWindowParams(WindowManager.LayoutParams params) {
        mReverseViewParams = params;
    }

    public final void addReverseView() {
        Handler handler = mMainHandler;
        handler.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
        handler.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
        handler.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
    }

    public final void removeReverseView() {
        Handler handler = mMainHandler;
        handler.removeMessages(HANDLER_MAIN_MSG.MSG_ADD_REVERSE_VIEW.ordinal());
        handler.removeMessages(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
        handler.sendEmptyMessage(HANDLER_MAIN_MSG.MSG_REMOVE_REVERSE_VIEW.ordinal());
    }
}
