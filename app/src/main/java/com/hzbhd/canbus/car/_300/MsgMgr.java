package com.hzbhd.canbus.car._300;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mDifferent;
    private int mEachId;
    private boolean mFrontStatus;
    private boolean mIsDoorFirst = true;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int[] mRadarDataNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 2) {
            setAirData0x02(context);
            return;
        }
        if (i == 3) {
            setRadarData0x03(context);
            return;
        }
        if (i == 4) {
            setVehicleData0x04(context);
            return;
        }
        if (i == 6) {
            setMultimediaKeyData0x06(context);
        } else if (i == 48) {
            setVersionInfo0x30(context);
        } else {
            if (i != 101) {
                return;
            }
            setEnginSpeedData0x65();
        }
    }

    private void setVersionInfo0x30(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void setAirData0x02(Context context) {
        String str = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]) ? "open" : "close";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, str));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        byte[] bArr = this.mCanBusInfoByte;
        bArr[6] = (byte) (bArr[6] & 1);
        updateOutDoorTemp(context, ((int) this.mCanBusInfoByte[7]) + getTempUnitC(context));
        byte[] bArr2 = this.mCanBusInfoByte;
        bArr2[7] = 0;
        if (isAirMsgRepeat(bArr2)) {
            return;
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAirData.front_auto_wind_speed = intFromByteWithBit == 8;
        GeneralAirData.front_wind_level = intFromByteWithBit;
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAirData.front_left_blow_window = isWindModeMatch(intFromByteWithBit2, 4, 5);
        GeneralAirData.front_left_blow_head = isWindModeMatch(intFromByteWithBit2, 1, 2);
        GeneralAirData.front_left_blow_foot = isWindModeMatch(intFromByteWithBit2, 2, 3, 4);
        GeneralAirData.front_left_auto_wind = isWindModeMatch(intFromByteWithBit2, 6);
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
        GeneralAirData.front_left_temperature = resolveAirTemp(context, this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_temperature = resolveAirTemp(context, this.mCanBusInfoInt[4]);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.ac = intFromByteWithBit3 == 2;
        GeneralAirData.ac_auto = intFromByteWithBit3 == 3;
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.in_out_auto_cycle = intFromByteWithBit4;
        if (intFromByteWithBit4 == 2) {
            GeneralAirData.in_out_auto_cycle = 0;
        }
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralAirData.max_cool = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.power = intFromByteWithBit != 0;
        updateAirActivity(context, 1001);
    }

    private void setRadarData0x03(Context context) {
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(3, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(3, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void setVehicleData0x04(Context context) {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanBusInfoInt[4])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setEnginSpeedData0x65() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(iArr[3] + (iArr[2] * 256)).append(" rpm").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append(" km/h").toString()));
        int[] iArr3 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr3[4] * 256) + iArr3[5]);
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr4 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr4[4], iArr4[5]));
    }

    private void setMultimediaKeyData0x06(Context context) {
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                realKeyClick3_1(context, 7);
                break;
            case 2:
                realKeyClick3_1(context, 8);
                break;
            case 3:
                realKeyClick3_2(context, 48);
                break;
            case 4:
                realKeyClick3_2(context, 47);
                break;
            case 5:
                realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                break;
            case 6:
                realKeyLongClick1(context, 49);
                break;
            case 7:
                realKeyLongClick1(context, 3);
                break;
            case 8:
                realKeyLongClick1(context, 128);
                break;
            case 9:
                realKeyLongClick1(context, 129);
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i11 = i10 - 1;
        if (i11 == 0) {
            i11 = 7;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) i11});
    }

    private boolean isWindModeMatch(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private String resolveAirTemp(Context context, int i) {
        return i == 0 ? "" : i == 1 ? "LO" : i == 63 ? "HI" : (i / 2.0f) + getTempUnitC(context);
    }

    private String resolveOutDoorTemperature(Context context) {
        return (this.mCanBusInfoByte[7] - 40) + getTempUnitC(context);
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick3_1(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick3_2(context, i, iArr[2], iArr[3]);
    }
}
