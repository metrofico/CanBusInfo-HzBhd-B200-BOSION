package com.hzbhd.canbus.car._210;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.MainActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
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
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.view.ParkAssistFloatView;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static boolean angleWide = false;
    private static boolean isDoorFirst = true;
    private static boolean isParkingFirst = true;
    private static int outDoorTemp;
    static boolean overlook;
    private static int rearTemp;
    private int ford_park_assist_info;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcInfoCopy;
    private int mCanId;
    private Context mContext;
    private byte[] mDoorInfoCopy;
    private ParkAssistFloatView mParkAssistFloatView;
    private byte[] mParkingInfoCopy;
    private boolean mIsAirFirst = true;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_REAR_AIR_TEMP_CHANGE_FORD = "is_rear_air_temp_change_ford";
    private final String IS_REAR_AIR_WIND_LV_CHANGE = "is_rear_air_wind_lv_change";
    private final String IS_REAR_AIR_POWER = "is_rear_air_power";
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    private final String IS_REAR_LOCK = "is_rear_lock";

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -111, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, -108});
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 3});
        updateOrignalSetting();
        initAmplifierData(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final byte[][] bArr = {new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume}, new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -83, 4, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -83, 5, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -83, 6, (byte) GeneralAmplifierData.bandTreble}};
        final TimerHelper timerHelper = new TimerHelper();
        timerHelper.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._210.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i < bArr2.length) {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                } else {
                    timerHelper.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            keyControl0x11();
            setTrackInfo();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 18) {
            if (isDoorMsgReturn(bArr)) {
                return;
            }
            setCarDoorInfo();
            setCarStatusInfo();
            return;
        }
        if (i == 33) {
            setPanelBtnInfo();
            dccOffOnCtrl(bArr);
            return;
        }
        if (i == 34) {
            setOriginalPanel();
            return;
        }
        if (i == 38) {
            setCarType();
            return;
        }
        if (i == 65) {
            setFrontRearRadar();
            return;
        }
        if (i == 97) {
            if (isParkingMsgReturn(bArr)) {
                return;
            }
            setEnvironmentLight();
            refreshFordParkAssistFlowView(this.mCanBusInfoInt);
            return;
        }
        if (i == 148) {
            languageSettingInfo();
            return;
        }
        if (i == 166) {
            setAmplifierData();
            return;
        }
        if (i == 224) {
            setCarVolumeCtrl();
            return;
        }
        if (i == 232) {
            setReversingVideo();
            setOriginalCameraStatusInfo();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 49) {
            setFrontAirData();
            return;
        }
        if (i == 50) {
            setCarStatus();
        } else if (i == 103) {
            setLightSetting();
        } else {
            if (i != 104) {
                return;
            }
            setTipsInfo();
        }
    }

    private void keyControl0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 8) {
            realKeyClick(48);
            return;
        }
        if (i == 9) {
            realKeyClick(47);
            return;
        }
        if (i == 98) {
            realKeyClick(17);
            return;
        }
        if (i != 101) {
            switch (i) {
                case 0:
                    realKeyClick(0);
                    break;
                case 1:
                    realKeyClick(7);
                    break;
                case 2:
                    realKeyClick(8);
                    break;
                case 3:
                    realKeyClick(3);
                    break;
                case 4:
                    realKeyClick(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    realKeyClick(HotKeyConstant.K_NEXT_HANGUP);
                    break;
                case 6:
                    realKeyClick(206);
                    break;
                default:
                    switch (i) {
                        case 13:
                            realKeyClick(45);
                            break;
                        case 14:
                            realKeyClick(46);
                            break;
                        case 15:
                            realKeyClick(49);
                            break;
                    }
            }
            return;
        }
        realKeyClick(31);
    }

    private void setTrackInfo() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void setFrontAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_left_auto_wind = false;
        GeneralAirData.front_right_auto_wind = false;
        if (i == 1) {
            GeneralAirData.front_left_auto_wind = true;
            GeneralAirData.front_right_auto_wind = true;
        } else if (i == 2) {
            GeneralAirData.front_defog = true;
        } else if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (i == 6) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
        } else {
            switch (i) {
                case 11:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 12:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_lock = !DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[12];
        rearTemp = i2;
        outDoorTemp = iArr[13];
        GeneralAirData.rear_temperature = resolveRearTemp(i2);
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[11];
        boolean zIsOnlyRearAirDataChange = isOnlyRearAirDataChange();
        boolean zIsOnlyOutDoorDataChange = isOnlyOutDoorDataChange();
        if (zIsOnlyRearAirDataChange && !isOnlyOutDoorDataChange()) {
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_temp_change_ford", rearTemp);
            SharePreUtil.setIntValue(this.mContext, "is_rear_air_wind_lv_change", GeneralAirData.rear_wind_level);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_air_power", GeneralAirData.rear_power);
            SharePreUtil.setBoolValue(this.mContext, "is_rear_lock", GeneralAirData.rear_lock);
            updateAirActivity(this.mContext, 1002);
        }
        if (!zIsOnlyRearAirDataChange && !zIsOnlyOutDoorDataChange) {
            updateAirActivity(this.mContext, 1001);
        }
        if (zIsOnlyOutDoorDataChange) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        }
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        if (GeneralAirData.fahrenheit_celsius) {
            return ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
        }
        return ((float) ((((i * 0.5d) - 40.0d) * 1.8d) + 32.0d)) + getTempUnitF(this.mContext);
    }

    private void setOriginalCameraStatusInfo() {
        angleWide = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        overlook = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRearRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
        int[] iArr3 = this.mCanBusInfoInt;
        RadarInfoUtil.setProbeRadarLocationData(7, iArr3[10], iArr3[11], 0, 0);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void languageSettingInfo() {
        int i = this.mCanBusInfoInt[2];
        int i2 = i != 27 ? i != 0 ? i - 1 : 0 : 2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(i2)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTipsInfo() {
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLightSetting() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit(2, this.mCanBusInfoInt[3])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setReversingVideo() {
        forceReverse(this.mContext, this.mCanBusInfoInt[5] == 1);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(this.mCanBusInfoInt[4])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setEnvironmentLight() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCarStatus() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        byte[] bArr = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 2, sb.append(bytesToLong(new byte[]{bArr[5], bArr[4]})).append("Rpm").toString()));
        StringBuilder sb2 = new StringBuilder();
        byte[] bArr2 = this.mCanBusInfoByte;
        arrayList.add(new DriverUpdateEntity(0, 3, sb2.append(bytesToLong(new byte[]{bArr2[7], bArr2[6]})).append("Km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarDoorInfo() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]) && isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            updateDoorView(this.mContext);
        }
    }

    private void setPanelBtnInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(0);
            return;
        }
        if (i == 1) {
            realKeyClick2(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 2) {
            realKeyClick2(45);
            return;
        }
        if (i == 3) {
            realKeyClick2(46);
            return;
        }
        if (i == 36) {
            realKeyClick2(59);
        } else if (i == 60) {
            realKeyClick2(48);
        } else {
            if (i != 61) {
                return;
            }
            realKeyClick2(47);
        }
    }

    private void dccOffOnCtrl(byte[] bArr) {
        if (this.mCanBusInfoInt[2] != 18 || isSwcMsgReturn(bArr)) {
            return;
        }
        Intent intent = new Intent(this.mContext, (Class<?>) MainActivity.class);
        intent.addFlags(268435456);
        this.mContext.startActivity(intent);
    }

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void setOriginalPanel() {
        int i = 0;
        if (this.mCanBusInfoInt[2] == 1) {
            if (this.mCanBusInfoByte[3] > 0) {
                while (i < this.mCanBusInfoByte[3]) {
                    realKeyClick1(7);
                    i++;
                }
                return;
            } else {
                while (this.mCanBusInfoByte[3] < i) {
                    realKeyClick1(8);
                    i--;
                }
                return;
            }
        }
        if (this.mCanBusInfoByte[3] > 0) {
            while (i < this.mCanBusInfoByte[3]) {
                realKeyClick1(46);
                i++;
            }
        } else {
            while (this.mCanBusInfoByte[3] < i) {
                realKeyClick1(45);
                i--;
            }
        }
    }

    private void setCarStatusInfo() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[2];
        String string2 = "";
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string._96_flame_out);
        } else if (i == 1) {
            string = this.mContext.getResources().getString(R.string._96_acc_on_mode);
        } else if (i == 2) {
            string = this.mContext.getResources().getString(R.string._96_operating_mode);
        } else if (i == 3) {
            string = this.mContext.getResources().getString(R.string._96_fire_mode);
        } else {
            string = i != 255 ? "" : this.mContext.getResources().getString(R.string._96_invalid_mode);
        }
        int i2 = this.mCanBusInfoInt[3];
        if (i2 == 0) {
            string2 = this.mContext.getResources().getString(R.string.invalid);
        } else if (i2 == 1) {
            string2 = "P";
        } else if (i2 == 2) {
            string2 = "N";
        } else if (i2 == 3) {
            string2 = "R";
        } else if (i2 == 4) {
            string2 = "D";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        arrayList.add(new DriverUpdateEntity(0, 1, string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarVolumeCtrl() {
        try {
            int[] iArr = this.mCanBusInfoInt;
            switch (iArr[2]) {
                case 1:
                    int i = iArr[3];
                    if (i == 1) {
                        changeBandFm1();
                        break;
                    } else if (i == 2) {
                        changeBandFm2();
                        break;
                    } else if (i == 3) {
                        changeBandAm1();
                        break;
                    } else if (i == 4) {
                        changeBandAm2();
                        break;
                    } else {
                        break;
                    }
                case 2:
                    changeBandAm1();
                    SystemClock.sleep(500L);
                    FutureUtil.instance.setCurrentFreq((this.mCanBusInfoByte[3] & 255) + "" + (this.mCanBusInfoByte[4] & 255) + "");
                    break;
                case 3:
                    changeBandFm1();
                    SystemClock.sleep(500L);
                    FutureUtil.instance.setCurrentFreq((this.mCanBusInfoByte[3] & 255) + "." + (this.mCanBusInfoByte[4] & 255));
                    break;
                case 4:
                    playPresetChann((this.mCanBusInfoByte[3] - 1) & 255);
                    break;
                case 5:
                    if (iArr[3] == 1) {
                        realKeyClick(this.mContext, 45);
                        break;
                    } else {
                        realKeyClick(this.mContext, 46);
                        break;
                    }
                case 6:
                    FutureUtil.instance.gotoTrack(this.mCanBusInfoInt[3]);
                    break;
                case 7:
                    if (iArr[3] == 1) {
                        FutureUtil.instance.playMpeg();
                        break;
                    } else {
                        FutureUtil.instance.pauseMpeg();
                        break;
                    }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void setAmplifierData() {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
            GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
            GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
            GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
            GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
            GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
            saveAmplifierData(this.mContext, this.mCanId);
            updateAmplifierActivity(new Bundle());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarType() {
        ArrayList arrayList = new ArrayList();
        if (this.mCanBusInfoInt[3] == 20) {
            arrayList.add(new DriverUpdateEntity(0, 4, this.mContext.getResources().getString(R.string.ford_car_type1)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        if (254 == i) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return (i * 0.5f) + getTempUnitC(this.mContext);
        }
        return ((int) (i * 0.5f)) + getTempUnitF(this.mContext);
    }

    private String resolveRearTemp(int i) {
        return i == 0 ? "Close" : 1 == i ? "Lo" : 9 == i ? "Hi" : 5 == i ? "Mid" : "" + i;
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen) ? false : true;
    }

    private boolean isOnlyRearAirDataChange() {
        return (SharePreUtil.getIntValue(this.mContext, "is_rear_air_temp_change_ford", 0) == rearTemp && SharePreUtil.getIntValue(this.mContext, "is_rear_air_wind_lv_change", 0) == GeneralAirData.rear_wind_level && SharePreUtil.getBoolValue(this.mContext, "is_rear_air_power", false) == GeneralAirData.rear_power && SharePreUtil.getBoolValue(this.mContext, "is_rear_lock", false) == GeneralAirData.rear_lock) ? false : true;
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mDoorInfoCopy)) {
            return true;
        }
        this.mDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isParkingMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mParkingInfoCopy)) {
            return true;
        }
        this.mParkingInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isParkingFirst) {
            return false;
        }
        isParkingFirst = false;
        return true;
    }

    void updateOrignalSetting() {
        int intValue = SharePreUtil.getIntValue(this.mContext, "_210_park_assess_item", 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(intValue)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return true;
    }

    private void realKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void realKeyClick1(int i) {
        realKeyClick(this.mContext, i);
    }

    private void realKeyClick2(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private long bytesToLong(byte[] bArr) {
        long jPow = 0;
        for (int i = 0; i < bArr.length; i++) {
            jPow = (long) (jPow + ((bArr[i] & 255) * Math.pow(256.0d, i)));
        }
        return jPow;
    }

    private void refreshFordParkAssistFlowView(int[] iArr) {
        final int i = iArr[3];
        if (i == 0 || this.ford_park_assist_info == i) {
            return;
        }
        this.ford_park_assist_info = i;
        if (this.mParkAssistFloatView == null) {
            this.mParkAssistFloatView = new ParkAssistFloatView(this.mContext);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._210.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                Bundle bundle = new Bundle();
                bundle.putByte(ParkAssistFloatView.REFRESH_UI_BUNDLE_KEY, (byte) i);
                MsgMgr.this.mParkAssistFloatView.refreshUi(bundle);
            }
        });
    }

    private void playPresetChann(int i) throws RemoteException {
        FutureUtil.instance.playPresetFreq(i);
        Intent intent = new Intent();
        intent.setAction("com.hzbhd.intent.action.fm");
        this.mContext.startActivity(intent);
    }

    private class TimerHelper {
        private Timer mTimer;
        private TimerTask mTimerTask;

        private TimerHelper() {
        }

        public void startTimer(TimerTask timerTask, long j, long j2) {
            Log.i("TimerUtil", "startTimer: " + this);
            if (timerTask == null) {
                return;
            }
            this.mTimerTask = timerTask;
            if (this.mTimer == null) {
                this.mTimer = new Timer();
            }
            this.mTimer.schedule(this.mTimerTask, j, j2);
        }

        public void stopTimer() {
            Log.i("TimerUtil", "stopTimer: " + this);
            TimerTask timerTask = this.mTimerTask;
            if (timerTask != null) {
                timerTask.cancel();
                this.mTimerTask = null;
            }
            Timer timer = this.mTimer;
            if (timer != null) {
                timer.cancel();
                this.mTimer = null;
            }
        }
    }
}
