package com.hzbhd.canbus.smartpower;

import com.hzbhd.canbus.smartpower.ISurgingPowerCallback;

/**
 * AIDL interface to communicate with surging power device.
 */
interface ISurgingPwerInterface {
    void sendMsg(in byte[] bArr);

    String registerCallBack(ISurgingPowerCallback callback);

    void unRegisterCallBack(String callbackId);
}