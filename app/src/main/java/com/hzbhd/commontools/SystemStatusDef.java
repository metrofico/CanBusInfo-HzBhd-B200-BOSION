package com.hzbhd.commontools;

/* loaded from: classes2.dex */
public class SystemStatusDef {
    public static final int NOT_READY = -1;
    public static final int OFF = 0;
    public static final int ON = 1;

    public enum FAKE_POWEROFF_MODE {
        NORMAL,
        RUNNIG
    }

    public enum POWER_STATUS {
        ACC_ON,
        FAKE_POWER_OFF,
        ACC_OFF,
        SLEEP
    }

    public enum SLEEP_MODE {
        NORMAL
    }

    public static boolean isNotReady(int i) {
        return i == -1;
    }
}
