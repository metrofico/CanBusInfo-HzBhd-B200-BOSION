// ISystemStatusManager.aidl
package com.hzbhd.systemstatus.proxy.aidl;
import com.hzbhd.systemstatus.proxy.aidl.IServiceConnectCallback;

// Declare any non-default types here with import statements

interface ISystemStatusManager {
    // Service State
    void regitsterServiceConnectCallback(int module, IServiceConnectCallback callback);
    void unregisterServiceConnectCallback(int module, IServiceConnectCallback callback);
    void setServiceData(int moduleId, int dataId, String strData);
    String getServiceData(int moduleId, int dataId);

}