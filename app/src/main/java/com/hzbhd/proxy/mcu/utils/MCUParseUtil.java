package com.hzbhd.proxy.mcu.utils;

/* loaded from: classes2.dex */
public class MCUParseUtil {
    public static int getIntFromByteWithBit(byte b, int i, int i2) {
        return ((b + 256) >> i) & ((1 << i2) - 1);
    }

    public static byte[] intToByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] shortToByteArray(short s) {
        return new byte[]{(byte) ((s >> 8) & 255), (byte) (s & 255)};
    }

    public static int byteArrayToInt(byte[] bArr) {
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += (bArr[i2] & 255) << ((3 - i2) * 8);
        }
        return i;
    }

    public static short byteArrayToShort(byte[] bArr) {
        short s = 0;
        for (int i = 0; i < 2; i++) {
            s = (short) (s + ((bArr[i] & 255) << ((1 - i) * 8)));
        }
        return s;
    }
}
