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
        ServiceConnection sc;

        public BinderDeathRecipient(ServiceConnection sc) {
            this.sc = sc;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.e(ServiceConnection.TAG, "ServiceConnection BinderDeathRecipient: binderDied");
            this.sc.mIsConnected = false;
            this.sc.serviceDied();
            this.sc.mConnectionManager.onServiceDisconnected(ServiceConnection.this.getServiceName());
        }
    }

    public IBinder connectService() {
        IBinder binder = null;
        try {
            // Llamar al método getService de ServiceManager
            binder = (IBinder) Class.forName("android.os.ServiceManager")
                    .getMethod("getService", String.class)
                    .invoke(null, this.getServiceName());

            // Verificar si el binder es nulo
            if (binder != null) {
                mIsConnected = true;
                // Configurar la notificación cuando el servicio muera
                binder.linkToDeath(new BinderDeathRecipient(this), 0);
                serviceConnected(true); // Llamar cuando el servicio está conectado
            } else {
                mIsConnected = false;
                mConnectionManager.onServiceDisconnected(this.getServiceName());
            }
        } catch (Exception e) {
            Log.e(TAG, "Error al conectar al servicio", e);
            mIsConnected = false;
            mConnectionManager.onServiceDisconnected(this.getServiceName());
        }
        return binder;
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
