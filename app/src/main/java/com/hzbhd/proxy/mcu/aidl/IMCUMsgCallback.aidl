
package com.hzbhd.proxy.mcu.aidl;

// Declare any non-default types here with import statements

interface IMCUMsgCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void onMsg(boolean isGet, in byte[] msg);
}