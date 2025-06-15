package com.hzbhd.canbus.car._408;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.car._361.MsgMgrKt;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.InitUtilsKt;
import com.hzbhd.canbus.util.RadarInfoUtil;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    private boolean[] array = {GeneralDoorData.isLeftFrontDoorOpen, GeneralDoorData.isRightFrontDoorOpen, GeneralDoorData.isLeftRearDoorOpen, GeneralDoorData.isRightRearDoorOpen, GeneralDoorData.isBackOpen};
    public int[] frame;

    private static final int set0x72Data$restrictValue(int i) {
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

        return null;
    }

    public final void setFrame(int[] iArr) {

        this.frame = iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        InitUtilsKt.setMContext(context);
    }

    private final void send0xD2Data(int d0) {
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -46}, MsgMgrKt.restrict$default(new byte[]{(byte) d0}, 13, 0, 4, null)));
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
            send0xD2Data(i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        send0xD2Data(stoagePath == 9 ? 14 : 13);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        setFrame(byteArrayToIntArray);
        switch (getFrame()[1]) {
            case 114:
                set0x72Data();
                break;
            case 115:
                set0x73Data();
                break;
            case 116:
                set0x74Data();
                break;
        }
    }

    private final void set0x74Data() {
        int i;
        Context mContext = InitUtilsKt.getMContext();
        switch (getFrame()[2]) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 21;
                break;
            case 3:
                i = 20;
                break;
            case 4:
                i = 2;
                break;
            case 5:
                i = 76;
                break;
            case 6:
                i = 4;
                break;
            case 7:
                i = 30;
                break;
            case 8:
                i = 151;
                break;
            case 9:
                i = 3;
                break;
            case 10:
                i = 33;
                break;
            case 11:
                i = 34;
                break;
            case 12:
                i = 35;
                break;
            case 13:
                i = 36;
                break;
            case 14:
                i = 37;
                break;
            case 15:
                i = 38;
                break;
            case 16:
                i = 128;
                break;
            case 17:
                i = 31;
                break;
            default:
                i = 0;
                break;
        }
        realKeyClick4(mContext, i);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 6, 2);
        if (intFromByteWithBit == 1) {
            DataHandleUtils.knob(InitUtilsKt.getMContext(), 7, DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 4));
        } else {
            if (intFromByteWithBit != 2) {
                return;
            }
            DataHandleUtils.knob(InitUtilsKt.getMContext(), 8, DataHandleUtils.getIntFromByteWithBit(getFrame()[3], 0, 4));
        }
    }

    private final void set0x73Data() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(getFrame()[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(getFrame()[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(getFrame()[3]);
        GeneralAirData.front_left_temperature = "Level " + getFrame()[4];
        com.hzbhd.canbus.car._281.MsgMgrKt.windDirectionInit();
        int i = getFrame()[6];
        if (i == 0) {
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
        }
        GeneralAirData.front_wind_level = getFrame()[7];
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(getFrame()[8]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(getFrame()[8]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(getFrame()[8]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(getFrame()[8]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(getFrame()[8]);
        boolean[] zArr = {DataHandleUtils.getBoolBit7(getFrame()[8]), DataHandleUtils.getBoolBit6(getFrame()[8]), DataHandleUtils.getBoolBit5(getFrame()[8]), DataHandleUtils.getBoolBit4(getFrame()[8]), DataHandleUtils.getBoolBit3(getFrame()[8])};
        if (!Arrays.equals(zArr, this.array)) {
            updateDoorView(InitUtilsKt.getMContext());
            this.array = zArr;
        }
        updateAirActivity(InitUtilsKt.getMContext(), 1004);
    }

    public final boolean[] getArray() {
        return this.array;
    }

    public final void setArray(boolean[] zArr) {

        this.array = zArr;
    }

    private final void set0x72Data() {
        int i;
        int i2 = getFrame()[7];
        if (i2 >= 0 && i2 < 21) {
            i = 1;
        } else {
            if (21 <= i2 && i2 < 41) {
                i = 2;
            } else {
                if (41 <= i2 && i2 < 61) {
                    i = 3;
                } else {
                    if (!(61 <= i2 && i2 < 81)) {
                        if (81 <= i2 && i2 < 101) {
                            i = 5;
                        }
                        RadarInfoUtil.mMinIsClose = true;
                        RadarInfoUtil.setRearRadarLocationData(15, set0x72Data$restrictValue(getFrame()[8]), 0, 0, set0x72Data$restrictValue(getFrame()[11]));
                        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                        updateParkUi(null, InitUtilsKt.getMContext());
                    }
                    i = 4;
                }
            }
        }
        setBacklightLevel(i);
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.setRearRadarLocationData(15, set0x72Data$restrictValue(getFrame()[8]), 0, 0, set0x72Data$restrictValue(getFrame()[11]));
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, InitUtilsKt.getMContext());
    }
}
