package com.hzbhd.proxy.mcu.core;

/* loaded from: classes2.dex */
public interface IMCUMainListener {
    void mcuInit(byte b, boolean z, boolean z2);

    void notifyCanboxVersion(String str);

    void notifyHardwareVersion(String str, String str2, String str3, String str4);

    void notifyMCUVersion(String str, String str2, String str3);

    void notifyPowerStatus(int i);

    void notifyScreenVersion(String str);
}
