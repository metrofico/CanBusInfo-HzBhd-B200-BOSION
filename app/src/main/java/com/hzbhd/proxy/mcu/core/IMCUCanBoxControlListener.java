package com.hzbhd.proxy.mcu.core;

/* loaded from: classes2.dex */
public interface IMCUCanBoxControlListener {
    void notifyCanboxData(int i, byte[] bArr);

    void onMcuConn();
}
