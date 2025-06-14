package com.hzbhd.canbus.car._474;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;
import org.apache.log4j.helpers.DateLayout;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int[] mAirData;
    int[] mCarDoorData;
    private Context mContext;
    int[] mDoorInfo;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");
    private String distanceUnit = "km";
    private String tempUnit = "℃";
    private String consumptionUnit = "l/100km";

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getRadarRange(int i) {
        return i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOn() {
        super.onAccOn();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAccOff() {
        super.onAccOff();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 10) {
            setTrack0x0A(byteArrayToIntArray);
            return;
        }
        if (i == 53) {
            set0x35DriveData(byteArrayToIntArray);
            return;
        }
        if (i != 127) {
            switch (i) {
                case 1:
                    set0x01BaseInfo(byteArrayToIntArray);
                    break;
                case 2:
                    set0x02WheelKey(byteArrayToIntArray);
                    break;
                case 3:
                    set0x03AirInfo(byteArrayToIntArray);
                    break;
                case 4:
                    setRearRadar0x04(byteArrayToIntArray);
                    break;
                case 5:
                    setRearRadar0x05(byteArrayToIntArray);
                    break;
                case 6:
                    setDoorInfo0x06(byteArrayToIntArray);
                    break;
                default:
                    switch (i) {
                        case 55:
                            setDrive0x37(byteArrayToIntArray);
                            break;
                        case 56:
                            setCarState0x38(byteArrayToIntArray);
                            break;
                        case 57:
                            setMaintainInfo0x39(byteArrayToIntArray);
                            break;
                    }
            }
            return;
        }
        updateVersionInfo(this.mContext, getVersionStr(bArr));
    }

    private void setCarState0x38(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_0"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_1"), Integer.valueOf(iArr[2])).setProgress(iArr[2] - 16));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_2"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[3]) ? 1 : 0)));
        this.tempUnit = DataHandleUtils.getBoolBit6(iArr[3]) ? "℉" : "℃";
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 2))));
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[3], 4, 2);
        if (intFromByteWithBit == 0) {
            this.consumptionUnit = "l/100km";
        } else if (intFromByteWithBit == 1) {
            this.consumptionUnit = "mpg uk";
        } else if (intFromByteWithBit == 2) {
            this.consumptionUnit = "km/l";
        } else if (intFromByteWithBit == 3) {
            this.consumptionUnit = "mpg us";
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_4"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[4]) ? 1 : 0)));
        this.distanceUnit = DataHandleUtils.getBoolBit0(iArr[4]) ? "mil" : "km";
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_buzzer_volume"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_automatic_door_lock"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_independent_unlocking_of_driver_door"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_independent_trunk_lid_unlocking"), Integer.valueOf(DataHandleUtils.getBoolBit5(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_61_radar_switch"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_143_setting_5"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "light_ctrl_3"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_474_photosensitivity"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_456_settings_5"), Integer.valueOf(iArr[6])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setMaintainInfo0x39(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_189_maintenance"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_94_maintenance_mileage"), DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]) + this.distanceUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_189_maintenance"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_maintenance_days"), iArr[4] + "day"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x35DriveData(int[] iArr) {
        StringBuilder sb;
        double d;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_front_fog_lamp"), DataHandleUtils.getBoolBit0(iArr[2]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_low_beam"), DataHandleUtils.getBoolBit1(iArr[2]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_high_beam"), DataHandleUtils.getBoolBit2(iArr[2]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_rear_fog_lamp"), DataHandleUtils.getBoolBit3(iArr[2]) ? "ON" : "OFF"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_left_trun_lamp"), DataHandleUtils.getBoolBit4(iArr[2]) ? "ON" : "OFF"));
        turnLeftLamp(DataHandleUtils.getBoolBit4(iArr[2]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_304_right_turn_lamp"), DataHandleUtils.getBoolBit5(iArr[2]) ? "ON" : "OFF"));
        turnRightLamp(DataHandleUtils.getBoolBit5(iArr[2]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), iArr[3] + "km/h"));
        updateSpeedInfo(iArr[3]);
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_112_available_mileage"), ((iArr[4] * 256) + iArr[5]) + this.distanceUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + "rpm"));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "show_outdoor_temperature");
        if (this.tempUnit.equals("℃")) {
            sb = new StringBuilder();
            d = (iArr[8] * 0.5d) - 40.0d;
        } else {
            sb = new StringBuilder();
            d = (((iArr[8] * 0.5d) - 40.0d) * 1.8d) + 32.0d;
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(d).append(this.tempUnit).toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_456_drive_0"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "total_mileage"), ((iArr[9] << 24) + (iArr[10] << 16) + (iArr[11] << 8) + iArr[12]) + this.distanceUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrive0x37(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_driving_time"), this.formatInteger2.format(iArr[2]) + ":" + this.formatInteger2.format(iArr[3])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_average"), iArr[4] + "km/h"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_distance_traveled"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[5], iArr[6]) / 10.0f) + this.distanceUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_a_average_fuel_consumption"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[7], iArr[8]) / 10.0f) + this.consumptionUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_driving_time"), this.formatInteger2.format(iArr[9]) + ":" + this.formatInteger2.format(iArr[10])));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_average"), iArr[11] + "km/h"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_distance_traveled"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[12], iArr[13]) / 10.0f) + this.distanceUnit));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "S97_Fuel_cons_mile_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_474_b_average_fuel_consumption"), this.formatDecimal2.format(DataHandleUtils.getMsbLsbResult(iArr[14], iArr[15]) / 10.0f) + this.consumptionUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x03AirInfo(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit4(iArr[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4);
            int i = iArr[4];
            if (i == 255) {
                GeneralAirData.front_left_temperature = "HI";
            } else if (i == 0) {
                GeneralAirData.front_left_temperature = "LO";
            } else {
                GeneralAirData.front_left_temperature = this.formatDecimal1.format(iArr[4] / 2.0f) + getTempUnitC(this.mContext);
            }
            int i2 = iArr[5];
            if (i2 == 255) {
                GeneralAirData.front_right_temperature = "HI";
            } else if (i2 == 0) {
                GeneralAirData.front_right_temperature = "LO";
            } else {
                GeneralAirData.front_right_temperature = this.formatDecimal1.format(iArr[5] / 2.0f) + "℃";
            }
            GeneralAirData.auto_cycle = DataHandleUtils.getBoolBit5(iArr[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit2(iArr[6]);
            GeneralAirData.mono = DataHandleUtils.getBoolBit1(iArr[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(iArr[6]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(iArr[8]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(iArr[8]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[8]);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit4(iArr[8]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setTrack0x0A(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            if (DataHandleUtils.getBoolBit0(iArr[2])) {
                GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0((byte) iArr[3], (byte) iArr[4], 0, 480, 16);
                updateParkUi(null, this.mContext);
            } else {
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[3], (byte) iArr[4], 0, 480, 16);
                updateParkUi(null, this.mContext);
            }
        }
    }

    private void setDoorInfo0x06(int[] iArr) {
        if (isDoorInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralDoorData.isShowSeatBelt = DataHandleUtils.getBoolBit0(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void setRearRadar0x05(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(3, getRadarRange(iArr[2]), getRadarRange(iArr[3]), getRadarRange(iArr[4]), getRadarRange(iArr[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadar0x04(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(3, getRadarRange(iArr[2]), getRadarRange(iArr[3]), getRadarRange(iArr[4]), getRadarRange(iArr[5]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x02WheelKey(int[] iArr) {
        int i = iArr[2];
        if (i == 13) {
            realKeyLongClick1(this.mContext, 49, iArr[3]);
            return;
        }
        if (i != 25) {
            switch (i) {
                case 0:
                    realKeyLongClick1(this.mContext, 0, iArr[3]);
                    break;
                case 1:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 2:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 3:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 68, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 15, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
            }
            return;
        }
        realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
    }

    private void set0x01BaseInfo(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            setBacklightLevel((iArr[3] / 20) + 1);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -58}, new byte[]{(byte) i2, (byte) i3, (byte) i4, (byte) (!z ? 1 : 0), (byte) i8, (byte) i6}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, (byte) getBandAmFM(str), (byte) getFreqMsb(str, str2), (byte) getFreqLsb(str, str2), (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "MUSIC".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "VIDEO".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 2}, str.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 7}, "AUX".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, "BT MUSIC".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (DateLayout.NULL_DATE_FORMAT.equals(str)) {
            CanbusMsgSender.sendMsg(DataHandleUtils.splicingByte(new byte[]{22, -64, 0}, "MEDIA OFF".getBytes()));
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private String getP_GearState(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._246_car_info12);
        }
        return this.mContext.getString(R.string._246_car_info11);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
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

    private boolean isAirDataChange(int[] iArr) {
        if (Arrays.equals(this.mAirData, iArr)) {
            return false;
        }
        this.mAirData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mFrontRadarData, iArr)) {
            return false;
        }
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange(int[] iArr) {
        if (Arrays.equals(this.mRearRadarData, iArr)) {
            return false;
        }
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mCarDoorData, iArr)) {
            return false;
        }
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isDoorInfoChange(int[] iArr) {
        if (Arrays.equals(this.mDoorInfo, iArr)) {
            return false;
        }
        this.mDoorInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTireInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTireInfo, iArr)) {
            return false;
        }
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange(int[] iArr) {
        if (Arrays.equals(this.mPanoramicInfo, iArr)) {
            return false;
        }
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
