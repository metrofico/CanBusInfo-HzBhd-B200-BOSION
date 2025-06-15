package com.hzbhd.canbus.car._353;

import android.content.Context;
import android.os.CountDownTimer;
import android.text.TextUtils;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final String SHARE_353_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
    static final String SHARE_353_AMPLIFIER_BASS = "share_122_amplifier_bass";
    static final String SHARE_353_AMPLIFIER_FADE = "share_122_amplifier_fade";
    static final String SHARE_353_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
    static final String SHARE_353_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
    static final String SHARE_353_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
    static final int _353_AMPLIFIER_OFFSET = 0;
    static final int _353_AMPLIFIER_RANGE = 0;
    int[] OutGoingPhoneNumber;
    int RoundVolume;
    int bandBass;
    int bandTreble;
    int[] comingPhoneNumber;
    private int eachId;
    int frontRear;
    int leftRight;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    int[] mFrontRadarData;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    int[] mRearRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private Timer mTimer;
    private TimerTask mTimerTask;
    String resulttemp;
    int speedVolume;
    private int[] talkingPhoneNumber;
    private UiMgr uiMgr;
    int volume;
    String str = null;
    private final int INVAILE_VALUE = -1;
    private DecimalFormat mDecimalFormat0p00 = new DecimalFormat("0.00");
    private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
    private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");
    private List<TireUpdateEntity> mTire0 = new ArrayList();
    private List<TireUpdateEntity> mTire1 = new ArrayList();
    private List<TireUpdateEntity> mTire2 = new ArrayList();
    private List<TireUpdateEntity> mTire3 = new ArrayList();

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int resolveAmplifier(int i) {
        switch (i) {
            case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                return 0;
            case 252:
                return 1;
            case 253:
                return 2;
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return 3;
            case 255:
                return 4;
            default:
                return i + 5;
        }
    }

    private int resolveAmplifier2(int i) {
        switch (i) {
            case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                return -5;
            case 252:
                return -4;
            case 253:
                return -3;
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return -2;
            case 255:
                return -1;
            default:
                return i;
        }
    }

    private int resolveAmplifier3(int i) {
        switch (i) {
            case com.hzbhd.canbus.car._464.MsgMgr.RADIO_MODE /* 251 */:
                return 5;
            case 252:
                return 4;
            case 253:
                return 3;
            case com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE /* 254 */:
                return 2;
            case 255:
                return 1;
            default:
                return i * (-1);
        }
    }

    private void setSettingFeedback0xD0() {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 41) {
            setTrackData0x29();
            return;
        }
        if (i == 96) {
            setCartypeInfo0x60();
            return;
        }
        if (i == 104) {
            setRotatingSpeedInfo0x6B();
            return;
        }
        if (i == 106) {
            setSpeedInfo0x6A();
            return;
        }
        if (i == 145) {
            setEmergencysignal0x91();
            return;
        }
        if (i == 147) {
            setCarSettingInfo0x93();
            return;
        }
        if (i == 208) {
            setSettingFeedback0xD0();
            return;
        }
        if (i == 47) {
            setWheelKeyInfo0x22();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 112) {
            serTireInfo0x70();
            return;
        }
        if (i != 113) {
            switch (i) {
                case 32:
                    setPanelKeyInfo0x20();
                    break;
                case 33:
                    setAirData0x21();
                    break;
                case 34:
                    setRearRadarInfo0x22();
                    break;
                case 35:
                    setFrontRadarInfo0x23();
                    break;
                case 36:
                    setDoorData0x24();
                    break;
            }
            return;
        }
        setFuelConsumptionMileageInfo0x71();
    }

    private void setPanelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
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
            buttonKey(45);
            return;
        }
        if (i == 4) {
            buttonKey(46);
            return;
        }
        if (i == 7) {
            buttonKey(2);
            return;
        }
        if (i == 135) {
            buttonKey(1);
            return;
        }
        if (i == 9) {
            buttonKey(14);
            return;
        }
        if (i == 10) {
            buttonKey(15);
            return;
        }
        if (i == 21) {
            buttonKey(50);
            return;
        }
        if (i == 22) {
            buttonKey(49);
            return;
        }
        switch (i) {
            case 96:
                buttonKey(45);
                break;
            case 97:
                buttonKey(33);
                break;
            case 98:
                buttonKey(48);
                break;
            case 99:
                buttonKey(34);
                break;
            case 100:
                buttonKey(46);
                break;
            case 101:
                buttonKey(35);
                break;
            case 102:
                buttonKey(47);
                break;
            case 103:
                buttonKey(36);
                break;
            default:
                switch (i) {
                    case 112:
                        buttonKey(49);
                        break;
                    case 113:
                        buttonKey(50);
                        break;
                    case 114:
                        buttonKey(128);
                        break;
                    case 115:
                        buttonKey(151);
                        break;
                    case 116:
                        buttonKey(205);
                        break;
                    case 117:
                        buttonKey(205);
                        break;
                }
        }
    }

    private void setAirData0x21() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadarInfo0x22() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x23() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = false;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
        updateParkUi(null, this.mContext);
    }

    private void setTrackData0x29() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4768, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setWheelKeyInfo0x22() {
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                buttonKey0x2F(77);
                break;
            case 2:
                buttonKey0x2F(76);
                break;
            case 3:
                buttonKey0x2F(76);
                break;
            case 4:
                buttonKey0x2F(130);
                break;
            case 5:
                buttonKey0x2F(139);
                break;
            case 6:
                buttonKey0x2F(139);
                break;
            case 7:
                buttonKey0x2F(140);
                break;
            case 8:
                buttonKey0x2F(141);
                break;
            case 9:
                buttonKey0x2F(76);
                break;
            case 10:
                buttonKey0x2F(139);
                break;
            case 11:
                buttonKey0x2F(143);
                break;
            case 12:
                buttonKey0x2F(143);
                break;
            case 13:
                buttonKey0x2F(144);
                break;
            case 14:
                buttonKey0x2F(142);
                break;
            case 15:
                buttonKey0x2F(145);
                break;
            case 16:
                buttonKey0x2F(146);
                break;
            case 17:
                buttonKey0x2F(HotKeyConstant.K_1_PICKUP);
                break;
            case 18:
                buttonKey0x2F(HotKeyConstant.K_2_HANGUP);
                break;
        }
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setCartypeInfo0x60() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            this.str = "日产2014款新奇骏高配（自动空调）";
        }
        if (i == 1) {
            this.str = "日产2014款新奇骏低配（手动空调）";
        }
        if (i == 2) {
            this.str = "日产2016款新逍客高配（自动空调）";
        }
        if (i == 3) {
            this.str = "日产2016款新逍客高配（手动空调）";
        }
        if (i == 4) {
            this.str = "日产2013款新天籁高配（自动空调）";
        }
        if (i == 5) {
            this.str = "日产2013款新天籁高配（手动空调）";
        }
        if (i == 6) {
            this.str = "日产2008-2012款天籁高配（自动空调）";
        }
        if (i == 7) {
            this.str = "日产2008-2012款天籁高配（手动空调）";
        }
        if (i == 8) {
            this.str = "日产2011-2016款新骐达高配（自动空调）";
        }
        if (i == 9) {
            this.str = "日产2011-2016款新骐达高配（手动空调）";
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_1_0"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_1_0", "_353_setting_1_0"), this.str).setValue(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRotatingSpeedInfo0x6B() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat00;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getData(iArr[2], iArr[3]))).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setSpeedInfo0x6A() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat00;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getData(iArr[2], iArr[3]))).append(" km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void setEmergencysignal0x91() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_2", "_353_setting_2_0"), Integer.valueOf(this.mCanBusInfoInt[2])));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarSettingInfo0x93() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        int[] iArr = this.mCanBusInfoInt;
        this.volume = iArr[2];
        GeneralAmplifierData.bandBass = resolveAmplifier(iArr[3]);
        this.bandBass = resolveAmplifier(this.mCanBusInfoInt[3]);
        GeneralAmplifierData.bandTreble = resolveAmplifier(this.mCanBusInfoInt[4]);
        this.bandTreble = resolveAmplifier(this.mCanBusInfoInt[4]);
        GeneralAmplifierData.leftRight = resolveAmplifier2(this.mCanBusInfoInt[5]);
        this.leftRight = resolveAmplifier2(this.mCanBusInfoInt[5]);
        GeneralAmplifierData.frontRear = resolveAmplifier3(this.mCanBusInfoInt[6]);
        this.frontRear = resolveAmplifier3(this.mCanBusInfoInt[6]);
        int[] iArr2 = this.mCanBusInfoInt;
        this.speedVolume = iArr2[7];
        this.RoundVolume = resolveAmplifier(iArr2[9]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_6"), Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_7"), Integer.valueOf(this.mCanBusInfoInt[8])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_8"), Integer.valueOf(resolveAmplifier(this.mCanBusInfoInt[9]))).setProgress(resolveAmplifier(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_353_setting_3"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_353_setting_3", "_353_setting_2_9"), Integer.valueOf(this.mCanBusInfoInt[10])));
        updateAmplifierActivity(null);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        saveAmplifierData();
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_353_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_353_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_353_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_353_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_353_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_353_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_353_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_353_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_353_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_353_AMPLIFIER_TREBLE, 0);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
        final byte[][] bArr = {new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 0 + 0)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 0 + 0)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 0)}, new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 0)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 0)}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._353.MsgMgr.1
            int index = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.index;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.finishTimer();
                } else {
                    this.index = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        };
        startTimer(100L, 100);
    }

    private void startTimer(long j, int i) {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishTimer() {
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

    private void serTireInfo0x70() {
        List<TireUpdateEntity> list = this.mTire0;
        String tpmsHighWarningStr = getTpmsHighWarningStr(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]));
        String tpmsLowWarningStr = getTpmsLowWarningStr(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        int[] iArr = this.mCanBusInfoInt;
        list.add(getTireEntity(0, tpmsHighWarningStr, tpmsLowWarningStr, getTpmsPressureNumStr(iArr[6], iArr[5]), getTpmsCheckWarningStr(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]))));
        List<TireUpdateEntity> list2 = this.mTire1;
        String tpmsHighWarningStr2 = getTpmsHighWarningStr(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        String tpmsLowWarningStr2 = getTpmsLowWarningStr(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        int[] iArr2 = this.mCanBusInfoInt;
        list2.add(getTireEntity(1, tpmsHighWarningStr2, tpmsLowWarningStr2, getTpmsPressureNumStr(iArr2[4], iArr2[3]), getTpmsCheckWarningStr(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]))));
        List<TireUpdateEntity> list3 = this.mTire2;
        String tpmsHighWarningStr3 = getTpmsHighWarningStr(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        String tpmsLowWarningStr3 = getTpmsLowWarningStr(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        int[] iArr3 = this.mCanBusInfoInt;
        list3.add(getTireEntity(2, tpmsHighWarningStr3, tpmsLowWarningStr3, getTpmsPressureNumStr(iArr3[10], iArr3[9]), getTpmsCheckWarningStr(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]))));
        List<TireUpdateEntity> list4 = this.mTire3;
        String tpmsHighWarningStr4 = getTpmsHighWarningStr(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        String tpmsLowWarningStr4 = getTpmsLowWarningStr(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        int[] iArr4 = this.mCanBusInfoInt;
        list4.add(getTireEntity(3, tpmsHighWarningStr4, tpmsLowWarningStr4, getTpmsPressureNumStr(iArr4[8], iArr4[7]), getTpmsCheckWarningStr(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]))));
        GeneralTireData.dataList = mergeList(this.mTire0, this.mTire1, this.mTire2, this.mTire3);
        updateTirePressureActivity(null);
    }

    private void setFuelConsumptionMileageInfo0x71() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "nissan_raise_mileage_title");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat00;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getMsbLsbResult(iArr[3], iArr[2]))).append(" KM").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "Average_fuel_consumption");
        StringBuilder sb2 = new StringBuilder();
        DecimalFormat decimalFormat2 = this.mDecimalFormat0p0;
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(decimalFormat2.format(getMsbLsbResult(iArr2[5], iArr2[4]) * 0.1d)).append(" L/100KM").toString()));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "total_mileage");
        StringBuilder sb3 = new StringBuilder();
        DecimalFormat decimalFormat3 = this.mDecimalFormat00;
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(decimalFormat3.format(getData(iArr3[6], iArr3[7], iArr3[8]))).append(" KM").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void buttonKey0x2F(int i) {
        realKeyClick4(this.mContext, i);
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            this.resulttemp = "LOW";
        } else if (i == 127) {
            this.resulttemp = "HIGH";
        } else if (i >= 31 && i <= 64) {
            String str = (i / 2.0f) + getTempUnitC(this.mContext);
            this.resulttemp = str;
            return str;
        }
        return this.resulttemp;
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[7];
        if (i > 86) {
            i += InputDeviceCompat.SOURCE_ANY;
        }
        return i + getTempUnitC(this.mContext);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        int i11 = z2 ? 128 : 0;
        if (z) {
            i5 = i8;
        } else {
            i11 |= 64;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -58, (byte) i5, (byte) i6, (byte) i11});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.comingPhoneNumber)) {
            return;
        }
        this.comingPhoneNumber = byteArrayToIntArray;
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        for (int i = 0; i < bArr.length; i++) {
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.OutGoingPhoneNumber)) {
            return;
        }
        this.OutGoingPhoneNumber = byteArrayToIntArray;
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 2, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int i3 = this.eachId;
        if (i3 == 1 || i3 == 2 || i3 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(1, 1);
        }
        int i4 = this.eachId;
        if (i4 == 1 || i4 == 2) {
            getUiMgr(this.mContext).sendRadioInfo(getBandAmFM(str), getFreqLsb(str, str2), getFreqMsb(str, str2), i);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        getUiMgr(this.mContext).sendSourceInfo(0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).sendSourceInfo(2, 33);
        getUiMgr(this.mContext).sendMediaPalyInfo(i4, i5, 0, 0, i / 60, i % 60);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).sendSourceInfo(8, 17);
        getUiMgr(this.mContext).sendMediaPalyInfo(getLsb(i2), getMsb(i2), getLsb(i), getMsb(i), b4, b5);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        getUiMgr(this.mContext).sendSourceInfo(0, 0);
        getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).sendSourceInfo(7, 48);
        getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        getUiMgr(this.mContext).sendSourceInfo(11, 34);
        getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        getUiMgr(this.mContext).sendSourceInfo(0, 0);
        getUiMgr(this.mContext).sendMediaPalyInfo(0, 0, 0, 0, 0, 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        int i2 = this.eachId;
        if (i2 == 1 || i2 == 2 || i2 == 9) {
            getUiMgr(this.mContext).sendSourceInfo(5, 64);
        }
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        if (Arrays.equals(byteArrayToIntArray, this.talkingPhoneNumber)) {
            return;
        }
        this.talkingPhoneNumber = byteArrayToIntArray;
        getUiMgr(this.mContext).sendPhoneNumber(SplicingByte(new byte[]{22, -59, 4, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        this.comingPhoneNumber = null;
        this.OutGoingPhoneNumber = null;
        this.talkingPhoneNumber = null;
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._353.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                new CountDownTimer(2000L, 500L) { // from class: com.hzbhd.canbus.car._353.MsgMgr.2.1
                    @Override // android.os.CountDownTimer
                    public void onTick(long j) {
                        MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }

                    @Override // android.os.CountDownTimer
                    public void onFinish() {
                        MsgMgr.this.getUiMgr(MsgMgr.this.mContext).sendPhoneNumber();
                    }
                }.start();
            }
        });
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    private int getBandAmFM(String str) {
        str.hashCode();
        switch (str) {
            case "AM":
                return 16;
            case "AM1":
                return 17;
            case "AM2":
                return 18;
            case "AM3":
                return 19;
            case "FM1":
                return 1;
            case "FM2":
                return 2;
            case "FM3":
                return 3;
            default:
                return 0;
        }
    }

    private int getFreqMsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getMsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getMsb((int) Double.parseDouble(str2));
    }

    private int getFreqLsb(String str, String str2) {
        if (getBandAmFM(str) == 0 || getBandAmFM(str) == 1 || getBandAmFM(str) == 2 || getBandAmFM(str) == 3) {
            return getLsb((int) (Double.parseDouble(str2) * 100.0d));
        }
        return getLsb((int) Double.parseDouble(str2));
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen && this.mHandBrake == GeneralDoorData.isHandBrakeUp) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mHandBrake = GeneralDoorData.isHandBrakeUp;
        return true;
    }

    protected static <T> List<T> mergeList(List<T>... listArr) {
        List<T> list;
        try {
            list = (List) listArr[0].getClass().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        for (List<T> list2 : listArr) {
            list.addAll(list2);
        }
        return list;
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

    private boolean compare(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj2.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    private String getTpmsPressureNumStr(int i, int i2) {
        return getMsbLsbResult(i, i2) + "  KPA";
    }

    private TireUpdateEntity getTireEntity(int i, String str, String str2, String str3, String str4) {
        String[] strArr;
        int i2 = 0;
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            strArr = new String[]{this.mContext.getResources().getString(R.string.air_set_3_2), str3, str4};
        } else {
            strArr = TextUtils.isEmpty(str) ? new String[]{str2, str3, str4} : new String[]{str, str3, str4};
            i2 = 1;
        }
        return new TireUpdateEntity(i, i2, strArr);
    }

    private String getTpmsCheckWarningStr(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string.effective);
        }
        return this.mContext.getResources().getString(R.string.invalid);
    }

    private String getTpmsHighWarningStr(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string.high_pressure_warm);
        }
        return null;
    }

    private String getTpmsLowWarningStr(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string.low_pressure_warm);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
