package com.hzbhd.proxy.mcu.constant;

/* loaded from: classes2.dex */
public enum MCU_MESSAGE_PEER {
    MCU,
    DVR,
    BTIVT,
    TSMCU;

    public static MCU_MESSAGE_PEER getPeerByValue(int i) {
        for (MCU_MESSAGE_PEER mcu_message_peer : values()) {
            if (i == mcu_message_peer.ordinal()) {
                return mcu_message_peer;
            }
        }
        return null;
    }
}
