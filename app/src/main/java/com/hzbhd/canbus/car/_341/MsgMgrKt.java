package com.hzbhd.canbus.car._341;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003\u001a\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006\u001a\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u0006\u001a\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\u0086\u0004¨\u0006\r"}, d2 = {"contentCompare", "", "one", "", "anotherOne", "getMsbLsbResult", "", "MSB", "LSB", "reverse", "param", "transTo", "", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(one, "one");
        Intrinsics.checkNotNullParameter(anotherOne, "anotherOne");
        return Arrays.equals(one, anotherOne);
    }

    public static final void transTo(int[] iArr, int[] one) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        Intrinsics.checkNotNullParameter(one, "one");
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            one[i] = iArr[i];
        }
    }
}
