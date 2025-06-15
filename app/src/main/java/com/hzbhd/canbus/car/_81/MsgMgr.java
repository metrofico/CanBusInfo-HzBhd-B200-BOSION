package com.hzbhd.canbus.car._81;

import android.content.Context;
import android.content.Intent;
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
import androidx.core.view.MotionEventCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.activity.WarningActivity;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car._94.ActivePark;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
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
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.disc.MpegConstantsDef;
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
import nfore.android.bt.res.NfDef;
import org.apache.log4j.Priority;


public class MsgMgr extends AbstractMsgMgr {
    static final String IS_ACTIVE_PARK_OPEN = "is_active_park_open";
    static int[] mLanguageMapArray;
    static SYNC_VERSION mSyncVersion;
    private String[] m0x05ItemTitleArray;
    private SparseArray<ActiveParkItem> mActiveParkItemArray;
    private ActiveParkView mActiveParkView;
    private String mAirUnit;
    private boolean mBackStatus;
    private int mBacklight;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private DataHandler mDataHandler;
    private Handler mHandler;
    private boolean mIsActiveViewOpen;
    private int mIsDiscExsit;
    private boolean mIsSyncNeedShowDialog;
    private int mKeyStatus;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private MyPanoramicView mPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private int[] mPreWarningData;
    private String mRadioCurrentBand;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, Integer> mSettingItemIndeHashMap;
    private SparseIntArray mSyncMenuIconResIdArray;
    private SparseIntArray mTopIconArray;
    private UiMgr mUiMgr;
    private String mVersionInfo;
    private WindowManager mWindowManager;
    private final String TAG = "_81_MSG";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;
    private final long DATA_HANDLE_PERIOD = 32;
    private final int BACKLIGHT_DATA_MAX = 256;
    private final int BACKLIGHT_SEGMENTS = 5;
    private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
    private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
    DecimalFormat df_2Integer = new DecimalFormat("00");
    private int mDiiferentId = getCurrentCanDifferentId();
    private int mEachId = getCurrentEachCanId();
    TimerUtil mTimerUtil = new TimerUtil();
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
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

    private void set0x16VehicleSpeed() {
    }

    public MsgMgr() {
        this.mPreWarningData = new int[18];
        this.mPreWarningData = new int[18];
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
        GeneralSyncData.mInfoLineShowAmount = 5;
        GeneralSyncData.mIsShowSoftKey = true;
        GeneralSyncData.mSyncTopIconCount = 9;
        GeneralSyncData.mSyncTopIconResIdArray = new int[GeneralSyncData.mSyncTopIconCount];
        RadarInfoUtil.mMinIsClose = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initData(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, 6});
        initSettingsItem(UiMgrFactory.getCanUiMgr(context).getSettingUiSet(context));
        initActivePark(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.SyncActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            Log.i("_81_MSG", "canbusInfoChange: 0x14");
            return;
        }
        if (i == 22) {
            set0x16VehicleSpeed();
            return;
        }
        if (i == 48) {
            set0x30VersionInfo(context);
            return;
        }
        if (i == 53) {
            set0x35TrackData(context);
            return;
        }
        if (i == 64) {
            set0x40SyncVersion();
            return;
        }
        if (i == 72) {
            set0x48VehicleSetting();
            return;
        }
        if (i == 86) {
            set0x56SyncHostControl(context);
            return;
        }
        if (i == 120) {
            set0x78SyncStatus(context);
            return;
        }
        if (i != 121) {
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
                    set0x24BaseInfo(context);
                    break;
                case 37:
                    set0x25RadarStatus(context);
                    break;
                default:
                    switch (i) {
                        case 39:
                            set0x27Language();
                            break;
                        case 40:
                            set0x28ActiveParkData(context);
                            break;
                        case 41:
                            set0x29VehicleInfo();
                            break;
                        case 42:
                            set0x2AWarningInfo(context);
                            break;
                        default:
                            switch (i) {
                                case 80:
                                    set0x50SyncMenuInfo();
                                    break;
                                case 81:
                                    DataHandler dataHandler = this.mDataHandler;
                                    if (dataHandler != null) {
                                        dataHandler.push(this.mCanBusInfoByte);
                                        break;
                                    }
                                    break;
                                case 82:
                                    set0x52SyncMediaTime();
                                    break;
                                case 83:
                                    set0x53SyncPhoneTime();
                                    break;
                                default:
                                    switch (i) {
                                        case 112:
                                            set0x70SyncSrtUp();
                                            break;
                                        case 113:
                                            set0x71SyncSrtDown();
                                            break;
                                        case 114:
                                            set0x72SyncSrtShort();
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        set0x79SyncSwitchRequest(context);
    }

    private void set0x14Backlight() {
        int i;
        if (!isDataChange(this.mCanBusInfoInt) || this.mBacklight == (i = (int) (this.mCanBusInfoInt[2] / 51.2f))) {
            return;
        }
        this.mBacklight = i;
        setBacklightLevel(5 - i);
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(context, 0);
            return;
        }
        if (i == 1) {
            realKeyClick2(context, 7);
            return;
        }
        if (i == 2) {
            realKeyClick2(context, 8);
            return;
        }
        if (i == 3) {
            realKeyClick2(context, 20);
            return;
        }
        if (i == 4) {
            realKeyClick2(context, 21);
            return;
        }
        if (i == 7) {
            realKeyClick2(context, 2);
            return;
        }
        if (i == 61) {
            realKeyClick2(context, HotKeyConstant.K_TIME);
            return;
        }
        if (i == 63) {
            realKeyClick2(context, HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 111) {
            realKeyClick2(context, 59);
            return;
        }
        if (i == 134) {
            realKeyClick2(context, 17);
            return;
        }
        if (i == 86) {
            realKeyClick2(context, 94);
            return;
        }
        if (i != 87) {
            switch (i) {
                case 14:
                    realKeyClick2(context, 45);
                    break;
                case 15:
                    realKeyClick2(context, 46);
                    break;
                case 16:
                    realKeyClick2(context, 47);
                    break;
                case 17:
                    realKeyClick2(context, 48);
                    break;
                case 18:
                    realKeyClick2(context, 49);
                    break;
                default:
                    switch (i) {
                        case 32:
                            dealSyncKey(context, 13, 48, 32);
                            break;
                        case 33:
                            dealSyncKey(context, 14, 49, 33);
                            break;
                        case 34:
                            dealSyncKey(context, 15, 50, 34);
                            break;
                        case 35:
                            dealSyncKey(context, 16, 51, 35);
                            break;
                        case 36:
                            dealSyncKey(context, 17, 52, 36);
                            break;
                        case 37:
                            dealSyncKey(context, 18, 53, 37);
                            break;
                        case 38:
                            dealSyncKey(context, 19, 54, 38);
                            break;
                        case 39:
                            dealSyncKey(context, 20, 55, 39);
                            break;
                        case 40:
                            dealSyncKey(context, 21, 56, 40);
                            break;
                        case 41:
                            dealSyncKey(context, 22, 57, 41);
                            break;
                        case 42:
                            dealSyncKey(context, 23);
                            break;
                        case 43:
                            dealSyncKey(context, 24);
                            break;
                        default:
                            switch (i) {
                                case 51:
                                    switchSyncActivity(context);
                                    break;
                                case 52:
                                    realKeyClick2(context, 76);
                                    break;
                                case 53:
                                    realKeyClick2(context, 130);
                                    break;
                                case 54:
                                    realKeyClick2(context, 141);
                                    break;
                                case 55:
                                    realKeyClick2(context, 52);
                                    break;
                                case 56:
                                    realKeyClick2(context, 4);
                                    break;
                                case 57:
                                    realKeyClick2(context, 14);
                                    break;
                                default:
                                    switch (i) {
                                        case 72:
                                            realKeyClick2(context, 49);
                                            break;
                                        case 73:
                                            realKeyClick2(context, 47);
                                            break;
                                        case 74:
                                            realKeyClick2(context, 48);
                                            break;
                                        case 75:
                                            realKeyClick2(context, 45);
                                            break;
                                        case 76:
                                            realKeyClick2(context, 46);
                                            break;
                                        default:
                                            switch (i) {
                                                case 82:
                                                    realKeyClick2(context, 206);
                                                    break;
                                                case 83:
                                                    realKeyClick2(context, HotKeyConstant.K_NEXT_HANGUP);
                                                    break;
                                                case 84:
                                                    realKeyClick2(context, 31);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 89:
                                                            realKeyClick2(context, 4);
                                                            break;
                                                        case 90:
                                                            realKeyClick2(context, 3);
                                                            break;
                                                        case 91:
                                                            realKeyClick2(context, 152);
                                                            break;
                                                        case 92:
                                                            dealSyncKey(context, 28);
                                                            break;
                                                        case 93:
                                                            dealSyncKey(context, 29);
                                                            break;
                                                        case 94:
                                                            dealSyncKey(context, 30);
                                                            break;
                                                        case 95:
                                                            dealSyncKey(context, 31);
                                                            break;
                                                        default:
                                                            switch (i) {
                                                                case NfDef.STATE_3WAY_M_HOLD /* 240 */:
                                                                    realKeyClick3_1(context, 7);
                                                                    break;
                                                                case 241:
                                                                    realKeyClick3_1(context, 8);
                                                                    break;
                                                                case 242:
                                                                    realKeyClick3_2(context, 48);
                                                                    break;
                                                                case 243:
                                                                    realKeyClick3_2(context, 47);
                                                                    break;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
            return;
        }
        startMainActivity(context);
    }

    private void set0x21AirInfo(Context context) {
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (GeneralAirData.fahrenheit_celsius) {
            this.mAirUnit = getTempUnitF(context);
        } else {
            this.mAirUnit = getTempUnitC(context);
        }
        updateOutDoorTemp(context, resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[7] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.max_front = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemperature(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemperature(this.mCanBusInfoInt[5]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        updateAirActivity(context, 1001);
    }

    private void set0x22RearRadar(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x23FrontRadar(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(31, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setProbeRadarLocationData(31, iArr2[7], iArr2[6], 0, 0);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x30VersionInfo(Context context) {
        String versionStr = getVersionStr(this.mCanBusInfoByte);
        this.mVersionInfo = versionStr;
        updateVersionInfo(context, versionStr);
    }

    private void set0x24BaseInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = 0;
        iArr[3] = iArr[3] & 192;
        if (isDataChange(iArr)) {
            setSettingData(new String[]{"_81_traction_control_system", "_81_turn_signals_setup", "ford_message_tone", "ford_alert_tone", "_81_current_voice_mode", "ford_range_unit", "brightness", "_81_tyre_monitor", "_81_rain_sensor", "_81_interior_lighting", "_81_park_lock_ctrl", "_81_hill_start_assist", "parking_assistance"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 3)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1)), DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]) ? "str_ok" : "null_value", Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 1, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 1)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 5, 1))});
            String str = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) ? "_81_max_vol_half" : "_81_unlimit";
            String str2 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? "_103_car_setting_value_7_1" : "_103_car_setting_value_7_0";
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 3);
            String string = intFromByteWithBit < 6 ? Integer.toString(intFromByteWithBit) : "";
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 3);
            String string2 = intFromByteWithBit2 < 6 ? Integer.toString(intFromByteWithBit2) : "";
            String str3 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 7) + "%";
            String str4 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]) ? "mazda_binary_car_set71_1" : "energy_no_display";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 3, CommUtil.getStrByResId(context, str)));
            arrayList.add(new DriverUpdateEntity(0, 4, CommUtil.getStrByResId(context, str2)));
            arrayList.add(new DriverUpdateEntity(0, 5, string));
            arrayList.add(new DriverUpdateEntity(0, 6, string2));
            arrayList.add(new DriverUpdateEntity(0, 7, str3));
            arrayList.add(new DriverUpdateEntity(0, 8, CommUtil.getStrByResId(context, str4)));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new PanoramicBtnUpdateEntity(0, !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7])));
            GeneralParkData.dataList = arrayList2;
            updateParkUi(null, context);
        }
    }

    private void set0x25RadarStatus(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            String str = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? "open" : "close";
            String str2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "open" : "close";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 9, CommUtil.getStrByResId(context, str)));
            arrayList.add(new DriverUpdateEntity(0, 10, CommUtil.getStrByResId(context, str2)));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x27Language() {
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

    private void set0x2AWarningInfo(Context context) {
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
                showWarningActivity(context);
            }
            int[] iArr2 = this.mCanBusInfoInt;
            this.mPreWarningData = Arrays.copyOf(iArr2, iArr2.length);
            GeneralWarningDataData.dataList = arrayList;
            updateWarningActivity(null);
        }
    }

    private void set0x70SyncSrtUp() {
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

    private void set0x71SyncSrtDown() {
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

    private void set0x72SyncSrtShort() {
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

    private void set0x78SyncStatus(Context context) {
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
        GeneralSyncData.mSyncTopIconResIdArray[7] = getSyncIconResId(context, "ford_sync_icon_" + intFromByteWithBit);
        GeneralSyncData.mSyncTopIconResIdArray[8] = getSyncIconResId(context, "ford_sync_icon_" + i3);
        updateSyncActivity(null);
    }

    private void set0x79SyncSwitchRequest(Context context) {
        if (compare(Integer.valueOf(this.mCanBusInfoInt[2]), 1, 3, 4)) {
            startSyncActivity(context);
        } else if (compare(Integer.valueOf(this.mCanBusInfoInt[2]), 0, 2, 5)) {
            if (SystemUtil.isForeground(context, SyncActivity.class.getName())) {
                realKeyClick(context, 50);
            } else {
                enterNoSource();
            }
        }
    }

    private void set0x40SyncVersion() {
        if (mSyncVersion != SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
            mSyncVersion = SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
            int i = AnonymousClass3.$SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
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

    /* renamed from: com.hzbhd.canbus.car._81.MsgMgr$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION;

        static {
            int[] iArr = new int[SYNC_VERSION.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION = iArr;
            try {
                iArr[SYNC_VERSION.VERSION_V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_81$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V3.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void set0x50SyncMenuInfo() {
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
    public void set0x51SyncMenuItemInfo(Context context, byte[] bArr) {
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

    private void set0x52SyncMediaTime() {
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            GeneralSyncData.mSyncTime = this.mDecimalFormat00.format(this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[4]);
            GeneralSyncData.mIsSyncTimeChange = true;
            updateSyncActivity(null);
        }
    }

    private void set0x53SyncPhoneTime() {
        if (SYNC_VERSION.VERSION_V3.equals(mSyncVersion)) {
            GeneralSyncData.mSyncTime = this.mDecimalFormat00.format(this.mCanBusInfoInt[2]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[3]) + ":" + this.mDecimalFormat00.format(this.mCanBusInfoInt[4]);
            GeneralSyncData.mIsSyncTimeChange = true;
            updateSyncActivity(null);
        }
    }

    private void set0x56SyncHostControl(Context context) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 32) {
            try {
                int i2 = iArr[4];
                if (i2 == 0 && iArr[5] == 0) {
                    int i3 = iArr[3];
                    if (i3 == 0) {
                        changeBandFm1();
                    } else if (i3 == 1) {
                        changeBandFm2();
                    } else if (i3 == 2) {
                        changeBandAm1();
                    } else if (i3 == 3) {
                        changeBandAm2();
                    }
                } else {
                    int data = getData(i2, iArr[5]);
                    if (isBandFm(this.mRadioCurrentBand)) {
                        FutureUtil.instance.setCurrentFreq(Float.toString(DataHandleUtils.rangeNumber(data, 8750, 10800) / 100.0f));
                    } else {
                        FutureUtil.instance.setCurrentFreq(Integer.toString(DataHandleUtils.rangeNumber(data, 522, 1710)));
                    }
                }
                return;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return;
            }
        }
        if (i != 34 && i != 35) {
            switch (i) {
                case 16:
                    realKeyClick(context, 130);
                    break;
                case 17:
                    switch (iArr[3]) {
                        case 1:
                        case 2:
                            FutureUtil.instance.playMpeg();
                            break;
                        case 3:
                            FutureUtil.instance.shuffleMpeg();
                            break;
                        case 4:
                            FutureUtil.instance.repeatMpeg();
                            break;
                        case 5:
                            FutureUtil.instance.prevMpeg();
                            break;
                        case 6:
                            FutureUtil.instance.nextMpeg();
                            break;
                    }
                case 18:
                    FutureUtil futureUtil = FutureUtil.instance;
                    MpegConstantsDef.K_SELECT k_select = MpegConstantsDef.K_SELECT.TITLE_SELECT;
                    int[] iArr2 = this.mCanBusInfoInt;
                    futureUtil.discGoto(k_select, getData(iArr2[3], iArr2[4]));
                    break;
            }
            return;
        }
        realKeyClick4(context, DataHandleUtils.rangeNumber(iArr[3], 1, 6) + 32);
    }

    private void set0x28ActiveParkData(final Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            this.mTimerUtil.stopTimer();
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._81.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public final void callback() {
                    this.f$0.m927lambda$set0x28ActiveParkData$0$comhzbhdcanbuscar_81MsgMgr(context);
                }
            });
        }
    }

    /* renamed from: lambda$set0x28ActiveParkData$0$com-hzbhd-canbus-car-_81-MsgMgr, reason: not valid java name */
    /* synthetic */ void m927lambda$set0x28ActiveParkData$0$comhzbhdcanbuscar_81MsgMgr(Context context) {
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
                    this.mTimerUtil.startTimer(new TimerTask(context, activeParkItem) { // from class: com.hzbhd.canbus.car._81.MsgMgr.1
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

    private void set0x29VehicleInfo() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 2 && isDataChange(iArr)) {
            String str = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]) ? "mph" : "km/h";
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            DecimalFormat decimalFormat = this.mDecimalFormat0p00;
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 0, sb.append(decimalFormat.format(getData(iArr2[4], iArr2[5]) / 100.0f)).append(" ").append(str).toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 1, sb2.append(getData(iArr3[6], iArr3[7])).append(" rpm").toString()));
            StringBuilder sb3 = new StringBuilder();
            DecimalFormat decimalFormat2 = this.mDecimalFormat0p00;
            int[] iArr4 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 2, sb3.append(decimalFormat2.format(getData(iArr4[8], iArr4[9]) / 100.0f)).append(" rad/s").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3])) {
                DecimalFormat decimalFormat3 = this.df_2Integer;
                int[] iArr5 = this.mCanBusInfoInt;
                updateSpeedInfo(Integer.parseInt(decimalFormat3.format((getData(iArr5[4], iArr5[5]) / 100) * 1.6d)));
            } else {
                DecimalFormat decimalFormat4 = this.df_2Integer;
                int[] iArr6 = this.mCanBusInfoInt;
                updateSpeedInfo(Integer.parseInt(decimalFormat4.format(getData(iArr6[4], iArr6[5]) / 100)));
            }
        }
    }

    private void set0x35TrackData(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, Priority.DEBUG_INT, 16);
            updateParkUi(null, context);
        }
    }

    private void set0x48VehicleSetting() {
        setSettingData(new String[]{"_176_car_setting_title_21"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4))});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        Log.i("_81_MSG", "dateTimeRepCanbus: " + this.mVersionInfo);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        if (this.mIsDiscExsit != i4) {
            this.mIsDiscExsit = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) (7 - i4)});
        }
        if (i4 == 1) {
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        this.mRadioCurrentBand = str;
        str.hashCode();
        switch (str) {
            case "AM1":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 3});
                break;
            case "AM2":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 4});
                break;
            case "FM1":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 1});
                break;
            case "FM2":
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 2});
                break;
        }
    }

    private void initData(Context context) {
        this.mAirUnit = getTempUnitC(context);
        this.mParkPageUiSet = getUiMgr(context).getParkPageUiSet(context);
        this.mPanoramicView = (MyPanoramicView) getUiMgr(context).getCusPanoramicView(context);
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._81.MsgMgr.2
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                int i = message.what;
                if (i == 10) {
                    MsgMgr.this.mActiveParkView.setAlertText((String) message.obj);
                    MsgMgr.this.mPanoramicView.setAlertMessage((String) message.obj);
                } else if (i == 11 && SystemUtil.isForeground((Context) message.obj, WarningActivity.class.getName())) {
                    MsgMgr.this.finishActivity();
                }
            }
        };
        this.mDataHandler = new DataHandler(context, 0L, 32L);
        this.m0x05ItemTitleArray = new String[0];
    }

    private void realKeyClick2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private void realKeyClick3_1(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[3];
        if (i2 == 0) {
            return;
        }
        realKeyClick3_1(context, i, iArr[2], i2);
    }

    private void realKeyClick3_2(Context context, int i) {
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[3];
        if (i2 == 0) {
            return;
        }
        realKeyClick3_2(context, i, iArr[2], i2);
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
            realKeyClick2(context, i3);
        }
        this.mKeyStatus = this.mCanBusInfoInt[3];
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

    private String resolveAirTemperature(int i) {
        if (i == 0) {
            return "LOW";
        }
        if (i == 127) {
            return "HIGH";
        }
        if (i < 31 || i > 59) {
            return "";
        }
        float f = i / 2.0f;
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((f * 9.0f) / 5.0f) + 32.0f) + this.mAirUnit;
        }
        return f + this.mAirUnit;
    }

    private String resolveOutdoorTemperature(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((i * 9) / 5.0f) + 32.0f) + this.mAirUnit;
        }
        return i + this.mAirUnit;
    }

    private void showWarningActivity(Context context) {
        this.mHandler.removeMessages(11);
        startWarningActivity(context);
        Message message = new Message();
        message.what = 11;
        message.obj = context;
        this.mHandler.sendMessageDelayed(message, 5000L);
    }

    void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    void updateSync(Bundle bundle) {
        updateSyncActivity(bundle);
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontStatus == GeneralDoorData.isLeftFrontDoorOpen && this.mRightFrontStatus == GeneralDoorData.isRightFrontDoorOpen && this.mLeftRearStatus == GeneralDoorData.isLeftRearDoorOpen && this.mRightRearStatus == GeneralDoorData.isRightRearDoorOpen && this.mBackStatus == GeneralDoorData.isBackOpen) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private void startSyncActivity(Context context) {
        Intent intent = new Intent(context, (Class<?>) SyncActivity.class);
        intent.setFlags(268435456);
        context.startActivity(intent);
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

    private void initSettingsItem(SettingPageUiSet settingPageUiSet) {
        Log.i("ljq", "initSettingsItem: ");
        this.mSettingItemIndeHashMap = new HashMap<>();
        for (int i = 0; i < settingPageUiSet.getList().size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = settingPageUiSet.getList().get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                this.mSettingItemIndeHashMap.put(itemList.get(i2).getTitleSrn(), Integer.valueOf(((i << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | i2));
            }
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

    private class DataHandler {
        private List<byte[]> bytes = new ArrayList();
        private int size;

        public DataHandler(final Context context, long j, long j2) {
            new Timer().schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._81.MsgMgr.DataHandler.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (DataHandler.this.size != 0) {
                        DataHandler.this.pop(context);
                    }
                }
            }, j, j2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void push(byte[] bArr) {
            this.bytes.add(bArr);
            this.size = this.bytes.size();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void pop(Context context) {
            if (this.bytes.get(0)[1] == 81) {
                MsgMgr.this.set0x51SyncMenuItemInfo(context, this.bytes.get(0));
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
}
