package com.hzbhd.proxy.mcu.interfaces;

/* loaded from: classes2.dex */
public interface ISerialPortDevice {
    void closeSerialDev(int i);

    int openSerialDev(int i);

    int readSerialData(int i, byte[] bArr, int i2, int i3);

    int setupSerialDev(int i, int i2, int i3, char c, int i4);

    int writeSerialData(int i, byte[] bArr, int i2);
}
