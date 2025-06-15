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

        // Procesamiento de comandos usando 'switch' directo sobre el comando 'str'
        switch (str) {
            case CanbusControlAction.DzCommands.CLOSE_AIR:
            case CanbusControlAction.REAR_WINDOWS_CLOSE:
                mAirControlHelper.airControlAction(str);
                break;
            case CanbusControlAction.DzCommands.MODE_COMFORT:
            case "mirror.fold":
            case "front_defog":
            case "skylight.open":
                mAirControlHelper.airControlAction(str);
                break;
            case "ac.temperature.max":
                mAirControlHelper.airControlStep(mAirControlHelper.getTemperatureUp());
                break;
            case "ac.temperature.min":
                mAirControlHelper.airControlStep(mAirControlHelper.getTemperatureDown());
                break;
            case "air.ac.on":
            case "air.ac.off":
                mAirControlHelper.airControlAction(str);
                break;
            case "ac.windlevel.max":
                mAirControlHelper.airControlMost(mAirControlHelper.getWindIncrease());
                break;
            case "ac.windlevel.min":
                mAirControlHelper.airControlMost(mAirControlHelper.getWindDecrease());
                break;
            case "ac.windlevel.up":
                mAirControlHelper.airControlStep(mAirControlHelper.getWindIncrease());
                break;
            case "ac.windlevel.down":
                mAirControlHelper.airControlStep(mAirControlHelper.getWindDecrease());
                break;
            case "ac.windlevel.to":
                if (str2 != null && str3 != null) {
                    mAirControlHelper.airControlTarget(mAirControlHelper.getWindTarget(), str2, str3);
                }
                break;
            case "ac.temperature.to":
                if (str2 != null && str3 != null) {
                    mAirControlHelper.airControlTarget(mAirControlHelper.getTemperatureTarget(), str2, str3);
                }
                break;
            case "all.windows.close":
            case "rear.windows.open":
            case "skylight.close":
            case "tailgate.open":
                mAirControlHelper.airControlAction(str);
                break;
            default:
                try {
                    MsgMgrFactory.getCanMsgMgrUtil(context).voiceControlInfo(str);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public static void registerHotKeyListener(final Context context) {
        ReceiveKeyManager.getInstance().registerHotKeyListener(KeyDispatcherConstant.getAppId(SourceConstantsDef.MODULE_ID.CANBUS), new IHotKeyListener() { // from class: com.hzbhd.canbus.util.ActionControlUtil.1
            private Intent intent;

            @Override // com.hzbhd.proxy.keydispatcher.interfaces.IHotKeyListener
            public boolean onKeyEvent(int i, int i2, int i3, Bundle bundle) {
                if (i == 182) {
                    try {
                        Objects.requireNonNull(MsgMgrFactory.getCanMsgMgrUtil(context)).onKeyEvent(i, i2, i3, bundle);
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
                    Intent intent = new Intent(context, AirActivity.class);
                    this.intent = intent;
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
