package com.hzbhd.canbus.car._260;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private int FreqInt;
    private byte freqHi;
    private byte freqLo;
    private boolean isMute;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusAirInfoCopy;
    private byte[] mCanbusDoorInfoCopy;
    private Context mContext;
    private boolean mIsHfpConnected;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_FRONT_OPEN = "is_front_open";
    private UiMgr uiMgr = null;

    private int getHsbMsbLsbResult(int i, int i2, int i3) {
        return ((i & 65535) << 16) | ((i2 & 255) << 8) | (i3 & 255);
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.uiMgr = getUiMgr(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -53, 1}, bArr));
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 6}, Util.exceptBOMHead("There's a call".getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -53, 1}, bArr));
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 6}, Util.exceptBOMHead("Make a call".getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -53, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -53, 1}, bArr));
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 6}, Util.exceptBOMHead("Talking".getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._260.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._260.MsgMgr.1.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        try {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumberState(MsgMgr.this.SplicingByte(new byte[]{22, -53, 6}, Util.exceptBOMHead("Tel Off".getBytes("unicode"))));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        try {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumberState(MsgMgr.this.SplicingByte(new byte[]{22, -53, 6}, Util.exceptBOMHead("Tel Off".getBytes("unicode"))));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i == 51) {
            tripComputerInfo();
            return;
        }
        if (i == 64) {
            vehicleSettingFeedbackStatus();
            return;
        }
        if (i != 149) {
            switch (i) {
                case 32:
                    realKeyControl();
                    break;
                case 33:
                    if (!isAirMsgReturn(bArr)) {
                        setAirData0x21();
                        break;
                    }
                    break;
                case 34:
                    setRadarInfo();
                    break;
                case 35:
                    frontRadarInfo();
                    break;
                case 36:
                    if (!isDoorMsgReturn(bArr)) {
                        setDoorData0x24();
                        break;
                    }
                    break;
                default:
                    switch (i) {
                        case 39:
                            setAirData0x27();
                            break;
                        case 40:
                            setDriveData0x28();
                            break;
                        case 41:
                            setTrackInfo();
                            break;
                    }
            }
            return;
        }
        emergencyCallStatusInfo(context);
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.minimum);
        } else if (i == 255) {
            string = this.mContext.getResources().getString(R.string.max);
        } else {
            string = this.mCanBusInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void realKeyControl() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            realKeyClick(0);
            return;
        }
        if (i == 1) {
            realKeyClick(7);
            return;
        }
        if (i == 2) {
            realKeyClick(8);
            return;
        }
        if (i == 3) {
            realKeyClick3(this.mContext, 45, i, iArr[3]);
            return;
        }
        if (i == 4) {
            realKeyClick3(this.mContext, 46, i, iArr[3]);
            return;
        }
        if (i == 6) {
            realKeyClick(3);
            return;
        }
        if (i == 7) {
            realKeyClick(2);
            return;
        }
        if (i == 21) {
            realKeyClick(50);
            return;
        }
        if (i != 22) {
            switch (i) {
                case 9:
                    realKeyClick(14);
                    break;
                case 10:
                    realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                    break;
                case 11:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
            }
            return;
        }
        realKeyClick(49);
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2);
        if (intFromByteWithBit == 0) {
            GeneralAirData.center_wheel = "Soft";
        } else if (intFromByteWithBit == 1) {
            GeneralAirData.center_wheel = "Normal";
        } else if (intFromByteWithBit == 2) {
            GeneralAirData.center_wheel = "Fast";
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanBusInfoInt[5]);
        if (DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])) {
            updateAirActivity(this.mContext, 1001);
        }
        if (getCurrentCanDifferentId() == 1 || getCurrentCanDifferentId() == 2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, this.uiMgr.settingPageIndex.get("_260_settingInfo_0_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2) == 0 ? 0 : 1)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isShowCarDoor = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (GeneralDoorData.isShowCarDoor && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_front_open", GeneralDoorData.isFrontOpen);
            updateDoorView(this.mContext);
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        String str = intFromByteWithBit != 0 ? intFromByteWithBit != 1 ? intFromByteWithBit != 2 ? "" : "D" : "R" : "P";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str));
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 1, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setRadarInfo() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void frontRadarInfo() {
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setAirData0x27() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setDriveData0x28() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, sb.append(iArr[2] + (iArr[3] * 256)).append("Km/h").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 4, sb2.append(iArr2[4] + (iArr2[5] * 256)).append("RPM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo(iArr3[2] + (iArr3[3] * 256));
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5376, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void tripComputerInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append(getMsbLsbResult(iArr[2], iArr[3]) / 10.0d).append("L/100Km").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, sb2.append(getMsbLsbResult(iArr2[4], iArr2[5]) / 10.0d).append("Km/H").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb3.append(getHsbMsbLsbResult(iArr3[6], iArr3[7], iArr3[8]) / 10.0d).append("Km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void vehicleSettingFeedbackStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_260_settingInfo_0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_260_settingInfo_0", "_260_settingInfo_0_1"), Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_1").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_5").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
        arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
        arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_1").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_2").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_3").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
        arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_4").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(3, this.uiMgr.settingPageIndex.get("_260_settingInfo_3_2").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        arrayList.add(new SettingUpdateEntity(3, this.uiMgr.settingPageIndex.get("_260_settingInfo_3_4").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
        arrayList.add(new SettingUpdateEntity(4, this.uiMgr.settingPageIndex.get("_260_settingInfo_4_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        arrayList.add(new SettingUpdateEntity(4, this.uiMgr.settingPageIndex.get("_260_settingInfo_4_2").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
        arrayList.add(new SettingUpdateEntity(4, this.uiMgr.settingPageIndex.get("_260_settingInfo_4_3").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(5, this.uiMgr.settingPageIndex.get("_260_settingInfo_5_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
        arrayList.add(new SettingUpdateEntity(5, this.uiMgr.settingPageIndex.get("_260_settingInfo_5_1").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
        if (getCurrentCanDifferentId() == 5 || getCurrentCanDifferentId() == 6) {
            arrayList.add(new SettingUpdateEntity(2, this.uiMgr.settingPageIndex.get("_260_settingInfo_2_5").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
            arrayList.add(new SettingUpdateEntity(3, this.uiMgr.settingPageIndex.get("_260_settingInfo_3_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
            arrayList.add(new SettingUpdateEntity(3, this.uiMgr.settingPageIndex.get("_260_settingInfo_3_1").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
            arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_0").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
            arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_2").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_3").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            arrayList.add(new SettingUpdateEntity(1, this.uiMgr.settingPageIndex.get("_260_settingInfo_1_4").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
            arrayList.add(new SettingUpdateEntity(3, this.uiMgr.settingPageIndex.get("_260_settingInfo_3_3").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))));
            arrayList.add(new SettingUpdateEntity(4, this.uiMgr.settingPageIndex.get("_260_settingInfo_4_1").intValue(), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        MyLog.temporaryTracking("刷新设置");
    }

    private void emergencyCallStatusInfo(Context context) {
        if (this.mCanBusInfoInt[2] == 1) {
            if (this.isMute) {
                return;
            }
            realKeyClick(this.mContext, 3);
        } else if (this.isMute) {
            realKeyClick(this.mContext, 3);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        MyLog.temporaryTracking("当前状态：" + i + "___" + i);
        this.isMute = z;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        byte b;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 64901:
                if (str.equals("AM1")) {
                    c = 0;
                    break;
                }
                break;
            case 64902:
                if (str.equals("AM2")) {
                    c = 1;
                    break;
                }
                break;
            case 69706:
                if (str.equals("FM1")) {
                    c = 2;
                    break;
                }
                break;
            case 69707:
                if (str.equals("FM2")) {
                    c = 3;
                    break;
                }
                break;
            case 69708:
                if (str.equals("FM3")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
                b = 16;
                break;
            case 2:
            default:
                b = 1;
                break;
            case 3:
                b = 2;
                break;
            case 4:
                b = 3;
                break;
        }
        getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, b, this.freqLo, this.freqHi, (byte) i, 0, 0});
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 5}, Util.exceptBOMHead(("NOW BAND: " + str).getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, i != 1 ? i != 2 ? i != 4 ? (byte) 0 : (byte) 2 : (byte) 3 : (byte) 1, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(z2 ? DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, bArr) : DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, new byte[]{0}));
        if (z) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        getUiMgr(this.mContext).sendMediaInfo(11, 0);
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 2}, Util.exceptBOMHead(str.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 3}, Util.exceptBOMHead(str3.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 4}, Util.exceptBOMHead(str2.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).sendMediaInfo(8, 0);
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 2}, Util.exceptBOMHead(str4.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 3}, Util.exceptBOMHead(str5.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        try {
            getUiMgr(this.mContext).sendPhoneNumberState(SplicingByte(new byte[]{22, -53, 4}, Util.exceptBOMHead(str6.getBytes("unicode"))));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -53, 2}, new byte[]{0}));
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInDestdroy() {
        super.auxInDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArr = {22, -64, 8, 19, (byte) i, b6, 0, 0, 0, 0};
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, bArr));
        getUiMgr(this.mContext).sendMediaInfo(8, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        return i == 0 ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusAirInfoCopy)) {
            return true;
        }
        this.mCanbusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusDoorInfoCopy)) {
            return true;
        }
        this.mCanbusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private String getCloseOpenStr(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.close);
        }
        return i == 1 ? this.mContext.getResources().getString(R.string.open) : "set_default";
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[2];
        if (1 > i || i > 254) {
            return i == 0 ? "--" : "";
        }
        return ((this.mCanBusInfoInt[2] * 0.5f) - 39.5d) + "" + getTempUnitC(this.mContext);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_front_open", false) == GeneralDoorData.isFrontOpen) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }
}
