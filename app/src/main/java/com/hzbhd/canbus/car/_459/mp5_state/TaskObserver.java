package com.hzbhd.canbus.car._459.mp5_state;

import android.app.ActivityManager;
import android.os.Handler;
import android.os.SystemProperties;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.source.ActivityInfoShare;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.util.ContextUtilKt;
import com.hzbhd.util.LogUtil;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TaskObserver.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0002&'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0006\u0010 \u001a\u00020\u001eJ\u0006\u0010!\u001a\u00020\u001eJ\b\u0010\"\u001a\u00020\u001eH\u0002J\u0012\u0010#\u001a\u00020\u001e2\b\u0010$\u001a\u0004\u0018\u00010%H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R#\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\b8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\u000e\u001a\u00060\u000fR\u00020\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\r\u001a\u0004\b\u001a\u0010\u001b¨\u0006("}, d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver;", "", "()V", "MSG_REFRESH_MEMEORY", "", "getMSG_REFRESH_MEMEORY", "()I", OriginalBtnAction.AM, "Landroid/app/ActivityManager;", "kotlin.jvm.PlatformType", "getAm", "()Landroid/app/ActivityManager;", "am$delegate", "Lkotlin/Lazy;", "currStatus", "Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "getCurrStatus", "()Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "currStatusListener", "Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "getCurrStatusListener", "()Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "setCurrStatusListener", "(Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;)V", "handler", "Landroid/os/Handler;", "getHandler", "()Landroid/os/Handler;", "handler$delegate", "reSendMsg", "", "refreshMemory", "refreshRunning", "refreshStorage", "registerShareDataListener", "updateSourceInfo", "sourceInfo", "", "CurrStatus", "CurrStatusListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class TaskObserver {
    private CurrStatusListener currStatusListener;
    private final CurrStatus currStatus = new CurrStatus();
    private final int MSG_REFRESH_MEMEORY = 1;

    /* renamed from: am$delegate, reason: from kotlin metadata */
    private final Lazy am = LazyKt.lazy(new Function0<ActivityManager>() { // from class: com.hzbhd.canbus.car._459.mp5_state.TaskObserver$am$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityManager invoke() {
            return (ActivityManager) ContextUtilKt.getBaseContext().getSystemService(ActivityManager.class);
        }
    });

    /* renamed from: handler$delegate, reason: from kotlin metadata */
    private final Lazy handler = LazyKt.lazy(new TaskObserver$handler$2(this));

    /* compiled from: TaskObserver.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u000e"}, d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatusListener;", "", "onEasyRunningChange", "", "running", "", "onEmulatedChange", "value", "", "onMemoryChange", "onMusicRunningChange", "onNaviRunningChange", "onRadioRunningChange", "onVideoRunningChange", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public interface CurrStatusListener {
        void onEasyRunningChange(boolean running);

        void onEmulatedChange(float value);

        void onMemoryChange(float value);

        void onMusicRunningChange(boolean running);

        void onNaviRunningChange(boolean running);

        void onRadioRunningChange(boolean running);

        void onVideoRunningChange(boolean running);
    }

    /* compiled from: TaskObserver.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0014\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\bR\u001a\u0010\u001b\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\b¨\u0006\u001e"}, d2 = {"Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver$CurrStatus;", "", "(Lcom/hzbhd/canbus/car/_459/mp5_state/TaskObserver;)V", "easyRunning", "", "getEasyRunning", "()Z", "setEasyRunning", "(Z)V", "emulated", "", "getEmulated", "()F", "setEmulated", "(F)V", "memory", "getMemory", "setMemory", "musicRunning", "getMusicRunning", "setMusicRunning", "naviRunning", "getNaviRunning", "setNaviRunning", "radioRunning", "getRadioRunning", "setRadioRunning", "videoRunning", "getVideoRunning", "setVideoRunning", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class CurrStatus {
        private boolean easyRunning;
        private float emulated;
        private float memory;
        private boolean musicRunning;
        private boolean naviRunning;
        private boolean radioRunning;
        private boolean videoRunning;

        public CurrStatus() {
        }

        public final boolean getNaviRunning() {
            return this.naviRunning;
        }

        public final void setNaviRunning(boolean z) {
            this.naviRunning = z;
        }

        public final boolean getEasyRunning() {
            return this.easyRunning;
        }

        public final void setEasyRunning(boolean z) {
            this.easyRunning = z;
        }

        public final boolean getVideoRunning() {
            return this.videoRunning;
        }

        public final void setVideoRunning(boolean z) {
            this.videoRunning = z;
        }

        public final boolean getMusicRunning() {
            return this.musicRunning;
        }

        public final void setMusicRunning(boolean z) {
            this.musicRunning = z;
        }

        public final boolean getRadioRunning() {
            return this.radioRunning;
        }

        public final void setRadioRunning(boolean z) {
            this.radioRunning = z;
        }

        public final float getMemory() {
            return this.memory;
        }

        public final void setMemory(float f) {
            this.memory = f;
        }

        public final float getEmulated() {
            return this.emulated;
        }

        public final void setEmulated(float f) {
            this.emulated = f;
        }
    }

    public TaskObserver() {
        reSendMsg();
        registerShareDataListener();
    }

    public final CurrStatus getCurrStatus() {
        return this.currStatus;
    }

    public final CurrStatusListener getCurrStatusListener() {
        return this.currStatusListener;
    }

    public final void setCurrStatusListener(CurrStatusListener currStatusListener) {
        this.currStatusListener = currStatusListener;
    }

    public final int getMSG_REFRESH_MEMEORY() {
        return this.MSG_REFRESH_MEMEORY;
    }

    public final ActivityManager getAm() {
        return (ActivityManager) this.am.getValue();
    }

    private final Handler getHandler() {
        return (Handler) this.handler.getValue();
    }

    public final void refreshStorage() {
        File file = new File("/storage/emulated/0");
        if (LogUtil.log5()) {
            LogUtil.d("refreshStorage: " + file.getFreeSpace() + "  " + file.getTotalSpace());
        }
        float freeSpace = (float) (file.getFreeSpace() / file.getTotalSpace());
        if (Math.abs(freeSpace - this.currStatus.getEmulated()) > 0.01f) {
            this.currStatus.setEmulated(freeSpace);
            CurrStatusListener currStatusListener = this.currStatusListener;
            if (currStatusListener != null) {
                currStatusListener.onEmulatedChange(freeSpace);
            }
        }
    }

    public final void refreshMemory() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        getAm().getMemoryInfo(memoryInfo);
        if (LogUtil.log5()) {
            LogUtil.d(memoryInfo.availMem + "  " + memoryInfo.totalMem);
        }
        float f = (float) (memoryInfo.availMem / memoryInfo.totalMem);
        if (Math.abs(f - this.currStatus.getMemory()) > 0.01f) {
            this.currStatus.setMemory(f);
            CurrStatusListener currStatusListener = this.currStatusListener;
            if (currStatusListener != null) {
                currStatusListener.onMemoryChange(f);
            }
        }
    }

    public final void refreshRunning() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) ContextUtilKt.getBaseContext().getSystemService(ActivityManager.class)).getRunningAppProcesses();
        String naviPackage = SystemProperties.get("persist.sys.navi.name");
        Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            String str = it.next().processName;
            Intrinsics.checkNotNullExpressionValue(str, "info.processName");
            String str2 = (String) StringsKt.split$default((CharSequence) str, new String[]{":"}, false, 0, 6, (Object) null).get(0);
            Intrinsics.checkNotNullExpressionValue(naviPackage, "naviPackage");
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) naviPackage, false, 2, (Object) null)) {
                z = true;
            }
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "net.easyconn", false, 2, (Object) null)) {
                z2 = true;
            }
        }
        if (this.currStatus.getNaviRunning() != z) {
            this.currStatus.setNaviRunning(z);
            CurrStatusListener currStatusListener = this.currStatusListener;
            if (currStatusListener != null) {
                currStatusListener.onNaviRunningChange(z);
            }
        }
        if (this.currStatus.getEasyRunning() != z2) {
            this.currStatus.setEasyRunning(z2);
            CurrStatusListener currStatusListener2 = this.currStatusListener;
            if (currStatusListener2 != null) {
                currStatusListener2.onEasyRunningChange(z2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reSendMsg() {
        getHandler().removeMessages(this.MSG_REFRESH_MEMEORY);
        getHandler().sendEmptyMessageDelayed(this.MSG_REFRESH_MEMEORY, 1000L);
    }

    private final void registerShareDataListener() {
        updateSourceInfo(ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CURRENT_SOURCE_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.car._459.mp5_state.TaskObserver$$ExternalSyntheticLambda0
            @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
            public final void onString(String str) {
                TaskObserver.m878registerShareDataListener$lambda0(this.f$0, str);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: registerShareDataListener$lambda-0, reason: not valid java name */
    public static final void m878registerShareDataListener$lambda0(TaskObserver this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LogUtil.log5()) {
            LogUtil.d("sourceinfo:" + str);
        }
        this$0.updateSourceInfo(str);
    }

    private final void updateSourceInfo(String sourceInfo) {
        if (sourceInfo != null) {
            SourceConstantsDef.SOURCE_ID type = SourceConstantsDef.SOURCE_ID.getType(((ActivityInfoShare) bhdGsonUtils.fromJson(sourceInfo, ActivityInfoShare.class)).getId());
            if (LogUtil.log5()) {
                LogUtil.d("onInit: CURRENT_SOURCE_INFO = " + type);
            }
            if (this.currStatus.getMusicRunning() != (type == SourceConstantsDef.SOURCE_ID.MUSIC)) {
                this.currStatus.setMusicRunning(type == SourceConstantsDef.SOURCE_ID.MUSIC);
                CurrStatusListener currStatusListener = this.currStatusListener;
                if (currStatusListener != null) {
                    currStatusListener.onMusicRunningChange(this.currStatus.getMusicRunning());
                }
            }
            if (this.currStatus.getVideoRunning() != (type == SourceConstantsDef.SOURCE_ID.VIDEO)) {
                this.currStatus.setVideoRunning(type == SourceConstantsDef.SOURCE_ID.VIDEO);
                CurrStatusListener currStatusListener2 = this.currStatusListener;
                if (currStatusListener2 != null) {
                    currStatusListener2.onVideoRunningChange(this.currStatus.getVideoRunning());
                }
            }
            if (this.currStatus.getRadioRunning() != (type == SourceConstantsDef.SOURCE_ID.FM)) {
                this.currStatus.setRadioRunning(type == SourceConstantsDef.SOURCE_ID.FM);
                CurrStatusListener currStatusListener3 = this.currStatusListener;
                if (currStatusListener3 != null) {
                    currStatusListener3.onRadioRunningChange(this.currStatus.getRadioRunning());
                }
            }
        }
    }
}
