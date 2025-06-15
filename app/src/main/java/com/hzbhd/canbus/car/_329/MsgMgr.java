package com.hzbhd.canbus.car._329;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.app.NotificationCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.interfaces.OnParseListener;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralBubbleData;
import com.hzbhd.canbus.ui_datas.GeneralDisplayMsgData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.DriverDataPageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import nfore.android.bt.res.NfDef;




public final class MsgMgr extends AbstractMsgMgr {
    private static final String TAG = "_329_MsgMgr";
    private UiMgr mUiMgr;
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private int mCanId = -1;
    private final SparseArray<Parser> mParserArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndexHashMap = new HashMap<>();
    private HashMap<String, DriverUpdateEntity> mDriveItemIndexHashMap = new HashMap<>();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {

        super.afterServiceNormalSetting(context);
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);

        this.mUiMgr = (UiMgr) canUiMgr;
        initItemsIndexHashMap(context);
        initParsers(context);
        int intValue = SharePreUtil.getIntValue(context, UiMgr.SHARE_329_IS_SUPPORT_PANORAMIC, 0);
        updateSettingItem("support_panorama", Integer.valueOf(intValue));
        updateBubble(context, Integer.valueOf(intValue).equals(1));
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final String initParsers$getTemperature(float f, MsgMgr msgMgr, Context context) {
        if (GeneralAirData.fahrenheit_celsius) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%.1f%s", Arrays.copyOf(new Object[]{Float.valueOf(((f * 9) / 5) + 32), msgMgr.getTempUnitF(context)}, 2));

            return str;
        }
        return f + msgMgr.getTempUnitC(context);
    }

    private final void initParsers(final Context context) {
        SparseArray<Parser> sparseArray = this.mParserArray;
        sparseArray.put(17, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$1
            private int mKeyValue;
            private int mTrackValue;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x11 车身基本信息");
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateSpeedInfo(msgMgr.mCanbusInfoInt[3]);
                if (this.mKeyValue != this.this$0.mCanbusInfoInt[4]) {
                    this.mKeyValue = this.this$0.mCanbusInfoInt[4];
                    switch (this.this$0.mCanbusInfoInt[4]) {
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
                            realKeyLongClick1(3);
                            break;
                        case 5:
                            realKeyLongClick1(HotKeyConstant.K_VOICE_PICKUP);
                            break;
                        case 6:
                            realKeyLongClick1(184);
                            break;
                        case 8:
                            realKeyLongClick1(45);
                            break;
                        case 9:
                            realKeyLongClick1(46);
                            break;
                        case 11:
                            realKeyLongClick1(2);
                            break;
                        case 12:
                            realKeyLongClick1(52);
                            break;
                    }
                }
                GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(this.this$0.mCanbusInfoByte[9], this.this$0.mCanbusInfoByte[8], 0, 540, 16);
                if (this.mTrackValue != GeneralParkData.trackAngle) {
                    this.mTrackValue = GeneralParkData.trackAngle;
                    this.this$0.updateParkUi(null, context);
                }
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[5]);
            }
        });
        sparseArray.append(18, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$2
            private int mDoorStatus;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x12 车身详细信息");
                GeneralDoorData.isShowSeatBelt = true;
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.mDoorStatus != this.this$0.mCanbusInfoInt[4]) {
                    this.mDoorStatus = this.this$0.mCanbusInfoInt[4];
                    GeneralDoorData.isLeftFrontDoorOpen = ((this.this$0.mCanbusInfoInt[4] >> 7) & 1) == 1;
                    GeneralDoorData.isRightFrontDoorOpen = ((this.this$0.mCanbusInfoInt[4] >> 6) & 1) == 1;
                    GeneralDoorData.isLeftRearDoorOpen = ((this.this$0.mCanbusInfoInt[4] >> 5) & 1) == 1;
                    GeneralDoorData.isRightRearDoorOpen = ((this.this$0.mCanbusInfoInt[4] >> 4) & 1) == 1;
                    GeneralDoorData.isBackOpen = ((this.this$0.mCanbusInfoInt[4] >> 3) & 1) == 1;
                    GeneralDoorData.isSeatBeltTie = ((this.this$0.mCanbusInfoInt[4] >> 1) & 1) == 1;
                    this.this$0.updateDoorView(context);
                }
            }
        });
        sparseArray.append(33, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$3
            private int mKeyValue;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x21 面板按键");
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                if (this.mKeyValue != this.this$0.mCanbusInfoInt[2]) {
                    this.mKeyValue = this.this$0.mCanbusInfoInt[2];
                    int i = this.this$0.mCanbusInfoInt[2];
                    if (i == 0) {
                        realKeyLongClick1(0);
                        return;
                    }
                    if (i == 1) {
                        realKeyLongClick1(HotKeyConstant.K_SLEEP);
                        return;
                    }
                    if (i == 2) {
                        realKeyLongClick1(45);
                        return;
                    }
                    if (i == 3) {
                        realKeyLongClick1(46);
                        return;
                    }
                    if (i == 6) {
                        realKeyLongClick1(50);
                        return;
                    }
                    if (i == 9) {
                        realKeyLongClick1(3);
                        return;
                    }
                    if (i == 32) {
                        realKeyLongClick1(128);
                        return;
                    }
                    if (i == 40) {
                        realKeyLongClick1(HotKeyConstant.K_PHONE_ON_OFF);
                        return;
                    }
                    if (i == 47) {
                        realKeyLongClick1(52);
                        return;
                    }
                    if (i == 51) {
                        realKeyLongClick1(129);
                        return;
                    }
                    if (i == 55) {
                        realKeyLongClick1(58);
                        return;
                    }
                    if (i == 57) {
                        realKeyLongClick1(152);
                    } else if (i == 36) {
                        realKeyLongClick1(59);
                    } else {
                        if (i != 37) {
                            return;
                        }
                        realKeyLongClick1(128);
                    }
                }
            }

            private final void realKeyLongClick1(int key) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.realKeyLongClick1(context, key, msgMgr.mCanbusInfoInt[3]);
            }
        });
        sparseArray.append(34, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$4
            private byte mSelectValue;
            private byte mVolumeValue;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x22 原车面板旋钮");
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                int i;
                int i2;
                int i3 = this.this$0.mCanbusInfoInt[2];
                if (i3 == 1) {
                    int i4 = this.mVolumeValue - this.this$0.mCanbusInfoByte[3];
                    MsgMgr msgMgr = this.this$0;
                    Context context2 = context;
                    if (i4 > 0) {
                        i = 8;
                    } else if (i4 >= 0) {
                        return;
                    } else {
                        i = 7;
                    }
                    msgMgr.realKeyClick3_1(context2, i, 0, Math.abs(i4));
                    this.mVolumeValue = msgMgr.mCanbusInfoByte[3];
                    return;
                }
                if (i3 != 2) {
                    return;
                }
                int i5 = this.mSelectValue - this.this$0.mCanbusInfoByte[3];
                MsgMgr msgMgr2 = this.this$0;
                Context context3 = context;
                if (i5 > 0) {
                    i2 = 47;
                } else if (i5 >= 0) {
                    return;
                } else {
                    i2 = 48;
                }
                msgMgr2.realKeyClick3_2(context3, i2, 0, Math.abs(i5));
                this.mSelectValue = msgMgr2.mCanbusInfoByte[3];
            }
        });
        sparseArray.append(NfDef.STATE_3WAY_M_HOLD, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0xF0 软件版本信息");
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                MsgMgr msgMgr = this.this$0;
                msgMgr.updateVersionInfo(context, msgMgr.getVersionStr(msgMgr.mCanbusInfoByte));
            }
        });
        sparseArray.append(65, new MsgMgr$initParsers$1$6(this, context));
        sparseArray.append(49, new MsgMgr$initParsers$1$7(this, context));
        final ArrayList arrayList = new ArrayList();
        arrayList.add(new TireUpdateEntity(0, 0, new String[2]));
        arrayList.add(new TireUpdateEntity(1, 0, new String[2]));
        arrayList.add(new TireUpdateEntity(2, 0, new String[2]));
        arrayList.add(new TireUpdateEntity(3, 0, new String[2]));
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        sparseArray.append(72, new Parser(context, arrayList, booleanRef) { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$8
            final /* synthetic */ Ref.BooleanRef $isTirePressureLow;
            final /* synthetic */ ArrayList<TireUpdateEntity> $tireInfoList;
            private final String mTemperatureUnit;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x48 胎压信息");
                this.$tireInfoList = arrayList;
                this.$isTirePressureLow = booleanRef;
                this.mTemperatureUnit = this.this$0.getTempUnitC(context);
                GeneralTireData.isHaveSpareTire = false;
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    try {
                        ArrayList<TireUpdateEntity> arrayList2 = this.$tireInfoList;
                        MsgMgr msgMgr = this.this$0;
                        Ref.BooleanRef booleanRef2 = this.$isTirePressureLow;
                        for (TireUpdateEntity tireUpdateEntity : arrayList2) {
                            tireUpdateEntity.getList().set(0, getTemperature(msgMgr.mCanbusInfoInt[tireUpdateEntity.getWhichTire() + 2]));
                            List<String> list = tireUpdateEntity.getList();
                            int i = msgMgr.mCanbusInfoInt[tireUpdateEntity.getWhichTire() + 2 + 4];
                            booleanRef2.element = i < 18;
                            if (booleanRef2.element) {
                                tireUpdateEntity.setTireStatus(1);
                            } else {
                                tireUpdateEntity.setTireStatus(0);
                            }
                            Unit unit = Unit.INSTANCE;
                            list.set(1, getTirePressure(i));
                        }
                        GeneralTireData.dataList = this.$tireInfoList;
                        this.this$0.updateTirePressureActivity(null);
                    } catch (Exception e) {
                        Log.i("_329_MsgMgr", "parse: " + e);
                    }
                }
            }

            private final String getTemperature(int i) {
                return Integer.valueOf(i).equals(255) ? "--" : i + ' ' + this.mTemperatureUnit;
            }

            private final String getTirePressure(int i) {
                return Integer.valueOf(i).equals(255) ? "-.-" : (i / 10.0f) + " bar";
            }
        });
        sparseArray.append(73, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$9
            private final SparseArray<String> tirePositions;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0x49 胎压报警提示信息");
                SparseArray<String> sparseArray2 = new SparseArray<>();
                sparseArray2.put(3, "_304_left_front");
                sparseArray2.append(5, "_304_right_front");
                sparseArray2.append(7, "_304_left_rear");
                sparseArray2.append(9, "_304_right_rear");
                this.tirePositions = sparseArray2;
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                if (booleanRef.element || !isDataChange()) {
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                MsgMgr msgMgr = this.this$0;
                Context context2 = context;
                for (int i = 0; i < 3; i++) {
                    if (((msgMgr.mCanbusInfoInt[2] >> i) & 1) == 1) {
                        arrayList2.add(new WarningEntity(CommUtil.getStrByResId(context2, "_329_tire_warning_0" + i)));
                    }
                }
                int[] iArr = {3, 5, 7, 9};
                for (int i2 = 0; i2 < 4; i2++) {
                    int i3 = iArr[i2];
                    for (int i4 = 0; i4 < 8; i4++) {
                        if (((msgMgr.mCanbusInfoInt[i3] >> i4) & 1) == 1) {
                            arrayList2.add(new WarningEntity(CommUtil.getStrByResId(context2, this.tirePositions.get(i3)) + CommUtil.getStrByResId(context2, "_329_tire_warning_" + i4)));
                        }
                    }
                }
                GeneralWarningDataData.dataList = arrayList2;
                if (GeneralWarningDataData.dataList.size() > 0) {
                    this.this$0.startWarningActivity(context);
                }
                this.this$0.updateWarningActivity(null);
            }
        });
        sparseArray.append(62, new Parser(this) { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$10
            {
                super(this, "0x3E 能量信息(仅1EV6E 支持)，暂不实现");
            }
        });
        sparseArray.append(63, new Parser(this) { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$11
            {
                super(this, "0x3F 充电状态(仅1EV6E支持)，暂不实现");
            }
        });
        sparseArray.append(50, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$12
            private final ArrayList<DriverUpdateEntity> list;

            {
                super(this.this$0, "0x32 车身信息");
                this.list = new ArrayList<>();
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                if (isDataChange()) {
                    this.list.clear();
                    DriverUpdateEntity driverUpdateEntity = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_250_engine_speed");
                    if (driverUpdateEntity != null) {
                        MsgMgr msgMgr = this.this$0;
                        ArrayList<DriverUpdateEntity> arrayList2 = this.list;
                        int i = msgMgr.mCanbusInfoInt[5] | (msgMgr.mCanbusInfoInt[4] << 8);
                        arrayList2.add(driverUpdateEntity.setValue(Integer.valueOf(i).equals(65535) ? "--" : i + " rpm"));
                    }
                    DriverUpdateEntity driverUpdateEntity2 = (DriverUpdateEntity) this.this$0.mDriveItemIndexHashMap.get("_303_speed");
                    if (driverUpdateEntity2 != null) {
                        MsgMgr msgMgr2 = this.this$0;
                        ArrayList<DriverUpdateEntity> arrayList3 = this.list;
                        int i2 = msgMgr2.mCanbusInfoInt[7] | (msgMgr2.mCanbusInfoInt[6] << 8);
                        arrayList3.add(driverUpdateEntity2.setValue(Integer.valueOf(i2).equals(65535) ? "--" : i2 + " km/h"));
                    }
                    this.this$0.updateGeneralDriveData(this.list);
                    this.this$0.updateDriveDataActivity(null);
                }
            }
        });
        sparseArray.append(135, new MsgMgr$initParsers$1$13(this));
        sparseArray.append(232, new Parser() { // from class: com.hzbhd.canbus.car._329.MsgMgr$initParsers$1$14
            private int avmStatus;
            private final int[] cameraStatus;
            private final ArrayList<PanoramicBtnUpdateEntity> list;
            private int warningType;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.this$0, "0XE8 全景信息");
                this.list = new ArrayList<>();
                this.cameraStatus = new int[6];
            }

            @Override // com.hzbhd.canbus.car._329.MsgMgr.Parser
            public void parse(int dataLength) {
                boolean z = false;
                if (this.avmStatus != this.this$0.mCanbusInfoInt[5]) {
                    int i = this.this$0.mCanbusInfoInt[5];
                    this.avmStatus = i;
                    this.this$0.forceReverse(context, i == 1);
                }
                this.this$0.mCanbusInfoInt[5] = 0;
                int[] iArrCopyOfRange = ArraysKt.copyOfRange(this.this$0.mCanbusInfoInt, 3, 9);
                MsgMgr msgMgr = this.this$0;
                Context context2 = context;
                if (!Arrays.equals(iArrCopyOfRange, this.cameraStatus)) {
                    ArraysKt.copyInto$default(iArrCopyOfRange, this.cameraStatus, 0, 0, 0, 14, (Object) null);
                    this.list.clear();
                    this.list.add(new PanoramicBtnUpdateEntity(0, msgMgr.mCanbusInfoInt[6] == 1));
                    this.list.add(new PanoramicBtnUpdateEntity(1, msgMgr.mCanbusInfoInt[7] == 1));
                    this.list.add(new PanoramicBtnUpdateEntity(2, msgMgr.mCanbusInfoInt[4] == 1));
                    this.list.add(new PanoramicBtnUpdateEntity(3, msgMgr.mCanbusInfoInt[8] == 1));
                    this.list.add(new PanoramicBtnUpdateEntity(4, msgMgr.mCanbusInfoInt[3] == 1));
                    this.list.add(new PanoramicBtnUpdateEntity(5, msgMgr.mCanbusInfoInt[3] == 2));
                    GeneralParkData.dataList = this.list;
                    msgMgr.updateParkUi(null, context2);
                }
                int i2 = (this.this$0.mCanbusInfoInt[2] >> 4) & 255;
                Context context3 = context;
                MsgMgr msgMgr2 = this.this$0;
                if (1 <= i2 && i2 < 5) {
                    z = true;
                }
                if (!z || this.warningType == i2) {
                    return;
                }
                this.warningType = i2;
                GeneralDisplayMsgData.displayMsg = CommUtil.getStrByResId(context3, "_329_pano_warn_" + i2);
                msgMgr2.sendDisplayMsgView(context3);
            }
        });
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) bHours24H, (byte) bMins, 0, isGpsTime ? (byte) 1 : (byte) 0, 0, (byte) bYear2Dig, (byte) bMonth, (byte) bDay, 0});
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

    public final void updateSettingItem(String title, Object value) {
        SettingUpdateEntity<Object> settingUpdateEntity;


        SettingUpdateEntity<Object> settingUpdateEntity2 = this.mSettingItemIndexHashMap.get(title);
        if (settingUpdateEntity2 != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(settingUpdateEntity2.setValue(value));
            if (Intrinsics.areEqual(title, "support_panorama") && (settingUpdateEntity = this.mSettingItemIndexHashMap.get("_55_0xE8_data4")) != null) {
                arrayList.add(settingUpdateEntity.setEnable(value.equals(1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    public final void updateBubble(Context context, boolean isShowBubble) {

        GeneralBubbleData.isShowBubble = isShowBubble;
        updateBubble(context);
    }
}
