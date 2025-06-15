package com.hzbhd.canbus.car._369;

import com.hzbhd.R;
import com.hzbhd.canbus.util.InitUtilsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;




public final class MsgMgrKt {
    private static byte lastKnobValue;

    public static final int getValueOfBoolean(boolean z) {
        return z ? 1 : 0;
    }

    public static final String getOpenOrClose(boolean z) {
        String string = InitUtilsKt.getMContext().getString(z ? R.string.open : R.string.close);

        return string;
    }

    public static final String getValidOrInvalid(boolean z) {
        String string = InitUtilsKt.getMContext().getString(z ? R.string.valid : R.string.invalid);

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
