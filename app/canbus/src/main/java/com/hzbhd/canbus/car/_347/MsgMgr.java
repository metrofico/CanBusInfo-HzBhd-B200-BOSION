package com.hzbhd.canbus.car._347;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.car._333.AlertView;
import com.hzbhd.canbus.car._341.MsgMgrKt;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] OutGoingPhoneNumber;
    int alarmInfo1;
    int alarmInfo2;
    int carDoorInt;
    int[] comingPhoneNumber;
    int differentId;
    private int eachId;
    DecimalFormat integerFormat = new DecimalFormat("0");
    int[] mAirData;
    int[] mBackLightInt;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    private byte mFreqHi;
    private byte mFreqLo;
    int[] mFrontRadarData;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    int[] mTireInfo;
    byte[] mTrackData;
    private int[] talkingPhoneNumber;
    private UiMgr uiMgr;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getSurroundVolFRprogress(int i) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return i;
            default:
                switch (i) {
                    case 247:
                        return -9;
                    case 248:
                        return -8;
                    case com.hzbhd.canbus.car._464.MsgMgr.REAR_DISC_MODE /* 249 */:
                        return -7;
                    case 250:
                        return -6;
                    case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                        return -5;
                    case 252:
                        return -4;
                    case 253:
                        return -3;
                    case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                        return -2;
                    case 255:
                        return -1;
                    default:
                        return 0;
                }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).sendCarType();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(this.mContext).sendCarType();
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            setBacklightInfo0x14();
            return;
        }
        if (i == 22) {
            setSpeedInfo0x16();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i != 65) {
            switch (i) {
                case 32:
                    setWheelKeyInfo0x20();
                    break;
                case 33:
                    if (this.eachId == 5) {
                        setAirData0x21();
                        break;
                    }
                    break;
                case 34:
                    int i2 = this.eachId;
                    if (i2 == 5 || i2 == 6) {
                        setRearRadarInfo0x22();
                        break;
                    }
                case 35:
                    int i3 = this.eachId;
                    if (i3 == 5 || i3 == 6) {
                        setFrontRadarInfo0x23();
                        break;
                    }
                case 36:
                    setDoorData0x24();
                    break;
                case 37:
                    if (this.eachId == 5) {
                        setPanoramicData0x25();
                        break;
                    }
                    break;
                case 38:
                    setTrackData0x26();
                    break;
                case 39:
                    setAmplifierInfo0x27();
                    break;
                case 40:
                    setPanelKey0x28(byteArrayToIntArray);
                    break;
            }
            return;
        }
        setDoorData0x41();
    }

    private void setPanelKey0x28(int[] iArr) {
        int i = iArr[2];
        if (i == 24) {
            realKeyLongClick1(this.mContext, HotKeyConstant.K_ACTION_RADIO, iArr[3]);
            return;
        }
        if (i == 56) {
            realKeyClick4(this.mContext, 33);
            return;
        }
        if (i == 64) {
            realKeyClick4(this.mContext, 7);
            return;
        }
        if (i == 65) {
            realKeyClick4(this.mContext, 8);
            return;
        }
        if (i == 80) {
            realKeyClick4(this.mContext, 45);
            return;
        }
        if (i != 81) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 129, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 39, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 128, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 40, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 41, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 58, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 42, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 47, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 50, iArr[3]);
                    break;
                case 16:
                    realKeyLongClick1(this.mContext, 48, iArr[3]);
                    break;
            }
            return;
        }
        realKeyClick4(this.mContext, 46);
    }

    private void setBacklightInfo0x14() {
        if (isBackLightIntChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info1"), this.mCanBusInfoInt[2] + ""));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        adjustBrightness();
    }

    private void setSpeedInfo0x16() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info2");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(MsgMgrKt.getMsbLsbResult(iArr[3], iArr[2]) / 16).append("km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo((int) (MsgMgrKt.getMsbLsbResult(iArr2[3], iArr2[2]) / 16.0f));
    }

    private void setWheelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i != 22) {
            switch (i) {
                case 0:
                    buttonKey(0);
                    break;
                case 1:
                    buttonKey(7);
                    break;
                case 2:
                    buttonKey(8);
                    break;
                case 3:
                    buttonKey(20);
                    break;
                case 4:
                    buttonKey(21);
                    break;
                case 5:
                    buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                    break;
                case 6:
                    buttonKey(3);
                    break;
                case 7:
                    buttonKey(2);
                    break;
                case 8:
                    buttonKey(136);
                    break;
                case 9:
                    buttonKey(14);
                    break;
                case 10:
                    buttonKey(15);
                    break;
                case 11:
                    buttonKey(135);
                    break;
            }
        }
        buttonKey(49);
    }

    private void setAirData0x21() {
        if (isAirDataChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = getTemperature(this.mCanBusInfoInt[4], 0.5d, 0.0d, "C", 0, com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE);
        GeneralAirData.front_right_temperature = getTemperature(this.mCanBusInfoInt[5], 0.5d, 0.0d, "C", 0, com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadarInfo0x22() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x23() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x24() {
        if (isBasicInfoChange()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info3"), getSwitchState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info4"), getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info5"), getReversingState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info10"), getP_GearState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        adjustBrightness();
    }

    private void setPanoramicData0x25() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info1"), getSwitchState(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_paring_info2"), getSwitchState(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_246_paring_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_paring_info", "_246_paring_info2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrackData0x26() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setAmplifierInfo0x27() {
        ArrayList arrayList = new ArrayList();
        GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[6];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[8];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[7];
        GeneralAmplifierData.frontRear = getSurroundVolFRprogress(this.mCanBusInfoInt[4]);
        GeneralAmplifierData.leftRight = getSurroundVolFRprogress(this.mCanBusInfoInt[5]);
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_330_Vehicle_volume_follows_ASL"), Integer.valueOf(this.mCanBusInfoInt[9])).setProgress(this.mCanBusInfoInt[9]));
        updateAmplifierActivity(new Bundle());
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x41() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        } else if (i != 2) {
            int i2 = 0;
            if (i == 3) {
                String str = "";
                if (isAlarm1InfoChange() && DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
                    str = "【" + this.mContext.getString(R.string._246_car_info20) + "1：" + this.mContext.getString(R.string._246_car_info18) + "】";
                    i2 = 1;
                }
                int i3 = i2;
                if (isAlarm2InfoChange() && DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
                    str = str + "【" + this.mContext.getString(R.string._246_car_info20) + (i2 + 1) + "：" + this.mContext.getString(R.string._246_car_info19) + "】";
                    i3 = 1;
                }
                if (i3 == 1) {
                    showDialog(str);
                }
            } else if (i == 64) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) - 1));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 1));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_330_amplifier_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_330_amplifier_info", "_347_setting_0_7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) - 1));
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_setting_0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_246_setting_car_info32", "_347_setting_0_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
                updateGeneralSettingData(arrayList);
                updateActivity(null);
            } else if (i == 128) {
                ArrayList arrayList2 = new ArrayList();
                if (this.mCanBusInfoInt[4] > 0) {
                    arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_0"), Integer.valueOf(this.mCanBusInfoInt[4] - 1)));
                }
                if (this.mCanBusInfoInt[5] > 0) {
                    arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_1"), Integer.valueOf(this.mCanBusInfoInt[5] - 1)));
                }
                if (this.mCanBusInfoInt[6] > 5) {
                    arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_347_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_347_settings", "_347_settings_2"), Integer.valueOf(this.mCanBusInfoInt[6] - 6)));
                }
                updateGeneralSettingData(arrayList2);
                updateActivity(null);
            }
        } else {
            ArrayList arrayList3 = new ArrayList();
            int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13");
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr2[3] * 256) + iArr2[4]).append(" RPM").toString()));
            int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14");
            StringBuilder sb2 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(((iArr3[5] * 256) + iArr3[6]) * 0.01d).append(" Km/h").toString()));
            int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info15");
            StringBuilder sb3 = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(((iArr4[7] * 256) + iArr4[8]) * 0.01d).append(" V").toString()));
            if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9])) {
                Context context = this.mContext;
                StringBuilder sb4 = new StringBuilder();
                DecimalFormat decimalFormat = this.integerFormat;
                int[] iArr5 = this.mCanBusInfoInt;
                updateOutDoorTemp(context, sb4.append(decimalFormat.format((((iArr5[9] * 256) + iArr5[10]) * 0.1d) - 6553.6d)).append(getTempUnitC(this.mContext)).toString());
            } else {
                Context context2 = this.mContext;
                StringBuilder sb5 = new StringBuilder();
                DecimalFormat decimalFormat2 = this.integerFormat;
                int[] iArr6 = this.mCanBusInfoInt;
                updateOutDoorTemp(context2, sb5.append(decimalFormat2.format(((iArr6[9] * 256) + iArr6[10]) * 0.1d)).append(getTempUnitC(this.mContext)).toString());
            }
            int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info");
            int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info16");
            StringBuilder sb6 = new StringBuilder();
            int[] iArr7 = this.mCanBusInfoInt;
            arrayList3.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb6.append((iArr7[11] * 65536) + (iArr7[12] * 256) + iArr7[13]).append(" Km").toString()));
            arrayList3.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info17"), this.mCanBusInfoInt[14] + " L"));
            updateGeneralDriveData(arrayList3);
            updateDriveDataActivity(null);
        }
        GeneralDoorData.isShowHandBrake = true;
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private String getTemperature(int i, double d, double d2, String str, int i2, int i3) {
        if (i == i2) {
            return "LO";
        }
        if (i == i3) {
            return "HI";
        }
        if (str.trim().equals("C")) {
            return ((i * d) + d2) + getTempUnitC(this.mContext);
        }
        return ((i * d) + d2) + getTempUnitF(this.mContext);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void adjustBrightness() {
        int i = this.mCanBusInfoInt[2];
        if (i <= 50) {
            setBacklightLevel(5);
            return;
        }
        if (i > 50 && i <= 100) {
            setBacklightLevel(4);
            return;
        }
        if (i > 100 && i <= 150) {
            setBacklightLevel(3);
            return;
        }
        if (i > 150 && i <= 200) {
            setBacklightLevel(2);
        } else if (i > 200) {
            setBacklightLevel(1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        if (z) {
            getUiMgr(this.mContext).sendVol(getDecimalFrom8Bit(1, DataHandleUtils.getIntFromByteWithBit(i, 6, 1), DataHandleUtils.getIntFromByteWithBit(i, 5, 1), DataHandleUtils.getIntFromByteWithBit(i, 4, 1), DataHandleUtils.getIntFromByteWithBit(i, 3, 1), DataHandleUtils.getIntFromByteWithBit(i, 2, 1), DataHandleUtils.getIntFromByteWithBit(i, 1, 1), DataHandleUtils.getIntFromByteWithBit(i, 0, 1)));
        } else {
            getUiMgr(this.mContext).sendVol(i);
        }
    }

    private int getDecimalFrom8Bit(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        return Integer.parseInt(i + "" + i2 + "" + i3 + "" + i4 + "" + i5 + "" + i6 + "" + i7 + "" + i8, 2);
    }

    private String getNullHaveState(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._330_no);
        }
        return this.mContext.getString(R.string._330_yes);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2 || i3 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(1, 1);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 1);
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2) {
            getUiMgr(this.mContext).sendRadioInfo(getBandAmFM(str), getFreqLsb(str, str2), getFreqMsb(str, str2), i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUiMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        int i8 = this.eachId;
        if (i8 == 1 || i8 == 2 || i8 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(2, 33);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        int i9 = this.eachId;
        if (i9 == 1 || i9 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(i4, i5, 0, 0, i / 60, i % 60);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2 || i4 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(8, 17);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        MyLog.e("stoagePath", ((int) b) + "");
        MyLog.e("playRatio", ((int) b2) + "");
        MyLog.e("currentPlayingIndexLow", i + "");
        MyLog.e("totalCount", i2 + "");
        MyLog.e("currentPlayingIndexHigh", ((int) b7) + "");
        MyLog.e("currentPos", j + "");
        MyLog.e("playIndex", i3 + "");
        int i5 = this.eachId;
        if (i5 == 1 || i5 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUiMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUiMgr(this.mContext).sendSourceInfo(7, 48);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUiMgr(this.mContext).sendSourceInfo(11, 34);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        int i = this.eachId;
        if (i == 1 || i == 2 || i == 9) {
            getUiMgr(this.mContext).sendSourceInfo(0, 0);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2) {
            getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("来电");
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.comingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传 来电");
        this.comingPhoneNumber = byteArrayToIntArray;
        int i = this.eachId;
        if (i == 1 || i == 2) {
            getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 1, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("拨号");
        int i = 0;
        while (i < bArr.length) {
            int i2 = i + 1;
            MyLog.e("号码" + i2, ((int) bArr[i]) + "");
            i = i2;
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.OutGoingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传 拨号");
        this.OutGoingPhoneNumber = byteArrayToIntArray;
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2) {
            getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 2, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2 || i2 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(5, 64);
        }
        getUiMgr(this.mContext).sendIconInfo(0, 0);
        MyLog.temporaryTracking("通话中");
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.talkingPhoneNumber)) {
            return;
        }
        MyLog.temporaryTracking("传递 通话中");
        this.talkingPhoneNumber = byteArrayToIntArray;
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2) {
            getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 4, 1}, bArr));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        MyLog.temporaryTracking("挂断");
        this.comingPhoneNumber = null;
        this.OutGoingPhoneNumber = null;
        this.talkingPhoneNumber = null;
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._347.MsgMgr.1
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._347.MsgMgr.1.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        MyLog.temporaryTracking("挂断中");
                        if (MsgMgr.this.eachId == 1 || MsgMgr.this.eachId == 2) {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumber();
                        }
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        MyLog.temporaryTracking("挂断完成");
                        if (MsgMgr.this.eachId == 1 || MsgMgr.this.eachId == 2) {
                            MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumber();
                        }
                    }
                }.start();
            }
        });
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        return true;
    }

    private int getBandAmFM(String str) {
        str.hashCode();
        switch (str) {
            case "AM":
                return 16;
            case "AM1":
                return 17;
            case "AM2":
                return 18;
            case "AM3":
                return 19;
            case "FM1":
                return 1;
            case "FM2":
                return 2;
            case "FM3":
                return 3;
            default:
                return 0;
        }
    }

    private int getFreqMsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getMsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getMsb((int) Double.parseDouble(str2));
    }

    private int getFreqLsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getLsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getLsb((int) Double.parseDouble(str2));
    }

    private String getP_GearState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info12);
        }
        return this.mContext.getString(R.string._246_car_info11);
    }

    private void tokingNowTime(int i) {
        int i2 = i % 3600;
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i2 % 60), (byte) (i2 / 60), (byte) (i / 3600), 0});
    }

    private void showDialog(final String str) {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._347.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new AlertView().showDialog(MsgMgr.this.getActivity(), str);
            }
        });
    }

    private String getSwitchState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info7);
        }
        return this.mContext.getString(R.string._246_car_info6);
    }

    private String getReversingState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info8);
        }
        return this.mContext.getString(R.string._246_car_info9);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return true;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBackLightIntChange() {
        if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mBackLightInt = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isCarDoorInfoChange() {
        int i = this.carDoorInt;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.carDoorInt = i2;
        return true;
    }

    private boolean isAlarm1InfoChange() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
        if (this.alarmInfo1 == intFromByteWithBit) {
            return false;
        }
        this.alarmInfo1 = intFromByteWithBit;
        return true;
    }

    private boolean isAlarm2InfoChange() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
        if (this.alarmInfo2 == intFromByteWithBit) {
            return false;
        }
        this.alarmInfo2 = intFromByteWithBit;
        return true;
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        int length = bArr.length + bArr2.length;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        MyLog.e("长度", length + "");
        for (int i = 0; i < length; i++) {
            MyLog.e("内容" + i, ((int) bArr3[i]) + "");
        }
        return bArr3;
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateAmplifier() {
        updateAmplifierActivity(null);
    }

    public void updateSettingsUi(Context context, int i, int i2, String str, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
