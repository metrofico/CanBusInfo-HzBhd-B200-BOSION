package com.hzbhd.proxy.service;

import android.util.Log;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes2.dex */
public class ServiceConnectionManager {
    private static final String TAG = "ServiceConnectionManager";
    static ServiceConnectionManager mServiceManager;
    private Thread mWorkThread = new Thread(null, new MonitorRunnable(), "ServiceConnectionManager thread");
    private ReentrantLock mLock = new ReentrantLock();
    private HashMap<String, ServiceConnection> mServices = new HashMap<>();
    private Vector<ServiceConnection> mServiceNeedConnected = new Vector<>();

    public static ServiceConnectionManager getServiceConnectionManager() {
        if (mServiceManager == null) {
            mServiceManager = new ServiceConnectionManager();
        }
        return mServiceManager;
    }

    private ServiceConnectionManager() {
        this.mWorkThread.start();
    }

    public void registerConnectionListener(String str, ServiceConnection serviceConnection) {
        this.mLock.lock();
        if (!this.mServices.containsKey(str)) {
            this.mServices.put(str, serviceConnection);
        }
        this.mLock.unlock();
    }

    public void onServiceDisconnected(String str) {
        this.mLock.lock();
        ServiceConnection serviceConnection = this.mServices.get(str);
        if (serviceConnection != null && !this.mServiceNeedConnected.contains(serviceConnection)) {
            this.mServiceNeedConnected.add(serviceConnection);
        }
        this.mLock.unlock();
    }

    public boolean connectService() {
        this.mLock.lock();
        Iterator<ServiceConnection> it = this.mServiceNeedConnected.iterator();
        while (it.hasNext()) {
            ServiceConnection next = it.next();
            if (!next.getServiceConnection()) {
                Log.w(TAG, "doBindService in back thread fail: " + next.getServiceName());
            } else {
                Log.i(TAG, "doBindService in back thread success: " + next.getServiceName());
                next.serviceReConnected();
            }
        }
        this.mLock.unlock();
        return false;
    }

    public void checkService() {
        this.mLock.lock();
        Vector<ServiceConnection> vector = new Vector<>();
        Iterator<ServiceConnection> it = this.mServiceNeedConnected.iterator();
        while (it.hasNext()) {
            ServiceConnection next = it.next();
            if (!next.isServiceConnected()) {
                vector.add(next);
                Log.i(TAG, "checkService in back thread add: " + next.getServiceName());
            } else {
                Log.i(TAG, "checkService in back thread del: " + next.getServiceName());
            }
        }
        this.mServiceNeedConnected = vector;
        this.mLock.unlock();
    }

    private class MonitorRunnable implements Runnable {
        private MonitorRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() throws InterruptedException {
            while (true) {
                try {
                    if (ServiceConnectionManager.this.mServiceNeedConnected.isEmpty()) {
                        Thread.sleep(2000L);
                    } else {
                        ServiceConnectionManager.this.connectService();
                        Thread.sleep(1000L);
                        ServiceConnectionManager.this.checkService();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
