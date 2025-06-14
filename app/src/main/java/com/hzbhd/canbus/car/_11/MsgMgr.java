package com.hzbhd.canbus.car._11;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car_cus._448.Interface.ActionCallback;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.entity.TireUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAmplifierData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralHybirdData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_datas.GeneralTireData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SelectCanTypeUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private static final int SEND_NORMAL_MESSAGE = 2;
    static final int SHARE_11_AMPLIFIER_BAND_OFFSET = 2;
    static final int SHARE_11_AMPLIFIER_FADE_OFFSET = 7;
    static final String SHARE_11_IS_HAVE_SPARE_TIRE = "share_11_is_have_spare_tire";
    static final String SHARE_11_IS_SUPPOT_HYBRID = "share_11_is_suppot_hybrid";
    static final String SHARE_11_IS_SUPPOT_TIRE = "share_11_is_suppot_tire";
    private byte freqHi;
    private byte freqLo;
    int frontRadarStatus;
    private int[] mAirFrontDataNow;
    private int[] mAirRearDataNow;
    private boolean mBackStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private int mCanId;
    private Context mContext;
    private int mDifferent;
    private int[] mFrontRadarDataNow;
    private boolean mFrontStatus;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private ID3[] mMusicId3s;
    private boolean mPanoramicStatusNow;
    private int[] mRearRadarDataNow;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int mSkyWindowStatus;
    private UiMgr mUiMgr;
    private String mUsbSongAlbumNow;
    private String mUsbSongArtistNow;
    private String mUsbSongTitleNow;
    private byte[] mVersionInfoNow;
    private boolean mIsAirFirst = true;
    private boolean mIsDoorFirst = true;
    int nowAmplifierModel = 1;

    private int getDriveData(int i, int i2) {
        return (i * 256) + i2;
    }

    private String getFuelUnit(int i) {
        return i == 0 ? " MPG(US)" : i == 1 ? " KM/L" : i == 2 ? " L/100KM" : i == 3 ? " MPG(UK)" : "";
    }

    private String getMileageUnit(int i) {
        return i == 1 ? " MILE" : i == 2 ? " KM" : "";
    }

    private float getTireRule(int i) {
        if (i != 0) {
            return i != 2 ? 1.0f : 0.4f;
        }
        return 10.0f;
    }

    private String getTireUnit(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "" : " KPA" : " PSI" : " BAR";
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        this.mContext = context;
        this.frontRadarStatus = SharePreUtil.getIntValue(context, "FRONT_RADAR_KEY", 0);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mDifferent = getCurrentCanDifferentId();
        this.mCanId = SelectCanTypeUtil.getCurrentCanTypeId(context);
        GeneralParkData.isShowDistanceNotShowLocationUi = false;
        RadarInfoUtil.mMinIsClose = true;
        initHandler(context);
        initAmplifierData(context);
        initID3();
    }

    private void initAmplifierData(Context context) {
        ArrayList arrayList = new ArrayList();
        if (context != null) {
            getAmplifierData(context, this.mCanId, UiMgrFactory.getCanUiMgr(context).getAmplifierPageUiSet(context));
            arrayList.add(new byte[]{22, -125, 36, (byte) SharePreUtil.getIntValue(context, "share_11_language", 0)});
        }
        arrayList.add(new byte[]{22, -127, 1, 1});
        arrayList.add(new byte[]{22, -124, 8, 1});
        arrayList.add(new byte[]{22, -124, 1, (byte) (GeneralAmplifierData.frontRear + 7)});
        arrayList.add(new byte[]{22, -124, 2, (byte) (GeneralAmplifierData.leftRight + 7)});
        arrayList.add(new byte[]{22, -124, 4, (byte) (GeneralAmplifierData.bandBass + 2)});
        arrayList.add(new byte[]{22, -124, 5, (byte) (GeneralAmplifierData.bandTreble + 2)});
        arrayList.add(new byte[]{22, -124, 6, (byte) (GeneralAmplifierData.bandMiddle + 2)});
        arrayList.add(new byte[]{22, -124, 7, (byte) GeneralAmplifierData.volume});
        for (int i = 0; i < arrayList.size(); i++) {
            sendNormalMessage(arrayList.get(i), i * 80);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        try {
            int i = byteArrayToIntArray[1];
            if (i != 65) {
                switch (i) {
                    case 29:
                        setFrontRadarData0x1D();
                        break;
                    case 30:
                        setRearRadarData0x1E();
                        break;
                    case 31:
                        setHybrid0x1F();
                        break;
                    case 32:
                        setWheelKey0x20();
                        break;
                    case 33:
                        setTripInfoOne0x21();
                        break;
                    case 34:
                        setTripInfoTwo0x22();
                        break;
                    case 35:
                        setHistoricalFuel0x23();
                        break;
                    case 36:
                        setDoorData0x24();
                        break;
                    case 37:
                        setTireData0x25();
                        break;
                    case 38:
                        setVehicleSettings0x26();
                        break;
                    case 39:
                        setTripInfoThree0x27();
                        break;
                    case 40:
                        setAirData0x28();
                        break;
                    case 41:
                        setTrackData0x29();
                        break;
                    default:
                        switch (i) {
                            case 47:
                                setMediaCommand0x2F();
                                break;
                            case 48:
                                setVersionInfo0x30();
                                break;
                            case 49:
                                setAmplifierData0x31();
                                break;
                            case 50:
                                setSystemInfo0x32();
                                break;
                        }
                }
            } else {
                setVehicleData0x41();
            }
        } catch (Exception e) {
            Log.e("CanBusError", e.toString());
        }
    }

    private void setWheelKey0x20() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            realKeyClick2(0);
        }
        if (i == 1) {
            realKeyClick2(7);
            return;
        }
        if (i == 2) {
            realKeyClick2(8);
            return;
        }
        if (i == 3) {
            realKeyClick2(48);
            return;
        }
        if (i == 4) {
            realKeyClick2(47);
            return;
        }
        switch (i) {
            case 7:
                realKeyClick2(2);
                break;
            case 8:
                realKeyClick2(HotKeyConstant.K_SPEECH);
                break;
            case 9:
                realKeyClick2(14);
                break;
            case 10:
                realKeyClick2(15);
                break;
            default:
                switch (i) {
                    case 19:
                        realKeyClick2(45);
                        break;
                    case 20:
                        realKeyClick2(46);
                        break;
                    case 21:
                        realKeyClick2(50);
                        break;
                    case 22:
                        realKeyClick2(49);
                        break;
                    default:
                        switch (i) {
                            case 129:
                                realKeyClick2(7);
                                break;
                            case 130:
                                realKeyClick2(8);
                                break;
                            case 131:
                                realKeyClick2(45);
                                break;
                            case 132:
                                realKeyClick2(46);
                                break;
                            case 133:
                                realKeyClick2(21);
                                break;
                            case HotKeyConstant.K_SLEEP /* 134 */:
                                realKeyClick2(20);
                                break;
                            case 135:
                                realKeyClick2(HotKeyConstant.K_SLEEP);
                                break;
                            case 136:
                                realKeyClick2(2);
                                break;
                        }
                }
        }
    }

    private void setMediaCommand0x2F() {
        int i = this.mCanBusInfoInt[2];
        switch (i) {
            case 1:
                realKeyClick4(77);
                break;
            case 2:
                realKeyClick4(76);
                break;
            case 3:
                realKeyClick4(130);
                break;
            case 4:
                realKeyClick4(141);
                break;
            case 5:
                realKeyClick4(139);
                break;
            case 6:
                realKeyClick4(39);
                break;
            case 7:
                realKeyClick4(140);
                break;
            case 8:
                realKeyClick4(142);
                break;
            case 9:
                realKeyClick4(40);
                break;
            default:
                switch (i) {
                    case 17:
                        realKeyClick4(14);
                        break;
                    case 18:
                        realKeyClick4(15);
                        break;
                    case 19:
                        realKeyClick4(45);
                        break;
                    case 20:
                        realKeyClick4(46);
                        break;
                    case 21:
                        realKeyClick4(12);
                        break;
                    case 22:
                        realKeyClick4(HotKeyConstant.K_ACTION_APP);
                        break;
                    default:
                        switch (i) {
                            case 33:
                                realKeyClick4(33);
                                break;
                            case 34:
                                realKeyClick4(34);
                                break;
                            case 35:
                                realKeyClick4(35);
                                break;
                            case 36:
                                realKeyClick4(36);
                                break;
                            case 37:
                                realKeyClick4(37);
                                break;
                        }
                }
        }
    }

    private void setTripInfoOne0x21() {
        String mileageUnit = getMileageUnit(this.mCanBusInfoInt[8]);
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 0, (DataHandleUtils.rangeNumber(getDriveData(iArr[2], iArr[3]), 0, 9999) / 10.0f) + mileageUnit));
        int[] iArr2 = this.mCanBusInfoInt;
        int iRangeNumber = DataHandleUtils.rangeNumber(getDriveData(iArr2[4], iArr2[5]), 0, 5999);
        arrayList.add(new DriverUpdateEntity(0, 1, (iRangeNumber / 60) + " H " + (iRangeNumber % 60) + " M"));
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 2, DataHandleUtils.rangeNumber(getDriveData(iArr3[6], iArr3[7]), 0, 9999) + mileageUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTripInfoTwo0x22() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(0, 3, (getDriveData(iArr[3], iArr[4]) / 10.0f) + fuelUnit));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setHistoricalFuel0x23() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 6; i++) {
            int[] iArr = this.mCanBusInfoInt;
            int i2 = i * 2;
            int driveData = getDriveData(iArr[i2 + 3], iArr[i2 + 4]);
            String str = (driveData / 10.0f) + fuelUnit;
            if (driveData == 65535) {
                str = "";
            }
            arrayList.add(new DriverUpdateEntity(1, i, str));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setDoorData0x24() {
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]) || DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
        if (intFromByteWithBit == 1) {
            GeneralDoorData.skyWindowOpenLevel = 2;
        } else if (intFromByteWithBit == 2) {
            GeneralDoorData.skyWindowOpenLevel = 0;
        } else if (intFromByteWithBit == 3) {
            GeneralDoorData.skyWindowOpenLevel = 1;
        }
        this.mRightFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        this.mLeftFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        this.mRightRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        this.mLeftRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        if (!isDoorDataChange() || isDoorFirst()) {
            return;
        }
        updateDoorView(this.mContext);
    }

    private void setTireData0x25() {
        SharePreUtil.setBoolValue(this.mContext, SHARE_11_IS_SUPPOT_TIRE, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        if (DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2])) {
            SharePreUtil.setBoolValue(this.mContext, SHARE_11_IS_HAVE_SPARE_TIRE, DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]));
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1);
            String tireUnit = getTireUnit(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            float tireRule = getTireRule(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2));
            GeneralTireData.isHaveSpareTire = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
            ArrayList arrayList = new ArrayList();
            arrayList.add(new TireUpdateEntity(0, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[3] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(1, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[4] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(2, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[5] / tireRule) + tireUnit}));
            arrayList.add(new TireUpdateEntity(3, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[6] / tireRule) + tireUnit}));
            if (GeneralTireData.isHaveSpareTire) {
                arrayList.add(new TireUpdateEntity(4, intFromByteWithBit, new String[]{(this.mCanBusInfoInt[7] / tireRule) + tireUnit}));
            }
            GeneralTireData.dataList = arrayList;
            updateTirePressureActivity(null);
        }
    }

    private void setVehicleSettings0x26() {
        SharePreUtil.setStringValue(this.mContext, "11_0x26", Base64.encodeToString(this.mCanBusInfoByte, 0));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1))));
        arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 3))));
        arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 2, 2))));
        arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 2))));
        arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 1))));
        arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 1)));
        arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 3)));
        arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 7, 1))));
        arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 1))));
        arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 5, 1))));
        arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 1))));
        arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3))));
        arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1))));
        arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1))));
        arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2))));
        arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 2, 2))));
        arrayList.add(new SettingUpdateEntity(1, 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 2))));
        arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 2))));
        arrayList.add(new SettingUpdateEntity(1, 12, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(1, 13, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 2))));
        arrayList.add(new SettingUpdateEntity(1, 14, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 2))));
        arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2))));
        if (this.mDifferent != 1) {
            arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 4))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setTripInfoThree0x27() {
        String fuelUnit = getFuelUnit(this.mCanBusInfoInt[2]);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 15; i++) {
            int i2 = i * 2;
            int i3 = 32 - i2;
            int[] iArr = this.mCanBusInfoInt;
            if (i3 >= iArr.length) {
                return;
            }
            int driveData = getDriveData(iArr[31 - i2], iArr[i3]);
            String str = (driveData / 10.0f) + fuelUnit;
            if (driveData == 65535) {
                str = "";
            }
            arrayList.add(new DriverUpdateEntity(2, i, str));
        }
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setAirData0x28() {
        int airWhat = getAirWhat();
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        byte[] bArr = this.mCanBusInfoByte;
        byte[] bArrCopyOf = Arrays.copyOf(bArr, bArr.length);
        bArrCopyOf[3] = (byte) (bArrCopyOf[3] & 239);
        if (isAirMsgRepeat(bArrCopyOf) || isFirst()) {
            return;
        }
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.front_auto_wind_model = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 4);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[6]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.fahrenheit_celsius = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_temperature = resolveAirTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveAirTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_right_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 6, 2);
        GeneralAirData.front_left_seat_cold_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 4, 2);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 0, 2);
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

    private void setTrackData0x29() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = -TrackInfoUtil.getTrackAngle0(bArr[2], bArr[3], 0, 380, 12);
        updateParkUi(null, this.mContext);
    }

    private void setAmplifierData0x31() {
        GeneralAmplifierData.frontRear = resolveAmpData(2, 4) - 7;
        GeneralAmplifierData.leftRight = resolveAmpData(2, 0) - 7;
        GeneralAmplifierData.bandBass = resolveAmpData(3, 4) - 2;
        GeneralAmplifierData.bandTreble = resolveAmpData(3, 0) - 2;
        GeneralAmplifierData.bandMiddle = resolveAmpData(4, 4) - 2;
        GeneralAmplifierData.volume = this.mCanBusInfoInt[5];
        updateAmplifierActivity(null);
        saveAmplifierData(this.mContext, this.mCanId);
        SharePreUtil.setStringValue(this.mContext, "11_0x31", Base64.encodeToString(this.mCanBusInfoByte, 0));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 3, 1))));
        arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setRearRadarData0x1E() {
        if (isRearRadarDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(4, iArr[2], iArr[3], iArr[4], iArr[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            SharePreUtil.setStringValue(this.mContext, "11_0x1E", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
            arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
            arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
            arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 3) - 1));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setFrontRadarData0x1D() {
        int[] iArr;
        int i;
        int i2;
        int i3;
        int i4;
        if (isFrontRadarDataChange()) {
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(4, iArr2[2], iArr2[3], iArr2[4], iArr2[5]);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            this.frontRadarStatus = SharePreUtil.getIntValue(this.mContext, "FRONT_RADAR_KEY", 0);
            Log.d("frontRadarStatus", this.frontRadarStatus + " " + this.mCanBusInfoInt[2] + " " + this.mCanBusInfoInt[3] + " " + this.mCanBusInfoInt[4] + " " + this.mCanBusInfoInt[5]);
            if ((this.frontRadarStatus == 1 && (i4 = this.mCanBusInfoInt[2]) <= 2 && i4 != 0) || (((i = (iArr = this.mCanBusInfoInt)[3]) <= 2 && i != 0) || (((i2 = iArr[4]) <= 2 && i2 != 0) || ((i3 = iArr[5]) <= 2 && i3 != 0)))) {
                forceReverse(this.mContext, true);
            } else {
                forceReverse(this.mContext, false);
            }
            updateParkUi(null, this.mContext);
        }
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3[2] != 0 || iArr3[3] != 0 || iArr3[4] != 0 || iArr3[5] != 0) {
            GeneralParkData.pKeyRadarState = true;
            updatePKeyRadar();
            CountDownTimer.getInstance().reset(5000, new ActionCallback() { // from class: com.hzbhd.canbus.car._11.MsgMgr.1
                @Override // com.hzbhd.canbus.car_cus._448.Interface.ActionCallback
                public void toDo(Object obj) {
                    GeneralParkData.pKeyRadarState = false;
                    MsgMgr.this.updatePKeyRadar();
                }
            });
        } else {
            GeneralParkData.pKeyRadarState = false;
            updatePKeyRadar();
        }
    }

    private void setHybrid0x1F() {
        SharePreUtil.setBoolValue(this.mContext, SHARE_11_IS_SUPPOT_HYBRID, DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]));
        GeneralHybirdData.powerBatteryLevel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralHybirdData.isWheelDriveMotor = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isBatteryDriveMotor = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveWheel = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isEngineDriveMotor = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveWheel = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralHybirdData.isMotorDriveBattery = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        updateHybirdActivity(null);
    }

    private void setVersionInfo0x30() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setSystemInfo0x32() {
        if (isPanoramicStatusChange()) {
            forceReverse(this.mContext, this.mPanoramicStatusNow);
        }
        SharePreUtil.setStringValue(this.mContext, "11_0x32", Base64.encodeToString(this.mCanBusInfoByte, 0));
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 1))));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
        if (this.nowAmplifierModel != DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 1)) {
            if (!DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2])) {
                this.nowAmplifierModel = 0;
                getUigMgr(this.mContext).removeAmplifierModel();
            } else {
                this.nowAmplifierModel = 1;
            }
        }
    }

    void updateSettingData(int i) {
        LogUtil.showLog("updateSettingData:" + i);
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            String stringValue = SharePreUtil.getStringValue(this.mContext, "11_0x26", null);
            if (!TextUtils.isEmpty(stringValue)) {
                byte[] bArrDecode = Base64.decode(stringValue, 0);
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 7, 1))));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 4, 3))));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 2, 2))));
                arrayList.add(new SettingUpdateEntity(0, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[2], 0, 2))));
            }
        } else if (i == 1) {
            String stringValue2 = SharePreUtil.getStringValue(this.mContext, "11_0x26", null);
            if (!TextUtils.isEmpty(stringValue2)) {
                byte[] bArrDecode2 = Base64.decode(stringValue2, 0);
                arrayList.add(new SettingUpdateEntity(1, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 7, 1))));
                arrayList.add(new SettingUpdateEntity(1, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 6, 1))));
                arrayList.add(new SettingUpdateEntity(1, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 5, 1))));
                arrayList.add(new SettingUpdateEntity(1, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 4, 1))));
                arrayList.add(new SettingUpdateEntity(1, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[3], 0, 3)));
                arrayList.add(new SettingUpdateEntity(1, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 7, 1))));
                arrayList.add(new SettingUpdateEntity(1, 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 6, 1))));
                arrayList.add(new SettingUpdateEntity(1, 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 5, 1))));
                arrayList.add(new SettingUpdateEntity(1, 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 4, 1))));
                arrayList.add(new SettingUpdateEntity(1, 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 3, 1))));
                arrayList.add(new SettingUpdateEntity(1, 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode2[4], 0, 3))));
            }
        } else if (i == 2) {
            String stringValue3 = SharePreUtil.getStringValue(this.mContext, "11_0x26", null);
            if (!TextUtils.isEmpty(stringValue3)) {
                byte[] bArrDecode3 = Base64.decode(stringValue3, 0);
                arrayList.add(new SettingUpdateEntity(2, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[5], 7, 1))));
                arrayList.add(new SettingUpdateEntity(2, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode3[5], 6, 1))));
            }
        } else if (i == 3) {
            String stringValue4 = SharePreUtil.getStringValue(this.mContext, "11_0x26", null);
            if (!TextUtils.isEmpty(stringValue4)) {
                byte[] bArrDecode4 = Base64.decode(stringValue4, 0);
                arrayList.add(new SettingUpdateEntity(3, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 3, 1))).setProgress(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[3], 3, 1)));
                arrayList.add(new SettingUpdateEntity(3, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[5], 4, 2))));
                arrayList.add(new SettingUpdateEntity(3, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[5], 2, 2))));
                arrayList.add(new SettingUpdateEntity(3, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[6], 5, 2))));
                arrayList.add(new SettingUpdateEntity(3, 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[7], 4, 2))));
                if (this.mDifferent != 1) {
                    arrayList.add(new SettingUpdateEntity(3, 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode4[7], 0, 4))));
                }
            }
        } else if (i == 4) {
            String stringValue5 = SharePreUtil.getStringValue(this.mContext, "11_0x1E", null);
            if (!TextUtils.isEmpty(stringValue5)) {
                byte[] bArrDecode5 = Base64.decode(stringValue5, 0);
                arrayList.add(new SettingUpdateEntity(4, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[6], 7, 1))));
                arrayList.add(new SettingUpdateEntity(4, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[6], 6, 1))));
                arrayList.add(new SettingUpdateEntity(4, 3, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[6], 4, 1))));
                arrayList.add(new SettingUpdateEntity(4, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[6], 0, 3))).setProgress(DataHandleUtils.getIntFromByteWithBit(bArrDecode5[6], 0, 3) - 1));
            }
        } else if (i == 5) {
            String stringValue6 = SharePreUtil.getStringValue(this.mContext, "11_0x31", null);
            String stringValue7 = SharePreUtil.getStringValue(this.mContext, "11_0x32", null);
            if (!TextUtils.isEmpty(stringValue6) && !TextUtils.isEmpty(stringValue7)) {
                byte[] bArrDecode6 = Base64.decode(stringValue6, 0);
                byte[] bArrDecode7 = Base64.decode(stringValue7, 0);
                arrayList.add(new SettingUpdateEntity(5, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode6[4], 3, 1))));
                arrayList.add(new SettingUpdateEntity(5, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode6[6], 0, 1))));
                arrayList.add(new SettingUpdateEntity(5, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode7[2], 6, 1))));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void setVehicleData0x41() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i == 1) {
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit5(iArr[3]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
            int intFromByteWithBit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 5, 2);
            if (intFromByteWithBit == 1) {
                GeneralDoorData.skyWindowOpenLevel = 2;
            } else if (intFromByteWithBit == 2) {
                GeneralDoorData.skyWindowOpenLevel = 0;
            } else if (intFromByteWithBit == 3) {
                GeneralDoorData.skyWindowOpenLevel = 1;
            }
            if (!isDoorDataChange() || isDoorFirst()) {
                return;
            }
            updateDoorView(this.mContext);
            return;
        }
        if (i != 2) {
            if (i != 3) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 9, sb.append((iArr2[3] * 256) + iArr2[4]).append(" RPM").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            setOutDoorTem();
            return;
        }
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 3, sb2.append((iArr3[3] * 256 * 256) + (iArr3[4] * 256) + iArr3[5]).append(" km").toString()));
        StringBuilder sb3 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 4, sb3.append((iArr4[6] * 256) + iArr4[7]).append(" km").toString()));
        StringBuilder sb4 = new StringBuilder();
        int[] iArr5 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 5, sb4.append(((((iArr5[8] * 256) * 256) + (iArr5[9] * 256)) + iArr5[10]) / 10.0f).append(" km").toString()));
        StringBuilder sb5 = new StringBuilder();
        int[] iArr6 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 6, sb5.append(((((iArr6[11] * 256) * 256) + (iArr6[12] * 256)) + iArr6[13]) / 10.0f).append(" km").toString()));
        StringBuilder sb6 = new StringBuilder();
        int[] iArr7 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 7, sb6.append(((iArr7[14] * 256) + iArr7[15]) / 100.0f).append(" km/h").toString()));
        StringBuilder sb7 = new StringBuilder();
        int[] iArr8 = this.mCanBusInfoInt;
        arrayList2.add(new DriverUpdateEntity(0, 8, sb7.append(((iArr8[16] * 256) + iArr8[17]) / 10.0f).append(" km/h").toString()));
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchChange(String str) {
        super.sourceSwitchChange(str);
        if (SourceConstantsDef.SOURCE_ID.NULL.name().equals(str)) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 0, 0, 0, 0, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        if (z) {
            return;
        }
        initAmplifierData(this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 1, 0, 0, 0, 0, 0});
        sndToyotaSimplePhone(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 3, 0, 0, 0, 0, 0});
        sndToyotaSimplePhone(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 2, 0, 0, 0, 0, 0});
        sndToyotaSimplePhone(bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4, 0, 0, 0, 0, 0});
        sndToyotaSimplePhone(bArr);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [com.hzbhd.canbus.car._11.MsgMgr$2] */
    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (!z2 && z) {
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 4});
        } else {
            if (z) {
                return;
            }
            CanbusMsgSender.sendMsg(new byte[]{22, -64, 5, 64, 0});
            new Thread() { // from class: com.hzbhd.canbus.car._11.MsgMgr.2
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() {
                    super.run();
                    for (int i4 = 0; i4 < 5; i4++) {
                        try {
                            sleep(100L);
                            MsgMgr.this.btPhoneCallLogInfoChange(i4, 0, "");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }.start();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneCallLogInfoChange(int i, int i2, String str) {
        super.btPhoneCallLogInfoChange(i, i2, str);
        if (i >= 5) {
            return;
        }
        CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -57, 16, (byte) (i + 1), (byte) i2}, Util.phoneNum2UnicodeLittleExtra(str.getBytes())));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        byte b2;
        byte b3;
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            String string = Settings.System.getString(this.mContext.getContentResolver(), "currentReport_disc");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), Base64.decode(string, 0));
            return;
        }
        if (i7 == 32) {
            if (i6 == 1) {
                b2 = (byte) (i4 / 256);
                b3 = (byte) (i4 % 256);
            } else {
                b2 = (byte) ((i3 >> 8) & 255);
                b3 = (byte) (i3 & 255);
            }
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 2, 16, b3, b2, 0, 0, 0, 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), new byte[]{22, -64, 8, 16, (byte) i, b7, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), b4, b5});
        this.mMusicId3s[0].info = str4;
        this.mMusicId3s[1].info = str5;
        this.mMusicId3s[2].info = str6;
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        this.mMusicId3s[0].info = " ";
        this.mMusicId3s[1].info = " ";
        this.mMusicId3s[2].info = " ";
        reportID3Info(this.mMusicId3s);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), new byte[]{22, -64, 8, 16, (byte) i, b6, (byte) (i2 & 255), (byte) ((i2 >> 8) & 255), b4, b5});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.AUX1.name(), new byte[]{22, -64, 7, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        CanbusMsgSender.sendMsg(new byte[]{22, -60, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicInfoChange() {
        super.btMusicInfoChange();
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.name(), new byte[]{22, -64, 11, 0, 0, 0, 0, 0, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte[] bArrByteMerger = Util.byteMerger(new byte[]{22, -56, 16, 6}, Util.phoneNum2UnicodeLittleExtra(str3.getBytes()));
        Settings.System.putString(this.mContext.getContentResolver(), "currentReportPrev", Base64.encodeToString(bArrByteMerger, 0));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), bArrByteMerger);
        getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, 1, getAllBandTypeData(str, (byte) 0, (byte) 0, (byte) 0, (byte) 16, (byte) 16), this.freqLo, this.freqHi, (byte) i});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        CanbusMsgSender.sendMsg(Util.byteMerger(new byte[]{22, -56, 16, 6}, Util.phoneNum2UnicodeLittleExtra("".getBytes())));
    }

    private void realKeyClick4(int i) {
        realKeyClick(this.mContext, i);
    }

    private void realKeyClick2(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick2(context, i, iArr[2], iArr[3]);
    }

    private String getDriveData(int i, int i2, String str) {
        int i3 = (i * 256) + i2;
        return i3 == 65535 ? " " : i3 + str;
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
        return ((int) this.mCanBusInfoByte[9]) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (!this.mIsAirFirst) {
            return false;
        }
        this.mIsAirFirst = false;
        return !GeneralAirData.power;
    }

    private String resolveAirTemp(int i) {
        if (GeneralAirData.fahrenheit_celsius) {
            if (i != 0) {
                if (i == 255) {
                    return "HIGH";
                }
                if (i >= 1 && i <= 254) {
                    return i + getTempUnitF(this.mContext);
                }
                return " ";
            }
            return "LOW";
        }
        if (i != 0) {
            if (i == 31) {
                return "HIGH";
            }
            if (i >= 1 && i <= 29) {
                return (((i - 1) * 0.5f) + 18.0f) + getTempUnitC(this.mContext);
            }
            if (i >= 32 && i <= 35) {
                return (i * 0.5f) + getTempUnitC(this.mContext);
            }
            if (i >= 36 && i <= 37) {
                return ((i * 0.5f) - 3.0f) + getTempUnitC(this.mContext);
            }
            return " ";
        }
        return "LOW";
    }

    private int getAirWhat() {
        int[] iArr = this.mCanBusInfoInt;
        int i = 0;
        int[] iArr2 = {iArr[2], iArr[3] & 239, iArr[4], iArr[5], iArr[6] & 241, iArr[7]};
        int[] iArr3 = {iArr[8], iArr[9]};
        if (!Arrays.equals(this.mAirFrontDataNow, iArr2)) {
            i = 1001;
        } else if (!Arrays.equals(this.mAirRearDataNow, iArr3)) {
            i = 1002;
        }
        this.mAirFrontDataNow = Arrays.copyOf(iArr2, 6);
        this.mAirRearDataNow = Arrays.copyOf(iArr3, 2);
        return i;
    }

    private void sndToyotaSimplePhone(byte[] bArr) {
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -56, 16, 1}, phoneNum2UnicodeLittleExtra(bArr)));
    }

    private void getFreqByteHiLo(String str, String str2) {
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            this.freqHi = (byte) (Integer.parseInt(str2) >> 8);
            this.freqLo = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            this.freqHi = (byte) (i >> 8);
            this.freqLo = (byte) (i & 255);
        }
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

    private byte[] phoneNum2UnicodeLittleExtra(byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr2[i2] = (byte) (bArr[i] & 255);
            bArr2[i2 + 1] = 0;
        }
        return bArr2;
    }

    private boolean isDoorDataChange() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.skyWindowOpenLevel == this.mSkyWindowStatus) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mSkyWindowStatus = GeneralDoorData.skyWindowOpenLevel;
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
        if (Arrays.equals(this.mVersionInfoNow, this.mCanBusInfoByte)) {
            return false;
        }
        byte[] bArr = this.mCanBusInfoByte;
        this.mVersionInfoNow = Arrays.copyOf(bArr, bArr.length);
        return true;
    }

    private boolean isPanoramicStatusChange() {
        boolean z = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]) && DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (this.mPanoramicStatusNow == z) {
            return false;
        }
        this.mPanoramicStatusNow = z;
        return true;
    }

    private boolean isSupportAmplifier() {
        return this.mDifferent == 3;
    }

    private int resolveAmpData(int i, int i2) {
        return DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[i], i2, 4);
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

    void updateSetting(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void initHandler(final Context context) {
        HandlerThread handlerThread = new HandlerThread("MyApplication");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._11.MsgMgr.3
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == 1) {
                    MsgMgr.this.sendMediaMsg(context, SourceConstantsDef.SOURCE_ID.values()[message.arg1].name(), (byte[]) message.obj);
                } else if (message.what == 2) {
                    CanbusMsgSender.sendMsg((byte[]) message.obj);
                }
            }
        };
    }

    private void sendNormalMessage(Object obj) {
        sendNormalMessage(obj, 0L);
    }

    private void sendNormalMessage(Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 2;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private void sendMediaMessage(int i, Object obj) {
        sendMediaMessage(i, obj, 0L);
    }

    private void sendMediaMessage(int i, Object obj, long j) {
        if (this.mHandler == null) {
            Log.i("ljq", "sendMediaMessage: mHandler is null");
            return;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = 1;
        messageObtain.arg1 = i;
        messageObtain.obj = obj;
        this.mHandler.sendMessageDelayed(messageObtain, j);
    }

    private class ID3 {
        private int command;
        private String info;
        private String record;

        private ID3(int i) {
            this.command = i;
            this.info = "";
            this.record = "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isId3Change() {
            if (this.record.equals(this.info)) {
                return false;
            }
            recordId3Info();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void recordId3Info() {
            this.record = this.info;
        }
    }

    private void initID3() {
        this.mMusicId3s = new ID3[]{new ID3(3), new ID3(4), new ID3(5)};
    }

    private void reportID3Info(ID3[] id3Arr) {
        for (ID3 id3 : id3Arr) {
            if (id3.isId3Change()) {
                for (ID3 id32 : id3Arr) {
                    id32.recordId3Info();
                    reportID3InfoFinal(id32.command, id32.info);
                }
                return;
            }
        }
    }

    private void reportID3InfoFinal(int i, String str) {
        try {
            sendNormalMessage(DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(new byte[]{22, -56, 16, (byte) i}, DataHandleUtils.exceptBOMHead(str.getBytes("unicodeLittle"))), 68), i * 80);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private UiMgr getUigMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    public void updateSettings(Context context, String str, int i, int i2, int i3) {
        SharePreUtil.setIntValue(context, str, i3);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }
}
