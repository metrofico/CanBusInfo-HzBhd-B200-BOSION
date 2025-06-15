package com.hzbhd.proxy.keydispatcher;


import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.keydispatcher.aidl.IKeyDispatcherService;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.ServiceManagerReflection;

/* loaded from: classes2.dex */
public class KeyDispatcherManager {
    public static final String SERVICE_NAME_KEY_DISPATCHER = "hzbhd_KeyDispatcher";
    private static KeyDispatcherManager mKeyDispatcherManager;

    protected static IKeyDispatcherService getIKeyDispatcherService() {
        if (mKeyDispatcherManager == null) {
            mKeyDispatcherManager = new KeyDispatcherManager();
            ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.KEY_DISPATCHER.getValue(), new IServiceConnectListener() { // from class: com.hzbhd.proxy.keydispatcher.KeyDispatcherManager.1
                @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
                public void onConnected() {
                    ReceiveKeyManager.getInstance().resetIKeyDispatcherCallback();
                }
            });
        }
        return IKeyDispatcherService.Stub.asInterface(ServiceManagerReflection.getService(SERVICE_NAME_KEY_DISPATCHER));
    }
}
