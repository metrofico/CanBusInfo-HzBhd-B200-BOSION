package com.hzbhd.setting.proxy;

import com.hzbhd.common.settings.constant.BodaSysContant;
import com.hzbhd.common.settings.constant.Configs;
import com.hzbhd.commontools.SourceConstantsDef;
import com.hzbhd.setting.proxy.aidl.ISettingServiceManager;
import com.hzbhd.setting.proxy.aidl.ISettingsCallBack;

import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class SettingsManager {
    private static final String TAG = "SettingsManager";
    private static SettingsManager mSettingsManager;
    private final HashMap<Integer, ArrayList<ISettingsListener>> mSettingsListenerLists = new HashMap<>();
    private final HashMap<Integer, ISettingsCallBack> mISettingsCallBacks = new HashMap<>();

    public static synchronized SettingsManager getInstance() {
        synchronized (SettingsManager.class) {
            if (mSettingsManager == null) {
                mSettingsManager = new SettingsManager();
            }
        }
        return mSettingsManager;
    }

    public void registerSettingsListener(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, ISettingsListener iSettingsListener) {
        int key = SettingServiceManager.getKey(module_id, typeDef);
        if (!this.mSettingsListenerLists.containsKey(Integer.valueOf(key))) {
            this.mSettingsListenerLists.put(Integer.valueOf(key), new ArrayList<>());
        }
        this.mSettingsListenerLists.get(Integer.valueOf(key)).add(iSettingsListener);
        if (this.mISettingsCallBacks.containsKey(Integer.valueOf(key))) {
            return;
        }
        this.mISettingsCallBacks.put(Integer.valueOf(key), new MyISettingsCallBack(key));
        ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
        if (iSettingServiceManager != null) {
            try {
                iSettingServiceManager.registerSettingsListener(key, this.mISettingsCallBacks.get(Integer.valueOf(key)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void unregisterSettingsListener(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, ISettingsListener iSettingsListener) {
        int key = SettingServiceManager.getKey(module_id, typeDef);
        if (this.mSettingsListenerLists.containsKey(Integer.valueOf(key)) && this.mSettingsListenerLists.get(Integer.valueOf(key)).contains(iSettingsListener)) {
            this.mSettingsListenerLists.get(Integer.valueOf(key)).remove(iSettingsListener);
            if (this.mSettingsListenerLists.get(Integer.valueOf(key)).isEmpty()) {
                this.mSettingsListenerLists.remove(Integer.valueOf(key));
                ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
                if (iSettingServiceManager != null) {
                    try {
                        iSettingServiceManager.unregisterSettingsListener(key, this.mISettingsCallBacks.get(Integer.valueOf(key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                this.mISettingsCallBacks.remove(Integer.valueOf(key));
            }
        }
    }

    protected void resetSettingsCallBacks() {
        if (this.mISettingsCallBacks.isEmpty()) {
            return;
        }
        for (Integer num : this.mISettingsCallBacks.keySet()) {
            ISettingServiceManager iSettingServiceManager = SettingServiceManager.getISettingServiceManager();
            if (iSettingServiceManager != null) {
                try {
                    iSettingServiceManager.registerSettingsListener(num.intValue(), this.mISettingsCallBacks.get(num));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyISettingsCallBack extends ISettingsCallBack.Stub {
        private final int mKey;

        private MyISettingsCallBack(int i) {
            this.mKey = i;
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsInt(int i, int i2, int i3) {
            if (SettingsManager.this.mSettingsListenerLists.containsKey(Integer.valueOf(this.mKey)) && SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)) != null) {
                for (int i4 = 0; i4 < SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)).size(); i4++) {
                    ((ISettingsListener) ((ArrayList) SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey))).get(i4)).onReceived(i, i2, Integer.valueOf(i3));
                }
            }
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsBytes(int i, int i2, byte[] bArr) {
            if (SettingsManager.this.mSettingsListenerLists.containsKey(Integer.valueOf(this.mKey)) && SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)) != null) {
                for (int i3 = 0; i3 < SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)).size(); i3++) {
                    ((ISettingsListener) ((ArrayList) SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey))).get(i3)).onReceived(i, i2, bArr);
                }
            }
        }

        @Override // com.hzbhd.setting.proxy.aidl.ISettingsCallBack
        public void onSettingsString(int i, int i2, String str) {
            if (SettingsManager.this.mSettingsListenerLists.containsKey(Integer.valueOf(this.mKey)) && SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)) != null) {
                for (int i3 = 0; i3 < SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey)).size(); i3++) {
                    ((ISettingsListener) ((ArrayList) SettingsManager.this.mSettingsListenerLists.get(Integer.valueOf(this.mKey))).get(i3)).onReceived(i, i2, str);
                }
            }
        }
    }

    public void setInt(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, int i3) {
        if (SettingServiceManager.getISettingServiceManager() != null) {
            try {
                SettingServiceManager.getISettingServiceManager().setInt(SettingServiceManager.getKey(module_id, typeDef), i, i2, i3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setBytes(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, byte[] bArr) {
        if (SettingServiceManager.getISettingServiceManager() != null) {
            try {
                SettingServiceManager.getISettingServiceManager().setBytes(SettingServiceManager.getKey(module_id, typeDef), i, i2, bArr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setString(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2, String str) {
        if (SettingServiceManager.getISettingServiceManager() != null) {
            try {
                SettingServiceManager.getISettingServiceManager().setString(SettingServiceManager.getKey(module_id, typeDef), i, i2, str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getInt(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2) {
        if (SettingServiceManager.getISettingServiceManager() == null) {
            return -1;
        }
        try {
            return SettingServiceManager.getISettingServiceManager().getInt(SettingServiceManager.getKey(module_id, typeDef), i, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public byte[] getBytes(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2) {
        if (SettingServiceManager.getISettingServiceManager() == null) {
            return null;
        }
        try {
            return SettingServiceManager.getISettingServiceManager().getBytes(SettingServiceManager.getKey(module_id, typeDef), i, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getString(SourceConstantsDef.MODULE_ID module_id, BodaSysContant.TypeDef typeDef, int i, int i2) {
        if (SettingServiceManager.getISettingServiceManager() == null) {
            return null;
        }
        try {
            return SettingServiceManager.getISettingServiceManager().getString(SettingServiceManager.getKey(module_id, typeDef), i, i2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getConfig(Configs.Module module) {
        if (SettingServiceManager.getISettingServiceManager() != null) {
            try {
                return SettingServiceManager.getISettingServiceManager().getConfig(module.ordinal());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public void notifyConfig(Configs.Module module, String str) {
        if (SettingServiceManager.getISettingServiceManager() != null) {
            try {
                SettingServiceManager.getISettingServiceManager().notifyConfig(module.ordinal(), str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
