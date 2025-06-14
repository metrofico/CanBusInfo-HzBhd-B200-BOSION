package com.hzbhd.canbus.car._394;

import com.hzbhd.canbus.util.DataHandleUtils;

/* loaded from: classes2.dex */
public class NewUtil {
    public static int getBoolBit7(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 7, 1);
    }

    public static int getBoolBit6(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 6, 1);
    }

    public static int getBoolBit5(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 5, 1);
    }

    public static int getBoolBit4(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 4, 1);
    }

    public static int getBoolBit3(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 3, 1);
    }

    public static int getBoolBit2(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 2, 1);
    }

    public static int getBoolBit1(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 1, 1);
    }

    public static int getBoolBit0(int i) {
        return DataHandleUtils.getIntFromByteWithBit(i, 0, 1);
    }
}
