package com.hzbhd.canbus.car._328;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b%\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0019\u0018\u0000 n2\u00020\u0001:\u0002noB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000fH\u0016J \u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\bH\u0016J \u0010)\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010-\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010.\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J \u0010/\u001a\u00020#2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0016J\u001a\u00100\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u00101\u001a\u00020\u000bH\u0016J\u001a\u00102\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010\u000f2\u0006\u00103\u001a\u00020\u0006H\u0016Jp\u00104\u001a\u00020#2\u0006\u00105\u001a\u00020\u00062\u0006\u00106\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00062\u0006\u00108\u001a\u00020\u00062\u0006\u00109\u001a\u00020\u00062\u0006\u0010:\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\u00062\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u00162\u0006\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u0006H\u0016J\u0012\u0010B\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0002J\u0012\u0010C\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010D\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u000fH\u0002J\u0010\u0010E\u001a\u00020#2\u0006\u0010$\u001a\u00020\u000fH\u0002J\b\u0010F\u001a\u00020#H\u0016J¾\u0001\u0010G\u001a\u00020#2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020I2\u0006\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020I2\u0006\u0010P\u001a\u00020I2\u0006\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020I2\b\u0010S\u001a\u0004\u0018\u00010\b2\b\u0010T\u001a\u0004\u0018\u00010\b2\b\u0010U\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020I2\u0006\u0010Y\u001a\u00020\u00062\u0006\u0010Z\u001a\u00020\u00162\u0006\u0010[\u001a\u00020W2\u0006\u0010\\\u001a\u00020\b2\u0006\u0010]\u001a\u00020\b2\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020\u0016H\u0016J0\u0010`\u001a\u00020#2\u0006\u0010a\u001a\u00020\u00062\u0006\u0010b\u001a\u00020\b2\u0006\u0010c\u001a\u00020\b2\u0006\u0010d\u001a\u00020\b2\u0006\u0010e\u001a\u00020\u0006H\u0016J\u000e\u0010f\u001a\u00020#2\u0006\u0010g\u001a\u00020\u0006J\u0010\u0010h\u001a\u00020#2\u0006\u0010i\u001a\u00020\u0016H\u0016J\u0016\u0010j\u001a\u00020#2\u0006\u0010&\u001a\u00020\b2\u0006\u0010g\u001a\u00020\u001eJ\u0098\u0001\u0010k\u001a\u00020#2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020\u00062\u0006\u0010L\u001a\u00020\u00062\u0006\u0010M\u001a\u00020I2\u0006\u0010N\u001a\u00020I2\u0006\u0010O\u001a\u00020I2\b\u0010P\u001a\u0004\u0018\u00010\b2\u0006\u0010Q\u001a\u00020I2\u0006\u0010R\u001a\u00020I2\b\u0010S\u001a\u0004\u0018\u00010\b2\b\u0010T\u001a\u0004\u0018\u00010\b2\b\u0010U\u001a\u0004\u0018\u00010\b2\u0006\u0010V\u001a\u00020\u00062\u0006\u0010l\u001a\u00020I2\u0006\u0010Z\u001a\u00020\u00162\u0006\u0010m\u001a\u00020\u0006H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0018\u001a\f\u0012\b\u0012\u00060\u001aR\u00020\u00000\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lcom/hzbhd/canbus/car/_328/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "mAmpTimerUtil", "Lcom/hzbhd/canbus/util/TimerUtil;", "mAmplifierSwitch", "", "mBtMusicSongTitleRecord", "", "mCanId", "mCanbusInfoByte", "", "mCanbusInfoInt", "", "mContext", "Landroid/content/Context;", "mDriveItemIndexHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/DriverUpdateEntity;", "mHandler", "Landroid/os/Handler;", "mIsPhoneIncoming", "", "mMeidaSongTitleRecord", "mParserArray", "Landroid/util/SparseArray;", "Lcom/hzbhd/canbus/car/_328/MsgMgr$Parser;", "mRadioInfoCommand", "mSettingItemIndexHashMap", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_328/UiMgr;", "mVersionReqCommand", "afterServiceNormalSetting", "", "context", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btPhoneHangUpInfoChange", "phoneNumber", "isMicMute", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "customLongClick", "key", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "initAmplifier", "initCommand", "initItemsIndexHashMap", "initParsers", "musicDestroy", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "setAmplifierSwitch", "value", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettings", "videoInfoChange", "playMode", "duration", "Companion", "Parser", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_DATA_BASS_MIN = 2;
    public static final int AMPLIFIER_DATA_FAD_MID = 11;
    private static final String TAG = "_330_MsgMgr";
    private int mAmplifierSwitch;
    private Context mContext;
    private boolean mIsPhoneIncoming;
    private UiMgr mUiMgr;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mCanId = -1;
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final byte[] mVersionReqCommand = {22, -112, ByteCompanionObject.MAX_VALUE, 0};
    private final byte[] mRadioInfoCommand = {22, -64, 1, 0, 0, 0, 0};
    private String mMeidaSongTitleRecord = "";
    private String mBtMusicSongTitleRecord = "";
    private final TimerUtil mAmpTimerUtil = new TimerUtil();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._328.UiMgr");
        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
        updateSettings("support_panorama", Integer.valueOf(SharePreUtil.getIntValue(context, UiMgr.SHARE_IS_SUPPORT_PANORAMIC, 0)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        if (isPowerOff) {
            this.mAmpTimerUtil.stopTimer();
        } else {
            initAmplifier(this.mContext);
        }
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
        setAmplifierSwitch(1);
        this.mAmpTimerUtil.stopTimer();
        this.mAmpTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._328.MsgMgr.initAmplifier.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 9, (byte) MsgMgr.this.mAmplifierSwitch});
            }
        }, 100L, 1000L);
        final Iterator it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -124, 1, (byte) (11 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 11)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 8, (byte) GeneralAmplifierData.volume}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initAmplifier$3$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (it.hasNext()) {
                    CanbusMsgSender.sendMsg(it.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 200L, 100L);
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
        sparseArray.put(32, new Parser() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x20】按键信息");
            }

            @Override // com.hzbhd.canbus.car._328.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i == 18) {
                    realKeyLongClick1(HotKeyConstant.K_SPEECH);
                    return;
                }
                if (i == 21) {
                    realKeyLongClick1(50);
                    return;
                }
                if (i != 22) {
                    switch (i) {
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
                            realKeyLongClick1(45);
                            break;
                        case 4:
                            realKeyLongClick1(46);
                            break;
                        case 5:
                            realKeyLongClick1(45);
                            break;
                        case 6:
                            realKeyLongClick1(46);
                            break;
                        case 7:
                            realKeyLongClick1(2);
                            break;
                        case 8:
                            realKeyLongClick1(3);
                            break;
                        case 9:
                            realKeyLongClick1(14);
                            break;
                        case 10:
                            realKeyLongClick1(15);
                            break;
                        default:
                            switch (i) {
                                case 129:
                                    realKeyLongClick1(HotKeyConstant.K_SLEEP);
                                    break;
                                case 130:
                                    realKeyClick31(7);
                                    break;
                                case 131:
                                    realKeyClick31(8);
                                    break;
                                case 132:
                                    realKeyLongClick1(49);
                                    break;
                                case 133:
                                    realKeyClick32(48);
                                    break;
                                case HotKeyConstant.K_SLEEP /* 134 */:
                                    realKeyClick32(47);
                                    break;
                                case 135:
                                    realKeyLongClick1(76);
                                    break;
                                case 136:
                                    realKeyLongClick1(4);
                                    break;
                                case 137:
                                    realKeyLongClick1(128);
                                    break;
                                case 138:
                                    realKeyLongClick1(52);
                                    break;
                                case 139:
                                    realKeyLongClick1(52);
                                    break;
                                case 140:
                                    realKeyLongClick1(58);
                                    break;
                                case 141:
                                    if (this.this$0.mCanbusInfoInt[3] == 1) {
                                        this.this$0.startMainActivity(context);
                                        break;
                                    }
                                    break;
                                case 142:
                                    realKeyLongClick1(152);
                                    break;
                                case 143:
                                    if (this.this$0.mCanbusInfoInt[3] == 1) {
                                        Context context2 = context;
                                        Intent intent = new Intent();
                                        intent.setComponent(Constant.Launcher);
                                        intent.setFlags(268435456);
                                        intent.putExtra("keycode", 284);
                                        context2.startActivity(intent);
                                        break;
                                    }
                                    break;
                            }
                    }
                    return;
                }
                realKeyLongClick1(49);
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
        sparseArray.append(23, new Parser() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$2
            private final int[] amplifierData;
            private final ArrayList<SettingUpdateEntity<Object>> list;
            private final int[] settingData;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x17】功放信息");
                this.list = new ArrayList<>();
                this.amplifierData = new int[6];
                this.settingData = new int[8];
            }

            @Override // com.hzbhd.canbus.car._328.MsgMgr.Parser
            public void parse(int dataLength) {
                Bundle bundle;
                if (isDataChange()) {
                    int[] iArrPlus = ArraysKt.plus(ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 2, 4), ArraysKt.plus(ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 5, 8), this.this$0.mCanbusInfoInt[9]));
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    if (Arrays.equals(iArrPlus, this.amplifierData)) {
                        bundle = null;
                    } else {
                        int[] iArr = this.amplifierData;
                        bundle = null;
                        ArraysKt.copyInto$default(iArrPlus, iArr, 0, 0, iArr.length, 6, (Object) null);
                        GeneralAmplifierData.frontRear = 11 - msgMgr.mCanbusInfoInt[2];
                        GeneralAmplifierData.leftRight = msgMgr.mCanbusInfoInt[3] - 11;
                        GeneralAmplifierData.bandBass = msgMgr.mCanbusInfoInt[5] - 2;
                        GeneralAmplifierData.bandTreble = msgMgr.mCanbusInfoInt[6] - 2;
                        GeneralAmplifierData.bandMiddle = msgMgr.mCanbusInfoInt[7] - 2;
                        GeneralAmplifierData.volume = msgMgr.mCanbusInfoInt[9];
                        msgMgr.updateAmplifierActivity(null);
                        msgMgr.saveAmplifierData(context2, msgMgr.mCanId);
                    }
                    int[] iArrPlus2 = ArraysKt.plus(ArraysKt.plus(ArraysKt.plus(new int[0], this.this$0.mCanbusInfoInt[4]), this.this$0.mCanbusInfoInt[8]), ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 10, this.this$0.mCanbusInfoInt.length));
                    MsgMgr msgMgr2 = this.this$0;
                    if (Arrays.equals(iArrPlus2, this.settingData)) {
                        return;
                    }
                    int[] iArr2 = this.settingData;
                    ArraysKt.copyInto$default(iArrPlus2, iArr2, 0, 0, iArr2.length, 6, (Object) null);
                    this.list.clear();
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_17");
                    if (settingUpdateEntity != null) {
                        this.list.add(settingUpdateEntity.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[4])));
                    }
                    SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("_103_punch");
                    if (settingUpdateEntity2 != null) {
                        this.list.add(settingUpdateEntity2.setValue(Integer.valueOf((msgMgr2.mCanbusInfoInt[8] - 2) - 3)).setProgress(msgMgr2.mCanbusInfoInt[8] - 2));
                    }
                    SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_8");
                    if (settingUpdateEntity3 != null) {
                        this.list.add(settingUpdateEntity3.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[10])));
                    }
                    SettingUpdateEntity settingUpdateEntity4 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_9");
                    if (settingUpdateEntity4 != null) {
                        this.list.add(settingUpdateEntity4.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[11])));
                    }
                    SettingUpdateEntity settingUpdateEntity5 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_10");
                    if (settingUpdateEntity5 != null) {
                        this.list.add(settingUpdateEntity5.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[12])));
                    }
                    SettingUpdateEntity settingUpdateEntity6 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_11");
                    if (settingUpdateEntity6 != null) {
                        this.list.add(settingUpdateEntity6.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[13])));
                    }
                    SettingUpdateEntity settingUpdateEntity7 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("outlander_simple_car_set_12");
                    if (settingUpdateEntity7 != null) {
                        this.list.add(settingUpdateEntity7.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[14])));
                    }
                    SettingUpdateEntity settingUpdateEntity8 = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("amplifier_switch");
                    if (settingUpdateEntity8 != null) {
                        this.list.add(settingUpdateEntity8.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[15])));
                    }
                    msgMgr2.updateGeneralSettingData(this.list);
                    msgMgr2.updateSettingActivity(bundle);
                }
            }
        });
        sparseArray.append(64, new MsgMgr$initParsers$1$3(this));
        sparseArray.append(80, new Parser() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$4
            private final ArrayList<SettingUpdateEntity<Object>> list;
            private final SparseArray<SettingsParseHelper> mHelperArray;

            {
                super(this.this$0, "【0x50】设置项信息");
                SparseArray<SettingsParseHelper> sparseArray2 = new SparseArray<>();
                sparseArray2.put(0, new SettingsParseHelper("_328_00", new Function1<Integer, Integer>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$4$mHelperArray$1$1
                    public final Integer invoke(int i) {
                        return Integer.valueOf(i - 1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }));
                sparseArray2.append(1, new SettingsParseHelper("_103_car_setting_title_1", null, 2, null));
                sparseArray2.append(2, new SettingsParseHelper("_328_02", new Function1<Integer, Integer>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$4$mHelperArray$1$2
                    public final Integer invoke(int i) {
                        return Integer.valueOf(Integer.valueOf(i).equals(192) ? 1 : 0);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }));
                sparseArray2.append(3, new SettingsParseHelper("_103_car_setting_title_2", null, 2, null));
                sparseArray2.append(4, new SettingsParseHelper("_103_car_setting_title_3", null, 2, null));
                sparseArray2.append(5, new SettingsParseHelper("_103_car_setting_title_4", null, 2, null));
                sparseArray2.append(6, new SettingsParseHelper("_103_car_setting_title_5", null, 2, null));
                sparseArray2.append(7, new SettingsParseHelper("_103_car_setting_title_6", null, 2, null));
                sparseArray2.append(8, new SettingsParseHelper("_103_car_setting_title_7", null, 2, null));
                sparseArray2.append(9, new SettingsParseHelper("_103_car_setting_title_8", null, 2, null));
                sparseArray2.append(10, new SettingsParseHelper("_103_car_setting_title_10", null, 2, null));
                sparseArray2.append(11, new SettingsParseHelper("_103_car_setting_title_11", null, 2, null));
                sparseArray2.append(12, new SettingsParseHelper("_103_car_setting_title_12", null, 2, null));
                sparseArray2.append(13, new SettingsParseHelper("_103_car_setting_title_13", null, 2, null));
                sparseArray2.append(14, new SettingsParseHelper("_103_car_setting_title_14", null, 2, null));
                sparseArray2.append(15, new SettingsParseHelper("_103_car_setting_title_15", null, 2, null));
                sparseArray2.append(16, new SettingsParseHelper("_103_car_setting_title_16", null, 2, null));
                sparseArray2.append(17, new SettingsParseHelper("_103_car_setting_title_17", null, 2, null));
                sparseArray2.append(18, new SettingsParseHelper("_103_car_setting_title_18", null, 2, null));
                sparseArray2.append(19, new SettingsParseHelper("_103_car_setting_title_19", null, 2, null));
                sparseArray2.append(20, new SettingsParseHelper("_103_car_setting_title_20", null, 2, null));
                sparseArray2.append(21, new SettingsParseHelper("_103_car_setting_title_21", null, 2, null));
                sparseArray2.append(22, new SettingsParseHelper("_165_eco_mode", null, 2, null));
                sparseArray2.append(23, new SettingsParseHelper("_103_car_setting_title_22", null, 2, null));
                sparseArray2.append(24, new SettingsParseHelper("_103_car_setting_title_23", null, 2, null));
                sparseArray2.append(25, new SettingsParseHelper("_103_car_setting_title_24", null, 2, null));
                sparseArray2.append(26, new SettingsParseHelper("_103_car_setting_title_25", null, 2, null));
                sparseArray2.append(27, new SettingsParseHelper("_103_car_setting_title_26", null, 2, null));
                sparseArray2.append(28, new SettingsParseHelper("_328_1c", new Function1<Integer, Integer>() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$4$mHelperArray$1$3
                    public final Integer invoke(int i) {
                        return Integer.valueOf(i - 1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Integer invoke(Integer num) {
                        return invoke(num.intValue());
                    }
                }));
                this.mHelperArray = sparseArray2;
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._328.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingsParseHelper settingsParseHelper = this.mHelperArray.get(this.this$0.mCanbusInfoInt[2]);
                if (settingsParseHelper != null) {
                    MsgMgr msgMgr = this.this$0;
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get(settingsParseHelper.getTitle());
                    if (settingUpdateEntity != null) {
                        this.list.clear();
                        ArrayList<SettingUpdateEntity<Object>> arrayList = this.list;
                        Integer numInvoke = settingsParseHelper.getParse().invoke(Integer.valueOf(msgMgr.mCanbusInfoInt[3]));
                        int iIntValue = numInvoke.intValue();
                        if (settingsParseHelper.getTitle().equals("_328_1c")) {
                            SettingUpdateEntity settingUpdateEntity2 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_328_1e");
                            if (settingUpdateEntity2 != null) {
                                this.list.add(settingUpdateEntity2.setEnable(iIntValue == 0));
                            }
                            SettingUpdateEntity settingUpdateEntity3 = (SettingUpdateEntity) msgMgr.mSettingItemIndexHashMap.get("_328_1d");
                            if (settingUpdateEntity3 != null) {
                                this.list.add(settingUpdateEntity3.setEnable(iIntValue == 1));
                            }
                        }
                        Unit unit = Unit.INSTANCE;
                        arrayList.add(settingUpdateEntity.setValue(numInvoke));
                        msgMgr.updateGeneralSettingData(this.list);
                        msgMgr.updateSettingActivity(null);
                    }
                }
            }
        });
        sparseArray.append(WorkQueueKt.MASK, new Parser() { // from class: com.hzbhd.canbus.car._328.MsgMgr$initParsers$1$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x7F】版本信息");
            }

            @Override // com.hzbhd.canbus.car._328.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005d  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10) {
        /*
            r5 = this;
            java.lang.String r10 = "currBand"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r10)
            java.lang.String r10 = "currentFreq"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r10)
            java.lang.String r10 = "psName"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r10)
            android.content.Context r9 = r5.mContext
            com.hzbhd.commontools.SourceConstantsDef$SOURCE_ID r10 = com.hzbhd.commontools.SourceConstantsDef.SOURCE_ID.FM
            java.lang.String r10 = r10.name()
            byte[] r0 = r5.mRadioInfoCommand
            int r1 = r7.hashCode()
            r2 = 2
            r3 = 0
            r4 = 3
            switch(r1) {
                case 64901: goto L51;
                case 64902: goto L45;
                case 69706: goto L3a;
                case 69707: goto L2f;
                case 69708: goto L24;
                default: goto L23;
            }
        L23:
            goto L5d
        L24:
            java.lang.String r1 = "FM3"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L2d
            goto L5d
        L2d:
            r1 = r4
            goto L5e
        L2f:
            java.lang.String r1 = "FM2"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L38
            goto L5d
        L38:
            r1 = r2
            goto L5e
        L3a:
            java.lang.String r1 = "FM1"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L43
            goto L5d
        L43:
            r1 = 1
            goto L5e
        L45:
            java.lang.String r1 = "AM2"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L4e
            goto L5d
        L4e:
            r1 = 18
            goto L5e
        L51:
            java.lang.String r1 = "AM1"
            boolean r1 = r7.equals(r1)
            if (r1 != 0) goto L5a
            goto L5d
        L5a:
            r1 = 17
            goto L5e
        L5d:
            r1 = r3
        L5e:
            r0[r4] = r1
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            java.lang.String r1 = "FM"
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r4 = 0
            boolean r7 = kotlin.text.StringsKt.contains$default(r7, r1, r3, r2, r4)
            if (r7 == 0) goto L77
            float r7 = java.lang.Float.parseFloat(r8)
            r8 = 100
            float r8 = (float) r8
            float r7 = r7 * r8
            int r7 = (int) r7
            goto L7b
        L77:
            int r7 = java.lang.Integer.parseInt(r8)
        L7b:
            r8 = 4
            r1 = r7 & 255(0xff, float:3.57E-43)
            byte r1 = (byte) r1
            r0[r8] = r1
            r8 = 5
            int r7 = r7 >> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            byte r7 = (byte) r7
            r0[r8] = r7
            r7 = 6
            byte r6 = (byte) r6
            r0[r7] = r6
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            r5.sendMediaMsg(r9, r10, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._328.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        this.mIsPhoneIncoming = true;
        byte[] bArr = {22, -64, 5, 1, 16, 2, (byte) phoneNumber.length};
        String str = new String(phoneNumber, Charsets.UTF_8);
        Charset UTF_16 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
        byte[] bytes = str.getBytes(UTF_16);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        this.mIsPhoneIncoming = false;
        byte[] bArr = {22, -64, 5, 3, 16, 2, (byte) phoneNumber.length};
        String str = new String(phoneNumber, Charsets.UTF_8);
        Charset UTF_16 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
        byte[] bytes = str.getBytes(UTF_16);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        byte[] bArr = new byte[7];
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = 5;
        bArr[3] = this.mIsPhoneIncoming ? (byte) 2 : (byte) 4;
        bArr[4] = 16;
        bArr[5] = 2;
        bArr[6] = (byte) phoneNumber.length;
        String str = new String(phoneNumber, Charsets.UTF_8);
        Charset UTF_16 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
        byte[] bytes = str.getBytes(UTF_16);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNullParameter(phoneNumber, "phoneNumber");
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 16, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) bMins, (byte) bHours24H});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._328.MsgMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m651dateTimeRepCanbus$lambda9(this.f$0);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dateTimeRepCanbus$lambda-9, reason: not valid java name */
    public static final void m651dateTimeRepCanbus$lambda9(MsgMgr this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CanbusMsgSender.sendMsg(this$0.mVersionReqCommand);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Intrinsics.checkNotNullParameter(songTitle, "songTitle");
        Intrinsics.checkNotNullParameter(songAlbum, "songAlbum");
        Intrinsics.checkNotNullParameter(songArtist, "songArtist");
        int i = stoagePath != 9 ? 8 : 9;
        if (Intrinsics.areEqual(this.mMeidaSongTitleRecord, songTitle)) {
            return;
        }
        this.mMeidaSongTitleRecord = songTitle;
        final byte[] bArr = {22, -64, (byte) i, 16, 1, 0};
        Charset UTF_16 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
        byte[] bytes = songTitle.getBytes(UTF_16);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        ArraysKt.plus(bArr, (byte) (bytes.length - 2));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
        bArr[4] = 2;
        Charset UTF_162 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_162, "UTF_16");
        final byte[] bytes2 = songArtist.getBytes(UTF_162);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        bArr[5] = (byte) (bytes2.length - 2);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._328.MsgMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m652musicInfoChange$lambda13$lambda12$lambda11(this.f$0, bArr, bytes2);
            }
        }, 333L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: musicInfoChange$lambda-13$lambda-12$lambda-11, reason: not valid java name */
    public static final void m652musicInfoChange$lambda13$lambda12$lambda11(MsgMgr this$0, byte[] this_run, byte[] it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.sendMediaMsg(this$0.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), ArraysKt.plus(this_run, ArraysKt.copyOfRange(it, 2, it.length)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        this.mMeidaSongTitleRecord = "";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 16, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(artist, "artist");
        Intrinsics.checkNotNullParameter(album, "album");
        final byte[] bArr = {22, -64, 11, 16, 1, 0};
        Charset UTF_16 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_16, "UTF_16");
        byte[] bytes = title.getBytes(UTF_16);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        ArraysKt.plus(bArr, (byte) (bytes.length - 2));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
        bArr[4] = 2;
        Charset UTF_162 = StandardCharsets.UTF_16;
        Intrinsics.checkNotNullExpressionValue(UTF_162, "UTF_16");
        final byte[] bytes2 = artist.getBytes(UTF_162);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        bArr[5] = (byte) (bytes2.length - 2);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._328.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m650btMusicId3InfoChange$lambda17$lambda16$lambda15(this.f$0, bArr, bytes2);
            }
        }, 333L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: btMusicId3InfoChange$lambda-17$lambda-16$lambda-15, reason: not valid java name */
    public static final void m650btMusicId3InfoChange$lambda17$lambda16$lambda15(MsgMgr this$0, byte[] this_run, byte[] it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(it, "$it");
        this$0.sendMediaMsg(this$0.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), ArraysKt.plus(this_run, ArraysKt.copyOfRange(it, 2, it.length)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        if (SharePreUtil.getIntValue(context, UiMgr.SHARE_IS_SUPPORT_PANORAMIC, 0) != 1 || key != 49) {
            return false;
        }
        CanbusMsgSender.sendMsg(UiMgr.INSTANCE.getPanoramicCommand());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0011\u001a\u00020\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0006H\u0016J\u0015\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/hzbhd/canbus/car/_328/MsgMgr$Parser;", "", NotificationCompat.CATEGORY_MESSAGE, "", "(Lcom/hzbhd/canbus/car/_328/MsgMgr;Ljava/lang/String;)V", "PARSE_LISTENERS_LENGTH", "", "canbusInfo", "", "getCanbusInfo", "()[I", "setCanbusInfo", "([I)V", "onParseListeners", "", "Lcom/hzbhd/canbus/interfaces/OnParseListener;", "[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "isDataChange", "", "parse", "", "dataLength", "setOnParseListeners", "()[Lcom/hzbhd/canbus/interfaces/OnParseListener;", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
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

    public final void updateSettings(String title, Object value) {
        SettingUpdateEntity<Object> settingUpdateEntity;
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        SettingUpdateEntity<Object> settingUpdateEntity2 = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity2 != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(settingUpdateEntity2.setValue(value));
            Log.i(TAG, "updateSettings: " + settingUpdateEntity2.getLeftListIndex() + "  " + settingUpdateEntity2.getRightListIndex() + "  " + value);
            if (Intrinsics.areEqual(title, "support_panorama") && (settingUpdateEntity = this.mSettingItemIndexHashMap.get("_23_enter_panoramic")) != null) {
                arrayList.add(settingUpdateEntity.setEnable(Intrinsics.areEqual(value, (Object) 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }
}
