package com.hzbhd.canbus.car._186;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSettingData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.RealKeyUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private static final String DEVICE_STATUS_AUX = "AUX";
    private static final String DEVICE_STATUS_CD = "CD";
    private static final String DEVICE_STATUS_POWER_OFF = "Power Off";
    private static final String DEVICE_STATUS_POWER_ON = "Power On";
    private static final String DEVICE_STATUS_RADIO = "RADIO";
    private static final String DEVICE_STATUS_TV = "TV";
    private static final String SHARE_AMPLIFIER_ASL = "share_amplifier_asl";
    private static final String SHARE_AMPLIFIER_BALANCE = "share_amplifier_balance";
    private static final String SHARE_AMPLIFIER_BASS = "share_amplifier_bass";
    private static final String SHARE_AMPLIFIER_BOSE = "share_amplifier_bose";
    private static final String SHARE_AMPLIFIER_FADE = "share_amplifier_fade";
    private static final String SHARE_AMPLIFIER_FIELD = "share_amplifier_field";
    private static final String SHARE_AMPLIFIER_SURROUND = "share_amplifier_surroubd";
    private static final String SHARE_AMPLIFIER_TREBLE = "share_amplifier_treble";
    private static final String SHARE_AMPLIFIER_VOLUME = "share_amplifier_volume";
    private static final int _187_AMPLIFIER_BAND_OFFSET = 5;
    private static final int _187_AMPLIFIER_VOLUME_OFFSET = 20;
    private int eachId;
    private int[] m0x10DataNow;
    int mAmpAslValueNow;
    int mAmpBoseValueNow;
    int mAmpFieldValueNow;
    int mAmpSurroundValueNow;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mCurrentDisc;
    private int mDifferentId;
    private byte mFreqHi;
    private int mFreqInt;
    private byte mFreqLo;
    private String mOriDeviFreq;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private MyPanoramicView mPanoramicView;
    private String mRunningState;
    private boolean mShowMessage;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private UiMgr uiMgr;
    private boolean mAirFirst = true;
    private String mDeviceStatusNow = "";
    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;
    int e = 0;
    int f = 0;
    boolean enable = false;
    private DecimalFormat df = new DecimalFormat("0.00");
    private List<TireUpdateEntity> tyreInfoList = new ArrayList();
    private String[] arr0 = new String[10];
    private String[] arr1 = new String[10];
    private String[] arr2 = new String[10];
    private String[] arr3 = new String[10];
    int cycle = 1;
    DecimalFormat timeFormat = new DecimalFormat("00");

    private String getAmpType(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "Beep" : "Balance" : "Fade" : "Treble" : "Bass";
    }

    private String getDeviceWorkModle(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : DEVICE_STATUS_TV : DEVICE_STATUS_AUX : DEVICE_STATUS_CD : DEVICE_STATUS_RADIO;
    }

    private int getIntWithThreeByte(int i, int i2, int i3) {
        return (i * 256 * 256) + (i2 * 256) + i3;
    }

    private int getIntWithTwoByte(int i, int i2) {
        return (i * 256) + i2;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private String getPowerStatus(boolean z) {
        return z ? DEVICE_STATUS_POWER_ON : DEVICE_STATUS_POWER_OFF;
    }

    private String getRadioBand(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "" : "FMAP" : "FM2" : "FM1" : "AMAP" : "AM";
    }

    /* JADX WARN: Type inference failed for: r5v3, types: [com.hzbhd.canbus.car._186.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        initAmplifierData();
        initAmplifierCmd();
        new Thread() { // from class: com.hzbhd.canbus.car._186.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    CanbusMsgSender.sendMsg(new byte[]{22, -119, -1});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -118, -1});
                    sleep(100L);
                    CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
        int i = this.eachId;
        if (i == 17 || i == 18) {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 89, (byte) (i - 16)});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, -18, 89, 0});
        }
        GeneralTireData.isHaveSpareTire = false;
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
        if (i == 1) {
            setTimeInfo();
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setMediaSwitchCommand0x40();
            return;
        }
        if (i == 80) {
            setPhoneCommand0x50();
        } else {
            if (i == 96) {
                setInstrument0x60();
                return;
            }
            if (i != 104) {
                if (i == 106) {
                    int i2 = this.eachId;
                    if (i2 == 5 || i2 == 7) {
                        setCarSpeed0x6A();
                        return;
                    }
                    return;
                }
                if (i == 16) {
                    if (this.eachId != 5) {
                        return;
                    }
                    setOriginalCarWorkModle0x10();
                    return;
                }
                if (i == 17) {
                    int i3 = this.eachId;
                    if (i3 == 17 || i3 == 18) {
                        setRearAir0x11();
                        return;
                    }
                    return;
                }
                switch (i) {
                    case 3:
                        if (this.eachId == 5) {
                            setRadioStatus0x03();
                            break;
                        }
                        break;
                    case 4:
                        if (this.eachId == 5) {
                            setRadioInfo0x04();
                            break;
                        }
                        break;
                    case 5:
                        if (this.eachId == 5) {
                            setRadioMessage0x05();
                            break;
                        }
                        break;
                    case 6:
                        if (this.eachId == 5) {
                            setCdStatus0x06();
                            break;
                        }
                        break;
                    case 7:
                        if (this.eachId == 5) {
                            setCdInfo0x07();
                            break;
                        }
                        break;
                    case 8:
                        if (this.eachId == 5) {
                            setCdMessage0x08();
                            break;
                        }
                        break;
                    case 9:
                        if (this.eachId == 5) {
                            setAmplifierInfo0x09();
                            break;
                        }
                        break;
                    case 10:
                        if (this.eachId == 5) {
                            setVolumeInfo0x0A();
                            break;
                        }
                        break;
                    default:
                        switch (i) {
                            case 32:
                                setRealKeyControl0x20();
                                break;
                            case 33:
                                int i4 = this.eachId;
                                if (i4 == 4 || i4 == 5 || i4 == 6 || i4 == 7 || i4 == 10 || i4 == 17 || i4 == 18) {
                                    setAirData0x21();
                                    break;
                                }
                            case 34:
                                setRearRadar0x22();
                                break;
                            case 35:
                                setFrontRadar0x23();
                                break;
                            default:
                                switch (i) {
                                    case 39:
                                        int i5 = this.eachId;
                                        if (i5 == 5 || i5 == 7 || i5 == 13) {
                                            setMileageData0x27();
                                            break;
                                        }
                                    case 40:
                                        int i6 = this.eachId;
                                        if (i6 == 5 || i6 == 7 || i6 == 13) {
                                            setVehicleDoorInfo0x28();
                                            break;
                                        }
                                    case 41:
                                        int i7 = this.eachId;
                                        if (i7 == 2 || i7 == 5 || i7 == 7 || i7 == 13) {
                                            setTrackData0x29();
                                            break;
                                        }
                                    default:
                                        switch (i) {
                                            case 146:
                                                setTireInfo();
                                                break;
                                            case 147:
                                                setAmplifierInfo0x93();
                                                break;
                                            case 148:
                                                setParkAssistance0x94();
                                                break;
                                            case 149:
                                                setDrivingAids0x95();
                                                break;
                                            case 150:
                                                if (this.eachId == 15) {
                                                    setRearEntertainmentSystem0x96();
                                                    break;
                                                }
                                                break;
                                        }
                                }
                                break;
                        }
                        break;
                }
                return;
            }
        }
        int i8 = this.eachId;
        if (i8 == 5 || i8 == 7 || i8 == 13) {
            setEngineSpeed0x68();
        }
    }

    private void setTireInfo() {
        if (isTrackDataNoChange()) {
            return;
        }
        if (this.mCanBusInfoInt[2] == 255) {
            this.arr0[0] = this.mContext.getString(R.string._186_tire_temp) + "：--" + getTempUnitC(this.mContext);
        } else {
            this.arr0[0] = this.mContext.getString(R.string._186_tire_temp) + "：" + (this.mCanBusInfoInt[2] - 40) + getTempUnitC(this.mContext);
        }
        if (this.mCanBusInfoInt[3] == 255) {
            this.arr1[0] = this.mContext.getString(R.string._186_tire_temp) + "：--" + getTempUnitC(this.mContext);
        } else {
            this.arr1[0] = this.mContext.getString(R.string._186_tire_temp) + "：" + (this.mCanBusInfoInt[3] - 40) + getTempUnitC(this.mContext);
        }
        if (this.mCanBusInfoInt[4] == 255) {
            this.arr2[0] = this.mContext.getString(R.string._186_tire_temp) + "：--" + getTempUnitC(this.mContext);
        } else {
            this.arr2[0] = this.mContext.getString(R.string._186_tire_temp) + "：" + (this.mCanBusInfoInt[4] - 40) + getTempUnitC(this.mContext);
        }
        if (this.mCanBusInfoInt[5] == 255) {
            this.arr3[0] = this.mContext.getString(R.string._186_tire_temp) + "：--" + getTempUnitC(this.mContext);
        } else {
            this.arr3[0] = this.mContext.getString(R.string._186_tire_temp) + "：" + (this.mCanBusInfoInt[5] - 40) + getTempUnitC(this.mContext);
        }
        if (this.mCanBusInfoInt[6] == 255) {
            this.arr0[1] = this.mContext.getString(R.string._186_tire_pres) + "：--Kpa";
        } else {
            this.arr0[1] = this.mContext.getString(R.string._186_tire_pres) + "：" + (this.mCanBusInfoInt[6] * 2) + "Kpa";
        }
        if (this.mCanBusInfoInt[7] == 255) {
            this.arr1[1] = this.mContext.getString(R.string._186_tire_pres) + "：--Kpa";
        } else {
            this.arr1[1] = this.mContext.getString(R.string._186_tire_pres) + "：" + (this.mCanBusInfoInt[7] * 2) + "Kpa";
        }
        if (this.mCanBusInfoInt[8] == 255) {
            this.arr2[1] = this.mContext.getString(R.string._186_tire_pres) + "：--Kpa";
        } else {
            this.arr2[1] = this.mContext.getString(R.string._186_tire_pres) + "：" + (this.mCanBusInfoInt[8] * 2) + "Kpa";
        }
        if (this.mCanBusInfoInt[9] == 255) {
            this.arr3[1] = this.mContext.getString(R.string._186_tire_pres) + "：--Kpa";
        } else {
            this.arr3[1] = this.mContext.getString(R.string._186_tire_pres) + "：" + (this.mCanBusInfoInt[9] * 2) + "Kpa";
        }
        this.tyreInfoList.add(new TireUpdateEntity(0, 0, this.arr0));
        this.tyreInfoList.add(new TireUpdateEntity(1, 0, this.arr1));
        this.tyreInfoList.add(new TireUpdateEntity(2, 0, this.arr2));
        this.tyreInfoList.add(new TireUpdateEntity(3, 0, this.arr3));
        GeneralTireData.dataList = this.tyreInfoList;
        updateTirePressureActivity(null);
    }

    private boolean isTrackDataNoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return true;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void setTimeInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr().getDrivingPageIndexes(this.mContext, "vehicle_info"), getUiMgr().getDrivingItemIndexes(this.mContext, "_186_time"), resolvetime(this.mCanBusInfoInt)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private String resolvetime(int[] iArr) {
        return iArr[2] + ":" + iArr[3];
    }

    private void setPhoneCommand0x50() {
        int i = this.mCanBusInfoInt[2];
        if (i == 1) {
            realKeyClick(14);
        } else if (i == 2) {
            realKeyClick(15);
        } else {
            if (i != 3) {
                return;
            }
            realKeyClick(15);
        }
    }

    private void setFrontRadar0x23() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRearRadar0x22() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setRearEntertainmentSystem0x96() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 50) {
            if (iArr[3] == 1) {
                this.enable = true;
            } else {
                this.enable = false;
            }
        }
        Log.d("Lai", this.enable + "");
        ArrayList arrayList = new ArrayList();
        int[] iArr2 = this.mCanBusInfoInt;
        switch (iArr2[2]) {
            case 50:
                this.a = iArr2[3];
                break;
            case 51:
                this.b = iArr2[3];
                break;
            case 52:
                this.c = iArr2[3];
                break;
            case 53:
                this.d = iArr2[3];
                break;
            case 54:
                this.e = iArr2[3];
                break;
            case 55:
                this.f = iArr2[3];
                break;
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Rear_Entertainment_System_Interface"), Integer.valueOf(this.a)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Left_monitor_power_supply"), Integer.valueOf(this.b)).setEnable(this.enable));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Left_speaker_output"), Integer.valueOf(this.c)).setEnable(this.enable));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Right_monitor_power_supply"), Integer.valueOf(this.d)).setEnable(this.enable));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Right_speaker_output"), Integer.valueOf(this.e)).setEnable(this.enable));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Rear_Entertainment_System"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Rear_Entertainment_System", "_186_Automatically_power_on_the_monitor"), Integer.valueOf(this.f)).setEnable(this.enable));
        GeneralSettingData.dataList = arrayList;
        updateSettingActivity(null);
    }

    private void setInstrument0x60() {
        if (this.mCanBusInfoInt[2] != 49) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "vm_golf7_language_setup"), getUiMgr().getSettingRightIndex(this.mContext, "vm_golf7_language_setup", "vm_golf7_language_setup"), Integer.valueOf(this.mCanBusInfoInt[3])));
        GeneralSettingData.dataList = arrayList;
        updateSettingActivity(null);
    }

    private void setAmplifierInfo0x93() {
        Log.i("ljq", "setAmplifierInfo0x93: ");
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandBass = this.mCanBusInfoByte[3] + 5;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoByte[4] + 5;
        GeneralAmplifierData.leftRight = this.mCanBusInfoByte[5];
        GeneralAmplifierData.frontRear = this.mCanBusInfoByte[6];
        updateAmplifierActivity(null);
        this.mAmpAslValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[7], 0, 5);
        this.mAmpBoseValueNow = this.mCanBusInfoInt[8];
        this.mAmpSurroundValueNow = DataHandleUtils.rangeNumber(this.mCanBusInfoByte[9], -5, 5);
        this.mAmpFieldValueNow = this.mCanBusInfoInt[10];
        changeAmplifierSettings();
        saveAmplifierData();
    }

    private void changeAmplifierSettings() {
        Log.i("ljq", "changeAmplifierSettings: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_asl"), Integer.valueOf(this.mAmpAslValueNow)).setProgress(this.mAmpAslValueNow));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_bose_centerpoint"), Integer.valueOf(this.mAmpBoseValueNow)));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_surround_volume"), Integer.valueOf(this.mAmpSurroundValueNow)).setProgress(this.mAmpSurroundValueNow + 5));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "amplifier_setting"), getUiMgr().getSettingRightIndex(this.mContext, "amplifier_setting", "_186_driver_sound_field"), Integer.valueOf(this.mAmpFieldValueNow)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void saveAmplifierData() {
        Log.i("ljq", "saveAmplifierData: ");
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_ASL, this.mAmpAslValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_BOSE, this.mAmpBoseValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_SURROUND, this.mAmpSurroundValueNow);
        SharePreUtil.setIntValue(this.mContext, SHARE_AMPLIFIER_FIELD, this.mAmpFieldValueNow);
    }

    private void initAmplifierData() {
        GeneralAmplifierData.volume = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_VOLUME, 0);
        GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_BASS, 0);
        GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_TREBLE, 0);
        GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_BALANCE, 0);
        GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_FADE, 0);
        this.mAmpAslValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_ASL, 0);
        this.mAmpBoseValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_BOSE, 0);
        this.mAmpSurroundValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_SURROUND, 0);
        this.mAmpFieldValueNow = SharePreUtil.getIntValue(this.mContext, SHARE_AMPLIFIER_FIELD, 0);
        Log.i("ljq", "initAmplifierData: " + this.mAmpAslValueNow + ", " + this.mAmpBoseValueNow + ", " + this.mAmpSurroundValueNow + ", " + this.mAmpFieldValueNow);
    }

    private void initAmplifierCmd() {
        Log.i("ljq", "initAmplifierCmd: ");
        final byte[][] bArr = {new byte[]{22, -125, 33, (byte) (GeneralAmplifierData.volume - 20)}, new byte[]{22, -125, 34, (byte) (GeneralAmplifierData.bandBass - 5)}, new byte[]{22, -125, 35, (byte) (GeneralAmplifierData.bandTreble - 5)}, new byte[]{22, -125, 36, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -125, 36, (byte) GeneralAmplifierData.leftRight}, new byte[]{22, -125, 37, (byte) GeneralAmplifierData.frontRear}, new byte[]{22, -125, 38, (byte) this.mAmpAslValueNow}, new byte[]{22, -125, 39, (byte) this.mAmpBoseValueNow}, new byte[]{22, -125, 40, (byte) this.mAmpSurroundValueNow}, new byte[]{22, -125, 41, (byte) this.mAmpFieldValueNow}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._186.MsgMgr.2
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr2 = bArr;
                if (i >= bArr2.length) {
                    MsgMgr.this.finishTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr2[i]);
                }
            }
        };
    }

    private void setParkAssistance0x94() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        this.mPanoramicView.mPageNumber = this.mCanBusInfoInt[2];
        this.mPanoramicView.mIbUpStatus = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbDownStatus = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftStatus = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightStatus = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbRightDownStatus = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mIbLeftDownStatus = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        this.mPanoramicView.mTvTipsStatus = this.mCanBusInfoInt[4];
        this.mPanoramicView.mIvCameraStatus = this.mCanBusInfoInt[5];
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._186.MsgMgr.3
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mPanoramicView.updatePanoramicView(MsgMgr.this.mContext);
            }
        });
    }

    private void setDrivingAids0x95() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_moving_object_detection"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_lane_departure_detection"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_driving_aids"), getUiMgr().getSettingRightIndex(this.mContext, "_186_driving_aids", "_186_blind_spot_detection"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Unlock_and_turn_on_the_lights"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Auto_light_sensitivity"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Vehicle_speed_linkage_intermittent_wiper"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings", "_186_Auto_light_off_time_setting"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_Smart_key_unlock"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_auto_retreat"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr().getSettingLeftIndexes(this.mContext, "_186_Original_car_settings2"), getUiMgr().getSettingRightIndex(this.mContext, "_186_Original_car_settings2", "_186_switch_Unlock"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRealKeyControl0x20() {
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
            realKeyClick1(45);
            return;
        }
        if (i == 4) {
            realKeyClick1(46);
            return;
        }
        if (i == 6) {
            realKeyClick1(3);
            return;
        }
        if (i == 7) {
            realKeyClick1(2);
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
        if (i == 21) {
            realKeyClick1(50);
            return;
        }
        if (i == 22) {
            realKeyClick1(49);
            return;
        }
        if (i == 64) {
            wheelKeyClick(141);
            return;
        }
        if (i == 66) {
            wheelKeyClick(30);
            return;
        }
        if (i == 68) {
            wheelKeyClick(HotKeyConstant.K_DISP);
            return;
        }
        if (i == 74) {
            wheelKeyClick(HotKeyConstant.K_BRIGHTNESS_INCREASE);
            return;
        }
        if (i == 78) {
            wheelKeyClick(222);
            return;
        }
        if (i == 96) {
            realKeyClick1(45);
            return;
        }
        if (i == 98) {
            realKeyClick1(48);
            return;
        }
        if (i == 100) {
            realKeyClick1(46);
            return;
        }
        if (i == 102) {
            realKeyClick1(47);
            return;
        }
        if (i == 135) {
            realKeyClick1(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 80) {
            wheelKeyClick(53);
            return;
        }
        if (i != 81) {
            switch (i) {
                case 32:
                    realKeyClick1(HotKeyConstant.K_HOUR);
                    break;
                case 33:
                    realKeyClick1(HotKeyConstant.K_HOUR);
                    break;
                case 34:
                    realKeyClick1(128);
                    break;
                case 35:
                    realKeyClick1(128);
                    break;
                case 36:
                    realKeyClick1(76);
                    break;
                case 37:
                    realKeyClick1(130);
                    break;
                case 38:
                    realKeyClick1(77);
                    break;
                case 39:
                    realKeyClick1(33);
                    break;
                case 40:
                    realKeyClick1(34);
                    break;
                case 41:
                    realKeyClick1(35);
                    break;
                case 42:
                    realKeyClick1(36);
                    break;
                case 43:
                    realKeyClick1(37);
                    break;
                case 44:
                    realKeyClick1(38);
                    break;
                case 45:
                    sendKnob_1(8);
                    break;
                case 46:
                    sendKnob_1(7);
                    break;
                case 47:
                    sendKnob_2(47);
                    break;
                case 48:
                    sendKnob_2(48);
                    break;
                case 49:
                    realKeyClick1(45);
                    break;
                case 50:
                    realKeyClick1(46);
                    break;
                case 51:
                    realKeyClick1(3);
                    break;
                default:
                    switch (i) {
                        case 70:
                            wheelKeyClick(56);
                            break;
                        case 71:
                            wheelKeyClick(151);
                            break;
                        case 72:
                            wheelKeyClick(56);
                            break;
                        default:
                            switch (i) {
                                case 112:
                                    realKeyClick1(49);
                                    break;
                                case 113:
                                    realKeyClick1(50);
                                    break;
                                case 114:
                                    realKeyClick1(128);
                                    break;
                                case 115:
                                    realKeyClick1(52);
                                    break;
                                case 116:
                                    sendKnob_1(7);
                                    break;
                                case 117:
                                    sendKnob_1(8);
                                    break;
                                default:
                                    switch (i) {
                                        case 145:
                                            realKeyClick1(18);
                                            break;
                                        case 146:
                                            realKeyClick1(20);
                                            break;
                                        case 147:
                                            realKeyClick1(21);
                                            break;
                                        case 148:
                                            realKeyClick1(19);
                                            break;
                                        case 149:
                                            realKeyClick1(90);
                                            break;
                                        case 150:
                                            realKeyClick1(91);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        wheelKeyClick(31);
    }

    private void setMediaSwitchCommand0x40() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                enterNoSource();
                realKeyClick(52);
                break;
            case 1:
                changeBandAm1();
                break;
            case 2:
                changeBandFm1();
                break;
            case 3:
                changeBandFm2();
                break;
            case 4:
                realKeyClick(130);
                break;
            case 5:
                realKeyClick(59);
                break;
            case 6:
                realKeyClick(61);
                break;
            case 7:
                realKeyClick(140);
                break;
            case 8:
                realKeyClick(141);
                break;
            case 9:
                realKeyClick(129);
                break;
            case 10:
                realKeyClick(139);
                break;
            case 11:
                realKeyClick(52);
                break;
        }
    }

    private void realKeyClick(int i) {
        realKeyClick6(this.mContext, i);
    }

    private void setRearAir0x11() {
        GeneralAirData.rear_power = true;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
        if (intFromByteWithBit == 11) {
            GeneralAirData.rear_auto = false;
        } else if (intFromByteWithBit == 27) {
            GeneralAirData.rear_auto = true;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        if (intFromByteWithBit2 == 0) {
            GeneralAirData.rear_power = false;
            GeneralAirData.rear_wind_level = 0;
        } else if (intFromByteWithBit2 == 1) {
            GeneralAirData.rear_wind_level = 1;
        } else if (intFromByteWithBit2 == 2) {
            GeneralAirData.rear_wind_level = 2;
        } else if (intFromByteWithBit2 == 3) {
            GeneralAirData.rear_wind_level = 3;
        } else if (intFromByteWithBit2 == 4) {
            GeneralAirData.rear_wind_level = 4;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.rear_wind_level = 5;
        }
        int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 6);
        if (intFromByteWithBit3 == 0) {
            GeneralAirData.rear_temperature = "";
        }
        if (intFromByteWithBit3 >= 1 && intFromByteWithBit3 <= 15) {
            GeneralAirData.rear_temperature = "LO";
        } else if (intFromByteWithBit3 >= 16 && intFromByteWithBit3 <= 48) {
            GeneralAirData.rear_temperature = (((intFromByteWithBit3 - 16) / 2.0f) + 16.0f) + " ℃";
        } else if (intFromByteWithBit3 >= 49 && intFromByteWithBit3 <= 63) {
            GeneralAirData.rear_temperature = "HI";
        }
        updateAirActivity(this.mContext, 1002);
    }

    private void setAirData0x21() {
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(this.mCanBusInfoByte, 6);
        setOutDoorTem();
        GeneralAirData.power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        cleanAllBlow();
        switch (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3)) {
            case 1:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 2:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 1;
        } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 2;
        } else if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 0;
        } else if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            GeneralAirData.cycle_in_out_close = 1;
        }
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.cycle = 1;
        } else if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])) {
            this.cycle = 2;
        } else if (!DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.cycle = 0;
        } else if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2])) {
            this.cycle = 1;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 6));
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 6));
        updateAirActivity(this.mContext, 1001);
    }

    private void setMileageData0x27() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, resolveTotalMileage()));
        arrayList.add(new DriverUpdateEntity(0, 1, resolveEnduranceMileage()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVehicleDoorInfo0x28() {
        if (isDoorMsgRepeat(new byte[]{this.mCanBusInfoByte[2]})) {
            return;
        }
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        updateDoorView(this.mContext);
    }

    private void setEngineSpeed0x68() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 2, resolveEngineSpeed()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setCarSpeed0x6A() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 3, resolveCarSpeed()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr[3], iArr[2]));
    }

    private void setTrackData0x29() {
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            int[] iArr = this.mCanBusInfoInt;
            iArr[2] = blockBit(iArr[2], 7);
            DecimalFormat decimalFormat = this.timeFormat;
            int[] iArr2 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = Integer.parseInt(decimalFormat.format((getMsbLsbResult(iArr2[2], iArr2[3]) * 0.1d) / 21.0d));
        } else {
            int[] iArr3 = this.mCanBusInfoInt;
            iArr3[2] = blockBit(iArr3[2], 7);
            DecimalFormat decimalFormat2 = this.timeFormat;
            int[] iArr4 = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = Integer.parseInt(decimalFormat2.format((-(getMsbLsbResult(iArr4[2], iArr4[3]) * 0.1d)) / 21.0d));
        }
        updateParkUi(null, this.mContext);
    }

    private int blockBit(int i, int i2) {
        if (i2 == 0) {
            return (DataHandleUtils.getIntFromByteWithBit(i, 1, 7) & 255) << 1;
        }
        if (i2 == 7) {
            return DataHandleUtils.getIntFromByteWithBit(i, 0, 7);
        }
        int i3 = i2 + 1;
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i, i3, 7 - i2) & 255;
        return ((DataHandleUtils.getIntFromByteWithBit(i, 0, i2) & 255) & 255) ^ (intFromByteWithBit << i3);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setRadioStatus0x03() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            GeneralOriginalCarDeviceData.rds = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.st = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.auto_p = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mShowMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            if (!this.mShowMessage) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(3, ""));
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setRadioInfo0x04() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, getRadioBand(this.mCanBusInfoInt[2])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "P" + this.mCanBusInfoInt[3]));
            int[] iArr = this.mCanBusInfoInt;
            String radioFreq = getRadioFreq(iArr[2], iArr[4], iArr[5]);
            this.mOriDeviFreq = radioFreq;
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, radioFreq));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setRadioMessage0x05() {
        if (isDeviceStatusConform(DEVICE_STATUS_RADIO)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, new String(this.mCanBusInfoByte)));
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdStatus0x06() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            GeneralOriginalCarDeviceData.folder = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.wma = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.mp3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralOriginalCarDeviceData.scane = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
            this.mShowMessage = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, getPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3))));
            if (!this.mShowMessage) {
                arrayList.add(new OriginalCarDeviceUpdateEntity(4, ""));
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new SongListEntity("Disc 1").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2) == 1).setSelected(this.mCurrentDisc == 1));
            arrayList2.add(new SongListEntity("Disc 2").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) == 1).setSelected(this.mCurrentDisc == 2));
            arrayList2.add(new SongListEntity("Disc 3").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) == 1).setSelected(this.mCurrentDisc == 3));
            arrayList2.add(new SongListEntity("Disc 4").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1).setSelected(this.mCurrentDisc == 4));
            arrayList2.add(new SongListEntity("Disc 5").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2) == 1).setSelected(this.mCurrentDisc == 5));
            arrayList2.add(new SongListEntity("Disc 6").setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2) == 1).setSelected(this.mCurrentDisc == 6));
            GeneralOriginalCarDeviceData.songList = arrayList2;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdInfo0x07() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            this.mCurrentDisc = this.mCanBusInfoInt[2];
            if (GeneralOriginalCarDeviceData.songList != null) {
                for (SongListEntity songListEntity : GeneralOriginalCarDeviceData.songList) {
                    songListEntity.setSelected(songListEntity.getTitle().substring(songListEntity.getTitle().length() - 1).equals(Integer.toString(this.mCurrentDisc)));
                }
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, "Disc " + this.mCurrentDisc));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, Integer.toString(this.mCanBusInfoInt[3])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, new DecimalFormat("00").format(this.mCanBusInfoInt[4]) + ":" + new DecimalFormat("00").format(this.mCanBusInfoInt[5])));
            GeneralOriginalCarDeviceData.mList = arrayList;
            String cdWorkModle = getCdWorkModle(this.mCanBusInfoInt[6]);
            this.mRunningState = cdWorkModle;
            GeneralOriginalCarDeviceData.runningState = cdWorkModle;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setCdMessage0x08() {
        if (isDeviceStatusConform(DEVICE_STATUS_CD)) {
            ArrayList arrayList = new ArrayList();
            if (this.mShowMessage) {
                byte[] bArr = this.mCanBusInfoByte;
                arrayList.add(new OriginalCarDeviceUpdateEntity(4, new String(Arrays.copyOfRange(bArr, 2, bArr.length))));
            } else {
                arrayList.add(new OriginalCarDeviceUpdateEntity(4, ""));
            }
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
        }
    }

    private void setAmplifierInfo0x09() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3);
        String str = getAmpType(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3)) + " " + getAmpValue(intFromByteWithBit, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 5));
        if (intFromByteWithBit != 0) {
            GeneralOriginalCarDeviceData.runningState = str;
            updateOriginalCarDeviceActivity(null);
            this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._186.MsgMgr.4
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    GeneralOriginalCarDeviceData.runningState = MsgMgr.this.mRunningState;
                    MsgMgr.this.updateOriginalCarDeviceActivity(null);
                    MsgMgr.this.finishTimer();
                }
            };
            startTimer();
        }
    }

    private void setVolumeInfo0x0A() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 6);
        int i = intFromByteWithBit >> 4;
        int i2 = intFromByteWithBit & 15;
        String str = boolBit7 ? Integer.toString(i) + Integer.toString(i2) : "";
        if ((i > 9 || i2 > 9) && boolBit7) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(getOriginalCarDevicePageUiSet().getItems().size() - 1, str));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalCarWorkModle0x10() {
        if (is0x10DataChange()) {
            GeneralOriginalCarDeviceData.cdStatus = getPowerStatus(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
            GeneralOriginalCarDeviceData.discStatus = getDeviceWorkModle(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3));
            updateOriginalCarDeviceActivity(null);
            if (DEVICE_STATUS_POWER_OFF.equals(GeneralOriginalCarDeviceData.cdStatus)) {
                cleanDevice();
                return;
            }
            if (isDeviceStatusSame(DEVICE_STATUS_RADIO)) {
                cleanDevice();
                setOriginalRadioPage();
            } else if (isDeviceStatusSame(DEVICE_STATUS_CD)) {
                cleanDevice();
                setOriginalCdPage();
            } else if (isDeviceStatusSame(DEVICE_STATUS_TV)) {
                setOriginalOtherPage();
            } else {
                if (isDeviceStatusSame(DEVICE_STATUS_AUX)) {
                    exitAuxIn2();
                    runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._186.MsgMgr.5
                        @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                        public void callback() {
                            CommUtil.showToast(MsgMgr.this.mContext, CommUtil.getStrByResId(MsgMgr.this.mContext, "_272_toast_text18"));
                        }
                    });
                    setOriginalOtherPage();
                    return;
                }
                return;
            }
            if (!DEVICE_STATUS_POWER_ON.equals(GeneralOriginalCarDeviceData.cdStatus) || "".equals(GeneralOriginalCarDeviceData.discStatus)) {
                return;
            }
            showOriginalDevice();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        Log.i("ljq", "sourceSwitchChange: " + str);
        if (SourceConstantsDef.SOURCE_ID.ATV.name().equals(str) || SourceConstantsDef.SOURCE_ID.AUX1.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTAUDIO.name().equals(str) || SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str) || SourceConstantsDef.SOURCE_ID.MPEG.name().equals(str) || SourceConstantsDef.SOURCE_ID.DTV.name().equals(str) || SourceConstantsDef.SOURCE_ID.MUSIC.name().equals(str) || SourceConstantsDef.SOURCE_ID.FM.name().equals(str) || SourceConstantsDef.SOURCE_ID.VIDEO.name().equals(str) || SourceConstantsDef.SOURCE_ID.NAVIAUDIO.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -117, 1});
        }
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void wheelKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void sendKnob_1(int i) {
        realKeyClick3_1(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    private void sendKnob_2(int i) {
        realKeyClick3_2(this.mContext, i, 0, this.mCanBusInfoInt[3]);
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
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
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[6];
        return (i < 1 || i > 254) ? "" : ((i * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (this.mAirFirst) {
            this.mAirFirst = false;
            if (!GeneralAirData.power || GeneralAirData.front_wind_level == 0) {
                return true;
            }
        }
        return false;
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private String resolveLeftAndRightTemp(int i) {
        return (i < 1 || i > 15) ? (i < 49 || i > 63) ? (i < 16 || i > 48) ? "---" : ((i / 2.0f) + 8.0f) + getTempUnitC(this.mContext) : "HI" : "LO";
    }

    private String resolveTotalMileage() {
        int[] iArr = this.mCanBusInfoInt;
        int intWithThreeByte = getIntWithThreeByte(iArr[3], iArr[4], iArr[5]);
        if (intWithThreeByte != 16777215) {
            return intWithThreeByte + (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]) ? " mile" : " km");
        }
        return "";
    }

    private String resolveEnduranceMileage() {
        int[] iArr = this.mCanBusInfoInt;
        int intWithTwoByte = getIntWithTwoByte(iArr[6], iArr[7]);
        if (intWithTwoByte != 65535) {
            return (intWithTwoByte / 10) + (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]) ? " mile" : " km");
        }
        return "";
    }

    private String resolveEngineSpeed() {
        int[] iArr = this.mCanBusInfoInt;
        return getIntWithTwoByte(iArr[3], iArr[2]) + " rpm";
    }

    private String resolveCarSpeed() {
        int[] iArr = this.mCanBusInfoInt;
        return getIntWithTwoByte(iArr[3], iArr[2]) + " km/h";
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 17;
            case "AM2":
                return (byte) 18;
            case "FM1":
                return (byte) 1;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 0;
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

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private String getPlayStatus(int i) {
        switch (i) {
            case 0:
                return this.mContext.getResources().getString(R.string.all_disc_rpt);
            case 1:
                return this.mContext.getResources().getString(R.string.one_disc_rpt);
            case 2:
                return this.mContext.getResources().getString(R.string.one_track_rpt);
            case 3:
                return this.mContext.getResources().getString(R.string.one_disc_rdm);
            case 4:
                return this.mContext.getResources().getString(R.string.all_disc_rdm);
            case 5:
                return this.mContext.getResources().getString(R.string.rdm_folder);
            case 6:
                return this.mContext.getResources().getString(R.string.rpt_folder);
            case 7:
                return this.mContext.getResources().getString(R.string.air_set_3_2);
            default:
                return "";
        }
    }

    private String getCdWorkModle(int i) {
        switch (i) {
            case 0:
                return "Play";
            case 1:
                return "Reading disc " + this.mCurrentDisc;
            case 2:
                return "Loading disc " + this.mCurrentDisc;
            case 3:
                return "Insert disc";
            case 4:
                return "Busy";
            case 5:
                return "Ejecting disc " + this.mCurrentDisc;
            case 6:
                return "Select disc to load";
            case 7:
                return "Select disc to eject";
            case 8:
                return "Disc error";
            default:
                return "";
        }
    }

    private String getRadioFreq(int i, int i2, int i3) {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(i2, 7, 1);
        int i4 = ((i2 & (255 >> intFromByteWithBit)) * 256) + i3;
        if (i == 1 || i == 2) {
            if (i4 > 120) {
                return this.mOriDeviFreq;
            }
            return ((((i4 - 1) * (intFromByteWithBit + 9)) + 531) - intFromByteWithBit) + "KHz";
        }
        if (i != 3 && i != 4 && i != 5) {
            return "";
        }
        if (i4 > 409) {
            return this.mOriDeviFreq;
        }
        return new BigDecimal(((i4 - 1) * 0.05f) + 87.5d).setScale(1, 4).floatValue() + "MHz";
    }

    private boolean isDeviceStatusSame(String str) {
        if (!str.equals(GeneralOriginalCarDeviceData.discStatus) || str.equals(this.mDeviceStatusNow)) {
            return false;
        }
        this.mDeviceStatusNow = str;
        return true;
    }

    private void cleanDevice() {
        this.mRunningState = "";
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.folder = false;
        GeneralOriginalCarDeviceData.wma = false;
        GeneralOriginalCarDeviceData.mp3 = false;
        GeneralOriginalCarDeviceData.scane = false;
        GeneralOriginalCarDeviceData.rds = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.auto_p = false;
        GeneralOriginalCarDeviceData.mList = null;
        Iterator<OriginalCarDevicePageUiSet.Item> it = getOriginalCarDevicePageUiSet().getItems().iterator();
        while (it.hasNext()) {
            it.next().setValue("");
        }
        updateOriginalCarDeviceActivity(null);
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.RDS, OriginalBtnAction.SCANE, OriginalBtnAction.ST, OriginalBtnAction.AUTO_P}, false);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_artist", "_186_play_modle", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_dvd", "_186_current_play_time", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "message", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FOLDER, OriginalBtnAction.WMA, OriginalBtnAction.MP3, OriginalBtnAction.SCANE}, true);
    }

    private void setOriginalOtherPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_music", "_186_volume", ""));
        changeOriginalDevicePage(arrayList, null, false);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, boolean z) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet();
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setHaveSongList(z);
        updateOriginalDeviceWithInit();
        cleanDevice();
    }

    private void updateOriginalDeviceWithInit() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void showOriginalDevice() {
        Intent intent = new Intent();
        intent.setComponent(Constant.OriginalDeviceActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet() {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr().getOriginalCarDevicePageUiSet(this.mContext);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private UiMgr getUiMgr() {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
        return this.mUiMgr;
    }

    private boolean isDeviceStatusConform(String str) {
        if (DEVICE_STATUS_POWER_OFF.equals(GeneralOriginalCarDeviceData.cdStatus)) {
            return false;
        }
        return str.equals(GeneralOriginalCarDeviceData.discStatus);
    }

    private void startTimer() {
        if (this.mTimerTask == null) {
            return;
        }
        if (this.mTimer == null) {
            this.mTimer = new Timer();
        }
        this.mTimer.schedule(this.mTimerTask, 1000L);
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

    private String getAmpValue(int i, int i2) {
        if (i == 5) {
            if (i2 > 1) {
                i2 = 1;
            }
            return i2 == 0 ? "OFF" : i2 == 1 ? "ON" : "";
        }
        if (i2 < 2) {
            i2 = 2;
        }
        return Integer.toString(i2 - 7);
    }

    private boolean is0x10DataChange() {
        if (Arrays.equals(this.m0x10DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x10DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    public void updateSettingActivity(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettingActivity(int i, int i2, Object obj, boolean z) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, obj).setEnable(z));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    protected void updateBubble(Context context, boolean z) {
        GeneralBubbleData.isShowBubble = z;
        updateBubble(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte allBandTypeData = getAllBandTypeData(str);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 1, 1, allBandTypeData, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            sendDiscEjectMsg(this.mContext);
            return;
        }
        if (i7 == 32) {
            int hour = getHour(i);
            int minute = getMinute(i);
            int second = getSecond(i);
            byte b2 = (byte) ((i6 == 1 || i6 == 5) ? i4 : i3);
            byte b3 = (byte) i5;
            byte b4 = (byte) hour;
            byte b5 = (byte) minute;
            byte b6 = (byte) second;
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 0, b2, b3, b4, b5, b6});
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 2, 16, 0, b2, b3, b4, b5, b6});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 3, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        if (b == 9) {
            return;
        }
        byte b10 = (byte) (i2 & 255);
        byte b11 = (byte) ((i2 >> 8) & 255);
        byte b12 = (byte) i;
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, b10, b11, b12, b7, b4, b5});
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, b10, b11, b12, b7, b4, b5});
        byte[] bArrByteMerger = DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 112, 17}, str4.getBytes(StandardCharsets.UTF_16LE)), 32), new byte[]{0, 0});
        byte[] bArrByteMerger2 = DataHandleUtils.byteMerger(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, 113, 17}, str6.getBytes(StandardCharsets.UTF_16LE)), 32), new byte[]{0, 0});
        CanbusMsgSender.sendMsg(bArrByteMerger);
        CanbusMsgSender.sendMsg(bArrByteMerger2);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (b == 9) {
            return;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 8, 17, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), (byte) i, b6, b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 11, -1, 1, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -59, 1, 1}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z && i == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 2, 1, 32});
        } else {
            if (z) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -59, 0, 1, 32});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i6, (byte) i8, z ? (byte) 1 : (byte) 0});
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }

    protected void realKeyClick6(Context context, int i) {
        RealKeyUtil.realKeyClick(context, i);
    }
}
