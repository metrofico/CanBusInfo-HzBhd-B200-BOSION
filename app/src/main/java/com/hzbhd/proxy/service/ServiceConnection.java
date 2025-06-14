package com.hzbhd.proxy.service;

import android.os.IBinder;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class ServiceConnection {
    private static final String TAG = "ServiceOperation";
    private ServiceConnectionManager mConnectionManager;
    protected ServiceConnection mServiceConnection = null;
    protected Boolean mIsConnected = true;
    private List<ServiceConnectListener> mServiceConnectListeners = new ArrayList();

    public boolean getServiceConnection() {
        return false;
    }

    public String getServiceName() {
        return null;
    }

    public ServiceConnection() {
        this.mConnectionManager = null;
        ServiceConnectionManager serviceConnectionManager = ServiceConnectionManager.getServiceConnectionManager();
        this.mConnectionManager = serviceConnectionManager;
        serviceConnectionManager.registerConnectionListener(getServiceName(), this);
    }

    public class BinderDeathRecipient implements IBinder.DeathRecipient {
        public BinderDeathRecipient() {
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.e(ServiceConnection.TAG, "ServiceConnection BinderDeathRecipient: binderDied");
            ServiceConnection.this.mIsConnected = false;
            ServiceConnection.this.serviceDied();
            ServiceConnection.this.mConnectionManager.onServiceDisconnected(ServiceConnection.this.getServiceName());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public android.os.IBinder connectService() throws android.os.RemoteException {
        /*
            r9 = this;
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            r1 = 0
            r2 = 1
            r3 = 0
            java.lang.String r4 = "android.os.ServiceManager"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch: java.lang.Exception -> L4e
            java.lang.String r5 = "getService"
            java.lang.Class[] r6 = new java.lang.Class[r2]     // Catch: java.lang.Exception -> L4e
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7     // Catch: java.lang.Exception -> L4e
            java.lang.reflect.Method r4 = r4.getMethod(r5, r6)     // Catch: java.lang.Exception -> L4e
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch: java.lang.Exception -> L4e
            java.lang.String r6 = r9.getServiceName()     // Catch: java.lang.Exception -> L4e
            r5[r1] = r6     // Catch: java.lang.Exception -> L4e
            java.lang.Object r0 = r4.invoke(r0, r5)     // Catch: java.lang.Exception -> L4e
            android.os.IBinder r0 = (android.os.IBinder) r0     // Catch: java.lang.Exception -> L4e
            if (r0 == 0) goto L3c
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Exception -> L4c
            r9.mIsConnected = r3     // Catch: java.lang.Exception -> L4c
            com.hzbhd.proxy.service.ServiceConnection$BinderDeathRecipient r3 = new com.hzbhd.proxy.service.ServiceConnection$BinderDeathRecipient     // Catch: java.lang.Exception -> L4c
            r3.<init>()     // Catch: java.lang.Exception -> L4c
            r0.linkToDeath(r3, r1)     // Catch: java.lang.Exception -> L4c
            r9.serviceConnected(r2)     // Catch: java.lang.Exception -> L4c
            goto L55
        L3c:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Exception -> L4c
            r9.mIsConnected = r3     // Catch: java.lang.Exception -> L4c
            com.hzbhd.proxy.service.ServiceConnectionManager r3 = r9.mConnectionManager     // Catch: java.lang.Exception -> L4c
            java.lang.String r4 = r9.getServiceName()     // Catch: java.lang.Exception -> L4c
            r3.onServiceDisconnected(r4)     // Catch: java.lang.Exception -> L4c
            goto L55
        L4c:
            r3 = move-exception
            goto L52
        L4e:
            r0 = move-exception
            r8 = r3
            r3 = r0
            r0 = r8
        L52:
            r3.printStackTrace()
        L55:
            if (r0 == 0) goto L5e
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r2)
            r9.mIsConnected = r1
            goto L64
        L5e:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            r9.mIsConnected = r1
        L64:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hzbhd.proxy.service.ServiceConnection.connectService():android.os.IBinder");
    }

    public boolean isServiceConnected() {
        return this.mIsConnected.booleanValue();
    }

    public void serviceReConnected() {
        Log.d(TAG, "serviceReConnected");
        serviceConnected(true);
    }

    public void serviceConnected(boolean z) {
        Log.d(TAG, "serviceConnected,flag=" + z);
        if (this.mServiceConnectListeners != null) {
            Log.d(TAG, "serviceReConnected,mServiceConnectListeners size=" + this.mServiceConnectListeners.size());
            Iterator<ServiceConnectListener> it = this.mServiceConnectListeners.iterator();
            while (it.hasNext()) {
                it.next().onServiceConnectStateChanged(z);
            }
        }
    }

    public void serviceDied() {
        try {
            Log.d(TAG, "serviceDied");
            serviceConnected(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerServiceConnectListener(ServiceConnectListener serviceConnectListener) {
        synchronized (this.mServiceConnectListeners) {
            if (!this.mServiceConnectListeners.contains(serviceConnectListener)) {
                this.mServiceConnectListeners.add(serviceConnectListener);
            }
        }
    }

    public void unRegisterServiceConnectListener(ServiceConnectListener serviceConnectListener) {
        synchronized (this.mServiceConnectListeners) {
            if (this.mServiceConnectListeners.contains(serviceConnectListener)) {
                this.mServiceConnectListeners.remove(serviceConnectListener);
            }
        }
    }
}
