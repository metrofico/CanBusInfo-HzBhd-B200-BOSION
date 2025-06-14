package com.hzbhd.canbus.car_cus._299;

import com.hzbhd.canbus.CanbusMsgSender;
import com.hzbhd.canbus.adapter.util.FutureUtil;

/* loaded from: classes2.dex */
public class MessageSender {
    public static void sendMsg(byte[] bArr) {
        CanbusMsgSender.sendMsg(bArr);
    }

    public static void sendDzMsg(int i, int i2, int i3, int i4, int i5) {
        FutureUtil.instance.reportSmartPowerInfo(new byte[]{(byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5});
    }
}
