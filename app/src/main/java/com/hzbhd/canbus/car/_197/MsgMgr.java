package com.hzbhd.canbus.car._197;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.view.MotionEventCompat;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.MainAction;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static final int AMPLIFIER_PARAM_BASS_OFFSET = 2;
    static final int AMPLIFIER_PARAM_HALF_FAD = 7;
    static final String SHARE_197_IS_HYBIRD_VEHICLE = "share_197_is_hybird_vehicle";
    static final int VEHICLE_TYPE_CIVIC_AUTO_AC = 17;
    static final int VEHICLE_TYPE_CIVIC_MANUAL_AC = 16;
    static final int VEHICLE_TYPE_ELANTRA_AUTO_AC = 33;
    static final int VEHICLE_TYPE_ELANTRA_MANUAL_AC = 32;
    static final int VEHICLE_TYPE_TOYOTA = 1;
    private String[] m0x26ItemTitleArray;
    private String[] m0x31ItemTitleArray;
    private String[] m0x32ItemTitleArray;
    private String[] m0x37ItemTitleArray;
    private int[] m0x56Data;
    private int m0xC1Data0;
    private int m0xC1Data0Record;
    private boolean mBackStatus;
    private boolean mBeltStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private DecimalFormat mDecimalFormat0p0;
    private int mDifferentId;
    private int mEachId;
    private boolean mHandBreakStatus;
    private Handler mHandler;
    private boolean mIsLeftRudder;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private ID3[] mMeidaInfos;
    private int mOutDoorTemperature;
    private boolean mRaerLockStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private final String TAG = "_197_MsgMgr";
    private final int INVAILE_VALUE = -1;
    private final String TAG_MEDIA_INFO_DEBUG = "_197_media_debug";
    private final int MEDIA_SOURCE_ID_OFF = 0;
    private final int MEDIA_SOURCE_ID_TUNER = 1;
    private final int MEDIA_SOURCE_ID_DISC = 2;
    private final int MEDIA_SOURCE_ID_TV = 3;
    private final int MEDIA_SOURCE_ID_PHONE = 5;
    private final int MEDIA_SOURCE_ID_AUX = 7;
    private final int MEDIA_SOURCE_ID_USB = 8;
    private final int MEDIA_SOURCE_ID_SD = 9;
    private final int MEDIA_SOURCE_ID_DVBT = 10;
    private final int MEDIA_SOURCE_ID_A2DP = 11;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
    private final int SEND_NORMAL_MESSAGE = 102;
    private final int DRIVE_DATA_PAGE_0X21_0x22 = 0;
    private final int DRIVE_DATA_PAGE_0X23 = 1;
    private final int DRIVE_DATA_PAGE_0X27 = 2;
    private final int DRIVE_DATA_PAGE_0X35_0X34 = 3;
    private HashMap<String, Integer> mSettingItemIndeHashMap = new HashMap<>();

    private String getFuelUnit(int i) {
        return i == 0 ? "mpg" : i == 1 ? "km/l" : i == 2 ? "l/100km" : "";
    }

    private String getMileageUnit(int i) {
        return i == 1 ? "mile" : i == 2 ? "km" : "";
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        Log.i("_197_MsgMgr", "CanbusMsgService initCommand: 197");
        initAmplifier(context);
        sendVehicleTypeCommand();
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._197.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}}).iterator();

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

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        Log.i("_197_MsgMgr", "CanbusMsgService afterServiceNormalSetting: ");
        this.mContext = context;
        this.mDifferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mIsLeftRudder = (this.mDifferentId & NfDef.STATE_3WAY_M_HOLD) == 32 && (this.mEachId & 128) == 0;
        initID3();
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowHandBrake = true;
        initData();
        getUiMgr().updateUiByDifferentCar(context);
        initSettingsItem(getUiMgr().getSettingUiSet(context));
        RadarInfoUtil.mMinIsClose = true;
        initHandler(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 38) {
            setVehicleSettings0x26();
        }
        if (i == 39) {
            setTripInfo0x27();
            return;
        }
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 85) {
            setAirData0x55();
            return;
        }
        switch (i) {
            case 29:
                setFrontRadarData0x1D();
                break;
            case 30:
                setRearRadarData0x1E();
                break;
            case 31:
                setHybirdData0x1F();
                break;
            case 32:
                setWheelKey0x20();
                break;
            case 33:
                setTripInfo0x21();
                break;
            case 34:
                setTripInfo0x22();
                break;
            case 35:
                setTripInfo0x23();
                break;
            case 36:
                setBaseInfo0x24();
                break;
            default:
                switch (i) {
                    case 48:
                        setVersionInfo0x30();
                        break;
                    case 49:
                        setAmplifierData0x31();
                        break;
                    case 50:
                        setSystemInfo0x32();
                        break;
                    default:
                        switch (i) {
                            case 52:
                                setOdoMileageData0x34();
                                break;
                            case 53:
                                setVehicleInfo0x35();
                                break;
                            case 54:
                                setMediaSwitch0x36();
                                break;
                            case 55:
                                setCeilingScreenData0x37();
                                break;
                        }
                }
        }
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick1(0);
            return;
        }
        if (i == 1) {
            realKeyClick1(7);
            return;
        }
        if (i == 2) {
            realKeyClick1(8);
            return;
        }
        if (i == 3) {
            realKeyClick1(48);
            return;
        }
        if (i == 4) {
            realKeyClick1(47);
            return;
        }
        if (i == 9) {
            realKeyClick1(14);
            return;
        }
        if (i == 10) {
            realKeyClick1(15);
            return;
        }
        if (i != 136) {
            switch (i) {
                case 19:
                    realKeyClick1(45);
                    break;
                case 20:
                    realKeyClick1(46);
                    break;
                case 21:
                    realKeyClick1(50);
                    break;
                case 22:
                    realKeyClick1(49);
                    break;
            }
            return;
        }
        realKeyClick1(2);
    }

    private void setTripInfo0x21() {
        String mileageUnit = getMileageUnit(this.mCanBusInfoInt[8]);
        int[] iArr = this.mCanBusInfoInt;
        String str = (DataHandleUtils.rangeNumber(getData(iArr[3], iArr[2]), 0, 9999) * 0.1f) + " " + getSpeedUnit(mileageUnit);
        int[] iArr2 = this.mCanBusInfoInt;
        String timeInfo = getTimeInfo(getTimeWithMinutes(DataHandleUtils.rangeNumber(getData(iArr2[5], iArr2[4]), 0, 5999)));
        int[] iArr3 = this.mCanBusInfoInt;
        String str2 = DataHandleUtils.rangeNumber(getData(iArr3[7], iArr3[6]), 0, 9999) + " " + mileageUnit;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, str));
        arrayList.add(new DriverUpdateEntity(0, 1, timeInfo));
        arrayList.add(new DriverUpdateEntity(0, 2, str2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTripInfo0x22() {
        String str = " " + getFuelUnit(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 3, (getData(iArr[4], iArr[3]) * 0.1f) + str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTripInfo0x23() {
        String str = " " + getFuelUnit(this.mCanBusInfoInt[2]);
        int[] iArr = this.mCanBusInfoInt;
        String info = getInfo(getData(iArr[4], iArr[3]), 0.1f, str);
        int[] iArr2 = this.mCanBusInfoInt;
        String info2 = getInfo(getData(iArr2[6], iArr2[5]), 0.1f, str);
        int[] iArr3 = this.mCanBusInfoInt;
        String info3 = getInfo(getData(iArr3[8], iArr3[7]), 0.1f, str);
        int[] iArr4 = this.mCanBusInfoInt;
        String info4 = getInfo(getData(iArr4[10], iArr4[9]), 0.1f, str);
        int[] iArr5 = this.mCanBusInfoInt;
        String info5 = getInfo(getData(iArr5[12], iArr5[11]), 0.1f, str);
        int[] iArr6 = this.mCanBusInfoInt;
        String info6 = getInfo(getData(iArr6[14], iArr6[13]), 0.1f, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, info));
        arrayList.add(new DriverUpdateEntity(1, 1, info2));
        arrayList.add(new DriverUpdateEntity(1, 2, info3));
        arrayList.add(new DriverUpdateEntity(1, 3, info4));
        arrayList.add(new DriverUpdateEntity(1, 4, info5));
        arrayList.add(new DriverUpdateEntity(1, 5, info6));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setBaseInfo0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setVehicleSettings0x26() {
        Object[] objArr = {Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = this.m0x26ItemTitleArray;
            if (i < strArr.length) {
                String str = strArr[i];
                int[] leftAndRight = getLeftAndRight(str);
                if (leftAndRight[0] != -1 && leftAndRight[1] != -1) {
                    Object obj = objArr[i];
                    str.hashCode();
                    if (str.equals("_55_0x67_data1_bit64") || str.equals("_197_lock_unlcok_feedback_tone")) {
                        arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], obj).setProgress(((Integer) obj).intValue()));
                    } else {
                        arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], obj));
                    }
                }
                i++;
            } else {
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                return;
            }
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setTripInfo0x27() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            arrayList.add(new DriverUpdateEntity(2, 14 - i, getInfo(getData(iArr[i2 + 4], iArr[i2 + 3]), 0.1f, " " + fuelUnit)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x55() {
        setOutDoorTemperature();
        byte[] bArr = this.mCanBusInfoByte;
        bArr[7] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[2]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
        GeneralAirData.front_left_auto_wind = intFromByteWithBit == 0;
        GeneralAirData.front_left_blow_window = isWindModeMatch(intFromByteWithBit, 4, 6);
        GeneralAirData.front_left_blow_head = isWindModeMatch(intFromByteWithBit, 1, 2);
        GeneralAirData.front_left_blow_foot = isWindModeMatch(intFromByteWithBit, 2, 3, 4);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.windshield_deicing = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        dealRightBlowMode();
        GeneralAirData.power = GeneralAirData.front_wind_level != 0;
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearAirInfo0x56() {
        int[] iArr = this.mCanBusInfoInt;
        iArr[6] = iArr[6] & com.hzbhd.canbus.car._0.MsgMgr.RADIO_MODE;
        if (is0x56DataChange()) {
            GeneralAirData.rear_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[2]);
            GeneralAirData.rear_right_temperature = GeneralAirData.rear_left_temperature;
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
            GeneralAirData.rear_left_auto_wind = intFromByteWithBit == 0;
            GeneralAirData.rear_right_auto_wind = intFromByteWithBit == 0;
            GeneralAirData.rear_left_blow_head = intFromByteWithBit == 1 || intFromByteWithBit == 2;
            GeneralAirData.rear_right_blow_head = intFromByteWithBit == 1 || intFromByteWithBit == 2;
            GeneralAirData.rear_left_blow_foot = intFromByteWithBit == 2 || intFromByteWithBit == 3;
            GeneralAirData.rear_right_blow_foot = intFromByteWithBit == 2 || intFromByteWithBit == 3;
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
            updateAirActivity(this.mContext, 1002);
        }
    }

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, HotKeyConstant.K_AIR_WIND_INC, 12);
        updateParkUi(null, this.mContext);
    }

    private void setAmplifierData0x31() {
        GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        GeneralAmplifierData.frontRear -= 7;
        GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAmplifierData.leftRight -= 7;
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        GeneralAmplifierData.bandBass -= 2;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAmplifierData.bandTreble -= 2;
        GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
        GeneralAmplifierData.bandMiddle -= 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        saveAmplifierData(this.mContext, this.mCanId);
        updateAmplifierActivity(null);
        Object[] objArr = {Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) / 8), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = this.m0x31ItemTitleArray;
            if (i < strArr.length) {
                int[] leftAndRight = getLeftAndRight(strArr[i]);
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], objArr[i]));
                i++;
            } else {
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                return;
            }
        }
    }

    private void setSystemInfo0x32() {
        Object[] objArr = {Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = this.m0x32ItemTitleArray;
            if (i < strArr.length) {
                int[] leftAndRight = getLeftAndRight(strArr[i]);
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], objArr[i]));
                i++;
            } else {
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                return;
            }
        }
    }

    private void setHybirdData0x1F() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        SharePreUtil.setBoolValue(this.mContext, SHARE_197_IS_HYBIRD_VEHICLE, boolBit7);
        getUiMgr().updateMainActivityItem(this.mContext, MainAction.HYBIRD, boolBit7);
        if (boolBit7) {
            GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            updateHybirdActivity(null);
        }
    }

    private void setRearRadarData0x1E() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarData0x1D() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setVehicleInfo0x35() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 0, sb.append(getData(iArr[3], iArr[2])).append(" rpm").toString()));
        arrayList.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[4] + " km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setOdoMileageData0x34() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 2, sb.append(getData(iArr[4], iArr[3], iArr[2])).append(" km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setMediaSwitch0x36() {
        try {
            switch (this.mCanBusInfoInt[2]) {
                case 0:
                    realKeyClick4(77);
                    break;
                case 1:
                    realKeyClick4(76);
                    break;
                case 2:
                    realKeyClick4(130);
                    break;
                case 3:
                    realKeyClick4(139);
                    break;
                case 4:
                    realKeyClick4(140);
                    break;
                case 5:
                    realKeyClick4(141);
                    break;
                case 6:
                    realKeyClick4(142);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCeilingScreenData0x37() {
        Object[] objArr = {Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 1, 1))};
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            String[] strArr = this.m0x37ItemTitleArray;
            if (i < strArr.length) {
                int[] leftAndRight = getLeftAndRight(strArr[i]);
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], objArr[i]));
                i++;
            } else {
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                return;
            }
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            Log.i("_197_media_debug", "sourceSwitchNoMediaInfoChange: ");
            sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, 0, (byte) 0}, new byte[]{0, 0, 0, 0, 0, 0}));
            this.mMeidaInfos[0].setInfo(" ");
            this.mMeidaInfos[1].setInfo(" ");
            this.mMeidaInfos[2].setInfo(" ");
            reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        Log.i("_197_media_debug", "radioInfoChange: ");
        int[] redioFrequencyHiLo = getRedioFrequencyHiLo(str, str2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 1, (byte) 1}, new byte[]{(byte) getRadioBandData(str), (byte) redioFrequencyHiLo[1], (byte) redioFrequencyHiLo[0], (byte) i, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        Log.i("_197_media_debug", "discInfoChange: ");
        if (i6 == 1) {
            i3 = i4;
        }
        int[] timeWithSeconds = getTimeWithSeconds(i);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 2, (byte) 255}, new byte[]{0, (byte) i3, (byte) i5, (byte) timeWithSeconds[0], (byte) timeWithSeconds[1], (byte) timeWithSeconds[2]}));
        this.mMeidaInfos[0].setInfo(str);
        this.mMeidaInfos[1].setInfo(str2);
        this.mMeidaInfos[2].setInfo(str3);
        reportID3Info(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected void sendDiscEjectMsg(Context context) {
        super.sendDiscEjectMsg(context);
        Log.i("_197_media_debug", "sendDiscEjectMsg: ");
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        Log.i("_197_media_debug", "atvInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 3, (byte) 34}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        Log.i("_197_media_debug", "btPhoneIncomingInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(new String(bArr));
        this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "incoming") + "...");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        Log.i("_197_media_debug", "btPhoneOutGoingInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(new String(bArr));
        this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "outgoing") + "...");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        Log.i("_197_media_debug", "btPhoneTalkingWithTimeInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(new String(bArr));
        this.mMeidaInfos[1].setInfo(CommUtil.getStrByResId(this.mContext, "talking") + "...");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        Log.i("_197_media_debug", "btPhoneHangUpInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 5, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        this.m0xC1Data0 = DataHandleUtils.setIntByteWithBit(this.m0xC1Data0, 4, z);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        Log.i("_197_media_debug", "auxInInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 7, (byte) 48}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        Log.i("_197_media_debug", "musicInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), (byte) 255}, new byte[]{(byte) i, b7, 0, b3, b4, b5}));
        this.mMeidaInfos[0].setInfo(str4);
        this.mMeidaInfos[1].setInfo(str5);
        this.mMeidaInfos[2].setInfo(str6);
        reportID3Info(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        Log.i("_197_media_debug", "musicDestroy: ");
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        Log.i("_197_media_debug", "videoInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), (byte) 19}, new byte[]{(byte) i, b6, 0, b3, b4, b5}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        Log.i("_197_media_debug", "dtvInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 10, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        Log.i("_197_media_debug", "btMusicInfoChange: ");
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 11, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        Log.i("_197_media_debug", "btMusicId3InfoChange: ");
        this.mMeidaInfos[0].setInfo(str);
        this.mMeidaInfos[1].setInfo(str2);
        this.mMeidaInfos[2].setInfo(str3);
        reportID3Info(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        Log.i("_197_media_debug", "btMusiceDestdroy: ");
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        int intFromByteWithBit = DataHandleUtils.setIntFromByteWithBit(this.m0xC1Data0, i4, 2, 1);
        this.m0xC1Data0 = intFromByteWithBit;
        this.m0xC1Data0 = DataHandleUtils.setIntFromByteWithBit(intFromByteWithBit, i6, 3, 1);
        send0xC1Data();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48});
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
    }

    private String getSpeedUnit(String str) {
        return TextUtils.isEmpty(str) ? "" : str + "/h";
    }

    private int getData(int... iArr) {
        int i = 0;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            i += iArr[i2] << (i2 * 8);
        }
        if (i == Math.pow(2.0d, iArr.length * 8) - 1.0d) {
            return -1;
        }
        return i;
    }

    private String getInfo(int i, float f, String str) {
        return i == -1 ? "" : this.mDecimalFormat0p0.format(i * f) + str;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mHandBreakStatus == GeneralDoorData.isHandBrakeUp && this.mBeltStatus == GeneralDoorData.isSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mHandBreakStatus = GeneralDoorData.isHandBrakeUp;
        this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
        return true;
    }

    private String resolveAirTemperature(int i) {
        return i == 16 ? "LOW" : i == 80 ? "HIGH" : (DataHandleUtils.rangeNumber(i, 32, 64) / 2.0f) + getTempUnitC(this.mContext);
    }

    private boolean isWindModeMatch(int i, int... iArr) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    private void setOutDoorTemperature() {
        int i = this.mOutDoorTemperature;
        int i2 = this.mCanBusInfoInt[7];
        if (i != i2) {
            this.mOutDoorTemperature = i2;
            updateOutDoorTemp(this.mContext, resolveOutDoorTemperature(i2));
        }
    }

    private String resolveOutDoorTemperature(int i) {
        return i == 255 ? " " : (i - 40) + getTempUnitC(this.mContext);
    }

    private void dealRightBlowMode() {
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        boolean z = false;
        if (this.mDifferentId == 1) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4);
            if (intFromByteWithBit == 0 && !GeneralAirData.front_right_blow_window) {
                z = true;
            }
            GeneralAirData.front_right_auto_wind = z;
            GeneralAirData.front_right_blow_head = isWindModeMatch(intFromByteWithBit, 1, 2);
            GeneralAirData.front_right_blow_foot = isWindModeMatch(intFromByteWithBit, 2, 3);
            return;
        }
        if (GeneralAirData.front_left_auto_wind && !GeneralAirData.front_right_blow_window) {
            z = true;
        }
        GeneralAirData.front_right_auto_wind = z;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean is0x56DataChange() {
        if (Arrays.equals(this.m0x56Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x56Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private int getRadioBandData(String str) {
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

    private int[] getRedioFrequencyHiLo(String str, String str2) {
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

    private int[] getTimeWithSeconds(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private int[] getTimeWithMinutes(int i) {
        return new int[]{i / 60, i % 60};
    }

    private String getTimeInfo(int[] iArr) {
        if (iArr.length == 0) {
            return "";
        }
        String str = iArr.length > 0 ? "" + iArr[0] + CommUtil.getStrByResId(this.mContext, "_197_hour") : "";
        if (iArr.length > 1) {
            str = str + iArr[1] + CommUtil.getStrByResId(this.mContext, "_197_minute");
        }
        return iArr.length > 2 ? str + iArr[1] + CommUtil.getStrByResId(this.mContext, "_197_second") : str;
    }

    private static class ID3 {
        private final int ID3_INFO_LENGTH;
        private int dataType;
        private String info;
        private String record;

        private ID3(int i) {
            this.ID3_INFO_LENGTH = 24;
            this.dataType = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            this.record = this.info;
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }

        public void setInfo(String str) {
            this.info = str;
            if (str.length() > 24) {
                this.info = this.info.substring(0, 21) + "...";
            }
        }
    }

    private void initID3() {
        this.mMeidaInfos = new ID3[]{new ID3(112), new ID3(113), new ID3(114)};
    }

    private void reportID3Info(int i, ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(i, id32.dataType, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, int i2, String str) {
        byte[] bArrExceptBOMHead = new byte[0];
        try {
            bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str.getBytes("UnicodeLittle"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(new byte[]{22, (byte) i2, 17}, bArrExceptBOMHead);
        if (i == SourceConstantsDef.SOURCE_ID.NULL.ordinal()) {
            sendNormalMessage(bArrByteMerger);
        } else {
            sendMediaMessage(i, bArrByteMerger);
        }
    }

    private void send0xC1Data() {
        int intByteWithBit = DataHandleUtils.setIntByteWithBit(this.m0xC1Data0, 6, FutureUtil.instance.isHaveAtv() || FutureUtil.instance.isHaveDtv());
        this.m0xC1Data0 = intByteWithBit;
        if (this.m0xC1Data0Record != intByteWithBit) {
            this.m0xC1Data0Record = intByteWithBit;
            CanbusMsgSender.sendMsg(new byte[]{22, -63, (byte) intByteWithBit});
        }
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    int[] getLeftAndRight(String str) {
        int[] iArr = new int[2];
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            int iIntValue = this.mSettingItemIndeHashMap.get(str).intValue();
            iArr[0] = iIntValue >> 8;
            iArr[1] = iIntValue & 255;
        } else {
            iArr[0] = -1;
            iArr[1] = -1;
        }
        Log.i("ljq", "getLeftAndRigth: left:" + iArr[0] + ", right:" + iArr[1]);
        return iArr;
    }

    private void initData() {
        this.m0x26ItemTitleArray = new String[]{"geely_daytime_running_lights", "_55_0x67_data1_bit64", "_18_vehicle_setting_item_3_43", "_55_0x67_data1_bit32", "_197_autolock_by_speed", "_197_autolock_by_shift_from_p", "_197_autolock_by_shift_to_p", "_197_remote_2_press_unlock", "_197_lock_unlcok_feedback_tone", "_197_unlock_when_operating_key_twice", "_197_Driver_seat_open_door_linkage_unlock", "_197_smart_car_door_unlock", "_197_smart_car_lock_and_one_button_start", "_197_emergency_flashing_light_response_when_unlocking_locking", "_197_automatic_relock_time", "_197_linkage_of_air_conditioner_and_auto_key", "_197_linkage_between_internal_and_external_circulation_and_auto_key", "_197_rear_camera_guide"};
        this.m0x31ItemTitleArray = new String[]{"_186_asl", "_118_setting_title_96"};
        this.m0x32ItemTitleArray = new String[]{"amplifier_switch", "_197_amplifier_mute"};
        this.m0x37ItemTitleArray = new String[]{"_197_rear_system", "_197_rear_system_lock"};
        this.m0xC1Data0 = 35;
        this.m0xC1Data0Record = 35;
        this.mDecimalFormat0p0 = new DecimalFormat("0.0");
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        Log.i("ljq", "initSettingsItem: ");
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingPageUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), Integer.valueOf(((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | i2));
            }
        }
    }

    private void initHandler(final Context context) {
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._197.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 101) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 102) {
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
        messageObtain.what = 102;
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
        messageObtain.what = 101;
        messageObtain.arg1 = i;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void sendVehicleTypeCommand() {
        Log.i("CanbusMsgService", "sendVehicleTypeCommand: mDifferentId:" + this.mDifferentId);
        int i = this.mDifferentId;
        if (i == 32 || i == 33) {
            Log.i("CanbusMsgService", "sendVehicleTypeCommand: mEachId:" + this.mEachId);
            CanbusMsgSender.sendMsg(new byte[]{22, -30, (byte) this.mEachId});
        }
    }
}
