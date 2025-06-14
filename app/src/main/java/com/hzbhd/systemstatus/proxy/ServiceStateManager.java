package com.hzbhd.systemstatus.proxy;

import com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback;
import com.hzbhd.systemstatus.proxy.aidl.ISystemStatusManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes2.dex */
public class ServiceStateManager {
    private static ServiceStateManager mServiceStateManager;
    private HashMap<Integer, List<IServiceConnectListener>> mServiceConnectListenerList = new HashMap<>();
    private HashMap<Integer, IServiceConnectCallback> mServiceConnectCallbacks = new HashMap<>();

    public static synchronized ServiceStateManager getInstance() {
        ServiceStateManager serviceStateManager;
        synchronized (ServiceStateManager.class) {
            if (mServiceStateManager == null) {
                mServiceStateManager = new ServiceStateManager();
            }
            serviceStateManager = mServiceStateManager;
        }
        return serviceStateManager;
        return serviceStateManager;
    }

    public synchronized boolean registerConnectListener(int i, IServiceConnectListener iServiceConnectListener) {
        ISystemStatusManager iSystemStatusManager = SystemStatusManager.getISystemStatusManager();
        if (iSystemStatusManager == null) {
            return false;
        }
        if (!this.mServiceConnectListenerList.containsKey(Integer.valueOf(i))) {
            this.mServiceConnectListenerList.put(Integer.valueOf(i), new ArrayList());
        }
        this.mServiceConnectListenerList.get(Integer.valueOf(i)).add(iServiceConnectListener);
        if (this.mServiceConnectCallbacks.containsKey(Integer.valueOf(i))) {
            return true;
        }
        this.mServiceConnectCallbacks.put(Integer.valueOf(i), new MyIServiceConnectCallback(i));
        try {
            iSystemStatusManager.regitsterServiceConnectCallback(i, this.mServiceConnectCallbacks.get(Integer.valueOf(i)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean unregisterConnnectListener(int i, IServiceConnectListener iServiceConnectListener) {
        ISystemStatusManager iSystemStatusManager = SystemStatusManager.getISystemStatusManager();
        if (iSystemStatusManager == null) {
            return false;
        }
        if (!this.mServiceConnectListenerList.containsKey(Integer.valueOf(i)) || !this.mServiceConnectListenerList.get(Integer.valueOf(i)).contains(iServiceConnectListener)) {
            return true;
        }
        this.mServiceConnectListenerList.get(Integer.valueOf(i)).remove(iServiceConnectListener);
        if (this.mServiceConnectListenerList.get(Integer.valueOf(i)).isEmpty()) {
            this.mServiceConnectListenerList.remove(Integer.valueOf(i));
            try {
                iSystemStatusManager.unregisterServiceConnectCallback(i, this.mServiceConnectCallbacks.get(Integer.valueOf(i)));
                this.mServiceConnectCallbacks.remove(Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public void setServiceData(int i, int i2, String str) {
        ISystemStatusManager iSystemStatusManager = SystemStatusManager.getISystemStatusManager();
        if (iSystemStatusManager == null) {
            return;
        }
        try {
            iSystemStatusManager.setServiceData(i, i2, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getServiceData(int i, int i2) {
        ISystemStatusManager iSystemStatusManager = SystemStatusManager.getISystemStatusManager();
        if (iSystemStatusManager == null) {
            return null;
        }
        try {
            return iSystemStatusManager.getServiceData(i, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private class MyIServiceConnectCallback extends IServiceConnectCallback.Stub {
        private int mModuleId;

        public MyIServiceConnectCallback(int i) {
            this.mModuleId = i;
        }

        @Override // com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback
        public void onConnected() {
            if (ServiceStateManager.this.mServiceConnectListenerList.containsKey(Integer.valueOf(this.mModuleId))) {
                List list = (List) ServiceStateManager.this.mServiceConnectListenerList.get(Integer.valueOf(this.mModuleId));
                for (int i = 0; i < list.size(); i++) {
                    ((IServiceConnectListener) list.get(i)).onConnected();
                }
            }
        }
    }
}
