package com.hzbhd.canbus.car._314;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgrKt {
    public static final int[] toIntArray(byte[] bArr) {

        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = bArr[i];
        }
        return iArr;
    }
}
