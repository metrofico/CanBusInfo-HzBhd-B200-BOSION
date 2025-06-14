package com.hzbhd.midware.jni;

/* loaded from: classes2.dex */
public class SerialPort {
    public static native void jni_serial_close(int i);

    public static native int jni_serial_open(int i);

    public static native int jni_serial_read_data(int i, byte[] bArr, int i2, int i3);

    public static native int jni_serial_setup(int i, int i2, int i3, char c, int i4);

    public static native int jni_serial_write_data(int i, byte[] bArr, int i2);

    static {
        System.loadLibrary("SerialPort");
    }
}
