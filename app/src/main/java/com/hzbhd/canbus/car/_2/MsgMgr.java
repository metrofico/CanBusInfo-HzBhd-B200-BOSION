package com.hzbhd.canbus.car._2;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.sql.Driver;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Priority;
import org.apache.log4j.net.SyslogAppender;


public class MsgMgr extends AbstractMsgMgr {
    static final int CAR_Golf_7 = 1;
    static final String _2_PARK_VOICE = "_2_park_voice";
    private int frontRadarStatus;
    private int isRearInfoChange;
    private int isRearStateChange;
    private int isRearTempChange;
    private int[] m0x22Data;
    private int[] m0x23Data;
    private int[] m0x32Data;
    private int[] m0x33Data;
    private int[] m0x65Data;
    private int[] m0x66Data;
    private boolean mAirClean;
    private String mAirUnit;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat;
    private int mDifferentId;
    private String mDistance;
    private String mElectric;
    private String mEndurance;
    private boolean mFrontStatus;
    private String mFuelUnit;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap;
    private String mSpeedUnit;
    private String[] mTireInfoArray;
    private String[] mTireInfoArray2;
    private List<TireResolve> mTireResolveList;
    private List<TireUpdateEntity> mTireUpdateEntityList;
    private String[] mTireWarningArray;
    private UiMgr mUiMgr;
    private final String TAG = "_2_MsgMgr";
    private final String _2_AIR_TEMP = "_2_air_temp";
    private final String _2_AIR_STATE = "_2_air_state";
    private final String _2_AIR_INFO = "_2_air_info";
    private final String _2_OFF_INDIVIDUAL = "_2_off_individual";
    private final String _2_TIRE_UNIT = "_2_tire_unit";
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private String[] mVehicleTips = new String[34];
    private String[] mStartStopMsgs = new String[9];
    private String[] mVehicleReports = new String[MpegConstantsDef.MPEG_KEYBOARD_STATUS_INT];
    private String[] mConvTips = new String[18];
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean rearRadarTag = false;
    private boolean frontRadarTag = false;
    private boolean leftRadarTag = false;
    private boolean rightRadarTag = false;
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private List<WarningEntity> m0x60Msgs = new ArrayList();
    private List<WarningEntity> m0x61Msgs = new ArrayList();
    private List<WarningEntity> m0x62Msgs = new ArrayList();

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mDecimalFormat = new DecimalFormat("0.0");
        this.mDifferentId = getCurrentCanDifferentId();
        this.frontRadarStatus = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 64, -1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 65, -1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 80, -1});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 99, -1});
        GeneralTireData.isHaveSpareTire = false;
        initSettingsItem(context);
        initMessages(context);
        initTireResolveTool();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        try {
            this.mCanBusInfoByte = bArr;
            int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
            this.mCanBusInfoInt = byteArrayToIntArray;
            int i = byteArrayToIntArray[1];
            if (i == 22) {
                set0x16SpeedInfo();
            } else if (i == 39) {
                set0x27OutDoorTemp(context);
            } else if (i == 41) {
                set0x29TrackData(context);
            } else if (i == 80) {
                set0x50DriverData();
            } else if (i == 149) {
                set0x95ERAInfo();
            } else if (i == 64) {
                set0x40CarState();
            } else if (i == 65) {
                set0x41CarEnable();
            } else if (i == 101) {
                set0x65TireWarning();
            } else if (i != 102) {
                switch (i) {
                    case 32:
                        set0x20WheelKey(context);
                        break;
                    case 33:
                        set0x21AirInfo(context);
                        break;
                    case 34:
                        set0x22RearRadar(context);
                        break;
                    case 35:
                        set0x23FrontRadar(context);
                        break;
                    case 36:
                        set0x24DoorData(context);
                        break;
                    case 37:
                        set0x25ParkingAssist(context);
                        break;
                    default:
                        switch (i) {
                            case 47:
                                set0x2FWheelCommand(context);
                                break;
                            case 48:
                                set0x30VersionData(context);
                                break;
                            case 49:
                                set0x31AirCleaningState();
                                break;
                            case 50:
                                set0x32LeftRadar(context);
                                break;
                            case 51:
                                set0x33RightRadar(context);
                                break;
                            default:
                                switch (i) {
                                    case 96:
                                        set0x60Data(context);
                                        break;
                                    case 97:
                                        set0x61Data(context);
                                        break;
                                    case 98:
                                        set0x62Data(context);
                                        break;
                                }
                        }
                }
            } else {
                set0x66TireInfo();
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        switch (i2) {
            case 0:
                realKeyLongClick1(context, 0);
                break;
            case 1:
                realKeyLongClick1(context, 7);
                break;
            case 2:
                realKeyLongClick1(context, 8);
                break;
            case 3:
                realKeyLongClick1(context, 46);
                break;
            case 4:
                realKeyLongClick1(context, 45);
                break;
            case 5:
                realKeyLongClick1(context, 14);
                break;
            case 6:
                realKeyLongClick1(context, 3);
                break;
            case 7:
                realKeyLongClick1(context, 2);
                break;
            case 8:
                realKeyLongClick1(context, HotKeyConstant.K_SPEECH);
                break;
        }
    }

    private void set0x2FWheelCommand(Context context) {
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[2];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        if (i2 == 1) {
            realKeyClick4(context, 45);
        }
        if (i2 == 2) {
            realKeyClick4(context, 46);
            return;
        }
        if (i2 == 3) {
            realKeyClick4(context, 22);
            return;
        }
        if (i2 == 4) {
            realKeyClick4(context, 23);
            return;
        }
        if (i2 == 5) {
            realKeyClick4(context, 17);
            return;
        }
        switch (i2) {
            case 17:
                realKeyClick4(context, 14);
                break;
            case 18:
                realKeyClick4(context, 15);
                break;
            case 19:
                realKeyClick4(context, 39);
                break;
            case 20:
                realKeyClick4(context, 40);
                break;
            case 21:
                realKeyClick4(context, 41);
                break;
            case 22:
                realKeyClick4(context, 135);
                break;
            case 23:
                realKeyClick4(context, 136);
                break;
            case 24:
                if (this.mDifferentId == 1) {
                    realKeyClick4(context, 137);
                    break;
                }
                break;
            case 25:
                if (this.mDifferentId == 1) {
                    realKeyClick4(context, 138);
                    break;
                }
                break;
        }
    }

    private void set0x21AirInfo(Context context) {
        if ((this.mCanBusInfoInt[6] & 1) == 1) {
            this.mAirUnit = getTempUnitF(context);
        } else {
            this.mAirUnit = getTempUnitC(context);
        }
        if (isAirMsgRepeat(this.mCanBusInfoByte)) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.clean_air = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        this.mAirClean = GeneralAirData.clean_air;
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[7]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 3);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 3);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 2);
        GeneralAirData.rear_temperature = resolveAirTemperature(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 4, 3);
        GeneralAirData.rear_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 1, 3);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[10]);
        GeneralAirData.rear_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
        if (this.mCanBusInfoByte.length > 6) {
            int[] iArr = this.mCanBusInfoInt;
            this.isRearTempChange = iArr[9];
            this.isRearStateChange = iArr[10];
            this.isRearInfoChange = iArr[11];
        }
        if (isOnlyRearUpdate()) {
            SharePreUtil.setIntValue(context, "_2_air_temp", this.isRearTempChange);
            SharePreUtil.setIntValue(context, "_2_air_state", this.isRearStateChange);
            SharePreUtil.setIntValue(context, "_2_air_info", this.isRearInfoChange);
            updateAirActivity(context, 1003);
            return;
        }
        updateAirActivity(context, 1004);
    }

    private void set0x31AirCleaningState() {
        if (this.mAirClean) {
            int i = this.mCanBusInfoInt[2];
            if (i != 15) {
                switch (i) {
                    case 1:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_1);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 2:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_2);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 3:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_3);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 4:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_4);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 5:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_5);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 6:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_6);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 7:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_7);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 8:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_8);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 9:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_9);
                        sendDisplayMsgView(this.mContext);
                        break;
                    case 10:
                        GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_10);
                        sendDisplayMsgView(this.mContext);
                        break;
                }
            }
            GeneralDisplayMsgData.displayMsg = this.mContext.getResources().getString(R.string._2_air_display_11);
            sendDisplayMsgView(this.mContext);
        }
    }

    private void set0x22RearRadar(Context context) {
        if (is0x22DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationDataType2(60, iArr[2], 165, iArr[3], 165, iArr[4], 60, iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x23FrontRadar(Context context) {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int i4;
        if (is0x23DataChange()) {
            int intValue = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
            this.frontRadarStatus = intValue;
            if ((intValue == 1 && (i4 = this.mCanBusInfoInt[2]) <= 12 && i4 != 0) || (((i = (iArr = this.mCanBusInfoInt)[5]) <= 12 && i != 0) || (((i2 = iArr[3]) <= 24 && i2 != 0) || ((i3 = iArr[4]) <= 24 && i3 != 0)))) {
                forceReverse(this.mContext, true);
                this.frontRadarTag = true;
            } else {
                this.frontRadarTag = false;
                if (!this.rightRadarTag && !this.leftRadarTag && !this.rearRadarTag) {
                    forceReverse(this.mContext, false);
                }
            }
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationDataType2(60, iArr2[2], 120, iArr2[3], 120, iArr2[4], 60, iArr2[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x32LeftRadar(Context context) {
        if (is0x32DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setLeftRadarLocationDataType2(60, iArr[2], 120, iArr[3], 120, iArr[4], 60, iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x33RightRadar(Context context) {
        if (is0x33DataChange()) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRightRadarLocationDataType2(60, iArr[2], 165, iArr[3], 165, iArr[4], 60, iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x24DoorData(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0x25ParkingAssist(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2])));
        arrayList.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])));
        SharePreUtil.setBoolValue(context, _2_PARK_VOICE, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, context);
        GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        updatePKeyRadar();
    }

    private void set0x16SpeedInfo() {
        float f = ((mCanBusInfoInt[3] << 8) | mCanBusInfoInt[2]) / 16.0f;
        if ((this.mCanBusInfoInt[4] & 1) == 1) {
            this.mSpeedUnit = " MPH";
            updateSpeedInfo(Integer.parseInt(this.df_2Integer.format(f * 1.6d)));
        } else {
            this.mSpeedUnit = " KM/H";
            updateSpeedInfo(Integer.parseInt(this.df_2Integer.format(f)));
        }
        ArrayList<DriverUpdateEntity> arrayList = new ArrayList<>();
        arrayList.add(new DriverUpdateEntity(0, 0, new DecimalFormat("00").format(f) + this.mSpeedUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x50DriverData() {
        int[] iArr = this.mCanBusInfoInt;
        switch (iArr[2]) {
            case 16:
                int i = (iArr[5] << 8) | iArr[4];
                if ((iArr[3] & 1) == 1) {
                    this.mEndurance = " MI";
                } else {
                    this.mEndurance = " KM";
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(new DriverUpdateEntity(0, 1, i + this.mEndurance));
                updateGeneralDriveData(arrayList);
                updateDriveDataActivity(null);
                break;
            case 32:
                double d = (((iArr[4] + (iArr[5] << 8)) + (iArr[6] << 16)) + (iArr[7] << 24)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mDistance = " MI";
                } else {
                    this.mDistance = " KM";
                }
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(new DriverUpdateEntity(0, 2, new DecimalFormat("00").format(d) + this.mDistance));
                updateGeneralDriveData(arrayList2);
                updateDriveDataActivity(null);
                break;
            case 33:
                double d2 = (((iArr[4] + (iArr[5] << 8)) + (iArr[6] << 16)) + (iArr[7] << 24)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mDistance = " MI";
                } else {
                    this.mDistance = " KM";
                }
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(new DriverUpdateEntity(0, 3, new DecimalFormat("00").format(d2) + this.mDistance));
                updateGeneralDriveData(arrayList3);
                updateDriveDataActivity(null);
                break;
            case 34:
                double d3 = (((iArr[4] + (iArr[5] << 8)) + (iArr[6] << 16)) + (iArr[7] << 24)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mDistance = " MI";
                } else {
                    this.mDistance = " KM";
                }
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(new DriverUpdateEntity(0, 4, new DecimalFormat("00").format(d3) + this.mDistance));
                updateGeneralDriveData(arrayList4);
                updateDriveDataActivity(null);
                break;
            case 48:
                double d4 = (iArr[4] + (iArr[5] * 256)) * 0.1f;
                if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 0) {
                    this.mFuelUnit = " L/100KM";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
                    this.mFuelUnit = " KM/L";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
                    this.mFuelUnit = " mpg(UK)";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
                    this.mFuelUnit = " mpg(US)";
                }
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(new DriverUpdateEntity(0, 5, new DecimalFormat("00").format(d4) + this.mFuelUnit));
                updateGeneralDriveData(arrayList5);
                updateDriveDataActivity(null);
                break;
            case 49:
                double d5 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 0) {
                    this.mFuelUnit = " L/100KM";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
                    this.mFuelUnit = " KM/L";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
                    this.mFuelUnit = " mpg(UK)";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
                    this.mFuelUnit = " mpg(US)";
                }
                ArrayList arrayList6 = new ArrayList();
                arrayList6.add(new DriverUpdateEntity(0, 6, new DecimalFormat("00").format(d5) + this.mFuelUnit));
                updateGeneralDriveData(arrayList6);
                updateDriveDataActivity(null);
                break;
            case 50:
                double d6 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 0) {
                    this.mFuelUnit = " L/100KM";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
                    this.mFuelUnit = " KM/L";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
                    this.mFuelUnit = " mpg(UK)";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
                    this.mFuelUnit = " mpg(US)";
                }
                ArrayList arrayList7 = new ArrayList();
                arrayList7.add(new DriverUpdateEntity(0, 7, new DecimalFormat("00").format(d6) + this.mFuelUnit));
                updateGeneralDriveData(arrayList7);
                updateDriveDataActivity(null);
                break;
            case 64:
                double d7 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mSpeedUnit = " MPH";
                } else {
                    this.mSpeedUnit = " KM/H";
                }
                ArrayList arrayList8 = new ArrayList();
                arrayList8.add(new DriverUpdateEntity(0, 8, new DecimalFormat("00").format(d7) + this.mSpeedUnit));
                updateGeneralDriveData(arrayList8);
                updateDriveDataActivity(null);
                break;
            case 65:
                double d8 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mSpeedUnit = " MPH";
                } else {
                    this.mSpeedUnit = " KM/H";
                }
                ArrayList arrayList9 = new ArrayList();
                arrayList9.add(new DriverUpdateEntity(0, 9, new DecimalFormat("00").format(d8) + this.mSpeedUnit));
                updateGeneralDriveData(arrayList9);
                updateDriveDataActivity(null);
                break;
            case 66:
                double d9 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if ((iArr[3] & 1) == 1) {
                    this.mSpeedUnit = " MPH";
                } else {
                    this.mSpeedUnit = " KM/H";
                }
                ArrayList arrayList10 = new ArrayList();
                arrayList10.add(new DriverUpdateEntity(0, 10, new DecimalFormat("00").format(d9) + this.mSpeedUnit));
                updateGeneralDriveData(arrayList10);
                updateDriveDataActivity(null);
                break;
            case 80:
                double d10 = iArr[3] + (iArr[4] << 8) + (iArr[5] << 16);
                ArrayList arrayList11 = new ArrayList();
                arrayList11.add(new DriverUpdateEntity(0, 11, new DecimalFormat("00").format(d10) + " MIN"));
                updateGeneralDriveData(arrayList11);
                updateDriveDataActivity(null);
                break;
            case 81:
                double d11 = iArr[3] + (iArr[4] << 8) + (iArr[5] << 16);
                ArrayList arrayList12 = new ArrayList();
                arrayList12.add(new DriverUpdateEntity(0, 12, new DecimalFormat("00").format(d11) + " MIN"));
                updateGeneralDriveData(arrayList12);
                updateDriveDataActivity(null);
                break;
            case 82:
                double d12 = iArr[3] + (iArr[4] << 8) + (iArr[5] << 16);
                ArrayList arrayList13 = new ArrayList();
                arrayList13.add(new DriverUpdateEntity(0, 13, new DecimalFormat("00").format(d12) + " MIN"));
                updateGeneralDriveData(arrayList13);
                updateDriveDataActivity(null);
                break;
            case 96:
                double d13 = iArr[4] + (iArr[5] * 256);
                if ((iArr[3] & 1) == 1) {
                    this.mElectric = " I/H";
                } else {
                    this.mElectric = " GAI/H";
                }
                ArrayList arrayList14 = new ArrayList();
                arrayList14.add(new DriverUpdateEntity(0, 14, new DecimalFormat("00").format(d13) + this.mElectric));
                updateGeneralDriveData(arrayList14);
                updateDriveDataActivity(null);
                break;
            case 97:
                double d14 = (iArr[4] + (iArr[5] * 256)) / 10.0f;
                if (DataHandleUtils.getIntFromByteWithBit(iArr[3], 0, 2) == 0) {
                    this.mFuelUnit = " L/100KM";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 1) {
                    this.mFuelUnit = " KM/L";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 2) {
                    this.mFuelUnit = " mpg(UK)";
                } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) == 3) {
                    this.mFuelUnit = " mpg(US)";
                }
                ArrayList arrayList15 = new ArrayList();
                arrayList15.add(new DriverUpdateEntity(0, 15, new DecimalFormat("00").format(d14) + this.mFuelUnit));
                updateGeneralDriveData(arrayList15);
                updateDriveDataActivity(null);
                break;
        }
    }

    private void set0x27OutDoorTemp(Context context) {
        updateOutDoorTemp(context, resolveOutDoorTemp());
    }

    private void set0x29TrackData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
        updateParkUi(null, context);
    }

    private void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x40CarState() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        switch (iArr[2]) {
            case 0:
                int i2 = iArr[3];
                if (i2 <= 11) {
                    i = i2;
                } else if (i2 == 13) {
                    i = 12;
                } else if (i2 == 22) {
                    i = 16;
                } else if (i2 == 23) {
                    i = 17;
                } else if (i2 == 29) {
                    i = 18;
                } else if (i2 != 30) {
                    switch (i2) {
                        case 16:
                            i = 13;
                            break;
                        case 17:
                            i = 14;
                            break;
                        case 18:
                            i = 15;
                            break;
                    }
                } else {
                    i = 19;
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(getSettingUpdateEntity("_2_set_language").setValue(Integer.valueOf(i)));
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
                break;
            case 16:
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(getSettingUpdateEntity("_2_setting_0_0").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
                updateGeneralSettingData(arrayList2);
                updateSettingActivity(null);
                break;
            case 32:
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(getSettingUpdateEntity("_2_setting_1_0").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1) == 1) {
                    arrayList3.add(getSettingUpdateEntity("_2_setting_1_3").setValue("KM/H").setValueStr(true));
                } else {
                    arrayList3.add(getSettingUpdateEntity("_2_setting_1_3").setValue("MPH").setValueStr(true));
                }
                arrayList3.add(getSettingUpdateEntity("_2_setting_1_1").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
                arrayList3.add(getSettingUpdateEntity("_2_setting_1_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
                updateGeneralSettingData(arrayList3);
                updateSettingActivity(null);
                break;
            case 48:
                ArrayList arrayList4 = new ArrayList();
                arrayList4.add(getSettingUpdateEntity("_2_setting_2_0").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))).setEnable((this.mCanBusInfoInt[4] & 1) == 1));
                arrayList4.add(getSettingUpdateEntity("_2_setting_2_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList4.add(getSettingUpdateEntity("_2_setting_2_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
                arrayList4.add(getSettingUpdateEntity("_2_setting_2_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
                updateGeneralSettingData(arrayList4);
                updateSettingActivity(null);
                break;
            case 49:
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))).setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))).setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
                arrayList5.add(getSettingUpdateEntity("_2_setting_3_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setEnable((this.mCanBusInfoInt[3] & 1) == 0));
                updateGeneralSettingData(arrayList5);
                updateSettingActivity(null);
                break;
            case 64:
                ArrayList arrayList6 = new ArrayList();
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + 1));
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + 1));
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4) + 1));
                arrayList6.add(getSettingUpdateEntity("_2_setting_4_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4) + 1));
                updateGeneralSettingData(arrayList6);
                updateSettingActivity(null);
                break;
            case 80:
                ArrayList arrayList7 = new ArrayList();
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_5").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4] / 10));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_6").setValue(Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5] / 5));
                arrayList7.add(getSettingUpdateEntity("_2_setting_5_7").setValue(Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6] / 5));
                updateGeneralSettingData(arrayList7);
                updateSettingActivity(null);
                break;
            case 81:
                ArrayList arrayList8 = new ArrayList();
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_0").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_1").setValue(Integer.valueOf(this.mCanBusInfoInt[4])));
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_2").setValue(Integer.valueOf(this.mCanBusInfoInt[5])));
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 3))));
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
                arrayList8.add(getSettingUpdateEntity("_2_setting_6_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
                updateGeneralSettingData(arrayList8);
                updateSettingActivity(null);
                break;
            case 82:
                ArrayList arrayList9 = new ArrayList();
                arrayList9.add(getSettingUpdateEntity("_2_setting_7_1").setValue(Integer.valueOf(this.mCanBusInfoInt[3])));
                arrayList9.add(getSettingUpdateEntity("_2_setting_7_2").setValue(Integer.valueOf(this.mCanBusInfoInt[4])));
                arrayList9.add(getSettingUpdateEntity("_2_setting_7_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4))));
                arrayList9.add(getSettingUpdateEntity("_2_setting_7_4").setValue(Integer.valueOf(this.mCanBusInfoInt[6])));
                updateGeneralSettingData(arrayList9);
                updateSettingActivity(null);
                break;
            case 96:
                ArrayList arrayList10 = new ArrayList();
                arrayList10.add(getSettingUpdateEntity("_2_setting_8_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList10.add(getSettingUpdateEntity("_2_setting_8_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList10.add(getSettingUpdateEntity("_2_setting_8_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
                arrayList10.add(getSettingUpdateEntity("_2_setting_8_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
                arrayList10.add(getSettingUpdateEntity("_2_setting_8_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
                updateGeneralSettingData(arrayList10);
                updateSettingActivity(null);
                break;
            case 112:
                ArrayList arrayList11 = new ArrayList();
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1))));
                arrayList11.add(getSettingUpdateEntity("_2_setting_9_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
                updateGeneralSettingData(arrayList11);
                updateSettingActivity(null);
                break;
            case 128:
                ArrayList arrayList12 = new ArrayList();
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_7").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_8").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_9").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1))));
                arrayList12.add(getSettingUpdateEntity("_2_setting_10_10").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
                updateGeneralSettingData(arrayList12);
                updateSettingActivity(null);
                break;
            case 144:
                ArrayList arrayList13 = new ArrayList();
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 4))));
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))));
                arrayList13.add(getSettingUpdateEntity("_2_setting_11_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))));
                SharePreUtil.setIntValue(this.mContext, "_2_tire_unit", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4));
                updateGeneralSettingData(arrayList13);
                updateSettingActivity(null);
                break;
            case 160:
                ArrayList arrayList14 = new ArrayList();
                if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1) == 1) {
                    arrayList14.add(getSettingUpdateEntity("_2_setting_12_2").setValue(this.mContext.getString(R.string._2_setting_12_2_2)).setValueStr(true));
                } else {
                    arrayList14.add(getSettingUpdateEntity("_2_setting_12_2").setValue(this.mContext.getString(R.string._2_setting_12_2_1)).setValueStr(true));
                }
                arrayList14.add(getSettingUpdateEntity("_2_setting_12_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))));
                arrayList14.add(getSettingUpdateEntity("_2_setting_12_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3));
                arrayList14.add(getSettingUpdateEntity("_2_setting_12_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4))).setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3));
                arrayList14.add(getSettingUpdateEntity("_2_setting_12_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 4))).setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3));
                int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
                if (intFromByteWithBit == 2) {
                    intFromByteWithBit = 1;
                }
                arrayList14.add(getSettingUpdateEntity("_2_setting_12_7").setValue(Integer.valueOf(intFromByteWithBit)).setEnable(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4) == 3));
                updateGeneralSettingData(arrayList14);
                updateSettingActivity(null);
                SharePreUtil.setIntValue(this.mContext, "_2_off_individual", DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4));
                break;
            case MpegConstantsDef.MPEG_INFO_DISCTYPE_CFM /* 161 */:
                ArrayList arrayList15 = new ArrayList();
                int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
                if (intFromByteWithBit2 == 3) {
                    intFromByteWithBit2 = 1;
                }
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_1").setValue(Integer.valueOf(intFromByteWithBit2)).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4))).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4))).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
                if (intFromByteWithBit3 == 2) {
                    intFromByteWithBit3 = 1;
                }
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_4").setValue(Integer.valueOf(intFromByteWithBit3)).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_7").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4);
                if (intFromByteWithBit4 == 3) {
                    intFromByteWithBit4 = 1;
                }
                arrayList15.add(getSettingUpdateEntity("_2_setting_13_8").setValue(Integer.valueOf(intFromByteWithBit4)).setEnable(SharePreUtil.getIntValue(this.mContext, "_2_off_individual", 0) == 6));
                updateGeneralSettingData(arrayList15);
                updateSettingActivity(null);
                break;
            case SyslogAppender.LOG_LOCAL6 /* 176 */:
                ArrayList arrayList16 = new ArrayList();
                arrayList16.add(getSettingUpdateEntity("_2_setting_14_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                updateGeneralSettingData(arrayList16);
                updateSettingActivity(null);
                break;
            case 192:
                ArrayList arrayList17 = new ArrayList();
                arrayList17.add(getSettingUpdateEntity("_2_setting_15_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))));
                arrayList17.add(getSettingUpdateEntity("_2_setting_15_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
                arrayList17.add(getSettingUpdateEntity("_2_setting_15_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
                arrayList17.add(getSettingUpdateEntity("_2_setting_15_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
                updateGeneralSettingData(arrayList17);
                updateSettingActivity(null);
                break;
            case HotKeyConstant.K_SOS /* 208 */:
                ArrayList arrayList18 = new ArrayList();
                arrayList18.add(getSettingUpdateEntity("_2_setting_16_1").setValue(Integer.valueOf(this.mCanBusInfoInt[3])));
                arrayList18.add(getSettingUpdateEntity("_2_setting_16_2").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4] - 30));
                arrayList18.add(getSettingUpdateEntity("_2_setting_16_3").setValue(Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5] - 30));
                arrayList18.add(getSettingUpdateEntity("_2_setting_16_4").setValue(Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6] - 30));
                updateGeneralSettingData(arrayList18);
                updateSettingActivity(null);
                break;
        }
    }

    private void set0x41CarEnable() {
        int i = this.mCanBusInfoInt[2];
        if (i == 16) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("_2_setting_0_0").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            return;
        }
        if (i == 32) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(getSettingUpdateEntity("_2_setting_1_0").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            updateGeneralSettingData(arrayList2);
            updateSettingActivity(null);
            return;
        }
        if (i == 64) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_2").setEnable(((this.mCanBusInfoInt[3] >> 6) & 1) == 1));
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_3").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_4").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_5").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
            arrayList3.add(getSettingUpdateEntity("_2_setting_4_6").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
            updateGeneralSettingData(arrayList3);
            updateSettingActivity(null);
            return;
        }
        if (i == 96) {
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(getSettingUpdateEntity("_2_setting_8_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList4.add(getSettingUpdateEntity("_2_setting_8_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList4.add(getSettingUpdateEntity("_2_setting_8_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            arrayList4.add(getSettingUpdateEntity("_2_setting_8_4").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
            arrayList4.add(getSettingUpdateEntity("_2_setting_8_5").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
            updateGeneralSettingData(arrayList4);
            updateSettingActivity(null);
            return;
        }
        if (i == 112) {
            ArrayList arrayList5 = new ArrayList();
            arrayList5.add(getSettingUpdateEntity("_2_setting_9_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList5.add(getSettingUpdateEntity("_2_setting_9_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList5.add(getSettingUpdateEntity("_2_setting_9_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            updateGeneralSettingData(arrayList5);
            updateSettingActivity(null);
            return;
        }
        if (i == 128) {
            ArrayList arrayList6 = new ArrayList();
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_4").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_5").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_6").setEnable(((this.mCanBusInfoInt[3] >> 5) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_7").setEnable(((this.mCanBusInfoInt[3] >> 6) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_8").setEnable(((this.mCanBusInfoInt[3] >> 7) & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_9").setEnable((this.mCanBusInfoInt[4] & 1) == 1));
            arrayList6.add(getSettingUpdateEntity("_2_setting_10_10").setEnable(((this.mCanBusInfoInt[4] >> 1) & 1) == 1));
            updateGeneralSettingData(arrayList6);
            updateSettingActivity(null);
            return;
        }
        if (i == 144) {
            ArrayList arrayList7 = new ArrayList();
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_4").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_5").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
            arrayList7.add(getSettingUpdateEntity("_2_setting_11_6").setEnable(((this.mCanBusInfoInt[3] >> 5) & 1) == 1));
            updateGeneralSettingData(arrayList7);
            updateSettingActivity(null);
            return;
        }
        if (i == 176) {
            ArrayList arrayList8 = new ArrayList();
            arrayList8.add(getSettingUpdateEntity("_2_setting_14_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            updateGeneralSettingData(arrayList8);
            updateSettingActivity(null);
            return;
        }
        if (i == 48) {
            ArrayList arrayList9 = new ArrayList();
            arrayList9.add(getSettingUpdateEntity("_2_setting_2_0").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList9.add(getSettingUpdateEntity("_2_setting_2_1").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList9.add(getSettingUpdateEntity("_2_setting_2_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            updateGeneralSettingData(arrayList9);
            updateSettingActivity(null);
            return;
        }
        if (i == 49) {
            ArrayList arrayList10 = new ArrayList();
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_4").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_5").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
            arrayList10.add(getSettingUpdateEntity("_2_setting_3_6").setEnable(((this.mCanBusInfoInt[3] >> 5) & 1) == 1));
            updateGeneralSettingData(arrayList10);
            updateSettingActivity(null);
            return;
        }
        if (i != 80) {
            if (i != 81) {
                return;
            }
            ArrayList arrayList11 = new ArrayList();
            arrayList11.add(getSettingUpdateEntity("_2_setting_6_0").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
            arrayList11.add(getSettingUpdateEntity("_2_setting_6_1").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
            arrayList11.add(getSettingUpdateEntity("_2_setting_6_2").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
            updateGeneralSettingData(arrayList11);
            updateSettingActivity(null);
            return;
        }
        ArrayList arrayList12 = new ArrayList();
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_1").setEnable((this.mCanBusInfoInt[3] & 1) == 1));
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_2").setEnable(((this.mCanBusInfoInt[3] >> 1) & 1) == 1));
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_3").setEnable(((this.mCanBusInfoInt[3] >> 2) & 1) == 1));
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_5").setEnable(((this.mCanBusInfoInt[3] >> 3) & 1) == 1));
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_6").setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
        arrayList12.add(getSettingUpdateEntity("_2_setting_5_7").setEnable(((this.mCanBusInfoInt[3] >> 5) & 1) == 1));
        updateGeneralSettingData(arrayList12);
        updateSettingActivity(null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0056, code lost:
    
        if (r10.m0x60Msgs.size() == 0) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
    
        r10.m0x60Msgs.add(0, new com.hzbhd.canbus.entity.WarningEntity(com.hzbhd.canbus.util.CommUtil.getStrByResId(r11, "_2_information_title_1")));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x60Data(android.content.Context r11) {
        /*
            r10 = this;
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            r0.clear()
            int[] r0 = r10.mCanBusInfoInt
            r1 = 2
            r2 = r0[r1]
            java.lang.String r3 = "\t"
            r4 = 0
            if (r2 <= 0) goto L69
            r0 = r4
        L10:
            int[] r2 = r10.mCanBusInfoInt
            r5 = r2[r1]
            if (r0 >= r5) goto L50
            r5 = 6
            if (r0 >= r5) goto L50
            int r5 = r0 + 3
            int r6 = r2.length
            if (r5 >= r6) goto L4f
            r2 = r2[r5]
            r6 = 34
            if (r2 < r6) goto L25
            goto L4f
        L25:
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r2 = r10.m0x60Msgs
            com.hzbhd.canbus.entity.WarningEntity r6 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            java.lang.StringBuilder r7 = r7.append(r3)
            java.lang.String[] r8 = r10.mVehicleTips
            int[] r9 = r10.mCanBusInfoInt
            r5 = r9[r5]
            r5 = r8[r5]
            java.lang.String r5 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r11, r5)
            java.lang.StringBuilder r5 = r7.append(r5)
            java.lang.String r5 = r5.toString()
            r6.<init>(r5)
            r2.add(r6)
            int r0 = r0 + 1
            goto L10
        L4f:
            return
        L50:
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            int r0 = r0.size()
            if (r0 == 0) goto Laf
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            com.hzbhd.canbus.entity.WarningEntity r1 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.String r2 = "_2_information_title_1"
            java.lang.String r11 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r11, r2)
            r1.<init>(r11)
            r0.add(r4, r1)
            goto Laf
        L69:
            r1 = 9
            r0 = r0[r1]
            if (r0 <= r1) goto L70
            return
        L70:
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            com.hzbhd.canbus.entity.WarningEntity r2 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String[] r5 = r10.mStartStopMsgs
            int[] r6 = r10.mCanBusInfoInt
            r1 = r6[r1]
            r1 = r5[r1]
            java.lang.String r1 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r11, r1)
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            r0.add(r2)
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            int r0 = r0.size()
            if (r0 == 0) goto Laf
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r10.m0x60Msgs
            com.hzbhd.canbus.entity.WarningEntity r1 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.String r2 = "_2_information_title_2"
            java.lang.String r11 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r11, r2)
            r1.<init>(r11)
            r0.add(r4, r1)
        Laf:
            r10.updateWarningActivity()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._2.MsgMgr.set0x60Data(android.content.Context):void");
    }

    private void set0x61Data(Context context) {
        int i;
        this.m0x61Msgs.clear();
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[2] | (iArr[3] << 8);
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 * 2;
            int i5 = i4 + 5;
            int[] iArr2 = this.mCanBusInfoInt;
            if (i5 >= iArr2.length || (i = i4 + 4) >= iArr2.length) {
                return;
            }
            int i6 = iArr2[i] | iArr2[i5];
            if (i6 < 155) {
                this.m0x61Msgs.add(new WarningEntity("\t" + CommUtil.getStrByResId(context, this.mVehicleReports[i6])));
            }
        }
        if (this.m0x61Msgs.size() != 0) {
            this.m0x61Msgs.add(0, new WarningEntity(CommUtil.getStrByResId(context, "_2_information_title_3")));
        }
        updateWarningActivity();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0050, code lost:
    
        if (r8.m0x62Msgs.size() == 0) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0052, code lost:
    
        r8.m0x62Msgs.add(0, new com.hzbhd.canbus.entity.WarningEntity(com.hzbhd.canbus.util.CommUtil.getStrByResId(r9, "_2_information_title_4")));
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0062, code lost:
    
        updateWarningActivity();
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x62Data(android.content.Context r9) {
        /*
            r8 = this;
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r0 = r8.m0x62Msgs
            r0.clear()
            r0 = 0
            r1 = r0
        L7:
            int[] r2 = r8.mCanBusInfoInt
            r3 = 2
            r3 = r2[r3]
            if (r1 >= r3) goto L4a
            r3 = 3
            if (r1 >= r3) goto L4a
            int r3 = r1 + 3
            int r4 = r2.length
            if (r3 >= r4) goto L49
            r2 = r2[r3]
            r4 = 18
            if (r2 < r4) goto L1d
            goto L49
        L1d:
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r2 = r8.m0x62Msgs
            com.hzbhd.canbus.entity.WarningEntity r4 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "\t"
            java.lang.StringBuilder r5 = r5.append(r6)
            java.lang.String[] r6 = r8.mConvTips
            int[] r7 = r8.mCanBusInfoInt
            r3 = r7[r3]
            r3 = r6[r3]
            java.lang.String r3 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r9, r3)
            java.lang.StringBuilder r3 = r5.append(r3)
            java.lang.String r3 = r3.toString()
            r4.<init>(r3)
            r2.add(r4)
            int r1 = r1 + 1
            goto L7
        L49:
            return
        L4a:
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r1 = r8.m0x62Msgs
            int r1 = r1.size()
            if (r1 == 0) goto L62
            java.util.List<com.hzbhd.canbus.entity.WarningEntity> r1 = r8.m0x62Msgs
            com.hzbhd.canbus.entity.WarningEntity r2 = new com.hzbhd.canbus.entity.WarningEntity
            java.lang.String r3 = "_2_information_title_4"
            java.lang.String r9 = com.hzbhd.canbus.util.CommUtil.getStrByResId(r9, r3)
            r2.<init>(r9)
            r1.add(r0, r2)
        L62:
            r8.updateWarningActivity()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._2.MsgMgr.set0x62Data(android.content.Context):void");
    }

    private void updateWarningActivity() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.m0x60Msgs);
        arrayList.addAll(this.m0x61Msgs);
        arrayList.addAll(this.m0x62Msgs);
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private void set0x66TireInfo() {
        if (is0x66DataChange()) {
            int intValue = SharePreUtil.getIntValue(this.mContext, "_2_tire_unit", 0);
            if (intValue >= this.mTireResolveList.size()) {
                return;
            }
            TireResolve tireResolve = this.mTireResolveList.get(intValue);
            for (int i = 0; i < this.mTireUpdateEntityList.size(); i++) {
                ArrayList arrayList = new ArrayList();
                String str = this.mDecimalFormat.format(this.mCanBusInfoInt[i + 3] * tireResolve.calculate) + tireResolve.unit;
                if (this.mCanBusInfoInt[2] == 0) {
                    this.mTireInfoArray[i] = str;
                } else {
                    this.mTireInfoArray2[i] = str;
                }
                arrayList.add(this.mTireInfoArray[i]);
                arrayList.add(this.mTireInfoArray2[i]);
                arrayList.add(this.mTireWarningArray[i]);
                this.mTireUpdateEntityList.get(i).setList(arrayList);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x65TireWarning() {
        int i;
        if (is0x65DataChange()) {
            for (int i2 = 0; i2 < this.mTireUpdateEntityList.size(); i2++) {
                int i3 = (this.mCanBusInfoInt[2] & (1 << i2)) == 0 ? 0 : 1;
                ArrayList arrayList = new ArrayList();
                arrayList.add(this.mTireInfoArray[i2]);
                arrayList.add(this.mTireInfoArray2[i2]);
                String[] strArr = this.mTireWarningArray;
                strArr[i2] = "";
                if (i3 != 0 && (i = this.mCanBusInfoInt[3]) >= 2 && i <= 4) {
                    strArr[i2] = CommUtil.getStrByResId(this.mContext, "_2_tire_warning_" + this.mCanBusInfoInt[3]);
                }
                arrayList.add(this.mTireWarningArray[i2]);
                this.mTireUpdateEntityList.get(i2).setList(arrayList);
                this.mTireUpdateEntityList.get(i2).setTireStatus(i3);
            }
            GeneralTireData.dataList = this.mTireUpdateEntityList;
            updateTirePressureActivity(null);
        }
    }

    private void set0x95ERAInfo() {
        ArrayList arrayList = new ArrayList();
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 8) == 0) {
            arrayList.add(getSettingUpdateEntity("_2_ERA_").setValue("OFF"));
        } else {
            arrayList.add(getSettingUpdateEntity("_2_ERA_").setValue("ON"));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int bandDate = getBandDate(str);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, (byte) bandDate, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i6 == 1 || i6 == 5) {
            i3 = i4;
        }
        int[] time = getTime(i);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, 0, (byte) i3, (byte) i5, (byte) time[0], (byte) time[1], (byte) time[2]});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.name(), new byte[]{22, -64, 3, 34, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.name(), new byte[]{22, -64, 10, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 48, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, (byte) (b == 9 ? 9 : 8), 18, (byte) i, b7, (byte) (i2 & 255), (byte) (i2 >> 8), 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 18, 0, 0, 0, 0, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, (byte) (b == 9 ? 9 : 8), 18, (byte) i, b6, (byte) (i2 & 255), (byte) (i2 >> 8), 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) (DataHandleUtils.rangeNumber(i, 0, 30) | (z ? 128 : 0))});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -90, (byte) i2, (byte) i3, (byte) i4, (byte) (i5 | (z ? 0 : 128)), (byte) i6, (byte) i7, (byte) (i9 - 1)});
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
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

    private int getBandDate(String str) {
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
            return 16;
        }
        return "AM2".equals(str) ? 18 : 0;
    }

    private TireUpdateEntity getTireEntity(int i, String str) {
        String[] strArr;
        int i2 = 0;
        if (TextUtils.isEmpty(str)) {
            strArr = new String[]{"tire_pressure_content", ""};
        } else {
            strArr = new String[]{"tire_pressure_content", str};
            i2 = 1;
        }
        return new TireUpdateEntity(i, i2, strArr);
    }

    private void initTireResolveTool() {
        this.mTireUpdateEntityList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            this.mTireUpdateEntityList.add(new TireUpdateEntity(i, 0, new String[0]));
        }
        ArrayList arrayList = new ArrayList();
        this.mTireResolveList = arrayList;
        arrayList.add(new TireResolve("bar", 0.1f));
        this.mTireResolveList.add(new TireResolve("psi", 0.5f));
        this.mTireResolveList.add(new TireResolve("kPa", 10.0f));
        this.mTireInfoArray = new String[4];
        this.mTireInfoArray2 = new String[4];
        this.mTireWarningArray = new String[4];
    }

    private class TireResolve {
        float calculate;
        String unit;

        public TireResolve(String str, float f) {
            this.unit = str;
            this.calculate = f;
        }
    }

    private boolean is0x65DataChange() {
        if (Arrays.equals(this.m0x65Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x65Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x66DataChange() {
        if (Arrays.equals(this.m0x66Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x66Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String resolveOutDoorTemp() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[4];
        float f = ((i << 8) | iArr[3]) * 0.1f;
        if ((iArr[2] & 1) == 1) {
            if (DataHandleUtils.getBoolBit7(i)) {
                return "- " + new DecimalFormat("0").format(6554.0f - f) + getTempUnitF(this.mContext);
            }
            return new DecimalFormat("0").format(f) + getTempUnitF(this.mContext);
        }
        if (DataHandleUtils.getBoolBit7(i)) {
            return "- " + new DecimalFormat("0.0").format(6553.6d - f) + getTempUnitC(this.mContext);
        }
        return new DecimalFormat("0.0").format(f) + getTempUnitC(this.mContext);
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

    private boolean isOnlyRearUpdate() {
        return (SharePreUtil.getIntValue(this.mContext, "_2_air_temp", 0) == this.isRearTempChange && SharePreUtil.getIntValue(this.mContext, "_2_air_state", 0) == this.isRearStateChange && SharePreUtil.getIntValue(this.mContext, "_2_air_info", 0) == this.isRearInfoChange) ? false : true;
    }

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 28) {
            return "";
        }
        float f = i;
        if ((this.mCanBusInfoInt[6] & 1) == 1) {
            return (f + 59.0f) + this.mAirUnit;
        }
        return ((f + 31.0f) * 0.5f) + this.mAirUnit;
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanBusInfoInt[3]);
    }

    private void initSettingsItem(Context context) {
        Log.i("_2_MsgMgr", "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        SettingPageUiSet settingUiSet = UiMgrFactory.getCanUiMgr(context).getSettingUiSet(context);
        for (int i = 0; i < settingUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        SettingUpdateEntity settingUpdateEntity = new SettingUpdateEntity(-1, -1, null);
        this.mSettingItemIndeHashMap.put(str, settingUpdateEntity);
        return settingUpdateEntity;
    }

    private void initMessages(Context context) {
        String[] strArr = this.mVehicleTips;
        strArr[0] = "_2_hinit_0x60_1_00";
        strArr[1] = "_2_hinit_0x60_1_01";
        strArr[2] = "_2_hinit_0x60_1_02";
        strArr[3] = "_2_hinit_0x60_1_03";
        strArr[4] = "_2_hinit_0x60_1_04";
        strArr[5] = "_2_hinit_0x60_1_05";
        strArr[6] = "_2_hinit_0x60_1_06";
        strArr[7] = "_2_hinit_0x60_1_07";
        strArr[8] = "_2_hinit_0x60_1_08";
        strArr[9] = "_2_hinit_0x60_1_09";
        strArr[10] = "_2_hinit_0x60_1_0A";
        strArr[11] = "_2_hinit_0x60_1_0B";
        strArr[12] = "_2_hinit_0x60_1_0C";
        strArr[13] = "_2_hinit_0x60_1_0D";
        strArr[14] = "_2_hinit_0x60_1_0E";
        strArr[15] = "_2_hinit_0x60_1_0F";
        strArr[16] = "_2_hinit_0x60_1_10";
        strArr[17] = "_2_hinit_0x60_1_11";
        strArr[18] = "_2_hinit_0x60_1_12";
        strArr[19] = "_2_hinit_0x60_1_13";
        strArr[20] = "_2_hinit_0x60_1_14";
        strArr[21] = "_2_hinit_0x60_1_15";
        strArr[22] = "_2_hinit_0x60_1_16";
        strArr[23] = "_2_hinit_0x60_1_17";
        strArr[24] = "_2_hinit_0x60_1_18";
        strArr[25] = "_2_hinit_0x60_1_19";
        strArr[26] = "_2_hinit_0x60_1_1A";
        strArr[27] = "_2_hinit_0x60_1_1B";
        strArr[28] = "_2_hinit_0x60_1_1C";
        strArr[29] = "_2_hinit_0x60_1_1D";
        strArr[30] = "_2_hinit_0x60_1_1E";
        strArr[31] = "_2_hinit_0x60_1_1F";
        strArr[32] = "_2_hinit_0x60_1_20";
        strArr[33] = "_2_hinit_0x60_1_21";
        String[] strArr2 = this.mStartStopMsgs;
        strArr2[0] = "_2_hinit_0x60_7_00";
        strArr2[1] = "_2_hinit_0x60_7_01";
        strArr2[2] = "_2_hinit_0x60_7_02";
        strArr2[3] = "_2_hinit_0x60_7_03";
        strArr2[4] = "_2_hinit_0x60_7_04";
        strArr2[5] = "_2_hinit_0x60_7_05";
        strArr2[6] = "_2_hinit_0x60_7_06";
        strArr2[7] = "_2_hinit_0x60_7_07";
        strArr2[8] = "_2_hinit_0x60_7_08";
        String[] strArr3 = this.mVehicleReports;
        strArr3[0] = "_2_car_report_0";
        strArr3[1] = "_2_car_report_1";
        strArr3[2] = "_2_car_report_2";
        strArr3[3] = "_2_car_report_3";
        strArr3[4] = "_2_car_report_4";
        strArr3[5] = "_2_car_report_5";
        strArr3[6] = "_2_car_report_6";
        strArr3[7] = "_2_car_report_7";
        strArr3[8] = "_2_car_report_8";
        strArr3[9] = "_2_car_report_9";
        strArr3[10] = "_2_car_report_10";
        strArr3[11] = "_2_car_report_11";
        strArr3[12] = "_2_car_report_12";
        strArr3[13] = "_2_car_report_13";
        strArr3[14] = "_2_car_report_14";
        strArr3[15] = "_2_car_report_15";
        strArr3[16] = "_2_car_report_16";
        strArr3[17] = "_2_car_report_17";
        strArr3[18] = "_2_car_report_18";
        strArr3[19] = "_2_car_report_19";
        strArr3[20] = "_2_car_report_20";
        strArr3[21] = "_2_car_report_21";
        strArr3[22] = "_2_car_report_22";
        strArr3[23] = "_2_car_report_23";
        strArr3[24] = "_2_car_report_24";
        strArr3[25] = "_2_car_report_25";
        strArr3[26] = "_2_car_report_26";
        strArr3[27] = "_2_car_report_27";
        strArr3[28] = "_2_car_report_28";
        strArr3[29] = "_2_car_report_29";
        strArr3[30] = "_2_car_report_30";
        strArr3[31] = "_2_car_report_31";
        strArr3[32] = "_2_car_report_32";
        strArr3[33] = "_2_car_report_33";
        strArr3[34] = "_2_car_report_34";
        strArr3[35] = "_2_car_report_35";
        strArr3[36] = "_2_car_report_36";
        strArr3[37] = "_2_car_report_37";
        strArr3[38] = "_2_car_report_38";
        strArr3[39] = "_2_car_report_39";
        strArr3[40] = "_2_car_report_40";
        strArr3[41] = "_2_car_report_41";
        strArr3[42] = "_2_car_report_42";
        strArr3[43] = "_2_car_report_43";
        strArr3[44] = "_2_car_report_44";
        strArr3[45] = "_2_car_report_45";
        strArr3[46] = "_2_car_report_46";
        strArr3[47] = "_2_car_report_47";
        strArr3[48] = "_2_car_report_48";
        strArr3[49] = "_2_car_report_49";
        strArr3[50] = "_2_car_report_50";
        strArr3[51] = "_2_car_report_51";
        strArr3[52] = "_2_car_report_52";
        strArr3[53] = "_2_car_report_53";
        strArr3[54] = "_2_car_report_54";
        strArr3[55] = "_2_car_report_55";
        strArr3[56] = "_2_car_report_56";
        strArr3[57] = "_2_car_report_57";
        strArr3[58] = "_2_car_report_58";
        strArr3[59] = "_2_car_report_59";
        strArr3[60] = "_2_car_report_60";
        strArr3[61] = "_2_car_report_61";
        strArr3[62] = "_2_car_report_62";
        strArr3[63] = "_2_car_report_63";
        strArr3[64] = "_2_car_report_64";
        strArr3[65] = "_2_car_report_65";
        strArr3[66] = "_2_car_report_66";
        strArr3[67] = "_2_car_report_67";
        strArr3[68] = "_2_car_report_68";
        strArr3[69] = "_2_car_report_69";
        strArr3[70] = "_2_car_report_70";
        strArr3[71] = "_2_car_report_71";
        strArr3[72] = "_2_car_report_72";
        strArr3[73] = "_2_car_report_73";
        strArr3[74] = "_2_car_report_74";
        strArr3[75] = "_2_car_report_75";
        strArr3[76] = "_2_car_report_76";
        strArr3[77] = "_2_car_report_77";
        strArr3[78] = "_2_car_report_78";
        strArr3[79] = "_2_car_report_79";
        strArr3[80] = "_2_car_report_80";
        strArr3[81] = "_2_car_report_81";
        strArr3[82] = "_2_car_report_82";
        strArr3[83] = "_2_car_report_83";
        strArr3[84] = "_2_car_report_84";
        strArr3[85] = "_2_car_report_85";
        strArr3[86] = "_2_car_report_86";
        strArr3[87] = "_2_car_report_87";
        strArr3[88] = "_2_car_report_88";
        strArr3[89] = "_2_car_report_89";
        strArr3[90] = "_2_car_report_90";
        strArr3[91] = "_2_car_report_91";
        strArr3[92] = "_2_car_report_92";
        strArr3[93] = "_2_car_report_93";
        strArr3[94] = "_2_car_report_94";
        strArr3[95] = "_2_car_report_95";
        strArr3[96] = "_2_car_report_96";
        strArr3[97] = "_2_car_report_97";
        strArr3[98] = "_2_car_report_98";
        strArr3[99] = "_2_car_report_99";
        strArr3[100] = "_2_car_report_100";
        strArr3[101] = "_2_car_report_101";
        strArr3[102] = "_2_car_report_102";
        strArr3[103] = "_2_car_report_103";
        strArr3[104] = "_2_car_report_104";
        strArr3[105] = "_2_car_report_105";
        strArr3[106] = "_2_car_report_106";
        strArr3[107] = "_2_car_report_107";
        strArr3[108] = "_2_car_report_108";
        strArr3[109] = "_2_car_report_109";
        strArr3[110] = "_2_car_report_110";
        strArr3[111] = "_2_car_report_111";
        strArr3[112] = "_2_car_report_112";
        strArr3[113] = "_2_car_report_113";
        strArr3[114] = "_2_car_report_114";
        strArr3[115] = "_2_car_report_115";
        strArr3[116] = "_2_car_report_116";
        strArr3[117] = "_2_car_report_117";
        strArr3[118] = "_2_car_report_118";
        strArr3[119] = "_2_car_report_119";
        strArr3[120] = "_2_car_report_120";
        strArr3[121] = "_2_car_report_121";
        strArr3[122] = "_2_car_report_122";
        strArr3[123] = "_2_car_report_123";
        strArr3[124] = "_2_car_report_124";
        strArr3[125] = "_2_car_report_125";
        strArr3[126] = "_2_car_report_126";
        strArr3[127] = "_2_car_report_127";
        strArr3[128] = "_2_car_report_128";
        strArr3[129] = "_2_car_report_129";
        strArr3[130] = "_2_car_report_130";
        strArr3[131] = "_2_car_report_131";
        strArr3[132] = "_2_car_report_132";
        strArr3[133] = "_2_car_report_133";
        strArr3[134] = "_2_car_report_134";
        strArr3[135] = "_2_car_report_135";
        strArr3[136] = "_2_car_report_136";
        strArr3[137] = "_2_car_report_137";
        strArr3[138] = "_2_car_report_138";
        strArr3[139] = "_2_car_report_139";
        strArr3[140] = "_2_car_report_140";
        strArr3[141] = "_2_car_report_141";
        strArr3[142] = "_2_car_report_142";
        strArr3[143] = "_2_car_report_143";
        strArr3[144] = "_2_car_report_144";
        strArr3[145] = "_2_car_report_145";
        strArr3[146] = "_2_car_report_146";
        strArr3[147] = "_2_car_report_147";
        strArr3[148] = "_2_car_report_148";
        strArr3[149] = "_2_car_report_149";
        strArr3[150] = "_2_car_report_150";
        strArr3[151] = "_2_car_report_151";
        strArr3[152] = "_2_car_report_152";
        strArr3[153] = "_2_car_report_153";
        strArr3[154] = "_2_car_report_154";
        String[] strArr4 = this.mConvTips;
        strArr4[0] = "_2_Conv_trips_01";
        strArr4[1] = "_2_Conv_trips_02";
        strArr4[2] = "_2_Conv_trips_03";
        strArr4[3] = "_2_Conv_trips_04";
        strArr4[4] = "_2_Conv_trips_05";
        strArr4[5] = "_2_Conv_trips_06";
        strArr4[6] = "_2_Conv_trips_07";
        strArr4[7] = "_2_Conv_trips_08";
        strArr4[8] = "_2_Conv_trips_09";
        strArr4[9] = "_2_Conv_trips_0A";
        strArr4[10] = "_2_Conv_trips_0B";
        strArr4[11] = "_2_Conv_trips_0C";
        strArr4[12] = "_2_Conv_trips_0D";
        strArr4[13] = "_2_Conv_trips_0E";
        strArr4[14] = "_2_Conv_trips_0F";
        strArr4[15] = "_2_Conv_trips_10";
        strArr4[16] = "_2_Conv_trips_11";
        strArr4[17] = "_2_Conv_trips_12";
    }

    /* JADX WARN: Type inference failed for: r6v0, types: [com.hzbhd.canbus.car._2.MsgMgr$1] */
    public void tireSetting() {
        new CountDownTimer(3000L, 500L) { // from class: com.hzbhd.canbus.car._2.MsgMgr.1
            @Override // android.os.CountDownTimer
            public void onTick(long j) {
                long j2 = j / 500;
                MyLog.temporaryTracking("时间：" + j2);
                if (j2 == 6) {
                    MsgMgr.this.updateSetUi("Resetting");
                    return;
                }
                if (j2 == 5) {
                    MsgMgr.this.updateSetUi("Resetting.");
                    return;
                }
                if (j2 == 4) {
                    MsgMgr.this.updateSetUi("Resetting..");
                    return;
                }
                if (j2 == 3) {
                    MsgMgr.this.updateSetUi("Resetting...");
                } else if (j2 == 2) {
                    MsgMgr.this.updateSetUi("Resetting....");
                } else if (j2 == 1) {
                    MsgMgr.this.updateSetUi("SUCCESS");
                }
            }

            @Override // android.os.CountDownTimer
            public void onFinish() {
                MsgMgr.this.updateSetUi("  ");
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSetUi(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_2_setting_1"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_2_setting_1", "_2_setting_1_4"), str).setValueStr(true));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }
}
