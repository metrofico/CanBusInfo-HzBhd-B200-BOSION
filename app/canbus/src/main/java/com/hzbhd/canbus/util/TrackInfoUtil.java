package com.hzbhd.canbus.util;

import androidx.core.view.MotionEventCompat;

/* loaded from: classes2.dex */
public class TrackInfoUtil {
    private static final int TRACK_DATA_LENGTH = 16;
    public static final int TRACK_MAX_ANGLE = 25;

    private static int getOffset(int i) {
        return 16 - i;
    }

    public static int getTrackAngle0(byte b, byte b2, int i, int i2, int i3) {
        return culTrackAngle((short) (((short) (((short) (((short) ((b & 255) | ((b2 << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK))) - i)) << getOffset(i3))) >> getOffset(i3)), i2 - i);
    }

    public static int getTrackAngle1(byte b, byte b2, int i, int i2, int i3) {
        short s = (short) ((b & 255) | ((b2 << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK));
        int i4 = i3 - 1;
        short s2 = (short) (1 << i4);
        return culTrackAngle((short) (((short) (((short) (s & ((short) (~s2)))) ^ (((short) ((s & s2) << getOffset(i3))) >> 15))) + (s2 >> i4)), i2 - i);
    }

    public static int culTrackAngle(short s, int i) {
        return DataHandleUtils.rangeNumber((int) (s / (i / 25.0f)), -25, 25);
    }
}
