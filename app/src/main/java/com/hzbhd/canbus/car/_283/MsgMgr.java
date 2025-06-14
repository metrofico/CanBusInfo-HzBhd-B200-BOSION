package com.hzbhd.canbus.car._283;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.UserHandle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.view.InputDeviceCompat;
import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.R;
import com.hzbhd.canbus.adapter.util.FutureUtil;
import com.hzbhd.canbus.adapter.util.PA6Utils;
import com.hzbhd.canbus.adapter.util.Util;
import com.hzbhd.canbus.car._283.GPSTimeManager;
import com.hzbhd.canbus.car_cus._283.GeneralDzData;
import com.hzbhd.canbus.car_cus._283.MainActivity;
import com.hzbhd.canbus.car_cus._283.MessageSender;
import com.hzbhd.canbus.car_cus._283.activity.AirActivity;
import com.hzbhd.canbus.car_cus._283.activity.AirCleanActivity;
import com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingActivity;
import com.hzbhd.canbus.car_cus._283.activity.MainSettingPersonalActivity;
import com.hzbhd.canbus.car_cus._283.activity.MeterActivity;
import com.hzbhd.canbus.car_cus._283.bean.DriveData;
import com.hzbhd.canbus.car_cus._283.bean.TimeSyncMode;
import com.hzbhd.canbus.car_cus._283.smart.SmartPowerActivity;
import com.hzbhd.canbus.car_cus._283.view.AirSeatView;
import com.hzbhd.canbus.car_cus._283.view.RadarView;
import com.hzbhd.canbus.control.CanbusControlAction;
import com.hzbhd.canbus.control.CanbusInfoChangeListener;
import com.hzbhd.canbus.msg_mgr.AbstractMsgMgr;
import com.hzbhd.canbus.smartpower.GeneralDzSmartData;
import com.hzbhd.canbus.ui_datas.GeneralAirData;
import com.hzbhd.canbus.ui_datas.GeneralDoorData;
import com.hzbhd.canbus.ui_datas.GeneralParkData;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.canbus.util.BtApi;
import com.hzbhd.canbus.util.CommUtil;
import com.hzbhd.canbus.util.DataHandleUtils;
import com.hzbhd.canbus.util.MyLog;
import com.hzbhd.canbus.util.RadarInfoUtil;
import com.hzbhd.canbus.util.SharePreUtil;
import com.hzbhd.canbus.util.SystemUtil;
import com.hzbhd.canbus.util.TrackInfoUtil;
import com.hzbhd.canbus.util.VoiceControlData;
import com.hzbhd.canbus.util.amap.AMapEntity;
import com.hzbhd.canbus.util.amap.AMapUtils;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.Arrays;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class MsgMgr extends AbstractMsgMgr {
    public static boolean CHANG_TIME = false;
    public static final ComponentName PA6SAirActivity = new ComponentName("com.hzbhd.canbus", "com.hzbhd.canbus.car_cus._283.activity.AirActivity");
    private static final String SAVE_METER_COLOR_B = "_283_meter_colorB";
    private static final String SAVE_METER_COLOR_G = "_283_meter_colorG";
    private static final String SAVE_METER_COLOR_R = "_283_meter_colorR";
    private static final String SAVE_METER_METER_LEFT_INT = "_283_meter_leftRotateInt";
    private static final String SAVE_METER_METER_MODE = "_283_meter_meter_mode";
    private static final String SAVE_METER_METER_RIGHT_INT = "_283_meter_rightRotateInt";
    public static final String SHARE_283_AIR_SYNC = "_283_air_sync";
    public static final String SHARE_283_DRIVER_MODE = "_283_driver_mode";
    private static final String SHARE_283_SMARTPOWER_DATA3 = "_283_smart_data3";
    private static final String SHARE_283_SMARTPOWER_DATA4 = "_283_smart_data4";
    private static final String SHARE_283_SMARTPOWER_DATA5 = "_283_smart_data5";
    private static final String SHARE_283_SMARTPOWER_DATA6 = "_283_smart_data6";
    private static final String SHARE_283_SMARTPOWER_MODE = "_283_smart_mode";
    private static final int WHAT_UPDATE_BT_NAME = 0;
    private static final int WHAT_UPDATE_BT_OperatorName = 1;
    private int data3;
    private int data4;
    private int data5;
    private int data6;
    private int left_front_hot;
    private int left_front_ventilate;
    private AMapBroadcast mAMapBroadcast;
    int[] mAirData;
    private AirSeatView mAirSeatView;
    private byte[] mCanBusInfoByte;
    private int[] mCanBusInfoInt;
    private byte[] mCanbusKeyCopy;
    private byte[] mCanbusKeyModeCopy;
    private Context mContext;
    private CusPanoramicView mPanoramicView;
    private RadarView mRadarView;
    private int mode;
    private int mode_int;
    private int nowVlume;
    byte[] nowWayNameByte;
    byte[] otherInfoByte;
    int result;
    private int right_front_hot;
    private int right_front_ventilate;
    private int version;
    private boolean isFirstAirSeat = true;
    private Handler mHandler = new Handler() { // from class: com.hzbhd.canbus.car._283.MsgMgr.1
        @Override // android.os.Handler
        public void handleMessage(Message message) throws UnsupportedEncodingException {
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                MsgMgr.this.sendOperatorName();
            } else if (MsgMgr.this.mContext != null) {
                String btName = PA6Utils.getBtName(MsgMgr.this.mContext);
                Log.d("scyscyscy", "-------->联系人名称：" + btName);
                if (TextUtils.isEmpty(btName)) {
                    MsgMgr.this.mHandler.sendEmptyMessageDelayed(0, 2500L);
                } else {
                    CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -107, 0}, btName.getBytes()), 27));
                }
            }
        }
    };
    DecimalFormat mDecimalFormat = new DecimalFormat("#####00");
    int a = 0;
    int b = 0;
    int now0x12Data2 = 0;
    private int tempDrivingMode = -1;
    private int mSystemDateFormat = 2;
    private int sendTimeTag = 0;
    GPSTimeManager.OnGPSTimeCallBack mOnGPSTimeCallBack = new GPSTimeManager.OnGPSTimeCallBack() { // from class: com.hzbhd.canbus.car._283.MsgMgr.3
        @Override // com.hzbhd.canbus.car._283.GPSTimeManager.OnGPSTimeCallBack
        public void onTimeCallBack(int i, int i2, int i3, int i4, int i5, int i6) {
            MsgMgr.this.sendTimeCommonds(i, i2, i3, i4, i5, true);
        }
    };
    private String mBlueToothName = "";
    private String mOperatorName = "";

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

    private int getTwoData(int i, int i2) {
        return (i * 256) + i2;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void initCommand(final Context context) {
        super.initCommand(context);
        this.mContext = context;
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.MsgMgr$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m353lambda$initCommand$0$comhzbhdcanbuscar_283MsgMgr(context);
            }
        }).start();
    }

    /* renamed from: lambda$initCommand$0$com-hzbhd-canbus-car-_283-MsgMgr, reason: not valid java name */
    /* synthetic */ void m353lambda$initCommand$0$comhzbhdcanbuscar_283MsgMgr(Context context) {
        try {
            GeneralDzSmartData.mode = SharePreUtil.getIntValue(context, SHARE_283_SMARTPOWER_MODE, 1);
            GeneralDzSmartData.data3 = SharePreUtil.getIntValue(context, SHARE_283_SMARTPOWER_DATA3, 0);
            GeneralDzSmartData.data4 = SharePreUtil.getIntValue(context, SHARE_283_SMARTPOWER_DATA4, 0);
            GeneralDzSmartData.data5 = SharePreUtil.getIntValue(context, SHARE_283_SMARTPOWER_DATA5, 0);
            GeneralDzSmartData.data6 = SharePreUtil.getIntValue(context, SHARE_283_SMARTPOWER_DATA6, 0);
            MessageSender.sendDzMsg(GeneralDzSmartData.mode, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
            sendMsg((byte) 17, SharePreUtil.getBoolValue(context, SHARE_283_AIR_SYNC, false));
            if (!FutureUtil.instance.getCurrentValidSource().name().equals(SourceConstantsDef.SOURCE_ID.FM.name())) {
                MeterManager.send0xE6Null(7);
                MeterManager.sendMediaMeterData(" ", " ", " ", " ");
            }
            GeneralDzData.colorR = SharePreUtil.getIntValue(context, SAVE_METER_COLOR_R, 0);
            GeneralDzData.colorG = SharePreUtil.getIntValue(context, SAVE_METER_COLOR_G, 0);
            GeneralDzData.colorB = SharePreUtil.getIntValue(context, SAVE_METER_COLOR_B, 0);
            GeneralDzData.meter_mode = SharePreUtil.getIntValue(context, SAVE_METER_METER_MODE, 0);
            GeneralDzData.leftRotateInt = SharePreUtil.getIntValue(context, SAVE_METER_METER_LEFT_INT, 0);
            GeneralDzData.rightRotateInt = SharePreUtil.getIntValue(context, SAVE_METER_METER_RIGHT_INT, 0);
            GeneralDzData.drivingMode = SharePreUtil.getIntValue(context, SHARE_283_DRIVER_MODE, -1);
        } catch (Exception e) {
            MessageSender.sendDzMsg(1, 0, 0, 0, 0);
            e.printStackTrace();
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void afterServiceNormalSetting(Context context) {
        super.afterServiceNormalSetting(context);
        AMapUtils.getInstance().startAMap(context);
        if (this.mAMapBroadcast == null) {
            Log.d("scyscyscy", "---------->开启导航广播监听");
            this.mAMapBroadcast = new AMapBroadcast();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(AMapUtils.AUTONAVI_STANDARD_BROADCAST_SEND);
            intentFilter.addAction("AUDIO_PLAY_NAME_AND_STAUS");
            context.registerReceiver(this.mAMapBroadcast, intentFilter);
        }
        updateMainActivity(3);
        updateMainActivity(2);
        updateMainActivity(1);
        GPSTimeManager.getInstance().start(context);
        GPSTimeManager.getInstance().setOnGPSTimeCallBack(this.mOnGPSTimeCallBack);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void onAMapCallBack(AMapEntity aMapEntity) {
        int i;
        int i2;
        super.onAMapCallBack(aMapEntity);
        if (aMapEntity.getDestinationDistance() == 0) {
            return;
        }
        int i3 = 0;
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
        if (aMapEntity.getDestinationDistance() == 0) {
            this.result = 0;
        } else {
            this.result = Integer.parseInt(this.mDecimalFormat.format((aMapEntity.getNextDistance() * 100) / aMapEntity.getDestinationDistance()));
        }
        sendGdOtherInfo(new byte[]{22, -28, ByteCompanionObject.MIN_VALUE, ByteCompanionObject.MIN_VALUE, (byte) getMsb(aMapEntity.getNextDistance() * 10), (byte) getMidMsb(aMapEntity.getNextDistance() * 10), (byte) getMidLsb(aMapEntity.getNextDistance() * 10), (byte) getLsb(aMapEntity.getNextDistance() * 10), (byte) this.result, (byte) getMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidMsb(aMapEntity.getDestinationDistance() * 10), (byte) getMidLsb(aMapEntity.getDestinationDistance() * 10), (byte) getLsb(aMapEntity.getDestinationDistance() * 10), (byte) i, (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(0, aMapEntity.getPlanTime().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getPlanTime().substring(aMapEntity.getPlanTime().indexOf(":") + 1)), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(0, aMapEntity.getSurplusAllTimeStr().indexOf(":"))), (byte) Integer.parseInt(aMapEntity.getSurplusAllTimeStr().substring(aMapEntity.getSurplusAllTimeStr().indexOf(":") + 1)), (byte) i2, 0, 0});
        byte[] bytes = aMapEntity.getNextWayName().trim().getBytes(StandardCharsets.UTF_8);
        byte[] bArr = new byte[30];
        if (bytes.length > 30) {
            while (i3 < 30) {
                bArr[i3] = bytes[i3];
                i3++;
            }
            sendWayName(bArr);
            return;
        }
        if (bytes.length < 30) {
            int length = bytes.length;
            while (i3 < 30 - length) {
                bytes = SplicingByte(bytes, " ".getBytes(StandardCharsets.UTF_8));
                i3++;
            }
            sendWayName(bytes);
            return;
        }
        sendWayName(bytes);
    }

    private void sendWayName(byte[] bArr) {
        if (isWayNameChange(bArr)) {
            this.a++;
            MyLog.temporaryTracking("道路名称：" + this.a);
            CanbusMsgSender.sendMsg(SplicingByte(new byte[]{22, -27, 0}, bArr));
        }
    }

    private boolean isWayNameChange(byte[] bArr) {
        if (Arrays.equals(this.nowWayNameByte, bArr)) {
            return false;
        }
        this.nowWayNameByte = bArr;
        return true;
    }

    private void sendGdOtherInfo(byte[] bArr) {
        if (isOtherInfoChange(bArr)) {
            this.b++;
            MyLog.temporaryTracking("其他信息：" + this.b);
            CanbusMsgSender.sendMsg(bArr);
        }
    }

    private boolean isOtherInfoChange(byte[] bArr) {
        if (Arrays.equals(this.otherInfoByte, bArr)) {
            return false;
        }
        this.otherInfoByte = bArr;
        return true;
    }

    private byte[] SplicingByte(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        return bArr3;
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void destroyCommand() {
        super.destroyCommand();
        Log.d("scyscyscy", "destroyCommand: ");
        AMapBroadcast aMapBroadcast = this.mAMapBroadcast;
        if (aMapBroadcast != null) {
            this.mContext.unregisterReceiver(aMapBroadcast);
        }
        GPSTimeManager.getInstance().unregister();
        MeterManager.send0xE6Null(8);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void voiceControlInfo(String str) {
        super.voiceControlInfo(str);
        MyLog.temporaryTracking("CanBus收到语音Action：" + str);
        setDyVoiceAction(str);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void setDyVoiceAction(String str) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1950015300:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_AC_MAX)) {
                    c = 0;
                    break;
                }
                break;
            case -1785642657:
                if (str.equals(CanbusControlAction.AIR_REAR_POAER_ON)) {
                    c = 1;
                    break;
                }
                break;
            case -1607857989:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_MANUAL)) {
                    c = 2;
                    break;
                }
                break;
            case -1585736007:
                if (str.equals(CanbusControlAction.AIR_REAR_RIGHT_SEAT_HEAT_TO)) {
                    c = 3;
                    break;
                }
                break;
            case -1567175431:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_FOOT)) {
                    c = 4;
                    break;
                }
                break;
            case -1567125909:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_HEAD)) {
                    c = 5;
                    break;
                }
                break;
            case -1470045433:
                if (str.equals("front_defog")) {
                    c = 6;
                    break;
                }
                break;
            case -1412208249:
                if (str.equals("air.ac.on")) {
                    c = 7;
                    break;
                }
                break;
            case -1409945485:
                if (str.equals(CanbusControlAction.AIR_LEFT_SEAT_HEAT_TO)) {
                    c = '\b';
                    break;
                }
                break;
            case -1326915554:
                if (str.equals("ac.temperature.max")) {
                    c = '\t';
                    break;
                }
                break;
            case -1326915316:
                if (str.equals("ac.temperature.min")) {
                    c = '\n';
                    break;
                }
                break;
            case -1256523009:
                if (str.equals(CanbusControlAction.AIR_LEFT_SEAT_COLD_TO)) {
                    c = 11;
                    break;
                }
                break;
            case -1226270570:
                if (str.equals("ac.open")) {
                    c = '\f';
                    break;
                }
                break;
            case -1092999012:
                if (str.equals(CanbusControlAction.AIR_IN_OUT_CYCLE_AUTO)) {
                    c = '\r';
                    break;
                }
                break;
            case -940325874:
                if (str.equals(CanbusControlAction.AIR_SYNC_ON)) {
                    c = 14;
                    break;
                }
                break;
            case -866529054:
                if (str.equals("air.in.out.cycle.off")) {
                    c = 15;
                    break;
                }
                break;
            case -854978899:
                if (str.equals(CanbusControlAction.AIR_REAR_LOCK_ON)) {
                    c = 16;
                    break;
                }
                break;
            case -828782905:
                if (str.equals("air.ac.off")) {
                    c = 17;
                    break;
                }
                break;
            case -734542239:
                if (str.equals(CanbusControlAction.AIR_REAR_LOCK_OFF)) {
                    c = 18;
                    break;
                }
                break;
            case -553279150:
                if (str.equals(CanbusControlAction.AIR_STEERING_WHEEL_OFF)) {
                    c = 19;
                    break;
                }
                break;
            case -112216342:
                if (str.equals(CanbusControlAction.AIR_CLEAN_ON)) {
                    c = 20;
                    break;
                }
                break;
            case 103110984:
                if (str.equals(CanbusControlAction.AIR_RIGHT_SEAT_HEAT_TO)) {
                    c = 21;
                    break;
                }
                break;
            case 256533460:
                if (str.equals(CanbusControlAction.AIR_RIGHT_SEAT_COLD_TO)) {
                    c = 22;
                    break;
                }
                break;
            case 479652335:
                if (str.equals(CanbusControlAction.AIR_REAR_POAER_OFF)) {
                    c = 23;
                    break;
                }
                break;
            case 629126444:
                if (str.equals("ac.close")) {
                    c = 24;
                    break;
                }
                break;
            case 816260548:
                if (str.equals(CanbusControlAction.AIR_CLEAN_OFF)) {
                    c = 25;
                    break;
                }
                break;
            case 890880226:
                if (str.equals(CanbusControlAction.AIR_REAR_LEFT_SEAT_HEAT_TO)) {
                    c = 26;
                    break;
                }
                break;
            case 914668832:
                if (str.equals(CanbusControlAction.AIR_SYNC_OFF)) {
                    c = 27;
                    break;
                }
                break;
            case 956867879:
                if (str.equals(CanbusControlAction.AIR_REAR_WIND_LEVEL_TO)) {
                    c = 28;
                    break;
                }
                break;
            case 1218099172:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_AUTO)) {
                    c = 29;
                    break;
                }
                break;
            case 1225772921:
                if (str.equals("ac.windlevel.to")) {
                    c = 30;
                    break;
                }
                break;
            case 1377338037:
                if (str.equals(CanbusControlAction.AIR_REAR_AUTO)) {
                    c = 31;
                    break;
                }
                break;
            case 1481217153:
                if (str.equals("ac.temperature.to")) {
                    c = ' ';
                    break;
                }
                break;
            case 1496068108:
                if (str.equals("air.in.out.cycle.on")) {
                    c = '!';
                    break;
                }
                break;
            case 1733069433:
                if (str.equals(CanbusControlAction.AIR_PRESET_MODE_ON_TO_MAX_FRONT)) {
                    c = '\"';
                    break;
                }
                break;
            case 1921814940:
                if (str.equals(CanbusControlAction.AIR_STEERING_WHEEL_ON)) {
                    c = '#';
                    break;
                }
                break;
            case 1959044539:
                if (str.equals(CanbusControlAction.AIR_WIND_BLOW_WINDOW)) {
                    c = Typography.dollar;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 1});
                break;
            case 1:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 1});
                break;
            case 2:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 3});
                break;
            case 3:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 44, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 4:
                if (!GeneralAirData.front_left_blow_foot) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 25, 0});
                    break;
                }
            case 5:
                if (!GeneralAirData.front_left_blow_head) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 24, 0});
                    break;
                }
            case 6:
                if (!GeneralAirData.front_defog) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 10, 0});
                    break;
                }
            case 7:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 1});
                break;
            case '\b':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 33, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case '\t':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -1});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -1});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -1});
                break;
            case '\n':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, -2});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, -2});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, -2});
                break;
            case 11:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 37, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case '\f':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 1});
                break;
            case '\r':
                if (GeneralAirData.in_out_auto_cycle != 2) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 14, 0});
                    break;
                }
            case 14:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 1});
                break;
            case 15:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 2});
                break;
            case 16:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 1});
                break;
            case 17:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 15, 0});
                break;
            case 18:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 18, 0});
                break;
            case 19:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 0});
                break;
            case 20:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 1});
                break;
            case 21:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 34, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 22:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 38, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 23:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 39, 0});
                break;
            case 24:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 2, 0});
                break;
            case 25:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 32, 0});
                break;
            case 26:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 43, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 27:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 17, 0});
                break;
            case 28:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 41, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 29:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 0});
                break;
            case 30:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 23, (byte) Integer.parseInt(VoiceControlData.voiceValue)});
                break;
            case 31:
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 40, 1});
                break;
            case ' ':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 20, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 21, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 22, (byte) (Integer.parseInt(VoiceControlData.voiceValue) * 2)});
                break;
            case '!':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 19, 1});
                break;
            case '\"':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, -127, 2});
                break;
            case '#':
                CanbusMsgSender.sendMsg(new byte[]{22, 58, 35, 1});
                break;
            case '$':
                if (!GeneralAirData.front_left_blow_window) {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 1});
                    break;
                } else {
                    CanbusMsgSender.sendMsg(new byte[]{22, 58, 26, 0});
                    break;
                }
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void canbusInfoChange(Context context, byte[] bArr) throws SecurityException {
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
        } else if (i == 89) {
            canBusInfo0x59(bArr);
        }
    }

    private void canBusInfo0x55(byte[] bArr) throws SecurityException {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[1];
        if (i == 23) {
            setDriveStatusHybrid0x17();
            return;
        }
        if (i == 24) {
            setHybridView0x18();
            return;
        }
        if (i == 30) {
            setUpKeep0x1E();
            return;
        }
        if (i == 31) {
            setUpKeep0x1F();
            return;
        }
        if (i == 49) {
            if (isAirDataNoChange()) {
                return;
            }
            setAir0x31();
            return;
        }
        if (i == 53) {
            if (isAirMsgRepeat(bArr)) {
                return;
            }
            setAir0x35();
            return;
        }
        if (i == 100) {
            setDoor0x64();
            return;
        }
        if (i == 118) {
            setCombination0x76();
            return;
        }
        if (i == 136) {
            setHybrid0x88();
            return;
        }
        if (i == 232) {
            if (isDataChange(iArr)) {
                setCamera0xE8();
                return;
            }
            return;
        }
        if (i == 240) {
            setCanVersion0xF0();
            return;
        }
        if (i == 65) {
            setDyFrontRearRadar0x41();
            setPublicFrontRearRadar0x41();
            return;
        }
        if (i == 66) {
            setDyLeftRightRadar0x42();
            setPublicLeftRightRadar0x42();
            return;
        }
        if (i == 133) {
            setSport0x85();
            return;
        }
        if (i == 134) {
            CommUtil.printHexString("scyscyscy:Mode DCC 收 ---------->", bArr);
            setDrivingMode0x86();
            return;
        }
        if (i == 193) {
            setUnit0xC1();
            return;
        }
        if (i != 194) {
            switch (i) {
                case 17:
                    setCarData0x11();
                    keyControl0x11();
                    updateSpeedInfo(this.mCanBusInfoInt[3]);
                    break;
                case 18:
                    setCarData0x12();
                    setDoor0x12();
                    break;
                case 19:
                    setCarData0x13();
                    break;
                case 20:
                    setCarData0x14();
                    break;
                case 21:
                    setCarData0x15();
                    break;
                default:
                    switch (i) {
                        case 69:
                            CommUtil.printHexString("111111收：", bArr);
                            setParking0x45();
                            break;
                        case 70:
                            setTmps0x46();
                            break;
                        case 71:
                            setDriverAssistant0x47();
                            break;
                        case 72:
                            setTmps0x48();
                            break;
                        case 73:
                            if (isDataChange(iArr)) {
                                setHybrid0x49();
                                break;
                            }
                            break;
                        default:
                            switch (i) {
                                case 103:
                                    setLight0x67();
                                    break;
                                case 104:
                                    setLight0x68();
                                    break;
                                case 105:
                                    setRear0x69();
                                    break;
                            }
                    }
            }
            return;
        }
        setTime0xC2();
    }

    private boolean isAirDataNoChange() {
        if (Arrays.equals(this.mAirData, this.mCanBusInfoInt)) {
            return true;
        }
        this.mAirData = this.mCanBusInfoInt;
        return false;
    }

    private void setDoor0x12() {
        int i = this.now0x12Data2;
        int i2 = this.mCanBusInfoInt[4];
        if (i != i2) {
            this.now0x12Data2 = i2;
            GeneralDoorData.isLeftFrontDoorOpen = DataHandleUtils.getBoolBit7(i2);
            GeneralDoorData.isRightFrontDoorOpen = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
            GeneralDoorData.isLeftRearDoorOpen = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
            GeneralDoorData.isRightRearDoorOpen = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
            GeneralDoorData.isBackOpen = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
            GeneralDoorData.isFrontOpen = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
            CanbusInfoChangeListener.getInstance().reportDoorInfo(this.mContext);
        }
    }

    private void canBusInfo0x59(byte[] bArr) {
        int[] iArr = this.mCanBusInfoInt;
        int i = iArr[6];
        if (i == 32) {
            GeneralDzData.meter_mode = iArr[9];
            GeneralDzData.leftRotateInt = this.mCanBusInfoInt[10];
            GeneralDzData.rightRotateInt = this.mCanBusInfoInt[11];
        } else if (i == 33) {
            GeneralDzData.colorR = iArr[9];
            GeneralDzData.colorG = this.mCanBusInfoInt[10];
            GeneralDzData.colorB = this.mCanBusInfoInt[11];
        }
        updateMeterActivity();
        saveMeterData();
    }

    private void saveMeterData() {
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_COLOR_R, GeneralDzData.colorR);
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_COLOR_G, GeneralDzData.colorG);
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_COLOR_B, GeneralDzData.colorB);
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_METER_MODE, GeneralDzData.meter_mode);
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_METER_LEFT_INT, GeneralDzData.leftRotateInt);
        SharePreUtil.setIntValue(this.mContext, SAVE_METER_METER_RIGHT_INT, GeneralDzData.rightRotateInt);
    }

    private void setCanVersion0xF0() {
        updateVersionInfo(this.mContext, getVersionStr(this.mCanBusInfoByte));
    }

    private void setHybrid0x88() {
        GeneralDzData.hybrid_mode_e = DataHandleUtils.getBoolBit7(this.mCanBusInfoByte[3]);
        GeneralDzData.hybrid_mode_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoByte[3]);
        GeneralDzData.hybrid_mode_keep = DataHandleUtils.getBoolBit5(this.mCanBusInfoByte[3]);
        GeneralDzData.hybrid_mode_charge = DataHandleUtils.getBoolBit4(this.mCanBusInfoByte[3]);
        updateMainActivity(9);
    }

    private void setDriveStatusHybrid0x17() {
        byte[] bArr = this.mCanBusInfoByte;
        double d = (((bArr[2] << 8) & InputDeviceCompat.SOURCE_ANY) | (bArr[3] & 255)) * 0.1d;
        int[] iArr = this.mCanBusInfoInt;
        int i = (iArr[4] * 256) + iArr[5];
        double d2 = (((bArr[6] << 8) & InputDeviceCompat.SOURCE_ANY) | (bArr[7] & 255)) * 0.1d;
        int i2 = (iArr[8] * 256) + iArr[9];
        double d3 = ((bArr[11] & 255) | ((bArr[10] << 8) & InputDeviceCompat.SOURCE_ANY)) * 0.1d;
        int i3 = (iArr[12] * 256) + iArr[13];
        GeneralDzData.hybrid_electricity_data_1 = ((float) d) + " kWh/100km";
        GeneralDzData.hybrid_electricity_data_2 = ((float) d2) + " kWh/100km";
        GeneralDzData.hybrid_electricity_data_3 = ((float) d3) + " kWh/100km";
        GeneralDzData.hybrid_travelled_data_1 = i + " km";
        GeneralDzData.hybrid_travelled_data_2 = i2 + " km";
        GeneralDzData.hybrid_travelled_data_3 = i3 + " km";
        updateMainActivity(8);
    }

    private void setHybridView0x18() {
        GeneralDzData.hybrid_energy_flow = getEnergy(this.mCanBusInfoInt[4]);
        StringBuilder sb = new StringBuilder();
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.hybrid_endurance = sb.append((iArr[2] * 256) + iArr[3]).append("km").toString();
        StringBuilder sb2 = new StringBuilder();
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.hybrid_travelled = sb2.append((iArr2[5] * 256) + iArr2[6]).append("km").toString();
        StringBuilder sb3 = new StringBuilder();
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.hybrid_zero_travelled = sb3.append((iArr3[7] * 256) + iArr3[8]).append("km").toString();
        GeneralDzData.hybrid_zero_persents = this.mCanBusInfoInt[9] + "%";
        GeneralDzData.hybrid_electricity = this.mCanBusInfoInt[10] + "%";
        updateMainActivity(7);
    }

    private String getEnergy(int i) {
        switch (i) {
            case 1:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_1);
            case 2:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_2);
            case 3:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_3);
            case 4:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_4);
            case 5:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_5);
            case 6:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_6);
            case 7:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_7);
            default:
                return this.mContext.getString(R.string._283_hybrid_energy_tips_0);
        }
    }

    private void setHybrid0x49() {
        GeneralDzData.hybrid_big_charge_current = this.mCanBusInfoInt[3];
        GeneralDzData.hybrid_air_use_electricity = this.mCanBusInfoInt[5] == 1;
        GeneralDzData.hybrid_low_charge = this.mCanBusInfoInt[6];
        GeneralDzData.hybrid_temperature_in = this.mCanBusInfoInt[4] / 2;
        updateMainSettingActivity(13);
    }

    private void setAir0x35() {
        GeneralDzData.auto_defogging = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[5]);
        GeneralDzData.auto_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[6]);
        updateAirSettingActivity();
    }

    private void canBusInfo0x57(byte[] bArr) {
        GeneralDzSmartData.mode = this.mCanBusInfoInt[1];
        GeneralDzSmartData.mode_int = getModeInt(this.mCanBusInfoInt);
        GeneralDzSmartData.data3 = this.mCanBusInfoInt[2];
        GeneralDzSmartData.data4 = this.mCanBusInfoInt[3];
        GeneralDzSmartData.data5 = this.mCanBusInfoInt[4];
        GeneralDzSmartData.data6 = this.mCanBusInfoInt[5];
        GeneralDzSmartData.version = this.mCanBusInfoInt[7];
        if (this.mode != GeneralDzSmartData.mode || this.mode_int != GeneralDzSmartData.mode_int || this.data3 != GeneralDzSmartData.data3 || this.data4 != GeneralDzSmartData.data4 || this.data5 != GeneralDzSmartData.data5 || this.data6 != GeneralDzSmartData.data6 || this.version != GeneralDzSmartData.version) {
            GeneralDzSmartData.show = true;
            this.mode = GeneralDzSmartData.mode;
            this.mode_int = GeneralDzSmartData.mode_int;
            this.data3 = GeneralDzSmartData.data3;
            this.data4 = GeneralDzSmartData.data4;
            this.data5 = GeneralDzSmartData.data5;
            this.data6 = GeneralDzSmartData.data6;
            this.version = GeneralDzSmartData.version;
            updateSmartActivity();
            SharePreUtil.setIntValue(this.mContext, SHARE_283_SMARTPOWER_MODE, GeneralDzSmartData.mode);
            SharePreUtil.setIntValue(this.mContext, SHARE_283_SMARTPOWER_DATA3, GeneralDzSmartData.data3);
            SharePreUtil.setIntValue(this.mContext, SHARE_283_SMARTPOWER_DATA4, GeneralDzSmartData.data4);
            SharePreUtil.setIntValue(this.mContext, SHARE_283_SMARTPOWER_DATA5, GeneralDzSmartData.data5);
            SharePreUtil.setIntValue(this.mContext, SHARE_283_SMARTPOWER_DATA6, GeneralDzSmartData.data6);
        }
        updateSystemUIDrivingPattern(GeneralDzSmartData.mode);
    }

    private void setTime0xC2() {
        GeneralDzData.time_source = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 7, 1);
        GeneralDzData.time_hour = this.mCanBusInfoInt[3];
        GeneralDzData.time_minute = this.mCanBusInfoInt[4];
        GeneralDzData.time_date3 = this.mCanBusInfoInt[5];
        GeneralDzData.time_date4 = this.mCanBusInfoInt[6];
        GeneralDzData.time_format = this.mCanBusInfoInt[7];
        GeneralDzData.date_year = this.mCanBusInfoInt[8];
        GeneralDzData.date_month = this.mCanBusInfoInt[9];
        GeneralDzData.date_day = this.mCanBusInfoInt[10];
        GeneralDzData.date_format = this.mCanBusInfoInt[11];
        updateMainSettingActivity(9);
    }

    private void setCombination0x76() {
        GeneralDzData.combination_current_consumption = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_average_consumption = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_convenience_consumer = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_travelling_time = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_distance_travelled = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_average_speed = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_digital_speed_display = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[4]);
        GeneralDzData.combination_speed_warning = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralDzData.combination_oil_temperature = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        updateMainSettingActivity(2);
    }

    private void setUnit0xC1() {
        GeneralDzData.unit_distance = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 7, 1);
        GeneralDzData.unit_speed = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 1);
        GeneralDzData.unit_temperature = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 1);
        GeneralDzData.unit_volume = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 3, 2);
        GeneralDzData.unit_consumption = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 1, 2);
        GeneralDzData.unit_electric = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 1);
        GeneralDzData.unit_pressure = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        updateMainSettingActivity(11);
    }

    private void setDoor0x64() {
        GeneralDzData.door_open = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        GeneralDzData.door_unlock = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        GeneralDzData.door_auto_lock = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralDzData.door_intelligence = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralDzData.door_boot = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralDzData.door_voice = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        updateMainSettingActivity(3);
    }

    private void setUpKeep0x1F() {
        GeneralDzData.car_imei = getCarImei(DataHandleUtils.getBytesEndWithAssign(this.mCanBusInfoByte, 0, (byte) 0));
        updateMainSettingActivity(12);
    }

    private void setUpKeep0x1E() {
        GeneralDzData.upkeep_unit = this.mCanBusInfoInt[2];
        GeneralDzData.upkeep_car_day_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 6, 2);
        GeneralDzData.upkeep_car_distance_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 4, 2);
        GeneralDzData.upkeep_oil_day_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        GeneralDzData.upkeep_oil_distance_mark = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 0, 2);
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.upkeep_car_day = getTwoData(iArr[4], iArr[5]);
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.upkeep_car_distance = getTwoData(iArr2[6], iArr2[7]) * 100;
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.upkeep_oil_day = getTwoData(iArr3[8], iArr3[9]);
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralDzData.upkeep_oil_distance = getTwoData(iArr4[10], iArr4[11]) * 100;
        updateMainSettingActivity(12);
    }

    private void setRear0x69() {
        GeneralDzData.rear_sync = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[4]);
        GeneralDzData.rear_down = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[4]);
        GeneralDzData.rear_in = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[4]);
        GeneralDzData.rear_auto_wiper = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralDzData.rear_wiper = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        updateMainSettingActivity(7);
    }

    private void setParking0x45() {
        GeneralDzData.parking_mode = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[8], 6, 2);
        GeneralDzData.parking_front_volume = this.mCanBusInfoInt[4];
        GeneralDzData.parking_front_tone = this.mCanBusInfoInt[5];
        GeneralDzData.parking_rear_volume = this.mCanBusInfoInt[6];
        GeneralDzData.parking_rear_tone = this.mCanBusInfoInt[7];
        GeneralDzData.parking_auto = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDzData.parking_out = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDzData.parking_function = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDzData.parking_switch = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        GeneralDzData.parking_radar_voice = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[8]);
        updateMainSettingActivity(5);
        updateAllRadarView();
    }

    private void setCarData0x12() {
        GeneralDzData.parking_off_road = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[7]);
        updateMainSettingActivity(5);
    }

    private void setDriverAssistant0x47() {
        GeneralDzData.pa_driving = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 6, 2);
        GeneralDzData.pa_last = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[9]);
        GeneralDzData.pa_distance = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[9], 0, 5);
        GeneralDzData.pa_front_assist = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[10]);
        GeneralDzData.pa_front_warning = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[10]);
        GeneralDzData.pa_front_distance = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[10]);
        GeneralDzData.pa_lane = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[11]);
        GeneralDzData.pa_traffice = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[12]);
        GeneralDzData.pa_driverAlertSystem = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[13]);
        GeneralDzData.pa_dead_zone = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[13]);
        GeneralDzData.pa_proactive = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[14]);
        GeneralDzData.pa_lane_keeping = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[15]);
        updateMainSettingActivity(6);
    }

    private void setLight0x67() {
        GeneralDzData.light_big = this.mCanBusInfoInt[3];
        GeneralDzData.light_ambient = this.mCanBusInfoInt[4];
        GeneralDzData.light_ambient_in = this.mCanBusInfoInt[5];
        GeneralDzData.light_ambient_right = this.mCanBusInfoInt[6];
        GeneralDzData.light_system = this.mCanBusInfoInt[7];
        GeneralDzData.light_ambient_all = this.mCanBusInfoInt[8];
        updateMainSettingActivity(4);
        updateMeterActivity();
    }

    private void setLight0x68() {
        GeneralDzData.light_assis = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[5]);
        GeneralDzData.light_bend = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[5]);
        GeneralDzData.light_openTime = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 4, 2);
        GeneralDzData.light_automatic = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        GeneralDzData.light_change = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[5]);
        GeneralDzData.light_travel = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 1, 1);
        GeneralDzData.light_day = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[5]);
        GeneralDzData.light_instrument = this.mCanBusInfoInt[6];
        GeneralDzData.light_door = this.mCanBusInfoInt[7];
        GeneralDzData.light_footwell = this.mCanBusInfoInt[8];
        GeneralDzData.light_coming = this.mCanBusInfoInt[9];
        GeneralDzData.light_leaving = this.mCanBusInfoInt[10];
        updateMainSettingActivity(4);
    }

    private void setSport0x85() {
        GeneralDzData.esc = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
        updateMainSettingActivity(1);
    }

    private void setAir0x31() {
        GeneralDzData.right_front_ventilate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        GeneralDzData.left_front_ventilate = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
        GeneralDzData.right_front_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        GeneralDzData.left_front_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        GeneralDzData.right_rear_hot = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 2, 2);
        if (this.right_front_ventilate != GeneralDzData.right_front_ventilate || this.left_front_ventilate != GeneralDzData.left_front_ventilate || this.right_front_hot != GeneralDzData.right_front_hot || this.left_front_hot != GeneralDzData.left_front_hot) {
            this.right_front_ventilate = GeneralDzData.right_front_ventilate;
            this.left_front_ventilate = GeneralDzData.left_front_ventilate;
            this.right_front_hot = GeneralDzData.right_front_hot;
            this.left_front_hot = GeneralDzData.left_front_hot;
            if (!this.isFirstAirSeat) {
                updateDZAirSeatView(this.mContext);
            } else {
                this.isFirstAirSeat = false;
            }
        }
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDzData.air_power = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDzData.max_ac = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDzData.auto = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDzData.sync = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        GeneralDzData.rear_lock = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralAirData.ac = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDzData.fahrenheit_celsius = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralAirData.in_out_cycle = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        GeneralDzData.steering_wheel_heating = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[5]);
        GeneralDzData.auto_wind_power = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 2);
        GeneralAirData.front_wind_level = this.mCanBusInfoInt[7];
        GeneralDzData.air_front_left_temp = resolveAutoTemp(this.mCanBusInfoInt[8]);
        GeneralDzData.air_front_right_temp = resolveAutoTemp(this.mCanBusInfoInt[9]);
        GeneralDzData.air_rear_temp = resolveAutoTemp(this.mCanBusInfoInt[12]);
        GeneralDzData.front_defog = this.mCanBusInfoInt[6];
        GeneralDzData.air_front_wind = this.mCanBusInfoInt[6];
        GeneralDzData.air_rear_wind = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[11], 0, 4);
        if (boolBit7) {
            if (!SystemUtil.isForeground(this.mContext, AirActivity.class.getName()) && !SystemUtil.isForeground(this.mContext, AirCleanActivity.class.getName()) && !SystemUtil.isForeground(this.mContext, AirSettingActivity.class.getName())) {
                Intent intent = new Intent();
                intent.addFlags(268435456);
                intent.setComponent(PA6SAirActivity);
                intent.putExtra("type", "SETUP");
                this.mContext.startActivity(intent);
            }
        } else {
            ComponentName componentName = ((ActivityManager) this.mContext.getSystemService("activity")).getRunningTasks(1).get(0).topActivity;
            MyLog.temporaryTracking(componentName.getClassName());
            if (componentName.getClassName().trim().equals("com.hzbhd.canbus.car_cus._283.activity.AirActivity")) {
                realKeyClick(this.mContext, 50);
            } else if (componentName.getClassName().trim().equals("com.hzbhd.canbus.car_cus._283.activity.AirSettingActivity")) {
                realKeyClick(this.mContext, 50);
                realKeyClick(this.mContext, 50);
            }
        }
        updateAirActivity();
        SharePreUtil.setBoolValue(this.mContext, SHARE_283_AIR_SYNC, GeneralDzData.sync);
        GeneralDzData.air_clean = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[5]);
        GeneralDzData.air_clean_progress = this.mCanBusInfoInt[10];
        updateAirCleanActivity();
        GeneralDzData.wheel_seat_hot_sync = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[5]);
        updateAirSettingActivity();
        updateOutDoorTemp(this.mContext, resolveOutDoorTemp());
    }

    private void setTmps0x48() {
        GeneralDzData.tmpsUnit = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[2], 6, 2);
        GeneralDzData.left_front_tmps_exception = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDzData.right_front_tmps_exception = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        GeneralDzData.left_rear_tmps_exception = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        GeneralDzData.right_rear_tmps_exception = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.left_front_tmps_reality = getTwoData(iArr[4], iArr[5]) / 10.0f;
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.right_front_tmps_reality = getTwoData(iArr2[6], iArr2[7]) / 10.0f;
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.left_rear_tmps_reality = getTwoData(iArr3[8], iArr3[9]) / 10.0f;
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralDzData.right_rear_tmps_reality = getTwoData(iArr4[10], iArr4[11]) / 10.0f;
        int[] iArr5 = this.mCanBusInfoInt;
        GeneralDzData.left_front_tmps_reference = getTwoData(iArr5[12], iArr5[13]) / 10.0f;
        int[] iArr6 = this.mCanBusInfoInt;
        GeneralDzData.right_front_tmps_reference = getTwoData(iArr6[14], iArr6[15]) / 10.0f;
        int[] iArr7 = this.mCanBusInfoInt;
        GeneralDzData.left_rear_tmps_reference = getTwoData(iArr7[16], iArr7[17]) / 10.0f;
        int[] iArr8 = this.mCanBusInfoInt;
        GeneralDzData.right_rear_tmps_reference = getTwoData(iArr8[18], iArr8[19]) / 10.0f;
        updateMainActivity(6);
    }

    private void setTmps0x46() {
        GeneralDzData.tmpsChoose = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[3], 5, 2);
        updateMainActivity(5);
        GeneralDzData.tmpsAlarm = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        GeneralDzData.tmpsAlarmSpeed = this.mCanBusInfoInt[4];
        updateMainSettingActivity(10);
    }

    private void setDrivingMode0x86() {
        GeneralDzData.drivingMode_comfort = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_normal = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_sport = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_indivdual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_cross_country = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[2]);
        GeneralDzData.drivingMode_snowfield = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[2]);
        boolean boolBit7 = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        boolean boolBit6 = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        boolean boolBit5 = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        boolean boolBit4 = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        boolean boolBit3 = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        boolean boolBit2 = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        boolean boolBit1 = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        if (boolBit7) {
            GeneralDzData.drivingMode = 7;
        }
        if (boolBit6) {
            GeneralDzData.drivingMode = 6;
        }
        if (boolBit5) {
            GeneralDzData.drivingMode = 5;
        }
        if (boolBit4) {
            GeneralDzData.drivingMode = 4;
        }
        if (boolBit3) {
            GeneralDzData.drivingMode = 3;
        }
        if (boolBit2) {
            GeneralDzData.drivingMode = 2;
        }
        if (boolBit1) {
            GeneralDzData.drivingMode = 1;
        }
        DriveData.Comfort = DataHandleUtils.getBoolBit7(this.mCanBusInfoInt[3]);
        DriveData.Normal = DataHandleUtils.getBoolBit6(this.mCanBusInfoInt[3]);
        DriveData.Sport = DataHandleUtils.getBoolBit5(this.mCanBusInfoInt[3]);
        DriveData.Eco = DataHandleUtils.getBoolBit4(this.mCanBusInfoInt[3]);
        DriveData.Indivdual = DataHandleUtils.getBoolBit3(this.mCanBusInfoInt[3]);
        DriveData.xuedi = DataHandleUtils.getBoolBit2(this.mCanBusInfoInt[3]);
        DriveData.yueye = DataHandleUtils.getBoolBit1(this.mCanBusInfoInt[3]);
        DriveData.yueye_personal = DataHandleUtils.getBoolBit0(this.mCanBusInfoInt[3]);
        DriveData.DCC = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 6, 2);
        DriveData.Dynamic_Bend_lighting = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 4, 2);
        DriveData.Engine = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 2, 2);
        DriveData.ACC = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[4], 0, 2);
        DriveData.Air_Conditioning = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 7, 1);
        DriveData.Steering = DataHandleUtils.getIntFromByteWithBit(this.mCanBusInfoInt[5], 6, 1);
        SharePreUtil.setIntValue(this.mContext, SHARE_283_DRIVER_MODE, GeneralDzData.drivingMode);
        updateMainSettingPersonalActivity(16);
        updateMainActivity(4);
    }

    private void setCarData0x13() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.launch_oil = getTwoDataVehicle(iArr[2], iArr[3]);
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.launch_residu = String.valueOf(getTwoData(iArr2[4], iArr2[5]));
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.launch_mileage = getTwoDataVehicle(iArr3[6], iArr3[7]);
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralDzData.launch_time = getTwoDataTime(iArr4[8], iArr4[9]);
        GeneralDzData.launch_speed = String.valueOf(this.mCanBusInfoInt[10]);
        updateMainActivity(1);
    }

    private void setCarData0x14() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.longtime_oil = getTwoDataVehicle(iArr[2], iArr[3]);
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.longtime_residu = String.valueOf(getTwoData(iArr2[4], iArr2[5]));
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.longtime_mileage = getTwoDataVehicle(iArr3[6], iArr3[7]);
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralDzData.longtime_time = getTwoDataTime(iArr4[8], iArr4[9]);
        GeneralDzData.longtime_speed = String.valueOf(this.mCanBusInfoInt[10]);
        updateMainActivity(2);
    }

    private void setCarData0x15() {
        int[] iArr = this.mCanBusInfoInt;
        GeneralDzData.refuel_oil = getTwoDataVehicle(iArr[2], iArr[3]);
        int[] iArr2 = this.mCanBusInfoInt;
        GeneralDzData.refuel_residu = String.valueOf(getTwoData(iArr2[4], iArr2[5]));
        int[] iArr3 = this.mCanBusInfoInt;
        GeneralDzData.refuel_mileage = getTwoDataVehicle(iArr3[6], iArr3[7]);
        int[] iArr4 = this.mCanBusInfoInt;
        GeneralDzData.refuel_time = getTwoDataTime(iArr4[8], iArr4[9]);
        GeneralDzData.refuel_speed = String.valueOf(this.mCanBusInfoInt[10]);
        updateMainActivity(3);
    }

    private void keyControl0x11() throws SecurityException {
        int i = 8;
        switch (this.mCanBusInfoInt[4]) {
            case 0:
                realKeyClick(0);
                realKeyLongClick(0);
                isKeyModeMsgRepeat(new byte[]{0, 0});
                break;
            case 1:
                realKeyLongClick(7);
                realKeyClick(7);
                break;
            case 2:
                realKeyLongClick(8);
                realKeyClick(8);
                break;
            case 3:
                realKeyClick(3);
                break;
            case 4:
                realKeyClick(HotKeyConstant.K_SPEECH);
                break;
            case 5:
                realKeyClick(HotKeyConstant.K_PHONE_ON_OFF);
                break;
            case 8:
                realKeyClick(HotKeyConstant.K_DOWN_HANGUP);
                break;
            case 9:
                realKeyClick(HotKeyConstant.K_UP_PICKUP);
                break;
            case 10:
            case 11:
                realKeyClick(2);
                break;
            case 12:
                CommUtil.printHexString("scyscyscy:Mode DCC key 1---------->", this.mCanBusInfoByte);
                byte[] bArr = this.mCanBusInfoByte;
                if (!isKeyModeMsgRepeat(new byte[]{bArr[4], bArr[5]})) {
                    CommUtil.printHexString("scyscyscy:Mode DCC key 2---------->", this.mCanBusInfoByte);
                    if (this.mCanBusInfoInt[5] != 0) {
                        CommUtil.printHexString("scyscyscy:Mode DCC key 3---------->", this.mCanBusInfoByte);
                        SystemUtil.isForeground(this.mContext, AirSettingActivity.class.getName());
                        int i2 = GeneralDzData.drivingMode;
                        Log.d("scyscyscy", "scyscyscy:Mode DCC key 4---------->" + i2 + "<----->" + this.tempDrivingMode);
                        if (i2 != -1 && i2 != this.tempDrivingMode) {
                            switch (i2) {
                                case 1:
                                    i = 6;
                                    break;
                                case 2:
                                    i = 1;
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    i = 5;
                                    break;
                                case 5:
                                    i = 4;
                                    break;
                                case 6:
                                    i = 3;
                                    break;
                                case 7:
                                    i = 2;
                                    break;
                                default:
                                    i = 0;
                                    break;
                            }
                            byte[] bArr2 = {22, -117, (byte) i, 0, 0};
                            CanbusMsgSender.sendMsg(bArr2);
                            CommUtil.printHexString("scyscyscy:Mode DCC 发 ", bArr2);
                            this.tempDrivingMode = i2;
                            break;
                        }
                    }
                }
                break;
        }
    }

    private void setCarData0x11() {
        byte[] bArr = this.mCanBusInfoByte;
        GeneralParkData.trackAngle = TrackInfoUtil.getTrackAngle0(bArr[9], bArr[8], 0, 540, 16);
        updateParkUi(null, this.mContext);
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._283.MsgMgr.2
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.getMyPanoramicView().updateParkUi();
            }
        });
        GeneralDzData.handBrake = DataHandleUtils.getBoolBit3(this.mCanBusInfoByte[2]);
        GeneralDzData.gears = DataHandleUtils.getBoolBit2(this.mCanBusInfoByte[2]);
        GeneralDzData.speed = this.mCanBusInfoByte[3];
        MessageSender.sendMsg((byte) 74, (byte) 11, GeneralDzData.handBrake);
        refreRadarUiData();
        updateDZRadarView(this.mContext);
    }

    private void setPublicFrontRearRadar0x41() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRearRadarLocationData(10, (iArr[2] / 16) + 1, (iArr[3] / 16) + 1, (iArr[4] / 16) + 1, (iArr[5] / 16) + 1);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setFrontRadarLocationData(10, (iArr2[6] / 16) + 1, (iArr2[7] / 16) + 1, (iArr2[8] / 16) + 1, (iArr2[9] / 16) + 1);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDyFrontRearRadar0x41() {
        GeneralDzData.radar_rear_l = this.mCanBusInfoInt[2];
        GeneralDzData.radar_rear_ml = this.mCanBusInfoInt[3];
        GeneralDzData.radar_rear_mr = this.mCanBusInfoInt[4];
        GeneralDzData.radar_rear_r = this.mCanBusInfoInt[5];
        GeneralDzData.radar_front_l = this.mCanBusInfoInt[6];
        GeneralDzData.radar_front_ml = this.mCanBusInfoInt[7];
        GeneralDzData.radar_front_mr = this.mCanBusInfoInt[8];
        GeneralDzData.radar_front_r = this.mCanBusInfoInt[9];
        if (getReverseState()) {
            GeneralDzData.show_radar = false;
            refreRadarUiData();
        } else {
            updateDZRadarView(this.mContext);
        }
    }

    private void setPublicLeftRightRadar0x42() {
        int[] iArr = this.mCanBusInfoInt;
        RadarInfoUtil.setRightRadarLocationData(10, (iArr[2] / 16) + 1, (iArr[3] / 16) + 1, (iArr[4] / 16) + 1, (iArr[5] / 16) + 1);
        int[] iArr2 = this.mCanBusInfoInt;
        RadarInfoUtil.setLeftRadarLocationData(10, (iArr2[6] / 16) + 1, (iArr2[7] / 16) + 1, (iArr2[8] / 16) + 1, (iArr2[9] / 16) + 1);
        GeneralParkData.radar_location_data = RadarInfoUtil.mLocationMap;
        updateParkUi(null, this.mContext);
    }

    private void setDyLeftRightRadar0x42() {
        GeneralDzData.radar_right_f = this.mCanBusInfoInt[2];
        GeneralDzData.radar_right_mf = this.mCanBusInfoInt[3];
        GeneralDzData.radar_right_mr = this.mCanBusInfoInt[4];
        GeneralDzData.radar_right_r = this.mCanBusInfoInt[5];
        GeneralDzData.radar_left_f = this.mCanBusInfoInt[6];
        GeneralDzData.radar_left_mf = this.mCanBusInfoInt[7];
        GeneralDzData.radar_left_mr = this.mCanBusInfoInt[8];
        GeneralDzData.radar_left_r = this.mCanBusInfoInt[9];
        if (getReverseState()) {
            GeneralDzData.show_radar = false;
            refreRadarUiData();
        } else {
            updateDZRadarView(this.mContext);
        }
    }

    private void setCamera0xE8() {
        GeneralDzData.camera_wide = this.mCanBusInfoInt[3] == 1;
        GeneralDzData.camera_standard = this.mCanBusInfoInt[3] == 2;
        GeneralDzData.camera_overlook = this.mCanBusInfoInt[3] == 3;
        GeneralDzData.camera_margin = this.mCanBusInfoInt[3] == 9;
        GeneralDzData.camera_light = this.mCanBusInfoInt[4];
        GeneralDzData.camera_contrast = this.mCanBusInfoInt[5];
        GeneralDzData.camera_color = this.mCanBusInfoInt[6];
        updateAllRadarView();
        if (this.mCanBusInfoInt[7] != 0 && !getReverseState()) {
            forceReverse(this.mContext, true);
        } else {
            forceReverse(this.mContext, false);
        }
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void sourceSwitchNoMediaInfoChange(boolean z) {
        super.sourceSwitchNoMediaInfoChange(z);
        Log.d("scyscyscy", "---------->sourceSwitchNoMediaInfoChange");
        MeterManager.send0xE6Null(9);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioInfoChange(int i, String str, String str2, String str3, int i2) {
        super.radioInfoChange(i, str, str2, str3, i2);
        byte[] bArrCompensateZero = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -111, 0, 0}, (str2 + " " + getBandUnit(str)).getBytes()), 28);
        byte[] bArrCompensateZero2 = DataHandleUtils.compensateZero(DataHandleUtils.byteMerger(new byte[]{22, -110, 0}, str.getBytes()), 27);
        CanbusMsgSender.sendMsg(bArrCompensateZero);
        CanbusMsgSender.sendMsg(bArrCompensateZero2);
        sendSourceMsg2(" ", 147);
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

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void radioDestroy() {
        super.radioDestroy();
        Log.d("scyscyscy", "---------->radioDestroy");
        MeterManager.send0xE6Null(1);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void discInfoChange(byte b, int i, int i2, int i3, int i4, int i5, int i6, boolean z, boolean z2, int i7, String str, String str2, String str3) {
        super.discInfoChange(b, i, i2, i3, i4, i5, i6, z, z2, i7, str, str2, str3);
        Log.d("scyscyscy", "---------->discInfoChange");
        boolean z3 = i6 == 1;
        String str4 = Util.numCompensateZero((byte) (i / 3600)) + ":" + Util.numCompensateZero((byte) ((i / 60) % 60)) + ":" + Util.numCompensateZero((byte) (i % 60));
        MeterManager.m0xE6Data[6] = (byte) DataHandleUtils.setIntByteWithBit(4, 7, true);
        MeterManager.send0xE6();
        MeterManager.sendMediaMeterData(z3 ? "DVD" : "CD", str4, " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void auxInInfoChange() {
        super.auxInInfoChange();
        Log.d("scyscyscy", "---------->auxInInfoChange");
        MeterManager.send0xE6Null(2);
        MeterManager.sendMediaMeterData("AUX", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dtvInfoChange() {
        super.dtvInfoChange();
        Log.d("scyscyscy", "---------->dtvInfoChange");
        MeterManager.send0xE6Null(3);
        MeterManager.sendMediaMeterData("DTV", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusicId3InfoChange(String str, String str2, String str3) {
        super.btMusicId3InfoChange(str, str2, str3);
        byte[] bArr = {22, -111, 0, 0};
        byte[] bArrExceptBOMHead = new byte[0];
        try {
            bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, bArrExceptBOMHead), 28));
        byte[] bArr2 = {22, -110, 0};
        byte[] bArrExceptBOMHead2 = new byte[0];
        try {
            bArrExceptBOMHead2 = DataHandleUtils.exceptBOMHead(str2.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr2, bArrExceptBOMHead2), 27));
        byte[] bArr3 = {22, -109, 0};
        byte[] bArrExceptBOMHead3 = new byte[0];
        try {
            bArrExceptBOMHead3 = DataHandleUtils.exceptBOMHead(str3.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.BTAUDIO.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr3, bArrExceptBOMHead3), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btMusiceDestdroy() {
        super.btMusiceDestdroy();
        Log.d("scyscyscy", "---------->btMusiceDestdroy");
        MeterManager.send0xE6Null(4);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, String str, String str2, String str3, long j, byte b9, int i3, boolean z, long j2, String str4, String str5, String str6, boolean z2) {
        super.musicInfoChange(b, b2, i, i2, b3, b4, b5, b6, b7, b8, str, str2, str3, j, b9, i3, z, j2, str4, str5, str6, z2);
        byte[] bArr = {22, -111, 0, 0};
        byte[] bArrExceptBOMHead = new byte[0];
        try {
            bArrExceptBOMHead = DataHandleUtils.exceptBOMHead(str4.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr, bArrExceptBOMHead), 28));
        byte[] bArr2 = {22, -110, 0};
        byte[] bArrExceptBOMHead2 = new byte[0];
        try {
            bArrExceptBOMHead2 = DataHandleUtils.exceptBOMHead(str6.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr2, bArrExceptBOMHead2), 27));
        byte[] bArr3 = {22, -109, 0};
        byte[] bArrExceptBOMHead3 = new byte[0];
        try {
            bArrExceptBOMHead3 = DataHandleUtils.exceptBOMHead(str5.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
        }
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.MUSIC.toString(), DataHandleUtils.makeBytesFixedLength(DataHandleUtils.byteMerger(bArr3, bArrExceptBOMHead3), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void musicDestroy() {
        super.musicDestroy();
        Log.d("scyscyscy", "---------->musicDestroy");
        MeterManager.send0xE6Null(5);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoDestroy() {
        super.videoDestroy();
        Log.d("scyscyscy", "---------->videoDestroy");
        MeterManager.send0xE6Null(6);
        MeterManager.sendMediaMeterData(" ", " ", " ", " ");
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void videoInfoChange(byte b, byte b2, int i, int i2, byte b3, byte b4, byte b5, String str, byte b6, byte b7, String str2, String str3, String str4, int i3, byte b8, boolean z, int i4) {
        super.videoInfoChange(b, b2, i, i2, b3, b4, b5, str, b6, b7, str2, str3, str4, i3, b8, z, i4);
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -111, 0, 0}, (((int) b4) + ":" + ((int) b5) + "   ").getBytes()), 28));
        sendMediaMsg(this.mContext, SourceConstantsDef.SOURCE_ID.VIDEO.toString(), DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, -110, 0}, ((((b6 & 255) * 256) + (i & 255)) + "/" + (i2 & 255)).getBytes()), 27));
        sendSourceMsg2(" ", 147);
    }

    private void sendSourceMsg2(String str, int i) {
        CanbusMsgSender.sendMsg(DataHandleUtils.compensateZero(Util.byteMerger(new byte[]{22, (byte) i, 0}, str.getBytes()), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void dateTimeRepCanbus(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) throws Settings.SettingNotFoundException {
        Context context;
        super.dateTimeRepCanbus(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        try {
            context = this.mContext;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            setMode1(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        }
        if (context == null) {
            return;
        }
        int i11 = Settings.System.getInt(context.getContentResolver(), TimeSyncMode.MODE_KEY);
        if (i11 == TimeSyncMode.MODE_1.intValue()) {
            setMode1(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        } else if (i11 == TimeSyncMode.MODE_2.intValue()) {
            setMode2(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        } else if (i11 == TimeSyncMode.MODE_3.intValue()) {
            setMode3(i, i2, i3, i4, i5, i6, i7, i8, i9, z, z2, z3, i10);
        }
        this.mSystemDateFormat = i9;
    }

    private void setMode1(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        Log.d("fxHouTime", "模式1 时间：" + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i6);
        if (CHANG_TIME && i7 == 0) {
            CHANG_TIME = false;
            sendTimeCommonds(i2, i3, i4, i5, i6, false);
            Log.d("fxHouTime", "模式1__成功");
        }
    }

    private void setMode2(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        Log.d("fxHouTime", "模式2 时间：" + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i6);
        int i11 = this.sendTimeTag;
        if (i11 < 10) {
            this.sendTimeTag = i11 + 1;
            Log.d("fxHouTime", "模式2__成功");
            sendTimeCommonds(i2, i3, i4, i5, i6, false);
        }
    }

    private void setMode3(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, boolean z, boolean z2, boolean z3, int i10) {
        Log.d("fxHouTime", "模式3__成功 时间：" + i2 + " " + i3 + " " + i4 + " " + i5 + " " + i7);
        sendTimeCommonds(i2, i3, i4, i5, i6, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTimeCommonds(int i, int i2, int i3, int i4, int i5, boolean z) {
        CanbusMsgSender.sendMsg(new byte[]{22, -53, (byte) DataHandleUtils.setIntByteWithBit(DataHandleUtils.setIntByteWithBit(0, 7, !z), 6, true), (byte) i4, (byte) i5, 0, 0, 1, (byte) i, (byte) i2, (byte) i3, (byte) this.mSystemDateFormat});
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneHangUpInfoChange(byte[] bArr, boolean z, boolean z2) throws UnsupportedEncodingException {
        super.btPhoneHangUpInfoChange(bArr, z, z2);
        Log.d("scyscyscy", "---------->btPhoneHangUpInfoChange");
        PA6Utils.setBtName(this.mContext, "");
        this.mHandler.removeMessages(0);
        sendBlueToothName();
        sendHandler(1);
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneStatusInfoChange(int i, byte[] bArr, final boolean z, boolean z2, boolean z3, boolean z4, int i2, int i3, Bundle bundle) {
        super.btPhoneStatusInfoChange(i, bArr, z, z2, z3, z4, i2, i3, bundle);
        if (i == 6) {
            this.mOperatorName = getOperator(bundle);
            sendHandler(1);
        } else {
            if (i != 0) {
                return;
            }
            if (!z) {
                this.mOperatorName = "";
                sendHandler(1);
            }
            new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.MsgMgr$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() throws InterruptedException, UnsupportedEncodingException {
                    this.f$0.m352x651dda15(z);
                }
            }).start();
            MeterManager.m0xE6Data[2] = (byte) DataHandleUtils.setIntByteWithBit(0, 6, z);
            MeterManager.m0xE6Data[4] = (byte) i2;
            MeterManager.m0xE6Data[5] = (byte) i3;
            MeterManager.send0xE6();
        }
    }

    /* renamed from: lambda$btPhoneStatusInfoChange$1$com-hzbhd-canbus-car-_283-MsgMgr, reason: not valid java name */
    /* synthetic */ void m352x651dda15(boolean z) throws InterruptedException, UnsupportedEncodingException {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!z) {
            this.mBlueToothName = "";
        } else {
            this.mBlueToothName = BtApi.INSTANCE.getInstance().getHfpDeviceName();
        }
        sendBlueToothName();
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneOutGoingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneOutGoingInfoChange(bArr, z, z2);
        Log.d("scyscyscy", "btPhoneOutGoingInfoChange: " + new String(bArr));
        this.mHandler.sendEmptyMessageDelayed(0, 1000L);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -106, 0}, bArr), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void btPhoneIncomingInfoChange(byte[] bArr, boolean z, boolean z2) {
        super.btPhoneIncomingInfoChange(bArr, z, z2);
        Log.d("scyscyscy", "btPhoneIncomingInfoChange: " + new String(bArr));
        this.mHandler.sendEmptyMessageDelayed(0, 1000L);
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(new byte[]{22, -106, 0}, bArr), 27));
    }

    @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr, com.hzbhd.canbus.interfaces.MsgMgrInterface
    public void currentVolumeInfoChange(int i, boolean z) {
        super.currentVolumeInfoChange(i, z);
        Log.d("scyscyscy", i + "<----currentVolumeInfoChange------>" + z);
        int intByteWithBit = z ? DataHandleUtils.setIntByteWithBit(4, 7, true) : 0;
        MeterManager.m0xE6Data[3] = (byte) i;
        MeterManager.m0xE6Data[8] = (byte) intByteWithBit;
        if (this.nowVlume != i) {
            this.nowVlume = i;
            MeterManager.send0xE6();
        }
    }

    private void sendBlueToothName() throws UnsupportedEncodingException {
        byte[] bArr = {22, -107, 0};
        byte[] bytes = new byte[0];
        try {
            if (TextUtils.isEmpty(this.mBlueToothName)) {
                this.mBlueToothName = "";
            }
            bytes = this.mBlueToothName.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(bArr, bytes), 27));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendOperatorName() throws UnsupportedEncodingException {
        Log.d("scyscyscy", "mOperatorName---------->" + this.mOperatorName);
        byte[] bArr = {22, -106, 0};
        byte[] bytes = new byte[0];
        try {
            if (TextUtils.isEmpty(this.mOperatorName)) {
                this.mOperatorName = "";
            }
            bytes = this.mOperatorName.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CanbusMsgSender.sendMsg(DataHandleUtils.makeBytesFixedLength(Util.byteMerger(bArr, bytes), 27));
    }

    private void realKeyClick(int i) {
        Context context = this.mContext;
        int[] iArr = this.mCanBusInfoInt;
        realKeyClick5(context, i, iArr[4], iArr[5]);
    }

    private void realKeyLongClick(int i) {
        realKeyLongClick1(this.mContext, i, this.mCanBusInfoInt[5]);
    }

    private String getTwoDataVehicle(int i, int i2) {
        return String.format("%.1f", Float.valueOf(((i * 256) + i2) * 0.1f));
    }

    private String getTwoDataTime(int i, int i2) {
        int i3 = (i * 256) + i2;
        return (i3 / 60) + "  h  " + (i3 % 60);
    }

    private String resolveOutDoorTemp() {
        return ((this.mCanBusInfoInt[13] * 0.5f) - 40.0f) + getTempUnitC(this.mContext);
    }

    private String resolveAutoTemp(int i) {
        if (254 == i) {
            return "LO";
        }
        if (255 == i) {
            return "HI";
        }
        if (GeneralDzData.fahrenheit_celsius) {
            return (i * 0.5f) + getTempUnitC(this.mContext);
        }
        return ((int) (i * 0.5f)) + getTempUnitF(this.mContext);
    }

    private String getCarImei(byte[] bArr) {
        try {
            return new String(bArr, "ascii");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
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

    private void updateSystemUIDrivingPattern(int i) {
        if (i < 0 || i > 4) {
            return;
        }
        Intent intent = new Intent("com.android.systemui.statusbar.action.CARMODECHANGE");
        intent.putExtra("_283_car_mode", String.valueOf(i));
        this.mContext.sendBroadcastAsUser(intent, UserHandle.ALL);
    }

    private void setTemp(String str) throws NumberFormatException {
        float f = Float.parseFloat(str);
        double d = f;
        if (d < 16.0d) {
            sendAirCommonds(254.0f);
            return;
        }
        if (d > 29.5d) {
            sendAirCommonds(255.0f);
        } else {
            if (d < 16.0d || d >= 29.5d) {
                return;
            }
            sendAirCommonds(f);
        }
    }

    private void addTemp(byte b, String str, float f) {
        try {
            if (!str.equals("HI") && GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("LO") ? 16.0f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_c_unit), "")).floatValue()) + f) * 2.0f;
                if (fFloatValue >= 59.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -1});
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subTemp(byte b, String str, float f) {
        try {
            if (!str.equals("LO") && GeneralDzData.fahrenheit_celsius) {
                float fFloatValue = ((str.equals("HI") ? 29.5f : Float.valueOf(str.replace(this.mContext.getString(R.string.str_temp_c_unit), "")).floatValue()) - f) * 2.0f;
                if (fFloatValue <= 32.0f) {
                    MessageSender.sendMsg(new byte[]{22, 58, b, -2});
                } else {
                    MessageSender.sendMsg(new byte[]{22, 58, b, (byte) fFloatValue});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subTempCommonds(final String str) {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.MsgMgr$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m354lambda$subTempCommonds$2$comhzbhdcanbuscar_283MsgMgr(str);
            }
        }).start();
    }

    /* renamed from: lambda$subTempCommonds$2$com-hzbhd-canbus-car-_283-MsgMgr, reason: not valid java name */
    /* synthetic */ void m354lambda$subTempCommonds$2$comhzbhdcanbuscar_283MsgMgr(String str) throws InterruptedException {
        subTemp((byte) 20, GeneralDzData.air_front_left_temp, Float.parseFloat(str));
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        subTemp((byte) 21, GeneralDzData.air_front_right_temp, Float.parseFloat(str));
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        subTemp((byte) 22, GeneralDzData.air_rear_temp, Float.parseFloat(str));
    }

    private void addTempCommonds(final String str) {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.MsgMgr$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                this.f$0.m351lambda$addTempCommonds$3$comhzbhdcanbuscar_283MsgMgr(str);
            }
        }).start();
    }

    /* renamed from: lambda$addTempCommonds$3$com-hzbhd-canbus-car-_283-MsgMgr, reason: not valid java name */
    /* synthetic */ void m351lambda$addTempCommonds$3$comhzbhdcanbuscar_283MsgMgr(String str) throws InterruptedException {
        addTemp((byte) 20, GeneralDzData.air_front_left_temp, Float.parseFloat(str));
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        addTemp((byte) 21, GeneralDzData.air_front_right_temp, Float.parseFloat(str));
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        addTemp((byte) 22, GeneralDzData.air_rear_temp, Float.parseFloat(str));
    }

    private void sendAirCommonds(final float f) {
        new Thread(new Runnable() { // from class: com.hzbhd.canbus.car._283.MsgMgr$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws InterruptedException {
                MsgMgr.lambda$sendAirCommonds$4(f);
            }
        }).start();
    }

    static /* synthetic */ void lambda$sendAirCommonds$4(float f) throws InterruptedException {
        byte b = (byte) (f * 2.0f);
        MessageSender.sendMsg(new byte[]{22, 58, 20, b});
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MessageSender.sendMsg(new byte[]{22, 58, 21, b});
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        MessageSender.sendMsg(new byte[]{22, 58, 22, b});
    }

    private void sendMsg(byte b, boolean z) {
        MessageSender.sendMsg(new byte[]{22, 58, b, z ? (byte) 1 : (byte) 0});
    }

    private void sendSmartPowerMode(int i) {
        MessageSender.sendDzMsg(i, GeneralDzSmartData.data3, GeneralDzSmartData.data4, GeneralDzSmartData.data5, GeneralDzSmartData.data6);
    }

    private void updateAllRadarView() {
        updateDZRadarView(this.mContext);
        refreRadarUiData();
    }

    private void refreRadarUiData() {
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._283.MsgMgr.4
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.getMyPanoramicView().refreRadarUi();
            }
        });
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

    private void updateMainSettingActivity(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("_283_CarSetting_what", i);
        if (getActivity() == null || !(getActivity() instanceof MainSettingActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateMainSettingPersonalActivity(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("_283_CarSetting_what", i);
        Log.d("mww getActivity()", "" + getActivity());
        if (getActivity() == null || !(getActivity() instanceof MainSettingPersonalActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateAirActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof AirActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateAirCleanActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof AirCleanActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateAirSettingActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof AirSettingActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateSmartActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof SmartPowerActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    private void updateMeterActivity() {
        Bundle bundle = new Bundle();
        if (getActivity() == null || !(getActivity() instanceof MeterActivity)) {
            return;
        }
        updateActivity(bundle);
    }

    protected void updateDZRadarView(Context context) {
        if (this.mRadarView == null) {
            this.mRadarView = new RadarView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._283.MsgMgr.5
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mRadarView.refreshUi();
            }
        });
    }

    protected void updateDZAirSeatView(Context context) {
        if (this.mAirSeatView == null) {
            this.mAirSeatView = new AirSeatView(context);
        }
        runOnUi(new AbstractMsgMgr.CallBackInterface() { // from class: com.hzbhd.canbus.car._283.MsgMgr.6
            @Override // com.hzbhd.canbus.msg_mgr.AbstractMsgMgr.CallBackInterface
            public void callback() {
                MsgMgr.this.mAirSeatView.refreshUi();
            }
        });
    }

    protected boolean isKeyMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusKeyCopy)) {
            return true;
        }
        this.mCanbusKeyCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    protected boolean isKeyModeMsgRepeat(byte[] bArr) {
        if (Arrays.equals(bArr, this.mCanbusKeyModeCopy)) {
            return true;
        }
        this.mCanbusKeyModeCopy = Arrays.copyOf(bArr, bArr.length);
        return false;
    }

    private String getOperator(Bundle bundle) {
        if (bundle == null) {
            return " ";
        }
        String string = bundle.getString("operatorName");
        if (string.equals("CHN-UNICOM")) {
            return this.mContext.getString(R.string._283_operator_1);
        }
        if (string.equals("CMCC")) {
            return this.mContext.getString(R.string._283_operator_2);
        }
        return string.equals("CHN-CT") ? this.mContext.getString(R.string._283_operator_3) : string;
    }

    private void sendHandler(int i) {
        this.mHandler.removeMessages(i);
        this.mHandler.sendEmptyMessage(i);
    }
}
