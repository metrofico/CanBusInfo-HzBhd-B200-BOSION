package com.hzbhd.canbus.car._475;

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
    Context mContext;
    int[] mFrontRadarData;
    int[] mPanoramicInfo;
    int[] mRearRadarData;
    int[] mTireInfo;
    int[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat formatDecimal2 = new DecimalFormat("###0.00");
    DecimalFormat formatDecimal1 = new DecimalFormat("###0.0");
    DecimalFormat formatInteger2 = new DecimalFormat("00");

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
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
        if (i == 1) {
            setBasicInfo0x01(byteArrayToIntArray);
            return;
        }
        if (i == 2) {
            setSwc0x02(byteArrayToIntArray);
            return;
        }
        if (i == 3) {
            set0x03AirInfo(byteArrayToIntArray);
            return;
        }
        if (i == 6) {
            setDoorInfo0x06(byteArrayToIntArray);
            return;
        }
        if (i == 10) {
            setTrack0x0A(byteArrayToIntArray);
            return;
        }
        if (i == 53) {
            set0x35DriveData(byteArrayToIntArray);
        } else if (i == 56) {
            setCarState0x38(byteArrayToIntArray);
        } else {
            if (i != 127) {
                return;
            }
            updateVersionInfo(this.mContext, getVersionStr(bArr));
        }
    }

    private void setCarState0x38(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_display_fuel_efficient_mode"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "parkingAssist"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 5, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_rearview_mirror_reverse_assist_adjustment"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_6"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "ramp_start_assist"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[2]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_start_headlights_when_unlocking"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[3], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_10"), Integer.valueOf(DataHandleUtils.getBoolBit5(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_high_beam_assist"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_220_daytime_running_lights"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_bending_mode"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lights_flashing_when_locked"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "auto_locking"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[3]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x60_data1_4"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_unlocking_get_off"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_94_key_free"), Integer.valueOf(DataHandleUtils.getBoolBit5(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_18"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_press_key_control_unlock"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_auto_activate"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "distance_unit"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_19"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[4]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_373_setting_car_control9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_370_Engine_Off_Light_delay"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[5], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_sound_the_horn_lock"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[5]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_sound_the_horn_remote"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[5]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_110"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[5]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_guidance_line"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[5]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), Integer.valueOf(iArr[6])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_123_rear_mirror"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x73_data3_1"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0x73_data3_0"), Integer.valueOf(DataHandleUtils.getBoolBit5(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_5"), Integer.valueOf(DataHandleUtils.getBoolBit4(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_2_setting_12_3_2"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_1193_setting_1_7"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_123_auxiliary_1"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_775_p_key"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[7]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[8], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[8], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lanesense_warning"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[8], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_lanesense_strength"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[8], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_29"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[9], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_blind_spot_alarm"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[9], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_start_stop_system"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[9]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_250_automatic_folding_mirror"), Integer.valueOf(DataHandleUtils.getBoolBit2(iArr[9]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_front_sensor_driving"), Integer.valueOf(DataHandleUtils.getBoolBit1(iArr[9]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_475_intelligent_electric_tailgate"), Integer.valueOf(DataHandleUtils.getBoolBit0(iArr[9]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_24"), Integer.valueOf(DataHandleUtils.getBoolBit7(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_25"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_26"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_27"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "_118_setting_title_28"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_118_setting_title_81"), Integer.valueOf(DataHandleUtils.getBoolBit6(iArr[10]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_118_setting_title_80"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[10], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_475_capacity_unit"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[11], 6, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_setting"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "car_setting", "hiworld_jeep_123_0xCA_0X08"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[11], 4, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "temperature_unit"), Integer.valueOf(DataHandleUtils.getBoolBit3(iArr[11]) ? 1 : 0)));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "unit_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "unit_settings", "_475_power_unit"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[11], 1, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x35DriveData(int[] iArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Left_turn"), DataHandleUtils.getBoolBit4(iArr[2]) ? "ON" : "OFF"));
        turnLeftLamp(DataHandleUtils.getBoolBit4(iArr[2]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_454_drive_Right_turn"), DataHandleUtils.getBoolBit5(iArr[2]) ? "ON" : "OFF"));
        turnRightLamp(DataHandleUtils.getBoolBit5(iArr[2]));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info14"), iArr[3] + "km/h"));
        updateSpeedInfo(iArr[3]);
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_246_car_info13"), DataHandleUtils.getMsbLsbResult(iArr[6], iArr[7]) + "rpm"));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_246_car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "show_outdoor_temperature"), ((iArr[8] * 0.5d) - 40.0d) + "℃"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
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
        if (isBasicInfoChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(iArr[2]);
            updateDoorView(this.mContext);
        }
    }

    private void set0x03AirInfo(int[] iArr) {
        int[] iArr2 = this.mAirData;
        boolean z = (iArr2 == null || iArr2.length == 0 || (iArr[8] == iArr2[8] && iArr[9] == iArr2[9] && iArr[10] == iArr2[10])) ? false : true;
        if (isAirDataChange(iArr)) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(iArr[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(iArr[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(iArr[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit4(iArr[2]);
            GeneralAirData.auto_2 = DataHandleUtils.getBoolBit3(iArr[2]);
            GeneralAirData.sync = DataHandleUtils.getBoolBit2(iArr[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(iArr[2]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(iArr[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(iArr[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(iArr[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(iArr[3]);
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
                GeneralAirData.front_right_temperature = this.formatDecimal1.format(iArr[5] / 2.0f) + getTempUnitC(this.mContext);
            }
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(iArr[6], 4, 4);
            GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(iArr[6]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit2(iArr[6]);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit1(iArr[6]);
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(iArr[6]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(iArr[7], 6, 2);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 4, 2);
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 2, 2);
            int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(iArr[7], 0, 2);
            if (intFromByteWithBit == 0) {
                GeneralAirData.left_set_seat_cold = false;
                GeneralAirData.front_left_seat_cold_level = 0;
            } else {
                GeneralAirData.left_set_seat_cold = true;
                GeneralAirData.front_left_seat_cold_level = intFromByteWithBit;
            }
            if (intFromByteWithBit2 == 0) {
                GeneralAirData.left_set_seat_heat = false;
                GeneralAirData.front_left_seat_heat_level = 0;
            } else {
                GeneralAirData.left_set_seat_heat = true;
                GeneralAirData.front_left_seat_heat_level = intFromByteWithBit2;
            }
            if (intFromByteWithBit3 == 0) {
                GeneralAirData.right_set_seat_cold = false;
                GeneralAirData.front_right_seat_cold_level = 0;
            } else {
                GeneralAirData.right_set_seat_cold = true;
                GeneralAirData.front_right_seat_cold_level = intFromByteWithBit3;
            }
            if (intFromByteWithBit4 == 0) {
                GeneralAirData.right_set_seat_heat = false;
                GeneralAirData.front_right_seat_heat_level = 0;
            } else {
                GeneralAirData.right_set_seat_heat = true;
                GeneralAirData.front_right_seat_heat_level = intFromByteWithBit4;
            }
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(iArr[8]);
            GeneralAirData.rear_ac = DataHandleUtils.getBoolBit6(iArr[8]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(iArr[8]);
            GeneralAirData.rear_sync = DataHandleUtils.getBoolBit4(iArr[8]);
            GeneralAirData.rear_lock = DataHandleUtils.getBoolBit3(iArr[8]);
            GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit2(iArr[8]);
            GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit2(iArr[8]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit1(iArr[8]);
            GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit1(iArr[8]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit0(iArr[8]);
            GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit0(iArr[8]);
            int i3 = iArr[9];
            if (i3 == 255) {
                GeneralAirData.rear_left_temperature = "HI";
            } else if (i3 == 0) {
                GeneralAirData.rear_left_temperature = "LO";
            } else {
                GeneralAirData.rear_left_temperature = this.formatDecimal1.format(iArr[9] / 2.0f) + getTempUnitC(this.mContext);
            }
            int i4 = iArr[10];
            if (i4 == 255) {
                GeneralAirData.rear_right_temperature = "HI";
            } else if (i4 == 0) {
                GeneralAirData.rear_right_temperature = "LO";
            } else {
                GeneralAirData.rear_right_temperature = this.formatDecimal1.format(iArr[10] / 2.0f) + getTempUnitC(this.mContext);
            }
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(iArr[11]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(iArr[11]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(iArr[11]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit4(iArr[11]);
            if (z) {
                updateAirActivity(this.mContext, 1002);
            } else {
                updateAirActivity(this.mContext, 1001);
            }
        }
    }

    private void setSwc0x02(int[] iArr) {
        switch (iArr[2]) {
            case 0:
                realKeyLongClick1(this.mContext, 0, iArr[3]);
                break;
            case 1:
            case 17:
                realKeyLongClick1(this.mContext, 7, iArr[3]);
                break;
            case 2:
            case 18:
                realKeyLongClick1(this.mContext, 8, iArr[3]);
                break;
            case 3:
            case 11:
            case 20:
                realKeyLongClick1(this.mContext, 46, iArr[3]);
                break;
            case 4:
            case 10:
            case 19:
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
            case 12:
                realKeyLongClick1(this.mContext, 151, iArr[3]);
                break;
            case 13:
                realKeyLongClick1(this.mContext, 49, iArr[3]);
                break;
            case 16:
                realKeyLongClick1(this.mContext, 50, iArr[3]);
                break;
            case 24:
                realKeyLongClick1(this.mContext, 1, iArr[3]);
                break;
            case 25:
                realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                break;
            case 28:
                realKeyLongClick1(this.mContext, 31, iArr[3]);
                break;
        }
    }

    private void setBasicInfo0x01(int[] iArr) {
        if (isBasicInfoChange(iArr)) {
            setBacklightLevel((iArr[3] / 20) + 1);
        }
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
