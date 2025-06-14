package com.hzbhd.proxy.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/* loaded from: classes2.dex */
public abstract class bhdNewServiceConnection implements IbhdServiceConnection, android.content.ServiceConnection {
    private static final String TAG = "bhdNewServiceConnection";
    private IBinder mBinder;
    private Context mContext;
    private IbhdServiceConnectionListener mListener;
    private boolean isConnected = false;
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() { // from class: com.hzbhd.proxy.service.bhdNewServiceConnection.1
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            Log.i(bhdNewServiceConnection.TAG, "bhdNewServiceConnection,DeathRecipient,binderDied");
            bhdNewServiceConnection.this.isConnected = false;
            bhdNewServiceConnection bhdnewserviceconnection = bhdNewServiceConnection.this;
            bhdnewserviceconnection.onServiceConnectionChanged(bhdnewserviceconnection.mBinder, false);
            bhdNewServiceConnection.this.asyncNotifyToListenerServiceConnectionChanged(false);
        }
    };
    private String mServiceName = getServiceName();
    private String mAction = getServiceAction();
    private String mPackageName = getServicePkgName();

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public String getServiceName() {
        return null;
    }

    public abstract void onServiceConnectionChanged(IBinder iBinder, boolean z);

    public bhdNewServiceConnection() {
    }

    public bhdNewServiceConnection(Context context) {
        this.mContext = context;
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public boolean isAlive() {
        IBinder iBinder;
        Log.i(TAG, "isAlive: isConnected=" + this.isConnected + ",mBinder=" + this.mBinder);
        return this.isConnected && (iBinder = this.mBinder) != null && iBinder.isBinderAlive();
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public void bindService() {
        bindService(this.mContext);
    }

    public void bindService(Context context) {
        Log.i(TAG, "bindService");
        bindService(context, new Intent());
    }

    public void bindService(String str) {
        bindService(this.mContext, str);
    }

    public void bindService(Context context, String str) {
        Log.i(TAG, "bindService,serviceName=" + str);
        Intent intent = new Intent();
        intent.putExtra(ServiceConstants.SERVICE_NAME_TAG, str);
        bindService(context, intent);
    }

    public void bindService(Context context, Intent intent) {
        String str = TAG;
        Log.i(str, "bindService with intent");
        if (context == null) {
            Log.e(str, "bindService with intent,mContext is null!");
        }
        if (!isAlive()) {
            Log.i(str, "bindService with intent,!isAlive");
            if (intent == null) {
                intent = new Intent();
            }
            Log.i(str, "bindService,mAction=" + this.mAction + " mPackageName=" + this.mPackageName);
            intent.setAction(this.mAction);
            intent.setPackage(this.mPackageName);
            context.bindService(intent, this, 1);
            return;
        }
        Log.i(str, "bindService with intent,isAlive");
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public IBinder getBinder() {
        return this.mBinder;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws RemoteException {
        String str = TAG;
        Log.i(str, "onServiceConnected,ComponentName=" + componentName + " service=" + iBinder);
        this.isConnected = true;
        this.mBinder = iBinder;
        if (iBinder == null) {
            Log.e(str, "onServiceConnected, mBinder is null,Please invoke connectServiceUseServiceManager to connect Service!");
        }
        onServiceConnectionChanged(this.mBinder, true);
        asyncNotifyToListenerServiceConnectionChanged(true);
        if (iBinder != null) {
            try {
                iBinder.linkToDeath(this.deathRecipient, 0);
                return;
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.e(TAG, "onServiceConnected: ", e);
                return;
            }
        }
        Log.i(str, "onServiceConnected: service is null!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void asyncNotifyToListenerServiceConnectionChanged(final boolean z) {
        if (this.mListener != null) {
            Log.d(TAG, "asyncNotifyToListenerServiceConnectionChanged,state=" + z);
            new Thread(new Runnable() { // from class: com.hzbhd.proxy.service.bhdNewServiceConnection.2
                @Override // java.lang.Runnable
                public void run() {
                    Log.d(bhdNewServiceConnection.TAG, "asyncNotifyToListenerServiceConnectionChanged,thread run state=" + z);
                    Log.d(bhdNewServiceConnection.TAG, "run: asyncNotifyToListenerServiceConnectionChanged mIbhdServiceConnectionListener " + bhdNewServiceConnection.this.mListener);
                    bhdNewServiceConnection.this.mListener.onServiceConnectionChanged(z);
                }
            }).start();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        Log.i(TAG, "onServiceDisconnected,name=" + componentName);
        this.isConnected = false;
        this.mBinder = null;
        onServiceConnectionChanged(null, false);
        asyncNotifyToListenerServiceConnectionChanged(false);
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public boolean isConnected() {
        return this.isConnected;
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public boolean registerServiceConnectionListener(IbhdServiceConnectionListener ibhdServiceConnectionListener) {
        this.mListener = ibhdServiceConnectionListener;
        return true;
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public boolean unregisterServiceConnectionListener(IbhdServiceConnectionListener ibhdServiceConnectionListener) {
        if (this.mListener != ibhdServiceConnectionListener) {
            return false;
        }
        this.mListener = null;
        return true;
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public void connectServiceUseServiceManager() {
        Object obj = new Object();
        if (this.mServiceName != null) {
            try {
                IBinder iBinder = (IBinder) Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(obj, getServiceName());
                Log.i(TAG, "connectServiceUseServiceManager");
                if (iBinder != null) {
                    onServiceConnected(new ComponentName(getServicePkgName(), getServiceAction()), iBinder);
                } else {
                    onServiceDisconnected(new ComponentName(getServicePkgName(), getServiceAction()));
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        String str = TAG;
        Log.e(str, "connectServiceUseServiceManager,mServiceName is " + this.mServiceName + ",please override method getServiceName() first!");
        Log.e(str, "connectServiceUseServiceManager,getServiceName()=" + getServiceName() + " getServicePkgName=" + getServicePkgName() + " getServiceAction=" + getServiceAction());
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public void unbindService() {
        unbindService(this.mContext);
    }

    @Override // com.hzbhd.proxy.service.IbhdServiceConnection
    public void unbindService(Context context) {
        Log.d(TAG, "unbindService,isConnected=" + this.isConnected + " mPackageName=" + this.mPackageName + " mAction=" + this.mAction + " mServiceName=" + this.mServiceName);
        if (this.isConnected) {
            context.unbindService(this);
            this.isConnected = false;
            this.mBinder = null;
        }
    }
}
