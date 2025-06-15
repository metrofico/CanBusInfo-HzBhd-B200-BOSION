package com.hzbhd.canbus.car._193;

import android.content.Context;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
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
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
    static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
    private static final String TAG = "123";
    DecimalFormat decimalFormat;
    int differentId;
    private int eachId;
    int[] mAirData;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private byte mFreqHi;
    private byte mFreqLo;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private MsgMgr msgMgr;
    private UiMgr uiMgr;

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        sendcarType();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 16) {
            setOutDoorTemp0x10();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setSettingstate0x40();
            return;
        }
        if (i != 65) {
            switch (i) {
                case 32:
                    setWheelKeyInfo0x20();
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
            }
            return;
        }
        setSettingstate20x41();
    }

    private void setSettingstate20x41() {
        if (this.eachId != 18) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "seatDriveProfile"), resolveMode(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_D"), Boolean.valueOf(DataHandleUtils.getBoolBit0(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_E"), Boolean.valueOf(DataHandleUtils.getBoolBit1(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_F"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_2"), Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_4"), Boolean.valueOf(DataHandleUtils.getBoolBit2(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_5"), Boolean.valueOf(DataHandleUtils.getBoolBit3(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_10", "_1193_setting_10_0"), Boolean.valueOf(DataHandleUtils.getBoolBit4(i))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_10"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_10", "_1193_setting_10_1"), Boolean.valueOf(DataHandleUtils.getBoolBit5(i))));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "nissan_raise_mileage_title");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(((iArr[5] * 256) + iArr[6]) * 0.1d).append("KM").toString()));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_16_min_value_1"), (this.mCanBusInfoInt[7] * 0.2d) + "KMH/100KM"));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
    }

    private void setOutDoorTemp0x10() {
        int i = this.eachId;
        if (i == 2 || i == 3 || i == 8 || i == 9) {
            updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[3] * 0.5d) - 55.0d) + getTempUnitC(this.mContext));
        }
    }

    private void setWheelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
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
                buttonKey(45);
                break;
            case 4:
                buttonKey(46);
                break;
            case 5:
                buttonKey(HotKeyConstant.K_1_PICKUP);
                if (this.eachId == 18) {
                    buttonKey(3);
                    break;
                }
                break;
            case 6:
                buttonKey(HotKeyConstant.K_2_HANGUP);
                break;
            case 7:
                buttonKey(2);
                break;
            case 8:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            default:
                switch (i) {
                    case 16:
                        buttonKey(76);
                        break;
                    case 17:
                        buttonKey(77);
                        break;
                    case 18:
                        buttonKey(1);
                        break;
                    case 19:
                        buttonKey(21);
                        break;
                    case 20:
                        buttonKey(20);
                        break;
                    case 21:
                        buttonKey(3);
                        break;
                    case 22:
                        buttonKey(30);
                        break;
                    case 23:
                        buttonKey(2);
                        break;
                    case 24:
                        buttonKey(75);
                        break;
                    case 25:
                        buttonKey(58);
                        break;
                    default:
                        switch (i) {
                            case 32:
                                buttonKey(7);
                                break;
                            case 33:
                                buttonKey(8);
                                break;
                            case 34:
                                buttonKey(4);
                                break;
                            case 35:
                                buttonKey(151);
                                break;
                            case 36:
                                buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                                break;
                            case 37:
                                buttonKey(62);
                                break;
                            case 38:
                                buttonKey(52);
                                break;
                            case 39:
                                buttonKey(220);
                                break;
                            case 40:
                                int i2 = this.eachId;
                                if (i2 == 2 || i2 == 5 || i2 == 6 || i2 == 8 || i2 == 9) {
                                    buttonKey(HotKeyConstant.K_1_PICKUP);
                                    break;
                                } else {
                                    buttonKey(128);
                                    break;
                                }
                            case 41:
                                buttonKey(128);
                                break;
                            case 42:
                                buttonKey(HotKeyConstant.K_DISP);
                                break;
                            case 43:
                                buttonKey(49);
                                break;
                            case 44:
                                buttonKey(45);
                                break;
                            case 45:
                                buttonKey(46);
                                break;
                            case 46:
                                buttonKey(7);
                                break;
                            case 47:
                                buttonKey(8);
                                break;
                        }
                }
        }
    }

    private void setAirData0x21() {
        if (isSupport0x21() || isAirDataChange()) {
            return;
        }
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        switch (this.mCanBusInfoInt[3]) {
            case 0:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.max_front = false;
                break;
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.max_front = false;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.max_front = false;
                break;
            case 3:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.max_front = false;
                break;
            case 4:
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_defog = true;
                GeneralAirData.max_front = false;
                break;
            case 5:
                GeneralAirData.front_right_blow_head = false;
                GeneralAirData.front_right_blow_foot = false;
                GeneralAirData.front_left_blow_head = false;
                GeneralAirData.front_left_blow_foot = false;
                GeneralAirData.front_defog = true;
                GeneralAirData.max_front = false;
                break;
            case 6:
                GeneralAirData.max_front = true;
                break;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[4];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
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
        GeneralDoorData.isShowLittleLight = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralDoorData.isShowRev = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralDoorData.isShowHandBrake = true;
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 32768, 41472, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSettingstate0x40() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 254) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
        } else if (i != 255) {
            switch (i) {
                case 0:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_0_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 1:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 2:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 3:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 4:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 5:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 6:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 7:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 8:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_3", "_1193_setting_3_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 9:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 10:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_4"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_4", "_1193_setting_4_1"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
                    break;
                case 11:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 12:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 13:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 14:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 15:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_6"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 16:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_7"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 17:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_8"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 18:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_9"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 19:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_A"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 20:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 21:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 22:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_2", "_1193_setting_2_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 23:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_B"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 24:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 25:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 26:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 27:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_1", "_1193_setting_1_C"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 50));
                    break;
                case 28:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 29:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 30:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 31:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 32:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_3"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3] - 1));
                    break;
                case 33:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_5"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_5", "_1193_setting_5_4"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                    break;
                case 34:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_0"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 35:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 36:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 37:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 38:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_4"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 39:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_5"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 40:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_8"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_8", "_1193_setting_8_5_3"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 41:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_7"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_7", "_1193_setting_7_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 42:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_6"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_6", "_1193_setting_6_2"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
            }
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_1193_setting_9"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_1193_setting_9", "_1193_setting_9"), Integer.valueOf(this.mCanBusInfoInt[3])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (this.mContext == null) {
            return;
        }
        byte allBandTypeData = getAllBandTypeData(str);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, allBandTypeData, this.mFreqLo, this.mFreqHi, (byte) i, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
        sendMusic(str4, str6);
    }

    private void sendMusic(String str, String str2) {
        byte[] bArr = {22, 113, 18};
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, 112, 18}, DataHandleUtils.exceptBOMHead(str.getBytes("UTF8"))));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, DataHandleUtils.exceptBOMHead(str2.getBytes("UTF8"))));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -90, 0, 0, 0, (byte) i8, (byte) i6, (byte) i7});
        sendcarType();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 3, 1, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        if (this.eachId == 1) {
            if (i > 28) {
                i = 28;
            }
        } else if (i > 31) {
            i = 31;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 1});
        tokingNowTime(0);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTPHONE.name(), new byte[]{22, -64, 5, -1, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 1, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        tokingNowTime(i);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateParkingBtn(int i) {
        ArrayList arrayList = new ArrayList();
        if (i == 4) {
            arrayList.add(new PanoramicBtnUpdateEntity(i, false));
        } else {
            arrayList.add(new PanoramicBtnUpdateEntity(i, true));
        }
        updateParkUi(null, this.mContext);
        updateParkingBtn(i);
    }

    void initRadar() {
        RadarInfoUtil.setFrontRadarLocationData(4, 0, 0, 0, 0);
        RadarInfoUtil.setRearRadarLocationData(4, 0, 0, 0, 0);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[3];
        if (i < 0 || i > 180) {
            return " ";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return new DecimalFormat("0.0").format(((((i / 2.0f) - 40.0f) * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext);
        }
        return ((i / 2.0f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private String resolveMode(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._327_Movement_mode);
        }
        return this.mContext.getResources().getString(R.string._327_economic_model);
    }

    private String resolveLeftAndRightTemp(int i) {
        return i == 0 ? "LO" : i == 30 ? "HI" : (i < 0 || i > 29) ? "---" : ((i + 35) / 2.0f) + getTempUnitC(this.mContext);
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
                return (byte) 16;
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

    private void tokingNowTime(int i) {
        this.decimalFormat = new DecimalFormat("00");
        int i2 = i / 1000;
        int i3 = i2 % 3600;
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i3 % 60), (byte) (i3 / 60), (byte) (i2 / 3600), 0});
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
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

    private boolean isSupport0x10() {
        int i = this.eachId;
        return (i == 2 || i == 3 || i == 8 || i == 9) ? false : true;
    }

    private boolean isSupport0x21() {
        switch (this.eachId) {
            case 2:
            case 3:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
                return false;
            case 4:
            case 12:
            case 15:
            default:
                return true;
        }
    }

    private boolean isSupport0x22() {
        int i = this.eachId;
        return (i == 11 || i == 13 || i == 16) ? false : true;
    }

    private boolean isSupport0x23() {
        int i = this.eachId;
        return (i == 11 || i == 13 || i == 16) ? false : true;
    }

    private boolean isSupport0x40() {
        switch (this.eachId) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 11:
            case 12:
            case 13:
            case 14:
            case 16:
            case 17:
                return false;
            case 10:
            case 15:
            default:
                return true;
        }
    }

    private void sendcarType() {
        if (this.eachId == 5) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 1});
        }
        if (this.eachId == 6) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 2});
        }
        if (this.eachId == 7) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 3});
        }
        if (this.eachId == 8) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 4});
        }
        if (this.eachId == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 5});
        }
        if (this.eachId == 10) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 6});
        }
        if (this.eachId == 11) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 7});
        }
        if (this.eachId == 13) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 8});
        }
        if (this.eachId == 14) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 9});
        }
        if (this.eachId == 16) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 10});
        }
        if (this.eachId == 17) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 11});
        }
        if (this.eachId == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
        if (this.eachId == 19) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
        }
        if (this.eachId == 20) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 4, 14});
        }
    }

    private MsgMgr getMsgMgr(Context context) {
        if (this.msgMgr == null) {
            this.msgMgr = (MsgMgr) MsgMgrFactory.getCanMsgMgr(context);
        }
        return this.msgMgr;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
