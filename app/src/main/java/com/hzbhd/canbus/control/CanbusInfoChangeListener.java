package com.hzbhd.canbus.control;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.hzbhd.canbus.factory.Dependency;
import com.hzbhd.canbus.factory.proxy.CanSettingProxy;
import com.hzbhd.canbus.interfaces.ActionDo;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralAllDataShare;
import com.hzbhd.canbus.ui_datas.GeneralData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.commontools.utils.bhdGsonUtils;
import com.hzbhd.constant.share.ShareConstants;
import com.hzbhd.constant.share.canbus.CanbusAirShare;
import com.hzbhd.constant.share.canbus.CanbusDoorShare;
import com.hzbhd.proxy.share.impl.ShareDataServiceImpl;
import com.hzbhd.util.LogUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class CanbusInfoChangeListener {
    private static final String TAG = "CanbusInfoChangeListener";
    private static CanbusInfoChangeListener instance;
    private static int nowCarSpeed;
    private static int nowLeftLampState;
    private static int nowLeftRightLampState;
    private static int nowRightLampState;
    private ThisCountDownTimer leftTimer;
    private boolean mIsLeftFrontDoorOpen;
    private boolean mIsLeftRearDoorOpen;
    private boolean mIsRightFrontDoorOpen;
    private boolean mIsRightRearDoorOpen;
    private String nowMsBasicInfoStrJson;
    private ThisCountDownTimer rightTimer;
    private CanbusAirShare mCanbusAirShare = new CanbusAirShare();
    private CanbusDoorShare mCanbusDoorShare = new CanbusDoorShare();
    private String mVoiceBroadcastInfo = null;

    public static CanbusInfoChangeListener getInstance() {
        if (instance == null) {
            instance = new CanbusInfoChangeListener();
        }
        return instance;
    }

    private CanbusInfoChangeListener() {
        registerSetShareListener();
    }

    private void registerSetShareListener() {
        ShareDataServiceImpl.init(SourceConstantsDef.MODULE_ID.CANBUS);
        ShareDataServiceImpl.addShareListeners(new ShareDataServiceImpl.ShareListeners() { // from class: com.hzbhd.canbus.control.CanbusInfoChangeListener.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:4:0x000b  */
            @Override // com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListeners, com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public int getInt(java.lang.String r3) {
                /*
                    r2 = this;
                    r3.hashCode()
                    int r0 = r3.hashCode()
                    r1 = -1
                    switch(r0) {
                        case -2119839599: goto L23;
                        case -1925922345: goto L18;
                        case -1380426251: goto Ld;
                        default: goto Lb;
                    }
                Lb:
                    r3 = r1
                    goto L2d
                Ld:
                    java.lang.String r0 = "canbus.Angle"
                    boolean r3 = r3.equals(r0)
                    if (r3 != 0) goto L16
                    goto Lb
                L16:
                    r3 = 2
                    goto L2d
                L18:
                    java.lang.String r0 = "canbus.speedInfo"
                    boolean r3 = r3.equals(r0)
                    if (r3 != 0) goto L21
                    goto Lb
                L21:
                    r3 = 1
                    goto L2d
                L23:
                    java.lang.String r0 = "canbus.lamp.turn.left.right.state"
                    boolean r3 = r3.equals(r0)
                    if (r3 != 0) goto L2c
                    goto Lb
                L2c:
                    r3 = 0
                L2d:
                    switch(r3) {
                        case 0: goto L39;
                        case 1: goto L34;
                        case 2: goto L31;
                        default: goto L30;
                    }
                L30:
                    return r1
                L31:
                    int r3 = com.hzbhd.canbus.ui_datas.GeneralParkData.trackAngle
                    return r3
                L34:
                    int r3 = com.hzbhd.canbus.control.CanbusInfoChangeListener.access$000()
                    return r3
                L39:
                    int r3 = com.hzbhd.canbus.control.CanbusInfoChangeListener.access$100()
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.canbus.control.CanbusInfoChangeListener.AnonymousClass1.getInt(java.lang.String):int");
            }

            @Override // com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListeners, com.hzbhd.proxy.share.impl.ShareDataServiceImpl.ShareListener
            public String getString(String str) {
                str.hashCode();
                switch (str) {
                    case "canbus.radarLeft":
                        return RadarInfoUtil.getRadarLeftShareInfo();
                    case "canbus.ms.basic.json.string":
                        return CanbusInfoChangeListener.this.nowMsBasicInfoStrJson;
                    case "canbus.canVersion":
                        return GeneralData.INSTANCE.getCanVersion();
                    case "canbus.RadarFront":
                        return RadarInfoUtil.getRadarFrontShareInfo();
                    case "can.request.data.share":
                        return "[]";
                    case "canbus.AirInfo":
                        return bhdGsonUtils.toJson(CanbusInfoChangeListener.this.mCanbusAirShare);
                    case "canbus.VoiceBroadcast":
                        return CanbusInfoChangeListener.this.mVoiceBroadcastInfo;
                    case "canbus.RadarRear":
                        return RadarInfoUtil.getRadarRearShareInfo();
                    case "can.bus.all.data.share":
                        return GeneralAllDataShare.canJsonData;
                    case "canbus.radarRight":
                        return RadarInfoUtil.getRadarRightShareInfo();
                    case "canbus.outdoorTemperature":
                        return GeneralAirData.outdoorTemperature;
                    case "canbus.DoorInfo":
                        return bhdGsonUtils.toJson(CanbusInfoChangeListener.this.mCanbusDoorShare);
                    default:
                        return null;
                }
            }
        });
    }

    public void reportAirInfo(Context context) {
        int iIsAirTemperatureSwitch = CommUtil.isAirTemperatureSwitch(context);
        this.mCanbusAirShare.setWind_speed(GeneralAirData.front_wind_level);
        this.mCanbusAirShare.setLeft_temperature(iIsAirTemperatureSwitch == 1 ? GeneralAirData.front_right_temperature : GeneralAirData.front_left_temperature);
        this.mCanbusAirShare.setRight_temperature(iIsAirTemperatureSwitch == 1 ? GeneralAirData.front_left_temperature : GeneralAirData.front_right_temperature);
        this.mCanbusAirShare.setBlow_window(GeneralAirData.front_left_blow_window);
        this.mCanbusAirShare.setBlow_head(GeneralAirData.front_left_blow_head);
        this.mCanbusAirShare.setBlow_foot(GeneralAirData.front_left_blow_foot);
        this.mCanbusAirShare.setFront_defog(GeneralAirData.front_defog);
        this.mCanbusAirShare.setAc(GeneralAirData.ac);
        this.mCanbusAirShare.setDual(GeneralAirData.dual);
        this.mCanbusAirShare.setAuto(GeneralAirData.auto);
        this.mCanbusAirShare.setRear_defog(GeneralAirData.rear_defog);
        this.mCanbusAirShare.setPower(GeneralAirData.power);
        this.mCanbusAirShare.setIn_out_cycle(GeneralAirData.in_out_cycle);
        String json = bhdGsonUtils.toJson(this.mCanbusAirShare);
        if (LogUtil.log5()) {
            LogUtil.d("<reportAirInfo> " + json);
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_AIR_INFO, json);
    }

    public void reportSpeedInfo(int i) {
        nowCarSpeed = i;
        ShareDataServiceImpl.setInt(ShareConstants.SHARE_CANBUS_SPEED_INFO, i);
    }

    public void reportLeftLampInfo(boolean z) {
        if (this.leftTimer == null) {
            this.leftTimer = new ThisCountDownTimer(new ActionDo() { // from class: com.hzbhd.canbus.control.CanbusInfoChangeListener.2
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    int unused = CanbusInfoChangeListener.nowLeftLampState = 0;
                    CanbusInfoChangeListener.this.ShareLampState();
                    Log.e("LampState", "exit left lamp");
                }
            });
        }
        if (z) {
            this.leftTimer.remove(699050);
            nowLeftLampState = 1;
            ShareLampState();
            Log.e("LampState", "turn left lamp");
            return;
        }
        this.leftTimer.start(699050, 1500);
    }

    public void reportRightLampInfo(boolean z) {
        if (this.rightTimer == null) {
            this.rightTimer = new ThisCountDownTimer(new ActionDo() { // from class: com.hzbhd.canbus.control.CanbusInfoChangeListener.3
                @Override // com.hzbhd.canbus.interfaces.ActionDo
                public void todo(List<Object> list) {
                    int unused = CanbusInfoChangeListener.nowRightLampState = 0;
                    CanbusInfoChangeListener.this.ShareLampState();
                    Log.e("LampState", "exit right lamp");
                }
            });
        }
        if (z) {
            this.rightTimer.remove(48059);
            nowRightLampState = 1;
            ShareLampState();
            Log.e("LampState", "turn right lamp");
            return;
        }
        this.rightTimer.start(48059, 1500);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ShareLampState() {
        int decimalFrom8Bit = DataHandleUtils.getDecimalFrom8Bit(0, 0, 0, 0, 0, 0, nowRightLampState, nowLeftLampState);
        nowLeftRightLampState = decimalFrom8Bit;
        ShareDataServiceImpl.setInt(ShareConstants.SHARE_CANBUS_LAMP_TURN_LEFT_RIGHT, decimalFrom8Bit);
    }

    public void reportMsAirInfo(String str) {
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_MS_AIR_JSON, str);
    }

    public void reportMsBasicInfo(String str) {
        this.nowMsBasicInfoStrJson = str;
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_MS_BISIC_JSON, str);
    }

    public void reportAllCanBusData(String str) {
        GeneralAllDataShare.canJsonData = str;
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CAN_BUS_ALL_DATA, str);
    }

    public void reportDoorInfo(Context context) {
        checkDoorSwitch(context);
        this.mCanbusDoorShare.setDoor_left_front(this.mIsLeftFrontDoorOpen);
        this.mCanbusDoorShare.setDoor_right_front(this.mIsRightFrontDoorOpen);
        this.mCanbusDoorShare.setDoor_left_rear(this.mIsLeftRearDoorOpen);
        this.mCanbusDoorShare.setDoor_right_rear(this.mIsRightRearDoorOpen);
        this.mCanbusDoorShare.setDoor_front(GeneralDoorData.isFrontOpen);
        this.mCanbusDoorShare.setDoor_back(GeneralDoorData.isBackOpen);
        String json = bhdGsonUtils.toJson(this.mCanbusDoorShare);
        if (LogUtil.log5()) {
            LogUtil.d("<reportDoorInfo> " + json);
        }
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_DOOR_INFO, json);
    }

    private void checkDoorSwitch(Context context) {
        if (!((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getDoorSwapFront()) {
            this.mIsLeftFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
            this.mIsRightFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
        } else {
            this.mIsLeftFrontDoorOpen = GeneralDoorData.isRightFrontDoorOpen;
            this.mIsRightFrontDoorOpen = GeneralDoorData.isLeftFrontDoorOpen;
        }
        if (!((CanSettingProxy) Dependency.get(CanSettingProxy.class)).getDoorSwapRear()) {
            this.mIsLeftRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
            this.mIsRightRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
        } else {
            this.mIsLeftRearDoorOpen = GeneralDoorData.isRightRearDoorOpen;
            this.mIsRightRearDoorOpen = GeneralDoorData.isLeftRearDoorOpen;
        }
    }

    public void reportVoiceBroadcast(String str) {
        if (LogUtil.log5()) {
            LogUtil.d("<reportVoiceBroadcast> " + str);
        }
        this.mVoiceBroadcastInfo = str;
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_VOICE_BROADCAST, str);
    }

    public void reportOutdoorTemperature() {
        reportOutdoorTemperature(GeneralAirData.outdoorTemperature);
    }

    public void reportOutdoorTemperature(String str) {
        ShareDataServiceImpl.setString(ShareConstants.SHARE_CANBUS_OUTDOOR_TEMPERATURE, CommUtil.temperatureUnitSwitch(str));
    }

    public void setOutdoorTemperatureVisible(boolean z) {
        if (z) {
            reportOutdoorTemperature();
        } else {
            reportOutdoorTemperature("");
        }
    }

    public class ThisCountDownTimer {
        private int ACTION_TAG = 255;
        private Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.hzbhd.canbus.control.CanbusInfoChangeListener.ThisCountDownTimer.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == ThisCountDownTimer.this.ACTION_TAG) {
                    ThisCountDownTimer.this.thisActionDo.todo(null);
                }
            }
        };
        private ActionDo thisActionDo;

        public ThisCountDownTimer(ActionDo actionDo) {
            this.thisActionDo = actionDo;
        }

        public void remove(int i) {
            this.ACTION_TAG = i;
            this.mHandler.removeMessages(i);
        }

        public void start(int i, int i2) {
            this.ACTION_TAG = i;
            this.mHandler.obtainMessage().what = this.ACTION_TAG;
            this.mHandler.sendEmptyMessageDelayed(this.ACTION_TAG, i2);
        }
    }

    public void reportString(String str, String str2) {
        ShareDataServiceImpl.setString(str, str2);
    }
}
