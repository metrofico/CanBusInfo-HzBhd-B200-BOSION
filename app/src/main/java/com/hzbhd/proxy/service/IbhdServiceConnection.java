package com.hzbhd.proxy.service;

import android.content.Context;
import android.os.IBinder;

/* loaded from: classes2.dex */
public interface IbhdServiceConnection {
    void bindService();

    void connectServiceUseServiceManager();

    IBinder getBinder();

    String getServiceAction();

    String getServiceName();

    String getServicePkgName();

    boolean isAlive();

    boolean isConnected();

    boolean registerServiceConnectionListener(IbhdServiceConnectionListener ibhdServiceConnectionListener);

    void unbindService();

    void unbindService(Context context);

    boolean unregisterServiceConnectionListener(IbhdServiceConnectionListener ibhdServiceConnectionListener);
}
