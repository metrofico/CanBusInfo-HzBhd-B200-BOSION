package com.hzbhd.systemstatus.proxy;

import com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager;
import com.hzbhd.util.ServiceManagerReflection;

/* loaded from: classes2.dex */
public class SystemStatusManager {
    public static final String SERVICE_NAME_SYSTEM_STATUS = "hzbhd_SystemStatusService";
    private static final String TAG = "SystemStatusManager";

    protected static ISystemStatusManager getISystemStatusManager() {
        return ISystemStatusManager.Stub.asInterface(ServiceManagerReflection.getService(SERVICE_NAME_SYSTEM_STATUS));
    }
}
