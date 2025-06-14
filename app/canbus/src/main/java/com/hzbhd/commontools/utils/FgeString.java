package com.hzbhd.commontools.utils;

/* loaded from: classes2.dex */
public class FgeString {
    public static String bytes2HexString(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }

    public static String bytes2HexString(int[] iArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(iArr[i2] & 255);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }

    public static String Int2HexString(int[] iArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(iArr[i2]);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            str = str + hexString.toUpperCase() + " ";
        }
        return str;
    }

    public static String bytetoHexString(byte b) {
        String hexString = Integer.toHexString(b & 255);
        if (hexString.length() == 1) {
            hexString = '0' + hexString;
        }
        return hexString.toUpperCase() + " ";
    }

    public static String bytes2BcdString(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append((int) ((byte) ((bArr[i] & 240) >>> 4)));
            stringBuffer.append((int) ((byte) (bArr[i] & 15)));
        }
        return stringBuffer.toString();
    }
}
