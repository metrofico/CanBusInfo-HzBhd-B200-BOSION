package com.hzbhd.canbus.car._350;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0010\u0006\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u0007\u001a\n\u0010\n\u001a\u00020\u000b*\u00020\f\u001a\n\u0010\r\u001a\u00020\u000b*\u00020\f¨\u0006\u000e"}, d2 = {"isEqual", "", "T", "first", "", "second", "reverse", "", "param", "max", "transToC", "", "", "transToF", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
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
        Intrinsics.checkNotNullParameter(first, "first");
        Intrinsics.checkNotNullParameter(second, "second");
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
