package com.hzbhd.canbus.car._341;

import com.hzbhd.canbus.CanbusMsgSender;
import kotlin.Metadata;




public final class UiMgrKt {
    private static int param;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendSettingsData(int i, int i2) {
        CanbusMsgSender.sendMsg(new byte[]{22, -124, (byte) i, (byte) i2});
    }

    public static final int getParam() {
        return param;
    }

    public static final void setParam(int i) {
        param = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void sendAirConditionData(int i) {
        int i2 = param + 1;
        param = i2;
        if (i2 % 2 == 0) {
            CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) i, -52});
        } else {
            CanbusMsgSender.sendMsg(new byte[]{22, 51, (byte) i, 34});
        }
    }
}
