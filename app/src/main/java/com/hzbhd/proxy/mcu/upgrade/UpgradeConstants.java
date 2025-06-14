package com.hzbhd.proxy.mcu.upgrade;

/* loaded from: classes2.dex */
public class UpgradeConstants {

    public enum DevType {
        BOOT,
        BT,
        CANBOX,
        MCU,
        MPEG,
        HDMI
    }

    public enum UpgradeStartCheckStatus {
        FILE_TOO_LARGE,
        FILE_VERSION_ERROR,
        UNKNOWN_ERROR
    }

    public enum UpgradeStartEndStatus {
        READY,
        LOADING,
        SUCCESS,
        FAIL,
        CHECK_FIAL
    }
}
