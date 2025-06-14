package com.hzbhd.canbus.util;

import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.hzbhd.adapter.BtAdapter;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.SystemStatusDef;
import com.hzbhd.constant.audio.AudioConstants;
import com.hzbhd.constant.media.MeidaConstants;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.ui.util.BaseUtil;
import java.util.Arrays;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.json.JSONObject;

/* compiled from: MediaShareData.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001:\t\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData;", MediaShareData.DEFAULT_STRING, "()V", "DEFAULT_INT", MediaShareData.DEFAULT_STRING, "DEFAULT_STRING", MediaShareData.DEFAULT_STRING, "TAG", "msgMgrInterface", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "registerModuleListener", MediaShareData.DEFAULT_STRING, "context", "Landroid/content/Context;", "Bluetooth", "Misc", "Music", "Radio", "Screen", "Source", "System", "Video", "Volume", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MediaShareData {
    private static final int DEFAULT_INT = -1;
    private static final String DEFAULT_STRING = "";
    private static final String TAG = "MediaShareData";
    public static final MediaShareData INSTANCE = new MediaShareData();
    private static final MsgMgrInterface msgMgrInterface = MsgMgrFactory.getCanMsgMgr(BaseUtil.INSTANCE.getContext());

    private MediaShareData() {
    }

    public final void registerModuleListener(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        System.INSTANCE.registerListener();
        Misc.INSTANCE.registerListener();
        Source.INSTANCE.registerListener(context);
        Screen.INSTANCE.registerListener();
        Radio.INSTANCE.registerListener();
        Bluetooth.INSTANCE.registerListener();
        Music.INSTANCE.registerListener();
        Video.INSTANCE.registerListener();
        Volume.INSTANCE.registerListener();
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\n\u001a\u00020\u000bR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\f"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$System;", MediaShareData.DEFAULT_STRING, "()V", "<set-?>", MediaShareData.DEFAULT_STRING, "backLight", "getBackLight", "()I", "mSystemStatusPower", "getMSystemStatusPower", "registerListener", MediaShareData.DEFAULT_STRING, "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class System {
        public static final System INSTANCE = new System();
        private static int backLight;
        private static int mSystemStatusPower;

        private System() {
        }

        public final int getMSystemStatusPower() {
            return mSystemStatusPower;
        }

        public final int getBackLight() {
            return backLight;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SYS_POWER, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$System$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.System.m1170registerListener$lambda0(i);
                }
            });
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SYS_BACKLIGHT_STATUS, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$System$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.System.backLight = i;
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1170registerListener$lambda0(int i) {
            mSystemStatusPower = i;
            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
            if (msgMgrInterface != null) {
                msgMgrInterface.sourceSwitchNoMediaInfoChange(i == SystemStatusDef.POWER_STATUS.ACC_OFF.ordinal());
            }
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Misc;", MediaShareData.DEFAULT_STRING, "()V", "<set-?>", MediaShareData.DEFAULT_STRING, "miscReverse", "getMiscReverse", "()I", "registerListener", MediaShareData.DEFAULT_STRING, "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Misc {
        public static final Misc INSTANCE = new Misc();
        private static int miscReverse;

        private Misc() {
        }

        public final int getMiscReverse() {
            return miscReverse;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MISC_REVERSE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Misc$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Misc.miscReverse = i;
                }
            });
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0007R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R6\u0010\u001a\u001a*\u0012\u0004\u0012\u00020\u0014\u0012 \u0012\u001e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c0\u001b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Source;", MediaShareData.DEFAULT_STRING, "()V", "<set-?>", MediaShareData.DEFAULT_STRING, "clsName", "getClsName", "()Ljava/lang/String;", MediaShareData.DEFAULT_STRING, "currentCamera", "getCurrentCamera", "()I", "id", "getId", "originalDeviceMap", MediaShareData.DEFAULT_STRING, "Landroid/content/ComponentName;", "pkgName", "getPkgName", "preSource", "Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;", LcdInfoShare.DspInfo.source, "getSource", "()Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;", "setSource", "(Lcom/hzbhd/commontools/SourceConstantsDef$SOURCE_ID;)V", "sourceMap", "Lkotlin/Pair;", "Lkotlin/Function0;", MediaShareData.DEFAULT_STRING, "parseSourceInfo", "it", "registerListener", "context", "Landroid/content/Context;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Source {
        private static int currentCamera;
        private static int id;
        public static final Source INSTANCE = new Source();
        private static String pkgName = MediaShareData.DEFAULT_STRING;
        private static String clsName = MediaShareData.DEFAULT_STRING;
        private static final Map<String, ComponentName> originalDeviceMap = MapsKt.mapOf(TuplesKt.to(Constant.OriginalDeviceActivity.getClassName(), Constant.OriginalDeviceActivity), TuplesKt.to(Constant.SyncActivity.getClassName(), Constant.SyncActivity), TuplesKt.to(Constant.OnStarActivity.getClassName(), Constant.OnStarActivity));
        private static SourceConstantsDef.SOURCE_ID source = SourceConstantsDef.SOURCE_ID.NULL;
        private static SourceConstantsDef.SOURCE_ID preSource = SourceConstantsDef.SOURCE_ID.NULL;
        private static final Map<SourceConstantsDef.SOURCE_ID, Pair<Function0<Unit>, Function0<Unit>>> sourceMap = MapsKt.mapOf(TuplesKt.to(SourceConstantsDef.SOURCE_ID.FM, new Pair(null, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$1
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.radioDestroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.VIDEO, new Pair(null, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$2
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.videoDestroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.MUSIC, new Pair(null, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$3
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.musicDestroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.BTAUDIO, new Pair(new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$4
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.btMusicInfoChange();
                }
            }
        }, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$5
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.btMusiceDestdroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.AUX1, new Pair(new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$6
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.auxInInfoChange();
                }
            }
        }, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$7
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.auxInDestdroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.ATV, new Pair(new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$8
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.atvInfoChange();
                }
            }
        }, new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$9
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.atvDestdroy();
                }
            }
        })), TuplesKt.to(SourceConstantsDef.SOURCE_ID.DTV, new Pair(new Function0<Unit>() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$sourceMap$10
            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.dtvInfoChange();
                }
            }
        }, null)));

        private Source() {
        }

        public final int getId() {
            return id;
        }

        public final String getPkgName() {
            return pkgName;
        }

        public final String getClsName() {
            return clsName;
        }

        public final int getCurrentCamera() {
            return currentCamera;
        }

        public final SourceConstantsDef.SOURCE_ID getSource() {
            return source;
        }

        public final void setSource(SourceConstantsDef.SOURCE_ID source_id) {
            Intrinsics.checkNotNullParameter(source_id, "<set-?>");
            source = source_id;
        }

        public final void registerListener(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            String strRegisterShareStringListener = ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_CURRENT_SOURCE_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Source.m1168registerListener$lambda0(str);
                }
            });
            if (strRegisterShareStringListener != null) {
                parseSourceInfo(strRegisterShareStringListener);
            }
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_CURRENT_CAMERA, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Source$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Source.m1169registerListener$lambda1(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1168registerListener$lambda0(String str) {
            INSTANCE.parseSourceInfo(str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-1, reason: not valid java name */
        public static final void m1169registerListener$lambda1(int i) {
            Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_CAMERA [" + i + ']');
            currentCamera = i;
            if (i == SourceConstantsDef.SOURCE_ID.FRONTCAMERA.getValue()) {
                MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
                if (msgMgrInterface != null) {
                    msgMgrInterface.cameraInfoChange();
                    return;
                }
                return;
            }
            MsgMgrInterface msgMgrInterface2 = MediaShareData.msgMgrInterface;
            if (msgMgrInterface2 != null) {
                msgMgrInterface2.cameraDestroy();
            }
        }

        private final void parseSourceInfo(String it) {
            Function0<Unit> first;
            Pair<Function0<Unit>, Function0<Unit>> pair;
            Function0<Unit> second;
            Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_SOURCE_INFO [" + it + ']');
            JSONObject jSONObject = new JSONObject(it);
            id = jSONObject.optInt("id");
            String strOptString = jSONObject.optString("pkgName");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(\"pkgName\")");
            pkgName = strOptString;
            String strOptString2 = jSONObject.optString("clsName");
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(\"clsName\")");
            clsName = strOptString2;
            SourceConstantsDef.SOURCE_ID type = SourceConstantsDef.SOURCE_ID.getType(id);
            Intrinsics.checkNotNullExpressionValue(type, "getType(id)");
            source = type;
            Log.i(MediaShareData.TAG, "Source: SHARE_CURRENT_SOURCE_INFO source[" + source + "]  preSource[" + preSource + ']');
            if (source == SourceConstantsDef.SOURCE_ID.FM && !Radio.INSTANCE.getRadioInfoSave().equals("{}")) {
                Radio.INSTANCE.notifyRadioInfo(Radio.INSTANCE.getRadioInfoSave());
            }
            MsgMgrInterface msgMgrInterface = MediaShareData.msgMgrInterface;
            if (msgMgrInterface != null) {
                msgMgrInterface.sourceSwitchChange(source.name());
            }
            SourceConstantsDef.SOURCE_ID source_id = source;
            SourceConstantsDef.SOURCE_ID source_id2 = preSource;
            if (source_id != source_id2 && (pair = sourceMap.get(source_id2)) != null && (second = pair.getSecond()) != null) {
                second.invoke();
            }
            Pair<Function0<Unit>, Function0<Unit>> pair2 = sourceMap.get(source);
            if (pair2 != null && (first = pair2.getFirst()) != null) {
                first.invoke();
            }
            preSource = source;
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Screen;", MediaShareData.DEFAULT_STRING, "()V", "<set-?>", MediaShareData.DEFAULT_STRING, "screenBacklight", "getScreenBacklight", "()I", "registerListener", MediaShareData.DEFAULT_STRING, "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Screen {
        public static final Screen INSTANCE = new Screen();
        private static int screenBacklight;

        private Screen() {
        }

        public final int getScreenBacklight() {
            return screenBacklight;
        }

        public final void registerListener() {
            screenBacklight = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_SCREEN_BACKLIGHT, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Screen$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Screen.m1166registerListener$lambda0(i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1166registerListener$lambda0(int i) {
            Log.i(MediaShareData.TAG, "Screen: SHARE_SCREEN_BACKLIGHT [" + i + ']');
            screenBacklight = i;
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0004J\u0006\u0010'\u001a\u00020%R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u000f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR$\u0010\u0012\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R$\u0010\u0017\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u0016R$\u0010\u001a\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\f\"\u0004\b\u001c\u0010\u000eR\u001e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\fR\u000e\u0010 \u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\f\"\u0004\b#\u0010\u000e¨\u0006("}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Radio;", MediaShareData.DEFAULT_STRING, "()V", "currBand", MediaShareData.DEFAULT_STRING, "currClickPresetIndex", MediaShareData.DEFAULT_STRING, "currentFreq", "isStereo", "value", "mBand", "getMBand", "()Ljava/lang/String;", "setMBand", "(Ljava/lang/String;)V", "mFreq", "getMFreq", "setMFreq", "mIsStereo", "getMIsStereo", "()I", "setMIsStereo", "(I)V", "mPresetIndex", "getMPresetIndex", "setMPresetIndex", "mPsName", "getMPsName", "setMPsName", "<set-?>", "mUnit", "getMUnit", "psName", "radioInfoSave", "getRadioInfoSave", "setRadioInfoSave", "notifyRadioInfo", MediaShareData.DEFAULT_STRING, "it", "registerListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Radio {
        private static int currClickPresetIndex;
        private static int isStereo;
        private static int mIsStereo;
        private static int mPresetIndex;
        public static final Radio INSTANCE = new Radio();
        private static String mBand = MediaShareData.DEFAULT_STRING;
        private static String mFreq = MediaShareData.DEFAULT_STRING;
        private static String mUnit = MediaShareData.DEFAULT_STRING;
        private static String mPsName = MediaShareData.DEFAULT_STRING;
        private static String currBand = MediaShareData.DEFAULT_STRING;
        private static String currentFreq = MediaShareData.DEFAULT_STRING;
        private static String psName = MediaShareData.DEFAULT_STRING;
        private static String radioInfoSave = "{}";

        private Radio() {
        }

        public final String getMBand() {
            return mBand;
        }

        private final void setMBand(String str) {
            mBand = str;
            currBand = str;
        }

        public final String getMFreq() {
            return mFreq;
        }

        private final void setMFreq(String str) {
            mFreq = str;
            currentFreq = str;
        }

        public final String getMUnit() {
            return mUnit;
        }

        public final int getMPresetIndex() {
            return mPresetIndex;
        }

        private final void setMPresetIndex(int i) {
            mPresetIndex = i;
            currClickPresetIndex = i;
        }

        public final String getMPsName() {
            return mPsName;
        }

        private final void setMPsName(String str) {
            mPsName = str;
            psName = str;
        }

        public final int getMIsStereo() {
            return mIsStereo;
        }

        private final void setMIsStereo(int i) {
            mIsStereo = i;
            isStereo = i;
        }

        public final String getRadioInfoSave() {
            return radioInfoSave;
        }

        public final void setRadioInfoSave(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            radioInfoSave = str;
        }

        public final void registerListener() {
            String strRegisterShareStringListener = ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_RADIO_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Radio$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Radio.m1164registerListener$lambda0(str);
                }
            });
            Intrinsics.checkNotNullExpressionValue(strRegisterShareStringListener, "getInstance().registerSh…dioInfo(it)\n            }");
            radioInfoSave = strRegisterShareStringListener;
            if (strRegisterShareStringListener != null) {
                notifyRadioInfo(strRegisterShareStringListener);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1164registerListener$lambda0(String str) {
            Log.i(MediaShareData.TAG, "Radio: SHARE_RADIO_INFO [" + str + ']');
            if (str != null) {
                INSTANCE.notifyRadioInfo(str);
            }
        }

        public final void notifyRadioInfo(String it) {
            MsgMgrInterface msgMgrInterface;
            JSONObject jSONObject = new JSONObject(it);
            Radio radio = INSTANCE;
            String strOptString = jSONObject.optString("band");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(\"band\")");
            radio.setMBand(strOptString);
            String strOptString2 = jSONObject.optString(LcdInfoShare.RadioInfo.freq);
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(\"freq\")");
            radio.setMFreq(strOptString2);
            String strOptString3 = jSONObject.optString("unit");
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(\"unit\")");
            mUnit = strOptString3;
            radio.setMPresetIndex(jSONObject.optInt("presetIndex"));
            String strOptString4 = jSONObject.optString("psName");
            Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(\"psName\")");
            radio.setMPsName(strOptString4);
            radio.setMIsStereo(jSONObject.optInt("isStereo"));
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.FM || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J\u001e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ6\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ\u001e\u0010!\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\fJ&\u0010\"\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u001b\u001a\u00020\f2\u0006\u0010#\u001a\u00020\bJ\u0006\u0010$\u001a\u00020\u0016J\u0006\u0010%\u001a\u00020\u0014J\u0006\u0010&\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Bluetooth;", MediaShareData.DEFAULT_STRING, "()V", "POST_DELAY", MediaShareData.DEFAULT_STRING, "btStatusRunnable", "Ljava/lang/Runnable;", "callStatus", MediaShareData.DEFAULT_STRING, "handler", "Landroid/os/Handler;", "isAudioTransferTowardsAG", MediaShareData.DEFAULT_STRING, "isCallingFlag", "isHfpConnected", "isMicMute", "notifyBtStatusRunnable", "phoneNumber", MediaShareData.DEFAULT_STRING, "btMusicId3InfoChange", MediaShareData.DEFAULT_STRING, LcdInfoShare.MediaInfo.title, MediaShareData.DEFAULT_STRING, LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btPhoneHangUpInfoChange", "number", "isAudioAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "isHfpConn", "isCalling", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "getCallState", "notifyBtStatusChange", "registerListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Bluetooth {
        private static final long POST_DELAY = 300;
        private static int callStatus;
        private static boolean isAudioTransferTowardsAG;
        private static boolean isCallingFlag;
        private static boolean isHfpConnected;
        private static boolean isMicMute;
        public static final Bluetooth INSTANCE = new Bluetooth();
        private static byte[] phoneNumber = new byte[0];
        private static final Handler handler = new Handler(Looper.getMainLooper());
        private static final Runnable btStatusRunnable = new Runnable() { // from class: com.hzbhd.canbus.util.MediaShareData$Bluetooth$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MediaShareData.Bluetooth.m1153btStatusRunnable$lambda0();
            }
        };
        private static final Runnable notifyBtStatusRunnable = new Runnable() { // from class: com.hzbhd.canbus.util.MediaShareData$Bluetooth$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MediaShareData.Bluetooth.m1154notifyBtStatusRunnable$lambda1();
            }
        };

        private Bluetooth() {
        }

        public final String getCallState() {
            int i = callStatus;
            return i != 1 ? i != 2 ? i != 4 ? i != 5 ? "NO_CALL" : "END_CALL" : "CALLING" : "OUT_CALLING" : "IN_CALLING";
        }

        public final void registerListener() {
            BtAdapter.INSTANCE.registerListener();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: btStatusRunnable$lambda-0, reason: not valid java name */
        public static final void m1153btStatusRunnable$lambda0() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG, 0, 0, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: notifyBtStatusRunnable$lambda-1, reason: not valid java name */
        public static final void m1154notifyBtStatusRunnable$lambda1() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.notifyBtStatusChange();
        }

        public final void btPhoneStatusInfoChange(int callStatus2, byte[] number, boolean isHfpConn, boolean isCalling, boolean isMicMute2, boolean isAudioAG) {
            Intrinsics.checkNotNullParameter(number, "number");
            callStatus = callStatus;
            phoneNumber = number;
            isHfpConnected = isHfpConn;
            isCallingFlag = isCalling;
            isMicMute = isMicMute;
            isAudioTransferTowardsAG = isAudioAG;
            Handler handler2 = handler;
            Runnable runnable = btStatusRunnable;
            handler2.removeCallbacks(runnable);
            handler2.postDelayed(runnable, POST_DELAY);
        }

        public final void btPhoneOutGoingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(number, "number");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneOutGoingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneIncomingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(number, "number");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneIncomingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneTalkingInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(number, "number");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneTalkingInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btPhoneTalkingWithTimeInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG, int time) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(number, "number");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneTalkingWithTimeInfoChange(number, isMicMute2, isAudioAG, time);
        }

        public final void btPhoneHangUpInfoChange(byte[] number, boolean isMicMute2, boolean isAudioAG) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(number, "number");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTPHONE || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btPhoneHangUpInfoChange(number, isMicMute2, isAudioAG);
        }

        public final void btMusicId3InfoChange(String title, String artist, String album) {
            MsgMgrInterface msgMgrInterface;
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(artist, "artist");
            Intrinsics.checkNotNullParameter(album, "album");
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.BTAUDIO || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.btMusicId3InfoChange(title, artist, album);
        }

        public final void notifyBtStatusChange() {
            Handler handler2 = handler;
            Runnable runnable = notifyBtStatusRunnable;
            handler2.removeCallbacks(runnable);
            handler2.postDelayed(runnable, POST_DELAY);
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b-\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010F\u001a\u00020GH\u0002J\u0006\u0010H\u001a\u00020GR$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001e\u0010!\u001a\u00020\u000f2\u0006\u0010 \u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR$\u0010#\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001d\"\u0004\b%\u0010\u001fR$\u0010&\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u0007\"\u0004\b(\u0010\tR$\u0010)\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR$\u0010,\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001d\"\u0004\b.\u0010\u001fR\u000e\u0010/\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00100\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u0010\u0007\"\u0004\b2\u0010\tR$\u00103\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0007\"\u0004\b5\u0010\tR$\u00106\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u0010\u0007\"\u0004\b8\u0010\tR$\u00109\u001a\u00020\u00152\u0006\u0010\u0003\u001a\u00020\u0015@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u000e\u0010>\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010?\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010C\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010D\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Music;", MediaShareData.DEFAULT_STRING, "()V", "value", MediaShareData.DEFAULT_STRING, "aPlayIndex", "getAPlayIndex", "()I", "setAPlayIndex", "(I)V", "currentAllMinute", MediaShareData.DEFAULT_STRING, "currentAllMinuteStr", "currentHour", "currentHourStr", MediaShareData.DEFAULT_STRING, "currentMinute", "currentMinuteStr", "currentPlayingIndexHigh", "currentPlayingIndexLow", "currentPos", MediaShareData.DEFAULT_STRING, "currentSecond", "currentSecondStr", "isPlaying", MediaShareData.DEFAULT_STRING, "isReportFromPlay", "mAlbumName", "getMAlbumName", "()Ljava/lang/String;", "setMAlbumName", "(Ljava/lang/String;)V", "<set-?>", "mAlbumPath", "getMAlbumPath", "mArtist", "getMArtist", "setMArtist", "mDuration", "getMDuration", "setMDuration", "mName", "getMName", "setMName", "mPath", "getMPath", "setMPath", "mPlayIndex", "mPlayMode", "getMPlayMode", "setMPlayMode", "mPlaySize", "getMPlaySize", "setMPlaySize", "mPlayState", "getMPlayState", "setMPlayState", "mPos", "getMPos", "()J", "setMPos", "(J)V", "playModel", "playRatio", "songAlbum", "songArtist", "songTitle", "storagePath", "totalCount", "totalTime", "infoChange", MediaShareData.DEFAULT_STRING, "registerListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Music {
        private static int aPlayIndex;
        private static byte currentAllMinute;
        private static byte currentAllMinuteStr;
        private static byte currentHour;
        private static byte currentMinute;
        private static byte currentPlayingIndexHigh;
        private static int currentPlayingIndexLow;
        private static long currentPos;
        private static byte currentSecond;
        private static boolean isPlaying;
        private static boolean isReportFromPlay;
        private static int mDuration;
        private static int mPlayIndex;
        private static int mPlayMode;
        private static int mPlaySize;
        private static int mPlayState;
        private static long mPos;
        private static byte playModel;
        private static byte playRatio;
        private static byte storagePath;
        private static int totalCount;
        private static long totalTime;
        public static final Music INSTANCE = new Music();
        private static String mPath = MediaShareData.DEFAULT_STRING;
        private static String mName = MediaShareData.DEFAULT_STRING;
        private static String mArtist = MediaShareData.DEFAULT_STRING;
        private static String mAlbumName = MediaShareData.DEFAULT_STRING;
        private static String mAlbumPath = MediaShareData.DEFAULT_STRING;
        private static String currentHourStr = MediaShareData.DEFAULT_STRING;
        private static String currentMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentSecondStr = MediaShareData.DEFAULT_STRING;
        private static String songTitle = MediaShareData.DEFAULT_STRING;
        private static String songAlbum = MediaShareData.DEFAULT_STRING;
        private static String songArtist = MediaShareData.DEFAULT_STRING;

        private Music() {
        }

        public final int getMPlayState() {
            return mPlayState;
        }

        private final void setMPlayState(int i) {
            mPlayState = i;
            isPlaying = i == MeidaConstants.PLAY_STATE.STARTED.ordinal();
        }

        public final String getMPath() {
            return mPath;
        }

        private final void setMPath(String str) {
            mPath = str;
            storagePath = StringsKt.contains$default((CharSequence) str, (CharSequence) "emulated", false, 2, (Object) null) ? (byte) 9 : (byte) 0;
        }

        public final String getMName() {
            return mName;
        }

        private final void setMName(String str) {
            mName = str;
            songTitle = str;
        }

        public final String getMArtist() {
            return mArtist;
        }

        private final void setMArtist(String str) {
            mArtist = str;
            songArtist = str;
        }

        public final String getMAlbumName() {
            return mAlbumName;
        }

        private final void setMAlbumName(String str) {
            mAlbumName = str;
            songAlbum = str;
        }

        public final String getMAlbumPath() {
            return mAlbumPath;
        }

        public final int getAPlayIndex() {
            return aPlayIndex;
        }

        private final void setAPlayIndex(int i) {
            aPlayIndex = i;
            int i2 = i + 1;
            currentPlayingIndexLow = i2 & 255;
            currentPlayingIndexHigh = (byte) ((i2 >> 8) & 255);
            mPlayIndex = i2;
        }

        public final int getMPlaySize() {
            return mPlaySize;
        }

        private final void setMPlaySize(int i) {
            mPlaySize = i;
            totalCount = i;
        }

        public final int getMPlayMode() {
            return mPlayMode;
        }

        private final void setMPlayMode(int i) {
            mPlayMode = i;
            playModel = (byte) i;
        }

        public final long getMPos() {
            return mPos;
        }

        private final void setMPos(long j) {
            mPos = j;
            currentHour = (byte) (r2 / r0);
            currentMinute = (byte) (r2 % r0);
            currentSecond = (byte) (r7 % r0);
            byte b = (byte) ((j / 1000) / 60);
            currentAllMinuteStr = b;
            currentAllMinute = b;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentHour)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            currentHourStr = str;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentMinute)}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            currentMinuteStr = str2;
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str3 = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentSecond)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
            currentSecondStr = str3;
            currentPos = mPos;
        }

        public final int getMDuration() {
            return mDuration;
        }

        private final void setMDuration(int i) {
            mDuration = i;
            playRatio = i == 0 ? (byte) 0 : (byte) ((mPos * 100) / i);
            totalTime = i;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_MUSIC_PLAY_STATE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Music.m1159registerListener$lambda0(i);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_SONG_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Music.m1160registerListener$lambda2(str);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_TIME_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda2
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Music.m1161registerListener$lambda4(str);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_MUSIC_NUM_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Music$$ExternalSyntheticLambda3
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Music.m1162registerListener$lambda9(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1159registerListener$lambda0(int i) {
            Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_PLAY_STATE [" + i + ']');
            Music music = INSTANCE;
            music.setMPlayState(i);
            music.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-2, reason: not valid java name */
        public static final void m1160registerListener$lambda2(String str) {
            Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_SONG_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            Music music = INSTANCE;
            String strOptString = jSONObject.optString("path");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(\"path\")");
            music.setMPath(strOptString);
            String strOptString2 = jSONObject.optString(LcdInfoShare.MediaInfo.name);
            Intrinsics.checkNotNullExpressionValue(strOptString2, "optString(\"name\")");
            music.setMName(strOptString2);
            String strOptString3 = jSONObject.optString(LcdInfoShare.MediaInfo.artist);
            Intrinsics.checkNotNullExpressionValue(strOptString3, "optString(\"artist\")");
            music.setMArtist(strOptString3);
            String strOptString4 = jSONObject.optString("albumName");
            Intrinsics.checkNotNullExpressionValue(strOptString4, "optString(\"albumName\")");
            music.setMAlbumName(strOptString4);
            String strOptString5 = jSONObject.optString("albumPath");
            Intrinsics.checkNotNullExpressionValue(strOptString5, "optString(\"albumPath\")");
            mAlbumPath = strOptString5;
            music.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-4, reason: not valid java name */
        public static final void m1161registerListener$lambda4(String str) {
            Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_TIME_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            Music music = INSTANCE;
            music.setMPos(jSONObject.optLong("pos"));
            music.setMDuration(jSONObject.optInt("duration"));
            music.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-9, reason: not valid java name */
        public static final void m1162registerListener$lambda9(String str) {
            Log.i(MediaShareData.TAG, "Music: SHARE_MUSIC_NUM_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("playIndex", -1);
            if (!Integer.valueOf(iOptInt).equals(-1)) {
                INSTANCE.setAPlayIndex(iOptInt);
            }
            int iOptInt2 = jSONObject.optInt("playSize", -1);
            if (!Integer.valueOf(iOptInt2).equals(-1)) {
                INSTANCE.setMPlaySize(iOptInt2);
            }
            int iOptInt3 = jSONObject.optInt("playMode", -1);
            if (!Integer.valueOf(iOptInt3).equals(-1)) {
                INSTANCE.setMPlayMode(iOptInt3);
            }
            INSTANCE.infoChange();
        }

        private final void infoChange() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.MUSIC || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.musicInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, mPlayIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b \n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010>\u001a\u00020?H\u0002J\u0006\u0010@\u001a\u00020?R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u001e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0017R$\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010\"\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R$\u0010$\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u0006@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0017\"\u0004\b&\u0010'R$\u0010(\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u001f\"\u0004\b*\u0010!R$\u0010+\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001f\"\u0004\b-\u0010!R$\u0010.\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001f\"\u0004\b0\u0010!R$\u00101\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u001f\"\u0004\b3\u0010!R$\u00105\u001a\u0002042\u0006\u0010\u001c\u001a\u000204@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u000e\u0010:\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010;\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010<\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010=\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Video;", MediaShareData.DEFAULT_STRING, "()V", "currentAllMinute", MediaShareData.DEFAULT_STRING, "currentAllMinuteStr", MediaShareData.DEFAULT_STRING, "currentHour", "currentHourStr", "currentMinute", "currentMinuteStr", "currentPlayingIndexHigh", "currentPlayingIndexLow", MediaShareData.DEFAULT_STRING, "currentPos", "currentSecond", "currentSecondStr", "duration", "isPlaying", MediaShareData.DEFAULT_STRING, "<set-?>", "mAlbumName", "getMAlbumName", "()Ljava/lang/String;", "mAlbumPath", "getMAlbumPath", "mArtist", "getMArtist", "value", "mDuration", "getMDuration", "()I", "setMDuration", "(I)V", "mName", "getMName", "mPath", "getMPath", "setMPath", "(Ljava/lang/String;)V", "mPlayIndex", "getMPlayIndex", "setMPlayIndex", "mPlayMode", "getMPlayMode", "setMPlayMode", "mPlaySize", "getMPlaySize", "setMPlaySize", "mPlayState", "getMPlayState", "setMPlayState", MediaShareData.DEFAULT_STRING, "mPos", "getMPos", "()J", "setMPos", "(J)V", "playMode", "playRatio", "storagePath", "totalCount", "infoChange", MediaShareData.DEFAULT_STRING, "registerListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Video {
        private static byte currentAllMinute;
        private static byte currentHour;
        private static byte currentMinute;
        private static byte currentPlayingIndexHigh;
        private static int currentPlayingIndexLow;
        private static int currentPos;
        private static byte currentSecond;
        private static int duration;
        private static boolean isPlaying;
        private static int mDuration;
        private static int mPlayIndex;
        private static int mPlayMode;
        private static int mPlaySize;
        private static int mPlayState;
        private static long mPos;
        private static byte playMode;
        private static byte playRatio;
        private static byte storagePath;
        private static int totalCount;
        public static final Video INSTANCE = new Video();
        private static String mPath = MediaShareData.DEFAULT_STRING;
        private static String mName = MediaShareData.DEFAULT_STRING;
        private static String mArtist = MediaShareData.DEFAULT_STRING;
        private static String mAlbumName = MediaShareData.DEFAULT_STRING;
        private static String mAlbumPath = MediaShareData.DEFAULT_STRING;
        private static String currentAllMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentHourStr = MediaShareData.DEFAULT_STRING;
        private static String currentMinuteStr = MediaShareData.DEFAULT_STRING;
        private static String currentSecondStr = MediaShareData.DEFAULT_STRING;

        private Video() {
        }

        public final int getMPlayState() {
            return mPlayState;
        }

        private final void setMPlayState(int i) {
            mPlayState = i;
            isPlaying = i == MeidaConstants.PLAY_STATE.STARTED.ordinal();
        }

        public final String getMPath() {
            return mPath;
        }

        private final void setMPath(String str) {
            mPath = str;
            storagePath = StringsKt.contains$default((CharSequence) str, (CharSequence) "emulated", false, 2, (Object) null) ? (byte) 9 : (byte) 0;
        }

        public final String getMName() {
            return mName;
        }

        public final String getMArtist() {
            return mArtist;
        }

        public final String getMAlbumName() {
            return mAlbumName;
        }

        public final String getMAlbumPath() {
            return mAlbumPath;
        }

        public final long getMPos() {
            return mPos;
        }

        private final void setMPos(long j) {
            mPos = j;
            currentHour = (byte) (r4 / r2);
            currentMinute = (byte) (r4 % r2);
            currentSecond = (byte) (r0 % r2);
            currentAllMinute = (byte) ((j / 1000) / 60);
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentAllMinute)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            currentAllMinuteStr = str;
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            String str2 = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentHour)}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            currentHourStr = str2;
            StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
            String str3 = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentMinute)}, 1));
            Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
            currentMinuteStr = str3;
            StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
            String str4 = String.format("%02d", Arrays.copyOf(new Object[]{Byte.valueOf(currentSecond)}, 1));
            Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
            currentSecondStr = str4;
            currentPos = (int) j;
        }

        public final int getMDuration() {
            return mDuration;
        }

        private final void setMDuration(int i) {
            mDuration = i;
            playRatio = (byte) ((mPos * 100) / i);
            duration = i;
        }

        public final int getMPlayIndex() {
            return mPlayIndex;
        }

        private final void setMPlayIndex(int i) {
            mPlayIndex = i;
            int i2 = i + 1;
            currentPlayingIndexLow = i2 & 255;
            currentPlayingIndexHigh = (byte) ((i2 >> 8) & 255);
        }

        public final int getMPlaySize() {
            return mPlaySize;
        }

        private final void setMPlaySize(int i) {
            mPlaySize = i;
            totalCount = i;
        }

        public final int getMPlayMode() {
            return mPlayMode;
        }

        private final void setMPlayMode(int i) {
            mPlayMode = i;
            playMode = (byte) i;
        }

        public final void registerListener() {
            ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_VIDEO_PLAY_STATE, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Video.m1172registerListener$lambda0(i);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_MOVIE_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda1
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Video.m1173registerListener$lambda2(str);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_TIME_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda2
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Video.m1174registerListener$lambda4(str);
                }
            });
            ShareDataManager.getInstance().registerShareStringListener(ShareConstants.SHARE_VIDEO_NUM_INFO, new IShareStringListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Video$$ExternalSyntheticLambda3
                @Override // com.hzbhd.proxy.share.interfaces.IShareStringListener
                public final void onString(String str) {
                    MediaShareData.Video.m1175registerListener$lambda9(str);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1172registerListener$lambda0(int i) {
            Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_PLAY_STATE [" + i + ']');
            Video video = INSTANCE;
            video.setMPlayState(i);
            video.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-2, reason: not valid java name */
        public static final void m1173registerListener$lambda2(String str) {
            Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_MOVIE_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            Video video = INSTANCE;
            String strOptString = jSONObject.optString("path");
            Intrinsics.checkNotNullExpressionValue(strOptString, "optString(\"path\")");
            video.setMPath(strOptString);
            video.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-4, reason: not valid java name */
        public static final void m1174registerListener$lambda4(String str) {
            Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_TIME_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            Video video = INSTANCE;
            video.setMPos(jSONObject.optLong("pos"));
            video.setMDuration(jSONObject.optInt("duration"));
            video.infoChange();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-9, reason: not valid java name */
        public static final void m1175registerListener$lambda9(String str) {
            Log.i(MediaShareData.TAG, "Video: SHARE_VIDEO_NUM_INFO [" + str + ']');
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("playIndex", -1);
            if (!Integer.valueOf(iOptInt).equals(-1)) {
                INSTANCE.setMPlayIndex(iOptInt);
            }
            int iOptInt2 = jSONObject.optInt("playSize", -1);
            if (!Integer.valueOf(iOptInt2).equals(-1)) {
                INSTANCE.setMPlaySize(iOptInt2);
            }
            int iOptInt3 = jSONObject.optInt("playMode", -1);
            if (!Integer.valueOf(iOptInt3).equals(-1)) {
                INSTANCE.setMPlayMode(iOptInt3);
            }
            INSTANCE.infoChange();
        }

        private final void infoChange() {
            MsgMgrInterface msgMgrInterface;
            if (SourceConstantsDef.SOURCE_ID.getType(Source.INSTANCE.getId()) != SourceConstantsDef.SOURCE_ID.VIDEO || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.videoInfoChange(storagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
        }
    }

    /* compiled from: MediaShareData.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u0006\u0010\u000e\u001a\u00020\fR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0007@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, d2 = {"Lcom/hzbhd/canbus/util/MediaShareData$Volume;", MediaShareData.DEFAULT_STRING, "()V", "<set-?>", MediaShareData.DEFAULT_STRING, "isMute", "()Z", MediaShareData.DEFAULT_STRING, "volume", "getVolume", "()I", "infoChange", MediaShareData.DEFAULT_STRING, SyncAction.KEYBOARD_INFO, "registerListener", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Volume {
        public static final Volume INSTANCE = new Volume();
        private static boolean isMute;
        private static int volume;

        private Volume() {
        }

        public final int getVolume() {
            return volume;
        }

        public final boolean isMute() {
            return isMute;
        }

        public final void registerListener() {
            int iRegisterShareIntListener = ShareDataManager.getInstance().registerShareIntListener(ShareConstants.SHARE_AUDIO_VOLUME, new IShareIntListener() { // from class: com.hzbhd.canbus.util.MediaShareData$Volume$$ExternalSyntheticLambda0
                @Override // com.hzbhd.proxy.share.interfaces.IShareIntListener
                public final void onInt(int i) {
                    MediaShareData.Volume.m1176registerListener$lambda0(i);
                }
            });
            Log.i(MediaShareData.TAG, "Volume: register SHARE_AUDIO_VOLUME [" + iRegisterShareIntListener + ']');
            infoChange(iRegisterShareIntListener);
            if (volume == 127) {
                final TimerUtil timerUtil = new TimerUtil();
                timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.util.MediaShareData$Volume$registerListener$3$1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (MediaShareData.Volume.INSTANCE.getVolume() == 127) {
                            MediaShareData.Volume volume2 = MediaShareData.Volume.INSTANCE;
                            int i = ShareDataManager.getInstance().getInt(ShareConstants.SHARE_AUDIO_VOLUME);
                            Log.i("MediaShareData", "Volume: get SHARE_AUDIO_VOLUME [" + i + ']');
                            volume2.infoChange(i);
                            return;
                        }
                        timerUtil.stopTimer();
                    }
                }, 0L, 1000L);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: registerListener$lambda-0, reason: not valid java name */
        public static final void m1176registerListener$lambda0(int i) {
            Log.i(MediaShareData.TAG, "Volume: on SHARE_AUDIO_VOLUME [" + i + ']');
            INSTANCE.infoChange(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void infoChange(int info) {
            MsgMgrInterface msgMgrInterface;
            volume = AudioConstants.getVolume(info);
            isMute = AudioConstants.isMute(info);
            if (volume == 127 || (msgMgrInterface = MediaShareData.msgMgrInterface) == null) {
                return;
            }
            msgMgrInterface.currentVolumeInfoChange(volume, isMute);
        }
    }
}
