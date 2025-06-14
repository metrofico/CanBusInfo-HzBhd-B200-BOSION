package com.hzbhd.canbus.car._291;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity;
import com.hzbhd.canbus.car_cus._291.AirActivity;
import com.hzbhd.canbus.car_cus._291.AirSeatView;
import com.hzbhd.canbus.car_cus._291.GeneralDzPQData;
import com.hzbhd.canbus.car_cus._291.MainActivity;
import com.hzbhd.canbus.car_cus._291.MainSettingActivity;
import com.hzbhd.canbus.car_cus._291.RadarView;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    private static final int SEND_GIVEN_MEDIA_MESSAGE = 1;
    private static final int SEND_NORMAL_MESSAGE = 2;
    private static final String SHARE_291_SMARTPOWER_DATA3 = "_291_smart_data3";
    private static final String SHARE_291_SMARTPOWER_DATA4 = "_291_smart_data4";
    private static final String SHARE_291_SMARTPOWER_DATA5 = "_291_smart_data5";
    private static final String SHARE_291_SMARTPOWER_DATA6 = "_291_smart_data6";
    private static final String SHARE_291_SMARTPOWER_MODE = "_291_smart_mode";
    private int data3;
    private int data4;
    private int data5;
    private int data6;
    private int left_front_hot;
    private int[] m0x12Data;
    private int[] m0x13Data;
    private int[] m0x42Data;
    private int[] m0x47Data;
    private AirSeatView mAirSeatView;
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
    private CusPanoramicView mPanoramicView;
    private int[] mParkData;
    private RadarView mRadarView;
    private boolean mRightFrontRec;
    private boolean mRightFrontStatus;
    private boolean mRightRearRec;
    private boolean mRightRearStatus;
    private int mRightWindLevelNow;
    private int mRightWindLevelRec;
    private int mVehicleSpeedData;
    private boolean mWashingFluidStatus;
    private int mWheelKeyData;
    private int mode;
    private int mode_int;
    private int right_front_hot;
    private int version;
    private boolean mAirFirst = true;
    private boolean mDoorFirst = true;
    private boolean isFirstAirSeat = true;
    DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
    int result = 0;

    private int distanceToLocation(int i) {
        if (i >= 0 && i < 13) {
            return 10;
        }
        if (i >= 13 && i < 26) {
            return 9;
        }
        if (i >= 26 && i < 39) {
            return 8;
        }
        if (i >= 39 && i < 52) {
            return 7;
        }
        if (i >= 52 && i < 65) {
            return 6;
        }
        if (i >= 65 && i < 78) {
            return 5;
        }
        if (i >= 78 && i < 91) {
            return 4;
        }
        if (i >= 91 && i < 104) {
            return 3;
        }
        if (i >= 104 && i < 117) {
            return 2;
        }
        if (i < 117 || i >= 128) {
            return i >= 128 ? 255 : 0;
        }
        return 1;
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        this.mContext = context;
        initHandler(context);
        initPower(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AMapUtils.getInstance().startAMap(context);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
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
        CanbusMsgSender.sendMsg(new byte[]{22, -28, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, (byte) getMsb(aMapEntity.getNextDistance() * 10), (byte) getMidMsb(aMapEntity.getNextDistance() * 10), (byte) getMidLsb(aMapEntity.getNextDistance() * 10), (byte) getLsb(aMapEntity.getNextDistance() * 10), (byte) this.result, (byte) getMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidLsb(aMapEntity.getDestinationDistance() * 10), (byte) getLsb(aMapEntity.getDestinationDistance() * 10), (byte) i, (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(0, aMapEntity.getPlanTime().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(aMapEntity.getPlanTime().indexOf(":") + 1)), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(0, aMapEntity.getSurplusAllTimeStr().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(aMapEntity.getSurplusAllTimeStr().indexOf(":") + 1)), (byte) i2, 0, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        super.canbusInfoChange(context, bArr);
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[0];
        if (i == 85) {
            canBusInfo0x55(bArr);
        } else if (i == 87) {
            canBusInfo0x57(bArr);
        }
    }

    private void canBusInfo0x55(byte[] bArr) {
        int i = this.mCanBusInfoInt[1];
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
            return;
        }
        if (i == 114) {
            setKeySpeedTrackRadar0x72();
        } else if (i == 115 && !isAirMsgRepeat(bArr)) {
            setAirDoor0x73();
        }
    }

    private void canBusInfo0x57(byte[] bArr) {
        GeneralDzSmartData.mode = this.mCanBusInfoInt[1];
        GeneralDzSmartData.mode_int = getModeInt(this.mCanBusInfoInt);
        GeneralDzSmartData.data3 = this.mCanBusInfoInt[2];
        GeneralDzSmartData.data4 = this.mCanBusInfoInt[3];
        GeneralDzSmartData.data5 = this.mCanBusInfoInt[4];
        GeneralDzSmartData.data6 = this.mCanBusInfoInt[5];
        GeneralDzSmartData.version = this.mCanBusInfoInt[7];
        if (this.mode == GeneralDzSmartData.mode && this.mode_int == GeneralDzSmartData.mode_int && this.data3 == GeneralDzSmartData.data3 && this.data4 == GeneralDzSmartData.data4 && this.data5 == GeneralDzSmartData.data5 && this.data6 == GeneralDzSmartData.data6 && this.version == GeneralDzSmartData.version) {
            return;
        }
        GeneralDzSmartData.show = true;
        this.mode = GeneralDzSmartData.mode;
        this.mode_int = GeneralDzSmartData.mode_int;
        this.data3 = GeneralDzSmartData.data3;
        this.data4 = GeneralDzSmartData.data4;
        this.data5 = GeneralDzSmartData.data5;
        this.data6 = GeneralDzSmartData.data6;
        this.version = GeneralDzSmartData.version;
        updateSmartActivity();
        updateSystemUIDrivingPattern(GeneralDzSmartData.mode);
        SharePreUtil.setIntValue(this.mContext, SHARE_291_SMARTPOWER_MODE, GeneralDzSmartData.mode);
        SharePreUtil.setIntValue(this.mContext, SHARE_291_SMARTPOWER_DATA3, GeneralDzSmartData.data3);
        SharePreUtil.setIntValue(this.mContext, SHARE_291_SMARTPOWER_DATA4, GeneralDzSmartData.data4);
        SharePreUtil.setIntValue(this.mContext, SHARE_291_SMARTPOWER_DATA5, GeneralDzSmartData.data5);
        SharePreUtil.setIntValue(this.mContext, SHARE_291_SMARTPOWER_DATA6, GeneralDzSmartData.data6);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        str.hashCode();
        switch (str) {
            case "rear.windows.close":
                MessageSender.sendVoiceMsg(20);
                break;
            case "rear.right.window.close":
                MessageSender.sendVoiceMsg(7);
                break;
            case "mirror.fold":
                MessageSender.sendVoiceMsg(11);
                break;
            case "mirror.unfold":
                MessageSender.sendVoiceMsg(12);
                break;
            case "skylight.open":
                MessageSender.sendVoiceMsg(10);
                break;
            case "front.right.window.close":
                MessageSender.sendVoiceMsg(3);
                break;
            case "front.windows.close":
                MessageSender.sendVoiceMsg(18);
                break;
            case "tailgate.open":
                MessageSender.sendVoiceMsg(13);
                break;
            case "rear.right.window.open":
                MessageSender.sendVoiceMsg(8);
                break;
            case "front.windows.open":
                MessageSender.sendVoiceMsg(17);
                break;
            case "skylight.close":
                MessageSender.sendVoiceMsg(9);
                break;
            case "rear.left.window.close":
                MessageSender.sendVoiceMsg(5);
                break;
            case "front.left.window.open":
                MessageSender.sendVoiceMsg(2);
                break;
            case "front.right.window.open":
                MessageSender.sendVoiceMsg(4);
                break;
            case "all.windows.close":
                MessageSender.sendVoiceMsg(16);
                break;
            case "rear.windows.open":
                MessageSender.sendVoiceMsg(19);
                break;
            case "tailgate.close":
                MessageSender.sendVoiceMsg(14);
                break;
            case "rear.left.window.open":
                MessageSender.sendVoiceMsg(6);
                break;
            case "all.windows.open":
                MessageSender.sendVoiceMsg(15);
                break;
            case "front.left.window.close":
                MessageSender.sendVoiceMsg(1);
                break;
        }
    }

    private void setKeySpeedTrackRadar0x72() {
        int i;
        GeneralDzData.speed = this.mCanBusInfoInt[3];
        GeneralDzData.gears = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDzData.handBrake = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
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
                realKeyLongClick2(14);
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
                realKeyLongClick2(HotKeyConstant.K_VOICE_PICKUP);
            }
        }
        GeneralDzPQData.vehicleLight = getLightStr(this.mCanBusInfoInt[9]);
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
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._291.MsgMgr.1
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    MsgMgr.this.getMyPanoramicView().updateParkUi();
                }
            });
        }
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr2[8], iArr2[9], iArr2[10], iArr2[11]);
        int[] iArr3 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr3[12], iArr3[13], iArr3[14], iArr3[15]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        if (getReverseState()) {
            GeneralDzData.show_radar = false;
            runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._291.MsgMgr.2
                @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
                public void callback() {
                    MsgMgr.this.getMyPanoramicView().refreRadarUi();
                }
            });
        } else {
            updateDZRadarView(this.mContext);
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
        if (isAirMsgRepeat(bArrBytesExpectOneByte)) {
            return;
        }
        CommUtil.printHexString("scyscyscy：", bArrBytesExpectOneByte);
        if (isFirst()) {
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
        GeneralAirData.front_wind_level = this.mLeftWindLevelRec;
        GeneralAirData.front_right_wind_level = this.mRightWindLevelRec;
        updateAirActivity("");
        if (PA6Utils.getAirShow(this.mContext) && !SystemUtil.isForeground(this.mContext, AirActivity.class.getName())) {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setComponent(new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._291.AirActivity"));
            intent.putExtra("type", "SETUP");
            this.mContext.startActivity(intent);
        }
        if (this.right_front_hot == GeneralAirData.front_right_seat_heat_level && this.left_front_hot == GeneralAirData.front_left_seat_heat_level) {
            return;
        }
        this.right_front_hot = GeneralAirData.front_right_seat_heat_level;
        this.left_front_hot = GeneralAirData.front_left_seat_heat_level;
        if (!this.isFirstAirSeat) {
            updateDZAirSeatView(this.mContext);
        } else {
            this.isFirstAirSeat = false;
        }
    }

    private void setCanVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setParkingSystem0x47() {
        if (is0x47DataChange()) {
            GeneralDzPQData.vehicleOut = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
            GeneralDzPQData.vehicleIn = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[15]);
            GeneralDzPQData.vehicleRadarMute = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[15]);
            updateMainSettingActivity();
        }
    }

    private void setVehicleInfoOne0x12() {
        if (is0x12DataChange()) {
            GeneralDzPQData.vehicleFuel = this.mCanBusInfoInt[5] + "." + this.mCanBusInfoInt[6] + " L/100KM";
            GeneralDzPQData.vehicleOil = this.mCanBusInfoInt[9] + " L";
            GeneralDzPQData.vehicleV = this.mCanBusInfoInt[10] + "." + this.mCanBusInfoInt[11] + " V";
            updateMainActivity(1);
        }
        GeneralDoorData.isShowFuelWarning = true;
        GeneralDoorData.isShowBatteryWarning = true;
        GeneralDoorData.isShowSeatBelt = true;
        GeneralDoorData.isShowWashingFluidWarning = true;
        GeneralDoorData.isFuelWarning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[8]);
        GeneralDoorData.isBatteryWarning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[8]);
        GeneralDoorData.isSeatBeltTie = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
        GeneralDoorData.isWashingFluidWarning = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[8]);
        updateMainActivity(2);
    }

    private void setVehicleInfoTwo0x13() {
        if (is0x13DataChange()) {
            StringBuilder sb = new StringBuilder();
            int[] iArr = this.mCanBusInfoInt;
            GeneralDzPQData.vehicleDistance = sb.append((iArr[2] * 256 * 256) + (iArr[3] * 256) + iArr[4]).append(" KM").toString();
            StringBuilder sb2 = new StringBuilder();
            int[] iArr2 = this.mCanBusInfoInt;
            GeneralDzPQData.vehicleRev = sb2.append((iArr2[10] * 256) + iArr2[11]).append(" RPM").toString();
            updateMainActivity(1);
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 0}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, getAllBandTypeData(str)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, "P" + i).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.FM.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str2 + getBandUnit(str)).getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void atvInfoChange() {
        super.atvInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, 8}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.ATV.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneTalkingInfoChange(bArr, z, z2);
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -46, 10}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, new String(bArr)).getBytes()));
        sendMediaMessage(DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.AUX1.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, " ").getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, ((b7 * 256) + i) + "/" + i2).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.MUSIC.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str + ":" + str2 + ":" + str3).getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -46, (byte) (b == 9 ? 14 : 13)}, DataHandleUtils.makeMediaInfoCenteredInBytes(12, " ").getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -30}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, ((b6 * 256) + i) + "/" + i2).getBytes()));
        sendMediaMessage(SourceConstantsDef.SOURCE_ID.VIDEO.ordinal(), DataHandleUtils.byteMerger(new byte[]{22, -29}, DataHandleUtils.makeMediaInfoCenteredInBytes(13, str2 + ":" + str3 + ":" + str4).getBytes()));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
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
        int[] iArr2 = {iArr[5], iArr[6], iArr[9], iArr[10]};
        if (Arrays.equals(this.m0x12Data, iArr2)) {
            return false;
        }
        this.m0x12Data = Arrays.copyOf(iArr2, 4);
        return true;
    }

    private int distanceToLocationLeftRight(int i) {
        if (i > 127) {
            return 255;
        }
        return (i / 12) + 1;
    }

    private void initPower(final Context context) {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._291.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgMgr.lambda$initPower$0(context);
            }
        }).start();
    }

    static /* synthetic */ void lambda$initPower$0(Context context) {
        try {
            GeneralDzSmartData.mode = SharePreUtil.getIntValue(context, SHARE_291_SMARTPOWER_MODE, 1);
            GeneralDzSmartData.data3 = SharePreUtil.getIntValue(context, SHARE_291_SMARTPOWER_DATA3, 0);
            GeneralDzSmartData.data4 = SharePreUtil.getIntValue(context, SHARE_291_SMARTPOWER_DATA4, 0);
            GeneralDzSmartData.data5 = SharePreUtil.getIntValue(context, SHARE_291_SMARTPOWER_DATA5, 0);
            GeneralDzSmartData.data6 = SharePreUtil.getIntValue(context, SHARE_291_SMARTPOWER_DATA6, 0);
            MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
        } catch (Exception e) {
            MessageSender.sendDzMsg(1, 0, 0, 0, 0);
            e.printStackTrace();
        }
    }

    private void initHandler(final Context context) {
        HandlerThread handlerThread = new HandlerThread("MyApplication");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.hzbhd.canbus.car._291.MsgMgr.3
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

    private int getModeInt(int[] iArr) {
        if (iArr.length > 4) {
            int i = iArr[1];
            int i2 = iArr[2];
            if (i == 3 || i == 4) {
                i2 = iArr[3];
            }
            return DataHandleUtils.getIntFromByteWithBit(i2, (i == 3 || i == 0) ? 4 : 0, 4) - 1;
        }
        return GeneralDzSmartData.mode;
    }

    private void updateSmartActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof SmartPowerActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateSystemUIDrivingPattern(int i) {
        if (i < 0 || i > 4) {
            return;
        }
        Intent intent = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
        intent.putExtra("_283_car_mode", String.valueOf(i));
        this.mContext.sendBroadcast(intent);
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

    private String getLightStr(int i) {
        if (i == 0) {
            return this.mContext.getString(R.string._291_close);
        }
        if (i == 100) {
            return this.mContext.getString(R.string._291_bigger);
        }
        return i + "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CusPanoramicView getMyPanoramicView() {
        if (this.mPanoramicView == null) {
            this.mPanoramicView = (CusPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        }
        return this.mPanoramicView;
    }

    private void updateMainActivity(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bundle_dezhong_what", i);
        if (getActivity() == null || !(getActivity() instanceof MainActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateMainSettingActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof MainSettingActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateAirActivity(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(AirActivity.UPDATE_WIND_POWER, str);
        if (getActivity() == null || !(getActivity() instanceof AirActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    protected void updateDZAirSeatView(Context context) {
        if (this.mAirSeatView == null) {
            this.mAirSeatView = new AirSeatView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._291.MsgMgr.4
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mAirSeatView.refreshUi();
            }
        });
    }

    protected void updateDZRadarView(Context context) {
        if (this.mRadarView == null) {
            this.mRadarView = new RadarView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._291.MsgMgr.5
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mRadarView.refreshUi();
            }
        });
    }
}
