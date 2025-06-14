package com.hzbhd.canbus.car._85;

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
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.ParkPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int AMPLIFIER_BALANCE_OFFSET = 7;
    static final String SHARE_85_AIR_AUTO_MANUAL_STATUS = "share_85_air_auto_manual_status";
    static int[] mLanguageMapArray;
    static SYNC_VERSION mSyncVersion;
    private SparseArray<ActiveParkItem> mActiveParkItemArray;
    private ActiveParkView mActiveParkView;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private String mAirUnit;
    private boolean mAutoManualStatus;
    private boolean mBackStatus;
    private int mBacklight;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private DataHandler mDataHandler;
    private boolean mFrontStatus;
    private Handler mHandler;
    private ID3[] mId3s;
    private boolean mIsActiveViewOpen;
    private int mIsDiscExsit;
    private boolean mIsSyncNeedShowDialog;
    private int mKeyStatus;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mPanoramicStatus;
    private MyPanoramicView mPanoramicView;
    private ParkPageUiSet mParkPageUiSet;
    private int mPreWindLevel;
    private String mRadioCurrentBand;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private HashMap<String, AbstractSettingEntityProvider> mSettingItemIndeHashMap;
    private SparseIntArray mSyncMenuIconResIdArray;
    private SparseIntArray mTopIconArray;
    private UiMgr mUiMgr;
    private String mVersionInfo;
    private WindowManager mWindowManager;
    private final String TAG = "_85_MSG";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;
    private final long DATA_HANDLE_PERIOD = 32;
    private final int BACKLIGHT_DATA_MAX = 256;
    private final int BACKLIGHT_SEGMENTS = 5;
    private final int MSG_ACTIVE_PARK_0X1C_COUNT_DOWN = 10;
    private final int MSG_DISMISS_WARNING_ACTIVITY = 11;
    private final int SEND_GIVEN_MEDIA_MESSAGE = 101;
    private final int SEND_NORMAL_MESSAGE = 102;
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

    public MsgMgr() {
        mLanguageMapArray = new int[]{2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 18, 22, 27};
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
        initID3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._85.MsgMgr.1
            final Iterator<byte[]> iterator = Arrays.stream(new byte[][]{new byte[]{22, -127, 1, 1}, new byte[]{22, -58, -94, 6}, new byte[]{22, -58, -64, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -58, -63, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -58, -62, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -58, -61, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -58, -60, (byte) (GeneralAmplifierData.leftRight + 7)}}).iterator();

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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        SelectCanTypeUtil.enableApp(context, Constant.SyncActivity);
        initData(context);
        initSettingsItem(getUiMgr(context).getSettingUiSet(context));
        initActivePark(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mAutoManualStatus = SharePreUtil.getBoolValue(context, SHARE_85_AIR_AUTO_MANUAL_STATUS, false);
        getUiMgr(context).getOnAirAutoManualChangeListener().onChange(this.mAutoManualStatus);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            Log.i("_85_MSG", "canbusInfoChange: 0x14");
            return;
        }
        if (i == 22) {
            set0x16VehicleSpeed();
            return;
        }
        if (i == 32) {
            set0x20WheelKey(context);
            return;
        }
        if (i == 48) {
            set0x30VersionInfo(context);
            return;
        }
        if (i == 64) {
            set0x40SyncVersion();
            return;
        }
        if (i == 86) {
            set0x56SyncHostControl(context);
            return;
        }
        if (i == 72) {
            set0x48VehicleSetting(context);
            return;
        }
        if (i == 73) {
            set0x49PanoramicStatus();
            return;
        }
        if (i == 120) {
            set0x78SyncStatus(context);
            return;
        }
        if (i != 121) {
            switch (i) {
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
                            set0x29AirInfo(context);
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
        setBacklightLevel(i + 1);
    }

    private void set0x16VehicleSpeed() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, sb.append((iArr[3] * 256) + iArr[2]).append(" km/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo((iArr2[3] * 256) + iArr2[2]);
    }

    private void set0x20WheelKey(Context context) {
        int i = this.mCanBusInfoInt[2];
        if (i == 9) {
            realKeyClick2(context, 14);
            return;
        }
        if (i == 10) {
            realKeyClick2(context, 15);
            return;
        }
        if (i == 86) {
            realKeyClick2(context, 94);
            return;
        }
        if (i == 87) {
            dealSyncKey(context, 6, -1, HotKeyConstant.K_CAN_CONFIG);
            return;
        }
        if (i == 240) {
            realKeyClick3_1(context, 7);
            return;
        }
        if (i != 241) {
            switch (i) {
                case 0:
                    realKeyClick2(context, 0);
                    break;
                case 1:
                    realKeyClick2(context, 7);
                    break;
                case 2:
                    realKeyClick2(context, 8);
                    break;
                case 3:
                    realKeyClick2(context, 46);
                    break;
                case 4:
                    realKeyClick2(context, 45);
                    break;
                case 5:
                    realKeyClick2(context, 14);
                    break;
                case 6:
                    realKeyClick2(context, 3);
                    break;
                case 7:
                    realKeyClick2(context, 2);
                    break;
                default:
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
                                case 61:
                                    realKeyClick2(context, HotKeyConstant.K_TIME);
                                    break;
                                case 63:
                                    realKeyClick2(context, HotKeyConstant.K_SLEEP);
                                    break;
                                case 104:
                                    realKeyClick2(context, 50);
                                    break;
                                case 106:
                                    realKeyClick2(context, 58);
                                    break;
                                case 111:
                                    realKeyClick2(context, 59);
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
                                            dealSyncKey(context, 2, -1, 52);
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
                                                    dealSyncKey(context, 12, -1, 49);
                                                    break;
                                                case 73:
                                                    dealSyncKey(context, 25, -1, 47);
                                                    break;
                                                case 74:
                                                    dealSyncKey(context, 26, -1, 48);
                                                    break;
                                                case 75:
                                                    dealSyncKey(context, 10, -1, 45);
                                                    break;
                                                case 76:
                                                    dealSyncKey(context, 11, -1, 46);
                                                    break;
                                                default:
                                                    switch (i) {
                                                        case 82:
                                                            realKeyClick2(context, HotKeyConstant.K_UP_PICKUP);
                                                            break;
                                                        case 83:
                                                            realKeyClick2(context, HotKeyConstant.K_DOWN_HANGUP);
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
                                                                case 96:
                                                                    realKeyClick3_2(context, 48);
                                                                    break;
                                                                case 97:
                                                                    realKeyClick3_2(context, 47);
                                                                    break;
                                                                case 98:
                                                                    realKeyClick2(context, 17);
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
        realKeyClick3_1(context, 8);
    }

    private void set0x29AirInfo(Context context) {
        int airWhat = getAirWhat();
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        if (GeneralAirData.fahrenheit_celsius) {
            this.mAirUnit = getTempUnitF(context);
        } else {
            this.mAirUnit = getTempUnitC(context);
        }
        GeneralAirData.auto_manual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        updateOutDoorTemp(context, resolveOutdoorTemperature(this.mCanBusInfoByte[7]));
        if (this.mAutoManualStatus ^ GeneralAirData.auto_manual) {
            boolean z = GeneralAirData.auto_manual;
            this.mAutoManualStatus = z;
            SharePreUtil.setBoolValue(context, SHARE_85_AIR_AUTO_MANUAL_STATUS, z);
            getUiMgr(context).getOnAirAutoManualChangeListener().onChange(this.mAutoManualStatus);
        }
        byte[] bArr = this.mCanBusInfoByte;
        bArr[3] = (byte) (bArr[3] & 239);
        bArr[6] = (byte) (bArr[6] & 247);
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
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        if (GeneralAirData.auto) {
            GeneralAirData.front_left_blow_window = false;
            GeneralAirData.front_left_blow_head = false;
            GeneralAirData.front_left_blow_foot = false;
        } else {
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        }
        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        if (GeneralAirData.auto_manual) {
            GeneralAirData.front_left_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = resolveAirTemperature(context, this.mCanBusInfoInt[8]);
        } else {
            GeneralAirData.front_left_temperature = resolveManualAirTemperature(context, this.mCanBusInfoInt[4]);
            GeneralAirData.front_right_temperature = GeneralAirData.front_left_temperature;
        }
        GeneralAirData.rear_temperature = resolveRearAirTemperature(context, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4));
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 3);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[6]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.front_auto_wind_speed = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_auto_wind = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 2, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 2);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
        if (airWhat == 0 && this.mPreWindLevel != GeneralAirData.front_wind_level) {
            airWhat = 3;
        }
        this.mPreWindLevel = GeneralAirData.front_wind_level;
        updateAirActivity(context, airWhat);
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

    private void set0x24BaseInfo(Context context) {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(context);
        }
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = 0;
        iArr[3] = iArr[3] & 224;
        if (isDataChange(iArr)) {
            setSettingData(new String[]{"ford_range_unit"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))});
            String str = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]) ? "open" : "close";
            String str2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) ? "_81_max_vol_half" : "_81_unlimit";
            String str3 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]) ? "_103_car_setting_value_7_1" : "_103_car_setting_value_7_0";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 1, CommUtil.getStrByResId(context, str)));
            arrayList.add(new DriverUpdateEntity(0, 2, CommUtil.getStrByResId(context, str2)));
            arrayList.add(new DriverUpdateEntity(0, 3, CommUtil.getStrByResId(context, str3)));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new PanoramicBtnUpdateEntity(0, !DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])));
            arrayList2.add(new PanoramicBtnUpdateEntity(1, DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5])));
            GeneralParkData.dataList = arrayList2;
            updateParkUi(null, context);
        }
    }

    private void set0x25RadarStatus(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            String str = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) ? "open" : "close";
            String str2 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) ? "open" : "close";
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 4, CommUtil.getStrByResId(context, str)));
            arrayList.add(new DriverUpdateEntity(0, 5, CommUtil.getStrByResId(context, str2)));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void set0x30VersionInfo(Context context) {
        Log.i("_85_MSG", "set0x30VersionInfo: ");
        String versionStr = getVersionStr(this.mCanBusInfoByte);
        this.mVersionInfo = versionStr;
        updateVersionInfo(context, versionStr);
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

    private void set0x40SyncVersion() {
        if (mSyncVersion != SYNC_VERSION.values()[this.mCanBusInfoInt[2]]) {
            mSyncVersion = SYNC_VERSION.values()[this.mCanBusInfoInt[2]];
            int i = AnonymousClass4.$SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION[mSyncVersion.ordinal()];
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

    /* renamed from: com.hzbhd.canbus.car._85.MsgMgr$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION;

        static {
            int[] iArr = new int[SYNC_VERSION.values().length];
            $SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION = iArr;
            try {
                iArr[SYNC_VERSION.VERSION_V1.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V2.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hzbhd$canbus$car$_85$MsgMgr$SYNC_VERSION[SYNC_VERSION.VERSION_V3.ordinal()] = 3;
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
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._85.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public final void callback() {
                    this.f$0.m933lambda$set0x28ActiveParkData$0$comhzbhdcanbuscar_85MsgMgr(context);
                }
            });
        }
    }

    /* renamed from: lambda$set0x28ActiveParkData$0$com-hzbhd-canbus-car-_85-MsgMgr, reason: not valid java name */
    /* synthetic */ void m933lambda$set0x28ActiveParkData$0$comhzbhdcanbuscar_85MsgMgr(Context context) {
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
                    this.mTimerUtil.startTimer(new TimerTask(context, activeParkItem) { // from class: com.hzbhd.canbus.car._85.MsgMgr.2
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

    private void set0x48VehicleSetting(Context context) {
        if (isDataChange(this.mCanBusInfoInt)) {
            setSettingData(new String[]{"_176_car_setting_title_21", "_85_driver_seat_massage_backrest", "_85_driver_seat_massage_cushion", "_85_driver_seat_adjust_upper_plus", "_85_driver_seat_adjust_upper_minus", "_85_driver_seat_adjust_middle_plus", "_85_driver_seat_adjust_middle_minus", "_85_driver_seat_adjust_lower_plus", "_85_driver_seat_adjust_lower_minus", "_85_passenger_seat_massage_backrest", "_85_passenger_seat_massage_cushion", "_85_passenger_seat_adjust_upper_plus", "_85_passenger_seat_adjust_upper_minus", "_85_passenger_seat_adjust_middle_plus", "_85_passenger_seat_adjust_middle_minus", "_85_passenger_seat_adjust_lower_plus", "_85_passenger_seat_adjust_lower_minus"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4))});
            int[] iArr = this.mCanBusInfoInt;
            if (iArr.length > 10) {
                setSettingData(new String[]{"speed_linkage_volume_adjustment", "_85_sound_mode"}, new Object[]{Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(iArr[10], 6, 2)), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[10], 0, 1))});
                GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 4);
                GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4);
                GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 4);
                GeneralAmplifierData.frontRear = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 4, 4) - 7;
                GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4) - 7;
                saveAmplifierData(context, this.mCanId);
                updateAmplifierActivity(null);
            }
        }
    }

    private void set0x49PanoramicStatus() {
        if (isDataChange(this.mCanBusInfoInt)) {
            boolean z = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
            if (this.mPanoramicStatus != z) {
                this.mPanoramicStatus = z;
                forceReverse(this.mContext, z);
            }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        Log.i("_85_MSG", "dateTimeRepCanbus: " + this.mVersionInfo);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
        int i11 = z2 ? 128 : 0;
        if (z) {
            i5 = i8;
        } else {
            i11 |= 64;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -56, (byte) i5, (byte) i6, (byte) i11});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            sendNormalMessage(new byte[]{22, -64, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifier(this.mContext);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:4:0x005d  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10) {
        /*
            r5 = this;
            r9 = 4
            byte[] r10 = new byte[r9]
            r10 = {x00d0: FILL_ARRAY_DATA , data: [22, -64, 1, 1} // fill-array
            com.hzbhd.commontools.SourceConstantsDef$SOURCE_ID r0 = com.hzbhd.commontools.SourceConstantsDef.SOURCE_ID.FM
            int r0 = r0.ordinal()
            r5.sendMediaMessage(r0, r10)
            int r10 = r5.getband(r7)
            int[] r8 = r5.getFreqByteHiLo(r7, r8)
            r0 = 6
            byte[] r0 = new byte[r0]
            r1 = 22
            r2 = 0
            r0[r2] = r1
            r1 = -62
            r3 = 1
            r0[r3] = r1
            byte r10 = (byte) r10
            r1 = 2
            r0[r1] = r10
            r10 = r8[r3]
            byte r10 = (byte) r10
            r4 = 3
            r0[r4] = r10
            r8 = r8[r2]
            byte r8 = (byte) r8
            r0[r9] = r8
            byte r6 = (byte) r6
            r8 = 5
            r0[r8] = r6
            com.hzbhd.commontools.SourceConstantsDef$SOURCE_ID r6 = com.hzbhd.commontools.SourceConstantsDef.SOURCE_ID.FM
            int r6 = r6.ordinal()
            r5.sendMediaMessage(r6, r0)
            r6 = 8
            byte[] r6 = new byte[r6]
            r6 = {x00d6: FILL_ARRAY_DATA , data: [22, -61, 0, 0, 0, 0, 0, 0} // fill-array
            com.hzbhd.commontools.SourceConstantsDef$SOURCE_ID r8 = com.hzbhd.commontools.SourceConstantsDef.SOURCE_ID.FM
            int r8 = r8.ordinal()
            r5.sendMediaMessage(r8, r6)
            r5.mRadioCurrentBand = r7
            r7.hashCode()
            int r6 = r7.hashCode()
            r8 = -1
            switch(r6) {
                case 64901: goto L80;
                case 64902: goto L75;
                case 69706: goto L6a;
                case 69707: goto L5f;
                default: goto L5d;
            }
        L5d:
            r2 = r8
            goto L89
        L5f:
            java.lang.String r6 = "FM2"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L68
            goto L5d
        L68:
            r2 = r4
            goto L89
        L6a:
            java.lang.String r6 = "FM1"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L73
            goto L5d
        L73:
            r2 = r1
            goto L89
        L75:
            java.lang.String r6 = "AM2"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L7e
            goto L5d
        L7e:
            r2 = r3
            goto L89
        L80:
            java.lang.String r6 = "AM1"
            boolean r6 = r7.equals(r6)
            if (r6 != 0) goto L89
            goto L5d
        L89:
            switch(r2) {
                case 0: goto La8;
                case 1: goto L9f;
                case 2: goto L96;
                case 3: goto L8d;
                default: goto L8c;
            }
        L8c:
            goto Lb0
        L8d:
            byte[] r6 = new byte[r9]
            r6 = {x00de: FILL_ARRAY_DATA , data: [22, -58, -80, 2} // fill-array
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
            goto Lb0
        L96:
            byte[] r6 = new byte[r9]
            r6 = {x00e4: FILL_ARRAY_DATA , data: [22, -58, -80, 1} // fill-array
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
            goto Lb0
        L9f:
            byte[] r6 = new byte[r9]
            r6 = {x00ea: FILL_ARRAY_DATA , data: [22, -58, -80, 4} // fill-array
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
            goto Lb0
        La8:
            byte[] r6 = new byte[r9]
            r6 = {x00f0: FILL_ARRAY_DATA , data: [22, -58, -80, 3} // fill-array
            com.hzbhd.canbus.CanbusMsgSender.sendMsg(r6)
        Lb0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._85.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -64, 2, 16});
        if (i6 == 1) {
            i3 = i4;
        }
        int iRangeNumber = DataHandleUtils.rangeNumber(i3, 99);
        int[] time = getTime(i);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), new byte[]{22, -61, 0, (byte) iRangeNumber, 0, 0, (byte) time[1], (byte) time[2]}, 40L);
        this.mId3s[0].info = str;
        this.mId3s[1].info = str2;
        this.mId3s[2].info = str3;
        reportID3Info(this.mId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -64, 7, 48});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), new byte[]{22, -61, 0, 0, 0, 0, 0, 0}, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -64, 8, 16});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), new byte[]{22, -61, 0, (byte) DataHandleUtils.rangeNumber((b7 * 256) + i, 99), 0, 0, b4, b5}, 40L);
        this.mId3s[0].info = str4;
        this.mId3s[1].info = str5;
        this.mId3s[2].info = str6;
        reportID3Info(this.mId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -64, 8, 16});
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), new byte[]{22, -61, 0, (byte) DataHandleUtils.rangeNumber((b6 * 256) + i, 99), 0, 0, b4, b5}, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendNormalMessage(new byte[]{22, -64, 11, 0});
        sendNormalMessage(new byte[]{22, -59, 102, 1, 0, 0}, 80L);
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, bArr), 34), 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        sendNormalMessage(new byte[]{22, -64, 11, 0});
        int[] time = getTime(i);
        sendNormalMessage(new byte[]{22, -59, 102, 2, (byte) time[1], (byte) time[2]}, 80L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendNormalMessage(new byte[]{22, -64, 11, 0});
        sendNormalMessage(new byte[]{22, -59, 102, 3, 0, 0}, 80L);
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, bArr), 34), 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        sendNormalMessage(new byte[]{22, -59, 102, 4, 0, 0});
        sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 1, 1}, " ".getBytes()), 34), 160L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        if (!z) {
            sendNormalMessage(new byte[]{22, -59, 102, 0, 0, 0});
        } else if (i == 0) {
            sendNormalMessage(new byte[]{22, -59, 102, 4, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        if (this.mIsDiscExsit != i4) {
            this.mIsDiscExsit = i4;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) (7 - i4)});
            if (i4 == 1) {
                CanbusMsgSender.sendMsg(new byte[]{22, -58, -80, 0});
            }
        }
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        int i2 = iArr[6];
        int[] iArr2 = {iArr[2], iArr[3] & NfDef.STATE_3WAY_M_HOLD, iArr[4], i2 & 246, iArr[8], iArr[9]};
        int[] iArr3 = {iArr[5] & 143, i2 & 1};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 2);
        return i;
    }

    private int[] getTime(int i) {
        int i2 = i / 60;
        return new int[]{i2 / 60, i2 % 60, i % 60};
    }

    private int getband(String str) {
        if ("FM1".equals(str)) {
            return 1;
        }
        if ("FM2".equals(str)) {
            return 2;
        }
        return str.contains("AM") ? 16 : 1;
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

    private void initData(final Context context) {
        this.mAirUnit = getTempUnitC(context);
        this.mParkPageUiSet = getUiMgr(context).getParkPageUiSet(context);
        this.mPanoramicView = (MyPanoramicView) getUiMgr(context).getCusPanoramicView(context);
        this.mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.car._85.MsgMgr.3
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

    private String resolveAirTemperature(Context context, int i) {
        if (i == 30 || i == 119) {
            return "LO";
        }
        if (i == 60 || i == 171) {
            return "HI";
        }
        if (i <= 30 || i >= 60) {
            return (i <= 119 || i >= 171) ? "" : (i / 2) + getTempUnitF(context);
        }
        return (i / 2.0f) + getTempUnitC(context);
    }

    private String resolveManualAirTemperature(Context context, int i) {
        if (i == 0) {
            return CommUtil.getStrByResId(context, "_85_coldest");
        }
        if (i == 15) {
            return CommUtil.getStrByResId(context, "middle");
        }
        if (i == 30) {
            return CommUtil.getStrByResId(context, "_85_hottest");
        }
        if (i <= 0 || i >= 15) {
            return (i <= 15 || i >= 30) ? "" : CommUtil.getStrByResId(context, "heating") + " " + (i - 15);
        }
        return CommUtil.getStrByResId(context, "refrigeration") + " " + (15 - i);
    }

    private String resolveRearAirTemperature(Context context, int i) {
        if (i == 9) {
            return "";
        }
        if (i < 4) {
            if (i == 0) {
                return CommUtil.getStrByResId(context, "_85_coldest");
            }
            return CommUtil.getStrByResId(context, "refrigeration") + " " + (4 - i);
        }
        if (i == 4) {
            return CommUtil.getStrByResId(context, "middle");
        }
        if (i <= 4) {
            return "";
        }
        if (i == 8) {
            return CommUtil.getStrByResId(context, "_85_hottest");
        }
        return CommUtil.getStrByResId(context, "heating") + " " + (i - 4);
    }

    private String resolveOutdoorTemperature(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((i * 9) / 5.0f) + 32.0f) + this.mAirUnit;
        }
        return i + this.mAirUnit;
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
        this.mSettingItemIndeHashMap = new HashMap<>();
        List<SettingPageUiSet.ListBean> list = settingPageUiSet.getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = itemList.get(i2);
                String titleSrn = itemListBean.getTitleSrn();
                if (itemListBean.getStyle() == 2) {
                    this.mSettingItemIndeHashMap.put(titleSrn, new SettingProgressEntityProvider(titleSrn, i, i2, itemListBean.getMin()));
                } else {
                    this.mSettingItemIndeHashMap.put(titleSrn, new SettingNormalEntityProvider(titleSrn, i, i2));
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
        Log.i("_85_MSG", "setSettingData: Unequal length");
    }

    private abstract class AbstractSettingEntityProvider {
        private int left;
        private int right;
        private String title;

        public abstract SettingUpdateEntity getEntity(Object obj);

        public AbstractSettingEntityProvider(String str, int i, int i2) {
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

    private class SettingProgressEntityProvider extends AbstractSettingEntityProvider {
        private int min;

        public SettingProgressEntityProvider(String str, int i, int i2, int i3) {
            super(str, i, i2);
            this.min = i3;
        }

        @Override // com.hzbhd.canbus.car._85.MsgMgr.AbstractSettingEntityProvider
        public SettingUpdateEntity getEntity(Object obj) {
            return new SettingUpdateEntity(getLeft(), getRight(), obj).setProgress(((Integer) obj).intValue() - this.min);
        }
    }

    private class SettingNormalEntityProvider extends AbstractSettingEntityProvider {
        public SettingNormalEntityProvider(String str, int i, int i2) {
            super(str, i, i2);
        }

        @Override // com.hzbhd.canbus.car._85.MsgMgr.AbstractSettingEntityProvider
        public SettingUpdateEntity getEntity(Object obj) {
            return new SettingUpdateEntity(getLeft(), getRight(), obj);
        }
    }

    private class DataHandler {
        private List<byte[]> bytes = new ArrayList();
        private int size;

        public DataHandler(final Context context, long j, long j2) {
            new Timer().schedule(new TimerTask() { // from class: com.hzbhd.canbus.car._85.MsgMgr.DataHandler.1
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
            Log.i("ljq", "sendMediaMessage: mHandler is null");
        } else {
            handler.sendMessageDelayed(handler.obtainMessage(101, i, 0, obj), j);
        }
    }

    private class ID3 {
        private int command;
        private String info;
        private String record;

        private ID3(int i) {
            this.command = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            recordId3Info();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }
    }

    private void initID3() {
        this.mId3s = new ID3[]{new ID3(3), new ID3(4), new ID3(5)};
    }

    private void reportID3Info(ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(id32.command, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, String str) {
        try {
            sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -53, 17, (byte) i}, DataHandleUtils.exceptBOMHead(str.getBytes("unicode"))), 34), (i - 3) * 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
