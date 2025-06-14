package com.hzbhd.canbus.car._165;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int CAR_ELFA = 50;
    private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private static final int SEND_NORMAL_MESSAGE = 2;
    static final int SHARE_165_AMPLIFIER_BAND_OFFSET = 2;
    static final int SHARE_165_AMPLIFIER_FADE_OFFSET = 7;
    static final String SHARE_165_IS_HAVE_SPARE_TIRE = "share_165_is_have_spare_tire";
    static final String SHARE_165_IS_SUPPORT_TPMS = "share_165_is_suppot_tire";
    static final String SHARE_165_IS_SUPPOT_HYBRID = "share_165_is_suppot_hybrid";
    static final int VEHICLE_TYPE_14_PRADO_HIGH_PANORAMIC = 34;
    static final int VEHICLE_TYPE_16_RAV4 = 1;
    static final int VEHICLE_TYPE_18_CAMRY_AUTO_AC = 49;
    static final int VEHICLE_TYPE_CAMRY = 48;
    static final int VEHICLE_TYPE_HIGHLANDER = 17;
    static final int VEHICLE_TYPE_LAND_CRUISER_4_ZONE = 33;
    static final int VEHICLE_TYPE_LAND_CRUISER_PRADO = 32;
    static final int VEHICLE_TYPE_TUNDRA = 19;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private Context mContext;
    private DecimalFormat mDecimalFormat0p0;
    private int mDifferent;
    private int mEachId;
    private boolean mFrontStatus;
    private Handler mHandler;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mPanoramicStatusNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, SettingItem> mSettingItemIndeHashMap;
    private boolean mSubSeatBeltStatus;
    private int mTireAlertStatus;
    private final String TAG = "_165_MsgMgr";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;

    private int getDriveData(int i, int i2) {
        return (i * 256) + i2;
    }

    private String getFuelUnit(int i) {
        return i == 0 ? " MPG" : i == 1 ? " KM/L" : i == 2 ? " L/100KM" : i == 3 ? " MPG(UK)" : "";
    }

    private String getMileageUnit(int i) {
        return i == 1 ? " MILE" : i == 2 ? " KM" : "";
    }

    private float getTireRule(int i) {
        if (i != 0) {
            return i != 2 ? 1.0f : 0.4f;
        }
        return 10.0f;
    }

    private String getTireUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : " KPA" : " PSI" : " BAR";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 36, (byte) SharePreUtil.getIntValue(context, "share_165_language", 0)});
        }
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new byte[]{22, -127, 1});
        arrayList.add(new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 7)});
        arrayList.add(new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)});
        arrayList.add(new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)});
        arrayList.add(new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)});
        arrayList.add(new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)});
        arrayList.add(new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume});
        arrayList.add(new byte[]{22, -124, 8, 1});
        if (this.mDifferent >= 16) {
            arrayList.add(new byte[]{22, -30, (byte) this.mEachId});
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._165.MsgMgr.1
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.i < arrayList.size()) {
                    CanbusMsgSender.sendMsg((byte[]) arrayList.get(this.i));
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 100L, 100L);
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
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AppEnableUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        this.mCanbusDataArray = new SparseArray<>();
        this.mDecimalFormat0p0 = new DecimalFormat("0.0");
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        initHandler(context);
        intiSettingItem(UiMgrFactory.getCanUiMgr(context).getSettingUiSet(context));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            set0x16VehicleSpeed();
            return;
        }
        if (i == 53) {
            set0x35SeatHeatStatus(context);
            return;
        }
        if (i == 80) {
            set0x50EnginSpeed();
            return;
        }
        if (i != 102) {
            switch (i) {
                case 29:
                    set0x1DFrontRadarData(context);
                    break;
                case 30:
                    set0x1ERearRadarData(context);
                    break;
                case 31:
                    set0x1FHybrid();
                    break;
                case 32:
                    set0x20WheelKey(context);
                    break;
                case 33:
                    set0x21TripInfoOne();
                    break;
                case 34:
                    set0x22TripInfoTwo();
                    break;
                case 35:
                    set0x23HistoricalFuel();
                    break;
                case 36:
                    set0x24DoorData();
                    break;
                case 37:
                    set0x25TireData(context);
                    break;
                case 38:
                    set0x26VehicleSettings();
                    break;
                case 39:
                    set0x27TripInfoThree();
                    break;
                case 40:
                    set0x28AirData(context);
                    break;
                case 41:
                    set0x29TrackData();
                    break;
                default:
                    switch (i) {
                        case 48:
                            set0x30VersionInfo();
                            break;
                        case 49:
                            set0x31AmplifierData();
                            break;
                        case 50:
                            set0x32SystemInfo();
                            break;
                    }
            }
            return;
        }
        set0x66CeilingScreen();
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyLongClick1(context, 0);
        }
        if (i == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i == 3) {
            realKeyLongClick1(context, 48);
            return;
        }
        if (i == 4) {
            realKeyLongClick1(context, 47);
            return;
        }
        switch (i) {
            case 7:
                realKeyLongClick1(context, 2);
                break;
            case 8:
                realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyLongClick1(context, 14);
                break;
            case 10:
                realKeyLongClick1(context, 15);
                break;
            default:
                switch (i) {
                    case 19:
                        realKeyLongClick1(context, 45);
                        break;
                    case 20:
                        realKeyLongClick1(context, 46);
                        break;
                    case 21:
                        realKeyLongClick1(context, 50);
                        break;
                    case 22:
                        realKeyLongClick1(context, 49);
                        break;
                    default:
                        switch (i) {
                            case 129:
                                realKeyLongClick1(context, 7);
                                break;
                            case 130:
                                realKeyLongClick1(context, 8);
                                break;
                            case 131:
                                realKeyLongClick1(context, 45);
                                break;
                            case 132:
                                realKeyLongClick1(context, 46);
                                break;
                            case 133:
                                realKeyLongClick1(context, 21);
                                break;
                            case HotKeyConstant.K_SLEEP /* 134 */:
                                realKeyLongClick1(context, 20);
                                break;
                            case 135:
                                realKeyLongClick1(context, HotKeyConstant.K_SLEEP);
                                break;
                            case 136:
                                realKeyLongClick1(context, 2);
                                break;
                        }
                }
        }
    }

    private void set0x21TripInfoOne() {
        String mileageUnit = getMileageUnit(this.mCanBusInfoInt[8]);
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        int driveData = getDriveData(iArr[2], iArr[3]);
        int[] iArr2 = this.mCanBusInfoInt;
        int driveData2 = getDriveData(iArr2[4], iArr2[5]);
        int[] iArr3 = this.mCanBusInfoInt;
        int driveData3 = getDriveData(iArr3[6], iArr3[7]);
        int iRangeNumber = DataHandleUtils.rangeNumber(driveData, 9999);
        int iRangeNumber2 = DataHandleUtils.rangeNumber(driveData2, 5999);
        int iRangeNumber3 = DataHandleUtils.rangeNumber(driveData3, 9999);
        arrayList.add(new DriverUpdateEntity(0, 1, (iRangeNumber / 10.0f) + mileageUnit + "/H"));
        arrayList.add(new DriverUpdateEntity(0, 4, (iRangeNumber2 / 60) + " H " + (iRangeNumber2 % 60) + " M"));
        arrayList.add(new DriverUpdateEntity(0, 5, iRangeNumber3 + mileageUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x22TripInfoTwo() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, (getDriveData(iArr[3], iArr[4]) / 10.0f) + fuelUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x23HistoricalFuel() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            int driveData = getDriveData(iArr[i2 + 3], iArr[i2 + 4]);
            String str = (driveData / 10.0f) + fuelUnit;
            if (driveData == 65535) {
                str = "";
            }
            arrayList.add(new DriverUpdateEntity(1, i, str));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x24DoorData() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void set0x25TireData(Context context) {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        SharePreUtil.setBoolValue(this.mContext, SHARE_165_IS_SUPPORT_TPMS, boolBit7);
        if (boolBit7) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
            if (this.mTireAlertStatus != intFromByteWithBit) {
                this.mTireAlertStatus = intFromByteWithBit;
                if (intFromByteWithBit == 1) {
                    startTireActivity(context);
                }
            }
            String tireUnit = getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            float tireRule = getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new TireUpdateEntity(0, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[3] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(1, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[4] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(2, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[5] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(3, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[6] / tireRule) + tireUnit}));
            if (GeneralTireData.isHaveSpareTire) {
                arrayList.add(new TireUpdateEntity(4, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[7] / tireRule) + tireUnit}));
            }
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x26VehicleSettings() {
        int[] iArr = this.mCanBusInfoInt;
        int intFromByteWithBit = iArr.length > 6 ? DataHandleUtils.getIntFromByteWithBit(iArr[6], 0, 2) : 0;
        if (intFromByteWithBit == 3) {
            intFromByteWithBit = 2;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        setSettingData(new String[]{"light_ctrl_3", "_18_vehicle_setting_item_3_210", "_18_vehicle_setting_item_3_43", "_55_0x67_data1_bit32", "_55_0x65_data1_bit54_item1", "_18_vehicle_setting_item_1_2", "_18_vehicle_setting_item_1_3", "_18_vehicle_setting_item_2_6", "hiworld_jeep_123_0x60_data1_65", "_18_vehicle_setting_item_2_4", "_18_vehicle_setting_item_1_4", "_18_vehicle_setting_item_1_5", "_18_vehicle_setting_item_2_5", "_18_vehicle_setting_item_2_7", "_11_0x26_data2_bit20", "_18_vehicle_setting_item_1_1", "_18_vehicle_setting_item_1_0", "_11_0x26_data3_bit32", "_55_0x65_data1_bit21", "_11_0x26_data4_bit65", "_165_sterring_column"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)), Integer.valueOf(intFromByteWithBit), Integer.valueOf(iArr2.length > 7 ? DataHandleUtils.getIntFromByteWithBit(iArr2[7], 0, 3) : 0)});
    }

    private void set0x27TripInfoThree() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            int driveData = getDriveData(iArr[31 - i2], iArr[32 - i2]);
            String str = (driveData / 10.0f) + fuelUnit;
            if (driveData == 65535) {
                str = "";
            }
            arrayList.add(new DriverUpdateEntity(2, i, str));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x28AirData(Context context) {
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_dual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        updateOutDoorTemp(context, resolveOutDoorTemperature(context));
        int airWhat = getAirWhat();
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[7] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp(context, this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(context, this.mCanBusInfoInt[5]);
        GeneralAirData.rear_left_temperature = resolveAirTemp(context, this.mCanBusInfoInt[8]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        if (this.mDifferent == 33) {
            GeneralAirData.rear_right_temperature = resolveAirTemp(context, this.mCanBusInfoInt[10]);
        } else {
            GeneralAirData.rear_right_temperature = GeneralAirData.rear_left_temperature;
        }
        if (this.mDifferent == 33) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
            boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
            GeneralAirData.front_left_blow_head = compare(Integer.valueOf(intFromByteWithBit), 1, 2);
            GeneralAirData.front_left_blow_foot = compare(Integer.valueOf(intFromByteWithBit), 2, 3) || boolBit4;
            GeneralAirData.front_left_blow_window = boolBit4;
            GeneralAirData.front_right_blow_head = compare(Integer.valueOf(intFromByteWithBit2), 1, 2);
            GeneralAirData.front_right_blow_foot = compare(Integer.valueOf(intFromByteWithBit2), 2, 3) || boolBit4;
            GeneralAirData.front_right_blow_window = boolBit4;
        }
        updateAirActivity(this.mContext, airWhat);
    }

    private void set0x29TrackData() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 380, 12);
        updateParkUi(null, this.mContext);
    }

    private void set0x30VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x31AmplifierData() {
        GeneralAmplifierData.frontRear = resolveAmpData(2, 4) - 7;
        GeneralAmplifierData.leftRight = resolveAmpData(2, 0) - 7;
        GeneralAmplifierData.bandBass = resolveAmpData(3, 4) - 2;
        GeneralAmplifierData.bandTreble = resolveAmpData(3, 0) - 2;
        GeneralAmplifierData.bandMiddle = resolveAmpData(4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        updateAmplifierActivity(null);
        saveAmplifierData(this.mContext, this.mCanId);
        setSettingData(new String[]{"_186_asl", "_18_surround"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))});
    }

    private void set0x32SystemInfo() {
        if (isPanoramicStatusChange()) {
            forceReverse(this.mContext, this.mPanoramicStatusNow);
        }
        setSettingData(new String[]{"amplifier_switch"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))});
    }

    private void set0x1FHybrid() {
        SharePreUtil.setBoolValue(this.mContext, SHARE_165_IS_SUPPOT_HYBRID, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        updateHybirdActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        if (iArr.length > 4) {
            setSettingData(new String[]{"_165_eco_mode", "_165_ev_mode"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[4], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))});
        }
    }

    private void set0x1ERearRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
            String[] strArr = {"_283_rear_radar_alert_distance", "_283_front_radar_alert_distance", "_283_radar_switch", "radar_volume"};
            Object[] objArr = new Object[4];
            objArr[0] = Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1));
            objArr[1] = Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1));
            objArr[2] = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]) ? "open" : "close";
            objArr[3] = Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3));
            setSettingData(strArr, objArr);
        }
    }

    private void set0x1DFrontRadarData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x50EnginSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, sb.append(iArr[2] | (iArr[3] << 8)).append(" rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x16VehicleSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat0p0;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append(decimalFormat.format((iArr[2] | (iArr[3] << 8)) / 16.0f)).append(" km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void set0x35SeatHeatStatus(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            GeneralAirData.front_left_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4), 4);
            GeneralAirData.front_left_seat_cold_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4), 4);
            GeneralAirData.front_right_seat_heat_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 4);
            GeneralAirData.front_right_seat_cold_level = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4), 4);
            updateAirActivity(context, 1001);
        }
    }

    private void set0x66CeilingScreen() {
        GeneralOriginalCarDeviceData.cdStatus = getPlayMode();
        GeneralOriginalCarDeviceData.runningState = getMediaStatus();
        GeneralOriginalCarDeviceData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralOriginalCarDeviceData.lock = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        updateOriginalCarDeviceActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private String getDriveData(int i, int i2, String str) {
        int i3 = (i * 256) + i2;
        return i3 == 65535 ? " " : i3 + str;
    }

    private String resolveOutDoorTemperature(Context context) {
        float f = (this.mCanBusInfoInt[7] / 2.0f) - 40.0f;
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((f * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(context);
        }
        return f + getTempUnitC(context);
    }

    private String resolveAirTemp(Context context, int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 31) {
            return "HIGH";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            if (i >= 1 && i <= 29) {
                return (i + 64) + getTempUnitF(context);
            }
            if (i >= 33 && i <= 38) {
                return (i + 26) + getTempUnitF(context);
            }
        } else {
            if (i >= 1 && i <= 29) {
                return ((i + 35) / 2.0f) + getTempUnitC(context);
            }
            if (i >= 33 && i <= 38) {
                return ((i - 3) / 2.0f) + getTempUnitC(context);
            }
        }
        return " ";
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        int i2 = iArr.length > 11 ? iArr[11] : 0;
        int i3 = iArr[6];
        int[] iArr2 = {iArr[2], iArr[3] & 239, iArr[4], iArr[5], i3 & 245, i2};
        int[] iArr3 = {i3 & 2, iArr[8], iArr[9], iArr[10]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 4);
        return i;
    }

    private boolean compare(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mSubSeatBeltStatus == GeneralDoorData.isSubSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mSubSeatBeltStatus = GeneralDoorData.isSubSeatBeltTie;
        return true;
    }

    private boolean isPanoramicStatusChange() {
        boolean z = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (this.mPanoramicStatusNow == z) {
            return false;
        }
        this.mPanoramicStatusNow = z;
        return true;
    }

    private int resolveAmpData(int i, int i2) {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i], i2, 4);
    }

    private String getPlayMode() {
        int i = this.mCanBusInfoInt[2];
        if (i == 255) {
            return "UNKNOW";
        }
        switch (i) {
            case 0:
                return "OFF";
            case 1:
                return "DISC";
            case 2:
                return "DISC CD";
            case 3:
                return "DISC DVD";
            case 4:
                return "SD";
            case 5:
                return "USB";
            case 6:
                return "A/V";
            default:
                return " ";
        }
    }

    private String getMediaStatus() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        if (intFromByteWithBit == 15) {
            return "ERROR " + (this.mCanBusInfoInt[3] & 15);
        }
        switch (intFromByteWithBit) {
            case 0:
                return "IDLE";
            case 1:
                return "LOAD";
            case 2:
                return "WAIT";
            case 3:
                return "DISC READING";
            case 4:
                return "PLAY";
            case 5:
                return "EJECT";
            case 6:
                return "PAUSE";
            case 7:
                return "STOP";
            case 8:
                return "UNRECOGNIZED";
            default:
                return " ";
        }
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void initHandler(final Context context) {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._165.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 2) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = i;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void intiSettingItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                String titleSrn = itemListBean.getTitleSrn();
                if (itemListBean.getStyle() == 2) {
                    this.mSettingItemIndeHashMap.put(titleSrn, new SettingProgressItem(titleSrn, i, i2, itemListBean.getMin()));
                } else {
                    this.mSettingItemIndeHashMap.put(titleSrn, new SettingNormalItem(titleSrn, i, i2));
                }
            }
        }
    }

    private void setSettingData(String[] strArr, Object[] objArr) {
        if (strArr.length == objArr.length) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < strArr.length; i++) {
                if (this.mSettingItemIndeHashMap.containsKey(strArr[i])) {
                    arrayList.add(this.mSettingItemIndeHashMap.get(strArr[i]).getEntity(objArr[i]));
                }
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            return;
        }
        Log.i("_165_MsgMgr", "setSettingData: Unequal length");
    }

    private abstract class SettingItem {
        private int left;
        private int right;
        private String title;

        public abstract SettingUpdateEntity getEntity(Object obj);

        public SettingItem(String str, int i, int i2) {
            this.title = str;
            this.left = i;
            this.right = i2;
        }

        public String getTitle() {
            return this.title;
        }

        public int getLeft() {
            return this.left;
        }

        public int getRight() {
            return this.right;
        }
    }

    private class SettingProgressItem extends SettingItem {
        private int min;

        public SettingProgressItem(String str, int i, int i2, int i3) {
            super(str, i, i2);
            this.min = i3;
        }

        @Override // com.hzbhd.canbus.car._165.MsgMgr.SettingItem
        public SettingUpdateEntity getEntity(Object obj) {
            return new SettingUpdateEntity(getLeft(), getRight(), obj).setProgress(((Integer) obj).intValue() - this.min);
        }
    }

    private class SettingNormalItem extends SettingItem {
        public SettingNormalItem(String str, int i, int i2) {
            super(str, i, i2);
        }

        @Override // com.hzbhd.canbus.car._165.MsgMgr.SettingItem
        public SettingUpdateEntity getEntity(Object obj) {
            return new SettingUpdateEntity(getLeft(), getRight(), obj);
        }
    }

    private void startTireActivity(Context context) {
        if (SystemUtil.isForeground(context, Constant.TireInfoActivity.getClassName())) {
            return;
        }
        Intent intent = new Intent();
        intent.setComponent(Constant.TireInfoActivity);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }
}
