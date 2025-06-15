package com.hzbhd.canbus.smartpower;

/**
 * AIDL interface for notifying surging power updates.
 */
interface ISurgingPowerCallback {
    /**
     * Callback method to notify surging power data.
     * @param data Byte array with surging power information.
     */
    void notifySurgingPower(in byte[] data);
}