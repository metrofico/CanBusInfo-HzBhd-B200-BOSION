package com.hzbhd.canbus.car._219;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.vectordrawable.graphics.drawable.PathInterpolatorCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.HzbhdLog;
import com.hzbhd.canbus.adapter.util.SystemConstant;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MathUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.FgeString;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_123_OUTDOOR_TEMPERATURE_UNIT = "share_123_outdoor_temperature_unit";
    private static String TAG = "MsgMgr";
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x25Data;
    private int[] m0x29Data;
    private int[] m0x30Data;
    private int[] m0x32Data;
    private int[] m0x33DataIndexOne;
    private int[] m0x33DataIndexTwo;
    private int[] m0xD1Data;
    private int[] m0xD2Data;
    private int[] m0xD8Data;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private DecimalFormat mDecimalFormat;
    private DecimalFormat mDecimalFormat0p0;
    private ID3[] mDiscId3s;
    private boolean mFrontStatus;
    private boolean mFrontViewBtnStatus;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private ID3[] mMusicId3s;
    private int mPageIndex = 0;
    private boolean mPanoramicStatusNow;
    private int mRadioBand;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private TimerUtil mTimerUtil;
    private boolean mTurnSignalStatus;

    private String getDistanceUnit(int i) {
        return i != 0 ? i != 1 ? "" : " mile" : " km";
    }

    private String getFuelUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : " l/100km" : " km/l" : " mpg";
    }

    private int getRange(int i) {
        switch (i) {
            case 0:
                return 60;
            case 1:
                return 10;
            case 2:
                return 12;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
                return (i - 1) * 10;
            default:
                return 0;
        }
    }

    private void setBackLightStatus0x14() {
    }

    private void setCarStatus0xD0() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        this.mDecimalFormat0p0 = new DecimalFormat("0.0");
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mRadioBand = 1;
        this.mDecimalFormat = new DecimalFormat("0.0");
        requestData();
        initID3();
        initAmplifierData(context);
    }

    private void initAmplifierData(Context context) {
        boolean boolValue = false;
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_VOL", 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_FR", 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_LR", 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_TRE", 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, "__162_SAVE_AMP_BASS", 0);
            boolValue = SharePreUtil.getBoolValue(this.mContext, "CAN_162_LOUD", false);
        }
        TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask(boolValue, timerUtil) { // from class: com.hzbhd.canbus.car._219.MsgMgr.1
            Iterator<byte[]> iterator;
            final /* synthetic */ boolean val$finalParamBool;
            final /* synthetic */ TimerUtil val$util;

            {
                this.val$finalParamBool = boolValue;
                this.val$util = timerUtil;
                this.iterator = Arrays.stream(new byte[][]{new byte[]{22, 9, 19, (byte) GeneralAmplifierData.bandBass, (byte) (GeneralAmplifierData.frontRear + 7), (byte) (GeneralAmplifierData.leftRight + 7), (byte) GeneralAmplifierData.bandTreble, 0}, new byte[]{22, 9, 15, (byte) GeneralAmplifierData.volume}, new byte[]{22, 9, 17, boolValue ? (byte) 1 : (byte) 0}}).iterator();
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (this.iterator.hasNext()) {
                    CanbusMsgSender.sendMsg(this.iterator.next());
                } else {
                    this.val$util.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 2) {
            setData0x02();
            return;
        }
        if (i == 8) {
            setData0x08();
            return;
        }
        if (i == 41) {
            setData0x29();
            return;
        }
        if (i != 56) {
            switch (i) {
                case 51:
                    setDriveData0x33();
                    break;
                case 52:
                    setDriveData0x34();
                    break;
                case 53:
                    setDriveData0x35();
                    break;
                case 54:
                    setData0x36();
                    break;
            }
            return;
        }
        setData0x38();
    }

    public void setData0x01() {
        HzbhdLog.d(TAG, "setData0x01 " + this.mCanBusInfoInt[2]);
    }

    public void setData0x02() {
        HzbhdLog.d(TAG, KeysMap.keymaps.get(Integer.valueOf(this.mCanBusInfoInt[2])) + " ,  " + this.mCanBusInfoInt[2]);
        if (KeysMap.keymaps.containsKey(Integer.valueOf(this.mCanBusInfoInt[2]))) {
            realKeyClick2(KeysMap.keymaps.get(Integer.valueOf(this.mCanBusInfoInt[2])).intValue());
        }
    }

    public void setData0x08() {
        HzbhdLog.d(TAG, "setData0x08 " + this.mCanBusInfoInt[2]);
        if (is0x08DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            int i = iArr[2];
            int i2 = iArr[3];
            int i3 = iArr[4];
            RadarInfoUtil.setFrontRadarLocationData(5, i, i2, i3, i3);
            int[] iArr2 = this.mCanBusInfoInt;
            int i4 = iArr2[5];
            int i5 = iArr2[6];
            int i6 = iArr2[7];
            RadarInfoUtil.setRearRadarLocationData(5, i4, i5, i6, i6);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    public void setData0x29() {
        HzbhdLog.d(TAG, "setData0x29 " + this.mCanBusInfoInt[2]);
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 5450, 16);
            updateParkUi(null, this.mContext);
        }
    }

    public void setData0x33() {
        HzbhdLog.d(TAG, "setData0x33 " + this.mCanBusInfoInt[2]);
    }

    public void setData0x34() {
        HzbhdLog.d(TAG, "setData0x34 " + this.mCanBusInfoInt[2]);
    }

    public void setData0x35() {
        HzbhdLog.d(TAG, "setData0x35 " + this.mCanBusInfoInt[2]);
    }

    public void setData0x36() {
        HzbhdLog.d(TAG, "setData0x36 " + this.mCanBusInfoInt[2]);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    public void setData0x38() {
        HzbhdLog.d(TAG, "setData0x38 " + this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        this.mPageIndex = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        updateDoorView(this.mContext);
    }

    private boolean isPanoramicStatusChange() {
        boolean z = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (this.mPanoramicStatusNow == z) {
            return false;
        }
        this.mPanoramicStatusNow = z;
        return true;
    }

    private String resolveOutDoorTem() {
        GeneralAirData.fahrenheit_celsius = SharePreUtil.getBoolValue(this.mContext, SHARE_123_OUTDOOR_TEMPERATURE_UNIT, false);
        return ((DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1) == 1 ? -1 : 1) * DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6)) + getTempUnitC(this.mContext);
    }

    private void setCarSystemStatus0x40() {
        if (isPanoramicStatusChange()) {
            forceReverse(this.mContext, this.mPanoramicStatusNow);
        }
        String str = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? "_288_0x31_setting7" : "_288_0x31_setting8";
        String str2 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? "_288_0x31_setting7" : "_288_0x31_setting8";
        String str3 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]) ? "open" : "close";
        String str4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "open" : "close";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(6, 5, str));
        arrayList.add(new SettingUpdateEntity(6, 6, str2));
        arrayList.add(new SettingUpdateEntity(6, 7, str3));
        arrayList.add(new SettingUpdateEntity(6, 8, str4));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSpeedStatus0x71() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat0p0;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 7, sb.append(decimalFormat.format(iArr[2] | (iArr[3] << 8))).append(" rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarAmplifierStatus0x31() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 8) - 9;
        GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 8) - 9;
        GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
        GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 8);
        GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 8);
        GeneralAmplifierData.megaBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 8);
        saveAmplifierData(this.mContext, this.mCanId);
        updateAmplifierActivity(null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(12, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 8))));
        arrayList.add(new SettingUpdateEntity(12, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 8))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSpeedStatus0x16() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat0p0;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 6, sb.append(decimalFormat.format(iArr[2] | (iArr[3] << 8))).append(" km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setWheelKey0x20() {
        Log.d("mww", "setWheelKey0x20 " + FgeString.bytetoHexString((byte) this.mCanBusInfoInt[2]));
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(0);
            return;
        }
        if (i == 1) {
            realKeyClick2(7);
            return;
        }
        if (i == 2) {
            realKeyClick2(8);
            return;
        }
        if (i == 3) {
            realKeyClick2(48);
            return;
        }
        if (i == 4) {
            realKeyClick2(47);
            return;
        }
        if (i == 19) {
            realKeyClick2(45);
            return;
        }
        if (i == 20) {
            realKeyClick2(46);
            return;
        }
        if (i == 129) {
            realKeyClick2(7);
            return;
        }
        if (i == 130) {
            realKeyClick2(8);
            return;
        }
        if (i != 134) {
            switch (i) {
                case 7:
                    realKeyClick2(2);
                    break;
                case 8:
                    realKeyClick2(HotKeyConstant.K_SPEECH);
                    break;
                case 9:
                    realKeyClick2(14);
                    break;
                case 10:
                    realKeyClick2(HotKeyConstant.K_PHONE_OFF_RETURN);
                    break;
                default:
                    switch (i) {
                        case 22:
                            realKeyClick2(49);
                            break;
                        case 23:
                            realKeyClick2(52);
                            break;
                        case 24:
                            realKeyClick2(21);
                            break;
                        case 25:
                            realKeyClick2(20);
                            break;
                        default:
                            switch (i) {
                                case 145:
                                    realKeyClick2(3);
                                    break;
                                case 146:
                                    realKeyClick2(45);
                                    break;
                                case 147:
                                    realKeyClick2(46);
                                    break;
                                case 148:
                                    realKeyClick2(47);
                                    break;
                                case 149:
                                    realKeyClick2(48);
                                    break;
                                case 150:
                                    Log.d("mww", "22 setWheelKey0x20 " + FgeString.bytetoHexString((byte) this.mCanBusInfoInt[2]));
                                    realKeyClick20x96(this.mCanBusInfoInt[3]);
                                    break;
                            }
                    }
            }
            return;
        }
        realKeyClick2(3);
    }

    private void setAirData0x21() {
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        if (GeneralAirData.climate != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6])) {
            GeneralAirData.climate = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            if (!GeneralAirData.climate) {
                if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
                    finishActivity();
                }
            } else {
                AirActivity.mIsClickOpen = true;
                Intent intent = new Intent(this.mContext, (Class<?>) AirActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.mContext.startActivity(intent);
            }
        }
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[6] = (byte) (bArr[6] & 191);
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        int airWhat = getAirWhat();
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = true ^ DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        if (isThree()) {
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[7]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[7]);
        }
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.manual = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_temperature = resolveAirTemperature(this.mCanBusInfoInt[8]);
        GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 6, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2);
        updateAirActivity(this.mContext, airWhat);
    }

    private void setDoorData0x24() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        this.mRightFrontRec = boolBit7;
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        this.mLeftFrontRec = boolBit6;
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        this.mRightRearRec = boolBit5;
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        this.mLeftRearRec = boolBit4;
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setVersionInfo0x30() {
        if (is0x30DataChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setVehicleSetupData0x32() {
        if (is0x32DataChange()) {
            ArrayList arrayList = new ArrayList();
            switch (this.mCanBusInfoInt.length) {
                case 3:
                    int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit - 5)).setProgress(intFromByteWithBit));
                    break;
                case 4:
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit2 - 5)).setProgress(intFromByteWithBit2));
                    break;
                case 5:
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit22 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit22 - 5)).setProgress(intFromByteWithBit22));
                    break;
                case 6:
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit222 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit222 - 5)).setProgress(intFromByteWithBit222));
                    break;
                case 7:
                    arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit2222 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit2222 - 5)).setProgress(intFromByteWithBit2222));
                    break;
                case 8:
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit22222 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit22222 - 5)).setProgress(intFromByteWithBit22222));
                    break;
                case 10:
                    arrayList.add(new SettingUpdateEntity(5, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(7, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit222222 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit222222 - 5)).setProgress(intFromByteWithBit222222));
                    break;
                case 12:
                    arrayList.add(new SettingUpdateEntity(5, 6, Integer.valueOf(DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 2), 0, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(7, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
                    arrayList.add(new SettingUpdateEntity(4, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
                    arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                    int intFromByteWithBit2222222 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
                    arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit2222222 - 5)).setProgress(intFromByteWithBit2222222));
                    break;
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

    private void setCompassData0xD2() {
        if (is0xD2DataChange()) {
            ArrayList arrayList = new ArrayList();
            String strByResId = CommUtil.getStrByResId(this.mContext, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? "compass_calibrating" : "compass_calibration_finish");
            String string = Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
            arrayList.add(new SettingUpdateEntity(8, 1, strByResId).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(8, 2, string).setValueStr(true).setProgress(Integer.valueOf(string).intValue() - 1));
            arrayList.add(new SettingUpdateEntity(8, 3, ((this.mCanBusInfoInt[3] * 3) / 2.0f) + "°").setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
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

    private void setRearRadarData0x22() {
        if (is0x22DataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setParkAssistData0x25() {
        if (is0x25DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(9, 0, getOpenClose(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(9, 1, getOpenClose(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(9, 2, getOpenClose(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]))).setValueStr(true));
            arrayList.add(new SettingUpdateEntity(9, 3, getOpenClose(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]))).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setCameraStatus0xD1() throws SecurityException {
        if (is0xD1DataChange()) {
            boolean zIsForeground = SystemUtil.isForeground(this.mContext, new String[]{Constant.FCameraActivity.getClassName(), Constant.AuxInActivity.getClassName()});
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            if (this.mFrontViewBtnStatus && !boolBit6) {
                switchFCamera(this.mContext, !zIsForeground);
                zIsForeground = !zIsForeground;
            }
            this.mFrontViewBtnStatus = boolBit6;
            boolean z = ((1 << (SharePreUtil.getBoolValue(this.mContext, "share_41_front_camera_switch", false) ? 3 : 7)) & this.mCanBusInfoInt[3]) != 0;
            if (this.mTurnSignalStatus ^ z) {
                this.mTurnSignalStatus = z;
                if (zIsForeground ^ z) {
                    switchFCamera(this.mContext, z);
                }
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i11 = i5 | 128;
        if (!z) {
            i8 = i11;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -58, 80, (byte) i8, (byte) i6, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 9, 0});
        }
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
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        try {
            Handler.INSTANCE.sendRadioData(str, str2);
        } catch (Exception e) {
            Log.e("radioInfoChange", Log.getStackTraceString(e));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1) {
            i3 = i4;
        }
        int iRangeNumber = DataHandleUtils.rangeNumber(i3, 0, 99);
        int[] time = getTime(i);
        sendMediaMsg(SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[][]{new byte[]{22, 9, 1, (byte) iRangeNumber, 0, 0, (byte) time[1], (byte) time[2]}});
        this.mDiscId3s[0].id3 = str;
        this.mDiscId3s[1].id3 = str2;
        this.mDiscId3s[2].id3 = str3;
        reportID3Info(this.mDiscId3s, false);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected void sendDiscEjectMsg(Context context) {
        super.sendDiscEjectMsg(context);
        this.mDiscId3s[0].id3 = "";
        this.mDiscId3s[1].id3 = "";
        this.mDiscId3s[2].id3 = "";
        reportID3Info(this.mDiscId3s, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        sendMediaMsg(null, new byte[][]{new byte[]{22, -64, 5, 64}, new byte[]{22, -61, 0, 0, 0, 0, 0, 0}, DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, bArr), 35, 32)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        sendMediaMsg(null, new byte[][]{new byte[]{22, -64, 5, 64}, new byte[]{22, -61, 0, 0, 0, 0, 0, 0}, DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1}, bArr), 35, 32)});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(new byte[]{22, -53, 1}, 35, 32));
    }

    private void setDriveData0x33() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, new DecimalFormat("0.0").format(DataHandleUtils.rangeNumber((iArr[0] * 256) + iArr[1], 0, PathInterpolatorCompat.MAX_NUM_POINTS) / 10) + "L/100KM"));
        int[] iArr2 = this.mCanBusInfoInt;
        int iRangeNumber = DataHandleUtils.rangeNumber((iArr2[2] * 256) + iArr2[3], 0, SystemConstant.THREAD_SLEEP_TIME_2000);
        String str = new DecimalFormat("0.0").format(iRangeNumber) + "KM";
        if (iRangeNumber == 65535) {
            str = "--";
        }
        arrayList.add(new DriverUpdateEntity(1, 5, str));
        int[] iArr3 = this.mCanBusInfoInt;
        int iRangeNumber2 = DataHandleUtils.rangeNumber((iArr3[4] * 256) + iArr3[5], 0, 6000);
        arrayList.add(new DriverUpdateEntity(1, 9, iRangeNumber2 != 65535 ? new DecimalFormat("0.0").format(iRangeNumber2) + "KM" : "--"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDriveData0x34() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, new DecimalFormat("0.0").format(DataHandleUtils.rangeNumber((iArr[0] * 256) + iArr[1], 0, PathInterpolatorCompat.MAX_NUM_POINTS) / 10) + "L/100KM"));
        int[] iArr2 = this.mCanBusInfoInt;
        int iRangeNumber = DataHandleUtils.rangeNumber((iArr2[2] * 256) + iArr2[3], 0, 250);
        String str = new DecimalFormat("0.0").format(iRangeNumber) + "KM/H";
        if (iRangeNumber == 65535) {
            str = "--";
        }
        arrayList.add(new DriverUpdateEntity(1, 8, str));
        int[] iArr3 = this.mCanBusInfoInt;
        int iRangeNumber2 = DataHandleUtils.rangeNumber((iArr3[4] * 256) + iArr3[5], 0, 9999);
        arrayList.add(new DriverUpdateEntity(1, 4, iRangeNumber2 != 65535 ? new DecimalFormat("0.0").format(iRangeNumber2) + "KM" : "--"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr4 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr4[4], iArr4[5]));
    }

    private void setDriveData0x35() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, new DecimalFormat("0.0").format(DataHandleUtils.rangeNumber((iArr[0] * 256) + iArr[1], 0, PathInterpolatorCompat.MAX_NUM_POINTS) / 10) + "L/100KM"));
        int[] iArr2 = this.mCanBusInfoInt;
        int iRangeNumber = DataHandleUtils.rangeNumber((iArr2[2] * 256) + iArr2[3], 0, 250);
        String str = new DecimalFormat("0.0").format(iRangeNumber) + "KM/H";
        if (iRangeNumber == 65535) {
            str = "--";
        }
        arrayList.add(new DriverUpdateEntity(1, 8, str));
        int[] iArr3 = this.mCanBusInfoInt;
        int iRangeNumber2 = DataHandleUtils.rangeNumber((iArr3[4] * 256) + iArr3[5], 0, 9999);
        arrayList.add(new DriverUpdateEntity(1, 4, iRangeNumber2 != 65535 ? new DecimalFormat("0.0").format(iRangeNumber2) + "KM" : "--"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[][]{new byte[]{22, -64, 7, 48}, new byte[]{22, -61, 0, 0, 0, 0, 0, 0}});
    }

    public void setSourceMode() {
        int i = AnonymousClass5.$SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[FutureUtil.instance.getCurrentValidSource().ordinal()];
        if (i == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 1});
        } else if (i == 2) {
            CanbusMsgSender.sendMsg(new byte[]{22, 9, 2});
        } else {
            if (i != 3) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, 9, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
        }
    }

    /* renamed from: com.hzbhd.canbus.car._219.MsgMgr$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID;

        static {
            int[] iArr = new int[SourceConstantsDef.SOURCE_ID.values().length];
            $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID = iArr;
            try {
                iArr[SourceConstantsDef.SOURCE_ID.MPEG.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[SourceConstantsDef.SOURCE_ID.FM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$commontools$SourceConstantsDef$SOURCE_ID[SourceConstantsDef.SOURCE_ID.MUSIC.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        Handler.INSTANCE.sendMusicOrVideoData(b, i2, DataHandleUtils.getMsbLsbResult(b7, i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMusicId3s[0].id3 = "";
        this.mMusicId3s[1].id3 = "";
        this.mMusicId3s[2].id3 = "";
        reportID3Info(this.mMusicId3s, true);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        Handler.INSTANCE.sendMusicOrVideoData(b, i2, DataHandleUtils.getMsbLsbResult(b6, i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 15, (byte) (i | (z ? 128 : 0))});
    }

    private void realKeyClick2(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void realKeyClick20x96(int i) {
        if (i == 17) {
            realKeyClick4(this.mContext, 77);
            return;
        }
        if (i == 65) {
            realKeyClick4(this.mContext, 76);
        } else if (i == 34) {
            realKeyClick4(this.mContext, 139);
        } else {
            if (i != 35) {
                return;
            }
            realKeyClick4(this.mContext, 68);
        }
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        int i2 = iArr[6];
        int[] iArr2 = {iArr[2] & com.hzbhd.canbus.car._0.MsgMgr.DVD_MODE, iArr[3], iArr[4], iArr[5], i2 & 129, iArr[7]};
        int[] iArr3 = {iArr[2] & 1, i2 & 4, iArr[8], iArr[9], iArr[10]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 5);
        return i;
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 255) {
            return "HIGH";
        }
        if (GeneralAirData.fahrenheit_celsius) {
            return i + getTempUnitF(this.mContext);
        }
        return (i * 0.5f) + getTempUnitC(this.mContext);
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
        return iPow == i2 ? "- - -" : this.mDecimalFormat.format(iPow * f) + str;
    }

    private String getOpenClose(boolean z) {
        return CommUtil.getStrByResId(this.mContext, z ? "open" : "close");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TimerUtil getTimerUtil() {
        if (this.mTimerUtil == null) {
            this.mTimerUtil = new TimerUtil();
        }
        return this.mTimerUtil;
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == this.mLeftFrontRec && this.mRightFrontStatus == this.mRightFrontRec && this.mLeftRearStatus == this.mLeftRearRec && this.mRightRearStatus == this.mRightRearRec && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
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

    private boolean is0x08DataChange() {
        if (Arrays.equals(this.m0xD8Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0xD8Data = Arrays.copyOf(iArr, iArr.length);
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

    private int getBandData(String str) {
        str.hashCode();
        switch (str) {
            case "MW":
                return 4;
            case "AM1":
                return 3;
            case "AM2":
                return 4;
            case "FM2":
                return 1;
            case "FM3":
                return 2;
            default:
                return 0;
        }
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

    private int[] getFreqByte(String str, String str2) {
        int[] iArr = new int[2];
        Log.d(TAG, "mCurrentFreq " + str2);
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) MathUtils.am_thousands_hundreads(str2);
            iArr[1] = (byte) MathUtils.am_tens_units(str2);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            iArr[0] = (byte) MathUtils.fm_thousands_hundreads(str2);
            iArr[1] = (byte) MathUtils.tens_units(str2);
        }
        return iArr;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._219.MsgMgr$2] */
    private void sendMediaMsg(final String str, final byte[][] bArr) {
        new Thread() { // from class: com.hzbhd.canbus.car._219.MsgMgr.2
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

    void updateAmpUi(Context context) {
        updateAmplifierActivity(null);
    }

    private class ID3 {
        private int cmd;
        private String id3;
        private String record;

        private ID3(int i) {
            this.cmd = i;
            this.id3 = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.id3)) {
                return false;
            }
            this.record = this.id3;
            return true;
        }
    }

    private void initID3() {
        int i = 3;
        ID3[] id3Arr = new ID3[3];
        this.mDiscId3s = id3Arr;
        int i2 = 2;
        id3Arr[0] = new ID3(i2);
        this.mDiscId3s[1] = new ID3(i);
        int i3 = 4;
        this.mDiscId3s[2] = new ID3(i3);
        ID3[] id3Arr2 = new ID3[3];
        this.mMusicId3s = id3Arr2;
        id3Arr2[0] = new ID3(i2);
        this.mMusicId3s[1] = new ID3(i);
        this.mMusicId3s[2] = new ID3(i3);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._219.MsgMgr$3] */
    private void reportID3Info(final ID3[] id3Arr, final boolean z) {
        new Thread() { // from class: com.hzbhd.canbus.car._219.MsgMgr.3
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
                                MsgMgr.this.reportID3InfoFinal(id32.cmd, id32.id3);
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
    public void reportID3InfoFinal(int i, String str) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, (byte) i}, DataHandleUtils.exceptBOMHead(str.getBytes("unicode"))), 33));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void requestData() {
        final byte[][] bArr = {new byte[]{22, -112, 50, 0}, new byte[]{22, -112, 51, 0}, new byte[]{22, -112, 37, 0}, new byte[]{22, -112, -46, 0}};
        getTimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._219.MsgMgr.4
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.getTimerUtil().stopTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        }, 200L, 200L);
    }

    public boolean isOne() {
        return getCurrentCanDifferentId() == 0;
    }

    public boolean isTwo() {
        return getCurrentCanDifferentId() == 1;
    }

    public boolean isThree() {
        return getCurrentCanDifferentId() == 2;
    }

    public boolean isFour() {
        return getCurrentCanDifferentId() == 3;
    }
}
