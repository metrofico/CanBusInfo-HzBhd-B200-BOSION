package com.hzbhd.canbus.car._181;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.log4j.Priority;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    String ResultTemp;
    int differentId;
    private int eachId;
    int i;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private DecimalFormat mDecimalFormat0p0;
    int[] mFrontRadarData;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mRearRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr uiMgr;

    private boolean getBose(int i) {
        return i == 1;
    }

    private int getLeftRight(int i) {
        int i2 = i != 10 ? (i < 0 || i > 10) ? 0 : i : 0;
        return (i < 10 || i > 20) ? i2 : i - 10;
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

    private int getTenData(int i) {
        if (i == 0) {
            return -10;
        }
        if (i == 1) {
            return -9;
        }
        if (i == 2) {
            return -8;
        }
        if (i == 3) {
            return -7;
        }
        if (i == 4) {
            return -6;
        }
        if (i == 5) {
            return -5;
        }
        if (i == 6) {
            return -4;
        }
        if (i == 7) {
            return -3;
        }
        if (i == 8) {
            return -2;
        }
        if (i == 9) {
            return -1;
        }
        if (i == 10) {
            return 0;
        }
        return i - 10;
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
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 54) {
            setSettingInfo0x36();
            return;
        }
        if (i == 55) {
            setAmplifierInfo0x37();
            return;
        }
        if (i == 65) {
            setPanoramicstate0x41();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 33:
                    setWheelKeyInfo0x21();
                    break;
                case 34:
                    setPanelKeyInfo0x22();
                    break;
                case 35:
                    setFrontAirInfo0x23();
                    break;
                case 36:
                    setRearAirInfo0x24();
                    break;
                default:
                    switch (i) {
                        case 38:
                            setRearRadarInfo0x26();
                            break;
                        case 39:
                            setFrontRadarInfo0x27();
                            break;
                        case 40:
                            setBaseInfo0x28();
                            break;
                        case 41:
                            setOriginalVehicleInfo0x29();
                            break;
                        default:
                            switch (i) {
                                case 48:
                                    setTrackData0x30();
                                    break;
                                case 49:
                                    setSettingInfo0x31();
                                    break;
                                case 50:
                                    setScreenBrightness0x32();
                                    break;
                            }
                    }
            }
            return;
        }
        VersionInfo0x7F();
    }

    private void setWheelKeyInfo0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 7) {
            buttonKey(2);
            return;
        }
        switch (i) {
            case 9:
                buttonKey(HotKeyConstant.K_1_PICKUP);
                break;
            case 10:
                buttonKey(HotKeyConstant.K_2_HANGUP);
                break;
            case 11:
                buttonKey(45);
                break;
            case 12:
                buttonKey(46);
                break;
            case 13:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 14:
                buttonKey(3);
                break;
            default:
                switch (i) {
                    case 16:
                        buttonKey(62);
                        break;
                    case 17:
                        buttonKey(1);
                        break;
                    case 18:
                        buttonKey(7);
                        break;
                    case 19:
                        buttonKey(8);
                        break;
                    case 20:
                        buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                        break;
                    case 21:
                        buttonKey(128);
                        break;
                    case 22:
                        buttonKey(129);
                        break;
                    case 23:
                        buttonKey(50);
                        break;
                    case 24:
                        buttonKey(151);
                        break;
                    case 25:
                        buttonKey(31);
                        break;
                    case 26:
                        buttonKey(47);
                        break;
                    case 27:
                        buttonKey(48);
                        break;
                }
        }
    }

    private void setPanelKeyInfo0x22() {
        switch (this.mCanBusInfoInt[2]) {
            case 16:
                buttonKey(62);
                break;
            case 17:
                buttonKey(1);
                break;
            case 18:
                buttonKey(7);
                break;
            case 19:
                buttonKey(8);
                break;
            case 20:
                buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 21:
                buttonKey(128);
                break;
            case 22:
                buttonKey(129);
                break;
            case 23:
                buttonKey(50);
                break;
            case 24:
                buttonKey(151);
                break;
            case 25:
                buttonKey(31);
                break;
            case 26:
                buttonKey(47);
                break;
            case 27:
                buttonKey(48);
                break;
        }
    }

    private void setFrontAirInfo0x23() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[4];
        int i4 = iArr[5];
        int i5 = iArr[6];
        int i6 = iArr[7];
        int i7 = iArr[8];
        int i8 = iArr[9];
        int i9 = iArr[10];
        int i10 = iArr[11];
        GeneralAirData.power = DataHandleUtils.getBoolBit7(i);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(i);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(i);
        GeneralAirData.threeZone = DataHandleUtils.getBoolBit4(i);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(i);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(i);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(i);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(i);
        switch (i2) {
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = false;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = false;
                break;
            case 3:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = false;
                break;
            case 4:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_left_blow_window = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_right_blow_window = false;
                GeneralAirData.front_defog = true;
                break;
        }
        GeneralAirData.front_wind_level = i3;
        GeneralAirData.front_left_temperature = resolveAirTemperature(i4);
        GeneralAirData.front_right_temperature = resolveAirTemperature(i5);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i7, 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i7, 0, 4);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(i8, 4, 4);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(i8, 0, 4);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i9, 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i9, 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i10, 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_1", "_181_set_1_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(i10, 0, 4))));
        resolveOutDoorTem(i6);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearAirInfo0x24() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[4];
        int i4 = iArr[5];
        int i5 = iArr[6];
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(i);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit3(i);
        if (i2 == 1) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = false;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = false;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_defog = false;
        } else if (i2 == 2) {
            GeneralAirData.rear_left_blow_head = true;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_head = true;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_defog = false;
        } else if (i2 == 3) {
            GeneralAirData.rear_left_blow_head = false;
            GeneralAirData.rear_left_blow_foot = true;
            GeneralAirData.rear_left_blow_window = false;
            GeneralAirData.rear_right_blow_head = false;
            GeneralAirData.rear_right_blow_foot = true;
            GeneralAirData.rear_right_blow_window = false;
            GeneralAirData.rear_defog = false;
        }
        GeneralAirData.rear_wind_level = i3;
        GeneralAirData.rear_temperature = resolveAirTemperature(i4);
        GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i5, 0, 4);
        GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i5, 0, 4);
        updateAirActivity(this.mContext, 1002);
    }

    private void setRearRadarInfo0x26() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x27() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setBaseInfo0x28() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setOriginalVehicleInfo0x29() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_2"), resolveCoolantTemperature(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_3"), (this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_1"), resolveVoltage(this.mCanBusInfoInt[4])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_driver_4"), resolveAirPressure(this.mCanBusInfoInt[5])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrackData0x30() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setSettingInfo0x31() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_4"), Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_6"), Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_7"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_8"), Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_9"), Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_11"), Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_12"), Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_13"), Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_14"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_19"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_15"), Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_setting_16"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setScreenBrightness0x32() {
        String str;
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 26) {
            str = "最暗";
        } else {
            str = i == 255 ? "最亮" : this.mCanBusInfoInt[2] + "";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_143_setting_16"), str));
        setBacklightLevel(this.mCanBusInfoInt[2]);
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSettingInfo0x36() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info1"), resolveSlope(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 7))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info2"), this.mCanBusInfoInt[3] + "%"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info3"), resolveDipAngle(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info4"), resolveTrailer(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_337_drive_car_info_tow"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_337_drive_car_info5"), resolveAltitude(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), this.mCanBusInfoInt[7])));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_181_set_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_181_set_2", "_337_drive_car_info5"), resolveAltitude(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7), this.mCanBusInfoInt[7])).setProgress(this.i));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAmplifierInfo0x37() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[3];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[4];
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
        GeneralAmplifierData.leftRight = getTenData(this.mCanBusInfoInt[6]);
        GeneralAmplifierData.frontRear = getTenData(this.mCanBusInfoInt[7]);
        GeneralAmplifierData.bose_center_b = getBose(this.mCanBusInfoInt[8]);
        updateAmplifierActivity(new Bundle());
    }

    private void setPanoramicstate0x41() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_181_panoramic"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_181_panoramic"), resolvePanoramic(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void VersionInfo0x7F() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            this.ResultTemp = "LOW";
        } else if (i == 255) {
            this.ResultTemp = "HIGH";
        } else {
            this.ResultTemp = (((i - 116) / 2.0f) + 16.0f) + getTempUnitC(this.mContext);
        }
        return this.ResultTemp;
    }

    private String resolveCoolantTemperature(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.mDecimalFormat0p0 = decimalFormat;
        return decimalFormat.format(((i * 0.75d) - 48.0d) + getTempUnitC(this.mContext));
    }

    private String resolveVoltage(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.mDecimalFormat0p0 = decimalFormat;
        return decimalFormat.format((i * 0.25d) + "V");
    }

    private String resolveAirPressure(int i) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        this.mDecimalFormat0p0 = decimalFormat;
        return decimalFormat.format((i * 0.59d) + "Kpa");
    }

    private String resolveOutDoorTem(int i) {
        return ((i / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private String resolvePanoramic(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._181_panoramic_1);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._181_panoramic_2);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._181_panoramic_3);
        }
        if (i == 4) {
            return this.mContext.getResources().getString(R.string._181_panoramic_4);
        }
        if (i == 5) {
            return this.mContext.getResources().getString(R.string._181_panoramic_5);
        }
        return null;
    }

    private String resolveAltitude(boolean z, int i, int i2) throws Resources.NotFoundException {
        String string;
        if (!z) {
            string = this.mContext.getResources().getString(R.string._181_Atitude_1);
        } else {
            string = this.mContext.getResources().getString(R.string._181_Atitude_2);
        }
        this.i = ((i & 255) << 8) | (i2 & 255);
        return string + this.i;
    }

    private String resolveTrailer(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._337_drive_car_other4);
        }
        return this.mContext.getResources().getString(R.string._337_drive_car_other5);
    }

    private String resolveDipAngle(boolean z, int i) throws Resources.NotFoundException {
        String string;
        if (z) {
            string = this.mContext.getResources().getString(R.string._337_drive_car_other2);
        } else {
            string = this.mContext.getResources().getString(R.string._337_drive_car_other3);
        }
        return string + i + this.mContext.getResources().getString(R.string.unit_degree);
    }

    private String resolveSlope(boolean z, int i) throws Resources.NotFoundException {
        String string;
        if (z) {
            string = this.mContext.getResources().getString(R.string._337_drive_car_other7);
        } else {
            string = this.mContext.getResources().getString(R.string._337_drive_car_other6);
        }
        return string + i + this.mContext.getResources().getString(R.string.unit_degree);
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

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private Object getAmplifierVoltage() {
        if (this.mCanBusInfoInt[10] == 255) {
            return this.mContext.getString(R.string._337_drive_car_other1);
        }
        return (this.mCanBusInfoInt[10] * 0.25d) + "V";
    }

    private String getAmplifierTemperature() {
        if (this.mCanBusInfoInt[11] == 255) {
            return this.mContext.getString(R.string._337_drive_car_other1);
        }
        return (this.mCanBusInfoInt[11] - 40) + getTempUnitC(this.mContext);
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        if (this.eachId != 7) {
            return;
        }
        final byte[] bytes = str4.getBytes(StandardCharsets.UTF_8);
        final byte[] bytes2 = str6.getBytes(StandardCharsets.UTF_8);
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._181.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                MsgMgr.lambda$musicInfoChange$0(bytes, bytes2);
            }
        }).start();
    }

    static /* synthetic */ void lambda$musicInfoChange$0(byte[] bArr, byte[] bArr2) throws InterruptedException {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64}, bArr));
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64}, bArr2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        int i4 = (!isBandFm(str) && isBandAm(str)) ? 1 : 0;
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
                i3 = 3;
                break;
            case 1:
                i3 = 4;
                break;
            case 2:
            default:
                i3 = 0;
                break;
            case 3:
                i3 = 1;
                break;
            case 4:
                i3 = 2;
                break;
        }
        String[] validFreqList = FutureUtil.instance.getValidFreqList(i3);
        int i5 = FutureUtil.instance.getAutoSearchStatus() ? 4 : 0;
        if (FutureUtil.instance.getPSSwitchStatus()) {
            i5 = 3;
        }
        if (FutureUtil.instance.getSeekDownStatus()) {
            i5 = 2;
        }
        if (FutureUtil.instance.getSeekUpStatus()) {
            i5 = 1;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -58, (byte) i4, getFreqByteHi2(str, validFreqList[0]), getFreqByteLo(str, validFreqList[0]), getFreqByteHi2(str, validFreqList[1]), getFreqByteLo(str, validFreqList[1]), getFreqByteHi2(str, validFreqList[2]), getFreqByteLo(str, validFreqList[2]), getFreqByteHi2(str, validFreqList[3]), getFreqByteLo(str, validFreqList[3]), 0, 0, getFreqByteHi(str, str2), getFreqByteLo(str, str2), (byte) i5});
    }

    private byte getFreqByteHi2(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) Double.parseDouble(str2);
        }
        return (byte) (i >> 8);
    }

    private byte getFreqByteHi(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) (Double.parseDouble(str2) * 100.0d);
        }
        return (byte) (i >> 8);
    }

    private byte getFreqByteLo(String str, String str2) throws NumberFormatException {
        int i;
        if (isBandAm(str)) {
            i = Integer.parseInt(str2);
        } else {
            if (!isBandFm(str)) {
                return (byte) 0;
            }
            i = (int) (Double.parseDouble(str2) * 100.0d);
        }
        return (byte) (i & 255);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
