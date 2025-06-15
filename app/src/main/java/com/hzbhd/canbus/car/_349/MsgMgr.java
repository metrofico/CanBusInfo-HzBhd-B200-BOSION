package com.hzbhd.canbus.car._349;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.InputDeviceCompat;
import com.android.internal.util.ArrayUtils;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.car._94.ActivePark;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.SyncListUpdateEntity;
import com.hzbhd.canbus.entity.SyncSoftKeyUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.util.AppEnableUtil;
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
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import kotlinx.coroutines.scheduling.WorkQueueKt;


public class MsgMgr extends AbstractMsgMgr {
    static final String IS_ACTIVE_PARK_OPEN = "is_active_park_open";
    static int[] mLanguageMapArray;
    static SYNC_VERSION mSyncVersion;
    private int eachId;
    private String[] m0x05ItemTitleArray;
    private SparseArray<ActiveParkItem> mActiveParkItemArray;
    private ActiveParkView mActiveParkView;
    private String mAirUnit;
    int[] mBackLightInt;
    private boolean mBackStatus;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    Context mContext;
    private DataHandler mDataHandler;
    int[] mFrontRadarData;
    private boolean mFrontStatus;
    private boolean mHandBrake;
    private Handler mHandler;
    private boolean mIsActiveViewOpen;
    private boolean mIsSyncNeedShowDialog;
    private int mKeyStatus;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private ID3[] mMeidaInfos;
    private MyPanoramicView mPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private int[] mPreWarningData;
    private String mRadioCurrentBand;
    int[] mRearRadarData;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, Integer> mSettingItemIndeHashMap;
    private SparseIntArray mSyncMenuIconResIdArray;
    TimerUtil mTimerUtil;
    private SparseIntArray mTopIconArray;
    private WindowManager mWindowManager;
    String resulttemp;
    private UiMgr uiMgr;
    private final int INVAILE_VALUE = -1;
    private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
    private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
    private final long DATA_HANDLE_PERIOD = 32;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
    private final int SEND_NORMAL_MESSAGE = 102;
    private final int MEDIA_SOURCE_ID_OFF = 0;
    private final int MEDIA_SOURCE_ID_TUNER = 1;
    private final int MEDIA_SOURCE_ID_DISC = 2;
    private final int MEDIA_SOURCE_ID_TV = 3;
    private final int MEDIA_SOURCE_ID_AUX = 7;
    private final int MEDIA_SOURCE_ID_USB = 8;
    private final int MEDIA_SOURCE_ID_SD = 9;
    private final int MEDIA_SOURCE_ID_DVBT = 10;
    private final int MEDIA_SOURCE_ID_A2DP = 11;
    private DecimalFormat mDecimalFormat0p00 = new DecimalFormat("0.00");
    private DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");
    private DecimalFormat mDecimalFormat00 = new DecimalFormat("00");

    public enum SYNC_KEY_STATE {
        NONE,
        ICON,
        HIGHLIGHT_ICON,
        TEXT,
        HIGHTLIGHT_TEXT
    }

    public enum SYNC_LINE_TEXT_ATT {
        NOR_TEXT,
        NOR_TEXT_GRAY_BKG,
        GRAY_TEXT,
        GRAY_TEXT_GRAY_BKG,
        DEEP_GRAY_TEXT,
        HIDDEN
    }

    public enum SYNC_MESSAGE_TYPE {
        NONE,
        _1_LINE_NO_BUTTON,
        _1_LINE_4_BUTTON,
        _2_LINE_NO_BUTTON,
        _2_LINE_4_BUTTON,
        _3_LINE_NO_BUTTON,
        _3_LINE_4_BUTTON,
        DIAL_REDIAL,
        SPEECH,
        DIAL_A_NUMBER
    }

    public enum SYNC_VERSION {
        NONE,
        VERSION_V1,
        VERSION_V2,
        VERSION_V3
    }

    private void setTableControl0x7A() {
    }

    public MsgMgr() {
        mLanguageMapArray = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 18, 22, 23, 27, 28};
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mSyncMenuIconResIdArray = sparseIntArray;
        sparseIntArray.put(0, R.drawable.ford_sync_icon_null);
        this.mSyncMenuIconResIdArray.append(2, R.drawable.ford_sync_icon_3);
        this.mSyncMenuIconResIdArray.append(10, R.drawable.ford_sync_icon_11);
        this.mSyncMenuIconResIdArray.append(8, R.drawable.ford_sync_icon_9);
        this.mSyncMenuIconResIdArray.append(5, R.drawable.ford_sync_icon_6);
        this.mSyncMenuIconResIdArray.append(12, R.drawable.ford_sync_icon_13);
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        this.mTopIconArray = sparseIntArray2;
        sparseIntArray2.put(0, R.drawable.ford_sync_icon);
        this.mTopIconArray.append(1, R.drawable.ford_sync_icon_204);
        this.mTopIconArray.append(3, R.drawable.ford_sync_icon_93);
        this.mTopIconArray.append(4, R.drawable.ford_sync_icon_130);
        this.mTopIconArray.append(5, R.drawable.ford_sync_icon_41);
        this.mTopIconArray.append(6, R.drawable.ford_sync_keyboard_info);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
        AppEnableUtil.enableApp(context, Constant.SyncActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initActivePark(context);
        initData(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 13) {
            setWarningVolumeState();
            return;
        }
        if (i == 20) {
            setBackLightInfo0x14();
            return;
        }
        if (i == 42) {
            setWarningInfo(context);
            return;
        }
        if (i == 48) {
            setVersionInfo0x30();
            return;
        }
        if (i == 64) {
            setSyncVersion();
            return;
        }
        if (i == 80) {
            setSyncMenuInfo();
            return;
        }
        if (i == 39) {
            setLanguageInfo();
            return;
        }
        if (i == 40) {
            setAutoParkStateInfo(context);
            return;
        }
        if (i == 82) {
            setSyncMediaTimeInfo();
            return;
        }
        if (i != 83) {
            switch (i) {
                case 32:
                    setWheelKeyInfo0x20();
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
                case 37:
                    setPanoramicData0x25();
                    break;
                default:
                    switch (i) {
                        case 102:
                            setTrackData0x66();
                            break;
                        case 103:
                            setTurnSignalInfo();
                            break;
                        case 104:
                            setRotatingSpeed();
                            break;
                        case 105:
                            setAmbientLightState();
                            break;
                        case 106:
                            setSpeedState();
                            break;
                        case 107:
                            setAirData20x6B();
                            break;
                        case 108:
                            setMultiFunctionSeatStatus0x6C();
                            break;
                        default:
                            switch (i) {
                                case 112:
                                    setSyncSrtUp0x70();
                                    break;
                                case 113:
                                    setSyncSrtDown0x71();
                                    break;
                                case 114:
                                    setSyncSrtShort0x72();
                                    break;
                                default:
                                    switch (i) {
                                        case 120:
                                            setSyncState0x78();
                                            break;
                                        case 121:
                                            setSyncSwitch0x79();
                                            break;
                                        case 122:
                                            setTableControl0x7A();
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        setSyncTalkingTimeInfo();
    }

    private void setBackLightInfo0x14() {
        String str;
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            str = "Min";
        } else {
            str = i == 255 ? "Max" : this.mCanBusInfoInt[2] + "";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "keyboard_backlight_adjustment"), str));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        adjustBrightness();
    }

    private void setWheelKeyInfo0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
            return;
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
            buttonKey(20);
            return;
        }
        if (i == 4) {
            buttonKey(21);
            return;
        }
        if (i == 5) {
            buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
            return;
        }
        if (i == 7) {
            buttonKey(2);
            return;
        }
        if (i == 24) {
            buttonKey(HotKeyConstant.K_SPEECH);
            return;
        }
        if (i == 61) {
            buttonKey(HotKeyConstant.K_TIME);
            return;
        }
        if (i == 63) {
            buttonKey(1);
            return;
        }
        if (i == 86) {
            buttonKey(94);
            return;
        }
        if (i == 87) {
            buttonKey(58);
            return;
        }
        if (i == 240) {
            buttonKey(7);
            return;
        }
        if (i != 241) {
            switch (i) {
                case 14:
                    buttonKey(45);
                    break;
                case 15:
                    buttonKey(46);
                    break;
                case 16:
                    buttonKey(47);
                    break;
                case 17:
                    buttonKey(48);
                    break;
                case 18:
                    buttonKey(49);
                    break;
                default:
                    switch (i) {
                        case 32:
                            buttonKey(32);
                            break;
                        case 33:
                            buttonKey(33);
                            break;
                        case 34:
                            buttonKey(34);
                            break;
                        case 35:
                            buttonKey(35);
                            break;
                        case 36:
                            buttonKey(36);
                            break;
                        case 37:
                            buttonKey(37);
                            break;
                        case 38:
                            buttonKey(38);
                            break;
                        case 39:
                            buttonKey(39);
                            break;
                        case 40:
                            buttonKey(40);
                            break;
                        case 41:
                            buttonKey(41);
                            break;
                        case 42:
                            dealSyncKey(this.mContext, 23);
                            break;
                        case 43:
                            dealSyncKey(this.mContext, 24);
                            break;
                        default:
                            switch (i) {
                                case 51:
                                    switchSyncActivity(this.mContext);
                                    break;
                                case 52:
                                    buttonKey(76);
                                    break;
                                case 53:
                                    buttonKey(130);
                                    break;
                                case 54:
                                    buttonKey(141);
                                    break;
                                case 55:
                                    buttonKey(151);
                                    break;
                                case 56:
                                    buttonKey(73);
                                    break;
                                case 57:
                                    buttonKey(HotKeyConstant.K_PHONE_ON_OFF);
                                    break;
                                default:
                                    switch (i) {
                                        case 72:
                                            buttonKey(49);
                                            break;
                                        case 73:
                                            buttonKey(47);
                                            break;
                                        case 74:
                                            buttonKey(48);
                                            break;
                                        case 75:
                                            buttonKey(45);
                                            break;
                                        case 76:
                                            buttonKey(46);
                                            break;
                                        default:
                                            switch (i) {
                                                case 82:
                                                    buttonKey(206);
                                                    break;
                                                case 83:
                                                    buttonKey(HotKeyConstant.K_NEXT_HANGUP);
                                                    break;
                                                case 84:
                                                    buttonKey(31);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 89:
                                                            buttonKey(4);
                                                            break;
                                                        case 90:
                                                            buttonKey(3);
                                                            break;
                                                        case 91:
                                                            buttonKey(57);
                                                            break;
                                                        case 92:
                                                            buttonKey(33);
                                                            break;
                                                        case 93:
                                                            buttonKey(34);
                                                            break;
                                                        case 94:
                                                            buttonKey(35);
                                                            break;
                                                        case 95:
                                                            buttonKey(36);
                                                            break;
                                                    }
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        buttonKey(8);
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
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (GeneralAirData.fahrenheit_celsius) {
            this.mAirUnit = getTempUnitF(this.mContext);
        } else {
            this.mAirUnit = getTempUnitC(this.mContext);
        }
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearRadarInfo0x22() {
        if (isRearRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(15, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadarInfo0x23() {
        if (isFrontRadarDataChange()) {
            return;
        }
        RadarInfoUtil.mMinIsClose = true;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(15, iArr[2], iArr[3], iArr[4], iArr[5]);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDoorData0x24() throws Resources.NotFoundException {
        String string;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String str;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "reverse_state");
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
            string = this.mContext.getResources().getString(R.string.reversing);
        } else {
            string = this.mContext.getResources().getString(R.string.non_reverse);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, string));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_309_title_7");
        if (DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3])) {
            string2 = this.mContext.getResources().getString(R.string._309_value_1);
        } else {
            string2 = this.mContext.getResources().getString(R.string._309_value_2);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, string2));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "light_info");
        if (DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])) {
            string3 = this.mContext.getResources().getString(R.string.open);
        } else {
            string3 = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, string3));
        int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "hand_brake_status");
        if (DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])) {
            string4 = this.mContext.getResources().getString(R.string.pull_up);
        } else {
            string4 = this.mContext.getResources().getString(R.string.put_down);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, string4));
        int drivingPageIndexes5 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes5 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_85_passenger_airbag_sataus");
        if (DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])) {
            string5 = this.mContext.getResources().getString(R.string.open);
        } else {
            string5 = this.mContext.getResources().getString(R.string.close);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes5, drivingItemIndexes5, string5));
        int drivingPageIndexes6 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes6 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_my_key_vol_limit");
        if (DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
            string6 = this.mContext.getResources().getString(R.string._81_unlimit);
        } else {
            string6 = this.mContext.getResources().getString(R.string._81_max_vol_half);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes6, drivingItemIndexes6, string6));
        int drivingPageIndexes7 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes7 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_my_key_mute_status");
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
            string7 = this.mContext.getResources().getString(R.string._349_setting_1_0);
        } else {
            string7 = this.mContext.getResources().getString(R.string._349_setting_1_1);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes7, drivingItemIndexes7, string7));
        int drivingPageIndexes8 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes8 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_engine_display");
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6])) {
            string8 = this.mContext.getResources().getString(R.string.mazda_binary_car_set71_1);
        } else {
            string8 = this.mContext.getResources().getString(R.string.energy_no_display);
        }
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes8, drivingItemIndexes8, string8));
        DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7);
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_engine"), DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) >= 100 ? "100% mileage" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) + "% 里程"));
        String str2 = "Full";
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3) == 0) {
            str = "All empty";
        } else {
            str = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3) >= 5 ? "Full" : DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3) + "";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_ford_vehicle_speed"), str));
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) == 0) {
            str2 = "All empty";
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) < 5) {
            str2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3) + "";
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_81_ford_driving_plan"), str2));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_traction_control_system"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_turn_signals_setup"), Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_message_tone"), Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_alert_tone"), Boolean.valueOf(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_81_current_voice_mode"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "ford_range_unit"), Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]))));
        arrayList2.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "brightness"), Boolean.valueOf(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]))));
        updateGeneralSettingData(arrayList2);
        updateGeneralDriveData(arrayList);
        updateActivity(null);
        updateDriveDataActivity(null);
        if (isDoorChange()) {
            updateDoorView(this.mContext);
        }
        updateParkUi(null, this.mContext);
    }

    private void setPanoramicData0x25() {
        String str = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? "open" : "close";
        String str2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "open" : "close";
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_front_radar"), CommUtil.getStrByResId(this.mContext, str)));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_41_rear_radar"), CommUtil.getStrByResId(this.mContext, str2)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setLanguageInfo() {
        if (!isDataChange(this.mCanBusInfoInt)) {
            return;
        }
        int i = 0;
        while (true) {
            int[] iArr = mLanguageMapArray;
            if (i >= iArr.length) {
                return;
            }
            if (this.mCanBusInfoInt[2] == iArr[i]) {
                setSettingData(new String[]{"language_setup"}, new Object[]{Integer.valueOf(i)});
                return;
            }
            i++;
        }
    }

    private void setAutoParkStateInfo(final Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            SharePreUtil.setBoolValue(context, IS_ACTIVE_PARK_OPEN, DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]));
            this.mTimerUtil.stopTimer();
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._349.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public final void callback() {
                    this.f$0.m749lambda$setAutoParkStateInfo$0$comhzbhdcanbuscar_349MsgMgr(context);
                }
            });
        }
    }

    /* renamed from: lambda$setAutoParkStateInfo$0$com-hzbhd-canbus-car-_349-MsgMgr, reason: not valid java name */
    /* synthetic */ void m749lambda$setAutoParkStateInfo$0$comhzbhdcanbuscar_349MsgMgr(Context context) {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
            openActiveParView();
            this.mParkPageUiSet.setShowCusPanoramicView(true);
            this.mActiveParkView.hideAlert();
            this.mPanoramicView.hideAlertWindow();
            ActiveParkItem activeParkItem = this.mActiveParkItemArray.get(this.mCanBusInfoInt[3]);
            if (activeParkItem.getLeftImageResId() == 0 && activeParkItem.getRightImageResId() == 0) {
                this.mActiveParkView.showAlert();
                this.mPanoramicView.showAlertWindow();
                if (this.mCanBusInfoInt[3] == 28) {
                    this.mTimerUtil.startTimer(new TimerTask(context, activeParkItem) { // from class: com.hzbhd.canbus.car._349.MsgMgr.1
                        int countDown = 10;
                        String text;
                        final /* synthetic */ ActiveParkItem val$activeParkItem;
                        final /* synthetic */ Context val$context;

                        {
                            this.val$context = context;
                            this.val$activeParkItem = activeParkItem;
                            this.text = context.getString(activeParkItem.getMessageResId());
                        }

                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            if (this.countDown >= 0) {
                                Message message = new Message();
                                message.what = 10;
                                message.obj = this.text + "\n" + this.countDown + CommUtil.getStrByResId(this.val$context, "_197_second");
                                MsgMgr.this.mHandler.sendMessage(message);
                                this.countDown--;
                                return;
                            }
                            MsgMgr.this.mTimerUtil.stopTimer();
                        }
                    }, 0L, 1000L);
                } else {
                    this.mActiveParkView.setAlertText(activeParkItem.getMessageResId());
                    this.mPanoramicView.setAlertMessage(activeParkItem.getMessageResId());
                }
            } else {
                this.mActiveParkView.setLeftSideImage(activeParkItem.getLeftImageResId());
                this.mActiveParkView.setRightSideImage(activeParkItem.getRightImageResId());
                this.mActiveParkView.setViewText(activeParkItem.getMessageResId());
                this.mPanoramicView.refreshIcon(activeParkItem.getReverseIcon());
                this.mPanoramicView.setMessage(activeParkItem.getMessageResId());
            }
            updateParkUi(null, context);
            return;
        }
        closeActiveParkView();
        this.mParkPageUiSet.setShowCusPanoramicView(false);
        updateParkUi(null, context);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setWarningInfo(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = iArr[2] & com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE;
        iArr[3] = iArr[3] & WorkQueueKt.MASK;
        iArr[4] = iArr[4] & 128;
        iArr[5] = iArr[5] & 3;
        iArr[11] = iArr[11] & 15;
        iArr[12] = iArr[12] & 1;
        iArr[13] = iArr[13] & 3;
        iArr[14] = 3 & iArr[14];
        iArr[15] = iArr[15] & 200;
        iArr[16] = iArr[16] & 1;
        iArr[17] = iArr[17] & 160;
        if (isDataChange(iArr)) {
            ArrayList arrayList = new ArrayList();
            boolean z = false;
            for (int i = 0; i < 16; i++) {
                for (int i2 = 0; i2 < 8; i2++) {
                    int i3 = i + 2;
                    int i4 = 1 << i2;
                    if ((this.mCanBusInfoInt[i3] & i4) != 0) {
                        arrayList.add(new WarningEntity(CommUtil.getStrByResId(context, "_81_alert_info_" + i + "_" + i2)));
                        if ((this.mPreWarningData[i3] & i4) == 0) {
                            z = true;
                        }
                    }
                }
            }
            if (z) {
                showWarningActivity(this.mContext);
            }
            int[] iArr2 = this.mCanBusInfoInt;
            this.mPreWarningData = Arrays.copyOf(iArr2, iArr2.length);
            GeneralWarningDataData.dataList = arrayList;
            updateWarningActivity(null);
        }
    }

    private void setSyncVersion() {
        if (mSyncVersion != SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
            mSyncVersion = SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
            int i = AnonymousClass3.$SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
            if (i == 1 || i == 2) {
                GeneralSyncData.mInfoLineShowAmount = 3;
                GeneralSyncData.mIsShowSoftKey = false;
            } else if (i == 3) {
                GeneralSyncData.mInfoLineShowAmount = 5;
                GeneralSyncData.mIsShowSoftKey = true;
            }
            GeneralSyncData.mSyncTime = "";
            updateSyncActivity(null);
        }
    }

    /* renamed from: com.hzbhd.canbus.car._349.MsgMgr$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION;

        static {
            int[] iArr = new int[SYNC_VERSION.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION = iArr;
            try {
                iArr[SYNC_VERSION.VERSION_V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_349$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void setSyncMenuInfo() {
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.mSyncMenuIconResIdArray = sparseIntArray;
        sparseIntArray.put(0, R.drawable.ford_sync_icon_null);
        this.mSyncMenuIconResIdArray.append(2, R.drawable.ford_sync_icon_3);
        this.mSyncMenuIconResIdArray.append(10, R.drawable.ford_sync_icon_11);
        this.mSyncMenuIconResIdArray.append(8, R.drawable.ford_sync_icon_9);
        this.mSyncMenuIconResIdArray.append(5, R.drawable.ford_sync_icon_6);
        this.mSyncMenuIconResIdArray.append(12, R.drawable.ford_sync_icon_13);
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            GeneralSyncData.mSelectedLineIndex = this.mCanBusInfoInt[3] - 1;
            int[] iArr = this.mCanBusInfoInt;
            boolean z = iArr[5] != 0;
            this.mIsSyncNeedShowDialog = z;
            if (z) {
                GeneralSyncData.mSelectedLineIndex = iArr[6] - 1;
            }
            GeneralSyncData.mSyncTopIconResIdArray[0] = this.mSyncMenuIconResIdArray.get(this.mCanBusInfoInt[7]);
            updateSyncActivity(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSyncMenuItemInfo(Context context, byte[] bArr) {
        String syncInfo;
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            boolean z = false;
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(bArr[3], 0, 4);
            byte b = bArr[2];
            boolean z2 = true;
            if (b >= 1 && b <= 10) {
                int i = (b - 1) % 5;
                int syncIconResId = getSyncIconResId(context, "ford_sync_icon_" + (bArr[4] & 255));
                int syncIconResId2 = getSyncIconResId(context, "ford_sync_icon_" + (bArr[5] & 255));
                String syncInfo2 = getSyncInfo(bArr);
                boolean boolBit4 = DataHandleUtils.getBoolBit4(bArr[3]);
                for (SyncListUpdateEntity syncListUpdateEntity : GeneralSyncData.mInfoUpdateEntityList) {
                    if (syncListUpdateEntity.getIndex() == i) {
                        syncListUpdateEntity.setLeftIconResId(syncIconResId).setRightIconResId(syncIconResId2).setInfo(syncInfo2).setEnable(boolBit4);
                        updateSyncActivity(null);
                        return;
                    }
                }
                GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(i).setLeftIconResId(syncIconResId).setRightIconResId(syncIconResId2).setInfo(syncInfo2).setEnable(boolBit4));
                updateSyncActivity(null);
                return;
            }
            if (b < 11 || b > 18) {
                return;
            }
            int syncIconResId3 = R.drawable.ford_sync_icon_null;
            syncInfo = "";
            if (intFromByteWithBit != 0) {
                if (intFromByteWithBit != 2) {
                    if (intFromByteWithBit == 3) {
                        syncIconResId3 = getSyncIconResId(context, "ford_sync_icon_" + (bArr[4] & 255));
                    } else if (intFromByteWithBit == 10) {
                        syncInfo = getSyncInfo(bArr);
                    } else {
                        syncInfo = intFromByteWithBit == 11 ? getSyncInfo(bArr) : "";
                    }
                    z = true;
                } else {
                    syncIconResId3 = getSyncIconResId(context, "ford_sync_icon_" + (bArr[4] & 255));
                }
                z2 = false;
                z = true;
            } else {
                z2 = false;
            }
            int i2 = (bArr[2] - 11) % 4;
            for (SyncSoftKeyUpdateEntity syncSoftKeyUpdateEntity : GeneralSyncData.mSoftKeyUpdateEntityList) {
                if (syncSoftKeyUpdateEntity.getIndex() == i2) {
                    syncSoftKeyUpdateEntity.setName(syncInfo).setImageResId(syncIconResId3).setSelected(z2).setVisible(z);
                    updateSyncActivity(null);
                    return;
                }
            }
            GeneralSyncData.mSoftKeyUpdateEntityList.add(new SyncSoftKeyUpdateEntity(i2).setName(syncInfo).setImageResId(syncIconResId3).setSelected(z2).setVisible(z));
            updateSyncActivity(null);
        }
    }

    private void setSyncMediaTimeInfo() {
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            GeneralSyncData.mSyncTime = this.mDecimalFormat00.format(this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[4]);
            GeneralSyncData.mIsSyncTimeChange = true;
            updateSyncActivity(null);
        }
    }

    private void setSyncTalkingTimeInfo() {
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            GeneralSyncData.mSyncTime = this.mDecimalFormat00.format(this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[4]);
            GeneralSyncData.mIsSyncTimeChange = true;
            updateSyncActivity(null);
        }
    }

    private void setWarningVolumeState() {
        String str;
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            str = "Minimum volume";
        } else if (i == 15) {
            str = "Maximum volume";
        } else if (i == 6) {
            str = "ordinary";
        } else {
            str = i == 12 ? "high" : this.mCanBusInfoInt[2] + "";
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_349_setting_5_0"), str).setProgress(this.mCanBusInfoInt[2]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTrackData0x66() {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 4768, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setTurnSignalInfo() throws Resources.NotFoundException {
        ArrayList arrayList = new ArrayList();
        String string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_1);
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_1);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_2);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 2) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_3);
        }
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 3) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set77_4);
        }
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "mazda_binary_car_set77"), string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setRotatingSpeed() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "engine_speed");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat0p00;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getData(iArr[2], iArr[3]))).append("rpm").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAmbientLightState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_1193_setting_5_3"), this.mCanBusInfoInt[2] == 0 ? "关闭氛围灯" : this.mCanBusInfoInt[2] + "").setProgress(this.mCanBusInfoInt[2]));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_250_vehicle_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_250_vehicle_settings", "_176_car_setting_title_21"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSpeedState() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_status");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "a_current_speed");
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = this.mDecimalFormat0p00;
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(decimalFormat.format(getData(iArr[2], iArr[3]))).append(" km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(DataHandleUtils.getMsbLsbResult(iArr2[3], iArr2[2]));
    }

    private void setAirData20x6B() throws Resources.NotFoundException {
        String string;
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) == 0) {
            string = this.mContext.getResources().getString(R.string.mazda_binary_car_set30_1);
        } else if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2) == 1) {
            string = this.mContext.getResources().getString(R.string.refrigeration);
        } else {
            string = this.mContext.getResources().getString(R.string.heating);
        }
        GeneralAirData.center_wheel = string;
        updateAirActivity(this.mContext, 0);
    }

    private void setMultiFunctionSeatStatus0x6C() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_0"), Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_1"), Integer.valueOf(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_2"), Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_3"), Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_2_4"), Integer.valueOf(this.mCanBusInfoInt[6])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_0"), Integer.valueOf(this.mCanBusInfoInt[7])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_1"), Integer.valueOf(this.mCanBusInfoInt[8])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_2"), Integer.valueOf(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_3"), Integer.valueOf(this.mCanBusInfoInt[10])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_3_4"), Integer.valueOf(this.mCanBusInfoInt[11])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_0"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 2, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_349_setting_2"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_349_setting_2", "_349_setting_4_3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[13], 2, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setSyncSrtUp0x70() {
        if (compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
            GeneralSyncData.mInfoLineShowAmount = 3;
            GeneralSyncData.mIsShowSoftKey = false;
            byte[] bArr = this.mCanBusInfoByte;
            String str = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
            for (SyncListUpdateEntity syncListUpdateEntity : GeneralSyncData.mInfoUpdateEntityList) {
                if (syncListUpdateEntity.getIndex() == 0) {
                    syncListUpdateEntity.setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false);
                    updateSyncActivity(null);
                    return;
                }
            }
            GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(0).setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false));
            updateSyncActivity(null);
        }
    }

    private void setSyncSrtDown0x71() {
        if (compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
            GeneralSyncData.mInfoLineShowAmount = 3;
            GeneralSyncData.mIsShowSoftKey = false;
            byte[] bArr = this.mCanBusInfoByte;
            String str = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
            for (SyncListUpdateEntity syncListUpdateEntity : GeneralSyncData.mInfoUpdateEntityList) {
                if (syncListUpdateEntity.getIndex() == 1) {
                    syncListUpdateEntity.setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false);
                    updateSyncActivity(null);
                    return;
                }
            }
            GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(1).setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false));
            updateSyncActivity(null);
        }
    }

    private void setSyncSrtShort0x72() {
        if (compare(mSyncVersion, SYNC_VERSION.VERSION_V1, SYNC_VERSION.VERSION_V2)) {
            GeneralSyncData.mInfoLineShowAmount = 3;
            GeneralSyncData.mIsShowSoftKey = false;
            byte[] bArr = this.mCanBusInfoByte;
            String str = new String(Arrays.copyOfRange(bArr, 2, bArr.length));
            for (SyncListUpdateEntity syncListUpdateEntity : GeneralSyncData.mInfoUpdateEntityList) {
                if (syncListUpdateEntity.getIndex() == 2) {
                    syncListUpdateEntity.setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false);
                    updateSyncActivity(null);
                    return;
                }
            }
            GeneralSyncData.mInfoUpdateEntityList.add(new SyncListUpdateEntity(2).setLeftIconResId(R.drawable.ford_sync_null).setRightIconResId(R.drawable.ford_sync_null).setInfo(str).setEnable(false));
            updateSyncActivity(null);
        }
    }

    private void setSyncState0x78() {
        for (int i = 0; i < 2; i++) {
            if ((this.mCanBusInfoInt[3] & (1 << i)) == 0) {
                GeneralSyncData.mSyncTopIconResIdArray[i + 1] = R.drawable.ford_sync_null;
            } else {
                GeneralSyncData.mSyncTopIconResIdArray[i + 1] = this.mTopIconArray.get(i);
            }
        }
        for (int i2 = 3; i2 < 7; i2++) {
            if ((this.mCanBusInfoInt[3] & (1 << i2)) == 0) {
                GeneralSyncData.mSyncTopIconResIdArray[i2] = R.drawable.ford_sync_null;
            } else {
                GeneralSyncData.mSyncTopIconResIdArray[i2] = this.mTopIconArray.get(i2);
            }
        }
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) + 103;
        if (intFromByteWithBit > 107) {
            intFromByteWithBit = 0;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4) + 97;
        int i3 = intFromByteWithBit2 <= 101 ? intFromByteWithBit2 : 0;
        GeneralSyncData.mSyncTopIconResIdArray[7] = getSyncIconResId(this.mContext, "ford_sync_icon_" + intFromByteWithBit);
        GeneralSyncData.mSyncTopIconResIdArray[8] = getSyncIconResId(this.mContext, "ford_sync_icon_" + i3);
        updateSyncActivity(null);
    }

    private void setSyncSwitch0x79() {
        if (compare(Integer.valueOf(this.mCanBusInfoInt[2]), 1, 3, 4)) {
            startSyncActivity(this.mContext);
        } else if (compare(Integer.valueOf(this.mCanBusInfoInt[2]), 0, 2, 5)) {
            if (SystemUtil.isForeground(this.mContext, SyncActivity.class.getName())) {
                realKeyClick(this.mContext, 50);
            } else {
                enterNoSource();
            }
        }
    }

    private boolean isBackLightIntChange() {
        if (Arrays.equals(this.mBackLightInt, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mBackLightInt = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private void adjustBrightness() {
        setBacklightLevel((this.mCanBusInfoInt[2] / 51) + 1);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void dealSyncKey(Context context, int i) {
        dealSyncKey(context, i, -1, 0);
    }

    private void dealSyncKey(Context context, int i, int i2, int i3) {
        if (SystemUtil.isForeground(context, SyncActivity.class.getName())) {
            if (this.mKeyStatus == 1) {
                int i4 = this.mCanBusInfoInt[3];
                if (i4 == 0) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i});
                } else if (i4 == 2 && i2 != -1) {
                    CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) i2});
                }
            }
        } else {
            realKeyClick2(this.mContext, i3);
        }
        this.mKeyStatus = this.mCanBusInfoInt[3];
    }

    private String resolveAirTemperature(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            if (i == 119) {
                this.resulttemp = "LOW";
            }
            if (i == 171) {
                this.resulttemp = "HIGH";
            } else if (i >= 120 && i <= 170) {
                String str = (i / 2.0f) + this.mAirUnit;
                this.resulttemp = str;
                return str;
            }
        } else if (i == 0) {
            this.resulttemp = "LOW";
        } else if (i == 127) {
            this.resulttemp = "HIGH";
        } else if (i >= 31 && i <= 59) {
            String str2 = (i / 2.0f) + this.mAirUnit;
            this.resulttemp = str2;
            return str2;
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

    private void switchSyncActivity(Context context) {
        if (this.mCanBusInfoInt[3] == 1) {
            if (SystemUtil.isForeground(context, SyncActivity.class.getName())) {
                realKeyClick(context, 50);
                return;
            }
            Intent intent = new Intent();
            intent.setComponent(Constant.SyncActivity);
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    private int[] getLeftAndRight(String str) {
        int[] iArr = {-1, -1};
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            int iIntValue = this.mSettingItemIndeHashMap.get(str).intValue();
            iArr[0] = iIntValue >> 8;
            iArr[1] = iIntValue & 255;
            Log.i("ljq", "getLeftAndRigth: left:" + iArr[0] + ", right:" + iArr[1]);
        }
        return iArr;
    }

    private void setSettingData(String[] strArr, Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < strArr.length; i++) {
            int[] leftAndRight = getLeftAndRight(strArr[i]);
            if (leftAndRight[0] != -1 && leftAndRight[1] != -1) {
                arrayList.add(new SettingUpdateEntity(leftAndRight[0], leftAndRight[1], objArr[i]));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void showWarningActivity(Context context) {
        this.mHandler.removeMessages(11);
        startWarningActivity(context);
        Message message = new Message();
        message.what = 11;
        message.obj = context;
        this.mHandler.sendMessageDelayed(message, 5000L);
    }

    private void initData(final Context context) {
        this.mAirUnit = getTempUnitC(context);
        this.mParkPageUiSet = getUiMgr(context).getParkPageUiSet(context);
        this.mPanoramicView = (MyPanoramicView) getUiMgr(context).getCusPanoramicView(this.mContext);
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._349.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 10) {
                    MsgMgr.this.mActiveParkView.setAlertText((String) message.obj);
                    MsgMgr.this.mPanoramicView.setAlertMessage((String) message.obj);
                    return;
                }
                if (i == 11) {
                    if (SystemUtil.isForeground((Context) message.obj, WarningActivity.class.getName())) {
                        MsgMgr.this.finishActivity();
                    }
                } else if (i == 101) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else {
                    if (i != 102) {
                        return;
                    }
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
        this.mDataHandler = new DataHandler(context, 0L, 32L);
        this.m0x05ItemTitleArray = new String[0];
    }

    private class DataHandler {
        private List<byte[]> bytes = new ArrayList();
        private int size;

        public DataHandler(final Context context, long j, long j2) {
            new Timer().schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._349.MsgMgr.DataHandler.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (DataHandler.this.size != 0) {
                        DataHandler.this.pop(context);
                    }
                }
            }, j, j2);
        }

        private void push(byte[] bArr) {
            this.bytes.add(bArr);
            this.size = this.bytes.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void pop(Context context) {
            if (this.bytes.get(0)[1] == 81) {
                MsgMgr.this.setSyncMenuItemInfo(context, this.bytes.get(0));
            }
            this.bytes.remove(0);
            this.size = this.bytes.size();
        }
    }

    private class ActiveParkView extends LinearLayout {
        private ImageView mIvLeftSide;
        private ImageView mIvRightSide;
        private LinearLayout mLlAlert;
        private TextView mTvAlertMessage;
        private TextView mTvMessage;

        private void initView() {
        }

        public ActiveParkView(Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.layout_active_park_81_view, this);
            findeView();
            initView();
        }

        private void findeView() {
            this.mIvLeftSide = (ImageView) findViewById(R.id.iv_left_side);
            this.mIvRightSide = (ImageView) findViewById(R.id.iv_right_side);
            this.mTvMessage = (TextView) findViewById(R.id.tv_message);
            this.mLlAlert = (LinearLayout) findViewById(R.id.ll_alert);
            this.mTvAlertMessage = (TextView) findViewById(R.id.tv_alert_message);
        }

        public void showAlert() {
            this.mLlAlert.setVisibility(0);
        }

        public void hideAlert() {
            this.mLlAlert.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLeftSideImage(int i) {
            this.mIvLeftSide.setImageResource(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setRightSideImage(int i) {
            this.mIvRightSide.setImageResource(i);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setViewText(int i) {
            this.mTvMessage.setText(i);
        }

        public void setAlertText(int i) {
            this.mTvAlertMessage.setText(i);
        }

        public void setAlertText(String str) {
            this.mTvAlertMessage.setText(str);
        }
    }

    private void initActivePark(Context context) {
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.type = 2002;
        this.mLayoutParams.gravity = 17;
        this.mLayoutParams.width = -1;
        this.mLayoutParams.height = -1;
        this.mActiveParkView = new ActiveParkView(context);
        SparseArray<ActiveParkItem> sparseArray = new SparseArray<>();
        this.mActiveParkItemArray = sparseArray;
        sparseArray.put(2, new ActiveParkItem(R.string._81_active_park_02));
        this.mActiveParkItemArray.append(3, new ActiveParkItem(R.string._81_active_park_03));
        this.mActiveParkItemArray.append(4, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x04, R.string._81_active_park_04, ActivePark.MyPanoramicView.RIGHT_RADAR));
        this.mActiveParkItemArray.append(5, new ActiveParkItem(R.drawable.ford_pa_0x05, R.drawable.ford_sync_icon_null, R.string._81_active_park_05, ActivePark.MyPanoramicView.LEFT_RADAR));
        this.mActiveParkItemArray.append(6, new ActiveParkItem(R.string._81_active_park_06));
        this.mActiveParkItemArray.append(7, new ActiveParkItem(R.drawable._81_ford_active_park_07, R.drawable.ford_sync_icon_null, R.string._81_active_park_07, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(8, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_08, R.string._81_active_park_08, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(9, new ActiveParkItem(R.drawable._81_ford_active_park_07, R.drawable.ford_sync_icon_null, R.string._81_active_park_09, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(10, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_08, R.string._81_active_park_0A, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(11, new ActiveParkItem(R.drawable._81_ford_active_park_0b, R.drawable.ford_sync_icon_null, R.string._81_active_park_0B, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(12, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_0c, R.string._81_active_park_0C, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(13, new ActiveParkItem(R.drawable._81_ford_active_park_0b, R.drawable.ford_sync_icon_null, R.string._81_active_park_0D, "left_REVERSE"));
        this.mActiveParkItemArray.append(14, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_0c, R.string._81_active_park_0E, "left_REVERSE"));
        this.mActiveParkItemArray.append(15, new ActiveParkItem(R.drawable.ford_pa_0x0f, R.drawable.ford_sync_icon_null, R.string._81_active_park_0F, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(16, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x10, R.string._81_active_park_10, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(17, new ActiveParkItem(R.drawable.ford_pa_0x0f, R.drawable.ford_sync_icon_null, R.string._81_active_park_11, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(18, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable.ford_pa_0x10, R.string._81_active_park_12, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(19, new ActiveParkItem(R.drawable._81_ford_active_park_13, R.drawable.ford_sync_icon_null, R.string._81_active_park_13, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(20, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_14, R.string._81_active_park_14, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(21, new ActiveParkItem(R.drawable._81_ford_active_park_15, R.drawable.ford_sync_icon_null, R.string._81_active_park_15, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(22, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_16, R.string._81_active_park_16, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(23, new ActiveParkItem(R.drawable._81_ford_active_park_17, R.drawable.ford_sync_icon_null, R.string._81_active_park_17, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(24, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_18, R.string._81_active_park_18, ActivePark.MyPanoramicView.LEFT_STOP));
        this.mActiveParkItemArray.append(25, new ActiveParkItem(R.drawable._81_ford_active_park_19, R.drawable.ford_sync_icon_null, R.string._81_active_park_19, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(26, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_1a, R.string._81_active_park_1A, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(27, new ActiveParkItem(R.string._81_active_park_1B));
        this.mActiveParkItemArray.append(28, new ActiveParkItem(R.string._81_active_park_1C));
        this.mActiveParkItemArray.append(29, new ActiveParkItem(R.string._81_active_park_1D));
        this.mActiveParkItemArray.append(30, new ActiveParkItem(R.string._81_active_park_1E));
        this.mActiveParkItemArray.append(31, new ActiveParkItem(R.string._81_active_park_1F));
        this.mActiveParkItemArray.append(32, new ActiveParkItem(R.string._81_active_park_20));
        this.mActiveParkItemArray.append(33, new ActiveParkItem(R.string._81_active_park_21));
        this.mActiveParkItemArray.append(34, new ActiveParkItem(R.string._81_active_park_22));
        this.mActiveParkItemArray.append(35, new ActiveParkItem(R.drawable._81_ford_active_park_15, R.drawable.ford_sync_icon_null, R.string._81_active_park_15, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(36, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_16, R.string._81_active_park_16, ActivePark.MyPanoramicView.LEFT_FORWARD));
        this.mActiveParkItemArray.append(37, new ActiveParkItem(R.drawable._81_ford_active_park_19, R.drawable.ford_sync_icon_null, R.string._81_active_park_19, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(38, new ActiveParkItem(R.drawable.ford_sync_icon_null, R.drawable._81_ford_active_park_1a, R.string._81_active_park_1A, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        this.mActiveParkItemArray.append(39, new ActiveParkItem(R.string._81_active_park_1D));
    }

    private class ActiveParkItem {
        private int leftImageResId;
        private int messageResId;
        private String reverseIcon;
        private int rightImageResId;

        public ActiveParkItem(int i) {
            this.messageResId = i;
        }

        public ActiveParkItem(int i, int i2, int i3, String str) {
            this.leftImageResId = i;
            this.rightImageResId = i2;
            this.messageResId = i3;
            this.reverseIcon = str;
        }

        public int getLeftImageResId() {
            return this.leftImageResId;
        }

        public int getRightImageResId() {
            return this.rightImageResId;
        }

        public int getMessageResId() {
            return this.messageResId;
        }

        public String getReverseIcon() {
            return this.reverseIcon;
        }
    }

    private void closeActiveParkView() {
        ActiveParkView activeParkView;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (activeParkView = this.mActiveParkView) == null || !this.mIsActiveViewOpen) {
            return;
        }
        windowManager.removeView(activeParkView);
        this.mIsActiveViewOpen = false;
    }

    private void openActiveParView() {
        if (this.mIsActiveViewOpen) {
            return;
        }
        this.mWindowManager.addView(this.mActiveParkView, this.mLayoutParams);
        this.mIsActiveViewOpen = true;
    }

    private int getSyncIconResId(Context context, String str) {
        int imgIdByResId = CommUtil.getImgIdByResId(context, str);
        return imgIdByResId == R.drawable.music_photo_null ? R.drawable.ford_sync_icon_null : imgIdByResId;
    }

    private String getSyncInfo(byte[] bArr) {
        if (bArr.length < 8) {
            return "";
        }
        int length = bArr.length;
        int i = 7;
        while (true) {
            if (i >= bArr.length) {
                break;
            }
            int i2 = i - 1;
            if (bArr[i2] == 0 && bArr[i] == 0) {
                length = i2;
                break;
            }
            i += 2;
        }
        try {
            return new String(Arrays.copyOfRange(bArr, 6, length), "unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
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

    private void startSyncActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) SyncActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) (i - 2000), (byte) i3, (byte) i4, (byte) i8, (byte) i6, (byte) i7});
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)).setProgress(i3));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateSync(Bundle bundle) {
        updateSyncActivity(bundle);
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        Handler handler = this.mHandler;
        if (handler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
        } else {
            handler.sendMessageDelayed(handler.obtainMessage(102, obj), j);
        }
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        Handler handler = this.mHandler;
        if (handler == null) {
            return;
        }
        handler.sendMessageDelayed(handler.obtainMessage(101, i, 0, obj), j);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendNormalMessage(DataHandleUtils.byteMerger(new byte[]{22, -64, 0, (byte) 0}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        int[] redioFrequencyHiLo = getRedioFrequencyHiLo(str, str2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 1, (byte) 1}, new byte[]{(byte) getRadioBandData(str), (byte) redioFrequencyHiLo[1], (byte) redioFrequencyHiLo[0], (byte) i, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.FM.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
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
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 3, (byte) 34}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 7, (byte) 48}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), (byte) 255}, new byte[]{(byte) i, b7, 0, b3, b4, b5}));
        this.mMeidaInfos[0].setInfo(str4);
        this.mMeidaInfos[1].setInfo(str5);
        this.mMeidaInfos[2].setInfo(str6);
        reportID3Info(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, (byte) (b != 9 ? 8 : 9), (byte) 19}, new byte[]{(byte) i, b6, 0, b3, b4, b5}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 10, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -64, 11, (byte) 255}, new byte[]{0, 0, 0, 0, 0, 0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        this.mMeidaInfos[0].setInfo(str);
        this.mMeidaInfos[1].setInfo(str2);
        this.mMeidaInfos[2].setInfo(str3);
        reportID3Info(SourceConstantsDef.SOURCE_ID.BTAUDIO.ordinal(), this.mMeidaInfos);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        this.mMeidaInfos[0].setInfo(" ");
        this.mMeidaInfos[1].setInfo(" ");
        this.mMeidaInfos[2].setInfo(" ");
        reportID3Info(SourceConstantsDef.SOURCE_ID.NULL.ordinal(), this.mMeidaInfos);
    }

    private static class ID3 {
        private int dataType;
        private final int ID3_INFO_LENGTH = 24;
        private String info = "";
        private String record = "";

        private ID3(int i) {
            this.dataType = i;
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
        if (ArrayUtils.isEmpty(iArr)) {
            return "";
        }
        String str = iArr.length > 0 ? "" + iArr[0] + CommUtil.getStrByResId(this.mContext, "_197_hour") : "";
        if (iArr.length > 1) {
            str = str + iArr[1] + CommUtil.getStrByResId(this.mContext, "_197_minute");
        }
        return iArr.length > 2 ? str + iArr[1] + CommUtil.getStrByResId(this.mContext, "_197_second") : str;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.uiMgr == null) {
            this.uiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.uiMgr;
    }
}
