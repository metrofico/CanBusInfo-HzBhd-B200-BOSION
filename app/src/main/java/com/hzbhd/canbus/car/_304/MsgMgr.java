package com.hzbhd.canbus.car._304;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.car_cus._304.activity.AirActivity;
import com.hzbhd.canbus.car_cus._304.data.MyGeneralData;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.control.CanbusControlAction;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDriveData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.tencent.bugly.BuglyStrategy;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static final String SHARE_304_WARN_COUNT = "share_304_warn_count";
    public static final int VEHICLE_TYPE_HIGH_CONFIG = 1;
    public static final int VEHICLE_TYPE_LOW_CONFIG = 0;
    private int mBreakSystemFaultData;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private SparseArray<int[]> mCanbusDataArray;
    private int mDiiferentId;
    private int mEachId;
    private int mEpsFualtStatus;
    private boolean mIsShiftParking;
    CusPanoramicView mPanoramicView;
    private int[] mRadarFaults;
    private int[] mRadarInfos;
    private WarningManger mWmAirInfo;
    private WarningManger mWmBaseInfo;
    private WarningManger mWmBattery;
    private WarningManger mWmBrakeSystem;
    private WarningManger mWmEpb;
    private WarningManger mWmOther;
    private WarningManger mWmPowerSystem;
    private WarningManger mWmRadar;
    private WarningManger mWmTpmsInfo;
    private WarningManger mWmTrackInfo;
    private SparseArray<VoiceBoardcastItem> voiceBoardcastItemArray;
    private final String TAG = "_304_MsgMgr";
    private final int DATA_TYPE = 1;
    private final boolean IS_SHOW_TRACK = true;
    private final int TRACK_MAX = 510;
    private final int TRACK_MID = 7200;
    private final int SHIFT_POSITION_PARKING = 0;
    private final int SHIFT_POSITION_REVERSE = 1;
    private final int SHIFT_POSITION_NEUTRAL = 2;
    private final int SHIFT_POSITION_DRIVE = 3;
    private final int SHIFT_POSITION_ECO = 4;
    private final int EPB_STATUS_PARKAPPLIED = 1;
    private final int EPB_STATUS_RELEASED = 2;
    private final int EPB_STATUS_COMPLETELYRELEASED = 3;
    private final int EPB_STATUS_APPLYING = 4;
    private final int EPB_STATUS_RELEASING = 5;
    private String YYY_VOICE_BROADCAST_2 = "请拉起电子手刹";
    private String YYY_VOICE_BROADCAST_3 = "小灯未关闭";
    private String YYY_VOICE_BROADCAST_4 = "请踩制动启动";
    private String YYY_VOICE_BROADCAST_5 = "请挂驻车档启动";
    private String YYY_VOICE_BROADCAST_6 = "请挂驻车档";
    private String YYY_VOICE_BROADCAST_7 = "电机过热，请注意";
    private String YYY_VOICE_BROADCAST_11 = "动力系统准备就绪";
    private String YYY_VOICE_BROADCAST_12 = "动力电池电量低，请充电";
    private String YYY_VOICE_BROADCAST_13 = "请系好安全带";
    private String YYY_VOICE_BROADCAST_14 = "您已超速，请减速慢行";
    private String YYY_VOICE_BROADCAST_15 = "车门未关，请注意";
    private boolean mIsVoiceBcFinished = false;
    private HashMap<Constant.RadarLocation, Integer> mLocationMap = new HashMap<>();
    private List<OnWindLevelChangeListener> mOnWindLevelChangeListenerList = new ArrayList();

    public interface OnWindLevelChangeListener {
        void onChange();
    }

    private interface WarningHelper {
        void initAlarmArray();

        void updateWarningList(int[] iArr);
    }

    private void set0x50PowerSystemData(Context context) {
    }

    private void set0x59Tbox249Data(Context context) {
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -126, 46, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mDiiferentId = getCurrentCanDifferentId();
        this.mEachId = getCurrentEachCanId();
        MyGeneralData.mPedestrianRemind = true;
        this.mCanbusDataArray = new SparseArray<>();
        initWarningData(context);
        initVoiceBoardcastItems();
        startVoiceBoardcastThread();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 30) {
            set0x1ERearRadarData(context);
        }
        if (i == 89) {
            set0x59Tbox249Data(context);
            return;
        }
        if (i == 127) {
            set0x7FVersionData(context);
            return;
        }
        if (i == 36) {
            set0x24VehicleBaseInfo(context);
            return;
        }
        if (i == 37) {
            set0x25TpmsData(context);
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
        if (i == 81) {
            set0x51brakeSystemData(context);
            return;
        }
        if (i == 82) {
            set0x52BatteryData(context);
            return;
        }
        switch (i) {
            case 84:
                set0x54OtherFaultData(context);
                break;
            case 85:
                set0x55VoiceBoardcast();
                break;
            case 86:
                set0x56EpbInfo(context);
                break;
            case 87:
                set0x57Vcu255Data(context);
                break;
        }
    }

    private void set0x1ERearRadarData(Context context) {
        if (this.mRadarInfos != Arrays.copyOfRange(this.mCanBusInfoInt, 2, 8)) {
            this.mRadarInfos = Arrays.copyOfRange(this.mCanBusInfoInt, 2, 8);
            RadarInfoUtil.mMinIsClose = true;
            this.mLocationMap.put(Constant.RadarLocation.REAR_LEFT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[2], 3)));
            this.mLocationMap.put(Constant.RadarLocation.REAR_MID_LEFT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3], 4)));
            this.mLocationMap.put(Constant.RadarLocation.REAR_MID_RIGHT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[4], 4)));
            this.mLocationMap.put(Constant.RadarLocation.REAR_RIGHT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[5], 3)));
            this.mLocationMap.put(Constant.RadarLocation.FRONT_RIGHT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[6], 3)));
            this.mLocationMap.put(Constant.RadarLocation.FRONT_LEFT, Integer.valueOf(DataHandleUtils.rangeNumber(this.mCanBusInfoInt[7], 3)));
            MyGeneralData.mRadarLocationMap = this.mLocationMap;
            updateRadar(context);
        }
        if (this.mRadarFaults != Arrays.copyOfRange(this.mCanBusInfoInt, 8, 10)) {
            this.mRadarFaults = Arrays.copyOfRange(this.mCanBusInfoInt, 8, 10);
            this.mWmRadar.updateWarningList(this.mCanBusInfoInt);
            updateWarnActivity(context);
        }
    }

    private void set0x24VehicleBaseInfo(Context context) {
        this.mWmBaseInfo.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x25TpmsData(Context context) {
        this.mWmTpmsInfo.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x28AirData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        bArr[4] = 0;
        bArr[7] = 0;
        if (isAirMsgRepeat(bArr)) {
            return;
        }
        GeneralAirData.power = !DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        MyGeneralData.mBlowMode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 3);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        MyGeneralData.ptc = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        MyGeneralData.mTemperatureGear = DataHandleUtils.rangeNumber(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 5), 1, 15);
        MyGeneralData.mTemperature = (MyGeneralData.mTemperatureGear + 17) + getTempUnitC(context);
        MyGeneralData.etc_power = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        MyGeneralData.etc_status = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 2);
        MyGeneralData.etc_close_ptc_ac = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[6]);
        MyGeneralData.compressor_status = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 2);
        MyGeneralData.evaporator_temperature = new DecimalFormat("0.0").format(this.mCanBusInfoInt[7] * 0.1f) + getTempUnitC(context);
        updateAirActivity(context);
        for (OnWindLevelChangeListener onWindLevelChangeListener : this.mOnWindLevelChangeListenerList) {
            if (onWindLevelChangeListener != null) {
                try {
                    onWindLevelChangeListener.onChange();
                } catch (Exception e) {
                    Log.i("ljqdebug", "set0x28AirData: " + e.toString());
                }
            }
        }
        this.mWmAirInfo.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x29TrackData(Context context) {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 7200, 12300, 16);
        updateParkUi(null, context);
        int i = this.mEpsFualtStatus;
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[4];
        if (i != i2) {
            this.mEpsFualtStatus = i2;
            this.mWmTrackInfo.updateWarningList(iArr);
            updateWarnActivity(context);
        }
    }

    private void set0x7FVersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x51brakeSystemData(Context context) {
        int i = this.mBreakSystemFaultData;
        int[] iArr = this.mCanBusInfoInt;
        int i2 = iArr[2];
        if (i != i2) {
            this.mBreakSystemFaultData = i2;
            this.mWmBrakeSystem.updateWarningList(iArr);
            updateWarnActivity(context);
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (((iArr2[3] | (iArr2[2] << 8)) * 5626) / BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH > 20) {
            FutureUtil.instance.setParking(false);
        }
    }

    private void set0x52BatteryData(Context context) {
        this.mWmBattery.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x53WindowLightData(Context context) {
        String string;
        String string2;
        String string3;
        MyGeneralData.mIsRoofInit = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        MyGeneralData.mRoofStatus = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 2);
        MyGeneralData.mIsRoofAntipinchActive = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        MyGeneralData.mRoofPosition = this.mCanBusInfoInt[3];
        MyGeneralData.mIsAtmosLampOn = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        MyGeneralData.mAtmosLampR = this.mCanBusInfoInt[5];
        MyGeneralData.mAtmosLampG = this.mCanBusInfoInt[6];
        MyGeneralData.mAtmosLampB = this.mCanBusInfoInt[7];
        MyGeneralData.mAtmosLampBrightness = this.mCanBusInfoInt[8];
        if (MyGeneralData.mIsRoofInit) {
            string = context.getString(R.string._304_initializing);
        } else {
            string = context.getString(R.string._253_normal);
        }
        int i = MyGeneralData.mRoofStatus;
        String string4 = "";
        if (i == 0) {
            string2 = context.getString(R.string._304_opening);
        } else if (i == 1) {
            string2 = context.getString(R.string._304_closing);
        } else if (i == 2) {
            string2 = context.getString(R.string._123_divice_status_6);
        } else {
            string2 = i != 3 ? "" : context.getString(R.string._250_invalid);
        }
        if (MyGeneralData.mIsRoofAntipinchActive) {
            string3 = context.getString(R.string._283_esc_cativate);
        } else {
            string3 = context.getString(R.string._304_not_active);
        }
        if (MyGeneralData.mRoofPosition >= 0 && MyGeneralData.mRoofPosition <= 99) {
            string4 = context.getString(R.string._304_in_warping_area);
        } else if (MyGeneralData.mRoofPosition == 100) {
            string4 = context.getString(R.string._304_in_sliding_area);
        } else if (MyGeneralData.mRoofPosition >= 101 && MyGeneralData.mRoofPosition <= 200) {
            string4 = context.getString(R.string._304_at_closing_point);
        } else if (MyGeneralData.mRoofPosition == 255) {
            string4 = context.getString(R.string._304_uninitialized);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(5, 0, string));
        arrayList.add(new DriverUpdateEntity(5, 1, string2));
        arrayList.add(new DriverUpdateEntity(5, 2, string3));
        arrayList.add(new DriverUpdateEntity(5, 3, string4));
        GeneralDriveData.dataList = arrayList;
        updateDriveDataActivity(null);
        new String[]{"_304_atoms_lamp_r", "_304_atoms_lamp_g", "_304_atoms_lamp_b", "_250_ambient_light_brightness", "_304_atoms_lamp_control"};
    }

    private void set0x54OtherFaultData(Context context) {
        this.mWmOther.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x55VoiceBoardcast() {
        if (isDataChange(this.mCanBusInfoInt)) {
            for (int i = 0; i < 2; i++) {
                for (int i2 = 0; i2 < 8; i2++) {
                    int i3 = (i << 4) | i2;
                    if (this.voiceBoardcastItemArray.get(i3) != null) {
                        this.voiceBoardcastItemArray.get(i3).setPlay((this.mCanBusInfoInt[i + 2] & (1 << i2)) != 0);
                    }
                }
            }
        }
    }

    private void set0x56EpbInfo(Context context) {
        Log.i("_304_MsgMgr", "set0x56EpbInfo: position <--> " + MyGeneralData.mShiftLevelPosition + ", mIsShiftParking <--> " + this.mIsShiftParking);
        Log.i("_304_MsgMgr", "set0x56EpbInfo: epbStatus <--> " + DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3));
        this.voiceBoardcastItemArray.get(2).setPlay(this.mIsShiftParking && (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 3) == 2));
        this.mWmEpb.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    private void set0x57Vcu255Data(Context context) {
        MyGeneralData.mShiftLevelPosition = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
        Log.i("_304_MsgMgr", "set0x57Vcu255Data: position <--> " + MyGeneralData.mShiftLevelPosition);
        boolean z = MyGeneralData.mShiftLevelPosition == 0;
        this.mIsShiftParking = z;
        if (z) {
            FutureUtil.instance.setParking(true);
        }
        this.mWmPowerSystem.updateWarningList(this.mCanBusInfoInt);
        updateWarnActivity(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -112, ByteCompanionObject.MAX_VALUE});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        Log.i("_304_MsgMgr", "voiceControlInfo: " + str);
        if (CanbusControlAction.VoiceBoardcast.VOICE_BROADCAST_IS_FINISHED.equals(str)) {
            this.mIsVoiceBcFinished = true;
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

    /* JADX INFO: Access modifiers changed from: private */
    public String getFualt(Context context, int i) {
        return context.getString(i) + context.getString(R.string.ac_diagnostic_info_item_6);
    }

    private abstract class WarningManger implements WarningHelper {
        public SparseArray<WarningEntity> alarmArray;
        private List<WarningEntity> entityList;

        public WarningManger(String str) {
            Log.i("_304_MsgMgr", "WarningManger: title:" + str);
            this.entityList = new ArrayList();
            SparseArray<WarningEntity> sparseArray = new SparseArray<>();
            this.alarmArray = sparseArray;
            sparseArray.put(-1, new WarningEntity(str));
            initAlarmArray();
        }

        @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
        public void updateWarningList(int[] iArr) {
            this.entityList.clear();
            this.entityList.add(this.alarmArray.get(-1));
        }

        public List<WarningEntity> getEntityList() {
            return this.entityList;
        }

        public void setEntityList(List<WarningEntity> list) {
            this.entityList.addAll(list);
        }
    }

    private void initWarningData(final Context context) {
        this.mWmRadar = new WarningManger(context.getString(R.string._304_radar_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.1
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(7, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_front_left_sensor)));
                this.alarmArray.append(6, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_front_right_sensor)));
                this.alarmArray.append(5, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_rear_left_sensor)));
                this.alarmArray.append(4, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_rear_left_mid_sensor)));
                this.alarmArray.append(3, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_rear_right_mid_sensor)));
                this.alarmArray.append(2, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_rear_right_sensor)));
                this.alarmArray.append(1, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_pdc_system)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 7; i > 0; i--) {
                    if ((iArr[8] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmBaseInfo = new WarningManger(context.getString(R.string._304_light_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.2
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(6, new WarningEntity("\t" + context.getString(R.string._304_brake_light_fault)));
                this.alarmArray.append(4, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_right_turn_lamp)));
                this.alarmArray.append(3, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_left_trun_lamp)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 3; i < 7; i++) {
                    if (i != 5 && (iArr[5] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmTpmsInfo = new WarningManger(context.getString(R.string._304_tpms_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.3
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(7, new WarningEntity("\t" + context.getString(R.string._304_right_rear) + context.getString(R.string._304_tire_pressure) + context.getString(R.string._304_sensor_low_power)));
                this.alarmArray.append(6, new WarningEntity("\t" + context.getString(R.string._304_left_rear) + context.getString(R.string._304_tire_pressure) + context.getString(R.string._304_sensor_low_power)));
                this.alarmArray.append(5, new WarningEntity("\t" + context.getString(R.string._304_right_front) + context.getString(R.string._304_tire_pressure) + context.getString(R.string._304_sensor_low_power)));
                this.alarmArray.append(4, new WarningEntity("\t" + context.getString(R.string._304_left_front) + context.getString(R.string._304_tire_pressure) + context.getString(R.string._304_sensor_low_power)));
                this.alarmArray.append(3, new WarningEntity("\t" + context.getString(R.string._304_tpms_system_fault)));
                this.alarmArray.append(19, new WarningEntity("\t" + context.getString(R.string._304_right_rear) + context.getString(R.string._304_tire) + context.getString(R.string._304_sensor_temp_too_high)));
                this.alarmArray.append(18, new WarningEntity("\t" + context.getString(R.string._304_left_rear) + context.getString(R.string._304_tire) + context.getString(R.string._304_sensor_temp_too_high)));
                this.alarmArray.append(17, new WarningEntity("\t" + context.getString(R.string._304_right_front) + context.getString(R.string._304_tire) + context.getString(R.string._304_sensor_temp_too_high)));
                this.alarmArray.append(16, new WarningEntity("\t" + context.getString(R.string._304_left_front) + context.getString(R.string._304_tire) + context.getString(R.string._304_sensor_temp_too_high)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 3; i < 8; i++) {
                    if ((iArr[2] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                for (int i2 = 0; i2 < 4; i2++) {
                    if ((iArr[3] & (1 << i2)) != 0) {
                        arrayList.add(this.alarmArray.get(i2 + 16));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmAirInfo = new WarningManger(context.getString(R.string.geely_e6_fualt_4)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.4
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(0, new WarningEntity("\t" + context.getString(R.string._304_compressor_fault)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                if (DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanBusInfoInt[6], 4, 2) == 2) {
                    arrayList.add(this.alarmArray.get(0));
                }
                setEntityList(arrayList);
            }
        };
        this.mWmTrackInfo = new WarningManger(context.getString(R.string._304_eps_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.5
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(1, new WarningEntity("\t" + context.getString(R.string._304_eps_light_level_failure)));
                this.alarmArray.append(2, new WarningEntity("\t" + context.getString(R.string._304_eps_heavy_level_failure)));
                this.alarmArray.append(3, new WarningEntity("\t" + context.getString(R.string._304_eps_invalid)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                WarningEntity warningEntity = this.alarmArray.get(DataHandleUtils.getIntFromByteWithBit(MsgMgr.this.mCanBusInfoInt[4], 6, 2));
                if (warningEntity != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(warningEntity);
                    setEntityList(arrayList);
                }
            }
        };
        this.mWmPowerSystem = new WarningManger(context.getString(R.string._304_power_system_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.6
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(6, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_vehicle_system)));
                this.alarmArray.append(7, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_motor_over_temperature)));
                this.alarmArray.append(0, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_dcdc_charge)));
                this.alarmArray.append(1, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_motor_drive)));
                this.alarmArray.append(2, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_vacuum)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 6; i < 8; i++) {
                    if ((iArr[2] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                for (int i2 = 0; i2 < 3; i2++) {
                    if ((iArr[3] & (1 << i2)) != 0) {
                        arrayList.add(this.alarmArray.get(i2));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmBrakeSystem = new WarningManger(context.getString(R.string._304_brake_system_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.7
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(7, new WarningEntity("\t" + context.getString(R.string._304_brake_system_fault)));
                this.alarmArray.append(5, new WarningEntity("\t" + context.getString(R.string._304_esc_fault)));
                this.alarmArray.append(4, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_brake_fluid)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 4; i < 8; i++) {
                    if (i != 6 && (iArr[2] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmBattery = new WarningManger(context.getString(R.string._304_battery_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.8
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(7, new WarningEntity("\t" + context.getString(R.string._304_battery_fault)));
                this.alarmArray.append(6, new WarningEntity("\t" + context.getString(R.string._304_isolation_fault)));
                this.alarmArray.append(5, new WarningEntity("\t" + context.getString(R.string._304_charger_fault)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 5; i < 8; i++) {
                    if ((iArr[2] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                setEntityList(arrayList);
            }
        };
        this.mWmOther = new WarningManger(context.getString(R.string._304_other_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.9
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(6, new WarningEntity("\t" + context.getString(R.string._304_avm_system_self_check_fautl)));
                this.alarmArray.append(5, new WarningEntity("\t" + context.getString(R.string._304_sas_calibrated_false)));
                this.alarmArray.append(4, new WarningEntity("\t" + MsgMgr.this.getFualt(context, R.string._304_airbag_status)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(null);
                ArrayList arrayList = new ArrayList();
                for (int i = 4; i < 7; i++) {
                    if (i != 5 && (iArr[2] & (1 << i)) != 0) {
                        arrayList.add(this.alarmArray.get(i));
                    }
                }
                if (!DataHandleUtils.getBoolBit5(iArr[2])) {
                    arrayList.add(this.alarmArray.get(5));
                }
                setEntityList(arrayList);
            }
        };
        this.mWmEpb = new WarningManger(context.getString(R.string._304_epb_fault)) { // from class: com.hzbhd.canbus.car._304.MsgMgr.10
            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void initAlarmArray() {
                this.alarmArray.append(4, new WarningEntity("\t" + context.getString(R.string._304_epb_fault)));
            }

            @Override // com.hzbhd.canbus.car._304.MsgMgr.WarningManger, com.hzbhd.canbus.car._304.MsgMgr.WarningHelper
            public void updateWarningList(int[] iArr) {
                super.updateWarningList(iArr);
                ArrayList arrayList = new ArrayList();
                if (DataHandleUtils.getBoolBit4(MsgMgr.this.mCanBusInfoInt[3])) {
                    arrayList.add(this.alarmArray.get(4));
                }
                setEntityList(arrayList);
            }
        };
    }

    private void initVoiceBoardcastItems() {
        Log.i("_304_MsgMgr", "initVoiceBoardcastItems: ");
        SparseArray<VoiceBoardcastItem> sparseArray = new SparseArray<>();
        this.voiceBoardcastItemArray = sparseArray;
        sparseArray.put(2, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_2));
        this.voiceBoardcastItemArray.append(3, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_3));
        this.voiceBoardcastItemArray.append(4, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_4));
        this.voiceBoardcastItemArray.append(5, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_5));
        this.voiceBoardcastItemArray.append(6, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_6));
        this.voiceBoardcastItemArray.append(7, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_7));
        this.voiceBoardcastItemArray.append(19, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_11));
        this.voiceBoardcastItemArray.append(20, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_12));
        this.voiceBoardcastItemArray.append(21, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_13));
        this.voiceBoardcastItemArray.append(22, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_14));
        this.voiceBoardcastItemArray.append(23, new VoiceBoardcastItem(this.YYY_VOICE_BROADCAST_15));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.hzbhd.canbus.car._304.MsgMgr$11] */
    private void startVoiceBoardcastThread() {
        final int size = this.voiceBoardcastItemArray.size();
        new Thread() { // from class: com.hzbhd.canbus.car._304.MsgMgr.11
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                int i = 0;
                while (true) {
                    try {
                        if (i >= size) {
                            i = 0;
                        }
                        VoiceBoardcastItem voiceBoardcastItem = (VoiceBoardcastItem) MsgMgr.this.voiceBoardcastItemArray.valueAt(i);
                        if (voiceBoardcastItem.isPlay()) {
                            MsgMgr.this.updateVoiceBroadcast(null, voiceBoardcastItem.getInfo());
                            sleep(voiceBoardcastItem.getInfo().length() * 400);
                        }
                        if (SystemClock.elapsedRealtime() % 3000 == 0) {
                            Log.i("_304_MsgMgr", "run: alive " + SystemClock.elapsedRealtime());
                        }
                        i++;
                    } catch (Exception e) {
                        Log.i("_304_MsgMgr", "run: error <--> " + e.toString());
                        return;
                    }
                }
            }
        }.start();
    }

    private void updateWarnActivity(Context context) {
        GeneralWarningDataData.dataList = addAll(context, this.mWmRadar.getEntityList(), this.mWmBaseInfo.getEntityList(), this.mWmTpmsInfo.getEntityList(), this.mWmAirInfo.getEntityList(), this.mWmTrackInfo.getEntityList(), this.mWmPowerSystem.getEntityList(), this.mWmBrakeSystem.getEntityList(), this.mWmBattery.getEntityList(), this.mWmOther.getEntityList(), this.mWmEpb.getEntityList());
        updateWarningActivity(null);
    }

    private List<WarningEntity> addAll(Context context, List<WarningEntity>... listArr) {
        ArrayList arrayList = new ArrayList();
        int size = 0;
        for (List<WarningEntity> list : listArr) {
            if (list.size() > 1) {
                arrayList.addAll(list);
                size += list.size() - 1;
            }
        }
        SharePreUtil.setIntValue(context, SHARE_304_WARN_COUNT, size);
        return arrayList;
    }

    private class VoiceBoardcastItem {
        private final String info;
        private boolean isPlay;

        public VoiceBoardcastItem(String str) {
            this.info = str;
        }

        public boolean isPlay() {
            return this.isPlay;
        }

        public void setPlay(boolean z) {
            Log.i("_304_MsgMgr", "setPlay: \ninfo <--> " + this.info + "\nisPlay <--> " + z);
            this.isPlay = z;
        }

        public String getInfo() {
            return this.info;
        }
    }

    private void updateAirActivity(Context context) {
        if (getActivity() instanceof AirActivity) {
            updateActivity(null);
        }
    }

    private void updateRadar(Context context) {
        getMyPanoramicView(context).refreshRadar();
    }

    private CusPanoramicView getMyPanoramicView(Context context) {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (CusPanoramicView) UiMgrFactory.getCanUiMgr(context).getCusPanoramicView(context);
        }
        return this.mPanoramicView;
    }

    public void registOnWindLevelChangeListener(OnWindLevelChangeListener onWindLevelChangeListener) {
        Log.i("ljqdebug", "registOnWindLevelChangeListener: " + onWindLevelChangeListener);
        this.mOnWindLevelChangeListenerList.add(onWindLevelChangeListener);
    }
}
