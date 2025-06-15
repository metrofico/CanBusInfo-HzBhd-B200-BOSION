
package com.hzbhd.setting.proxy.aidl;
import com.hzbhd.setting.proxy.aidl.ISettingsCallBack;
import com.hzbhd.setting.proxy.aidl.IModuleCallBack;

// Declare any non-default types here with import statements

interface ISettingServiceManager {
    void registerSettingsListener(int key, ISettingsCallBack listener);
    void unregisterSettingsListener(int key, ISettingsCallBack listener);

    void setInt(int key, int func, int arg1, int arg2);
    void setBytes(int key, int func, int arg1, in byte[] data);
    void setString(int key, int func, int arg1, String strData);
    int getInt(int key, int func, int arg1);
    byte[] getBytes(int key, int func, int arg1);
    String getString(int key, int func, int arg1);

    void registerModuleListener(int key, IModuleCallBack listener);
    void unregisterModuleListener(int key, IModuleCallBack listener);
    void notifyInt(int key, int func, int arg1, int arg2);
    void notifyBytes(int key, int func, int arg1, in byte[] data);
    void notifyString(int key, int func, int arg1, String strData);
    String getConfig(int module);
    void notifyConfig(int module,String value);
}