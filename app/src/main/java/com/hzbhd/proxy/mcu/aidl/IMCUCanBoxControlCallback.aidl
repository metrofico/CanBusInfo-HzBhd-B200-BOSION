package com.hzbhd.proxy.mcu.aidl;

// Declare any non-default types here with import statements

interface IMCUCanBoxControlCallback {
    void notifyCanboxData(int cmd, in byte[] data);
}
