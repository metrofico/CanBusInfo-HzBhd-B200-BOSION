package com.hzbhd.setting.proxy;

import android.os.RemoteException;

import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.setting.proxy.aidl.IModuleCallBack;
import com.hzbhd.setting.proxy.aidl.ISettingServiceManager;

import java.util.HashMap;

/* loaded from: classes2.dex */
public class ModuleManager {
    private static final String TAG = "ModuleManager";
    private static ModuleManager mModuleManager;
    private final HashMap<Integer, IModuleListener> mModuleListeners = new HashMap<>();
    private final HashMap<Integer, IModuleCallBack> mIModuleCallBacks = new HashMap<>();

    public static synchronized ModuleManager getInstance() {
        synchronized (ModuleManager.class) {
            if (mModuleManager == null) {
                mModuleManager = new ModuleManager();
            }
        }
        return mModuleManager;
    }

    public void registerModuleListener(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, IModuleListener iModuleListener) {
        int key = SettingServiceManager.getKey(module_id, typeDef);
        this.mModuleListeners.put(Integer.valueOf(key), iModuleListener);
        if (this.mIModuleCallBacks.containsKey(Integer.valueOf(key))) {
            return;
        }
        this.mIModuleCallBacks.put(Integer.valueOf(key), new MyIModuleCallBack(key));
        ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
        if (iSettingServiceManager != null) {
            try {
                iSettingServiceManager.registerModuleListener(key, this.mIModuleCallBacks.get(Integer.valueOf(key)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterModuleListener(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, IModuleListener iModuleListener) {
        int key = SettingServiceManager.getKey(module_id, typeDef);
        if (this.mModuleListeners.containsKey(Integer.valueOf(key))) {
            this.mModuleListeners.remove(Integer.valueOf(key));
            ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
            if (iSettingServiceManager != null) {
                try {
                    iSettingServiceManager.unregisterModuleListener(key, this.mIModuleCallBacks.get(Integer.valueOf(key)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.mIModuleCallBacks.remove(Integer.valueOf(key));
        }
    }

    protected void resetModuleCallbacks() {
        if (this.mIModuleCallBacks.isEmpty()) {
            return;
        }
        for (Integer num : this.mIModuleCallBacks.keySet()) {
            ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
            if (iSettingServiceManager != null) {
                try {
                    iSettingServiceManager.registerModuleListener(num.intValue(), this.mIModuleCallBacks.get(num));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyIModuleCallBack extends IModuleCallBack.Stub {
        private final int mKey;

        private MyIModuleCallBack(int i) {
            this.mKey = i;
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleInt(int i, int i2, int i3) throws RemoteException {
            if (ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null) {
                return;
            }
            ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).onReceived(i, i2, Integer.valueOf(i3));
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleBytes(int i, int i2, byte[] bArr) throws RemoteException {
            if (ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null) {
                return;
            }
            ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).onReceived(i, i2, bArr);
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public void onModuleString(int i, int i2, String str) throws RemoteException {
            if (ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null) {
                return;
            }
            ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).onReceived(i, i2, str);
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public int getModuleInt(int i, int i2) throws RemoteException {
            if (ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null) {
                return -1;
            }
            return ((Integer) ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).getData(i, i2)).intValue();
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public byte[] getModuleBytes(int i, int i2) throws RemoteException {
            return ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null ? new byte[0] : (byte[]) ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).getData(i, i2);
        }

        @Override // com.hzbhd.setting.proxy.aidl.IModuleCallBack
        public String getModuleString(int i, int i2) throws RemoteException {
            if (ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)) == null) {
                return null;
            }
            return (String) ModuleManager.this.mModuleListeners.get(Integer.valueOf(this.mKey)).getData(i, i2);
        }
    }

    public void notifyInt(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, int i3) throws RemoteException {
        ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
        if (iSettingServiceManager == null) {
            return;
        }
        iSettingServiceManager.notifyInt(SettingServiceManager.getKey(module_id, typeDef), i, i2, i3);
    }

    public void notifyBytes(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, byte[] bArr) throws RemoteException {
        ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
        if (iSettingServiceManager == null) {
            return;
        }
        iSettingServiceManager.notifyBytes(SettingServiceManager.getKey(module_id, typeDef), i, i2, bArr);
    }

    public void notifyString(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, String str) throws RemoteException {
        ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
        if (iSettingServiceManager == null) {
            return;
        }
        iSettingServiceManager.notifyString(SettingServiceManager.getKey(module_id, typeDef), i, i2, str);
    }
}
