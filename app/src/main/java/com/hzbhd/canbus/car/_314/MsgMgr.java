package com.hzbhd.canbus.car._314;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\t\n\u0002\b\u001e\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002J\u0010\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\u001dH\u0002J\b\u0010$\u001a\u00020\u001dH\u0002J\b\u0010%\u001a\u00020\u001dH\u0016JT\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u00162\b\u0010(\u001a\u0004\u0018\u00010\"2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*2\u0006\u0010.\u001a\u00020\u00162\u0006\u0010/\u001a\u00020\u00162\b\u00100\u001a\u0004\u0018\u000101H\u0016J\b\u00102\u001a\u00020\u001dH\u0002J\u001c\u00103\u001a\u00020\u001d2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016Jp\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u00020\u00162\u0006\u00107\u001a\u00020\u00162\u0006\u00108\u001a\u00020\u00162\u0006\u00109\u001a\u00020\u00162\u0006\u0010:\u001a\u00020\u00162\u0006\u0010;\u001a\u00020\u00162\u0006\u0010<\u001a\u00020\u00162\u0006\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020*2\u0006\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020*2\u0006\u0010A\u001a\u00020\u0016H\u0016Jv\u0010B\u001a\u00020\u001d2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010D\u001a\u00020\u00162\u0006\u0010E\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u00162\u0006\u0010H\u001a\u00020\u00162\u0006\u0010I\u001a\u00020\u00162\u0006\u0010J\u001a\u00020*2\u0006\u0010K\u001a\u00020*2\u0006\u0010L\u001a\u00020\u00162\b\u0010M\u001a\u0004\u0018\u00010N2\b\u0010O\u001a\u0004\u0018\u00010N2\b\u0010P\u001a\u0004\u0018\u00010NH\u0016J\b\u0010Q\u001a\u00020\u001dH\u0002J\b\u0010R\u001a\u00020\u001dH\u0016JÄ\u0001\u0010S\u001a\u00020\u001d2\u0006\u0010T\u001a\u00020\u001b2\u0006\u0010U\u001a\u00020\u001b2\u0006\u0010V\u001a\u00020\u00162\u0006\u0010W\u001a\u00020\u00162\u0006\u0010X\u001a\u00020\u001b2\u0006\u0010Y\u001a\u00020\u001b2\u0006\u0010Z\u001a\u00020\u001b2\u0006\u0010[\u001a\u00020\u001b2\u0006\u0010\\\u001a\u00020\u001b2\u0006\u0010]\u001a\u00020\u001b2\b\u0010^\u001a\u0004\u0018\u00010N2\b\u0010_\u001a\u0004\u0018\u00010N2\b\u0010`\u001a\u0004\u0018\u00010N2\u0006\u0010a\u001a\u00020b2\u0006\u0010C\u001a\u00020\u001b2\u0006\u0010c\u001a\u00020\u00162\u0006\u0010K\u001a\u00020*2\u0006\u0010d\u001a\u00020b2\b\u0010e\u001a\u0004\u0018\u00010N2\b\u0010f\u001a\u0004\u0018\u00010N2\b\u0010g\u001a\u0004\u0018\u00010N2\u0006\u0010h\u001a\u00020*H\u0016J\b\u0010i\u001a\u00020\u001dH\u0002J\u0010\u0010j\u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\"H\u0002J\b\u0010k\u001a\u00020\u001dH\u0002J6\u0010l\u001a\u00020\u001d2\u0006\u0010m\u001a\u00020\u00162\b\u0010n\u001a\u0004\u0018\u00010N2\b\u0010o\u001a\u0004\u0018\u00010N2\b\u0010p\u001a\u0004\u0018\u00010N2\u0006\u0010q\u001a\u00020\u0016H\u0016J\u001a\u0010r\u001a\u00020\u001d2\u0006\u0010s\u001a\u00020\u00162\b\b\u0002\u0010t\u001a\u00020\"H\u0002J\b\u0010u\u001a\u00020\u001dH\u0002J\u0010\u0010v\u001a\u00020\u001d2\u0006\u0010w\u001a\u00020\"H\u0002J\b\u0010x\u001a\u00020\u001dH\u0002J\b\u0010y\u001a\u00020\u001dH\u0002J\b\u0010z\u001a\u00020\u001dH\u0002J\u0010\u0010{\u001a\u00020\u001d2\u0006\u0010|\u001a\u00020*H\u0016J\b\u0010}\u001a\u00020\u001dH\u0002J\b\u0010~\u001a\u00020\u001dH\u0002J\u0006\u0010\u007f\u001a\u00020\u001dR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0080\u0001"}, d2 = {"Lcom/hzbhd/canbus/car/_314/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mUiMgr", "Lcom/hzbhd/canbus/car/_314/UiMgr;", "getMUiMgr", "()Lcom/hzbhd/canbus/car/_314/UiMgr;", "setMUiMgr", "(Lcom/hzbhd/canbus/car/_314/UiMgr;)V", "x11D2", "", "Ljava/lang/Integer;", "x12", "x21D0", "x22D1", "", "afterServiceNormalSetting", "", "air", "airSettings", MainAction.AMPLIFIER, "canbusInfo", "", "angle", "anotherSetting", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "phoneNumber", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "button", "canbusInfoChange", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "discInfoChange", "playModel", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "", LcdInfoShare.MediaInfo.album, LcdInfoShare.MediaInfo.artist, "door", "dtvInfoChange", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelBtn", "panelKnob", "radar", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendVoiceSource", "d0", "d1t12", "set0xA9Data", "set0xF0Data", "bytes", "setOliConsumptionData", "setOliConsumptionHistoryData", MainAction.SETTING, "sourceSwitchNoMediaInfoChange", "isPowerOff", "speed", "tyre", "updateSettingsData", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;
    public UiMgr mUiMgr;
    private Integer x11D2;
    private Integer x12;
    private Integer x21D0;
    private byte x22D1;

    private static final int radar$assignRadarProgress(int i) {
        if (i == 255) {
            return 0;
        }
        return i;
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

    public final Context getContext() {
        Context context = this.context;
        if (context != null) {
            return context;
        }
        Intrinsics.throwUninitializedPropertyAccessException("context");
        return null;
    }

    public final void setContext(Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.context = context;
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        GeneralTireData.isHaveSpareTire = false;
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._314.UiMgr");
        setMUiMgr((UiMgr) canUiMgr);
        InitUtilsKt.initSettingItemsIndexHashMap$default(context, getMUiMgr(), null, 4, null);
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, getMUiMgr(), null, 4, null);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
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
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        byte[] bArr = {22, -88};
        if (song == null) {
            return;
        }
        byte[] bytes = song.getBytes(Charsets.UTF_16LE);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(bytes, 32, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        sendVoiceSource$default(this, 10, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -53;
        bArr[2] = 0;
        bArr[3] = 0;
        bArr[4] = 0;
        if (isFormat24H) {
            bHours = bHours24H;
        }
        bArr[5] = (byte) bHours;
        bArr[6] = (byte) bMins;
        bArr[7] = isFormat24H ? (byte) 1 : (byte) 0;
        bArr[8] = isFormatPm ? (byte) 1 : (byte) 0;
        bArr[9] = 0;
        bArr[10] = 0;
        bArr[11] = 0;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendVoiceSource$default(this, 12, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        sendVoiceSource$default(this, 8, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        if (context == null) {
            return;
        }
        setContext(context);
        if (canbusInfo == null) {
            return;
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo ?: return)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            button();
            angle();
            return;
        }
        if (i == 18) {
            door();
            return;
        }
        if (i == 22) {
            setOliConsumptionData();
            return;
        }
        if (i == 23) {
            setOliConsumptionHistoryData();
            return;
        }
        if (i == 33) {
            panelBtn();
            return;
        }
        if (i == 34) {
            panelKnob(canbusInfo);
            return;
        }
        if (i == 49) {
            air();
            return;
        }
        if (i == 50) {
            speed();
            return;
        }
        if (i == 53) {
            airSettings();
            return;
        }
        if (i == 65) {
            radar();
            return;
        }
        if (i == 72) {
            tyre();
            return;
        }
        if (i == 166) {
            amplifier(canbusInfo);
            return;
        }
        if (i == 169) {
            set0xA9Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
        } else if (i == 97) {
            setting();
        } else {
            if (i != 98) {
                return;
            }
            anotherSetting();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0095  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0xA9Data() {
        /*
            r10 = this;
            android.content.Context r0 = r10.getContext()
            com.hzbhd.canbus.interfaces.UiMgrInterface r0 = com.hzbhd.canbus.ui_mgr.UiMgrFactory.getCanUiMgr(r0)
            android.content.Context r1 = r10.getContext()
            com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet r0 = r0.getOriginalCarDevicePageUiSet(r1)
            java.lang.String r1 = "getCanUiMgr(context).get…rDevicePageUiSet(context)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int[] r1 = r10.getFrame()
            r2 = 2
            r1 = r1[r2]
            boolean r1 = com.hzbhd.canbus.util.DataHandleUtils.getBoolBit7(r1)
            if (r1 == 0) goto L25
            java.lang.String r1 = "Power on"
            goto L27
        L25:
            java.lang.String r1 = "Power off"
        L27:
            com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData.cdStatus = r1
            int[] r1 = r10.getFrame()
            r1 = r1[r2]
            r3 = 4
            r4 = 3
            int r1 = com.hzbhd.canbus.util.DataHandleUtils.getIntFromByteWithBit(r1, r3, r4)
            java.lang.String r5 = "DVD"
            java.lang.String r6 = "AM"
            java.lang.String r7 = "CD"
            java.lang.String r8 = "FM"
            r9 = 1
            if (r1 == r9) goto L55
            if (r1 == r2) goto L53
            if (r1 == r4) goto L50
            if (r1 == r3) goto L4e
            r2 = 5
            if (r1 == r2) goto L4c
            java.lang.String r1 = ""
            goto L56
        L4c:
            r1 = r5
            goto L56
        L4e:
            r1 = r6
            goto L56
        L50:
            java.lang.String r1 = "AUX"
            goto L56
        L53:
            r1 = r7
            goto L56
        L55:
            r1 = r8
        L56:
            com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData.runningState = r1
            java.lang.String r1 = com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData.runningState
            if (r1 == 0) goto L95
            int r2 = r1.hashCode()
            r3 = 2092(0x82c, float:2.932E-42)
            if (r2 == r3) goto L8b
            r3 = 2145(0x861, float:3.006E-42)
            if (r2 == r3) goto L80
            r3 = 2247(0x8c7, float:3.149E-42)
            if (r2 == r3) goto L79
            r3 = 68082(0x109f2, float:9.5403E-41)
            if (r2 == r3) goto L72
            goto L95
        L72:
            boolean r1 = r1.equals(r5)
            if (r1 != 0) goto L87
            goto L95
        L79:
            boolean r1 = r1.equals(r8)
            if (r1 != 0) goto L91
            goto L95
        L80:
            boolean r1 = r1.equals(r7)
            if (r1 != 0) goto L87
            goto L95
        L87:
            set0xA9Data$setCDPage(r0, r10)
            goto L9c
        L8b:
            boolean r1 = r1.equals(r6)
            if (r1 == 0) goto L95
        L91:
            set0xA9Data$setRadioPage(r0, r10)
            goto L9c
        L95:
            java.util.List r0 = r0.getItems()
            r0.clear()
        L9c:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "bundle_key_orinal_init_view"
            r0.putBoolean(r1, r9)
            r10.updateOriginalCarDeviceActivity(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._314.MsgMgr.set0xA9Data():void");
    }

    private static final void set0xA9Data$setRadioPage(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, MsgMgr msgMgr) {
        String str;
        originalCarDevicePageUiSet.setRowBottomBtnAction(new String[0]);
        List<OriginalCarDevicePageUiSet.Item> items = originalCarDevicePageUiSet.getItems();
        items.clear();
        StringBuilder sbAppend = new StringBuilder().append(DataHandleUtils.getMsbLsbResult(msgMgr.getFrame()[3], msgMgr.getFrame()[4]));
        String str2 = GeneralOriginalCarDeviceData.runningState;
        if (Intrinsics.areEqual(str2, "FM")) {
            str = " MHZ";
        } else {
            str = Intrinsics.areEqual(str2, "AM") ? " KHZ" : "";
        }
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_frequency", sbAppend.append(str).toString()));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_preset", String.valueOf(msgMgr.getFrame()[5])));
    }

    private static final void set0xA9Data$setCDPage(OriginalCarDevicePageUiSet originalCarDevicePageUiSet, MsgMgr msgMgr) {
        originalCarDevicePageUiSet.setRowBottomBtnAction(new String[]{"left", OriginalBtnAction.PLAY, OriginalBtnAction.PAUSE, "right"});
        List<OriginalCarDevicePageUiSet.Item> items = originalCarDevicePageUiSet.getItems();
        items.clear();
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Current_track", String.valueOf(msgMgr.getFrame()[8])));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_Total_repertoire", String.valueOf(msgMgr.getFrame()[9])));
        items.add(new OriginalCarDevicePageUiSet.Item("music_music", "_330_play_time", new StringBuilder().append(DataHandleUtils.getMsbLsbResult(msgMgr.getFrame()[6], msgMgr.getFrame()[7])).append('s').toString()));
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void speed() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item != null) {
            item.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5])));
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7])));
        }
        updateDriveDataActivity(null);
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]));
    }

    private final void airSettings() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 4, 4);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 0, 4);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 7, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 6, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 5, 1);
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_1");
        if (itemListBean != null) {
            itemListBean.setProgress(intFromByteWithBit - 1);
            itemListBean.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_2");
        if (itemListBean2 != null) {
            itemListBean2.setProgress(intFromByteWithBit2 - 1);
            itemListBean2.setValue(String.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(Integer.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(itemListBean4.getValueSrnArray().get(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S314_SettingForAir_5");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(intFromByteWithBit5));
        }
        updateSettingActivity(null);
    }

    private final void setOliConsumptionHistoryData() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_1");
        if (item != null) {
            item.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_2");
        if (item2 != null) {
            item2.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_3");
        if (item3 != null) {
            item3.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[6], getFrame()[7]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_4");
        if (item4 != null) {
            item4.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[8], getFrame()[9]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item5 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_5");
        if (item5 != null) {
            item5.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[10], getFrame()[11]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item6 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_6");
        if (item6 != null) {
            item6.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[13]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item7 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_7");
        if (item7 != null) {
            item7.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[14], getFrame()[15]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item8 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_8");
        if (item8 != null) {
            item8.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[16], getFrame()[17]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item9 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_9");
        if (item9 != null) {
            item9.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[18], getFrame()[19]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item10 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumptionHistory_10");
        if (item10 != null) {
            item10.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[20], getFrame()[21]) / 10.0f) + " l/100km");
        }
        updateDriveDataActivity(null);
    }

    private final void setOliConsumptionData() {
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_1");
        if (item != null) {
            item.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[2], getFrame()[3]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_2");
        if (item2 != null) {
            item2.setValue((DataHandleUtils.getMsbLsbResult(getFrame()[4], getFrame()[5]) / 10.0f) + " l/100km");
        }
        DriverDataPageUiSet.Page.Item item3 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_3");
        if (item3 != null) {
            item3.setValue(com.hzbhd.canbus.car._361.MsgMgrKt.getAnotherMsbLsbResult(getFrame()[6], getFrame()[7], getFrame()[8]) + " km");
        }
        DriverDataPageUiSet.Page.Item item4 = InitUtilsKt.getMDrivingItemIndex().get("S314_OilConsumption_4");
        if (item4 != null) {
            item4.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[12], getFrame()[13]) + " km");
        }
        updateDriveDataActivity(null);
    }

    private final void amplifier(byte[] canbusInfo) {
        int[] intArray = MsgMgrKt.toIntArray(canbusInfo);
        GeneralAmplifierData.volume = intArray[2];
        GeneralAmplifierData.leftRight = intArray[3];
        GeneralAmplifierData.frontRear = intArray[4];
        GeneralAmplifierData.bandBass = intArray[5] + 5;
        GeneralAmplifierData.bandMiddle = intArray[6] + 5;
        GeneralAmplifierData.bandTreble = intArray[7] + 5;
        updateAmplifierActivity(null);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 4, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 3, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 0, 3);
        byte b = canbusInfo[9];
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(Integer.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_3");
        if (itemListBean3 != null) {
            itemListBean3.setProgress(intFromByteWithBit3);
            itemListBean3.setValue(String.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S314_DSPSettings_4");
        if (itemListBean4 != null) {
            itemListBean4.setProgress(b);
            itemListBean4.setValue(String.valueOf((int) b));
        }
        updateSettingActivity(null);
    }

    private final void anotherSetting() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("D314_Other_1");
        if (itemListBean != null) {
            itemListBean.setValue(itemListBean.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 6, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_2");
        if (itemListBean2 != null) {
            itemListBean2.setValue(itemListBean2.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 5, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_3");
        if (itemListBean3 != null) {
            itemListBean3.setValue(itemListBean3.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(getFrame()[3]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_5");
        if (itemListBean5 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 4, 4) - 1;
            itemListBean5.setProgress(intFromByteWithBit);
            itemListBean5.setValue(String.valueOf((intFromByteWithBit * 10) + 200));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_6");
        if (itemListBean6 != null) {
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 4) - 1;
            itemListBean6.setProgress(intFromByteWithBit2);
            itemListBean6.setValue(String.valueOf((intFromByteWithBit2 * 10) + 200));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_7");
        if (itemListBean7 != null) {
            itemListBean7.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_8");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_9");
        if (itemListBean9 != null) {
            itemListBean9.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_10");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_11");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(DataHandleUtils.getBoolBit3(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_12");
        if (itemListBean12 != null) {
            itemListBean12.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_13");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_14");
        if (itemListBean14 != null) {
            itemListBean14.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[5]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_15");
        if (itemListBean15 != null) {
            itemListBean15.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[6]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_16");
        if (itemListBean16 != null) {
            itemListBean16.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(getFrame()[6]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = InitUtilsKt.getMSettingItemIndex().get("D314_Other_17");
        if (itemListBean17 != null) {
            itemListBean17.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(getFrame()[6]) ? 1 : 0));
        }
        updateSettingActivity(null);
    }

    private final void setting() {
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_radar_1");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[2]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean2 = InitUtilsKt.getMSettingItemIndex().get("S314_radar_2");
        if (itemListBean2 != null) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 4, 3);
            itemListBean2.setProgress(intFromByteWithBit - 1);
            itemListBean2.setValue(String.valueOf(intFromByteWithBit));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean3 = InitUtilsKt.getMSettingItemIndex().get("S314_radar_3");
        if (itemListBean3 != null) {
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 1, 3);
            itemListBean3.setProgress(intFromByteWithBit2 - 1);
            itemListBean3.setValue(String.valueOf(intFromByteWithBit2));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean4 = InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
        if (itemListBean4 != null) {
            itemListBean4.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[2]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean5 = InitUtilsKt.getMSettingItemIndex().get("S314_setting1_1");
        if (itemListBean5 != null) {
            itemListBean5.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[3]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean6 = InitUtilsKt.getMSettingItemIndex().get("S314_setting1_2");
        if (itemListBean6 != null) {
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 4, 3);
            itemListBean6.setProgress(intFromByteWithBit3);
            itemListBean6.setValue(String.valueOf(intFromByteWithBit3));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean7 = InitUtilsKt.getMSettingItemIndex().get("S314_setting1_3");
        if (itemListBean7 != null) {
            itemListBean7.setValue(itemListBean7.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 1, 3)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean8 = InitUtilsKt.getMSettingItemIndex().get("S314_setting1_4");
        if (itemListBean8 != null) {
            itemListBean8.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[3]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean9 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_1");
        if (itemListBean9 != null) {
            itemListBean9.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean10 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_2");
        if (itemListBean10 != null) {
            itemListBean10.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean11 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_3");
        if (itemListBean11 != null) {
            itemListBean11.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean12 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_4");
        if (itemListBean12 != null) {
            itemListBean12.setValue(itemListBean12.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 3, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean13 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_5");
        if (itemListBean13 != null) {
            itemListBean13.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean14 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_6");
        if (itemListBean14 != null) {
            itemListBean14.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean15 = InitUtilsKt.getMSettingItemIndex().get("S314_setting2_7");
        if (itemListBean15 != null) {
            itemListBean15.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[4]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean16 = InitUtilsKt.getMSettingItemIndex().get("S314_display_1");
        if (itemListBean16 != null) {
            int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 6, 2);
            itemListBean16.setProgress(intFromByteWithBit4 - 1);
            itemListBean16.setValue(String.valueOf(intFromByteWithBit4));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean17 = InitUtilsKt.getMSettingItemIndex().get("S314_display_2");
        if (itemListBean17 != null) {
            itemListBean17.setValue(itemListBean17.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 4, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean18 = InitUtilsKt.getMSettingItemIndex().get("S314_display_3");
        if (itemListBean18 != null) {
            itemListBean18.setValue(itemListBean18.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 2, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean19 = InitUtilsKt.getMSettingItemIndex().get("S314_display_4");
        if (itemListBean19 != null) {
            itemListBean19.setValue(itemListBean19.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[5], 0, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean20 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_1");
        if (itemListBean20 != null) {
            itemListBean20.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[6]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean21 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_2");
        if (itemListBean21 != null) {
            itemListBean21.setValue(itemListBean21.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 5, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean22 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_3");
        if (itemListBean22 != null) {
            itemListBean22.setValue(itemListBean22.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 3, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean23 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_4");
        if (itemListBean23 != null) {
            itemListBean23.setValue(itemListBean23.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 2, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean24 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_5");
        if (itemListBean24 != null) {
            itemListBean24.setValue(Integer.valueOf(DataHandleUtils.getBoolBit1(getFrame()[6]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean25 = InitUtilsKt.getMSettingItemIndex().get("S314_setting3_6");
        if (itemListBean25 != null) {
            itemListBean25.setValue(Integer.valueOf(DataHandleUtils.getBoolBit0(getFrame()[6]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean26 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_1");
        if (itemListBean26 != null) {
            itemListBean26.setValue(Integer.valueOf(DataHandleUtils.getBoolBit7(getFrame()[7]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean27 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_2");
        if (itemListBean27 != null) {
            itemListBean27.setValue(Integer.valueOf(DataHandleUtils.getBoolBit6(getFrame()[7]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean28 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_3");
        if (itemListBean28 != null) {
            itemListBean28.setValue(Integer.valueOf(DataHandleUtils.getBoolBit5(getFrame()[7]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean29 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_4");
        if (itemListBean29 != null) {
            itemListBean29.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(getFrame()[7]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean30 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_5");
        if (itemListBean30 != null) {
            int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 2, 2);
            itemListBean30.setProgress(intFromByteWithBit5 - 1);
            itemListBean30.setValue(String.valueOf(intFromByteWithBit5));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean31 = InitUtilsKt.getMSettingItemIndex().get("S314_setting4_6");
        if (itemListBean31 != null) {
            itemListBean31.setValue(itemListBean31.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[7], 0, 2)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean32 = InitUtilsKt.getMSettingItemIndex().get("S314_setting5_1");
        if (itemListBean32 != null) {
            itemListBean32.setValue(Integer.valueOf(DataHandleUtils.getBoolBit4(getFrame()[8]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean33 = InitUtilsKt.getMSettingItemIndex().get("S314_setting5_2");
        if (itemListBean33 != null) {
            itemListBean33.setValue(itemListBean33.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 3, 1)));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean34 = InitUtilsKt.getMSettingItemIndex().get("S314_setting5_3");
        if (itemListBean34 != null) {
            itemListBean34.setValue(Integer.valueOf(DataHandleUtils.getBoolBit2(getFrame()[8]) ? 1 : 0));
        }
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean35 = InitUtilsKt.getMSettingItemIndex().get("S314_setting5_4");
        if (itemListBean35 != null) {
            itemListBean35.setValue(itemListBean35.getValueSrnArray().get(DataHandleUtils.getIntFromByteWithBit(getFrame()[8], 0, 2)));
        }
        updateSettingActivity(null);
    }

    private final void tyre() {
        ArrayList arrayList = new ArrayList();
        double d = 100;
        arrayList.add(new TireUpdateEntity(0, 0, new String[]{String.valueOf(Math.rint((getFrame()[4] * 0.1d) * d) / d), "BAR"}));
        arrayList.add(new TireUpdateEntity(1, 0, new String[]{String.valueOf(Math.rint((getFrame()[5] * 0.1d) * d) / d), "BAR"}));
        arrayList.add(new TireUpdateEntity(2, 0, new String[]{String.valueOf(Math.rint((getFrame()[6] * 0.1d) * d) / d), "BAR"}));
        arrayList.add(new TireUpdateEntity(3, 0, new String[]{String.valueOf(Math.rint((getFrame()[7] * 0.1d) * d) / d), "BAR"}));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private final void radar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setFrontRadarLocationData(4, radar$assignRadarProgress(getFrame()[6]), radar$assignRadarProgress(getFrame()[7]), radar$assignRadarProgress(getFrame()[8]), radar$assignRadarProgress(getFrame()[9]));
        RadarInfoUtil.setRearRadarLocationData(4, radar$assignRadarProgress(getFrame()[2]), radar$assignRadarProgress(getFrame()[3]), radar$assignRadarProgress(getFrame()[4]), radar$assignRadarProgress(getFrame()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, getContext());
        SettingPageUiSet.ListBean.ItemListBean<?> itemListBean = InitUtilsKt.getMSettingItemIndex().get("S314_radar_4");
        if (itemListBean != null) {
            itemListBean.setValue(Integer.valueOf(getFrame()[12] != 1 ? 0 : 1));
        }
        updateSettingActivity(null);
    }

    private final void air() {
        String str;
        String str2;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit4(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 3);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 3, 2);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(getFrame()[3]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit1(getFrame()[3]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(getFrame()[3]);
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 3);
        GeneralAirData.climate = getFrame()[5] == 1;
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 4, 4);
        if (intFromByteWithBit == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 12) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.front_right_blow_head = true;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[6], 0, 4);
        if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit2 == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit2 == 6) {
            GeneralAirData.front_left_blow_head = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i = getFrame()[8];
        String str3 = "High";
        if (i != 254) {
            str = i != 255 ? (i / 2.0f) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i2 = getFrame()[9];
        if (i2 != 254) {
            str2 = i2 != 255 ? (i2 / 2.0f) + " °C" : "High";
        } else {
            str2 = "Low";
        }
        GeneralAirData.front_right_temperature = str2;
        int i3 = getFrame()[10];
        if (i3 == 1) {
            GeneralAirData.rear_auto_wind_model = true;
        } else if (i3 == 2) {
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i3 == 3) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_right_blow_foot = true;
        } else if (i3 == 4) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_right_blow_head = true;
        }
        GeneralAirData.rear_wind_level = getFrame()[11];
        int i4 = getFrame()[12];
        if (i4 == 254) {
            str3 = "Low";
        } else if (i4 != 255) {
            str3 = (i4 / 2.0f) + " °C";
        }
        GeneralAirData.rear_temperature = str3;
        updateAirActivity(getContext(), 1004);
        updateAirActivity(getContext(), 1003);
    }

    private final void panelKnob(byte[] canbusInfo) {
        byte b = canbusInfo[3];
        byte b2 = this.x22D1;
        int iAbs = Math.abs(b - b2);
        int i = getFrame()[2];
        if (i != 1) {
            if (i == 2) {
                if (b > b2) {
                    DataHandleUtils.knob(getContext(), 46, iAbs);
                } else if (b < b2) {
                    DataHandleUtils.knob(getContext(), 45, iAbs);
                }
            }
        } else if (b > b2) {
            DataHandleUtils.knob(getContext(), 7, iAbs);
        } else if (b < b2) {
            DataHandleUtils.knob(getContext(), 8, iAbs);
        }
        this.x22D1 = canbusInfo[3];
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01b7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void panelBtn() {
        /*
            Method dump skipped, instructions count: 520
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._314.MsgMgr.panelBtn():void");
    }

    private final void door() {
        Integer num = this.x12;
        int i = getFrame()[4];
        if (num == null || num.intValue() != i) {
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
            updateDoorView(getContext());
        }
        this.x12 = Integer.valueOf(getFrame()[4]);
    }

    private final void button() {
        int i = getFrame()[4];
        Integer num = this.x11D2;
        if ((num == null || i != num.intValue()) && getFrame()[5] != 0) {
            int i2 = getFrame()[4];
            if (i2 == 0) {
                realKeyClick4(getContext(), 0);
            } else if (i2 == 1) {
                realKeyClick4(getContext(), 7);
            } else if (i2 == 2) {
                realKeyClick4(getContext(), 8);
            } else if (i2 == 4) {
                realKeyClick4(getContext(), HotKeyConstant.K_SPEECH);
            } else if (i2 == 5) {
                realKeyClick4(getContext(), 14);
            } else if (i2 == 8) {
                realKeyClick4(getContext(), 21);
            } else if (i2 == 9) {
                realKeyClick4(getContext(), 20);
            } else if (i2 == 12) {
                realKeyClick4(getContext(), 2);
            } else if (i2 == 15) {
                realKeyClick4(getContext(), 49);
            } else if (i2 == 16) {
                realKeyClick4(getContext(), 50);
            }
        }
        this.x11D2 = Integer.valueOf(getFrame()[4]);
    }

    private final void angle() {
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    static /* synthetic */ void sendVoiceSource$default(MsgMgr msgMgr, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        msgMgr.sendVoiceSource(i, bArr);
    }

    private final void sendVoiceSource(int d0, byte[] d1t12) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, com.hzbhd.canbus.car._361.MsgMgrKt.restrict$default(ArraysKt.plus(new byte[]{(byte) d0}, d1t12), 13, 0, 4, null)));
    }

    public final void updateSettingsData() {
        updateSettingActivity(null);
    }
}
