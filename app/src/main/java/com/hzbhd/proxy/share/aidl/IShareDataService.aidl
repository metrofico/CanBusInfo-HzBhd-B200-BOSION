package com.hzbhd.proxy.share.aidl;
import com.hzbhd.proxy.share.aidl.IShareIntCallback;
import com.hzbhd.proxy.share.aidl.IShareStringCallback;
import com.hzbhd.proxy.share.aidl.IShareBundleCallback;
import android.os.Bundle;

interface IShareDataService {

    int registerShareIntCallback(String key, IShareIntCallback callback);
    void unregisterShareIntCallback(String key, IShareIntCallback callback);
    int getInt(String key);
    void reportInt(String key,int value);

    void reportOtherInt(String key,int value,int from);

    String registerShareStringCallback(String key, IShareStringCallback callback);
    void unregisterShareStringCallback(String key, IShareStringCallback callback);
    String getString(String key);
    void reportString(String key,String value);

    void reportOtherString(String key,String value,int from);

    Bundle registerShareBundleCallback(String key, IShareBundleCallback callback);
    void unregisterShareBundleCallback(String key, IShareBundleCallback callback);
    Bundle getBundle(String key);
    void reportBundle(String key,inout Bundle value);
    void reportOtherBundle(String key,inout Bundle value,int from);
}
