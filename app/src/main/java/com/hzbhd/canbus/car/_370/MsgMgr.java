package com.hzbhd.canbus.car._370;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_KEY_253_FRONT_RADAR_ENABLE = "share_key_253_front_radar_enable";
    static final String SHARE_KEY_253_REAR_RADAR_ENABLE = "share_key_253_rear_radar_enable";
    static final int _1111_AMPLIFIER_BAND_MAX = 1;
    static final int _1111_AMPLIFIER_HALF_MAX = 10;
    private int differentId;
    private int eachId;
    private int[] m0xAEData;
    private int mAmplifierSwitch;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private TimerUtil mTimerUtil = new TimerUtil();
    int rdm;
    int rpt;
    private UiMgr uiMgr;

    private int getSwapVal(int i) {
        return (i < 1 || i != 10) ? 0 : 1;
    }

    private int resovleDelay(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 30) {
            return 1;
        }
        if (i == 60) {
            return 2;
        }
        return i == 90 ? 3 : 0;
    }

    private int resovleDelay2(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 3) {
            return 1;
        }
        if (i == 20) {
            return 2;
        }
        return i == 40 ? 3 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
        sendtype();
        initAmplifier(this.mContext);
    }

    void updateAmplifierSwitch(int i) {
        this.mAmplifierSwitch = i;
    }

    private void initAmplifier(Context context) {
        getAmplifierData(context, this.mCanId, getUiMgr(context).getAmplifierPageUiSet(context));
        final byte[][] bArr = {new byte[]{22, -124, 2, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 3, (byte) (10 - GeneralAmplifierData.frontRear)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.leftRight + 10)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandBass + 1)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandTreble + 1)}, new byte[]{22, -124, 7, (byte) (GeneralAmplifierData.bandMiddle + 1)}};
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._370.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    CanbusMsgSender.sendMsg(bArr2[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 100L, 133L);
        this.mAmplifierSwitch = 1;
        this.mTimerUtil.stopTimer();
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._370.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                CanbusMsgSender.sendMsg(new byte[]{22, -124, 1, (byte) MsgMgr.this.mAmplifierSwitch});
            }
        }, 100L, 1000L);
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
        if (i == 41) {
            setTrackInfo0x29();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setCenterControlInfo0x40();
            return;
        }
        if (i == 80) {
            if (this.eachId != 1) {
                return;
            }
            setCurrentRotatingSpeed0x50();
            return;
        }
        if (i == 103) {
            setTurnLight0x67();
            return;
        }
        if (i == 22) {
            if (this.eachId != 1) {
                return;
            }
            setSpeedInfo0x16();
            return;
        }
        if (i == 23) {
            if (this.eachId == 2) {
                return;
            }
            setAmplifier0x17();
            return;
        }
        if (i == 38) {
            setCarbodytime0x26();
            return;
        }
        if (i == 39) {
            if (this.eachId == 2) {
                return;
            }
            setOutDoortempInfo0x27();
            return;
        }
        if (i == 66) {
            if (this.eachId != 1) {
                return;
            }
            setCDstatus0x42();
            return;
        }
        if (i != 67) {
            switch (i) {
                case 32:
                    setWheelKeyInfo();
                    break;
                case 33:
                    if (this.eachId != 2) {
                        setAirInfo0x21();
                        break;
                    }
                    break;
                case 34:
                    setRearRaderInfo0x22();
                    break;
                case 35:
                    setFrontRaderInfo0x23();
                    break;
                case 36:
                    setDoorInfo0x24();
                    break;
            }
            return;
        }
        if (this.eachId != 1) {
            return;
        }
        setCDPlayInfo0x43();
    }

    private void setBacklightInfo0x14() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_BackLight"), resolveBackLight(this.mCanBusInfoInt[2])));
        int i = this.mCanBusInfoInt[2];
        if (i > 0 && i <= 33) {
            setBacklightLevel(1);
        } else if (i > 33 && i <= 66) {
            setBacklightLevel(2);
        } else if (i > 66 && i <= 100) {
            setBacklightLevel(3);
        } else if (i > 100 && i <= 133) {
            setBacklightLevel(4);
        } else if (i > 133 && i <= 167) {
            setBacklightLevel(5);
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarbodytime0x26() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Time");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resolveTime(iArr[2], iArr[3], iArr[4], iArr[5], iArr[6], iArr[7])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -57, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7});
        sendtype();
    }

    private void setOutDoortempInfo0x27() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[3];
        String str = "——";
        if (i == 0) {
            if (iArr[2] != 0) {
                str = ((this.mCanBusInfoInt[2] / 2) - 39) + getTempUnitC(this.mContext);
            }
        } else if (i == 1) {
            if (iArr[2] != 0) {
                str = ((((r0 / 2) - 39) * 1.8d) + 32.0d) + getTempUnitF(this.mContext);
            }
        } else {
            str = "温度出错了";
        }
        updateOutDoorTemp(this.mContext, str);
    }

    private void setAmplifier0x17() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[3];
        GeneralAmplifierData.frontRear = (-this.mCanBusInfoInt[4]) + 10;
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        GeneralAmplifierData.leftRight = -((-iArr[5]) + 10);
        int i2 = this.mCanBusInfoInt[5];
        GeneralAmplifierData.bandBass = r0[6] - 1;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[8] - 1;
        updateAmplifierActivity(null);
    }

    private void setWheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
        }
        if (i == 1) {
            buttonKey(7);
            return;
        }
        if (i == 2) {
            buttonKey(8);
            return;
        }
        if (i == 3) {
            buttonKey(20);
            return;
        }
        if (i == 4) {
            buttonKey(21);
            return;
        }
        if (i != 5) {
            if (i != 32) {
                if (i != 33) {
                    switch (i) {
                        case 7:
                            buttonKey(2);
                            break;
                        case 8:
                            buttonKey(HotKeyConstant.K_SPEECH);
                            break;
                        case 9:
                            buttonKey(HotKeyConstant.K_1_PICKUP);
                            break;
                        case 10:
                            buttonKey(HotKeyConstant.K_2_HANGUP);
                            break;
                        default:
                            switch (i) {
                                case 18:
                                case 22:
                                    buttonKey(49);
                                    break;
                                case 21:
                                    buttonKey(50);
                                    break;
                                case 23:
                                    buttonKey(7);
                                    break;
                                case 24:
                                    buttonKey(8);
                                    break;
                                case 25:
                                    buttonKey(45);
                                    break;
                            }
                    }
                    return;
                }
                buttonKey(HotKeyConstant.K_SLEEP);
                return;
            }
            buttonKey(46);
            return;
        }
        buttonKey(3);
    }

    private void setAirInfo0x21() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        int i2 = iArr[3];
        int i3 = iArr[4];
        int i4 = iArr[5];
        int i5 = iArr[6];
        int i6 = iArr[7];
        GeneralAirData.power = DataHandleUtils.getBoolBit7(i);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(i);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(i);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(i);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(i);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(i2);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(i2);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(i2);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(i2, 0, 4);
        GeneralAirData.front_left_temperature = resolvetemp(i3, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2));
        GeneralAirData.front_right_temperature = resolvetemp(i4, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]), DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2));
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(i5);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(i5);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(i5);
        GeneralAirData.eco = DataHandleUtils.getBoolBit4(i5);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(i5);
        GeneralAirData.rear = DataHandleUtils.getBoolBit2(i5);
        GeneralAirData.auto_manual = !DataHandleUtils.getBoolBit1(i5);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(i5);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i6, 4, 3);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(i6, 0, 3);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRaderInfo0x22() {
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

    private void setFrontRaderInfo0x23() {
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

    private void setDoorInfo0x24() {
        int i = this.mCanBusInfoInt[2];
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(i);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(i);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(i);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(i);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(i);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(i);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void setTrackInfo0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
        updateParkUi(null, this.mContext);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCenterControlInfo0x40() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "language_setup"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "language_setup", "language_setup"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 1) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Measure"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Fuel_consumption"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Mileage"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Temp_Unit"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Unit"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Unit", "_370_Air_Pressure"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
        } else if (i == 32) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_10"), Integer.valueOf(resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 7)))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_32"), Integer.valueOf(resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 7)))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data1_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "_112_rear_mirror_dimmer"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data2_76"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        } else if (i == 33) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "light_info"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "light_info", "hiworld_jeep_123_0x62_data3_765"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
        } else if (i == 48) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x62_data3_4_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_65"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "hiworld_jeep_123_0x60_data1_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "_370_Remote_Start"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x60_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x60_title", "_370_Remote_unlock"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1))));
        } else if (i == 64) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Engine_Off_Light_delay"), Integer.valueOf(resovleDelay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8)))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Engine_Off_Power_delay"), Integer.valueOf(resovleDelay2(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8)))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Engine_off"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Engine_off", "_370_Driver_Seat_Convenient"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
        } else if (i == 144) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Auto_Open_Comfortable_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Auto_Open_Comfortable_System", "_370_Start_Open_Heat"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Auto_Open_Comfortable_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Auto_Open_Comfortable_System", "_370_Remote_Start_Reminder"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        } else if (i == 160) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_76"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data5_32"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_32"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data3_54"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_10"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "hiworld_jeep_123_0x43_title"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "hiworld_jeep_123_0x43_title", "hiworld_jeep_123_0x43_data4_5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        } else if (i == 176) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_direction"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_run_calibration"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "compass"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "compass", "compass_deviation"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) - 1));
        } else if (i == 192) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Brake"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Brake", "_370_Automatic_parking_brake"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_370_Parking_service"), resolveBrakeService(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 3))));
        } else if (i == 208) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Sound_Settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Sound_Settings", "_370_Speed_adjustment_volume"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_370_Sound_Settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_370_Sound_Settings", "_370_Surround_Sound"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
    }

    private void setCDstatus0x42() {
        if (is0xAEDataChange()) {
            GeneralOriginalCarDeviceData.cdStatus = "CD";
            GeneralOriginalCarDeviceData.runningState = getRunStatus(this.mCanBusInfoInt[2]);
            if (this.mCanBusInfoInt[2] == 5 && GeneralOriginalCarDeviceData.songList != null) {
                GeneralOriginalCarDeviceData.songList.clear();
            }
            GeneralOriginalCarDeviceData.rpt = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                this.rpt = 1;
            } else {
                this.rpt = 0;
            }
            GeneralOriginalCarDeviceData.rdm = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
                this.rdm = 1;
            } else {
                this.rdm = 0;
            }
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCDPlayInfo0x43() {
        if (is0xAEDataChange()) {
            GeneralOriginalCarDeviceData.cdStatus = "CD";
            DecimalFormat decimalFormat = new DecimalFormat("00");
            int[] iArr = this.mCanBusInfoInt;
            int i = (iArr[7] * 3600) + (iArr[8] * 60) + iArr[9];
            GeneralOriginalCarDeviceData.startTime = decimalFormat.format(this.mCanBusInfoInt[7]) + ":" + decimalFormat.format(this.mCanBusInfoInt[8]) + ":" + decimalFormat.format(this.mCanBusInfoInt[9]);
            int[] iArr2 = this.mCanBusInfoInt;
            int i2 = (iArr2[4] * 3600) + (iArr2[5] * 60) + iArr2[6];
            GeneralOriginalCarDeviceData.endTime = decimalFormat.format(this.mCanBusInfoInt[4]) + ":" + decimalFormat.format(this.mCanBusInfoInt[5]) + ":" + decimalFormat.format(this.mCanBusInfoInt[6]);
            if (i2 == 0) {
                GeneralOriginalCarDeviceData.progress = 0;
            } else {
                GeneralOriginalCarDeviceData.progress = (i * 100) / i2;
            }
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, sb.append((iArr3[10] * 256) + iArr3[11]).append("").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, sb2.append((iArr4[2] * 256) + iArr4[3]).append("").toString()));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCurrentRotatingSpeed0x50() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resolveRotating0x50(iArr[2], iArr[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSpeedInfo0x16() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, resolveSpeed0x50(iArr[2], iArr[3])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void setTurnLight0x67() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "mazda_binary_car_set77"), resolveTurnLight(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolvetemp(int i, boolean z, boolean z2, int i2) {
        if (z2) {
            if (i2 == 0 || i2 == 3) {
                return this.mContext.getResources().getString(R.string._370_Nomral);
            }
            if (i2 == 1) {
                return this.mContext.getResources().getString(R.string._370_Cold);
            }
            if (i2 == 2) {
                return this.mContext.getResources().getString(R.string._370_Heat);
            }
        } else {
            if (i == 0) {
                return "LOW";
            }
            if (i == 255) {
                return "HIGH";
            }
            if (!z) {
                return (i / 2.0f) + getTempUnitC(this.mContext);
            }
            if (z) {
                return i + getTempUnitF(this.mContext);
            }
        }
        return "";
    }

    private String resolveBackLight(int i) {
        return i == 0 ? "Min" : i == 167 ? "Max" : i + "";
    }

    private String resolveBrakeService(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_1);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_2);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_3);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_4);
        }
        if (i == 4) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_5);
        }
        if (i == 5) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_6);
        }
        if (i == 6) {
            return this.mContext.getResources().getString(R.string._370_Parking_service_7);
        }
        return i == 7 ? this.mContext.getResources().getString(R.string._370_Parking_service_8) : "";
    }

    private String resolveRotating0x50(int i, int i2) {
        return (i + (i2 * 256)) + "rmp";
    }

    private String resolveSpeed0x50(int i, int i2) {
        return ((i + (i2 * 256)) / 16) + "km/h";
    }

    private String resolveTurnLight(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string.mazda_binary_car_set77_1);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string.mazda_binary_car_set77_2);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string.mazda_binary_car_set77_3);
        }
        return i == 3 ? this.mContext.getResources().getString(R.string.mazda_binary_car_set77_4) : "";
    }

    private String resolveTime(int i, int i2, int i3, int i4, int i5, int i6) {
        int i7;
        int[] iArr = {i, i2, i3, i4, i5, i6};
        String[] strArr = {"", "", "", "", "", ""};
        if (DataHandleUtils.getBoolBit7(i4) && (i7 = iArr[3]) > 23) {
            iArr[3] = i7 - 128;
        }
        for (int i8 = 0; i8 < 6; i8++) {
            if (iArr[i8] < 10) {
                strArr[i8] = "0" + iArr[i8];
            } else {
                strArr[i8] = iArr[i8] + "";
            }
        }
        return "20" + strArr[0] + "-" + strArr[1] + "-" + strArr[2] + "-" + strArr[3] + "-" + strArr[4] + "-" + strArr[5];
    }

    private String getRunStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "_370_divice_status_" + i);
    }

    private String getBandUnit(String str) {
        return (!str.contains("FM") && str.contains("AM")) ? " KKz" : " MKz";
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

    private boolean is0xAEDataChange() {
        if (Arrays.equals(this.m0xAEData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xAEData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        String str4 = str + " " + str2 + getBandUnit(str);
        byte[] bArrByteMerger = {22, -107, 1};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, DataHandleUtils.exceptBOMHead(str4.getBytes("Unicode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() throws UnsupportedEncodingException {
        super.auxInInfoChange();
        byte[] bytes = new byte[36];
        try {
            bytes = DataHandleUtils.makeMediaInfoCenteredInBytes(18, "AUX").getBytes("unicode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), DataHandleUtils.byteMerger(new byte[]{22, -64, 7}, DataHandleUtils.exceptBOMHead(bytes)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArrByteMerger = {22, -64, 8};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, DataHandleUtils.exceptBOMHead(((((b7 & 255) * 256) + i) + "/" + i2 + "  " + str2 + ":" + str3).getBytes("Unicode")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        String str4 = i4 + "/" + i5 + "  " + decimalFormat.format(getHour(i)) + ":" + decimalFormat.format(getMinute(i)) + ":" + decimalFormat.format(getSecond(i));
        byte[] bArrByteMerger = {22, -64, 16};
        try {
            bArrByteMerger = DataHandleUtils.byteMerger(bArrByteMerger, str4.getBytes("Unicode"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), DataHandleUtils.makeBytesFixedLength(bArrByteMerger, 39));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        byte[] bArrByteMerger = {22, -64, 8};
        try {
            bArrByteMerger = Util.byteMerger(bArrByteMerger, Util.exceptBOMHead(((((b6 & 255) * 256) + i) + "/" + i2 + "  " + str2 + ":" + str3 + ":" + str4).getBytes("Unicode")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), Util.makeBytesFixedLength(bArrByteMerger, 39));
    }

    private void sendtype() {
        if (this.differentId == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 1});
        }
        if (this.differentId == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, -30, 0, 0});
        }
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
