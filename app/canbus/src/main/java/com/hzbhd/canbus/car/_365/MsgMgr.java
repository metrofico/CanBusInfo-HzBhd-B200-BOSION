package com.hzbhd.canbus.car._365;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._281.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u001c\u0010\u0015\u001a\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\nH\u0002JÄ\u0001\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u001d2\u0006\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020\u001d2\u0006\u0010'\u001a\u00020\u001d2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010)2\b\u0010+\u001a\u0004\u0018\u00010)2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u00020 2\u0006\u00100\u001a\u00020\u00192\u0006\u00101\u001a\u00020-2\b\u00102\u001a\u0004\u0018\u00010)2\b\u00103\u001a\u0004\u0018\u00010)2\b\u00104\u001a\u0004\u0018\u00010)2\u0006\u00105\u001a\u00020\u0019H\u0016J6\u00106\u001a\u00020\u00132\u0006\u00107\u001a\u00020 2\b\u00108\u001a\u0004\u0018\u00010)2\b\u00109\u001a\u0004\u0018\u00010)2\b\u0010:\u001a\u0004\u0018\u00010)2\u0006\u0010;\u001a\u00020 H\u0016J\u0010\u0010<\u001a\u00020\u00132\u0006\u0010=\u001a\u00020 H\u0002J\b\u0010>\u001a\u00020\u0013H\u0002J\b\u0010?\u001a\u00020\u0013H\u0002J\b\u0010@\u001a\u00020\u0013H\u0002J\b\u0010A\u001a\u00020\u0013H\u0002J\b\u0010B\u001a\u00020\u0013H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000e¨\u0006C"}, d2 = {"Lcom/hzbhd/canbus/car/_365/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "mAirData", "getMAirData", "setMAirData", "afterServiceNormalSetting", "", "auxInInfoChange", "canbusInfoChange", "canbusInfo", "", "isAirDataChange", "", "mCanBusInfoInt", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendXA1Data", "d1", "set0x11Data", "set0x12Data", "set0x24Data", "set0x31Data", "set0x41Data", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public Context context;
    public int[] frame;
    private int[] mAirData = new int[0];

    private final void set0x24Data() {
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        if (context == null) {
            return;
        }
        setContext(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 38, 7, (byte) getCurrentCanDifferentId()});
    }

    private final void sendXA1Data(int d1) {
        CanbusMsgSender.sendMsg(new byte[]{22, -95, ByteCompanionObject.MIN_VALUE, (byte) d1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        int i;
        Intrinsics.checkNotNull(currBand);
        String str = currBand;
        if (StringsKt.contains$default((CharSequence) str, (CharSequence) "FM", false, 2, (Object) null)) {
            i = 10;
        } else if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "AM", false, 2, (Object) null)) {
            return;
        } else {
            i = 11;
        }
        sendXA1Data(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendXA1Data(3);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (stoagePath != 9) {
            sendXA1Data(6);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        int i;
        Intrinsics.checkNotNull(context);
        Intrinsics.checkNotNull(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        setFrame(byteArrayToIntArray);
        int i2 = getFrame()[1];
        if (i2 == 17) {
            set0x11Data();
            return;
        }
        if (i2 == 18) {
            set0x12Data();
            return;
        }
        if (i2 == 36) {
            set0x24Data();
            return;
        }
        if (i2 == 49) {
            set0x31Data();
            return;
        }
        if (i2 == 65) {
            set0x41Data();
            return;
        }
        if (i2 != 175) {
            return;
        }
        int i3 = getFrame()[3];
        if (i3 == 2) {
            i = 130;
        } else if (i3 == 3) {
            i = 141;
        } else if (i3 == 6) {
            i = 139;
        } else if (i3 != 7) {
            switch (i3) {
                case 10:
                    i = 76;
                    break;
                case 11:
                    i = 77;
                    break;
                case 12:
                    i = 68;
                    break;
                default:
                    i = 0;
                    break;
            }
        } else {
            i = HotKeyConstant.K_PHONE_ON_OFF;
        }
        realKeyClick4(context, i);
    }

    private final void set0x41Data() {
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        RadarInfoUtil.setRearRadarDistanceData(getFrame()[2], getFrame()[3], getFrame()[4], getFrame()[5]);
        RadarInfoUtil.setFrontRadarDistanceData(getFrame()[6], getFrame()[7], getFrame()[8], getFrame()[9]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, getContext());
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(getFrame()[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2) == 1;
        GeneralAirData.ac_auto = DataHandleUtils.getIntFromByteWithBit(getFrame()[2], 0, 2) == 2;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 7, 1);
        if (intFromByteWithBit == 0) {
            GeneralAirData.right_set_seat_heat = true;
        } else if (intFromByteWithBit != 1) {
            return;
        } else {
            GeneralAirData.right_set_seat_cold = true;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 6, 1);
        if (intFromByteWithBit2 == 0) {
            GeneralAirData.left_set_seat_heat = true;
        } else if (intFromByteWithBit2 != 1) {
            return;
        } else {
            GeneralAirData.left_set_seat_cold = true;
        }
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(getFrame()[3]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]) ? 1 : 0;
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getBoolBit3(getFrame()[3]) ? 2 : GeneralAirData.in_out_auto_cycle;
        GeneralAirData.auto_defog = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(getFrame()[4], 0, 2);
        MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_defog = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        String str2 = "High";
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        int i3 = getFrame()[9];
        if (i3 == 254) {
            str2 = "Low";
        } else if (i3 != 255) {
            str2 = (i3 * 0.5d) + " °C";
        }
        GeneralAirData.front_right_temperature = str2;
        if (isAirDataChange(getFrame())) {
            updateAirActivity(getContext(), 1004);
        }
    }

    public final int[] getMAirData() {
        return this.mAirData;
    }

    public final void setMAirData(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<set-?>");
        this.mAirData = iArr;
    }

    private final boolean isAirDataChange(int[] mCanBusInfoInt) {
        if (Arrays.equals(this.mAirData, mCanBusInfoInt)) {
            return false;
        }
        this.mAirData = mCanBusInfoInt;
        return true;
    }

    private final void set0x12Data() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(getFrame()[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(getFrame()[4]);
        updateDoorView(getContext());
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x11Data() {
        /*
            r10 = this;
            int[] r0 = r10.getFrame()
            r1 = 3
            r0 = r0[r1]
            r10.updateSpeedInfo(r0)
            r0 = r10
            com.hzbhd.canbus.car._365.MsgMgr r0 = (com.hzbhd.canbus.car._365.MsgMgr) r0
            android.content.Context r0 = r10.getContext()
            int[] r2 = r10.getFrame()
            r3 = 4
            r2 = r2[r3]
            r4 = 8
            r5 = 6
            r6 = 2
            r7 = 5
            r8 = 1
            r9 = 0
            if (r2 == 0) goto L44
            if (r2 == r8) goto L42
            if (r2 == r6) goto L45
            if (r2 == r7) goto L3f
            if (r2 == r5) goto L3c
            if (r2 == r4) goto L39
            r4 = 9
            if (r2 == r4) goto L36
            r4 = 24
            if (r2 == r4) goto L34
            goto L4e
        L34:
            r4 = r1
            goto L45
        L36:
            r4 = 46
            goto L45
        L39:
            r4 = 45
            goto L45
        L3c:
            r4 = 15
            goto L45
        L3f:
            r4 = 14
            goto L45
        L42:
            r4 = 7
            goto L45
        L44:
            r4 = r9
        L45:
            int[] r2 = r10.getFrame()
            r2 = r2[r7]
            r10.realKeyLongClick1(r0, r4, r2)
        L4e:
            int[] r0 = r10.getFrame()
            r0 = r0[r5]
            r2 = 21
            if (r0 < 0) goto L5c
            if (r0 >= r2) goto L5c
            r4 = r8
            goto L5d
        L5c:
            r4 = r9
        L5d:
            if (r4 == 0) goto L61
            r1 = r8
            goto L92
        L61:
            r4 = 41
            if (r2 > r0) goto L69
            if (r0 >= r4) goto L69
            r2 = r8
            goto L6a
        L69:
            r2 = r9
        L6a:
            if (r2 == 0) goto L6e
            r1 = r6
            goto L92
        L6e:
            r2 = 61
            if (r4 > r0) goto L76
            if (r0 >= r2) goto L76
            r4 = r8
            goto L77
        L76:
            r4 = r9
        L77:
            if (r4 == 0) goto L7a
            goto L92
        L7a:
            r1 = 81
            if (r2 > r0) goto L82
            if (r0 >= r1) goto L82
            r2 = r8
            goto L83
        L82:
            r2 = r9
        L83:
            if (r2 == 0) goto L87
            r1 = r3
            goto L92
        L87:
            if (r1 > r0) goto L8e
            r1 = 101(0x65, float:1.42E-43)
            if (r0 >= r1) goto L8e
            goto L8f
        L8e:
            r8 = r9
        L8f:
            if (r8 == 0) goto L95
            r1 = r7
        L92:
            r10.setBacklightLevel(r1)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._365.MsgMgr.set0x11Data():void");
    }
}
