package com.hzbhd.ui.util;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import com.hzbhd.ui.util.FullScreenHelper;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.log4j.helpers.DateLayout;

/* compiled from: FullScreenHelper.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0016\u0018\u00002\u00020\u0001:\u0002./B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\tJ\u0006\u0010%\u001a\u00020#J\u0010\u0010&\u001a\u00020#2\u0006\u0010'\u001a\u00020\u000eH\u0016J\b\u0010(\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016J\u000e\u0010+\u001a\u00020#2\u0006\u0010$\u001a\u00020\tJ\b\u0010,\u001a\u00020#H\u0016J\b\u0010-\u001a\u00020#H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R!\u0010\u0007\u001a\u0012\u0012\u0004\u0012\u00020\t0\bj\b\u0012\u0004\u0012\u00020\t`\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0011\u0010\u001e\u001a\u00020\u001f¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00060"}, d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper;", "", "()V", "AUTO_HIDE_SYSTEMUI_TIME", "", "getAUTO_HIDE_SYSTEMUI_TIME", "()J", "activitys", "Ljava/util/ArrayList;", "Landroid/app/Activity;", "Lkotlin/collections/ArrayList;", "getActivitys", "()Ljava/util/ArrayList;", "isFullScreen", "", "()Z", "setFullScreen", "(Z)V", "lastMsg", "Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "getLastMsg", "()Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "setLastMsg", "(Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;)V", "listener", "Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "getListener", "()Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "setListener", "(Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;)V", "mainHandler", "Landroid/os/Handler;", "getMainHandler", "()Landroid/os/Handler;", "addAvtivity", "", "activity", "finish", "fullScreenChange", "fullScreen", "fullScreenChangeInit", "hideSystemUI", "refreshSystemUI", "removeAvtivity", "showOrHideSystemUI", "showSystemUI", "MAIN_MSG", "OnFullScreenChangeListener", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public class FullScreenHelper {
    private final long AUTO_HIDE_SYSTEMUI_TIME = 5000;
    private final ArrayList<Activity> activitys;
    private boolean isFullScreen;
    private MAIN_MSG lastMsg;
    private OnFullScreenChangeListener listener;
    private final Handler mainHandler;

    /* compiled from: FullScreenHelper.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper$MAIN_MSG;", "", "(Ljava/lang/String;I)V", DateLayout.NULL_DATE_FORMAT, "REFRESH_SYSTEMUI_VISIABLE", "SYSTEMUI_HIDE", "SYSTEMUI_SHOW", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum MAIN_MSG {
        NULL,
        REFRESH_SYSTEMUI_VISIABLE,
        SYSTEMUI_HIDE,
        SYSTEMUI_SHOW
    }

    /* compiled from: FullScreenHelper.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/hzbhd/ui/util/FullScreenHelper$OnFullScreenChangeListener;", "", "fullScreen", "", "notFullScreen", "java-base_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface OnFullScreenChangeListener {
        void fullScreen();

        void notFullScreen();
    }

    public FullScreenHelper() {
        final Looper mainLooper = Looper.getMainLooper();
        this.mainHandler = new Handler(mainLooper) { // from class: com.hzbhd.ui.util.FullScreenHelper$mainHandler$1

            /* compiled from: FullScreenHelper.kt */
            @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[FullScreenHelper.MAIN_MSG.values().length];
                    iArr[FullScreenHelper.MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal()] = 1;
                    iArr[FullScreenHelper.MAIN_MSG.SYSTEMUI_HIDE.ordinal()] = 2;
                    iArr[FullScreenHelper.MAIN_MSG.SYSTEMUI_SHOW.ordinal()] = 3;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                FullScreenHelper.MAIN_MSG main_msg = FullScreenHelper.MAIN_MSG.values()[msg.what];
                if (this.this$0.getLastMsg() != main_msg) {
                    if (LogUtil.log5()) {
                        LogUtil.d("handleMessage: " + this.this$0.getLastMsg() + " != " + main_msg);
                        return;
                    }
                    return;
                }
                if (LogUtil.log5()) {
                    LogUtil.d("handleMessage: " + main_msg);
                }
                int i = WhenMappings.$EnumSwitchMapping$0[main_msg.ordinal()];
                if (i == 1) {
                    if (this.this$0.getIsFullScreen()) {
                        FullScreenHelper.OnFullScreenChangeListener listener = this.this$0.getListener();
                        if (listener != null) {
                            listener.notFullScreen();
                        }
                        this.this$0.fullScreenChange(false);
                    }
                    this.this$0.setLastMsg(FullScreenHelper.MAIN_MSG.SYSTEMUI_HIDE);
                    sendEmptyMessageDelayed(FullScreenHelper.MAIN_MSG.SYSTEMUI_HIDE.ordinal(), this.this$0.getAUTO_HIDE_SYSTEMUI_TIME());
                    return;
                }
                if (i == 2) {
                    if (this.this$0.getIsFullScreen()) {
                        return;
                    }
                    FullScreenHelper.OnFullScreenChangeListener listener2 = this.this$0.getListener();
                    if (listener2 != null) {
                        listener2.fullScreen();
                    }
                    this.this$0.fullScreenChange(true);
                    return;
                }
                if (i == 3 && this.this$0.getIsFullScreen()) {
                    FullScreenHelper.OnFullScreenChangeListener listener3 = this.this$0.getListener();
                    if (listener3 != null) {
                        listener3.notFullScreen();
                    }
                    this.this$0.fullScreenChange(false);
                }
            }
        };
        this.activitys = new ArrayList<>();
        this.lastMsg = MAIN_MSG.NULL;
    }

    public final long getAUTO_HIDE_SYSTEMUI_TIME() {
        return this.AUTO_HIDE_SYSTEMUI_TIME;
    }

    public final Handler getMainHandler() {
        return this.mainHandler;
    }

    public final ArrayList<Activity> getActivitys() {
        return this.activitys;
    }

    public final void addAvtivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitys.contains(activity)) {
            return;
        }
        this.activitys.add(activity);
    }

    public final void removeAvtivity(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (this.activitys.contains(activity)) {
            this.activitys.remove(activity);
        }
    }

    public final void finish() {
        Iterator<Activity> it = this.activitys.iterator();
        while (it.hasNext()) {
            it.next().finish();
        }
    }

    public final OnFullScreenChangeListener getListener() {
        return this.listener;
    }

    public final void setListener(OnFullScreenChangeListener onFullScreenChangeListener) {
        this.listener = onFullScreenChangeListener;
    }

    /* renamed from: isFullScreen, reason: from getter */
    public final boolean getIsFullScreen() {
        return this.isFullScreen;
    }

    public final void setFullScreen(boolean z) {
        this.isFullScreen = z;
    }

    public final MAIN_MSG getLastMsg() {
        return this.lastMsg;
    }

    public final void setLastMsg(MAIN_MSG main_msg) {
        Intrinsics.checkNotNullParameter(main_msg, "<set-?>");
        this.lastMsg = main_msg;
    }

    public void fullScreenChange(boolean fullScreen) {
        this.isFullScreen = fullScreen;
        if (LogUtil.log5()) {
            LogUtil.d("fullScreenChange: " + fullScreen);
        }
        Iterator<Activity> it = this.activitys.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            if (!next.isInMultiWindowMode()) {
                if (fullScreen) {
                    WindowManager.LayoutParams attributes = next.getWindow().getAttributes();
                    attributes.flags |= 1024;
                    next.getWindow().setAttributes(attributes);
                } else {
                    WindowManager.LayoutParams attributes2 = next.getWindow().getAttributes();
                    attributes2.flags &= -1025;
                    next.getWindow().setAttributes(attributes2);
                }
            }
        }
    }

    public void fullScreenChangeInit() {
        if (LogUtil.log5()) {
            LogUtil.d("fullScreenChangeInit");
        }
        Iterator<Activity> it = this.activitys.iterator();
        while (it.hasNext()) {
            Activity next = it.next();
            next.getWindow().addFlags(512);
            next.getWindow().addFlags(256);
        }
    }

    public void refreshSystemUI() {
        this.lastMsg = MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE;
        if (LogUtil.log5()) {
            LogUtil.d("refreshSystemUI: ");
        }
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal(), 10L);
    }

    public void showOrHideSystemUI() {
        if (this.isFullScreen) {
            refreshSystemUI();
        } else {
            hideSystemUI();
        }
    }

    public void showSystemUI() {
        this.lastMsg = MAIN_MSG.SYSTEMUI_SHOW;
        if (LogUtil.log5()) {
            LogUtil.d("showSystemUI: ");
        }
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_SHOW.ordinal(), 10L);
    }

    public void hideSystemUI() {
        this.lastMsg = MAIN_MSG.SYSTEMUI_HIDE;
        if (LogUtil.log5()) {
            LogUtil.d("hideSystemUI: ");
        }
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_HIDE.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.SYSTEMUI_SHOW.ordinal());
        this.mainHandler.removeMessages(MAIN_MSG.REFRESH_SYSTEMUI_VISIABLE.ordinal());
        this.mainHandler.sendEmptyMessageDelayed(MAIN_MSG.SYSTEMUI_HIDE.ordinal(), 10L);
    }
}
