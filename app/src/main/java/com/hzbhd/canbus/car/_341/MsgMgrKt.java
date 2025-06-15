package com.hzbhd.canbus.car._341;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgrKt {
    public static final int getMsbLsbResult(int i, int i2) {
        return ((i & 255) << 8) | (i2 & 255);
    }

    public static final int reverse(int i) {
        int i2 = 14;
        int i3 = 0;
        while (-1 < i2) {
            int i4 = i3 + 1;
            if (i3 == i) {
                return i2;
            }
            i2--;
            i3 = i4;
        }
        return 0;
    }

    public static final boolean contentCompare(int[] one, int[] anotherOne) {


        return Arrays.equals(one, anotherOne);
    }

    public static final void transTo(int[] iArr, int[] one) {


        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            one[i] = iArr[i];
        }
    }
}
