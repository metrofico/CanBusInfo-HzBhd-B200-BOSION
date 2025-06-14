package com.hzbhd.canbus.msg_mgr;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import java.lang.reflect.InvocationTargetException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgrInterfaceUtil.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b4\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J&\u0010\u0015\u001a\u00020\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\"\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u0017H\u0016J\"\u0010!\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J\"\u0010'\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J\"\u0010(\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016JT\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010+\u001a\u00020%2\u0006\u0010,\u001a\u00020%2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001e2\u0006\u0010.\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\"\u0010/\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%H\u0016J*\u00100\u001a\u00020\r2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020%2\u0006\u00101\u001a\u00020\u001eH\u0016J\b\u00102\u001a\u00020\rH\u0016J\b\u00103\u001a\u00020\rH\u0016J\u001c\u00104\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u00105\u001a\u0004\u0018\u00010#H\u0016J\u0018\u00106\u001a\u00020\r2\u0006\u00107\u001a\u00020\u001e2\u0006\u00108\u001a\u00020%H\u0016J\u001a\u00109\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010:\u001a\u00020\u001eH\u0016J\u001a\u0010;\u001a\u00020%2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010:\u001a\u00020\u001eH\u0016Jp\u0010<\u001a\u00020\r2\u0006\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020\u001e2\u0006\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u001e2\u0006\u0010C\u001a\u00020\u001e2\u0006\u0010D\u001a\u00020\u001e2\u0006\u0010E\u001a\u00020\u001e2\u0006\u0010F\u001a\u00020%2\u0006\u0010G\u001a\u00020%2\u0006\u0010H\u001a\u00020%2\u0006\u0010I\u001a\u00020\u001eH\u0016J\b\u0010J\u001a\u00020\rH\u0016Jh\u0010K\u001a\u00020\r2\u0006\u0010L\u001a\u00020\u001e2\u0006\u0010M\u001a\u00020\u001e2\u0006\u0010N\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020\u001e2\u0006\u0010Q\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020\u001e2\u0006\u0010S\u001a\u00020\u001e2\u0006\u0010T\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020\u001e2\u0006\u0010V\u001a\u00020\u001e2\u0006\u0010W\u001a\u00020\u001eH\u0016Jv\u0010X\u001a\u00020\r2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\\\u001a\u00020\u001e2\u0006\u0010]\u001a\u00020\u001e2\u0006\u0010^\u001a\u00020\u001e2\u0006\u0010_\u001a\u00020\u001e2\u0006\u0010`\u001a\u00020\u001e2\u0006\u0010a\u001a\u00020%2\u0006\u0010b\u001a\u00020%2\u0006\u0010c\u001a\u00020\u001e2\b\u0010d\u001a\u0004\u0018\u00010\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010e\u001a\u00020\rH\u0016J\u0012\u0010f\u001a\u00020\r2\b\u0010g\u001a\u0004\u0018\u00010hH\u0016J\b\u0010i\u001a\u00020\u0001H\u0016J\u0012\u0010j\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\u001c\u0010k\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010l\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010m\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010n\u001a\u0004\u0018\u00010#H\u0016J\u001c\u0010o\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010p\u001a\u0004\u0018\u00010#H\u0016J\b\u0010q\u001a\u00020\rH\u0016JÍ\u0001\u0010r\u001a\u00020\r2\u0006\u0010s\u001a\u00020Z2\u0006\u0010t\u001a\u00020Z2\u0006\u0010u\u001a\u00020\u001e2\u0006\u0010v\u001a\u00020\u001e2\u0006\u0010w\u001a\u00020Z2\u0006\u0010x\u001a\u00020Z2\u0006\u0010y\u001a\u00020Z2\u0006\u0010z\u001a\u00020Z2\u0006\u0010{\u001a\u00020Z2\u0006\u0010|\u001a\u00020Z2\b\u0010}\u001a\u0004\u0018\u00010\u00172\b\u0010~\u001a\u0004\u0018\u00010\u00172\b\u0010\u007f\u001a\u0004\u0018\u00010\u00172\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010Y\u001a\u00020Z2\u0007\u0010\u0082\u0001\u001a\u00020\u001e2\u0006\u0010b\u001a\u00020%2\b\u0010\u0083\u0001\u001a\u00030\u0081\u00012\t\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u0087\u0001\u001a\u00020%H\u0016J\u0014\u0010\u0088\u0001\u001a\u00020\r2\t\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\t\u0010\u008a\u0001\u001a\u00020\rH\u0016J\u0015\u0010\u008b\u0001\u001a\u00020\r2\n\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001H\u0016J\t\u0010\u008e\u0001\u001a\u00020\rH\u0016J\t\u0010\u008f\u0001\u001a\u00020\rH\u0016J\u0013\u0010\u0090\u0001\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J.\u0010\u0091\u0001\u001a\u00020\r2\u0007\u0010\u0092\u0001\u001a\u00020\u001e2\u0007\u0010\u0093\u0001\u001a\u00020\u001e2\u0007\u0010\u0094\u0001\u001a\u00020\u001e2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\t\u0010\u0095\u0001\u001a\u00020\rH\u0016J\t\u0010\u0096\u0001\u001a\u00020\rH\u0016J\t\u0010\u0097\u0001\u001a\u00020\rH\u0016J<\u0010\u0098\u0001\u001a\u00020\r2\u0007\u0010\u0099\u0001\u001a\u00020\u001e2\t\u0010\u009a\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00172\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u009d\u0001\u001a\u00020\u001eH\u0016J\u001e\u0010\u009e\u0001\u001a\u00020\r2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010#H\u0016J\u0015\u0010 \u0001\u001a\u00020\r2\n\u0010¡\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0016J\u0014\u0010£\u0001\u001a\u00020\r2\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0017H\u0016J\u0012\u0010¥\u0001\u001a\u00020\r2\u0007\u0010¦\u0001\u001a\u00020%H\u0016J\t\u0010§\u0001\u001a\u00020\rH\u0016J\u009c\u0001\u0010¨\u0001\u001a\u00020\r2\u0006\u0010s\u001a\u00020Z2\u0006\u0010t\u001a\u00020Z2\u0006\u0010u\u001a\u00020\u001e2\u0006\u0010v\u001a\u00020\u001e2\u0006\u0010w\u001a\u00020Z2\u0006\u0010x\u001a\u00020Z2\u0006\u0010y\u001a\u00020Z2\b\u0010z\u001a\u0004\u0018\u00010\u00172\u0006\u0010{\u001a\u00020Z2\u0006\u0010|\u001a\u00020Z2\b\u0010}\u001a\u0004\u0018\u00010\u00172\b\u0010~\u001a\u0004\u0018\u00010\u00172\b\u0010\u007f\u001a\u0004\u0018\u00010\u00172\u0007\u0010\u0080\u0001\u001a\u00020\u001e2\u0007\u0010©\u0001\u001a\u00020Z2\u0006\u0010b\u001a\u00020%2\u0007\u0010ª\u0001\u001a\u00020\u001eH\u0016J\u0014\u0010«\u0001\u001a\u00020\r2\t\u0010¬\u0001\u001a\u0004\u0018\u00010\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\u0007\u001a\u0004\u0018\u00010\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\u00ad\u0001"}, d2 = {"Lcom/hzbhd/canbus/msg_mgr/MsgMgrInterfaceUtil;", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mMsgMgrInterface", "getMMsgMgrInterface", "()Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "mMsgMgrInterface$delegate", "Lkotlin/Lazy;", "aMapCallBack", "", "bundle", "Landroid/os/Bundle;", "afterServiceNormalSetting", "atvDestdroy", "atvInfoChange", "auxInDestdroy", "auxInInfoChange", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, "", LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btMusicInfoChange", "btMusiceDestdroy", "btPhoneCallLogInfoChange", "index", "", "type", "number", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "cameraDestroy", "cameraInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "customLongClick", "key", "customShortClick", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "destroyCommand", "deviceStatusInfoChange", "btOn", "discRadom", "discRepeat", "discExsit", "sdCardIn", "usbIn", "radioSt", "mute", "singleCycle", "fullCurve", "folderLoop", "randomFolder", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getBackCameraUiService", "backCameraUiService", "Lcom/hzbhd/canbus/park/BackCameraUiService;", "getInstance", "initCommand", "linInfoChange", "linInfo", "mcuErrorState", "errorInfo", "medianCalibration", "calibrationInfo", "musicDestroy", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "musicLrcInfoChange", "lrc", "notifyBtStatusChange", "onAMapCallBack", "aMapEntity", "Lcom/hzbhd/canbus/util/amap/AMapEntity;", "onAccOff", "onAccOn", "onHandshake", "onKeyEvent", "hotKey", "keyStateOrArg1", "beepOrArg2", "onPowerOff", "onSleep", "radioDestroy", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "serialDataChange", "serialData", "setMgrRefreshUiInterface", "mgrRefreshUiInterface", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "sourceSwitchChange", LcdInfoShare.DspInfo.source, "sourceSwitchNoMediaInfoChange", "isPowerOff", "videoDestroy", "videoInfoChange", "playMode", "duration", "voiceControlInfo", "action", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgrInterfaceUtil implements MsgMgrInterface {
    private final Context context;

    /* renamed from: mMsgMgrInterface$delegate, reason: from kotlin metadata */
    private final Lazy mMsgMgrInterface;

    public MsgMgrInterfaceUtil(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.mMsgMgrInterface = LazyKt.lazy(new Function0<MsgMgrInterface>() { // from class: com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil$mMsgMgrInterface$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MsgMgrInterface invoke() throws IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException {
                try {
                    Object objNewInstance = Class.forName("com.hzbhd.canbus.car._" + CanbusConfig.INSTANCE.getCanType() + ".MsgMgr").getConstructors()[0].newInstance(new Object[0]);
                    Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type com.hzbhd.canbus.interfaces.MsgMgrInterface");
                    return ((MsgMgrInterface) objNewInstance).getInstance();
                } catch (Exception e) {
                    LogUtil.showLog("MsgMgrFactory getCanMsgMgr :" + e);
                    return null;
                }
            }
        });
    }

    public final Context getContext() {
        return this.context;
    }

    public final MsgMgrInterface getMMsgMgrInterface() {
        return (MsgMgrInterface) this.mMsgMgrInterface.getValue();
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.afterServiceNormalSetting(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity mgrRefreshUiInterface) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.setMgrRefreshUiInterface(mgrRefreshUiInterface);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] errorInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.mcuErrorState(context, errorInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.canbusInfoChange(context, canbusInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] serialData) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.serialDataChange(context, serialData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.getBackCameraUiService(backCameraUiService);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.initCommand(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.destroyCommand();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.radioInfoChange(currClickPresetIndex, currBand, currentFreq, psName, isStereo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.radioDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.dateTimeRepCanbus(bYearTotal, bYear2Dig, bMonth, bDay, bHours, bMins, bSecond, bHours24H, systemDateFormat, isFormat24H, isFormatPm, isGpsTime, dayOfWeek);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.videoInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.videoDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playModel, playIndex, isPlaying, totalTime, songTitle, songAlbum, songArtist, isReportFromPlay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.dtvInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.discInfoChange(playModel, currentTime, playTitleNo, position, currentPlayNo, currentTotalNo, discType, isUnMuted, isPlaying, playStat, song, album, artist);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int btOn, int discRadom, int discRepeat, int discExsit, int sdCardIn, int usbIn, int radioSt, int mute, int singleCycle, int fullCurve, int folderLoop, int randomFolder) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.deviceStatusInfoChange(btOn, discRadom, discRepeat, discExsit, sdCardIn, usbIn, radioSt, mute, singleCycle, fullCurve, folderLoop, randomFolder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.currentVolumeInfoChange(volValue, isMute);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneIncomingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneOutGoingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneTalkingInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneHangUpInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneStatusInfoChange(callStatus, phoneNumber, isHfpConnected, isCallingFlag, isMicMute, isAudioTransferTowardsAG, batteryStatus, signalValue, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneTalkingWithTimeInfoChange(phoneNumber, isMicMute, isAudioTransferTowardsAG, time);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int index, int type, String number) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btPhoneCallLogInfoChange(index, type, number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void notifyBtStatusChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.notifyBtStatusChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusicInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusiceDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.btMusicId3InfoChange(title, artist, album);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.auxInInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.auxInDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.atvInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.atvDestdroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.sourceSwitchNoMediaInfoChange(isPowerOff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.sourceSwitchChange(source);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String lrc) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.musicLrcInfoChange(lrc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.cameraInfoChange();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.cameraDestroy();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String action) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.voiceControlInfo(action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                return mMsgMgrInterface.customLongClick(context, key);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int key) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                return mMsgMgrInterface.customShortClick(context, key);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.aMapCallBack(bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int hotKey, int keyStateOrArg1, int beepOrArg2, Bundle bundle) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onKeyEvent(hotKey, keyStateOrArg1, beepOrArg2, bundle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAMapCallBack(aMapEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAccOn();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onAccOff();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onSleep() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onSleep();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onPowerOff() {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onPowerOff();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.onHandshake(context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] linInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.linInfoChange(context, linInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] calibrationInfo) {
        try {
            MsgMgrInterface mMsgMgrInterface = getMMsgMgrInterface();
            if (mMsgMgrInterface != null) {
                mMsgMgrInterface.medianCalibration(context, calibrationInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }
}
