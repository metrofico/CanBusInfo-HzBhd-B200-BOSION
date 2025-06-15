package com.hzbhd.canbus.car._59;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;


public class MsgMgr extends AbstractMsgMgr {
    private byte[] m0xe9Bytes;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int[] mDoorLockDataNow;
    private int[] mDriveDatax16Now;
    private int[] mDrivingAssistanceDataNow;
    private int mFrontCameraStatusNow;
    private boolean mFrontStatus;
    private String mFuelUnit;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearStatus;
    private int[] mLightDataNow;
    private String mMileageUnit;
    private int[] mPanoramicDataNow;
    private int[] mRadarDataNow;
    private boolean mRightFrontStatus;
    private boolean mRightRearStatus;
    private boolean mSeatBeltTie;
    private int[] mSetting0x75DataNow;
    private boolean mSubSeatBeltTie;
    private byte[] mTrackDataNow;
    private UiMgr mUiMgr;
    private byte[] mVersionInfoNow;
    private int mWheelData;
    private int mWheelKeyNow;
    private String mInvalid = " - -";
    private boolean mIsAirFirst = true;
    private boolean mIs0x12First = true;
    private boolean mIsDoorFirst = true;
    private boolean mIs0xe9First = true;

    private int getDriveData(int i, int i2) {
        return (i * 256) + i2;
    }

    private int getDriveData(int i, int i2, int i3) {
        return (i * 256 * 256) + (i2 * 256) + i3;
    }

    private int getFuelRange(int i) {
        switch (i) {
            case 1:
                return 10;
            case 2:
                return 12;
            case 3:
                return 20;
            case 4:
                return 30;
            case 5:
                return 40;
            case 6:
                return 50;
            case 7:
                return 70;
            case 8:
                return 80;
            case 9:
                return 90;
            case 10:
                return 100;
            default:
                return 60;
        }
    }

    private String getFuelUnit(int i) {
        return i == 0 ? " MPG" : i == 1 ? " km/L" : i == 2 ? " L/100km" : "";
    }

    private String getMileageUnit(int i) {
        return i == 0 ? " km" : i == 1 ? " mile" : "";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        CanbusMsgSender.sendMsg(new byte[]{22, 36, (byte) getCurrentEachCanId(), 2});
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(this.mContext);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        SelectCanTypeUtil.enableApp(context, Constant.OriginalDeviceActivity);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 17) {
            setKeyTrack0x11();
            setDriveData0x11();
            updateSpeedInfo(this.mCanBusInfoInt[3]);
            return;
        }
        if (i == 18) {
            if (isDoorMsgRepeat(this.mCanBusInfoByte) || is0x12First()) {
                return;
            }
            setDoorData0x12();
            openRightCamera();
            return;
        }
        if (i == 22) {
            setDriveData0x16();
            return;
        }
        if (i == 23) {
            setDriveData0x17();
            return;
        }
        if (i == 49) {
            setAirData0x31();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
            return;
        }
        if (i == 101) {
            setDoorLock0x65();
            return;
        }
        if (i == 117) {
            setSetting0x75();
            return;
        }
        if (i == 240) {
            setVersionInfo0xF0();
            return;
        }
        if (i == 103) {
            setLightStatus0x67();
            return;
        }
        if (i == 104) {
            setDrivingAssistance0x68();
            return;
        }
        if (i == 232) {
            setPanoramic0xE8();
        } else if (i == 233 && !is0xe9Repeat(bArr)) {
            setCameraStatus();
        }
    }

    private boolean isWheelKeyDataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[5] | (iArr[4] << 8);
        if (this.mWheelKeyNow == i) {
            return false;
        }
        this.mWheelKeyNow = i;
        return true;
    }

    private void setKeyTrack0x11() {
        if (isWheelKeyDataChange()) {
            int i = this.mCanBusInfoInt[4];
            if (i == 0) {
                wheelKeyLongClick(0);
            } else if (i == 1) {
                wheelKeyLongClick(7);
            } else if (i == 2) {
                wheelKeyLongClick(8);
            } else if (i != 12) {
                switch (i) {
                    case 8:
                        wheelKeyLongClick(45);
                        break;
                    case 9:
                        wheelKeyLongClick(46);
                        break;
                    case 10:
                        wheelKeyLongClick(2);
                        break;
                }
            } else {
                wheelKeyLongClick(HotKeyConstant.K_CAN_CONFIG);
            }
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle1(bArr[9], bArr[8], 0, 5200, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setDriveData0x11() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(0, 6, this.mCanBusInfoInt[3] + "Km/h"));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorData0x12() {
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isSubShowSeatBelt = true;
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDoorData.isSubSeatBeltTie = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void openRightCamera() {
        if (CommUtil.isMiscReverse()) {
            return;
        }
        switchFCamera(this.mContext, this.mCanBusInfoInt[5] == 1);
    }

    private boolean isDriveData0x16Change() {
        if (Arrays.equals(this.mDriveDatax16Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveDatax16Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDriveData0x16() {
        if (isDriveData0x16Change()) {
            this.mMileageUnit = getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
            this.mFuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
            int fuelRange = getFuelRange(this.mCanBusInfoInt[15]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, new DecimalFormat("0.0").format((fuelRange / 21.0f) * mCanBusInfoInt[2]) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 2))));
            int[] iArr = this.mCanBusInfoInt;
            int driveData = getDriveData(iArr[3], iArr[4]);
            String str = (driveData / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData == 65535) {
                str = "--";
            }
            arrayList.add(new DriverUpdateEntity(0, 1, str));
            int[] iArr2 = this.mCanBusInfoInt;
            int driveData2 = getDriveData(iArr2[5], iArr2[6]);
            String str2 = (driveData2 / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData2 == 65535) {
                str2 = "--";
            }
            arrayList.add(new DriverUpdateEntity(0, 2, str2));
            int[] iArr3 = this.mCanBusInfoInt;
            int driveData3 = getDriveData(iArr3[9], iArr3[10], iArr3[11]);
            String str3 = (driveData3 / 10.0f) + this.mMileageUnit;
            if (driveData3 == 65535) {
                str3 = "--";
            }
            arrayList.add(new DriverUpdateEntity(0, 3, str3));
            int[] iArr4 = this.mCanBusInfoInt;
            int driveData4 = getDriveData(iArr4[7], iArr4[8]);
            String str4 = (driveData4 / 10.0f) + this.mFuelUnit;
            if (driveData4 == 65535) {
                str4 = "--";
            }
            arrayList.add(new DriverUpdateEntity(0, 4, str4));
            int[] iArr5 = this.mCanBusInfoInt;
            int driveData5 = getDriveData(iArr5[12], iArr5[13]);
            arrayList.add(new DriverUpdateEntity(0, 5, driveData5 != 65535 ? driveData5 + getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1)) : "--"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setDriveData0x17() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        this.mMileageUnit = getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 6, 1));
        this.mFuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[17], 4, 2));
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        float driveData = getDriveData(iArr[2], iArr[3], iArr[4]);
        if (driveData == 1.6777215E7f) {
            str = this.mInvalid;
        } else {
            str = (driveData / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 1, str));
        int[] iArr2 = this.mCanBusInfoInt;
        float driveData2 = getDriveData(iArr2[5], iArr2[6]);
        if (driveData2 == 65535.0f) {
            str2 = this.mInvalid;
        } else {
            str2 = (driveData2 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 2, str2));
        int[] iArr3 = this.mCanBusInfoInt;
        float driveData3 = getDriveData(iArr3[7], iArr3[8], iArr3[9]);
        if (driveData3 == 1.6777215E7f) {
            str3 = this.mInvalid;
        } else {
            str3 = (driveData3 / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 4, str3));
        int[] iArr4 = this.mCanBusInfoInt;
        float driveData4 = getDriveData(iArr4[10], iArr4[11]);
        if (driveData4 == 65535.0f) {
            str4 = this.mInvalid;
        } else {
            str4 = (driveData4 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 5, str4));
        int[] iArr5 = this.mCanBusInfoInt;
        float driveData5 = getDriveData(iArr5[12], iArr5[13], iArr5[14]);
        if (driveData5 == 1.6777215E7f) {
            str5 = this.mInvalid;
        } else {
            str5 = (driveData5 / 10.0f) + this.mMileageUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 7, str5));
        int[] iArr6 = this.mCanBusInfoInt;
        float driveData6 = getDriveData(iArr6[15], iArr6[16]);
        if (driveData6 == 65535.0f) {
            str6 = this.mInvalid;
        } else {
            str6 = (driveData6 / 10.0f) + this.mFuelUnit;
        }
        arrayList.add(new DriverUpdateEntity(1, 8, str6));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x31() {
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) == 1;
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        cleanAllBlow();
        switch (this.mCanBusInfoInt[6]) {
            case 2:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 3:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 4:
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_foot = true;
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_right_blow_window = true;
                break;
            case 5:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_left_blow_foot = true;
                GeneralAirData.front_right_blow_head = true;
                GeneralAirData.front_right_blow_foot = true;
                break;
            case 6:
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 7:
                GeneralAirData.front_left_blow_window = true;
                GeneralAirData.front_left_blow_head = true;
                GeneralAirData.front_right_blow_window = true;
                GeneralAirData.front_right_blow_head = true;
                break;
            case 8:
                GeneralAirData.rear_defog = true;
                break;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[9]);
        updateAirActivity(this.mContext, 1001);
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            Log.d("cwh", "0x41");
            this.mUiMgr.setShowRadar(this.mCanBusInfoInt[12] == 1);
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isPanoramic0xE8Change() {
        if (Arrays.equals(this.mPanoramicDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mPanoramicDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setPanoramic0xE8() {
        if (isPanoramic0xE8Change()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(this.mCanBusInfoInt[2])).setProgress(this.mCanBusInfoInt[2]));
            arrayList.add(new SettingUpdateEntity(1, 1, Byte.valueOf(this.mCanBusInfoByte[3])).setProgress(this.mCanBusInfoByte[3] + 5));
            arrayList.add(new SettingUpdateEntity(1, 2, Byte.valueOf(this.mCanBusInfoByte[4])).setProgress(this.mCanBusInfoByte[4] + 5));
            arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setCameraStatus() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 1));
        arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 2));
        arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 3));
        GeneralParkData.dataList = arrayList;
        updateParkUi(null, this.mContext);
        if (CommUtil.isMiscReverse() || !isFrontCameraStatusChange()) {
            return;
        }
        forceReverse(this.mContext, this.mCanBusInfoInt[4] == 1);
        Log.d("cwh", "在右视");
    }

    private boolean isLightDataChange() {
        if (Arrays.equals(this.mLightDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mLightDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isFrontCameraStatusChange() {
        int i = this.mCanBusInfoInt[4];
        if (this.mFrontCameraStatusNow == i) {
            return false;
        }
        this.mFrontCameraStatusNow = i;
        return true;
    }

    private void setLightStatus0x67() {
        if (isLightDataChange()) {
            SharePreUtil.setStringValue(this.mContext, "55_0x67", Base64.encodeToString(this.mCanBusInfoByte, 0));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(intFromByteWithBit)));
            if (intFromByteWithBit2 != 0) {
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(intFromByteWithBit2 - 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean isDoorLockDataChange() {
        if (Arrays.equals(this.mDoorLockDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDoorLockDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDoorLock0x65() {
        if (isDoorLockDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]) ? 1 : 0)));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2) - 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean isDrivingAssistanceChange() {
        if (Arrays.equals(this.mDrivingAssistanceDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDrivingAssistanceDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDrivingAssistance0x68() {
        if (isDrivingAssistanceChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 4) - 5)).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoByte[3], 0, 4)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean isSetting0x75Change() {
        if (Arrays.equals(this.mSetting0x75DataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mSetting0x75DataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setSetting0x75() {
        if (isSetting0x75Change()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(0, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2) - 1)));
            }
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(0, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) - 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -53, 0, (byte) i8, (byte) i6, 0, 0, z ? (byte) 1 : (byte) 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraInfoChange() {
        super.cameraInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 1});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void cameraDestroy() {
        super.cameraDestroy();
        CanbusMsgSender.sendMsg(new byte[]{22, -14, 7, 0});
    }

    private void wheelKeyShortClick(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void wheelKeyLongClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return true;
    }

    private boolean is0x12First() {
        if (!this.mIs0x12First) {
            return false;
        }
        this.mIs0x12First = false;
        return true;
    }

    private String resolveAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : i + getTempUnitC(this.mContext);
    }

    private void cleanAllBlow() {
        GeneralAirData.front_auto_wind_model = false;
        GeneralAirData.front_left_blow_window = false;
        GeneralAirData.front_left_blow_head = false;
        GeneralAirData.front_left_blow_foot = false;
        GeneralAirData.front_right_blow_window = false;
        GeneralAirData.front_right_blow_head = false;
        GeneralAirData.front_right_blow_foot = false;
        GeneralAirData.rear_left_blow_window = false;
        GeneralAirData.rear_left_blow_head = false;
        GeneralAirData.rear_left_blow_foot = false;
        GeneralAirData.rear_right_blow_window = false;
        GeneralAirData.rear_right_blow_head = false;
        GeneralAirData.rear_right_blow_foot = false;
        GeneralAirData.rear_defog = false;
    }

    private boolean isDoorDataChange() {
        if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.isSeatBeltTie == this.mSeatBeltTie && GeneralDoorData.isSubSeatBeltTie == this.mSubSeatBeltTie) {
            return false;
        }
        this.mLeftFrontStatus = GeneralDoorData.isLeftFrontDoorOpen;
        this.mRightFrontStatus = GeneralDoorData.isRightFrontDoorOpen;
        this.mLeftRearStatus = GeneralDoorData.isLeftRearDoorOpen;
        this.mRightRearStatus = GeneralDoorData.isRightRearDoorOpen;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mSeatBeltTie = GeneralDoorData.isSeatBeltTie;
        this.mSubSeatBeltTie = GeneralDoorData.isSubSeatBeltTie;
        return true;
    }

    private boolean isDoorFirst() {
        if (this.mIsDoorFirst) {
            this.mIsDoorFirst = false;
            if (!GeneralDoorData.isLeftFrontDoorOpen && !GeneralDoorData.isRightFrontDoorOpen && !GeneralDoorData.isLeftRearDoorOpen && !GeneralDoorData.isRightRearDoorOpen && !GeneralDoorData.isBackOpen && !GeneralDoorData.isFrontOpen) {
                return true;
            }
        }
        return false;
    }

    private boolean isVersionInfoChange() {
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isTrackDataChange() {
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArr2 = {bArr[8], bArr[9]};
        if (Arrays.equals(this.mTrackDataNow, bArr2)) {
            return false;
        }
        this.mTrackDataNow = Arrays.copyOf(bArr2, 2);
        return true;
    }

    private boolean is0xe9Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m0xe9Bytes)) {
            return true;
        }
        this.m0xe9Bytes = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }
}
