package com.hzbhd.canbus.car._323;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SongListEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalBtnAction;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    static final String DEVICE_WORK_MODE_AUX = "AUX";
    static final String DEVICE_WORK_MODE_CD = "CD";
    static final String DEVICE_WORK_MODE_MEDIA_OFF = "Media Off";
    static final String DEVICE_WORK_MODE_OTHERS = "Others";
    static final String DEVICE_WORK_MODE_RADIO = "Radio";
    private static final String SHARE_323_AMPLIFIER_BALANCE = "share_323_amplifier_balance";
    private static final String SHARE_323_AMPLIFIER_BASS = "share_323_amplifier_bass";
    private static final String SHARE_323_AMPLIFIER_FADE = "share_323_amplifier_fade";
    private static final String SHARE_323_AMPLIFIER_MIDDLE = "share_323_amplifier_middle";
    private static final String SHARE_323_AMPLIFIER_TREBLE = "share_323_amplifier_treble";
    private static final String SHARE_323_AMPLIFIER_VOLUME = "share_323_amplifier_volume";
    static final int VEHICLE_TYPE_ES = 5;
    static final int VEHICLE_TYPE_IS = 1;
    static final int VEHICLE_TYPE_RX = 18;
    static final int _323_AMPLIFIER_BAND_MAX = 2;
    static final int _323_AMPLIFIER_HALF_MAX = 7;
    private static boolean isOriginalCdFirst = true;
    public static List<OriginalCarDeviceUpdateEntity> mList;
    int currentDisc;
    int currentPtore;
    private int[] m0x1dData;
    private int[] m0x1eData;
    private byte[] mAirData;
    private int[] mAmplifierDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mCurrentClick;
    private int mCurrentStatus;
    private String mDeviceStatusNow;
    private int mDifferentId;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private OriginalCarDevicePageUiSet mOriginalSet;
    private int mPanel;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private int mRockKey;
    private byte[] mTrackData;
    private UiMgr mUiMgr;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap = new HashMap<>();
    private boolean isOriginalCd = true;
    private String isCdSongList = "";
    private String _0x61runningStatus = " ";
    private byte[] m0x31Command = new byte[7];
    private int mLastDataCd = 0;
    DecimalFormat mDecimalFormat = new DecimalFormat("00");
    private List<OriginalCarDeviceUpdateEntity> mList1 = new ArrayList();
    private List<OriginalCarDeviceUpdateEntity> mList2 = new ArrayList();

    private String disStatus(boolean z) {
        return z ? "Have Disk" : "No Disk";
    }

    private String getDeviceWorkMode(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 255 ? "" : DEVICE_WORK_MODE_OTHERS : DEVICE_WORK_MODE_AUX : DEVICE_WORK_MODE_CD : DEVICE_WORK_MODE_RADIO : DEVICE_WORK_MODE_MEDIA_OFF;
    }

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private String setBand(int i) {
        return i != 1 ? i != 2 ? i != 16 ? "" : "AM" : "FM2" : "FM1";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mUiMgr = getUiMgr(context);
        initSettingsItem(context);
        this.mDifferentId = getCurrentCanDifferentId();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mOriginalSet = UiMgrFactory.getCanUiMgr(context).getOriginalCarDevicePageUiSet(context);
        initAmplifierData(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 22) {
            set0x16CarSpeedInfo();
        }
        if (i == 32) {
            set0x20WheelKey(context);
            return;
        }
        if (i == 36) {
            set0x24CarBaseInfo(context);
            return;
        }
        if (i == 80) {
            if (equal(Integer.valueOf(this.mDifferentId), 5)) {
                set0x50RotationInfo();
                return;
            }
            return;
        }
        if (i == 29) {
            set0x1dRearRadarData(context);
            return;
        }
        if (i == 30) {
            set0x1eFrontRadarData(context);
            return;
        }
        if (i == 40) {
            set0x28AirData(context);
            return;
        }
        if (i == 41) {
            set0x29TrackData(context);
            return;
        }
        switch (i) {
            case 48:
                set0x30VersionData(context);
                break;
            case 49:
                set0x31Amplifier(context);
                break;
            case 50:
                set0x32SystemData();
                break;
            default:
                switch (i) {
                    case 96:
                        if (equal(Integer.valueOf(this.mDifferentId), 18, 1)) {
                            set0x60WheelKey();
                            break;
                        }
                        break;
                    case 97:
                        if (equal(Integer.valueOf(this.mDifferentId), 18, 1)) {
                            set0x61CdStatus();
                            break;
                        }
                        break;
                    case 98:
                        if (equal(Integer.valueOf(this.mDifferentId), 18, 1)) {
                            set0x62DiscInfo();
                            break;
                        }
                        break;
                    case 99:
                        set0x63VehicleInfo();
                        break;
                    case 100:
                        set0x64PanelKey();
                        break;
                    case 101:
                        if (equal(Integer.valueOf(this.mDifferentId), 18)) {
                            set0x65RockerData(context);
                            break;
                        }
                        break;
                }
        }
    }

    private boolean equal(Object obj, Object... objArr) {
        for (Object obj2 : objArr) {
            if (obj.equals(obj2)) {
                return true;
            }
        }
        return false;
    }

    private void set0x16CarSpeedInfo() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, new DecimalFormat("0.0").format(iArr[2] | (iArr[3] << 8)) + "KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void set0x1dRearRadarData(Context context) {
        if (is0x1dDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x1eFrontRadarData(Context context) {
        if (is0x1eDataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 0) {
            realKeyLongClick1(context, 0);
            return;
        }
        if (i2 == 1) {
            realKeyLongClick1(context, 7);
            return;
        }
        if (i2 == 2) {
            realKeyLongClick1(context, 8);
            return;
        }
        if (i2 == 19) {
            realKeyLongClick1(context, 45);
            return;
        }
        if (i2 != 20) {
            switch (i2) {
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
            }
            return;
        }
        realKeyLongClick1(context, 46);
    }

    private void set0x21OriginalCarUsb() {
        DecimalFormat decimalFormat = new DecimalFormat("00");
        GeneralOriginalCarDeviceData.cdStatus = getCdStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2));
        GeneralOriginalCarDeviceData.runningState = getRunStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4));
        GeneralOriginalCarDeviceData.progress = this.mCanBusInfoInt[11];
        GeneralOriginalCarDeviceData.startTime = decimalFormat.format(this.mCanBusInfoInt[4]) + ":" + decimalFormat.format(this.mCanBusInfoInt[5]);
        GeneralOriginalCarDeviceData.endTime = "";
        String str = this.mCanBusInfoInt[4] + ":" + this.mCanBusInfoInt[5];
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[6] << 8) | iArr[7];
        int i2 = iArr[9] | (iArr[8] << 8);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, i + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, i2 + ""));
        GeneralOriginalCarDeviceData.mList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private void set0x24CarBaseInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0x28AirData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        if (isAirDataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
            GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
            GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = resolverAirTemperature(this.mCanBusInfoByte[4]);
            GeneralAirData.front_right_temperature = resolverAirTemperature(this.mCanBusInfoByte[5]);
            GeneralAirData.front_right_auto_wind = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
            updateOutDoorTemp(context, resolverOutdoorTemperature(context));
            updateAirActivity(context, 1001);
        }
    }

    private void set0x29TrackData(Context context) {
        if (isTrackDataChange()) {
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(this.mCanBusInfoByte[2], (byte) DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4), 0, 380, 12);
            updateParkUi(null, context);
        }
    }

    private void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    byte[] get0x31Data() {
        return this.m0x31Command;
    }

    private void set0x31Amplifier(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x31Command = Arrays.copyOf(bArr, bArr.length);
        if (isDataChange(this.mCanBusInfoInt)) {
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 4);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
            int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
            int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4);
            int i = this.mCanBusInfoInt[5];
            GeneralAmplifierData.bandBass = intFromByteWithBit3 - 2;
            GeneralAmplifierData.bandMiddle = intFromByteWithBit5 - 2;
            GeneralAmplifierData.bandTreble = intFromByteWithBit4 - 2;
            GeneralAmplifierData.volume = i;
            GeneralAmplifierData.frontRear = 7 - intFromByteWithBit;
            GeneralAmplifierData.leftRight = intFromByteWithBit2 - 7;
            updateAmplifierActivity(null);
            saveAmplifierData(context, this.mCanId);
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("_323_amplifier_setting_1").setValue(Integer.valueOf(this.mCanBusInfoInt[4] == 8 ? 1 : 0)));
            arrayList.add(getSettingUpdateEntity("_323_amplifier_setting_2").setValue(Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6])))));
            arrayList.add(getSettingUpdateEntity("_323_amplifier_setting_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x32SystemData() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_323_amplifier_setting_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1))));
        arrayList.add(getSettingUpdateEntity("_323_amplifier_setting_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x50RotationInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2] | (iArr[3] << 8);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 1, i + " rpm"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x60WheelKey() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 8))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x61CdStatus() {
        this.mCurrentClick = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        this.mCurrentStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4);
        String strDisStatus = disStatus(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        String strDisStatus2 = disStatus(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]));
        String strDisStatus3 = disStatus(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]));
        String strDisStatus4 = disStatus(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]));
        String strDisStatus5 = disStatus(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]));
        String strDisStatus6 = disStatus(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SongListEntity("1. " + strDisStatus));
        arrayList.add(new SongListEntity("2. " + strDisStatus2));
        arrayList.add(new SongListEntity("3. " + strDisStatus3));
        arrayList.add(new SongListEntity("4. " + strDisStatus4));
        arrayList.add(new SongListEntity("5. " + strDisStatus5));
        arrayList.add(new SongListEntity("6. " + strDisStatus6));
        GeneralOriginalCarDeviceData.runningState = getChangerStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4));
        this._0x61runningStatus = GeneralOriginalCarDeviceData.runningState;
        GeneralOriginalCarDeviceData.songList = arrayList;
        updateOriginalCarDeviceActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDeviceUpdateEntity(5, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) + ""));
        this.mList1.clear();
        this.mList1.addAll(arrayList2);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1);
        updateOriginalCarDeviceActivity(null);
    }

    private void set0x62DiscInfo() throws Resources.NotFoundException {
        GeneralOriginalCarDeviceData.cdStatus = getDeviceWorkMode(this.mCanBusInfoInt[2]);
        if (!GeneralOriginalCarDeviceData.cdStatus.equals(this.mDeviceStatusNow)) {
            this.mDeviceStatusNow = GeneralOriginalCarDeviceData.cdStatus;
            cleanDevice();
            cleanSongList();
            enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        }
        if (isDeviceStatusSame(DEVICE_WORK_MODE_CD)) {
            if (this.isOriginalCd || this.mCurrentClick == 0) {
                setOriginalCdPage();
                setCdInfo();
                Log.i("cbc", "set0x62DiscInfo: " + this.mCurrentClick);
            } else {
                if (isOriginalCdChange()) {
                    openAuxIn();
                }
                Log.i("cbc", "set0x62DiscInfo2: " + this.mCurrentClick);
            }
        }
        if (isDeviceStatusSame(DEVICE_WORK_MODE_RADIO)) {
            setOriginalRadioPage();
            setRadioInfo();
        }
        if (DEVICE_WORK_MODE_RADIO.equals(GeneralOriginalCarDeviceData.cdStatus) || DEVICE_WORK_MODE_CD.equals(GeneralOriginalCarDeviceData.cdStatus)) {
            return;
        }
        setOriginalOtherPage();
    }

    private void set0x63VehicleInfo() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 32) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_1)));
        } else if (i == 33) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_2)));
        } else if (i == 48) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_3)));
        } else if (i == 49) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_4)));
        } else if (i == 80) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_5)));
        } else if (i == 81) {
            arrayList.add(new DriverUpdateEntity(0, 2, this.mContext.getResources().getString(R.string._323_carType_6)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x64PanelKey() {
        int i = this.mPanel;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mPanel = i2;
        }
        if (i2 == 0) {
            realKeyLongClick1(this.mContext, 0);
            return;
        }
        if (i2 == 1) {
            changeBandAm1();
            return;
        }
        if (i2 == 2) {
            changeBandFm1();
            return;
        }
        if (i2 == 3) {
            changeBandFm2();
        } else if (i2 == 4) {
            realKeyLongClick1(this.mContext, 130);
        } else {
            if (i2 != 32) {
                return;
            }
            realKeyLongClick1(this.mContext, 4);
        }
    }

    private void set0x65RockerData(Context context) {
        int i = this.mRockKey;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mRockKey = i2;
        }
        if (i2 == 0) {
            realKeyLongClick1(context, 0);
        }
        if (i2 == 1) {
            realKeyLongClick1(context, 45);
            return;
        }
        if (i2 == 3) {
            realKeyLongClick1(context, 48);
            return;
        }
        if (i2 == 5) {
            realKeyLongClick1(context, 46);
            return;
        }
        if (i2 == 7) {
            realKeyLongClick1(context, 47);
            return;
        }
        switch (i2) {
            case 16:
                realKeyLongClick1(context, 7);
                break;
            case 17:
                realKeyLongClick1(context, 8);
                break;
            case 18:
                realKeyLongClick1(context, 49);
                break;
            case 19:
                realKeyLongClick1(context, 50);
                break;
            case 20:
                realKeyLongClick1(context, 52);
                break;
            case 21:
                realKeyLongClick1(context, 59);
                break;
            case 22:
                realKeyLongClick1(context, 128);
                break;
        }
    }

    void turnToRearCdPage() {
        cleanDevice();
        cleanSongList();
        enterAuxIn2(this.mContext, Constant.OriginalDeviceActivity);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux", OriginalBtnAction.RPT, OriginalBtnAction.RDM, OriginalBtnAction.SCAN}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "down", "right", OriginalBtnAction.NEXT_DISC}, false, true);
    }

    private void setOriginalOtherPage() {
        GeneralOriginalCarDeviceData.mList = null;
        changeOriginalDevicePage(new ArrayList(), new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux"}, new String[0], false, false);
    }

    private void setRadioInfo() throws Resources.NotFoundException {
        String string;
        if (this.mCanBusInfoInt[3] == 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new OriginalCarDeviceUpdateEntity(0, setBand(this.mCanBusInfoInt[4])));
            arrayList.add(new OriginalCarDeviceUpdateEntity(1, setState(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
            arrayList.add(new OriginalCarDeviceUpdateEntity(2, setPreset(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new OriginalCarDeviceUpdateEntity(3, setFreq(iArr[6], iArr[7])));
            this.currentPtore = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
            if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5])) {
                string = this.mContext.getResources().getString(R.string.ford_original_status2);
            } else {
                string = this.mContext.getResources().getString(R.string.ford_original_status1);
            }
            GeneralOriginalCarDeviceData.runningState = string;
            GeneralOriginalCarDeviceData.mList = arrayList;
            updateOriginalCarDeviceActivity(null);
            return;
        }
        setPresetData();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void setPresetData() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[4];
        int i2 = 2;
        if (i != 0 && i != 1 && i != 2) {
            switch (i) {
                case 16:
                case 17:
                case 18:
                    while (i2 < 8) {
                        int[] iArr = this.mCanBusInfoInt;
                        int i3 = i2 * 2;
                        arrayList.add(new SongListEntity((i2 - 1) + ". " + ((iArr[i3 + 2] * 256) + iArr[i3 + 1]) + "KHz"));
                        i2++;
                    }
                    break;
            }
        } else {
            while (i2 < 8) {
                int[] iArr2 = this.mCanBusInfoInt;
                int i4 = i2 * 2;
                arrayList.add(new SongListEntity((i2 - 1) + ". " + (((iArr2[i4 + 2] * 256) + iArr2[i4 + 1]) / 100.0f) + "MHz"));
                i2++;
            }
        }
        GeneralOriginalCarDeviceData.songList = arrayList;
        updateOriginalCarDeviceActivity(null);
    }

    private String setFreq(int i, int i2) {
        if (this.mCanBusInfoInt[4] == 16) {
            return (i + (i2 * 256)) + "KHz";
        }
        return ((i + (i2 * 256)) / 100.0f) + "MHz";
    }

    private String setPreset(int i) {
        return i == 0 ? "" : "P" + i;
    }

    private String setState(boolean z) {
        Resources resources;
        int i;
        if (z) {
            resources = this.mContext.getResources();
            i = R.string.stereo;
        } else {
            resources = this.mContext.getResources();
            i = R.string._288_divice_radio_mode;
        }
        return resources.getString(i);
    }

    private void setOriginalRadioPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_preset", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux"}, new String[]{"up", "left", OriginalBtnAction.RADIO_SCAN, "right", "down"}, true, false);
    }

    private void openAuxIn() {
        ComponentName componentName = new ComponentName(HzbhdComponentName.MISC, "com.hzbhd.misc.auxin.MainActivity");
        Intent intent = new Intent();
        intent.setComponent(componentName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.mContext.startActivity(intent);
    }

    private boolean isOriginalCdChange() {
        int i = this.mCurrentStatus;
        if (i == this.mLastDataCd) {
            return false;
        }
        this.mLastDataCd = i;
        if (!isOriginalCdFirst) {
            return true;
        }
        isOriginalCdFirst = false;
        return false;
    }

    private void setCdInfo() {
        ArrayList arrayList = new ArrayList();
        this.currentDisc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + ""));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, setPlayStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
        arrayList.add(new OriginalCarDeviceUpdateEntity(2, setCycleStatus(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))));
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(3, setByteHighLow(iArr[7], iArr[6])));
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new OriginalCarDeviceUpdateEntity(4, setByteHighLow(iArr2[9], iArr2[8])));
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralOriginalCarDeviceData.endTime = resolveTime(iArr3[10], iArr3[11], iArr3[12]);
        int[] iArr4 = this.mCanBusInfoInt;
        int i = iArr4[13];
        int i2 = iArr4[14];
        GeneralOriginalCarDeviceData.startTime = resolveTime(i, i2, i2);
        int[] iArr5 = this.mCanBusInfoInt;
        int i3 = iArr5[10];
        int i4 = iArr5[11];
        int i5 = iArr5[12];
        if ((i3 * 3600) + (i4 * 60) + i5 != 0) {
            GeneralOriginalCarDeviceData.progress = ((((iArr5[13] * 3600) + (iArr5[14] * 60)) + iArr5[15]) * 100) / (((i3 * 3600) + (i4 * 60)) + i5);
        }
        this.mList2.clear();
        this.mList2.addAll(arrayList);
        GeneralOriginalCarDeviceData.mList = mergeLists(this.mList1, this.mList2);
        updateOriginalCarDeviceActivity(null);
    }

    private String resolveTime(int i, int i2, int i3) {
        return (i == 255 || i2 == 255 || i3 == 255) ? "--" : this.mDecimalFormat.format(i) + ":" + this.mDecimalFormat.format(i2) + ":" + this.mDecimalFormat.format(i3);
    }

    private String setByteHighLow(int i, int i2) {
        int i3 = (i * 256) + i2;
        return i3 == 255 ? "--" : i3 + "";
    }

    private String setCycleStatus(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._288_divice_cd_mode3);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getResources().getString(R.string._288_divice_cd_mode5);
        }
        return this.mContext.getResources().getString(R.string._288_divice_cd_mode4);
    }

    private String setPlayStatus(int i) {
        Resources resources;
        int i2;
        if (i == 1) {
            resources = this.mContext.getResources();
            i2 = R.string._288_divice_cd_mode2;
        } else {
            resources = this.mContext.getResources();
            i2 = R.string._288_divice_cd_mode1;
        }
        return resources.getString(i2);
    }

    private void changeOriginalDevicePage(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr, String[] strArr2, boolean z, boolean z2) {
        OriginalCarDevicePageUiSet originalCarDevicePageUiSet = UiMgrFactory.getCanUiMgr(this.mContext).getOriginalCarDevicePageUiSet(this.mContext);
        if (originalCarDevicePageUiSet == null) {
            return;
        }
        originalCarDevicePageUiSet.setItems(list);
        originalCarDevicePageUiSet.setRowTopBtnAction(strArr);
        originalCarDevicePageUiSet.setRowBottomBtnAction(strArr2);
        originalCarDevicePageUiSet.setHaveSongList(z);
        originalCarDevicePageUiSet.setHavePlayTimeSeekBar(z2);
        Bundle bundle = new Bundle();
        bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        updateOriginalCarDeviceActivity(bundle);
    }

    private void setOriginalCdPage() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_disc", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_99", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_100", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_118_setting_title_101", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", ""));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_288_divice_status_100", ""));
        changeOriginalDevicePage(arrayList, new String[]{OriginalBtnAction.FM1, OriginalBtnAction.FM2, OriginalBtnAction.AM, OriginalBtnAction.CDC, OriginalBtnAction.REAR_CDC, "aux", OriginalBtnAction.RPT, OriginalBtnAction.RDM, OriginalBtnAction.SCAN}, new String[]{OriginalBtnAction.PREV_DISC, "left", "up", "down", "right", OriginalBtnAction.NEXT_DISC}, true, true);
    }

    private boolean isCurrentStatus(String str) {
        return str.equals(GeneralOriginalCarDeviceData.cdStatus);
    }

    private boolean isDeviceStatusSame(String str) {
        return isCurrentStatus(str);
    }

    private void cleanSongList() {
        if (GeneralOriginalCarDeviceData.songList != null) {
            GeneralOriginalCarDeviceData.songList.clear();
        }
    }

    private void cleanDevice() {
        GeneralOriginalCarDeviceData.runningState = "";
        GeneralOriginalCarDeviceData.am_st = false;
        GeneralOriginalCarDeviceData.st = false;
        GeneralOriginalCarDeviceData.scan = false;
        GeneralOriginalCarDeviceData.disc_scan = false;
        GeneralOriginalCarDeviceData.rpt = false;
        GeneralOriginalCarDeviceData.rpt_disc = false;
        GeneralOriginalCarDeviceData.rdm_off = false;
        GeneralOriginalCarDeviceData.rdm_disc = false;
        GeneralOriginalCarDeviceData.startTime = "     ";
        GeneralOriginalCarDeviceData.endTime = "     ";
        GeneralOriginalCarDeviceData.progress = 0;
        updateOriginalCarDeviceActivity(null);
    }

    private static <T> List<T> mergeLists(List<T>... listArr) {
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

    private String getChangerStatus(int i) {
        return CommUtil.getStrByResId(this.mContext, "_288_divice_status_" + i);
    }

    private void initAmplifierData(Context context) {
        if (context != null) {
            GeneralAmplifierData.leftRight = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_BALANCE, GeneralAmplifierData.leftRight);
            GeneralAmplifierData.frontRear = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_FADE, GeneralAmplifierData.frontRear);
            GeneralAmplifierData.bandBass = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_BASS, GeneralAmplifierData.bandBass);
            GeneralAmplifierData.bandMiddle = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_MIDDLE, GeneralAmplifierData.bandMiddle);
            GeneralAmplifierData.bandTreble = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_TREBLE, GeneralAmplifierData.bandTreble);
            GeneralAmplifierData.volume = SharePreUtil.getIntValue(context, SHARE_323_AMPLIFIER_VOLUME, GeneralAmplifierData.volume);
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._323.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}}).iterator();

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

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    private void initSettingsItem(Context context) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray sparseArray = new SparseArray();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private String resolverOutdoorTemperature(Context context) {
        return ((this.mCanBusInfoInt[7] - 80.0d) * 0.5d) + getTempUnitC(context);
    }

    private String resolverAirTemperature(int i) {
        return i == 0 ? "LO" : i == 31 ? "HI" : (1 > i || i > 29) ? "" : (((i + 36) * 0.5f) - 0.5d) + getTempUnitC(this.mContext);
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mAirData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isDoorChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen && this.mFrontStatus == GeneralDoorData.isFrontOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        return true;
    }

    private double getMinute() {
        int[] iArr = this.mCanBusInfoInt;
        return (((iArr[4] + iArr[5]) / (iArr[11] * 0.01d)) / 60.0d) % 60.0d;
    }

    private double getSecond() {
        int[] iArr = this.mCanBusInfoInt;
        return ((iArr[4] + iArr[5]) / (iArr[11] * 0.01d)) % 60.0d;
    }

    private String getCdStatus(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._323_original_status_1);
        }
        if (i != 1) {
            return i != 2 ? "" : this.mContext.getString(R.string._323_original_status_3);
        }
        return this.mContext.getString(R.string._323_original_status_2);
    }

    private String getRunStatus(int i) {
        switch (i) {
            case 0:
                return this.mContext.getString(R.string._323_original_runningStatus_1);
            case 1:
                return this.mContext.getString(R.string._323_original_runningStatus_2);
            case 2:
                return this.mContext.getString(R.string._323_original_runningStatus_3);
            case 3:
                return this.mContext.getString(R.string._323_original_runningStatus_4);
            case 4:
                return this.mContext.getString(R.string._323_original_runningStatus_5);
            case 5:
                return this.mContext.getString(R.string._323_original_runningStatus_6);
            case 6:
                return this.mContext.getString(R.string._323_original_runningStatus_7);
            default:
                return "";
        }
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private boolean is0x1dDataChange() {
        if (Arrays.equals(this.m0x1dData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x1dData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x1eDataChange() {
        if (Arrays.equals(this.m0x1eData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x1eData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
