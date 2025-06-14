package com.hzbhd.canbus.car._338;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\n\n\u0002\b\u001c\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\"\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0014\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0015\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\"\u0010\u0016\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0016\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0010J\u0018\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0010\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0012H\u0016Jp\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u00042\u0006\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u00042\u0006\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u00042\u0006\u0010-\u001a\u00020\u00122\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u0004H\u0016J\u0010\u00101\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u00102\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u0004H\u0002J \u00106\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002JÄ\u0001\u00109\u001a\u00020\f2\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020;2\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00020\u00042\u0006\u0010?\u001a\u00020;2\u0006\u0010@\u001a\u00020;2\u0006\u0010A\u001a\u00020;2\u0006\u0010B\u001a\u00020;2\u0006\u0010C\u001a\u00020;2\u0006\u0010D\u001a\u00020;2\b\u0010E\u001a\u0004\u0018\u00010F2\b\u0010G\u001a\u0004\u0018\u00010F2\b\u0010H\u001a\u0004\u0018\u00010F2\u0006\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020;2\u0006\u0010L\u001a\u00020\u00042\u0006\u0010M\u001a\u00020\u00122\u0006\u0010N\u001a\u00020J2\b\u0010O\u001a\u0004\u0018\u00010F2\b\u0010P\u001a\u0004\u0018\u00010F2\b\u0010Q\u001a\u0004\u0018\u00010F2\u0006\u0010R\u001a\u00020\u0012H\u0016J\u0010\u0010S\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010T\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J6\u0010U\u001a\u00020\f2\u0006\u0010V\u001a\u00020\u00042\b\u0010W\u001a\u0004\u0018\u00010F2\b\u0010X\u001a\u0004\u0018\u00010F2\b\u0010Y\u001a\u0004\u0018\u00010F2\u0006\u0010Z\u001a\u00020\u0004H\u0016J\u0010\u0010[\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\\\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010]\u001a\u00020\f2\u0006\u0010^\u001a\u00020\u0004H\u0002J\u0012\u0010_\u001a\u00020\f2\b\u00108\u001a\u0004\u0018\u00010\u0010H\u0002J8\u0010`\u001a\u00020\f2\u0006\u0010a\u001a\u00020\u00042\u0006\u0010^\u001a\u00020\u00042\u0006\u0010b\u001a\u00020\u00042\u0006\u0010c\u001a\u00020\u00042\u0006\u0010d\u001a\u00020\u00042\u0006\u0010e\u001a\u00020\u0004H\u0002J\u0010\u0010f\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\b\u0010g\u001a\u00020\fH\u0002J \u0010h\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00042\u0006\u00108\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006j"}, d2 = {"Lcom/hzbhd/canbus/car/_338/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "carId", "", "context", "Landroid/content/Context;", "mCanBusInfoInt", "", "mVolume", "staged", "afterServiceNormalSetting", "", "airInfo", "btPhoneHangUpInfoChange", "phoneNumber", "", "isMicMute", "", "isAudioTransferTowardsAG", "btPhoneIncomingInfoChange", "btPhoneOutGoingInfoChange", "btPhoneTalkingInfoChange", "byteMerger", "bt1", "bt2", "canbusInfoChange", "canbusInfo", "culTrackAngleLeft", "track", "", "culTrackAngleRight", "currentVolumeInfoChange", "volValue", "isMute", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "isFormatPm", "isGpsTime", "dayOfWeek", "frontRadarInfo", "generalInfo", "getMsbLsbResult", "MSB", "LSB", "knob", "whatKey", "number", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "panelButtonInfo", "panoramicInfo", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "realKeyInfo", "rearRadarInfo", "sendPhoneState", "data1", "sendPhoneText", "sendTimeInfo", "data0", "data2", "data3", "data4", "data5", "steeringWheelAngleInfo", "vehicleSettingInfo", "volumeKnob", "keyValue", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    private int carId;
    private Context context;
    private int[] mCanBusInfoInt = new int[0];
    private int mVolume;
    private int staged;

    private final int getMsbLsbResult(int MSB, int LSB) {
        return ((MSB & 255) << 8) | (LSB & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(canbusInfo, "canbusInfo");
        this.context = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 40) {
            generalInfo(context);
            return;
        }
        if (i == 48) {
            steeringWheelAngleInfo(context);
            return;
        }
        if (i == 64) {
            panoramicInfo(context);
            return;
        }
        if (i != 82) {
            switch (i) {
                case 32:
                    realKeyInfo(context);
                    break;
                case 33:
                    airInfo(context);
                    break;
                case 34:
                    rearRadarInfo(context);
                    break;
                case 35:
                    frontRadarInfo(context);
                    break;
                case 36:
                    panelButtonInfo(context);
                    break;
            }
            return;
        }
        vehicleSettingInfo();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        this.carId = getCurrentCanDifferentId();
        GeneralDoorData.isShowMasterDriverSeatBelt = true;
        GeneralDoorData.isShowCoPilotSeatBelt = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        sendTimeInfo(bYear2Dig, bMonth, bDay, bHours, bMins, bSecond);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        this.mVolume = volValue;
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (isMute ? volValue | 128 : volValue & WorkQueueKt.MASK)});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0058 A[LOOP:0: B:24:0x0056->B:25:0x0058, LOOP_END] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r2, java.lang.String r3, java.lang.String r4, java.lang.String r5, int r6) {
        /*
            r1 = this;
            if (r3 == 0) goto L64
            int r2 = r3.hashCode()
            r4 = 0
            switch(r2) {
                case 64901: goto L31;
                case 64902: goto L28;
                case 69706: goto L1d;
                case 69707: goto L14;
                case 69708: goto Lb;
                default: goto La;
            }
        La:
            goto L64
        Lb:
            java.lang.String r2 = "FM3"
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L64
            goto L26
        L14:
            java.lang.String r2 = "FM2"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L26
            goto L64
        L1d:
            java.lang.String r2 = "FM1"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L26
            goto L64
        L26:
            r2 = r4
            goto L3c
        L28:
            java.lang.String r2 = "AM2"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L3a
            goto L64
        L31:
            java.lang.String r2 = "AM1"
            boolean r2 = r3.equals(r2)
            if (r2 != 0) goto L3a
            goto L64
        L3a:
            r2 = 16
        L3c:
            r3 = 5
            byte[] r5 = new byte[r3]
            r6 = 22
            r5[r4] = r6
            r6 = -64
            r0 = 1
            r5[r0] = r6
            r6 = 2
            byte r0 = (byte) r0
            r5[r6] = r0
            r6 = 3
            r5[r6] = r4
            r6 = 4
            byte r2 = (byte) r2
            r5[r6] = r2
            byte[] r2 = new byte[r3]
            r6 = r4
        L56:
            if (r6 >= r3) goto L5d
            r2[r6] = r4
            int r6 = r6 + 1
            goto L56
        L5d:
            byte[] r2 = kotlin.collections.ArraysKt.plus(r5, r2)
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r2)
        L64:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._338.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendPhoneState(1);
        sendPhoneText(phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendPhoneState(2);
        sendPhoneText(phoneNumber);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendPhoneState(4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {
        sendPhoneState(5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        String str = songTitle == null ? " " : songTitle;
        Charset UTF_16LE = StandardCharsets.UTF_16LE;
        Intrinsics.checkNotNullExpressionValue(UTF_16LE, "UTF_16LE");
        final byte[] bytes = str.getBytes(UTF_16LE);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        String str2 = songArtist != null ? songArtist : " ";
        Charset UTF_16LE2 = StandardCharsets.UTF_16LE;
        Intrinsics.checkNotNullExpressionValue(UTF_16LE2, "UTF_16LE");
        final byte[] bytes2 = str2.getBytes(UTF_16LE2);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._338.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                MsgMgr.m707musicInfoChange$lambda0(bytes, bytes2);
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: musicInfoChange$lambda-0, reason: not valid java name */
    public static final void m707musicInfoChange$lambda0(byte[] title, byte[] artist) throws InterruptedException {
        Intrinsics.checkNotNullParameter(title, "$title");
        Intrinsics.checkNotNullParameter(artist, "$artist");
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 112, 16}, title));
        Thread.sleep(500L);
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, 113, 16}, artist));
    }

    private final void realKeyInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(context, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(context, 7, iArr[3]);
                break;
            case 2:
                realKeyLongClick1(context, 8, iArr[3]);
                break;
            case 3:
                realKeyLongClick1(context, 21, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(context, 20, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(context, 3, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(context, 2, iArr[3]);
                break;
            case 9:
                realKeyLongClick1(context, 14, iArr[3]);
                break;
            case 10:
                realKeyLongClick1(context, 15, iArr[3]);
                break;
        }
    }

    private final void airInfo(Context context) {
        GeneralAirData.econ = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2) == 0;
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        int i = this.mCanBusInfoInt[3];
        if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = false;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = false;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_defog = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_defog = true;
        }
        int i2 = this.mCanBusInfoInt[4];
        if (i2 == 0) {
            GeneralAirData.front_wind_level = i2;
            GeneralAirData.power = false;
        } else {
            GeneralAirData.front_wind_level = i2;
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5])) {
            int i3 = this.mCanBusInfoInt[5];
            if (i3 == 241) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i3 == 242) {
                GeneralAirData.front_left_temperature = "HI";
            } else {
                GeneralAirData.front_left_temperature = ((DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5) / 2.0d) + 18) + "°C";
            }
        } else {
            int i4 = this.mCanBusInfoInt[5];
            if (i4 == 241) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i4 == 242) {
                GeneralAirData.front_left_temperature = "HI";
            } else {
                GeneralAirData.front_left_temperature = "Level " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 5);
            }
        }
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            int i5 = this.mCanBusInfoInt[6];
            if (i5 == 241) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i5 == 242) {
                GeneralAirData.front_right_temperature = "HI";
            } else {
                GeneralAirData.front_right_temperature = ((DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5) / 2.0d) + 18) + "°C";
            }
        } else {
            int i6 = this.mCanBusInfoInt[6];
            if (i6 == 241) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i6 == 242) {
                GeneralAirData.front_right_temperature = "HI";
            } else {
                GeneralAirData.front_right_temperature = "Level " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 5);
            }
        }
        updateAirActivity(context, 1001);
    }

    private final void rearRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private final void frontRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private final void panelButtonInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = HotKeyConstant.K_DISP;
        switch (i) {
            case 0:
                realKeyLongClick1(context, 0, iArr[3]);
                break;
            case 1:
                realKeyLongClick1(context, HotKeyConstant.K_SLEEP, iArr[3]);
                break;
            case 2:
                volumeKnob(context, 2, iArr[3]);
                break;
            case 3:
                volumeKnob(context, 3, iArr[3]);
                break;
            case 4:
                realKeyLongClick1(context, 3, iArr[3]);
                break;
            case 5:
                realKeyLongClick1(context, 63, iArr[3]);
                break;
            case 6:
                realKeyLongClick1(context, 21, iArr[3]);
                break;
            case 7:
                realKeyLongClick1(context, 20, iArr[3]);
                break;
            case 8:
                realKeyLongClick1(context, 49, iArr[3]);
                break;
            case 9:
                knob(context, 45, iArr[3]);
                break;
            case 10:
                knob(context, 46, iArr[3]);
                break;
            case 11:
                realKeyLongClick1(context, 33, iArr[3]);
                break;
            case 12:
                realKeyLongClick1(context, 34, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(context, 35, iArr[3]);
                break;
            case 14:
                realKeyLongClick1(context, 36, iArr[3]);
                break;
            case 15:
                realKeyLongClick1(context, 37, iArr[3]);
                break;
            case 16:
                realKeyLongClick1(context, 38, iArr[3]);
                break;
            case 17:
                realKeyLongClick1(context, 91, iArr[3]);
                break;
            case 18:
                realKeyLongClick1(context, 90, iArr[3]);
                break;
            case 19:
                realKeyLongClick1(context, 62, iArr[3]);
                break;
            case 20:
                realKeyLongClick1(context, 139, iArr[3]);
                break;
            case 22:
                realKeyLongClick1(context, 27, iArr[3]);
                break;
            case 23:
                realKeyLongClick1(context, 68, iArr[3]);
                break;
            case 24:
                realKeyLongClick1(context, 130, iArr[3]);
                break;
            case 25:
                realKeyLongClick1(context, 73, iArr[3]);
                break;
            case 26:
                if (this.carId == 2) {
                    i2 = HotKeyConstant.K_PHONE_ON_OFF;
                }
                realKeyLongClick1(context, i2, iArr[3]);
                break;
            case 27:
                realKeyLongClick1(context, 30, iArr[3]);
                break;
            case 28:
                realKeyLongClick1(context, HotKeyConstant.K_DISP, iArr[3]);
                break;
            case 29:
                realKeyLongClick1(context, 14, iArr[3]);
                break;
            case 30:
                realKeyLongClick1(context, 15, iArr[3]);
                break;
            case 31:
                realKeyLongClick1(context, 50, iArr[3]);
                break;
            case 32:
                realKeyLongClick1(context, 52, iArr[3]);
                break;
            case 33:
                realKeyLongClick1(context, 47, iArr[3]);
                break;
            case 34:
                realKeyLongClick1(context, 59, iArr[3]);
                break;
        }
    }

    private final void generalInfo(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i != this.staged) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralDoorData.isSeatMasterDriverBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralDoorData.isSeatCoPilotBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            updateDoorView(context);
        }
        int[] iArr = this.mCanBusInfoInt;
        this.staged = iArr[2];
        int i2 = iArr[3];
        if (i2 == 255) {
            updateOutDoorTemp(context, "--°C");
        } else if (i2 == 254) {
            updateOutDoorTemp(context, " ");
        } else {
            updateOutDoorTemp(context, (this.mCanBusInfoInt[3] - 40) + "°C");
        }
    }

    private final void steeringWheelAngleInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        short msbLsbResult = (short) (getMsbLsbResult((byte) (iArr[2] & WorkQueueKt.MASK), iArr[3]) / 10);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            GeneralParkData.trackAngle = culTrackAngleRight(msbLsbResult);
        } else {
            GeneralParkData.trackAngle = culTrackAngleLeft(msbLsbResult);
        }
        updateParkUi(null, context);
    }

    private final void panoramicInfo(Context context) {
        forceReverse(context, this.mCanBusInfoInt[2] != 0);
    }

    private final void vehicleSettingInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[3])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final int culTrackAngleLeft(short track) {
        return -DataHandleUtils.rangeNumber((int) (track / 21.6f), 0, 25);
    }

    private final int culTrackAngleRight(short track) {
        return DataHandleUtils.rangeNumber((int) (track / 21.6f), 0, 25);
    }

    private final void volumeKnob(Context context, int keyValue, int number) {
        if (keyValue == 3 && this.mVolume > 0) {
            for (int i = 0; i < number; i++) {
                realKeyClick(context, 8);
            }
        }
        if (keyValue != 2 || this.mVolume >= 40) {
            return;
        }
        for (int i2 = 0; i2 < number; i2++) {
            realKeyClick(context, 7);
        }
    }

    private final void sendPhoneState(int data1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) data1});
    }

    private final void sendPhoneText(byte[] number) {
        int i = this.carId;
        if (i == 4) {
            Intrinsics.checkNotNull(number);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 4, 2}, number));
        } else if (i == 5) {
            Intrinsics.checkNotNull(number);
            CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 4, 16}, number));
        }
    }

    private final void sendTimeInfo(int data0, int data1, int data2, int data3, int data4, int data5) {
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) data0, (byte) data1, (byte) data2, (byte) DataHandleUtils.getIntFromByteWithBit(data3, 1, 6), (byte) data4, (byte) data5});
    }

    public final byte[] byteMerger(byte[] bt1, byte[] bt2) {
        Intrinsics.checkNotNullParameter(bt1, "bt1");
        Intrinsics.checkNotNullParameter(bt2, "bt2");
        byte[] bArr = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bArr, 0, bt1.length);
        System.arraycopy(bt2, 0, bArr, bt1.length, bt2.length);
        return bArr;
    }

    private final void knob(Context context, int whatKey, int number) {
        for (int i = 0; i < number; i++) {
            realKeyClick(context, whatKey);
        }
    }
}
