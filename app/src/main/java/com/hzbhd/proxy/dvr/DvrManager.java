package com.hzbhd.proxy.dvr;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.hzbhd.proxy.aidl.IDvrAidlInterface;
import com.hzbhd.proxy.callback.IDvrDataCallBack;
import com.hzbhd.proxy.service.ServiceConnection;
import com.hzbhd.util.LogUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DvrManager.kt */
@Metadata(d1 = {"\u00009\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002*\u0001\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/hzbhd/proxy/dvr/DvrManager;", "", "()V", "SERVICE_NAME_DVR", "", "TAG", "dvrService", "Lcom/hzbhd/proxy/aidl/IDvrAidlInterface;", "getDvrService", "()Lcom/hzbhd/proxy/aidl/IDvrAidlInterface;", "mServiceConnection", "com/hzbhd/proxy/dvr/DvrManager$mServiceConnection$1", "Lcom/hzbhd/proxy/dvr/DvrManager$mServiceConnection$1;", "registerOnDataReadCallback", "", "callback", "Lcom/hzbhd/proxy/callback/IDvrDataCallBack;", "sendData", "bytes", "", "unregisterOnDataReadCallback", "dvr-proxy_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class DvrManager {
    public static final DvrManager INSTANCE = new DvrManager();
    public static final String SERVICE_NAME_DVR = "hzbhd_dvr";
    private static final String TAG = "DvrManager";
    private static IDvrAidlInterface dvrService;
    private static final DvrManager$mServiceConnection$1 mServiceConnection;

    private DvrManager() {
    }

    private final IDvrAidlInterface getDvrService() {
        if (dvrService == null) {
            Log.i(TAG, "getService: is null");
        }
        return dvrService;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.hzbhd.proxy.dvr.DvrManager$mServiceConnection$1] */
    static {
        ?? r0 = new ServiceConnection() { // from class: com.hzbhd.proxy.dvr.DvrManager$mServiceConnection$1
            @Override // com.hzbhd.proxy.service.ServiceConnection
            public String getServiceName() {
                return DvrManager.SERVICE_NAME_DVR;
            }

            @Override // com.hzbhd.proxy.service.ServiceConnection
            public boolean getServiceConnection() {
                IBinder iBinderConnectService = connectService();
                if (iBinderConnectService == null) {
                    DvrManager dvrManager = DvrManager.INSTANCE;
                    DvrManager.dvrService = null;
                    if (LogUtil.log5()) {
                        LogUtil.d("getServiceConnection not connect");
                    }
                    return false;
                }
                DvrManager dvrManager2 = DvrManager.INSTANCE;
                DvrManager.dvrService = IDvrAidlInterface.Stub.asInterface(iBinderConnectService);
                return true;
            }
        };
        mServiceConnection = r0;
        r0.getServiceConnection();
    }

    public final void registerOnDataReadCallback(IDvrDataCallBack callback) throws RemoteException {
        Intrinsics.checkNotNullParameter(callback, "callback");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            dvrService2.registerOnDataReadCallback(callback);
        }
    }

    public final void unregisterOnDataReadCallback(IDvrDataCallBack callback) throws RemoteException {
        Intrinsics.checkNotNullParameter(callback, "callback");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            dvrService2.unregisterOnDataReadCallback(callback);
        }
    }

    public final void sendData(byte[] bytes) throws RemoteException {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            dvrService2.sendData(bytes);
        }
    }
}
