package com.hzbhd.setting.proxy;

import android.os.ServiceManager;
import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.setting.proxy.aidl.ISettingServiceManager;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;

/* loaded from: classes2.dex */
public class SettingServiceManager {
    public static final String SERVICE_NAME_SETTING_SERVICE = "hzbhd_SettingService";
    private static SettingServiceManager mSettingServiceManager;

    protected static ISettingServiceManager getISettingServiceManager() {
        if (mSettingServiceManager == null) {
            mSettingServiceManager = new SettingServiceManager();
            ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.SETTING.ordinal(), new IServiceConnectListener() { // from class: com.hzbhd.setting.proxy.SettingServiceManager.1
                @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
                public void onConnected() {
                    SettingsManager.getInstance().resetSettingsCallBacks();
                    ModuleManager.getInstance().resetModuleCallbacks();
                }
            });
        }
        return ISettingServiceManager.Stub.asInterface(ServiceManager.getService(SERVICE_NAME_SETTING_SERVICE));
    }

    protected static int getKey(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef) {
        return (module_id.getValue() << 8) + typeDef.ordinal();
    }
}
