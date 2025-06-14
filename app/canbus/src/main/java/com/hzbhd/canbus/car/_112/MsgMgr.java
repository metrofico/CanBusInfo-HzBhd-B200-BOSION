package com.hzbhd.canbus.car._112;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\u0005\n\u0002\b\u001f\n\u0002\u0010\t\n\u0002\b\u001d\u0018\u0000 v2\u00020\u0001:\u0003vwxB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000bH\u0016J\b\u0010!\u001a\u00020\u001fH\u0016J\b\u0010\"\u001a\u00020\u001fH\u0016J\"\u0010#\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J \u0010(\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J \u0010)\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u00072\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H\u0016J\u001a\u0010*\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000b2\u0006\u0010+\u001a\u00020\u0007H\u0016Jp\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00042\u0006\u00101\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020&2\u0006\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020&2\u0006\u00109\u001a\u00020\u0004H\u0016Jv\u0010:\u001a\u00020\u001f2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020\u00042\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0006\u0010B\u001a\u00020\u00042\u0006\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020&2\u0006\u0010E\u001a\u00020\u00042\b\u0010F\u001a\u0004\u0018\u00010\u000f2\b\u0010G\u001a\u0004\u0018\u00010\u000f2\b\u0010H\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010I\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0002J\u0012\u0010J\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010K\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010L\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u000bH\u0002J¾\u0001\u0010M\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020<2\u0006\u0010U\u001a\u00020<2\u0006\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020<2\b\u0010X\u001a\u0004\u0018\u00010\u000f2\b\u0010Y\u001a\u0004\u0018\u00010\u000f2\b\u0010Z\u001a\u0004\u0018\u00010\u000f2\u0006\u0010[\u001a\u00020\\2\u0006\u0010;\u001a\u00020<2\u0006\u0010]\u001a\u00020\u00042\u0006\u0010D\u001a\u00020&2\u0006\u0010^\u001a\u00020\\2\u0006\u0010_\u001a\u00020\u000f2\u0006\u0010`\u001a\u00020\u000f2\u0006\u0010a\u001a\u00020\u000f2\u0006\u0010b\u001a\u00020&H\u0016J\u000e\u0010c\u001a\u00020\u001f2\u0006\u0010d\u001a\u00020\u000fJ0\u0010e\u001a\u00020\u001f2\u0006\u0010f\u001a\u00020\u00042\u0006\u0010g\u001a\u00020\u000f2\u0006\u0010h\u001a\u00020\u000f2\u0006\u0010i\u001a\u00020\u000f2\u0006\u0010j\u001a\u00020\u0004H\u0016J\u0018\u0010k\u001a\u00020\u001f2\u0006\u0010l\u001a\u00020\u00042\u0006\u0010m\u001a\u00020\u0007H\u0002J\u000e\u0010n\u001a\u00020\u001f2\u0006\u0010o\u001a\u00020\u0004J\u0012\u0010p\u001a\u00020\u001f2\b\u0010l\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010q\u001a\u00020\u001f2\u0006\u0010r\u001a\u00020&H\u0016J\u0098\u0001\u0010s\u001a\u00020\u001f2\u0006\u0010N\u001a\u00020<2\u0006\u0010O\u001a\u00020<2\u0006\u0010P\u001a\u00020\u00042\u0006\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020<2\u0006\u0010S\u001a\u00020<2\u0006\u0010T\u001a\u00020<2\b\u0010U\u001a\u0004\u0018\u00010\u000f2\u0006\u0010V\u001a\u00020<2\u0006\u0010W\u001a\u00020<2\b\u0010X\u001a\u0004\u0018\u00010\u000f2\b\u0010Y\u001a\u0004\u0018\u00010\u000f2\b\u0010Z\u001a\u0004\u0018\u00010\u000f2\u0006\u0010[\u001a\u00020\u00042\u0006\u0010t\u001a\u00020<2\u0006\u0010D\u001a\u00020&2\u0006\u0010u\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0013\u001a\f\u0012\b\u0012\u00060\u0015R\u00020\u00000\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00170\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006y"}, d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAmplifierSwitch", "", "mCanId", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDifferent", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mUiMgr", "Lcom/hzbhd/canbus/car/_112/UiMgr;", "mUnits", "afterServiceNormalSetting", "", "context", "auxInInfoChange", "btMusicInfoChange", "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", LcdInfoShare.MediaInfo.album, LcdInfoShare.MediaInfo.artist, "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "myToast", "content", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendPhoneCommand", LcdInfoShare.DspInfo.source, "phone", "setAmplifierSwitch", "value", "sourceSwitchChange", "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoInfoChange", "playMode", "duration", "Companion", "Parser", "VehicleType", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_DATA_MID = 10;
    public static final int AMPLIFIER_DATA_MIN = 1;
    public static final String SHARE_112_IS_HAVE_AMPLIFIER = "share_112_is_have_amplifier";
    public static final String TAG = "_112_MsgMgr";
    private int mAmplifierSwitch;
    private Context mContext;
    private UiMgr mUiMgr;
    private int mUnits;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mCanId = -1;
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final int mDifferent = getCurrentCanDifferentId();
    private final TimerUtil mTimerUtil = new TimerUtil();

    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr$VehicleType;", "", "value", "", "(Ljava/lang/String;II)V", "getValue", "()I", "JEEP_CHEROKEE_2016", "JEEP_CHEROKEE_2018", "JEEP_RENEGADE_2016", "FIAT_AEGEA_2016", "FIAT_AEGEA_2018", "JEEP_COMPASS_2017", "JEEP_COMPASS_2018", "FIAT_DOBLO_2015", "JEEP_GRAND_CHEROKEE_2014", "JEEP_RENEGADE_2018", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public enum VehicleType {
        JEEP_CHEROKEE_2016(1),
        JEEP_CHEROKEE_2018(11),
        JEEP_RENEGADE_2016(2),
        FIAT_AEGEA_2016(3),
        FIAT_AEGEA_2018(13),
        JEEP_COMPASS_2017(4),
        JEEP_COMPASS_2018(14),
        FIAT_DOBLO_2015(5),
        JEEP_GRAND_CHEROKEE_2014(6),
        JEEP_RENEGADE_2018(7);

        private final int value;

        VehicleType(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.afterServiceNormalSetting(context);
        AppEnableUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._112.UiMgr");
        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    private final void initAmplifier(Context context) {
        if (context != null) {
            int i = this.mCanId;
            UiMgr uiMgr = this.mUiMgr;
            if (uiMgr == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
                uiMgr = null;
            }
            getAmplifierData(context, i, uiMgr.getAmplifierPageUiSet(context));
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        final byte[][] bArr = {new byte[]{22, -124, 1, 1}, new byte[]{22, -124, 2, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 3, (byte) (10 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.leftRight + 10)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandBass + 1)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandTreble + 1)}, new byte[]{22, -124, 7, (byte) (GeneralAmplifierData.bandMiddle + 1)}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initAmplifier$2$1$1
            private int i;

            public final int getI() {
                return this.i;
            }

            public final void setI(int i2) {
                this.i = i2;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i2 = this.i;
                byte[][] bArr2 = bArr;
                if (i2 < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i2]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 100L, 133L);
        this.mAmplifierSwitch = 1;
        this.mTimerUtil.stopTimer();
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._112.MsgMgr.initAmplifier.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) MsgMgr.this.mAmplifierSwitch});
            }
        }, 100L, 1000L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        if (isPowerOff) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        Parser parser = this.mParserArray.get(byteArrayToIntArray[1]);
        if (parser != null) {
            parser.parse(this.mCanbusInfoInt.length - 2);
        }
    }

    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.put(7, new MsgMgr$initParsers$1$1(this));
        sparseArray.append(23, new MsgMgr$initParsers$1$2(this, context));
        sparseArray.append(39, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x27】室外温度及单位信息");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    int i = msgMgr.mCanbusInfoInt[2];
                    String str = " ";
                    if (i == 0) {
                        str = "--";
                    } else {
                        boolean z = false;
                        if (1 <= i && i < 256) {
                            z = true;
                        }
                        if (z) {
                            int i2 = this.this$0.mCanbusInfoInt[3];
                            if (i2 == 0) {
                                str = (((this.this$0.mCanbusInfoInt[2] + 1) / 2) - 40) + this.this$0.getTempUnitC(context);
                            } else if (i2 == 1) {
                                str = (this.this$0.mCanbusInfoInt[2] - 40) + this.this$0.getTempUnitF(context);
                            }
                        }
                    }
                    msgMgr.updateOutDoorTemp(context2, str);
                }
            }
        });
        sparseArray.append(32, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x20】方向盘按键");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i == 0) {
                    realKeyLongClick1(0);
                }
                if (i == 1) {
                    realKeyLongClick1(7);
                    return;
                }
                if (i == 2) {
                    realKeyLongClick1(8);
                    return;
                }
                if (i == 3) {
                    realKeyLongClick1(46);
                    return;
                }
                if (i == 4) {
                    realKeyLongClick1(45);
                    return;
                }
                switch (i) {
                    case 6:
                        realKeyLongClick1(3);
                        break;
                    case 7:
                        realKeyLongClick1(2);
                        break;
                    case 8:
                        realKeyLongClick1(HotKeyConstant.K_SPEECH);
                        break;
                    case 9:
                        realKeyLongClick1(14);
                        break;
                    case 10:
                        realKeyLongClick1(15);
                        break;
                    default:
                        switch (i) {
                            case 18:
                                realKeyLongClick1(49);
                                break;
                            case 19:
                                realKeyLongClick1(3);
                                break;
                            case 20:
                                realKeyLongClick1(152);
                                break;
                            case 21:
                                realKeyLongClick1(50);
                                break;
                            case 22:
                                realKeyLongClick1(49);
                                break;
                            case 23:
                                realKeyClick31(7);
                                break;
                            case 24:
                                realKeyClick31(8);
                                break;
                            case 25:
                                realKeyClick32(48);
                                break;
                            case 26:
                                realKeyClick32(47);
                                break;
                            case 27:
                                realKeyLongClick1(HotKeyConstant.K_SLEEP);
                                break;
                        }
                }
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }

            private final void realKeyClick31(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_1(context, value, 0, msgMgr.mCanbusInfoInt[3]);
            }

            private final void realKeyClick32(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_2(context, value, 0, msgMgr.mCanbusInfoInt[3]);
            }
        });
        sparseArray.append(33, new MsgMgr$initParsers$1$5(this, context));
        sparseArray.append(34, new MsgMgr$initParsers$1$6(this, context));
        sparseArray.append(35, new MsgMgr$initParsers$1$7(this, context));
        sparseArray.append(36, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$8
            private int doorStatus;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x24】车身基本信息");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDoorStatusChange()) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    GeneralDoorData.isRightFrontDoorOpen = ((i >> 7) & 1) == 1;
                    GeneralDoorData.isLeftFrontDoorOpen = ((i >> 6) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((i >> 5) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((i >> 4) & 1) == 1;
                    GeneralDoorData.isBackOpen = (i & 10) != 0;
                    GeneralDoorData.isFrontOpen = ((i >> 2) & 1) == 1;
                    this.this$0.updateDoorView(context);
                }
            }

            private final boolean isDoorStatusChange() {
                int i = this.this$0.mCanbusInfoInt[2] & com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
                if (Integer.valueOf(i).equals(Integer.valueOf(this.doorStatus))) {
                    return false;
                }
                this.doorStatus = i;
                return true;
            }
        });
        sparseArray.append(41, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x29】EPS 方向盘转角");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[2], this.this$0.mCanbusInfoByte[3], 0, 4600, 16);
                    this.this$0.updateParkUi(null, context);
                }
            }
        });
        sparseArray.append(48, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$10
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x30】解码盒版本信息");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        sparseArray.append(64, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$11
            private int brakeMessage;
            private final ArrayList<SettingUpdateEntity<Object>> list;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x40】中控设置信息");
                this.list = new ArrayList<>();
            }

            private final void updateItem(String title, Object value) {
                SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) this.this$0.mSettingItemIndexHashMap.get(title);
                if (settingUpdateEntity != null) {
                    this.list.add(settingUpdateEntity.setValue(value));
                }
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    int[] iArr = this.this$0.mCanbusInfoInt;
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    int i = 2;
                    int i2 = iArr[2];
                    if (i2 == 0) {
                        updateItem("language_setup", Integer.valueOf(iArr[3] - 1));
                    } else if (i2 == 1) {
                        Integer numValueOf = Integer.valueOf(iArr[3] & 1);
                        msgMgr.mUnits = numValueOf.intValue();
                        Unit unit = Unit.INSTANCE;
                        updateItem("hiworld_jeep_123_0xC1_data3", numValueOf);
                        updateItem("hiworld_jeep_123_0xC1_data2", Integer.valueOf((iArr[3] >> 1) & 3));
                        updateItem("hiworld_jeep_123_0xCA_0X01", Integer.valueOf((iArr[3] >> 3) & 1));
                        updateItem("hiworld_jeep_123_0xC1_data1", Integer.valueOf((iArr[3] >> 4) & 1));
                        updateItem("hiworld_jeep_123_0xCA_0X08", Integer.valueOf((iArr[3] >> 5) & 3));
                        updateItem("_118_setting_title_81", Integer.valueOf((iArr[3] >> 7) & 1));
                    } else if (i2 == 3) {
                        updateItem("_276_setting_0", Integer.valueOf(iArr[3] & 1));
                    } else if (i2 != 48) {
                        int i3 = 0;
                        if (i2 == 64) {
                            int i4 = iArr[3];
                            updateItem("_118_setting_title_9", Integer.valueOf(i4 != 30 ? i4 != 60 ? i4 != 90 ? 0 : 3 : 2 : 1));
                            int i5 = iArr[4];
                            if (i5 == 3) {
                                i = 1;
                            } else if (i5 != 20) {
                                i = i5 != 40 ? 0 : 3;
                            }
                            updateItem("hiworld_jeep_123_0x62_data2_54", Integer.valueOf(i));
                            updateItem("_118_setting_title_19", Integer.valueOf(iArr[5] & 1));
                            updateItem("_112_headunit_power_off_mode", Integer.valueOf((iArr[5] >> 1) & 1));
                        } else if (i2 == 96) {
                            updateItem("_112_aux1_type", Integer.valueOf((iArr[3] >> 7) & 1));
                            updateItem("_112_aux1_power", Integer.valueOf((iArr[3] >> 6) & 1));
                            updateItem("_112_aux1_recalled", Integer.valueOf((iArr[3] >> 5) & 1));
                            updateItem("_112_aux2_type", Integer.valueOf((iArr[3] >> 3) & 3));
                            updateItem("_112_aux2_power", Integer.valueOf((iArr[3] >> 2) & 1));
                            updateItem("_112_aux2_recalled", Integer.valueOf((iArr[3] >> 1) & 1));
                            updateItem("_112_aux3_type", Integer.valueOf((iArr[4] >> 7) & 1));
                            updateItem("_112_aux3_power", Integer.valueOf((iArr[4] >> 6) & 1));
                            updateItem("_112_aux3_recalled", Integer.valueOf((iArr[4] >> 5) & 1));
                            updateItem("_112_aux4_type", Integer.valueOf((iArr[4] >> 3) & 3));
                            updateItem("_112_aux4_power", Integer.valueOf((iArr[4] >> 2) & 1));
                            updateItem("_112_aux4_recalled", Integer.valueOf((iArr[4] >> 1) & 1));
                        } else if (i2 == 144) {
                            updateItem("seat_auto_heat", Integer.valueOf(iArr[3] & 3));
                        } else if (i2 == 192) {
                            updateItem("_118_setting_title_37", Integer.valueOf(iArr[3] & 1));
                            updateItem("_118_setting_title_38", Integer.valueOf((iArr[3] >> 1) & 1));
                            int i6 = this.brakeMessage;
                            int i7 = iArr[4];
                            if (i6 != i7) {
                                this.brakeMessage = i7;
                                if (1 <= i7 && i7 < 9) {
                                    GeneralDisplayMsgData.displayMsg = CommUtil.getStrByResId(context2, "_112_brake_message_" + this.brakeMessage);
                                }
                                msgMgr.sendDisplayMsgView(context2);
                            }
                        } else if (i2 == 208) {
                            updateItem("_118_setting_title_24", Integer.valueOf(iArr[3] & 1));
                            updateItem("_118_setting_title_26", Integer.valueOf((iArr[3] >> 1) & 1));
                            updateItem("_118_setting_title_27", Integer.valueOf((iArr[3] >> 2) & 1));
                            updateItem("_118_setting_title_28", Integer.valueOf((iArr[3] >> 3) & 1));
                            updateItem("_118_setting_title_25", Integer.valueOf((iArr[3] >> 4) & 1));
                            updateItem("_112_auto_suspension", Integer.valueOf((iArr[3] >> 5) & 1));
                        } else if (i2 == 32) {
                            updateItem("_112_adaptive_high_beam", Integer.valueOf((iArr[3] >> 7) & 1));
                            int i8 = iArr[3] & WorkQueueKt.MASK;
                            updateItem("hiworld_jeep_123_0x62_data2_10", Integer.valueOf(i8 != 30 ? i8 != 60 ? i8 != 90 ? 0 : 3 : 2 : 1));
                            updateItem("hiworld_jeep_123_0x62_data3_4", Integer.valueOf((iArr[4] >> 7) & 1));
                            int i9 = iArr[4] & WorkQueueKt.MASK;
                            if (i9 == 30) {
                                i3 = 1;
                            } else if (i9 == 60) {
                                i3 = 2;
                            } else if (i9 == 90) {
                                i3 = 3;
                            }
                            updateItem("hiworld_jeep_123_0x62_data2_32", Integer.valueOf(i3));
                            updateItem("hiworld_jeep_123_0x62_data3_3", Integer.valueOf(iArr[5] & 1));
                            updateItem("_112_cornering_lights", Integer.valueOf((iArr[5] >> 1) & 1));
                            updateItem("hiworld_jeep_123_0x62_data3_0", Integer.valueOf((iArr[5] >> 2) & 1));
                            updateItem("_112_rear_mirror_dimmer", Integer.valueOf((iArr[5] >> 3) & 1));
                            updateItem("_112_auto_anti_glare", Integer.valueOf((iArr[5] >> 4) & 1));
                            updateItem("hiworld_jeep_123_0x62_data2_76", Integer.valueOf((iArr[5] >> 5) & 3));
                            SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("hiworld_jeep_123_0x62_data2_76");
                            if (settingUpdateEntity != null) {
                                int i10 = (iArr[5] >> 5) & 3;
                                this.list.add(settingUpdateEntity.setValue(Integer.valueOf(i10 + 1)).setProgress(i10));
                            }
                            updateItem("_250_welcome_light", Integer.valueOf((iArr[5] >> 7) & 1));
                        } else if (i2 == 33) {
                            updateItem("hiworld_jeep_123_0x62_data3_765", Integer.valueOf(iArr[3] & 15));
                        } else if (i2 == 160) {
                            updateItem("hiworld_jeep_123_0x43_data3_76", Integer.valueOf(iArr[3] & 1));
                            updateItem("hiworld_jeep_123_0x43_data5_4", Integer.valueOf((iArr[3] >> 1) & 1));
                            updateItem("hiworld_jeep_123_0x43_data4_3", Integer.valueOf((iArr[3] >> 2) & 1));
                            updateItem("hiworld_jeep_123_0x43_data4_2", Integer.valueOf((iArr[3] >> 3) & 1));
                            updateItem("hiworld_jeep_123_0x43_data4_6", Integer.valueOf((iArr[3] >> 4) & 1));
                            updateItem("hiworld_jeep_123_0x43_data4_10", Integer.valueOf((iArr[3] >> 6) & 3));
                            updateItem("hiworld_jeep_123_0x43_data5_10", Integer.valueOf(iArr[4] & 3));
                            updateItem("hiworld_jeep_123_0x43_data5_32", Integer.valueOf((iArr[4] >> 2) & 3));
                            updateItem("hiworld_jeep_123_0x43_data3_0", Integer.valueOf((iArr[4] >> 4) & 1));
                            updateItem("hiworld_jeep_123_0x43_data3_1", Integer.valueOf((iArr[4] >> 5) & 3));
                            updateItem("_118_setting_title_4", Integer.valueOf((iArr[4] >> 7) & 1));
                            updateItem("hiworld_jeep_123_0x43_data3_54", Integer.valueOf(iArr[5] & 3));
                            updateItem("hiworld_jeep_123_0x43_data4_5", Integer.valueOf((iArr[5] >> 2) & 1));
                            updateItem("_112_rear_park_assist", Integer.valueOf((iArr[5] >> 3) & 1));
                            updateItem("_112_auto_park_assist", Integer.valueOf((iArr[5] >> 4) & 1));
                            updateItem("_118_setting_title_65", Integer.valueOf((iArr[5] >> 5) & 1));
                            updateItem("_176_car_setting_title_22", Integer.valueOf((iArr[5] >> 6) & 3));
                        } else if (i2 == 161) {
                            updateItem("_118_setting_title_34", Integer.valueOf((iArr[3] >> 6) & 3));
                            updateItem("_118_setting_title_30", Integer.valueOf((iArr[3] >> 3) & 1));
                            updateItem("_118_setting_title_29", Integer.valueOf(iArr[3] & 3));
                            updateItem("_112_offset_dist_alarm", Integer.valueOf((iArr[4] >> 6) & 3));
                            updateItem("_112_offset_dist_alarm_vol", Integer.valueOf((iArr[4] >> 4) & 3));
                        }
                    } else {
                        updateItem("hiworld_jeep_123_0x60_data1_0", Integer.valueOf(iArr[3] & 1));
                        updateItem("hiworld_jeep_123_0x60_data1_1", Integer.valueOf((iArr[3] >> 1) & 1));
                        updateItem("_118_setting_title_74", Integer.valueOf((iArr[3] >> 2) & 1));
                        updateItem("hiworld_jeep_123_0x60_data1_65", Integer.valueOf((iArr[3] >> 3) & 3));
                        updateItem("_118_setting_title_18", Integer.valueOf((iArr[3] >> 5) & 1));
                        updateItem("hiworld_jeep_123_0x60_data1_4", Integer.valueOf((iArr[3] >> 7) & 1));
                        updateItem("_118_setting_title_16", Integer.valueOf(iArr[4] & 1));
                        updateItem("_213_car_set14_2_0", Integer.valueOf((iArr[4] >> 1) & 1));
                        updateItem("hiworld_jeep_123_0x60_data1_3", Integer.valueOf((iArr[4] >> 2) & 1));
                        updateItem("_112_horn_remote_start", Integer.valueOf(iArr[5] & 1));
                        updateItem("remote_door_unlock", Integer.valueOf((iArr[5] >> 1) & 1));
                    }
                    this.this$0.updateGeneralSettingData(this.list);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(66, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$12
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x42】CD 状态");
                GeneralOriginalCarDeviceData.cdStatus = "CD";
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    Context context2 = context;
                    int i = this.this$0.mCanbusInfoInt[2];
                    GeneralOriginalCarDeviceData.runningState = CommUtil.getStrByResId(context2, i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "null_value" : "_118_setting_value_93" : "_118_setting_value_94" : "_118_setting_value_90" : "_118_setting_value_92" : "_118_setting_value_91" : "_118_setting_value_89");
                    GeneralOriginalCarDeviceData.rpt = (this.this$0.mCanbusInfoInt[3] & 1) == 1;
                    GeneralOriginalCarDeviceData.rdm = ((this.this$0.mCanbusInfoInt[3] >> 1) & 1) == 1;
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(67, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$13
            {
                super(this.this$0, "【0x43】CD 播放时间信息");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr = this.this$0;
                    arrayList.add(new OriginalCarDeviceUpdateEntity(3, ((msgMgr.mCanbusInfoInt[2] << 8) | msgMgr.mCanbusInfoInt[3]) + " / " + (msgMgr.mCanbusInfoInt[11] | (msgMgr.mCanbusInfoInt[10] << 8))));
                    GeneralOriginalCarDeviceData.mList = arrayList;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.this$0.mCanbusInfoInt[4]), Integer.valueOf(this.this$0.mCanbusInfoInt[5]), Integer.valueOf(this.this$0.mCanbusInfoInt[6])}, 3));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    GeneralOriginalCarDeviceData.endTime = str;
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String str2 = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(this.this$0.mCanbusInfoInt[7]), Integer.valueOf(this.this$0.mCanbusInfoInt[8]), Integer.valueOf(this.this$0.mCanbusInfoInt[9])}, 3));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                    GeneralOriginalCarDeviceData.startTime = str2;
                    int i = (this.this$0.mCanbusInfoInt[4] * 60 * 60) + (this.this$0.mCanbusInfoInt[5] * 60) + this.this$0.mCanbusInfoInt[6];
                    if (i != 0) {
                        GeneralOriginalCarDeviceData.progress = (((((this.this$0.mCanbusInfoInt[7] * 60) * 60) + (this.this$0.mCanbusInfoInt[8] * 60)) + this.this$0.mCanbusInfoInt[9]) * 100) / i;
                    } else {
                        GeneralOriginalCarDeviceData.progress = 0;
                    }
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(68, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$14
            {
                super(this.this$0, "【0x44】CD 播放曲目信息");
                GeneralOriginalCarDeviceData.songList = new ArrayList();
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    byte[] bArr = this.this$0.mCanbusInfoByte;
                    byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bArr, 6, bArr.length);
                    Charset UTF_16 = StandardCharsets.UTF_16;
                    Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
                    String str = new String(bArrCopyOfRange, UTF_16);
                    if (this.this$0.mCanbusInfoInt[2] == 4) {
                        List<SongListEntity> list = GeneralOriginalCarDeviceData.songList;
                        MsgMgr msgMgr = this.this$0;
                        int i = (msgMgr.mCanbusInfoInt[5] | (msgMgr.mCanbusInfoInt[4] << 8)) - 1;
                        if (i < list.size()) {
                            list.get(i).setTitle(str);
                        } else {
                            list.add(new SongListEntity(str));
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(new OriginalCarDeviceUpdateEntity(this.this$0.mCanbusInfoInt[2] - 1, str));
                        GeneralOriginalCarDeviceData.mList = arrayList;
                    }
                    this.this$0.updateOriginalCarDeviceActivity(null);
                }
            }
        });
        sparseArray.append(40, new Parser() { // from class: com.hzbhd.canbus.car._112.MsgMgr$initParsers$1$15
            {
                super(this.this$0, "【0x28】转速车速信息");
            }

            @Override // com.hzbhd.canbus.car._112.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateSpeedInfo((msgMgr.mCanbusInfoInt[3] << 8) | this.this$0.mCanbusInfoInt[2]);
                if (isDataChange()) {
                    MsgMgr msgMgr2 = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr3 = this.this$0;
                    arrayList.add(new DriverUpdateEntity(0, 0, ((msgMgr3.mCanbusInfoInt[3] << 8) | msgMgr3.mCanbusInfoInt[2]) + " KM/H"));
                    arrayList.add(new DriverUpdateEntity(0, 1, (msgMgr3.mCanbusInfoInt[4] | (msgMgr3.mCanbusInfoInt[5] << 8)) + " RPM"));
                    msgMgr2.updateGeneralDriveData(arrayList);
                    this.this$0.updateDriveDataActivity(null);
                }
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) bYear2Dig, (byte) bMonth, (byte) bDay, (byte) DataHandleUtils.setOneBit(bHours24H, 7, !isFormat24H ? 1 : 0), (byte) bMins, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._112.MsgMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m52dateTimeRepCanbus$lambda4();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dateTimeRepCanbus$lambda-4, reason: not valid java name */
    public static final void m52dateTimeRepCanbus$lambda4() {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        Context context = this.mContext;
        Settings.System.putString(context != null ? context.getContentResolver() : null, "reportAfterHangUp", "");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0058  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, int r11) throws java.lang.NumberFormatException {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._112.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        sendPhoneCommand(65, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        sendPhoneCommand(66, phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendPhoneCommand(67, new byte[]{32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Intrinsics.checkNotNullParameter(songTitle, "songTitle");
        Intrinsics.checkNotNullParameter(songAlbum, "songAlbum");
        Intrinsics.checkNotNullParameter(songArtist, "songArtist");
        Context context = this.mContext;
        String strName = SourceConstantsDef.SOURCE_ID.MUSIC.name();
        byte[] bArr = new byte[10];
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = (byte) (stoagePath == 9 ? 9 : 8);
        bArr[3] = 17;
        bArr[4] = (byte) currentPlayingIndexLow;
        bArr[5] = currentPlayingIndexHigh;
        bArr[6] = (byte) (totalCount & 255);
        bArr[7] = (byte) ((totalCount >> 8) & 255);
        bArr[8] = currentMinute;
        bArr[9] = currentSecond;
        sendMediaMsg(context, strName, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        Context context = this.mContext;
        String strName = SourceConstantsDef.SOURCE_ID.VIDEO.name();
        byte[] bArr = new byte[10];
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = (byte) (stoagePath == 9 ? 9 : 8);
        bArr[3] = 17;
        bArr[4] = (byte) currentPlayingIndexLow;
        bArr[5] = currentPlayingIndexHigh;
        bArr[6] = (byte) (totalCount & 255);
        bArr[7] = (byte) ((totalCount >> 8) & 255);
        bArr[8] = currentMinute;
        bArr[9] = currentSecond;
        sendMediaMsg(context, strName, bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, -1, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        if (discType == 1 || discType == 5) {
            position = currentPlayNo;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 17, (byte) ((position >> 8) & 255), (byte) (position & 255), (byte) ((currentTotalNo >> 8) & 255), (byte) (currentTotalNo & 255), (byte) ((currentTime / 60) % 60), (byte) (currentTime % 60)});
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/car/_112/MsgMgr$Parser;", "", NotificationCompat.CATEGORY_MESSAGE, "", "(Lcom/hzbhd/canbus/car/_112/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    public final void setAmplifierSwitch(int value) {
        this.mAmplifierSwitch = value;
    }

    private final void sendPhoneCommand(int source, byte[] phone) {
        final byte[][] bArr = {new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0}, new byte[]{22, -64, (byte) source, -1, 0, 0, 0, 0, 0, 0}, ArraysKt.plus(new byte[]{22, 112, 1}, phone)};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._112.MsgMgr$sendPhoneCommand$1$1$1
            private int count;

            public final int getCount() {
                return this.count;
            }

            public final void setCount(int i) {
                this.count = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.count;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i]);
                    this.count++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    public final void myToast(final String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._112.MsgMgr$$ExternalSyntheticLambda0
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public final void callback() {
                MsgMgr.m53myToast$lambda13(this.f$0, content);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: myToast$lambda-13, reason: not valid java name */
    public static final void m53myToast$lambda13(MsgMgr this$0, String content) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(content, "$content");
        Toast.makeText(this$0.mContext, content, 0).show();
    }
}
