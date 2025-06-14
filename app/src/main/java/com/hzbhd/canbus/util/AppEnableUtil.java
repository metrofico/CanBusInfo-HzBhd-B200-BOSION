package com.hzbhd.canbus.util;

import android.content.ComponentName;
import android.content.Context;
import com.hzbhd.canbus.adapter.util.HzbhdComponentName;
import com.hzbhd.canbus.car._462.AvmSwitchTag;
import com.hzbhd.canbus.comm.Constant;
import com.hzbhd.canbus.config.CanbusConfig;
import com.hzbhd.commontools.utils.ConfigUtil;

/* loaded from: classes2.dex */
public class AppEnableUtil {
    public static void cusProjectSetting(Context context, int i) {
        if (i == 273) {
            enableApp(context, Constant.ChengWeiMainActivity);
        } else {
            disableApp(context, Constant.ChengWeiMainActivity);
        }
        if (i == 277) {
            enableApp(context, Constant.VehicleMonitorActivity);
            enableApp(context, Constant.VehicleDiagnosisActivity);
            enableApp(context, Constant.EnergyFeedbackActivity);
            if (SharePreUtil.getBoolValue(context, Constant.SHARE_PRE_IS_FIRST_TIME_LAUNCHER, true)) {
                SharePreUtil.setBoolValue(context, Constant.SHARE_PRE_IS_FIRST_TIME_LAUNCHER, false);
                SystemUtil.cleanLauncher(context);
            }
        } else {
            disableApp(context, Constant.VehicleMonitorActivity);
            disableApp(context, Constant.VehicleDiagnosisActivity);
            disableApp(context, Constant.EnergyFeedbackActivity);
            if (ConfigUtil.getDeviceId().contains("YHTC05W1")) {
                LogUtil.showLog("setSpecifyCanTypeIdAndRestMpu");
                SelectCanTypeUtil.setCanTypeNotResetMpu(277);
            }
        }
        if (i == 283) {
            enableApp(context, Constant.DZMainActivity);
        } else {
            disableApp(context, Constant.DZMainActivity);
        }
        if (i == 291) {
            enableApp(context, Constant.DZMainActivity291);
        } else {
            disableApp(context, Constant.DZMainActivity291);
        }
        if (i == 290) {
            enableApp(context, Constant.ChengWeiMainActivity290);
        } else {
            disableApp(context, Constant.ChengWeiMainActivity290);
        }
        if (i == 304) {
            enableApp(context, HzbhdComponentName.YuanYiYuanMainActivity);
        } else {
            disableApp(context, HzbhdComponentName.YuanYiYuanMainActivity);
        }
        if (i == 436) {
            enableApp(context, Constant.MdDvrActivity);
        } else if (i == 441) {
            if (CanbusConfig.INSTANCE.getEachId() == 1) {
                enableApp(context, Constant.MdDvrActivity);
            } else if (CanbusConfig.INSTANCE.getEachId() == 2) {
                disableApp(context, Constant.MdDvrActivity);
            }
        } else if (i == 442) {
            enableApp(context, Constant.MdDvrActivity);
        } else {
            disableApp(context, Constant.MdDvrActivity);
        }
        if (i == 446) {
            enableApp(context, HzbhdComponentName.WmCarSettings);
        } else {
            disableApp(context, HzbhdComponentName.WmCarSettings);
        }
        if (i == 448) {
            enableApp(context, Constant.ID448DvrActivity);
        } else {
            disableApp(context, Constant.ID448DvrActivity);
        }
        if (i == 452 && CanbusConfig.INSTANCE.getEachId() == 3) {
            enableApp(context, HzbhdComponentName.OriginalMediaActivity_LEXUS);
        } else {
            disableApp(context, HzbhdComponentName.OriginalMediaActivity_LEXUS);
        }
        if (i == 451 || (i == 452 && CanbusConfig.INSTANCE.getEachId() != 3)) {
            enableApp(context, HzbhdComponentName.Aux2Activity);
        } else {
            disableApp(context, HzbhdComponentName.Aux2Activity);
        }
        if (i == 460) {
            enableApp(context, HzbhdComponentName.AirActivity460);
            enableApp(context, HzbhdComponentName.SettingActivity460);
        } else {
            disableApp(context, HzbhdComponentName.AirActivity460);
            disableApp(context, HzbhdComponentName.SettingActivity460);
        }
        boolean boolValue = SharePreUtil.getBoolValue(context, AvmSwitchTag.avmShow, false);
        if (i == 462 && boolValue) {
            enableApp(context, HzbhdComponentName.AvmActivityD1);
        } else {
            disableApp(context, HzbhdComponentName.AvmActivityD1);
        }
        if (i == 462) {
            enableApp(context, HzbhdComponentName.CarBodyActivityD1);
        } else {
            disableApp(context, HzbhdComponentName.CarBodyActivityD1);
        }
        if (i == 439) {
            enableApp(context, HzbhdComponentName.ID439MainActivity);
            enableApp(context, HzbhdComponentName.ID439PanelActivity);
        } else {
            disableApp(context, HzbhdComponentName.ID439MainActivity);
            disableApp(context, HzbhdComponentName.ID439PanelActivity);
        }
        if (i == 467) {
            enableApp(context, HzbhdComponentName.ID439MainActivity1280x720);
            enableApp(context, HzbhdComponentName.ID439PanelActivity1280x720);
        } else {
            disableApp(context, HzbhdComponentName.ID439MainActivity1280x720);
            disableApp(context, HzbhdComponentName.ID439PanelActivity1280x720);
        }
        if (i == 324 && CanbusConfig.INSTANCE.getDifferentId() == 1) {
            enableApp(context, HzbhdComponentName.Id324AuxVideoActivity);
        } else {
            disableApp(context, HzbhdComponentName.Id324AuxVideoActivity);
        }
        if (i == 466) {
            enableApp(context, HzbhdComponentName.SettingsActivity466);
        } else {
            disableApp(context, HzbhdComponentName.SettingsActivity466);
        }
        if (i == 471) {
            enableApp(context, HzbhdComponentName.CarBodyActivityE1);
        } else {
            disableApp(context, HzbhdComponentName.CarBodyActivityE1);
        }
        if (i == 473) {
            enableApp(context, HzbhdComponentName.CarBodyActivityA3);
        } else {
            disableApp(context, HzbhdComponentName.CarBodyActivityA3);
        }
    }

    public static void isUseNewApp(Context context, int i) {
        if (i == 1) {
            SelectCanTypeUtil.disableApp(context, HzbhdComponentName.CanbusCarInfoMainActivity);
        }
    }

    public static void isShowApp(Context context, int i) {
        if (i == 1) {
            SelectCanTypeUtil.enableApp(context, HzbhdComponentName.NewCanBusMainActivity);
        } else {
            SelectCanTypeUtil.disableApp(context, HzbhdComponentName.NewCanBusMainActivity);
        }
    }

    public static void disableAux2Activity(Context context) {
        SelectCanTypeUtil.disableApp(context, HzbhdComponentName.NewCanBusOriginalCarDeviceActivity);
        SelectCanTypeUtil.disableApp(context, HzbhdComponentName.NewCanbusOnStarActivity);
        SelectCanTypeUtil.disableApp(context, HzbhdComponentName.NewCanbusSyncActivity);
    }

    public static void enableApp(Context context, ComponentName componentName) {
        SelectCanTypeUtil.enableApp(context, componentName);
    }

    public static void disableApp(Context context, ComponentName componentName) {
        SelectCanTypeUtil.disableApp(context, componentName);
    }
}
