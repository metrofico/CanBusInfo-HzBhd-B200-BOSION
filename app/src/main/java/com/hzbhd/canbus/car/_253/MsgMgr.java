package com.hzbhd.canbus.car._253;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
    static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
    static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
    private int eachId;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferentId;
    private byte mFreqHi;
    private byte mFreqLo;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private MsgMgr msgMgr;
    private final int MEDIA_SOURCE_ID_AUX = 7;
    private final int MEDIA_SOURCE_ID_USB = 8;
    private int AutoAcState = 0;
    private int frontDefogState = 0;
    private int RearDefogState = 0;
    private int DualState = 0;

    private boolean isEnlarge(int i) {
        return i == 5 || i == 6 || i == 7 || i == 8;
    }

    private boolean isFront(int i) {
        return i == 1 || i == 5;
    }

    private boolean isLeft(int i) {
        return i == 3 || i == 7;
    }

    private boolean isNarrow(int i) {
        return i == 1 || i == 2 || i == 3 || i == 4;
    }

    private boolean isRear(int i) {
        return i == 2 || i == 6;
    }

    private boolean isRight(int i) {
        return i == 4 || i == 8;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14BackLight();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo();
            return;
        }
        if (i != 57) {
            switch (i) {
                case 32:
                    set0x20WheelKeyInfo();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadar0x22();
                    break;
                case 35:
                    setFrontRadar0x23();
                    break;
                case 36:
                    setDoorData0x24();
                    break;
                case 37:
                    setPanelKey0x25();
                    break;
                case 38:
                    setPanaramic0x26();
                    break;
                case 39:
                    setDAS0x27();
                    break;
            }
            return;
        }
        setCarSettingState0x39();
    }

    private void set0x14BackLight() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            setBacklightLevel((iArr[3] / 41) + 1);
        }
    }

    private void setCarSettingState0x39() {
        String.valueOf(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        switch (this.mCanBusInfoInt[2]) {
            case 2:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_initial_perspective"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                break;
            case 3:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_front_radar_switch"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 4:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_rear_radar_switch"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 5:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Lane_deviation_warning"), Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]))));
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Blind_spot_warning"), Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]))));
                break;
            case 6:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Language"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 7:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Unit_of_speed_and_fuel_consumption"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 8:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Temperature_unit"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 9:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Theme"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 10:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_HeadLight_delay"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 11:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Remote_lock_reminder"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 12:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_Lightness"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 13:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Tire_Pressure_Monitoring"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 14:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Automatic_folding_Rearview_mirror"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 15:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Parking_unlocked"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 16:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Ambient_light_mode"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
            case 17:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Rearview_mirror_backlight_brightness"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                break;
            case 18:
                arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Rearview_mirror_automatically_anti_glare"), Integer.valueOf(this.mCanBusInfoInt[3])));
                break;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setDAS0x27() {
        if (isSupport0x27()) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Lane_deviation_warning"), Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
        arrayList.add(new SettingUpdateEntity(getmUigMgr(this.mContext).getSettingLeftIndexes(this.mContext, "car_set"), getmUigMgr(this.mContext).getSettingRightIndex(this.mContext, "car_set", "_253_Blind_spot_warning"), Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x20WheelKeyInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 16) {
            realKeyLongClick1(this.mContext, 14, iArr[3]);
            return;
        }
        if (i != 17) {
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
                    realKeyLongClick1(this.mContext, 45, iArr[3]);
                    break;
                case 4:
                    realKeyLongClick1(this.mContext, 46, iArr[3]);
                    break;
                case 5:
                    if (getCurrentCanDifferentId() == 0 || getCurrentCanDifferentId() == 8) {
                        realKeyLongClick1(this.mContext, HotKeyConstant.K_PHONE_ON_OFF, this.mCanBusInfoInt[3]);
                        break;
                    } else {
                        realKeyLongClick1(this.mContext, 14, this.mCanBusInfoInt[3]);
                        break;
                    }
                case 6:
                    if (getCurrentCanDifferentId() == 2) {
                        realKeyLongClick1(this.mContext, 15, this.mCanBusInfoInt[3]);
                        break;
                    } else {
                        realKeyLongClick1(this.mContext, 184, this.mCanBusInfoInt[3]);
                        break;
                    }
                case 7:
                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                    break;
                case 8:
                    realKeyLongClick1(this.mContext, 7, iArr[3]);
                    break;
                case 9:
                    realKeyLongClick1(this.mContext, 8, iArr[3]);
                    break;
                case 10:
                    realKeyLongClick1(this.mContext, 1, iArr[3]);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realKeyLongClick1(this.mContext, 3, iArr[3]);
                            break;
                        case 23:
                            realKeyLongClick1(this.mContext, 52, iArr[3]);
                            break;
                        case 24:
                            realKeyLongClick1(this.mContext, 50, iArr[3]);
                            break;
                        case 25:
                            realKeyLongClick1(this.mContext, 128, iArr[3]);
                            break;
                        default:
                            switch (i) {
                                case 32:
                                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SLEEP, iArr[3]);
                                    break;
                                case 33:
                                    realKeyLongClick1(this.mContext, 2, iArr[3]);
                                    break;
                                case 34:
                                    realKeyLongClick1(this.mContext, HotKeyConstant.K_SPEECH, iArr[3]);
                                    break;
                                case 35:
                                    realKeyLongClick1(this.mContext, 49, iArr[3]);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyLongClick1(this.mContext, 15, iArr[3]);
    }

    private void setOutDoorTemp() {
        if (this.mCanBusInfoInt.length < 7) {
            return;
        }
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private void setAirData0x21() {
        if (isSupport0x21()) {
            return;
        }
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 9);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        setOutDoorTemp();
        GeneralAirData.center_wheel = resolveCenterWheel(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2));
        if (isAirMsgRepeat(bArrBytesExpectOneByte)) {
            return;
        }
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.ion = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        int i = this.eachId;
        if (i == 3 || i == 13) {
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (GeneralAirData.in_out_auto_cycle != 2) {
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1);
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadar0x22() {
        if (isSupport0x22()) {
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = false;
        if (SharePreUtil.getBoolValue(this.mContext, SHARE_KEY_253_REAR_RADAR_ENABLE, true)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        } else {
            RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
        }
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar0x23() {
        if (isSupport0x23()) {
            return;
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = false;
        if (SharePreUtil.getBoolValue(this.mContext, SHARE_KEY_253_FRONT_RADAR_ENABLE, true)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        } else {
            RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
        }
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
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

    private void setPanelKey0x25() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            reakkeyClick(0);
        }
        if (i == 1) {
            reakkeyClick(128);
            return;
        }
        if (i == 6) {
            reakkeyClick(50);
            return;
        }
        switch (i) {
            case 9:
                reakkeyClick(33);
                break;
            case 10:
                reakkeyClick(34);
                break;
            case 11:
                reakkeyClick(35);
                break;
            case 12:
                reakkeyClick(36);
                break;
            case 13:
                reakkeyClick(37);
                break;
            case 14:
                reakkeyClick(38);
                break;
            case 15:
                reakkeyClick(39);
                break;
            case 16:
                reakkeyClick(40);
                break;
            case 17:
                realKeyClick3_1(this.mContext, 8, 0, iArr[3]);
                break;
            case 18:
                realKeyClick3_1(this.mContext, 7, 0, iArr[3]);
                break;
            case 19:
                reakkeyClick(49);
                break;
            case 20:
                reakkeyClick(68);
                break;
            case 21:
                reakkeyClick(59);
                break;
            case 22:
                reakkeyClick(76);
                break;
            case 23:
                reakkeyClick(52);
                break;
            case 24:
                reakkeyClick(58);
                break;
            case 25:
                reakkeyClick(1);
                break;
        }
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setPanaramic0x26() {
        if (isSupport0x26()) {
            return;
        }
        forceReverse(this.mContext, this.mCanBusInfoInt[2] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 0));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(2, isFront(this.mCanBusInfoInt[4])));
        arrayList.add(new PanoramicBtnUpdateEntity(3, isRear(this.mCanBusInfoInt[4])));
        arrayList.add(new PanoramicBtnUpdateEntity(4, isLeft(this.mCanBusInfoInt[4])));
        arrayList.add(new PanoramicBtnUpdateEntity(5, isRight(this.mCanBusInfoInt[4])));
        arrayList.add(new PanoramicBtnUpdateEntity(6, isEnlarge(this.mCanBusInfoInt[4])));
        arrayList.add(new PanoramicBtnUpdateEntity(7, isNarrow(this.mCanBusInfoInt[4])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void setTrackData0x29() {
        /*
            r5 = this;
            int r0 = r5.eachId
            r1 = 1
            r2 = 16
            r3 = 3
            r4 = 2
            if (r0 == r1) goto L23
            if (r0 == r4) goto L11
            if (r0 == r3) goto L11
            switch(r0) {
                case 8: goto L11;
                case 9: goto L23;
                case 10: goto L23;
                case 11: goto L11;
                case 12: goto L11;
                case 13: goto L11;
                default: goto L10;
            }
        L10:
            return
        L11:
            byte[] r0 = r5.mCanBusInfoByte
            r1 = r0[r4]
            r0 = r0[r3]
            r3 = 7808(0x1e80, float:1.0941E-41)
            r4 = 13708(0x358c, float:1.9209E-41)
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r1, r0, r3, r4, r2)
            int r0 = -r0
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
            goto L34
        L23:
            byte[] r0 = r5.mCanBusInfoByte
            r1 = r0[r4]
            r0 = r0[r3]
            r3 = 7936(0x1f00, float:1.1121E-41)
            r4 = 13055(0x32ff, float:1.8294E-41)
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r1, r0, r3, r4, r2)
            int r0 = -r0
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
        L34:
            r0 = 0
            android.content.Context r1 = r5.mContext
            r5.updateParkUi(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._253.MsgMgr.setTrackData0x29():void");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002a  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void voiceControlInfo(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 716
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._253.MsgMgr.voiceControlInfo(java.lang.String):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (this.mContext == null) {
            return;
        }
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.mFreqLo, this.mFreqHi, (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 1});
        tokingNowTime(0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        tokingNowTime(i);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    private void sendMusic(String str) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 1}, DataHandleUtils.exceptBOMHead(str.getBytes("Unicode"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -123, (byte) (i - 2020), (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[9];
        if (i < 0 || i > 180) {
            return " ";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return new DecimalFormat("0.0").format(((((i / 2.0f) - 40.0f) * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext);
        }
        return ((i / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private String resolveLeftAndRightTemp(int i) {
        int i2 = this.eachId;
        if (i2 == 3) {
            return Integer.toString(DataHandleUtils.rangeNumber(i, 1, 9)) + "LEVER";
        }
        if (i2 == 4 || i2 == 5 || i2 == 6) {
            return Integer.toString(DataHandleUtils.rangeNumber(i, 1, 15)) + "LEVER";
        }
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            if (i >= 1 && i <= 33) {
                return (i + 60) + getTempUnitF(this.mContext);
            }
        } else if (i >= 1 && i <= 31) {
            return (((i + 32) / 2.0f) - 0.5d) + getTempUnitC(this.mContext);
        }
        return "---";
    }

    private void tokingNowTime(int i) {
        int i2 = i % 3600;
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i2 % 60), (byte) (i2 / 60), (byte) (i / 3600), 0});
    }

    private String resolveCenterWheel(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._253_air_light);
        }
        if (i != 1) {
            return i != 2 ? " " : this.mContext.getResources().getString(R.string._253_air_strong);
        }
        return this.mContext.getResources().getString(R.string._253_air_medium);
    }

    private void reakkeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private boolean isSupport0x21() {
        switch (this.eachId) {
            case 1:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 9:
            case 10:
            case 12:
            case 13:
                return false;
            case 2:
            case 8:
            case 11:
            default:
                return true;
        }
    }

    private boolean isSupport0x22() {
        switch (this.eachId) {
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
                return false;
            default:
                return true;
        }
    }

    private boolean isSupport0x23() {
        int i = this.eachId;
        return (i == 9 || i == 10) ? false : true;
    }

    private boolean isSupport0x26() {
        int i = this.eachId;
        return (i == 9 || i == 10) ? false : true;
    }

    private boolean isSupport0x27() {
        return this.eachId != 10;
    }

    private boolean isSupport0x39() {
        switch (this.eachId) {
            case 11:
            case 12:
            case 13:
                return false;
            default:
                return true;
        }
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
        }
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.mFreqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.mFreqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.mFreqHi = (byte) (i >> 8);
            this.mFreqLo = (byte) (i & 255);
        }
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void initRadar() {
        RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
        RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private UiMgr getmUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
