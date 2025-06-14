package com.hzbhd.canbus.car._325;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.ui_set.SettingPageUiSet;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String IS_AIR_LEFT_TEMP = "is_air_left_temp";
    static final String IS_AIR_RIGHT_TEMP = "is_air_right_temp";
    static final String IS_AIR_WIND_LEVEL = "is_air_right_temp";
    private int[] m0x11Data;
    private int[] m0x41Data;
    private byte[] mAirData;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mEachId;
    private boolean mFrontStatus;
    private int mKeyStatus;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private UiMgr mUiMgr;
    private SparseArray<int[]> mCanbusDataArray = new SparseArray<>();
    private final int DATA_TYPE = 1;
    private HashMap<String, SettingUpdateEntity> mSettingItemIndeHashMap = new HashMap<>();

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        initSettingsItem(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        CanbusMsgSender.sendMsg(new byte[]{22, 106, 5, 1, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        switch (byteArrayToIntArray[1]) {
            case 17:
                set0x11CarBaseData(context);
                break;
            case 25:
                set0x19MileageInfo();
                break;
            case 49:
                set0x31AirData();
                break;
            case 65:
                set0x41RadarInfo(context);
                break;
            case 70:
                set0x46TyresInfo();
                break;
            case 72:
                set0x48AIDSInfo();
                break;
            case 100:
                set0x64WindowAndCC();
                break;
            case 104:
                set0x68LightInfo();
                break;
            case 113:
                set0x71ParkAlarm();
                break;
            case 125:
                set0x7dKeyTotal();
                break;
            case 193:
                set0xC1UnitInfo();
                break;
            case 195:
                set0xC3MileageAndMaintenanceInfo();
                break;
            case HotKeyConstant.K_TIME /* 196 */:
                set0xC4TirePressure();
                break;
        }
    }

    private void set0x11CarBaseData(Context context) {
        updateSpeedInfo(this.mCanBusInfoInt[3]);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + " KM/H"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int i = this.mKeyStatus;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.mKeyStatus = i2;
        }
        switch (i2) {
            case 0:
                realKeyLongClick2(this.mContext, 0);
                break;
            case 1:
                realKeyLongClick2(this.mContext, 7);
                break;
            case 2:
                realKeyLongClick2(this.mContext, 8);
                break;
            case 3:
                realKeyLongClick2(this.mContext, 3);
                break;
            case 5:
                realKeyLongClick2(this.mContext, 14);
                break;
            case 6:
                realKeyLongClick2(this.mContext, 15);
                break;
            case 8:
                realKeyLongClick2(this.mContext, 45);
                break;
            case 9:
                realKeyLongClick2(this.mContext, 46);
                break;
            case 10:
                realKeyLongClick2(this.mContext, 2);
                break;
        }
        if (isTrackDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0((byte) iArr[9], (byte) iArr[8], 0, 480, 16);
        }
        updateParkUi(null, context);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[11]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[11]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[11]);
        if (isDoorChange()) {
            updateDoorView(context);
        }
    }

    private void set0x31AirData() {
        if (isAirDataChange()) {
            GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
            GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2);
            GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1);
            GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
            GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            cleanAllBlow();
            int i = this.mCanBusInfoInt[6];
            if (i == 1) {
                GeneralAirData.front_auto_wind_model = true;
            } else if (i == 3) {
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (i == 5) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
            } else if (i == 6) {
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
            } else {
                switch (i) {
                    case 11:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        break;
                    case 12:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                    case 13:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        break;
                    case 14:
                        GeneralAirData.front_left_blow_window = true;
                        GeneralAirData.front_right_blow_window = true;
                        GeneralAirData.front_left_blow_head = true;
                        GeneralAirData.front_right_blow_head = true;
                        GeneralAirData.front_left_blow_foot = true;
                        GeneralAirData.front_right_blow_foot = true;
                        break;
                }
            }
            GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
            SharePreUtil.setIntValue(this.mContext, "is_air_right_temp", this.mCanBusInfoInt[7] & 15);
            GeneralAirData.front_left_temperature = resolverAirTemperature(this.mCanBusInfoInt[8]);
            GeneralAirData.front_right_temperature = resolverAirTemperature(this.mCanBusInfoInt[9]);
            SharePreUtil.setIntValue(this.mContext, IS_AIR_LEFT_TEMP, this.mCanBusInfoInt[8]);
            SharePreUtil.setIntValue(this.mContext, "is_air_right_temp", this.mCanBusInfoInt[9]);
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void set0x46TyresInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_325_setting_2").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(((this.mCanBusInfoInt[2] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_325_setting_3").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setEnable(((this.mCanBusInfoInt[3] >> 7) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x48AIDSInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_3").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x41RadarInfo(Context context) {
        if (isAirDataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(255, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(255, iArr2[6], iArr2[7], iArr2[8], iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, context);
        }
    }

    private void set0x68LightInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_4").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(((this.mCanBusInfoInt[2] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_5").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(((this.mCanBusInfoInt[2] >> 4) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_6").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(((this.mCanBusInfoInt[2] >> 3) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_8").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))).setEnable(((this.mCanBusInfoInt[2] >> 1) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_9").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))).setEnable(((this.mCanBusInfoInt[2] >> 0) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_325_light_1").setValue(Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]).setEnable(((this.mCanBusInfoInt[2] >> 2) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_10").setValue(Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x64WindowAndCC() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_11").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_12").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))).setEnable(((this.mCanBusInfoInt[2] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_13").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))).setEnable(((this.mCanBusInfoInt[2] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_14").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))).setEnable(((this.mCanBusInfoInt[3] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_15").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))).setEnable(((this.mCanBusInfoInt[3] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_16").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 5, 1))).setEnable(((this.mCanBusInfoInt[3] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_17").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 1))).setEnable(((this.mCanBusInfoInt[3] >> 4) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x71ParkAlarm() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_22").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_18").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_19").setValue(Integer.valueOf(this.mCanBusInfoInt[5])).setProgress(this.mCanBusInfoInt[5]).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_20").setValue(Integer.valueOf(this.mCanBusInfoInt[6])).setProgress(this.mCanBusInfoInt[6]).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_21").setValue(Integer.valueOf(this.mCanBusInfoInt[7])).setProgress(this.mCanBusInfoInt[7]).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0xC1UnitInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_23").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_24").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))).setEnable(((this.mCanBusInfoInt[2] >> 6) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_25").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))).setEnable(((this.mCanBusInfoInt[2] >> 5) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_26").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2))).setEnable(((this.mCanBusInfoInt[2] >> 4) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_27").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))).setEnable(((this.mCanBusInfoInt[2] >> 3) & 1) == 1));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_28").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))).setEnable(((this.mCanBusInfoInt[2] >> 2) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte b = (byte) 0;
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) DataHandleUtils.setIntByteWithBit(0, 7, z3), (byte) i8, (byte) i6, b, b, z ? (byte) 1 : (byte) 0, (byte) (i2 + HotKeyConstant.K_SOS), (byte) i3, (byte) i4, (byte) i9});
    }

    private void set0xC3MileageAndMaintenanceInfo() {
        Context context;
        int i;
        if (this.mCanBusInfoInt[4] == 1) {
            context = this.mContext;
            i = R.string.vm_golf7_vehicle_setup_units_distance_mi_hw;
        } else {
            context = this.mContext;
            i = R.string.vm_golf7_vehicle_setup_units_distance_km;
        }
        String string = context.getString(i);
        String string2 = this.mContext.getString(R.string.time_day);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 0, String.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new DriverUpdateEntity(1, 1, string));
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 2, sb.append(((iArr[5] * 256) + iArr[6]) * 100).append(string).toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 3, sb2.append((iArr2[7] * 256) + iArr2[8]).append(string2).toString()));
        arrayList.add(new DriverUpdateEntity(1, 4, (this.mCanBusInfoInt[9] * 100) + string));
        arrayList.add(new DriverUpdateEntity(1, 5, this.mCanBusInfoInt[10] + string2));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(getSettingUpdateEntity("_303_setting_content_30").setValue(Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        updateGeneralSettingData(arrayList2);
        updateSettingActivity(null);
    }

    private void set0x19MileageInfo() {
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[2] << 16) + (iArr[3] << 8) + iArr[4];
        int i2 = (iArr[10] << 8) + iArr[11];
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(1, 6, String.valueOf(i)));
        arrayList.add(new DriverUpdateEntity(1, 7, String.valueOf(i2)));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0xC4TirePressure() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_37").setValue(Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
        arrayList.add(getSettingUpdateEntity("_303_setting_content_38").setValue(Integer.valueOf(this.mCanBusInfoInt[4])).setProgress(this.mCanBusInfoInt[4]));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x7dKeyTotal() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getSettingUpdateEntity("_303_setting_content_39").setValue(Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]).setEnable(((this.mCanBusInfoInt[2] >> 7) & 1) == 1));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        int i3;
        super.radioInfoChange(i, str, str2, str3, i2);
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case 64901:
                if (str.equals("AM1")) {
                    c = 0;
                    break;
                }
                break;
            case 64902:
                if (str.equals("AM2")) {
                    c = 1;
                    break;
                }
                break;
            case 69706:
                if (str.equals("FM1")) {
                    c = 2;
                    break;
                }
                break;
            case 69707:
                if (str.equals("FM2")) {
                    c = 3;
                    break;
                }
                break;
            case 69708:
                if (str.equals("FM3")) {
                    c = 4;
                    break;
                }
                break;
        }
        String str4 = "01";
        switch (c) {
            case 0:
                i3 = 4;
                break;
            case 1:
                i3 = 5;
                str4 = "03";
                break;
            case 2:
            default:
                i3 = 1;
                break;
            case 3:
                str4 = "10";
                i3 = 2;
                break;
            case 4:
                str4 = "10";
                i3 = 3;
                break;
        }
        String str5 = (str.contains("FM") && str.length() == 4) ? "  " : " ";
        if (str.contains("FM") && str.length() == 4) {
            str2 = " " + str2;
        } else if (str.contains("AM")) {
            if (str.length() == 3) {
                str2 = str2 + "  ";
            } else {
                str2 = str2 + " ";
            }
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, (byte) i3}, (str4 + str5 + str2 + getBandUnit(str) + " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 8, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (bArr.length < 12) {
            byte[] bArr2 = new byte[12 - bArr.length];
            Arrays.fill(bArr2, (byte) 32);
            bArr = DataHandleUtils.byteMerger(bArr2, bArr);
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, ((((b7 & 255) * 256) + i) + "/" + i2 + " " + str2 + ":" + str3 + "     ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, ((((b6 & 255) * 256) + i) + "/" + i2 + " " + str3 + ":" + str4 + "     ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 7, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, "BT MUSIC    ".getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -31, 0, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32});
    }

    private String getBandUnit(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
            case "AM2":
                return "KHz";
            case "FM1":
            case "FM2":
            case "FM3":
                return "MHz";
            default:
                return "";
        }
    }

    private boolean is0x1dDataChange() {
        if (Arrays.equals(this.m0x41Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x41Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void initSettingsItem(Context context) {
        this.mSettingItemIndeHashMap = new HashMap<>();
        SparseArray sparseArray = new SparseArray();
        List<SettingPageUiSet.ListBean> list = getUiMgr(context).getSettingUiSet(context).getList();
        for (int i = 0; i < list.size(); i++) {
            List<SettingPageUiSet.ListBean.ItemListBean> itemList = list.get(i).getItemList();
            for (int i2 = 0; i2 < itemList.size(); i2++) {
                String titleSrn = itemList.get(i2).getTitleSrn();
                sparseArray.append((i << 8) | i2, titleSrn);
                this.mSettingItemIndeHashMap.put(titleSrn, new SettingUpdateEntity(i, i2, null));
            }
        }
    }

    private SettingUpdateEntity getSettingUpdateEntity(String str) {
        if (this.mSettingItemIndeHashMap.containsKey(str)) {
            return this.mSettingItemIndeHashMap.get(str);
        }
        this.mSettingItemIndeHashMap.put(str, new SettingUpdateEntity(-1, -1, null));
        return getSettingUpdateEntity(str);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
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

    private String resolverAirTemperature(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5d) + getTempUnitC(this.mContext);
    }

    private void cleanAllBlow() {
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
    }

    private boolean isAirDataChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mAirData = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        if (Arrays.equals(this.m0x11Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x11Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
