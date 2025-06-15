package com.hzbhd.proxy.aidl;

import com.hzbhd.proxy.callback.IDvrDataCallBack;

interface IDvrAidlInterface {

    void registerOnDataReadCallback(IDvrDataCallBack callback);

    void unregisterOnDataReadCallback(IDvrDataCallBack callback);

    void sendData(in byte[] bytes);
}