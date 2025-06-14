package com.hzbhd.canbus.car._292;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.activity.OnStarActivity;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.fragment.OnStartPhoneFragment;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOnStartData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static int outDoorTemp;
    private int FreqInt;
    private byte bandType;
    private byte freqHi;
    private byte freqLo;
    private byte[] mCanInfoByte;
    private int[] mCanInfoInt;
    private Context mContext;

    private void rearPanelBtn() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) getCurrentEachCanId()});
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OnStarActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        super.canbusInfoChange(context, bArr);
        this.mContext = context;
        this.mCanInfoInt = getByteArrayToIntArray(bArr);
        this.mCanInfoByte = bArr;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        int[] iArr = this.mCanInfoInt;
        int i = iArr[1];
        if (i == 1) {
            setSwc();
            return;
        }
        if (i == 2) {
            setPanelBtnKey();
            return;
        }
        if (i == 3) {
            setAirInfo();
            return;
        }
        if (i == 4) {
            smallLight();
            return;
        }
        if (i == 38) {
            epsSetting();
            return;
        }
        if (i == 97) {
            carBodyStatus();
            return;
        }
        if (i != 98) {
            switch (i) {
                case 6:
                    centerCtrlSetting();
                    break;
                case 7:
                    radarOnOffInfo();
                    break;
                case 8:
                    onStarPhoneInfo();
                    break;
                case 9:
                    onStarStatusInfo();
                    break;
                case 10:
                    centerCtrlSetting2();
                    break;
                case 11:
                    updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[2], iArr[3]));
                    break;
                case 12:
                    setLanguage();
                    break;
                case 13:
                    warningVolumeStatus();
                    break;
                default:
                    switch (i) {
                        case 34:
                            rearRadar();
                            break;
                        case 35:
                            frontRadar();
                            break;
                        case 36:
                            setCarStatus();
                            break;
                        default:
                            switch (i) {
                                case 48:
                                    updateVersionInfo(this.mContext, getVersionStr(this.mCanInfoByte));
                                    break;
                                case 49:
                                    carSetting1();
                                    break;
                                case 50:
                                    carSetting2();
                                    break;
                            }
                    }
            }
            return;
        }
        rearPanelBtn();
    }

    private void setSwc() {
        switch (this.mCanInfoInt[2]) {
            case 0:
                realKeyClick2(0);
                break;
            case 1:
                realKeyClick2(7);
                break;
            case 2:
                realKeyClick2(8);
                break;
            case 3:
                realKeyClick2(45);
                break;
            case 4:
                realKeyClick2(46);
                break;
            case 5:
                realKeyClick2(2);
                break;
            case 6:
                realKeyClick2(HotKeyConstant.K_VOICE_PICKUP);
                break;
            case 7:
                realKeyClick2(184);
                break;
        }
    }

    private void setPanelBtnKey() {
        int i = this.mCanInfoInt[2];
        if (i == 0) {
            realKeyClick2(0);
            return;
        }
        if (i == 1) {
            realKeyClick2(1);
            return;
        }
        if (i == 2) {
            realKeyClick2(21);
            return;
        }
        if (i == 3) {
            realKeyClick2(20);
            return;
        }
        if (i != 4) {
            if (i == 21) {
                realKeyClick2(75);
                return;
            }
            if (i == 22) {
                realKeyClick2(49);
                return;
            }
            if (i == 64) {
                realKeyClick2(141);
                return;
            }
            if (i == 80) {
                realKeyClick2(52);
                return;
            }
            if (i != 81) {
                switch (i) {
                    case 6:
                        realKeyClick2(50);
                        return;
                    case 7:
                        realKeyClick2(76);
                        return;
                    case 8:
                        realKeyClick2(130);
                        return;
                    case 9:
                        realKeyClick2(3);
                        return;
                    case 10:
                        realKeyClick2(33);
                        return;
                    case 11:
                        realKeyClick2(34);
                        return;
                    case 12:
                        realKeyClick2(35);
                        return;
                    case 13:
                        realKeyClick2(36);
                        return;
                    case 14:
                        realKeyClick2(37);
                        return;
                    case 15:
                        realKeyClick2(38);
                        return;
                    default:
                        switch (i) {
                            case 17:
                                realKeyClick2(31);
                                break;
                            case 18:
                                break;
                            case 19:
                                realKeyClick2(HotKeyConstant.K_TIME);
                                break;
                            default:
                                switch (i) {
                                    case 25:
                                        realKeyClick2(48);
                                        break;
                                    case 26:
                                        realKeyClick2(47);
                                        break;
                                    case 27:
                                        realKeyClick2(17);
                                        break;
                                    case 28:
                                        realKeyClick2(7);
                                        break;
                                    case 29:
                                        realKeyClick2(46);
                                        break;
                                }
                        }
                        return;
                }
            }
            realKeyClick2(2);
            return;
        }
        realKeyClick2(HotKeyConstant.K_CAN_CONFIG);
    }

    private void setAirInfo() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanInfoInt[2]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 3);
        GeneralAirData.eco = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
        GeneralAirData.ac_auto = DataHandleUtils.getBoolBit1(this.mCanInfoInt[8]);
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit0(this.mCanInfoInt[8]);
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.max_front = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 4) == 2;
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 4)) {
            case 1:
                GeneralAirData.front_left_auto_wind = true;
                GeneralAirData.front_right_auto_wind = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 8:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 9:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
        }
        GeneralAirData.front_left_temperature = resolveLeftAndRightAutoTemp(this.mCanInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightAutoTemp(this.mCanInfoInt[5]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 4, 4);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 0, 4);
        outDoorTemp = this.mCanInfoByte[7];
        if (isOutDoorTempChange()) {
            SharePreUtil.setIntValue(this.mContext, "_291_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, outDoorTemp + "℃");
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void smallLight() {
        String resString;
        String resString2;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 7);
        if (intFromByteWithBit == 0) {
            resString = getResString(R.string.mazda_binary_car_set32_1);
        } else if (intFromByteWithBit == 17) {
            resString = getResString(R.string.mazda_binary_car_set32_2);
        } else {
            resString = intFromByteWithBit + "";
        }
        if (DataHandleUtils.getBoolBit7(this.mCanInfoInt[2])) {
            resString2 = getResString(R.string.open) + "   " + getResString(R.string.brightness) + ": " + resString;
        } else {
            resString2 = getResString(R.string.close);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, resString2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void centerCtrlSetting() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 5, 2);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 4, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 3, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 1, 2);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 7, 1);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 5, 2);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 4, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 3, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 1, 1);
        ArrayList arrayList = new ArrayList();
        int[] iArr = {intFromByteWithBit, intFromByteWithBit2, intFromByteWithBit3, intFromByteWithBit4, intFromByteWithBit5, intFromByteWithBit6, intFromByteWithBit7, intFromByteWithBit8, intFromByteWithBit9, intFromByteWithBit10, intFromByteWithBit11};
        for (int i = 0; i < 11; i++) {
            arrayList.add(new SettingUpdateEntity(0, i, Integer.valueOf(iArr[i])));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void radarOnOffInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void onStarPhoneInfo() {
        GeneralOnStartData.mOnStarPhoneNum = phoneNum();
        updateOnStarActivity(1001);
    }

    private void onStarStatusInfo() {
        if (this.mCanInfoInt[2] == 0) {
            exitAuxIn2();
            Log.d("cwh", "anjixng name = " + OnStarActivity.class.getName());
            if (SystemUtil.isForeground(this.mContext, OnStarActivity.class.getName())) {
                realKeyClick(this.mContext, 52);
            }
        } else {
            enterAuxIn2(this.mContext, Constant.OnStarActivity);
            openOnStarPhoneInfoFragment();
        }
        GeneralOnStartData.mOnStarStatus = this.mCanInfoInt[2];
        updateOnStarActivity(1001);
    }

    private void centerCtrlSetting2() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 7, 1);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 6, 1);
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 5, 1);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 4, 1);
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 3, 1);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 2, 1);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[2], 0, 2);
        int intFromByteWithBit8 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 7, 1);
        int intFromByteWithBit9 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 6, 1);
        int intFromByteWithBit10 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 5, 1);
        int intFromByteWithBit11 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 4, 1);
        int intFromByteWithBit12 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 3, 1);
        int intFromByteWithBit13 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 2, 1);
        int intFromByteWithBit14 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 2);
        int intFromByteWithBit15 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 3, 1);
        int intFromByteWithBit16 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 2, 1);
        int intFromByteWithBit17 = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 0, 1);
        ArrayList arrayList = new ArrayList();
        int[] iArr = {intFromByteWithBit, intFromByteWithBit2, intFromByteWithBit3, intFromByteWithBit4, intFromByteWithBit5, intFromByteWithBit6, intFromByteWithBit7, intFromByteWithBit8, intFromByteWithBit9, intFromByteWithBit10, intFromByteWithBit11, intFromByteWithBit12, intFromByteWithBit13, intFromByteWithBit15, intFromByteWithBit16, intFromByteWithBit17};
        for (int i = 0; i < 16; i++) {
            arrayList.add(new SettingUpdateEntity(1, i, Integer.valueOf(iArr[i])));
        }
        if (intFromByteWithBit14 == 3) {
            arrayList.add(new SettingUpdateEntity(1, 16, 2));
        } else {
            arrayList.add(new SettingUpdateEntity(1, 16, Integer.valueOf(intFromByteWithBit14)));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLanguage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.mCanInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void warningVolumeStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(this.mCanInfoInt[2])).setProgress(this.mCanInfoInt[2]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void rearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void frontRadar() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setCarStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, getResString(DataHandleUtils.getBoolBit2(this.mCanInfoInt[3]) ? R.string.open : R.string.close)));
        arrayList.add(new DriverUpdateEntity(0, 2, getResString(DataHandleUtils.getBoolBit1(this.mCanInfoInt[3]) ? R.string.gear_not_p : R.string.gear_p)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void epsSetting() {
        byte[] bArr = this.mCanInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 7800, 16);
        updateParkUi(null, this.mContext);
    }

    private void carSetting1() {
        String str = DataHandleUtils.getBoolBit7(this.mCanInfoInt[4]) ? "- " : " ";
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[4], 0, 7);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, (this.mCanInfoInt[3] / 10) + ""));
        arrayList.add(new DriverUpdateEntity(1, 1, str + intFromByteWithBit + "℃"));
        arrayList.add(new DriverUpdateEntity(1, 2, DataHandleUtils.getBoolBit7(this.mCanInfoInt[5]) ? getResString(R.string.open) : getResString(R.string.close)));
        arrayList.add(new DriverUpdateEntity(1, 3, DataHandleUtils.getBoolBit6(this.mCanInfoInt[5]) ? getResString(R.string.open) : getResString(R.string.close)));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb.append((iArr[6] * 256 * 256 * 256) + (iArr[7] * 256 * 256) + (iArr[8] * 256) + iArr[9]).append("Km").toString()));
        arrayList.add(new DriverUpdateEntity(0, 3, DataHandleUtils.getBoolBit7(this.mCanInfoInt[2]) ? getResString(R.string.open) : getResString(R.string.close)));
        arrayList.add(new DriverUpdateEntity(0, 4, DataHandleUtils.getBoolBit6(this.mCanInfoInt[2]) ? getResString(R.string.open) : getResString(R.string.close)));
        arrayList.add(new DriverUpdateEntity(0, 5, DataHandleUtils.getBoolBit5(this.mCanInfoInt[2]) ? getResString(R.string.open) : getResString(R.string.close)));
        arrayList.add(new DriverUpdateEntity(0, 6, DataHandleUtils.getBoolBit4(this.mCanInfoInt[2]) ? getResString(R.string.open) : getResString(R.string.close)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void carSetting2() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 5, sb.append(((iArr[4] * 256) + iArr[5]) * 0.1d).append(" L/100km").toString()));
        arrayList.add(new DriverUpdateEntity(1, 6, this.mCanInfoInt[6] + "%"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void carBodyStatus() {
        GeneralDoorData.isShowCarBody = false;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        if (((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature()) {
            GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]);
            GeneralDoorData.isSubSeatBeltTie = true ^ DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]);
        } else {
            GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]);
            GeneralDoorData.isSubSeatBeltTie = true ^ DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]);
        }
        updateDoorView(this.mContext);
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private String resolveLeftAndRightAutoTemp(int i) {
        if (255 == i) {
            return "--";
        }
        if (i == 0) {
            return "LO";
        }
        if (30 == i) {
            return "HI";
        }
        if (i >= 1 && i <= 28) {
            return ((i + 33) * 0.5f) + getTempUnitC(this.mContext);
        }
        if (i == 29) {
            return 16 + getTempUnitC(this.mContext);
        }
        if (i == 31) {
            return 16.5d + getTempUnitC(this.mContext);
        }
        if (i == 32) {
            return 15 + getTempUnitC(this.mContext);
        }
        return i == 33 ? 15.5d + getTempUnitC(this.mContext) : "";
    }

    private boolean isOutDoorTempChange() {
        return SharePreUtil.getIntValue(this.mContext, "_291_out_door_temp", 0) != outDoorTemp;
    }

    private String getResString(int i) {
        return this.mContext.getResources().getString(i);
    }

    private void openOnStarPhoneInfoFragment() {
        if (SystemUtil.isForeground(this.mContext, Constant.OnStarActivity.getClassName())) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(Constant.OnStarActivity);
        intent.setFlags(268435456);
        intent.putExtra(OnStarActivity.BUNDLE_OPEN_FRAGMENT, OnStartPhoneFragment.class);
        this.mContext.startActivity(intent);
    }

    private String phoneNum() {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= 11 && getLow4Bit(this.mCanInfoInt[i]) != 15 && getHigh4Bit(this.mCanInfoInt[i]) != 15; i++) {
            if (getHigh4Bit(this.mCanInfoInt[i]) == 10 || getLow4Bit(this.mCanInfoInt[i]) == 10) {
                sb.append("*");
            }
            if (getHigh4Bit(this.mCanInfoInt[i]) == 11 || getLow4Bit(this.mCanInfoInt[i]) == 11) {
                sb.append("#");
            } else {
                sb.append(getHigh4Bit(this.mCanInfoInt[i]));
                sb.append(getLow4Bit(this.mCanInfoInt[i]));
            }
            Log.d("cwh", i + " = " + getHigh4Bit(this.mCanInfoInt[i]) + "、" + getLow4Bit(this.mCanInfoInt[i]));
        }
        return sb.toString();
    }

    private int getHigh4Bit(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 4, 4);
    }

    private int getLow4Bit(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 0, 4);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1});
        setVwRadioInfo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte) i});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    private void setVwRadioInfo(String str, String str2) throws NumberFormatException {
        if (str.equals("AM1") || str.equals("AM2")) {
            int i = Integer.parseInt(str2);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
            this.bandType = (byte) 16;
        } else if (str.equals("FM1")) {
            this.bandType = (byte) 1;
        } else if (str.equals("FM2")) {
            this.bandType = (byte) 2;
        }
        getFreqByteHiLo(str, str2);
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("AM1")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 33});
            CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) i4, (byte) i5, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 17});
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 1, (byte) i3, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 16});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 19});
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 32});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 32});
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }
}
