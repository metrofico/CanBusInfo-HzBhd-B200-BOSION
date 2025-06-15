package com.hzbhd.canbus.car._350;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgrKt {
    public static final int reverse(int i, int i2) {
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

    public static final String transToF(double d) {
        double d2 = 10;
        return (Math.rint(((d * 1.8d) + 32) * d2) / d2) + " °F";
    }

    public static final String transToC(double d) {
        double d2 = 10;
        return (Math.rint(d * d2) / d2) + " °C";
    }

    public static /* synthetic */ int reverse$default(int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i2 = 14;
        }
        return reverse(i, i2);
    }

    public static final <T> boolean isEqual(List<? extends T> first, List<? extends T> second) {


        if (first.size() != second.size()) {
            return false;
        }
        List<Pair> listZip = CollectionsKt.zip(first, second);
        if (!(listZip instanceof Collection) || !listZip.isEmpty()) {
            for (Pair pair : listZip) {
                if (!Intrinsics.areEqual(pair.component1(), pair.component2())) {
                    return false;
                }
            }
        }
        return true;
    }
}
