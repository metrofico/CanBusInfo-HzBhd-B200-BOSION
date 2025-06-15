package com.hzbhd.canbus.car._3;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.SettingActivity;
import com.hzbhd.canbus.car._206.MqbHybirdActivity;
import com.hzbhd.canbus.car_cus._451.Interface.ActionCallback;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MediaShareData;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import nfore.android.bt.res.NfDef;


public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_BALANCE_DATA_HALF = 9;
    public static final float MILE_TO_KILO_RATE = 1.609344f;
    private static final String TAG = "_3_MsgMgr";
    private final byte[] m0xC7Command;
    private final byte[] m0xCACommand;
    private int[] mAirData;
    private UiMgr mUiMgr;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArrayList<Integer> languageList = CollectionsKt.arrayListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 16, 17, 18, 20, 22, 23, 26, 29, 30, 31, 32, 33, 37, 38, 39, 40, 41, 42, 43);
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private final Map<Integer, Parser> mParserMap = new LinkedHashMap<>();
    private final HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private final HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final int mColour = 1;
    private final byte[] m0xA6Command = {22, -90, 0, 0, 0, 0, 0, 0, 1};
    private final M0x70Sender m0x70Sender = new M0x70Sender();
    private final byte[] m0xC0Command = {22, -64, 0, 0, 0, 0, 0, 0, 0, 0};
    private final byte[] m0xC1Command = {22, -63, 0, 0};
    private final byte[] m0xC4Command = {22, -60, 0};
    private final byte[] m0xC5Command = {22, -59, 0, 0};

    public MsgMgr() {
        byte[] bArr = new byte[15];
        int i = 0;
        while (i < 15) {
            bArr[i] = i != 0 ? i != 1 ? i != 2 ? i != 3 ? (byte) 0 : (byte) 1 : (byte) 3 : (byte) -54 : (byte) 22;
            i++;
        }
        this.m0xCACommand = bArr;
        this.m0xC7Command = new byte[]{22, -57, 0, 0, 0};
        this.mAirData = new int[0];
    }

    /* compiled from: MsgMgr.kt */

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public ArrayList<Integer> getLanguageList() {
            return MsgMgr.languageList;
        }
    }

    public int getMColour() {
        return this.mColour;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        super.afterServiceNormalSetting(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        this.mUiMgr = (UiMgr) canUiMgr;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 255;
        RadarInfoUtil.mDisableData2 = 255;
        initItemsIndexHashMap(context);
        initParsers(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        Log.i(TAG, "initCommand: ");
        initAmplifier(context);
    }

    private void initAmplifier(Context context) {
        Log.i(TAG, "initAmplifier: context[" + context + ']');
        if (context != null) {
            int canId = getCanId();
            UiMgr uiMgr = this.mUiMgr;
            if (uiMgr == null) {

                uiMgr = null;
            }
            getAmplifierData(context, canId, uiMgr.getAmplifierPageUiSet(context));
        }
        final Iterator<byte[]> it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -127, 1}, this.m0xC4Command, new byte[]{22, -88, 0, (byte) GeneralAmplifierData.volume}, new byte[]{22, -88, 1, (byte) GeneralAmplifierData.bandTreble}, new byte[]{22, -88, 2, (byte) GeneralAmplifierData.bandMiddle}, new byte[]{22, -88, 3, (byte) GeneralAmplifierData.bandBass}, new byte[]{22, -88, 4, (byte) (GeneralAmplifierData.frontRear + 9)}, new byte[]{22, -88, 5, (byte) (GeneralAmplifierData.leftRight + 9)}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initAmplifier$2$1$1
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

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean isPowerOff) {
        Log.i(TAG, "sourceSwitchNoMediaInfoChange: isPowerOff[" + isPowerOff + ']');
        if (isPowerOff) {
            return;
        }
        initAmplifier(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void reverseStateChange(boolean isReverse) {
        super.reverseStateChange(isReverse);
        if (isReverse) {
            CycleRequest.getInstance().start(100, new ActionCallback() { // from class: com.hzbhd.canbus.car._3.MsgMgr$$ExternalSyntheticLambda0
                @Override // com.hzbhd.canbus.car_cus._451.Interface.ActionCallback
                public void toDo(Object obj) {
                    MsgMgr.m363reverseStateChange$lambda3(obj);
                }
            });
        } else {
            CycleRequest.getInstance().stop();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: reverseStateChange$lambda-3, reason: not valid java name */
    public static void m363reverseStateChange$lambda3(Object obj) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 41, 41});
        CycleRequest.getInstance().reset(100);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] canbusInfo) {

        super.canbusInfoChange(context, canbusInfo);
        int[] byteArrayToIntArray = getByteArrayToIntArray(canbusInfo);

        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = canbusInfo;
        try {
            Parser parser = this.mParserMap.get(Integer.valueOf(byteArrayToIntArray[1]));
            if (parser != null) {
                parser.parse(this.mCanbusInfoInt.length - 2);
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void initParsers(final Context context) {
        Map<Integer, Parser> map = this.mParserMap;
        map.put(32, new Parser(this, "【0x20】方向盘按键") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$1

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                switch (this.msgmgr.mCanbusInfoInt[2]) {
                    case 0:
                        realKeyLongClick1(0);
                        break;
                    case 1:
                        realKeyLongClick1(7);
                        break;
                    case 2:
                        realKeyLongClick1(8);
                        break;
                    case 3:
                        realKeyLongClick1(20);
                        break;
                    case 4:
                        realKeyLongClick1(21);
                        break;
                    case 5:
                        realKeyLongClick1(HotKeyConstant.K_PHONE_ON_OFF);
                        break;
                    case 6:
                        realKeyLongClick1(3);
                        break;
                    case 7:
                        realKeyLongClick1(2);
                        break;
                    case 8:
                        realKeyLongClick1(HotKeyConstant.K_SPEECH);
                        break;
                }
            }

            private void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.msgmgr;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }

            private void compoundKey(int... keys) {
                MsgMgr msgMgr = this.msgmgr;
                msgMgr.compoundKey(context, keys, msgMgr.mCanbusInfoInt[3]);
            }
        });
        map.put(33, new Parser(this, "【0x21】空调信息") {

            private int rearData;
            private int rearDataRecord;
            private int data8;
            private int[] frontData = new int[0];
            private final ArrayList<SettingUpdateEntity> list = new ArrayList<>();

            @Override
            public void parse(int dataLength) {
                if (dataLength > 8) {
                    if (this.data8 != msgmgr.mCanbusInfoInt[10]) {
                        this.data8 = msgmgr.mCanbusInfoInt[10];
                        this.list.clear();
                        SettingUpdateEntity settingUpdateEntity = msgmgr.mSettingItemIndexHashMap.get("_3_21h_d8_b7");
                        if (settingUpdateEntity != null) {
                            this.list.add(settingUpdateEntity.setValue(Integer.valueOf((msgmgr.mCanbusInfoInt[10] >> 7) & 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity2 = msgmgr.mSettingItemIndexHashMap.get("_3_21h_d8_b65");
                        if (settingUpdateEntity2 != null) {
                            this.list.add(settingUpdateEntity2.setValue(Integer.valueOf((msgmgr.mCanbusInfoInt[10] >> 5) & 3)));
                        }
                        msgmgr.updateGeneralSettingData(this.list);
                        msgmgr.updateSettingActivity(null);
                    }
                    msgmgr.mCanbusInfoInt[10] = 0;
                }
                msgmgr.mCanbusInfoInt[3] = msgmgr.mCanbusInfoInt[3] & 239;
                if (msgmgr.isAirDataChange()) {
                    super.parse(dataLength);
                    msgmgr.updateAirActivity(context, getWhat());
                }
            }

            private int getWhat() {
                if (!Arrays.equals(this.frontData, msgmgr.mCanbusInfoInt)) {
                    int[] iArr = msgmgr.mCanbusInfoInt;

                    this.frontData = Arrays.copyOf(iArr, iArr.length);
                    return 1004;
                }
                int i = this.rearDataRecord;
                int i2 = this.rearData;
                if (i == i2) {
                    return 0;
                }
                this.rearDataRecord = i2;
                return 1003;
            }

            private String toTemperature(int i) {
                if (i == 0) {
                    return "LO";
                }
                if (i == 31) {
                    return "HI";
                }
                if (!(1 <= i && i < 29)) {
                    return "";
                }
                if (GeneralAirData.fahrenheit_celsius) {
                    return String.valueOf(i + 59) + (char) 8457;
                }
                return String.format("%.1f℃", Arrays.copyOf(new Object[]{Float.valueOf((i + 31) / 2.0f)}, 1));
            }

            @Override
            public OnParseListener[] setOnParseListeners() {
                return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.power = ((msgmgr.mCanbusInfoInt[2] >> 7) & 1) == 1;
                        GeneralAirData.ac = ((msgmgr.mCanbusInfoInt[2] >> 6) & 1) == 1;
                        GeneralAirData.in_out_cycle = ((msgmgr.mCanbusInfoInt[2] >> 5) & 1) == 0;
                        GeneralAirData.auto_1_2 = (msgmgr.mCanbusInfoInt[2] >> 3) & 3;
                        GeneralAirData.dual = ((msgmgr.mCanbusInfoInt[2] >> 2) & 1) == 1;
                        GeneralAirData.max_front = ((msgmgr.mCanbusInfoInt[2] >> 1) & 1) == 1;
                        GeneralAirData.rear = (msgmgr.mCanbusInfoInt[2] & 1) == 1;
                        GeneralAirData.manual = GeneralAirData.auto_1_2 == 0 && !GeneralAirData.ac_max && !GeneralAirData.max_front;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda1
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.front_left_blow_window = ((msgmgr.mCanbusInfoInt[3] >> 7) & 1) == 1;
                        GeneralAirData.front_left_blow_head = ((msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1;
                        GeneralAirData.front_left_blow_foot = ((msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1;
                        GeneralAirData.front_wind_level = msgmgr.mCanbusInfoInt[3] & 15;
                        GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
                        GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
                        GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda2
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.front_left_temperature = toTemperature(msgmgr.mCanbusInfoInt[4]);
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda3
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.front_right_temperature = toTemperature(msgmgr.mCanbusInfoInt[5]);
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda4
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.front_defog = ((msgmgr.mCanbusInfoInt[6] >> 7) & 1) == 1;
                        GeneralAirData.rear_defog = ((msgmgr.mCanbusInfoInt[6] >> 6) & 1) == 1;
                        GeneralAirData.aqs = ((msgmgr.mCanbusInfoInt[6] >> 5) & 1) == 1;
                        GeneralAirData.eco = ((msgmgr.mCanbusInfoInt[6] >> 4) & 1) == 1;
                        GeneralAirData.ac_max = ((msgmgr.mCanbusInfoInt[6] >> 3) & 1) == 1;
                        GeneralAirData.clean_air = ((msgmgr.mCanbusInfoInt[6] >> 2) & 1) == 1;
                        GeneralAirData.fahrenheit_celsius = (msgmgr.mCanbusInfoInt[6] & 1) == 1;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda5
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.steering_wheel_heating = ((msgmgr.mCanbusInfoInt[7] >> 7) & 1) == 1;
                        GeneralAirData.front_left_seat_heat_level = (msgmgr.mCanbusInfoInt[7] >> 4) & 7;
                        GeneralAirData.front_right_seat_heat_level = msgmgr.mCanbusInfoInt[7] & 7;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda6
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.front_left_seat_cold_level = (msgmgr.mCanbusInfoInt[8] >> 6) & 3;
                        GeneralAirData.front_right_seat_cold_level = (msgmgr.mCanbusInfoInt[8] >> 4) & 3;
                        GeneralAirData.auto_wind_lv = msgmgr.mCanbusInfoInt[8] & 3;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$2$$ExternalSyntheticLambda7
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        int i = msgmgr.mCanbusInfoInt[9];
                        rearData = i;
                        GeneralAirData.rear_temperature = toTemperature(i);
                        msgmgr.mCanbusInfoInt[9] = 0;
                    }
                }};
            }
        });
        map.put(47, new Parser(this, "【0x2F】方向盘指令") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$3
            private int preFast;

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = this.msgmgr.mCanbusInfoInt[2];
                if (i == 1) {
                    realKeyClick4(45);
                }
                if (i == 2) {
                    realKeyClick4(46);
                    return;
                }
                if (i == 3) {
                    this.preFast = 22;
                    realKeyClick4(22);
                    return;
                }
                if (i == 4) {
                    this.preFast = 23;
                    realKeyClick4(23);
                    return;
                }
                if (i == 5) {
                    realKeyClick4(this.preFast);
                    return;
                }
                switch (i) {
                    case 17:
                        realKeyClick4(14);
                        break;
                    case 18:
                        realKeyClick4(15);
                        break;
                    case 19:
                        realKeyClick4(137);
                        break;
                    case 20:
                        realKeyClick4(0);
                        break;
                    case 21:
                        realKeyClick4(0);
                        break;
                    case 22:
                        realKeyClick4(135);
                        break;
                    case 23:
                        realKeyClick4(136);
                        break;
                    case 24:
                        realKeyClick4(137);
                        break;
                    case 25:
                        realKeyClick4(138);
                        break;
                }
            }

            private void realKeyClick4(int key) {
                this.msgmgr.realKeyClick(context, key);
            }
        });
        map.put(34, new Parser(this, "【0x22】后雷达信息") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.msgmgr.mCanbusInfoInt[6] = 0;
                this.msgmgr.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setRearRadarLocationData(10, MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[2], 60), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[3], 165), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[4], 165), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[5], 60));
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.msgmgr.updateParkUi(null, context);
                }
            }
        });
        map.put(35, new Parser(this, "【0x23】前雷达信息") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.msgmgr.mCanbusInfoInt[6] = 0;
                this.msgmgr.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setFrontRadarLocationData(10, MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[2], 60), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[3], 120), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[4], 120), MsgMgr.m362initParsers$lambda4$getRadarValue(this.msgmgr.mCanbusInfoInt[5], 60));
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.msgmgr.updateParkUi(null, context);
                }
            }
        });
        map.put(36, new Parser(this, "【0x24】基本信息") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.msgmgr.mCanbusInfoInt[2] = this.msgmgr.mCanbusInfoInt[2] & 252;
                this.msgmgr.mCanbusInfoInt[3] = 0;
                if (isDataChange()) {
                    GeneralDoorData.isRightFrontDoorOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 7) & 1) == 1;
                    GeneralDoorData.isLeftFrontDoorOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 6) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 5) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 4) & 1) == 1;
                    GeneralDoorData.isBackOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 3) & 1) == 1;
                    GeneralDoorData.isFrontOpen = ((this.msgmgr.mCanbusInfoInt[2] >> 2) & 1) == 1;
                    this.msgmgr.updateDoorView(context);
                }
            }
        });
        map.put(37, new Parser(this, "【0x25】泊车辅助状态") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$7
            private final ArrayList<DriverUpdateEntity> list = new ArrayList<>();


            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = this.msgmgr.mDriveItemIndexHashMap.get("_41_rear_radar");
                    if (driverUpdateEntity != null) {
                        this.list.add(driverUpdateEntity.setValue(toSwitch((this.msgmgr.mCanbusInfoInt[2] >> 3) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = this.msgmgr.mDriveItemIndexHashMap.get("_41_front_radar");
                    if (driverUpdateEntity2 != null) {
                        this.list.add(driverUpdateEntity2.setValue(toSwitch((this.msgmgr.mCanbusInfoInt[2] >> 2) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity3 = this.msgmgr.mDriveItemIndexHashMap.get("_41_park_assist");
                    if (driverUpdateEntity3 != null) {
                        this.list.add(driverUpdateEntity3.setValue(toSwitch((this.msgmgr.mCanbusInfoInt[2] >> 1) & 1)));
                    }
                    DriverUpdateEntity driverUpdateEntity4 = this.msgmgr.mDriveItemIndexHashMap.get("_41_radar_sound");
                    if (driverUpdateEntity4 != null) {
                        this.list.add(driverUpdateEntity4.setValue(toSwitch(this.msgmgr.mCanbusInfoInt[2] & 1)));
                    }
                    this.msgmgr.updateGeneralDriveData(this.list);
                    this.msgmgr.updateDriveDataActivity(null);
                    GeneralParkData.pKeyRadarState = DataHandleUtils.getBoolBit1(this.msgmgr.mCanbusInfoInt[2]);
                    this.msgmgr.updatePKeyRadar();
                }
            }

            private String toSwitch(int i) {
                return CommUtil.getStrByResId(context, Integer.valueOf(i).equals(1) ? "_103_car_setting_value_7_1" : "_103_car_setting_value_7_0");
            }
        });
        map.put(39, new Parser(this, "【0x27】环境温度信息") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$8
            private String outdoorTemperature = "";

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                short s = (short) ((this.msgmgr.mCanbusInfoInt[4] << 8) | this.msgmgr.mCanbusInfoInt[3]);
                String str = (s / 10) + ' ' + ((this.msgmgr.mCanbusInfoInt[2] & 1) == 1 ? "℉" : "℃");
                Log.i("_3_MsgMgr", "parse: value[" + ((int) s) + "] result[" + str + "] outdoorTemperature[" + this.outdoorTemperature + ']');
                if (Intrinsics.areEqual(this.outdoorTemperature, str)) {
                    return;
                }
                this.outdoorTemperature = str;
                this.msgmgr.updateOutDoorTemp(context, str);
            }
        });
        map.put(40, new Parser(this, "【0x28】后座空调") {
            @Override
            public void parse(int dataLength) {
                if (msgmgr.isAirDataChange()) {
                    super.parse(dataLength);
                    msgmgr.updateAirActivity(context, 1003);
                }
            }

            @Override
            public OnParseListener[] setOnParseListeners() {
                return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.rear_power = ((msgmgr.mCanbusInfoInt[2] >> 7) & 1) == 1;
                        GeneralAirData.auto_manual = ((msgmgr.mCanbusInfoInt[2] >> 3) & 1) == 1;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda1
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAirData.rear_left_blow_head = ((msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1;
                        GeneralAirData.rear_left_blow_foot = ((msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1;
                        GeneralAirData.rear_wind_level = msgmgr.mCanbusInfoInt[3] & 15;
                        GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
                        GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$9$$ExternalSyntheticLambda2
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {

                        GeneralAirData.rear_left_seat_heat_level = (msgmgr.mCanbusInfoInt[4] >> 4) & 7;
                        GeneralAirData.rear_right_seat_heat_level = msgmgr.mCanbusInfoInt[4] & 7;
                    }
                }};
            }
        });
        map.put(41, new Parser(this, "【0x29】方向盘转角") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.msgmgr.mCanbusInfoByte[2], this.msgmgr.mCanbusInfoByte[3], 0, 19918, 16);
                    this.msgmgr.updateTrack();
                }
            }
        });
        map.put(42, new Parser(this, "【0x2A】PM2.5") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$11
            private final ArrayList<DriverUpdateEntity> list = new ArrayList<>();

            public void parse(int r6) {
                if (!this.isDataChange()) return;

                this.list.clear();

                int[] canbusData = this.msgmgr.mCanbusInfoInt;
                int pmValue = (canbusData[2] << 8) | canbusData[3];

                String resKey;

                if (pmValue >= 0 && pmValue < 36) {
                    resKey = "pm_excellent";
                } else if (pmValue < 76) {
                    resKey = "pm_good";
                } else if (pmValue < 116) {
                    resKey = "pm_mild_pollution";
                } else if (pmValue < 151) {
                    resKey = "pm_moderately_polluted";
                } else if (pmValue < 251) {
                    resKey = "pm_heavy_pollution";
                } else if (pmValue < 1001) {
                    resKey = "pm_severe_pollution";
                } else {
                    resKey = null;
                }

                String displayValue;
                if (resKey != null) {
                    String label = CommUtil.getStrByResId(context, resKey);
                    displayValue = pmValue + " " + label;
                } else {
                    displayValue = "---";
                }

                DriverUpdateEntity entity = this.msgmgr.mDriveItemIndexHashMap.get("_3_2ah_d1");
                if (entity != null) {
                    this.list.add(entity.setValue(displayValue));
                }
                this.msgmgr.updateGeneralDriveData(list);
                this.msgmgr.updateDriveDataActivity(null);
            }
        });
        map.put(48, new Parser(this, "【0x30】解码盒版本信息") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.msgmgr;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        map.put(50, new Parser(this, "【0x32】左侧雷达数据") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.msgmgr.mCanbusInfoInt[6] = 0;
                this.msgmgr.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setLeftRadarLocationData(7, this.msgmgr.mCanbusInfoInt[2] + 1, this.msgmgr.mCanbusInfoInt[3] + 1, this.msgmgr.mCanbusInfoInt[4] + 1, this.msgmgr.mCanbusInfoInt[5] + 1);
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.msgmgr.updateParkUi(null, context);
                }
            }
        });
        map.put(51, new Parser(this, "【0x33】右侧雷达数据") {
            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                this.msgmgr.mCanbusInfoInt[6] = 0;
                this.msgmgr.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    RadarInfoUtil.setRightRadarLocationData(7, this.msgmgr.mCanbusInfoInt[2] + 1, this.msgmgr.mCanbusInfoInt[3] + 1, this.msgmgr.mCanbusInfoInt[4] + 1, this.msgmgr.mCanbusInfoInt[5] + 1);
                    GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
                    this.msgmgr.updateParkUi(null, context);
                }
            }
        });
        map.put(64, new Parser(this, "【0x40】车身状态信息") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$15
            private int driveModeButtonStatus;
            private int isShowDriveAssist;
            private int isShowDriveMode;
            private int isShowParkSetting;
            private final ArrayList<SettingUpdateEntity> list = new ArrayList<>();
            private int m0x40Param4;


            public void parse(int var1_1) {
                // Verificar si hubo un cambio en los datos.
                if (!this.isDataChange()) {
                    return;
                }

                // Limpiar la lista de actualizaciones previas.
                this.list.clear();

                // Obtener los datos del CAN bus.
                int[] canbusInfo = this.msgmgr.mCanbusInfoInt;
                var1_1 = 2; // Esto parece estar relacionado con un parámetro de control (posiblemente un índice)
                int status = canbusInfo[2];

                // Manejar diferentes valores de status.
                if (status == 0) {
                    return;  // Si el status es 0, no hacer nada.
                }

                // Establecer la bandera de modo inglés si el estado es 16.
                if (status == 16) {
                    updateSetting("english_on");
                    return;
                }

                // Casos para otros valores de status (32, 64, etc.)
                switch (status) {
                    case 32:
                        updateSetting("spanish_on");
                        break;
                    case 64:
                        updateSetting("french_on");
                        break;
                    case 96:
                        updateSetting("german_on");
                        break;
                    case 112:
                        updateSetting("italian_on");
                        break;
                    case 128:
                        updateSetting("portuguese_on");
                        break;
                    case 144:
                        updateSetting("japanese_on");
                        break;
                    default:
                        handleCustomCases(status);
                        break;
                }

                // Procesar valores adicionales del CAN bus.
                processCanbusData(canbusInfo);
            }

            private void updateSetting(String language) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get(language);
                if (setting != null) {
                    this.list.add(setting.setValue(language));
                }
            }

            private void handleCustomCases(int status) {
                switch (status) {
                    case 178:
                        processIndividualSettings();
                        break;
                    case 177:
                        processOffroadSettings();
                        break;
                    case 176:
                        processDriveModeSettings(this.msgmgr.mCanbusInfoInt);
                        break;
                    case 83:
                        processLightSettings();
                        break;
                    case 82:
                        processCarLightingSettings();
                        break;
                    case 81:
                        processSpeedAlertSettings();
                        break;
                    case 80:
                        processChargingSettings();
                        break;
                    default:
                        break;
                }
            }

            private void processCanbusData(int[] canbusInfo) {
                // Procesar configuraciones relacionadas con el control de conducción.
                processDriveAssistSettings(canbusInfo);
                processDriveModeSettings(canbusInfo);

                // Actualizar la UI con los nuevos datos.
                msgmgr.updateGeneralSettingData(list);
                this.msgmgr.updateSettingActivity(null);
            }

            private void processDriveAssistSettings(int[] canbusInfo) {
                int driveAssistStatus = canbusInfo[3] >> 7 & 1;
                if (this.isShowDriveAssist != driveAssistStatus) {
                    this.isShowDriveAssist = driveAssistStatus;
                    if (driveAssistStatus == 1) {
                        openDriveAssistSettings();
                    } else if (msgmgr.getActivity() instanceof SettingActivity && msgmgr.isActivityFront()) {
                        msgmgr.finishActivity();
                    }
                }
            }

            private void openDriveAssistSettings() {

                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("_2_setting_2_0");
                if (setting != null) {
                    msgmgr.startSettingActivity(context, setting.getLeftListIndex(), setting.getRightListIndex());
                }
            }

            private void processDriveModeSettings(int[] canbusInfo) {
                int driveModeStatus = canbusInfo[3] >> 5 & 1;
                if (this.isShowDriveMode != driveModeStatus) {
                    this.isShowDriveMode = driveModeStatus;
                    if (driveModeStatus == 1) {
                        openDriveModeSettings();
                    } else if (msgmgr.getActivity() instanceof SettingActivity && msgmgr.isActivityFront()) {
                        msgmgr.finishActivity();
                    }
                }
            }

            private void openDriveModeSettings() {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("_283_title_2");
                if (setting != null) {
                    msgmgr.startSettingActivity(context, setting.getLeftListIndex(), setting.getRightListIndex());
                }
            }

            private void processIndividualSettings() {
                // Actualiza las configuraciones relacionadas con la individualidad (por ejemplo, ajustes del motor, dirección, etc.)
                updateIndividualSetting("engine");
                updateIndividualSetting("steering");
                updateIndividualSetting("cornering_light");
                updateIndividualSetting("air_conditioning");
            }

            private void updateIndividualSetting(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_individual_" + settingType);
                if (setting != null) {
                    this.list.add(setting.setValue(getCanbusDataForSetting(settingType)));
                }
            }

            private int getCanbusDataForSetting(String settingType) {
                // Aquí se puede implementar la lógica para extraer el valor correspondiente del CAN bus según el tipo de configuración.
                // Por ejemplo:
                switch (settingType) {
                    case "engine":

                        return msgmgr.mCanbusInfoInt[4] >> 4 & 15;
                    case "steering":
                        return msgmgr.mCanbusInfoInt[4] & 15;
                    case "cornering_light":
                        return msgmgr.mCanbusInfoInt[5] >> 4 & 15;
                    case "air_conditioning":
                        return (msgmgr.mCanbusInfoInt[5] & 15) == 2 ? 1 : 0;
                    default:
                        return 0;
                }
            }

            private void processOffroadSettings() {
                // Procesa las configuraciones relacionadas con el modo offroad.
                updateOffroadSetting("drive");
                updateOffroadSetting("steering");
                updateOffroadSetting("four_wheel_drive");
                updateOffroadSetting("air_conditioning");
            }

            private void updateOffroadSetting(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_offroad_" + settingType);
                if (setting != null) {
                    this.list.add(setting.setValue(getCanbusDataForOffroadSetting(settingType)));
                }
            }

            private int getCanbusDataForOffroadSetting(String settingType) {
                // Similar a los ajustes individuales, se obtienen los datos del CAN bus para el modo offroad.
                switch (settingType) {
                    case "drive":
                        return msgmgr.mCanbusInfoInt[4] >> 4 & 15;
                    case "steering":
                        return msgmgr.mCanbusInfoInt[3] & 15;
                    case "four_wheel_drive":
                        return msgmgr.mCanbusInfoInt[4] >> 4 & 15;
                    case "air_conditioning":
                        return (msgmgr.mCanbusInfoInt[4] & 15) == 2 ? 1 : 0;
                    default:
                        return 0;
                }
            }

            private void processLightSettings() {
                // Actualiza las configuraciones relacionadas con las luces.
                updateLightSetting("handle_box_light");
                updateLightSetting("sync_light");
                updateLightSetting("first_color");
                updateLightSetting("second_color");
            }

            private void updateLightSetting(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_" + settingType);
                if (setting != null) {
                    this.list.add(setting.setValue(getCanbusDataForLightSetting(settingType)));
                }
            }

            private int getCanbusDataForLightSetting(String settingType) {
                // Obtener datos del CAN bus para los ajustes de las luces.
                switch (settingType) {
                    case "handle_box_light":
                        return msgmgr.mCanbusInfoInt[3] / 10;
                    case "sync_light":
                        return 1 - (msgmgr.mCanbusInfoInt[4] >> 7 & 1);
                    case "first_color":
                        return msgmgr.mCanbusInfoInt[5] & 63;
                    case "second_color":
                        return msgmgr.mCanbusInfoInt[6] & 63;
                    default:
                        return 0;
                }
            }

            private void processCarLightingSettings() {
                // Ajuste de configuraciones relacionadas con las luces del coche.
                updateLightingSettings("roof_light");
                updateLightingSettings("front_light");
                updateLightingSettings("all_area_light");
            }

            private void updateLightingSettings(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_" + settingType);
                if (setting != null) {
                    this.list.add(setting.setValue(getCanbusDataForLightingSetting(settingType)));
                }
            }

            private int getCanbusDataForLightingSetting(String settingType) {
                // Obtener datos del CAN bus para las configuraciones de iluminación del coche.
                switch (settingType) {
                    case "roof_light":
                        return msgmgr.mCanbusInfoInt[3] / 10;
                    case "front_light":
                        return msgmgr.mCanbusInfoInt[4] / 10;
                    case "all_area_light":
                        return msgmgr.mCanbusInfoInt[6] / 10;
                    default:
                        return 0;
                }
            }

            private void processSpeedAlertSettings() {
                // Configuraciones relacionadas con las alertas de velocidad.
                updateSpeedAlertSetting("current_consumption");
                updateSpeedAlertSetting("avg_consumption");
                updateSpeedAlertSetting("con_consumers");
                updateSpeedAlertSetting("eco_tips");
            }

            private void updateSpeedAlertSetting(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_" + settingType);
                if (setting != null) {
                    this.list.add(setting.setValue(getCanbusDataForSpeedAlertSetting(settingType)));
                }
            }

            private int getCanbusDataForSpeedAlertSetting(String settingType) {
                // Obtener los datos del CAN bus relacionados con las alertas de velocidad.
                switch (settingType) {
                    case "current_consumption":
                        return msgmgr.mCanbusInfoInt[3] & 1;
                    case "avg_consumption":
                        return msgmgr.mCanbusInfoInt[3] >> 1 & 1;
                    case "con_consumers":
                        return msgmgr.mCanbusInfoInt[3] >> 2 & 1;
                    case "eco_tips":
                        return msgmgr.mCanbusInfoInt[3] >> 3 & 1;
                    default:
                        return 0;
                }
            }

            private void processChargingSettings() {
                // Configuraciones de carga.
                updateChargingSetting("current");
                updateChargingSetting("speed");
            }

            private void updateChargingSetting(String settingType) {
                SettingUpdateEntity setting = this.msgmgr.mSettingItemIndexHashMap.get("vm_power_consumption");
                if (setting != null) {
                    this.list.add(setting.setValue(this.msgmgr.mCanbusInfoInt[3] & 3));
                }
            }

        });
        map.put(65, new Parser(this, "【0x41】中控使能") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$16
            private final ArrayList<SettingUpdateEntity> list = new ArrayList<>();


            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                SettingUpdateEntity settingUpdateEntity;
                if (isDataChange()) {
                    this.list.clear();
                    int i = this.msgmgr.mCanbusInfoInt[2];
                    if (i == 16) {
                        SettingUpdateEntity settingUpdateEntity2 = this.msgmgr.mSettingItemIndexHashMap.get("_2_setting_0");
                        if (settingUpdateEntity2 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity2.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity3 = this.msgmgr.mSettingItemIndexHashMap.get("_3_40h_10h_p2_b7");
                        if (settingUpdateEntity3 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity3.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                    } else if (i == 32) {
                        SettingUpdateEntity settingUpdateEntity4 = this.msgmgr.mSettingItemIndexHashMap.get("tpms_set");
                        if (settingUpdateEntity4 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity4.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity5 = this.msgmgr.mSettingItemIndexHashMap.get("_3_40h_20h_p1_b0");
                        if (settingUpdateEntity5 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity5.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity6 = this.msgmgr.mSettingItemIndexHashMap.get("_3_40h_20h_p2");
                        if (settingUpdateEntity6 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity6.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                    } else if (i == 64) {
                        SettingUpdateEntity settingUpdateEntity7 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active_auto");
                        if (settingUpdateEntity7 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity7.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity8 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_volume");
                        if (settingUpdateEntity8 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity8.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity9 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_front_tone_setting");
                        if (settingUpdateEntity9 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity9.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity10 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_volume");
                        if (settingUpdateEntity10 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity10.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity11 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_rear_tone_setting");
                        if (settingUpdateEntity11 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity11.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity12 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_parking_active");
                        if (settingUpdateEntity12 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity12.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                    } else if (i == 96) {
                        SettingUpdateEntity settingUpdateEntity13 = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_syncchronous_adjustment");
                        if (settingUpdateEntity13 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity13.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity14 = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_lower_while_reversing");
                        if (settingUpdateEntity14 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity14.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity15 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_auto_wiping");
                        if (settingUpdateEntity15 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity15.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity16 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mirror_wipers_rear_window_wiping");
                        if (settingUpdateEntity16 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity16.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity17 = this.msgmgr.mSettingItemIndexHashMap.get("_3_40h_60h_p2_b0");
                        if (settingUpdateEntity17 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity17.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity18 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                        if (settingUpdateEntity18 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity18.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 112) {
                        SettingUpdateEntity settingUpdateEntity19 = this.msgmgr.mSettingItemIndexHashMap.get("_3_40h_70h_p1_b30");
                        if (settingUpdateEntity19 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity19.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity20 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_door_unlocking");
                        if (settingUpdateEntity20 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity20.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity21 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_open_close_auto_locking");
                        if (settingUpdateEntity21 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity21.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity22 = this.msgmgr.mSettingItemIndexHashMap.get("inductive_trunk_lid");
                        if (settingUpdateEntity22 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity22.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                    } else if (i == 128) {
                        SettingUpdateEntity settingUpdateEntity23 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_current_consumption");
                        if (settingUpdateEntity23 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity23.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity24 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_consumption");
                        if (settingUpdateEntity24 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity24.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity25 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_con_consumers");
                        if (settingUpdateEntity25 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity25.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity26 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_eco_tips");
                        if (settingUpdateEntity26 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity26.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity27 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_travelling_time");
                        if (settingUpdateEntity27 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity27.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity28 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_distance_travelled");
                        if (settingUpdateEntity28 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity28.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity29 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_avg_speed");
                        if (settingUpdateEntity29 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity29.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity30 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_digital_speed_display");
                        if (settingUpdateEntity30 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity30.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity31 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_speed_warning");
                        if (settingUpdateEntity31 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity31.setEnable((this.msgmgr.mCanbusInfoInt[4] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity32 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_mfd_oil_temperature");
                        if (settingUpdateEntity32 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity32.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 1) & 1) == 1)));
                        }
                    } else if (i == 144) {
                        SettingUpdateEntity settingUpdateEntity33 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_distance");
                        if (settingUpdateEntity33 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity33.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity34 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_speed");
                        if (settingUpdateEntity34 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity34.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity35 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_temperature");
                        if (settingUpdateEntity35 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity35.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity36 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_volume");
                        if (settingUpdateEntity36 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity36.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity37 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_consumption");
                        if (settingUpdateEntity37 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity37.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity38 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_units_pressure");
                        if (settingUpdateEntity38 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity38.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 48) {
                        SettingUpdateEntity settingUpdateEntity39 = this.msgmgr.mSettingItemIndexHashMap.get("_2_setting_2_0");
                        if (settingUpdateEntity39 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity39.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity40 = this.msgmgr.mSettingItemIndexHashMap.get("_283_car_setting_pa_6");
                        if (settingUpdateEntity40 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity40.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity41 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_lane_assisit");
                        if (settingUpdateEntity41 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity41.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                    } else if (i == 49) {
                        SettingUpdateEntity settingUpdateEntity42 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_driver_assistance_last_distance_selected");
                        if (settingUpdateEntity42 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity42.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity43 = this.msgmgr.mSettingItemIndexHashMap.get("_283_car_setting_pa_1");
                        if (settingUpdateEntity43 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity43.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity44 = this.msgmgr.mSettingItemIndexHashMap.get("_283_car_setting_pa_2");
                        if (settingUpdateEntity44 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity44.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity45 = this.msgmgr.mSettingItemIndexHashMap.get("_283_car_setting_pa_3");
                        if (settingUpdateEntity45 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity45.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity46 = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_acc_drive_program");
                        if (settingUpdateEntity46 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity46.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity47 = this.msgmgr.mSettingItemIndexHashMap.get("_2_settings_acc_distance");
                        if (settingUpdateEntity47 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity47.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                    } else if (i == 80) {
                        SettingUpdateEntity settingUpdateEntity48 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_switch_on_time");
                        if (settingUpdateEntity48 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity48.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity49 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_auto_control");
                        if (settingUpdateEntity49 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity49.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity50 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_lane_change_flash");
                        if (settingUpdateEntity50 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity50.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity51 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_ins_swi_lighting");
                        if (settingUpdateEntity51 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity51.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity52 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_coming_home");
                        if (settingUpdateEntity52 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity52.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity53 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_leaving_home");
                        if (settingUpdateEntity53 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity53.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity54 = this.msgmgr.mSettingItemIndexHashMap.get("_283_car_setting_light_4");
                        if (settingUpdateEntity54 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity54.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity55 = this.msgmgr.mSettingItemIndexHashMap.get("headlight_illumination_distance_adjustment");
                        if (settingUpdateEntity55 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity55.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                    } else if (i == 81) {
                        SettingUpdateEntity settingUpdateEntity56 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_travel_mode");
                        if (settingUpdateEntity56 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity56.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity57 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_door_ambient_light");
                        if (settingUpdateEntity57 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity57.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity58 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_footwell_light");
                        if (settingUpdateEntity58 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity58.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity59 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_roof_light");
                        if (settingUpdateEntity59 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity59.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity60 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_car_front_light");
                        if (settingUpdateEntity60 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity60.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity61 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_all_area_light");
                        if (settingUpdateEntity61 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity61.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity62 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_assist");
                        if (settingUpdateEntity62 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity62.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity63 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_dynamic_light_follow");
                        if (settingUpdateEntity63 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity63.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity64 = this.msgmgr.mSettingItemIndexHashMap.get("_303_setting_content_11");
                        if (settingUpdateEntity64 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity64.setEnable((this.msgmgr.mCanbusInfoInt[4] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity65 = this.msgmgr.mSettingItemIndexHashMap.get("_303_setting_content_12");
                        if (settingUpdateEntity65 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity65.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity66 = this.msgmgr.mSettingItemIndexHashMap.get("_303_setting_content_13");
                        if (settingUpdateEntity66 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity66.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 2) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity67 = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_light_handle_box_light");
                        if (settingUpdateEntity67 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity67.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 3) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity68 = this.msgmgr.mSettingItemIndexHashMap.get("two_color_sync");
                        if (settingUpdateEntity68 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity68.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 4) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity69 = this.msgmgr.mSettingItemIndexHashMap.get("first_color");
                        if (settingUpdateEntity69 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity69.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 5) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity70 = this.msgmgr.mSettingItemIndexHashMap.get("second_color");
                        if (settingUpdateEntity70 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity70.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 6) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity71 = this.msgmgr.mSettingItemIndexHashMap.get("on_off_btn_txt_7");
                        if (settingUpdateEntity71 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity71.setEnable(((this.msgmgr.mCanbusInfoInt[4] >> 7) & 1) == 1)));
                        }
                    } else if (i == 176) {
                        SettingUpdateEntity settingUpdateEntity72 = this.msgmgr.mSettingItemIndexHashMap.get("seat_remote_key_memory_matching");
                        if (settingUpdateEntity72 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity72.setEnable((this.msgmgr.mCanbusInfoInt[3] & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity73 = this.msgmgr.mSettingItemIndexHashMap.get("driver_seat");
                        if (settingUpdateEntity73 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity73.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 1) & 1) == 1)));
                        }
                        SettingUpdateEntity settingUpdateEntity74 = this.msgmgr.mSettingItemIndexHashMap.get("_2_setting_14_1");
                        if (settingUpdateEntity74 != null) {
                            Boolean.valueOf(this.list.add(settingUpdateEntity74.setEnable((this.msgmgr.mCanbusInfoInt[3] & 3) != 0)));
                        }
                    } else if (i == 177 && (settingUpdateEntity = this.msgmgr.mSettingItemIndexHashMap.get("individual")) != null) {
                        Boolean.valueOf(this.list.add(settingUpdateEntity.setEnable(((this.msgmgr.mCanbusInfoInt[3] >> 7) & 1) == 1)));
                    }
                    this.msgmgr.updateGeneralSettingData(this.list);
                    this.msgmgr.updateSettingActivity(null);
                }
            }
        });
        map.put(80, new Parser(this, "【0x50】Driving Data") {

            private final ArrayList<DriverUpdateEntity> list = new ArrayList<>();
            final DecimalFormat df_2Integer = new DecimalFormat("00");
            private final Supplier<String> unit0x10 = () -> (msgmgr.mCanbusInfoInt[3] & 1) == 1 ? "mi" : "km";

            private int value0x10;
            private int value0x11;

            private final Consumer<DriverUpdateEntity> parse0x20 = driverUpdateEntity -> {
                StringBuilder sb = new StringBuilder();
                list.add(driverUpdateEntity.setValue(sb.append(msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5], msgmgr.mCanbusInfoInt[6], msgmgr.mCanbusInfoInt[7]) / 10.0f).append(' ').append(unit0x10.get()).toString()));
            };
            private final Consumer<DriverUpdateEntity> parse0x30 = driverUpdateEntity -> {
                String strSubstring;

                int iMergeValue = msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5], msgmgr.mCanbusInfoInt[6], msgmgr.mCanbusInfoInt[7]);
                String str = "";
                switch (iMergeValue) {
                    case 65534:
                    case 65535:
                        strSubstring = "--";
                        break;
                    default:
                        String strValueOf = String.valueOf(iMergeValue / 10.0f);
                        int length = strValueOf.length();
                        int i = 0;
                        for (int i2 = 0; i2 < length; i2++) {
                            char cCharAt = strValueOf.charAt(i2);
                            if ('0' <= cCharAt && cCharAt < ':') {
                                i++;
                            }
                            if (i == 3 || i2 == strValueOf.length() - 1) {
                                strSubstring = strValueOf.substring(0, i2 + 1);

                                break;
                            }
                        }
                        strSubstring = "";
                        break;
                }
                ArrayList arrayList = this.list;
                StringBuilder sbAppend = new StringBuilder().append(strSubstring).append(' ');
                int i3 = msgmgr.mCanbusInfoInt[3];
                if (i3 == 0) {
                    str = "L/100km";
                } else if (i3 == 1) {
                    str = "km/L";
                } else if (i3 == 2 || i3 == 3) {
                    str = "mpg";
                } else if (i3 != 4) {
                    switch (i3) {
                        case 18:
                            str = "kWh/100km";
                            break;
                        case 19:
                            str = "km/kWh";
                            break;
                        case 20:
                            str = "kWh/mi";
                            break;
                        case 21:
                            str = "mi/kWh";
                            break;
                    }
                } else {
                    str = "l/h";
                }
                arrayList.add(driverUpdateEntity.setValue(sbAppend.append(str).toString()));
            };
            private final Consumer<DriverUpdateEntity> parse0x40 = driverUpdateEntity -> {

                StringBuilder sb = new StringBuilder();
                StringBuilder sbAppend = sb.append(msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5]) / 10.0f).append(' ');
                if ((msgmgr.mCanbusInfoInt[3] & 1) == 1) {
                    String str = this.df_2Integer.format(((msgmgr.mCanbusInfoInt[4] + (msgmgr.mCanbusInfoInt[5] * 256)) / 10) * 1.6d);

                    msgmgr.updateSpeedInfo(Integer.parseInt(str));
                } else {
                    String str2 = this.df_2Integer.format(Integer.valueOf((msgmgr.mCanbusInfoInt[4] + (msgmgr.mCanbusInfoInt[5] * 256)) / 10));

                    msgmgr.updateSpeedInfo(Integer.parseInt(str2));
                }
                list.add(driverUpdateEntity.setValue(sbAppend.append(Unit.INSTANCE).toString()));
            };
            private final Consumer<DriverUpdateEntity> parse0x50 = driverUpdateEntity -> {
                int iMergeValue = msgmgr.mergeValue(msgmgr.mCanbusInfoInt[3], msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5]);
                StringBuilder sbAppend = new StringBuilder().append(iMergeValue / 60).append(" : ");
                String str = String.format("%02d", Arrays.copyOf(new Object[]{Integer.valueOf(iMergeValue % 60)}, 1));
                list.add(driverUpdateEntity.setValue(sbAppend.append(str).toString()));
            };

            private final Consumer<DriverUpdateEntity> parse0x70 = driverUpdateEntity -> {

                StringBuilder sb = new StringBuilder();
                list.add(driverUpdateEntity.setValue(sb.append(((short) msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5])) / 10.0f).append(' ').append((msgmgr.mCanbusInfoInt[3] & 1) == 1 ? "km/kwh" : "kwh/100km").toString()));
            };

            @Override
            public void parse(int dataLength) {
                super.parse(dataLength);
                if (isDataChange()) {
                    this.list.clear();
                    switch (msgmgr.mCanbusInfoInt[2]) {
                        case 16:
                            DriverUpdateEntity driverUpdateEntity = msgmgr.mDriveItemIndexHashMap.get("_3_50h_10h");
                            if (driverUpdateEntity != null) {
                                MsgMgr msgMgr = msgmgr;
                                this.value0x10 = msgMgr.mergeValue(msgMgr.mCanbusInfoInt[4], msgMgr.mCanbusInfoInt[5]);
                                this.list.add(driverUpdateEntity.setValue(this.value0x10 + ' ' + this.unit0x10.get()));
                                break;
                            }
                            break;
                        case 17:
                            DriverUpdateEntity driverUpdateEntity2 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_11h");
                            if (driverUpdateEntity2 != null) {
                                MsgMgr msgMgr2 = msgmgr;
                                this.value0x11 = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[4], msgMgr2.mCanbusInfoInt[5]);
                                this.list.add(driverUpdateEntity2.setValue(this.value0x11 + ' ' + this.unit0x10.get()));
                                break;
                            }
                            break;
                        case 18:
                            DriverUpdateEntity driverUpdateEntity3 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_12h");
                            if (driverUpdateEntity3 != null) {
                                this.list.add(driverUpdateEntity3.setValue((this.value0x10 + this.value0x11) + ' ' + this.unit0x10.get()));
                                break;
                            }
                            break;
                        case 32:
                            DriverUpdateEntity driverUpdateEntity4 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_20h");
                            if (driverUpdateEntity4 != null) {
                                parse0x20.accept(driverUpdateEntity4);
                                break;
                            }
                            break;
                        case 33:
                            DriverUpdateEntity driverUpdateEntity5 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_21h");
                            if (driverUpdateEntity5 != null) {
                                this.parse0x20.accept(driverUpdateEntity5);
                                break;
                            }
                            break;
                        case 34:
                            DriverUpdateEntity driverUpdateEntity6 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_22h");
                            if (driverUpdateEntity6 != null) {
                                this.parse0x20.accept(driverUpdateEntity6);
                                break;
                            }
                            break;
                        case 48:
                            DriverUpdateEntity driverUpdateEntity7 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_30h");
                            if (driverUpdateEntity7 != null) {
                                this.parse0x30.accept(driverUpdateEntity7);
                                break;
                            }
                            break;
                        case 49:
                            DriverUpdateEntity driverUpdateEntity8 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_31h");
                            if (driverUpdateEntity8 != null) {
                                this.parse0x30.accept(driverUpdateEntity8);
                                break;
                            }
                            break;
                        case 50:
                            DriverUpdateEntity driverUpdateEntity9 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_32h");
                            if (driverUpdateEntity9 != null) {
                                this.parse0x30.accept(driverUpdateEntity9);
                                break;
                            }
                            break;
                        case 64:
                            DriverUpdateEntity driverUpdateEntity10 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_40h");
                            if (driverUpdateEntity10 != null) {
                                this.parse0x40.accept(driverUpdateEntity10);
                                break;
                            }
                            break;
                        case 65:
                            DriverUpdateEntity driverUpdateEntity11 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_41h");
                            if (driverUpdateEntity11 != null) {
                                this.parse0x40.accept(driverUpdateEntity11);
                                break;
                            }
                            break;
                        case 66:
                            DriverUpdateEntity driverUpdateEntity12 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_42h");
                            if (driverUpdateEntity12 != null) {
                                this.parse0x40.accept(driverUpdateEntity12);
                                break;
                            }
                            break;
                        case 80:
                            DriverUpdateEntity driverUpdateEntity13 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_50h");
                            if (driverUpdateEntity13 != null) {
                                this.parse0x50.accept(driverUpdateEntity13);
                                break;
                            }
                            break;
                        case 81:
                            DriverUpdateEntity driverUpdateEntity14 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_51h");
                            if (driverUpdateEntity14 != null) {
                                this.parse0x50.accept(driverUpdateEntity14);
                                break;
                            }
                            break;
                        case 82:
                            DriverUpdateEntity driverUpdateEntity15 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_52h");
                            if (driverUpdateEntity15 != null) {
                                this.parse0x50.accept(driverUpdateEntity15);
                                break;
                            }
                            break;
                        case 96:
                            DriverUpdateEntity driverUpdateEntity16 = msgmgr.mDriveItemIndexHashMap.get("_2_driver_15");
                            if (driverUpdateEntity16 != null) {
                                MsgMgr msgMgr3 = msgmgr;
                                this.list.add(driverUpdateEntity16.setValue(msgMgr3.mergeValue(msgMgr3.mCanbusInfoInt[4], msgMgr3.mCanbusInfoInt[5]) + ' ' + ((msgMgr3.mCanbusInfoInt[3] & 1) == 1 ? "l/h" : "gal/h")));
                                break;
                            }
                            break;
                        case 97:
                            DriverUpdateEntity driverUpdateEntity17 = msgmgr.mDriveItemIndexHashMap.get("_2_driver_16");
                            if (driverUpdateEntity17 != null) {
                                MsgMgr msgMgr4 = msgmgr;
                                ArrayList<DriverUpdateEntity> arrayList = this.list;
                                StringBuilder sbAppend = new StringBuilder().append(msgMgr4.mergeValue(msgMgr4.mCanbusInfoInt[4], msgMgr4.mCanbusInfoInt[5]) / 10.0f).append(' ');
                                int i = msgMgr4.mCanbusInfoInt[3] & 3;
                                String str = "mpg";
                                if (i == 0) {
                                    str = "L/100km";
                                } else if (i == 1) {
                                    str = "km/L";
                                } else if (i != 2 && i != 3) {
                                    str = "";
                                }
                                arrayList.add(driverUpdateEntity17.setValue(sbAppend.append(str).toString()));
                                break;
                            }
                            break;
                        case 112:
                            DriverUpdateEntity driverUpdateEntity18 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_70h");
                            if (driverUpdateEntity18 != null) {
                                this.parse0x70.accept(driverUpdateEntity18);
                                break;
                            }
                            break;
                        case 113:
                            DriverUpdateEntity driverUpdateEntity19 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_71h");
                            if (driverUpdateEntity19 != null) {
                                this.parse0x70.accept(driverUpdateEntity19);
                                break;
                            }
                            break;
                        case 114:
                            DriverUpdateEntity driverUpdateEntity20 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_72h");
                            if (driverUpdateEntity20 != null) {
                                this.parse0x70.accept(driverUpdateEntity20);
                                break;
                            }
                            break;
                        case 128:
                            DriverUpdateEntity driverUpdateEntity21 = msgmgr.mDriveItemIndexHashMap.get("_3_50h_80h");
                            if (driverUpdateEntity21 != null) {
                                MsgMgr msgMgr5 = msgmgr;
                                ArrayList<DriverUpdateEntity> arrayList2 = this.list;
                                StringBuilder sbAppend2 = new StringBuilder().append(msgMgr5.mCanbusInfoInt[3]).append(' ');
                                int i2 = msgMgr5.mCanbusInfoInt[4];
                                arrayList2.add(driverUpdateEntity21.setValue(sbAppend2.append(CommUtil.getStrByResId(context, i2 != 1 ? i2 != 3 ? i2 != 5 ? i2 != 9 ? "_3_50h_80h_p2_e" : "_3_50h_80h_p2_9" : "_3_50h_80h_p2_5" : "_3_50h_80h_p2_3" : "_3_50h_80h_p2_1")).toString()));
                                break;
                            }
                            break;
                    }
                    msgmgr.updateGeneralDriveData(this.list);
                    msgmgr.updateDriveDataActivity(null);
                }
            }


        });
        map.put(81, new Parser(this, "【0x51】功放信息") {

            private final int[] amplifierData = new int[6];
            private final ArrayList<SettingUpdateEntity> list = new ArrayList<>();
            private int mData6;
            private int mData6Record;

            @Override
            public void parse(int dataLength) {
                super.parse(dataLength);
                int[] iArrCopyOfRange = ArraysKt.copyOfRange(this.msgmgr.mCanbusInfoInt, 2, 8);

                if (!Arrays.equals(iArrCopyOfRange, this.amplifierData)) {
                    System.arraycopy(iArrCopyOfRange, 0, this.amplifierData, 0, 14);
                    msgmgr.updateAmplifierActivity(null);
                    msgmgr.saveAmplifierData(context, msgmgr.getCanId());
                }
                int i = this.mData6Record;
                int i2 = this.mData6;
                if (i != i2) {
                    this.mData6Record = i2;
                    this.msgmgr.updateGeneralSettingData(this.list);
                    this.msgmgr.updateSettingActivity(null);
                }
            }

            @Override
            public OnParseListener[] setOnParseListeners() {
                return new OnParseListener[]{new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda0
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.volume = msgmgr.mCanbusInfoInt[2];
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda1
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.bandTreble = msgmgr.mCanbusInfoInt[3];
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda2
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.bandMiddle = msgmgr.mCanbusInfoInt[4];
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda3
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.bandBass = msgmgr.mCanbusInfoInt[5];
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda4
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.frontRear = msgmgr.mCanbusInfoInt[6] - 9;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda5
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        GeneralAmplifierData.leftRight = msgmgr.mCanbusInfoInt[7] - 9;
                    }
                }, new OnParseListener() { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$18$$ExternalSyntheticLambda6
                    @Override // com.hzbhd.canbus.interfaces.OnParseListener
                    public void onParse() {
                        list.clear();
                        mData6 = msgmgr.mCanbusInfoInt[8];
                        SettingUpdateEntity settingUpdateEntity = msgmgr.mSettingItemIndexHashMap.get("_143_0xAD_setting6");
                        if (settingUpdateEntity != null) {
                            list.add(settingUpdateEntity.setValue(Integer.valueOf(mData6)).setProgress(mData6));
                        }
                    }
                }};
            }
        });
        map.put(82, new Parser(this, "【0x52】电子显示") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$19
            private final ArrayList<DriverUpdateEntity> list = new ArrayList<>();
            private int mData45;

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                int i = (this.msgmgr.mCanbusInfoInt[6] << 8) | this.msgmgr.mCanbusInfoInt[7];
                MsgMgr msgMgr = this.msgmgr;
                if (this.mData45 != i) {
                    this.mData45 = i;
                    GeneralHybirdData.energyDirection = (msgMgr.mCanbusInfoInt[6] >> 6) & 3;
                    GeneralHybirdData.isShowMotor = ((msgMgr.mCanbusInfoInt[6] >> 5) & 1) == 1;
                    int i2 = (msgMgr.mCanbusInfoInt[6] >> 2) & 7;
                    GeneralHybirdData.wheelTrack = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? 0 : 4 : 2 : 1 : 3;
                    GeneralHybirdData.powerBatteryLevel = RangesKt.coerceAtMost((msgMgr.mCanbusInfoInt[7] / 10) + 1, 10);
                    GeneralHybirdData.powerBatteryValue = msgMgr.mCanbusInfoInt[7];
                    GeneralHybirdData.isShowBattery = true;
                    if (msgMgr.getActivity() instanceof MqbHybirdActivity) {
                        msgMgr.updateActivity(null);
                    }
                }
                this.msgmgr.mCanbusInfoInt[6] = 0;
                this.msgmgr.mCanbusInfoInt[7] = 0;
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = this.msgmgr.mDriveItemIndexHashMap.get("mqb_endurance_potential");
                    if (driverUpdateEntity != null) {
                        MsgMgr msgMgr2 = this.msgmgr;
                        ArrayList<DriverUpdateEntity> arrayList = this.list;
                        StringBuilder sb = new StringBuilder();
                        int iMergeValue = msgMgr2.mergeValue(msgMgr2.mCanbusInfoInt[3], msgMgr2.mCanbusInfoInt[2]) / 10;
                        arrayList.add(driverUpdateEntity.setValue(sb.append(iMergeValue < 1 ? "< 1" : String.valueOf(iMergeValue)).append(" km").toString()));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = this.msgmgr.mDriveItemIndexHashMap.get("battery_life");
                    if (driverUpdateEntity2 != null) {
                        MsgMgr msgMgr3 = this.msgmgr;
                        this.list.add(driverUpdateEntity2.setValue((msgMgr3.mergeValue(msgMgr3.mCanbusInfoInt[5], msgMgr3.mCanbusInfoInt[4]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity3 = this.msgmgr.mDriveItemIndexHashMap.get("driven_distance");
                    if (driverUpdateEntity3 != null) {
                        MsgMgr msgMgr4 = this.msgmgr;
                        this.list.add(driverUpdateEntity3.setValue((msgMgr4.mergeValue(msgMgr4.mCanbusInfoInt[9], msgMgr4.mCanbusInfoInt[8]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity4 = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_hybrid_zero_emission");
                    if (driverUpdateEntity4 != null) {
                        MsgMgr msgMgr5 = this.msgmgr;
                        this.list.add(driverUpdateEntity4.setValue((msgMgr5.mergeValue(msgMgr5.mCanbusInfoInt[11], msgMgr5.mCanbusInfoInt[10]) / 10) + " km"));
                    }
                    DriverUpdateEntity driverUpdateEntity5 = this.msgmgr.mDriveItemIndexHashMap.get("proportion");
                    if (driverUpdateEntity5 != null) {
                        this.list.add(driverUpdateEntity5.setValue(String.valueOf(this.msgmgr.mCanbusInfoInt[12]) + '%'));
                    }
                    this.msgmgr.updateGeneralDriveData(this.list);
                    this.msgmgr.updateDriveDataActivity(null);
                }
            }
        });
        map.put(83, new Parser(this, "【0x53】能量回收") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$20
            private final float barMaxValue = 100.0f;
            private final String fullBar = "==============================================================";
            private final ArrayList<DriverUpdateEntity> list = new ArrayList<>();


            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    int i = this.msgmgr.mCanbusInfoInt[2];
                    if (i != 0) {
                        if (i == 16) {
                            for (int i2 = 1; i2 < 16; i2++) {
                                setEntity(String.valueOf(i2), (i2 * 2) + 1);
                            }
                        } else if (i == 17) {
                            for (int i3 = 16; i3 < 31; i3++) {
                                setEntity(String.valueOf(i3), ((i3 - 15) * 2) + 1);
                            }
                        }
                    } else {
                        setEntity("_3_53h_00", 3);
                    }
                    this.msgmgr.updateGeneralDriveData(this.list);
                    this.msgmgr.updateDriveDataActivity(null);
                }
            }

            private void setEntity(String name, int valueIndex) {
                DriverUpdateEntity driverUpdateEntity;
                if (valueIndex + 1 < this.msgmgr.mCanbusInfoInt.length && (driverUpdateEntity = this.msgmgr.mDriveItemIndexHashMap.get(name)) != null) {
                    MsgMgr msgMgr = this.msgmgr;
                    int iMergeValue = (int) (msgMgr.mergeValue(msgMgr.mCanbusInfoInt[valueIndex], msgMgr.mCanbusInfoInt[valueIndex + 1]) / 10.0f);
                    this.list.add(driverUpdateEntity.setValue(iMergeValue == 0 ? "" : iMergeValue + " kWh"));
                }
            }
        });
        final ArrayList<WarningEntity> arrayList = new ArrayList<>();
        final ArrayList<WarningEntity> arrayList2 = new ArrayList<>();
        map.put(96, new Parser(this, "【0x60】车辆提示信息") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$21
            private final ArrayList<WarningEntity> list = new ArrayList<>();


            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    arrayList.clear();
                    arrayList.add(new WarningEntity(CommUtil.getStrByResId(context, "_2_information_title_1")));

                    // Verificar si el valor en mCanbusInfoInt[2] es distinto de 0
                    if (this.msgmgr.mCanbusInfoInt[2] != 0) {
                        // Limitar el rango de 0 a 6 (mCanbusInfoInt[2] está entre 0 y 6)
                        int maxRange = RangesKt.coerceAtMost(this.msgmgr.mCanbusInfoInt[2], 6);

                        // Iterar sobre el rango máximo y agregar las advertencias correspondientes
                        for (int i = 0; i < maxRange; i++) {
                            String str;
                            int status = this.msgmgr.mCanbusInfoInt[i + 3]; // El valor para obtener el tipo de advertencia

                            // Determinar el mensaje que se debe mostrar basado en el valor de 'status'
                            switch (status) {
                                case 0:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_0";
                                    break;
                                case 1:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_1";
                                    break;
                                case 2:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_2";
                                    break;
                                case 3:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_3";
                                    break;
                                case 4:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_4";
                                    break;
                                case 5:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_5";
                                    break;
                                case 6:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_6";
                                    break;
                                case 7:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_7";
                                    break;
                                case 8:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_8";
                                    break;
                                case 9:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_9";
                                    break;
                                case 10:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_10";
                                    break;
                                case 11:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_11";
                                    break;
                                case 12:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_12";
                                    break;
                                case 13:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_13";
                                    break;
                                case 14:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_14";
                                    break;
                                case 15:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_15";
                                    break;
                                case 16:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_16";
                                    break;
                                case 17:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_17";
                                    break;
                                case 18:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_18";
                                    break;
                                case 19:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_19";
                                    break;
                                case 20:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_20";
                                    break;
                                case 21:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_21";
                                    break;
                                case 22:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_22";
                                    break;
                                case 23:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_23";
                                    break;
                                case 24:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_24";
                                    break;
                                case 25:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_25";
                                    break;
                                case 26:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_26";
                                    break;
                                case 27:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_27";
                                    break;
                                case 28:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_28";
                                    break;
                                case 29:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_29";
                                    break;
                                case 30:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_30";
                                    break;
                                case 31:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_31";
                                    break;
                                case 32:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_32";
                                    break;
                                case 33:
                                    str = "vm_golf7_vehicle_status_start_stop_prompt_33";
                                    break;
                                default:
                                    str = "Unknown"; // En caso de un valor no contemplado.
                            }

                            // Agregar la advertencia al arrayList
                            String warningMessage = "\t\t" + CommUtil.getStrByResId(context, str);
                            arrayList.add(new WarningEntity(warningMessage));
                        }
                    } else {
                        // Si el valor de mCanbusInfoInt[2] es 0, agregar una advertencia predeterminada
                        String warningMessage = "\t\t" + CommUtil.getStrByResId(context, (this.msgmgr.mCanbusInfoInt[9] & 1) == 1 ? "vm_golf7_vehicle_status_start_stop_0" : "_3_60h_d7_1");
                        arrayList.add(new WarningEntity(warningMessage));
                    }

                    // Unir todas las listas de advertencias en 'arrayList'
                    ArrayList<WarningEntity> finalList = new ArrayList<>();
                    finalList.addAll(arrayList); // Array de advertencias obtenidas
                    finalList.addAll(arrayList2); // Array de advertencias adicionales (arrayList2)

                    // Actualizar el dataList de advertencias
                    GeneralWarningDataData.dataList = finalList;

                    // Actualizar la actividad de advertencia
                    this.msgmgr.updateWarningActivity(null);
                }
            }

        });
        map.put(98, new Parser(this, "【0x62】Conv. Consumers 信息") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$22
            private final ArrayList<WarningEntity> list = new ArrayList<>();


            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                String str = "";
                arrayList2.clear();
                arrayList2.add(new WarningEntity(CommUtil.getStrByResId(context, "_2_information_title_4")));
                int iCoerceAtMost = RangesKt.coerceAtMost(this.msgmgr.mCanbusInfoInt[2], 3);
                for (int i = 0; i < iCoerceAtMost; i++) {
                    ArrayList<WarningEntity> arrayList3 = arrayList2;
                    StringBuilder sbAppend = new StringBuilder().append("\t\t");
                    Context context2 = context;
                    switch (this.msgmgr.mCanbusInfoInt[i + 3]) {
                        case 0:
                            str = "vm_golf7_Conv_consumers_prompt_0";
                            break;
                        case 1:
                            str = "vm_golf7_Conv_consumers_prompt_1";
                            break;
                        case 2:
                            str = "vm_golf7_Conv_consumers_prompt_2";
                            break;
                        case 3:
                            str = "vm_golf7_Conv_consumers_prompt_3";
                            break;
                        case 4:
                            str = "vm_golf7_Conv_consumers_prompt_4";
                            break;
                        case 5:
                            str = "vm_golf7_Conv_consumers_prompt_5";
                            break;
                        case 6:
                            str = "vm_golf7_Conv_consumers_prompt_6";
                            break;
                        case 7:
                            str = "vm_golf7_Conv_consumers_prompt_7";
                            break;
                        case 8:
                            str = "vm_golf7_Conv_consumers_prompt_8";
                            break;
                        case 9:
                            str = "vm_golf7_Conv_consumers_prompt_9";
                            break;
                        case 10:
                            str = "vm_golf7_Conv_consumers_prompt_10";
                            break;
                        case 11:
                            str = "vm_golf7_Conv_consumers_prompt_11";
                            break;
                        case 12:
                            str = "vm_golf7_Conv_consumers_prompt_12";
                            break;
                        case 13:
                            str = "vm_golf7_Conv_consumers_prompt_13";
                            break;
                        case 14:
                            str = "vm_golf7_Conv_consumers_prompt_14";
                            break;
                        case 15:
                            str = "vm_golf7_Conv_consumers_prompt_15";
                            break;
                        case 16:
                            str = "vm_golf7_Conv_consumers_prompt_16";
                            break;
                        case 17:
                            str = "vm_golf7_Conv_consumers_prompt_17";
                            break;
                        case 18:
                            str = "vm_golf7_Conv_consumers_prompt_18";
                            break;
                        default:
                            ArrayList<WarningEntity> arrayList4 = this.list;
                            ArrayList<WarningEntity> arrayList5 = arrayList;
                            ArrayList<WarningEntity> arrayList6 = arrayList2;
                            arrayList4.clear();
                            arrayList4.addAll(arrayList5);
                            arrayList4.addAll(arrayList6);
                            GeneralWarningDataData.dataList = arrayList4;
                            this.msgmgr.updateWarningActivity(null);
                    }
                    arrayList3.add(new WarningEntity(sbAppend.append(CommUtil.getStrByResId(context2, str)).toString()));
                }
                ArrayList<WarningEntity> arrayList42 = this.list;
                ArrayList<WarningEntity> arrayList52 = arrayList;
                ArrayList<WarningEntity> arrayList62 = arrayList2;
                arrayList42.clear();
                arrayList42.addAll(arrayList52);
                arrayList42.addAll(arrayList62);
                GeneralWarningDataData.dataList = arrayList42;
                this.msgmgr.updateWarningActivity(null);
            }
        });
        map.put(99, new Parser(this, "【0x63】Service") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$23
            private final ArrayList<DriverUpdateEntity> driList = new ArrayList<>();
            private final ArrayList<SettingUpdateEntity> setList = new ArrayList<>();

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i = this.msgmgr.mCanbusInfoInt[2];
                    if (i == 0) {
                        DriverUpdateEntity driverUpdateEntity = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_vehicle_no");
                        if (driverUpdateEntity != null) {
                            MsgMgr msgMgr = this.msgmgr;
                            ArrayList<DriverUpdateEntity> arrayList3 = this.driList;
                            arrayList3.clear();
                            arrayList3.add(driverUpdateEntity.setValue(new String(ArraysKt.copyOfRange(msgMgr.mCanbusInfoByte, 3, msgMgr.mCanbusInfoByte.length), Charsets.UTF_8)));
                            msgMgr.updateGeneralDriveData(arrayList3);
                            msgMgr.updateDriveDataActivity(null);
                            return;
                        }
                        return;
                    }
                    if (i == 16) {
                        DriverUpdateEntity driverUpdateEntity2 = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_days");
                        if (driverUpdateEntity2 != null) {
                            setDay(driverUpdateEntity2);
                            return;
                        }
                        return;
                    }
                    if (i != 17) {
                        switch (i) {
                            case 32:
                                DriverUpdateEntity driverUpdateEntity3 = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_days");
                                if (driverUpdateEntity3 != null) {
                                    setDay(driverUpdateEntity3);
                                    break;
                                }
                                break;
                            case 33:
                                DriverUpdateEntity driverUpdateEntity4 = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_oil_change_service_distance");
                                if (driverUpdateEntity4 != null) {
                                    setDistance(driverUpdateEntity4);
                                    break;
                                }
                                break;
                            case 34:
                                SettingUpdateEntity settingUpdateEntity = this.msgmgr.mSettingItemIndexHashMap.get("vm_golf7_vehicle_setup_service_wiper_in_maintenance_position");
                                if (settingUpdateEntity != null) {
                                    MsgMgr msgMgr2 = this.msgmgr;
                                    ArrayList<SettingUpdateEntity> arrayList4 = this.setList;
                                    arrayList4.clear();
                                    arrayList4.add(settingUpdateEntity.setValue(Integer.valueOf(msgMgr2.mCanbusInfoInt[3] & 1)));
                                    msgMgr2.updateGeneralSettingData(arrayList4);
                                    msgMgr2.updateSettingActivity(null);
                                    break;
                                }
                                break;
                        }
                        return;
                    }
                    DriverUpdateEntity driverUpdateEntity5 = this.msgmgr.mDriveItemIndexHashMap.get("vm_golf7_vehicle_setup_service_volkswagen_inspection_distance");
                    if (driverUpdateEntity5 != null) {
                        setDistance(driverUpdateEntity5);
                    }
                }
            }

            private void setDay(DriverUpdateEntity driverUpdateEntity) {
                String string = null;
                ArrayList<DriverUpdateEntity> arrayList = this.driList;
                MsgMgr msgMgr = this.msgmgr;

                // Limpiar la lista de actualizaciones del conductor
                arrayList.clear();

                // Obtener el valor de mCanbusInfoInt[3] y aplicarle una máscara
                int n = msgmgr.mCanbusInfoInt[3] & 0xF;

                // Determinar el mensaje según el valor de 'n'
                if (n != 0) {
                    if (n == 1) {
                        string = "vm_golf7_vehicle_setup_service_days"; // Si 'n' es 1, mensaje de días de servicio
                    } else if (n == 2) {
                        string = "vm_golf7_vehicle_setup_service_overdue_days"; // Si 'n' es 2, mensaje de días de servicio vencidos
                    }
                }

                // Si el mensaje es válido, obtener la cadena formateada
                if (string != null) {
                    // Obtener el texto de la cadena usando el recurso de texto
                    string = CommUtil.getStrByResId(context, string);

                    // Verificar que la cadena no sea nula


                    // Formatear la cadena con los valores de mCanbusInfoInt[4] y mCanbusInfoInt[5]
                    string = String.format(string, Arrays.copyOf(new Object[]{msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5])}, 1));

                    // Verificar que la cadena formateada no sea nula

                }

                // Si la cadena es nula, asignar un valor predeterminado
                if (string == null) {
                    string = "--";
                }

                // Agregar el valor formateado o predeterminado a la lista de actualizaciones del conductor
                arrayList.add(driverUpdateEntity.setValue(string));

                msgmgr.updateGeneralDriveData(arrayList);
                msgmgr.updateDriveDataActivity(null);
            }

            private void setDistance(DriverUpdateEntity driverUpdateEntity) {
                String result = null;
                ArrayList<DriverUpdateEntity> arrayList = this.driList;


                arrayList.clear();

                int n = msgmgr.mCanbusInfoInt[3] & 0xF;

                // Determinar la cadena de texto basada en el valor de 'n'
                if (n != 0) {
                    if (n == 1) {
                        result = ""; // Si n es 1, no se agrega ningún texto adicional
                    } else if (n == 2) {
                        result = CommUtil.getStrByResId(context, "vm_golf7_vehicle_setup_service_overdue") + ' '; // Si n es 2, agregar el texto de "servicio vencido"
                    }
                }

                // Si 'result' no es nulo, proceder a construir la cadena
                if (result != null) {
                    StringBuilder stringBuilder = new StringBuilder(result);

                    // Agregar el valor formateado con los valores de mCanbusInfo[4] y mCanbusInfo[5]
                    stringBuilder.append(msgmgr.mergeValue(msgmgr.mCanbusInfoInt[4], msgmgr.mCanbusInfoInt[5]) * 100).append(' ');

                    // Determinar la unidad (millas o kilómetros) según el valor de mCanbusInfo[3]
                    result = (msgmgr.mCanbusInfoInt[3] >> 4 & 1) == 1 ? "mi" : "km";

                    // Añadir la unidad a la cadena final
                    result = stringBuilder.append(result).toString();
                }

                // Si no se pudo formar la cadena, asignar un valor predeterminado
                if (result == null) {
                    result = "--";
                }

                // Agregar el valor final a la lista de actualizaciones
                arrayList.add(driverUpdateEntity.setValue(result));

                // Actualizar los datos generales del conductor
                msgmgr.updateGeneralDriveData(arrayList);

                // Actualizar la actividad de los datos del conductor
                msgmgr.updateDriveDataActivity(null);
            }
        });
        final ArrayList<TireUpdateEntity> arrayList3 = new ArrayList<>();
        String[] strArr = new String[3];
        for (int i = 0; i < 3; i++) {
            strArr[i] = "";
        }
        arrayList3.add(new TireUpdateEntity(0, 0, strArr));
        String[] strArr2 = new String[3];
        for (int i2 = 0; i2 < 3; i2++) {
            strArr2[i2] = "";
        }
        arrayList3.add(new TireUpdateEntity(1, 0, strArr2));
        String[] strArr3 = new String[3];
        for (int i3 = 0; i3 < 3; i3++) {
            strArr3[i3] = "";
        }
        arrayList3.add(new TireUpdateEntity(2, 0, strArr3));
        String[] strArr4 = new String[3];
        for (int i4 = 0; i4 < 3; i4++) {
            strArr4[i4] = "";
        }
        arrayList3.add(new TireUpdateEntity(3, 0, strArr4));
        GeneralTireData.isHaveSpareTire = false;
        map.put(102, new Parser(this, "【0x66】直接式胎压监测信息") { // from class: com.hzbhd.canbus.car._3.MsgMgr$initParsers$1$28
            private final Pair<String, Function<Integer, Float>>[] units = new Pair[]{new Pair<>("bar", new Function<Integer, Float>() {
                @Override
                public Float apply(Integer i5) {
                    return i5 / 10.0f;
                }
            }), new Pair<>("psi", new Function<Integer, Float>() {
                @Override
                public Float apply(Integer i5) {
                    return i5 / 2.0f;
                }
            }), new Pair<>("kPa", new Function<Integer, Float>() {
                @Override
                public Float apply(Integer i5) {
                    return i5 * 10.0f;
                }
            })};

            private int coerceIn(int value, int min, int max) {
                return Math.max(min, Math.min(value, max));
            }

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    Pair<String, Function<Integer, Float>> pair = this.units[(coerceIn(this.msgmgr.mCanbusInfoInt[7], 0, 2))];
                    MsgMgr msgMgr = this.msgmgr;
                    int i5 = 0;
                    for (TireUpdateEntity obj : arrayList3) {
                        int i6 = i5 + 1;
                        if (i5 < 0) {
                            continue;
                        }
                        obj.getList().set(RangesKt.coerceIn(msgMgr.mCanbusInfoInt[2], new IntRange(0, 1)), pair.getSecond().apply(msgMgr.mCanbusInfoInt[i5 + 3]) + ' ' + pair.getFirst());
                        i5 = i6;
                    }
                    GeneralTireData.dataList = arrayList3;
                    this.msgmgr.updateTirePressureActivity(null);
                }
            }
        });
        map.put(104, new Parser(this, "【0x68】胎压报警信息状态 2") {

            @Override // com.hzbhd.canbus.car._3.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    int i5 = this.msgmgr.mCanbusInfoInt[3];
                    String str = i5 != 2 ? i5 != 3 ? i5 != 4 ? "null_value" : "vm_golf7__direct_tpms_warn_lose_pressure" : "vm_golf7__direct_tpms_warn_low_pressure" : "vm_golf7__direct_tpms_warn_check_pressure";

                    int i6 = 0;
                    for (TireUpdateEntity tireUpdateEntity : arrayList3) {
                        int i7 = i6 + 1;
                        if (i6 < 0) {
                            continue;
                        }
                        tireUpdateEntity.setTireStatus((this.msgmgr.mCanbusInfoInt[2] >> i6) & 1);
                        tireUpdateEntity.getList().set(2, CommUtil.getStrByResId(context, tireUpdateEntity.getTireStatus() == 0 ? "vm_golf7__direct_tpms_warn_normal" : str));
                        i6 = i7;
                    }
                    GeneralTireData.dataList = arrayList3;
                    this.msgmgr.updateTirePressureActivity(null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initParsers$lambda-4$getRadarValue, reason: not valid java name */
    public static int m362initParsers$lambda4$getRadarValue(int i, int i2) {
        if (Integer.valueOf(i).equals(0)) {
            return 0;
        }
        return ((i / i2) * 9) + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MsgMgr.kt */

    abstract class Parser {
        private final int PARSE_LISTENERS_LENGTH;
        private int[] canbusInfo;
        private final OnParseListener[] onParseListeners;
        final MsgMgr msgmgr;

        public Parser(MsgMgr msgMgr, String msg) {

            this.msgmgr = msgMgr;
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
            if (Arrays.equals(this.canbusInfo, this.msgmgr.mCanbusInfoInt)) {
                return false;
            }
            int[] iArr = this.msgmgr.mCanbusInfoInt;
            int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);

            this.canbusInfo = iArrCopyOf;
            return true;
        }

        public OnParseListener[] setOnParseListeners() {
            return new OnParseListener[0];
        }
    }

    private void initItemsIndexHashMap(Context context) {
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

    public void updateSettings(String title, Object value) {


        SettingUpdateEntity<Object> settingUpdateEntity = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity != null) {
            updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntity.setValue(value)));
            updateSettingActivity(null);
        }
    }

    /* compiled from: MsgMgr.kt */

    public static final class SpeedAlertHelper {
        public static final SpeedAlertHelper INSTANCE = new SpeedAlertHelper();
        private static final SpeedUnit kmh;
        private static final SpeedUnit mph;
        private static SpeedUnit speedUnit;
        private static int value;

        /* compiled from: MsgMgr.kt */

        public static class SpeedUnit {
            private final Function<Integer, Float> conversion;
            private final int max;
            private final int min;
            private final int step;
            private final String unitStrName;

            // Constructor
            public SpeedUnit(int step, int min, int max, String unitStrName, Function<Integer, Float> conversion) {
                if (unitStrName == null || conversion == null) {
                    throw new IllegalArgumentException("Unit name and conversion function cannot be null");
                }
                this.step = step;
                this.min = min;
                this.max = max;
                this.unitStrName = unitStrName;
                this.conversion = conversion;
            }

            // Getter methods
            public int getStep() {
                return this.step;
            }

            public int getMin() {
                return this.min;
            }

            public int getMax() {
                return this.max;
            }

            public String getUnitStrName() {
                return this.unitStrName;
            }

            public Function<Integer, Float> getConversion() {
                return this.conversion;
            }

            // Copy constructor equivalent (without synthetic Kotlin copy method)
            public SpeedUnit copy(int step, int min, int max, String unitStrName, Function<Integer, Float> conversion) {
                if (unitStrName == null || conversion == null) {
                    throw new IllegalArgumentException("Unit name and conversion function cannot be null");
                }
                return new SpeedUnit(step, min, max, unitStrName, conversion);
            }

            // equals method (overriding Object.equals)
            @Override
            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                if (!(other instanceof SpeedUnit)) {
                    return false;
                }
                SpeedUnit otherSpeedUnit = (SpeedUnit) other;
                return this.step == otherSpeedUnit.step && this.min == otherSpeedUnit.min && this.max == otherSpeedUnit.max && this.unitStrName.equals(otherSpeedUnit.unitStrName) && this.conversion.equals(otherSpeedUnit.conversion);
            }

            // hashCode method (overriding Object.hashCode)
            @Override
            public int hashCode() {
                int result = Integer.hashCode(this.step);
                result = 31 * result + Integer.hashCode(this.min);
                result = 31 * result + Integer.hashCode(this.max);
                result = 31 * result + this.unitStrName.hashCode();
                result = 31 * result + this.conversion.hashCode();
                return result;
            }

            // toString method
            @Override
            public String toString() {
                return "SpeedUnit{" + "step=" + step + ", min=" + min + ", max=" + max + ", unitStrName='" + unitStrName + '\'' + ", conversion=" + conversion + '}';
            }

        }

        private SpeedAlertHelper() {
        }

        static {
            SpeedUnit speedUnit2 = new SpeedUnit(10, 30, NfDef.STATE_3WAY_M_HOLD, "_2_setting_1_3_1", new Function<Integer, Float>() {
                @Override
                public Float apply(Integer i) {
                    return i * 1.609344f;
                }
            });
            kmh = speedUnit2;
            mph = new SpeedUnit(5, 20, 150, "_2_setting_1_3_2", new Function<Integer, Float>() {
                @Override
                public Float apply(Integer integer) {
                    return integer / 1.609344f;
                }
            });
            speedUnit = speedUnit2;
        }

        public SpeedUnit getSpeedUnit() {
            return speedUnit;
        }

        public void setUnit(Context context, int value2) {

            SpeedUnit speedUnit2 = value2 == 0 ? kmh : mph;
            if (Intrinsics.areEqual(speedUnit, speedUnit2)) {
                return;
            }
            speedUnit = speedUnit2;
            List<SettingPageUiSet.ListBean> list = UiMgrFactory.getCanUiMgr(context).getSettingUiSet(context).getList();

            ArrayList<SettingPageUiSet.ListBean.ItemListBean> arrayList = new ArrayList<>();
            Iterator<SettingPageUiSet.ListBean> it = list.iterator();
            while (it.hasNext()) {
                List<SettingPageUiSet.ListBean.ItemListBean> itemList = it.next().getItemList();

                CollectionsKt.addAll(arrayList, itemList);
            }
            ArrayList<SettingPageUiSet.ListBean.ItemListBean> arrayList2 = new ArrayList();
            for (SettingPageUiSet.ListBean.ItemListBean obj : arrayList) {
                if (Intrinsics.areEqual(obj.getTitleSrn(), "_3_40h_20h_p2")) {
                    arrayList2.add(obj);
                }
            }
            ArrayList<SettingPageUiSet.ListBean.ItemListBean> arrayList3 = arrayList2;
            if (arrayList3.size() > 0) {
                SettingPageUiSet.ListBean.ItemListBean itemListBean = arrayList3.get(0);
                SpeedAlertHelper speedAlertHelper = INSTANCE;
                itemListBean.setMin(speedUnit.getMin() / speedUnit.getStep());
                itemListBean.setMax(speedUnit.getMax() / speedUnit.getStep());
                itemListBean.setUnit(speedUnit.getUnitStrName());
                speedAlertHelper.setValue((int) speedUnit.getConversion().apply(Integer.valueOf(value)).floatValue());
            }
        }

        public void setValue(int i) {
            SpeedUnit speedUnit2 = speedUnit;
            value = RangesKt.coerceIn(i, speedUnit2.getMin(), speedUnit2.getMax());
        }

        public int getValue() {
            return value / speedUnit.getStep();
        }

        public int getProgress() {
            SpeedUnit speedUnit2 = speedUnit;
            return (value - speedUnit2.getMin()) / speedUnit2.getStep();
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        byte[] bArr = this.m0xA6Command;
        bArr[2] = (byte) bYear2Dig;
        bArr[3] = (byte) bMonth;
        bArr[4] = (byte) bDay;
        bArr[5] = (byte) ((isFormat24H ? 0 : 128) | bHours24H);
        bArr[6] = (byte) bMins;
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override
    public void radioInfoChange(int var1, String currBand, String currentFreq, String psName, int var5) {
        // Validar los parámetros de entrada


        // Inicializamos el comando de radio
        byte[] radioCommand = this.m0xC0Command;

        // Configurar band status y preset status
        radioCommand[2] = 1;  // Band status: 1 significa que está activo
        radioCommand[3] = 1;  // Preset status: 1 significa que está activo

        // Obtener la banda actual
        String band = currBand;
        int bandValue = 0;

        // Determinar el valor de la banda
        switch (band) {
            case "FM3":
                bandValue = 1;
                break;
            case "FM2":
                bandValue = 2;
                break;
            case "FM1":
                bandValue = 3;
                break;
            case "AM2":
                bandValue = 4;
                break;
            case "AM1":
                bandValue = 5;
                break;
            default:
                bandValue = -1; // Banda no reconocida
                break;
        }

        // Asignamos el valor correspondiente al comando de radio
        radioCommand[4] = (byte) bandValue;

        // Determinar la frecuencia según la banda
        int frequency = 0;
        if (band.contains("FM")) {
            // Para FM, multiplicamos por 100 para obtener la frecuencia en entero
            frequency = (int) (Float.parseFloat(currentFreq) * 100);
        } else if (band.contains("AM")) {
            // Para AM, la frecuencia es un valor entero
            frequency = Integer.parseInt(currentFreq);
        }

        // Establecer la frecuencia en el comando
        radioCommand[5] = (byte) (frequency & 255);  // Primer byte de la frecuencia
        radioCommand[6] = (byte) ((frequency >> 8) & 255);  // Segundo byte de la frecuencia

        // Establecer el índice del preset
        radioCommand[7] = (byte) Integer.parseInt(psName);

        // Configurar el estéreo
        byte[] stereoCommand = this.m0xC1Command;
        // Usamos el valor 1 para activar el bit 6 del byte en el índice 2
        stereoCommand[2] = (byte) DataHandleUtils.setOneBit(stereoCommand[2], 6, 1);  // Activar estéreo

        // Enviar los comandos para cambiar la información del radio
        this.sendMultiCommand(this.m0xC0Command, this.m0xC1Command);
    }


    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 3;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        m0x70Sender.sendInfo("ATV", null, null, null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 7;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        m0x70Sender.sendInfo("AUX", null, null, null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, byte currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, long currentPos, byte playModel, int playIndex, boolean isPlaying, long totalTime, String songTitle, String songAlbum, String songArtist, boolean isReportFromPlay) {
        MediaShareData.Music music = MediaShareData.Music.INSTANCE;
        int i = currentSecond % 10;
        if (!(i >= 0 && i < 5)) {
            if (5 <= i && i < 10) {
                sendTextInfo(112, songTitle);
                sendTextInfo(113, songArtist);
                sendTextInfo(114, songAlbum);
                sendTextInfo(115, " ");
                return;
            }
            return;
        }
        if (music.getMPath().contains("emulated")) {
            m0xC0Command[2] = 9;  // Si contiene "emulated", asignamos 9
        } else {
            m0xC0Command[2] = 8;  // Si no contiene "emulated", asignamos 8
        }
        byte[] bArr = this.m0xC0Command;
        bArr[3] = 17;
        bArr[4] = (byte) (music.getMPlaySize() & 255);
        this.m0xC0Command[5] = (byte) ((music.getMPlaySize() >> 8) & 255);
        this.m0xC0Command[6] = (byte) (music.getAPlayIndex() & 255);
        this.m0xC0Command[7] = (byte) ((music.getAPlayIndex() >> 8) & 255);
        long j = 1000;
        long j2 = 60;
        this.m0xC0Command[8] = (byte) (((music.getMPos() / j) / j2) % j2);
        this.m0xC0Command[9] = (byte) ((music.getMPos() / j) % j2);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte stoagePath, byte playRatio, int currentPlayingIndexLow, int totalCount, byte currentHour, byte currentMinute, byte currentSecond, String currentAllMinuteStr, byte currentPlayingIndexHigh, byte currentAllMinute, String currentHourStr, String currentMinuteStr, String currentSecondStr, int currentPos, byte playMode, boolean isPlaying, int duration) {
        MediaShareData.Video video = MediaShareData.Video.INSTANCE;
        if (video.getMPath().contains("emulated")) {
            m0xC0Command[2] = 9;  // Si contiene "emulated", asignamos 9
        } else {
            m0xC0Command[2] = 8;  // Si no contiene "emulated", asignamos 8
        }
        byte[] bArr = this.m0xC0Command;
        bArr[3] = 17;
        bArr[4] = (byte) (video.getMPlaySize() & 255);
        this.m0xC0Command[5] = (byte) ((video.getMPlaySize() >> 8) & 255);
        this.m0xC0Command[6] = (byte) (video.getMPlayIndex() & 255);
        this.m0xC0Command[7] = (byte) ((video.getMPlayIndex() >> 8) & 255);
        long j = 1000;
        long j2 = 60;
        this.m0xC0Command[8] = (byte) (((video.getMPos() / j) / j2) % j2);
        this.m0xC0Command[9] = (byte) ((video.getMPos() / j) % j2);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String title, String artist, String album) {


        byte[] bArr = this.m0xC0Command;
        bArr[2] = 11;
        bArr[3] = -1;
        bArr[4] = 0;
        bArr[5] = 0;
        bArr[6] = 0;
        bArr[7] = 0;
        bArr[8] = 0;
        bArr[9] = 0;
        CanbusMsgSender.sendMsg(bArr);
        m0x70Sender.sendInfo(title, artist, album, null);
        sendTextInfo(112, title);
        sendTextInfo(113, artist);
        sendTextInfo(114, album);
        sendTextInfo(115, " ");
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        byte[] bArr = this.m0xC0Command;
        bArr[2] = 10;
        bArr[3] = -1;
        ArraysKt.fill(bArr, (byte) 0, 4, 10);
        CanbusMsgSender.sendMsg(this.m0xC0Command);
        m0x70Sender.sendInfo("DTV", null, null, null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int volValue, boolean isMute) {
        MediaShareData.Volume volume = MediaShareData.Volume.INSTANCE;
        byte[] bArr = this.m0xC4Command;
        bArr[2] = (byte) (volume.getVolume() | (volume.getVolume() == 0 ? 128 : 0));
        CanbusMsgSender.sendMsg(bArr);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int callStatus, byte[] phoneNumber, boolean isHfpConnected, boolean isCallingFlag, boolean isMicMute, boolean isAudioTransferTowardsAG, int batteryStatus, int signalValue, Bundle bundle) {
        byte[] bArr = this.m0xC5Command;
        bArr[2] = (byte) ((signalValue & NfDef.STATE_3WAY_M_HOLD) | (batteryStatus & 15));
        bArr[3] = (byte) ((isAudioTransferTowardsAG ? 0 : 64) | (isMicMute ? 0 : 32) | 16 | ((!isHfpConnected ? 6 : callStatus) & 15));
        if (phoneNumber != null) {
            System.arraycopy(phoneNumber, 0, m0xCACommand, 4, 12);
        }
        sendMultiCommand(this.m0xC5Command, this.m0xCACommand);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] phoneNumber, boolean isMicMute, boolean isAudioTransferTowardsAG, int time) {
        byte[] bArr = this.m0xC7Command;
        int i = time / 60;
        bArr[2] = (byte) ((i / 60) / 60);
        bArr[3] = (byte) (i % 60);
        bArr[4] = (byte) (time % 60);
        CanbusMsgSender.sendMsg(bArr);
    }

    private void sendTextInfo(int dataType, String text) {
        byte[] bytes;
        if (text == null) {
            text = "Unknown";
        }
        if (text.length() > 10) {
            bytes = StringsKt.substring(text, new IntRange(0, 10)).getBytes(Charsets.UTF_16BE);

        } else {
            bytes = text.getBytes(Charsets.UTF_16BE);

        }
        CanbusMsgSender.sendMsg(ArraysKt.plus(new byte[]{22, (byte) dataType, 16}, bytes));
    }

    private void sendMultiCommand(final byte[]... commands) {
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$sendMultiCommand$1$1
            private int i;

            public int getI() {
                return this.i;
            }

            public void setI(int i) {
                this.i = i;
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr = commands;
                if (i >= bArr.length) {
                    timerUtil.stopTimer();
                } else {
                    this.i = i + 1;
                    CanbusMsgSender.sendMsg(bArr[i]);
                }
            }
        }, 0L, 100L);
    }

    /* compiled from: MsgMgr.kt */

    public final class M0x70Sender {
        private final byte[][] commands;
        private final byte[] emptyInfo;
        private final TimerUtil timerUtil;

        public M0x70Sender() {
            byte[][] bArr = new byte[4][];
            for (int i = 0; i < 4; i++) {
                byte[] bArr2 = new byte[23];
                int i2 = 0;
                while (i2 < 23) {
                    bArr2[i2] = i2 != 0 ? i2 != 1 ? i2 != 2 ? (byte) 0 : (byte) 16 : (byte) (i + 112) : (byte) 22;
                    i2++;
                }
                bArr[i] = bArr2;
            }
            this.commands = bArr;
            byte[] bytes = "          ".getBytes(Charsets.UTF_16);

            this.emptyInfo = bytes;
            this.timerUtil = new TimerUtil();
        }


        public void sendInfo(String line1, String line2, String line3, String line4) {

            Pair<String, byte[]>[] pairArr = new Pair[]{new Pair<>(line1, this.commands[0]), new Pair<>(line2, this.commands[1]), new Pair<>(line3, this.commands[2]), new Pair<>(line4, this.commands[3])};

            final MsgMgr msgMgr = MsgMgr.this;
            this.timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._3.MsgMgr$M0x70Sender$sendInfo$1$1
                private int i;

                public int getI() {
                    return this.i;
                }

                public void setI(int i) {
                    this.i = i;
                }

                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    byte[] bytes;
                    int i = this.i;
                    if (i >= pairArr.length) {
                        timerUtil.stopTimer();
                        return;
                    }
                    if (Intrinsics.areEqual(pairArr[i].getFirst(), "")) {
                        bytes = emptyInfo;
                    } else {
                        bytes = msgMgr.getInfo(pairArr[this.i].getFirst(), 10).getBytes(Charsets.UTF_16);

                    }
                    System.arraycopy(bytes, 2, pairArr[this.i].getSecond(), 3, 8);
                    CanbusMsgSender.sendMsg(pairArr[this.i].getSecond());
                    this.i++;
                }
            }, 100L, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getInfo(String str, int i) {
        if (str.length() < i) {
            return getInfo(str + ' ', i);
        }
        if (str.length() <= i) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = str.substring(0, i - 2);

        return sb.append(strSubstring).append("..").toString();
    }

    public int[] getMAirData() {
        return this.mAirData;
    }

    public void setMAirData(int[] iArr) {

        this.mAirData = iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanbusInfoInt)) {
            return false;
        }
        this.mAirData = this.mCanbusInfoInt;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int mergeValue(int... values) {
        int length = values.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            i2 |= values[i] << (i3 * 8);
            i++;
            i3++;
        }
        return i2;
    }
}
