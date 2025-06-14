package com.hzbhd.canbus.canCustom.canBase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hzbhd.canbus.activity.AbstractBaseActivity;
import com.hzbhd.canbus.interfaces.MsgMgrInterface;
import com.hzbhd.canbus.park.BackCameraUiService;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CanDocking.kt */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b4\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0019\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\b\u0010\f\u001a\u00020\u0004H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J&\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0004H\u0016J\"\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u0010H\u0016J\"\u0010\u001a\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\"\u0010 \u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J\"\u0010!\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016JT\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u00172\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010&\u001a\u00020\u00172\u0006\u0010'\u001a\u00020\u00172\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\"\u0010(\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0016J*\u0010)\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u0017H\u0016J\b\u0010+\u001a\u00020\u0004H\u0016J\b\u0010,\u001a\u00020\u0004H\u0016J\u001c\u0010-\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010.\u001a\u0004\u0018\u00010\u001cH\u0016J\u0018\u0010/\u001a\u00020\u00042\u0006\u00100\u001a\u00020\u00172\u0006\u00101\u001a\u00020\u001eH\u0016J\u001a\u00102\u001a\u00020\u001e2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u00103\u001a\u00020\u0017H\u0016J\u001a\u00104\u001a\u00020\u001e2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u00103\u001a\u00020\u0017H\u0016Jp\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u00172\u0006\u00107\u001a\u00020\u00172\u0006\u00108\u001a\u00020\u00172\u0006\u00109\u001a\u00020\u00172\u0006\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020\u00172\u0006\u0010=\u001a\u00020\u00172\u0006\u0010>\u001a\u00020\u00172\u0006\u0010?\u001a\u00020\u001e2\u0006\u0010@\u001a\u00020\u001e2\u0006\u0010A\u001a\u00020\u001e2\u0006\u0010B\u001a\u00020\u0017H\u0016J\b\u0010C\u001a\u00020\u0004H\u0016Jh\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u00172\u0006\u0010F\u001a\u00020\u00172\u0006\u0010G\u001a\u00020\u00172\u0006\u0010H\u001a\u00020\u00172\u0006\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020\u00172\u0006\u0010K\u001a\u00020\u00172\u0006\u0010L\u001a\u00020\u00172\u0006\u0010M\u001a\u00020\u00172\u0006\u0010N\u001a\u00020\u00172\u0006\u0010O\u001a\u00020\u00172\u0006\u0010P\u001a\u00020\u0017H\u0016Jv\u0010Q\u001a\u00020\u00042\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\u00172\u0006\u0010U\u001a\u00020\u00172\u0006\u0010V\u001a\u00020\u00172\u0006\u0010W\u001a\u00020\u00172\u0006\u0010X\u001a\u00020\u00172\u0006\u0010Y\u001a\u00020\u00172\u0006\u0010Z\u001a\u00020\u001e2\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\\\u001a\u00020\u00172\b\u0010]\u001a\u0004\u0018\u00010\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010^\u001a\u00020\u0004H\u0016J\u0012\u0010_\u001a\u00020\u00042\b\u0010`\u001a\u0004\u0018\u00010aH\u0016J\u0010\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010\u001cJ\b\u0010e\u001a\u00020\u0001H\u0016J\u0012\u0010f\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010g\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010h\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010i\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010j\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010k\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010l\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010m\u001a\u00020\u0004H\u0016JÈ\u0001\u0010n\u001a\u00020\u00042\u0006\u0010o\u001a\u00020S2\u0006\u0010p\u001a\u00020S2\u0006\u0010q\u001a\u00020\u00172\u0006\u0010r\u001a\u00020\u00172\u0006\u0010s\u001a\u00020S2\u0006\u0010t\u001a\u00020S2\u0006\u0010u\u001a\u00020S2\u0006\u0010v\u001a\u00020S2\u0006\u0010w\u001a\u00020S2\u0006\u0010x\u001a\u00020S2\b\u0010y\u001a\u0004\u0018\u00010\u00102\b\u0010z\u001a\u0004\u0018\u00010\u00102\b\u0010{\u001a\u0004\u0018\u00010\u00102\u0006\u0010|\u001a\u00020}2\u0006\u0010R\u001a\u00020S2\u0006\u0010~\u001a\u00020\u00172\u0006\u0010[\u001a\u00020\u001e2\u0006\u0010\u007f\u001a\u00020}2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0081\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u00102\u0007\u0010\u0083\u0001\u001a\u00020\u001eH\u0016J\u0014\u0010\u0084\u0001\u001a\u00020\u00042\t\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u0010H\u0016J\t\u0010\u0086\u0001\u001a\u00020\u0004H\u0016J\u0015\u0010\u0087\u0001\u001a\u00020\u00042\n\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001H\u0016J\t\u0010\u008a\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u008b\u0001\u001a\u00020\u0004H\u0016J\u0013\u0010\u008c\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J.\u0010\u008d\u0001\u001a\u00020\u00042\u0007\u0010\u008e\u0001\u001a\u00020\u00172\u0007\u0010\u008f\u0001\u001a\u00020\u00172\u0007\u0010\u0090\u0001\u001a\u00020\u00172\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\t\u0010\u0091\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u0092\u0001\u001a\u00020\u0004H\u0016J\t\u0010\u0093\u0001\u001a\u00020\u0004H\u0016J<\u0010\u0094\u0001\u001a\u00020\u00042\u0007\u0010\u0095\u0001\u001a\u00020\u00172\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u00102\t\u0010\u0098\u0001\u001a\u0004\u0018\u00010\u00102\u0007\u0010\u0099\u0001\u001a\u00020\u0017H\u0016J\u001c\u0010\u009a\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010\u009b\u0001\u001a\u00020\u0017H\u0014J%\u0010\u009c\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010\u009b\u0001\u001a\u00020\u00172\u0007\u0010\u009d\u0001\u001a\u00020\u0017H\u0014J\u001e\u0010\u009e\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\t\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u001cH\u0016J\u0015\u0010 \u0001\u001a\u00020\u00042\n\u0010¡\u0001\u001a\u0005\u0018\u00010¢\u0001H\u0016J\u0014\u0010£\u0001\u001a\u00020\u00042\t\u0010¤\u0001\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010¥\u0001\u001a\u00020\u00042\u0007\u0010¦\u0001\u001a\u00020\u001eH\u0016J\u001d\u0010§\u0001\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\n\u0010¨\u0001\u001a\u0005\u0018\u00010©\u0001H\u0014J\u001c\u0010ª\u0001\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0007\u0010«\u0001\u001a\u00020\u0010H\u0016J\t\u0010¬\u0001\u001a\u00020\u0004H\u0016J\u009b\u0001\u0010\u00ad\u0001\u001a\u00020\u00042\u0006\u0010o\u001a\u00020S2\u0006\u0010p\u001a\u00020S2\u0006\u0010q\u001a\u00020\u00172\u0006\u0010r\u001a\u00020\u00172\u0006\u0010s\u001a\u00020S2\u0006\u0010t\u001a\u00020S2\u0006\u0010u\u001a\u00020S2\b\u0010v\u001a\u0004\u0018\u00010\u00102\u0006\u0010w\u001a\u00020S2\u0006\u0010x\u001a\u00020S2\b\u0010y\u001a\u0004\u0018\u00010\u00102\b\u0010z\u001a\u0004\u0018\u00010\u00102\b\u0010{\u001a\u0004\u0018\u00010\u00102\u0006\u0010|\u001a\u00020\u00172\u0007\u0010®\u0001\u001a\u00020S2\u0006\u0010[\u001a\u00020\u001e2\u0007\u0010¯\u0001\u001a\u00020\u0017H\u0016J\u0014\u0010°\u0001\u001a\u00020\u00042\t\u0010±\u0001\u001a\u0004\u0018\u00010\u0010H\u0016¨\u0006²\u0001"}, d2 = {"Lcom/hzbhd/canbus/canCustom/canBase/CanDocking;", "Lcom/hzbhd/canbus/interfaces/MsgMgrInterface;", "()V", "aMapCallBack", "", "bundle", "Landroid/os/Bundle;", "afterServiceNormalSetting", "context", "Landroid/content/Context;", "atvDestdroy", "atvInfoChange", "auxInDestdroy", "auxInInfoChange", "btMusicId3InfoChange", LcdInfoShare.MediaInfo.title, "", LcdInfoShare.MediaInfo.artist, LcdInfoShare.MediaInfo.album, "btMusicInfoChange", "btMusiceDestdroy", "btPhoneCallLogInfoChange", "index", "", "type", "number", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneStatusInfoChange", "callStatus", "isHfpConnected", "isCallingFlag", "batteryStatus", "signalValue", "btPhoneTalkingInfoChange", "btPhoneTalkingWithTimeInfoChange", "time", "cameraDestroy", "cameraInfoChange", "canbusInfoChange", "canbusInfo", "currentVolumeInfoChange", "volValue", "isMute", "customLongClick", "key", "customShortClick", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "destroyCommand", "deviceStatusInfoChange", "btOn", "discRadom", "discRepeat", "discExsit", "sdCardIn", "usbIn", "radioSt", "mute", "singleCycle", "fullCurve", "folderLoop", "randomFolder", "discInfoChange", "playModel", "", "currentTime", "playTitleNo", "position", "currentPlayNo", "currentTotalNo", "discType", "isUnMuted", "isPlaying", "playStat", "song", "dtvInfoChange", "getBackCameraUiService", "backCameraUiService", "Lcom/hzbhd/canbus/park/BackCameraUiService;", "getByteArrayToIntArray", "", "mCanbusInfo", "getInstance", "initCommand", "linInfoChange", "linInfo", "mcuErrorState", "errorInfo", "medianCalibration", "calibrationInfo", "musicDestroy", "musicInfoChange", "stoagePath", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playIndex", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "musicLrcInfoChange", "lrc", "notifyBtStatusChange", "onAMapCallBack", "aMapEntity", "Lcom/hzbhd/canbus/util/amap/AMapEntity;", "onAccOff", "onAccOn", "onHandshake", "onKeyEvent", "hotKey", "keyStateOrArg1", "beepOrArg2", "onPowerOff", "onSleep", "radioDestroy", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "realKeyClick4", "whatKey", "realKeyLongClick1", "keyState", "serialDataChange", "serialData", "setMgrRefreshUiInterface", "mgrRefreshUiInterface", "Lcom/hzbhd/canbus/activity/AbstractBaseActivity;", "sourceSwitchChange", LcdInfoShare.DspInfo.source, "sourceSwitchNoMediaInfoChange", "isPowerOff", "startOtherActivity", "componentName", "Landroid/content/ComponentName;", "updateVersionInfo", "version", "videoDestroy", "videoInfoChange", "playMode", "duration", "voiceControlInfo", "action", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public class CanDocking implements MsgMgrInterface {
    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void aMapCallBack(Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int index, int type, String number) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customShortClick(Context context, int key) {
        return false;
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int btOn, int discRadom, int discRepeat, int discExsit, int sdCardIn, int usbIn, int radioSt, int mute, int singleCycle, int fullCurve, int folderLoop, int randomFolder) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void getBackCameraUiService(BackCameraUiService backCameraUiService) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void linInfoChange(Context context, byte[] linInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void mcuErrorState(Context context, byte[] errorInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void medianCalibration(Context context, byte[] calibrationInfo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicLrcInfoChange(String lrc) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void notifyBtStatusChange() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onKeyEvent(int hotKey, int keyStateOrArg1, int beepOrArg2, Bundle bundle) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onPowerOff() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onSleep() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void serialDataChange(Context context, byte[] serialData) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void setMgrRefreshUiInterface(AbstractBaseActivity mgrRefreshUiInterface) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String source) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String action) {
    }

    public void updateVersionInfo(Context context, String version) {
        Intrinsics.checkNotNullParameter(version, "version");
        if (LogUtil.log5()) {
            LogUtil.d("updateVersionInfo: " + version);
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_CAN_VERSION, version);
        GeneralData.INSTANCE.setCanVersion(version);
    }

    public final int[] getByteArrayToIntArray(byte[] mCanbusInfo) {
        int[] iArr = mCanbusInfo != null ? new int[mCanbusInfo.length] : null;
        Intrinsics.checkNotNull(iArr);
        int length = mCanbusInfo.length;
        for (int i = 0; i < length; i++) {
            byte b = mCanbusInfo[i];
            if ((b & ByteCompanionObject.MIN_VALUE) == 0) {
                iArr[i] = b;
            } else {
                iArr[i] = b & 255;
            }
        }
        return iArr;
    }

    protected void realKeyLongClick1(Context context, int whatKey, int keyState) {
        if (RealKeyUtil.isCompoundKey(context, whatKey, keyState)) {
            return;
        }
        RealKeyUtil.realKeyLongClick(context, whatKey, keyState);
    }

    protected void realKeyClick4(Context context, int whatKey) {
        realKeyLongClick1(context, whatKey, 1);
        realKeyLongClick1(context, whatKey, 0);
    }

    protected void startOtherActivity(Context context, ComponentName componentName) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.hzbhd.canbus.interfaces.MsgMgrInterface
    public MsgMgrInterface getInstance() {
        return this;
    }
}
