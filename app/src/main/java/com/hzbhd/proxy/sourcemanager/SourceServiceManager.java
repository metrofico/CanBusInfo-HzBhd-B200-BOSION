package com.hzbhd.proxy.sourcemanager;

import android.os.ServiceManager;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.proxy.sourcemanager.aidl.ISourceService;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;

/* loaded from: classes2.dex */
public class SourceServiceManager {
    public static final String SERVICE_NAME_SOURCE_SERVICE = "hzbhd_SourceService";
    private static final String TAG = "SourceServiceManager";
    private static SourceServiceManager mSourceServiceManager;

    public static ISourceService getISourceService() {
        if (mSourceServiceManager == null) {
            mSourceServiceManager = new SourceServiceManager();
            ServiceStateManager.getInstance().registerConnectListener(SourceConstantsDef.MODULE_ID.SOURCE_MANAGER.ordinal(), new IServiceConnectListener() { // from class: com.hzbhd.proxy.sourcemanager.SourceServiceManager.1
                @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
                public void onConnected() {
                    SourceAudioDepend.getInstance().resetAudioCallback();
                    SourceManager.getInstance().resetSourceCallback();
                    SourceManager.getInstance().resetSourceBluetoothCallbacks();
                }
            });
        }
        return ISourceService.Stub.asInterface(ServiceManager.getService(SERVICE_NAME_SOURCE_SERVICE));
    }
}
