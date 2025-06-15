package com.hzbhd.proxy.dvr;

import android.os.IBinder;
import android.util.Log;

import com.hzbhd.proxy.aidl.IDvrAidlInterface;
import com.hzbhd.proxy.callback.IDvrDataCallBack;
import com.hzbhd.proxy.service.ServiceConnection;
import com.hzbhd.util.LogUtil;

import kotlin.jvm.internal.Intrinsics;

public final class DvrManager {
    public static final DvrManager INSTANCE = new DvrManager();
    public static final String SERVICE_NAME_DVR = "hzbhd_dvr";
    private static final String TAG = "DvrManager";
    private static IDvrAidlInterface dvrService;
    private static final ServiceConnection mServiceConnection = new ServiceConnection() { // from class: com.hzbhd.proxy.dvr.DvrManager$mServiceConnection$1
        @Override // com.hzbhd.proxy.service.ServiceConnection
        public String getServiceName() {
            return DvrManager.SERVICE_NAME_DVR;
        }

        @Override // com.hzbhd.proxy.service.ServiceConnection
        public boolean getServiceConnection() {
            IBinder connectService = connectService();
            if (connectService == null) {
                DvrManager.dvrService = null;
                if (LogUtil.log5()) {
                    LogUtil.d("getServiceConnection not connect");
                }
                return false;
            }
            DvrManager.dvrService = IDvrAidlInterface.Stub.asInterface(connectService);
            return true;
        }
    };

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
        mServiceConnection.getServiceConnection();
    }

    public final void registerOnDataReadCallback(IDvrDataCallBack callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            try {
                dvrService2.registerOnDataReadCallback(callback);
            } catch (Throwable w) {
                w.printStackTrace();
            }
        }
    }

    public final void unregisterOnDataReadCallback(IDvrDataCallBack callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            try {
                dvrService2.unregisterOnDataReadCallback(callback);
            } catch (Throwable w) {
                w.printStackTrace();
            }
        }
    }

    public final void sendData(byte[] bytes) {
        Intrinsics.checkNotNullParameter(bytes, "bytes");
        IDvrAidlInterface dvrService2 = getDvrService();
        if (dvrService2 != null) {
            try {
                dvrService2.sendData(bytes);
            } catch (Throwable w) {
                w.printStackTrace();
            }
        }
    }
}