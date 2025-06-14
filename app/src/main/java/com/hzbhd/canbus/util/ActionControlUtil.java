package com.hzbhd.canbus.util;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.hzbhd.canbus.activity.AirActivity;
import com.hzbhd.canbus.adapter.bean.AirPageUiSet;
import com.hzbhd.canbus.config.CanIdSpecialConfig;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.canbus.control.CanbusControlAction;
import com.hzbhd.canbus.control.air_control.AirControlHelper;
import com.hzbhd.canbus.interfaces.UiMgrInterface;
import com.hzbhd.canbus.msg_mgr.MsgMgrFactory;
import com.hzbhd.canbus.msg_mgr.MsgMgrInterfaceUtil;
import com.hzbhd.canbus.ui_mgr.UiMgrFactory;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.midware.constant.HotKeyConstant;
import com.hzbhd.proxy.keydispatcher.ReceiveKeyManager;
import com.hzbhd.proxy.keydispatcher.constants.KeyDispatcherConstant;
import com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener;
import java.util.Objects;
import kotlin.text.Typography;

/* loaded from: classes2.dex */
public class ActionControlUtil {
    private static final String TAG = "ActionControlUtil";
    private static AirControlHelper mAirControlHelper;
    private static AirPageUiSet mAirPageUiSet;
    private static UiMgrInterface mUiMgrInterface;

    public static void acControl(Context context, String str) {
        acControl(context, str, null, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static void acControl(Context context, String str, String str2, String str3) {
        Log.i(TAG, "acControl: 1");
        if (TextUtils.isEmpty(str)) {
        }
        Log.i(TAG, "acControl: 2");
        if (mUiMgrInterface == null) {
            mUiMgrInterface = UiMgrFactory.getCanUiMgr(context);
        }
        if (mUiMgrInterface == null) {
            return;
        }
        Log.i(TAG, "acControl: 3");
        if (mAirPageUiSet == null) {
            mAirPageUiSet = mUiMgrInterface.getAirUiSet(context);
        }
        if (mAirPageUiSet == null) {
            return;
        }
        Log.i(TAG, "acControl: 4");
        if (mAirControlHelper == null) {
            mAirControlHelper = new AirControlHelper(context, mAirPageUiSet);
        }
        Log.i(TAG, "acControl: action[" + str + "], type[" + str2 + "], value[" + str3 + "]");
        if (CanIdSpecialConfig.isNewVoiceCanID(CanbusConfig.INSTANCE.getCanType())) {
            VoiceControlData.voiceAction = str;
            VoiceControlData.voiceType = str2;
            VoiceControlData.voiceValue = str3;
            try {
                MsgMgrFactory.getCanMsgMgrUtil(context).voiceControlInfo(str);
                return;
            } catch (NullPointerException e) {
                e.printStackTrace();
                return;
            }
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -2132439526:
                if (str.equals(CanbusControlAction.DzCommands.CLOSE_AIR)) {
                    c = '7';
                    break;
                }
                break;
            case -2039274493:
                if (str.equals("rear.windows.close")) {
                    c = '/';
                    break;
                }
                break;
            case -1911175584:
                if (str.equals(CanbusControlAction.DzCommands.MODE_COMFORT)) {
                    c = '3';
                    break;
                }
                break;
            case -1871342186:
                if (str.equals("rear.right.window.close")) {
                    c = Typography.quote;
                    break;
                }
                break;
            case -1789699888:
                if (str.equals("mirror.fold")) {
                    c = Typography.amp;
                    break;
                }
                break;
            case -1486324951:
                if (str.equals("mirror.unfold")) {
                    c = '\'';
                    break;
                }
                break;
            case -1470045433:
                if (str.equals("front_defog")) {
                    c = 23;
                    break;
                }
                break;
            case -1424245827:
                if (str.equals(CanbusControlAction.DzCommands.MODE_ORIGINAL)) {
                    c = '2';
                    break;
                }
                break;
            case -1412208249:
                if (str.equals("air.ac.on")) {
                    c = 16;
                    break;
                }
                break;
            case -1391366141:
                if (str.equals("skylight.open")) {
                    c = '%';
                    break;
                }
                break;
            case -1386876687:
                if (str.equals("front.right.window.close")) {
                    c = 30;
                    break;
                }
                break;
            case -1326915554:
                if (str.equals("ac.temperature.max")) {
                    c = 11;
                    break;
                }
                break;
            case -1326915316:
                if (str.equals("ac.temperature.min")) {
                    c = '\f';
                    break;
                }
                break;
            case -1226270570:
                if (str.equals("ac.open")) {
                    c = 14;
                    break;
                }
                break;
            case -954737720:
                if (str.equals("front.windows.close")) {
                    c = '-';
                    break;
                }
                break;
            case -910491843:
                if (str.equals("tailgate.open")) {
                    c = '(';
                    break;
                }
                break;
            case -891288852:
                if (str.equals("rear.right.window.open")) {
                    c = '#';
                    break;
                }
                break;
            case -866529054:
                if (str.equals("air.in.out.cycle.off")) {
                    c = 19;
                    break;
                }
                break;
            case -861720966:
                if (str.equals("front.windows.open")) {
                    c = ',';
                    break;
                }
                break;
            case -828782905:
                if (str.equals("air.ac.off")) {
                    c = 17;
                    break;
                }
                break;
            case -779932258:
                if (str.equals(CanbusControlAction.AIR_RIGHT_TEMPERATURE_UP)) {
                    c = 7;
                    break;
                }
                break;
            case -655752154:
                if (str.equals("ac.windlevel.max")) {
                    c = 2;
                    break;
                }
                break;
            case -655751916:
                if (str.equals("ac.windlevel.min")) {
                    c = 3;
                    break;
                }
                break;
            case -631663038:
                if (str.equals("rear_defog")) {
                    c = 24;
                    break;
                }
                break;
            case -597744666:
                if (str.equals("blow_positive")) {
                    c = 27;
                    break;
                }
                break;
            case -301140387:
                if (str.equals(CanbusControlAction.VoiceBoardcast.VOICE_BROADCAST_IS_FINISHED)) {
                    c = '0';
                    break;
                }
                break;
            case -193868961:
                if (str.equals("skylight.close")) {
                    c = Typography.dollar;
                    break;
                }
                break;
            case -89973496:
                if (str.equals(CanbusControlAction.DzCommands.MODE_SPORT)) {
                    c = '4';
                    break;
                }
                break;
            case -89004321:
                if (str.equals(CanbusControlAction.DzCommands.MODE_TRACK)) {
                    c = '5';
                    break;
                }
                break;
            case 3106:
                if (str.equals("ac")) {
                    c = 20;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c = 21;
                    break;
                }
                break;
            case 3094652:
                if (str.equals("dual")) {
                    c = 22;
                    break;
                }
                break;
            case 106858757:
                if (str.equals("power")) {
                    c = 26;
                    break;
                }
                break;
            case 225548432:
                if (str.equals(CanbusControlAction.AIR_LEFT_TEMPERATURE_DOWN)) {
                    c = 6;
                    break;
                }
                break;
            case 292772663:
                if (str.equals("rear.left.window.close")) {
                    c = ' ';
                    break;
                }
                break;
            case 345504582:
                if (str.equals("front.left.window.open")) {
                    c = 29;
                    break;
                }
                break;
            case 556354226:
                if (str.equals(CanbusControlAction.DzCommands.MODE_ECONOMICS)) {
                    c = '1';
                    break;
                }
                break;
            case 629126444:
                if (str.equals("ac.close")) {
                    c = 15;
                    break;
                }
                break;
            case 756225563:
                if (str.equals("in_out_cycle")) {
                    c = 25;
                    break;
                }
                break;
            case 925454385:
                if (str.equals("front.right.window.open")) {
                    c = 31;
                    break;
                }
                break;
            case 1146265120:
                if (str.equals("ac.windlevel.down")) {
                    c = 1;
                    break;
                }
                break;
            case 1166714377:
                if (str.equals(CanbusControlAction.AIR_LEFT_TEMPERATURE_UP)) {
                    c = 5;
                    break;
                }
                break;
            case 1225772921:
                if (str.equals("ac.windlevel.to")) {
                    c = 4;
                    break;
                }
                break;
            case 1225772953:
                if (str.equals("ac.windlevel.up")) {
                    c = 0;
                    break;
                }
                break;
            case 1358234944:
                if (str.equals("all.windows.close")) {
                    c = '+';
                    break;
                }
                break;
            case 1458598623:
                if (str.equals("rear.windows.open")) {
                    c = '.';
                    break;
                }
                break;
            case 1481217153:
                if (str.equals("ac.temperature.to")) {
                    c = '\r';
                    break;
                }
                break;
            case 1481217185:
                if (str.equals("ac.temperature.up")) {
                    c = '\t';
                    break;
                }
                break;
            case 1496068108:
                if (str.equals("air.in.out.cycle.on")) {
                    c = 18;
                    break;
                }
                break;
            case 1815036200:
                if (str.equals("ac.temperature.down")) {
                    c = '\n';
                    break;
                }
                break;
            case 1828332389:
                if (str.equals("tailgate.close")) {
                    c = ')';
                    break;
                }
                break;
            case 1949467947:
                if (str.equals("rear.left.window.open")) {
                    c = '!';
                    break;
                }
                break;
            case 1983837698:
                if (str.equals("all.windows.open")) {
                    c = '*';
                    break;
                }
                break;
            case 2074838878:
                if (str.equals(CanbusControlAction.DzCommands.OPEN_AIR)) {
                    c = '6';
                    break;
                }
                break;
            case 2103873253:
                if (str.equals(CanbusControlAction.AIR_RIGHT_TEMPERATURE_DOWN)) {
                    c = '\b';
                    break;
                }
                break;
            case 2109515900:
                if (str.equals("front.left.window.close")) {
                    c = 28;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                AirControlHelper airControlHelper = mAirControlHelper;
                airControlHelper.airControlStep(airControlHelper.getWindIncrease());
                break;
            case 1:
                AirControlHelper airControlHelper2 = mAirControlHelper;
                airControlHelper2.airControlStep(airControlHelper2.getWindDecrease());
                break;
            case 2:
                AirControlHelper airControlHelper3 = mAirControlHelper;
                airControlHelper3.airControlMost(airControlHelper3.getWindIncrease());
                break;
            case 3:
                AirControlHelper airControlHelper4 = mAirControlHelper;
                airControlHelper4.airControlMost(airControlHelper4.getWindDecrease());
                break;
            case 4:
                if (str2 != null && str3 != null) {
                    AirControlHelper airControlHelper5 = mAirControlHelper;
                    airControlHelper5.airControlTarget(airControlHelper5.getWindTarget(), str2, str3);
                    break;
                }
                break;
            case 5:
                AirControlHelper airControlHelper6 = mAirControlHelper;
                airControlHelper6.airControlStep(airControlHelper6.getLeftTemperatureUp());
                break;
            case 6:
                AirControlHelper airControlHelper7 = mAirControlHelper;
                airControlHelper7.airControlStep(airControlHelper7.getLeftTemperatureDown());
                break;
            case 7:
                AirControlHelper airControlHelper8 = mAirControlHelper;
                airControlHelper8.airControlStep(airControlHelper8.getRightTemperatureUp());
                break;
            case '\b':
                AirControlHelper airControlHelper9 = mAirControlHelper;
                airControlHelper9.airControlStep(airControlHelper9.getRightTemperatureDown());
                break;
            case '\t':
                AirControlHelper airControlHelper10 = mAirControlHelper;
                airControlHelper10.airControlStep(airControlHelper10.getTemperatureUp());
                break;
            case '\n':
                AirControlHelper airControlHelper11 = mAirControlHelper;
                airControlHelper11.airControlStep(airControlHelper11.getTemperatureDown());
                break;
            case 11:
                AirControlHelper airControlHelper12 = mAirControlHelper;
                airControlHelper12.airControlMost(airControlHelper12.getTemperatureUp());
                break;
            case '\f':
                AirControlHelper airControlHelper13 = mAirControlHelper;
                airControlHelper13.airControlMost(airControlHelper13.getTemperatureDown());
                break;
            case '\r':
                if (str2 != null && str3 != null) {
                    AirControlHelper airControlHelper14 = mAirControlHelper;
                    airControlHelper14.airControlTarget(airControlHelper14.getTemperatureTarget(), str2, str3);
                    break;
                }
                break;
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
                mAirControlHelper.airControlAction(str);
                break;
            default:
                try {
                    MsgMgrFactory.getCanMsgMgrUtil(context).voiceControlInfo(str);
                    break;
                } catch (NullPointerException e2) {
                    e2.printStackTrace();
                    return;
                }
        }
    }

    public static void registerHotKeyListener(final Context context) {
        ReceiveKeyManager.getInstance().registerHotKeyListener(KeyDispatcherConstant.getAppId(SourceConstantsDef.MODULE_ID.CANBUS), new IHotKeyListener() { // from class: com.hzbhd.canbus.util.ActionControlUtil.1
            private Intent intent;

            @Override // com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener
            public boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) {
                if (i == 182) {
                    try {
                        ((MsgMgrInterfaceUtil) Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(context))).onKeyEvent(i, i2, i3, bundle);
                        return true;
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                if (i == 185 || i == 217) {
                    ActivityUtils.startCanbusMainActivity(context);
                    return true;
                }
                if (i == 218) {
                    Intent intent = new Intent(context, (Class<?>) AirActivity.class);
                    this.intent = intent;
                    intent.setFlags(268435456);
                    context.startActivity(this.intent);
                    return true;
                }
                switch (i) {
                    case HotKeyConstant.K_AIR_WIND_DEC /* 359 */:
                        ActionControlUtil.acControl(context, "ac.windlevel.down");
                        return true;
                    case HotKeyConstant.K_AIR_WIND_INC /* 360 */:
                        ActionControlUtil.acControl(context, "ac.windlevel.up");
                        return true;
                    case HotKeyConstant.K_AIR_TEMP_DOWN /* 361 */:
                        ActionControlUtil.acControl(context, "ac.temperature.down");
                        return true;
                    case HotKeyConstant.K_AIR_TEMP_UP /* 362 */:
                        ActionControlUtil.acControl(context, "ac.temperature.up");
                        return true;
                    case HotKeyConstant.K_AIR_FRONT_DEFOG /* 363 */:
                        ActionControlUtil.acControl(context, "front_defog");
                        return true;
                    case HotKeyConstant.K_AIR_IN_OUT_CYCLE /* 364 */:
                        ActionControlUtil.acControl(context, "in_out_cycle");
                        return true;
                    case HotKeyConstant.K_AIR_AUTO /* 365 */:
                        ActionControlUtil.acControl(context, "auto");
                        return true;
                    case HotKeyConstant.K_AIR_AC /* 366 */:
                        ActionControlUtil.acControl(context, "ac");
                        return true;
                    case HotKeyConstant.K_AIR_POWER /* 367 */:
                        ActionControlUtil.acControl(context, "power");
                        return true;
                    default:
                        return false;
                }
            }
        });
    }
}
