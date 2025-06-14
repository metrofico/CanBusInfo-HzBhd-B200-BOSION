package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._206.MqbHybirdActivity;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u0011\n\u0002\b\u0012\u0018\u0000 \u008c\u00012\u00020\u0001:\b\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0016J\b\u0010,\u001a\u00020)H\u0016J\b\u0010-\u001a\u00020)H\u0016J \u0010.\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u001cH\u0016JT\u00102\u001a\u00020)2\u0006\u00103\u001a\u00020\u00162\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00105\u001a\u0002062\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u00162\b\u0010<\u001a\u0004\u0018\u00010=H\u0016J*\u0010>\u001a\u00020)2\b\u00104\u001a\u0004\u0018\u00010\u00062\u0006\u00108\u001a\u0002062\u0006\u00109\u001a\u0002062\u0006\u0010?\u001a\u00020\u0016H\u0016J\u001a\u0010@\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010A\u001a\u00020\u0006H\u0016J\u0018\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020\u00162\u0006\u0010D\u001a\u000206H\u0016Jp\u0010E\u001a\u00020)2\u0006\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020\u00162\u0006\u0010K\u001a\u00020\u00162\u0006\u0010L\u001a\u00020\u00162\u0006\u0010M\u001a\u00020\u00162\u0006\u0010N\u001a\u00020\u00162\u0006\u0010O\u001a\u0002062\u0006\u0010P\u001a\u0002062\u0006\u0010Q\u001a\u0002062\u0006\u0010R\u001a\u00020\u0016H\u0016J\b\u0010S\u001a\u00020)H\u0016J\u0012\u0010T\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0012\u0010U\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010V\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0002J\u0010\u0010W\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010X\u001a\u000206H\u0002J\u0014\u0010Y\u001a\u00020\u00162\n\u0010Z\u001a\u00020\u000e\"\u00020\u0016H\u0002JÄ\u0001\u0010[\u001a\u00020)2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020\u00162\u0006\u0010`\u001a\u00020\u00162\u0006\u0010a\u001a\u00020]2\u0006\u0010b\u001a\u00020]2\u0006\u0010c\u001a\u00020]2\u0006\u0010d\u001a\u00020]2\u0006\u0010e\u001a\u00020]2\u0006\u0010f\u001a\u00020]2\b\u0010g\u001a\u0004\u0018\u00010\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u001c2\b\u0010i\u001a\u0004\u0018\u00010\u001c2\u0006\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020]2\u0006\u0010m\u001a\u00020\u00162\u0006\u0010n\u001a\u0002062\u0006\u0010o\u001a\u00020k2\b\u0010p\u001a\u0004\u0018\u00010\u001c2\b\u0010q\u001a\u0004\u0018\u00010\u001c2\b\u0010r\u001a\u0004\u0018\u00010\u001c2\u0006\u0010s\u001a\u000206H\u0016J0\u0010t\u001a\u00020)2\u0006\u0010u\u001a\u00020\u00162\u0006\u0010v\u001a\u00020\u001c2\u0006\u0010w\u001a\u00020\u001c2\u0006\u0010x\u001a\u00020\u001c2\u0006\u0010y\u001a\u00020\u0016H\u0016J\u0010\u0010z\u001a\u00020)2\u0006\u0010{\u001a\u000206H\u0016J!\u0010|\u001a\u00020)2\u0012\u0010}\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060~\"\u00020\u0006H\u0002¢\u0006\u0002\u0010\u007fJ\u001d\u0010\u0080\u0001\u001a\u00020)2\u0007\u0010\u0081\u0001\u001a\u00020\u00162\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u001cH\u0002J\u0012\u0010\u0083\u0001\u001a\u00020)2\u0007\u0010\u0084\u0001\u001a\u000206H\u0016J\u0018\u0010\u0085\u0001\u001a\u00020)2\u0006\u0010/\u001a\u00020\u001c2\u0007\u0010\u0086\u0001\u001a\u00020%J\u009b\u0001\u0010\u0087\u0001\u001a\u00020)2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020]2\u0006\u0010_\u001a\u00020\u00162\u0006\u0010`\u001a\u00020\u00162\u0006\u0010a\u001a\u00020]2\u0006\u0010b\u001a\u00020]2\u0006\u0010c\u001a\u00020]2\b\u0010d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010e\u001a\u00020]2\u0006\u0010f\u001a\u00020]2\b\u0010g\u001a\u0004\u0018\u00010\u001c2\b\u0010h\u001a\u0004\u0018\u00010\u001c2\b\u0010i\u001a\u0004\u0018\u00010\u001c2\u0006\u0010j\u001a\u00020\u00162\u0007\u0010\u0088\u0001\u001a\u00020]2\u0006\u0010n\u001a\u0002062\u0007\u0010\u0089\u0001\u001a\u00020\u0016H\u0016J\u0016\u0010\u008a\u0001\u001a\u00020\u001c*\u00020\u001c2\u0007\u0010\u008b\u0001\u001a\u00020\u0016H\u0002R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0015\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010 \u001a\u0012\u0012\u0004\u0012\u00020\u0016\u0012\b\u0012\u00060\"R\u00020\u00000!X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010#\u001a\u0014\u0012\u0004\u0012\u00020\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u00020%0$0\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0090\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "m0x70Sender", "Lcom/hzbhd/canbus/car/_3/MsgMgr$M0x70Sender;", "m0xA6Command", "", "m0xC0Command", "m0xC1Command", "m0xC4Command", "m0xC5Command", "m0xC7Command", "m0xCACommand", "mAirData", "", "getMAirData", "()[I", "setMAirData", "([I)V", "mCanbusInfoByte", "mCanbusInfoInt", "<set-?>", "", "mColour", "getMColour", "()I", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserMap", "", "Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_3/UiMgr;", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "atvInfoChange", "auxInInfoChange", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "btPhoneTalkingWithTimeInfoChange", "time", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "dtvInfoChange", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "isAirDataChange", "mergeValue", "values", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "reverseStateChange", "isReverse", "sendMultiCommand", "commands", "", "([[B)V", "sendTextInfo", "dataType", "text", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettings", "value", "videoInfoChange", "playMode", "duration", "getInfo", "len", "Companion", "M0x70Sender", "Parser", "SpeedAlertHelper", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_BALANCE_DATA_HALF = 9;
    public static final float MILE_TO_KILO_RATE = 1.609344f;
    private static final String TAG = "_3_MsgMgr";
    private final byte[] m0xC7Command;
    private final byte[] m0xCACommand;
    private int[] mAirData;
    private UiMgr mUiMgr;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArrayList<Integer> languageList = CollectionsKt.arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 22, 23, 26, 29, 30, 31, 32, 33, 37, 38, 39, 40, 41, 42, 43);
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private final Map<Integer, Parser> mParserMap = new LinkedHashMap();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private int mColour = 1;
    private final byte[] m0xA6Command = {22, -90, 0, 0, 0, 0, 0, 0, 1};
    private final M0x70Sender m0x70Sender = new M0x70Sender();
    private final byte[] m0xC0Command = {22, -64, 0, 0, 0, 0, 0, 0, 0, 0};
    private final byte[] m0xC1Command = {22, -63, 0, 0};
    private final byte[] m0xC4Command = {22, -60, 0};
    private final byte[] m0xC5Command = {22, -59, 0, 0};

    public MsgMgr() {
        byte[] bArr = new byte[15];
        int i = 0;
        while (i < 15) {
            bArr[i] = i != 0 ? i != 1 ? i != 2 ? i != 3 ? (byte) 0 : (byte) 1 : (byte) 3 : (byte) -54 : (byte) 22;
            i++;
        }
        this.m0xCACommand = bArr;
        this.m0xC7Command = new byte[]{22, -57, 0, 0, 0};
        this.mAirData = new int[0];
    }

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R!\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00040\nj\b\u0012\u0004\u0012\u00020\u0004`\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$Companion;", "", "()V", "AMPLIFIER_BALANCE_DATA_HALF", "", "MILE_TO_KILO_RATE", "", "TAG", "", "languageList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLanguageList", "()Ljava/util/ArrayList;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ArrayList<Integer> getLanguageList() {
            return MsgMgr.languageList;
        }
    }

    public final int getMColour() {
        return this.mColour;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.afterServiceNormalSetting(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._3.UiMgr");
        this.mUiMgr = (UiMgr) canUiMgr;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mDisableData2 = 255;
        initItemsIndexHashMap(context);
        initParsers(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        Log.i(TAG, "initCommand: ");
        initAmplifier(context);
    }

    private final void initAmplifier(Context context) {
        Log.i(TAG, "initAmplifier: context[" + context + ']');
        if (context != null) {
            int canId = getCanId();
            UiMgr uiMgr = this.mUiMgr;
            if (uiMgr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
                uiMgr = null;
            }
            getAmplifierData(context, canId, uiMgr.getAmplifierPageUiSet(context));
        }
        final Iterator it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -127, 1}, this.m0xC4Command, new byte[]{22, -88, 0, (byte) GeneralAmplifierData.volume}, new byte[]{22, -88, 1, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -88, 2, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -88, 3, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -88, 4, (byte) (GeneralAmplifierData.frontRear + 9)}, new byte[]{22, -88, 5, (byte) (GeneralAmplifierData.leftRight + 9)}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initAmplifier$2$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (it.hasNext()) {
                    CanbusMsgSender.sendMsg(it.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        Log.i(TAG, "sourceSwitchNoMediaInfoChange: isPowerOff[" + isPowerOff + ']');
        if (isPowerOff) {
            return;
        }
        initAmplifier(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean isReverse) {
        super.reverseStateChange(isReverse);
        if (isReverse) {
            CycleRequest.getInstance().start(100, new ActionCallback() { // from class: com.hzbhd.canbus.car._3.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car_cus._451.Interface.ActionCallback
                public final void toDo(Object obj) {
                    MsgMgr.m363reverseStateChange$lambda3(obj);
                }
            });
        } else {
            CycleRequest.getInstance().stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseStateChange$lambda-3, reason: not valid java name */
    public static final void m363reverseStateChange$lambda3(Object obj) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 41});
        CycleRequest.getInstance().reset(100);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        try {
            Parser parser = this.mParserMap.get(Integer.valueOf(byteArrayToIntArray[1]));
            if (parser != null) {
                parser.parse(this.mCanbusInfoInt.length - 2);
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private final void initParsers(final Context context) {
        Map<Integer, Parser> map = this.mParserMap;
        map.put(32, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$1
            private int index;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x20】方向盘按键");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                switch (this.this$0.mCanbusInfoInt[2]) {
                    case 0:
                        realKeyLongClick1(0);
                        break;
                    case 1:
                        realKeyLongClick1(7);
                        break;
                    case 2:
                        realKeyLongClick1(8);
                        break;
                    case 3:
                        realKeyLongClick1(20);
                        break;
                    case 4:
                        realKeyLongClick1(21);
                        break;
                    case 5:
                        realKeyLongClick1(HotKeyConstant.K_PHONE_ON_OFF);
                        break;
                    case 6:
                        realKeyLongClick1(3);
                        break;
                    case 7:
                        realKeyLongClick1(2);
                        break;
                    case 8:
                        realKeyLongClick1(HotKeyConstant.K_SPEECH);
                        break;
                }
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }

            private final void compoundKey(int... keys) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.compoundKey(context, keys, msgMgr.mCanbusInfoInt[3]);
            }
        });
        map.put(33, new MsgMgr$initParsers$1$2(this, context));
        map.put(47, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$3
            private int preFast;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x2F】方向盘指令");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i == 1) {
                    realKeyClick4(45);
                }
                if (i == 2) {
                    realKeyClick4(46);
                    return;
                }
                if (i == 3) {
                    this.preFast = 22;
                    realKeyClick4(22);
                    return;
                }
                if (i == 4) {
                    this.preFast = 23;
                    realKeyClick4(23);
                    return;
                }
                if (i == 5) {
                    realKeyClick4(this.preFast);
                    return;
                }
                switch (i) {
                    case 17:
                        realKeyClick4(14);
                        break;
                    case 18:
                        realKeyClick4(15);
                        break;
                    case 19:
                        realKeyClick4(137);
                        break;
                    case 20:
                        realKeyClick4(0);
                        break;
                    case 21:
                        realKeyClick4(0);
                        break;
                    case 22:
                        realKeyClick4(135);
                        break;
                    case 23:
                        realKeyClick4(136);
                        break;
                    case 24:
                        realKeyClick4(137);
                        break;
                    case 25:
                        realKeyClick4(138);
                        break;
                }
            }

            private final void realKeyClick4(int key) {
                this.this$0.realKeyClick(context, key);
            }
        });
        map.put(34, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x22】后雷达信息");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.this$0.mCanbusInfoInt[6] = 0;
                this.this$0.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setRearRadarLocationData(10, MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[2], 60), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[3], 165), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[4], 165), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[5], 60));
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        map.put(35, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x23】前雷达信息");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.this$0.mCanbusInfoInt[6] = 0;
                this.this$0.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setFrontRadarLocationData(10, MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[2], 60), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[3], 120), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[4], 120), MsgMgr.m362initParsers$lambda4$getRadarValue(this.this$0.mCanbusInfoInt[5], 60));
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        map.put(36, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x24】基本信息");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.this$0.mCanbusInfoInt[2] = this.this$0.mCanbusInfoInt[2] & 252;
                this.this$0.mCanbusInfoInt[3] = 0;
                if (isDataChange()) {
                    GeneralDoorData.isRightFrontDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 7) & 1) == 1;
                    GeneralDoorData.isLeftFrontDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 6) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 5) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((this.this$0.mCanbusInfoInt[2] >> 4) & 1) == 1;
                    GeneralDoorData.isBackOpen = ((this.this$0.mCanbusInfoInt[2] >> 3) & 1) == 1;
                    GeneralDoorData.isFrontOpen = ((this.this$0.mCanbusInfoInt[2] >> 2) & 1) == 1;
                    this.this$0.updateDoorView(context);
                }
            }
        });
        map.put(37, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$7
            private final ArrayList<DriverUpdateEntity> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x25】泊车辅助状态");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_41_rear_radar");
                    if (driverUpdateEntity != null) {
                        this.list.add(driverUpdateEntity.setValue(toSwitch((this.this$0.mCanbusInfoInt[2] >> 3) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_41_front_radar");
                    if (driverUpdateEntity2 != null) {
                        this.list.add(driverUpdateEntity2.setValue(toSwitch((this.this$0.mCanbusInfoInt[2] >> 2) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_41_park_assist");
                    if (driverUpdateEntity3 != null) {
                        this.list.add(driverUpdateEntity3.setValue(toSwitch((this.this$0.mCanbusInfoInt[2] >> 1) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_41_radar_sound");
                    if (driverUpdateEntity4 != null) {
                        this.list.add(driverUpdateEntity4.setValue(toSwitch(this.this$0.mCanbusInfoInt[2] & 1)));
                    }
                    this.this$0.updateGeneralDriveData(this.list);
                    this.this$0.updateDriveDataActivity(null);
                    GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.this$0.mCanbusInfoInt[2]);
                    this.this$0.updatePKeyRadar();
                }
            }

            private final String toSwitch(int i) {
                return CommUtil.getStrByResId(context, Integer.valueOf(i).equals(1) ? "_103_car_setting_value_7_1" : "_103_car_setting_value_7_0");
            }
        });
        map.put(39, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$8
            private String outdoorTemperature;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x27】环境温度信息");
                this.outdoorTemperature = "";
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                short s = (short) ((this.this$0.mCanbusInfoInt[4] << 8) | this.this$0.mCanbusInfoInt[3]);
                String str = (s / 10) + ' ' + ((this.this$0.mCanbusInfoInt[2] & 1) == 1 ? "℉" : "℃");
                Log.i("_3_MsgMgr", "parse: value[" + ((int) s) + "] result[" + str + "] outdoorTemperature[" + this.outdoorTemperature + ']');
                if (Intrinsics.areEqual(this.outdoorTemperature, str)) {
                    return;
                }
                this.outdoorTemperature = str;
                this.this$0.updateOutDoorTemp(context, str);
            }
        });
        map.put(40, new MsgMgr$initParsers$1$9(this, context));
        map.put(41, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$10
            {
                super(this.this$0, "【0x29】方向盘转角");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 19918, 16);
                    this.this$0.updateTrack();
                }
            }
        });
        map.put(42, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$11
            private final ArrayList<DriverUpdateEntity> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x2A】PM2.5");
                this.list = new ArrayList<>();
            }

            /* JADX WARN: Removed duplicated region for block: B:52:0x009a  */
            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void parse(int r6) {
                /*
                    r5 = this;
                    boolean r6 = r5.isDataChange()
                    if (r6 == 0) goto Lc3
                    java.util.ArrayList<com.hzbhd.canbus.entity.DriverUpdateEntity> r6 = r5.list
                    r6.clear()
                    com.hzbhd.canbus.car._3.MsgMgr r6 = r5.this$0
                    int[] r6 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r6)
                    r0 = 2
                    r6 = r6[r0]
                    int r6 = r6 << 8
                    com.hzbhd.canbus.car._3.MsgMgr r0 = r5.this$0
                    int[] r0 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r1 = 3
                    r0 = r0[r1]
                    r6 = r6 | r0
                    r0 = 36
                    r1 = 1
                    r2 = 0
                    if (r6 < 0) goto L2a
                    if (r6 >= r0) goto L2a
                    r3 = r1
                    goto L2b
                L2a:
                    r3 = r2
                L2b:
                    r4 = 0
                    if (r3 == 0) goto L32
                    java.lang.String r0 = "pm_excellent"
                    goto L78
                L32:
                    r3 = 76
                    if (r0 > r6) goto L3a
                    if (r6 >= r3) goto L3a
                    r0 = r1
                    goto L3b
                L3a:
                    r0 = r2
                L3b:
                    if (r0 == 0) goto L40
                    java.lang.String r0 = "pm_good"
                    goto L78
                L40:
                    r0 = 116(0x74, float:1.63E-43)
                    if (r3 > r6) goto L48
                    if (r6 >= r0) goto L48
                    r3 = r1
                    goto L49
                L48:
                    r3 = r2
                L49:
                    if (r3 == 0) goto L4e
                    java.lang.String r0 = "pm_mild_pollution"
                    goto L78
                L4e:
                    r3 = 151(0x97, float:2.12E-43)
                    if (r0 > r6) goto L56
                    if (r6 >= r3) goto L56
                    r0 = r1
                    goto L57
                L56:
                    r0 = r2
                L57:
                    if (r0 == 0) goto L5c
                    java.lang.String r0 = "pm_moderately_polluted"
                    goto L78
                L5c:
                    r0 = 251(0xfb, float:3.52E-43)
                    if (r3 > r6) goto L64
                    if (r6 >= r0) goto L64
                    r3 = r1
                    goto L65
                L64:
                    r3 = r2
                L65:
                    if (r3 == 0) goto L6a
                    java.lang.String r0 = "pm_heavy_pollution"
                    goto L78
                L6a:
                    if (r0 > r6) goto L71
                    r0 = 1001(0x3e9, float:1.403E-42)
                    if (r6 >= r0) goto L71
                    goto L72
                L71:
                    r1 = r2
                L72:
                    if (r1 == 0) goto L77
                    java.lang.String r0 = "pm_severe_pollution"
                    goto L78
                L77:
                    r0 = r4
                L78:
                    if (r0 == 0) goto L9a
                    android.content.Context r1 = r2
                    java.lang.StringBuilder r2 = new java.lang.StringBuilder
                    r2.<init>()
                    java.lang.StringBuilder r6 = r2.append(r6)
                    r2 = 32
                    java.lang.StringBuilder r6 = r6.append(r2)
                    java.lang.String r0 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r1, r0)
                    java.lang.StringBuilder r6 = r6.append(r0)
                    java.lang.String r6 = r6.toString()
                    if (r6 == 0) goto L9a
                    goto L9c
                L9a:
                    java.lang.String r6 = "---"
                L9c:
                    com.hzbhd.canbus.car._3.MsgMgr r0 = r5.this$0
                    java.util.HashMap r0 = com.hzbhd.canbus.car._3.MsgMgr.access$getMDriveItemIndexHashMap$p(r0)
                    java.lang.String r1 = "_3_2ah_d1"
                    java.lang.Object r0 = r0.get(r1)
                    com.hzbhd.canbus.entity.DriverUpdateEntity r0 = (com.hzbhd.canbus.entity.DriverUpdateEntity) r0
                    if (r0 == 0) goto Lb5
                    java.util.ArrayList<com.hzbhd.canbus.entity.DriverUpdateEntity> r1 = r5.list
                    com.hzbhd.canbus.entity.DriverUpdateEntity r6 = r0.setValue(r6)
                    r1.add(r6)
                Lb5:
                    com.hzbhd.canbus.car._3.MsgMgr r6 = r5.this$0
                    java.util.ArrayList<com.hzbhd.canbus.entity.DriverUpdateEntity> r0 = r5.list
                    java.util.List r0 = (java.util.List) r0
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateGeneralDriveData(r6, r0)
                    com.hzbhd.canbus.car._3.MsgMgr r6 = r5.this$0
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateDriveDataActivity(r6, r4)
                Lc3:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$11.parse(int):void");
            }
        });
        map.put(48, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x30】解码盒版本信息");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        map.put(50, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x32】左侧雷达数据");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.this$0.mCanbusInfoInt[6] = 0;
                this.this$0.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setLeftRadarLocationData(7, this.this$0.mCanbusInfoInt[2] + 1, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        map.put(51, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$14
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x33】右侧雷达数据");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.this$0.mCanbusInfoInt[6] = 0;
                this.this$0.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setRightRadarLocationData(7, this.this$0.mCanbusInfoInt[2] + 1, this.this$0.mCanbusInfoInt[3] + 1, this.this$0.mCanbusInfoInt[4] + 1, this.this$0.mCanbusInfoInt[5] + 1);
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        map.put(64, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$15
            private int driveModeButtonStatus;
            private int isShowDriveAssist;
            private int isShowDriveMode;
            private int isShowParkSetting;
            private final ArrayList<SettingUpdateEntity<Object>> list;
            private int m0x40Param4;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x40】车身状态信息");
                this.list = new ArrayList<>();
            }

            /* JADX WARN: Removed duplicated region for block: B:178:0x06a9  */
            /* JADX WARN: Removed duplicated region for block: B:221:0x08cb  */
            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void parse(int r17) {
                /*
                    Method dump skipped, instructions count: 6368
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$15.parse(int):void");
            }
        });
        map.put(65, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$16
            private final ArrayList<SettingUpdateEntity<Object>> list;

            {
                super(this.this$0, "【0x41】中控使能");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingUpdateEntity settingUpdateEntity;
                if (isDataChange()) {
                    this.list.clear();
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 16) {
                        SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_setting_0");
                        if (settingUpdateEntity2 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity2.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_40h_10h_p2_b7");
                        if (settingUpdateEntity3 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity3.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                    } else if (i == 32) {
                        SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("tpms_set");
                        if (settingUpdateEntity4 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity4.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p1_b0");
                        if (settingUpdateEntity5 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity5.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_40h_20h_p2");
                        if (settingUpdateEntity6 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity6.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                    } else if (i == 64) {
                        SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active_auto");
                        if (settingUpdateEntity7 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity7.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity8 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_volume");
                        if (settingUpdateEntity8 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity8.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity9 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_tone_setting");
                        if (settingUpdateEntity9 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity9.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity10 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_volume");
                        if (settingUpdateEntity10 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity10.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity11 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_tone_setting");
                        if (settingUpdateEntity11 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity11.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity12 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active");
                        if (settingUpdateEntity12 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity12.setEnable(((this.this$0.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                    } else if (i == 96) {
                        SettingUpdateEntity settingUpdateEntity13 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_settings_syncchronous_adjustment");
                        if (settingUpdateEntity13 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity13.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity14 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_settings_lower_while_reversing");
                        if (settingUpdateEntity14 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity14.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity15 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_auto_wiping");
                        if (settingUpdateEntity15 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity15.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity16 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping");
                        if (settingUpdateEntity16 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity16.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity17 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_40h_60h_p2_b0");
                        if (settingUpdateEntity17 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity17.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity18 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                        if (settingUpdateEntity18 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity18.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 112) {
                        SettingUpdateEntity settingUpdateEntity19 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_3_40h_70h_p1_b30");
                        if (settingUpdateEntity19 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity19.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity20 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_door_unlocking");
                        if (settingUpdateEntity20 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity20.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity21 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_auto_locking");
                        if (settingUpdateEntity21 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity21.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity22 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("inductive_trunk_lid");
                        if (settingUpdateEntity22 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity22.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                    } else if (i == 128) {
                        SettingUpdateEntity settingUpdateEntity23 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_current_consumption");
                        if (settingUpdateEntity23 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity23.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity24 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_consumption");
                        if (settingUpdateEntity24 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity24.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity25 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_con_consumers");
                        if (settingUpdateEntity25 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity25.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity26 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_eco_tips");
                        if (settingUpdateEntity26 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity26.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity27 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_travelling_time");
                        if (settingUpdateEntity27 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity27.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity28 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_distance_travelled");
                        if (settingUpdateEntity28 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity28.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity29 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
                        if (settingUpdateEntity29 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity29.setEnable(((this.this$0.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity30 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_digital_speed_display");
                        if (settingUpdateEntity30 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity30.setEnable(((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity31 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_speed_warning");
                        if (settingUpdateEntity31 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity31.setEnable((this.this$0.mCanbusInfoInt[4] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity32 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_oil_temperature");
                        if (settingUpdateEntity32 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity32.setEnable(((this.this$0.mCanbusInfoInt[4] >> 1) & 1) == 1)));
                        }
                    } else if (i == 144) {
                        SettingUpdateEntity settingUpdateEntity33 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_distance");
                        if (settingUpdateEntity33 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity33.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity34 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_speed");
                        if (settingUpdateEntity34 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity34.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity35 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_temperature");
                        if (settingUpdateEntity35 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity35.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity36 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_volume");
                        if (settingUpdateEntity36 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity36.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity37 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_consumption");
                        if (settingUpdateEntity37 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity37.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity38 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_pressure");
                        if (settingUpdateEntity38 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity38.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 48) {
                        SettingUpdateEntity settingUpdateEntity39 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_setting_2_0");
                        if (settingUpdateEntity39 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity39.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity40 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_6");
                        if (settingUpdateEntity40 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity40.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity41 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_lane_assisit");
                        if (settingUpdateEntity41 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity41.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                    } else if (i == 49) {
                        SettingUpdateEntity settingUpdateEntity42 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_last_distance_selected");
                        if (settingUpdateEntity42 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity42.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity43 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_1");
                        if (settingUpdateEntity43 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity43.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity44 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_2");
                        if (settingUpdateEntity44 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity44.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity45 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_pa_3");
                        if (settingUpdateEntity45 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity45.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity46 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_drive_program");
                        if (settingUpdateEntity46 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity46.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity47 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_settings_acc_distance");
                        if (settingUpdateEntity47 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity47.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 80) {
                        SettingUpdateEntity settingUpdateEntity48 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_switch_on_time");
                        if (settingUpdateEntity48 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity48.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity49 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_auto_control");
                        if (settingUpdateEntity49 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity49.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity50 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_lane_change_flash");
                        if (settingUpdateEntity50 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity50.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity51 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_ins_swi_lighting");
                        if (settingUpdateEntity51 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity51.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity52 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_coming_home");
                        if (settingUpdateEntity52 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity52.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity53 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_leaving_home");
                        if (settingUpdateEntity53 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity53.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity54 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_283_car_setting_light_4");
                        if (settingUpdateEntity54 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity54.setEnable(((this.this$0.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity55 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("headlight_illumination_distance_adjustment");
                        if (settingUpdateEntity55 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity55.setEnable(((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                    } else if (i == 81) {
                        SettingUpdateEntity settingUpdateEntity56 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_travel_mode");
                        if (settingUpdateEntity56 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity56.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity57 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_door_ambient_light");
                        if (settingUpdateEntity57 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity57.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity58 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_footwell_light");
                        if (settingUpdateEntity58 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity58.setEnable(((this.this$0.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity59 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_roof_light");
                        if (settingUpdateEntity59 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity59.setEnable(((this.this$0.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity60 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_front_light");
                        if (settingUpdateEntity60 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity60.setEnable(((this.this$0.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity61 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_all_area_light");
                        if (settingUpdateEntity61 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity61.setEnable(((this.this$0.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity62 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_assist");
                        if (settingUpdateEntity62 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity62.setEnable(((this.this$0.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity63 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_follow");
                        if (settingUpdateEntity63 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity63.setEnable(((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity64 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_11");
                        if (settingUpdateEntity64 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity64.setEnable((this.this$0.mCanbusInfoInt[4] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity65 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_12");
                        if (settingUpdateEntity65 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity65.setEnable(((this.this$0.mCanbusInfoInt[4] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity66 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_303_setting_content_13");
                        if (settingUpdateEntity66 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity66.setEnable(((this.this$0.mCanbusInfoInt[4] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity67 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_handle_box_light");
                        if (settingUpdateEntity67 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity67.setEnable(((this.this$0.mCanbusInfoInt[4] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity68 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("two_color_sync");
                        if (settingUpdateEntity68 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity68.setEnable(((this.this$0.mCanbusInfoInt[4] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity69 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("first_color");
                        if (settingUpdateEntity69 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity69.setEnable(((this.this$0.mCanbusInfoInt[4] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity70 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("second_color");
                        if (settingUpdateEntity70 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity70.setEnable(((this.this$0.mCanbusInfoInt[4] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity71 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("on_off_btn_txt_7");
                        if (settingUpdateEntity71 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity71.setEnable(((this.this$0.mCanbusInfoInt[4] >> 7) & 1) == 1)));
                        }
                    } else if (i == 176) {
                        SettingUpdateEntity settingUpdateEntity72 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("seat_remote_key_memory_matching");
                        if (settingUpdateEntity72 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity72.setEnable((this.this$0.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity73 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("driver_seat");
                        if (settingUpdateEntity73 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity73.setEnable(((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity74 = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("_2_setting_14_1");
                        if (settingUpdateEntity74 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity74.setEnable((this.this$0.mCanbusInfoInt[3] & 3) != 0)));
                        }
                    } else if (i == 177 && (settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("individual")) != null) {
                        Boolean.valueOf(this.list.add(settingUpdateEntity.setEnable(((this.this$0.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                    }
                    this.this$0.updateGeneralSettingData(this.list);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        map.put(80, new MsgMgr$initParsers$1$17(this, context));
        map.put(81, new MsgMgr$initParsers$1$18(this, context));
        map.put(82, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$19
            private final ArrayList<DriverUpdateEntity> list;
            private int mData45;

            {
                super(this.this$0, "【0x52】电子显示");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = (this.this$0.mCanbusInfoInt[6] << 8) | this.this$0.mCanbusInfoInt[7];
                MsgMgr msgMgr = this.this$0;
                if (this.mData45 != i) {
                    this.mData45 = i;
                    GeneralHybirdData.energyDirection = (msgMgr.mCanbusInfoInt[6] >> 6) & 3;
                    GeneralHybirdData.isShowMotor = ((msgMgr.mCanbusInfoInt[6] >> 5) & 1) == 1;
                    int i2 = (msgMgr.mCanbusInfoInt[6] >> 2) & 7;
                    GeneralHybirdData.wheelTrack = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? 0 : 4 : 2 : 1 : 3;
                    GeneralHybirdData.powerBatteryLevel = RangesKt.coerceAtMost((msgMgr.mCanbusInfoInt[7] / 10) + 1, 10);
                    GeneralHybirdData.powerBatteryValue = msgMgr.mCanbusInfoInt[7];
                    GeneralHybirdData.isShowBattery = true;
                    if (msgMgr.getActivity() instanceof MqbHybirdActivity) {
                        msgMgr.updateActivity(null);
                    }
                }
                this.this$0.mCanbusInfoInt[6] = 0;
                this.this$0.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("mqb_endurance_potential");
                    if (driverUpdateEntity != null) {
                        MsgMgr msgMgr2 = this.this$0;
                        ArrayList<DriverUpdateEntity> arrayList = this.list;
                        StringBuilder sb = new StringBuilder();
                        int iMergeValue = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[3], msgMgr2.mCanbusInfoInt[2]) / 10;
                        arrayList.add(driverUpdateEntity.setValue(sb.append(iMergeValue < 1 ? "< 1" : String.valueOf(iMergeValue)).append(" km").toString()));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("battery_life");
                    if (driverUpdateEntity2 != null) {
                        MsgMgr msgMgr3 = this.this$0;
                        this.list.add(driverUpdateEntity2.setValue((msgMgr3.mergeValue(msgMgr3.mCanbusInfoInt[5], msgMgr3.mCanbusInfoInt[4]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("driven_distance");
                    if (driverUpdateEntity3 != null) {
                        MsgMgr msgMgr4 = this.this$0;
                        this.list.add(driverUpdateEntity3.setValue((msgMgr4.mergeValue(msgMgr4.mCanbusInfoInt[9], msgMgr4.mCanbusInfoInt[8]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_hybrid_zero_emission");
                    if (driverUpdateEntity4 != null) {
                        MsgMgr msgMgr5 = this.this$0;
                        this.list.add(driverUpdateEntity4.setValue((msgMgr5.mergeValue(msgMgr5.mCanbusInfoInt[11], msgMgr5.mCanbusInfoInt[10]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity5 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("proportion");
                    if (driverUpdateEntity5 != null) {
                        this.list.add(driverUpdateEntity5.setValue(new StringBuilder().append(this.this$0.mCanbusInfoInt[12]).append('%').toString()));
                    }
                    this.this$0.updateGeneralDriveData(this.list);
                    this.this$0.updateDriveDataActivity(null);
                }
            }
        });
        map.put(83, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$20
            private final float barMaxValue;
            private final String fullBar;
            private final ArrayList<DriverUpdateEntity> list;

            {
                super(this.this$0, "【0x53】能量回收");
                this.list = new ArrayList<>();
                this.fullBar = "==============================================================";
                this.barMaxValue = 100.0f;
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i != 0) {
                        if (i == 16) {
                            for (int i2 = 1; i2 < 16; i2++) {
                                setEntity(String.valueOf(i2), (i2 * 2) + 1);
                            }
                        } else if (i == 17) {
                            for (int i3 = 16; i3 < 31; i3++) {
                                setEntity(String.valueOf(i3), ((i3 - 15) * 2) + 1);
                            }
                        }
                    } else {
                        setEntity("_3_53h_00", 3);
                    }
                    this.this$0.updateGeneralDriveData(this.list);
                    this.this$0.updateDriveDataActivity(null);
                }
            }

            private final void setEntity(String name, int valueIndex) {
                DriverUpdateEntity driverUpdateEntity;
                if (valueIndex + 1 < this.this$0.mCanbusInfoInt.length && (driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get(name)) != null) {
                    MsgMgr msgMgr = this.this$0;
                    int iMergeValue = (int) (msgMgr.mergeValue(msgMgr.mCanbusInfoInt[valueIndex], msgMgr.mCanbusInfoInt[r0]) / 10.0f);
                    this.list.add(driverUpdateEntity.setValue(iMergeValue == 0 ? "" : iMergeValue + " kWh"));
                }
            }
        });
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        map.put(96, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$21
            private final ArrayList<WarningEntity> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x60】车辆提示信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                String str;
                if (isDataChange()) {
                    arrayList.clear();
                    arrayList.add(new WarningEntity(CommUtil.getStrByResId(context, "_2_information_title_1")));
                    if (this.this$0.mCanbusInfoInt[2] != 0) {
                        int iCoerceAtMost = RangesKt.coerceAtMost(this.this$0.mCanbusInfoInt[2], 6);
                        for (int i = 0; i < iCoerceAtMost; i++) {
                            ArrayList<WarningEntity> arrayList3 = arrayList;
                            StringBuilder sbAppend = new StringBuilder().append("\t\t");
                            Context context2 = context;
                            switch (this.this$0.mCanbusInfoInt[i + 3]) {
                                case 0:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_0";
                                    break;
                                case 1:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_1";
                                    break;
                                case 2:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_2";
                                    break;
                                case 3:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_3";
                                    break;
                                case 4:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_4";
                                    break;
                                case 5:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_5";
                                    break;
                                case 6:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_6";
                                    break;
                                case 7:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_7";
                                    break;
                                case 8:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_8";
                                    break;
                                case 9:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_9";
                                    break;
                                case 10:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_10";
                                    break;
                                case 11:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_11";
                                    break;
                                case 12:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_12";
                                    break;
                                case 13:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_13";
                                    break;
                                case 14:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_14";
                                    break;
                                case 15:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_15";
                                    break;
                                case 16:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_16";
                                    break;
                                case 17:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_17";
                                    break;
                                case 18:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_18";
                                    break;
                                case 19:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_19";
                                    break;
                                case 20:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_20";
                                    break;
                                case 21:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_21";
                                    break;
                                case 22:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_22";
                                    break;
                                case 23:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_23";
                                    break;
                                case 24:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_24";
                                    break;
                                case 25:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_25";
                                    break;
                                case 26:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_26";
                                    break;
                                case 27:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_27";
                                    break;
                                case 28:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_28";
                                    break;
                                case 29:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_29";
                                    break;
                                case 30:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_30";
                                    break;
                                case 31:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_31";
                                    break;
                                case 32:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_32";
                                    break;
                                case 33:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_33";
                                    break;
                            }
                            arrayList3.add(new WarningEntity(sbAppend.append(CommUtil.getStrByResId(context2, str)).toString()));
                        }
                    } else {
                        arrayList.add(new WarningEntity("\t\t" + CommUtil.getStrByResId(context, (this.this$0.mCanbusInfoInt[9] & 1) == 1 ? "vm_golf7_vehicle_status_start_stop_0" : "_3_60h_d7_1")));
                    }
                    ArrayList<WarningEntity> arrayList4 = this.list;
                    ArrayList<WarningEntity> arrayList5 = arrayList;
                    ArrayList<WarningEntity> arrayList6 = arrayList2;
                    arrayList4.clear();
                    arrayList4.addAll(arrayList5);
                    arrayList4.addAll(arrayList6);
                    GeneralWarningDataData.dataList = arrayList4;
                    this.this$0.updateWarningActivity(null);
                }
            }
        });
        map.put(98, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$22
            private final ArrayList<WarningEntity> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x62】Conv. Consumers 信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                String str;
                arrayList2.clear();
                arrayList2.add(new WarningEntity(CommUtil.getStrByResId(context, "_2_information_title_4")));
                int iCoerceAtMost = RangesKt.coerceAtMost(this.this$0.mCanbusInfoInt[2], 3);
                for (int i = 0; i < iCoerceAtMost; i++) {
                    ArrayList<WarningEntity> arrayList3 = arrayList2;
                    StringBuilder sbAppend = new StringBuilder().append("\t\t");
                    Context context2 = context;
                    switch (this.this$0.mCanbusInfoInt[i + 3]) {
                        case 0:
                            str = "vm_golf7_Conv_consumers_prompt_0";
                            break;
                        case 1:
                            str = "vm_golf7_Conv_consumers_prompt_1";
                            break;
                        case 2:
                            str = "vm_golf7_Conv_consumers_prompt_2";
                            break;
                        case 3:
                            str = "vm_golf7_Conv_consumers_prompt_3";
                            break;
                        case 4:
                            str = "vm_golf7_Conv_consumers_prompt_4";
                            break;
                        case 5:
                            str = "vm_golf7_Conv_consumers_prompt_5";
                            break;
                        case 6:
                            str = "vm_golf7_Conv_consumers_prompt_6";
                            break;
                        case 7:
                            str = "vm_golf7_Conv_consumers_prompt_7";
                            break;
                        case 8:
                            str = "vm_golf7_Conv_consumers_prompt_8";
                            break;
                        case 9:
                            str = "vm_golf7_Conv_consumers_prompt_9";
                            break;
                        case 10:
                            str = "vm_golf7_Conv_consumers_prompt_10";
                            break;
                        case 11:
                            str = "vm_golf7_Conv_consumers_prompt_11";
                            break;
                        case 12:
                            str = "vm_golf7_Conv_consumers_prompt_12";
                            break;
                        case 13:
                            str = "vm_golf7_Conv_consumers_prompt_13";
                            break;
                        case 14:
                            str = "vm_golf7_Conv_consumers_prompt_14";
                            break;
                        case 15:
                            str = "vm_golf7_Conv_consumers_prompt_15";
                            break;
                        case 16:
                            str = "vm_golf7_Conv_consumers_prompt_16";
                            break;
                        case 17:
                            str = "vm_golf7_Conv_consumers_prompt_17";
                            break;
                        case 18:
                            str = "vm_golf7_Conv_consumers_prompt_18";
                            break;
                        default:
                            ArrayList<WarningEntity> arrayList4 = this.list;
                            ArrayList<WarningEntity> arrayList5 = arrayList;
                            ArrayList<WarningEntity> arrayList6 = arrayList2;
                            arrayList4.clear();
                            arrayList4.addAll(arrayList5);
                            arrayList4.addAll(arrayList6);
                            GeneralWarningDataData.dataList = arrayList4;
                            this.this$0.updateWarningActivity(null);
                    }
                    arrayList3.add(new WarningEntity(sbAppend.append(CommUtil.getStrByResId(context2, str)).toString()));
                }
                ArrayList<WarningEntity> arrayList42 = this.list;
                ArrayList<WarningEntity> arrayList52 = arrayList;
                ArrayList<WarningEntity> arrayList62 = arrayList2;
                arrayList42.clear();
                arrayList42.addAll(arrayList52);
                arrayList42.addAll(arrayList62);
                GeneralWarningDataData.dataList = arrayList42;
                this.this$0.updateWarningActivity(null);
            }
        });
        map.put(99, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$23
            private final ArrayList<DriverUpdateEntity> driList;
            private final ArrayList<SettingUpdateEntity<Object>> setList;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x63】Service");
                this.setList = new ArrayList<>();
                this.driList = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 0) {
                        DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_vehicle_no");
                        if (driverUpdateEntity != null) {
                            MsgMgr msgMgr = this.this$0;
                            ArrayList<DriverUpdateEntity> arrayList3 = this.driList;
                            arrayList3.clear();
                            arrayList3.add(driverUpdateEntity.setValue(new String(ArraysKt.copyOfRange(msgMgr.mCanbusInfoByte, 3, msgMgr.mCanbusInfoByte.length), Charsets.UTF_8)));
                            msgMgr.updateGeneralDriveData(arrayList3);
                            msgMgr.updateDriveDataActivity(null);
                            return;
                        }
                        return;
                    }
                    if (i == 16) {
                        DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_days");
                        if (driverUpdateEntity2 != null) {
                            setDay(driverUpdateEntity2);
                            return;
                        }
                        return;
                    }
                    if (i != 17) {
                        switch (i) {
                            case 32:
                                DriverUpdateEntity driverUpdateEntity3 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_days");
                                if (driverUpdateEntity3 != null) {
                                    setDay(driverUpdateEntity3);
                                    break;
                                }
                                break;
                            case 33:
                                DriverUpdateEntity driverUpdateEntity4 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_distance");
                                if (driverUpdateEntity4 != null) {
                                    setDistance(driverUpdateEntity4);
                                    break;
                                }
                                break;
                            case 34:
                                SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                                if (settingUpdateEntity != null) {
                                    MsgMgr msgMgr2 = this.this$0;
                                    ArrayList<SettingUpdateEntity<Object>> arrayList4 = this.setList;
                                    arrayList4.clear();
                                    arrayList4.add(settingUpdateEntity.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[3] & 1)));
                                    msgMgr2.updateGeneralSettingData(arrayList4);
                                    msgMgr2.updateSettingActivity(null);
                                    break;
                                }
                                break;
                        }
                        return;
                    }
                    DriverUpdateEntity driverUpdateEntity5 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_distance");
                    if (driverUpdateEntity5 != null) {
                        setDistance(driverUpdateEntity5);
                    }
                }
            }

            /* JADX WARN: Removed duplicated region for block: B:14:0x005f  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            private final void setDay(com.hzbhd.canbus.entity.DriverUpdateEntity r11) {
                /*
                    r10 = this;
                    com.hzbhd.canbus.car._3.MsgMgr r0 = r10.this$0
                    java.util.ArrayList<com.hzbhd.canbus.entity.DriverUpdateEntity> r1 = r10.driList
                    android.content.Context r2 = r2
                    r1.clear()
                    int[] r3 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r4 = 3
                    r3 = r3[r4]
                    r3 = r3 & 15
                    r4 = 0
                    r5 = 2
                    r6 = 1
                    if (r3 == 0) goto L22
                    if (r3 == r6) goto L1f
                    if (r3 == r5) goto L1c
                    return
                L1c:
                    java.lang.String r3 = "vm_golf7_vehicle_setup_service_overdue_days"
                    goto L23
                L1f:
                    java.lang.String r3 = "vm_golf7_vehicle_setup_service_days"
                    goto L23
                L22:
                    r3 = r4
                L23:
                    if (r3 == 0) goto L5f
                    java.lang.String r2 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r2, r3)
                    java.lang.String r3 = "getStrByResId(context, it)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                    java.lang.Object[] r3 = new java.lang.Object[r6]
                    int[] r5 = new int[r5]
                    int[] r7 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r8 = 4
                    r7 = r7[r8]
                    r8 = 0
                    r5[r8] = r7
                    int[] r7 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r9 = 5
                    r7 = r7[r9]
                    r5[r6] = r7
                    int r5 = com.hzbhd.canbus.car._3.MsgMgr.access$mergeValue(r0, r5)
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                    r3[r8] = r5
                    java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r6)
                    java.lang.String r2 = java.lang.String.format(r2, r3)
                    java.lang.String r3 = "format(this, *args)"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
                    if (r2 == 0) goto L5f
                    goto L61
                L5f:
                    java.lang.String r2 = "--"
                L61:
                    com.hzbhd.canbus.entity.DriverUpdateEntity r11 = r11.setValue(r2)
                    r1.add(r11)
                    java.util.List r1 = (java.util.List) r1
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateGeneralDriveData(r0, r1)
                    com.hzbhd.canbus.car._3.MsgMgr r11 = r10.this$0
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateDriveDataActivity(r11, r4)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$23.setDay(com.hzbhd.canbus.entity.DriverUpdateEntity):void");
            }

            /* JADX WARN: Removed duplicated region for block: B:18:0x0082  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            private final void setDistance(com.hzbhd.canbus.entity.DriverUpdateEntity r12) {
                /*
                    r11 = this;
                    com.hzbhd.canbus.car._3.MsgMgr r0 = r11.this$0
                    java.util.ArrayList<com.hzbhd.canbus.entity.DriverUpdateEntity> r1 = r11.driList
                    android.content.Context r2 = r2
                    r1.clear()
                    int[] r3 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r4 = 3
                    r3 = r3[r4]
                    r3 = r3 & 15
                    r5 = 32
                    r6 = 0
                    r7 = 2
                    r8 = 1
                    if (r3 == 0) goto L39
                    if (r3 == r8) goto L36
                    if (r3 == r7) goto L1e
                    return
                L1e:
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.String r9 = "vm_golf7_vehicle_setup_service_overdue"
                    java.lang.String r2 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r2, r9)
                    java.lang.StringBuilder r2 = r3.append(r2)
                    java.lang.StringBuilder r2 = r2.append(r5)
                    java.lang.String r2 = r2.toString()
                    goto L3a
                L36:
                    java.lang.String r2 = ""
                    goto L3a
                L39:
                    r2 = r6
                L3a:
                    if (r2 == 0) goto L82
                    java.lang.StringBuilder r3 = new java.lang.StringBuilder
                    r3.<init>()
                    java.lang.StringBuilder r2 = r3.append(r2)
                    int[] r3 = new int[r7]
                    r7 = 0
                    int[] r9 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r10 = 4
                    r9 = r9[r10]
                    r3[r7] = r9
                    int[] r7 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r9 = 5
                    r7 = r7[r9]
                    r3[r8] = r7
                    int r3 = com.hzbhd.canbus.car._3.MsgMgr.access$mergeValue(r0, r3)
                    int r3 = r3 * 100
                    java.lang.StringBuilder r2 = r2.append(r3)
                    java.lang.StringBuilder r2 = r2.append(r5)
                    int[] r3 = com.hzbhd.canbus.car._3.MsgMgr.access$getMCanbusInfoInt$p(r0)
                    r3 = r3[r4]
                    int r3 = r3 >> r10
                    r3 = r3 & r8
                    if (r3 != r8) goto L75
                    java.lang.String r3 = "mi"
                    goto L77
                L75:
                    java.lang.String r3 = "km"
                L77:
                    java.lang.StringBuilder r2 = r2.append(r3)
                    java.lang.String r2 = r2.toString()
                    if (r2 == 0) goto L82
                    goto L84
                L82:
                    java.lang.String r2 = "--"
                L84:
                    com.hzbhd.canbus.entity.DriverUpdateEntity r12 = r12.setValue(r2)
                    r1.add(r12)
                    java.util.List r1 = (java.util.List) r1
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateGeneralDriveData(r0, r1)
                    com.hzbhd.canbus.car._3.MsgMgr r12 = r11.this$0
                    com.hzbhd.canbus.car._3.MsgMgr.access$updateDriveDataActivity(r12, r6)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$23.setDistance(com.hzbhd.canbus.entity.DriverUpdateEntity):void");
            }
        });
        final ArrayList arrayList3 = new ArrayList();
        String[] strArr = new String[3];
        for (int i = 0; i < 3; i++) {
            strArr[i] = "";
        }
        arrayList3.add(new TireUpdateEntity(0, 0, strArr));
        String[] strArr2 = new String[3];
        for (int i2 = 0; i2 < 3; i2++) {
            strArr2[i2] = "";
        }
        arrayList3.add(new TireUpdateEntity(1, 0, strArr2));
        String[] strArr3 = new String[3];
        for (int i3 = 0; i3 < 3; i3++) {
            strArr3[i3] = "";
        }
        arrayList3.add(new TireUpdateEntity(2, 0, strArr3));
        String[] strArr4 = new String[3];
        for (int i4 = 0; i4 < 3; i4++) {
            strArr4[i4] = "";
        }
        arrayList3.add(new TireUpdateEntity(3, 0, strArr4));
        GeneralTireData.isHaveSpareTire = false;
        map.put(102, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$28
            private final Pair<String, Function1<Integer, Float>>[] units;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x66】直接式胎压监测信息");
                this.units = new Pair[]{TuplesKt.to("bar", new Function1<Integer, Float>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$28$units$1
                    public final Float invoke(int i5) {
                        return Float.valueOf(i5 / 10.0f);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }), TuplesKt.to("psi", new Function1<Integer, Float>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$28$units$2
                    public final Float invoke(int i5) {
                        return Float.valueOf(i5 / 2.0f);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }), TuplesKt.to("kPa", new Function1<Integer, Float>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$28$units$3
                    public final Float invoke(int i5) {
                        return Float.valueOf(i5 * 10.0f);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                })};
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    Pair<String, Function1<Integer, Float>> pair = this.units[RangesKt.coerceIn(this.this$0.mCanbusInfoInt[7], (ClosedRange<Integer>) new IntRange(0, 2))];
                    ArrayList<TireUpdateEntity> arrayList4 = arrayList3;
                    MsgMgr msgMgr = this.this$0;
                    int i5 = 0;
                    for (Object obj : arrayList4) {
                        int i6 = i5 + 1;
                        if (i5 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        ((TireUpdateEntity) obj).getList().set(RangesKt.coerceIn(msgMgr.mCanbusInfoInt[2], (ClosedRange<Integer>) new IntRange(0, 1)), pair.getSecond().invoke(Integer.valueOf(msgMgr.mCanbusInfoInt[i5 + 3])).floatValue() + ' ' + pair.getFirst());
                        i5 = i6;
                    }
                    GeneralTireData.dataList = arrayList3;
                    this.this$0.updateTirePressureActivity(null);
                }
            }
        });
        map.put(104, new Parser() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$29
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x68】胎压报警信息状态 2");
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i5 = this.this$0.mCanbusInfoInt[3];
                    String str = i5 != 2 ? i5 != 3 ? i5 != 4 ? "null_value" : "vm_golf7__direct_tpms_warn_lose_pressure" : "vm_golf7__direct_tpms_warn_low_pressure" : "vm_golf7__direct_tpms_warn_check_pressure";
                    ArrayList<TireUpdateEntity> arrayList4 = arrayList3;
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    int i6 = 0;
                    for (Object obj : arrayList4) {
                        int i7 = i6 + 1;
                        if (i6 < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        TireUpdateEntity tireUpdateEntity = (TireUpdateEntity) obj;
                        tireUpdateEntity.setTireStatus((msgMgr.mCanbusInfoInt[2] >> i6) & 1);
                        tireUpdateEntity.getList().set(2, CommUtil.getStrByResId(context2, tireUpdateEntity.getTireStatus() == 0 ? "vm_golf7__direct_tpms_warn_normal" : str));
                        i6 = i7;
                    }
                    GeneralTireData.dataList = arrayList3;
                    this.this$0.updateTirePressureActivity(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initParsers$lambda-4$getRadarValue, reason: not valid java name */
    public static final int m362initParsers$lambda4$getRadarValue(int i, int i2) {
        if (Integer.valueOf(i).equals(0)) {
            return 0;
        }
        return (int) (((i / i2) * 9) + 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$Parser;", "", NotificationCompat.CATEGORY_MESSAGE, "", "(Lcom/hzbhd/canbus/car/_3/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    public final void updateSettings(String title, Object value) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        SettingUpdateEntity<Object> settingUpdateEntity = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity != null) {
            updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntity.setValue(value)));
            updateSettingActivity(null);
        }
    }

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\u000bJ\u0006\u0010\r\u001a\u00020\u000bJ\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bJ\n\u0010\u0012\u001a\u00020\u000f*\u00020\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper;", "", "()V", "kmh", "Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "mph", "<set-?>", "speedUnit", "getSpeedUnit", "()Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "value", "", "getProgress", "getValue", "setUnit", "", "context", "Landroid/content/Context;", "setValue", "SpeedUnit", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class SpeedAlertHelper {
        public static final SpeedAlertHelper INSTANCE = new SpeedAlertHelper();
        private static final SpeedUnit kmh;
        private static final SpeedUnit mph;
        private static SpeedUnit speedUnit;
        private static int value;

        /* compiled from: MsgMgr.kt */
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B>\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u001a\u0010\u0019\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bHÆ\u0003JL\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0019\b\u0002\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000bHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0007HÖ\u0001R\"\u0010\b\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$SpeedAlertHelper$SpeedUnit;", "", "step", "", "min", "max", "unitStrName", "", "conversion", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(IIILjava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getConversion", "()Lkotlin/jvm/functions/Function1;", "getMax", "()I", "getMin", "getStep", "getUnitStrName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
        public static final /* data */ class SpeedUnit {
            private final Function1<Integer, Float> conversion;
            private final int max;
            private final int min;
            private final int step;
            private final String unitStrName;

            public static /* synthetic */ SpeedUnit copy$default(SpeedUnit speedUnit, int i, int i2, int i3, String str, Function1 function1, int i4, Object obj) {
                if ((i4 & 1) != 0) {
                    i = speedUnit.step;
                }
                if ((i4 & 2) != 0) {
                    i2 = speedUnit.min;
                }
                int i5 = i2;
                if ((i4 & 4) != 0) {
                    i3 = speedUnit.max;
                }
                int i6 = i3;
                if ((i4 & 8) != 0) {
                    str = speedUnit.unitStrName;
                }
                String str2 = str;
                if ((i4 & 16) != 0) {
                    function1 = speedUnit.conversion;
                }
                return speedUnit.copy(i, i5, i6, str2, function1);
            }

            /* renamed from: component1, reason: from getter */
            public final int getStep() {
                return this.step;
            }

            /* renamed from: component2, reason: from getter */
            public final int getMin() {
                return this.min;
            }

            /* renamed from: component3, reason: from getter */
            public final int getMax() {
                return this.max;
            }

            /* renamed from: component4, reason: from getter */
            public final String getUnitStrName() {
                return this.unitStrName;
            }

            public final Function1<Integer, Float> component5() {
                return this.conversion;
            }

            public final SpeedUnit copy(int step, int min, int max, String unitStrName, Function1<? super Integer, Float> conversion) {
                Intrinsics.checkNotNullParameter(unitStrName, "unitStrName");
                Intrinsics.checkNotNullParameter(conversion, "conversion");
                return new SpeedUnit(step, min, max, unitStrName, conversion);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SpeedUnit)) {
                    return false;
                }
                SpeedUnit speedUnit = (SpeedUnit) other;
                return this.step == speedUnit.step && this.min == speedUnit.min && this.max == speedUnit.max && Intrinsics.areEqual(this.unitStrName, speedUnit.unitStrName) && Intrinsics.areEqual(this.conversion, speedUnit.conversion);
            }

            public int hashCode() {
                return (((((((Integer.hashCode(this.step) * 31) + Integer.hashCode(this.min)) * 31) + Integer.hashCode(this.max)) * 31) + this.unitStrName.hashCode()) * 31) + this.conversion.hashCode();
            }

            public String toString() {
                return "SpeedUnit(step=" + this.step + ", min=" + this.min + ", max=" + this.max + ", unitStrName=" + this.unitStrName + ", conversion=" + this.conversion + ')';
            }

            /* JADX WARN: Multi-variable type inference failed */
            public SpeedUnit(int i, int i2, int i3, String unitStrName, Function1<? super Integer, Float> conversion) {
                Intrinsics.checkNotNullParameter(unitStrName, "unitStrName");
                Intrinsics.checkNotNullParameter(conversion, "conversion");
                this.step = i;
                this.min = i2;
                this.max = i3;
                this.unitStrName = unitStrName;
                this.conversion = conversion;
            }

            public final int getStep() {
                return this.step;
            }

            public final int getMin() {
                return this.min;
            }

            public final int getMax() {
                return this.max;
            }

            public final String getUnitStrName() {
                return this.unitStrName;
            }

            public final Function1<Integer, Float> getConversion() {
                return this.conversion;
            }
        }

        private SpeedAlertHelper() {
        }

        static {
            SpeedUnit speedUnit2 = new SpeedUnit(10, 30, NfDef.STATE_3WAY_M_HOLD, "_2_setting_1_3_1", new Function1<Integer, Float>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$SpeedAlertHelper$kmh$1
                public final Float invoke(int i) {
                    return Float.valueOf(i * 1.609344f);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            kmh = speedUnit2;
            mph = new SpeedUnit(5, 20, 150, "_2_setting_1_3_2", new Function1<Integer, Float>() { // from class: com.hzbhd.canbus.car._3.MsgMgr$SpeedAlertHelper$mph$1
                public final Float invoke(int i) {
                    return Float.valueOf(i / 1.609344f);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Float invoke(Integer num) {
                    return invoke(num.intValue());
                }
            });
            speedUnit = speedUnit2;
        }

        public final SpeedUnit getSpeedUnit() {
            return speedUnit;
        }

        public final void setUnit(Context context, int value2) {
            Intrinsics.checkNotNullParameter(context, "context");
            SpeedUnit speedUnit2 = value2 == 0 ? kmh : mph;
            if (Intrinsics.areEqual(speedUnit, speedUnit2)) {
                return;
            }
            speedUnit = speedUnit2;
            List<SettingPageUiSet.ListBean> list = UiMgrFactory.getCanUiMgr(context).getSettingUiSet(context).getList();
            Intrinsics.checkNotNullExpressionValue(list, "getCanUiMgr(context)\n   …ettingUiSet(context).list");
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = ((SettingPageUiSet.ListBean) it.next()).getItemList();
                Intrinsics.checkNotNullExpressionValue(itemList, "it.itemList");
                CollectionsKt.addAll(arrayList, itemList);
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (Intrinsics.areEqual(((SettingPageUiSet.ListBean.ItemListBean) obj).getTitleSrn(), "_3_40h_20h_p2")) {
                    arrayList2.add(obj);
                }
            }
            ArrayList arrayList3 = arrayList2;
            if (arrayList3.size() > 0) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = (SettingPageUiSet.ListBean.ItemListBean) arrayList3.get(0);
                SpeedAlertHelper speedAlertHelper = INSTANCE;
                itemListBean.setMin(speedUnit.getMin() / speedUnit.getStep());
                itemListBean.setMax(speedUnit.getMax() / speedUnit.getStep());
                itemListBean.setUnit(speedUnit.getUnitStrName());
                speedAlertHelper.setValue((int) speedUnit.getConversion().invoke(Integer.valueOf(value)).floatValue());
            }
        }

        public final void setValue(int i) {
            SpeedUnit speedUnit2 = speedUnit;
            value = RangesKt.coerceIn(i, speedUnit2.getMin(), speedUnit2.getMax());
        }

        public final int getValue() {
            return value / speedUnit.getStep();
        }

        public final int getProgress() {
            SpeedUnit speedUnit2 = speedUnit;
            return (value - speedUnit2.getMin()) / speedUnit2.getStep();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = this.m0xA6Command;
        bArr[2] = (byte) bYear2Dig;
        bArr[3] = (byte) bMonth;
        bArr[4] = (byte) bDay;
        bArr[5] = (byte) ((isFormat24H ? 0 : 128) | bHours24H);
        bArr[6] = (byte) bMins;
        CanbusMsgSender.sendMsg(bArr);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, int r9) {
        /*
            Method dump skipped, instructions count: 240
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._3.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 3;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        M0x70Sender.sendInfo$default(this.m0x70Sender, "ATV", null, null, null, 14, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 7;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        M0x70Sender.sendInfo$default(this.m0x70Sender, "AUX", null, null, null, 14, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        MediaShareData.Music music = MediaShareData.Music.INSTANCE;
        int i = currentSecond % 10;
        if (!(i >= 0 && i < 5)) {
            if (5 <= i && i < 10) {
                sendTextInfo(112, songTitle);
                sendTextInfo(113, songArtist);
                sendTextInfo(114, songAlbum);
                sendTextInfo(115, " ");
                return;
            }
            return;
        }
        this.m0xC0Command[2] = StringsKt.contains$default((CharSequence) music.getMPath(), (CharSequence) "emulated", false, 2, (Object) null) ? (byte) 9 : (byte) 8;
        byte[] bArr = this.m0xC0Command;
        bArr[3] = 17;
        bArr[4] = (byte) (music.getMPlaySize() & 255);
        this.m0xC0Command[5] = (byte) ((music.getMPlaySize() >> 8) & 255);
        this.m0xC0Command[6] = (byte) (music.getAPlayIndex() & 255);
        this.m0xC0Command[7] = (byte) ((music.getAPlayIndex() >> 8) & 255);
        long j = 1000;
        long j2 = 60;
        this.m0xC0Command[8] = (byte) (((music.getMPos() / j) / j2) % j2);
        this.m0xC0Command[9] = (byte) ((music.getMPos() / j) % j2);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        MediaShareData.Video video = MediaShareData.Video.INSTANCE;
        this.m0xC0Command[2] = StringsKt.contains$default((CharSequence) video.getMPath(), (CharSequence) "emulated", false, 2, (Object) null) ? (byte) 9 : (byte) 8;
        byte[] bArr = this.m0xC0Command;
        bArr[3] = 17;
        bArr[4] = (byte) (video.getMPlaySize() & 255);
        this.m0xC0Command[5] = (byte) ((video.getMPlaySize() >> 8) & 255);
        this.m0xC0Command[6] = (byte) (video.getMPlayIndex() & 255);
        this.m0xC0Command[7] = (byte) ((video.getMPlayIndex() >> 8) & 255);
        long j = 1000;
        long j2 = 60;
        this.m0xC0Command[8] = (byte) (((video.getMPos() / j) / j2) % j2);
        this.m0xC0Command[9] = (byte) ((video.getMPos() / j) % j2);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(album, "album");
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 11;
        bArr[3] = -1;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = 0;
        CanbusMsgSender.sendMsg(bArr);
        M0x70Sender.sendInfo$default(this.m0x70Sender, title, artist, album, null, 8, null);
        sendTextInfo(112, title);
        sendTextInfo(113, artist);
        sendTextInfo(114, album);
        sendTextInfo(115, " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 10;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        M0x70Sender.sendInfo$default(this.m0x70Sender, "DTV", null, null, null, 14, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        MediaShareData.Volume volume = MediaShareData.Volume.INSTANCE;
        byte[] bArr = this.m0xC4Command;
        bArr[2] = (byte) (volume.getVolume() | (volume.getVolume() == 0 ? 128 : 0));
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        byte[] bArr = this.m0xC5Command;
        bArr[2] = (byte) ((signalValue & NfDef.STATE_3WAY_M_HOLD) | (batteryStatus & 15));
        bArr[3] = (byte) ((isAudioTransferTowardsAG ? 0 : 64) | (isMicMute ? 0 : 32) | 16 | ((!isHfpConnected ? 6 : callStatus) & 15));
        if (phoneNumber != null) {
            ArraysKt.copyInto$default(phoneNumber, this.m0xCACommand, 4, 0, 0, 12, (Object) null);
        }
        sendMultiCommand(this.m0xC5Command, this.m0xCACommand);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
        byte[] bArr = this.m0xC7Command;
        int i = time / 60;
        bArr[2] = (byte) ((i / 60) / 60);
        bArr[3] = (byte) (i % 60);
        bArr[4] = (byte) (time % 60);
        CanbusMsgSender.sendMsg(bArr);
    }

    private final void sendTextInfo(int dataType, String text) {
        byte[] bytes;
        if (text == null) {
            text = "Unknown";
        }
        if (text.length() > 10) {
            bytes = StringsKt.substring(text, new IntRange(0, 10)).getBytes(Charsets.UTF_16BE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        } else {
            bytes = text.getBytes(Charsets.UTF_16BE);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) dataType, 16}, bytes));
    }

    private final void sendMultiCommand(final byte[]... commands) {
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$sendMultiCommand$1$1
            private int i;

            public final int getI() {
                return this.i;
            }

            public final void setI(int i) {
                this.i = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr = commands;
                if (i >= bArr.length) {
                    timerUtil.stopTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr[i]);
                }
            }
        }, 0L, 100L);
    }

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\rR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_3/MsgMgr$M0x70Sender;", "", "(Lcom/hzbhd/canbus/car/_3/MsgMgr;)V", "commands", "", "", "[[B", "emptyInfo", "timerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "sendInfo", "", "line1", "", "line2", "line3", "line4", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public final class M0x70Sender {
        private final byte[][] commands;
        private final byte[] emptyInfo;
        private final TimerUtil timerUtil;

        public M0x70Sender() {
            byte[][] bArr = new byte[4][];
            for (int i = 0; i < 4; i++) {
                byte[] bArr2 = new byte[23];
                int i2 = 0;
                while (i2 < 23) {
                    bArr2[i2] = i2 != 0 ? i2 != 1 ? i2 != 2 ? (byte) 0 : (byte) 16 : (byte) (i + 112) : (byte) 22;
                    i2++;
                }
                bArr[i] = bArr2;
            }
            this.commands = bArr;
            byte[] bytes = "          ".getBytes(Charsets.UTF_16);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            this.emptyInfo = bytes;
            this.timerUtil = new TimerUtil();
        }

        public static /* synthetic */ void sendInfo$default(M0x70Sender m0x70Sender, String str, String str2, String str3, String str4, int i, Object obj) {
            if ((i & 1) != 0) {
                str = "";
            }
            if ((i & 2) != 0) {
                str2 = "";
            }
            if ((i & 4) != 0) {
                str3 = "";
            }
            if ((i & 8) != 0) {
                str4 = "";
            }
            m0x70Sender.sendInfo(str, str2, str3, str4);
        }

        public final void sendInfo(String line1, String line2, String line3, String line4) {
            Intrinsics.checkNotNullParameter(line1, "line1");
            Intrinsics.checkNotNullParameter(line2, "line2");
            Intrinsics.checkNotNullParameter(line3, "line3");
            Intrinsics.checkNotNullParameter(line4, "line4");
            final Pair[] pairArr = {TuplesKt.to(line1, this.commands[0]), TuplesKt.to(line2, this.commands[1]), TuplesKt.to(line3, this.commands[2]), TuplesKt.to(line4, this.commands[3])};
            final MsgMgr msgMgr = MsgMgr.this;
            this.timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$M0x70Sender$sendInfo$1$1
                private int i;

                public final int getI() {
                    return this.i;
                }

                public final void setI(int i) {
                    this.i = i;
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    byte[] bytes;
                    int i = this.i;
                    Pair<String, byte[]>[] pairArr2 = pairArr;
                    if (i >= pairArr2.length) {
                        this.timerUtil.stopTimer();
                        return;
                    }
                    if (Intrinsics.areEqual(pairArr2[i].getFirst(), "")) {
                        bytes = this.emptyInfo;
                    } else {
                        bytes = msgMgr.getInfo(pairArr[this.i].getFirst(), 10).getBytes(Charsets.UTF_16);
                        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    }
                    ArraysKt.copyInto$default(bytes, pairArr[this.i].getSecond(), 3, 2, 0, 8, (Object) null);
                    CanbusMsgSender.sendMsg(pairArr[this.i].getSecond());
                    this.i++;
                }
            }, 100L, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getInfo(String str, int i) {
        if (str.length() < i) {
            return getInfo(str + ' ', i);
        }
        if (str.length() <= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(0, i - 2);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return sb.append(strSubstring).append("..").toString();
    }

    public final int[] getMAirData() {
        return this.mAirData;
    }

    public final void setMAirData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData = iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanbusInfoInt)) {
            return false;
        }
        this.mAirData = this.mCanbusInfoInt;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int mergeValue(int... values) {
        int length = values.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            i2 |= values[i] << (i3 * 8);
            i++;
            i3++;
        }
        return i2;
    }
}
