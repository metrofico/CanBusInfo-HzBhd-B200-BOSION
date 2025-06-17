package com.hzbhd.canbus.car._244;

import android.content.Context;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgr extends AbstractMsgMgr {
    private Context mContext;
    private boolean mute;
    private int[] mCanBusInfoInt = new int[0];
    private byte[] mCanBusInfoByte = new byte[0];

    private final int getIndexBy2Bit(boolean bit) {
        return bit ? 1 : 0;
    }

    private final int getIndexBy3Bit(int bit) {
        if (bit != 0) {
            if (bit == 1) {
                return 1;
            }
            if (bit == 2) {
                return 2;
            }
        }
        return 0;
    }

    private final int getIndexBy4Bit(int bit) {
        if (bit != 0) {
            if (bit == 1) {
                return 1;
            }
            if (bit == 2) {
                return 2;
            }
            if (bit == 3) {
                return 3;
            }
        }
        return 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {


        this.mCanBusInfoByte = canbusInfo;
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            wheelSteerBtn();
            return;
        }
        if (i == 40) {
            if (isDoorMsgRepeat(canbusInfo)) {
                return;
            }
            setDoorData0x28();
            return;
        }
        if (i == 48) {
            steeringWheelAngle(context);
            return;
        }
        if (i == 54) {
            setAirData0x36();
            return;
        }
        if (i == 64) {
            setSettingData0x40();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 35:
                    if (!isAirMsgRepeat(canbusInfo)) {
                        setAirData0x23();
                        break;
                    }
                    break;
                case 36:
                    rearRadarInfo(context);
                    break;
                case 37:
                    frontRadarInfo(context);
                    break;
            }
            return;
        }
        setVersionInfo();
    }

    private final void wheelSteerBtn() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick2(this.mContext, 0);
            return;
        }
        if (i == 1) {
            realKeyLongClick2(this.mContext, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick2(this.mContext, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick2(this.mContext, 47);
            return;
        }
        if (i == 4) {
            realKeyLongClick2(this.mContext, 48);
        } else if (i == 8) {
            realKeyLongClick2(this.mContext, HotKeyConstant.K_SPEECH);
        } else {
            if (i != 9) {
                return;
            }
            realKeyLongClick2(this.mContext, HotKeyConstant.K_PHONE_ON_OFF);
        }
    }

    private final void steeringWheelAngle(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 7784, 13784, 16);
        updateParkUi(null, context);
    }

    private final void rearRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationDataType2(3, iArr[2], 4, iArr[3], 4, iArr[4], 3, iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    private final void frontRadarInfo(Context context) {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationDataType2(3, iArr[2], 4, iArr[3], 4, iArr[4], 3, iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int currClickPresetIndex, String currBand, String currentFreq, String psName, int isStereo) throws NumberFormatException {
        int i;
        int msb;
        int lsb;



        Log.i("lyn", currBand);
        int iHashCode = currBand.hashCode();
        if (iHashCode == 2092) {
            if (currBand.equals("AM")) {
                i = 3;
                int i2 = Integer.parseInt(currentFreq);
                msb = DataHandleUtils.getMsb(i2);
                lsb = DataHandleUtils.getLsb(i2);
                sendMediaInfo$default(this, 1, i, msb, lsb, currClickPresetIndex, 0, 0, 96, null);
                return;
            }
            throw new IllegalStateException("Unexpected value: " + currBand);
        }
        if (iHashCode == 2247) {
            if (currBand.equals("FM")) {
                i = 1;
                int i3 = (int) (Double.parseDouble(currentFreq) * 100);
                msb = DataHandleUtils.getMsb(i3);
                lsb = DataHandleUtils.getLsb(i3);
                sendMediaInfo$default(this, 1, i, msb, lsb, currClickPresetIndex, 0, 0, 96, null);
                return;
            }
            throw new IllegalStateException("Unexpected value: " + currBand);
        }
        if (iHashCode == 69707 && currBand.equals("FM2")) {
            i = 2;
            int i4 = (int) (Double.parseDouble(currentFreq) * 100);
            msb = DataHandleUtils.getMsb(i4);
            lsb = DataHandleUtils.getLsb(i4);
            sendMediaInfo$default(this, 1, i, msb, lsb, currClickPresetIndex, 0, 0, 96, null);
            return;
        }
        throw new IllegalStateException("Unexpected value: " + currBand);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte) bHours24H, (byte) bMins, (byte) bSecond});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        if (stoagePath == 9) {
            sendMediaInfo$default(this, 2, 0, 0, 0, 0, currentMinute, currentSecond, 30, null);
        } else if (stoagePath == 8) {
            sendMediaInfo$default(this, 3, 0, 0, 0, 0, currentMinute, currentSecond, 30, null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaInfo$default(this, 5, 0, 0, 0, 0, 0, 0, 126, null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        sendMediaInfo$default(this, 6, 0, 0, 0, 0, 0, 0, 126, null);
    }

    private final int getD7() {
        return this.mute ? 2 : 1;
    }

    static /* synthetic */ void sendMediaInfo$default(MsgMgr msgMgr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, Object obj) {
        if ((i8 & 1) != 0) {
            i = 0;
        }
        if ((i8 & 2) != 0) {
            i2 = 0;
        }
        if ((i8 & 4) != 0) {
            i3 = 0;
        }
        if ((i8 & 8) != 0) {
            i4 = 0;
        }
        if ((i8 & 16) != 0) {
            i5 = 0;
        }
        if ((i8 & 32) != 0) {
            i6 = 0;
        }
        if ((i8 & 64) != 0) {
            i7 = 0;
        }
        msgMgr.sendMediaInfo(i, i2, i3, i4, i5, i6, i7);
    }

    private final void sendMediaInfo(int d0, int d1, int d2, int d3, int d4, int d5, int d6) {
        CanbusMsgSender.sendMsg(new byte[]{22, 117, (byte) d0, (byte) d1, (byte) d2, (byte) d3, (byte) d4, (byte) d5, (byte) d6, (byte) getD7()});
    }

    public final boolean getMute() {
        return this.mute;
    }

    public final void setMute(boolean z) {
        this.mute = z;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        this.mute = isMute;
    }

    private final void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private final void setAirData0x36() {
        updateOutDoorTemp(this.mContext, resolveOutDorrTem());
    }

    private final void setAirData0x23() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        int i = this.mCanBusInfoInt[3];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        if (i == 0) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 1) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 2) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 4) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_right_blow_window = true;
        }
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private final void setSettingData0x40() {
        int indexBy4Bit = getIndexBy4Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2));
        int indexBy3Bit = getIndexBy3Bit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        int indexBy2Bit = getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]));
        int indexBy2Bit2 = getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(indexBy4Bit)));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(indexBy3Bit)));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(indexBy2Bit)));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(indexBy2Bit2)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private final void setDoorData0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private final String resolveOutDorrTem() {
        String string;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7);
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            string = intFromByteWithBit == 0 ? " 0 " : new StringBuilder().append('-').append(intFromByteWithBit).append(' ').toString();
        } else {
            string = new StringBuilder().append(intFromByteWithBit).append(' ').toString();
        }
        return string + getTempUnitC(this.mContext);
    }

    private final String resolveLeftAndRightTemp(int value) {
        if (value == 0) {
            return "LO";
        }
        if (255 == value) {
            return "HI";
        }
        if (254 != value) {
            return (18 > value || 32 < value) ? "" : value + getTempUnitC(this.mContext);
        }
        Context context = this.mContext;

        String string = context.getString(R.string.no_display);

        return string;
    }
}
