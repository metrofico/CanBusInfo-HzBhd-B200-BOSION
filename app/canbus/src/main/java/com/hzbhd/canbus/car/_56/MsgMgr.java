package com.hzbhd.canbus.car._56;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.PanoramicBtnUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    static final String _55_IS_RADAR_OPEN = "_55_is_radar_open";
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private int[] mAmplifierDataNow;
    private boolean mBackStatus;
    private int mBtOn;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private int mData;
    private int mDifferent;
    private int[] mDisplayDataNow;
    private int[] mDoorLockDataNow;
    private int[] mDriveDatax16Now;
    private int[] mDriveDatax17Now;
    private int[] mDrivingAssistanceDataNow;
    private DecimalFormat mFormat00;
    private DecimalFormat mFormat000;
    private int mFrontCameraStatusNow;
    private boolean mFrontStatus;
    private String mFuelUnit;
    private boolean mIsMute;
    private boolean mIsShowRadar;
    private boolean mLeftFrontStatus;
    private int mLeftHeat;
    private boolean mLeftRearStatus;
    private int[] mLightDataNow;
    private int mMediaStatusNow;
    private String mMileageUnit;
    private int mPanoramicBtnNow;
    private int mPanoramicStatusNow;
    private int[] mRadarDataNow;
    private int[] mRemoteDataNow;
    private boolean mRightFrontStatus;
    private int mRightHeat;
    private boolean mRightRearStatus;
    private TimerTask mTimeTask;
    private Timer mTimer;
    private int[] mTireDataNow;
    private byte[] mTrackDataNow;
    private UiMgr mUiMgr;
    private String mUsbSonTimeNow;
    private byte[] mUsbSonTimeNowByte;
    private String mUsbSongAlbumNow;
    private String mUsbSongArtistNow;
    private String mUsbSongTitleNow;
    private int[] mVehicleInfoDataNow;
    private byte[] mVersionInfoNow;
    private int mWheelKeyNow;
    private byte[] m_0xe0;
    private byte[] mediaDataNow;
    private int vol;
    private String mInvalid = " - -";
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    private boolean mIsRightCameraFirst = true;

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

    private boolean isSupportDriveData0x16() {
        return true;
    }

    private boolean isSupportDriveData0x17() {
        return true;
    }

    private boolean isSupportPanoramic() {
        return true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        int currentCanDifferentId = getCurrentCanDifferentId();
        this.mDifferent = currentCanDifferentId;
        if (currentCanDifferentId == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 33, 2});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 32, 2});
        }
        if (context.getResources().getConfiguration().orientation == 1 && this.mDifferent == 104) {
            CanbusMsgSender.sendMsg(new byte[]{22, 36, 32});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        this.mCanBusInfoInt = getByteArrayToIntArray(bArr);
        this.mContext = context;
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        int i = this.mCanBusInfoInt[1];
        if (i == 17) {
            setKeyTrack0x11();
            return;
        }
        if (i == 18) {
            setDoorData0x12();
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
        if (i == 50) {
            setVehicleInfo0x32();
            return;
        }
        if (i == 65) {
            setRadarData0x41();
            return;
        }
        if (i == 72) {
            setTirePressure0x48();
            return;
        }
        if (i == 224) {
            if (is0xe0Repeat(bArr)) {
                return;
            }
            setMediaSwitch0xE0();
            return;
        }
        if (i == 232) {
            setPanoramic0xE8();
            return;
        }
        if (i != 240) {
            switch (i) {
                case 101:
                    setDoorLock0x65();
                    break;
                case 102:
                    setRemoteControl0x66();
                    break;
                case 103:
                    setLightStatus0x67();
                    break;
                case 104:
                    setDrivingAssistance0x68();
                    break;
                case 105:
                    setDisplay0x69();
                    break;
            }
            return;
        }
        setVersionInfo0xF0();
    }

    private boolean isWheelKeyDataChange() {
        int i = this.mWheelKeyNow;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return i2 == 1 || i2 == 2 || i2 == 8 || i2 == 9 || i2 == 13 || i2 == 14;
        }
        this.mWheelKeyNow = i2;
        return true;
    }

    private void setKeyTrack0x11() {
        updateSpeedInfo(this.mCanBusInfoInt[3]);
        if (isWheelKeyDataChange()) {
            switch (this.mCanBusInfoInt[4]) {
                case 0:
                    wheelKeyLongClick(0);
                    break;
                case 1:
                    wheelKeyLongClick(7);
                    break;
                case 2:
                    wheelKeyLongClick(8);
                    break;
                case 4:
                    wheelKeyLongClick(HotKeyConstant.K_SPEECH);
                    break;
                case 5:
                    wheelKeyLongClick(14);
                    break;
                case 6:
                    wheelKeyLongClick(HotKeyConstant.K_PHONE_OFF_RETURN);
                    break;
                case 8:
                    wheelKeyLongClick(47);
                    break;
                case 9:
                    wheelKeyLongClick(48);
                    break;
                case 10:
                    wheelKeyLongClick(3);
                    break;
                case 11:
                    wheelKeyLongClick(2);
                    break;
                case 12:
                    wheelKeyLongClick(HotKeyConstant.K_CAN_CONFIG);
                    break;
                case 13:
                    wheelKeyLongClick(45);
                    break;
                case 14:
                    wheelKeyLongClick(46);
                    break;
                case 15:
                    wheelKeyLongClick(49);
                    break;
            }
        }
        if (isTrackDataChange()) {
            byte[] bArr = this.mCanBusInfoByte;
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1(bArr[9], bArr[8], 0, 5200, 16);
            updateParkUi(null, this.mContext);
        }
    }

    private void setDoorData0x12() {
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

    private boolean isDriveData0x16Change() {
        if (Arrays.equals(this.mDriveDatax16Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveDatax16Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDriveData0x16() {
        if (isSupportDriveData0x16() && isDriveData0x16Change()) {
            int fuelRange = getFuelRange(this.mCanBusInfoInt[15]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(1, 0, new DecimalFormat("0.0").format((fuelRange / 21.0f) * r2[2]) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 0, 2))));
            int[] iArr = this.mCanBusInfoInt;
            int driveData = getDriveData(iArr[3], iArr[4]);
            String str = (driveData / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData == 65535) {
                str = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 1, str));
            int[] iArr2 = this.mCanBusInfoInt;
            int driveData2 = getDriveData(iArr2[5], iArr2[6]);
            String str2 = (driveData2 / 10.0f) + getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 2, 2));
            if (driveData2 == 65535) {
                str2 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 2, str2));
            int[] iArr3 = this.mCanBusInfoInt;
            int driveData3 = getDriveData(iArr3[9], iArr3[10], iArr3[11]);
            String mileageUnit = getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 6, 1));
            this.mMileageUnit = mileageUnit;
            String str3 = (driveData3 / 10.0f) + mileageUnit;
            if (driveData3 == 65535) {
                str3 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 3, str3));
            int[] iArr4 = this.mCanBusInfoInt;
            int driveData4 = getDriveData(iArr4[7], iArr4[8]);
            String fuelUnit = getFuelUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 4, 2));
            this.mFuelUnit = fuelUnit;
            String str4 = (driveData4 / 10.0f) + fuelUnit;
            if (driveData4 == 65535) {
                str4 = "--";
            }
            arrayList.add(new DriverUpdateEntity(1, 4, str4));
            int[] iArr5 = this.mCanBusInfoInt;
            int driveData5 = getDriveData(iArr5[12], iArr5[13]);
            arrayList.add(new DriverUpdateEntity(1, 5, driveData5 != 65535 ? driveData5 + getMileageUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[14], 7, 1)) : "--"));
            int i = this.mCanBusInfoInt[16];
            arrayList.add(new DriverUpdateEntity(1, 6, i + "km/h"));
            updateSpeedInfo(i);
            arrayList.add(new DriverUpdateEntity(1, 7, this.mCanBusInfoInt[17] + " h " + this.mCanBusInfoInt[18] + " m"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private boolean isDriveDatax17Change() {
        if (Arrays.equals(this.mDriveDatax17Now, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDriveDatax17Now = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDriveData0x17() {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        if (isSupportDriveData0x17() && isDriveDatax17Change()) {
            int[] iArr = this.mCanBusInfoInt;
            getFuelUnit(iArr[iArr.length - 1]);
            ArrayList arrayList = new ArrayList();
            int[] iArr2 = this.mCanBusInfoInt;
            float driveData = getDriveData(iArr2[2], iArr2[3], iArr2[4]);
            if (driveData == 1.6777215E7f) {
                str = this.mInvalid;
            } else {
                str = (driveData / 10.0f) + this.mMileageUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 1, str));
            int[] iArr3 = this.mCanBusInfoInt;
            float driveData2 = getDriveData(iArr3[5], iArr3[6]);
            if (driveData2 == 65535.0f) {
                str2 = this.mInvalid;
            } else {
                str2 = (driveData2 / 10.0f) + this.mFuelUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 2, str2));
            int[] iArr4 = this.mCanBusInfoInt;
            float driveData3 = getDriveData(iArr4[7], iArr4[8], iArr4[9]);
            if (driveData3 == 1.6777215E7f) {
                str3 = this.mInvalid;
            } else {
                str3 = (driveData3 / 10.0f) + this.mMileageUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 4, str3));
            int[] iArr5 = this.mCanBusInfoInt;
            float driveData4 = getDriveData(iArr5[10], iArr5[11]);
            if (driveData4 == 65535.0f) {
                str4 = this.mInvalid;
            } else {
                str4 = (driveData4 / 10.0f) + this.mFuelUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 5, str4));
            int[] iArr6 = this.mCanBusInfoInt;
            float driveData5 = getDriveData(iArr6[12], iArr6[13], iArr6[14]);
            if (driveData5 == 1.6777215E7f) {
                str5 = this.mInvalid;
            } else {
                str5 = (driveData5 / 10.0f) + this.mMileageUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 7, str5));
            int[] iArr7 = this.mCanBusInfoInt;
            float driveData6 = getDriveData(iArr7[15], iArr7[16]);
            if (driveData6 == 65535.0f) {
                str6 = this.mInvalid;
            } else {
                str6 = (driveData6 / 10.0f) + this.mFuelUnit;
            }
            arrayList.add(new DriverUpdateEntity(2, 8, str6));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setPanelKey0x21() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            panelKeyClick(0);
        }
        if (i == 69) {
            panelKeyClick(7);
            return;
        }
        if (i == 70) {
            panelKeyClick(8);
            return;
        }
        if (i == 87) {
            panelKeyClick(48);
            return;
        }
        if (i == 88) {
            panelKeyClick(47);
            return;
        }
        switch (i) {
            case 91:
                panelKeyClick(20);
                break;
            case 92:
                panelKeyClick(21);
                break;
            case 93:
                panelKeyClick(46);
                break;
            case 94:
                panelKeyClick(45);
                break;
        }
    }

    private void setAirData0x31() {
        int airWhat = getAirWhat();
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(this.mCanBusInfoByte) || isFirst()) {
            return;
        }
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        cleanAllBlow();
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 4);
        if (intFromByteWithBit == 3) {
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 4) {
            GeneralAirData.front_left_blow_foot = true;
            GeneralAirData.front_left_blow_window = true;
        } else if (intFromByteWithBit == 5) {
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        } else if (intFromByteWithBit == 6) {
            GeneralAirData.front_left_blow_head = true;
        } else if (intFromByteWithBit == 7) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
        } else if (intFromByteWithBit == 10) {
            GeneralAirData.front_left_blow_window = true;
            GeneralAirData.front_left_blow_head = true;
            GeneralAirData.front_left_blow_foot = true;
        }
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 4);
        if (intFromByteWithBit2 == 3) {
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 4) {
            GeneralAirData.front_right_blow_foot = true;
            GeneralAirData.front_right_blow_window = true;
        } else if (intFromByteWithBit2 == 5) {
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        } else if (intFromByteWithBit2 == 6) {
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit2 == 7) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
        } else if (intFromByteWithBit2 == 10) {
            GeneralAirData.front_right_blow_window = true;
            GeneralAirData.front_right_blow_head = true;
            GeneralAirData.front_right_blow_foot = true;
        }
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[8]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[9]);
        GeneralAirData.rear_wind_level = this.mCanBusInfoInt[10];
        if (isSeatHeatChange()) {
            return;
        }
        updateAirActivity(this.mContext, airWhat);
    }

    private void setRadarData0x41() {
        if (isRadarDataChange()) {
            this.mUiMgr.setShowRadar(this.mCanBusInfoInt[12] == 1);
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mDisableData = 255;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr2[6], 255, 255, iArr2[9]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setPanoramic0xE8() {
        if (!CommUtil.isMiscReverse() && isFrontCameraStatusChange()) {
            switchFCamera(this.mContext, this.mCanBusInfoInt[4] == 1);
        }
        if (isSupportPanoramic() && isPanoramicStatusChange()) {
            forceReverse(this.mContext, this.mCanBusInfoInt[6] == 1);
        }
        if (isPanoramicBtnChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new PanoramicBtnUpdateEntity(0, this.mCanBusInfoInt[3] == 1));
            arrayList.add(new PanoramicBtnUpdateEntity(1, this.mCanBusInfoInt[3] == 2));
            arrayList.add(new PanoramicBtnUpdateEntity(2, this.mCanBusInfoInt[3] == 3));
            GeneralParkData.dataList = arrayList;
            updateParkUi(null, this.mContext);
        }
    }

    private boolean isPanoramicBtnChange() {
        int i = this.mPanoramicBtnNow;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mPanoramicBtnNow = i2;
        return true;
    }

    private void setMediaSwitch0xE0() {
        if (isMediaStatusChange()) {
            int i = this.mCanBusInfoInt[2];
            if (i == 0) {
                sourceKeyClick(52);
                enterNoSource();
            }
            switch (i) {
                case 32:
                    sourceKeyClick(77);
                    break;
                case 33:
                    sourceKeyClick(76);
                    break;
                case 34:
                    sourceKeyClick(139);
                    break;
                case 35:
                    sourceKeyClick(140);
                    break;
                case 36:
                    sourceKeyClick(141);
                    break;
            }
        }
    }

    private boolean isLightDataChange() {
        if (Arrays.equals(this.mLightDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mLightDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setLightStatus0x67() {
        if (isLightDataChange()) {
            SharePreUtil.setStringValue(this.mContext, "55_0x67", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 3))));
            arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 3))));
            arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2) - 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean isRemoteDataChange() {
        if (Arrays.equals(this.mRemoteDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRemoteDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setRemoteControl0x66() {
        if (isRemoteDataChange()) {
            SharePreUtil.setStringValue(this.mContext, "55_0x66", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
            arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 1))));
            arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
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
            SharePreUtil.setStringValue(this.mContext, "55_0x65", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2) - 1)));
            }
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1))));
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
            SharePreUtil.setStringValue(this.mContext, "55_0x68", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2))));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
            if (intFromByteWithBit != 0) {
                arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(intFromByteWithBit - 1)));
            }
            arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))));
            arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 1))));
            int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
            if (intFromByteWithBit2 != 0) {
                arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(intFromByteWithBit2 - 1)));
            }
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private boolean isDisplayChange() {
        if (Arrays.equals(this.mDisplayDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mDisplayDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setDisplay0x69() {
        if (isDisplayChange()) {
            SharePreUtil.setStringValue(this.mContext, "55_0x69", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(6, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))));
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(6, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2) - 1)));
            }
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(6, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2) - 1)));
            }
            if (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) != 0) {
                arrayList.add(new SettingUpdateEntity(6, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2) - 1)));
            }
            arrayList.add(new SettingUpdateEntity(6, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 1))));
            arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 3, 1))));
            arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 1))));
            arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 5, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    void updateLanguage(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(i)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setAmplifierData0xA6() {
        if (isSupportAmplifier()) {
            byte[] bArr = this.mCanBusInfoByte;
            int[] iArr = {bArr[3], bArr[4], bArr[5], bArr[7], bArr[6]};
            if (isAmplifierDataChange(iArr)) {
                Bundle bundle = new Bundle();
                bundle.putIntArray("Amplifier", iArr);
                Intent intent = new Intent();
                intent.setAction("com.CAMRY_POWER_AMPLIFIER_DATA_CHANGED");
                intent.putExtras(bundle);
                this.mContext.sendBroadcast(intent);
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(9, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 4, 4))));
            arrayList.add(new SettingUpdateEntity(9, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 1, 2))));
            arrayList.add(new SettingUpdateEntity(9, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 0, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVersionInfo0xF0() {
        if (isVersionInfoChange()) {
            updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
        }
    }

    private boolean isVehicleInfoChange() {
        if (Arrays.equals(this.mVehicleInfoDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mVehicleInfoDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setVehicleInfo0x32() {
        if (isVehicleInfoChange()) {
            ArrayList arrayList = new ArrayList();
            int[] iArr = this.mCanBusInfoInt;
            int driveData = getDriveData(iArr[4], iArr[5]);
            String str = driveData + " rpm";
            if (driveData == 65535) {
                str = " -- ";
            }
            arrayList.add(new DriverUpdateEntity(0, 0, str));
            int[] iArr2 = this.mCanBusInfoInt;
            int driveData2 = getDriveData(iArr2[6], iArr2[7]);
            arrayList.add(new DriverUpdateEntity(0, 1, driveData2 != 65535 ? driveData2 + " km/h" : " -- "));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private boolean isTireDataChange() {
        if (Arrays.equals(this.mTireDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mTireDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private void setTirePressure0x48() {
        if (isTireDataChange()) {
            int intFromByteWithBit = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]) ? DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1) : 0;
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < 5; i++) {
                StringBuilder sb = new StringBuilder();
                int[] iArr = this.mCanBusInfoInt;
                arrayList.add(new TireUpdateEntity(i, intFromByteWithBit, new String[]{sb.append(iArr[i + 4] + iArr[i + 9]).append("kPa").toString()}));
            }
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
        }
    }

    void updateSettingData(int i) {
        LogUtil.showLog("updateSettingData:" + i);
        ArrayList arrayList = new ArrayList();
        switch (i) {
            case 2:
                String stringValue = SharePreUtil.getStringValue(this.mContext, "55_0x67", null);
                if (!TextUtils.isEmpty(stringValue)) {
                    byte[] bArrDecode = Base64.decode(stringValue, 0);
                    arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 0, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[3], 4, 3))));
                    arrayList.add(new SettingUpdateEntity(2, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[3], 2, 2))));
                    int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(bArrDecode[3], 0, 2);
                    if (intFromByteWithBit != 0) {
                        arrayList.add(new SettingUpdateEntity(2, 4, Integer.valueOf(intFromByteWithBit - 1)));
                        break;
                    }
                }
                break;
            case 3:
                String stringValue2 = SharePreUtil.getStringValue(this.mContext, "55_0x66", null);
                if (!TextUtils.isEmpty(stringValue2)) {
                    byte[] bArrDecode2 = Base64.decode(stringValue2, 0);
                    arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 1, 1))));
                    arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 0, 1))));
                    break;
                }
                break;
            case 4:
                String stringValue3 = SharePreUtil.getStringValue(this.mContext, "55_0x65", null);
                if (!TextUtils.isEmpty(stringValue3)) {
                    byte[] bArrDecode3 = Base64.decode(stringValue3, 0);
                    arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[3], 3, 1))));
                    int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(bArrDecode3[3], 1, 2);
                    if (intFromByteWithBit2 != 0) {
                        arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(intFromByteWithBit2 - 1)));
                    }
                    arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[3], 0, 1))));
                    break;
                }
                break;
            case 5:
                String stringValue4 = SharePreUtil.getStringValue(this.mContext, "55_0x68", null);
                if (!TextUtils.isEmpty(stringValue4)) {
                    byte[] bArrDecode4 = Base64.decode(stringValue4, 0);
                    arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 6, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 4, 2))));
                    arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(5, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 0, 2))));
                    break;
                }
                break;
            case 6:
                String stringValue5 = SharePreUtil.getStringValue(this.mContext, "55_0x69", null);
                if (!TextUtils.isEmpty(stringValue5)) {
                    byte[] bArrDecode5 = Base64.decode(stringValue5, 0);
                    arrayList.add(new SettingUpdateEntity(6, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[3], 0, 3))));
                    int intFromByteWithBit3 = DataHandleUtils.getIntFromByteWithBit(bArrDecode5[3], 3, 2);
                    if (intFromByteWithBit3 != 0) {
                        arrayList.add(new SettingUpdateEntity(6, 6, Integer.valueOf(intFromByteWithBit3 - 1)));
                    }
                    int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(bArrDecode5[3], 5, 2);
                    if (intFromByteWithBit4 != 0) {
                        arrayList.add(new SettingUpdateEntity(6, 5, Integer.valueOf(intFromByteWithBit4 - 1)));
                    }
                    int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(bArrDecode5[2], 0, 2);
                    if (intFromByteWithBit5 != 0) {
                        arrayList.add(new SettingUpdateEntity(6, 4, Integer.valueOf(intFromByteWithBit5 - 1)));
                    }
                    arrayList.add(new SettingUpdateEntity(6, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[2], 2, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[2], 3, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[2], 4, 1))));
                    arrayList.add(new SettingUpdateEntity(6, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[2], 5, 1))));
                    break;
                }
                break;
            case 7:
                arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(SharePreUtil.getIntValue(this.mContext, "55_language", 0))));
                break;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(new byte[]{22, -75, (byte) i5, (byte) i6, (byte) i7});
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        byte[] bArr = {22, -31, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        byte[] bArr2 = {22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (!bArr2.equals(this.mUsbSonTimeNowByte)) {
            CanbusMsgSender.sendMsg(bArr2);
            this.mUsbSonTimeNowByte = bArr2;
        }
        reportID3Info("", "", "", true, "ascii");
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.toString(), bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        String str4;
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i == 256) {
            i = 0;
        }
        byte allBandTypeData = getAllBandTypeData(str, (byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5);
        if (isBandAm(str)) {
            if (str2.length() == 4) {
                str4 = new DecimalFormat("00").format(i) + " " + str2 + "     ";
            } else {
                str4 = new DecimalFormat("00").format(i) + " 0" + str2 + "     ";
            }
        } else if (str2.length() == 5) {
            str4 = new DecimalFormat("00").format(i) + "  " + str2.substring(0, str2.length() - 1) + "    ";
        } else {
            str4 = new DecimalFormat("00").format(i) + " " + str2.substring(0, str2.length() - 1) + "    ";
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.FM.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, allBandTypeData}, str4.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("AUX".getBytes(), 0, bArr, 0, "AUX".getBytes().length);
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.ATV.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        byte[] bArr = new byte[9];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("A2DP".getBytes(), 0, bArr, 0, "A2DP".getBytes().length);
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, -123}, DataHandleUtils.byteMerger("000".getBytes(), bArr)));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        String str7 = new DecimalFormat("00").format(b5);
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED}, (String.format(new DecimalFormat("000").format(((b7 & 255) * 256) + (i & 255)), new Object[0]) + " 00      ").getBytes()));
        if (!str7.equals(this.mUsbSonTimeNow)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            this.mUsbSonTimeNow = str7;
        }
        reportID3Info(str4, "", "", false, "gbk");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        reportID3Info("", "", "", true, "ascii");
        byte[] bArr = {22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (bArr.equals(this.mUsbSonTimeNowByte)) {
            return;
        }
        CanbusMsgSender.sendMsg(bArr);
        this.mUsbSonTimeNowByte = bArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        if (this.mFormat00 == null) {
            this.mFormat00 = new DecimalFormat("00");
        }
        if (this.mFormat000 == null) {
            this.mFormat000 = new DecimalFormat("000");
        }
        String str5 = this.mFormat00.format(b5);
        String str6 = String.format(this.mFormat000.format(((b6 & 255) * 256) + (i & 255)), new Object[0]) + " 00      ";
        byte[] bArr = {22, -31, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED};
        if (!str5.equals(this.mUsbSonTimeNow)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -104, 0, 0, 0, 0, b4, b5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
            this.mUsbSonTimeNow = str5;
        }
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.byteMerger(bArr, str6.getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        byte[] bArr = {22, -104, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (bArr.equals(this.mUsbSonTimeNowByte)) {
            return;
        }
        CanbusMsgSender.sendMsg(bArr);
        this.mUsbSonTimeNowByte = bArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        byte[] bArr = new byte[12];
        Arrays.fill(bArr, (byte) 32);
        System.arraycopy("TV".getBytes(), 0, bArr, 0, "TV".getBytes().length);
        sendMediaMsg1(this.mContext, SourceConstantsDef.SOURCE_ID.DTV.toString(), DataHandleUtils.byteMerger(new byte[]{22, -31, 8}, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 1}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 2}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 3}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        byte[] bArr2 = new byte[30];
        Arrays.fill(bArr2, (byte) 32);
        bArr2[bArr.length] = 0;
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 4}, bArr2), new byte[]{0}));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (z && i == 0) {
            byte[] bArr2 = new byte[30];
            Arrays.fill(bArr2, (byte) 0);
            CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(DataHandleUtils.byteMerger(new byte[]{22, -25, 0}, bArr2), new byte[]{0}));
        }
        if (z) {
            this.mBtOn = 1;
        } else {
            this.mBtOn = 0;
        }
        FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
        Log.d("cwh", "mBtOn = " + this.mBtOn);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void deviceStatusInfoChange(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        super.deviceStatusInfoChange(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        int i13 = (i2 << 3) | (i3 << 4) | (i6 << 7) | (this.mBtOn << 6) | ((this.mIsMute ? 1 : 0) << 5) | (i11 << 2) | (i12 << 1);
        this.mData = i13;
        CanbusMsgSender.sendMsg(new byte[]{22, -26, (byte) i13, 0, 0, 0, (byte) this.vol, 0, 0, 0, 0, 0});
        Log.d("cwh", "设备状态改变，蓝牙图标是 : " + this.mBtOn);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        long j = i;
        byte[] bytes = ("VOL " + String.format(new DecimalFormat("00").format(j), new Object[0]) + "  " + String.format(new DecimalFormat("00").format(j), new Object[0]) + "  ").getBytes();
        this.vol = i;
        this.mIsMute = z;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 32}, bytes));
        FutureUtil.instance.DevicesStutasRepCanbus(this.mContext);
        Log.d("cwh", "当前音量改变");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -31, 10}, ("    " + new DecimalFormat("00").format((i / 60) % 60) + new DecimalFormat("00").format(i % 60) + "    ").getBytes()));
    }

    private byte getAllBandTypeData(String str, byte b, byte b2, byte b3, byte b4, byte b5) {
        str.hashCode();
        switch (str) {
            case "LW":
            case "AM1":
                return b4;
            case "MW":
            case "AM2":
                return b5;
            case "FM1":
                return b;
            case "FM2":
                return b2;
            case "FM3":
            case "OIRT":
                return b3;
            default:
                return (byte) 0;
        }
    }

    private void wheelKeyShortClick(int i) {
        realKeyClick4(this.mContext, i);
    }

    private void wheelKeyLongClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private void panelKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick5(context, i, iArr[2], iArr[3]);
    }

    private void sourceKeyClick(int i) {
        realKeyClick(this.mContext, i);
    }

    private String getDriveTime() {
        int[] iArr = this.mCanBusInfoInt;
        int driveData = getDriveData(iArr[8], iArr[9]);
        return new DecimalFormat("00").format(driveData / 60) + ":" + new DecimalFormat("00").format(driveData % 60);
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
        return ((this.mCanBusInfoInt[13] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return true;
    }

    private String resolveAirTemp(int i) {
        return i == 254 ? "LO" : i == 255 ? "HI" : (i * 0.5f) + getTempUnitC(this.mContext);
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
    }

    private int getAirWhat() {
        int[] iArrCopyOfRange = Arrays.copyOfRange(this.mCanBusInfoInt, 2, 9);
        int i = 0;
        int[] iArr = {this.mCanBusInfoInt[10]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArrCopyOfRange)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArrCopyOfRange, iArrCopyOfRange.length);
        this.mAirRearDataNow = Arrays.copyOf(iArr, 1);
        return i;
    }

    private byte getAllBandTypeData(String str) {
        str.hashCode();
        switch (str) {
            case "AM1":
                return (byte) 4;
            case "AM2":
                return (byte) 5;
            case "FM2":
                return (byte) 2;
            case "FM3":
                return (byte) 3;
            default:
                return (byte) 1;
        }
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

    private int getHour(int i) {
        return (i / 60) / 60;
    }

    private int getMinute(int i) {
        return (i / 60) % 60;
    }

    private int getSecond(int i) {
        return i % 60;
    }

    private boolean isDoorDataChange() {
        if (GeneralDoorData.isLeftFrontDoorOpen == this.mLeftFrontStatus && GeneralDoorData.isRightFrontDoorOpen == this.mRightFrontStatus && GeneralDoorData.isLeftRearDoorOpen == this.mLeftRearStatus && GeneralDoorData.isRightRearDoorOpen == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus) {
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

    private boolean isSeatHeatChange() {
        if (GeneralAirData.front_left_seat_heat_level == this.mLeftHeat && GeneralAirData.front_right_seat_heat_level == this.mRightHeat) {
            return false;
        }
        this.mLeftHeat = GeneralAirData.front_left_seat_heat_level;
        this.mRightHeat = GeneralAirData.front_right_seat_heat_level;
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

    private boolean isPanoramicStatusChange() {
        int i = this.mCanBusInfoInt[6];
        if (this.mPanoramicStatusNow == i) {
            return false;
        }
        this.mPanoramicStatusNow = i;
        return true;
    }

    private boolean isFrontCameraStatusChange() {
        int i = this.mCanBusInfoInt[4];
        if (this.mFrontCameraStatusNow == i) {
            if (this.mIsRightCameraFirst) {
                this.mIsRightCameraFirst = false;
            }
            return false;
        }
        this.mFrontCameraStatusNow = i;
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

    private boolean isMediaStatusChange() {
        int i = this.mCanBusInfoInt[2];
        if (this.mMediaStatusNow == i) {
            return false;
        }
        this.mMediaStatusNow = i;
        return true;
    }

    private boolean isAmplifierDataChange(int[] iArr) {
        if (Arrays.equals(iArr, this.mAmplifierDataNow)) {
            return false;
        }
        this.mAmplifierDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRadarDataChange() {
        if (Arrays.equals(this.mRadarDataNow, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.mRadarDataNow = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean isRadarOpen() {
        int i = this.mCanBusInfoInt[12];
        boolean z = i == 1;
        this.mUiMgr.setShowRadar(i == 1);
        return z;
    }

    private boolean isSupportAmplifier() {
        int i = this.mDifferent;
        return i == 1 || i == 2;
    }

    private boolean is0xe0Repeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.m_0xe0)) {
            return true;
        }
        this.m_0xe0 = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private void sendMediaMsg1(Context context, String str, byte[] bArr) {
        Log.i("AbstractMsgMgr", "sendMediaMsg: context: " + context + ", media: " + str);
        if (context == null) {
            return;
        }
        String string = FutureUtil.instance.getCurrentValidSource().toString();
        Log.i("AbstractMsgMgr", "sendMediaMsg: frontSeat:" + string);
        if (Settings.System.getInt(context.getContentResolver(), "btState", 1) == 1) {
            if ((str.equals(string) || SourceConstantsDef.SOURCE_ID.NORMAL_SOURCE.name().equals(str)) && !Arrays.equals(bArr, this.mediaDataNow)) {
                CanbusMsgSender.sendMsg(bArr);
                this.mediaDataNow = Arrays.copyOf(bArr, bArr.length);
                if (SourceConstantsDef.SOURCE_ID.BTPHONE.name().equals(str)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportAfterHangUp", Base64.encodeToString(bArr, 0));
                if (SourceConstantsDef.SOURCE_ID.MPEG.toString().equals(string)) {
                    return;
                }
                Settings.System.putString(context.getContentResolver(), "reportForDiscEject", Base64.encodeToString(bArr, 0));
            }
        }
    }

    /* JADX WARN: Type inference failed for: r7v0, types: [com.hzbhd.canbus.car._56.MsgMgr$1] */
    private void reportID3Info(final String str, final String str2, final String str3, final boolean z, final String str4) {
        new Thread() { // from class: com.hzbhd.canbus.car._56.MsgMgr.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                super.run();
                try {
                    if (z) {
                        sleep(1000L);
                    }
                    if (str.equals(MsgMgr.this.mUsbSongTitleNow) && str2.equals(MsgMgr.this.mUsbSongAlbumNow) && str3.equals(MsgMgr.this.mUsbSongArtistNow)) {
                        return;
                    }
                    MsgMgr.this.mUsbSongTitleNow = str;
                    MsgMgr.this.mUsbSongAlbumNow = str2;
                    MsgMgr.this.mUsbSongArtistNow = str3;
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -28, str, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -29, str2, str4);
                    sleep(100L);
                    MsgMgr.this.reportID3InfoFinal((byte) -30, str3, str4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportID3InfoFinal(byte b, String str, String str2) {
        try {
            CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, b}, DataHandleUtils.exceptBOMHead(str.getBytes(str2))), 34));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
