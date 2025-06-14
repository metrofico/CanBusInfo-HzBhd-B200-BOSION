package com.hzbhd.canbus.car._317;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.constant.share.lcd.LcdInfoShare;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u001e\u0018\u0000 F2\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016J\u001c\u0010\u001c\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u000bH\u0016Jp\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u00062\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u0006H\u0016J\u001a\u0010-\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0018\u00010\u00152\u0006\u0010.\u001a\u00020\u0004H\u0002J\u0012\u0010/\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u00100\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0016J\u0012\u00101\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0006\u00102\u001a\u00020)J\u0006\u00103\u001a\u00020)J\u001a\u00104\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u00105\u001a\u00020\u0006H\u0002J\u001a\u00106\u001a\u00020\u00042\b\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u00107\u001a\u00020\u0006H\u0002J\u0012\u00108\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u00109\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010:\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010;\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010<\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010=\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010>\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\b\u0010?\u001a\u00020\u001aH\u0002J\u0012\u0010@\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010A\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010B\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eH\u0002J\u0010\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020)H\u0016J\u0016\u0010E\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020\u00042\u0006\u00107\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0015\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/hzbhd/canbus/car/_317/MsgMgr;", "Lcom/hzbhd/canbus/msg_mgr/AbstractMsgMgr;", "()V", "TAG", "", "mCanId", "", "mCanbusDataArray", "Landroid/util/SparseArray;", "", "mCanbusInfoByte", "", "mCanbusInfoInt", "mContext", "Landroid/content/Context;", "mDecimalFormat0p0", "Ljava/text/DecimalFormat;", "mDifferentId", "mEachId", "mSettingItemIndeHashMap", "Ljava/util/HashMap;", "Lcom/hzbhd/canbus/entity/SettingUpdateEntity;", "", "mUiMgr", "Lcom/hzbhd/canbus/car/_317/UiMgr;", "afterServiceNormalSetting", "", "context", "canbusInfoChange", "canbusInfo", "dateTimeRepCanbus", "bYearTotal", "bYear2Dig", "bMonth", "bDay", "bHours", "bMins", "bSecond", "bHours24H", "systemDateFormat", "isFormat24H", "", "isFormatPm", "isGpsTime", "dayOfWeek", "getSettingUpdateEntity", LcdInfoShare.MediaInfo.title, "initAmplifier", "initCommand", "initSettingsItem", "is2016PajeroSport", "is2020PajeroSport", "realKeyLongClick1", "key", "resolveAirTemperature", "value", "set0x1DFrontRadarData", "set0x1ERearRadarData", "set0x20WheelKeyData", "set0x24BaseData", "set0x29TrackData", "set0x30VersionData", "set0x35VehicleData", "set0x40Vehicle2Data", "set0x41AmplifierData", "set0x55AirData", "set0x60TireData", "sourceSwitchNoMediaInfoChange", "isPowerOff", "updateSettingItem", "Companion", "CanBusInfo_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgr extends AbstractMsgMgr {
    public static final int AMPLIFIER_POSITION_OFFSET = 11;
    public static final int AMPLIFIER_PROGRESS_OFFSET = 2;
    public static final String SHARE_317_LEFT_RIGHT_HAND = "share_317_left_right_hand";
    public static final int VEHICLE_TYPE_2016_PAJERO_SPORT = 2;
    public static final int VEHICLE_TYPE_2020_PAJERO_SPORT = 1;
    private int mCanId;
    private Context mContext;
    private int mDifferentId;
    private int mEachId;
    private UiMgr mUiMgr;
    private final String TAG = "_317_MsgMgr";
    private int[] mCanbusInfoInt = new int[0];
    private byte[] mCanbusInfoByte = new byte[0];
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private HashMap<String, SettingUpdateEntity<Object>> mSettingItemIndeHashMap = new HashMap<>();
    private final DecimalFormat mDecimalFormat0p0 = new DecimalFormat("0.0");

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        this.mDifferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        UiMgrInterface canUiMgr = UiMgrFactory.getCanUiMgr(context);
        Intrinsics.checkNotNull(canUiMgr, "null cannot be cast to non-null type com.hzbhd.canbus.car._317.UiMgr");
        this.mUiMgr = (UiMgr) canUiMgr;
        initSettingsItem(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        switch (this.mEachId) {
            case 0:
                Log.i(this.TAG, "initCommand: 13 款欧蓝德高配");
                break;
            case 1:
                Log.i(this.TAG, "initCommand: 17 款欧蓝德高配");
                break;
            case 2:
                Log.i(this.TAG, "initCommand: 16 款帕杰罗劲畅高配");
                break;
            case 3:
                Log.i(this.TAG, "initCommand: 13 款欧蓝德低配");
                break;
            case 4:
                Log.i(this.TAG, "initCommand: 17 款欧蓝德低配");
                break;
            case 5:
                Log.i(this.TAG, "initCommand: 16 款帕杰罗劲畅低配");
                break;
            case 6:
                Log.i(this.TAG, "initCommand: 帕杰罗 V97 高配（空调控制为可选功能）");
                break;
            case 7:
                Log.i(this.TAG, "initCommand: 帕杰罗 V97 低配（空调控制为可选功能）");
                break;
            case 8:
                Log.i(this.TAG, "initCommand: 20 款帕杰罗劲畅高配");
                break;
            case 9:
                Log.i(this.TAG, "initCommand: 20 款帕杰罗劲畅低配");
                break;
        }
        initAmplifier(context);
    }

    private final void initAmplifier(Context context) {
        int intValue;
        if (context != null) {
            intValue = SharePreUtil.getIntValue(context, SHARE_317_LEFT_RIGHT_HAND, 0);
            getAmplifierData(context, getCanId(), UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
        } else {
            intValue = 0;
        }
        byte b = (byte) intValue;
        final Iterator it = ArrayIteratorKt.iterator(new byte[][]{new byte[]{22, -127, 1, 0}, new byte[]{22, -30, (byte) this.mEachId}, new byte[]{22, -18, b, 0}, new byte[]{22, -18, b, 0}, new byte[]{22, -18, b, 0}, new byte[]{22, -124, 9, 1}, new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 11)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 11)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 8, (byte) GeneralAmplifierData.frontRear}});
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._317.MsgMgr$initAmplifier$2$1$1
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
        Intrinsics.checkNotNullExpressionValue(byteArrayToIntArray, "getByteArrayToIntArray(canbusInfo)");
        this.mCanbusInfoInt = byteArrayToIntArray;
        Intrinsics.checkNotNull(canbusInfo);
        this.mCanbusInfoByte = canbusInfo;
        int i = this.mCanbusInfoInt[1];
        if (i == 29) {
            set0x1DFrontRadarData(context);
            return;
        }
        if (i == 30) {
            set0x1ERearRadarData(context);
            return;
        }
        if (i == 32) {
            set0x20WheelKeyData(context);
            return;
        }
        if (i == 36) {
            set0x24BaseData(context);
            return;
        }
        if (i == 41) {
            set0x29TrackData(context);
            return;
        }
        if (i == 53) {
            set0x35VehicleData(context);
            return;
        }
        if (i == 85) {
            set0x55AirData(context);
            return;
        }
        if (i == 96) {
            set0x60TireData(context);
            return;
        }
        if (i == 48 || i == 49) {
            set0x30VersionData(context);
        } else if (i == 64) {
            set0x40Vehicle2Data();
        } else {
            if (i != 65) {
                return;
            }
            set0x41AmplifierData(context);
        }
    }

    private final void set0x20WheelKeyData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            switch (this.mCanbusInfoInt[2]) {
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
        }
    }

    private final void set0x24BaseData(Context context) {
        int[] iArr = this.mCanbusInfoInt;
        iArr[2] = iArr[2] & com.hzbhd.canbus.car._464.MsgMgr.REAR_DISC_MODE;
        iArr[3] = iArr[3] & 16;
        if (isDataChange(iArr)) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
            GeneralDoorData.isShowSeatBelt = true;
            GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[3]);
            updateDoorView(context);
        }
    }

    private final void set0x41AmplifierData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            GeneralAmplifierData.frontRear = this.mCanbusInfoInt[2] - 11;
            GeneralAmplifierData.leftRight = this.mCanbusInfoInt[3] - 11;
            GeneralAmplifierData.bandBass = this.mCanbusInfoInt[5] - 2;
            GeneralAmplifierData.bandTreble = this.mCanbusInfoInt[6] - 2;
            GeneralAmplifierData.bandMiddle = this.mCanbusInfoInt[7] - 2;
            GeneralAmplifierData.volume = this.mCanbusInfoInt[9];
            updateAmplifierActivity(null);
            saveAmplifierData(context, this.mCanId);
            SettingUpdateEntity<Object> settingUpdateEntity = getSettingUpdateEntity("outlander_simple_car_set_17");
            Intrinsics.checkNotNull(settingUpdateEntity);
            SettingUpdateEntity<Object> settingUpdateEntity2 = getSettingUpdateEntity("_103_punch");
            Intrinsics.checkNotNull(settingUpdateEntity2);
            SettingUpdateEntity value = settingUpdateEntity2.setValue(Integer.valueOf((this.mCanbusInfoInt[8] - 2) - 3));
            Intrinsics.checkNotNull(value);
            SettingUpdateEntity<Object> settingUpdateEntity3 = getSettingUpdateEntity("outlander_simple_car_set_8");
            Intrinsics.checkNotNull(settingUpdateEntity3);
            SettingUpdateEntity<Object> settingUpdateEntity4 = getSettingUpdateEntity("outlander_simple_car_set_9");
            Intrinsics.checkNotNull(settingUpdateEntity4);
            SettingUpdateEntity<Object> settingUpdateEntity5 = getSettingUpdateEntity("outlander_simple_car_set_10");
            Intrinsics.checkNotNull(settingUpdateEntity5);
            SettingUpdateEntity<Object> settingUpdateEntity6 = getSettingUpdateEntity("outlander_simple_car_set_11");
            Intrinsics.checkNotNull(settingUpdateEntity6);
            SettingUpdateEntity<Object> settingUpdateEntity7 = getSettingUpdateEntity("outlander_simple_car_set_12");
            Intrinsics.checkNotNull(settingUpdateEntity7);
            SettingUpdateEntity<Object> settingUpdateEntity8 = getSettingUpdateEntity("amplifier_switch");
            Intrinsics.checkNotNull(settingUpdateEntity8);
            updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntity.setValue(Integer.valueOf(this.mCanbusInfoInt[4])), value.setProgress(this.mCanbusInfoInt[8] - 2), settingUpdateEntity3.setValue(Integer.valueOf(this.mCanbusInfoInt[10])), settingUpdateEntity4.setValue(Integer.valueOf(this.mCanbusInfoInt[11])), settingUpdateEntity5.setValue(Integer.valueOf(this.mCanbusInfoInt[12])), settingUpdateEntity6.setValue(Integer.valueOf(this.mCanbusInfoInt[13])), settingUpdateEntity7.setValue(Integer.valueOf(this.mCanbusInfoInt[14])), settingUpdateEntity8.setValue(Integer.valueOf(this.mCanbusInfoInt[15]))));
            updateSettingActivity(null);
        }
    }

    private final void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanbusInfoByte));
    }

    private final void set0x55AirData(Context context) {
        if (!is2020PajeroSport()) {
            this.mCanbusInfoInt[5] = 0;
        }
        if (isDataChange(this.mCanbusInfoInt)) {
            GeneralAirData.front_left_temperature = resolveAirTemperature(context, this.mCanbusInfoInt[2]);
            GeneralAirData.front_right_temperature = resolveAirTemperature(context, this.mCanbusInfoInt[3]);
            GeneralAirData.front_wind_level = this.mCanbusInfoInt[4] & 15;
            int i = (this.mCanbusInfoInt[4] >>> 4) & 15;
            GeneralAirData.front_left_auto_wind = i == 0;
            GeneralAirData.front_left_blow_head = ArraysKt.contains(new Integer[]{1, 2, 5, 7}, Integer.valueOf(i));
            GeneralAirData.front_left_blow_foot = ArraysKt.contains(new Integer[]{2, 3, 4, 7}, Integer.valueOf(i));
            GeneralAirData.front_left_blow_window = ArraysKt.contains(new Integer[]{4, 6, 5, 7}, Integer.valueOf(i));
            GeneralAirData.rear = ((this.mCanbusInfoInt[5] >>> 2) & 1) == 1;
            GeneralAirData.auto = ((this.mCanbusInfoInt[6] >>> 0) & 1) == 1;
            GeneralAirData.ac = ((this.mCanbusInfoInt[6] >>> 1) & 1) == 1;
            GeneralAirData.sync = ((this.mCanbusInfoInt[6] >>> 2) & 1) == 1;
            GeneralAirData.in_out_cycle = ((this.mCanbusInfoInt[6] >>> 3) & 1) == 0;
            GeneralAirData.rear_defog = ((this.mCanbusInfoInt[6] >>> 6) & 1) == 1;
            GeneralAirData.max_front = ((this.mCanbusInfoInt[6] >>> 7) & 1) == 1;
            GeneralAirData.front_right_auto_wind = GeneralAirData.front_left_auto_wind;
            GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
            GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
            updateAirActivity(context, 1001);
        }
    }

    private final void set0x29TrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], (byte) (bArr[3] & ByteCompanionObject.MAX_VALUE), 0, HotKeyConstant.K_AIR_WIND_INC, 12);
            Log.i(this.TAG, "set0x29TrackData: track " + GeneralParkData.trackAngle);
            updateParkUi(null, context);
        }
    }

    private final void set0x1ERearRadarData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private final void set0x1DFrontRadarData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(7, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private final void set0x35VehicleData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            Log.i(this.TAG, "set0x35VehicleData: " + context);
        }
        updateSpeedInfo(this.mCanbusInfoInt[4]);
    }

    private final void set0x60TireData(Context context) {
        if (is2016PajeroSport() && isDataChange(this.mCanbusInfoInt) && this.mCanbusInfoInt[2] > 0) {
            TireUpdateEntity[] tireUpdateEntityArr = new TireUpdateEntity[1];
            int[] iArr = this.mCanbusInfoInt;
            int i = iArr[2] - 1;
            String[] strArr = new String[3];
            strArr[0] = iArr[4] == 255 ? "" : this.mDecimalFormat0p0.format(Float.valueOf(this.mCanbusInfoInt[4] / 2.94f)) + " PSI";
            strArr[1] = this.mCanbusInfoInt[5] != 255 ? (this.mCanbusInfoInt[5] - 50) + ' ' + getTempUnitC(context) : "";
            strArr[2] = new StringBuilder().append((this.mCanbusInfoInt[6] / 4.0f) * 100).append('%').toString();
            tireUpdateEntityArr[0] = new TireUpdateEntity(i, 0, strArr);
            GeneralTireData.dataList = CollectionsKt.arrayListOf(tireUpdateEntityArr);
            updateTirePressureActivity(null);
        }
    }

    private final void set0x40Vehicle2Data() {
        if (is2016PajeroSport() && isDataChange(this.mCanbusInfoInt)) {
            DriverUpdateEntity[] driverUpdateEntityArr = new DriverUpdateEntity[1];
            driverUpdateEntityArr[0] = new DriverUpdateEntity(0, 0, this.mCanbusInfoInt[5] == 255 ? "" : new StringBuilder().append(this.mCanbusInfoInt[5] / 10.0f).append('V').toString());
            updateGeneralDriveData(CollectionsKt.arrayListOf(driverUpdateEntityArr));
            updateDriveDataActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int bYearTotal, int bYear2Dig, int bMonth, int bDay, int bHours, int bMins, int bSecond, int bHours24H, int systemDateFormat, boolean isFormat24H, boolean isFormatPm, boolean isGpsTime, int dayOfWeek) {
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    private final void initSettingsItem(Context context) {
        Log.i(this.TAG, "initSettingsItem: ");
        UiMgr uiMgr = this.mUiMgr;
        Intrinsics.checkNotNull(uiMgr);
        SettingPageUiSet settingUiSet = uiMgr.getSettingUiSet(context);
        Intrinsics.checkNotNullExpressionValue(settingUiSet, "mUiMgr!!.getSettingUiSet(context)");
        Iterator<SettingPageUiSet.ListBean> it = settingUiSet.getList().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            int i3 = 0;
            for (SettingPageUiSet.ListBean.ItemListBean itemListBean : it.next().getItemList()) {
                HashMap<String, SettingUpdateEntity<Object>> map = this.mSettingItemIndeHashMap;
                Intrinsics.checkNotNull(map);
                String titleSrn = itemListBean.getTitleSrn();
                Intrinsics.checkNotNullExpressionValue(titleSrn, "itemListBean.titleSrn");
                map.put(titleSrn, new SettingUpdateEntity<>(i, i3));
                i3++;
            }
            i = i2;
        }
    }

    private final void realKeyLongClick1(Context context, int key) {
        realKeyLongClick1(context, key, this.mCanbusInfoInt[3]);
    }

    private final SettingUpdateEntity<Object> getSettingUpdateEntity(String title) {
        HashMap<String, SettingUpdateEntity<Object>> map = this.mSettingItemIndeHashMap;
        Intrinsics.checkNotNull(map);
        if (map.containsKey(title)) {
            HashMap<String, SettingUpdateEntity<Object>> map2 = this.mSettingItemIndeHashMap;
            Intrinsics.checkNotNull(map2);
            return map2.get(title);
        }
        HashMap<String, SettingUpdateEntity<Object>> map3 = this.mSettingItemIndeHashMap;
        Intrinsics.checkNotNull(map3);
        map3.put(title, new SettingUpdateEntity<>(-1, -1, null));
        return getSettingUpdateEntity(title);
    }

    private final String resolveAirTemperature(Context context, int value) {
        if (value == 16) {
            return "LO";
        }
        if (value == 80) {
            return "HI";
        }
        boolean z = false;
        if (30 <= value && value < 65) {
            z = true;
        }
        return z ? (value / 2.0f) + ' ' + getTempUnitC(context) : "";
    }

    public final void updateSettingItem(String title, Object value) {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(value, "value");
        SettingUpdateEntity[] settingUpdateEntityArr = new SettingUpdateEntity[1];
        SettingUpdateEntity<Object> settingUpdateEntity = getSettingUpdateEntity(title);
        settingUpdateEntityArr[0] = settingUpdateEntity != null ? settingUpdateEntity.setValue(value) : null;
        updateGeneralSettingData(CollectionsKt.arrayListOf(settingUpdateEntityArr));
        updateSettingActivity(null);
    }

    public final boolean is2020PajeroSport() {
        return this.mDifferentId == 1;
    }

    public final boolean is2016PajeroSport() {
        return this.mDifferentId == 2;
    }
}
