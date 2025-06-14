package com.hzbhd.canbus.car._461;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int[] mAirData;
    private int[] mCarDoorData;
    private int[] mCarSettingInfo;
    private Context mContext;
    private int[] mFrontRadarData;
    private int[] mRearRadarData;
    private int[] mSystemConfigInfo;
    private int[] mTrackData;
    private UiMgr mUiMgr;
    private DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    private String tempUnit = "℃";

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onHandshake(Context context) {
        super.onHandshake(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte b = (byte) i6;
        CanbusMsgSender.sendMsg(new byte[]{22, -125, (byte) i5, b, (byte) i4, b, (byte) (i - 2000)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        int i = byteArrayToIntArray[1];
        if (i == 3) {
            set0x03DriveData(byteArrayToIntArray);
            return;
        }
        if (i == 4) {
            set0x04SystemConfig(byteArrayToIntArray);
            return;
        }
        if (i == 41) {
            set0x29Esp(byteArrayToIntArray);
            return;
        }
        if (i != 64) {
            switch (i) {
                case 32:
                    set0x20Swc(byteArrayToIntArray);
                    break;
                case 33:
                    set0x21AirInfo(byteArrayToIntArray);
                    break;
                case 34:
                    set0x22RearRadar(byteArrayToIntArray);
                    break;
                case 35:
                    set0x23FrontRadarInfo(byteArrayToIntArray);
                    break;
                case 36:
                    set0x24BasicInfo(byteArrayToIntArray);
                    break;
            }
            return;
        }
        set0x40Setting(byteArrayToIntArray);
    }

    private void set0x40Setting(int[] iArr) {
        if (isCarSettingChange(iArr)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_speed"), Integer.valueOf(iArr[2])).setProgress(iArr[2]));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_home_lighting"), Integer.valueOf(iArr[3])).setProgress(iArr[3]));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_three_lighting"), Integer.valueOf(iArr[4])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_running_lights"), Integer.valueOf(iArr[5])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_welcome_light"), Integer.valueOf(iArr[6])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_car_door_locks"), Integer.valueOf(iArr[7])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_relock"), Integer.valueOf(iArr[8])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_set", "_461_setting_set_interlock"), Integer.valueOf(iArr[9])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x04SystemConfig(int[] iArr) {
        if (isSystemConfigChange(iArr)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_language"), Integer.valueOf(iArr[2])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_fuel_unit"), Integer.valueOf(iArr[3])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_temp_unit"), Integer.valueOf(iArr[4])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_date"), Integer.valueOf(iArr[5])));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_461_setting_config"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_461_setting_config", "_461_setting_config_time"), Integer.valueOf(iArr[6])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x03DriveData(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_temp"), getTempValue(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_mileage"), DataHandleUtils.getMsbLsbResult(iArr[4], iArr[5]) + "km"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_speed"), this.formatDecimal1.format(DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) / 10.0f) + "km/h"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_461_drive_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_461_drive_info_fuel"), this.formatDecimal1.format(DataHandleUtils.getMsbLsbResult(iArr[8], iArr[9]) / 10.0f) + "l/100km"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String getTempValue(int i) {
        if (this.tempUnit.equals("℃")) {
            return this.formatDecimal1.format(i * 0.5d) + getTempUnitC(this.mContext);
        }
        return i + getTempUnitF(this.mContext);
    }

    private void set0x29Esp(int[] iArr) {
        if (isTrackInfoChange(iArr)) {
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[2], (byte) iArr[3], 0, 5400, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x24BasicInfo(int[] iArr) {
        iArr[3] = 0;
        if (isBasicInfoChange(iArr) && DataHandleUtils.getBoolBit0(iArr[2])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x23FrontRadarInfo(int[] iArr) {
        if (isFrontRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x22RearRadar(int[] iArr) {
        if (isRearRadarDataChange(iArr)) {
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void set0x21AirInfo(int[] iArr) {
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_foot = false;
            if (DataHandleUtils.getBoolBit7(iArr[3])) {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
            }
            if (DataHandleUtils.getBoolBit6(iArr[3])) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            }
            if (DataHandleUtils.getBoolBit5(iArr[3])) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            }
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 4);
            int i = iArr[5];
            if (i == 0) {
                GeneralAirData.front_left_temperature = "LO";
            } else if (i == 31) {
                GeneralAirData.front_left_temperature = "HI";
            } else {
                GeneralAirData.front_left_temperature = this.formatDecimal1.format((iArr[5] / 2.0f) + 15.5d) + getTempUnitC(this.mContext);
            }
            int i2 = iArr[6];
            if (i2 == 0) {
                GeneralAirData.front_right_temperature = "LO";
            } else if (i2 == 31) {
                GeneralAirData.front_right_temperature = "HI";
            } else {
                GeneralAirData.front_right_temperature = this.formatDecimal1.format((iArr[6] / 2.0f) + 15.5d) + getTempUnitC(this.mContext);
            }
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 3);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 3);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x20Swc(int[] iArr) {
        int i = iArr[2];
        if (i != 70) {
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
                    realKeyLongClick1(this.mContext, 20, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 21, iArr[3]);
                    break;
                case 5:
                    realKeyLongClick1(this.mContext, 14, iArr[3]);
                    break;
                case 6:
                    realKeyLongClick1(this.mContext, 3, iArr[3]);
                    break;
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 128, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 33, iArr[3]);
                    break;
                case 11:
                    realKeyLongClick1(this.mContext, 34, iArr[3]);
                    break;
                case 12:
                    realKeyLongClick1(this.mContext, 35, iArr[3]);
                    break;
                case 13:
                    realKeyLongClick1(this.mContext, 36, iArr[3]);
                    break;
                case 14:
                    realKeyLongClick1(this.mContext, 37, iArr[3]);
                    break;
                case 15:
                    realKeyLongClick1(this.mContext, 38, iArr[3]);
                    break;
                case 16:
                    realKeyLongClick1(this.mContext, 39, iArr[3]);
                    break;
                case 17:
                    realKeyLongClick1(this.mContext, 40, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 41:
                            realKeyClick4(this.mContext, 21);
                            break;
                        case 42:
                            realKeyClick4(this.mContext, 20);
                            break;
                        case 43:
                            realKeyLongClick1(this.mContext, 21, iArr[3]);
                            break;
                        case 44:
                            realKeyLongClick1(this.mContext, 20, iArr[3]);
                            break;
                        case 45:
                            realKeyLongClick1(this.mContext, 45, iArr[3]);
                            break;
                        case 46:
                            realKeyLongClick1(this.mContext, 46, iArr[3]);
                            break;
                        case 47:
                            realKeyLongClick1(this.mContext, 49, iArr[3]);
                            break;
                        case 48:
                            realKeyLongClick1(this.mContext, 59, iArr[3]);
                            break;
                        case 49:
                            realKeyLongClick1(this.mContext, 62, iArr[3]);
                            break;
                        case 50:
                            realKeyLongClick1(this.mContext, 52, iArr[3]);
                            break;
                        case 51:
                            realKeyLongClick1(this.mContext, 14, iArr[3]);
                            break;
                        case 52:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        case 53:
                            realKeyLongClick1(this.mContext, 50, iArr[3]);
                            break;
                    }
            }
        }
        realKeyLongClick1(this.mContext, 58, iArr[3]);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private boolean isTrackInfoChange(int[] iArr) {
        if (Arrays.equals(this.mTrackData, iArr)) {
            return false;
        }
        this.mTrackData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isSystemConfigChange(int[] iArr) {
        if (Arrays.equals(this.mSystemConfigInfo, iArr)) {
            return false;
        }
        this.mSystemConfigInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isCarSettingChange(int[] iArr) {
        if (Arrays.equals(this.mCarSettingInfo, iArr)) {
            return false;
        }
        this.mCarSettingInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
