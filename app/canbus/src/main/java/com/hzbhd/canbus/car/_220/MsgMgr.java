package com.hzbhd.canbus.car._220;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.R;
import com.hzbhd.canbus.entity.DriverUpdateEntity;
import com.hzbhd.canbus.entity.SettingUpdateEntity;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import nfore.android.bt.res.NfDef;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    protected static final String CAN_220_SAVE_360 = "_220_SAVE_360";
    private static boolean isLastLongClick = false;
    private static int lastPanelKeyVal = 0;
    private static int lastPanelSt = 0;
    private static int lastSwcKey = 0;
    private static int lastSwcSt = 0;
    private static int longClickCount = 0;
    private static int mDifferentId = 0;
    private static boolean mIsBackLast = false;
    private static boolean mIsBelt = false;
    private static boolean mIsFLDoorLast = false;
    private static boolean mIsFRDoorLast = false;
    private static boolean mIsFrontLast = false;
    private static boolean mIsHandBreak = false;
    private static boolean mIsRLDoorLast = false;
    private static boolean mIsRRDoorLast = false;
    protected static boolean mLast360st = false;
    protected static int mNewEnergyData;
    protected static int mNewEnergyEndTime;
    protected static int mNewEnergyStartTime;
    private int eachId;
    private boolean isCallIn = false;
    private boolean isHangUp = false;
    LocationListener locationListener = new LocationListener() { // from class: com.hzbhd.canbus.car._220.MsgMgr.1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
            Log.i("onStatusChanged", "onStatusChanged");
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
            MsgMgr.this.mLocationManager.getLastKnownLocation(str);
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) {
            if (location != null) {
                MsgMgr.this.sndCurCompassSt(location);
            }
        }
    };
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private Context mContext;
    private LocationManager mLocationManager;
    private MyPanoramicView mPanoramicView;
    private UiMgr mUiMgr;

    private int getLsb(int i) {
        return ((i & 65535) >> 0) & 255;
    }

    private int getMsb(int i) {
        return ((i & 65535) >> 8) & 255;
    }

    private int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    private int getSwapVal(int i) {
        return (i < 1 || i == 2 || i != 1) ? 0 : 1;
    }

    private void keySwc(int i) {
        switch (i) {
            case 17:
                panelBtnKeyClick(2);
                break;
            case 18:
                panelBtnKeyClick(48);
                break;
            case 19:
                panelBtnKeyClick(47);
                break;
            case 20:
                panelBtnKeyClick(7);
                break;
            case 21:
                panelBtnKeyClick(8);
                break;
            case 22:
                panelBtnKeyClick(3);
                break;
            case 23:
                panelBtnKeyClick(49);
                break;
            case 24:
                panelBtnKeyClick(50);
                break;
            default:
                switch (i) {
                    case 48:
                        panelBtnKeyClick(14);
                        break;
                    case 50:
                        panelBtnKeyClick(HotKeyConstant.K_SPEECH);
                        break;
                }
                panelBtnKeyClick(15);
                break;
        }
    }

    private void setSwcKey() {
        int i = this.mCanBusInfoInt[2];
        if (i == 0) {
            buttonKey(0);
        }
        switch (i) {
            case 17:
                buttonKey(2);
                break;
            case 18:
                buttonKey(48);
                break;
            case 19:
                buttonKey(47);
                break;
            case 20:
                buttonKey(7);
                break;
            case 21:
                buttonKey(8);
                break;
            case 22:
                buttonKey(3);
                break;
            case 23:
                buttonKey(49);
                break;
            case 24:
                buttonKey(50);
                break;
            default:
                switch (i) {
                    case 48:
                        buttonKey(14);
                        break;
                    case 49:
                        buttonKey(15);
                        break;
                    case 50:
                        buttonKey(HotKeyConstant.K_SPEECH);
                        break;
                }
        }
    }

    private void setPanelBtnInfo() {
        if (lastPanelSt == 1) {
            int i = longClickCount + 1;
            longClickCount = i;
            if (i >= 5) {
                int[] iArr = this.mCanBusInfoInt;
                if (iArr[3] == 0 && iArr[2] == 9) {
                    isLastLongClick = true;
                    panelBtnKeyClick(HotKeyConstant.K_SLEEP);
                    longClickCount = 0;
                }
            }
            if (this.mCanBusInfoInt[3] == 0 && !isLastLongClick) {
                int i2 = lastPanelKeyVal;
                switch (i2) {
                    case 1:
                        panelBtnKeyClick(HotKeyConstant.K_SLEEP);
                        break;
                    case 2:
                        panelBtnKeyClick(31);
                        break;
                    case 3:
                        panelBtnKeyClick(52);
                        break;
                    case 4:
                        panelBtnKeyClick(2);
                        break;
                    case 5:
                        panelBtnKeyClick(129);
                        break;
                    case 6:
                        panelBtnKeyClick(47);
                        break;
                    case 7:
                        panelBtnKeyClick(48);
                        break;
                    case 8:
                        panelBtnKeyClick(58);
                        break;
                    case 9:
                        panelBtnKeyClick(3);
                        break;
                    case 10:
                        panelBtnKeyClick(128);
                        break;
                    case 11:
                        panelBtnKeyClick(75);
                        break;
                    case 12:
                        panelBtnKeyClick(50);
                        break;
                    case 13:
                        panelBtnKeyClick(14);
                        break;
                    case 14:
                        panelBtnKeyClick(HotKeyConstant.K_SPEECH);
                        break;
                    default:
                        switch (i2) {
                            case 16:
                                panelBtnKeyClick(7);
                                break;
                            case 17:
                                panelBtnKeyClick(8);
                                break;
                            case 18:
                                panelBtnKeyClick(45);
                                break;
                            case 19:
                                panelBtnKeyClick(46);
                                break;
                            default:
                                switch (i2) {
                                    case 33:
                                        panelBtnKeyClick(33);
                                        break;
                                    case 34:
                                        panelBtnKeyClick(34);
                                        break;
                                    case 35:
                                        panelBtnKeyClick(35);
                                        break;
                                    case 36:
                                        panelBtnKeyClick(36);
                                        break;
                                    case 37:
                                        panelBtnKeyClick(37);
                                        break;
                                    case 38:
                                        panelBtnKeyClick(38);
                                        break;
                                    case 39:
                                        forceReverse(this.mContext, false);
                                        panelBtnKeyClick(75);
                                        break;
                                    case 40:
                                        panelBtnKeyClick(75);
                                        break;
                                    case 41:
                                        panelBtnKeyClick(14);
                                        break;
                                    case 42:
                                        panelBtnKeyClick(HotKeyConstant.K_SPEECH);
                                        break;
                                    case 43:
                                        realKeyClick(152);
                                        break;
                                    case 44:
                                        panelBtnKeyClick(17);
                                        break;
                                    case 45:
                                        panelBtnKeyClick(39);
                                        break;
                                    case 46:
                                        panelBtnKeyClick(40);
                                        break;
                                    case 47:
                                        Context context = this.mContext;
                                        startSettingActivity(context, getUiMgr(context).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), 0);
                                        break;
                                }
                        }
                }
                lastPanelKeyVal = 0;
            }
        } else {
            longClickCount = 0;
            isLastLongClick = false;
        }
        int[] iArr2 = this.mCanBusInfoInt;
        if (iArr2[3] >= 1) {
            switch (iArr2[2]) {
                case 16:
                    konbVolUp(lastPanelSt);
                    break;
                case 17:
                    konbVolDn(lastPanelSt);
                    break;
                case 18:
                    konbKeySelRight(lastPanelSt);
                    break;
                case 19:
                    konbKeySelLeft(lastPanelSt);
                    break;
            }
        }
        int[] iArr3 = this.mCanBusInfoInt;
        int i3 = iArr3[3];
        lastPanelSt = i3;
        if (i3 != 0) {
            lastPanelKeyVal = iArr3[2];
        }
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick1(context, i, iArr[2], iArr[3]);
    }

    private void setCarIdCmd() {
        switch (getCurrentCanDifferentId()) {
            case 10:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 1});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 2});
                break;
            case 12:
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 4});
                break;
            case 13:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 3});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 5});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 6});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 7});
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 8});
                break;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 9});
                break;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 10});
                break;
            case 23:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 11});
                break;
            case 24:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, NfDef.AVRCP_EVENT_ID_UIDS_CHANGED});
                break;
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, NfDef.AVRCP_EVENT_ID_VOLUME_CHANGED});
                break;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 14});
                break;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, -18, 2, 15});
                break;
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(Context context) {
        super.initCommand(context);
        mDifferentId = getCurrentCanDifferentId();
        this.eachId = getCurrentEachCanId();
        this.mLocationManager = (LocationManager) context.getSystemService("location");
        CanbusMsgSender.sendMsg(new byte[]{22, -127, 1});
        setCarIdCmd();
        try {
            this.mLocationManager.requestLocationUpdates("gps", 1000L, 1.0f, this.locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sndCurCompassSt(Location location) {
        if (isHaveCompass()) {
            CanbusMsgSender.sendMsg(new byte[]{22, -122, (byte) ((location.getBearing() * 32.0f) / 360.0f), 0});
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) {
        this.mCanBusInfoByte = bArr;
        int[] byteArrayToIntArray = getByteArrayToIntArray(bArr);
        this.mCanBusInfoInt = byteArrayToIntArray;
        this.mContext = context;
        int i = byteArrayToIntArray[1];
        if (i == 20) {
            set0x14BackLightInfo();
            return;
        }
        if (i == 36) {
            setDoorData0x24();
            return;
        }
        if (i == 64) {
            setParkAssistance0x40();
            return;
        }
        if (i == 80) {
            setPanelBtnInfo();
            return;
        }
        if (i != 88) {
            switch (i) {
                case 16:
                    if (!isAirMsgRepeat(bArr)) {
                        setAirData0x10();
                        break;
                    }
                    break;
                case 17:
                    if (!isAirMsgRepeat(bArr) && isHaveRearAir()) {
                        setRearAir();
                        break;
                    }
                    break;
                case 18:
                    setSwcKey();
                    break;
                default:
                    switch (i) {
                        case 48:
                            setVersionInfo();
                            break;
                        case 49:
                            setTrack();
                            break;
                        case 50:
                            setRearRadar();
                            break;
                        case 51:
                            setFrontRadar();
                            break;
                        default:
                            switch (i) {
                                case 82:
                                    language_settings_0x52();
                                    air_conditioning_settings_0x52();
                                    body_and_accessories_0x52();
                                    lighting_control_0x52();
                                    seat_set_0x52();
                                    driving_assistance_0x52();
                                    Intelligent_parking_assistance0x52();
                                    break;
                                case 83:
                                    setNewEnergyInfo_0x53();
                                    break;
                                case 84:
                                    set0x54newEnergyInfo();
                                    break;
                                case 85:
                                    setIntelligentPerceptionSystemInfo0x55();
                                    break;
                            }
                    }
            }
            return;
        }
        setVehicleControlInfo0x58();
    }

    private void set0x14BackLightInfo() {
        int i = this.mCanBusInfoInt[2];
        if (i != 255) {
            switch (i) {
                case 0:
                    setBacklightLevel(1);
                    break;
                case 1:
                    setBacklightLevel(1);
                    break;
                case 2:
                    setBacklightLevel(2);
                    break;
                case 3:
                    setBacklightLevel(2);
                    break;
                case 4:
                    setBacklightLevel(3);
                    break;
                case 5:
                    setBacklightLevel(3);
                    break;
                case 6:
                    setBacklightLevel(4);
                    break;
                case 7:
                    setBacklightLevel(4);
                    break;
            }
        }
        setBacklightLevel(5);
    }

    private void setVehicleControlInfo0x58() {
        if (this.eachId != 8) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_frontrightdoor"), Integer.valueOf(this.mCanBusInfoInt[2])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_rearrightdoor"), Integer.valueOf(this.mCanBusInfoInt[3])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_frontleftdoor"), Integer.valueOf(this.mCanBusInfoInt[4])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_rearleftdoor"), Integer.valueOf(this.mCanBusInfoInt[5])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Skylight"), Skylight(this.mCanBusInfoInt[6])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Sunshade"), SunShade(this.mCanBusInfoInt[7])));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_ElectricTailgateFunction"), Boolean.valueOf(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_ElectricTailgate"), Boolean.valueOf(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_car_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_car_control", "_220_Electric_Tailgate_Open_Position"), Boolean.valueOf(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_frontrightdoor"), DoorOpenPosition(this.mCanBusInfoInt[2])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_rearrightdoor"), DoorOpenPosition(this.mCanBusInfoInt[3])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_frontleftdoor"), DoorOpenPosition(this.mCanBusInfoInt[4])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_rearleftdoor"), DoorOpenPosition(this.mCanBusInfoInt[5])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Skylight"), Skylight(this.mCanBusInfoInt[6])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Sunshade"), SunShade(this.mCanBusInfoInt[7])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_ElectricTailgateFunction"), ElectricTailgate(DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[8]))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_ElectricTailgate"), ElectricTailgate(DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[8]))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), 10, ElectricTailgatePosition(DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[8]))));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), 8, DoorOpenPosition(this.mCanBusInfoInt[9])));
        arrayList2.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "car_info"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_Memory_Open_Position"), DoorOpenPosition(this.mCanBusInfoInt[10])));
        updateGeneralSettingData(arrayList);
        updateGeneralDriveData(arrayList2);
        updateDriveDataActivity(null);
        updateSettingActivity(null);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr
    public void forceReverse(Context context, boolean z) {
        super.forceReverse(context, z);
    }

    private void setIntelligentPerceptionSystemInfo0x55() {
        if (this.eachId != 8) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        switch (this.mCanBusInfoInt[2]) {
            case 1:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Intelligent_Perception_System"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 2:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Distraction_Reminder"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 3:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Fatigue_Testing"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 4:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Ventilate_While_Smoking"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 5:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Decrease_The_Volume_During_A_Call"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 6:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Sight_Bright_Screen"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 7:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Gestures_To_Cut_The_Song"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
            case 8:
                arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_Perception_System"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_Perception_System", "_220_Convenient_For_Car"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                break;
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void set0x54newEnergyInfo() {
        if (this.eachId != 2) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function1"), this.mCanBusInfoInt[3] + ":" + this.mCanBusInfoInt[2]));
        int drivingPageIndexes = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
        int drivingItemIndexes = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function2");
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes, drivingItemIndexes, sb.append((iArr[5] * 256) + iArr[4]).append("V").toString()));
        int drivingPageIndexes2 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
        int drivingItemIndexes2 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function3");
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes2, drivingItemIndexes2, sb2.append(((iArr2[7] * 256) + iArr2[6]) * 0.1d).append("%").toString()));
        int drivingPageIndexes3 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
        int drivingItemIndexes3 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function4");
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes3, drivingItemIndexes3, sb3.append(((iArr3[9] * 256) + iArr3[8]) * 0.1d).append("A").toString()));
        int drivingPageIndexes4 = getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1");
        int drivingItemIndexes4 = getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function5");
        StringBuilder sb4 = new StringBuilder();
        int[] iArr4 = this.mCanBusInfoInt;
        arrayList.add(new DriverUpdateEntity(drivingPageIndexes4, drivingItemIndexes4, sb4.append((iArr4[11] * 256) + iArr4[10]).append("V").toString()));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function6"), getFunction6State(DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function7"), getFunction7State(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[12]))));
        arrayList.add(new DriverUpdateEntity(getUiMgr(this.mContext).getDrivingPageIndexes(this.mContext, "_220_add_function_page1"), getUiMgr(this.mContext).getDrivingItemIndexes(this.mContext, "_220_add_function8"), getFunction8State(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[12], 0, 5))));
        updateGeneralDriveData(arrayList);
        updateDriveDataActivity(null);
    }

    private void setTrack() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[3], bArr[2], 7680, 12544, 16);
        updateParkUi(null, this.mContext);
    }

    private void setFrontRadar() {
        if (this.mCanBusInfoInt[2] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mDisableData2 = this.mCanBusInfoInt[3] + 1;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setFrontRadarLocationData(iArr[3], iArr[4] + 1, iArr[5] + 1, iArr[6] + 1, iArr[7] + 1);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 25; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        for (int i2 = 128; i2 <= 255; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        GeneralParkData.radar_distance_disable = Arrays.stream((Integer[]) arrayList.toArray(new Integer[arrayList.size()])).mapToInt(new MsgMgr$$ExternalSyntheticLambda0()).toArray();
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarDistanceData(iArr2[4], iArr2[5] + 1, iArr2[6] + 1, iArr2[7] + 1);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    private void setRearRadar() {
        if (this.mCanBusInfoInt[2] == 0) {
            GeneralParkData.isShowDistanceNotShowLocationUi = false;
            RadarInfoUtil.mMinIsClose = true;
            RadarInfoUtil.mDisableData = 255;
            RadarInfoUtil.mDisableData2 = this.mCanBusInfoInt[3] + 1;
            int[] iArr = this.mCanBusInfoInt;
            RadarInfoUtil.setRearRadarLocationData(iArr[3], iArr[4] + 1, iArr[5] + 1, iArr[6] + 1, iArr[7] + 1);
            GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
            updateParkUi(null, this.mContext);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 25; i++) {
            arrayList.add(Integer.valueOf(i));
        }
        for (int i2 = 128; i2 <= 255; i2++) {
            arrayList.add(Integer.valueOf(i2));
        }
        GeneralParkData.radar_distance_disable = Arrays.stream((Integer[]) arrayList.toArray(new Integer[arrayList.size()])).mapToInt(new MsgMgr$$ExternalSyntheticLambda0()).toArray();
        GeneralParkData.isShowDistanceNotShowLocationUi = true;
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarDistanceData(iArr2[4], iArr2[5], iArr2[6], iArr2[7]);
        GeneralParkData.radar_distance_data = RadarInfoUtil.mDistanceMap;
        updateParkUi(null, this.mContext);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -56}, new byte[]{(byte) i6, (byte) i5, z ? (byte) 1 : (byte) 0, z2 ? (byte) 1 : (byte) 0}));
        setCarIdCmd();
    }

    void init360Disp(Context context) {
        int intValue = SharePreUtil.getIntValue(context, CAN_220_SAVE_360, 0);
        if (isHaveCam360()) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new SettingUpdateEntity(7, 0, Integer.valueOf(intValue)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    boolean isHaveCam360() {
        int currentCanDifferentId = getCurrentCanDifferentId();
        return currentCanDifferentId == 3 || currentCanDifferentId == 9 || currentCanDifferentId == 14 || currentCanDifferentId == 16 || currentCanDifferentId == 19 || currentCanDifferentId == 5 || currentCanDifferentId == 6 || currentCanDifferentId == 7 || currentCanDifferentId == 11 || currentCanDifferentId == 12;
    }

    private boolean isHaveRearAir() {
        int i;
        return getCurrentCanDifferentId() == 7 || getCurrentCanDifferentId() == 16 || getCurrentCanDifferentId() == 25 || getCurrentCanDifferentId() == 17 || (i = this.eachId) == 1 || i == 6;
    }

    private boolean isHaveNewEnergy() {
        return (getCurrentCanDifferentId() == 11 || getCurrentCanDifferentId() == 13 || getCurrentCanDifferentId() == 15) ? false : true;
    }

    private boolean isHaveCompass() {
        return (getCurrentCanDifferentId() == 9 || getCurrentCanDifferentId() == 7 || getCurrentCanDifferentId() == 16) ? false : true;
    }

    private void setVersionInfo() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setDoorData0x24() {
        GeneralDoorData.isShowHandBrake = true;
        GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDoorData.isHandBrakeUp = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (mIsFLDoorLast != GeneralDoorData.isLeftFrontDoorOpen || mIsFRDoorLast != GeneralDoorData.isRightFrontDoorOpen || mIsRLDoorLast != GeneralDoorData.isLeftRearDoorOpen || mIsRRDoorLast != GeneralDoorData.isRightRearDoorOpen || mIsFrontLast != GeneralDoorData.isFrontOpen || mIsBackLast != GeneralDoorData.isBackOpen || mIsHandBreak != GeneralDoorData.isHandBrakeUp) {
            updateDoorView(this.mContext);
        }
        mIsFLDoorLast = GeneralDoorData.isLeftFrontDoorOpen;
        mIsFRDoorLast = GeneralDoorData.isRightFrontDoorOpen;
        mIsRLDoorLast = GeneralDoorData.isLeftRearDoorOpen;
        mIsRRDoorLast = GeneralDoorData.isRightRearDoorOpen;
        mIsFrontLast = GeneralDoorData.isFrontOpen;
        mIsBackLast = GeneralDoorData.isBackOpen;
        mIsHandBreak = GeneralDoorData.isHandBrakeUp;
        mIsBelt = GeneralDoorData.isSeatBeltTie;
    }

    private void setAirData0x10() {
        GeneralAirData.power = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = !DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.front_defog = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralAirData.rear_defog = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_foot = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralAirData.front_left_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.front_right_blow_head = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        GeneralAirData.auto = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralAirData.ion = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        GeneralAirData.ac_max = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralAirData.dual = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralAirData.threeZone = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralAirData.rear = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralAirData.front_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 4);
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        if (i != 0) {
            GeneralAirData.front_left_temperature = resoveTempLev(i);
            GeneralAirData.front_right_temperature = resoveTempLev(this.mCanBusInfoInt[2]);
        } else {
            GeneralAirData.front_left_temperature = resolveLeftAndRightTemp(iArr[5]);
            GeneralAirData.front_right_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[6]);
        }
        updateAirActivity(this.mContext, 1001);
    }

    private void setRearAir() {
        GeneralAirData.rear_left_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_right_blow_foot = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_left_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_right_blow_head = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_auto = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralAirData.rear_wind_level = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 0, 4);
        GeneralAirData.rear_temperature = resolveLeftAndRightTemp(this.mCanBusInfoInt[3]);
        updateAirActivity(this.mContext, 1002);
    }

    private String DoorOpenPosition(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._220_Door1);
        }
        if (i == 100) {
            return this.mContext.getResources().getString(R.string._220_Door2);
        }
        return i + "%";
    }

    private String Skylight(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._220_SkylightOff);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._220_SkylightOn);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._220_SkylightVentilation);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._220_SkylightOn_ing);
        }
        return this.mContext.getResources().getString(R.string._220_SkylightOff_ing);
    }

    private String ElectricTailgate(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._220_status2);
        }
        return this.mContext.getResources().getString(R.string._220_status1);
    }

    private String ElectricTailgatePosition(boolean z) {
        if (z) {
            return this.mContext.getResources().getString(R.string._220_Memory_position);
        }
        return this.mContext.getResources().getString(R.string._220_Clear_position);
    }

    private String SunShade(int i) {
        if (i == 0) {
            return this.mContext.getResources().getString(R.string._220_SunshadeOff);
        }
        if (i == 1) {
            return this.mContext.getResources().getString(R.string._220_SunshadeOn);
        }
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._220_SunshadeOn_ing);
        }
        return this.mContext.getResources().getString(R.string._220_SunshadeOff_ing);
    }

    private String resolveLeftAndRightTemp(int i) {
        return 1 == i ? "LO" : 57 == i ? "HI" : (3 > i || 55 < i) ? "--" : ((((i - 3) / 2) * 0.5f) + 18.5d) + getTempUnitC(this.mContext);
    }

    private String resoveTempLev(int i) {
        return (i < 1 || i > 7) ? "--" : "LEV: " + i;
    }

    private void panelBtnKeyClick(int i) {
        realKeyClick(this.mContext, i);
    }

    private String getDoorStatus(int i) throws Resources.NotFoundException {
        String string = this.mContext.getResources().getString(R.string._220_SkylightOff);
        if (i == 2) {
            return this.mContext.getResources().getString(R.string._220_SkylightOn);
        }
        if (i == 3) {
            return this.mContext.getResources().getString(R.string._220_SkylightOff);
        }
        if (i == 14) {
            return this.mContext.getResources().getString(R.string._220_SkylightOn_ing);
        }
        return i == 15 ? this.mContext.getResources().getString(R.string._220_SkylightOff_ing) : string;
    }

    private void language_settings_0x52() {
        if (this.mCanBusInfoInt[2] == 1) {
            ArrayList arrayList = new ArrayList();
            int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_language_settings");
            int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_language_settings", "_220_language");
            int i = this.mCanBusInfoInt[3];
            if (i >= 1) {
                i--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(i)));
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void air_conditioning_settings_0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 2) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_auto_when_the_compressor_status"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 3) {
            int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
            int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_auto_outer_loop_control");
            int i2 = this.mCanBusInfoInt[3];
            if (i2 >= 1) {
                i2--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(i2)));
        } else if (i == 4) {
            int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
            int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_airconditioned_comfort_curve_settings");
            int i3 = this.mCanBusInfoInt[3];
            if (i3 >= 1) {
                i3--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, Integer.valueOf(i3)));
        } else if (i == 24) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_negative_ion_mode"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 37) {
            int settingLeftIndexes3 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
            int settingRightIndex3 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_air_quality_sensor");
            int i4 = this.mCanBusInfoInt[3];
            if (i4 >= 1) {
                i4--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes3, settingRightIndex3, Integer.valueOf(i4)));
        } else if (i == 38) {
            int settingLeftIndexes4 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings");
            int settingRightIndex4 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_automatic_mode_wind");
            int i5 = this.mCanBusInfoInt[3];
            if (i5 >= 1) {
                i5--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes4, settingRightIndex4, Integer.valueOf(i5)));
        } else if (i == 59) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_Unlock_the_ventilation"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 60) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_airconditioning_settings"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_airconditioning_settings", "_220_Air_conditioning_self_drying"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private String[] strHeatingLev(Context context) {
        return new String[]{context.getResources().getString(R.string.close), context.getResources().getString(R.string._220_level_1), context.getResources().getString(R.string._220_level_2), context.getResources().getString(R.string._220_level_3)};
    }

    private void seat_set_0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 5) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_automatic_driving_seat_heating"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 6) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_automatic_heating_passenger_seat"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 22) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_left_seat_heat"), strHeatingLev(this.mContext)[this.mCanBusInfoInt[3]]).setValueStr(true));
        } else if (i == 23) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_right_seat_heat"), strHeatingLev(this.mContext)[this.mCanBusInfoInt[3]]).setValueStr(true));
        } else if (i == 25) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_seat_welcome_light"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 26) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_seat_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_seat_set", "_220_smart_key_automatic_identification_seat"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void Intelligent_parking_assistance0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 57) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_parking_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_parking_assistance", "_220_P_mode_exit"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 58) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_Intelligent_parking_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_Intelligent_parking_assistance", "_220_Turn_signals_activate_panorama"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    private void driving_assistance_0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 34) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_and_the_right_auxiliary_line"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 35) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_and_left_auxiliary_line"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i != 56) {
            switch (i) {
                case 7:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_speeding_alert"), Integer.valueOf(this.mCanBusInfoInt[3])));
                    break;
                case 8:
                    int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                    int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_the_instrument_cluster_alarm_volume");
                    int i2 = this.mCanBusInfoInt[3];
                    if (i2 >= 1) {
                        i2--;
                    }
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(i2)));
                    break;
                case 9:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_remote_poweron_time"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                    break;
                case 10:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_remote_start_time"), Integer.valueOf(this.mCanBusInfoInt[3])).setProgress(this.mCanBusInfoInt[3]));
                    break;
                case 11:
                    int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                    int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_steering_modes");
                    int i3 = this.mCanBusInfoInt[3];
                    if (i3 >= 1) {
                        i3--;
                    }
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, Integer.valueOf(i3)));
                    break;
                default:
                    switch (i) {
                        case 45:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Active_brake_assist"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 46:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Cruise_mode"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 47:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Front_collision_warning"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 48:
                            int settingLeftIndexes3 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                            int settingRightIndex3 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Front_collision_warning_distance");
                            int i4 = this.mCanBusInfoInt[3];
                            if (i4 >= 1) {
                                i4--;
                            }
                            arrayList.add(new SettingUpdateEntity(settingLeftIndexes3, settingRightIndex3, Integer.valueOf(i4)));
                            break;
                        case 49:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Lane_assist_switch"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 50:
                            int settingLeftIndexes4 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance");
                            int settingRightIndex4 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Lane_assist");
                            int i5 = this.mCanBusInfoInt[3];
                            if (i5 >= 1) {
                                i5--;
                            }
                            arrayList.add(new SettingUpdateEntity(settingLeftIndexes4, settingRightIndex4, Integer.valueOf(i5)));
                            break;
                    }
            }
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_driving_assistance"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_driving_assistance", "_220_Memorize_the_current_driving_mode"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void body_and_accessories_0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 39) {
            int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories");
            int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_open_trunk_induction");
            int i2 = this.mCanBusInfoInt[3];
            if (i2 >= 1) {
                i2--;
            }
            arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(i2)));
        } else if (i == 40) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Automatic_Wiper_Function"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i != 44) {
            switch (i) {
                case 12:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_remote_unlock"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 13:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_speed_lock"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 14:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_auto_unlock"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 15:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_remote_left_front_window_and_skylight"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 16:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_front_wiper_maintenance_functions"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 17:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_rear_wiper_automatic_reverse_function"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                default:
                    switch (i) {
                        case 27:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_automatic_folding_exterior_rear_view_mirror"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 28:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_unlock_lock_tone"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 29:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_intelligent_active_lock"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 30:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_smart_unlock_initiative"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 31:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_manually_adjustable_exterior_mirror_angle"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                        case 32:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_automatically_adjusting_the_angle_of_the_outer_mirror"), Integer.valueOf(this.mCanBusInfoInt[3] - 1)));
                            break;
                        default:
                            switch (i) {
                                case 51:
                                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Key_forget_reminder"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                                    break;
                                case 52:
                                    int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories");
                                    int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Low_speed_beep");
                                    int i3 = this.mCanBusInfoInt[3];
                                    if (i3 >= 1) {
                                        i3--;
                                    }
                                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, Integer.valueOf(i3)));
                                    break;
                                case 53:
                                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Auto_Close_Windows"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                                    break;
                                case 54:
                                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Wiper_maintenance_mode"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                                    break;
                            }
                    }
            }
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_body_and_accessories"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_body_and_accessories", "_220_Wireless_charging"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void lighting_control_0x52() {
        ArrayList arrayList = new ArrayList();
        int i = this.mCanBusInfoInt[2];
        if (i == 33) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_intelligent_welcome_light_function"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i == 36) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_ambient_light_control"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        } else if (i != 55) {
            switch (i) {
                case 18:
                    int settingLeftIndexes = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control");
                    int settingRightIndex = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_i_went_home_with");
                    int i2 = this.mCanBusInfoInt[3];
                    if (i2 >= 1) {
                        i2--;
                    }
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes, settingRightIndex, Integer.valueOf(i2)));
                    break;
                case 19:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_fog_lights_steering_assist"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 20:
                    arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_daytime_running_lights"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                    break;
                case 21:
                    int settingLeftIndexes2 = getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control");
                    int settingRightIndex2 = getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_auto_light_sensitivity");
                    int i3 = this.mCanBusInfoInt[3];
                    if (i3 >= 1) {
                        i3--;
                    }
                    arrayList.add(new SettingUpdateEntity(settingLeftIndexes2, settingRightIndex2, Integer.valueOf(i3)));
                    break;
                default:
                    switch (i) {
                        case 41:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Ambient_light_brightness"), Integer.valueOf(this.mCanBusInfoInt[3] + 1)).setProgress(this.mCanBusInfoInt[3]));
                            break;
                        case 42:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Ambient_light_Color"), Integer.valueOf(this.mCanBusInfoInt[3] + 1)).setProgress(this.mCanBusInfoInt[3]));
                            break;
                        case 43:
                            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Intelligent_high_beam"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
                            break;
                    }
            }
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_lighting_control"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_lighting_control", "_220_Delayed_headlight_off"), Integer.valueOf(getSwapVal(this.mCanBusInfoInt[3]))));
        }
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    private void setParkAssistance0x40() {
        if (getCurrentCanDifferentId() != 3) {
            return;
        }
        MyPanoramicView myPanoramicView = (MyPanoramicView) UiMgrFactory.getCanUiMgr(this.mContext).getCusPanoramicView(this.mContext);
        this.mPanoramicView = myPanoramicView;
        myPanoramicView.camSt = this.mCanBusInfoInt[2];
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._220.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mPanoramicView.updateUi();
            }
        });
    }

    private void setNewEnergyInfo_0x53() {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[2];
        mNewEnergyStartTime = (i << 8) | iArr[3];
        mNewEnergyEndTime = iArr[5] | (iArr[4] << 8);
        int intFromByteWithBit = ((DataHandleUtils.getIntFromByteWithBit(i, 0, 3) << 2) & 255) | DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        int intFromByteWithBit2 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 6);
        int intFromByteWithBit3 = (DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 3) << 2) | DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        int intFromByteWithBit4 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 0, 6);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 0, Integer.valueOf(intFromByteWithBit)).setProgress(intFromByteWithBit));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 1, Integer.valueOf(intFromByteWithBit2)).setProgress(intFromByteWithBit2));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 2, Integer.valueOf(intFromByteWithBit3)).setProgress(intFromByteWithBit3));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 3, Integer.valueOf(intFromByteWithBit4)).setProgress(intFromByteWithBit4));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 4, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 0, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 5, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 1, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 6, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 2, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 7, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 3, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 8, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 4, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 9, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 5, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 10, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 6, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 11, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[6], 7, 1))));
        int[] iArr2 = this.mCanBusInfoInt;
        mNewEnergyData = iArr2[6];
        int intFromByteWithBit5 = DataHandleUtils.getIntFromByteWithBit(iArr2[7], 0, 3);
        int intFromByteWithBit6 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[7], 3, 5);
        int intFromByteWithBit7 = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 2, 6);
        int[] iArr3 = this.mCanBusInfoInt;
        if (iArr3[7] == 0 && iArr3[8] == 0) {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_energy_set", "_220_from_the_last_charging_time"), "--" + this.mContext.getResources().getString(R.string.time_day) + "--" + this.mContext.getResources().getString(R.string.unit_hour) + "--" + this.mContext.getResources().getString(R.string.unit_minute)).setValueStr(true));
        } else {
            arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), getUiMgr(this.mContext).getSettingRightIndex(this.mContext, "_220_energy_set", "_220_from_the_last_charging_time"), intFromByteWithBit5 + this.mContext.getResources().getString(R.string.time_day) + intFromByteWithBit6 + this.mContext.getResources().getString(R.string.unit_hour) + intFromByteWithBit7 + this.mContext.getResources().getString(R.string.unit_minute)).setValueStr(true));
        }
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 14, this.mContext.getResources().getString(R.string._220_energy_lev) + " " + this.mCanBusInfoInt[9]).setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 15, this.mCanBusInfoInt[10] + "").setValueStr(true));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 16, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 2))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 17, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 7, 1))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 18, Boolean.valueOf(DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[11]))));
        arrayList.add(new SettingUpdateEntity(getUiMgr(this.mContext).getSettingLeftIndexes(this.mContext, "_220_energy_set"), 19, Integer.valueOf(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 4))).setProgress(DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 2, 4)));
        if (arrayList.size() != 0) {
            updateGeneralSettingData(arrayList);
            updateSettingActivity(null);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 0});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        if (i > 6) {
            i = 0;
        }
        byte radioCurrentBand = CommUtil.getRadioCurrentBand(str, (byte) 1, (byte) 2, (byte) 3, (byte) 17, (byte) 18);
        int[] freqByteHiLo = getFreqByteHiLo(str, str2);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.FM.name(), new byte[]{22, -64, 1, radioCurrentBand, (byte) freqByteHiLo[1], (byte) freqByteHiLo[0], (byte) i});
    }

    private int[] getFreqByteHiLo(String str, String str2) {
        int[] iArr = new int[2];
        if (str.equals("AM2") || str.equals("MW") || str.equals("AM1") || str.equals("LW")) {
            iArr[0] = (byte) (Integer.parseInt(str2) >> 8);
            iArr[1] = (byte) (Integer.parseInt(str2) & 255);
        } else if (str.equals("FM1") || str.equals("FM2") || str.equals("FM3") || str.equals("OIRT")) {
            int i = (int) (Double.parseDouble(str2) * 100.0d);
            iArr[0] = (byte) (i >> 8);
            iArr[1] = (byte) (i & 255);
        }
        return iArr;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 0, 0, 0, 1, (byte) (bArr != null ? bArr.length : 0)}, bArr));
        this.isHangUp = true;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        this.isCallIn = true;
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 1, 0, 0, 1, (byte) (bArr != null ? bArr.length : 0)}, bArr));
        this.isHangUp = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        this.isCallIn = false;
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 5, 3, 0, 0, 1, (byte) (bArr != null ? bArr.length : 0)}, bArr));
        this.isHangUp = false;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneTalkingWithTimeInfoChange(byte[] bArr, boolean z, boolean z2, int i) {
        super.btPhoneTalkingWithTimeInfoChange(bArr, z, z2, i);
        if (this.isHangUp) {
            return;
        }
        byte length = (byte) (bArr != null ? bArr.length : 0);
        int msb = getMsb(i);
        int lsb = getLsb(i);
        byte[] bArr2 = new byte[8];
        bArr2[0] = 22;
        bArr2[1] = -64;
        bArr2[2] = 5;
        bArr2[3] = (byte) (this.isCallIn ? 2 : 4);
        bArr2[4] = (byte) msb;
        bArr2[5] = (byte) lsb;
        bArr2[6] = 1;
        bArr2[7] = length;
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(bArr2, bArr));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) throws UnsupportedEncodingException {
        byte[] bytes = new byte[0];
        try {
            bytes = str.getBytes("Unicode");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.byteMerger(new byte[]{22, -64, 11, -1, 16, 1, (byte) bytes.length}, bytes));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        CanbusMsgSender.sendMsg(new byte[]{22, -64, 7});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        boolean z3 = b == 9;
        byte b10 = (byte) (i2 & 255);
        byte b11 = (byte) ((i2 >> 8) & 255);
        byte b12 = (byte) (j2 & 255);
        byte b13 = (byte) (255 & (j2 >> 8));
        int i4 = (b3 * 3600) + (b4 * 60) + b5;
        byte b14 = (byte) (i4 & 255);
        byte b15 = (byte) ((i4 >> 8) & 255);
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = (byte) (z3 ? 9 : 8);
        bArr[3] = 0;
        bArr[4] = b11;
        bArr[5] = b10;
        bArr[6] = b7;
        bArr[7] = (byte) i;
        bArr[8] = b13;
        bArr[9] = b12;
        bArr[10] = b15;
        bArr[11] = b14;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.name(), bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        boolean z2 = b == 9;
        byte b9 = (byte) (i2 & 255);
        byte b10 = (byte) ((i2 >> 8) & 255);
        int i5 = (b3 * 3600) + (b4 * 60) + b5;
        byte b11 = (byte) (i5 & 255);
        byte b12 = (byte) ((i5 >> 8) & 255);
        byte[] bArr = new byte[12];
        bArr[0] = 22;
        bArr[1] = -64;
        bArr[2] = (byte) (z2 ? 9 : 8);
        bArr[3] = 0;
        bArr[4] = b10;
        bArr[5] = b9;
        bArr[6] = b6;
        bArr[7] = (byte) i;
        bArr[8] = 0;
        bArr[9] = 0;
        bArr[10] = b12;
        bArr[11] = b11;
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.name(), bArr);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        if (i6 == 6 || i6 == 7) {
            if (i6 == 1 || i6 == 5) {
                i3 = i4;
            }
            byte b2 = (byte) (i5 & 255);
            byte b3 = (byte) ((i5 >> 8) & 255);
            sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MPEG.name(), new byte[]{22, -64, 16, 0, b3, b2, (byte) (i3 & 255), b3, 0, 0, (byte) ((i >> 8) & 255), (byte) (i & 255)});
        }
    }

    private void konbVolUp(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            panelBtnKeyClick(7);
        }
    }

    private void konbVolDn(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            panelBtnKeyClick(8);
        }
    }

    private void konbKeySelLeft(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            panelBtnKeyClick(47);
        }
    }

    private void konbKeySelRight(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            panelBtnKeyClick(48);
        }
    }

    private UiMgr getUiMgr(Context context) {
        if (this.mUiMgr == null) {
            this.mUiMgr = (UiMgr) UiMgrFactory.getCanUiMgr(context);
        }
        return this.mUiMgr;
    }

    private String getFunction7State(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._220_add_function7_2);
        }
        return this.mContext.getString(R.string._220_add_function7_1);
    }

    private String getFunction6State(boolean z) {
        if (z) {
            return this.mContext.getString(R.string._220_add_function6_2);
        }
        return this.mContext.getString(R.string._220_add_function6_1);
    }

    private String getFunction8State(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._220_add_function8_1);
            case 2:
                return this.mContext.getString(R.string._220_add_function8_2);
            case 3:
                return this.mContext.getString(R.string._220_add_function8_3);
            case 4:
                return this.mContext.getString(R.string._220_add_function8_4);
            case 5:
                return this.mContext.getString(R.string._220_add_function8_5);
            case 6:
                return this.mContext.getString(R.string._220_add_function8_6);
            case 7:
                return this.mContext.getString(R.string._220_add_function8_7);
            case 8:
                return this.mContext.getString(R.string._220_add_function8_8);
            case 9:
                return this.mContext.getString(R.string._220_add_function8_9);
            case 10:
                return this.mContext.getString(R.string._220_add_function8_10);
            case 11:
                return this.mContext.getString(R.string._220_add_function8_11);
            case 12:
                return this.mContext.getString(R.string._220_add_function8_12);
            case 13:
                return this.mContext.getString(R.string._220_add_function8_13);
            case 14:
                return this.mContext.getString(R.string._220_add_function8_14);
            case 15:
                return this.mContext.getString(R.string._220_add_function8_15);
            case 16:
                return this.mContext.getString(R.string._220_add_function8_16);
            case 17:
                return this.mContext.getString(R.string._220_add_function8_17);
            case 18:
                return this.mContext.getString(R.string._220_add_function8_18);
            case 19:
                return this.mContext.getString(R.string._220_add_function8_19);
            case 20:
                return this.mContext.getString(R.string._220_add_function8_20);
            case 21:
                return this.mContext.getString(R.string._220_add_function8_21);
            case 22:
                return this.mContext.getString(R.string._220_add_function8_22);
            case 23:
                return this.mContext.getString(R.string._220_add_function8_23);
            case 24:
                return this.mContext.getString(R.string._220_add_function8_24);
            case 25:
                return this.mContext.getString(R.string._220_add_function8_25);
            case 26:
                return this.mContext.getString(R.string._220_add_function8_26);
            case 27:
                return this.mContext.getString(R.string._220_add_function8_27);
            case 28:
                return this.mContext.getString(R.string._220_add_function8_28);
            case 29:
                return this.mContext.getString(R.string._220_add_function8_29);
            case 30:
                return this.mContext.getString(R.string._220_add_function8_30);
            default:
                return this.mContext.getString(R.string._220_add_function8_0);
        }
    }

    public void updateSettings(int i, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new SettingUpdateEntity(i, i2, Integer.valueOf(i3)));
        updateGeneralSettingData(arrayList);
        updateSettingActivity(null);
    }

    public void buttonKey(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[3]);
    }
}
