package com.hzbhd.canbus.car._209;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x16Data;
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x25Data;
    private int[] m0x29Data;
    private int[] m0x30Data;
    private int[] m0x32Data;
    private int[] m0x33Data;
    private int[] m0x33DataIndexOne;
    private int[] m0x33DataIndexTwo;
    private int[] m0x70Data;
    private int[] m0x71Data;
    private int m0xC5Data1;
    private int[] m0xD1Data;
    private Thread m0xD1ResolveThread;
    private int[] m0xD2Data;
    private int[] m0xD3Data;
    private List<SongListEntity> mAmPresetFrequencyList;
    private List<SongListEntity> mAmValidFrequencyList;
    private boolean mBackStatus;
    private boolean mCameraStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat0P0;
    private DecimalFormat mDecimalFormat0P00;
    private List<SongListEntity> mFmPresetFrequencyList;
    private List<SongListEntity> mFmValidFrequencyList;
    private boolean mFrontStatus;
    private boolean mFrontViewBtnStatus;
    private ID3[] mId3s;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private List<SongListEntity> mPresetList;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private TimerUtil mTimerUtil;
    private boolean mTurnSignalStatus;
    private List<SongListEntity> mValidList;
    private boolean mIsDoorFirst = true;
    private int mOriginalRadioBand = -1;

    private String getBand(int i) {
        return i == 0 ? "FM" : "AM";
    }

    private String getDistanceUnit(int i) {
        return i != 0 ? i != 1 ? "" : "mile" : "km";
    }

    private String getFuelUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : "l/100km" : "km/l" : "mpg";
    }

    private int getRange(int i) {
        if (i == 0) {
            return 60;
        }
        if (i == 1) {
            return 10;
        }
        if (i != 2) {
            return (i - 1) * 10;
        }
        return 12;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        initId3();
        GeneralOriginalCarDeviceData.songList = new ArrayList();
        initOriginalDeviceList();
        this.mDecimalFormat0P0 = new DecimalFormat("0.0");
        this.mDecimalFormat0P00 = new DecimalFormat("0.00");
        GeneralParkData.radar_location_data = null;
        RadarInfoUtil.mMinIsClose = true;
        this.m0xD1ResolveThread = new Thread() { // from class: com.hzbhd.canbus.car._209.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
            }
        };
        GeneralOriginalCarDeviceData.isBottomBtnChange = false;
        this.mTimerUtil = new TimerUtil();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws SecurityException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            setVehicleSpeedData0x16();
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
        if (i == 50) {
            setVehicleSetupData0x32();
            return;
        }
        if (i == 51) {
            setFuelData0x33();
            return;
        }
        if (i == 112) {
            setCameraStatus0x70();
            return;
        }
        if (i != 113) {
            switch (i) {
                case 32:
                    setWheelKey0x20();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadarData0x22();
                    break;
                case 35:
                    setFrontRadarData0x23();
                    break;
                case 36:
                    setDoorData0x24();
                    break;
                case 37:
                    setParkAssistData0x25();
                    break;
                default:
                    switch (i) {
                        case HotKeyConstant.K_AVM /* 209 */:
                            setOriginalRadioData0xD1();
                            break;
                        case 210:
                            setCameraStatusData0xD2();
                            break;
                        case 211:
                            setScreenData0xD3();
                            break;
                    }
            }
            return;
        }
        setEngineSpeed0x71();
    }

    private void setWheelKey0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                realKeyLongClick1(7);
                break;
            case 2:
                realKeyLongClick1(8);
                break;
            case 3:
                realKeyLongClick1(48);
                break;
            case 4:
                realKeyLongClick1(47);
                break;
            case 7:
                realKeyLongClick1(2);
                break;
            case 8:
                realKeyLongClick1(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(14);
                break;
            case 10:
                realKeyLongClick1(HotKeyConstant.K_PHONE_OFF_RETURN);
                break;
        }
    }

    private void setAirData0x21() {
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[5]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setDoorData0x24() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        this.mRightFrontRec = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        this.mLeftFrontRec = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        this.mRightRearRec = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        this.mLeftRearRec = boolBit4;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setFuelData0x33() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            if (is0x33IndexOneDataChange()) {
                String fuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 0, 2));
                String fuelUnit2 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 2, 2));
                String fuelUnit3 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 4, 2));
                String distanceUnit = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1));
                String distanceUnit2 = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1));
                int range = getRange(this.mCanBusInfoInt[16]);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DriverUpdateEntity(0, 0, getData(new int[]{this.mCanBusInfoInt[3]}, range * 0.04761905f, fuelUnit)));
                int[] iArr = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 1, getData(new int[]{iArr[5], iArr[4]}, 0.1f, fuelUnit2)));
                int[] iArr2 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 2, getData(new int[]{iArr2[7], iArr2[6]}, 0.1f, fuelUnit2)));
                int[] iArr3 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 3, getData(new int[]{iArr3[9], iArr3[8]}, 0.1f, fuelUnit3)));
                int[] iArr4 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 4, getData(new int[]{iArr4[12], iArr4[11], iArr4[10]}, 0.1f, distanceUnit)));
                int[] iArr5 = this.mCanBusInfoInt;
                arrayList.add(new DriverUpdateEntity(0, 5, getData(new int[]{iArr5[14], iArr5[13]}, 1.0f, distanceUnit2)));
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
                return;
            }
            return;
        }
        if (i == 2 && is0x33IndexTwoDataChange()) {
            String fuelUnit4 = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 4, 2));
            String distanceUnit3 = getDistanceUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[18], 6, 1));
            ArrayList arrayList2 = new ArrayList();
            int[] iArr6 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 1, getData(new int[]{iArr6[5], iArr6[4], iArr6[3]}, 0.1f, distanceUnit3)));
            int[] iArr7 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 2, getData(new int[]{iArr7[7], iArr7[6]}, 0.1f, fuelUnit4)));
            int[] iArr8 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 4, getData(new int[]{iArr8[10], iArr8[9], iArr8[8]}, 0.1f, distanceUnit3)));
            int[] iArr9 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 5, getData(new int[]{iArr9[12], iArr9[11]}, 0.1f, fuelUnit4)));
            int[] iArr10 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 7, getData(new int[]{iArr10[15], iArr10[14], iArr10[13]}, 0.1f, distanceUnit3)));
            int[] iArr11 = this.mCanBusInfoInt;
            arrayList2.add(new DriverUpdateEntity(1, 8, getData(new int[]{iArr11[17], iArr11[16]}, 0.1f, fuelUnit4)));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
        }
    }

    private void setVersionInfo0x30() {
        if (is0x30DataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private void setVehicleSetupData0x32() {
        if (is0x32DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 1))));
            arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
            arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
            arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
            arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))));
            arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))));
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
            arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
            arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
            arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit - 5)).setProgress(intFromByteWithBit));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setOriginalRadioData0xD1() {
        if (is0xD1DataChange()) {
            GeneralOriginalCarDeviceData.isSongListChange = this.mOriginalRadioBand != this.mCanBusInfoInt[3];
            int[] iArr = this.mCanBusInfoInt;
            int i = iArr[3];
            this.mOriginalRadioBand = i;
            List<SongListEntity> list = i == 0 ? this.mFmPresetFrequencyList : this.mAmPresetFrequencyList;
            this.mPresetList = list;
            List<SongListEntity> list2 = i == 0 ? this.mFmValidFrequencyList : this.mAmValidFrequencyList;
            this.mValidList = list2;
            int i2 = iArr[2];
            if (i2 == 1) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, getBand(this.mCanBusInfoInt[3])));
                arrayList.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)));
                int[] iArr2 = this.mCanBusInfoInt;
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, getFrequency(iArr2[3], iArr2[4], iArr2[5])));
                GeneralOriginalCarDeviceData.mList = arrayList;
            } else if (i2 == 2) {
                refreshStationList(list2);
                GeneralOriginalCarDeviceData.isSongListChange = true;
            } else if (i2 == 3) {
                refreshStationList(list);
                GeneralOriginalCarDeviceData.isSongListChange = true;
            }
            GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralOriginalCarDeviceData.scan = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            GeneralOriginalCarDeviceData.refresh = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
            GeneralOriginalCarDeviceData.songList.clear();
            GeneralOriginalCarDeviceData.songList.addAll(this.mPresetList);
            GeneralOriginalCarDeviceData.songList.addAll(this.mValidList);
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCameraStatusData0xD2() {
        if (is0xD2DataChange()) {
            if (this.mCameraStatus ^ DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
                boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                this.mCameraStatus = boolBit7;
                forceReverse(this.mContext, boolBit7);
            }
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, intFromByteWithBit == 0));
            arrayList.add(new PanoramicBtnUpdateEntity(1, intFromByteWithBit == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(2, intFromByteWithBit == 2));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setFrontRadarData0x23() {
        if (is0x23DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setParkAssistData0x25() {
        if (is0x25DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(2, 2, CommUtil.getStrByResId(this.mContext, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "open" : "close")));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setScreenData0xD3() {
        if (is0xD3DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(this.mCanBusInfoInt[3] - 5)).setProgress(this.mCanBusInfoInt[3]));
            arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(this.mCanBusInfoInt[4] - 5)).setProgress(this.mCanBusInfoInt[4]));
            arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(this.mCanBusInfoInt[5] - 5)).setProgress(this.mCanBusInfoInt[5]));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVehicleSpeedData0x16() {
        if (is0x16DataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(2, 0, sb.append(iArr[2] | (iArr[3] << 8)).append(" km/h").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr2 = this.mCanBusInfoInt;
            updateSpeedInfo(iArr2[2] | (iArr2[3] << 8));
        }
    }

    private void setCameraStatus0x70() throws SecurityException {
        if (is0x70DataChange()) {
            boolean zIsForeground = SystemUtil.isForeground(this.mContext, new String[]{Constant.FCameraActivity.getClassName(), Constant.AuxInActivity.getClassName()});
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            if (this.mFrontViewBtnStatus && !boolBit6) {
                switchFCamera(this.mContext, !zIsForeground);
                zIsForeground = !zIsForeground;
            }
            this.mFrontViewBtnStatus = boolBit6;
            int i = SharePreUtil.getBoolValue(this.mContext, "share_209_front_camera_switch", false) ? 3 : 7;
            Log.i("ljq", "setCameraStatus0x70: side-->" + i);
            boolean z = ((1 << i) & this.mCanBusInfoInt[3]) != 0;
            if (this.mTurnSignalStatus ^ z) {
                this.mTurnSignalStatus = z;
                if (zIsForeground ^ z) {
                    switchFCamera(this.mContext, z);
                }
            }
        }
    }

    private void setEngineSpeed0x71() {
        if (is0x71DataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(2, 1, sb.append(iArr[2] | (iArr[3] << 8)).append(" rpm").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int bandData = getBandData(str);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte) bandData, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        int[] time = getTime(i);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, (byte) i3, (byte) i5, (byte) time[0], (byte) time[1], (byte) time[2]});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendMediaMsg(null, new byte[][]{new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0}, DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, DataHandleUtils.byteMerger(new byte[]{32}, bArr)), 36, 32)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendMediaMsg(null, new byte[][]{new byte[]{22, -64, 5, 64, 0, 0, 0, 0, 0, 0}, DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, DataHandleUtils.byteMerger(new byte[]{32}, bArr)), 36, 32)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        int[] time = getTime(i);
        byte[] bArr2 = {22, -64, 5, 64, 0, (byte) time[1], (byte) time[2], 0, 0, 0};
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0xC5Data1, 5, !z);
        this.m0xC5Data1 = intByteWithBit;
        sendMediaMsg(null, new byte[][]{bArr2, new byte[]{22, -59, 0, (byte) intByteWithBit}, DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, DataHandleUtils.byteMerger(new byte[]{32}, bArr)), 36, 32)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0xC5Data1, 4, z);
        this.m0xC5Data1 = intByteWithBit;
        int intFromByteWithBit = DataHandleUtils.setIntFromByteWithBit(intByteWithBit, i, 0, 3);
        this.m0xC5Data1 = intFromByteWithBit;
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) intFromByteWithBit});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        int intFromByteWithBit = DataHandleUtils.setIntFromByteWithBit(this.m0xC5Data1, i6, 6, 1);
        this.m0xC5Data1 = intFromByteWithBit;
        int intFromByteWithBit2 = DataHandleUtils.setIntFromByteWithBit(intFromByteWithBit, i4, 3, 1);
        this.m0xC5Data1 = intFromByteWithBit2;
        CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, (byte) intFromByteWithBit2});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) (i2 >> 8), (byte) i, b7, b4, b5});
        this.mId3s[0].id3 = str4;
        this.mId3s[1].id3 = str5;
        this.mId3s[2].id3 = str6;
        reportID3Info(this.mId3s, false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mId3s[0].id3 = "";
        this.mId3s[1].id3 = "";
        this.mId3s[2].id3 = "";
        reportID3Info(this.mId3s, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) (i2 >> 8), (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (DataHandleUtils.rangeNumber(i, 0, 30) | (z ? 128 : 0))});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        if (!z) {
            i8 |= 128;
        }
        final byte[] bArr = {22, -119, (byte) i8, (byte) i6};
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        this.mTimerUtil.stopTimer();
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._209.MsgMgr.2
            int count = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.count >= 3) {
                    MsgMgr.this.mTimerUtil.stopTimer();
                } else {
                    CanbusMsgSender.sendMsg(bArr);
                    this.count++;
                }
            }
        }, 0L, 5000L);
    }

    private void realKeyLongClick1(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private String resolveAirTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 255) {
            return "HI";
        }
        return (i * (GeneralAirData.fahrenheit_celsius ? 1.0f : 0.5f)) + (GeneralAirData.fahrenheit_celsius ? getTempUnitF(this.mContext) : getTempUnitC(this.mContext));
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && GeneralDoorData.skyWindowOpenLevel == 0) {
                return true;
            }
        }
        return false;
    }

    private int getDriveData(int[] iArr) {
        int iPow = iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(256.0d, i)));
        }
        return iPow;
    }

    private String getData(int[] iArr, float f, String str) {
        int iPow = 0;
        for (int i = 0; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(2.0d, i * 8)));
        }
        int i2 = 1;
        for (int i3 = 0; i3 < (iArr.length * 8) - 1; i3++) {
            i2 = (i2 << 1) + 1;
        }
        return iPow == i2 ? "- - -" : this.mDecimalFormat0P0.format(iPow * f) + " " + str;
    }

    private String getFrequency(int i, int i2, int i3) {
        float f = i2 | (i3 << 8);
        if (i == 0) {
            return this.mDecimalFormat0P00.format(f / 10.0f) + " MHz";
        }
        return this.mDecimalFormat0P00.format(f) + " KHz";
    }

    private void refreshStationList(List<SongListEntity> list) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        if (intFromByteWithBit != 0) {
            String str = "P" + intFromByteWithBit + " ";
        }
        if (this.mCanBusInfoInt[7] + 1 < list.size()) {
            SongListEntity songListEntity = list.get(this.mCanBusInfoInt[7] + 1);
            StringBuilder sbAppend = new StringBuilder().append("\t\t");
            int[] iArr = this.mCanBusInfoInt;
            songListEntity.setTitle(sbAppend.append(getFrequency(iArr[3], iArr[4], iArr[5])).toString());
            return;
        }
        StringBuilder sbAppend2 = new StringBuilder().append("\t\t");
        int[] iArr2 = this.mCanBusInfoInt;
        list.add(new SongListEntity(sbAppend2.append(getFrequency(iArr2[3], iArr2[4], iArr2[5])).toString()));
    }

    private boolean is0x30DataChange() {
        if (Arrays.equals(this.m0x30Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x30Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x32DataChange() {
        if (Arrays.equals(this.m0x32Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x32Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x33DataChange() {
        if (Arrays.equals(this.m0x33Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x33Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x33IndexOneDataChange() {
        if (Arrays.equals(this.m0x33DataIndexOne, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x33DataIndexOne = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x33IndexTwoDataChange() {
        if (Arrays.equals(this.m0x33DataIndexTwo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x33DataIndexTwo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x29DataChange() {
        if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x29Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0xD3DataChange() {
        if (Arrays.equals(this.m0xD3Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xD3Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0xD2DataChange() {
        if (Arrays.equals(this.m0xD2Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xD2Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x22DataChange() {
        if (Arrays.equals(this.m0x22Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x22Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x23DataChange() {
        if (Arrays.equals(this.m0x23Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x23Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x25DataChange() {
        if (Arrays.equals(this.m0x25Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x25Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0xD1DataChange() {
        if (Arrays.equals(this.m0xD1Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xD1Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x16DataChange() {
        if (Arrays.equals(this.m0x16Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x16Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x70DataChange() {
        if (Arrays.equals(this.m0x70Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x70Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x71DataChange() {
        if (Arrays.equals(this.m0x71Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x71Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private int getBandData(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        if ("FM3".equals(str)) {
            return 3;
        }
        if ("AM1".equals(str)) {
            return 17;
        }
        return "AM2".equals(str) ? 18 : 0;
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._209.MsgMgr$3] */
    private void reportID3Info(final ID3[] id3Arr, final boolean z) {
        new Thread() { // from class: com.hzbhd.canbus.car._209.MsgMgr.3
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    for (ID3 id3 : id3Arr) {
                        if (id3.isId3Change()) {
                            if (z) {
                                sleep(900L);
                            }
                            for (ID3 id32 : id3Arr) {
                                sleep(100L);
                                MsgMgr.this.reportID3InfoFinal((byte) id32.cmd, id32.id3);
                            }
                            return;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str) throws Exception {
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, b, 2}, DataHandleUtils.exceptBOMHead(str.getBytes("unicodeLittle"))), 36));
    }

    private class ID3 {
        private int cmd;
        private String id3 = new String();
        private String record = new String();

        public ID3(int i) {
            this.cmd = i;
        }

        public boolean isId3Change() {
            if (this.record.equals(this.id3)) {
                return false;
            }
            this.record = this.id3;
            return true;
        }
    }

    private void initId3() {
        this.mId3s = new ID3[]{new ID3(2), new ID3(3), new ID3(4)};
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._209.MsgMgr$4] */
    private void sendMediaMsg(final String str, final byte[][] bArr) {
        new Thread() { // from class: com.hzbhd.canbus.car._209.MsgMgr.4
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    int i = 0;
                    if (str == null) {
                        byte[][] bArr2 = bArr;
                        int length = bArr2.length;
                        while (i < length) {
                            byte[] bArr3 = bArr2[i];
                            sleep(100L);
                            CanbusMsgSender.sendMsg(bArr3);
                            i++;
                        }
                        return;
                    }
                    byte[][] bArr4 = bArr;
                    int length2 = bArr4.length;
                    while (i < length2) {
                        byte[] bArr5 = bArr4[i];
                        sleep(100L);
                        MsgMgr msgMgr = MsgMgr.this;
                        msgMgr.sendMediaMsg(msgMgr.mContext, str, bArr5);
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void initOriginalDeviceList() {
        ArrayList arrayList = new ArrayList();
        this.mFmPresetFrequencyList = arrayList;
        arrayList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
        this.mFmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
        ArrayList arrayList2 = new ArrayList();
        this.mFmValidFrequencyList = arrayList2;
        arrayList2.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
        this.mFmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
        ArrayList arrayList3 = new ArrayList();
        this.mAmPresetFrequencyList = arrayList3;
        arrayList3.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_preset_station")));
        this.mAmPresetFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
        ArrayList arrayList4 = new ArrayList();
        this.mAmValidFrequencyList = arrayList4;
        arrayList4.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "_209_valid_station")));
        this.mAmValidFrequencyList.add(new SongListEntity(CommUtil.getStrByResId(this.mContext, "null_value")));
        this.mPresetList = this.mFmPresetFrequencyList;
        this.mValidList = this.mFmValidFrequencyList;
    }

    int getPresetListSize() {
        return this.mPresetList.size();
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
