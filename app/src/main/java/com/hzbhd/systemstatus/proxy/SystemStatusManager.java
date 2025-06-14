package com.hzbhd.systemstatus.proxy;

import android.os.ServiceManager;
import com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager;

/* loaded from: classes2.dex */
public class SystemStatusManager {
    public static final String SERVICE_NAME_SYSTEM_STATUS = "hzbhd_SystemStatusService";
    private static final String TAG = "SystemStatusManager";

    protected static ISystemStatusManager getISystemStatusManager() {
        return ISystemStatusManager.Stub.asInterface(ServiceManager.getService(SERVICE_NAME_SYSTEM_STATUS));
    }
}
