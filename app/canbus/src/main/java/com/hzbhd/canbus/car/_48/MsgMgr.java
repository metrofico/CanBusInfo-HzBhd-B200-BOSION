package com.hzbhd.canbus.car._48;

import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.OriginalCarDeviceActivity;
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
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private int[] m0x22Data;
    private int[] m0x25Data;
    private int[] m0x29Data;
    private int[] m0x30Data;
    private int[] m0x32Data;
    private int[] m0x33DataIndexOne;
    private int[] m0x33DataIndexTwo;
    private int[] m0xD1Data;
    private int m0xD2Data0;
    private int[] m0xD3Data;
    private int[] m0xD4Data;
    private List<SongListEntity> mAmPresetFrequencyList;
    private List<SongListEntity> mAmValidFrequencyList;
    private boolean mBackStatus;
    private int mBsmData;
    boolean mCalibrationStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat0P0;
    private DecimalFormat mDecimalFormat0P00;
    private List<SongListEntity> mFmPresetFrequencyList;
    private List<SongListEntity> mFmValidFrequencyList;
    private boolean mFrontStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private boolean mOrginalAuxStatus;
    private OriginalCarDevicePageUiSet mOriDevUiSet;
    private List<SongListEntity> mPresetList;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private boolean mRightViewStatus;
    private List<SongListEntity> mValidList;
    private final String ORGINAL_DEVICE_AUX = "AUX";
    private final String ORGINAL_DEVICE_FM = "FM";
    private final String ORGINAL_DEVICE_AM = "AM";
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
        GeneralOriginalCarDeviceData.songList = new ArrayList();
        initOriginalDeviceList();
        this.mDecimalFormat0P0 = new DecimalFormat("0.0");
        this.mDecimalFormat0P00 = new DecimalFormat("0.00");
        GeneralParkData.radar_location_data = null;
        RadarInfoUtil.mMinIsClose = true;
        GeneralOriginalCarDeviceData.isBottomBtnChange = false;
        this.mOriDevUiSet = UiMgrFactory.getCanUiMgr(context).getOriginalCarDevicePageUiSet(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 36) {
            setDoorData0x24();
            return;
        }
        if (i == 37) {
            setParkAssistData0x25();
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
        if (i != 51) {
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
                        case NfDef.STATE_3WAY_INCOMING_CALL_S_ACT_S_HOLD /* 212 */:
                            setCompassData0xD4();
                            break;
                    }
            }
            return;
        }
        setFuelData0x33();
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            realKeyLongClick1(7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(46);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(45);
            return;
        }
        if (i != 23) {
            switch (i) {
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
            return;
        }
        realKeyLongClick1(152);
    }

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
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
        if (isDoorDataChange() && !isDoorFirst()) {
            updateDoorView(this.mContext);
        }
        if (this.mOrginalAuxStatus != DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            this.mOrginalAuxStatus = boolBit3;
            if (boolBit3) {
                enterAuxIn2();
                return;
            }
            if (SystemUtil.isForeground(this.mContext, OriginalCarDeviceActivity.class.getName())) {
                realKeyClick(this.mContext, 52);
            }
            exitAuxIn2();
        }
    }

    private void setParkAssistData0x25() {
        if (is0x25DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(2, 0, CommUtil.getStrByResId(this.mContext, DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "open" : "close")));
            updateGeneralDriveData(arrayList);
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
            int length = this.mCanBusInfoInt.length;
            if (length != 3) {
                if (length != 4) {
                    if (length != 5) {
                        if (length == 6) {
                            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
                            arrayList.add(new SettingUpdateEntity(3, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
                        }
                    }
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                }
                arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
                arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit - 5)).setProgress(intFromByteWithBit));
            } else {
                int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit2 - 5)).setProgress(intFromByteWithBit2));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
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

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4608, 16);
            updateParkUi(null, this.mContext);
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

    private void setOriginalRadioData0xD1() {
        if (is0xD1DataChange()) {
            GeneralOriginalCarDeviceData.isSongListChange = this.mOriginalRadioBand != this.mCanBusInfoInt[3];
            int i = this.mCanBusInfoInt[3];
            this.mOriginalRadioBand = i;
            if (i > 3) {
                this.mPresetList = this.mAmPresetFrequencyList;
                this.mValidList = this.mAmValidFrequencyList;
                GeneralOriginalCarDeviceData.cdStatus = "AM";
            } else {
                this.mPresetList = this.mFmPresetFrequencyList;
                this.mValidList = this.mFmValidFrequencyList;
                GeneralOriginalCarDeviceData.cdStatus = "FM";
            }
            int i2 = this.mCanBusInfoInt[2];
            if (i2 == 1) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new OriginalCarDeviceUpdateEntity(0, getBand(this.mCanBusInfoInt[3])));
                arrayList.add(new OriginalCarDeviceUpdateEntity(1, "P" + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)));
                int[] iArr = this.mCanBusInfoInt;
                arrayList.add(new OriginalCarDeviceUpdateEntity(2, getFrequency(iArr[3], iArr[4], iArr[5])));
                GeneralOriginalCarDeviceData.mList = arrayList;
            } else if (i2 == 2) {
                refreshStationList(this.mValidList);
                GeneralOriginalCarDeviceData.isSongListChange = true;
            } else if (i2 == 3) {
                refreshStationList(this.mPresetList);
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
        if (is0xD2Data0Change()) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, intFromByteWithBit == 0));
            arrayList.add(new PanoramicBtnUpdateEntity(1, intFromByteWithBit == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(2, intFromByteWithBit == 2));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
        if (this.mRightViewStatus ^ DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3])) {
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3]);
            this.mRightViewStatus = boolBit7;
            switchFCamera(this.mContext, boolBit7);
        }
        if (isBsmDataChange()) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new SettingUpdateEntity(6, 0, Integer.valueOf(this.mBsmData)));
            updateGeneralSettingData(arrayList2);
            updateSettingActivity(null);
        }
    }

    private void setScreenData0xD3() {
        if (is0xD3DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(this.mCanBusInfoInt[3] - 5)).setProgress(this.mCanBusInfoInt[3]));
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(this.mCanBusInfoInt[4] - 5)).setProgress(this.mCanBusInfoInt[4]));
            arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(this.mCanBusInfoInt[5] - 5)).setProgress(this.mCanBusInfoInt[5]));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setCompassData0xD4() {
        if (is0xD4DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(5, 0, getCalibrationStatus()).setValueStr(true));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit - 1));
            arrayList.add(new SettingUpdateEntity(5, 3, ((this.mCanBusInfoInt[3] * 3) / 2) + CommUtil.getStrByResId(this.mContext, "unit_degree")).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
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

    private String getData(int[] iArr, float f, String str) {
        int iPow = 0;
        for (int i = 0; i < iArr.length; i++) {
            iPow = (int) (iPow + (iArr[i] * Math.pow(2.0d, i * 8)));
        }
        return ((double) iPow) == Math.pow(2.0d, (double) (iArr.length * 8)) - 1.0d ? "---" : this.mDecimalFormat0P0.format(iPow * f) + " " + str;
    }

    private String getFrequency(int i, int i2, int i3) {
        float f = i2 | (i3 << 8);
        if (i == 0) {
            return this.mDecimalFormat0P00.format(f / 10.0f) + " MHz";
        }
        return this.mDecimalFormat0P00.format(f) + " KHz";
    }

    private void refreshStationList(List<SongListEntity> list) {
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

    private String getCalibrationStatus() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        this.mCalibrationStatus = boolBit7;
        if (boolBit7) {
            return CommUtil.getStrByResId(this.mContext, "compass_calibrating");
        }
        return CommUtil.getStrByResId(this.mContext, "compass_calibration_finish");
    }

    void onAuxClick() {
        GeneralOriginalCarDeviceData.cdStatus = "AUX";
        updateOriginalCarDeviceActivity(null);
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

    private boolean is0xD4DataChange() {
        if (Arrays.equals(this.m0xD4Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xD4Data = Arrays.copyOf(iArr, iArr.length);
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

    private boolean is0xD2Data0Change() {
        int i = this.m0xD2Data0;
        int i2 = this.mCanBusInfoInt[2];
        if (i == i2) {
            return false;
        }
        this.m0xD2Data0 = i2;
        return true;
    }

    private boolean isBsmDataChange() {
        if (this.mBsmData == DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)) {
            return false;
        }
        this.mBsmData = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
        return true;
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

    void updateSetttings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
