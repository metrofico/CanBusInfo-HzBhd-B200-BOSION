package com.hzbhd.canbus.car._294;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Base64;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.Util;
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
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.SystemPropertiesUtils;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;



public class MsgMgr extends AbstractMsgMgr {
    private int FreqInt;
    private byte bandType;
    private byte freqHi;
    private byte freqLo;
    boolean isNeedCanCtrlAmplifier;
    private byte[] mCanInfoByte;
    private int[] mCanInfoInt;
    private Context mContext;
    private Timer mTimer;
    private UiMgr mUiMgr;

    private int getAuto(boolean z, boolean z2) {
        if (z) {
            return 1;
        }
        return z2 ? 2 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 16, -64});
        CanbusMsgSender.sendMsg(new byte[]{22, 16, -62});
        CanbusMsgSender.sendMsg(new byte[]{22, 16, -61});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
        startTask();
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(this.mContext, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._294.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1, 1}, new byte[]{22, -96, 2, (byte) GeneralAmplifierData.frontRear}, new byte[]{22, -96, 1, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -96, 3, (byte) (GeneralAmplifierData.bandBass - 9)}, new byte[]{22, -96, 5, (byte) (GeneralAmplifierData.bandMiddle - 9)}, new byte[]{22, -96, 4, (byte) (GeneralAmplifierData.bandTreble - 9)}, new byte[]{22, -96, 0, (byte) GeneralAmplifierData.volume}}).iterator();

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    private void startTask() {
        final byte[][] bArr = {new byte[]{22, -112, 34}, new byte[]{22, -112, 35}, new byte[]{22, -112, 37}, new byte[]{22, -112, 38}};
        final byte[][] bArr2 = {new byte[]{22, -112, 35}, new byte[]{22, -112, Byte.MAX_VALUE}};
        Timer timer = this.mTimer;
        if (timer == null) {
            Timer timer2 = new Timer();
            this.mTimer = timer2;
            timer2.schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._294.MsgMgr.2
                int i = 0;

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if ("true".equals(SystemPropertiesUtils.get("vendor.backCamera_needshow"))) {
                        byte[][] bArr3 = bArr;
                        CanbusMsgSender.sendMsg(bArr3[this.i % bArr3.length]);
                    } else {
                        byte[][] bArr4 = bArr2;
                        CanbusMsgSender.sendMsg(bArr4[this.i % bArr4.length]);
                    }
                    this.i++;
                }
            }, 0L, 100L);
        } else {
            timer.cancel();
            this.mTimer = null;
            startTask();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanInfoByte = bArr;
        this.mCanInfoInt = getByteArrayToIntArray(bArr);
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        int[] iArr = this.mCanInfoInt;
        int i = iArr[1];
        if (i == 20) {
            setCarSetData0x14();
            return;
        }
        if (i == 22) {
            updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]));
            return;
        }
        if (i == 48) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanInfoByte));
            return;
        }
        if (i == 65) {
            setCarBodyInfo();
            return;
        }
        if (i == 112) {
            setTurnLight();
            return;
        }
        if (i == 50) {
            setLeftRadar();
            return;
        }
        if (i == 51) {
            setRightRadar();
            return;
        }
        if (i == 126) {
            setUpdateAble();
            return;
        }
        if (i != 127) {
            switch (i) {
                case 32:
                    setSwc();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadar();
                    break;
                case 35:
                    setFrontRadar();
                    break;
                default:
                    switch (i) {
                        case 37:
                            setParkingState();
                            break;
                        case 38:
                            setEsp();
                            break;
                        case 39:
                            setAmplifierInfo();
                            break;
                    }
            }
            return;
        }
        setScreenType();
    }

    private void setCarSetData0x14() throws Resources.NotFoundException {
        String string;
        int i = this.mCanInfoInt[2];
        if (i == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_1);
        } else if (i == 255) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set32_2);
        } else {
            string = this.mCanInfoInt[2] + "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanInfoInt[2]);
        GeneralAirData.auto_1_2 = getAuto(DataHandleUtils.getBoolBit3(this.mCanInfoInt[2]), DataHandleUtils.getBoolBit4(this.mCanInfoInt[2]));
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[3], 0, 4);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 4, 2);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanInfoInt[6]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanInfoInt[6], 0, 2);
        GeneralAirData.front_left_temperature = resolveLeftAndRightManualTemp(this.mCanInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightManualTemp(this.mCanInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar() {
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 0;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanInfoInt[3]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanInfoInt[3]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
        updateDoorView(this.mContext);
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getBoolBit2(this.mCanInfoInt[2])) {
            string = this.mContext.getResources().getString(R.string.open);
        } else {
            string = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(0, 3, string));
        arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]) ? "Not P" : "P"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSwc() {
        switch (this.mCanInfoInt[2]) {
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
                realKeyClick(45);
                break;
            case 4:
                realKeyClick(46);
                break;
            case 5:
                realKeyClick(14);
                break;
            case 6:
                realKeyClick(3);
                break;
            case 7:
                realKeyClick(2);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
        }
    }

    private void setParkingState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, getResString(DataHandleUtils.getBoolBit0(this.mCanInfoInt[2]) ? R.string.voice_on : R.string.voice_off)));
        arrayList.add(new DriverUpdateEntity(1, 1, getResString(DataHandleUtils.getBoolBit1(this.mCanInfoInt[2]) ? R.string.open : R.string.close)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setEsp() {
        byte[] bArr = this.mCanInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 11016, 16);
        updateParkUi(null, this.mContext);
    }

    private void setAmplifierInfo() {
        int[] iArr = this.mCanInfoInt;
        int i = iArr[2];
        boolean z = i == 2 || i == 3;
        this.isNeedCanCtrlAmplifier = z;
        if (z) {
            GeneralAmplifierData.volume = iArr[3];
            GeneralAmplifierData.leftRight = this.mCanInfoByte[4];
            GeneralAmplifierData.frontRear = this.mCanInfoByte[5];
            GeneralAmplifierData.bandBass = this.mCanInfoByte[6] + 9;
            GeneralAmplifierData.bandMiddle = this.mCanInfoByte[8] + 9;
            GeneralAmplifierData.bandTreble = this.mCanInfoByte[7] + 9;
            updateAmplifierActivity(null);
        }
        saveAmplifierData(this.mContext, 293);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(this.mCanInfoInt[9])).setProgress(this.mCanInfoInt[9]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTurnLight() {
        String resString;
        int i = this.mCanInfoInt[2];
        if (i == 0) {
            resString = getResString(R.string.mazda_binary_car_set77_1);
        } else if (i == 1) {
            resString = getResString(R.string.mazda_binary_car_set77_2);
        } else if (i == 2) {
            resString = getResString(R.string.mazda_binary_car_set77_3);
        } else {
            resString = i != 3 ? "" : getResString(R.string._293_drive_data5);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, resString));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setUpdateAble() {
        String resString;
        int i = this.mCanInfoInt[2];
        if (i == 0) {
            resString = getResString(R.string._293_drive_data2);
        } else if (i == 1) {
            resString = getResString(R.string._293_drive_data3);
        } else {
            resString = i != 2 ? "" : getResString(R.string._293_drive_data4);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, resString));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarBodyInfo() {
        int i = this.mCanInfoInt[2];
        if (i == 1) {
            GeneralDoorData.isShowMasterDriverSeatBelt = true;
            GeneralDoorData.isSeatMasterDriverBeltTie = !DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
            GeneralDoorData.isShowWashingFluidWarning = true;
            GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
            GeneralDoorData.isShowHandBrake = true;
            GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit5(this.mCanInfoInt[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanInfoInt[3]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanInfoInt[3]);
            updateDoorView(this.mContext);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            GeneralDoorData.isShowFuelWarning = true;
            GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanInfoInt[3]);
            GeneralDoorData.isShowBatteryWarning = true;
            GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanInfoInt[3]);
            updateDoorView(this.mContext);
            return;
        }
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb.append(((iArr[7] * 256) + iArr[8]) / 100).append("V").toString()));
        arrayList.add(new DriverUpdateEntity(1, 3, (((this.mCanInfoByte[9] * 256) + this.mCanInfoInt[10]) / 10) + "℃"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 4, sb2.append((iArr2[11] * 65536) + (iArr2[12] * 256) + iArr2[13]).append("Km").toString()));
        arrayList.add(new DriverUpdateEntity(1, 5, this.mCanInfoInt[14] + "L"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setScreenType() {
        String resString;
        int i = this.mCanInfoInt[2];
        if (i == 0) {
            resString = getResString(R.string._293_drive_date7);
        } else {
            resString = i != 1 ? "" : getResString(R.string._293_drive_date8);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 3, resString));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        SharePreUtil.setBoolValue(this.mContext, "_293_0x7f_is_red", this.mCanInfoInt[2] == 1);
    }

    private void setLeftRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(5, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRightRadar() {
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanInfoInt;
        RadarInfoUtil.setRightRadarLocationData(5, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private String resolveLeftAndRightManualTemp(int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (1 > i || 17 < i) ? "" : ((i * 0.5f) + 17.5d) + "℃";
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private String getResString(int i) {
        return this.mContext.getResources().getString(i);
    }

    private boolean isScreenRed() {
        return SharePreUtil.getBoolValue(this.mContext, "_293_0x7f_is_red", false);
    }

    private void sendMediaMsg(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        if (!isScreenRed()) {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 0}, bArr));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 1}, bArr2));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 2, 2}, bArr3));
        } else {
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 0}, bArr));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 1}, bArr2));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 2}, bArr3));
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 0, 4}, bArr4));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name(), new byte[]{22, -64, 0, 0});
            CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
            sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) throws NumberFormatException {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1});
        setVwRadioInfo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -62, this.bandType, this.freqLo, this.freqHi, (byte) i});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendMediaMsg(str.getBytes(), str2.getBytes(), ("P" + i).getBytes(), " ".getBytes());
    }

    private void setVwRadioInfo(String str, String str2) throws NumberFormatException {
        if (str.equals("AM2") || str.equals("MW")) {
            this.bandType = (byte) 18;
        } else if (str.equals("AM1") || str.equals("LW")) {
            int i = Integer.parseInt(str2);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
            this.bandType = (byte) 17;
        } else if (str.equals("FM1")) {
            this.bandType = (byte) 1;
        } else if (str.equals("FM2")) {
            this.bandType = (byte) 2;
        } else if (str.equals("FM3") || str.equals("OIRT")) {
            this.bandType = (byte) 3;
        }
        getFreqByteHiLo(str, str2);
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.FreqInt = i;
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 3, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendMediaMsg(" ".getBytes(), " ".getBytes(), bArr, (z ? "is Connected" : "Not Connected").getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 64});
        CanbusMsgSender.sendMsg(new byte[]{22, -61, 0, 0, 0, 0, 0, 0});
        sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        try {
            sndSimpleVol((byte) 30, (byte) i, i == 0);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void sndSimpleVol(byte b, byte b2, boolean z) throws RemoteException {
        if (b >= 30) {
            b = 30;
        }
        if (b2 >= b) {
            b2 = b;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) ((b2 & Byte.MAX_VALUE) | ((z ? -128 : 0) << 7))});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 33, false, false);
            sndDiscMidea(this.mContext, new byte[]{22, -61, (byte) i4, (byte) i5, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        } else {
            Util.reportSimpleMediaInfo(this.mContext, CanTypeMsg.ENUM_CANSBUS_SIMPLE_MEDIA_TYPE.DISC, 16, false, false);
            sndDiscMidea(this.mContext, new byte[]{22, -61, 1, (byte) i3, 0, 0, (byte) ((i / 60) % 60), (byte) (i % 60)});
        }
    }

    private void sndDiscMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BACKCAMERA || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MPEG || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 9, 17});
        } else {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17});
        }
        sendMediaMsg(str4.getBytes(), str5.getBytes(), str6.getBytes(), " ".getBytes());
        sndMusicMidea(this.mContext, new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b7, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        sendMediaMsg(" ".getBytes(), " ".getBytes(), " ".getBytes(), " ".getBytes());
    }

    private void sndMusicMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.MUSIC || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                    Settings.System.putString(context.getContentResolver(), "currentReport_disc", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 9, 32});
        } else {
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 32});
        }
        sndVideoMidea(this.mContext, new byte[]{22, -61, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, 0, 0});
        sendMediaMsg(" ".getBytes(), (new DecimalFormat("00").format(b3) + ":" + new DecimalFormat("00").format(b4) + ":" + new DecimalFormat("00").format(b5)).getBytes(), (((b6 * 256) + i) + "/" + i2).getBytes(), " ".getBytes());
    }

    private void sndVideoMidea(Context context, byte[] bArr) {
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if (FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.VIDEO || FutureUtil.instance.getCurrentValidSource() == SourceConstantsDef.SOURCE_ID.BTPHONE) {
                try {
                    CanbusMsgSender.sendMsg(bArr);
                    Settings.System.putString(context.getContentResolver(), "currentReport", Base64.encodeToString(bArr, 0));
                    Settings.System.putString(context.getContentResolver(), "currentReport_disc", Base64.encodeToString(bArr, 0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }
}
