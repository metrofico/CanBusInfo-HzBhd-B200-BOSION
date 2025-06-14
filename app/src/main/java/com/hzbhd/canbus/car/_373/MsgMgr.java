package com.hzbhd.canbus.car._373;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.hzbhd.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    int differentId;
    int eachId;
    int[] mAirData;
    byte[] mCanBusInfoByte;
    int[] mCanBusInfoInt;
    int[] mCarDoorData;
    Context mContext;
    int[] mFrontRadarData;
    private LocationManager mLocationManager;
    int[] mPanoramicInfo;
    int[] mRearAirData;
    int[] mRearRadarData;
    int[] mTireInfo;
    byte[] mTrackData;
    private UiMgr mUiMgr;
    DecimalFormat df_2Decimal = new DecimalFormat("###0.00");
    DecimalFormat df_1Decimal = new DecimalFormat("###0.0");
    DecimalFormat df_2Integer = new DecimalFormat("00");
    LocationListener locationListener = new LocationListener() { // from class: com.hzbhd.canbus.car._373.MsgMgr.1
        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            MyLog.temporaryTracking("指南针数据：onStatusChanged");
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            MyLog.temporaryTracking("指南针数据：onProviderEnabled");
        }

        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
            MyLog.temporaryTracking("指南针数据：temporaryTracking");
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            MyLog.temporaryTracking("指南针数据：onLocationChanged");
            if (location != null) {
                MsgMgr.this.sndCurCompassSt(location);
            }
        }
    };

    private String getAccState(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? "Invalid Info" : "ACC ON" : "ACC" : "ACC OFF" : "Key out";
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getSeatState(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return i == 3 ? 2 : 0;
    }

    private String getSwitchState(boolean z) {
        return z ? "ON" : "OFF";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.differentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mContext = context;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        getUiMgr(this.mContext).send0x81MakeConnection();
        new AmpUtil().initAmpData(this.mContext);
        if (this.mLocationManager == null) {
            this.mLocationManager = (LocationManager) this.mContext.getSystemService("location");
        }
        try {
            this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        getUiMgr(this.mContext).send0x98TimeInfo(z ? 1 : 0, i5, i6, i7);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, (byte) ((str.equals("FM") || str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || !(str.equals("AM") || str.equals("AM1") || str.equals("AM2"))) ? 1 : 2), 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 3, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 4, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 4, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 7, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        getUiMgr(this.mContext).send0x90MediaInfo(new byte[]{22, -112, 0, 0, 32, 0, 80, 0, 108, 0, 97, 0, 121, 0, 105, 0, 110, 0, 103});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        int i = byteArrayToIntArray[1];
        if (i == 1) {
            set0x01WheelKeyInfo();
            return;
        }
        if (i == 3) {
            set0x03SpeedInfo();
            return;
        }
        if (i == 4) {
            set0x04WheelKeyInfo();
            return;
        }
        if (i == 5) {
            set0x05FrontAir();
            return;
        }
        if (i == 6) {
            set0x06RearAir();
            return;
        }
        if (i == 7) {
            set0x07CarControl();
            return;
        }
        if (i == 112) {
            set0x70AmpInfo();
            return;
        }
        if (i != 113) {
            switch (i) {
                case 9:
                    set0x09EspInfo();
                    break;
                case 10:
                    set0x0ACarState();
                    break;
                case 11:
                    set0x0BCompassInfo();
                    break;
            }
            return;
        }
        set0x71VersionInfo();
    }

    private void set0x71VersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void set0x70AmpInfo() {
        GeneralAmplifierData.volume = this.mCanBusInfoInt[2];
        GeneralAmplifierData.leftRight = this.mCanBusInfoInt[3] - 10;
        GeneralAmplifierData.frontRear = (-this.mCanBusInfoInt[4]) + 10;
        GeneralAmplifierData.bandBass = this.mCanBusInfoInt[5] - 1;
        GeneralAmplifierData.bandMiddle = this.mCanBusInfoInt[6] - 1;
        GeneralAmplifierData.bandTreble = this.mCanBusInfoInt[7] - 1;
        updateAmplifierActivity(new Bundle());
        AmpUtil.saveAmpUIValue(this.mContext);
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_373_other_seting");
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getOtherSettingRight("_373_amp_seting1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getOtherSettingRight("_373_amp_seting2"), Integer.valueOf(this.mCanBusInfoInt[9])));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getOtherSettingRight("_373_amp_seting3"), Integer.valueOf(this.mCanBusInfoInt[10] - 1)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x0BCompassInfo() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_compass"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_compass1"), getCompassState(this.mCanBusInfoInt[2])));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void set0x0ACarState() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data2"), getAccState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 3))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data3"), getSwitchState(DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data4"), getSwitchState(DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data5"), getSwitchState(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        setBacklightLevel(this.mCanBusInfoInt[4] / 2);
        int[] iArr = this.mCanBusInfoInt;
        iArr[2] = 0;
        iArr[4] = 0;
        if (isBasicInfoChange()) {
            return;
        }
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        updateDoorView(this.mContext);
    }

    private void set0x09EspInfo() {
        if (isTrackInfoChange()) {
            return;
        }
        if (this.mCanBusInfoInt[2] < 128) {
            GeneralParkData.trackAngle = (this.mCanBusInfoByte[2] / 5) - 25;
        } else {
            GeneralParkData.trackAngle = (this.mCanBusInfoByte[2] / 5) + 25;
        }
        updateParkUi(null, this.mContext);
    }

    private void set0x07CarControl() {
        ArrayList arrayList = new ArrayList();
        int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_373_setting_car_control");
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control1"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control2"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control_add_function"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control3"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control4"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control5"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 1, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control6"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control7"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 3, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control8"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 1))));
        arrayList.add(new SettingUpdateEntity(settingLeftIndexes, getCarCotrolRight("_373_setting_car_control9"), Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x06RearAir() {
        String str;
        if (isRearAirDataNoChange()) {
            return;
        }
        GeneralAirData.rear_power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_lock = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        int i = this.mCanBusInfoInt[4];
        if (i == 0) {
            str = "LO";
        } else if (i == 127) {
            str = "HI";
        } else if (i > 30) {
            str = this.mCanBusInfoInt[4] + getTempUnitF(this.mContext);
        } else {
            str = this.mCanBusInfoInt[4] + getTempUnitC(this.mContext);
        }
        GeneralAirData.rear_temperature = str;
        updateAirActivity(this.mContext, 1003);
    }

    private void set0x05FrontAir() {
        String str;
        updateOutDoorTemp(this.mContext, (this.mCanBusInfoInt[9] - 40) + getTempUnitC(this.mContext));
        this.mCanBusInfoInt[9] = 0;
        if (isAirDataNoChange()) {
            return;
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        int i = this.mCanBusInfoInt[5];
        String str2 = "HI";
        if (i == 0) {
            str = "LO";
        } else if (i == 127) {
            str = "HI";
        } else if (i > 59) {
            str = this.mCanBusInfoInt[5] + getTempUnitF(this.mContext);
        } else {
            str = this.mCanBusInfoInt[5] + getTempUnitC(this.mContext);
        }
        GeneralAirData.front_left_temperature = str;
        int i2 = this.mCanBusInfoInt[6];
        if (i2 == 0) {
            str2 = "LO";
        } else if (i2 != 127) {
            if (i2 > 59) {
                str2 = this.mCanBusInfoInt[6] + getTempUnitF(this.mContext);
            } else {
                str2 = this.mCanBusInfoInt[6] + getTempUnitC(this.mContext);
            }
        }
        GeneralAirData.front_right_temperature = str2;
        GeneralAirData.front_left_seat_heat_level = getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2));
        GeneralAirData.front_right_seat_heat_level = getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2));
        GeneralAirData.front_left_seat_cold_level = getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2));
        GeneralAirData.front_right_seat_cold_level = getSeatState(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2));
        GeneralAirData.steering_wheel_heating = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        updateAirActivity(this.mContext, 1003);
    }

    private void set0x04WheelKeyInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        if (i == 1) {
            buttonKey(HotKeyConstant.K_SLEEP);
            return;
        }
        switch (i) {
            case 4:
                buttonKey(49);
                break;
            case 5:
                buttonKey(8);
                break;
            case 6:
                buttonKey(7);
                break;
            case 7:
                knobKey(21);
                break;
            case 8:
                knobKey(20);
                break;
            case 9:
                buttonKey(31);
                break;
        }
    }

    private void set0x03SpeedInfo() {
        ArrayList arrayList = new ArrayList();
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_373_drive_data");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_373_drive_data1");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append(getMsbLsbResult(iArr[2], iArr[3])).append("Hm/h").toString()));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
        int[] iArr2 = this.mCanBusInfoInt;
        updateSpeedInfo(getMsbLsbResult(iArr2[2], iArr2[3]));
    }

    private void set0x01WheelKeyInfo() {
        switch (this.mCanBusInfoInt[2]) {
            case 0:
                buttonKey(0);
                break;
            case 1:
                buttonKey(7);
                break;
            case 2:
                buttonKey(8);
                break;
            case 3:
                buttonKey(45);
                break;
            case 4:
                buttonKey(46);
                break;
            case 5:
                buttonKey(2);
                break;
            case 6:
                buttonKey(62);
                break;
            case 7:
                buttonKey(HotKeyConstant.K_SPEECH);
                break;
            case 8:
                buttonKey(14);
                break;
        }
    }

    private int getCarCotrolRight(String str) {
        return getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_373_setting_car_control", str);
    }

    private int getOtherSettingRight(String str) {
        return getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_373_other_seting", str);
    }

    private String getCompassState(int i) {
        if (i != 15) {
            switch (i) {
                case 0:
                    return this.mContext.getString(R.string._373_compass10);
                case 1:
                    return this.mContext.getString(R.string._373_compass11);
                case 2:
                    return this.mContext.getString(R.string._373_compass12);
                case 3:
                    return this.mContext.getString(R.string._373_compass13);
                case 4:
                    return this.mContext.getString(R.string._373_compass14);
                case 5:
                    return this.mContext.getString(R.string._373_compass15);
                case 6:
                    return this.mContext.getString(R.string._373_compass16);
                case 7:
                    return this.mContext.getString(R.string._373_compass17);
                default:
                    return "NO INFO";
            }
        }
        return this.mContext.getString(R.string._373_compass18);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sndCurCompassSt(Location location) {
        int bearing = (int) location.getBearing();
        int i = 0;
        if (bearing != 0 && bearing != 360) {
            if (bearing > 0 && bearing < 90) {
                i = 1;
            } else if (bearing == 90) {
                i = 2;
            } else if (bearing > 90 && bearing < 180) {
                i = 3;
            } else if (bearing == 180) {
                i = 4;
            } else if (bearing > 180 && bearing < 270) {
                i = 5;
            } else if (bearing == 270) {
                i = 6;
            } else if (bearing > 270 && bearing < 360) {
                i = 7;
            }
        }
        getUiMgr(this.mContext).send0x99CompassInfo(i);
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }

    public void knobKey(int i) {
        realKeyClick4(this.mContext, i);
    }

    public void updateSettings(Context context, int i, int i2, String str, int i3, String str2) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, i3 + str2));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isRearAirDataNoChange() {
        if (Arrays.equals(this.mRearAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mRearAirData = this.mCanBusInfoInt;
        return false;
    }

    private boolean isFrontRadarDataChange() {
        if (Arrays.equals(this.mFrontRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mFrontRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRearRadarDataChange() {
        if (Arrays.equals(this.mRearRadarData, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRearRadarData = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isBasicInfoChange() {
        if (Arrays.equals(this.mCarDoorData, this.mCanBusInfoInt)) {
            return true;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mCarDoorData = Arrays.copyOf(iArr, iArr.length);
        return false;
    }

    private boolean isTrackInfoChange() {
        if (Arrays.equals(this.mTrackData, this.mCanBusInfoByte)) {
            return true;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mTrackData = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isTireInfoChange() {
        if (Arrays.equals(this.mTireInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isPanoramicInfoChange() {
        if (Arrays.equals(this.mPanoramicInfo, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicInfo = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is404(String str, String str2) {
        return getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, str) == -1 || getUiMgr(this.mContext).getSettingRightIndex(this.mContext, str, str2) == -1;
    }

    public void updateAmp() {
        updateAmplifierActivity(null);
    }
}
