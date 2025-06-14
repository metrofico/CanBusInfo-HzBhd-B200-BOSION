package com.hzbhd.canbus.car._402;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import nfore.android.bt.res.NfDef;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u001b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\b\u0010\r\u001a\u00020\nH\u0016JT\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001c\u0010\u001c\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0012H\u0016JÄ\u0001\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00102\u0006\u0010$\u001a\u00020 2\u0006\u0010%\u001a\u00020 2\u0006\u0010&\u001a\u00020 2\u0006\u0010'\u001a\u00020 2\u0006\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020 2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010+2\b\u0010-\u001a\u0004\u0018\u00010+2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020 2\u0006\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u00142\u0006\u00103\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u00010+2\b\u00105\u001a\u0004\u0018\u00010+2\b\u00106\u001a\u0004\u0018\u00010+2\u0006\u00107\u001a\u00020\u0014H\u0016J6\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\u00102\b\u0010:\u001a\u0004\u0018\u00010+2\b\u0010;\u001a\u0004\u0018\u00010+2\b\u0010<\u001a\u0004\u0018\u00010+2\u0006\u0010=\u001a\u00020\u0010H\u0016J\u001e\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u00102\f\b\u0002\u0010@\u001a\u00020\u0012\"\u00020 H\u0002J\b\u0010A\u001a\u00020\nH\u0002J\b\u0010B\u001a\u00020\nH\u0002J\b\u0010C\u001a\u00020\nH\u0002J\b\u0010D\u001a\u00020\nH\u0002J\b\u0010E\u001a\u00020\nH\u0002J\b\u0010F\u001a\u00020\nH\u0002J\b\u0010G\u001a\u00020\nH\u0002J\u0010\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u0012H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006J"}, d2 = {"Lcom/hzbhd/canbus/car/_402/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "frame", "", "getFrame", "()[I", "setFrame", "([I)V", "afterServiceNormalSetting", "", "context", "Landroid/content/Context;", "auxInInfoChange", "btPhoneStatusInfoChange", "callStatus", "", "phoneNumber", "", "isHfpConnected", "", "isCallingFlag", "isMicMute", "isAudioTransferTowardsAG", "batteryStatus", "signalValue", "bundle", "Landroid/os/Bundle;", "canbusInfoChange", "canbusInfo", "musicInfoChange", "stoagePath", "", "playRatio", "currentPlayingIndexLow", "totalCount", "currentHour", "currentMinute", "currentSecond", "currentAllMinuteStr", "currentPlayingIndexHigh", "currentAllMinute", "currentHourStr", "", "currentMinuteStr", "currentSecondStr", "currentPos", "", "playModel", "playIndex", "isPlaying", "totalTime", "songTitle", "songAlbum", "songArtist", "isReportFromPlay", "radioInfoChange", "currClickPresetIndex", "currBand", "currentFreq", "psName", "isStereo", "sendMediaData", "d0", "d1t12", "set0x11Data", "set0x12Data", "set0x1AData", "set0x21Data", "set0x26Data", "set0x31Data", "set0x41Data", "set0xF0Data", "bytes", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public int[] frame;

    private final void set0x26Data() {
    }

    private static final int set0x41Data$restrictValue(int i) {
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._402.UiMgr");
        InitUtilsKt.initDrivingItemsIndexHashMap$default(context, (UiMgr) canUiMgr, null, 4, null);
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentCanDifferentId(), NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) {
        byte b = 3;
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -111;
        if (currBand != null) {
            switch (currBand.hashCode()) {
                case 64901:
                    if (currBand.equals("AM1")) {
                        b = 4;
                        break;
                    } else {
                        return;
                    }
                case 64902:
                    if (currBand.equals("AM2")) {
                        b = 5;
                        break;
                    } else {
                        return;
                    }
                case 69706:
                    if (currBand.equals("FM1")) {
                        b = 1;
                        break;
                    } else {
                        return;
                    }
                case 69707:
                    if (currBand.equals("FM2")) {
                        b = 2;
                        break;
                    } else {
                        return;
                    }
                case 69708:
                    if (!currBand.equals("FM3")) {
                        return;
                    }
                    break;
                default:
                    return;
            }
            bArr[2] = b;
            Intrinsics.checkNotNull(currentFreq);
            CanbusMsgSender.sendMsg(MsgMgrKt.restrict(ArraysKt.plus(bArr, com.hzbhd.canbus.car._403.MsgMgrKt.radioAscii(currClickPresetIndex, currBand, 8, currentFreq)), 13, 32));
        }
    }

    static /* synthetic */ void sendMediaData$default(MsgMgr msgMgr, int i, byte[] bArr, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            bArr = new byte[0];
        }
        msgMgr.sendMediaData(i, bArr);
    }

    private final void sendMediaData(int d0, byte... d1t12) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -111}, MsgMgrKt.restrict$default(d1t12, 13, 0, 4, null)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaData$default(this, 12, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (stoagePath != 9) {
            sendMediaData$default(this, 13, null, 2, null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        sendMediaData$default(this, 10, null, 2, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {
        Intrinsics.checkNotNull(canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo!!)");
        setFrame(byteArrayToIntArray);
        int i = getFrame()[1];
        if (i == 17) {
            set0x11Data();
            return;
        }
        if (i == 18) {
            set0x12Data();
            return;
        }
        if (i == 26) {
            set0x1AData();
            return;
        }
        if (i == 38) {
            set0x26Data();
            return;
        }
        if (i == 49) {
            set0x31Data();
            return;
        }
        if (i == 65) {
            set0x41Data();
            return;
        }
        if (i == 240) {
            set0xF0Data(canbusInfo);
            return;
        }
        if (i == 33) {
            set0x21Data();
            return;
        }
        if (i != 34) {
            return;
        }
        byte b = canbusInfo[3];
        byte lastKnobValue = com.hzbhd.canbus.car._369.MsgMgrKt.getLastKnobValue();
        int iAbs = Math.abs(b - lastKnobValue);
        int i2 = getFrame()[2];
        if (i2 != 1) {
            if (i2 == 2) {
                if (b > lastKnobValue) {
                    DataHandleUtils.knob(context, 46, iAbs);
                } else if (b < lastKnobValue) {
                    DataHandleUtils.knob(context, 45, iAbs);
                }
            }
        } else if (b > lastKnobValue) {
            DataHandleUtils.knob(context, 7, iAbs);
        } else if (b < lastKnobValue) {
            DataHandleUtils.knob(context, 8, iAbs);
        }
        com.hzbhd.canbus.car._369.MsgMgrKt.setLastKnobValue(canbusInfo[3]);
    }

    private final void set0xF0Data(byte[] bytes) {
        updateVersionInfo(InitUtilsKt.getMContext(), getVersionStr(bytes));
    }

    private final void set0x31Data() {
        String str;
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(getFrame()[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(getFrame()[4]);
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 11) {
            GeneralAirData.front_left_blow_window = true;
        } else if (i == 12) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        int i2 = getFrame()[8];
        if (i2 != 254) {
            str = i2 != 255 ? (i2 * 0.5d) + " °C" : "High";
        } else {
            str = "Low";
        }
        GeneralAirData.front_left_temperature = str;
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    private final void set0x41Data() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(6, set0x41Data$restrictValue(getFrame()[2]), set0x41Data$restrictValue(getFrame()[3]), set0x41Data$restrictValue(getFrame()[4]), set0x41Data$restrictValue(getFrame()[5]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }

    private final void set0x1AData() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        DriverDataPageUiSet.Page.Item item = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_2");
        if (item != null) {
            item.setValue(DataHandleUtils.getMsbLsbResult(getFrame()[5], getFrame()[6]) + " km/h");
        }
        DriverDataPageUiSet.Page.Item item2 = InitUtilsKt.getMDrivingItemIndex().get("D314_Speed_1");
        if (item2 != null) {
            item2.setValue(String.valueOf(DataHandleUtils.getMsbLsbResult(getFrame()[11], getFrame()[12])));
        }
        updateDriveDataActivity(null);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(getFrame()[5], getFrame()[6]));
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void set0x21Data() {
        /*
            r7 = this;
            android.content.Context r0 = com.hzbhd.canbus.util.InitUtilsKt.getMContext()
            int[] r1 = r7.getFrame()
            r2 = 2
            r1 = r1[r2]
            r3 = 47
            r4 = 1
            r5 = 52
            r6 = 3
            if (r1 == r4) goto L6f
            if (r1 == r2) goto L6c
            if (r1 == r6) goto L69
            r4 = 6
            if (r1 == r4) goto L66
            r4 = 9
            if (r1 == r4) goto L64
            r4 = 18
            if (r1 == r4) goto L62
            r4 = 32
            if (r1 == r4) goto L5f
            r4 = 36
            if (r1 == r4) goto L70
            r4 = 43
            if (r1 == r4) goto L62
            if (r1 == r3) goto L62
            r4 = 51
            if (r1 == r4) goto L70
            r2 = 62
            if (r1 == r2) goto L64
            r2 = 76
            if (r1 == r2) goto L5c
            r2 = 69
            if (r1 == r2) goto L5a
            r2 = 70
            if (r1 == r2) goto L57
            switch(r1) {
                case 22: goto L54;
                case 23: goto L51;
                case 24: goto L4e;
                case 25: goto L4c;
                case 26: goto L49;
                case 27: goto L51;
                case 28: goto L4e;
                case 29: goto L4c;
                case 30: goto L49;
                default: goto L47;
            }
        L47:
            r2 = 0
            goto L70
        L49:
            r2 = 48
            goto L70
        L4c:
            r2 = r3
            goto L70
        L4e:
            r2 = 46
            goto L70
        L51:
            r2 = 45
            goto L70
        L54:
            r2 = 49
            goto L70
        L57:
            r2 = 8
            goto L70
        L5a:
            r2 = 7
            goto L70
        L5c:
            r2 = 188(0xbc, float:2.63E-43)
            goto L70
        L5f:
            r2 = 128(0x80, float:1.8E-43)
            goto L70
        L62:
            r2 = r5
            goto L70
        L64:
            r2 = r6
            goto L70
        L66:
            r2 = 50
            goto L70
        L69:
            r2 = 20
            goto L70
        L6c:
            r2 = 21
            goto L70
        L6f:
            r2 = r4
        L70:
            int[] r1 = r7.getFrame()
            r1 = r1[r6]
            r7.realKeyLongClick1(r0, r2, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._402.MsgMgr.set0x21Data():void");
    }

    private final void set0x12Data() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(getFrame()[4]);
        updateDoorView(InitUtilsKt.getMContext());
    }

    private final void set0x11Data() {
        Context mContext = InitUtilsKt.getMContext();
        int i = getFrame()[4];
        int i2 = 3;
        if (i == 1) {
            i2 = 7;
        } else if (i == 2) {
            i2 = 8;
        } else if (i != 3) {
            i2 = i != 5 ? i != 6 ? i != 8 ? i != 9 ? i != 11 ? 0 : 2 : 20 : 21 : 15 : 14;
        }
        realKeyLongClick1(mContext, i2, getFrame()[5]);
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) getFrame()[9], (byte) getFrame()[8], 0, 540, 16);
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
