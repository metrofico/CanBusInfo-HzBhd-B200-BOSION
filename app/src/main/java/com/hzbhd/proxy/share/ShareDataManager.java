package com.hzbhd.proxy.share;

import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import com.hzbhd.commontools.AppIdDef;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.constant.share.SharedModule;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import com.hzbhd.proxy.share.aidl.IShareDataService;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;
import com.hzbhd.proxy.share.interfaces.IShareBundleListener;
import com.hzbhd.proxy.share.interfaces.IShareIntListener;
import com.hzbhd.proxy.share.interfaces.IShareStringListener;
import com.hzbhd.systemstatus.proxy.IServiceConnectListener;
import com.hzbhd.systemstatus.proxy.ServiceStateManager;
import com.hzbhd.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class ShareDataManager {
    private static final String TAG = "ShareDataManager";
    private static ShareDataManager mThis;
    private HashMap<String, ArrayList<IShareIntListener>> mShareIntListeners = new HashMap<>();
    private HashMap<String, ArrayList<IShareStringListener>> mShareStringListeners = new HashMap<>();
    private HashMap<String, ArrayList<IShareBundleListener>> mShareBundleListeners = new HashMap<>();
    private HashMap<String, IShareIntCallback> mIShareIntCallbacks = new HashMap<>();
    private HashMap<String, IShareStringCallback> mIShareStringCallbacks = new HashMap<>();
    private HashMap<String, IShareBundleCallback> mIShareBundleCallbacks = new HashMap<>();
    private HashMap<SourceConstantsDef.MODULE_ID, Boolean> mReConnCallbackRegister = new HashMap<>();

    public static synchronized ShareDataManager getInstance() {
        synchronized (ShareDataManager.class) {
            if (mThis == null) {
                mThis = new ShareDataManager();
            }
        }
        return mThis;
        return mThis;
    }

    private IShareDataService getIShareDataService(SourceConstantsDef.MODULE_ID module_id) {
        try {
            return IShareDataService.Stub.asInterface(ServiceManager.getService("share_" + module_id));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private IShareDataService getIShareDataService(String str) {
        return getIShareDataService(getModuleId(str));
    }

    private void checkResetCallback(SourceConstantsDef.MODULE_ID module_id) {
        synchronized (this.mReConnCallbackRegister) {
            if (!this.mReConnCallbackRegister.containsKey(module_id)) {
                this.mReConnCallbackRegister.put(module_id, true);
                ServiceStateManager.getInstance().registerConnectListener(module_id.getValue(), new MyIServiceConnectListener(module_id));
            }
        }
    }

    private class MyIServiceConnectListener implements IServiceConnectListener {
        private SourceConstantsDef.MODULE_ID mModuleId;

        MyIServiceConnectListener(SourceConstantsDef.MODULE_ID module_id) {
            this.mModuleId = module_id;
        }

        @Override // com.hzbhd.systemstatus.proxy.IServiceConnectListener
        public void onConnected() {
            ShareDataManager.this.resetCallbacks(this.mModuleId);
        }
    }

    public int registerShareIntListener(String str, IShareIntListener iShareIntListener) {
        synchronized (this.mShareIntListeners) {
            if (this.mShareIntListeners.get(str) == null) {
                this.mShareIntListeners.put(str, new ArrayList<>());
            }
            this.mShareIntListeners.get(str).add(iShareIntListener);
        }
        IShareIntCallback iShareIntCallback = getIShareIntCallback(str);
        SourceConstantsDef.MODULE_ID moduleId = getModuleId(str);
        IShareDataService iShareDataService = getIShareDataService(moduleId);
        checkResetCallback(moduleId);
        if (iShareDataService == null) {
            return -1;
        }
        try {
            return iShareDataService.registerShareIntCallback(str, iShareIntCallback);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void unregisterShareIntListener(String str, IShareIntListener iShareIntListener) {
        if (this.mShareIntListeners.get(str) != null && this.mShareIntListeners.get(str).contains(iShareIntListener)) {
            this.mShareIntListeners.get(str).remove(iShareIntListener);
            if (this.mShareIntListeners.get(str).isEmpty()) {
                this.mShareIntListeners.remove(str);
                IShareDataService iShareDataService = getIShareDataService(str);
                if (iShareDataService != null) {
                    try {
                        iShareDataService.unregisterShareIntCallback(str, this.mIShareIntCallbacks.get(str));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.mIShareIntCallbacks.remove(str);
            }
        }
    }

    public int getInt(String str) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService == null) {
            return -1;
        }
        try {
            return iShareDataService.getInt(str);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void reportInt(String str, int i) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportInt(str, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reportOtherInt(String str, int i, AppIdDef.APP_ID app_id) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportOtherInt(str, i, app_id.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MyIShareIntCallback extends IShareIntCallback.Stub {
        private String mKey;

        private MyIShareIntCallback(String str) {
            this.mKey = str;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareIntCallback
        public void onInt(int i, int i2) throws RemoteException {
            ArrayList arrayList = (ArrayList) ShareDataManager.this.mShareIntListeners.get(this.mKey);
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                try {
                    ((IShareIntListener) arrayList.get(i3)).onInt(i);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private IShareIntCallback getIShareIntCallback(String str) {
        if (!this.mIShareIntCallbacks.containsKey(str)) {
            this.mIShareIntCallbacks.put(str, new MyIShareIntCallback(str));
        }
        return this.mIShareIntCallbacks.get(str);
    }

    public String registerShareStringListener(String str, IShareStringListener iShareStringListener) {
        synchronized (this.mShareStringListeners) {
            if (this.mShareStringListeners.get(str) == null) {
                this.mShareStringListeners.put(str, new ArrayList<>());
            }
            this.mShareStringListeners.get(str).add(iShareStringListener);
        }
        IShareStringCallback iShareStringCallback = getIShareStringCallback(str);
        SourceConstantsDef.MODULE_ID moduleId = getModuleId(str);
        IShareDataService iShareDataService = getIShareDataService(moduleId);
        checkResetCallback(moduleId);
        if (iShareDataService == null) {
            return null;
        }
        try {
            return iShareDataService.registerShareStringCallback(str, iShareStringCallback);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void unregisterShareStringListener(String str, IShareStringListener iShareStringListener) {
        if (this.mShareStringListeners.get(str) != null && this.mShareStringListeners.get(str).contains(iShareStringListener)) {
            this.mShareStringListeners.get(str).remove(iShareStringListener);
            if (this.mShareStringListeners.get(str).isEmpty()) {
                this.mShareStringListeners.remove(str);
                IShareDataService iShareDataService = getIShareDataService(str);
                if (iShareDataService != null) {
                    try {
                        iShareDataService.unregisterShareStringCallback(str, this.mIShareStringCallbacks.get(str));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.mIShareStringCallbacks.remove(str);
            }
        }
    }

    public String getString(String str) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService == null) {
            return null;
        }
        try {
            return iShareDataService.getString(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void reportString(String str, String str2) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportString(str, str2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reportOtherString(String str, String str2, AppIdDef.APP_ID app_id) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportOtherString(str, str2, app_id.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MyIShareStringCallback extends IShareStringCallback.Stub {
        private String mKey;

        private MyIShareStringCallback(String str) {
            this.mKey = str;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareStringCallback
        public void onString(String str, int i) throws RemoteException {
            ArrayList arrayList = (ArrayList) ShareDataManager.this.mShareStringListeners.get(this.mKey);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                try {
                    ((IShareStringListener) arrayList.get(i2)).onString(str);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private IShareStringCallback getIShareStringCallback(String str) {
        if (!this.mIShareStringCallbacks.containsKey(str)) {
            this.mIShareStringCallbacks.put(str, new MyIShareStringCallback(str));
        }
        return this.mIShareStringCallbacks.get(str);
    }

    public Bundle registerShareBundleListener(String str, IShareBundleListener iShareBundleListener) {
        synchronized (this.mShareBundleListeners) {
            if (this.mShareBundleListeners.get(str) == null) {
                this.mShareBundleListeners.put(str, new ArrayList<>());
            }
            this.mShareBundleListeners.get(str).add(iShareBundleListener);
        }
        IShareBundleCallback iShareBundleCallback = getIShareBundleCallback(str);
        SourceConstantsDef.MODULE_ID moduleId = getModuleId(str);
        IShareDataService iShareDataService = getIShareDataService(moduleId);
        checkResetCallback(moduleId);
        if (iShareDataService == null) {
            return null;
        }
        try {
            return iShareDataService.registerShareBundleCallback(str, iShareBundleCallback);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void unregisterShareBundleListener(String str, IShareBundleListener iShareBundleListener) {
        if (this.mShareBundleListeners.get(str) != null && this.mShareBundleListeners.get(str).contains(iShareBundleListener)) {
            this.mShareBundleListeners.get(str).remove(iShareBundleListener);
            if (this.mShareBundleListeners.get(str).isEmpty()) {
                this.mShareBundleListeners.remove(str);
                IShareDataService iShareDataService = getIShareDataService(str);
                if (iShareDataService != null) {
                    try {
                        iShareDataService.unregisterShareBundleCallback(str, this.mIShareBundleCallbacks.get(str));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.mIShareBundleCallbacks.remove(str);
            }
        }
    }

    public Bundle getBundle(String str) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService == null) {
            return null;
        }
        try {
            return iShareDataService.getBundle(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void reportBundle(String str, Bundle bundle) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportBundle(str, bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reportOtherBundle(String str, Bundle bundle, AppIdDef.APP_ID app_id) {
        IShareDataService iShareDataService = getIShareDataService(str);
        if (iShareDataService != null) {
            try {
                iShareDataService.reportOtherBundle(str, bundle, app_id.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class MyIShareBundleCallback extends IShareBundleCallback.Stub {
        private String mKey;

        private MyIShareBundleCallback(String str) {
            this.mKey = str;
        }

        @Override // com.hzbhd.proxy.share.aidl.IShareBundleCallback
        public void onBundle(Bundle bundle, int i) throws RemoteException {
            ArrayList arrayList = (ArrayList) ShareDataManager.this.mShareBundleListeners.get(this.mKey);
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                try {
                    ((IShareBundleListener) arrayList.get(i2)).onBundle(bundle);
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private IShareBundleCallback getIShareBundleCallback(String str) {
        if (!this.mIShareBundleCallbacks.containsKey(str)) {
            this.mIShareBundleCallbacks.put(str, new MyIShareBundleCallback(str));
        }
        return this.mIShareBundleCallbacks.get(str);
    }

    public void resetCallbacks(SourceConstantsDef.MODULE_ID module_id) {
        IShareDataService iShareDataService = getIShareDataService(module_id);
        if (LogUtil.log5()) {
            LogUtil.d("resetCallbacks: " + module_id + ":" + iShareDataService);
        }
        if (iShareDataService == null) {
            return;
        }
        for (String str : this.mIShareIntCallbacks.keySet()) {
            try {
                if (getModuleId(str) == module_id) {
                    getIShareIntCallback(str).onInt(iShareDataService.registerShareIntCallback(str, getIShareIntCallback(str)), -1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (String str2 : this.mIShareStringCallbacks.keySet()) {
            try {
                if (getModuleId(str2) == module_id) {
                    getIShareStringCallback(str2).onString(iShareDataService.registerShareStringCallback(str2, getIShareStringCallback(str2)), -1);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        for (String str3 : this.mIShareBundleCallbacks.keySet()) {
            try {
                if (getModuleId(str3) == module_id) {
                    getIShareBundleCallback(str3).onBundle(iShareDataService.registerShareBundleCallback(str3, getIShareBundleCallback(str3)), -1);
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    private SourceConstantsDef.MODULE_ID getModuleId(String str) {
        return SourceConstantsDef.MODULE_ID.getType(SharedModule.INSTANCE.getShared().getOrDefault(str, 0).intValue());
    }
}
