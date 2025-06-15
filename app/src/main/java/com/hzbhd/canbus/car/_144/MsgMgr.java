package com.hzbhd.canbus.car._144;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.provider.Settings;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.WarningEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralWarningDataData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TimerUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TimerTask;


public class MsgMgr extends AbstractMsgMgr {
    private static boolean isAirFirst = true;
    private static boolean isDoorFirst = true;
    private static boolean isWarnFirst = true;
    static int language;
    private static int outDoorTemp;
    private static int swc_data3;
    private static int up_dn_btn_data;
    private static int voice_btn_data;
    private boolean isDGear;
    private byte[] m0x41RadarData;
    private byte[] mCanBusAirInfoCopy;
    private byte[] mCanBusBtnPanelInfoCopy;
    private byte[] mCanBusDoorInfoCopy;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanBusSwcDataCopy;
    private byte[] mCanBusSwcInfoCopy;
    private byte[] mCanBusWarnInfoCopy;
    private Context mContext;
    private int nextItem;
    private final String TAG = "_144_MsgMgr";
    private final int DISMISS_PANORAMIC_DELAY = 5000;
    private final String IS_LEFT_FRONT_DOOR_OPEN = "is_left_front_door_open";
    private final String IS_RIGHT_FRONT_DOOR_OPEN = "is_right_front_door_open";
    private final String IS_RIGHT_REAR_DOOR_OPEN = "is_right_rear_door_open";
    private final String IS_LEFT_REAR_DOOR_OPEN = "is_left_rear_door_open";
    private final String IS_BACK_OPEN = "is_back_open";
    private final String IS_OUT_DOOR_TEMP = "is_out_door_temp";
    private final String IS_SEAT_MASTER_DRIVER_BELT_TIE = "is_seat_master_driver_belt_tie";
    private final String IS_SEAT_CO_PILOT_BELT_TIE = "is_seat_co_pilot_belt_tie";
    private final String IS_SEAT_R_L_BELT_TIE = "is_seat_r_l_belt_tie";
    private final String IS_SEAT_R_M_BELT_TIE = "is_seat_r_m_belt_tie";
    private final String IS_SEAT_R_R_BELT_TIE = "is_seat_r_r_belt_tie";
    private final String IS_SEAT_BELT_TIE = "is_seat_belt_tie";
    private final String SWC_DATA_3 = "swc_data_3";
    private final String SWC_BTN_DATA_3 = "swc_btn_data_3";
    TimerUtil mTimerUtil = new TimerUtil();
    BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.hzbhd.canbus.car._144.MsgMgr.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("selectPos1", 0);
            if ("REVERSING_SOUND".equals(intent.getAction())) {
                MsgMgr.this.settingLeft0Right12Data(intExtra);
            }
        }
    };
    private int currentItem = 0;

    private int getIndexBy2Bit(boolean z) {
        return z ? 1 : 0;
    }

    private int getIntByBoolean(boolean z) {
        return z ? 1 : 0;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("REVERSING_SOUND");
        context.registerReceiver(this.receiver, intentFilter);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws Resources.NotFoundException {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 33) {
            if (isSwcMsgReturn(bArr)) {
                return;
            }
            setPanelButton();
            return;
        }
        if (i == 34) {
            if (isBtnPanelMsgReturn(bArr)) {
                return;
            }
            setOriginalPanel();
            return;
        }
        if (i == 49) {
            if (isAirMsgReturn(bArr)) {
                return;
            }
            setAirData();
            return;
        }
        if (i == 118) {
            setVehicleSet();
            return;
        }
        if (i == 148) {
            setLanguage();
            return;
        }
        if (i == 193) {
            setUnit();
            return;
        }
        if (i == 240) {
            setVersionInfo();
            return;
        }
        if (i == 65) {
            setFrontRearRadar();
            return;
        }
        if (i != 66) {
            switch (i) {
                case 17:
                    keyControl0x11();
                    setDrivingPage(bArr);
                    break;
                case 18:
                    if (!isDoorMsgReturn(bArr)) {
                        setCarDoorInfo();
                        setCarStatusInfo();
                        break;
                    }
                    break;
                case 19:
                    setDrivingComputerInfo0();
                    break;
                case 20:
                    setDrivingComputerInfo1();
                    break;
                case 21:
                    setDrivingComputerInfo2();
                    break;
            }
            return;
        }
        if (isWarningMsgReturn(bArr)) {
            return;
        }
        setWarningInfo();
    }

    private void setDrivingPage(byte[] bArr) {
        if (isSwcDataMsgReturn(bArr)) {
            return;
        }
        int i = this.mCanBusInfoInt[4];
        if (i == 20 || i == 22) {
            startDrivingPage();
        }
    }

    private void setOriginalPanel() {
        int[] iArr = this.mCanBusInfoInt;
        if (iArr[2] == 1) {
            if (iArr[3] > voice_btn_data) {
                realKeyClick4(7);
                voice_btn_data = this.mCanBusInfoInt[3];
            }
            if (this.mCanBusInfoInt[3] < voice_btn_data) {
                realKeyClick4(8);
                voice_btn_data = this.mCanBusInfoInt[3];
                return;
            }
            return;
        }
        if (iArr[3] > up_dn_btn_data) {
            realKeyClick4(46);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
        if (this.mCanBusInfoInt[3] < up_dn_btn_data) {
            realKeyClick4(45);
            up_dn_btn_data = this.mCanBusInfoInt[3];
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void keyControl0x11() {
        /*
            r6 = this;
            int[] r0 = r6.mCanBusInfoInt
            r1 = 4
            r0 = r0[r1]
            r2 = 0
            r3 = 8
            if (r0 == 0) goto L82
            r4 = 1
            if (r0 == r4) goto L7d
            r4 = 2
            if (r0 == r4) goto L79
            r5 = 3
            if (r0 == r5) goto L75
            if (r0 == r1) goto L6f
            r1 = 5
            if (r0 == r1) goto L69
            r1 = 21
            if (r0 == r1) goto L55
            r1 = 64
            if (r0 == r1) goto L4f
            switch(r0) {
                case 8: goto L49;
                case 9: goto L43;
                case 10: goto L3d;
                case 11: goto L39;
                default: goto L23;
            }
        L23:
            switch(r0) {
                case 13: goto L43;
                case 14: goto L49;
                case 15: goto L33;
                case 16: goto L2d;
                case 17: goto L43;
                case 18: goto L49;
                case 19: goto L27;
                default: goto L26;
            }
        L26:
            goto L85
        L27:
            r0 = 185(0xb9, float:2.59E-43)
            r6.realKeyClick1(r0)
            goto L85
        L2d:
            r0 = 50
            r6.realKeyClick1(r0)
            goto L85
        L33:
            r0 = 49
            r6.realKeyClick1(r0)
            goto L85
        L39:
            r6.realKeyClick1(r4)
            goto L85
        L3d:
            r0 = 52
            r6.realKeyClick1(r0)
            goto L85
        L43:
            r0 = 45
            r6.realKeyClick1(r0)
            goto L85
        L49:
            r0 = 46
            r6.realKeyClick1(r0)
            goto L85
        L4f:
            r0 = 68
            r6.realKeyClick1(r0)
            goto L85
        L55:
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r1 = r6.mContext
            java.lang.Class<com.hzbhd.canbus.activity.WarningActivity> r4 = com.hzbhd.canbus.activity.WarningActivity.class
            r0.<init>(r1, r4)
            r1 = 268435456(0x10000000, float:2.524355E-29)
            r0.addFlags(r1)
            android.content.Context r1 = r6.mContext
            r1.startActivity(r0)
            goto L85
        L69:
            r0 = 14
            r6.realKeyClick1(r0)
            goto L85
        L6f:
            r0 = 187(0xbb, float:2.62E-43)
            r6.realKeyClick1(r0)
            goto L85
        L75:
            r6.realKeyClick1(r5)
            goto L85
        L79:
            r6.realKeyClick1(r3)
            goto L85
        L7d:
            r0 = 7
            r6.realKeyClick1(r0)
            goto L85
        L82:
            r6.realKeyClick1(r2)
        L85:
            byte[] r0 = r6.mCanBusInfoByte
            r1 = 9
            r1 = r0[r1]
            r0 = r0[r3]
            r3 = 540(0x21c, float:7.57E-43)
            r4 = 16
            int r0 = com.hzbhd.canbus.util.TrackInfoUtil.getTrackAngle0(r1, r0, r2, r3, r4)
            com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle = r0
            r0 = 0
            android.content.Context r1 = r6.mContext
            r6.updateParkUi(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.car._144.MsgMgr.keyControl0x11():void");
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick5(context, i, iArr[4], iArr[5]);
    }

    private void panelButtonClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    private void realKeyClick4(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void setAirData() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.mono = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.aqs = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.auto_wind_lv = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2);
        int i = this.mCanBusInfoInt[6];
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_auto_wind_model = false;
        if (i == 3) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (i == 5) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_right_blow_head = true;
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
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 13:
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    break;
                case 14:
                    GeneralAirData.front_left_blow_foot = true;
                    GeneralAirData.front_right_blow_foot = true;
                    GeneralAirData.front_left_blow_window = true;
                    GeneralAirData.front_right_blow_window = true;
                    GeneralAirData.front_left_blow_head = true;
                    GeneralAirData.front_right_blow_head = true;
                    break;
            }
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[9]);
        outDoorTemp = this.mCanBusInfoInt[13];
        if (isOnlyOutDoorDataChange()) {
            SharePreUtil.setFloatValue(this.mContext, "is_out_door_temp", outDoorTemp);
            updateOutDoorTemp(this.mContext, resolveOutDoorTem());
        } else {
            updateAirActivity(this.mContext, 1001);
        }
    }

    private void setFrontRearRadar() {
        if (Arrays.equals(this.m0x41RadarData, this.mCanBusInfoByte)) {
            return;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x41RadarData = Arrays.copyOf(bArr, bArr.length);
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        RadarInfoUtil.mDisableData = 8;
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(7, iArr[2] + 1, iArr[3] + 1, iArr[4] + 1, iArr[5] + 1);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(7, iArr2[6] + 1, iArr2[7] + 1, iArr2[8] + 1, iArr2[9] + 1);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
        Log.i("_144_MsgMgr", "setFrontRearRadar: isDGear <-> " + this.isDGear);
        if (this.isDGear) {
            openPanoramic();
        }
    }

    private void setWarningInfo() {
        String hexString = Integer.toHexString(this.mCanBusInfoInt[2]);
        if (hexString.length() == 1) {
            hexString = "0" + hexString;
        }
        String hexString2 = Integer.toHexString(this.mCanBusInfoInt[3]);
        if (hexString2.length() == 1) {
            hexString2 = "0" + hexString2;
        }
        String str = "psa_hiword_" + hexString + hexString2;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new WarningEntity(CommUtil.getStrByResId(this.mContext, str)));
        GeneralWarningDataData.dataList = arrayList;
        updateWarningActivity(null);
    }

    private void setVehicleSet() {
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])))));
        arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
        arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 8, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 9, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 10, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3])))));
        arrayList.add(new SettingUpdateEntity(0, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2))));
        Settings.System.putInt(this.mContext.getContentResolver(), "left0right3", getIndexBy2Bit(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2])));
        Settings.System.putInt(this.mContext.getContentResolver(), "left0right4", intFromByteWithBit);
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setLanguage() {
        int i = this.mCanBusInfoInt[2];
        int i2 = 16;
        if (i > 16 || i == 0) {
            switch (i) {
                case 18:
                    break;
                case 19:
                case 21:
                case 24:
                case 27:
                default:
                    i2 = 0;
                    break;
                case 20:
                    i2 = 17;
                    break;
                case 22:
                    i2 = 18;
                    break;
                case 23:
                    i2 = 19;
                    break;
                case 25:
                    i2 = 21;
                    break;
                case 26:
                    i2 = 20;
                    break;
                case 28:
                    i2 = 22;
                    break;
                case 29:
                    i2 = 23;
                    break;
                case 30:
                    i2 = 24;
                    break;
                case 31:
                    i2 = 25;
                    break;
                case 32:
                    i2 = 26;
                    break;
                case 33:
                    i2 = 27;
                    break;
            }
        } else {
            i2 = i - 1;
        }
        language = i;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(i2)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setUnit() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(getIndexBy2Bit(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3])))));
        if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) != 3) {
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setCarDoorInfo() {
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        if (isOnlyDoorMsgShow()) {
            SharePreUtil.setBoolValue(this.mContext, "is_left_front_door_open", GeneralDoorData.isLeftFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_front_door_open", GeneralDoorData.isRightFrontDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_right_rear_door_open", GeneralDoorData.isRightRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_left_rear_door_open", GeneralDoorData.isLeftRearDoorOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_back_open", GeneralDoorData.isBackOpen);
            SharePreUtil.setBoolValue(this.mContext, "is_seat_belt_tie", GeneralDoorData.isSeatBeltTie);
            updateDoorView(this.mContext);
        }
    }

    private void setCarStatusInfo() throws Resources.NotFoundException {
        String string;
        int i = this.mCanBusInfoInt[3];
        if (i != 0) {
            string = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "" : "D" : "R" : "N" : "P";
        } else {
            string = this.mContext.getResources().getString(R.string.invalid);
        }
        this.isDGear = this.mCanBusInfoInt[3] == 4;
        Log.i("_144_MsgMgr", "setCarStatusInfo: isDGear <-> " + this.isDGear);
        if (!this.isDGear) {
            closePanoramic();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 0, string));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrivingComputerInfo0() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(1, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDrivingComputerInfo1() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
        arrayList.add(new DriverUpdateEntity(2, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(2, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[5]);
    }

    private void setDrivingComputerInfo2() {
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 0, sb.append((float) (((iArr[2] * 256) + iArr[3]) * 0.1d)).append("L/100km").toString()));
        arrayList.add(new DriverUpdateEntity(3, 1, this.mCanBusInfoInt[5] + "km/h"));
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(3, 2, sb2.append((iArr2[6] * 256) + iArr2[7]).append("km").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        updateSpeedInfo(this.mCanBusInfoInt[5]);
    }

    private void setPanelButton() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelButtonClick(0);
            return;
        }
        if (i == 1) {
            panelButtonClick(HotKeyConstant.K_SLEEP);
            return;
        }
        if (i == 17) {
            panelButtonClick(31);
            return;
        }
        if (i == 64) {
            startDrivingPage();
            return;
        }
        if (i == 36) {
            panelButtonClick(49);
            return;
        }
        if (i != 37) {
            switch (i) {
                case 6:
                    panelButtonClick(50);
                    break;
                case 7:
                    panelButtonClick(52);
                    break;
                case 8:
                    panelButtonClick(2);
                    break;
                case 9:
                    panelButtonClick(3);
                    break;
                case 10:
                    panelButtonClick(33);
                    break;
                case 11:
                    panelButtonClick(34);
                    break;
                case 12:
                    panelButtonClick(35);
                    break;
                case 13:
                    panelButtonClick(36);
                    break;
                case 14:
                    panelButtonClick(37);
                    break;
                case 15:
                    panelButtonClick(38);
                    break;
                default:
                    switch (i) {
                        case 22:
                            panelButtonClick(129);
                            break;
                        case 23:
                            panelButtonClick(45);
                            break;
                        case 24:
                            panelButtonClick(46);
                            break;
                        case 25:
                            panelButtonClick(45);
                            break;
                        case 26:
                            panelButtonClick(46);
                            break;
                        default:
                            switch (i) {
                                case 39:
                                    startDrivingPage();
                                    break;
                                case 40:
                                    Intent intent = new Intent(this.mContext, (Class<?>) AirActivity.class);
                                    intent.addFlags(268435456);
                                    this.mContext.startActivity(intent);
                                    break;
                                case 41:
                                    panelButtonClick(HotKeyConstant.K_CAN_CONFIG);
                                    break;
                                case 42:
                                    panelButtonClick(49);
                                    break;
                                case 43:
                                    panelButtonClick(14);
                                    break;
                                case 44:
                                    panelButtonClick(2);
                                    break;
                                case 45:
                                    panelButtonClick(59);
                                    break;
                                case 46:
                                    panelButtonClick(HotKeyConstant.K_CAN_CONFIG);
                                    break;
                                case 47:
                                    panelButtonClick(129);
                                    break;
                                default:
                                    switch (i) {
                                        case 49:
                                            panelButtonClick(59);
                                            break;
                                        case 50:
                                            panelButtonClick(HotKeyConstant.K_SLEEP);
                                            break;
                                        case 51:
                                            panelButtonClick(128);
                                            break;
                                    }
                            }
                    }
            }
            return;
        }
        panelButtonClick(50);
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void settingLeft0Right12Data(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 12, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 254 == i ? "LO" : 255 == i ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
    }

    private boolean isOnlyDoorMsgShow() {
        return (SharePreUtil.getBoolValue(this.mContext, "is_left_front_door_open", false) == GeneralDoorData.isLeftFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_front_door_open", false) == GeneralDoorData.isRightFrontDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_right_rear_door_open", false) == GeneralDoorData.isRightRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_left_rear_door_open", false) == GeneralDoorData.isLeftRearDoorOpen && SharePreUtil.getBoolValue(this.mContext, "is_back_open", false) == GeneralDoorData.isBackOpen && SharePreUtil.getBoolValue(this.mContext, "is_seat_belt_tie", false) == GeneralDoorData.isSeatBeltTie) ? false : true;
    }

    private boolean isAirMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusAirInfoCopy)) {
            return true;
        }
        this.mCanBusAirInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isAirFirst) {
            return false;
        }
        isAirFirst = false;
        return true;
    }

    private boolean isDoorMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusDoorInfoCopy)) {
            return true;
        }
        this.mCanBusDoorInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isDoorFirst) {
            return false;
        }
        isDoorFirst = false;
        return true;
    }

    private boolean isSwcMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcInfoCopy)) {
            return true;
        }
        this.mCanBusSwcInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isSwcDataMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusSwcDataCopy)) {
            return true;
        }
        this.mCanBusSwcDataCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isBtnPanelMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusBtnPanelInfoCopy)) {
            return true;
        }
        this.mCanBusBtnPanelInfoCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isOnlyOutDoorDataChange() {
        return SharePreUtil.getFloatValue(this.mContext, "is_out_door_temp", 0.0f) != ((float) outDoorTemp);
    }

    private String resolveOutDoorTem() {
        int i = this.mCanBusInfoInt[13];
        return (i < 0 || i > 250) ? "" : ((float) ((i * 0.5d) - 40.0d)) + getTempUnitC(this.mContext);
    }

    private boolean isWarningMsgReturn(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanBusWarnInfoCopy)) {
            return true;
        }
        this.mCanBusWarnInfoCopy = Arrays.copyOf(bArr, bArr.length);
        if (!isWarnFirst) {
            return false;
        }
        isWarnFirst = false;
        return true;
    }

    private void startDrivingPage() {
        Log.d("cwh", "startDrivingPage");
        int i = this.nextItem;
        if (i == 0) {
            this.currentItem = 1;
            this.nextItem = 1;
            startDrivingDataActivity(this.mContext, 1);
        } else if (i == 1) {
            this.currentItem = 2;
            this.nextItem = 2;
            startDrivingDataActivity(this.mContext, 2);
        } else {
            if (i != 2) {
                return;
            }
            this.currentItem = 3;
            this.nextItem = 0;
            startDrivingDataActivity(this.mContext, 3);
        }
    }

    private void openPanoramic() {
        forceReverse(this.mContext, true);
        this.mTimerUtil.stopTimer();
        this.mTimerUtil.startTimer(new TimerTask() { // from class: com.hzbhd.canbus.car._144.MsgMgr.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                MsgMgr msgMgr = MsgMgr.this;
                msgMgr.forceReverse(msgMgr.mContext, false);
            }
        }, 5000L);
    }

    private void closePanoramic() {
        this.mTimerUtil.stopTimer();
        forceReverse(this.mContext, false);
    }
}
