package com.hzbhd.canbus.car._161;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.car._161.Popup;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u0005\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u0085\u00012\u00020\u0001:\u0006\u0085\u0001\u0086\u0001\u0087\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\u0010\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000eH\u0016J\b\u0010)\u001a\u00020&H\u0016J\b\u0010*\u001a\u00020&H\u0016J \u0010+\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u0015H\u0016J \u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0016J \u00104\u001a\u00020&2\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u000202H\u0016J\u0018\u00105\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000e2\u0006\u00106\u001a\u00020\u0004H\u0016J\u0018\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020\u000f2\u0006\u00109\u001a\u000202H\u0016Jp\u0010:\u001a\u00020&2\u0006\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u000f2\u0006\u0010=\u001a\u00020\u000f2\u0006\u0010>\u001a\u00020\u000f2\u0006\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u000f2\u0006\u0010B\u001a\u00020\u000f2\u0006\u0010C\u001a\u00020\u000f2\u0006\u0010D\u001a\u0002022\u0006\u0010E\u001a\u0002022\u0006\u0010F\u001a\u0002022\u0006\u0010G\u001a\u00020\u000fH\u0016Jp\u0010H\u001a\u00020&2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\u000f2\u0006\u0010L\u001a\u00020\u000f2\u0006\u0010M\u001a\u00020\u000f2\u0006\u0010N\u001a\u00020\u000f2\u0006\u0010O\u001a\u00020\u000f2\u0006\u0010P\u001a\u00020\u000f2\u0006\u0010Q\u001a\u0002022\u0006\u0010R\u001a\u0002022\u0006\u0010S\u001a\u00020\u000f2\u0006\u0010T\u001a\u00020\u00152\u0006\u0010.\u001a\u00020\u00152\u0006\u0010-\u001a\u00020\u0015H\u0016J\b\u0010U\u001a\u00020&H\u0016J\b\u0010V\u001a\u0004\u0018\u00010WJ\u0012\u0010X\u001a\u0004\u0018\u00010#2\u0006\u0010(\u001a\u00020\u000eH\u0002J\u0012\u0010Y\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010Z\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u0010[\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010\\\u001a\u00020&2\u0006\u0010(\u001a\u00020\u000eH\u0002J¾\u0001\u0010]\u001a\u00020&2\u0006\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020J2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020J2\u0006\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020J2\u0006\u0010e\u001a\u00020J2\u0006\u0010f\u001a\u00020J2\u0006\u0010g\u001a\u00020J2\b\u0010h\u001a\u0004\u0018\u00010\u00152\b\u0010i\u001a\u0004\u0018\u00010\u00152\b\u0010j\u001a\u0004\u0018\u00010\u00152\u0006\u0010k\u001a\u00020l2\u0006\u0010I\u001a\u00020J2\u0006\u0010m\u001a\u00020\u000f2\u0006\u0010R\u001a\u0002022\u0006\u0010n\u001a\u00020l2\u0006\u0010o\u001a\u00020\u00152\u0006\u0010p\u001a\u00020\u00152\u0006\u0010q\u001a\u00020\u00152\u0006\u0010r\u001a\u000202H\u0016J0\u0010s\u001a\u00020&2\u0006\u0010t\u001a\u00020\u000f2\u0006\u0010u\u001a\u00020\u00152\u0006\u0010v\u001a\u00020\u00152\u0006\u0010w\u001a\u00020\u00152\u0006\u0010x\u001a\u00020\u000fH\u0016J\u0012\u0010y\u001a\u00020&2\b\u0010z\u001a\u0004\u0018\u00010\u0015H\u0016J\u0016\u0010{\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00152\u0006\u0010|\u001a\u00020!J\u001e\u0010}\u001a\u00020&2\u0006\u0010~\u001a\u00020\u000f2\u0006\u0010\u007f\u001a\u00020\u000f2\u0006\u0010|\u001a\u00020\u000fJ\u009b\u0001\u0010\u0080\u0001\u001a\u00020&2\u0006\u0010^\u001a\u00020J2\u0006\u0010_\u001a\u00020J2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020J2\u0006\u0010c\u001a\u00020J2\u0006\u0010d\u001a\u00020J2\b\u0010e\u001a\u0004\u0018\u00010\u00152\u0006\u0010f\u001a\u00020J2\u0006\u0010g\u001a\u00020J2\b\u0010h\u001a\u0004\u0018\u00010\u00152\b\u0010i\u001a\u0004\u0018\u00010\u00152\b\u0010j\u001a\u0004\u0018\u00010\u00152\u0006\u0010k\u001a\u00020\u000f2\u0007\u0010\u0081\u0001\u001a\u00020J2\u0006\u0010R\u001a\u0002022\u0007\u0010\u0082\u0001\u001a\u00020\u000fH\u0016J\u0014\u0010\u0083\u0001\u001a\u00020&*\t\u0012\u0004\u0012\u00020\u00040\u0084\u0001H\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\f\u0012\b\u0012\u00060\u001eR\u00020\u00000\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001f\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020!0 0\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0088\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "<set-?>", "", "m0x3BDatas", "getM0x3BDatas", "()[B", "m0x3DDatas", "getM0x3DDatas", "mCanbusInfoByte", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "", "mDifferent", "getMDifferent", "()I", "mDiscCurrent", "mDiscTitle", "", "mDiscTotal", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_161/UiMgr;", "uiMgr", "Jump", "", "afterServiceNormalSetting", "context", "atvInfoChange", "auxInInfoChange", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btPhoneIncomingInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneOutGoingInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getCurrentActivity", "Landroid/app/Activity;", "getUiMgr", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sourceSwitchChange", LcdInfoShare.DspInfo.source, "updateSettingActivity", "value", "updateSettings", "leftListIndex", "rightListIndex", "videoInfoChange", "playMode", "duration", "sendCommands", "Ljava/util/ArrayList;", "Companion", "Parser", "VehicleType", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private static final String TAG = "_1161_MsgMgr";
    public static final String _161_VEHICLE_CONFIG = "_161_vehicle_config";
    private Context mContext;
    private int mDiscCurrent;
    private int mDiscTotal;
    private UiMgr mUiMgr;
    private UiMgr uiMgr;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private final HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private final HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private byte[] m0x3BDatas = {22, 59, 0, 0, 0, 0, 0, 0};
    private byte[] m0x3DDatas = {22, 61, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int mDifferent = getCurrentCanDifferentId();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private String mDiscTitle = " ";

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$VehicleType;", "", "()V", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class VehicleType {
        public static final int AIRCROSS_4008 = 26;
        public static final int AIRPANEL = 32;
        public static final int BERLINGO = 27;
        public static final int BRAZILIAN_C4 = 25;
        public static final int ORIGINAL_CAR_SMALL_SCREEN = 33;
        public static final int Opel = 70;
        public static final int V04_05_XSARA = 28;
        public static final int V04_07_307 = 30;
        public static final int V06_10_C_TRIOMPHE = 3;
        public static final int V06_206 = 29;
        public static final int V07_14_307 = 21;
        public static final int V09_17_C4 = 11;
        public static final int V10_16_C4 = 7;
        public static final int V10_16_C5 = 5;
        public static final int V10_18_C4L = 12;
        public static final int V11_16_508 = 15;
        public static final int V12_18_308 = 23;
        public static final int V13_18_C_ELYSEE = 6;
        public static final int V13_18_DS5 = 10;
        public static final int V13_19_3008 = 16;
        public static final int V14_16_DS5LS = 9;
        public static final int V14_17_DS6 = 8;
        public static final int V14_18_301 = 20;
        public static final int V14_19_408 = 17;
        public static final int V14_19_C3_XR = 13;
        public static final int V14_20_2008 = 19;
        public static final int V15_18_308S = 22;
        public static final int V15_18_C4 = 1;
        public static final int V16_19_4008 = 18;
        public static final int V17_19_5008 = 14;
        public static final int V17_C5_AIRCROSS = 4;
        public static final int V19_508L = 24;
        public static final int V19_C4L = 2;
    }

    public final byte[] getM0x3BDatas() {
        return this.m0x3BDatas;
    }

    public final byte[] getM0x3DDatas() {
        return this.m0x3DDatas;
    }

    public final int getMDifferent() {
        return this.mDifferent;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.afterServiceNormalSetting(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.UiMgr");
        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
        updateSettingActivity("_29_left_side", Integer.valueOf(SharePreUtil.getIntValue(context, UiMgr._161_LCD_MODE_LEFT, 0)));
        updateSettingActivity("_29_right_side", Integer.valueOf(SharePreUtil.getIntValue(context, UiMgr._161_LCD_MODE_RIGHT, 0)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void initAmplifier(android.content.Context r12) {
        /*
            r11 = this;
            r0 = 7
            r1 = 5
            r2 = 3
            r3 = 22
            r4 = 4
            r5 = 2
            r6 = 1
            r7 = 0
            if (r12 == 0) goto L72
            int r8 = r11.getCanId()
            com.hzbhd.canbus.interfaces.UiMgrInterface r9 = com.hzbhd.canbus.ui_mgr.UiMgrFactory.getCanUiMgr(r12)
            com.hzbhd.canbus.ui_set.AmplifierPageUiSet r9 = r9.getAmplifierPageUiSet(r12)
            r11.getAmplifierData(r12, r8, r9)
            byte[] r8 = new byte[r4]
            r8[r7] = r3
            r9 = -116(0xffffffffffffff8c, float:NaN)
            r8[r6] = r9
            java.lang.String r9 = "_161_lcd_mode_left"
            int r9 = com.hzbhd.canbus.util.SharePreUtil.getIntValue(r12, r9, r7)
            byte r9 = (byte) r9
            r8[r5] = r9
            java.lang.String r9 = "_161_lcd_mode_right"
            int r9 = com.hzbhd.canbus.util.SharePreUtil.getIntValue(r12, r9, r7)
            byte r9 = (byte) r9
            r8[r2] = r9
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r8)
            byte[] r8 = new byte[r1]
            r8[r7] = r3
            r9 = -89
            r8[r6] = r9
            int r9 = r11.mDifferent
            r10 = 13
            if (r9 == r10) goto L60
            r10 = 25
            if (r9 == r10) goto L5e
            r10 = 70
            if (r9 == r10) goto L5c
            if (r9 == r3) goto L60
            r10 = 23
            if (r9 == r10) goto L60
            switch(r9) {
                case 16: goto L5a;
                case 17: goto L60;
                case 18: goto L58;
                default: goto L56;
            }
        L56:
            r9 = r2
            goto L61
        L58:
            r9 = r5
            goto L61
        L5a:
            r9 = r7
            goto L61
        L5c:
            r9 = r0
            goto L61
        L5e:
            r9 = r4
            goto L61
        L60:
            r9 = r6
        L61:
            byte r9 = (byte) r9
            r8[r5] = r9
            java.lang.String r9 = "_161_vehicle_config"
            int r12 = com.hzbhd.canbus.util.SharePreUtil.getIntValue(r12, r9, r6)
            byte r12 = (byte) r12
            r8[r2] = r12
            r8[r4] = r7
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r8)
        L72:
            byte[][] r12 = new byte[r6][]
            r8 = 10
            byte[] r8 = new byte[r8]
            r8[r7] = r3
            r3 = -59
            r8[r6] = r3
            int r3 = com.hzbhd.canbus.ui_datas.GeneralAmplifierData.frontRear
            r6 = 9
            int r3 = r3 + r6
            byte r3 = (byte) r3
            r8[r5] = r3
            int r3 = com.hzbhd.canbus.ui_datas.GeneralAmplifierData.leftRight
            int r3 = r3 + r6
            byte r3 = (byte) r3
            r8[r2] = r3
            int r2 = com.hzbhd.canbus.ui_datas.GeneralAmplifierData.bandBass
            byte r2 = (byte) r2
            r8[r4] = r2
            int r2 = com.hzbhd.canbus.ui_datas.GeneralAmplifierData.bandTreble
            byte r2 = (byte) r2
            r8[r1] = r2
            r1 = 6
            int r2 = com.hzbhd.canbus.ui_datas.GeneralAmplifierData.bandMiddle
            byte r2 = (byte) r2
            r8[r1] = r2
            r8[r0] = r7
            r0 = 8
            r8[r0] = r7
            r8[r6] = r5
            r12[r7] = r8
            byte[][] r12 = (byte[][]) r12
            com.hzbhd.canbus.util.TimerUtil r0 = new com.hzbhd.canbus.util.TimerUtil
            r0.<init>()
            com.hzbhd.canbus.car._161.MsgMgr$initAmplifier$2$1$1 r1 = new com.hzbhd.canbus.car._161.MsgMgr$initAmplifier$2$1$1
            r1.<init>()
            java.util.TimerTask r1 = (java.util.TimerTask) r1
            r2 = 0
            r4 = 100
            r0.startTimer(r1, r2, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._161.MsgMgr.initAmplifier(android.content.Context):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        this.mContext = context;
        Parser parser = this.mParserArray.get(byteArrayToIntArray[1]);
        if (parser != null) {
            parser.parse(this.mCanbusInfoInt.length - 2);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) bYear2Dig, (byte) bMonth, (byte) bDay, (byte) bHours24H, (byte) bMins});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._161.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m136dateTimeRepCanbus$lambda3();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dateTimeRepCanbus$lambda-3, reason: not valid java name */
    public static final void m136dateTimeRepCanbus$lambda3() {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE, 0});
    }

    /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.util.List] */
    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.append(1, new MsgMgr$initParsers$1$1(this));
        sparseArray.put(2, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$2
            private int tripComputerPage;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x02】按键命令");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i == 0) {
                    realKeyLongClick2(0);
                    return;
                }
                if (i == 128) {
                    realKeyLongClick2(HotKeyConstant.K_SLEEP);
                    return;
                }
                if (i == 2) {
                    realKeyLongClick2(52);
                    return;
                }
                if (i == 3) {
                    realKeyLongClick2(45);
                    return;
                }
                if (i == 4) {
                    realKeyLongClick2(46);
                    return;
                }
                if (i == 7) {
                    realKeyLongClick2(49);
                    return;
                }
                if (i == 8) {
                    realKeyLongClick2(50);
                    return;
                }
                if (i == 9) {
                    realKeyLongClick2(53);
                    return;
                }
                if (i == 97) {
                    realKeyLongClick2(2);
                    return;
                }
                if (i != 98) {
                    switch (i) {
                        case 16:
                            realKeyLongClick2(2);
                            break;
                        case 17:
                            realKeyLongClick2(14);
                            break;
                        case 18:
                            realKeyLongClick2(20);
                            break;
                        case 19:
                            realKeyLongClick2(21);
                            break;
                        case 20:
                            realKeyLongClick2(7);
                            break;
                        case 21:
                            realKeyLongClick2(8);
                            break;
                        case 22:
                            realKeyLongClick2(3);
                            break;
                        case 23:
                            realKeyLongClick2(45);
                            break;
                        case 24:
                            realKeyLongClick2(46);
                            break;
                        case 25:
                            this.this$0.Jump();
                            break;
                        default:
                            switch (i) {
                                case 33:
                                    realKeyLongClick2(52);
                                    break;
                                case 34:
                                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_161_speed_remember");
                                    if (settingUpdateEntity != null) {
                                        this.this$0.startSettingActivity(context, settingUpdateEntity.getLeftListIndex(), settingUpdateEntity.getRightListIndex());
                                        break;
                                    }
                                    break;
                                case 35:
                                    realKeyLongClick2(68);
                                    break;
                                default:
                                    switch (i) {
                                        case 41:
                                            realKeyLongClick2(HotKeyConstant.K_SPEECH);
                                            break;
                                        case 42:
                                            this.this$0.startMainActivity(context);
                                            break;
                                        case 43:
                                            realKeyLongClick2(128);
                                            break;
                                        case 44:
                                            realKeyLongClick2(2);
                                            break;
                                        case 45:
                                            realKeyLongClick2(68);
                                            break;
                                        case 46:
                                            realKeyLongClick2(58);
                                            break;
                                        case 47:
                                            if (SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(AirActivity.class).getQualifiedName())) {
                                                this.this$0.finishActivity();
                                                break;
                                            } else {
                                                Context context2 = context;
                                                Intent intent = new Intent();
                                                intent.setComponent(Constant.AirActivity);
                                                intent.setFlags(268435456);
                                                context2.startActivity(intent);
                                                break;
                                            }
                                        case 48:
                                            realKeyLongClick2(14);
                                            break;
                                        case 49:
                                            realKeyLongClick2(15);
                                            break;
                                        case 50:
                                            realKeyLongClick2(128);
                                            break;
                                        case 51:
                                            realKeyLongClick2(76);
                                            break;
                                        case 52:
                                            realKeyLongClick2(58);
                                            break;
                                        case 53:
                                            realKeyLongClick2(68);
                                            break;
                                        case 54:
                                            realKeyLongClick2(59);
                                            break;
                                        case 55:
                                            realKeyLongClick2(128);
                                            break;
                                        case 56:
                                            realKeyLongClick2(45);
                                            break;
                                        case 57:
                                            realKeyLongClick2(46);
                                            break;
                                        default:
                                            switch (i) {
                                                case 64:
                                                    realKeyLongClick2(47);
                                                    break;
                                                case 65:
                                                    realKeyLongClick2(48);
                                                    break;
                                                case 66:
                                                    realKeyLongClick2(45);
                                                    break;
                                                case 67:
                                                    realKeyLongClick2(46);
                                                    break;
                                                case 68:
                                                    realKeyLongClick2(33);
                                                    break;
                                                case 69:
                                                    realKeyLongClick2(34);
                                                    break;
                                                case 70:
                                                    realKeyLongClick2(35);
                                                    break;
                                                case 71:
                                                    realKeyLongClick2(36);
                                                    break;
                                                case 72:
                                                    realKeyLongClick2(37);
                                                    break;
                                                case 73:
                                                    realKeyLongClick2(38);
                                                    break;
                                                case 74:
                                                    realKeyLongClick2(2);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 80:
                                                            realKeyLongClick2(129);
                                                            break;
                                                        case 81:
                                                            realKeyLongClick2(128);
                                                            break;
                                                        case 82:
                                                            realKeyLongClick2(4);
                                                            break;
                                                        case 83:
                                                            realKeyLongClick2(94);
                                                            break;
                                                        case 84:
                                                            realKeyLongClick2(HotKeyConstant.K_DARK_MODE);
                                                            break;
                                                        case 85:
                                                            realKeyLongClick2(31);
                                                            break;
                                                        case 86:
                                                            realKeyLongClick2(46);
                                                            break;
                                                        case 87:
                                                            realKeyLongClick2(45);
                                                            break;
                                                        case 88:
                                                            realKeyLongClick2(45);
                                                            break;
                                                        case 89:
                                                            realKeyLongClick2(46);
                                                            break;
                                                    }
                                            }
                                    }
                            }
                    }
                    return;
                }
                realKeyLongClick2(14);
            }

            private final void realKeyLongClick2(int key) {
                this.this$0.realKeyLongClick2(context, key);
            }
        });
        sparseArray.append(33, new MsgMgr$initParsers$1$3(this, context));
        sparseArray.append(34, new MsgMgr$initParsers$1$4(this, context));
        sparseArray.append(39, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$5
            {
                super(this.this$0, "【0x27】语言");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingUpdateEntity settingUpdateEntity;
                if (!isDataChange() || (settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_220_language_settings")) == null) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                settingUpdateEntity.setValue(Integer.valueOf(msgMgr.mCanbusInfoInt[2]));
                msgMgr.updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntity));
                msgMgr.updateSettingActivity(null);
            }
        });
        sparseArray.append(41, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x29】方向盘转角");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = Math.negateExact(TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 5450, 16));
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        sparseArray.append(50, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$7
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x32】雷达信息");
                RadarInfoUtil.mMinIsClose = true;
                RadarInfoUtil.mDisableData = 6;
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    RadarInfoUtil.setRearRadarLocationData(5, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
                    RadarInfoUtil.setFrontRadarLocationData(5, this.this$0.mCanbusInfoInt[6] + 1, this.this$0.mCanbusInfoInt[7] + 1, this.this$0.mCanbusInfoInt[7] + 1, this.this$0.mCanbusInfoInt[8] + 1);
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        sparseArray.append(51, new MsgMgr$initParsers$1$8(this));
        sparseArray.append(52, new MsgMgr$initParsers$1$9(this));
        sparseArray.append(53, new MsgMgr$initParsers$1$10(this));
        sparseArray.append(54, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x36】室外温度");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (!isDataChange() || this.this$0.mCanbusInfoInt[2] == 128) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                Context context2 = context;
                int i = msgMgr.mCanbusInfoInt[2] & WorkQueueKt.MASK;
                msgMgr.updateOutDoorTemp(context2, ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1 ? new StringBuilder().append('-').append(i).toString() : String.valueOf(i));
            }
        });
        final Popup popup = new Popup(context);
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = CollectionsKt.emptyList();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = CollectionsKt.emptyList();
        sparseArray.append(55, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$12
            private final SparseArray<SparseArray<Popup.Ops.WarningBean>> warningBeans;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x37】汽车报警记录信息");
                SparseArray<SparseArray<Popup.Ops.WarningBean>> sparseArray2 = new SparseArray<>();
                SparseArray<Popup.Ops.WarningBean> sparseArray3 = new SparseArray<>();
                sparseArray3.put(0, new Popup.Ops.WarningBean(R.string._161_warning_0_0));
                sparseArray3.append(1, new Popup.Ops.WarningBean(R.string._161_warning_0_1));
                sparseArray3.append(2, new Popup.Ops.WarningBean(R.string._161_warning_0_2));
                sparseArray3.append(3, new Popup.Ops.WarningBean(R.string._161_warning_0_3));
                sparseArray3.append(4, new Popup.Ops.WarningBean(R.string._161_warning_0_4));
                sparseArray3.append(5, new Popup.Ops.WarningBean(R.string._161_warning_0_5));
                sparseArray3.append(6, new Popup.Ops.WarningBean(R.string._161_warning_0_6));
                sparseArray3.append(7, new Popup.Ops.WarningBean(R.string._161_warning_0_7));
                Unit unit = Unit.INSTANCE;
                sparseArray2.put(0, sparseArray3);
                SparseArray<Popup.Ops.WarningBean> sparseArray4 = new SparseArray<>();
                sparseArray4.put(0, new Popup.Ops.WarningBean(R.string._161_warning_1_0));
                sparseArray4.append(1, new Popup.Ops.WarningBean(R.string._161_warning_1_1));
                sparseArray4.append(2, new Popup.Ops.WarningBean(R.string._161_warning_1_2));
                sparseArray4.append(3, new Popup.Ops.WarningBean(R.string._161_warning_1_3));
                sparseArray4.append(4, new Popup.Ops.WarningBean(R.string._161_warning_1_4));
                sparseArray4.append(5, new Popup.Ops.WarningBean(R.string._161_warning_1_5));
                sparseArray4.append(6, new Popup.Ops.WarningBean(R.string._161_warning_1_6));
                sparseArray4.append(7, new Popup.Ops.WarningBean(R.string._161_warning_1_7));
                Unit unit2 = Unit.INSTANCE;
                sparseArray2.append(1, sparseArray4);
                SparseArray<Popup.Ops.WarningBean> sparseArray5 = new SparseArray<>();
                sparseArray5.put(0, new Popup.Ops.WarningBean(R.string._161_warning_2_0));
                sparseArray5.append(1, new Popup.Ops.WarningBean(R.string._161_warning_2_1));
                sparseArray5.append(2, new Popup.Ops.WarningBean(R.string._161_warning_2_2));
                sparseArray5.append(3, new Popup.Ops.WarningBean(R.string._161_warning_2_3));
                sparseArray5.append(4, new Popup.Ops.WarningBean(R.string._161_warning_2_4));
                sparseArray5.append(5, new Popup.Ops.WarningBean(R.string._161_warning_2_5));
                sparseArray5.append(6, new Popup.Ops.WarningBean(R.string._161_warning_2_6));
                sparseArray5.append(7, new Popup.Ops.WarningBean(R.string._161_warning_2_7));
                Unit unit3 = Unit.INSTANCE;
                sparseArray2.append(2, sparseArray5);
                SparseArray<Popup.Ops.WarningBean> sparseArray6 = new SparseArray<>();
                sparseArray6.put(1, new Popup.Ops.WarningBean(R.string._161_warning_3_1));
                sparseArray6.append(2, new Popup.Ops.WarningBean(R.string._161_warning_3_2));
                sparseArray6.append(3, new Popup.Ops.WarningBean(R.string._161_warning_3_3));
                sparseArray6.append(4, new Popup.Ops.WarningBean(R.string._161_warning_3_4));
                sparseArray6.append(5, new Popup.Ops.WarningBean(R.string._161_warning_3_5));
                sparseArray6.append(6, new Popup.Ops.WarningBean(R.string._161_warning_3_6));
                sparseArray6.append(7, new Popup.Ops.WarningBean(R.string._161_warning_3_7));
                Unit unit4 = Unit.INSTANCE;
                sparseArray2.append(3, sparseArray6);
                SparseArray<Popup.Ops.WarningBean> sparseArray7 = new SparseArray<>();
                sparseArray7.put(0, new Popup.Ops.WarningBean(R.string._161_warning_4_0));
                sparseArray7.append(1, new Popup.Ops.WarningBean(R.string._161_warning_4_1));
                sparseArray7.append(2, new Popup.Ops.WarningBean(R.string._161_warning_4_2));
                sparseArray7.append(3, new Popup.Ops.WarningBean(R.string._161_warning_4_3));
                sparseArray7.append(4, new Popup.Ops.WarningBean(R.string._161_warning_4_4));
                sparseArray7.append(5, new Popup.Ops.WarningBean(R.string._161_warning_4_5));
                sparseArray7.append(6, new Popup.Ops.WarningBean(R.string._161_warning_4_6));
                Unit unit5 = Unit.INSTANCE;
                sparseArray2.append(4, sparseArray7);
                SparseArray<Popup.Ops.WarningBean> sparseArray8 = new SparseArray<>();
                sparseArray8.put(0, new Popup.Ops.WarningBean(R.string._161_warning_5_0));
                sparseArray8.append(1, new Popup.Ops.WarningBean(R.string._161_warning_5_1));
                sparseArray8.append(2, new Popup.Ops.WarningBean(R.string._161_warning_5_2));
                sparseArray8.append(3, new Popup.Ops.WarningBean(R.string._161_warning_5_3));
                sparseArray8.append(4, new Popup.Ops.WarningBean(R.string._161_warning_5_4));
                sparseArray8.append(5, new Popup.Ops.WarningBean(R.string._161_warning_5_5));
                sparseArray8.append(6, new Popup.Ops.WarningBean(R.string._161_warning_5_6));
                sparseArray8.append(7, new Popup.Ops.WarningBean(R.string._161_warning_5_7));
                Unit unit6 = Unit.INSTANCE;
                sparseArray2.append(5, sparseArray8);
                SparseArray<Popup.Ops.WarningBean> sparseArray9 = new SparseArray<>();
                sparseArray9.put(0, new Popup.Ops.WarningBean(R.string._161_warning_6_0));
                sparseArray9.append(1, new Popup.Ops.WarningBean(R.string._161_warning_6_1));
                sparseArray9.append(2, new Popup.Ops.WarningBean(R.string._161_warning_6_2));
                sparseArray9.append(3, new Popup.Ops.WarningBean(R.string._161_warning_6_3));
                sparseArray9.append(4, new Popup.Ops.WarningBean(R.string._161_warning_6_4));
                sparseArray9.append(5, new Popup.Ops.WarningBean(R.string._161_warning_6_5));
                sparseArray9.append(6, new Popup.Ops.WarningBean(R.string._161_warning_6_6));
                sparseArray9.append(7, new Popup.Ops.WarningBean(R.string._161_warning_6_7));
                Unit unit7 = Unit.INSTANCE;
                sparseArray2.append(6, sparseArray9);
                SparseArray<Popup.Ops.WarningBean> sparseArray10 = new SparseArray<>();
                sparseArray10.put(0, new Popup.Ops.WarningBean(R.string._161_warning_7_0));
                sparseArray10.append(1, new Popup.Ops.WarningBean(R.string._161_warning_7_1));
                sparseArray10.append(2, new Popup.Ops.WarningBean(R.string._161_warning_7_2));
                sparseArray10.append(3, new Popup.Ops.WarningBean(R.string._161_warning_7_3));
                sparseArray10.append(4, new Popup.Ops.WarningBean(R.string._161_warning_7_4));
                sparseArray10.append(5, new Popup.Ops.WarningBean(R.string._161_warning_7_5));
                sparseArray10.append(6, new Popup.Ops.WarningBean(R.string._161_warning_7_6));
                sparseArray10.append(7, new Popup.Ops.WarningBean(R.string._161_warning_7_7));
                Unit unit8 = Unit.INSTANCE;
                sparseArray2.append(7, sparseArray10);
                SparseArray<Popup.Ops.WarningBean> sparseArray11 = new SparseArray<>();
                sparseArray11.put(0, new Popup.Ops.WarningBean(R.string._161_warning_8_0));
                sparseArray11.append(1, new Popup.Ops.WarningBean(R.string._161_warning_8_1));
                sparseArray11.append(2, new Popup.Ops.WarningBean(R.string._161_warning_8_2));
                sparseArray11.append(3, new Popup.Ops.WarningBean(R.string._161_warning_8_3));
                sparseArray11.append(4, new Popup.Ops.WarningBean(R.string._161_warning_8_4));
                sparseArray11.append(5, new Popup.Ops.WarningBean(R.string._161_warning_8_5));
                sparseArray11.append(6, new Popup.Ops.WarningBean(R.string._161_warning_8_6));
                sparseArray11.append(7, new Popup.Ops.WarningBean(R.string._161_warning_8_7));
                Unit unit9 = Unit.INSTANCE;
                sparseArray2.append(8, sparseArray11);
                SparseArray<Popup.Ops.WarningBean> sparseArray12 = new SparseArray<>();
                sparseArray12.put(0, new Popup.Ops.WarningBean(R.string._161_warning_9_0));
                sparseArray12.append(1, new Popup.Ops.WarningBean(R.string._161_warning_9_1));
                sparseArray12.append(2, new Popup.Ops.WarningBean(R.string._161_warning_9_2));
                sparseArray12.append(3, new Popup.Ops.WarningBean(R.string._161_warning_9_3));
                sparseArray12.append(4, new Popup.Ops.WarningBean(R.string._161_warning_9_4));
                sparseArray12.append(5, new Popup.Ops.WarningBean(R.string._161_warning_9_5));
                sparseArray12.append(6, new Popup.Ops.WarningBean(R.string._161_warning_9_6));
                sparseArray12.append(7, new Popup.Ops.WarningBean(R.string._161_warning_9_7));
                Unit unit10 = Unit.INSTANCE;
                sparseArray2.append(9, sparseArray12);
                SparseArray<Popup.Ops.WarningBean> sparseArray13 = new SparseArray<>();
                sparseArray13.put(0, new Popup.Ops.WarningBean(R.string._161_warning_10_0));
                sparseArray13.append(1, new Popup.Ops.WarningBean(R.string._161_warning_10_1));
                sparseArray13.append(2, new Popup.Ops.WarningBean(R.string._161_warning_10_2));
                sparseArray13.append(3, new Popup.Ops.WarningBean(R.string._161_warning_10_3));
                sparseArray13.append(4, new Popup.Ops.WarningBean(R.string._161_warning_10_4));
                sparseArray13.append(5, new Popup.Ops.WarningBean(R.string._161_warning_10_5));
                sparseArray13.append(6, new Popup.Ops.WarningBean(R.string._161_warning_10_6));
                sparseArray13.append(7, new Popup.Ops.WarningBean(R.string._161_warning_10_7));
                Unit unit11 = Unit.INSTANCE;
                sparseArray2.append(10, sparseArray13);
                SparseArray<Popup.Ops.WarningBean> sparseArray14 = new SparseArray<>();
                sparseArray14.put(0, new Popup.Ops.WarningBean(R.string._161_warning_11_0));
                sparseArray14.append(1, new Popup.Ops.WarningBean(R.string._161_warning_11_1));
                sparseArray14.append(2, new Popup.Ops.WarningBean(R.string._161_warning_11_2));
                sparseArray14.append(3, new Popup.Ops.WarningBean(R.string._161_warning_11_3));
                sparseArray14.append(4, new Popup.Ops.WarningBean(R.string._161_warning_11_4));
                sparseArray14.append(5, new Popup.Ops.WarningBean(R.string._161_warning_11_5));
                sparseArray14.append(6, new Popup.Ops.WarningBean(R.string._161_warning_11_6));
                sparseArray14.append(7, new Popup.Ops.WarningBean(R.string._161_warning_11_7));
                Unit unit12 = Unit.INSTANCE;
                sparseArray2.append(11, sparseArray14);
                SparseArray<Popup.Ops.WarningBean> sparseArray15 = new SparseArray<>();
                sparseArray15.put(0, new Popup.Ops.WarningBean(R.string._161_warning_12_0));
                sparseArray15.append(1, new Popup.Ops.WarningBean(R.string._161_warning_12_1));
                sparseArray15.append(2, new Popup.Ops.WarningBean(R.string._161_warning_12_2));
                sparseArray15.append(3, new Popup.Ops.WarningBean(R.string._161_warning_12_3));
                sparseArray15.append(4, new Popup.Ops.WarningBean(R.string._161_warning_12_4));
                sparseArray15.append(5, new Popup.Ops.WarningBean(R.string._161_warning_12_5));
                sparseArray15.append(6, new Popup.Ops.WarningBean(R.string._161_warning_12_6));
                sparseArray15.append(7, new Popup.Ops.WarningBean(R.string._161_warning_12_7));
                Unit unit13 = Unit.INSTANCE;
                sparseArray2.append(12, sparseArray15);
                SparseArray<Popup.Ops.WarningBean> sparseArray16 = new SparseArray<>();
                sparseArray16.put(0, new Popup.Ops.WarningBean(R.string._161_warning_13_0));
                sparseArray16.append(1, new Popup.Ops.WarningBean(R.string._161_warning_13_1));
                sparseArray16.append(2, new Popup.Ops.WarningBean(R.string._161_warning_13_2));
                Unit unit14 = Unit.INSTANCE;
                sparseArray2.append(13, sparseArray16);
                this.warningBeans = sparseArray2;
            }

            /* JADX WARN: Type inference failed for: r15v2, types: [T, java.util.ArrayList] */
            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                Popup.Ops.WarningBean warningBean;
                if (isDataChange()) {
                    ?? arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    Ref.ObjectRef<List<WarningEntity>> objectRef3 = objectRef;
                    Popup popup2 = popup;
                    Ref.ObjectRef<List<WarningEntity>> objectRef4 = objectRef2;
                    int length = msgMgr.mCanbusInfoInt.length;
                    int i = 2;
                    while (true) {
                        if (i >= length) {
                            break;
                        }
                        for (int i2 = 0; i2 < 8; i2++) {
                            SparseArray<Popup.Ops.WarningBean> sparseArray2 = this.warningBeans.get(i - 2);
                            if (sparseArray2 != null && (warningBean = sparseArray2.get(i2)) != null) {
                                Intrinsics.checkNotNullExpressionValue(warningBean, "get(bit)");
                                Popup.InfoBean bean = warningBean.getBean();
                                int i3 = (msgMgr.mCanbusInfoInt[i] >> i2) & 1;
                                if (i3 == 1) {
                                    arrayList.add(new WarningEntity('\t' + context2.getString(warningBean.getMessageResId())));
                                    if (warningBean.getBean().isNeedShow() == 0) {
                                        popup2.plus(warningBean);
                                    }
                                } else if (warningBean.getBean().isNeedShow() == 1) {
                                    popup2.minus(warningBean);
                                }
                                bean.setNeedShow(i3);
                            }
                        }
                        i++;
                    }
                    if (arrayList.size() != 0) {
                        arrayList.add(0, new WarningEntity(context2.getString(R.string.warning_msg)));
                    }
                    objectRef3.element = arrayList;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(objectRef4.element);
                    arrayList2.addAll(objectRef3.element);
                    GeneralWarningDataData.dataList = arrayList2;
                    msgMgr.updateWarningActivity(null);
                }
            }
        });
        sparseArray.append(56, new MsgMgr$initParsers$1$13(this, context));
        sparseArray.append(57, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$14
            private final SparseArray<SparseArray<SparseArray<Popup.Ops.FunctionBean>>> functionBeans;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x39】汽车功能状态信息");
                SparseArray<SparseArray<SparseArray<Popup.Ops.FunctionBean>>> sparseArray2 = new SparseArray<>();
                SparseArray<SparseArray<Popup.Ops.FunctionBean>> sparseArray3 = new SparseArray<>();
                SparseArray<Popup.Ops.FunctionBean> sparseArray4 = new SparseArray<>();
                sparseArray4.put(1, new Popup.Ops.FunctionBean(R.drawable.drive_infor, R.string._161_parking_brake_activated));
                sparseArray4.append(2, new Popup.Ops.FunctionBean(R.drawable.drive_infor, R.string._161_parking_brake_released));
                Unit unit = Unit.INSTANCE;
                sparseArray3.put(16, sparseArray4);
                SparseArray<Popup.Ops.FunctionBean> sparseArray5 = new SparseArray<>();
                sparseArray5.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_wiper_auto_enable, R.string._161_automatic_wiper_enable));
                sparseArray5.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_wiper_auto_disable, R.string._161_automatic_wiper_disable));
                Unit unit2 = Unit.INSTANCE;
                sparseArray3.append(50, sparseArray5);
                SparseArray<Popup.Ops.FunctionBean> sparseArray6 = new SparseArray<>();
                sparseArray6.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_highbeam_auto_enable, R.string._161_automatic_High_beam_enable));
                sparseArray6.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_highbeam_auto_disable, R.string._161_automatic_High_beam_disable));
                Unit unit3 = Unit.INSTANCE;
                sparseArray3.append(84, sparseArray6);
                SparseArray<Popup.Ops.FunctionBean> sparseArray7 = new SparseArray<>();
                sparseArray7.put(1, new Popup.Ops.FunctionBean(R.drawable.drive_infor, R.string._161_door_auto_lock_enable));
                sparseArray7.append(2, new Popup.Ops.FunctionBean(R.drawable.drive_infor, R.string._161_door_auto_lock_disable));
                Unit unit4 = Unit.INSTANCE;
                sparseArray3.append(118, sparseArray7);
                Unit unit5 = Unit.INSTANCE;
                sparseArray2.put(0, sparseArray3);
                SparseArray<SparseArray<Popup.Ops.FunctionBean>> sparseArray8 = new SparseArray<>();
                SparseArray<Popup.Ops.FunctionBean> sparseArray9 = new SparseArray<>();
                sparseArray9.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_child_safety_enable, R.string._161_child_safety_device_enable));
                sparseArray9.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_child_safety_disable, R.string._161_child_safety_device_disable));
                Unit unit6 = Unit.INSTANCE;
                sparseArray8.put(16, sparseArray9);
                SparseArray<Popup.Ops.FunctionBean> sparseArray10 = new SparseArray<>();
                sparseArray10.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_esp_enable, R.string._161_esp_enable));
                sparseArray10.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_esp_disable, R.string._161_esp_disable));
                Unit unit7 = Unit.INSTANCE;
                sparseArray8.append(50, sparseArray10);
                SparseArray<Popup.Ops.FunctionBean> sparseArray11 = new SparseArray<>();
                sparseArray11.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_normal_mode, R.string._161_drive_mode_normal));
                sparseArray11.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_muddy_mode, R.string._161_drive_mode_muddy));
                sparseArray11.append(3, new Popup.Ops.FunctionBean(R.drawable.psa_snow_mode, R.string._161_drive_mode_snow));
                sparseArray11.append(4, new Popup.Ops.FunctionBean(R.drawable.psa_sand_mode, R.string._161_drive_mode_sand));
                Unit unit8 = Unit.INSTANCE;
                sparseArray8.append(100, sparseArray11);
                Unit unit9 = Unit.INSTANCE;
                sparseArray2.append(1, sparseArray8);
                SparseArray<SparseArray<Popup.Ops.FunctionBean>> sparseArray12 = new SparseArray<>();
                SparseArray<Popup.Ops.FunctionBean> sparseArray13 = new SparseArray<>();
                sparseArray13.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_start_stop_enable, R.string._161_start_stop_enable));
                sparseArray13.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_start_stop_disable, R.string._161_start_stop_disable));
                Unit unit10 = Unit.INSTANCE;
                sparseArray12.put(16, sparseArray13);
                SparseArray<Popup.Ops.FunctionBean> sparseArray14 = new SparseArray<>();
                sparseArray14.put(1, new Popup.Ops.FunctionBean(R.drawable.psa_traction_control_on, R.string._161_traction_control_on));
                sparseArray14.append(2, new Popup.Ops.FunctionBean(R.drawable.psa_traction_control_off, R.string._161_traction_control_off));
                Unit unit11 = Unit.INSTANCE;
                sparseArray12.append(50, sparseArray14);
                Unit unit12 = Unit.INSTANCE;
                sparseArray2.append(2, sparseArray12);
                this.functionBeans = sparseArray2;
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    SparseArray<SparseArray<SparseArray<Popup.Ops.FunctionBean>>> sparseArray2 = this.functionBeans;
                    MsgMgr msgMgr = this.this$0;
                    Popup popup2 = popup;
                    int size = sparseArray2.size();
                    for (int i = 0; i < size; i++) {
                        int iKeyAt = sparseArray2.keyAt(i);
                        Log.i("_1161_MsgMgr", "byteIndex  " + iKeyAt);
                        SparseArray<SparseArray<Popup.Ops.FunctionBean>> sparseArray3 = sparseArray2.get(iKeyAt);
                        int size2 = sparseArray3.size();
                        for (int i2 = 0; i2 < size2; i2++) {
                            int iKeyAt2 = sparseArray3.keyAt(i2);
                            int i3 = iKeyAt2 & 15;
                            Log.i("_1161_MsgMgr", "    bitIndex " + iKeyAt2 + "   end " + ((iKeyAt2 >> 4) & 15) + "  start " + i3);
                            SparseArray<Popup.Ops.FunctionBean> sparseArray4 = sparseArray3.get(iKeyAt2);
                            int size3 = sparseArray4.size();
                            int i4 = 0;
                            while (i4 < size3) {
                                int iKeyAt3 = sparseArray4.keyAt(i4);
                                SparseArray<SparseArray<SparseArray<Popup.Ops.FunctionBean>>> sparseArray5 = sparseArray2;
                                int i5 = size;
                                Log.i("_1161_MsgMgr", "        valueIndex  " + iKeyAt3);
                                Popup.Ops.FunctionBean functionBean = sparseArray4.get(iKeyAt3);
                                Popup.InfoBean bean = functionBean.getBean();
                                MsgMgr msgMgr2 = msgMgr;
                                int i6 = (msgMgr.mCanbusInfoInt[iKeyAt + 2] >> i3) & ((1 << ((r14 - i3) + 1)) - 1);
                                SparseArray<Popup.Ops.FunctionBean> sparseArray6 = sparseArray4;
                                int i7 = iKeyAt;
                                Log.i("_1161_MsgMgr", "        value " + i6 + "  isNeedShow " + functionBean.getBean().isNeedShow());
                                if (iKeyAt3 == i6 && functionBean.getBean().isNeedShow() != i6) {
                                    Intrinsics.checkNotNullExpressionValue(functionBean, "this");
                                    popup2.plus(functionBean);
                                }
                                bean.setNeedShow(i6);
                                i4++;
                                sparseArray2 = sparseArray5;
                                size = i5;
                                sparseArray4 = sparseArray6;
                                iKeyAt = i7;
                                msgMgr = msgMgr2;
                            }
                        }
                    }
                }
            }
        });
        sparseArray.append(58, new Parser(objectRef2, objectRef, context) { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$15
            final /* synthetic */ Ref.ObjectRef<List<WarningEntity>> $diagnosisList;
            final /* synthetic */ Ref.ObjectRef<List<WarningEntity>> $warningList;
            private final SparseArray<WarningEntity> diagnosisInfoArray;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x3A】诊断信息");
                SparseArray<WarningEntity> sparseArray2 = new SparseArray<>();
                sparseArray2.put(-1, new WarningEntity(CommUtil.getStrByResId(context, "_161_diagnostic_info")));
                sparseArray2.append(0, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_000")));
                sparseArray2.append(1, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_001")));
                sparseArray2.append(2, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_002")));
                sparseArray2.append(3, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_003")));
                sparseArray2.append(4, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_004")));
                sparseArray2.append(5, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_005")));
                sparseArray2.append(6, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_006")));
                sparseArray2.append(7, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_007")));
                sparseArray2.append(8, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_008")));
                sparseArray2.append(9, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_009")));
                sparseArray2.append(10, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_010")));
                sparseArray2.append(11, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_011")));
                sparseArray2.append(12, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_012")));
                sparseArray2.append(13, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_013")));
                sparseArray2.append(14, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_014")));
                sparseArray2.append(15, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_015")));
                sparseArray2.append(16, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_016")));
                sparseArray2.append(17, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_017")));
                sparseArray2.append(18, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_018")));
                sparseArray2.append(19, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_019")));
                sparseArray2.append(20, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_020")));
                sparseArray2.append(21, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_021")));
                sparseArray2.append(22, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_022")));
                sparseArray2.append(23, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_023")));
                sparseArray2.append(24, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_024")));
                sparseArray2.append(25, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_025")));
                sparseArray2.append(26, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_026")));
                sparseArray2.append(27, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_027")));
                sparseArray2.append(28, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_028")));
                sparseArray2.append(29, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_029")));
                sparseArray2.append(30, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_030")));
                sparseArray2.append(31, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_031")));
                sparseArray2.append(32, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_032")));
                sparseArray2.append(33, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_033")));
                sparseArray2.append(34, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_034")));
                sparseArray2.append(35, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_035")));
                sparseArray2.append(36, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_036")));
                sparseArray2.append(37, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_037")));
                sparseArray2.append(38, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_038")));
                sparseArray2.append(39, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_039")));
                sparseArray2.append(40, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_040")));
                sparseArray2.append(41, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_041")));
                sparseArray2.append(42, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_042")));
                sparseArray2.append(43, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_043")));
                sparseArray2.append(44, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_044")));
                sparseArray2.append(45, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_045")));
                sparseArray2.append(46, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_046")));
                sparseArray2.append(47, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_047")));
                sparseArray2.append(48, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_048")));
                sparseArray2.append(49, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_049")));
                sparseArray2.append(50, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_050")));
                sparseArray2.append(51, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_051")));
                sparseArray2.append(52, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_052")));
                sparseArray2.append(53, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_053")));
                sparseArray2.append(54, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_054")));
                sparseArray2.append(55, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_055")));
                sparseArray2.append(56, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_056")));
                sparseArray2.append(57, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_057")));
                sparseArray2.append(58, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_058")));
                sparseArray2.append(59, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_059")));
                sparseArray2.append(60, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_060")));
                sparseArray2.append(61, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_061")));
                sparseArray2.append(62, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_062")));
                sparseArray2.append(63, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_063")));
                sparseArray2.append(64, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_064")));
                sparseArray2.append(65, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_065")));
                sparseArray2.append(66, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_066")));
                sparseArray2.append(67, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_067")));
                sparseArray2.append(68, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_068")));
                sparseArray2.append(69, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_069")));
                sparseArray2.append(70, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_070")));
                sparseArray2.append(71, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_071")));
                sparseArray2.append(72, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_072")));
                sparseArray2.append(73, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_073")));
                sparseArray2.append(74, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_074")));
                sparseArray2.append(75, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_075")));
                sparseArray2.append(76, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_076")));
                sparseArray2.append(77, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_077")));
                sparseArray2.append(78, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_078")));
                sparseArray2.append(79, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_079")));
                sparseArray2.append(80, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_080")));
                sparseArray2.append(81, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_081")));
                sparseArray2.append(82, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_082")));
                sparseArray2.append(83, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_083")));
                sparseArray2.append(84, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_084")));
                sparseArray2.append(85, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_085")));
                sparseArray2.append(86, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_086")));
                sparseArray2.append(87, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_087")));
                sparseArray2.append(88, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_088")));
                sparseArray2.append(89, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_089")));
                sparseArray2.append(90, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_090")));
                sparseArray2.append(91, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_091")));
                sparseArray2.append(92, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_092")));
                sparseArray2.append(93, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_093")));
                sparseArray2.append(94, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_094")));
                sparseArray2.append(95, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_095")));
                sparseArray2.append(96, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_096")));
                sparseArray2.append(97, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_097")));
                sparseArray2.append(98, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_098")));
                sparseArray2.append(99, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_099")));
                sparseArray2.append(100, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_100")));
                sparseArray2.append(101, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_101")));
                sparseArray2.append(102, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_102")));
                sparseArray2.append(103, new WarningEntity('\t' + CommUtil.getStrByResId(context, "_161_info_103")));
                Unit unit = Unit.INSTANCE;
                this.diagnosisInfoArray = sparseArray2;
            }

            /* JADX WARN: Type inference failed for: r8v5, types: [T, java.util.ArrayList] */
            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    if (this.this$0.mCanbusInfoInt.length < 3) {
                        return;
                    }
                    ?? arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    Ref.ObjectRef<List<WarningEntity>> objectRef3 = this.$diagnosisList;
                    Ref.ObjectRef<List<WarningEntity>> objectRef4 = this.$warningList;
                    int length = msgMgr.mCanbusInfoInt.length;
                    for (int i = 3; i < length; i++) {
                        WarningEntity warningEntity = this.diagnosisInfoArray.get(msgMgr.mCanbusInfoInt[i]);
                        if (warningEntity != null) {
                            Intrinsics.checkNotNullExpressionValue(warningEntity, "diagnosisInfoArray[mCanbusInfoInt[i]]");
                            arrayList.add(warningEntity);
                        }
                    }
                    if (arrayList.size() != 0) {
                        arrayList.add(0, this.diagnosisInfoArray.get(-1));
                    }
                    objectRef3.element = arrayList;
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.addAll(objectRef3.element);
                    arrayList2.addAll(objectRef4.element);
                    GeneralWarningDataData.dataList = arrayList2;
                    msgMgr.updateWarningActivity(null);
                }
            }
        });
        sparseArray.append(59, new MsgMgr$initParsers$1$16(this));
        sparseArray.append(61, new MsgMgr$initParsers$1$17(this));
        sparseArray.append(63, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$18
            private boolean isCruiseRecord;
            private boolean isLimitRecord;
            private boolean isPopup;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x3F】速度巡航以及速度限制弹框");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingUpdateEntity settingUpdateEntity;
                SettingUpdateEntity settingUpdateEntity2;
                boolean z = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
                boolean z2 = ((this.this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
                if (this.isCruiseRecord != z || !z2) {
                    this.isCruiseRecord = z;
                    if (z && (settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_161_speed_cruise_1")) != null) {
                        this.this$0.startSettingActivity(context, settingUpdateEntity.getLeftListIndex(), settingUpdateEntity.getRightListIndex());
                        this.isPopup = true;
                    }
                }
                if (this.isLimitRecord != z2 || !z) {
                    this.isLimitRecord = z2;
                    if (z2 && (settingUpdateEntity2 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_161_speed_limit_1")) != null) {
                        this.this$0.startSettingActivity(context, settingUpdateEntity2.getLeftListIndex(), settingUpdateEntity2.getRightListIndex());
                        this.isPopup = true;
                    }
                }
                if (this.this$0.mCanbusInfoInt[2] == 0 && SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(SettingActivity.class).getQualifiedName()) && this.isPopup) {
                    this.this$0.finishActivity();
                    this.isPopup = false;
                }
            }
        });
        sparseArray.append(64, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$19
            private boolean isPanoramicRecord;
            private final ArrayList<PanoramicBtnUpdateEntity> list;
            private int mode;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x40】全景视频状态");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    boolean z = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
                    if (this.isPanoramicRecord != z) {
                        this.isPanoramicRecord = z;
                        this.this$0.forceReverse(context, z);
                    }
                    this.list.clear();
                    int i = this.this$0.mCanbusInfoInt[2];
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    if (Integer.valueOf(i).equals(Integer.valueOf(this.mode))) {
                        return;
                    }
                    this.mode = i;
                    int mDifferent = msgMgr.getMDifferent();
                    if (mDifferent == 16) {
                        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(msgMgr.mCanbusInfoInt[2], 0, 4);
                        if (intFromByteWithBit == 5) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, true));
                            this.list.add(new PanoramicBtnUpdateEntity(1, false));
                            this.list.add(new PanoramicBtnUpdateEntity(2, false));
                        } else if (intFromByteWithBit == 6) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, false));
                            this.list.add(new PanoramicBtnUpdateEntity(1, true));
                            this.list.add(new PanoramicBtnUpdateEntity(2, false));
                        } else if (intFromByteWithBit == 7) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, false));
                            this.list.add(new PanoramicBtnUpdateEntity(1, false));
                            this.list.add(new PanoramicBtnUpdateEntity(2, true));
                        }
                    } else {
                        if (mDifferent != 17) {
                            return;
                        }
                        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(msgMgr.mCanbusInfoInt[2], 0, 4);
                        if (intFromByteWithBit2 == 1) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, true));
                            this.list.add(new PanoramicBtnUpdateEntity(1, false));
                            this.list.add(new PanoramicBtnUpdateEntity(2, false));
                            this.list.add(new PanoramicBtnUpdateEntity(3, false));
                            this.list.add(new PanoramicBtnUpdateEntity(4, false));
                            this.list.add(new PanoramicBtnUpdateEntity(5, false));
                            this.list.add(new PanoramicBtnUpdateEntity(6, false));
                        } else if (intFromByteWithBit2 == 2) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, false));
                            this.list.add(new PanoramicBtnUpdateEntity(1, true));
                            this.list.add(new PanoramicBtnUpdateEntity(2, false));
                            this.list.add(new PanoramicBtnUpdateEntity(3, false));
                            this.list.add(new PanoramicBtnUpdateEntity(4, false));
                            this.list.add(new PanoramicBtnUpdateEntity(5, false));
                            this.list.add(new PanoramicBtnUpdateEntity(6, false));
                        } else if (intFromByteWithBit2 == 3) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, false));
                            this.list.add(new PanoramicBtnUpdateEntity(1, false));
                            this.list.add(new PanoramicBtnUpdateEntity(2, true));
                            this.list.add(new PanoramicBtnUpdateEntity(3, false));
                            this.list.add(new PanoramicBtnUpdateEntity(4, false));
                            this.list.add(new PanoramicBtnUpdateEntity(5, false));
                            this.list.add(new PanoramicBtnUpdateEntity(6, false));
                        } else if (intFromByteWithBit2 == 4) {
                            this.list.add(new PanoramicBtnUpdateEntity(0, false));
                            this.list.add(new PanoramicBtnUpdateEntity(1, false));
                            this.list.add(new PanoramicBtnUpdateEntity(2, false));
                            this.list.add(new PanoramicBtnUpdateEntity(3, true));
                            this.list.add(new PanoramicBtnUpdateEntity(4, false));
                            this.list.add(new PanoramicBtnUpdateEntity(5, false));
                            this.list.add(new PanoramicBtnUpdateEntity(6, false));
                        } else {
                            switch (intFromByteWithBit2) {
                                case 8:
                                    this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(4, true));
                                    this.list.add(new PanoramicBtnUpdateEntity(5, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(6, false));
                                    break;
                                case 9:
                                    this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(4, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(5, true));
                                    this.list.add(new PanoramicBtnUpdateEntity(6, false));
                                    break;
                                case 10:
                                    this.list.add(new PanoramicBtnUpdateEntity(1, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(2, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(3, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(4, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(5, false));
                                    this.list.add(new PanoramicBtnUpdateEntity(6, true));
                                    break;
                            }
                        }
                        Log.d("Lai", String.valueOf(msgMgr.getMDifferent()));
                    }
                    GeneralParkData.dataList = this.list;
                    msgMgr.updateParkUi(null, context2);
                }
            }
        });
        sparseArray.append(65, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$20
            private final ArrayList<SettingUpdateEntity<Object>> list;

            {
                super(this.this$0, "【0x41】设置项状态 2");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
                    MsgMgr msgMgr = this.this$0;
                    arrayList.clear();
                    int i = msgMgr.mCanbusInfoInt[2];
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_284_setting_item_2B");
                    if (settingUpdateEntity != null) {
                        arrayList.add(settingUpdateEntity.setValue(Integer.valueOf((i >> 7) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_anti_collision");
                    if (settingUpdateEntity2 != null) {
                        arrayList.add(settingUpdateEntity2.setValue(Integer.valueOf((i >> 6) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_decoder_voice");
                    if (settingUpdateEntity3 != null) {
                        arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 2) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_118_setting_title_17");
                    if (settingUpdateEntity4 != null) {
                        arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 1) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_light_assist");
                    if (settingUpdateEntity5 != null) {
                        arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(i & 1)));
                    }
                    this.this$0.updateGeneralSettingData(this.list);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(67, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$21
            private boolean isDriverSeatStatus;
            private boolean isPassengerSeatStatus;
            private boolean isPopup;
            private final ArrayList<SettingUpdateEntity<Object>> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x43】座椅按摩");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingUpdateEntity settingUpdateEntity;
                SettingUpdateEntity settingUpdateEntity2;
                boolean z = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
                boolean z2 = ((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1;
                if (this.isDriverSeatStatus != z || !this.isPopup) {
                    this.isDriverSeatStatus = z;
                    if (z && (settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_161_driver_massage_switch")) != null) {
                        this.this$0.startSettingActivity(context, settingUpdateEntity.getLeftListIndex(), settingUpdateEntity.getRightListIndex());
                        this.isPopup = true;
                    }
                }
                if (this.isPassengerSeatStatus != z2 || !this.isPopup) {
                    this.isPassengerSeatStatus = z2;
                    if (z2 && (settingUpdateEntity2 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_161_passenger_massage_switch")) != null) {
                        this.this$0.startSettingActivity(context, settingUpdateEntity2.getLeftListIndex(), settingUpdateEntity2.getRightListIndex());
                        this.isPopup = true;
                    }
                }
                if (!this.isDriverSeatStatus && !this.isPassengerSeatStatus && SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(SettingActivity.class).getQualifiedName()) && this.isPopup) {
                    this.this$0.finishActivity();
                    this.isPopup = false;
                }
                if (isDataChange()) {
                    ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
                    MsgMgr msgMgr = this.this$0;
                    arrayList.clear();
                    int i = msgMgr.mCanbusInfoInt[2];
                    SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_driver_massage_switch");
                    if (settingUpdateEntity3 != null) {
                        arrayList.add(settingUpdateEntity3.setValue(Integer.valueOf((i >> 6) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_driver_massage_intensity");
                    if (settingUpdateEntity4 != null) {
                        arrayList.add(settingUpdateEntity4.setValue(Integer.valueOf((i >> 4) & 3)));
                    }
                    SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_driver_massage_mode");
                    if (settingUpdateEntity5 != null) {
                        arrayList.add(settingUpdateEntity5.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(msgMgr.mCanbusInfoInt[2], 0, 4) - 1)));
                    }
                    int i2 = msgMgr.mCanbusInfoInt[3];
                    SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_passenger_massage_switch");
                    if (settingUpdateEntity6 != null) {
                        arrayList.add(settingUpdateEntity6.setValue(Integer.valueOf((i2 >> 6) & 1)));
                    }
                    SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_passenger_massage_intensity");
                    if (settingUpdateEntity7 != null) {
                        arrayList.add(settingUpdateEntity7.setValue(Integer.valueOf((i2 >> 4) & 3)));
                    }
                    SettingUpdateEntity settingUpdateEntity8 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_161_passenger_massage_mode");
                    if (settingUpdateEntity8 != null) {
                        arrayList.add(settingUpdateEntity8.setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(msgMgr.mCanbusInfoInt[3], 0, 4) - 1)));
                    }
                    this.this$0.updateGeneralSettingData(this.list);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(80, new MsgMgr$initParsers$1$22(this, context));
        sparseArray.append(81, new MsgMgr$initParsers$1$23(this));
        sparseArray.append(82, new MsgMgr$initParsers$1$24(this, context));
        sparseArray.append(83, new MsgMgr$initParsers$1$25(this, context));
        sparseArray.append(96, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$26
            {
                super(this.this$0, "【0x60】行车记录仪信息");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                String str;
                StringBuilder sbAppend = new StringBuilder().append("parse: 记录仪状态 ");
                int i = (this.this$0.mCanbusInfoInt[2] >> 7) & 1;
                String str2 = "";
                Log.i("_1161_MsgMgr", sbAppend.append(i != 0 ? i != 1 ? "" : "激活" : "未激活").toString());
                StringBuilder sbAppend2 = new StringBuilder().append("parse: 行车录像 ");
                int i2 = (this.this$0.mCanbusInfoInt[2] >> 6) & 1;
                String str3 = "ON";
                Log.i("_1161_MsgMgr", sbAppend2.append(i2 != 0 ? i2 != 1 ? "" : "ON" : "OFF").toString());
                StringBuilder sbAppend3 = new StringBuilder().append("parse: 自动紧急录像 ");
                int i3 = (this.this$0.mCanbusInfoInt[2] >> 5) & 1;
                if (i3 == 0) {
                    str3 = "OFF";
                } else if (i3 != 1) {
                    str3 = "";
                }
                Log.i("_1161_MsgMgr", sbAppend3.append(str3).toString());
                StringBuilder sbAppend4 = new StringBuilder().append("parse: 保存状态 ");
                int i4 = this.this$0.mCanbusInfoInt[2] & 3;
                Log.i("_1161_MsgMgr", sbAppend4.append(i4 != 0 ? i4 != 1 ? i4 != 2 ? i4 != 3 ? "" : "保存失败" : "保存成功" : "保存中" : "正常").toString());
                StringBuilder sbAppend5 = new StringBuilder().append("parse: 录像播放状态 ");
                switch (this.this$0.mCanbusInfoInt[3]) {
                    case 0:
                        str = "未播放";
                        break;
                    case 1:
                        str = "播放";
                        break;
                    case 2:
                        str = "暂停";
                        break;
                    case 3:
                        str = "停止";
                        break;
                    case 4:
                        str = "快退";
                        break;
                    case 5:
                        str = "快进";
                        break;
                    case 6:
                        str = "上一个录像";
                        break;
                    case 7:
                        str = "下一个录像";
                        break;
                    default:
                        str = "";
                        break;
                }
                Log.i("_1161_MsgMgr", sbAppend5.append(str).toString());
                StringBuilder sbAppend6 = new StringBuilder().append("parse: 记录仪状态2 ");
                int i5 = this.this$0.mCanbusInfoInt[4];
                if (i5 == 0) {
                    str2 = "正常";
                } else if (i5 == 1) {
                    str2 = "SD卡格式异常或损坏";
                } else if (i5 == 2) {
                    str2 = "行车录影功能不可用";
                } else if (i5 == 3) {
                    str2 = "请插入SD卡并关闭卡门";
                }
                Log.i("_1161_MsgMgr", sbAppend6.append(str2).toString());
                Log.i("_1161_MsgMgr", "parse: SD卡内存占比 " + this.this$0.mCanbusInfoInt[5] + '%');
            }
        });
        sparseArray.append(97, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$27
            {
                super(this.this$0, "【0x61】行车记录仪列表");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                Log.i("_1161_MsgMgr", "parse: 列表号 " + this.this$0.mCanbusInfoInt[2]);
                StringBuilder sbAppend = new StringBuilder().append("parse: 字符格式 ");
                int i = this.this$0.mCanbusInfoInt[3];
                Log.i("_1161_MsgMgr", sbAppend.append(i != 0 ? i != 1 ? "" : "ASKII" : "Unicode").toString());
                Log.i("_1161_MsgMgr", "parse: 总页码 " + ((this.this$0.mCanbusInfoInt[2] << 8) & this.this$0.mCanbusInfoInt[3]));
                Log.i("_1161_MsgMgr", "parse: 当前页码 " + ((this.this$0.mCanbusInfoInt[4] << 8) & this.this$0.mCanbusInfoInt[5]));
                StringBuilder sbAppend2 = new StringBuilder().append("parse: 字符 {");
                byte[] bArrCopyOfRange = Arrays.copyOfRange(this.this$0.mCanbusInfoByte, 8, this.this$0.mCanbusInfoByte.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOfRange, "copyOfRange(mCanbusInfoB… 8, mCanbusInfoByte.size)");
                Log.i("_1161_MsgMgr", sbAppend2.append(new String(bArrCopyOfRange, Charsets.UTF_8)).append('}').toString());
            }
        });
        sparseArray.append(WorkQueueKt.MASK, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$28
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x7F】版本信息");
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        sparseArray.append(HotKeyConstant.K_TTM, new Parser() { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$29
            private final int[] amplifierData;
            private final ArrayList<SettingUpdateEntity<Object>> list;
            private final int[] settingsData;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0xC5】功放信息（仅供UI调用）");
                this.list = new ArrayList<>();
                this.amplifierData = new int[3];
                this.settingsData = new int[2];
            }

            @Override // com.hzbhd.canbus.car._161.MsgMgr.Parser
            public void parse(int dataLength) {
                int i;
                int[] iArr = this.this$0.mCanbusInfoInt;
                MsgMgr msgMgr = this.this$0;
                Context context2 = context;
                if (Arrays.equals(ArraysKt.copyOfRange(iArr, 4, 7), this.amplifierData)) {
                    i = 9;
                } else {
                    i = 9;
                    ArraysKt.copyInto$default(iArr, this.amplifierData, 0, 4, 7, 2, (Object) null);
                    GeneralAmplifierData.frontRear = iArr[2] - 9;
                    GeneralAmplifierData.leftRight = iArr[3] - 9;
                    GeneralAmplifierData.bandBass = iArr[4];
                    GeneralAmplifierData.bandTreble = iArr[5];
                    GeneralAmplifierData.bandMiddle = iArr[6];
                    msgMgr.updateAmplifierActivity(null);
                    msgMgr.saveAmplifierData(context2, msgMgr.getCanId());
                }
                if (Arrays.equals(ArraysKt.copyOfRange(iArr, 7, i), this.settingsData)) {
                    return;
                }
                ArraysKt.copyInto$default(iArr, this.settingsData, 0, 7, 9, 2, (Object) null);
                this.list.clear();
                SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_143_0xAD_setting5");
                if (settingUpdateEntity != null) {
                    this.list.add(settingUpdateEntity.setValue(Integer.valueOf((msgMgr.mCanbusInfoInt[7] >> 7) & 1)));
                }
                SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_143_0xAD_setting6");
                if (settingUpdateEntity2 != null) {
                    this.list.add(settingUpdateEntity2.setValue(Integer.valueOf((msgMgr.mCanbusInfoInt[7] >> 6) & 1)));
                }
                SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_143_0xAD_setting7");
                if (settingUpdateEntity3 != null) {
                    this.list.add(settingUpdateEntity3.setValue(Integer.valueOf(msgMgr.mCanbusInfoInt[7] & 15)));
                }
                SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_324_centrik_speaker");
                if (settingUpdateEntity4 != null) {
                    this.list.add(settingUpdateEntity4.setValue(Integer.valueOf(((msgMgr.mCanbusInfoInt[8] >> 4) & 15) - 3)).setProgress((msgMgr.mCanbusInfoInt[8] >> 4) & 15));
                }
                SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_55_0xa6_setting_1");
                if (settingUpdateEntity5 != null) {
                    this.list.add(settingUpdateEntity5.setValue(Integer.valueOf((msgMgr.mCanbusInfoInt[8] & 15) - 3)).setProgress(msgMgr.mCanbusInfoInt[8] & 15));
                }
                msgMgr.updateGeneralSettingData(this.list);
                msgMgr.updateSettingActivity(null);
            }
        });
        sparseArray.append(HotKeyConstant.K_CARPLAY_SIRI, new Parser(this) { // from class: com.hzbhd.canbus.car._161.MsgMgr$initParsers$1$30
            {
                super(this, "【0xC6】源设置");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/car/_161/MsgMgr$Parser;", "", NotificationCompat.CATEGORY_MESSAGE, "", "(Lcom/hzbhd/canbus/car/_161/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    abstract class Parser {
        private final int PARSE_LISTENERS_LENGTH;
        private int[] canbusInfo;
        private final OnParseListener[] onParseListeners;
        final /* synthetic */ MsgMgr this$0;

        public Parser(MsgMgr msgMgr, String msg) {
            Intrinsics.checkNotNullParameter(msg, "msg");
            this.this$0 = msgMgr;
            OnParseListener[] onParseListeners = setOnParseListeners();
            this.onParseListeners = onParseListeners;
            int length = onParseListeners.length;
            this.PARSE_LISTENERS_LENGTH = length;
            Log.i(MsgMgr.TAG, "Parser: " + msg + " length " + length);
        }

        public final int[] getCanbusInfo() {
            return this.canbusInfo;
        }

        public final void setCanbusInfo(int[] iArr) {
            this.canbusInfo = iArr;
        }

        public void parse(int dataLength) {
            for (int iMin = Math.min(dataLength, this.PARSE_LISTENERS_LENGTH); iMin > 0; iMin--) {
                OnParseListener onParseListener = this.onParseListeners[iMin - 1];
                if (onParseListener != null) {
                    onParseListener.onParse();
                }
            }
        }

        public final boolean isDataChange() {
            if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
                return false;
            }
            int[] iArr = this.this$0.mCanbusInfoInt;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
            Intrinsics.checkNotNullExpressionValue(iArrCopyOf, "copyOf(this, size)");
            this.canbusInfo = iArrCopyOf;
            return true;
        }

        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[0];
        }
    }

    private final void initItemsIndexHashMap(Context context) {
        Log.i(TAG, "initItems: ");
        UiMgr uiMgr = this.mUiMgr;
        if (uiMgr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
            uiMgr = null;
        }
        Iterator<SettingPageUiSet.ListBean> it = uiMgr.getSettingUiSet(context).getList().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it.next().getItemList()) {
                HashMap<String, SettingUpdateEntity<Object>> map = this.mSettingItemIndexHashMap;
                String titleSrn = itemListBean.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "itemListBean.titleSrn");
                map.put(titleSrn, new SettingUpdateEntity<>(i, i3));
                i3++;
            }
            i = i2;
        }
        Iterator<DriverDataPageUiSet.Page> it2 = uiMgr.getDriverDataPageUiSet(context).getList().iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            int i5 = i4 + 1;
            int i6 = 0;
            for (DriverDataPageUiSet.Page.Item item : it2.next().getItemList()) {
                HashMap<String, DriverUpdateEntity> map2 = this.mDriveItemIndexHashMap;
                String titleSrn2 = item.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn2, "item.titleSrn");
                map2.put(titleSrn2, new DriverUpdateEntity(i4, i6, "null_value"));
                i6++;
            }
            i4 = i5;
        }
    }

    public final void updateSettingActivity(String title, Object value) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        SettingUpdateEntity<Object> settingUpdateEntity = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(settingUpdateEntity.setValue(value).setProgress(((Integer) value).intValue()));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        Intrinsics.checkNotNullParameter(currBand, "currBand");
        Intrinsics.checkNotNullParameter(currentFreq, "currentFreq");
        Intrinsics.checkNotNullParameter(psName, "psName");
        Pair pair = StringsKt.contains$default((CharSequence) currBand, (CharSequence) "FM", false, 2, (Object) null) ? new Pair(0, Integer.valueOf((int) (Float.parseFloat(currentFreq) * 100))) : new Pair(16, Integer.valueOf(Integer.parseInt(currentFreq)));
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, (byte) ((Number) pair.getFirst()).intValue(), (byte) ((((Number) pair.getSecond()).intValue() >> 8) & 255), (byte) (((Number) pair.getSecond()).intValue() & 255), (byte) currClickPresetIndex});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        super.sourceSwitchChange(source);
        if (source == null) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 0, 0});
        } else if (Intrinsics.areEqual(source, "RADIO")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 1, 0});
        } else if (Intrinsics.areEqual(source, "THIRD")) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, 4, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        Intrinsics.checkNotNullParameter(song, "song");
        Intrinsics.checkNotNullParameter(album, "album");
        Intrinsics.checkNotNullParameter(artist, "artist");
        ArrayList<byte[]> arrayList = new ArrayList<>();
        if (discType == 1 || discType == 5) {
            position = currentPlayNo;
        }
        if (this.mDiscCurrent != position || this.mDiscTotal != currentTotalNo) {
            this.mDiscCurrent = position;
            this.mDiscTotal = currentTotalNo;
            arrayList.add(new byte[]{22, -64, 2, 1, (byte) ((currentTotalNo >> 8) & 255), (byte) (currentTotalNo & 255), (byte) ((position >> 8) & 255), (byte) (position & 255)});
        }
        if (!Intrinsics.areEqual(this.mDiscTitle, song)) {
            this.mDiscTitle = song;
            arrayList.add(new byte[]{22, -64, 2, -1});
            byte[] bytes = song.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            arrayList.add(ArraysKt.plus(new byte[]{22, -61, 3}, bytes));
        }
        sendCommands(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, -1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(new byte[]{22, -64, 5, 1, 0});
        arrayList.add(new byte[]{22, -64, 5, -1});
        arrayList.add(ArraysKt.plus(new byte[]{22, -61, 3}, phoneNumber));
        sendCommands(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(new byte[]{22, -64, 5, 1, 0});
        arrayList.add(new byte[]{22, -64, 5, -1});
        arrayList.add(ArraysKt.plus(new byte[]{22, -61, 3}, phoneNumber));
        sendCommands(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Intrinsics.checkNotNullParameter(songTitle, "songTitle");
        Intrinsics.checkNotNullParameter(songAlbum, "songAlbum");
        Intrinsics.checkNotNullParameter(songArtist, "songArtist");
        if (stoagePath != 9) {
            ArrayList<byte[]> arrayList = new ArrayList<>();
            arrayList.add(new byte[]{22, -64, 8, 1, (byte) ((totalCount >> 8) & 255), (byte) (totalCount & 255), currentPlayingIndexHigh, (byte) currentPlayingIndexLow});
            arrayList.add(new byte[]{22, -64, 8, -1});
            byte[] bytes = songTitle.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            arrayList.add(ArraysKt.plus(new byte[]{22, -61, 3}, bytes));
            sendCommands(arrayList);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 1, (byte) ((totalCount >> 8) & 255), (byte) (totalCount & 255), currentPlayingIndexHigh, (byte) currentPlayingIndexLow});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(album, "album");
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(new byte[]{22, -64, 11, -1});
        byte[] bytes = title.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        arrayList.add(ArraysKt.plus(new byte[]{22, -61, 3}, bytes));
        sendCommands(arrayList);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -60;
        bArr[2] = (byte) (volValue | (isMute ? 128 : 0));
        CanbusMsgSender.sendMsg(bArr);
    }

    private final void sendCommands(final ArrayList<byte[]> arrayList) {
        int size = arrayList.size();
        if (size != 0) {
            if (size == 1) {
                CanbusMsgSender.sendMsg((byte[]) CollectionsKt.first((List) arrayList));
            } else {
                final TimerUtil timerUtil = new TimerUtil();
                timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._161.MsgMgr$sendCommands$1$1
                    private int i;

                    public final int getI() {
                        return this.i;
                    }

                    public final void setI(int i) {
                        this.i = i;
                    }

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (this.i < arrayList.size()) {
                            CanbusMsgSender.sendMsg(arrayList.get(this.i));
                            this.i++;
                        } else {
                            timerUtil.stopTimer();
                        }
                    }
                }, 0L, 100L);
            }
        }
    }

    public final void updateSettings(int leftListIndex, int rightListIndex, int value) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(leftListIndex, rightListIndex, Integer.valueOf(value)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void Jump() {
        Integer numValueOf;
        Intent intent = new Intent();
        Context context = this.mContext;
        Context context2 = null;
        if (context == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context = null;
        }
        intent.setClass(context, SettingActivity.class);
        intent.addFlags(268435456);
        intent.setAction(Constant.SETTING_OPEN_TARGET_PAGE);
        Context context3 = this.mContext;
        if (context3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
            context3 = null;
        }
        UiMgr uiMgr = getUiMgr(context3);
        if (uiMgr != null) {
            Context context4 = this.mContext;
            if (context4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mContext");
                context4 = null;
            }
            numValueOf = Integer.valueOf(uiMgr.getSettingLeftIndexes(context4, "_161_hybird_Setting"));
        } else {
            numValueOf = null;
        }
        intent.putExtra(Constant.LEFT_INDEX, numValueOf);
        Context context5 = this.mContext;
        if (context5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mContext");
        } else {
            context2 = context5;
        }
        context2.startActivity(intent);
    }

    private final UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
            Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._161.UiMgr");
            this.uiMgr = (UiMgr) canUiMgr;
        }
        return this.uiMgr;
    }

    public final Activity getCurrentActivity() {
        return getActivity();
    }
}
