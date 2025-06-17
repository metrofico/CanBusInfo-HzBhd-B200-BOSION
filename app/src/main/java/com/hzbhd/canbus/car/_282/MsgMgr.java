package com.hzbhd.canbus.car._282;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.activity.DriveDataActivity;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class MsgMgr extends AbstractMsgMgr {
    private static final String SHARE_281_RADAR_DISPLAY = "share_281_radar_display";
    private static final String SHARE_281_RADAR_DISTANCE = "share_281_radar_distance";
    private static final String SHARE_281_RADAR_VOLUME = "share_281_radar_volume";
    private static final String SHARE_281_RADAR_VOLUME_SWITCH = "share_281_radar_volume_switch";
    static final String SHARE_281_SHOW_RADAR = "share_281_show_radar";
    private byte[] m0x07Data;
    private int[] m0x14Data;
    private int[] m0x15Data;
    private int[] m0x17Data;
    private byte[] m0x19Data;
    private int[] m0x26Data;
    private int[] m0x27Data;
    private int[] m0x29Data;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mDay;
    private int mDifferent;
    private RelativeLayout mFloatView;
    private int[] mFrontRadarDataNow;
    private int mHour;
    private WindowManager.LayoutParams mLayoutParams;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private int mMinute;
    private int mMonth;
    private boolean mPanoramicStatus;
    private boolean mPanoramicStatus2;
    private boolean mPm2p5InsideCheck;
    private boolean mPm2p5OutsideCheck;
    private int mPm2p5Warning;
    private boolean mRearCameraStatus;
    private int[] mRearRadarDataNow;
    private boolean mRightCameraStatus;
    private boolean mRightCameraStatus2;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private Runnable mRunnable;
    private int mSecond;
    private int[] mVersionInfoNow;
    private WindowManager mWindowManager;
    private int mYear;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private boolean isShowing = false;

    private String getMainDisplay(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "null_value" : "_282_14_0_10_2" : "_282_14_0_10_1" : "invalid";
    }

    private String getOpenClose(boolean z) {
        return z ? "open" : "close";
    }

    private void setTtsData0x06() {
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        this.mDifferent = getCurrentCanDifferentId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        updateRadarSetup();
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 6) {
            setTtsData0x06();
        }
        if (i == 7) {
            setWindowSetup0x07();
            return;
        }
        if (i == 20) {
            setPm2p5Data0x14();
            return;
        }
        if (i == 21) {
            setPanoramicData0x15();
            return;
        }
        if (i == 23) {
            setCameraData0x17();
            return;
        }
        if (i == 25) {
            setDrivceSetup0x19();
            return;
        }
        if (i == 32) {
            setWheelKey0x20();
            return;
        }
        if (i == 36) {
            setDoorData0x24();
            return;
        }
        if (i == 29) {
            setFrontRadarData0x1D();
            return;
        }
        if (i == 30) {
            setRearRadarData0x1E();
            return;
        }
        switch (i) {
            case 38:
                setVehicleInfo0x26();
                break;
            case 39:
                setVehicleSetupData0x27();
                break;
            case 40:
                setAirData0x28();
                break;
            case 41:
                setTrackData0x29();
                break;
        }
    }

    private void setDoorData0x24() {
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        this.mRightFrontRec = boolBit7;
        GeneralDoorData.isRightFrontDoorOpen = boolBit7;
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        this.mLeftFrontRec = boolBit6;
        GeneralDoorData.isLeftFrontDoorOpen = boolBit6;
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        this.mRightRearRec = boolBit5;
        GeneralDoorData.isRightRearDoorOpen = boolBit5;
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        this.mLeftRearRec = boolBit4;
        GeneralDoorData.isLeftRearDoorOpen = boolBit4;
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        if (isDoorDataChange()) {
            updateDoorView(this.mContext);
        }
    }

    private void setAirData0x28() {
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        setOutDoorTem();
        int airWhat = getAirWhat();
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bytesExpectOneByte(this.mCanBusInfoByte, 7)) || isFirst()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_lock = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 4);
        updateAirActivity(this.mContext, airWhat);
    }

    private void setRearRadarData0x1E() {
        if (isRearRadarDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            SharePreUtil.setBoolValue(this.mContext, SHARE_281_SHOW_RADAR, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]));
            String string = Integer.toString(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3));
            String str = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]) ? "open" : "close";
            String str2 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]) ? "far" : "near";
            String str3 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]) ? "open" : "close";
            SharePreUtil.setStringValue(this.mContext, SHARE_281_RADAR_VOLUME, string);
            SharePreUtil.setStringValue(this.mContext, SHARE_281_RADAR_DISPLAY, str);
            SharePreUtil.setStringValue(this.mContext, SHARE_281_RADAR_DISTANCE, str2);
            SharePreUtil.setStringValue(this.mContext, SHARE_281_RADAR_VOLUME_SWITCH, str3);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 1, str));
            arrayList.add(new SettingUpdateEntity(4, 2, str2));
            arrayList.add(new SettingUpdateEntity(4, 3, str3));
            arrayList.add(new SettingUpdateEntity(4, 0, string).setValueStr(true));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setFrontRadarData0x1D() {
        if (isFrontRadarDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setWindowSetup0x07() {
        if (is0x07DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            if (this.mRightCameraStatus != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3])) {
                boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
                this.mRightCameraStatus = boolBit7;
                switchFCamera(this.mContext, boolBit7);
                byte[] bArr = new byte[3];
                bArr[0] = 22;
                bArr[1] = 9;
                bArr[2] = (byte) (this.mRightCameraStatus ? 128 : 0);
                CanbusMsgSender.sendMsg(bArr);
            }
            if (this.mPanoramicStatus != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3])) {
                boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
                this.mPanoramicStatus = boolBit6;
                forceReverse(this.mContext, boolBit6);
                byte[] bArr2 = new byte[3];
                bArr2[0] = 22;
                bArr2[1] = 9;
                bArr2[2] = (byte) (this.mPanoramicStatus ? 64 : 0);
                CanbusMsgSender.sendMsg(bArr2);
            }
        }
    }

    private void setPanoramicData0x15() {
        if (is0x15DataChange()) {
            if (this.mPanoramicStatus2 != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
                boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                this.mPanoramicStatus2 = boolBit7;
                forceReverse(this.mContext, boolBit7);
                byte[] bArr = new byte[3];
                bArr[0] = 22;
                bArr[1] = 9;
                bArr[2] = (byte) (this.mPanoramicStatus2 ? 64 : 0);
                CanbusMsgSender.sendMsg(bArr);
            }
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, intFromByteWithBit == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(1, intFromByteWithBit == 3));
            arrayList.add(new PanoramicBtnUpdateEntity(2, intFromByteWithBit == 4));
            arrayList.add(new PanoramicBtnUpdateEntity(3, intFromByteWithBit == 5));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private void setPm2p5Data0x14() {
        if (is0x14DataChange()) {
            boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
            boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(1, 0, getOpenClose(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]))));
            arrayList.add(new SettingUpdateEntity(1, 1, getOpenClose(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]))));
            arrayList.add(new SettingUpdateEntity(1, 2, getOpenClose(DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]))));
            arrayList.add(new SettingUpdateEntity(1, 3, getOpenClose(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
            arrayList.add(new SettingUpdateEntity(1, 4, DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2) == 0 ? "_253_normal" : "_282_14_0_32"));
            arrayList.add(new SettingUpdateEntity(1, 5, getMainDisplay(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
            arrayList.add(new SettingUpdateEntity(1, 6, getOpenClose(boolBit6)));
            arrayList.add(new SettingUpdateEntity(1, 7, getOpenClose(boolBit7)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
            if (this.mPm2p5InsideCheck != boolBit6) {
                this.mPm2p5InsideCheck = boolBit6;
                if (boolBit6) {
                    openDriveData();
                }
            }
            if (this.mPm2p5OutsideCheck != boolBit7) {
                this.mPm2p5OutsideCheck = boolBit7;
                if (boolBit7) {
                    openDriveData();
                }
            }
            ArrayList arrayList2 = new ArrayList();
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4) * 256;
            int[] iArr = this.mCanBusInfoInt;
            int i = intFromByteWithBit + iArr[3];
            int intFromByteWithBit2 = (iArr[5] * 16) + DataHandleUtils.getIntFromByteWithBit(iArr[4], 4, 4);
            arrayList2.add(new DriverUpdateEntity(0, 0, i == 4095 ? "" : Integer.toString(i)));
            arrayList2.add(new DriverUpdateEntity(0, 1, getPm2p5Level(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))));
            arrayList2.add(new DriverUpdateEntity(0, 2, intFromByteWithBit2 != 4095 ? Integer.toString(intFromByteWithBit2) : ""));
            arrayList2.add(new DriverUpdateEntity(0, 3, getPm2p5Level(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 3))));
            updateGeneralDriveData(arrayList2);
            updateDriveDataActivity(null);
            int i2 = this.mPm2p5Warning;
            int i3 = this.mCanBusInfoInt[7];
            if (i2 != i3) {
                this.mPm2p5Warning = i3;
                if (i3 == 1) {
                    runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._282.MsgMgr.1
                        @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                        public void callback() {
                            MsgMgr.this.showWindow();
                        }
                    });
                }
            }
        }
    }

    private void setWheelKey0x20() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                realKeyClick1(0);
                break;
            case 1:
                realKeyClick1(7);
                break;
            case 4:
                realKeyClick1(8);
                break;
            case 7:
                realKeyClick1(47);
                break;
            case 8:
                realKeyClick1(21);
                break;
            case 10:
                realKeyClick1(48);
                break;
            case 11:
                realKeyClick1(20);
                break;
            case 12:
                realKeyClick1(14);
                break;
            case 13:
                realKeyClick1(14);
                break;
            case 14:
                realKeyClick1(4);
                break;
            case 15:
                realKeyClick1(4);
                break;
            case 16:
                realKeyClick1(2);
                break;
            case 17:
                realKeyClick1(2);
                break;
            case 19:
                realKeyClick1(HotKeyConstant.K_SPEECH);
                break;
            case 20:
                realKeyClick1(HotKeyConstant.K_SPEECH);
                break;
            case 21:
                realKeyClick1(52);
                break;
            case 22:
                realKeyClick1(52);
                break;
            case 23:
                realKeyClick1(50);
                break;
            case 24:
                realKeyClick1(50);
                break;
            case 25:
                realKeyClick1(3);
                break;
            case 26:
                realKeyClick1(HotKeyConstant.K_SLEEP);
                break;
            case 27:
                realKeyClick1(152);
                break;
            case 29:
                realKeyClick1(15);
                break;
            case 30:
                realKeyClick1(205);
                break;
            case 31:
                if (SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
                    finishActivity();
                    break;
                } else {
                    AirActivity.mIsClickOpen = true;
                    Intent intent = new Intent(this.mContext, (Class<?>) AirActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.mContext.startActivity(intent);
                    break;
                }
        }
    }

    private void setTrackData0x29() {
        if (is0x29DataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 7799, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setCameraData0x17() {
        if (is0x17DataChange()) {
            if (this.mRightCameraStatus2 != DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
                boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
                this.mRightCameraStatus2 = boolBit7;
                switchFCamera(this.mContext, boolBit7);
                byte[] bArr = new byte[3];
                bArr[0] = 22;
                bArr[1] = 9;
                bArr[2] = (byte) (this.mRightCameraStatus2 ? 128 : 0);
                CanbusMsgSender.sendMsg(bArr);
            }
            if (this.mRearCameraStatus != DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2])) {
                boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
                this.mRearCameraStatus = boolBit6;
                forceReverse(this.mContext, boolBit6);
                byte[] bArr2 = new byte[3];
                bArr2[0] = 22;
                bArr2[1] = 9;
                bArr2[2] = (byte) (this.mRearCameraStatus ? 64 : 0);
                CanbusMsgSender.sendMsg(bArr2);
            }
        }
    }

    private void setDrivceSetup0x19() {
        if (is0x19DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            int iRangeNumber = DataHandleUtils.rangeNumber(this.mCanBusInfoInt[3], 15, 70);
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(iRangeNumber)).setProgress(iRangeNumber - 15));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVehicleInfo0x26() {
        if (is0x26DataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(1, 0, sb.append((iArr[2] * 256) + iArr[3]).append(" km/h").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(1, 1, sb2.append((iArr2[4] * 256) + iArr2[5]).append(" rpm").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            int[] iArr3 = this.mCanBusInfoInt;
            updateSpeedInfo((iArr3[2] * 256) + iArr3[3]);
        }
    }

    private void setVehicleSetupData0x27() {
        if (is0x27DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(this.mCanBusInfoInt[2])));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(this.mCanBusInfoInt[3])));
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(this.mCanBusInfoInt[4])));
            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(this.mCanBusInfoInt[5])));
            arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(this.mCanBusInfoInt[6])));
            arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(this.mCanBusInfoInt[7])));
            arrayList.add(new SettingUpdateEntity(3, 6, Integer.valueOf(this.mCanBusInfoInt[8])));
            arrayList.add(new SettingUpdateEntity(3, 7, Integer.valueOf(this.mCanBusInfoInt[9])));
            arrayList.add(new SettingUpdateEntity(3, 8, Integer.valueOf(this.mCanBusInfoInt[10])));
            arrayList.add(new SettingUpdateEntity(3, 9, Integer.valueOf(this.mCanBusInfoInt[11])));
            arrayList.add(new SettingUpdateEntity(3, 10, Integer.valueOf(this.mCanBusInfoInt[12])).setProgress(this.mCanBusInfoInt[12]));
            arrayList.add(new SettingUpdateEntity(3, 11, Integer.valueOf(this.mCanBusInfoInt[13])));
            arrayList.add(new SettingUpdateEntity(3, 12, Integer.valueOf(this.mCanBusInfoInt[14])));
            arrayList.add(new SettingUpdateEntity(3, 13, Integer.valueOf(this.mCanBusInfoInt[15])).setProgress(this.mCanBusInfoInt[15]));
            arrayList.add(new SettingUpdateEntity(3, 14, Integer.valueOf(this.mCanBusInfoInt[16])).setProgress(this.mCanBusInfoInt[16]));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0x30() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, 9, Byte.MIN_VALUE});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        super.cameraDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, 9, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        int i11;
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        int i12 = Calendar.getInstance().get(7) - 1;
        if (this.mYear != i2 || this.mMonth != i3 || this.mDay != i4) {
            this.mYear = i2;
            this.mMonth = i3;
            this.mDay = i4;
            i11 = 128;
        } else if (this.mHour == i5 && this.mMinute == i6) {
            i11 = 0;
        } else {
            this.mHour = i5;
            this.mMinute = i6;
            this.mSecond = i7;
            i11 = 64;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, 118, (byte) i2, (byte) i3, (byte) i4, (byte) i8, (byte) i6, (byte) i7, (byte) (i12 | i11)});
    }

    private void realKeyClick1(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private byte[] bytesExpectOneByte(byte[] bArr, int i) {
        int length = bArr.length - 1;
        byte[] bArr2 = new byte[length];
        if (i == length) {
            return Arrays.copyOf(bArr, length);
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (i2 < i) {
                bArr2[i2] = bArr[i2];
            } else {
                bArr2[i2] = bArr[i2 + 1];
            }
        }
        return bArr2;
    }

    private void setOutDoorTem() {
        updateOutDoorTemp(this.mContext, resolveOutDoorTem());
    }

    private String resolveOutDoorTem() {
        float f = (float) this.mCanBusInfoInt[7] / 2.0f - 40.0f;
        return GeneralAirData.fahrenheit_celsius ? new DecimalFormat("0.0").format(((f * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext) : f + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveAirTemp(int i) {
        if (i == 0) {
            return "LO";
        }
        if (i == 31) {
            return "HI";
        }
        if (i < 1 || i > 29) {
            return "";
        }

        float f = (float) (i + 35) / 2.0f;
        return GeneralAirData.fahrenheit_celsius ? new DecimalFormat("0.0").format(((f * 9.0f) / 5.0f) + 32.0f) + getTempUnitF(this.mContext) : f + getTempUnitC(this.mContext);
    }

    private int getAirWhat() {
        int i;
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[2], iArr[3], iArr[4], iArr[5], iArr[6] & com.hzbhd.canbus.car._0.MsgMgr.REAR_DISC_MODE};
        int[] iArr3 = {iArr[8], iArr[9]};
        if (Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = !Arrays.equals(this.mAirRearDataNow, iArr3) ? 1002 : -1;
        } else {
            i = 1001;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 5);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 2);
        return i;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen && GeneralDoorData.skyWindowOpenLevel == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVersionInfoNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x19DataChange() {
        if (Arrays.equals(this.m0x19Data, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x19Data = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x07DataChange() {
        if (Arrays.equals(this.m0x07Data, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.m0x07Data = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean is0x15DataChange() {
        if (Arrays.equals(this.m0x15Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x15Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x14DataChange() {
        if (Arrays.equals(this.m0x14Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x14Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x29DataChange() {
        if (Arrays.equals(this.m0x29Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x29Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x17DataChange() {
        if (Arrays.equals(this.m0x17Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x17Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x26DataChange() {
        if (Arrays.equals(this.m0x26Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x26Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x27DataChange() {
        if (Arrays.equals(this.m0x27Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x27Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private String getPm2p5Level(int i) {
        switch (i) {
            case 1:
                return CommUtil.getStrByResId(this.mContext, "pm_excellent");
            case 2:
                return CommUtil.getStrByResId(this.mContext, "pm_good");
            case 3:
                return CommUtil.getStrByResId(this.mContext, "pm_mild_pollution");
            case 4:
                return CommUtil.getStrByResId(this.mContext, "pm_moderately_polluted");
            case 5:
                return CommUtil.getStrByResId(this.mContext, "pm_heavy_pollution");
            case 6:
                return CommUtil.getStrByResId(this.mContext, "pm_severe_pollution");
            default:
                return "";
        }
    }

    private String getWarning(int i) {
        if (i == 0) {
            return CommUtil.getStrByResId(this.mContext, "_253_normal");
        }
        if (i != 1) {
            return i != 2 ? "" : CommUtil.getStrByResId(this.mContext, "_282_14_5_2");
        }
        return CommUtil.getStrByResId(this.mContext, "_282_14_5_1");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWindow() {
        initWindow();
        if (!this.isShowing) {
            this.mWindowManager.addView(this.mFloatView, this.mLayoutParams);
            this.isShowing = true;
        }
        this.mFloatView.removeCallbacks(this.mRunnable);
        this.mFloatView.postDelayed(this.mRunnable, 3000L);
    }

    private void initWindow() {
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        }
        if (this.mLayoutParams == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mLayoutParams = layoutParams;
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            this.mLayoutParams.gravity = android.view.Gravity.CENTER;
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = -2;
        }
        if (this.mFloatView == null) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this.mContext).inflate(R.layout.layout_282_alarm, (ViewGroup) null);
            this.mFloatView = relativeLayout;
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.hzbhd.canbus.car._282.MsgMgr.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MsgMgr.this.mFloatView.removeCallbacks(MsgMgr.this.mRunnable);
                    MsgMgr.this.mFloatView.post(MsgMgr.this.mRunnable);
                    MsgMgr.this.dismissView();
                }
            });
        }
        if (this.mRunnable == null) {
            this.mRunnable = new Runnable() { // from class: com.hzbhd.canbus.car._282.MsgMgr.3
                @Override // java.lang.Runnable
                public void run() {
                    MsgMgr.this.dismissView();
                }
            };
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissView() {
        RelativeLayout relativeLayout;
        WindowManager windowManager = this.mWindowManager;
        if (windowManager == null || (relativeLayout = this.mFloatView) == null) {
            return;
        }
        windowManager.removeView(relativeLayout);
        this.isShowing = false;
    }

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    byte[] get0x19Data() {
        if (this.m0x19Data.length == 0) {
            this.m0x19Data = new byte[4];
        }
        return this.m0x19Data;
    }

    byte[] get0x07Data() {
        if (this.m0x07Data.length == 0) {
            this.m0x07Data = new byte[4];
        }
        return Arrays.copyOf(this.m0x07Data, m0x07Data.length - 1);
    }

    private void openDriveData() {
        if (SystemUtil.isForeground(this.mContext, DriveDataActivity.class.getName())) {
            return;
        }
        startDrivingDataActivity(this.mContext, 0);
    }

    private void updateRadarSetup() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(4, 0, SharePreUtil.getStringValue(this.mContext, SHARE_281_RADAR_VOLUME, "1")).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(4, 1, SharePreUtil.getStringValue(this.mContext, SHARE_281_RADAR_DISPLAY, "open")));
        arrayList.add(new SettingUpdateEntity(4, 2, SharePreUtil.getStringValue(this.mContext, SHARE_281_RADAR_DISTANCE, "near")));
        arrayList.add(new SettingUpdateEntity(4, 3, SharePreUtil.getStringValue(this.mContext, SHARE_281_RADAR_VOLUME_SWITCH, "open")));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
