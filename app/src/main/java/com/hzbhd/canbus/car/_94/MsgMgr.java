package com.hzbhd.canbus.car._94;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.SyncActivity;
import com.hzbhd.canbus.car._94.ActivePark;
import com.hzbhd.canbus.car._94.MsgMgr;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralSyncData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.ui_set.SyncAction;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.disc.MpegConstantsDef;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.share.ShareDataManager;
import com.hzbhd.util.LogUtil;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;




public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_FADE_OFFSET = 7;
    private static final int INVALID_VALUE = -1;
    private static final int MSG_DISMISS_WARNING_ACTIVITY = 11;
    private static final int RADAR_RANGE = 31;
    public static final String SHARE_94_VEHICLE_CONFIG = "share_94_vehicle_config";
    public static final String TAG = "_1094_MsgMgr";
    public static final int VEHICLE_TYPE_19_TERRITORY = 13;
    public static final int VEHICLE_TYPE_20_ESCAPE = 22;
    public static final int VEHICLE_TYPE_EXPLORER = 14;
    private int allLsb;
    private int allMsb;
    private ActivePark mActivePark;
    private Context mContext;
    private int mDiscExist;
    private int mReversingValues;
    private UiMgr mUiMgr;
    private int nowLsb;
    private int nowMsb;
    private int number;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[] mLanguageIndexs = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 14, 15, 18, 19, 20, 21, 22, 23, 25, 26, 27, 28, 29, 31};
    private static final int[] m0xC90x21ItemValues = {0, 10, 20, 120};
    private static final int[] m0xC90x26ItemValues = {0, 1, 2};
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mDifferent = -1;
    private int mCanId = -1;
    private int mEachId = -1;
    private int mConfiguration = -1;
    private byte[] m0x61DataRecord = {0, 0, 0, 0};
    private final byte[] mPhoneInfoCommand = {22, -57, 0, 0, 0, 0};
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private final SparseArray<ActivePark.ActiveParkBeam> mActiveParkBeamArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final SeatData driver = new SeatData();
    private final SeatData passenger = new SeatData();
    private final Id3[] mId3Array = {new Id3(1), new Id3(2)};
    private final TimerUtil mId3Timer = new TimerUtil();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final List<PanoramicBtnUpdateEntity> listGeneralParkData = new ArrayList();
    private final List<Integer> listGeneralParkBigData = CollectionsKt.arrayListOf(0, 0, 0, 0, 0, 0);

    private final int getLsb(int data) {
        return ((data & 65535) >> 0) & 255;
    }

    private final int getMsb(int data) {
        return ((data & 65535) >> 8) & 255;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getMsbLsbResult(int MSB, int LSB) {
        return ((MSB & 255) << 8) | (LSB & 255);
    }

    /* compiled from: MsgMgr.kt */
    
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int[] getMLanguageIndexs() {
            return MsgMgr.mLanguageIndexs;
        }

        public final int[] getM0xC90x21ItemValues() {
            return MsgMgr.m0xC90x21ItemValues;
        }

        public final int[] getM0xC90x26ItemValues() {
            return MsgMgr.m0xC90x26ItemValues;
        }
    }

    public final int getMReversingValues() {
        return this.mReversingValues;
    }

    public final byte[] getM0x61DataRecord() {
        return this.m0x61DataRecord;
    }

    public final SeatData getDriver() {
        return this.driver;
    }

    public final SeatData getPassenger() {
        return this.passenger;
    }

    public final int getNumber() {
        return this.number;
    }

    public final void setNumber(int i) {
        this.number = i;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        int currentEachCanId = getCurrentEachCanId();
        this.mEachId = currentEachCanId;
        if (currentEachCanId != 3) {
            SelectCanTypeUtil.enableApp(context, Constant.SyncActivity);
        }

        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mConfiguration = SharePreUtil.getIntValue(context, SHARE_94_VEHICLE_CONFIG, 0);
        GeneralTireData.isHaveSpareTire = false;
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
        initActivePark();
        GeneralSyncData.mInfoLineShowAmount = 5;
        GeneralSyncData.mIsShowSoftKey = true;
        GeneralSyncData.mSyncTopIconCount = 9;
        GeneralSyncData.mSyncTopIconResIdArray = new int[GeneralSyncData.mSyncTopIconCount];
        GeneralSyncData.mSyncTime = "";
        this.mActivePark = getActivePark(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        new TimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._94.MsgMgr.afterServiceNormalSetting.1
            private final byte[] vehicleTypeCommand;

            {
                this.vehicleTypeCommand = new byte[]{22, -111, (byte) MsgMgr.this.mEachId, (byte) MsgMgr.this.mConfiguration};
            }

            public final byte[] getVehicleTypeCommand() {
                return this.vehicleTypeCommand;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.setNumber(msgMgr.getNumber() + 1);
                if (MsgMgr.this.getNumber() > 5) {
                    return;
                }
                this.vehicleTypeCommand[3] = (byte) MsgMgr.this.mConfiguration;
                CanbusMsgSender.sendMsg(this.vehicleTypeCommand);
            }
        }, 0L, 1000L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        initAmplifier(context);
    }

    private final void initAmplifier(Context context) {
        if (context != null) {
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        }
        final Iterator it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -127, 1}, new byte[]{22, -58, -94, 6, 0}, new byte[]{22, -61, 0, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -61, 1, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -61, 2, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -61, 3, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -61, 4, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -62, (byte) GeneralAmplifierData.volume}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initAmplifier$2$1$1
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (it.hasNext()) {
                    CanbusMsgSender.sendMsg(it.next());
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        super.sourceSwitchNoMediaInfoChange(isPowerOff);
        if (isPowerOff) {
            return;
        }
        initAmplifier(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        Parser parser = this.mParserArray.get(byteArrayToIntArray[1]);
        if (parser != null) {
            parser.parse(this.mCanbusInfoInt.length - 2);
        }
    }

    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.append(20, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$1
            {
                super(this.this$0, "【0x14】背光调节信息");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                MsgMgr msgMgr = this.this$0;
                int i2 = msgMgr.mCanbusInfoInt[2];
                if (1 <= i2 && i2 < 52) {
                    msgMgr.setBacklightLevel(1);
                    return;
                }
                if (52 <= i2 && i2 < 103) {
                    msgMgr.setBacklightLevel(2);
                    return;
                }
                if (103 <= i2 && i2 < 154) {
                    msgMgr.setBacklightLevel(3);
                    return;
                }
                if (154 <= i2 && i2 < 205) {
                    msgMgr.setBacklightLevel(4);
                    return;
                }
                if (205 <= i2 && i2 < 256) {
                    msgMgr.setBacklightLevel(5);
                }
            }
        });
        sparseArray.put(32, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$2
            private int normalKeyStatus;
            private int syncKeyStatus;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x20】方向盘按键");
            }

            /* JADX WARN: Removed duplicated region for block: B:108:0x0317  */
            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void parse(int r17) {
                /*
                    Method dump skipped, instructions count: 1038
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$2.parse(int):void");
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }

            private final void realKeyClick31(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_1(context, value, msgMgr.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }

            private final void realKeyClick32(int value) {
                if (this.this$0.mCanbusInfoInt[3] == 0) {
                    return;
                }
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyClick3_2(context, value, msgMgr.mCanbusInfoInt[2], this.this$0.mCanbusInfoInt[3]);
            }

            private final void switchSyncActivity() {
                if (this.this$0.mCanbusInfoInt[3] == 1) {
                    if (SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
                        this.this$0.realKeyClick(context, 50);
                        return;
                    }
                    Context context2 = context;
                    Intent intent = new Intent();
                    intent.setComponent(Constant.SyncActivity);
                    intent.setFlags(268435456);
                    context2.startActivity(intent);
                }
            }

            private final void switchPanoramic() {
                this.this$0.forceReverse(context, ShareDataManager.getInstance().getInt(ShareConstants.SHARE_MISC_REVERSE) == 0);
            }

            private final void dealSyncKey(int shortCmd) {
                dealSyncKey(shortCmd, -1, 0);
            }

            private final void dealSyncKey(int shortCmd, int longCmd, int key) {
                if (SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
                    if (this.syncKeyStatus == 1) {
                        if (this.this$0.mCanbusInfoInt[3] != 0) {
                            if (this.this$0.mCanbusInfoInt[3] == 2 && longCmd != -1) {
                                CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) longCmd});
                            }
                        } else {
                            CanbusMsgSender.sendMsg(new byte[]{22, -58, -95, (byte) shortCmd});
                        }
                    }
                } else {
                    realKeyLongClick1(key);
                }
                this.syncKeyStatus = this.this$0.mCanbusInfoInt[3];
            }
        });
        sparseArray.append(33, new MsgMgr$initParsers$1$3(context, this));
        sparseArray.append(34, new MsgMgr$initParsers$1$4(this, context));
        sparseArray.append(35, new MsgMgr$initParsers$1$5(this, context));
        sparseArray.append(36, new MsgMgr$initParsers$1$6(this, context));
        sparseArray.append(37, new MsgMgr$initParsers$1$7(this, context));
        sparseArray.append(38, new MsgMgr$initParsers$1$8(this));
        sparseArray.append(39, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$9
            {
                super(this.this$0, "【0x27】语言设置状态");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    MsgMgr msgMgr = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr2 = this.this$0;
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) msgMgr2.mSettingItemIndexHashMap.get("language_setup");
                    if (settingUpdateEntity != null) {
                        arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(ArraysKt.indexOf(MsgMgr.INSTANCE.getMLanguageIndexs(), msgMgr2.mCanbusInfoInt[2]))));
                    }
                    msgMgr.updateGeneralSettingData(arrayList);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(40, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$10
            {
                super(this.this$0, "【0x28】设置项状态");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                Object obj;
                if (isDataChange()) {
                    MsgMgr msgMgr = this.this$0;
                    ArrayList arrayList = new ArrayList();
                    MsgMgr msgMgr2 = this.this$0;
                    HashMap map = msgMgr2.mSettingItemIndexHashMap;
                    int iIndexOf = 2;
                    int i = msgMgr2.mCanbusInfoInt[2];
                    if (i == 48) {
                        obj = "_94_electric_trunk";
                    } else if (i == 64) {
                        obj = "_94_automatic_folding";
                    } else if (i == 144) {
                        obj = "_94_park_lock_control";
                    } else if (i == 160) {
                        obj = "_94_passenger_airbag";
                    } else if (i == 176) {
                        obj = "_94_hill_descent_control";
                    } else if (i != 177) {
                        switch (i) {
                            case 0:
                                obj = "_94_lane_keeping_mode";
                                break;
                            case 1:
                                obj = "_94_warning_intensity";
                                break;
                            case 2:
                                obj = "_94_reverse_gear_incoming_car_warning";
                                break;
                            case 3:
                                obj = "_94_tsc_control";
                                break;
                            case 4:
                                obj = "_94_cruise_control";
                                break;
                            case 5:
                                obj = "_94_automatic_engine_shutdown";
                                break;
                            case 6:
                                obj = "_94_esp_state";
                                break;
                            case 7:
                                obj = "_94_remote_control";
                                break;
                            case 8:
                                obj = "_94_auto_hold";
                                break;
                            case 9:
                                obj = "_94_lane_centring";
                                break;
                            case 10:
                                obj = "_94_sensitivity_assist";
                                break;
                            case 11:
                                obj = "_94_pre_collision_assist_drive";
                                break;
                            case 12:
                                obj = "_94_evasive_steering_assist";
                                break;
                            case 13:
                                obj = "_94_reverse_brake_assist";
                                break;
                            case 14:
                                obj = "_94_parking_sensirs";
                                break;
                            default:
                                switch (i) {
                                    case 16:
                                        obj = "_94_distance_prompt";
                                        break;
                                    case 17:
                                        obj = "_94_active_braking";
                                        break;
                                    case 18:
                                        obj = "_94_sensitivity";
                                        break;
                                    case 19:
                                        obj = "_94_blind_spot_monitoring";
                                        break;
                                    case 20:
                                        obj = "_94_fatigue_driving_warning";
                                        break;
                                    default:
                                        switch (i) {
                                            case 32:
                                                obj = "_94_atmosphere_lamp";
                                                break;
                                            case 33:
                                                obj = "_94_headlight_delay";
                                                break;
                                            case 34:
                                                obj = "_94_automatic_high_beam";
                                                break;
                                            case 35:
                                                obj = "_334_day_light";
                                                break;
                                            case 36:
                                                obj = "_250_i_went_home_with";
                                                break;
                                            case 37:
                                                obj = "_94_indoor_lamp";
                                                break;
                                            case 38:
                                                obj = "_94_welcome_lamp";
                                                break;
                                            case 39:
                                                obj = "_94_fog_light_steering_assist";
                                                break;
                                            default:
                                                switch (i) {
                                                    case 80:
                                                        obj = "_94_switch_prohibited";
                                                        break;
                                                    case 81:
                                                        obj = "_94_voice_feedback";
                                                        break;
                                                    case 82:
                                                        obj = "_94_false_lock_warning";
                                                        break;
                                                    case 83:
                                                        obj = "_94_remote_unlock";
                                                        break;
                                                    case 84:
                                                        obj = "_94_automatically_unlock";
                                                        break;
                                                    case 85:
                                                        obj = "_94_remote_control_on";
                                                        break;
                                                    case 86:
                                                        obj = "_94_remote_control_off";
                                                        break;
                                                    case 87:
                                                        obj = "_94_activate_remote_start";
                                                        break;
                                                    case 88:
                                                        obj = "_94_air_conditioning_control";
                                                        break;
                                                    case 89:
                                                        obj = "_94_steering_air";
                                                        break;
                                                    case 90:
                                                        obj = "_94_cycle";
                                                        break;
                                                    case 91:
                                                        obj = "_94_speed_lock";
                                                        break;
                                                    case 92:
                                                        obj = "_94_one_click";
                                                        break;
                                                    case 93:
                                                        obj = "_94_key_detection_alert";
                                                        break;
                                                    case 94:
                                                        obj = "_94_auto_lock";
                                                        break;
                                                    case 95:
                                                        obj = "_94_key_free";
                                                        break;
                                                    case 96:
                                                        obj = "_94_rain_sensing_wiper";
                                                        break;
                                                    case 97:
                                                        obj = "_94_repeat_wiper_once";
                                                        break;
                                                    case 98:
                                                        obj = "_94_rear_wiper";
                                                        break;
                                                    case 99:
                                                        obj = "_94_auto_wiper";
                                                        break;
                                                    case 100:
                                                        obj = "_94_automatic_maintenance";
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 112:
                                                                obj = "_94_tire_pressure_unit";
                                                                break;
                                                            case 113:
                                                                obj = "_94_measure_unit";
                                                                break;
                                                            case 114:
                                                                obj = "_94_temperature_unit";
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case 192:
                                                                        msgMgr2.refreshPanoramicParkUi(msgMgr2.mCanbusInfoInt[3]);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case 193:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 1);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case 194:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 2);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case 195:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 3);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case HotKeyConstant.K_TIME /* 196 */:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 4);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case HotKeyConstant.K_TTM /* 197 */:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 5);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    case HotKeyConstant.K_CARPLAY_SIRI /* 198 */:
                                                                        msgMgr2.refreshPanoramicParkUiBig(msgMgr2.mCanbusInfoInt, 6);
                                                                        obj = Unit.INSTANCE;
                                                                        break;
                                                                    default:
                                                                        obj = "";
                                                                        break;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                    } else {
                        obj = "_94_rear_drive_acceleration_lock";
                    }
                    SettingUpdateEntity settingUpdateEntity = (SettingUpdateEntity) map.get(obj);
                    if (settingUpdateEntity != null) {
                        int i2 = msgMgr2.mCanbusInfoInt[2];
                        if (i2 == 33) {
                            iIndexOf = ArraysKt.indexOf(MsgMgr.INSTANCE.getM0xC90x21ItemValues(), msgMgr2.mCanbusInfoInt[3]);
                        } else if (i2 == 38) {
                            iIndexOf = ArraysKt.indexOf(MsgMgr.INSTANCE.getM0xC90x26ItemValues(), msgMgr2.mCanbusInfoInt[3]);
                        } else if (i2 != 113) {
                            iIndexOf = msgMgr2.mCanbusInfoInt[3];
                        } else {
                            int i3 = msgMgr2.mCanbusInfoInt[3];
                            if (i3 == 2) {
                                iIndexOf = 1;
                            } else if (i3 != 3) {
                                iIndexOf = 0;
                            }
                        }
                        arrayList.add(settingUpdateEntity.setValue(Integer.valueOf(iIndexOf)));
                    }
                    msgMgr.updateGeneralSettingData(arrayList);
                    this.this$0.updateSettingActivity(null);
                }
            }
        });
        sparseArray.append(41, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$11
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x29】转角");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int trackAngle0 = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[3], this.this$0.mCanbusInfoByte[2], 0, 540, 16);
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    if (GeneralParkData.trackAngle != trackAngle0) {
                        GeneralParkData.trackAngle = trackAngle0;
                        msgMgr.updateParkUi(null, context2);
                    }
                }
            }
        });
        sparseArray.append(42, new MsgMgr$initParsers$1$12(this));
        sparseArray.append(48, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$13
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x30】版本信息");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        sparseArray.append(49, new MsgMgr$initParsers$1$14(this));
        sparseArray.append(50, new MsgMgr$initParsers$1$15(this, context));
        sparseArray.append(64, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$16
            {
                super(this.this$0, "【0x40】SYNC 版本");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                StringBuilder sbAppend = new StringBuilder().append("parse: SYNC_VERSION_");
                int i = this.this$0.mCanbusInfoInt[2];
                Log.i(MsgMgr.TAG, sbAppend.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? "" : "V3" : "V2" : "V1" : "NONE").toString());
            }
        });
        sparseArray.append(80, new MsgMgr$initParsers$1$17(this));
        sparseArray.append(81, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$18
            private final List<byte[]> datasBuffer;
            private byte[] datasRecord;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x51】SYNC 菜单项信息");
                this.datasBuffer = new ArrayList();
                new TimerUtil().startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$18.1
                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        List list = MsgMgr$initParsers$1$18.this.datasBuffer;
                        MsgMgr$initParsers$1$18 msgMgr$initParsers$1$18 = MsgMgr$initParsers$1$18.this;
                        if (list.size() != 0) {
                            msgMgr$initParsers$1$18.parse((byte[]) CollectionsKt.first(list));
                            list.remove(CollectionsKt.first(list));
                        }
                    }
                }, 0L, 32L);
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                this.datasBuffer.add(this.this$0.mCanbusInfoByte);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Removed duplicated region for block: B:46:0x011b  */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void parse(byte[] r17) {
                /*
                    Method dump skipped, instructions count: 332
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$18.parse(byte[]):void");
            }

            private final boolean isDataChange(byte[] datas) {
                if (Arrays.equals(this.datasRecord, datas)) {
                    return false;
                }
                byte[] bArrCopyOf = Arrays.copyOf(datas, datas.length);

                this.datasRecord = bArrCopyOf;
                return true;
            }

            private final String getSyncInfo(byte[] bytes) {
                if (bytes.length < 8) {
                    return "";
                }
                int length = bytes.length;
                int i = 7;
                int progressionLastElement = ProgressionUtilKt.getProgressionLastElement(7, bytes.length, 2);
                if (7 <= progressionLastElement) {
                    while (true) {
                        int i2 = i - 1;
                        if (bytes[i2] != 0 || bytes[i] != 0) {
                            if (i == progressionLastElement) {
                                break;
                            }
                            i += 2;
                        } else {
                            length = i2;
                            break;
                        }
                    }
                }
                byte[] bArrCopyOfRange = ArraysKt.copyOfRange(bytes, 6, length);
                Charset charsetForName = Charset.forName("unicode");

                return new String(bArrCopyOfRange, charsetForName);
            }
        });
        sparseArray.append(82, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$19
            {
                super(this.this$0, "【0x52】SYNC 曲目播放时间信息");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int[] iArr = this.this$0.mCanbusInfoInt;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3]), Integer.valueOf(iArr[4])}, 3));

                    GeneralSyncData.mSyncTime = str;
                    GeneralSyncData.mIsSyncTimeChange = true;
                    this.this$0.updateSyncActivity(null);
                }
            }
        });
        sparseArray.append(83, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$20
            {
                super(this.this$0, "【0x53】SYNC 电话通话时间信息");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int[] iArr = this.this$0.mCanbusInfoInt;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format("%02d:%02d:%02d", Arrays.copyOf(new Object[]{Integer.valueOf(iArr[2]), Integer.valueOf(iArr[3]), Integer.valueOf(iArr[4])}, 3));

                    GeneralSyncData.mSyncTime = str;
                    GeneralSyncData.mIsSyncTimeChange = true;
                    this.this$0.updateSyncActivity(null);
                }
            }
        });
        sparseArray.append(85, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$21
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x55】SYNC指令1");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 0) {
                        this.this$0.enterNoSource();
                        this.this$0.realKeyClick(context, 52);
                        return;
                    }
                    if (i != 1) {
                        if (i != 2) {
                            return;
                        }
                        this.this$0.realKeyClick(context, 130);
                        return;
                    }
                    if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.FM) {
                        this.this$0.realKeyClick(context, 62);
                    }
                    int i2 = this.this$0.mCanbusInfoInt[3];
                    if (i2 == 1) {
                        this.this$0.changeBandAm1();
                        return;
                    }
                    if (i2 == 2) {
                        this.this$0.changeBandAm2();
                    } else if (i2 == 17) {
                        this.this$0.changeBandFm1();
                    } else {
                        if (i2 != 18) {
                            return;
                        }
                        this.this$0.changeBandFm2();
                    }
                }
            }
        });
        sparseArray.append(86, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$22
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x56】SYNC指令2");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i = this.this$0.mCanbusInfoInt[2];
                    boolean z = false;
                    if (i == 1) {
                        int i2 = (this.this$0.mCanbusInfoInt[4] << 8) | this.this$0.mCanbusInfoInt[3];
                        MsgMgr msgMgr = this.this$0;
                        if (8750 <= i2 && i2 < 10801) {
                            msgMgr.setCurrentFreqFm(String.valueOf(i2 / 100));
                            return;
                        }
                        if (522 <= i2 && i2 < 1621) {
                            z = true;
                        }
                        if (z) {
                            msgMgr.setCurrentFreqAm(String.valueOf(i2));
                        }
                        return;
                    }
                    if (i == 2) {
                        int i3 = this.this$0.mCanbusInfoInt[3];
                        MsgMgr msgMgr2 = this.this$0;
                        Context context2 = context;
                        if (1 <= i3 && i3 < 7) {
                            z = true;
                        }
                        if (z) {
                            msgMgr2.realKeyClick(context2, i3 + 32);
                            return;
                        }
                        return;
                    }
                    if (i != 17) {
                        if (i != 18) {
                            return;
                        }
                        MsgMgr msgMgr3 = this.this$0;
                        Context context3 = context;
                        MpegConstantsDef.K_SELECT k_select = MpegConstantsDef.K_SELECT.TITLE_SELECT;
                        int i4 = this.this$0.mCanbusInfoInt[3];
                        msgMgr3.mpegDiscGoto(context3, k_select, i4 >= 1 ? i4 : 1);
                        return;
                    }
                    switch (this.this$0.mCanbusInfoInt[3]) {
                        case 1:
                        case 2:
                            this.this$0.mpegPlay(context);
                            break;
                        case 3:
                            this.this$0.mpegShuffle(context);
                            break;
                        case 4:
                            this.this$0.mpegRepeat(context);
                            break;
                        case 5:
                            this.this$0.mpegPrev(context);
                            break;
                        case 6:
                            this.this$0.mpegNext(context);
                            break;
                    }
                }
            }
        });
        sparseArray.append(96, new MsgMgr$initParsers$1$23(this, context));
        sparseArray.append(97, new MsgMgr$initParsers$1$24(this));
        sparseArray.append(98, new MsgMgr$initParsers$1$25(this, context));
        sparseArray.append(99, new MsgMgr$initParsers$1$26(this, context));
        sparseArray.append(105, new MsgMgr$initParsers$1$27(this));
        sparseArray.append(100, new MsgMgr$initParsers$1$28(this));
        sparseArray.append(101, new Parser(this) { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$29
            {
                super(this, "【0x65】保留原车CD原车源");
            }
        });
        sparseArray.append(102, new Parser(this) { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$30
            {
                super(this, "【0x66】保留原车CD的CD播放时间");
            }
        });
        sparseArray.append(103, new Parser(this) { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$31
            {
                super(this, "【0x67】保留原车CD的CD显状态");
            }
        });
        sparseArray.append(104, new Parser(this) { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$32
            {
                super(this, "【0x68】保留原车CD显示");
            }
        });
        sparseArray.append(120, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$33
            private final SparseIntArray topIconArray;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x78】SYNC 状态");
                SparseIntArray sparseIntArray = new SparseIntArray();
                this.topIconArray = sparseIntArray;
                sparseIntArray.put(0, R.drawable.ford_sync_icon);
                sparseIntArray.append(1, R.drawable.ford_sync_icon_204);
                sparseIntArray.append(3, R.drawable.ford_sync_icon_93);
                sparseIntArray.append(4, R.drawable.ford_sync_icon_130);
                sparseIntArray.append(5, R.drawable.ford_sync_icon_41);
                sparseIntArray.append(6, R.drawable.ford_sync_keyboard_info);
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int[] iArr = GeneralSyncData.mSyncTopIconResIdArray;
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    iArr[1] = getSyncIconResId(0);
                    iArr[2] = getSyncIconResId(1);
                    iArr[3] = getSyncIconResId(3);
                    iArr[4] = getSyncIconResId(4);
                    iArr[5] = getSyncIconResId(5);
                    iArr[6] = getSyncIconResId(6);
                    int i = msgMgr.mCanbusInfoInt[4] & 15;
                    int syncIconResId = R.drawable.ford_sync_icon_null;
                    iArr[7] = i > 4 ? R.drawable.ford_sync_icon_null : msgMgr.getSyncIconResId(context2, "ford_sync_icon_" + (i + 103));
                    int i2 = (msgMgr.mCanbusInfoInt[4] >> 4) & 15;
                    if (i2 <= 4) {
                        syncIconResId = msgMgr.getSyncIconResId(context2, "ford_sync_icon_" + (i2 + 97));
                    }
                    iArr[8] = syncIconResId;
                    this.this$0.updateSyncActivity(null);
                }
            }

            private final int getSyncIconResId(int index) {
                return ((this.this$0.mCanbusInfoInt[3] >> index) & 1) == 1 ? this.topIconArray.get(index) : R.drawable.ford_sync_null;
            }
        });
        sparseArray.append(121, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$34
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x79】SYNC 切换请求");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.this$0.mCanbusInfoInt[2];
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            if (i != 3 && i != 4) {
                                if (i != 5) {
                                    return;
                                }
                            }
                        }
                    }
                    Context context2 = context;
                    Intent intent = new Intent();
                    intent.setComponent(Constant.SyncActivity);
                    intent.setFlags(268435456);
                    context2.startActivity(intent);
                    return;
                }
                if (SystemUtil.isForeground(context, Reflection.getOrCreateKotlinClass(SyncActivity.class).getQualifiedName())) {
                    this.this$0.realKeyClick(context, 50);
                } else {
                    this.this$0.enterNoSource();
                }
            }
        });
        sparseArray.append(128, new Parser() { // from class: com.hzbhd.canbus.car._94.MsgMgr$initParsers$1$35
            private long realTime;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "【0x80】仪表控制源信息");
            }

            @Override // com.hzbhd.canbus.car._94.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    switch (this.this$0.mCanbusInfoInt[2]) {
                        case 1:
                            if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.FM) {
                                this.this$0.realKeyClick(context, 62);
                            }
                            int i = this.this$0.mCanbusInfoInt[3];
                            if (i == 0) {
                                this.this$0.changeBandFm1();
                            } else if (i == 1) {
                                this.this$0.changeBandFm2();
                            } else if (i == 2) {
                                this.this$0.changeBandFm3();
                            } else if (i == 3) {
                                String currentBand = this.this$0.getCurrentBand();

                                if (!StringsKt.contains$default((CharSequence) currentBand, (CharSequence) "FM", false, 2, (Object) null)) {
                                    this.this$0.changeBandFm1();
                                }
                                this.this$0.realKeyClick(context, 30);
                            } else if (i == 16) {
                                this.this$0.changeBandAm1();
                            } else if (i == 17) {
                                this.this$0.changeBandAm2();
                            } else if (i == 19) {
                                String currentBand2 = this.this$0.getCurrentBand();

                                if (!StringsKt.contains$default((CharSequence) currentBand2, (CharSequence) "AM", false, 2, (Object) null)) {
                                    this.this$0.changeBandAm1();
                                }
                                this.this$0.realKeyClick(context, 30);
                            }
                            int i2 = this.this$0.mCanbusInfoInt[4];
                            if (1 <= i2 && i2 < 7) {
                                MsgMgr msgMgr = this.this$0;
                                msgMgr.playPresetFreq(msgMgr.mCanbusInfoInt[4]);
                                break;
                            }
                            break;
                        case 2:
                            if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.MUSIC) {
                                this.this$0.realKeyClick(context, 59);
                                break;
                            }
                            break;
                        case 3:
                            int i3 = this.this$0.mCanbusInfoInt[3];
                            if (i3 == 0) {
                                this.this$0.realKeyClick(context, 15);
                                break;
                            } else if (i3 == 1) {
                                this.this$0.realKeyClick(context, 14);
                                break;
                            } else if (i3 == 3) {
                                this.this$0.realKeyClick(context, 135);
                                break;
                            } else if (i3 == 4) {
                                this.this$0.realKeyClick(context, 136);
                                break;
                            } else if (i3 == 5) {
                                this.this$0.realKeyClick(context, 137);
                                break;
                            } else if (i3 == 6) {
                                this.this$0.realKeyClick(context, 138);
                                break;
                            }
                            break;
                        case 4:
                            if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.BTAUDIO) {
                                this.this$0.realKeyClick(context, 140);
                                break;
                            }
                            break;
                        case 5:
                            int i4 = (this.this$0.mCanbusInfoInt[4] << 4) | this.this$0.mCanbusInfoInt[3];
                            MsgMgr msgMgr2 = this.this$0;
                            Context context2 = context;
                            if (i4 > 0) {
                                msgMgr2.mpegDiscGoto(context2, MpegConstantsDef.K_SELECT.TITLE_SELECT, i4);
                                break;
                            }
                            break;
                        case 7:
                            if (this.this$0.getSourceId() != SourceConstantsDef.SOURCE_ID.AUX1) {
                                this.this$0.realKeyClick(context, 141);
                                break;
                            }
                            break;
                        case 8:
                            this.this$0.realKeyClick(context, 128);
                            break;
                    }
                }
            }
        });
    }

    public final List<PanoramicBtnUpdateEntity> getListGeneralParkData() {
        return this.listGeneralParkData;
    }

    public final List<Integer> getListGeneralParkBigData() {
        return this.listGeneralParkBigData;
    }

    public final void refreshParkUi() {
        GeneralParkData.dataList = this.listGeneralParkData;
        updateParkUi(null, this.mContext);
    }

    public final void refreshPanoramicParkUi(int canInt) {
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(3, canInt == 0));
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(4, canInt == 1));
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(5, canInt == 2));
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(6, canInt == 3));
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(7, canInt == 4));
        refreshParkUi();
    }

    public final void refreshPanoramicParkUiBig(int[] canInt, int type) {

        if (LogUtil.log5()) {
            LogUtil.d("updateInitFinish: " + ArraysKt.joinToString$default(canInt, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null) + ' ' + type);
        }
        List<Integer> list = this.listGeneralParkBigData;
        int i = type - 1;
        list.set(i, Integer.valueOf(list.get(i).intValue() == 0 ? 1 : 0));
        this.listGeneralParkData.add(new PanoramicBtnUpdateEntity(type + 7, this.listGeneralParkBigData.get(i).intValue() == 1));
        refreshParkUi();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -126, (byte) bYear2Dig, (byte) bMonth, (byte) bDay, (byte) bHours24H, (byte) bMins, 0});
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._94.MsgMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m951dateTimeRepCanbus$lambda4();
            }
        }, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dateTimeRepCanbus$lambda-4, reason: not valid java name */
    public static final void m951dateTimeRepCanbus$lambda4() {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0011  */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void radioInfoChange(int r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, int r10) throws java.lang.NumberFormatException {
        /*
            r5 = this;
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.String r9 = "FM1"
            boolean r9 = r7.equals(r9)
            java.lang.String r10 = "AM"
            r0 = 0
            r1 = 1
            r2 = 2
            r3 = 0
            if (r9 == 0) goto L13
        L11:
            r9 = r3
            goto L35
        L13:
            java.lang.String r9 = "FM2"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L1d
            r9 = r1
            goto L35
        L1d:
            java.lang.String r9 = "FM3"
            boolean r9 = r7.equals(r9)
            if (r9 == 0) goto L27
            r9 = r2
            goto L35
        L27:
            r9 = r7
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            r4 = r10
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r9 = kotlin.text.StringsKt.contains$default(r9, r4, r3, r2, r0)
            if (r9 == 0) goto L11
            r9 = 16
        L35:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            java.lang.String r4 = "FM"
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            boolean r4 = kotlin.text.StringsKt.contains$default(r7, r4, r3, r2, r0)
            if (r4 == 0) goto L4e
            float r7 = java.lang.Float.parseFloat(r8)
            r8 = 100
            float r8 = (float) r8
            float r7 = r7 * r8
            int r7 = (int) r7
            goto L5a
        L4e:
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            boolean r7 = kotlin.text.StringsKt.contains$default(r7, r10, r3, r2, r0)
            if (r7 == 0) goto L92
            int r7 = java.lang.Integer.parseInt(r8)
        L5a:
            int[] r8 = new int[r2]
            r10 = r7 & 255(0xff, float:3.57E-43)
            r8[r3] = r10
            int r7 = r7 >> 8
            r7 = r7 & 255(0xff, float:3.57E-43)
            r8[r1] = r7
            android.content.Context r7 = r5.mContext
            com.hzbhd.commontools.SourceConstantsDef$SOURCE_ID r10 = com.hzbhd.commontools.SourceConstantsDef.SOURCE_ID.FM
            java.lang.String r10 = r10.name()
            r0 = 7
            byte[] r0 = new byte[r0]
            r4 = 22
            r0[r3] = r4
            r4 = -64
            r0[r1] = r4
            r0[r2] = r1
            r2 = 3
            byte r9 = (byte) r9
            r0[r2] = r9
            r9 = 4
            r2 = r8[r3]
            byte r2 = (byte) r2
            r0[r9] = r2
            r9 = 5
            r8 = r8[r1]
            byte r8 = (byte) r8
            r0[r9] = r8
            r8 = 6
            byte r6 = (byte) r6
            r0[r8] = r6
            r5.sendMediaMsg(r7, r10, r0)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._94.MsgMgr.radioInfoChange(int, java.lang.String, java.lang.String, java.lang.String, int):void");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte playModel, int currentTime, int playTitleNo, int position, int currentPlayNo, int currentTotalNo, int discType, boolean isUnMuted, boolean isPlaying, int playStat, String song, String album, String artist) {
        int i = (discType == 1 || discType == 5) ? currentPlayNo : position;
        int i2 = currentTime / 60;
        int[] iArr = {i2 / 60, i2 % 60, currentTime % 60};
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, (byte) ((i >> 8) & 255), (byte) (i & 255), (byte) ((currentTotalNo >> 8) & 255), (byte) (currentTotalNo & 255), (byte) iArr[0], (byte) iArr[1], (byte) iArr[2]});
        Id3 id3 = this.mId3Array[0];

        id3.setInfo(song);
        Id3 id32 = this.mId3Array[1];

        id32.setInfo(artist);
        this.nowMsb = getMsb(currentTime);
        this.nowLsb = getLsb(currentTime);
        this.allMsb = getMsb(playTitleNo);
        this.allLsb = getLsb(playTitleNo);
        reportId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        Id3 id3 = this.mId3Array[0];

        id3.setInfo(songTitle);
        Id3 id32 = this.mId3Array[1];

        id32.setInfo(songArtist);
        this.nowMsb = getMsb(currentPlayingIndexLow);
        this.nowLsb = getLsb(currentPlayingIndexLow);
        this.allMsb = getMsb(totalCount);
        this.allLsb = getLsb(totalCount);
        reportId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        super.videoInfoChange(stoagePath, playRatio, currentPlayingIndexLow, totalCount, currentHour, currentMinute, currentSecond, currentAllMinuteStr, currentPlayingIndexHigh, currentAllMinute, currentHourStr, currentMinuteStr, currentSecondStr, currentPos, playMode, isPlaying, duration);
        this.mId3Array[0].setInfo("USB Media");
        this.mId3Array[1].setInfo("Video Playing");
        this.nowMsb = getMsb(duration);
        this.nowLsb = getLsb(duration);
        this.allMsb = getMsb(totalCount);
        this.allLsb = getLsb(totalCount);
        reportId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {
        Id3 id3 = this.mId3Array[0];

        id3.setInfo(title);
        Id3 id32 = this.mId3Array[1];

        id32.setInfo(artist);
        this.nowMsb = 0;
        this.nowLsb = 0;
        this.allMsb = 0;
        this.allLsb = 0;
        reportId3();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        byte[] bArr = new byte[3];
        bArr[0] = 22;
        bArr[1] = -62;
        if (volValue > 30) {
            volValue = 30;
        }
        bArr[2] = (byte) volValue;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int btOn, int discRadom, int discRepeat, int discExsit, int sdCardIn, int usbIn, int radioSt, int mute, int singleCycle, int fullCurve, int folderLoop, int randomFolder) {
        if (this.mDiscExist != discExsit) {
            this.mDiscExist = discExsit;
            CanbusMsgSender.sendMsg(new byte[]{22, -58, -94, (byte) ((1 - discExsit) | 2 | 0), 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(final int callStatus, byte[] phoneNumber, final boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, final int batteryStatus, final int signalValue, Bundle bundle) {
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._94.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m949btPhoneStatusInfoChange$lambda14(this.f$0, signalValue, batteryStatus, callStatus, isHfpConnected);
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: btPhoneStatusInfoChange$lambda-14, reason: not valid java name */
    public static final void m949btPhoneStatusInfoChange$lambda14(MsgMgr this$0, int i, int i2, int i3, boolean z) {

        byte[] bArr = this$0.mPhoneInfoCommand;
        bArr[2] = (byte) (((i << 4) & NfDef.STATE_3WAY_M_HOLD) | (i2 & 15));
        if (i3 == 0) {
            if (!z) {
                bArr[2] = 0;
            }
            bArr[3] = z ? (byte) 1 : (byte) 0;
            bArr[4] = 0;
            bArr[5] = 0;
        }
        CanbusMsgSender.sendMsg(bArr);
        this$0.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._94.MsgMgr$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m950btPhoneStatusInfoChange$lambda14$lambda13();
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: btPhoneStatusInfoChange$lambda-14$lambda-13, reason: not valid java name */
    public static final void m950btPhoneStatusInfoChange$lambda14$lambda13() {
        CanbusMsgSender.sendMsg(new byte[]{22, -54, 3, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(final byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {

        byte[] bArr = this.mPhoneInfoCommand;
        bArr[3] = 2;
        CanbusMsgSender.sendMsg(bArr);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._94.MsgMgr$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m948btPhoneOutGoingInfoChange$lambda15(phoneNumber);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: btPhoneOutGoingInfoChange$lambda-15, reason: not valid java name */
    public static final void m948btPhoneOutGoingInfoChange$lambda15(byte[] phoneNumber) {

        String str = new String(phoneNumber, Charsets.UTF_8);
        Charset UTF_16 = StandardCharsets.UTF_16;

        byte[] bytes = str.getBytes(UTF_16);

        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 3}, bytes));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
        byte[] bArr = this.mPhoneInfoCommand;
        bArr[3] = 3;
        bArr[4] = (byte) ((time >> 8) & 255);
        bArr[5] = (byte) (time & 255);
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(final byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG) {

        byte[] bArr = this.mPhoneInfoCommand;
        bArr[3] = 2;
        CanbusMsgSender.sendMsg(bArr);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hzbhd.canbus.car._94.MsgMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.m947btPhoneIncomingInfoChange$lambda16(phoneNumber);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: btPhoneIncomingInfoChange$lambda-16, reason: not valid java name */
    public static final void m947btPhoneIncomingInfoChange$lambda16(byte[] phoneNumber) {

        String str = new String(phoneNumber, Charsets.UTF_8);
        Charset UTF_16 = StandardCharsets.UTF_16;

        byte[] bytes = str.getBytes(UTF_16);

        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, -54, 3}, bytes));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public boolean customLongClick(Context context, int key) {
        if (key != 187) {
            return false;
        }
        realKeyClick(context, 14);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    
    abstract class Parser {
        private final int PARSE_LISTENERS_LENGTH;
        private int[] canbusInfo;
        private final OnParseListener[] onParseListeners;
        final /* synthetic */ MsgMgr this$0;

        public Parser(MsgMgr msgMgr, String msg) {

            this.this$0 = msgMgr;
            OnParseListener[] onParseListeners = setOnParseListeners();
            this.onParseListeners = onParseListeners;
            int length = onParseListeners.length;
            this.PARSE_LISTENERS_LENGTH = length;
            Log.i(MsgMgr.TAG, "Parser: " + msg + " length " + length);
        }

        public final int[] getCanbusInfo() {
            return this.canbusInfo;
        }

        public final void setCanbusInfo(int[] iArr) {
            this.canbusInfo = iArr;
        }

        public void parse(int dataLength) {
            for (int iMin = Math.min(dataLength, this.PARSE_LISTENERS_LENGTH); iMin > 0; iMin--) {
                OnParseListener onParseListener = this.onParseListeners[iMin - 1];
                if (onParseListener != null) {
                    onParseListener.onParse();
                }
            }
        }

        public final boolean isDataChange() {
            if (Arrays.equals(this.canbusInfo, this.this$0.mCanbusInfoInt)) {
                return false;
            }
            int[] iArr = this.this$0.mCanbusInfoInt;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);

            this.canbusInfo = iArrCopyOf;
            return true;
        }

        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[0];
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setRadarData(Constant.RadarLocation direct, int data) {
        RadarInfoUtil.setGeneralRadarData(direct, data, 31);
    }

    private final void initItemsIndexHashMap(Context context) {
        Log.i(TAG, "initItems: ");
        UiMgr uiMgr = this.mUiMgr;
        if (uiMgr == null) {

            uiMgr = null;
        }
        Iterator<SettingPageUiSet.ListBean> it = uiMgr.getSettingUiSet(context).getList().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it.next().getItemList()) {
                HashMap<String, SettingUpdateEntity<Object>> map = this.mSettingItemIndexHashMap;
                String titleSrn = itemListBean.getTitleSrn();

                map.put(titleSrn, new SettingUpdateEntity<>(i, i3));
                i3++;
            }
            i = i2;
        }
        Iterator<DriverDataPageUiSet.Page> it2 = uiMgr.getDriverDataPageUiSet(context).getList().iterator();
        int i4 = 0;
        while (it2.hasNext()) {
            int i5 = i4 + 1;
            int i6 = 0;
            for (DriverDataPageUiSet.Page.Item item : it2.next().getItemList()) {
                HashMap<String, DriverUpdateEntity> map2 = this.mDriveItemIndexHashMap;
                String titleSrn2 = item.getTitleSrn();

                map2.put(titleSrn2, new DriverUpdateEntity(i4, i6, "null_value"));
                i6++;
            }
            i4 = i5;
        }
    }

    private final void initActivePark() {
        SparseArray<ActivePark.ActiveParkBeam> sparseArray = this.mActiveParkBeamArray;
        sparseArray.put(2, new ActivePark.ActiveParkBeam(R.string._81_active_park_02));
        sparseArray.append(3, new ActivePark.ActiveParkBeam(R.string._81_active_park_03));
        sparseArray.append(4, new ActivePark.ActiveParkBeam(R.string._81_active_park_04, null, Integer.valueOf(R.drawable.ford_pa_0x04), ActivePark.MyPanoramicView.RIGHT_RADAR));
        sparseArray.append(5, new ActivePark.ActiveParkBeam(R.string._81_active_park_05, Integer.valueOf(R.drawable.ford_pa_0x05), null, ActivePark.MyPanoramicView.LEFT_RADAR));
        sparseArray.append(6, new ActivePark.ActiveParkBeam(R.string._81_active_park_06));
        Integer numValueOf = Integer.valueOf(R.drawable._81_ford_active_park_07);
        sparseArray.append(7, new ActivePark.ActiveParkBeam(R.string._81_active_park_07, numValueOf, null, ActivePark.MyPanoramicView.LEFT_FORWARD));
        Integer numValueOf2 = Integer.valueOf(R.drawable._81_ford_active_park_08);
        sparseArray.append(8, new ActivePark.ActiveParkBeam(R.string._81_active_park_08, null, numValueOf2, ActivePark.MyPanoramicView.LEFT_FORWARD));
        sparseArray.append(9, new ActivePark.ActiveParkBeam(R.string._81_active_park_09, numValueOf, null, ActivePark.MyPanoramicView.LEFT_FORWARD));
        sparseArray.append(10, new ActivePark.ActiveParkBeam(R.string._81_active_park_0A, null, numValueOf2, ActivePark.MyPanoramicView.LEFT_FORWARD));
        Integer numValueOf3 = Integer.valueOf(R.drawable._81_ford_active_park_0b);
        sparseArray.append(11, new ActivePark.ActiveParkBeam(R.string._81_active_park_0B, numValueOf3, null, ActivePark.MyPanoramicView.LEFT_STOP));
        Integer numValueOf4 = Integer.valueOf(R.drawable._81_ford_active_park_0c);
        sparseArray.append(12, new ActivePark.ActiveParkBeam(R.string._81_active_park_0C, null, numValueOf4, ActivePark.MyPanoramicView.LEFT_STOP));
        sparseArray.append(13, new ActivePark.ActiveParkBeam(R.string._81_active_park_0D, numValueOf3, null, ActivePark.MyPanoramicView.LEFT_REVERSE));
        sparseArray.append(14, new ActivePark.ActiveParkBeam(R.string._81_active_park_0E, null, numValueOf4, ActivePark.MyPanoramicView.LEFT_REVERSE));
        Integer numValueOf5 = Integer.valueOf(R.drawable.ford_pa_0x0f);
        sparseArray.append(15, new ActivePark.ActiveParkBeam(R.string._81_active_park_0F, numValueOf5, null, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        Integer numValueOf6 = Integer.valueOf(R.drawable.ford_pa_0x10);
        sparseArray.append(16, new ActivePark.ActiveParkBeam(R.string._81_active_park_10, null, numValueOf6, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(17, new ActivePark.ActiveParkBeam(R.string._81_active_park_11, numValueOf5, null, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(18, new ActivePark.ActiveParkBeam(R.string._81_active_park_12, null, numValueOf6, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(19, new ActivePark.ActiveParkBeam(R.string._81_active_park_13, Integer.valueOf(R.drawable._81_ford_active_park_13), null, ActivePark.MyPanoramicView.LEFT_STOP));
        sparseArray.append(20, new ActivePark.ActiveParkBeam(R.string._81_active_park_14, null, Integer.valueOf(R.drawable._81_ford_active_park_14), ActivePark.MyPanoramicView.LEFT_STOP));
        Integer numValueOf7 = Integer.valueOf(R.drawable._81_ford_active_park_15);
        sparseArray.append(21, new ActivePark.ActiveParkBeam(R.string._81_active_park_15, numValueOf7, null, ActivePark.MyPanoramicView.LEFT_FORWARD));
        Integer numValueOf8 = Integer.valueOf(R.drawable._81_ford_active_park_16);
        sparseArray.append(22, new ActivePark.ActiveParkBeam(R.string._81_active_park_16, null, numValueOf8, ActivePark.MyPanoramicView.LEFT_FORWARD));
        sparseArray.append(23, new ActivePark.ActiveParkBeam(R.string._81_active_park_17, Integer.valueOf(R.drawable._81_ford_active_park_17), null, ActivePark.MyPanoramicView.LEFT_STOP));
        sparseArray.append(24, new ActivePark.ActiveParkBeam(R.string._81_active_park_18, null, Integer.valueOf(R.drawable._81_ford_active_park_18), ActivePark.MyPanoramicView.LEFT_STOP));
        Integer numValueOf9 = Integer.valueOf(R.drawable._81_ford_active_park_19);
        sparseArray.append(25, new ActivePark.ActiveParkBeam(R.string._81_active_park_19, numValueOf9, null, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        Integer numValueOf10 = Integer.valueOf(R.drawable._81_ford_active_park_1a);
        sparseArray.append(26, new ActivePark.ActiveParkBeam(R.string._81_active_park_1A, null, numValueOf10, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(27, new ActivePark.ActiveParkBeam(R.string._81_active_park_1B));
        sparseArray.append(28, new ActivePark.ActiveParkBeam(R.string._81_active_park_1C));
        sparseArray.append(29, new ActivePark.ActiveParkBeam(R.string._81_active_park_1D));
        sparseArray.append(30, new ActivePark.ActiveParkBeam(R.string._81_active_park_1E));
        sparseArray.append(31, new ActivePark.ActiveParkBeam(R.string._81_active_park_1F));
        sparseArray.append(32, new ActivePark.ActiveParkBeam(R.string._81_active_park_20));
        sparseArray.append(33, new ActivePark.ActiveParkBeam(R.string._81_active_park_21));
        sparseArray.append(34, new ActivePark.ActiveParkBeam(R.string._81_active_park_22));
        sparseArray.append(35, new ActivePark.ActiveParkBeam(R.string._81_active_park_15, numValueOf7, null, ActivePark.MyPanoramicView.LEFT_FORWARD));
        sparseArray.append(36, new ActivePark.ActiveParkBeam(R.string._81_active_park_16, null, numValueOf8, ActivePark.MyPanoramicView.LEFT_FORWARD));
        sparseArray.append(37, new ActivePark.ActiveParkBeam(R.string._81_active_park_19, numValueOf9, null, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(38, new ActivePark.ActiveParkBeam(R.string._81_active_park_1A, null, numValueOf10, ActivePark.MyPanoramicView.LEFT_BACKWARD));
        sparseArray.append(39, new ActivePark.ActiveParkBeam(R.string._81_active_park_1D));
    }

    public final ActivePark getActivePark(Context context) {

        if (this.mActivePark == null) {
            this.mActivePark = new ActivePark(context);
        }
        ActivePark activePark = this.mActivePark;

        return activePark;
    }

    public final void setConfiguration(Context context, int value) {

        this.mConfiguration = value;
        SharePreUtil.setIntValue(context, SHARE_94_VEHICLE_CONFIG, value);
    }

    public final void updateSettings(String title, Object value) {


        SettingUpdateEntity<Object> settingUpdateEntity = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity != null) {
            settingUpdateEntity.setValue(value);
            updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntity));
            updateSettingActivity(null);
        }
    }

    /* compiled from: MsgMgr.kt */
    
    public static final /* data */ class SeatData {
        private int backseat;
        private int cushion;
        private int high;
        private int low;
        private int middle;

        public static /* synthetic */ SeatData copy$default(SeatData seatData, int i, int i2, int i3, int i4, int i5, int i6, Object obj) {
            if ((i6 & 1) != 0) {
                i = seatData.high;
            }
            if ((i6 & 2) != 0) {
                i2 = seatData.middle;
            }
            int i7 = i2;
            if ((i6 & 4) != 0) {
                i3 = seatData.low;
            }
            int i8 = i3;
            if ((i6 & 8) != 0) {
                i4 = seatData.backseat;
            }
            int i9 = i4;
            if ((i6 & 16) != 0) {
                i5 = seatData.cushion;
            }
            return seatData.copy(i, i7, i8, i9, i5);
        }

        /* renamed from: component1, reason: from getter */
        public final int getHigh() {
            return this.high;
        }

        /* renamed from: component2, reason: from getter */
        public final int getMiddle() {
            return this.middle;
        }

        /* renamed from: component3, reason: from getter */
        public final int getLow() {
            return this.low;
        }

        /* renamed from: component4, reason: from getter */
        public final int getBackseat() {
            return this.backseat;
        }

        /* renamed from: component5, reason: from getter */
        public final int getCushion() {
            return this.cushion;
        }

        public final SeatData copy(int high, int middle, int low, int backseat, int cushion) {
            return new SeatData(high, middle, low, backseat, cushion);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SeatData)) {
                return false;
            }
            SeatData seatData = (SeatData) other;
            return this.high == seatData.high && this.middle == seatData.middle && this.low == seatData.low && this.backseat == seatData.backseat && this.cushion == seatData.cushion;
        }

        public int hashCode() {
            return (((((((Integer.hashCode(this.high) * 31) + Integer.hashCode(this.middle)) * 31) + Integer.hashCode(this.low)) * 31) + Integer.hashCode(this.backseat)) * 31) + Integer.hashCode(this.cushion);
        }

        public String toString() {
            return "SeatData(high=" + this.high + ", middle=" + this.middle + ", low=" + this.low + ", backseat=" + this.backseat + ", cushion=" + this.cushion + ')';
        }

        public SeatData(int i, int i2, int i3, int i4, int i5) {
            this.high = i;
            this.middle = i2;
            this.low = i3;
            this.backseat = i4;
            this.cushion = i5;
        }

        public final int getBackseat() {
            return this.backseat;
        }

        public final int getCushion() {
            return this.cushion;
        }

        public final int getHigh() {
            return this.high;
        }

        public final int getLow() {
            return this.low;
        }

        public final int getMiddle() {
            return this.middle;
        }

        public final void setBackseat(int i) {
            this.backseat = i;
        }

        public final void setCushion(int i) {
            this.cushion = i;
        }

        public final void setHigh(int i) {
            this.high = i;
        }

        public final void setLow(int i) {
            this.low = i;
        }

        public final void setMiddle(int i) {
            this.middle = i;
        }

        public SeatData() {
            this(0, 0, 0, 0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getSyncIconResId(Context context, String name) {
        int imgIdByResId = CommUtil.getImgIdByResId(context, name);
        return imgIdByResId == R.drawable.music_photo_null ? R.drawable.ford_sync_icon_null : imgIdByResId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */
    
    static final class Id3 {
        private String info = "";
        private boolean isInfoChange;
        private final int type;

        public Id3(int i) {
            this.type = i;
        }

        public final int getType() {
            return this.type;
        }

        public final String getInfo() {
            this.isInfoChange = false;
            return this.info;
        }

        public final void setInfo(String value) {

            if (!Intrinsics.areEqual(this.info, value)) {
                this.isInfoChange = true;
            }
            this.info = value;
        }

        /* renamed from: isInfoChange, reason: from getter */
        public final boolean getIsInfoChange() {
            return this.isInfoChange;
        }
    }

    private final void reportId3() {
        for (Id3 id3 : this.mId3Array) {
            if (id3.getIsInfoChange()) {
                final TimerUtil timerUtil = this.mId3Timer;
                timerUtil.stopTimer();
                timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._94.MsgMgr$reportId3$1$1
                    private int i;
                    private final int size;

                    {
                        this.size = this.this$0.mId3Array.length;
                    }

                    public final int getI() {
                        return this.i;
                    }

                    public final void setI(int i) {
                        this.i = i;
                    }

                    public final int getSize() {
                        return this.size;
                    }

                    @Override // java.util.TimerTask, java.lang.Runnable
                    public void run() {
                        if (this.i < this.size) {
                            MsgMgr.Id3[] id3Arr = this.this$0.mId3Array;
                            int i = this.i;
                            this.i = i + 1;
                            MsgMgr.Id3 id32 = id3Arr[i];
                            MsgMgr msgMgr = this.this$0;
                            byte[] bArr = {22, -63, (byte) id32.getType(), (byte) msgMgr.nowLsb, (byte) msgMgr.nowMsb, (byte) msgMgr.allLsb, (byte) msgMgr.allMsb};
                            String strStringFixLength = msgMgr.stringFixLength(id32.getInfo());
                            Charset UTF_16 = StandardCharsets.UTF_16;

                            byte[] bytes = strStringFixLength.getBytes(UTF_16);

                            CanbusMsgSender.sendMsg(ArraysKt.plus(bArr, ArraysKt.copyOfRange(bytes, 2, bytes.length)));
                            return;
                        }
                        timerUtil.stopTimer();
                    }
                }, 100L, 100L);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String stringFixLength(String value) {
        StringBuffer stringBuffer = new StringBuffer(value);
        if (stringBuffer.length() > 10) {
            return StringsKt.removeRange(stringBuffer, 10, stringBuffer.length()).toString();
        }
        for (int length = stringBuffer.length(); length < 10; length++) {
            stringBuffer.append(" ");
        }
        String string = stringBuffer.toString();

        return string;
    }

    public final void updateSync(Bundle bundle) {
        updateSyncActivity(bundle);
    }

    /* compiled from: MsgMgr.kt */
    
    public static final class SyncKey {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final Long num0 = new Long(13, 48);
        private static final Long num1 = new Long(14, 49);
        private static final Long num2 = new Long(15, 50);
        private static final Long num3 = new Long(16, 51);
        private static final Long num4 = new Long(17, 52);
        private static final Long num5 = new Long(18, 53);
        private static final Long num6 = new Long(19, 54);
        private static final Long num7 = new Long(20, 55);
        private static final Long num8 = new Long(21, 56);
        private static final Long num9 = new Long(22, 57);
        private static final Short star = new Short(23);
        private static final Short well = new Short(24);
        private static final Short k1 = new Short(28);
        private static final Short k2 = new Short(29);
        private static final Short k3 = new Short(30);
        private static final Short k4 = new Short(31);

        /* compiled from: MsgMgr.kt */
        
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final Long getNum0() {
                return SyncKey.num0;
            }

            public final Long getNum1() {
                return SyncKey.num1;
            }

            public final Long getNum2() {
                return SyncKey.num2;
            }

            public final Long getNum3() {
                return SyncKey.num3;
            }

            public final Long getNum4() {
                return SyncKey.num4;
            }

            public final Long getNum5() {
                return SyncKey.num5;
            }

            public final Long getNum6() {
                return SyncKey.num6;
            }

            public final Long getNum7() {
                return SyncKey.num7;
            }

            public final Long getNum8() {
                return SyncKey.num8;
            }

            public final Long getNum9() {
                return SyncKey.num9;
            }

            public final Short getStar() {
                return SyncKey.star;
            }

            public final Short getWell() {
                return SyncKey.well;
            }

            public final Short getK1() {
                return SyncKey.k1;
            }

            public final Short getK2() {
                return SyncKey.k2;
            }

            public final Short getK3() {
                return SyncKey.k3;
            }

            public final Short getK4() {
                return SyncKey.k4;
            }
        }

        /* compiled from: MsgMgr.kt */
        
        public static final /* data */ class Short {
            private final int short;

            public static /* synthetic */ Short copy$default(Short r0, int i, int i2, Object obj) {
                if ((i2 & 1) != 0) {
                    i = r0.short;
                }
                return r0.copy(i);
            }

            /* renamed from: component1, reason: from getter */
            public final int getShort() {
                return this.short;
            }

            public final Short copy(int i) {
                return new Short(i);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Short) && this.short == ((Short) other).short;
            }

            public int hashCode() {
                return Integer.hashCode(this.short);
            }

            public String toString() {
                return "Short(short=" + this.short + ')';
            }

            public Short(int i) {
                this.short = i;
            }

            public final int getShort() {
                return this.short;
            }
        }

        /* compiled from: MsgMgr.kt */
        
        public static final /* data */ class Long {
            private final int long;
            private final int short;

            public static /* synthetic */ Long copy$default(Long r0, int i, int i2, int i3, Object obj) {
                if ((i3 & 1) != 0) {
                    i = r0.short;
                }
                if ((i3 & 2) != 0) {
                    i2 = r0.long;
                }
                return r0.copy(i, i2);
            }

            /* renamed from: component1, reason: from getter */
            public final int getShort() {
                return this.short;
            }

            /* renamed from: component2, reason: from getter */
            public final int getLong() {
                return this.long;
            }

            public final Long copy(int i, int i2) {
                return new Long(i, i2);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof Long)) {
                    return false;
                }
                Long r5 = (Long) other;
                return this.short == r5.short && this.long == r5.long;
            }

            public int hashCode() {
                return (Integer.hashCode(this.short) * 31) + Integer.hashCode(this.long);
            }

            public String toString() {
                return "Long(short=" + this.short + ", long=" + this.long + ')';
            }

            public Long(int i, int i2) {
                this.short = i;
                this.long = i2;
            }

            public final int getLong() {
                return this.long;
            }

            public final int getShort() {
                return this.short;
            }
        }
    }
}
