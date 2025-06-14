package com.hzbhd.canbus.car._369;

import com.hzbhd.canbus.R;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MsgMgr.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n\u001a\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e\u001a\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e\"\u001a\u0010\u0000\u001a\u00020\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005¨\u0006\u0011"}, d2 = {"lastKnobValue", "", "getLastKnobValue", "()B", "setLastKnobValue", "(B)V", "accurateTo", "", "double", "digits", "", "getOpenOrClose", "", "boolean", "", "getValidOrInvalid", "getValueOfBoolean", "CanBusInfo_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes2.dex */
public final class MsgMgrKt {
    private static byte lastKnobValue;

    public static final int getValueOfBoolean(boolean z) {
        return z ? 1 : 0;
    }

    public static final String getOpenOrClose(boolean z) {
        String string = InitUtilsKt.getMContext().getString(z ? R.string.open : R.string.close);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.run {\n    if (b…tString(R.string.close)\n}");
        return string;
    }

    public static final String getValidOrInvalid(boolean z) {
        String string = InitUtilsKt.getMContext().getString(z ? R.string.valid : R.string.invalid);
        Intrinsics.checkNotNullExpressionValue(string, "mContext.run {\n    if (b…tring(R.string.invalid)\n}");
        return string;
    }

    public static final double accurateTo(double d, int i) {
        double d2 = i;
        return Math.rint(d * d2) / d2;
    }

    public static final byte getLastKnobValue() {
        return lastKnobValue;
    }

    public static final void setLastKnobValue(byte b) {
        lastKnobValue = b;
    }
}
