package com.hzbhd.canbus.car._5;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.LogUtil;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;

import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

import nfore.android.bt.res.NfDef;


public class MsgMgr extends AbstractMsgMgr {
    private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private static final int SEND_NORMAL_MESSAGE = 2;
    private int[] m0x12Data;
    private int[] m0x13Data;
    private int[] m0x42Data;
    private int[] m0x47Data;
    private boolean mBackStatus;
    private boolean mBatteryStatus;
    private boolean mBeltStatus;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private boolean mFrontStatus;
    private boolean mFuelStaus;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private boolean mLeftFrontRec;
    private boolean mLeftFrontStatus;
    private boolean mLeftRearRec;
    private boolean mLeftRearStatus;
    private int mLeftWindLevelNow;
    private int mLeftWindLevelRec;
    private int mOutDoorTemperature;
    private int[] mParkData;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int mRightWindLevelNow;
    private int mRightWindLevelRec;
    private int mVehicleSpeedData;
    private boolean mWashingFluidStatus;
    private int mWheelKeyData;
    private boolean mAirFirst = true;
    private boolean mDoorFirst = true;
    DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
    int result = 0;

    private int distanceToLocation(int i) {
        if (i >= 0 && i < 13) {
            return 1;
        }
        if (i >= 13 && i < 26) {
            return 2;
        }
        if (i >= 26 && i < 39) {
            return 3;
        }
        if (i >= 39 && i < 52) {
            return 4;
        }
        if (i >= 52 && i < 65) {
            return 5;
        }
        if (i >= 65 && i < 78) {
            return 6;
        }
        if (i >= 78 && i < 91) {
            return 7;
        }
        if (i >= 91 && i < 104) {
            return 8;
        }
        if (i >= 104 && i < 117) {
            return 9;
        }
        if (i < 117 || i >= 128) {
            return i >= 128 ? 255 : 0;
        }
        return 10;
    }

    public static int getLsb(int i) {
        return ((i & (-1)) >> 0) & 255;
    }

    public static int getMidLsb(int i) {
        return ((i & (-1)) >> 8) & 255;
    }

    public static int getMidMsb(int i) {
        return ((i & (-1)) >> 16) & 255;
    }

    public static int getMsb(int i) {
        return ((i & (-1)) >> 24) & 255;
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        initHandler(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AMapUtils.getInstance().startAMap(context);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        int i;
        int i2;
        super.onAMapCallBack(aMapEntity);
        if (aMapEntity.getDestinationDistance() == 0) {
            return;
        }
        if (aMapEntity.getCarDirection() == 7) {
            i = 7;
        } else if (aMapEntity.getCarDirection() == 8) {
            i = 8;
        } else if (aMapEntity.getCarDirection() == 1) {
            i = 1;
        } else if (aMapEntity.getCarDirection() == 2) {
            i = 2;
        } else if (aMapEntity.getCarDirection() == 3) {
            i = 3;
        } else if (aMapEntity.getCarDirection() == 4) {
            i = 4;
        } else if (aMapEntity.getCarDirection() == 5) {
            i = 5;
        } else {
            i = aMapEntity.getCarDirection() == 6 ? 6 : 0;
        }
        if (aMapEntity.getIcon() == 9) {
            i2 = 1;
        } else if (aMapEntity.getIcon() == 5) {
            i2 = 2;
        } else if (aMapEntity.getIcon() == 3) {
            i2 = 3;
        } else if (aMapEntity.getIcon() == 7) {
            i2 = 4;
        } else if (aMapEntity.getIcon() == 6) {
            i2 = 6;
        } else if (aMapEntity.getIcon() == 2) {
            i2 = 7;
        } else {
            i2 = aMapEntity.getIcon() == 4 ? 8 : 0;
        }
        if (aMapEntity.getDestinationDistance() != 0) {
            this.result = Integer.parseInt(this.mDecimalFormat.format((aMapEntity.getNextDistance() * 100) / aMapEntity.getDestinationDistance()));
        } else {
            this.result = 0;
        }
        CanbusMsgSender.sendMsg(new byte[]{22, -28, Byte.MIN_VALUE, Byte.MIN_VALUE, (byte) getMsb(aMapEntity.getNextDistance() * 10), (byte) getMidMsb(aMapEntity.getNextDistance() * 10), (byte) getMidLsb(aMapEntity.getNextDistance() * 10), (byte) getLsb(aMapEntity.getNextDistance() * 10), (byte) this.result, (byte) getMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidLsb(aMapEntity.getDestinationDistance() * 10), (byte) getLsb(aMapEntity.getDestinationDistance() * 10), (byte) i, (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(0, aMapEntity.getPlanTime().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(aMapEntity.getPlanTime().indexOf(":") + 1)), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(0, aMapEntity.getSurplusAllTimeStr().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(aMapEntity.getSurplusAllTimeStr().indexOf(":") + 1)), (byte) i2, 0, 0});
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        byte[] bytes = (i5 + ":" + i6).getBytes(StandardCharsets.UTF_8);
        if (bytes.length == 20) {
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -27}, bytes));
            return;
        }
        if (bytes.length > 20) {
            byte[] bArr = new byte[20];
            for (int i11 = 0; i11 < 20; i11++) {
                bArr[i11] = bytes[i11];
            }
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -27}, bArr));
            return;
        }
        if (bytes.length < 20) {
            byte[] bArr2 = new byte[20];
            for (int i12 = 0; i12 < 20; i12++) {
                if (i12 < bytes.length) {
                    bArr2[i12] = bytes[i12];
                } else {
                    bArr2[i12] = 0;
                }
            }
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -27}, bArr2));
        }
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
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
        if (i == 18) {
            setVehicleInfoOne0x12();
            return;
        }
        if (i == 19) {
            setVehicleInfoTwo0x13();
            return;
        }
        if (i == 66) {
            setRadarInfo0x42();
            return;
        }
        if (i == 71) {
            setParkingSystem0x47();
            return;
        }
        if (i == 240) {
            setCanVersion0xF0();
        } else if (i == 114) {
            setKeySpeedTrackRadar0x72();
        } else {
            if (i != 115) {
                return;
            }
            setAirDoor0x73();
        }
    }

    private void setKeySpeedTrackRadar0x72() {
        int i;
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        if (isVehicleSpeedDataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 0, this.mCanBusInfoInt[3] + "KM/H"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
            updateSpeedInfo(this.mCanBusInfoInt[3]);
        }
        if (isWheelKeyDataChange()) {
            int i2 = this.mCanBusInfoInt[4];
            if (i2 == 0) {
                realKeyLongClick2(0);
            } else if (i2 == 1) {
                realKeyLongClick2(7);
            } else if (i2 == 2) {
                realKeyLongClick2(8);
            } else if (i2 == 3) {
                realKeyLongClick2(3);
            } else if (i2 == 5) {
                realKeyLongClick2(HotKeyConstant.K_PHONE_ON_OFF);
            } else if (i2 == 6) {
                realKeyLongClick2(15);
            } else if (i2 != 15) {
                switch (i2) {
                    case 8:
                        realKeyLongClick2(45);
                        break;
                    case 9:
                        realKeyLongClick2(46);
                        break;
                    case 10:
                        realKeyLongClick2(2);
                        break;
                }
            } else {
                realKeyLongClick2(HotKeyConstant.K_SPEECH);
            }
        }
        if (isParkDataChange()) {
            int[] iArr = this.mCanBusInfoInt;
            int i3 = iArr[7];
            int i4 = iArr[6];
            if (i4 <= 0 || i3 != 0) {
                i = 0;
            } else {
                i3 = i4;
                i = 128;
            }
            GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle1((byte) i3, (byte) i, 0, 140, 16);
            GeneralParkData.radar_distance_disable = new int[]{0, 255};
            GeneralParkData.isShowDistanceNotShowLocationUi = true;
            int[] iArr2 = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarDistanceData(iArr2[8], iArr2[9], iArr2[10], iArr2[11]);
            int[] iArr3 = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarDistanceData(iArr3[12], iArr3[13], iArr3[14], iArr3[15]);
            GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.setRearRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[8]), distanceToLocation(this.mCanBusInfoInt[9]), distanceToLocation(this.mCanBusInfoInt[10]), distanceToLocation(this.mCanBusInfoInt[11]));
            RadarInfoUtil.setFrontRadarLocationData(11, distanceToLocation(this.mCanBusInfoInt[12]), distanceToLocation(this.mCanBusInfoInt[13]), distanceToLocation(this.mCanBusInfoInt[14]), distanceToLocation(this.mCanBusInfoInt[15]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    private void setAirDoor0x73() {
        if (DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[9])) {
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[9]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[9]);
            this.mRightFrontRec = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[9]);
            this.mLeftFrontRec = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[9]);
            this.mLeftRearRec = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
            this.mRightRearRec = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[9]);
            if (isDoorDataRepeat() && isDoorFirst()) {
                updateDoorView(this.mContext);
            }
        }
        byte[] bArrBytesExpectOneByte = bytesExpectOneByte(bytesExpectOneByte(this.mCanBusInfoByte, 9), 8);
        if (isOutDoorTemperatureChange()) {
            setOutDoorTem();
        }
        GeneralAirData.power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        if (isAirMsgRepeat(bArrBytesExpectOneByte) || isFirst()) {
            return;
        }
        GeneralAirData.in_out_auto_cycle = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 4, 2);
        GeneralAirData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.auto_2 = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        GeneralAirData.front_left_seat_heat_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[4]);
        GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[5]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[6]);
        GeneralAirData.front_left_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        boolean switchAcTemperature = ((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getSwitchAcTemperature();
        this.mLeftWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[(switchAcTemperature ? 1 : 0) + 6], 0, 3);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        GeneralAirData.front_right_blow_window = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[7]);
        this.mRightWindLevelRec = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7 - (switchAcTemperature ? 1 : 0)], 0, 3);
        int i = this.mLeftWindLevelNow;
        int i2 = this.mLeftWindLevelRec;
        if (i != i2) {
            this.mLeftWindLevelNow = i2;
            GeneralAirData.front_wind_level = i2;
            GeneralAirData.center_wheel = "Left";
        }
        int i3 = this.mRightWindLevelNow;
        int i4 = this.mRightWindLevelRec;
        if (i3 != i4) {
            this.mRightWindLevelNow = i4;
            GeneralAirData.front_wind_level = i4;
            GeneralAirData.center_wheel = "Right";
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setCanVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setParkingSystem0x47() {
        if (is0x47DataChange()) {
            SharePreUtil.setStringValue(this.mContext, "5_0x47", Base64.encodeToString(this.mCanBusInfoByte, 0));
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 7, 1))));
            arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 6, 1))));
            arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[15], 5, 1))));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setVehicleInfoOne0x12() {
        if (is0x12DataChange()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new DriverUpdateEntity(0, 1, this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + " L/100KM"));
            arrayList.add(new DriverUpdateEntity(0, 2, this.mCanBusInfoInt[9] + " L"));
            arrayList.add(new DriverUpdateEntity(0, 3, this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + " V"));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
        GeneralDoorData.isShowFuelWarning = true;
        GeneralDoorData.isShowBatteryWarning = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowWashingFluidWarning = true;
        GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
        GeneralDoorData.isSeatBeltTie = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
        GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
        if (isDoorDataRepeat() && isDoorFirst()) {
            updateDoorView(this.mContext);
        }
    }

    private void setVehicleInfoTwo0x13() {
        if (is0x13DataChange()) {
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 4, sb.append((iArr[2] * 256 * 256) + (iArr[3] * 256) + iArr[4]).append(".").append(Math.round(this.mCanBusInfoInt[7] * 100) / 100).append(" KM").toString()));
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 5, sb2.append((iArr2[10] * 256) + iArr2[11]).append(" RPM").toString()));
            StringBuilder sb3 = new StringBuilder();
            int[] iArr3 = this.mCanBusInfoInt;
            arrayList.add(new DriverUpdateEntity(0, 6, sb3.append(DataHandleUtils.getMsbLsbResult(iArr3[5], iArr3[6]) / 10.0d).append(" KM/H").toString()));
            updateGeneralDriveData(arrayList);
            updateDriveDataActivity(null);
        }
    }

    private void setRadarInfo0x42() {
        if (is0x42DataChange()) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.setRightRadarLocationData(10, distanceToLocationLeftRight(this.mCanBusInfoInt[2]), distanceToLocationLeftRight(this.mCanBusInfoInt[3]), distanceToLocationLeftRight(this.mCanBusInfoInt[4]), distanceToLocationLeftRight(this.mCanBusInfoInt[5]));
            RadarInfoUtil.setLeftRadarLocationData(10, distanceToLocationLeftRight(this.mCanBusInfoInt[6]), distanceToLocationLeftRight(this.mCanBusInfoInt[7]), distanceToLocationLeftRight(this.mCanBusInfoInt[8]), distanceToLocationLeftRight(this.mCanBusInfoInt[9]));
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
        }
    }

    void updateSettingData(int i) {
        LogUtil.showLog("updateSettingData:" + i);
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            String stringValue = SharePreUtil.getStringValue(this.mContext, "5_0x47", null);
            if (!TextUtils.isEmpty(stringValue)) {
                byte[] bArrDecode = Base64.decode(stringValue, 0);
                arrayList.add(new SettingUpdateEntity(0, 0, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[15], 7, 1))));
                arrayList.add(new SettingUpdateEntity(0, 1, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[15], 6, 1))));
                arrayList.add(new SettingUpdateEntity(0, 2, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(bArrDecode[15], 5, 1))));
            }
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 0}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, getAllBandTypeData(str)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, "P" + i).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str2 + getBandUnit(str)).getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        if (i7 == 240) {
            String string = Settings.System.getString(this.mContext.getContentResolver(), "reportForDiscEject");
            if (TextUtils.isEmpty(string)) {
                return;
            }
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), Base64.decode(string, 0));
            return;
        }
        if (i7 == 32) {
            int hour = getHour(i);
            int minute = getMinute(i);
            int second = getSecond(i);
            int i8 = 6;
            if (i6 != 1) {
                if (i6 == 5) {
                }
                int iRangeNumber = DataHandleUtils.rangeNumber(i3, 0, 99);
                sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
                sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, "T" + new DecimalFormat("000").format(iRangeNumber) + "/" + new DecimalFormat("000").format(i5)).getBytes()));
                sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, new DecimalFormat("00").format(hour) + ":" + new DecimalFormat("00").format(minute) + ":" + new DecimalFormat("00").format(second)).getBytes()));
            }
            i8 = 7;
            i3 = i4;
            int iRangeNumber2 = DataHandleUtils.rangeNumber(i3, 0, 99);
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) i8}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, "T" + new DecimalFormat("000").format(iRangeNumber2) + "/" + new DecimalFormat("000").format(i5)).getBytes()));
            sendMediaMessage(SourceConstantsDef.SOURCE_ID.MPEG.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, new DecimalFormat("00").format(hour) + ":" + new DecimalFormat("00").format(minute) + ":" + new DecimalFormat("00").format(second)).getBytes()));
        }
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, new String(bArr)).getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, ((b7 * 256) + i) + "/" + i2).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str + ":" + str2 + ":" + str3).getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, ((b6 * 256) + i) + "/" + i2).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str2 + ":" + str3 + ":" + str4).getBytes()));
    }

    @Override
    // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, 20}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.DTV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    private void realKeyLongClick2(int i) {
        realKeyLongClick2(this.mContext, i);
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
        int i = this.mCanBusInfoInt[8];
        return (i < 1 || i > 255) ? " " : ((i * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private boolean isFirst() {
        if (this.mAirFirst) {
            this.mAirFirst = false;
            if (!GeneralAirData.power) {
                return true;
            }
        }
        return false;
    }

    private String resolveLeftAndRightTemp(int i) {
        return i == 1 ? "LO" : i == 255 ? "HI" : (i < 18 || i > 26) ? " " : i + getTempUnitC(this.mContext);
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

    private boolean isDoorDataRepeat() {
        if (this.mLeftFrontRec == this.mLeftFrontStatus && this.mRightFrontRec == this.mRightFrontStatus && this.mLeftRearRec == this.mLeftRearStatus && this.mRightRearRec == this.mRightRearStatus && GeneralDoorData.isBackOpen == this.mBackStatus && GeneralDoorData.isFrontOpen == this.mFrontStatus && GeneralDoorData.isFuelWarning == this.mFuelStaus && GeneralDoorData.isBatteryWarning == this.mBatteryStatus && GeneralDoorData.isSeatBeltTie == this.mBeltStatus && GeneralDoorData.isWashingFluidWarning == this.mWashingFluidStatus) {
            return false;
        }
        this.mLeftFrontStatus = this.mLeftFrontRec;
        this.mRightFrontStatus = this.mRightFrontRec;
        this.mLeftRearStatus = this.mLeftRearRec;
        this.mRightRearStatus = this.mRightRearRec;
        this.mBackStatus = GeneralDoorData.isBackOpen;
        this.mFrontStatus = GeneralDoorData.isFrontOpen;
        this.mFuelStaus = GeneralDoorData.isFuelWarning;
        this.mBatteryStatus = GeneralDoorData.isBatteryWarning;
        this.mBeltStatus = GeneralDoorData.isSeatBeltTie;
        this.mWashingFluidStatus = GeneralDoorData.isWashingFluidWarning;
        return true;
    }

    private boolean isDoorFirst() {
        if (!this.mDoorFirst) {
            return true;
        }
        this.mDoorFirst = false;
        return GeneralDoorData.isLeftFrontDoorOpen || GeneralDoorData.isRightFrontDoorOpen || GeneralDoorData.isLeftRearDoorOpen || GeneralDoorData.isRightRearDoorOpen || GeneralDoorData.isBackOpen || GeneralDoorData.isFrontOpen || GeneralDoorData.isFuelWarning || GeneralDoorData.isBatteryWarning || GeneralDoorData.isWashingFluidWarning;
    }

    private boolean isOutDoorTemperatureChange() {
        int i = this.mOutDoorTemperature;
        int i2 = this.mCanBusInfoInt[8];
        if (i == i2) {
            return false;
        }
        this.mOutDoorTemperature = i2;
        return true;
    }

    private boolean isVehicleSpeedDataChange() {
        int i = this.mVehicleSpeedData;
        int i2 = this.mCanBusInfoInt[3];
        if (i == i2) {
            return false;
        }
        this.mVehicleSpeedData = i2;
        return true;
    }

    private boolean isWheelKeyDataChange() {
        int i = this.mWheelKeyData;
        int i2 = this.mCanBusInfoInt[4];
        if (i == i2) {
            return false;
        }
        this.mWheelKeyData = i2;
        return true;
    }

    private boolean isParkDataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArrCopyOfRange = Arrays.copyOfRange(iArr, 6, iArr.length);
        if (Arrays.equals(this.mParkData, iArrCopyOfRange)) {
            return false;
        }
        this.mParkData = Arrays.copyOf(iArrCopyOfRange, iArrCopyOfRange.length);
        return true;
    }

    private boolean is0x47DataChange() {
        if (Arrays.equals(this.m0x47Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x47Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x13DataChange() {
        if (Arrays.equals(this.m0x13Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x13Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x42DataChange() {
        if (Arrays.equals(this.m0x42Data, this.mCanBusInfoInt)) {
            return false;
        }
        int[] iArr = this.mCanBusInfoInt;
        this.m0x42Data = Arrays.copyOf(iArr, iArr.length);
        return true;
    }

    private boolean is0x12DataChange() {
        int[] iArr = this.mCanBusInfoInt;
        int[] iArr2 = {iArr[5], iArr[6], iArr[9], iArr[10], iArr[11]};
        if (Arrays.equals(this.m0x12Data, iArr2)) {
            return false;
        }
        this.m0x12Data = Arrays.copyOf(iArr2, 5);
        return true;
    }

    private int distanceToLocationLeftRight(int i) {
        if (i > 127) {
            return 255;
        }
        return (i / 12) + 1;
    }

    private void initHandler(final Context context) {
        HandlerThread handlerThread = new HandlerThread("MyApplication");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._5.MsgMgr.1
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

    private void sendMediaMessage(Object obj) {
        sendMediaMessage(obj, 0L);
    }

    private void sendMediaMessage(Object obj, long j) {
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
}
