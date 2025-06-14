package com.hzbhd.canbus.car._310;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.CanTypeMsg;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.OriginalCarDeviceUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralOriginalCarDeviceData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.OriginalCarDevicePageUiSet;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final int AMPLIFIER_BAND_OFFSET = 2;
    static final int AMPLIFIER_FAD_MIDDLE_VALUE = 7;
    static final String SHARE_IS_SUPPORT_PASSENGER_WIND_MODE = "share_is_support_passenger_wind_mode";
    private int m0x1EData4;
    private int[] mAirFrontData;
    private int[] mAirRearData;
    private int mAmplifierSettingData;
    private int mCanId;
    private SparseArray<int[]> mCanbusDataArray;
    private byte[] mCanbusInfoByte;
    private int[] mCanbusInfoInt;
    private Context mContext;
    private DecimalFormat mDecimalFormat00;
    private DecimalFormat mDecimalFormat0p0;
    private DecimalFormat mDecimalFormat0p00;
    private int mEachId;
    private boolean mIsBackOpen;
    private boolean mIsBeltTie;
    private boolean mIsFahrenheit;
    private boolean mIsFrontOpen;
    private boolean mIsLeftFrontOpen;
    private boolean mIsLeftRearOpen;
    private boolean mIsPanoramicStart;
    private boolean mIsRightFrontOpen;
    private boolean mIsRightRearOpen;
    private int mIsSkylightOpen;
    private int mMediaType;
    private OriginalCarDevicePageUiSet mOriginalCarDevicePageUiSet;
    private SparseArray<OriginalDeviceData> mOriginalDeviceDataArray;
    private byte mOutDoorTempValue;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap;
    private UiMgr mUiMgr;
    private final String TAG = "_310_MsgMgr";
    private final int DATA_TYPE = 1;
    private final int INVAILE_VALUE = -1;
    private final int DRI_PAGE_HISTORICAL_FUEL_CONSUMPTION = 0;
    private final int DRI_PAGE_LAST_15_MINUTE_FUEL_CONSUMPTION = 1;
    private final int DRI_PAGE_VEHICLE_INFO = 2;
    private final String MEDIA_TYPE_MEDIA_OFF = "MEDIA OFF";
    private final String MEDIA_TYPE_RADIO = "RADIO";
    private final String MEDIA_TYPE_CD_DVD = "CD/DVD";
    private final String MEDIA_TYPE_AUX = "AUX";
    private final String MEDIA_TYPE_USB = "USB";
    private final String MEDIA_TYPE_OTHER = CanTypeMsg.OTHER;

    private String getMuteStatus(boolean z) {
        return z ? "_309_value_4" : "_309_value_3";
    }

    private String getPowerStatus(boolean z) {
        return z ? "_16_value_34" : "_16_value_33";
    }

    private String getYesOrNone(boolean z) {
        return z ? "_16_value_30" : "_16_value_29";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        int i = this.mEachId;
        if (i != 255) {
            CanbusMsgSender.sendMsg(new byte[]{22, -125, 39, (byte) i});
        }
        sendAmplifierInit(context);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.hzbhd.canbus.car._310.MsgMgr$1] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(final Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        new Thread() { // from class: com.hzbhd.canbus.car._310.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                MsgMgr.this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.mEachId = msgMgr.getCurrentEachCanId();
                MsgMgr.this.mCanbusDataArray = new SparseArray();
                MsgMgr.this.mDecimalFormat0p0 = new DecimalFormat("0.0");
                MsgMgr.this.mDecimalFormat0p00 = new DecimalFormat("0.00");
                MsgMgr.this.mDecimalFormat00 = new DecimalFormat("00");
                MsgMgr.this.mAirFrontData = new int[0];
                MsgMgr.this.mAirRearData = new int[0];
                MsgMgr.this.initSettingsItem(context);
                MsgMgr.this.initOriginalDeviceDataArray();
                int componentEnabledSetting = context.getPackageManager().getComponentEnabledSetting(Constant.OriginalDeviceActivity);
                if (componentEnabledSetting == 2 || componentEnabledSetting == 3) {
                    SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
                }
            }
        }.start();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanbusInfoInt = byteArrayToIntArray;
        this.mCanbusInfoByte = bArr;
        int i = byteArrayToIntArray[1];
        if (i == 29) {
            set0x1DData(context);
        }
        if (i == 30) {
            set0x1EData(context);
            return;
        }
        if (i == 32) {
            set0x20WheelKeyData(context);
            return;
        }
        if (i == 42) {
            set0x2AVehicleData(context);
            return;
        }
        if (i == 53) {
            set0x35WheelTrackData(context);
            return;
        }
        if (i == 98) {
            set0x62OriginalDeviceData(context);
            return;
        }
        if (i == 35) {
            set0x23HisFuelConsData();
            return;
        }
        if (i == 36) {
            set0x24BaseInfoData(context);
            return;
        }
        switch (i) {
            case 38:
                set0x26VehicleSettingData();
                break;
            case 39:
                set0x27Last15FuelConsData();
                break;
            case 40:
                set0x28AirData(context);
                break;
            default:
                switch (i) {
                    case 48:
                        set0x30VersionData(context);
                        break;
                    case 49:
                        set0x31AmpliferData(context);
                        break;
                    case 50:
                        set0x32SystemInfoData();
                        break;
                }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x20WheelKeyData(android.content.Context r5) {
        /*
            r4 = this;
            int[] r0 = r4.mCanbusInfoInt
            r1 = 2
            r0 = r0[r1]
            r2 = 8
            r3 = 7
            switch(r0) {
                case 1: goto L41;
                case 2: goto L3f;
                case 3: goto L3c;
                case 4: goto L39;
                case 5: goto L42;
                case 6: goto L37;
                case 7: goto L34;
                case 8: goto L31;
                case 9: goto L2e;
                default: goto Lb;
            }
        Lb:
            switch(r0) {
                case 19: goto L2b;
                case 20: goto L28;
                case 21: goto L25;
                case 22: goto L22;
                default: goto Le;
            }
        Le:
            switch(r0) {
                case 129: goto L41;
                case 130: goto L3f;
                case 131: goto L1f;
                case 132: goto L1c;
                case 133: goto L19;
                case 134: goto L16;
                case 135: goto L13;
                case 136: goto L42;
                default: goto L11;
            }
        L11:
            r1 = 0
            goto L42
        L13:
            r1 = 134(0x86, float:1.88E-43)
            goto L42
        L16:
            r1 = 48
            goto L42
        L19:
            r1 = 47
            goto L42
        L1c:
            r1 = 66
            goto L42
        L1f:
            r1 = 65
            goto L42
        L22:
            r1 = 49
            goto L42
        L25:
            r1 = 50
            goto L42
        L28:
            r1 = 46
            goto L42
        L2b:
            r1 = 45
            goto L42
        L2e:
            r1 = 15
            goto L42
        L31:
            r1 = 14
            goto L42
        L34:
            r1 = 187(0xbb, float:2.62E-43)
            goto L42
        L37:
            r1 = 3
            goto L42
        L39:
            r1 = 21
            goto L42
        L3c:
            r1 = 20
            goto L42
        L3f:
            r1 = r2
            goto L42
        L41:
            r1 = r3
        L42:
            r4.realKeyLongClick1(r5, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._310.MsgMgr.set0x20WheelKeyData(android.content.Context):void");
    }

    private void set0x24BaseInfoData(Context context) {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
        GeneralDoorData.skyWindowOpenLevel = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[3]) ? 2 : 0;
        if (isDoorStatusChange()) {
            updateDoorView(context);
        }
    }

    private void set0x28AirData(Context context) {
        int[] iArr = this.mCanbusInfoInt;
        iArr[3] = iArr[3] & 239;
        if (isDataChange(iArr)) {
            GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[6]);
            if (this.mIsFahrenheit ^ GeneralAirData.fahrenheit_celsius) {
                this.mIsFahrenheit = GeneralAirData.fahrenheit_celsius;
                sendOutDoorTemperature(context);
            }
            GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]);
            GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]);
            GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[2]);
            GeneralAirData.negative_ion = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[2]);
            GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[2]);
            GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]);
            GeneralAirData.front_window_heat = DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]);
            GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[3]);
            GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[3]);
            GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4);
            GeneralAirData.front_left_temperature = getAirTemperature(context, this.mCanbusInfoInt[4]);
            GeneralAirData.front_right_temperature = getAirTemperature(context, this.mCanbusInfoInt[5]);
            GeneralAirData.pollrn_removal = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[6]);
            GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[6]);
            GeneralAirData.aqs = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[6]);
            GeneralAirData.clean_air = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[6]);
            GeneralAirData.swing = DataHandleUtils.getBoolBit3(this.mCanbusInfoInt[6]);
            GeneralAirData.rear = DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[6]);
            GeneralAirData.rear_left_temperature = getAirTemperature(context, this.mCanbusInfoInt[7]);
            GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[8]);
            GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[8]);
            GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[8]);
            GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanbusInfoInt[8]);
            GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[8], 0, 4);
            GeneralAirData.rear_right_temperature = getAirTemperature(context, this.mCanbusInfoInt[9]);
            if (SharePreUtil.getBoolValue(context, SHARE_IS_SUPPORT_PASSENGER_WIND_MODE, false)) {
                GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[10]);
                GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[10]);
                GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanbusInfoInt[10]);
            } else {
                GeneralAirData.front_right_blow_window = GeneralAirData.front_left_blow_window;
                GeneralAirData.front_right_blow_head = GeneralAirData.front_left_blow_head;
                GeneralAirData.front_right_blow_foot = GeneralAirData.front_left_blow_foot;
            }
            GeneralAirData.rear_right_blow_head = GeneralAirData.rear_left_blow_head;
            GeneralAirData.rear_right_blow_foot = GeneralAirData.rear_left_blow_foot;
            updateAirActivity(context, getAirWhat());
        }
    }

    private void set0x35WheelTrackData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            byte[] bArr = this.mCanbusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 0, 384, 12);
            updateParkUi(null, context);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void set0x2AVehicleData(android.content.Context r15) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._310.MsgMgr.set0x2AVehicleData(android.content.Context):void");
    }

    private void set0x1DData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x1EData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanbusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
            int i = this.m0x1EData4;
            int i2 = this.mCanbusInfoInt[6];
            if (i != i2) {
                this.m0x1EData4 = i2;
                ArrayList arrayList = new ArrayList();
                arrayList.add(getSettingUpdateEntity("radar_distance").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 6, 1))));
                arrayList.add(getSettingUpdateEntity("_283_radar_switch").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 5, 1))));
                int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 3);
                arrayList.add(getSettingUpdateEntity("radar_volume").setValue(Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit - 1));
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
            }
        }
    }

    private void set0x26VehicleSettingData() {
        if (isDataChange(this.mCanbusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("light_ctrl_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 7, 1))));
            arrayList.add(getSettingUpdateEntity("light_ctrl_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 3))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_3_43").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2))));
            arrayList.add(getSettingUpdateEntity("_55_0x67_data1_bit32").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 2))));
            arrayList.add(getSettingUpdateEntity("_310_speed_auto_lock").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 7, 1))));
            arrayList.add(getSettingUpdateEntity("_310_shift_auto_lock").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 6, 1))));
            arrayList.add(getSettingUpdateEntity("_310_position_p_unlock").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 5, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_2_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 1))));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 3);
            arrayList.add(getSettingUpdateEntity("hiworld_jeep_123_0x60_data1_65").setValue(Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_2_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 7, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_1_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 6, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_1_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 5, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_2_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 4, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_2_7").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 3, 1))));
            arrayList.add(getSettingUpdateEntity("_11_0x26_data2_bit20").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 0, 3))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_1_1").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 7, 1))));
            arrayList.add(getSettingUpdateEntity("_18_vehicle_setting_item_1_0").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 6, 1))));
            arrayList.add(getSettingUpdateEntity("_11_0x26_data3_bit32").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 2, 2))));
            arrayList.add(getSettingUpdateEntity("_55_0x65_data1_bit21").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[5], 0, 2))));
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 2, 3);
            arrayList.add(getSettingUpdateEntity("_310_auto_circulation_sensitivity").setValue(Integer.valueOf(intFromByteWithBit2 + 1)).setProgress(intFromByteWithBit2));
            arrayList.add(getSettingUpdateEntity("_157_seat_auto_move").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[6], 0, 2))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void set0x31AmpliferData(Context context) {
        if (isDataChange(this.mCanbusInfoInt)) {
            GeneralAmplifierData.frontRear = 7 - DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 4, 4);
            GeneralAmplifierData.leftRight = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 0, 4) - 7;
            GeneralAmplifierData.bandBass = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 4, 4) - 2;
            GeneralAmplifierData.bandTreble = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[3], 0, 4) - 2;
            GeneralAmplifierData.bandMiddle = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 4, 4) - 2;
            GeneralAmplifierData.volume = this.mCanbusInfoInt[5];
            updateAmplifierActivity(null);
            saveAmplifierData(context, this.mCanId);
            int[] iArr = this.mCanbusInfoInt;
            int i = iArr[6] | ((iArr[4] & 15) << 8);
            if (this.mAmplifierSettingData != i) {
                this.mAmplifierSettingData = i;
                ArrayList arrayList = new ArrayList();
                arrayList.add(getSettingUpdateEntity("_186_asl").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[4], 3, 1))));
                arrayList.add(getSettingUpdateEntity("_16_title_23").setValue(Integer.valueOf(this.mCanbusInfoInt[6])));
                updateGeneralSettingData(arrayList);
                updateSettingActivity(null);
            }
        }
    }

    private void set0x32SystemInfoData() {
        if (isDataChange(this.mCanbusInfoInt)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(getSettingUpdateEntity("_301_original_car_power_amplifier").setValue(getYesOrNone(DataHandleUtils.getBoolBit0(this.mCanbusInfoInt[2]))));
            arrayList.add(getSettingUpdateEntity("_301_original_car_rear_seat").setValue(getYesOrNone(DataHandleUtils.getBoolBit1(this.mCanbusInfoInt[2]))));
            arrayList.add(getSettingUpdateEntity("_310_panoramic_node").setValue(getYesOrNone(DataHandleUtils.getBoolBit2(this.mCanbusInfoInt[2]))));
            arrayList.add(getSettingUpdateEntity("_310_amplifier_status").setValue(getPowerStatus(DataHandleUtils.getBoolBit6(this.mCanbusInfoInt[2]))));
            arrayList.add(getSettingUpdateEntity("_310_mute_status").setValue(getMuteStatus(DataHandleUtils.getBoolBit7(this.mCanbusInfoInt[2]))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            boolean z = DataHandleUtils.getIntFromByteWithBit(this.mCanbusInfoInt[2], 2, 2) == 3;
            if (this.mIsPanoramicStart ^ z) {
                this.mIsPanoramicStart = z;
                forceReverse(this.mContext, z);
            }
        }
    }

    private void set0x30VersionData(Context context) {
        updateVersionInfo(context, getVersionStr(this.mCanbusInfoByte));
    }

    private void set0x23HisFuelConsData() {
        int i = this.mCanbusInfoInt[2];
        String str = i != 0 ? i != 1 ? i != 2 ? "" : "L/100KM" : "KM/L" : "MPG";
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < 6; i2++) {
            int i3 = i2 * 2;
            int[] iArr = this.mCanbusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, i2, getInfo(getData(iArr[i3 + 2 + 2], iArr[i3 + 1 + 2]), 0.1f, " " + str)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x27Last15FuelConsData() {
        int i = this.mCanbusInfoInt[2];
        String str = i != 0 ? i != 1 ? i != 2 ? "" : "L/100KM" : "KM/L" : "MPG";
        ArrayList arrayList = new ArrayList();
        for (int i2 = 1; i2 <= 15; i2++) {
            int i3 = i2 * 2;
            int[] iArr = this.mCanbusInfoInt;
            arrayList.add(new DriverUpdateEntity(1, i2 - 1, getInfo(getData(iArr[(32 - i3) + 2], iArr[(31 - i3) + 2]), 0.1f, str)));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x62OriginalDeviceData(Context context) {
        OriginalDeviceData originalDeviceData = this.mOriginalDeviceDataArray.get(this.mCanbusInfoInt[2]);
        Bundle bundle = null;
        GeneralOriginalCarDeviceData.mList = null;
        int i = this.mCanbusInfoInt[2];
        if (i == 0) {
            GeneralOriginalCarDeviceData.cdStatus = "MEDIA OFF";
        } else if (i == 1) {
            GeneralOriginalCarDeviceData.cdStatus = "RADIO";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceRadioUpdateEntityList(this.mCanbusInfoInt);
        } else if (i == 2) {
            GeneralOriginalCarDeviceData.cdStatus = "CD/DVD";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanbusInfoInt);
        } else if (i == 3) {
            GeneralOriginalCarDeviceData.cdStatus = "AUX";
        } else if (i == 4) {
            GeneralOriginalCarDeviceData.cdStatus = "USB";
            GeneralOriginalCarDeviceData.mList = getOriginalDeviceCdDvdUsbUpdateEntityList(this.mCanbusInfoInt);
        } else if (i == 255) {
            GeneralOriginalCarDeviceData.cdStatus = CanTypeMsg.OTHER;
        }
        int i2 = this.mMediaType;
        int i3 = this.mCanbusInfoInt[2];
        if (i2 != i3) {
            this.mMediaType = i3;
            OriginalCarDevicePageUiSet originalCarDevicePageUiSet = getOriginalCarDevicePageUiSet(context);
            originalCarDevicePageUiSet.setItems(originalDeviceData.getItemList());
            originalCarDevicePageUiSet.setRowBottomBtnAction(originalDeviceData.getBottomBtns());
            bundle = new Bundle();
            bundle.putBoolean(Constant.BUNDLE_KEY_ORINAL_INIT_VIEW, true);
        }
        updateOriginalCarDeviceActivity(bundle);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        Log.i("_310_MsgMgr", "dateTimeRepCanbus: bHours" + i5 + ", bHours24H" + i8);
        CanbusMsgSender.sendMsg(new byte[]{22, -123, 0, 0, 0, (byte) (i5 % 12), (byte) i6, 0});
        CanbusMsgSender.sendMsg(new byte[]{22, -112, 48, 0});
    }

    void msgMgrUpdateSettingActivity(String str, Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity(str).setValue(obj));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void realKeyLongClick1(Context context, int i) {
        realKeyLongClick1(context, i, this.mCanbusInfoInt[3]);
    }

    private boolean isDoorStatusChange() {
        if (!(this.mIsLeftFrontOpen ^ GeneralDoorData.isLeftFrontDoorOpen) && !(this.mIsRightFrontOpen ^ GeneralDoorData.isRightFrontDoorOpen) && !(this.mIsLeftRearOpen ^ GeneralDoorData.isLeftRearDoorOpen) && !(this.mIsRightRearOpen ^ GeneralDoorData.isRightRearDoorOpen) && !(this.mIsBackOpen ^ GeneralDoorData.isBackOpen) && !(this.mIsBeltTie ^ GeneralDoorData.isSeatBeltTie) && !(this.mIsFrontOpen ^ GeneralDoorData.isFrontOpen) && this.mIsSkylightOpen == GeneralDoorData.skyWindowOpenLevel) {
            return false;
        }
        this.mIsLeftFrontOpen = GeneralDoorData.isLeftFrontDoorOpen;
        this.mIsRightFrontOpen = GeneralDoorData.isRightFrontDoorOpen;
        this.mIsLeftRearOpen = GeneralDoorData.isLeftRearDoorOpen;
        this.mIsRightRearOpen = GeneralDoorData.isRightRearDoorOpen;
        this.mIsBackOpen = GeneralDoorData.isBackOpen;
        this.mIsBeltTie = GeneralDoorData.isSeatBeltTie;
        this.mIsFrontOpen = GeneralDoorData.isFrontOpen;
        this.mIsSkylightOpen = GeneralDoorData.skyWindowOpenLevel;
        return true;
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

    private String getInfo(int i, float f, String str) {
        return i != -1 ? this.mDecimalFormat0p0.format(i * f) + str : "---";
    }

    private int getAirWhat() {
        int[] iArr = this.mCanbusInfoInt;
        int[] iArr2 = {iArr[2], iArr[3], iArr[4], iArr[5], iArr[6] & com.hzbhd.canbus.car._464.MsgMgr.DVD_MODE, iArr[10]};
        int[] iArr3 = {iArr[7], iArr[8], iArr[9]};
        if (!Arrays.equals(this.mAirFrontData, iArr2)) {
            this.mAirFrontData = Arrays.copyOf(iArr2, 6);
            return 1001;
        }
        if (Arrays.equals(this.mAirRearData, iArr3)) {
            return 0;
        }
        this.mAirRearData = Arrays.copyOf(iArr3, 3);
        return 1002;
    }

    private String getAirTemperature(Context context, int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 29) {
            return (i < 33 || i > 38) ? "" : fahrenheit(context, (i - 3) * 0.5f);
        }
        return fahrenheit(context, (i + 35) * 0.5f);
    }

    private String fahrenheit(Context context, float f) {
        if (GeneralAirData.fahrenheit_celsius) {
            return this.mDecimalFormat0p0.format(((f * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(context);
        }
        return f + getTempUnitC(context);
    }

    private void sendOutDoorTemperature(Context context) {
        byte b = this.mOutDoorTempValue;
        updateOutDoorTemp(context, b == 0 ? " " : fahrenheit(context, b));
    }

    private OriginalCarDevicePageUiSet getOriginalCarDevicePageUiSet(Context context) {
        if (this.mOriginalCarDevicePageUiSet == null) {
            this.mOriginalCarDevicePageUiSet = getUiMgr(context).getOriginalCarDevicePageUiSet(context);
        }
        return this.mOriginalCarDevicePageUiSet;
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private void sendAmplifierInit(Context context) {
        if (context != null) {
            getAmplifierData(context, this.mCanId, getUiMgr(context).getAmplifierPageUiSet(context));
        }
        final TimerUtil timerUtil = new TimerUtil();
        timerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._310.MsgMgr.2
            byte[][] commands = {new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 7)}, new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)}, new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)}, new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)}, new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)}, new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume}};
            int i = 0;

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                int i = this.i;
                byte[][] bArr = this.commands;
                if (i < bArr.length) {
                    CanbusMsgSender.sendMsg(bArr[i]);
                    this.i++;
                } else {
                    timerUtil.stopTimer();
                }
            }
        }, 0L, 100L);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        sendAmplifierInit(this.mContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSettingsItem(Context context) {
        Log.i("_310_MsgMgr", "initSettingsItem: ");
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

    private static class OriginalDeviceData {
        private final String[] bottomBtns;
        private final List<OriginalCarDevicePageUiSet.Item> list;

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list) {
            this(list, null);
        }

        public OriginalDeviceData(List<OriginalCarDevicePageUiSet.Item> list, String[] strArr) {
            this.list = list;
            this.bottomBtns = strArr;
        }

        public List<OriginalCarDevicePageUiSet.Item> getItemList() {
            return this.list;
        }

        public String[] getBottomBtns() {
            return this.bottomBtns;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOriginalDeviceDataArray() {
        OriginalDeviceData originalDeviceData = new OriginalDeviceData(new ArrayList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_band", null));
        arrayList.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_frequency", null));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_track", null));
        arrayList2.add(new OriginalCarDevicePageUiSet.Item("music_list", "_186_current_play_time", null));
        SparseArray<OriginalDeviceData> sparseArray = new SparseArray<>();
        this.mOriginalDeviceDataArray = sparseArray;
        sparseArray.put(0, originalDeviceData);
        this.mOriginalDeviceDataArray.put(1, new OriginalDeviceData(arrayList));
        this.mOriginalDeviceDataArray.put(2, new OriginalDeviceData(arrayList2, new String[]{"power"}));
        this.mOriginalDeviceDataArray.put(3, originalDeviceData);
        this.mOriginalDeviceDataArray.put(4, new OriginalDeviceData(arrayList2));
        this.mOriginalDeviceDataArray.put(255, originalDeviceData);
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceCdDvdUsbUpdateEntityList(int[] iArr) {
        int i;
        int i2 = iArr[3];
        String str = "";
        String string = (i2 == 255 || (i = iArr[4]) == 255) ? "" : Integer.toString(i2 | (i << 8));
        if (iArr[5] != 255 && iArr[6] != 255) {
            str = this.mDecimalFormat00.format(iArr[5]) + ":" + this.mDecimalFormat00.format(iArr[6]);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, string));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, str));
        return arrayList;
    }

    private List<OriginalCarDeviceUpdateEntity> getOriginalDeviceRadioUpdateEntityList(int[] iArr) {
        String str;
        String str2;
        int i = (iArr[5] << 8) | iArr[4];
        if (iArr[3] == 16) {
            str2 = i + " KHz";
            str = "AM";
        } else {
            str = "FM" + iArr[3];
            str2 = this.mDecimalFormat0p00.format(i / 100.0f) + " MHz";
        }
        if (i == 65535) {
            str2 = "";
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new OriginalCarDeviceUpdateEntity(0, str));
        arrayList.add(new OriginalCarDeviceUpdateEntity(1, str2));
        return arrayList;
    }
}
