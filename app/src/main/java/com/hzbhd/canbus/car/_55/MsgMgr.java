package com.hzbhd.canbus.car._55;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.ui.util.BaseUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b-\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u001aH\u0002J\"\u0010\u001e\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010$\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010%\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\"\u0010&\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0016J\u001c\u0010'\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010(\u001a\u0004\u0018\u00010 H\u0016J\b\u0010)\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002Jp\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-2\u0006\u0010/\u001a\u00020-2\u0006\u00100\u001a\u00020-2\u0006\u00101\u001a\u00020-2\u0006\u00102\u001a\u00020-2\u0006\u00103\u001a\u00020-2\u0006\u00104\u001a\u00020-2\u0006\u00105\u001a\u00020-2\u0006\u00106\u001a\u00020\"2\u0006\u00107\u001a\u00020\"2\u0006\u00108\u001a\u00020\"2\u0006\u00109\u001a\u00020-H\u0016J\b\u0010:\u001a\u00020\u001aH\u0002J\b\u0010;\u001a\u00020\u001aH\u0002J\b\u0010<\u001a\u00020\u001aH\u0002J\b\u0010=\u001a\u00020\u001aH\u0002J\u000e\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\"J\u0018\u0010@\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\u0006\u0010A\u001a\u00020\"JÄ\u0001\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020D2\u0006\u0010I\u001a\u00020D2\u0006\u0010J\u001a\u00020D2\u0006\u0010K\u001a\u00020D2\u0006\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020D2\b\u0010N\u001a\u0004\u0018\u00010O2\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010Q\u001a\u0004\u0018\u00010O2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020D2\u0006\u0010U\u001a\u00020-2\u0006\u0010V\u001a\u00020\"2\u0006\u0010W\u001a\u00020S2\b\u0010X\u001a\u0004\u0018\u00010O2\b\u0010Y\u001a\u0004\u0018\u00010O2\b\u0010Z\u001a\u0004\u0018\u00010O2\u0006\u0010[\u001a\u00020\"H\u0016J\b\u0010\\\u001a\u00020\u001aH\u0002J\b\u0010]\u001a\u00020\u001aH\u0002J\u000e\u0010^\u001a\u00020\u001a2\u0006\u0010_\u001a\u00020\"J\b\u0010`\u001a\u00020\u001aH\u0002J\b\u0010a\u001a\u00020\u001aH\u0002J\b\u0010b\u001a\u00020\u001aH\u0002J\b\u0010c\u001a\u00020\u001aH\u0002J\b\u0010d\u001a\u00020\u001aH\u0002J\b\u0010e\u001a\u00020\u001aH\u0002J6\u0010f\u001a\u00020\u001a2\u0006\u0010g\u001a\u00020-2\b\u0010h\u001a\u0004\u0018\u00010O2\b\u0010i\u001a\u0004\u0018\u00010O2\b\u0010j\u001a\u0004\u0018\u00010O2\u0006\u0010k\u001a\u00020-H\u0016J\b\u0010l\u001a\u00020\u001aH\u0002J\b\u0010m\u001a\u00020\u001aH\u0002J\b\u0010n\u001a\u00020\u001aH\u0002J \u0010o\u001a\u00020\u001a2\u0006\u0010p\u001a\u00020-2\u0006\u0010q\u001a\u00020 2\u0006\u0010r\u001a\u00020-H\u0002J\u001a\u0010s\u001a\u00020\u001a2\u0006\u0010p\u001a\u00020-2\b\b\u0002\u0010t\u001a\u00020 H\u0002J\b\u0010u\u001a\u00020\u001aH\u0002J\b\u0010v\u001a\u00020\u001aH\u0002J\u0010\u0010w\u001a\u00020\u001a2\u0006\u0010x\u001a\u00020 H\u0002J\b\u0010y\u001a\u00020\u001aH\u0002J\u0010\u0010z\u001a\u00020\u001a2\u0006\u0010{\u001a\u00020\"H\u0016J\u0006\u0010|\u001a\u00020\u001aJ\u0098\u0001\u0010}\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020D2\u0006\u0010F\u001a\u00020-2\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020D2\u0006\u0010I\u001a\u00020D2\u0006\u0010J\u001a\u00020D2\b\u0010K\u001a\u0004\u0018\u00010O2\u0006\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020D2\b\u0010N\u001a\u0004\u0018\u00010O2\b\u0010P\u001a\u0004\u0018\u00010O2\b\u0010Q\u001a\u0004\u0018\u00010O2\u0006\u0010R\u001a\u00020-2\u0006\u0010~\u001a\u00020D2\u0006\u0010V\u001a\u00020\"2\u0006\u0010\u007f\u001a\u00020-H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u0080\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_55/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "mOriginalCarDevicePageUiSet", "Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "getMOriginalCarDevicePageUiSet", "()Lcom/hzbhd/canbus/ui_set/OriginalCarDevicePageUiSet;", "mUiMgr", "Lcom/hzbhd/canbus/car/_55/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_55/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_55/UiMgr;)V", "afterServiceNormalSetting", "", "context", "airConditioning", "basicCarInfo", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "canbusInfoChange", "canbusInfo", "carDetailsInfo", "carInfo", "dateTimeRepCanbus", "bYearTotal", "", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "doorLock", "driverAssistanceSystemSetting", "drivingPosturePersonalization", "light", "mEnterOrExitAux", "bool", "mForceReverse", "isReverse", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "oilConsumption", "oilConsumptionHistory", "openRightCamera", "isOpen", "originalCarScreen", "originalVehiclePowerAmplifierStatus", "panelButton", "powerTailgateSettingStatus", "radar", "radioInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "radioPreStoredStationInfo", "remoteControl", "requestSourceCut", "sendBtPhoneNumber", "d0", "d1t30", "d31", "sendVoiceSource", "d1t12", "set0x1FData", "set0xA4Data", "set0xF0Data", "bytes", "setDisplaySettingStatus", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSetting", "videoInfoChange", "playMode", "duration", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;
    public Context mContext;
    private final OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    public UiMgr mUiMgr;

    private static final int radar$assignRadarProgress(int i) {
        if (i == 1) {
            return 4;
        }
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i != 4 ? 0 : 1;
        }
        return 2;
    }

    public MsgMgr() {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(BaseUtil.INSTANCE.getContext()).getOriginalCarDevicePageUiSet(BaseUtil.INSTANCE.getContext());
        Intrinsics.checkNotNullExpressionValue(originalCarDevicePageUiSet, "getCanUiMgr(context).get…rDevicePageUiSet(context)");
        this.mOriginalCarDevicePageUiSet = originalCarDevicePageUiSet;
    }

    public final int[] getFrame() {
        int[] iArr = this.frame;
        if (iArr != null) {
            return iArr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("frame");
        return null;
    }

    public final void setFrame(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.frame = iArr;
    }

    public final UiMgr getMUiMgr() {
        UiMgr uiMgr = this.mUiMgr;
        if (uiMgr != null) {
            return uiMgr;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mUiMgr");
        return null;
    }

    public final void setMUiMgr(UiMgr uiMgr) {
        Intrinsics.checkNotNullParameter(uiMgr, "<set-?>");
        this.mUiMgr = uiMgr;
    }

    public final Context getMContext() {
        Context context = this.mContext;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mContext");
        return null;
    }

    public final void setMContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[5];
        bArr[0] = 22;
        bArr[1] = -75;
        bArr[2] = (byte) (isFormat24H ? bHours24H : bHours);
        byte b = (byte) bMins;
        bArr[3] = b;
        byte b2 = (byte) bSecond;
        bArr[4] = b2;
        CanbusMsgSender.sendMsg(bArr);
        byte[] bArr2 = new byte[12];
        bArr2[0] = 22;
        bArr2[1] = -53;
        bArr2[2] = 0;
        bArr2[3] = (byte) (isFormat24H ? bHours24H : bHours);
        bArr2[4] = b;
        bArr2[5] = b2;
        bArr2[6] = 0;
        bArr2[7] = isFormat24H ? (byte) 1 : (byte) 0;
        bArr2[8] = (byte) bYear2Dig;
        bArr2[9] = (byte) bMonth;
        bArr2[10] = (byte) bDay;
        bArr2[11] = 0;
        CanbusMsgSender.sendMsg(bArr2);
    }

    private final void sendBtPhoneNumber(int d0, byte[] d1t30, int d31) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(ArraysKt.plus(new byte[]{22, -25, (byte) d0}, d1t30), new byte[]{(byte) d31}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNull(phoneNumber);
        sendBtPhoneNumber(1, MsgMgrKt.restrict$default(phoneNumber, 30, 0, 4, null), 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNull(phoneNumber);
        sendBtPhoneNumber(2, MsgMgrKt.restrict$default(phoneNumber, 30, 0, 4, null), 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNull(phoneNumber);
        sendBtPhoneNumber(3, MsgMgrKt.restrict$default(phoneNumber, 30, 0, 4, null), 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        Intrinsics.checkNotNull(phoneNumber);
        sendBtPhoneNumber(4, MsgMgrKt.restrict$default(phoneNumber, 30, 0, 4, null), 0);
    }

    static /* synthetic */ void sendVoiceSource$default(MsgMgr msgMgr, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        msgMgr.sendVoiceSource(i, bArr);
    }

    private final void sendVoiceSource(int d0, byte[] d1t12) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -31}, MsgMgrKt.restrict(ArraysKt.plus(new byte[]{(byte) d0}, d1t12), 13, 32)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        sendVoiceSource$default(this, 0, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        if (currBand != null) {
            switch (currBand.hashCode()) {
                case 64901:
                    if (currBand.equals("AM1")) {
                        i = 4;
                        break;
                    } else {
                        return;
                    }
                case 64902:
                    if (currBand.equals("AM2")) {
                        i = 5;
                        break;
                    } else {
                        return;
                    }
                case 69706:
                    if (currBand.equals("FM1")) {
                        i = 1;
                        break;
                    } else {
                        return;
                    }
                case 69707:
                    if (currBand.equals("FM2")) {
                        i = 2;
                        break;
                    } else {
                        return;
                    }
                case 69708:
                    if (currBand.equals("FM3")) {
                        i = 3;
                        break;
                    } else {
                        return;
                    }
                default:
                    return;
            }
            Intrinsics.checkNotNull(currentFreq);
            sendVoiceSource(i, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(currClickPresetIndex, currBand, 8, currentFreq));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        sendVoiceSource$default(this, stoagePath != 9 ? 13 : 14, null, 2, null);
        Intrinsics.checkNotNull(songAlbum);
        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(HotKeyConstant.K_CARPLAY, songAlbum, Charsets.UTF_16BE, 32, 10);
        Intrinsics.checkNotNull(songArtist);
        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(HotKeyConstant.K_ANDROIDAUTO, songArtist, Charsets.UTF_16BE, 32, 10);
        Intrinsics.checkNotNull(songTitle);
        com.hzbhd.canbus.car._404.MsgMgrKt.sendTextInfo(HotKeyConstant.K_ANDROIDAUTO, songTitle, Charsets.UTF_16BE, 32, 10);
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -104}, MsgMgrKt.restrict$default(new byte[]{0, 0, 0, 0, currentMinute, currentSecond}, 16, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        sendVoiceSource$default(this, stoagePath != 9 ? 13 : 14, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        if (context != null) {
            setMContext(context);
            UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
            Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._55.UiMgr");
            setMUiMgr((UiMgr) canUiMgr);
            SingletonForKt.INSTANCE.init(context, getMUiMgr());
        }
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            basicCarInfo();
            return;
        }
        if (i == 18) {
            carDetailsInfo();
            return;
        }
        if (i == 22) {
            oilConsumption();
            return;
        }
        if (i == 23) {
            oilConsumptionHistory();
            return;
        }
        if (i == 31) {
            set0x1FData();
            return;
        }
        if (i == 33) {
            panelButton();
            return;
        }
        if (i == 65) {
            radar();
            return;
        }
        if (i == 117) {
            powerTailgateSettingStatus();
            return;
        }
        if (i == 164) {
            set0xA4Data();
            return;
        }
        if (i == 166) {
            originalVehiclePowerAmplifierStatus();
            return;
        }
        if (i == 224) {
            requestSourceCut();
            return;
        }
        if (i == 232) {
            originalCarScreen();
            return;
        }
        if (i == 240) {
            Intrinsics.checkNotNull(canbusInfo);
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 49) {
            airConditioning();
            return;
        }
        if (i == 50) {
            carInfo();
            return;
        }
        if (i == 132) {
            radioInfo();
            return;
        }
        if (i != 133) {
            switch (i) {
                case 100:
                    drivingPosturePersonalization();
                    break;
                case 101:
                    doorLock();
                    break;
                case 102:
                    remoteControl();
                    break;
                case 103:
                    light();
                    break;
                case 104:
                    driverAssistanceSystemSetting();
                    break;
                case 105:
                    setDisplaySettingStatus();
                    break;
            }
            return;
        }
        radioPreStoredStationInfo();
    }

    private final void set0x1FData() {
        GeneralHybirdData.powerBatteryLevel = (DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 7) / 11) % 9;
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(getFrame()[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(getFrame()[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(getFrame()[3]);
        updateHybirdActivity(null);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    public final OriginalCarDevicePageUiSet getMOriginalCarDevicePageUiSet() {
        return this.mOriginalCarDevicePageUiSet;
    }

    private final void set0xA4Data() {
        int i = getFrame()[12];
        String str = "";
        GeneralOriginalCarDeviceData.cdStatus = i != 1 ? i != 2 ? i != 3 ? i != 6 ? i != 9 ? i != 12 ? i != 13 ? "" : "LOADING" : "EJECT" : "FR" : "STOP" : "FF" : "PLAY" : "PAUSE";
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 4);
        if (intFromByteWithBit == 13) {
            str = "USB";
        } else if (intFromByteWithBit == 14) {
            str = "Ipod";
        }
        GeneralOriginalCarDeviceData.runningState = str;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = this.mOriginalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setRowTopBtnAction(new String[0]);
        originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"up", "power", "down"});
        List<OriginalCarDevicePageUiSet.Item> items = originalCarDevicePageUiSet.getItems();
        items.clear();
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Current_track", String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[5], getFrame()[6]))));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Total_repertoire", String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[7]))));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_play_time", getFrame()[9] + " : " + getFrame()[10]));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Playback_progress", getFrame()[11] + " %"));
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    public final void mForceReverse(Context context, boolean isReverse) {
        super.forceReverse(context, isReverse);
    }

    public final void openRightCamera(boolean isOpen) {
        switchFCamera(getMContext(), isOpen);
    }

    public final void mEnterOrExitAux(boolean bool) {
        if (bool) {
            enterAuxIn2();
        } else {
            exitAuxIn2();
        }
    }

    private final void requestSourceCut() {
        switch (getFrame()[2]) {
            case 32:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 77);
                break;
            case 33:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 76);
                break;
            case 34:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 139);
                break;
            case 35:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 140);
                break;
            case 36:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 141);
                break;
            case 37:
                realKeyClick4(BaseUtil.INSTANCE.getContext(), 53);
                break;
        }
    }

    private final void originalVehiclePowerAmplifierStatus() {
        GeneralAmplifierData.volume = getFrame()[2];
        GeneralAmplifierData.frontRear = com.hzbhd.canbus.car._350.MsgMgrKt.reverse(getFrame()[4], 18) - 9;
        GeneralAmplifierData.leftRight = getFrame()[3] - 9;
        GeneralAmplifierData.bandBass = getFrame()[5];
        GeneralAmplifierData.bandTreble = getFrame()[7];
        GeneralAmplifierData.bandMiddle = getFrame()[6];
        updateAmplifierActivity(null);
        SingletonForKt.INSTANCE.setAmplifierData(getFrame());
        updateSettingActivity(null);
    }

    private final void originalCarScreen() {
        SingletonForKt.INSTANCE.setOriginalCarData(getFrame());
        updateSettingActivity(null);
    }

    private final void radioPreStoredStationInfo() {
        List<SongListEntity> listListOf;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = this.mOriginalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setHaveSongList(true);
        originalCarDevicePageUiSet.setSongListShowIndex(false);
        int i = getFrame()[2];
        if (i == 1) {
            listListOf = CollectionsKt.listOf((Object[]) new SongListEntity[]{new SongListEntity("Preset 1: " + DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[3]) + " KHz"), new SongListEntity("Preset 2: " + DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[5]) + " KHz"), new SongListEntity("Preset 3: " + DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[7]) + " KHz"), new SongListEntity("Preset 4: " + DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[9]) + " KHz"), new SongListEntity("Preset 5: " + DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[11]) + " KHz"), new SongListEntity("Preset 6: " + DataHandleUtils.getMsbLsbResult(getFrame()[14], getFrame()[13]) + " KHz")});
        } else if (i != 2) {
            return;
        } else {
            listListOf = CollectionsKt.listOf((Object[]) new SongListEntity[]{new SongListEntity("Preset 1: " + DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[3]) + " MHz"), new SongListEntity("Preset 2: " + DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[5]) + " MHz"), new SongListEntity("Preset 3: " + DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[7]) + " MHz"), new SongListEntity("Preset 4: " + DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[9]) + " MHz"), new SongListEntity("Preset 5: " + DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[11]) + " MHz"), new SongListEntity("Preset 6: " + DataHandleUtils.getMsbLsbResult(getFrame()[14], getFrame()[13]) + " MHz"), new SongListEntity("Preset 7: " + DataHandleUtils.getMsbLsbResult(getFrame()[16], getFrame()[15]) + " MHz"), new SongListEntity("Preset 8: " + DataHandleUtils.getMsbLsbResult(getFrame()[18], getFrame()[17]) + " MHz"), new SongListEntity("Preset 9: " + DataHandleUtils.getMsbLsbResult(getFrame()[20], getFrame()[19]) + " MHz"), new SongListEntity("Preset 10: " + DataHandleUtils.getMsbLsbResult(getFrame()[22], getFrame()[21]) + " MHz"), new SongListEntity("Preset 11: " + DataHandleUtils.getMsbLsbResult(getFrame()[24], getFrame()[23]) + " MHz"), new SongListEntity("Preset 12: " + DataHandleUtils.getMsbLsbResult(getFrame()[26], getFrame()[25]) + " MHz")});
        }
        GeneralOriginalCarDeviceData.songList = listListOf;
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void radioInfo() {
        String str;
        String string;
        int i = getFrame()[2];
        if (i == 0) {
            str = "FM";
        } else if (i != 1) {
            return;
        } else {
            str = "AM";
        }
        GeneralOriginalCarDeviceData.cdStatus = str;
        int i2 = getFrame()[6];
        String str2 = "";
        if (i2 == 0) {
            string = getMContext().getString(R.string.ford_original_status1);
        } else if (i2 == 1) {
            string = getMContext().getString(R.string.ford_original_status2);
        } else {
            string = i2 != 2 ? "" : getMContext().getString(R.string.ford_original_status3);
        }
        GeneralOriginalCarDeviceData.runningState = string;
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = this.mOriginalCarDevicePageUiSet;
        originalCarDevicePageUiSet.setRowTopBtnAction(new String[]{OriginalBtnAction.FM, OriginalBtnAction.AM, OriginalBtnAction.SCAN, OriginalBtnAction.REFRESH});
        originalCarDevicePageUiSet.setRowBottomBtnAction(new String[0]);
        List<OriginalCarDevicePageUiSet.Item> items = originalCarDevicePageUiSet.getItems();
        items.clear();
        StringBuilder sbAppend = new StringBuilder().append(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[3]));
        String str3 = GeneralOriginalCarDeviceData.cdStatus;
        if (Intrinsics.areEqual(str3, "FM")) {
            str2 = " MHZ";
        } else if (Intrinsics.areEqual(str3, "AM")) {
            str2 = " KHZ";
        }
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", sbAppend.append(str2).toString()));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_preset", String.valueOf(getFrame()[5])));
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private final void powerTailgateSettingStatus() {
        SingletonForKt.INSTANCE.setElectricDoor(getFrame());
        updateSettingActivity(null);
    }

    private final void setDisplaySettingStatus() {
        SingletonForKt.INSTANCE.setOther2Data(getFrame());
        updateSettingActivity(null);
    }

    private final void driverAssistanceSystemSetting() {
        SingletonForKt.INSTANCE.setDrivingAssistance(getFrame());
        updateSettingActivity(null);
    }

    private final void light() {
        SingletonForKt.INSTANCE.setLightingData(getFrame());
        updateSettingActivity(null);
    }

    private final void remoteControl() {
        SingletonForKt.INSTANCE.setRemoteControlData(getFrame());
        updateSettingActivity(null);
    }

    private final void doorLock() {
        SingletonForKt.INSTANCE.setLockData(getFrame());
        updateSettingActivity(null);
    }

    private final void drivingPosturePersonalization() {
        SingletonForKt.INSTANCE.setOtherData(getFrame());
        updateSettingActivity(null);
    }

    private final void radar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(4, radar$assignRadarProgress(getFrame()[6]), radar$assignRadarProgress(getFrame()[7]), radar$assignRadarProgress(getFrame()[8]), radar$assignRadarProgress(getFrame()[9]));
        RadarInfoUtil.setRearRadarLocationData(4, radar$assignRadarProgress(getFrame()[2]), radar$assignRadarProgress(getFrame()[3]), radar$assignRadarProgress(getFrame()[4]), radar$assignRadarProgress(getFrame()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, BaseUtil.INSTANCE.getContext());
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(getFrame()[12] != 1 ? 0 : 1));
        }
        updateSettingActivity(null);
    }

    private final void carInfo() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S55_Speed_1");
        if (item != null) {
            item.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) + " rpm");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S55_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateDriveDataActivity(null);
    }

    private final void airConditioning() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(getFrame()[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 4);
        if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (intFromByteWithBit == 7) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
        } else if (intFromByteWithBit == 10) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 4, 4);
        if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 4) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit2 == 7) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit2 == 10) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i = getFrame()[8];
        String str2 = "HIGH";
        if (i != 254) {
            str = i != 255 ? (i * 0.5d) + " °C" : "HIGH";
        } else {
            str = "LOW";
        }
        GeneralAirData.front_left_temperature = str;
        int i2 = getFrame()[9];
        if (i2 == 254) {
            str2 = "LOW";
        } else if (i2 != 255) {
            str2 = (i2 * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        GeneralAirData.rear_wind_level = getFrame()[10];
        updateAirActivity(getMContext(), 1004);
    }

    private final void panelButton() {
        int i = getFrame()[3];
        int i2 = getFrame()[2];
        if (i2 == 0) {
            realKeyLongClick1(getMContext(), 0, i);
        }
        if (i2 == 69) {
            realKeyLongClick1(getMContext(), 7, i);
            return;
        }
        if (i2 == 70) {
            realKeyLongClick1(getMContext(), 8, i);
            return;
        }
        if (i2 == 87) {
            realKeyLongClick1(getMContext(), 45, i);
            return;
        }
        if (i2 == 88) {
            realKeyLongClick1(getMContext(), 46, i);
            return;
        }
        switch (i2) {
            case 91:
                realKeyLongClick1(getMContext(), 48, i);
                break;
            case 92:
                realKeyLongClick1(getMContext(), 47, i);
                break;
            case 93:
                realKeyLongClick1(getMContext(), 45, i);
                break;
            case 94:
                realKeyLongClick1(getMContext(), 46, i);
                break;
        }
    }

    private final void oilConsumptionHistory() {
        SingletonForKt.INSTANCE.setHistoryOilConsumptionData(getFrame());
        updateDriveDataActivity(null);
    }

    private final void oilConsumption() {
        SingletonForKt.INSTANCE.setOilConsumptionData(getFrame());
        updateDriveDataActivity(null);
    }

    private final void carDetailsInfo() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        updateDoorView(getMContext());
    }

    private final void basicCarInfo() {
        int i = 5;
        int i2 = getFrame()[5];
        int i3 = getFrame()[4];
        if (i3 == 0) {
            realKeyLongClick1(getMContext(), 0, i2);
        } else if (i3 == 1) {
            realKeyLongClick1(getMContext(), 7, i2);
        } else if (i3 == 2) {
            realKeyLongClick1(getMContext(), 8, i2);
        } else if (i3 == 4) {
            realKeyLongClick1(getMContext(), HotKeyConstant.K_SPEECH, i2);
        } else if (i3 == 5) {
            realKeyLongClick1(getMContext(), 14, i2);
        } else if (i3 == 6) {
            realKeyLongClick1(getMContext(), HotKeyConstant.K_PHONE_OFF_RETURN, i2);
        } else {
            switch (i3) {
                case 8:
                    realKeyLongClick1(getMContext(), 45, i2);
                    break;
                case 9:
                    realKeyLongClick1(getMContext(), 46, i2);
                    break;
                case 10:
                    realKeyLongClick1(getMContext(), 151, i2);
                    break;
                case 11:
                    realKeyLongClick1(getMContext(), 2, i2);
                    break;
                case 12:
                    realKeyLongClick1(getMContext(), 3, i2);
                    break;
                case 13:
                    realKeyLongClick1(getMContext(), 45, i2);
                    break;
                case 14:
                    realKeyLongClick1(getMContext(), 46, i2);
                    break;
                case 15:
                    realKeyLongClick1(getMContext(), 49, i2);
                    break;
                default:
                    switch (i3) {
                        case 34:
                            realKeyLongClick1(getMContext(), 3, i2);
                            break;
                        case 35:
                            realKeyLongClick1(getMContext(), 76, i2);
                            break;
                        case 36:
                            realKeyLongClick1(getMContext(), 77, i2);
                            break;
                        case 37:
                            realKeyLongClick1(getMContext(), 139, i2);
                            break;
                        case 38:
                            realKeyLongClick1(getMContext(), 3, i2);
                            break;
                        case 39:
                            realKeyLongClick1(getMContext(), 3, i2);
                            break;
                    }
            }
        }
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte) getFrame()[9], (byte) getFrame()[8], 0, 5200, 16);
        updateParkUi(null, getMContext());
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S362_vehicleSpeedInfo_1");
        if (item != null) {
            item.setValue(getFrame()[3] + " km/h");
        }
        updateDriveDataActivity(null);
        int i4 = getFrame()[7];
        if (i4 >= 0 && i4 < 21) {
            i = 1;
        } else {
            if (21 <= i4 && i4 < 41) {
                i = 2;
            } else {
                if (41 <= i4 && i4 < 61) {
                    i = 3;
                } else {
                    if (61 <= i4 && i4 < 81) {
                        i = 4;
                    } else {
                        if (!(81 <= i4 && i4 < 101)) {
                            return;
                        }
                    }
                }
            }
        }
        setBacklightLevel(i);
    }

    public final void updateSetting() {
        updateSettingActivity(null);
    }
}
