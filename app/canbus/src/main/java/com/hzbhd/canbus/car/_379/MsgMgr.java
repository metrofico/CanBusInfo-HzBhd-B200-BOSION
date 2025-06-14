package com.hzbhd.canbus.car._379;

import android.app.Activity;
import android.content.Context;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String SHARE_379_AMPLIFIER_BALANCE = "share_122_amplifier_balance";
    static final String SHARE_379_AMPLIFIER_BASS = "share_122_amplifier_bass";
    static final String SHARE_379_AMPLIFIER_FADE = "share_122_amplifier_fade";
    static final String SHARE_379_AMPLIFIER_MIDDLE = "share_122_amplifier_middle";
    static final String SHARE_379_AMPLIFIER_TREBLE = "share_122_amplifier_treble";
    static final String SHARE_379_AMPLIFIER_VOLUME = "share_122_amplifier_volume";
    static final int _379_AMPLIFIER_OFFSET = 1;
    static final int _379_AMPLIFIER_RANGE = 9;
    public boolean CameraDelay;
    public boolean DepressedView;
    int EngineSpeed;
    int FactoryCode;
    int InstantaneousSpeed;
    int LeftCold;
    int LeftHeat;
    int LeftTemp;
    int Memory;
    int RightCold;
    int RightHeat;
    int RightTemp;
    int WindLevel;
    boolean ac;
    boolean ac_max;
    boolean auto;
    boolean blowfoot;
    boolean blowhead;
    boolean blowwindow;
    int differentId;
    boolean dual;
    int eachId;
    boolean in_out_cycle;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private HashMap<String, DriveDataUpdateHelper> mDriveItemHashMap;
    private HashMap<String, SettingUpdateHelper> mSettingItemHashMap;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private UiMgr mUiMgr;
    boolean mac_heat;
    boolean power;
    boolean rear_defog;
    boolean steering_wheel_heating;
    Boolean EngineSpeedEnable = false;
    Boolean InstantaneousSpeedEnable = false;

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifierData(this.mContext);
        initSettingsItem(getUiMgr(this.mContext).getSettingUiSet(this.mContext));
        initDriveItem(getUiMgr(this.mContext).getDriverDataPageUiSet(this.mContext));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mContext = context;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            SetWheelKey0x11();
            SetTrackInfo0x11();
            return;
        }
        if (i == 18) {
            setDoorInfo0x12();
            return;
        }
        if (i == 38) {
            setCarModel0x26();
            return;
        }
        if (i == 63) {
            setDriveInfo0x3F();
            return;
        }
        if (i == 65) {
            setRadarInfo0x41();
            return;
        }
        if (i == 97) {
            setSettingInfo0x61();
            return;
        }
        if (i == 104) {
            setRemainder0x68();
            return;
        }
        if (i == 148) {
            setLanguage0x94();
            return;
        }
        if (i == 166) {
            setAmplifierInfo0xA6();
            return;
        }
        if (i == 232) {
            setOriginalCarVideoStatus0xE8();
            return;
        }
        if (i == 49) {
            setAirInfo0x31();
            return;
        }
        if (i == 50) {
            setDriveInfo0x32();
        } else if (i == 240) {
            setVersionInfo0xF0();
        } else {
            if (i != 241) {
                return;
            }
            setAirVersionInfo0xF1();
        }
    }

    private void setAirVersionInfo0xF1() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("_379_item_26", resolveAirVersion(this.mCanBusInfoInt), true, true)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveAirVersion(int[] iArr) {
        return iArr + "";
    }

    private void setCarModel0x26() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("_379_item_13", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4) - 3))));
        arrayList.add(checkEntity(helperSetValue("_379_item_14", Integer.valueOf(resolveSeries(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))))));
        arrayList.add(checkEntity(helperSetValue("_379_item_15", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4) - 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_16", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) - 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_17", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) - 1))));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private int resolveSeries(int i) {
        return new int[]{0, 0, 1, 0, 0, 2, 3, 4, 5, 6}[i];
    }

    private void setAmplifierInfo0xA6() {
        ArrayList arrayList = new ArrayList();
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5];
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6];
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7];
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 7;
        GeneralAmplifierData.frontRear = this.mCanBusInfoInt[4] - 7;
        arrayList.add(checkEntity(helperSetValue("speed_linkage_volume_adjustment", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2)))));
        arrayList.add(checkEntity(helperSetValue("surround_sound", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1)))));
        updateAmplifierActivity(null);
        saveAmplifierData();
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private void saveAmplifierData() {
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
        SharePreUtil.setIntValue(this.mContext, SHARE_379_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_VOLUME, 0);
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_BALANCE, 0);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_FADE, 0);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_BASS, 0);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_MIDDLE, 0);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_379_AMPLIFIER_TREBLE, 0);
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -83, 1, (byte) GeneralAmplifierData.volume});
        final byte[][] bArr = {new byte[]{22, -83, 2, (byte) (GeneralAmplifierData.leftRight + 1 + 9)}, new byte[]{22, -83, 3, (byte) (GeneralAmplifierData.frontRear + 1 + 9)}, new byte[]{22, -83, 4, (byte) (GeneralAmplifierData.bandBass + 1)}, new byte[]{22, -83, 5, (byte) (GeneralAmplifierData.bandMiddle + 1)}, new byte[]{22, -83, 6, (byte) (GeneralAmplifierData.bandTreble + 1)}};
        this.mTimerTask = new TimerTask() { // from class: com.hzbhd.canbus.car._379.MsgMgr.1
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

    private void setOriginalCarVideoStatus0xE8() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4])));
        this.CameraDelay = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        this.DepressedView = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[5];
        if (i == 0) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, true));
            arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            arrayList.add(new PanoramicBtnUpdateEntity(5, false));
            arrayList.add(new PanoramicBtnUpdateEntity(6, false));
        } else if (i == 1) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList.add(new PanoramicBtnUpdateEntity(3, true));
            arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            arrayList.add(new PanoramicBtnUpdateEntity(5, false));
            arrayList.add(new PanoramicBtnUpdateEntity(6, false));
        } else if (i == 2) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList.add(new PanoramicBtnUpdateEntity(4, true));
            arrayList.add(new PanoramicBtnUpdateEntity(5, false));
            arrayList.add(new PanoramicBtnUpdateEntity(6, false));
        } else if (i == 3) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            arrayList.add(new PanoramicBtnUpdateEntity(5, true));
            arrayList.add(new PanoramicBtnUpdateEntity(6, false));
        } else if (i == 4) {
            arrayList.add(new PanoramicBtnUpdateEntity(2, false));
            arrayList.add(new PanoramicBtnUpdateEntity(3, false));
            arrayList.add(new PanoramicBtnUpdateEntity(4, false));
            arrayList.add(new PanoramicBtnUpdateEntity(5, false));
            arrayList.add(new PanoramicBtnUpdateEntity(6, true));
        }
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
    }

    private void setLanguage0x94() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("language_setup", Integer.valueOf(resolveLanguage(this.mCanBusInfoInt[2])))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private int resolveLanguage(int i) {
        return new int[]{0, 0, 1, 0, 0, 2, 0, 3}[i];
    }

    private void setRemainder0x68() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("_379_item_12", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1)))));
        arrayList.add(checkEntity(helperSetValue("temperature_unit", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1)))));
        updateSettingActivity(null);
        updateGeneralSettingData(arrayList);
    }

    private void setSettingInfo0x61() {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.add(checkEntity(helperSetValue("S96ColorTitle", Integer.valueOf(this.mCanBusInfoInt[4]))));
        this.FactoryCode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 8);
        arrayList.add(checkEntity(helperSetValue("_379_item_01", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_02", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_03", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_04", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 2) + 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_05", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2) + 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_06", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_07", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_08", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 4)), true, true)));
        arrayList.add(checkEntity(helperSetValue("_379_item_09", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 2, 2) + 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_10", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 2) + 1))));
        arrayList.add(checkEntity(helperSetValue("_379_item_11", Integer.valueOf(this.mCanBusInfoInt[11]))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._379.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                TypeInView.toJudge(MsgMgr.this.FactoryCode);
            }
        });
    }

    private void setDriveInfo0x3F() {
        this.EngineSpeedEnable = Boolean.valueOf(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        this.InstantaneousSpeedEnable = Boolean.valueOf(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        setDriveIndo0x320x3F();
    }

    private void setDriveInfo0x32() {
        int[] iArr = this.mCanBusInfoInt;
        this.EngineSpeed = (iArr[4] << 8) + iArr[5];
        this.InstantaneousSpeed = (iArr[6] << 8) + iArr[7];
        setDriveIndo0x320x3F();
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[6], iArr2[7]));
    }

    private void setDriveIndo0x320x3F() {
        ArrayList arrayList = new ArrayList();
        if (this.EngineSpeedEnable.booleanValue()) {
            arrayList.add(checkDriveEntity(helperSetDriveDataValue("_214_car_speed1", this.EngineSpeed + "")));
        }
        if (this.InstantaneousSpeedEnable.booleanValue()) {
            arrayList.add(checkDriveEntity(helperSetDriveDataValue("_214_car_speed2", this.InstantaneousSpeed + "")));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setVersionInfo0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setRadarInfo0x41() {
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setAirInfo0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        this.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        this.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        this.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = resolveAcMax(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
        this.ac_max = resolveAcMax(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
        GeneralAirData.max_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        this.mac_heat = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        this.steering_wheel_heating = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        this.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        this.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        this.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        this.LeftHeat = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        this.RightHeat = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        this.LeftCold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        this.RightCold = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2);
        int[] iArr = this.mCanBusInfoInt;
        this.WindLevel = iArr[7];
        this.LeftTemp = iArr[8];
        this.RightTemp = iArr[9];
        int i = iArr[6];
        if (i == 2 || i == 4 || i == 7 || i == 10) {
            this.blowwindow = true;
        } else {
            this.blowwindow = false;
        }
        if (i == 3 || i == 4 || i == 5 || i == 10) {
            this.blowfoot = true;
        } else {
            this.blowfoot = false;
        }
        if (i == 6 || i == 5 || i == 7 || i == 10) {
            this.blowhead = true;
        } else {
            this.blowhead = false;
        }
        if (i == 0) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
            GeneralAirData.front_right_blow_window = false;
            GeneralAirData.front_right_blow_head = false;
            GeneralAirData.front_right_blow_foot = false;
        } else if (i != 10) {
            switch (i) {
                case 2:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 3:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 4:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = false;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = false;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 5:
                    GeneralAirData.front_left_blow_window = false;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_window = false;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = true;
                    break;
                case 6:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
                case 7:
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_left_blow_foot = false;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_right_blow_foot = false;
                    break;
            }
        } else {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveTemp(this.mCanBusInfoInt[9]);
        updateOutDoorTemp(this.mContext, ((this.mCanBusInfoInt[13] * 0.5d) - 40.0d) + getTempUnitC(this.mContext));
        updateAirActivity(this.mContext, 1001);
    }

    private String resolveTemp(int i) {
        String str = (i * 0.5d) + getTempUnitC(this.mContext);
        if (i == 254) {
            str = "Low_Temp";
        }
        return i == 255 ? "High_Temp" : str;
    }

    private boolean resolveAcMax(int i) {
        Boolean bool = false;
        if (i == 3) {
            bool = true;
        }
        return bool.booleanValue();
    }

    private void setDoorInfo0x12() {
        int i = this.Memory;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return;
        }
        this.Memory = i2;
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i2);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        updateDoorView(this.mContext);
    }

    private void SetTrackInfo0x11() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
    }

    private void SetWheelKey0x11() {
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            RealKeyClick(0);
            return;
        }
        if (i == 1) {
            RealKeyClick(7);
            return;
        }
        if (i == 2) {
            RealKeyClick(8);
            return;
        }
        if (i == 3) {
            RealKeyClick(3);
            return;
        }
        if (i == 5) {
            RealKeyClick(21);
            return;
        }
        if (i == 6) {
            RealKeyClick(20);
            return;
        }
        if (i == 10) {
            RealKeyClick(14);
            return;
        }
        if (i == 11) {
            RealKeyClick(15);
        } else if (i == 23) {
            RealKeyClick(HotKeyConstant.K_ACTION_MEDIA);
        } else {
            if (i != 40) {
                return;
            }
            RealKeyClick(HotKeyConstant.K_SPEECH);
        }
    }

    private void RealKeyClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        this.mSettingItemHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                this.mSettingItemHashMap.put(itemListBean.getTitleSrn(), new SettingUpdateHelper(new SettingUpdateEntity(i, i2, "null_value"), itemListBean.getMin()));
            }
        }
    }

    private void initDriveItem(DriverDataPageUiSet driverDataPageUiSet) {
        this.mDriveItemHashMap = new HashMap<>();
        List<DriverDataPageUiSet.Page> list = driverDataPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<DriverDataPageUiSet.Page.Item> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mDriveItemHashMap.put(itemList.get(i2).getTitleSrn(), new DriveDataUpdateHelper(new DriverUpdateEntity(i, i2, "null_value")));
            }
        }
    }

    private SettingUpdateEntity checkEntity(SettingUpdateEntity settingUpdateEntity) {
        if (settingUpdateEntity.getLeftListIndex() == -1 || settingUpdateEntity.getRightListIndex() == -1) {
            return null;
        }
        return settingUpdateEntity;
    }

    private DriverUpdateEntity checkDriveEntity(DriverUpdateEntity driverUpdateEntity) {
        if (driverUpdateEntity.getPage() == -1 || driverUpdateEntity.getIndex() == -1) {
            return null;
        }
        return driverUpdateEntity;
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value")));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z);
    }

    private SettingUpdateEntity helperSetValue(String str, Object obj, boolean z, boolean z2) {
        if (!this.mSettingItemHashMap.containsKey(str)) {
            this.mSettingItemHashMap.put(str, new SettingUpdateHelper(new SettingUpdateEntity(-1, -1, "null_value").setEnable(false).setValueStr(false)));
        }
        return this.mSettingItemHashMap.get(str).setValue(obj).setEnable(z).setValueStr(z2);
    }

    private DriverUpdateEntity helperSetDriveDataValue(String str, String str2) {
        if (!this.mDriveItemHashMap.containsKey(str)) {
            this.mDriveItemHashMap.put(str, new DriveDataUpdateHelper(new DriverUpdateEntity(-1, -1, "null")));
        }
        return this.mDriveItemHashMap.get(str).setValue(str2);
    }

    private static class DriveDataUpdateHelper {
        private DriverUpdateEntity entity;

        public DriveDataUpdateHelper(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public void setEntity(DriverUpdateEntity driverUpdateEntity) {
            this.entity = driverUpdateEntity;
        }

        public DriverUpdateEntity getEntity() {
            return this.entity;
        }

        public DriverUpdateEntity setValue(String str) {
            this.entity.setValue(str);
            return this.entity;
        }
    }

    private static class SettingUpdateHelper {
        private SettingUpdateEntity entity;
        private int progressMin;

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity) {
            this(settingUpdateEntity, 0);
        }

        public SettingUpdateHelper(SettingUpdateEntity settingUpdateEntity, int i) {
            this.entity = settingUpdateEntity;
            this.progressMin = i;
        }

        public SettingUpdateEntity getEntity() {
            return this.entity;
        }

        public SettingUpdateEntity setValue(Object obj) {
            if (obj instanceof Integer) {
                Integer num = (Integer) obj;
                this.entity.setValue(Integer.valueOf(num.intValue() + this.progressMin));
                this.entity.setProgress(num.intValue());
            } else {
                this.entity.setValue(obj);
            }
            return this.entity;
        }

        public SettingUpdateEntity setEnable(boolean z) {
            this.entity.setEnable(z);
            return this.entity;
        }

        private SettingUpdateEntity setValueStr(boolean z) {
            this.entity.setValueStr(z);
            return this.entity;
        }
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    protected void forceReverse(Context context, boolean z) {
        super.forceReverse(context, z);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public Activity getCurrentActivity() {
        return getActivity();
    }
}
