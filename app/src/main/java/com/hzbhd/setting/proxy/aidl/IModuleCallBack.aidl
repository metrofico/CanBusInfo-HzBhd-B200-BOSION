
package com.hzbhd.setting.proxy.aidl;

// Declare any non-default types here with import statements

interface IModuleCallBack {
    void onModuleInt(int func, int arg1, int arg2);
    void onModuleBytes(int func, int arg1, in byte[] data);
    void onModuleString(int func, int arg1, String strData);
    int getModuleInt(int func, int arg1);
    byte[] getModuleBytes(int func, int arg1);
    String getModuleString(int func, int arg1);
}