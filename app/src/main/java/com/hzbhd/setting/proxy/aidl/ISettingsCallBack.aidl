
package com.hzbhd.setting.proxy.aidl;

// Declare any non-default types here with import statements

interface ISettingsCallBack {
    void onSettingsInt(int func, int arg1, int arg2);
    void onSettingsBytes(int func, int arg1, in byte[] data);
    void onSettingsString(int func, int arg1, String strData);
}