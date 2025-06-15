package com.hzbhd.canbus.car._97;

import android.content.Context;
import android.os.Bundle;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_97_RADAR_SWITCH = "share_97_radar_switch";
    static final String SHARE_97_TEMPERATURE_UNIT = "share_97_temperature_unit";
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mDifferent;
    private int[] mDriveDataNow;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private int mEachId;
    private ID3[] mId3s;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private int[] mLightDataNow;
    private int mOutDoorTempDataNow;
    private int[] mRadarDataNow;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int[] mTrackDataNow;
    private UiMgr mUiMgr;
    private int mUnitDataNow;
    private int[] mVersionInfoNow;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private String[] FL = new String[2];
    private String[] FR = new String[2];
    private String[] RL = new String[2];
    private String[] RR = new String[2];

    private int getCar(int i) {
        if (i == 5) {
            return 0;
        }
        if (i == 9) {
            return 1;
        }
        if (i == 32) {
            return 9;
        }
        switch (i) {
            case 20:
                return 3;
            case 21:
                return 4;
            case 22:
                return 5;
            default:
                switch (i) {
                    case 28:
                        return 6;
                    case 29:
                        return 7;
                    case 30:
                        return 8;
                    default:
                        return 2;
                }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        CanbusMsgSender.sendMsg(new byte[]{22, 36, 19, 3});
        initId3();
        initDriveItem(getUiMgr(context).getDriverDataPageUiSet(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setKeyTrack0x11();
            int i2 = this.mCanBusInfoInt[3];
            if (i2 != 0) {
                updateSpeedInfo(i2);
                return;
            }
            return;
        }
        if (i == 18) {
            setDoorData0x12();
            return;
        }
        if (i == 33) {
            setPanelKey0x21();
            return;
        }
        if (i == 34) {
            setPanelKnob0x22();
            return;
        }
        if (i == 38) {
            set0x26CarSelect();
            return;
        }
        if (i == 52) {
            setFuelConsumptionMileageInfo();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
            return;
        }
        if (i == 72) {
            setTireInfo0x48();
            return;
        }
        if (i == 97) {
            setLightData0x61();
            return;
        }
        if (i == 104) {
            setUnitData0x68();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
        } else if (i == 49) {
            setAirData0x31();
        } else {
            if (i != 50) {
                return;
            }
            setVehicleInfo0x32();
        }
    }

    private void set0x26CarSelect() {
        if (this.mCanBusInfoInt[2] == 3) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_97_car"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_97_car", "_97_car"), Integer.valueOf(getCar(this.mCanBusInfoInt[3]))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setTireInfo0x48() {
        GeneralTireData.isHaveSpareTire = false;
        String[] strArr = this.FL;
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        strArr[0] = sb.append(((iArr[4] * 256) + iArr[5]) / 10.0f).append(" Kpq").toString();
        String[] strArr2 = this.FR;
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        strArr2[0] = sb2.append(((iArr2[6] * 256) + iArr2[7]) / 10.0f).append(" Kpa").toString();
        String[] strArr3 = this.RL;
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        strArr3[0] = sb3.append(((iArr3[8] * 256) + iArr3[9]) / 10.0f).append(" Kpa").toString();
        String[] strArr4 = this.RR;
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        strArr4[0] = sb4.append(((iArr4[10] * 256) + iArr4[11]) / 10.0f).append(" Kpa").toString();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, 0, this.FL));
        arrayList.add(new TireUpdateEntity(1, 0, this.FR));
        arrayList.add(new TireUpdateEntity(2, 0, this.RL));
        arrayList.add(new TireUpdateEntity(3, 0, this.RR));
        GeneralTireData.dataList = arrayList;
        updateTirePressureActivity(null);
    }

    private void setFuelConsumptionMileageInfo() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        String string = sb.append((iArr[4] * 256) + iArr[5]).append("").toString();
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        String string2 = sb2.append((((iArr2[6] << 16) + (iArr2[7] << 8)) + iArr2[8]) / 10.0f).append("").toString();
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("S97_recharge_mileage", string)));
        arrayList.add(checkDriveEntity(helperSetDriveDataValue("S97_total_mileage", string2)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setKeyTrack0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            wheelKeyClick(0);
        } else if (i == 1) {
            wheelKeyClick(7);
        } else if (i == 2) {
            wheelKeyClick(8);
        } else if (i == 3) {
            wheelKeyClick(3);
        } else if (i == 5) {
            wheelKeyClick(HotKeyConstant.K_UP_PICKUP);
        } else if (i == 6) {
            wheelKeyClick(HotKeyConstant.K_DOWN_HANGUP);
        } else if (i == 23) {
            wheelKeyClick(139);
        } else if (i == 40) {
            wheelKeyClick(HotKeyConstant.K_SPEECH);
        } else if (i == 48) {
            startMainActivity(this.mContext);
            playBeep();
        } else if (i != 49) {
            switch (i) {
                case 8:
                    wheelKeyClick(48);
                    break;
                case 9:
                    wheelKeyClick(47);
                    break;
                case 10:
                    wheelKeyClick(14);
                    break;
                case 11:
                    wheelKeyClick(15);
                    break;
                case 12:
                    wheelKeyClick(2);
                    break;
                case 13:
                    wheelKeyClick(45);
                    break;
                case 14:
                    wheelKeyClick(46);
                    break;
                case 15:
                    wheelKeyClick(49);
                    break;
            }
        } else {
            wheelKeyClick(52);
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorData0x12() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        this.mLeftFrontRec = boolBit7;
        GeneralDoorData.isLeftFrontDoorOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        this.mRightFrontRec = boolBit6;
        GeneralDoorData.isRightFrontDoorOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.mLeftRearRec = boolBit5;
        GeneralDoorData.isLeftRearDoorOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        this.mRightRearRec = boolBit4;
        GeneralDoorData.isRightRearDoorOpen = boolBit4;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setPanelKey0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 0) {
            panelKeyClick(0, iArr[3]);
            return;
        }
        if (i == 1) {
            panelKeyClick(HotKeyConstant.K_SLEEP, iArr[3]);
            return;
        }
        if (i == 2) {
            panelKeyClick(21, iArr[3]);
            return;
        }
        if (i == 3) {
            panelKeyClick(20, iArr[3]);
            return;
        }
        if (i == 17) {
            panelKeyClick(31, iArr[3]);
            return;
        }
        if (i == 45) {
            panelKeyClick(76, iArr[3]);
        } else if (i == 63) {
            panelKeyClick(151, iArr[3]);
        } else {
            if (i != 64) {
                return;
            }
            panelKeyClick(1, iArr[3]);
        }
    }

    private void setPanelKnob0x22() {
        if (this.mCanBusInfoInt[2] == 1) {
            if (this.mCanBusInfoByte[3] > 0) {
                realKeyClick3_1(7);
            }
            if (this.mCanBusInfoByte[3] < 0) {
                realKeyClick3_1(8);
            }
        }
        if (this.mCanBusInfoInt[2] == 2) {
            if (this.mCanBusInfoByte[3] > 0) {
                realKeyClick3_2(48);
            }
            if (this.mCanBusInfoByte[3] < 0) {
                realKeyClick3_2(47);
            }
        }
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(9, iArr[2] + 1, iArr[3] + 1, iArr[4] + 1, iArr[5] + 1);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(9, iArr2[6] + 1, iArr2[7] + 1, iArr2[8] + 1, iArr2[9] + 1);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            SharePreUtil.setBoolValue(this.mContext, SHARE_97_RADAR_SWITCH, this.mCanBusInfoInt[12] == 1);
        }
    }

    private void setAirData0x31() {
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 13);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 3;
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.max_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_auto_wind = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        GeneralAirData.center_wheel = intFromByteWithBit == 0 ? "" : "Auto " + intFromByteWithBit;
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        if (!DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            int i = this.mCanBusInfoInt[6];
            if (i != 10) {
                switch (i) {
                    case 2:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 3:
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                    case 4:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                    case 5:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                    case 6:
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                    case 7:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                }
            } else {
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setVehicleInfo0x32() {
        if (isDriveDataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[4] * 256) + iArr[5]).append(" rpm").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, sb2.append((iArr2[6] * 256) + iArr2[7]).append(" km/h").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr3 = this.mCanBusInfoInt;
            updateSpeedInfo((iArr3[6] * 256) + iArr3[7]);
        }
    }

    private void setUnitData0x68() {
        if (isUnitDataChange()) {
            SharePreUtil.setBoolValue(this.mContext, SHARE_97_TEMPERATURE_UNIT, DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setLightData0x61() {
        if (isLightDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(this.mCanBusInfoInt[4])));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bArr = {22, -53};
        byte[] bArr2 = new byte[10];
        bArr2[0] = 0;
        bArr2[1] = (byte) i8;
        bArr2[2] = (byte) i6;
        bArr2[3] = 0;
        bArr2[4] = 0;
        bArr2[5] = (byte) (!z ? 0 : 1);
        bArr2[6] = (byte) i2;
        bArr2[7] = (byte) i3;
        bArr2[8] = (byte) i4;
        bArr2[9] = 0;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr, bArr2));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -46, (byte) 0));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        String str5 = new DecimalFormat("00").format(i);
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 2, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            str4 = (str5 + " ") + str2;
        } else {
            for (int i3 = 0; i3 < 7 - str2.length(); i3++) {
                str5 = str5 + " ";
            }
            str4 = str5 + new DecimalFormat("0.0").format(Float.valueOf(str2));
        }
        while (str4.length() < 12) {
            str4 = str4 + " ";
        }
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -111, allBandTypeData}, str4.getBytes());
        CanbusMsgSender.sendMsg(bArrByteMerger);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -111, 10}, ("    " + new DecimalFormat("00").format(getMinute(i)) + new DecimalFormat("00").format(getSecond(i)) + "    ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        byte[] bArr2 = {22, -51, (byte) (z ? i : 6), 0, 0};
        if (i == 0) {
            bArr = new byte[]{32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr2, DataHandleUtils.phoneNum2UnicodeBig(bArr)), 29));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.byteMerger(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b7 * 256) + i, 0, 999)) + "      " + new DecimalFormat("000").format(DataHandleUtils.rangeNumber(i2, 0, 999))).getBytes()));
        this.mId3s[0].setId3(str4);
        this.mId3s[1].setId3(str5);
        this.mId3s[2].setId3(str6);
        reportID3Info(this.mId3s, false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, -111, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (new DecimalFormat("000").format(DataHandleUtils.rangeNumber((b6 * 256) + i, 0, 999)) + "      " + new DecimalFormat("000").format(DataHandleUtils.rangeNumber(i2, 0, 999))).getBytes());
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), bArrByteMerger);
        CanbusMsgSender.sendMsg(bArrByteMerger);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), DataHandleUtils.setReportHiworldSrcInfoData((byte) -111, (byte) -123));
        CanbusMsgSender.sendMsg(new byte[]{22, -111, -123});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mId3s[0].setId3("");
        this.mId3s[1].setId3("");
        this.mId3s[2].setId3("");
        reportID3Info(this.mId3s, true);
    }

    private void initId3() {
        this.mId3s = new ID3[]{new ID3(146, "Unicode", 34), new ID3(147, "Unicode", 34), new ID3(148, "Unicode", 34)};
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void panelKeyClick(int i, int i2) {
        realKeyLongClick1(this.mContext, i, i2);
    }

    private void realKeyClick3_1(int i) {
        realKeyClick3_1(this.mContext, i, this.mCanBusInfoInt[2], Math.abs((int) this.mCanBusInfoByte[3]));
    }

    private void realKeyClick3_2(int i) {
        realKeyClick3_2(this.mContext, i, this.mCanBusInfoInt[2], Math.abs((int) this.mCanBusInfoByte[3]));
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (i == length) {
            return Arrays.copyOf(bArr, length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void setOutDoorTem() {
        if (isOutDoorTempChange()) {
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        float fRangeNumber = (DataHandleUtils.rangeNumber(this.mCanBusInfoInt[13], 0, com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE) * 0.5f) - 40.0f;
        String tempUnitC = getTempUnitC(this.mContext);
        if (SharePreUtil.getBoolValue(this.mContext, SHARE_97_TEMPERATURE_UNIT, false)) {
            fRangeNumber = ((fRangeNumber * 9.0f) / 5.0f) + 32.0f;
            tempUnitC = getTempUnitF(this.mContext);
        }
        return new DecimalFormat("0.0").format(fRangeNumber) + tempUnitC;
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveAirTemp(int i) {
        String tempUnitF = SharePreUtil.getBoolValue(this.mContext, SHARE_97_TEMPERATURE_UNIT, false) ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext);
        return SharePreUtil.getBoolValue(this.mContext, "share_air_conditioner_type", true) ? i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + tempUnitF : i == 255 ? "LO" : i == 254 ? "HI" : ((90 - i) * 0.5f) + tempUnitF;
    }

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return b4;
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
                return b3;
            default:
                return (byte) 0;
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._97.MsgMgr$1] */
    private void reportID3Info(final ID3[] id3Arr, final boolean z) {
        new Thread() { // from class: com.hzbhd.canbus.car._97.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    ID3[] id3Arr2 = id3Arr;
                    if (id3Arr2.length > 0) {
                        if (id3Arr2[0].isId3Change()) {
                            if (z) {
                                sleep(900L);
                            }
                            for (ID3 id3 : id3Arr) {
                                sleep(100L);
                                MsgMgr.this.reportID3InfoFinal((byte) id3.getHead(), id3.getId3(), id3.getCharsetName(), id3.getLength());
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2, int i) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVersionInfoNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[8], iArr[9]};
        if (Arrays.equals(this.mTrackDataNow, iArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(iArr2, 2);
        return true;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isOutDoorTempChange() {
        int i = this.mOutDoorTempDataNow;
        int i2 = this.mCanBusInfoInt[13];
        if (i == i2) {
            return false;
        }
        this.mOutDoorTempDataNow = i2;
        return true;
    }

    private boolean isUnitDataChange() {
        int i = this.mUnitDataNow;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mUnitDataNow = i2;
        return true;
    }

    private boolean isDriveDataChange() {
        if (Arrays.equals(this.mDriveDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isLightDataChange() {
        if (Arrays.equals(this.mLightDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mLightDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
